package com.chenyoyo.plugin.bridge;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class FileUtilInject {
    public static void injectStart(MethodVisitor mv, String key) {
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/qibu/sdk/myapplication/aop/life/LifeUtil", "getInstance", "()Lcom/qibu/sdk/myapplication/aop/life/LifeUtil;", false);
        mv.visitLdcInsn(key);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/qibu/sdk/myapplication/aop/life/LifeUtil", "markStartTime", "(Ljava/lang/String;)V", false);
    }

    public static void upLoadMethod(MethodVisitor mv, String key) {
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/qibu/sdk/myapplication/aop/life/LifeUtil", "getInstance", "()Lcom/qibu/sdk/myapplication/aop/life/LifeUtil;", false);
        mv.visitLdcInsn(key);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/qibu/sdk/myapplication/aop/life/LifeUtil", "uploadTime", "(Ljava/lang/String;)V", false);
    }

}
