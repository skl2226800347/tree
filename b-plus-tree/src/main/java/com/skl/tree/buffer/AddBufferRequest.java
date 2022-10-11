package com.skl.tree.buffer;

public class AddBufferRequest extends BufferRequest{

    public AddBufferRequest(Object value){
        super(value);
    }

    public static AddBufferRequest createAddBufferRequest(Object value){
        AddBufferRequest request = new AddBufferRequest(value);
        return request;
    }


}
