package com.jdtx.state.impl;

import com.jdtx.state.*;
import com.jdtx.tree.*;

public class StateItemStackNamedImpl extends StateItemStackImpl implements StateItemStackNamed {

    public static String keyForStateItemName = "__name__";
    public static String defaultStateItemName = "__default__";


    // ---
    // StateItemStack
    // ---

    public void start() {
        start(defaultStateItemName);
    }


    // ---
    // StateItemStackNamed
    // ---

    public void start(String name) {
        if (name == null) {
            name = defaultStateItemName;
        }

        // Ищем ранее запускавшийся дочерний элемент с указанным именем
        ITreeNode<StateItem> childNode = findChildByName(stackPoint, name);

        if (childNode != null) {
            // Нашли ранее запускавшийся дочерний элемент
            // Запускаем его снова
            StateItem item = childNode.getItem();
            item.start();
            // Кладем его на вершину стека
            stackPoint = childNode;
        } else {
            // Такой элемент ранее не запускали
            // Стартуем новый элемент
            super.start();
            // Даем новому элементу имя
            StateItem item = super.get();
            item.setValue(keyForStateItemName, name);
        }
    }

    public void remove(String name) {
        ITreeNode<StateItem> node = findChildByName(stackPoint, name);
        if (node != null) {
            node.getItem().stop();
            stackPoint.getChildNodes().remove(node);
        }
    }

    /**
     * Возвращает элемент по имени с текущего уровня стека.
     *
     * @param name имя элемента
     * @return элемент
     */
    private ITreeNode<StateItem> findChildByName(ITreeNode<StateItem> owner, String name) {
        for (ITreeNode<StateItem> childNode : owner.getChildNodes()) {
            if (childNode.getItem().getValue(keyForStateItemName).equals(name)) {
                return childNode;
            }
        }
        return null;
    }


}
