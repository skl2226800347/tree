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
        BPlusTreeNode plusTreeNode= this.root;
        Object firstKey = plusTreeNode.getKeys()[0];
        if(firstKey == null){
            doInsert(key,plusTreeNode,0);
            return;
        }
        if(firstKey != null){
            int compareValue = CompareUtil.compare(firstKey,key);
            //当前b+tree节点最小值仍然大于key，需要迁移到左边b+tree节点
            if(compareValue <=Constans.ZERO){

            }
        }
        Object lastKey = plusTreeNode.getKeys()[plusTreeNode.getKeys().length-1];
        if(lastKey != null) {
            int compareValue = CompareUtil.compare(lastKey, key);
            //当前b+tree节点最大值仍然小于key，需要迁移到右边b+tree节点
            if(compareValue > Constans.ZERO){

            }
        }
        for(int index=0 ;index<plusTreeNode.getKeys().length;index++){
            Object curKey = plusTreeNode.getKeys()[index];
            if(curKey == null){
                doInsert(key,plusTreeNode,index);
                return;
            }
            int compareValue = CompareUtil.compare(curKey,key);
            if(compareValue <=Constans.ZERO){
                doInsert(key,plusTreeNode,index);
                break;
            } else {
                continue;
            }
        }
    }
    private void doInsert(Object key,BPlusTreeNode bPlusTreeNode,int index){
        int keyLength = bPlusTreeNode.getKeys().length;
        boolean isStored = bPlusTreeNode.isStored();
        Object curKey=bPlusTreeNode.getKeys()[index];
        if(curKey == null){
            //tree节点第一次存储到文件中
            if(isStored==false && index==Constans.ZERO) {
                bPlusTreeNode.getKeys()[index]=key;
                treeMappedFile.add(AddBufferRequest.createAddBufferRequest(bPlusTreeNode));
                return;
                //不是第一次插入，更新覆盖
            }else if (isStored == true){
                treeMappedFile.modify(ModifyBufferRequest.createModifyBufferRequest(bPlusTreeNode).startOffset(bPlusTreeNode.getStartOffset()));
            }
        }else {
            int compareValue = CompareUtil.compare(curKey, key);
            //等于
            int storeNumber = bPlusTreeNode.getStoreNumber();
            int moveLength = (storeNumber-index);
            if(CompareConstants.EQUAL==compareValue) {
                System.arraycopy(bPlusTreeNode.getKeys(),index,bPlusTreeNode.getKeys(),index+1,moveLength);
                bPlusTreeNode.getKeys()[index]=curKey;
            } else if(compareValue > CompareConstants.EQUAL) {//大于

            } else {//小于
                System.arraycopy(bPlusTreeNode.getKeys(),index,bPlusTreeNode.getKeys(),index+1,moveLength);
                bPlusTreeNode.getKeys()[index]=curKey;
            }
        }
    }


    public static final boolean isCanInsertCondition(BPlusTreeNode bPlusTreeNode,Object key){
        if(bPlusTreeNode == null){
            return false;
        }
        Object first = bPlusTreeNode.getKeys()[0];
        if(first == null){
            return true;
        }
        Object last = bPlusTreeNode.getKeys()[bPlusTreeNode.getKeys().length-1];
        if(last == null){
            return true;
        }
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
        int size = treeMappedFile.getSize(Constans.START_OFFSET+Constans.INT_LENGTH);
        if(size > Constans.ZERO){
            return doLoadRootFromDisk(size);
        }
        //插入
        BPlusTreeNode root = new BPlusTreeNode(this.degree);
        root.setStored(false);
        return root;
    }

    private BPlusTreeNode doLoadRootFromDisk(int size){
        int offset = Constans.START_OFFSET+Constans.INT_LENGTH+Constans.INT_LENGTH;
        GetBufferResult getBufferResult = treeMappedFile.getBuffer(offset,size);
        BPlusTreeNode bPlusTreeNode =  (BPlusTreeNode)getBufferResult.getValue();
        bPlusTreeNode.setStored(true);
        return bPlusTreeNode;
    }

}
