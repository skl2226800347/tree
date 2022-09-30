package com.skl.tree;

import com.skl.tree.utils.KeyCompareUtil;

import java.io.Serializable;

public class BPlusTree implements Serializable {
    private int degree;
    private BPlusTreeNode root;
    public BPlusTree(int degree){
        this.degree= degree;
        root = new BPlusTreeNode(degree);
    }

    public void insert(Object key){
        boolean include = include(root,key);
        if(include){
            return ;
        }
        for(int i=0 ;i<root.getTreeNodes().length;i++){
            BPlusTreeNode bPlusTreeNode = root.getTreeNodes()[i];
            if(bPlusTreeNode == null){
                if(bPlusTreeNode == null){
                    bPlusTreeNode = new BPlusTreeNode(degree,key);
                    root.getTreeNodes()[i]=bPlusTreeNode;
                    break;
                }else {

                }
            }
        }
    }
    private void doInsert(Object key,BPlusTreeNode bPlusTreeNode){
        if(bPlusTreeNode == root){

        }
    }


    public static final boolean include(BPlusTreeNode bPlusTreeNode,Object key){
        if(bPlusTreeNode == null){
            return false;
        }
        BPlusTreeNode first = bPlusTreeNode.getTreeNodes()[0];
        BPlusTreeNode last = bPlusTreeNode.getTreeNodes()[bPlusTreeNode.getTreeNodes().length-1];
        int startCompare = KeyCompareUtil.compare(first.getKey(),key);
        int endCompare = KeyCompareUtil.compare(last.getKey(),key);
        if(startCompare>=0 && endCompare <0){
            return true;
        }
        return  false;
    }

}
