package com.chenyoyo.plugin;

import com.chenyoyo.plugin.bridge.FileUtilInject;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;


public class LifecycleOnCreateMethodVisitor extends AdviceAdapter {

    protected LifecycleOnCreateMethodVisitor(int i, MethodVisitor methodVisitor, int i1, String s, String s1) {
        super(i, methodVisitor, i1, s, s1);
    }


    @Override
    protected void onMethodEnter() {
        FileUtilInject.injectStart(mv, "onCreate");
        super.onMethodEnter();
    }


    @Override
    protected void onMethodExit(int i) {
        super.onMethodExit(i);
        FileUtilInject.upLoadMethod(mv, "onCreate");
    }

}
