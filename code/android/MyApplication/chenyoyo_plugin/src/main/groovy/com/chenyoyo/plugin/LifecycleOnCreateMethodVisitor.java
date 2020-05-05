package com.chenyoyo.plugin;

import com.chenyoyo.plugin.bridge.FileUtilInject;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LifecycleOnCreateMethodVisitor extends MethodVisitor {

    public LifecycleOnCreateMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM4, mv);
    }

    @Override
    public void visitCode() {
        FileUtilInject.injectStart(mv, "onCreate");
        super.visitCode();
        FileUtilInject.upLoadMethod(mv, "onCreate");
    }
}
