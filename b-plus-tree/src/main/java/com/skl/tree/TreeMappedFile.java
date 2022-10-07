package com.skl.tree;

import com.skl.tree.buffer.AddBufferParam;
import com.skl.tree.buffer.AddBufferResult;
import com.skl.tree.buffer.GetBufferResult;
import com.skl.tree.utils.DecoderUtil;
import com.skl.tree.utils.EncoderUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TreeMappedFile {
    private static final int POSITION=0;
    private static final int FILE_SIZE = 1024*1024*1024;
    private String fileName;
    private File file;
    private RandomAccessFile randomAccessFile;
    private FileChannel fileChannel;
    private MappedByteBuffer mappedByteBuffer;
    private AtomicInteger physicalOffset= new AtomicInteger(0);

    public TreeMappedFile(String fileName){
        try {
            this.fileName = fileName;
            file = new File(fileName);
            randomAccessFile = new RandomAccessFile(file, "rw");
            fileChannel = randomAccessFile.getChannel();
            mappedByteBuffer =fileChannel.map(FileChannel.MapMode.READ_WRITE,POSITION,FILE_SIZE);
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public AddBufferResult add(AddBufferParam addBufferParam){
        Objects.requireNonNull(addBufferParam.getValue()," value is not null");
        try {
            int offset = physicalOffset.get();
            ByteBuffer byteBuffer = mappedByteBuffer.slice();
            byteBuffer.position(offset);
            byte[] bytes = EncoderUtil.encoder(addBufferParam.getValue());
            int size = bytes.length;
            physicalOffset.addAndGet(size);
            byteBuffer.put(bytes,offset,bytes.length);

            System.out.println("mappedByteBuffer="+mappedByteBuffer);
          //  ByteBuffer byteBuffer = mappedByteBuffer.slice();
           // System.out.println("byteBuffer="+byteBuffer);
            //System.out.println("mappedByteBuffer="+mappedByteBuffer);
            //System.out.println("byteBuffer="+byteBuffer);
            System.out.println("mappedByteBuffer="+mappedByteBuffer);
            return AddBufferResult.createAddBufferResult().offset(offset).size(bytes.length);
        }catch (Throwable e){
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }
    public GetBufferResult getBuffer(int physicalOffset, int size){
        try {

            mappedByteBuffer.position(physicalOffset);
            ByteBuffer byteBuffer = mappedByteBuffer.slice();
            byteBuffer.position(physicalOffset);
            ByteBuffer newByteBuffer = byteBuffer.slice();
            byte[] bytes = new byte[size];
            newByteBuffer.get(bytes);
            newByteBuffer.limit(bytes.length);
            return GetBufferResult.createGetBufferResult().bytes(bytes).value(DecoderUtil.decoder(bytes));
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    public void destroy()throws IOException{
        randomAccessFile.close();
        if(file.exists()){
            boolean isDelete = file.delete();
            System.out.println(isDelete);
        }
    }
}
