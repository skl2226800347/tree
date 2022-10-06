package com.skl.tree.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EncoderUtil {

    public static final byte[] encoder(Object obj) throws IOException {
        if(obj == null){
            return null;
        }
        byte[] bytes;
        /*if(obj instanceof String){
            bytes = ((String)obj).getBytes();
            return bytes;
        }else if (obj instanceof Serializable){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        }*/
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        return baos.toByteArray();
       // throw new IllegalArgumentException("不支持");
    }
}
