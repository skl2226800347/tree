package com.skl.tree;

import com.skl.tree.constatns.CompareConstants;
import com.skl.tree.utils.CompareUtil;

import java.io.Serializable;

public class BPlusTreeNode implements Serializable {
    private boolean root=false;
    private int degree;
    private int startOffset;
    private Object[] keys;
    private Offset[] offsets;
    private boolean stored;
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

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public void setStartOffset(int startOffset) {
        this.startOffset = startOffset;
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

    public boolean isStored() {
        return stored;
    }

    public void setStored(boolean stored) {
        this.stored = stored;
    }
}
