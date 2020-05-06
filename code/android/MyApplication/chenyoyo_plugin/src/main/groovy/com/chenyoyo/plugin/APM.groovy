package com.chenyoyo.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

import static org.objectweb.asm.ClassReader.EXPAND_FRAMES

public class APM extends Transform implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        AppExtension extension = project.getExtensions().getByType(AppExtension.class);
        extension.registerTransform(this);
    }

    @Override
    public String getName() {
        return "LifecyclePlugin";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }


    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        System.out.println("lifecycle visit start");
        long startTime = System.currentTimeMillis();
        Collection<TransformInput> inputs = transformInvocation.getInputs();
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();
        if (outputProvider != null) {
            outputProvider.deleteAll();
        }

        for (TransformInput input : inputs) {
            for (DirectoryInput directoryInput : input.getDirectoryInputs()) {
                handleDirectoryInput(directoryInput, outputProvider);
            }

            for (JarInput jar : input.getJarInputs()) {
                handleJarInput(jar, outputProvider);
            }
        }
        System.out.println("lifecycle visit end");
        System.out.println("lifecycle visit cost time is " + (System.currentTimeMillis() - startTime));
    }

    void handleDirectoryInput(DirectoryInput input, TransformOutputProvider outputProvider) {
        if (input.getFile().isDirectory()) {
            input.file.eachFileRecurse {
                File f ->
                    String fileName = f.getName();
                    if (checkClassFile(fileName)) {
                        System.out.println("handle class " + fileName);
                        try {
                            ClassReader classReader = new ClassReader(FileUtils.readFileToByteArray(f));
                            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
                            ClassVisitor visitor = new LifecycleClassVisitor(classWriter);
                            classReader.accept(visitor, ClassReader.EXPAND_FRAMES);
                            byte[] code = classWriter.toByteArray();
                            FileOutputStream fileOutputStream = new FileOutputStream(Objects.requireNonNull(f.getParentFile()).getAbsolutePath() + File.separator + fileName);
                            fileOutputStream.write(code);
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("handle class err " + e.getMessage());
                        }
                    }
            };
        }
        File dest = outputProvider.getContentLocation(input.getName(),
                input.getContentTypes(), input.getScopes(),
                Format.DIRECTORY);
        try {
            FileUtils.copyDirectory(input.getFile(), dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void handleJarInput(JarInput jarInput, TransformOutputProvider outputProvider) throws IOException {
        if (jarInput.getFile().getAbsolutePath().endsWith(".jar")) {
            String jarName = jarInput.getName();
            String md5Name = DigestUtils.md5Hex(jarInput.getFile().getAbsolutePath());
            if (jarName.endsWith(".jar")) {
                jarName = jarName.substring(0, jarName.length() - 4);
            }
            JarFile jarFile = new JarFile(jarInput.getFile());
            Enumeration enumeration = jarFile.entries();
            File tmpFile = new File(jarInput.getFile().getParent() + File.separator + "classes_temp.jar");
            //避免上次的缓存被重复插入
            if (tmpFile.exists()) {
                tmpFile.delete();
            }
            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(tmpFile));
            //用于保存
            while (enumeration.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) enumeration.nextElement();
                String entryName = jarEntry.getName();
                ZipEntry zipEntry = new ZipEntry(entryName);
                InputStream inputStream = jarFile.getInputStream(jarEntry);
                if (checkClassFile(entryName)) {
                    System.out.println("deal with \"jar\" class file <" + entryName + "> -----------");
                    jarOutputStream.putNextEntry(zipEntry);
                    ClassReader classReader = new ClassReader(IOUtils.toByteArray(inputStream));
                    ClassWriter classWriter = new ClassWriter(classReader, org.objectweb.asm.ClassWriter.COMPUTE_MAXS);
                    ClassVisitor cv = new LifecycleClassVisitor(classWriter);
                    classReader.accept(cv, EXPAND_FRAMES);
                    byte[] code = classWriter.toByteArray();
                    jarOutputStream.write(code);
                } else {
                    jarOutputStream.putNextEntry(zipEntry);
                    jarOutputStream.write(IOUtils.toByteArray(inputStream));
                }
                jarOutputStream.closeEntry();
            }
            jarOutputStream.close();
            jarFile.close();
            File dest = outputProvider.getContentLocation(jarName + md5Name,
                    jarInput.getContentTypes(), jarInput.getScopes(), Format.JAR);
            FileUtils.copyFile(tmpFile, dest);
            tmpFile.delete();
        }
    }

    static boolean checkClassFile(String name) {
        return (name.endsWith(".class") && !name.startsWith("R\$")
                && "R.class" != name && "BuildConfig.class" != name);
    }
}
