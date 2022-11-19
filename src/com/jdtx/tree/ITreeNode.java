package com.jdtx.tree;

import java.util.*;

/**
 * Универсальное дерево
 *
 * @param <O> Класс, экземпляры которого хранятся в узлах дерева
 */
public interface ITreeNode<O> {

    O getItem();

    ITreeNode<O> getParent();

    void setParent(ITreeNode<O> node);

    /**
     * Есть ли дочерние
     */
    int getChildsCount();

    /**
     * Добавить дочерний объект
     */
    ITreeNode<O> addChild(O obj);

    /**
     * Добавить дочерний узел
     */
    void addChildNode(ITreeNode<O> node);

    /**
     * Дочерние узлы. Всегда не null.
     */
    List<ITreeNode<O>> getChildNodes();

    /**
     * Дочерние узлы через итератор. Всегда не null.
     * Чтобы можно было итерировать экземпляры класса <O>, а не класса IUtTreeNode<O>.
     */
    Iterable<O> getChilds();

}
