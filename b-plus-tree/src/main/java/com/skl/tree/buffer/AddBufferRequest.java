package com.skl.tree.buffer;

public class AddBufferRequest {
    public static AddBufferRequest createAddBufferRequest(Object value){
        AddBufferRequest request = new AddBufferRequest(value);
        return request;
    }

    public AddBufferRequest(Object value){
        this.value = value;
    }
    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
