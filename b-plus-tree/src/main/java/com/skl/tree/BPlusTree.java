package com.skl.tree;
import com.skl.tree.buffer.AddBufferRequest;
import com.skl.tree.buffer.BufferResult;
import com.skl.tree.buffer.GetBufferResult;
import com.skl.tree.buffer.ModifyBufferRequest;
import com.skl.tree.constatns.CompareConstants;
import com.skl.tree.constatns.Constans;
import com.skl.tree.file.TreeMappedFile;
import com.skl.tree.utils.CompareUtil;
import java.io.Serializable;

public class BPlusTree implements Serializable {
    private int degree;
    private BPlusTreeNode root;
    private TreeMappedFile treeMappedFile;
    public BPlusTree(int degree,TreeMappedFile treeMappedFile){
        this.degree= degree;
        this.treeMappedFile = treeMappedFile;
        root = loadRoot();
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
        boolean isStored = bPlusTreeNode.isStored();
        for(int i=0;i<keyLength;i++) {
            Object curKey=bPlusTreeNode.getKeys()[i];
            if(curKey == null){
                //tree节点第一次存储到文件中
                if(isStored==false && i==Constans.ZERO) {
                    bPlusTreeNode.getKeys()[i]=key;
                    BufferResult addBufferResult = treeMappedFile.add(AddBufferRequest.createAddBufferRequest(bPlusTreeNode));
                    bPlusTreeNode.setStored(true);
                    return;
                    //不是第一次插入，更新覆盖
                }else if (isStored == true){
                    treeMappedFile.modify(ModifyBufferRequest.createModifyBufferRequest(bPlusTreeNode).startOffset(bPlusTreeNode.getStartOffset()));
                }
            }else {
                int compareValue = CompareUtil.compare(curKey, key);
                //等于
                int storeNumber = bPlusTreeNode.getStoreNumber();
                int availableNumber = (keyLength-storeNumber);
                int moveLength = (storeNumber-i);
                if(CompareConstants.EQUAL==compareValue) {
                    System.arraycopy(bPlusTreeNode.getKeys(),i,bPlusTreeNode.getKeys(),i+1,moveLength);
                    bPlusTreeNode.getKeys()[i]=curKey;
                } else if(compareValue > CompareConstants.EQUAL) {//大于
                    continue;
                } else {//小于
                    System.arraycopy(bPlusTreeNode.getKeys(),i,bPlusTreeNode.getKeys(),i+1,moveLength);
                    bPlusTreeNode.getKeys()[i]=curKey;
                }
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

    /**
     * 加载根节点
     */
    protected BPlusTreeNode loadRoot(){
        int size = treeMappedFile.getSize(Constans.START_OFFSET);
        if(size > Constans.ZERO){
            return doLoadRootFromDisk(size);
        }
        //插入
        BPlusTreeNode root = new BPlusTreeNode(this.degree);
        root.setStored(false);
        return root;
    }

    private BPlusTreeNode doLoadRootFromDisk(int size){
        int offset = Constans.START_OFFSET+Constans.INT_LENGTH;
        GetBufferResult getBufferResult = treeMappedFile.getBuffer(offset,size);
        BPlusTreeNode bPlusTreeNode =  (BPlusTreeNode)getBufferResult.getValue();
        bPlusTreeNode.setStored(true);
        return bPlusTreeNode;
    }

}
