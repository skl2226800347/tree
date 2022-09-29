package com.skl.tree;

import java.io.Serializable;

public class BPlusTreeNode implements Serializable {
    private int degree;
    private Object key;
    private BPlusTreeNode[] treeNodes;
    private Object[] address;
    public BPlusTreeNode(int degree){
        this.degree = degree;
        treeNodes= new BPlusTreeNode[degree*2];
        address = new Object[degree*2];
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public BPlusTreeNode[] getTreeNodes() {
        return treeNodes;
    }

    public void setTreeNodes(BPlusTreeNode[] treeNodes) {
        this.treeNodes = treeNodes;
    }

    public Object[] getAddress() {
        return address;
    }

    public void setAddress(Object[] address) {
        this.address = address;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }
}
