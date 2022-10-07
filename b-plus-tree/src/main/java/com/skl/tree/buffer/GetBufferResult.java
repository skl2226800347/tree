package com.skl.tree.buffer;

public class GetBufferResult<T extends GetBufferResult> {
    public static final GetBufferResult createGetBufferResult(){
        return new GetBufferResult();
    }
    byte[] bytes;
    private Object value;

    public T value(Object value){
        this.value = value;
        return self();
    }

    public T bytes(byte[] bytes){
        this.bytes = bytes;
        return self();
    }


    public T self(){
        return (T)this;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
