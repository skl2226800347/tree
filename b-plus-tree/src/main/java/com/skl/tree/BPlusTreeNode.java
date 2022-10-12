package com.skl.tree;

import com.skl.tree.constatns.CompareConstants;
import com.skl.tree.utils.CompareUtil;

import java.io.Serializable;

public class BPlusTreeNode<T extends BPlusTreeNode> implements Serializable {
    private boolean root=false;
    private int degree;
    private int startOffset;
    private Object[] keys;
    private Offset[] offsets;
    private boolean stored;
    private int number;
    private int storeNumber;
    public BPlusTreeNode(int degree){
        this.degree = degree;
        this.number = (degree *2);
        keys= new Object[number];
        offsets = new Offset[number];
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

    public T startOffset(int startOffset){
        this.startOffset = startOffset;
        return self();
    }

    public T stored(boolean stored){
        this.stored = stored;
        return self();
    }

    public T self(){
        return (T)this;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(int storeNumber) {
        this.storeNumber = storeNumber;
    }
}
