package com.jdtx.tree;

import java.util.*;

public class TreeNodeIterble<O> implements Iterable<O> {

    TreeNodeIterator<O> iterator;

    public TreeNodeIterble(TreeNode<O> node) {
        super();
        this.iterator = new TreeNodeIterator<>(node);
    }

    @Override
    public Iterator<O> iterator() {
        return iterator;
    }

}
