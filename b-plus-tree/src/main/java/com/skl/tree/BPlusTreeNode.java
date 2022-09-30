package com.skl.tree;

import com.skl.tree.constatns.Constatns;
import com.skl.tree.utils.KeyCompareUtil;

import java.io.Serializable;

public class BPlusTreeNode implements Serializable {
    private int degree;
    private Object[] keys;
    private long[] address;
    public BPlusTreeNode(int degree){
        this.degree = degree;
        keys= new Object[degree*2];
        address = new long[degree*2];
    }

    public BPlusTreeNode(int degree,Object key){
        this.degree = degree;
        keys= new Object[degree*2];
        keys[0]=key;
        address = new long[degree*2];
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }


    public int compare(Object key){
        Object firstKey = this.keys[0];
        int firstCompare = KeyCompareUtil.compare(firstKey,key);
        if(firstCompare <= Constatns.EQUAL){
            return firstCompare;
        }

    }

}
