package com.skl.tree;

import com.skl.tree.buffer.AddBufferParam;
import com.skl.tree.buffer.GetBufferResult;
import com.skl.tree.utils.EncoderUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class TreeMappedFile {
    private static final int POSITION=0;
    private static final int FILE_SIZE = 1024*1024*1024;
    private RandomAccessFile file;
    private FileChannel fileChannel;
    private MappedByteBuffer mappedByteBuffer;
    private AtomicLong position= new AtomicLong(0);

    public TreeMappedFile(String fileName){
        try {
            file = new RandomAccessFile(fileName, "rw");
            fileChannel = file.getChannel();
            mappedByteBuffer =fileChannel.map(FileChannel.MapMode.READ_WRITE,POSITION,FILE_SIZE);
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void add(AddBufferParam addBufferParam){
        Objects.requireNonNull(addBufferParam.getValue()," value is not null");
        //mappedByteBuffer.
        try {
            byte[] bytes = EncoderUtil.encoder(addBufferParam.getValue());
            position.addAndGet(bytes.length);
            ByteBuffer byteBuffer = mappedByteBuffer.slice();
            byteBuffer.put(bytes);
        }catch (Throwable e){
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }

    }
    public GetBufferResult getBuffer(long physicalOffset, int size){
        return null;
    }
}
