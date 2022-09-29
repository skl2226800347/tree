package com.skl.tree.utils;

import java.util.Collection;

public class CollectionUtil {

    public static final boolean isEmpty(Collection<?> collections){
        if(collections == null || collections.size()<=0){
            return true;
        }
        return false;
    }

    public static final boolean isNotEmpty(Collection<?> collections) {
        return (!isEmpty(collections));
    }
}
