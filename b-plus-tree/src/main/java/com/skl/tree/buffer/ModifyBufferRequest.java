package com.skl.tree.buffer;

public class ModifyBufferRequest<T extends ModifyBufferRequest> extends BufferRequest{
    private int startOffset;
    public static final ModifyBufferRequest createModifyBufferRequest(Object value){
        ModifyBufferRequest request = new ModifyBufferRequest(value);
        return request;
    }
    public ModifyBufferRequest(Object value){
        super(value);
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

    public T self(){
        return (T)this;
    }
}
