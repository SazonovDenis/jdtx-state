package com.jdtx.state.impl;

import com.jdtx.state.*;
import com.jdtx.tree.*;

public class StateItemStackImpl implements StateItemStack {


    ITreeNode<StateItem> root;

    ITreeNode<StateItem> stackPoint;

    public StateItemStackImpl() {
        root = new TreeNode<>(new StateItemImpl());
        stackPoint = root;
    }


    // ---
    // StateItemContainerStack
    // ---

    public ITreeNode<StateItem> getAll() {
        return root;
    }

    public void start() {
        // Создаем элемент
        StateItem stateItem = new StateItemImpl();
        stateItem.start();

        // Кладем в стек
        stackPoint = stackPoint.addChild(stateItem);
    }

    public void stop() {
        ITreeNode<StateItem> currentStackPointNode = stackPoint;

        if (currentStackPointNode.getParent() == null) {
            return;
        }

        // Забираем элемент
        StateItem stateItem = currentStackPointNode.getItem();
        stateItem.stop();

        // Извлекаем из стека
        stackPoint = currentStackPointNode.getParent();
        //stackPoint.getChildNodes().remove(currentStackPointNode);
    }

    public StateItem get() {
        return stackPoint.getItem();
    }


}
