package com.skl.tree.utils;

import java.util.Objects;

import static com.skl.tree.constatns.Constatns.EQUAL;
import static com.skl.tree.constatns.Constatns.GREATER;

public class KeyCompareUtil {

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
