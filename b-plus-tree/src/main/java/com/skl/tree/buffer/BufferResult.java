package com.skl.tree.buffer;

public class BufferResult<T extends BufferResult> {

    public static final BufferResult createBufferResult(){
        return new BufferResult();
    }
    private int offset;
    private int size;
    private int pageOffset;
    private int pageSize;

    public T offset(int offset){
        this.offset = offset;
        return self();
    }

    public T size(int size){
        this.size = size;
        return self();
    }

    public T self(){
        return  (T)this;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public T pageOffset(int pageOffset){
        this.pageOffset = pageOffset;
        return self();
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public T pageSize(int pageSize){
        this.pageSize = pageSize;
        return self();
    }
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
