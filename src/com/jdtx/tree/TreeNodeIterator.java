package com.jdtx.tree;

import java.util.*;

public class TreeNodeIterator<O> implements Iterator<O> {

    int index;
    TreeNode<O> node;

    public TreeNodeIterator(TreeNode<O> node) {
        super();
        index = 0;
        this.node = node;
    }

    @Override
    public boolean hasNext() {
        return index < node.childs.size();
    }

    @Override
    public O next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        } else {
            O obj = node.getChildNodes().get(index).getItem();
            this.index = this.index + 1;
            return obj;
        }
    }

}
