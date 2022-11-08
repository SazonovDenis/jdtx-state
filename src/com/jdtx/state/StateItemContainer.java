package com.jdtx.state;

import com.jdtx.state.impl.*;
import com.jdtx.tree.*;

public class StateItemContainer {

    ITreeNode<StateItem> root;

    ITreeNode<StateItem> stackPoint;

    public StateItemContainer() {
        root = new TreeNode<>(null);
        stackPoint = root;
    }

    public ITreeNode<StateItem> getRoot() {
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

    public StateItem current() {
        return stackPoint.getItem();
    }

}
