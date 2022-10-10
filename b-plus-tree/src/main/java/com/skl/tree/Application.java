package com.skl.tree;

import com.skl.tree.file.TreeMappedFile;

public class Application {
    private static final int DEGREE = 4;
    private static final String FILE_NAME ="d:/2.log";
    public static final void main(String[]args) throws Exception{
        TreeMappedFile treeMappedFile = new TreeMappedFile(FILE_NAME);
        BPlusTree bPlusTree = new BPlusTree(DEGREE,treeMappedFile);
        bPlusTree.insert("333");
    }
}
