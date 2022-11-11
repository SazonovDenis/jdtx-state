package com.jdtx.state.impl;

import com.jdtx.state.*;
import com.jdtx.tree.*;

import java.util.*;

public class StateItemStackThread implements StateItemStackNamed {

    public static StateItemStackThread stateItemStack = new StateItemStackThread();

    private Map<Thread, StateItemStackNamed> stackByThread = new HashMap<>();

    synchronized public void start() {
        StateItemStack stateItemStack = getOrCreate();
        stateItemStack.start();
    }

    synchronized public void stop() {
        StateItemStack stateItemStack = getOrCreate();
        stateItemStack.stop();
    }

    synchronized public StateItem get() {
        StateItemStack stateItemStack = getOrCreate();
        return stateItemStack.get();
    }

    // todo копию!!!!!
    synchronized public ITreeNode<StateItem> getAll() {
        ITreeNode<StateItem> nodeAllThreadRoot = new TreeNode<>(null);

        for (StateItemStack stateItemStack : stackByThread.values()) {
            nodeAllThreadRoot.addChildNode(stateItemStack.getAll());
        }

        return nodeAllThreadRoot;
    }

    synchronized public void start(String name) {
        StateItemStackNamed stateItemStack = getOrCreate();
        stateItemStack.start(name);
    }

    synchronized public void remove(String name) {
        StateItemStackNamed stateItemStack = getOrCreate();
        stateItemStack.remove(name);
    }

    private StateItemStackNamed getOrCreate() {
        Thread thread = Thread.currentThread();

        StateItemStackNamed stateItemStack = stackByThread.get(thread);
        if (stateItemStack == null) {
            stateItemStack = new StateItemStackNamedImpl();
            stackByThread.put(thread, stateItemStack);
            ((StateItemStackNamedImpl) stateItemStack).root.getItem().setValue("__thread__", thread);
        }

        return stateItemStack;
    }

}
