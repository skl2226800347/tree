package com.skl.tree;

import java.io.Serializable;

public class BPlusTree implements Serializable {
    private int degree;
    private BPlusTree root;
    public BPlusTree(int degree){
        this.degree= degree;
        root = new BPlusTree(degree);
    }

    public void insert(Object key){
        r
    }

}
