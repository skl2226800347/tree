package com.skl.tree.utils;

import java.util.Objects;

public class KeyCompareUtil {
    public static final int GREATER = 1;
    public static final int LESS = -1;
    public static final int EQUAL =0;
    public static final int compare(Object oldObj,Object newObj){
        if(oldObj == null){
            return GREATER;
        }
        Objects.requireNonNull(newObj,"newObj not is null");
        if(oldObj.getClass() != newObj.getClass()) {
            Objects.requireNonNull(newObj,"newObj not is null");
        }
        if (oldObj instanceof String){
            String str = (String) oldObj;
            return str.compareTo((String)newObj);
        }
        return EQUAL;
    }

}
