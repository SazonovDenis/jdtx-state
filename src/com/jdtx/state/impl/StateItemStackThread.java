package com.jdtx.state.impl;

import com.jdtx.state.*;
import com.jdtx.tree.*;

import java.lang.reflect.*;
import java.util.*;

/**
 * Потокобезопасная реализация StateItemStackNamed
 */
public class StateItemStackThread implements StateItemStackNamed {

    private Map<Thread, StateItemStackNamed> stackByThread = new HashMap<>();

    synchronized public void start() {
        StateItemStack stateItemStack = getStateItemStackForTread();
        stateItemStack.start();
    }

    synchronized public void stop() {
        StateItemStack stateItemStack = getStateItemStackForTread();
        stateItemStack.stop();
    }

    synchronized public StateItem get() {
        StateItemStack stateItemStack = getStateItemStackForTread();
        return stateItemStack.get();
    }

    synchronized public ITreeNode<StateItem> getAll() throws Exception {
        ITreeNode<StateItem> nodeAllThreadRoot = new TreeNode<>(null);

        //
        for (StateItemStack stateItemStack : stackByThread.values()) {
            ITreeNode<StateItem> nodeThread = stateItemStack.getAll();
            ITreeNode<StateItem> nodeThreadCopy = cloneTreeNode(nodeThread);
            nodeAllThreadRoot.addChildNode(nodeThreadCopy);
        }

        //
        return nodeAllThreadRoot;
    }

    synchronized public void start(String name) {
        StateItemStackNamed stateItemStack = getStateItemStackForTread();
        stateItemStack.start(name);
    }

    synchronized public void remove(String name) {
        StateItemStackNamed stateItemStack = getStateItemStackForTread();
        stateItemStack.remove(name);
    }

    private StateItemStackNamed getStateItemStackForTread() {
        Thread thread = Thread.currentThread();

        //
        StateItemStackNamed stateItemStack = stackByThread.get(thread);
        if (stateItemStack == null) {
            stateItemStack = new StateItemStackNamedImpl();
            stackByThread.put(thread, stateItemStack);
            ((StateItemStackNamedImpl) stateItemStack).root.getItem().setValue("__thread__", thread);
        }

        //
        return stateItemStack;
    }

    public ITreeNode<StateItem> cloneTreeNode(ITreeNode<StateItem> node) throws Exception {
        StateItem cloneItem = cloneStateItem(node.getItem());
        ITreeNode<StateItem> cloneNode = new TreeNode<>(cloneItem);

        //
        for (ITreeNode<StateItem> nodeChild : node.getChildNodes()) {
            cloneNode.addChildNode(cloneTreeNode(nodeChild));
        }

        //
        return cloneNode;
    }

    public StateItem cloneStateItem(StateItem item) throws Exception {
        Constructor<?> constructor = item.getClass().getConstructor();
        StateItem clone = (StateItem) constructor.newInstance();

        //
        clone.assign(item);

        //
        return clone;
    }

}
