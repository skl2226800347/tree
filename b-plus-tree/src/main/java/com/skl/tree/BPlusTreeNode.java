package com.skl.tree;

import com.skl.tree.constatns.CompareConstants;
import com.skl.tree.utils.CompareUtil;

import java.io.Serializable;

public class BPlusTreeNode implements Serializable {
    private int degree;
    private Object[] keys;
    private Offset[] offsets;
    public BPlusTreeNode(int degree){
        this.degree = degree;
        keys= new Object[degree*2];
        offsets = new Offset[degree*2];
    }

    public BPlusTreeNode(int degree,Object key){
        this.degree = degree;
        keys= new Object[degree*2];
        keys[0]=key;
        offsets = new Offset[degree*2];
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }


    public int compare(Object key){
        Object firstKey = this.keys[0];
        int firstCompare = CompareUtil.compare(firstKey,key);
        if(firstCompare <= CompareConstants.EQUAL){
            return firstCompare;
        }
        return 0;
    }

    public Object[] getKeys() {
        return keys;
    }

    public void setKeys(Object[] keys) {
        this.keys = keys;
    }

    public Offset[] getOffsets() {
        return offsets;
    }

    public void setOffsets(Offset[] offsets) {
        this.offsets = offsets;
    }
}
