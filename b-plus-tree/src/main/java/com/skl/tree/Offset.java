package com.skl.tree;

import java.io.Serializable;

public class Offset implements Serializable {
    private long physicalOffset;
    private int size;

    public long getPhysicalOffset() {
        return physicalOffset;
    }

    public void setPhysicalOffset(long physicalOffset) {
        this.physicalOffset = physicalOffset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
