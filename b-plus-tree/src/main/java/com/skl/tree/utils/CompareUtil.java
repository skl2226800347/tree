package com.skl.tree.utils;

import com.skl.tree.constatns.CompareConstants;

import java.util.Objects;

public class CompareUtil {

    public static final boolean isCanInsert(Object curKey,Object nextKey,Object key){
        if(curKey == null && nextKey == null){
            return true;
        }
        if(curKey == null && compare(key,nextKey) <= CompareConstants.EQUAL){
            return true;
        }
        if(curKey == null && compare(key,nextKey) > CompareConstants.EQUAL){
            return false;
        }
        if(compare(key,curKey) >= CompareConstants.EQUAL && compare(key,nextKey) <= CompareConstants.EQUAL){
            return true;
        }


        return true;
    }


    public static final int compare(Object preObj,Object obj){
        if(preObj == null){
            throw new NullPointerException("preObj is null");
        }
        if(obj == null) {
            throw  new NullPointerException("obj is null");
        }
        if(preObj.getClass() != obj.getClass()) {
            Objects.requireNonNull(preObj,"newObj not is null");
        }
        if (preObj instanceof String){
            String str = (String) obj;
            return str.compareTo((String)preObj);
        }
        throw new IllegalArgumentException("不支持");
    }

}
