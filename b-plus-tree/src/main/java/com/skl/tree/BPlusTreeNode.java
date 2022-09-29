package com.skl.tree;

import java.io.Serializable;

public class BPlusTreeNode implements Serializable {
    private int degree;
    private BPlusTreeNode[] treeNodes;
    private Object[] address;
    public BPlusTreeNode(int degree){
        this.degree = degree;
        treeNodes= new BPlusTreeNode[degree*2];
        address = new Object[degree*2];
    }

}
