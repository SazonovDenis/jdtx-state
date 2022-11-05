package com.jdtx.tree;

import java.util.*;

public class TreeNode<O> implements ITreeNode<O> {

    O item;
    ITreeNode<O> parent;
    List<ITreeNode<O>> childs;

    public TreeNode(O item) {
        super();
        this.item = item;
        this.parent = null;
        this.childs = new ArrayList<>();
    }

    @Override
    public O getItem() {
        return item;
    }

    @Override
    public ITreeNode<O> getParent() {
        return parent;
    }

    @Override
    public void setParent(ITreeNode<O> node) {
        parent = node;
    }

    @Override
    public int getChildsCount() {
        return childs.size();
    }

    @Override
    public ITreeNode<O> addChild(O obj) {
        ITreeNode<O> node = new TreeNode<>(obj);
        node.setParent(this);
        childs.add(node);
        return node;
    }

    @Override
    public void addChildNode(ITreeNode<O> node) {
        node.setParent(this);
        childs.add(node);
    }

    @Override
    public List<ITreeNode<O>> getChildNodes() {
        return childs;
    }

    @Override
    public Iterable<O> getChilds() {
        TreeNodeIterble<O> iterator = new TreeNodeIterble<>(this);
        return iterator;
    }

}
