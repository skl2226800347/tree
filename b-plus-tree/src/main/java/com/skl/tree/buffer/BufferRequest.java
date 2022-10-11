package com.skl.tree.buffer;

import java.io.Serializable;

public class BufferRequest<T extends BufferRequest> implements Serializable {
    private Object value;

    public BufferRequest(Object value){
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    public T self(){
        return (T)this;
    }

}
