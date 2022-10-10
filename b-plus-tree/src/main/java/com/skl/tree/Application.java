package com.skl.tree;

import com.skl.tree.file.TreeMappedFile;

public class Application {
    private static final int DEGREE = 4;
    private static final String FILE_NAME ="d:/2.log";
    public static TreeMappedFile treeMappedFile = new TreeMappedFile(FILE_NAME);
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                treeMappedFile.cleanup();
            }
        }));
    }

    public static final void main(String[]args) throws Exception{

        BPlusTree bPlusTree = new BPlusTree(DEGREE,treeMappedFile);
        bPlusTree.insert("333");
    }
}
