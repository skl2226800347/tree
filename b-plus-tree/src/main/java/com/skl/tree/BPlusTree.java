package com.skl.tree;

import java.io.Serializable;

public class BPlusTree implements Serializable {
    private int degree;
    private BPlusTreeNode root;
    public BPlusTree(int degree){
        this.degree= degree;
        root = new BPlusTreeNode(degree);
    }

    public void insert(Object key){
        for(BPlusTreeNode bPlusTreeNode :root.getTreeNodes()){

        }
    }

}
