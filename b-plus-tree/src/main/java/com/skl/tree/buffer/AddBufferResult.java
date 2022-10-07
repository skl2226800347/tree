package com.skl.tree.buffer;

public class AddBufferResult<T extends AddBufferResult> {

    public static final AddBufferResult createAddBufferResult(){
        return new AddBufferResult();
    }
    private int offset;
    private int size;

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
}
