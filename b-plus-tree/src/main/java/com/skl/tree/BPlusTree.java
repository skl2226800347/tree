package com.skl.tree;

import com.skl.tree.utils.CompareUtil;

import java.io.Serializable;

public class BPlusTree implements Serializable {
    private int degree;
    private BPlusTreeNode root;
    private TreeMappedFile treeMappedFile;
    public BPlusTree(int degree,TreeMappedFile treeMappedFile){
        this.degree= degree;
        root = new BPlusTreeNode(degree);
        this.treeMappedFile = treeMappedFile;
    }

    public void insert(Object key){
        boolean isCanInsert = isCanInsertCondition(root,key);
        if(isCanInsert){
            doInsert(key,root);
            return ;
        }
        for(int i=0 ;i<root.getKeys().length;i++){
            Object preKey = root.getKeys()[i];

        }
    }
    private void doInsert(Object key,BPlusTreeNode bPlusTreeNode){
        int keyLength = bPlusTreeNode.getKeys().length;
        for(int i=0;i<keyLength;i++) {
            Object curKey=bPlusTreeNode.getKeys()[i];
            if(curKey == null){

            }else {
                int compareValue = CompareUtil.compare(curKey, key);
            }
        }
    }


    public static final boolean isCanInsertCondition(BPlusTreeNode bPlusTreeNode,Object key){
        if(bPlusTreeNode == null){
            return false;
        }
        Object first = bPlusTreeNode.getKeys()[0];
        Object last = bPlusTreeNode.getKeys()[bPlusTreeNode.getKeys().length-1];
        int startCompare = CompareUtil.compare(first,key);
        int endCompare = CompareUtil.compare(last,key);
        if(startCompare>=0 && endCompare <0){
            return true;
        }
        return  false;
    }

}
