package com.skl.tree.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skl.tree.BPlusTreeNode;
import com.skl.tree.buffer.AddBufferRequest;
import com.skl.tree.buffer.BufferResult;
import com.skl.tree.buffer.GetBufferResult;
import com.skl.tree.buffer.ModifyBufferRequest;
import com.skl.tree.constatns.Constans;
import com.skl.tree.utils.DecoderUtil;
import com.skl.tree.utils.EncoderUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
public class TreeMappedFile {
    private static final int POSITION=0;
    private static final int FILE_SIZE = 1024*1024*1024;
    private String fileName;
    private File file;
    private RandomAccessFile randomAccessFile;
    private FileChannel fileChannel;
    private MappedByteBuffer mappedByteBuffer;
    private AtomicInteger writeOffset;
    public TreeMappedFile(String fileName){
        try {
            this.fileName = fileName;
            file = new File(fileName);
            randomAccessFile = new RandomAccessFile(file, "rw");
            fileChannel = randomAccessFile.getChannel();
            mappedByteBuffer =fileChannel.map(FileChannel.MapMode.READ_WRITE,POSITION,FILE_SIZE);
            init();
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    public BufferResult add(AddBufferRequest addBufferRequest){
        Objects.requireNonNull(addBufferRequest.getValue()," value is not null");
        try {
            final int offset = writeOffset.get();
            writeOffset.addAndGet(Constans.OS_PAGE);


            if(addBufferRequest.getValue() instanceof BPlusTreeNode) {
                ((BPlusTreeNode) addBufferRequest.getValue())
                        .startOffset(offset).stored(true);
            }

            System.out.println("value:"+ JSONObject.toJSONString(addBufferRequest.getValue()));
            byte[] bytes = EncoderUtil.encoder(addBufferRequest.getValue());
            System.out.println("bytes.length:"+bytes.length);
            System.out.println("bytes:"+JSONObject.toJSONString(bytes));
            int size = bytes.length;

            ByteBuffer byteBuffer = mappedByteBuffer.slice();
            byteBuffer.position(offset);

            ByteBuffer dataByteBuffer;
            if(offset > Constans.ZERO) {
                dataByteBuffer = ByteBuffer.allocate(bytes.length + Constans.INT_LENGTH);
                dataByteBuffer.putInt(size);
            }else if(offset == Constans.ZERO){
                dataByteBuffer = ByteBuffer.allocate(bytes.length + Constans.INT_LENGTH+Constans.INT_LENGTH);
                dataByteBuffer.putInt(writeOffset.intValue());
                dataByteBuffer.putInt(size);
            } else{
                throw new RuntimeException("offset:"+offset+"  不支持");
            }
            dataByteBuffer.put(bytes, 0, bytes.length);

            byteBuffer.put(dataByteBuffer.array(),0,dataByteBuffer.array().length);
            return BufferResult.createBufferResult().offset(offset).size(bytes.length)
                   .pageSize(Constans.OS_PAGE);
        }catch (Throwable e){
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    public BufferResult modify(ModifyBufferRequest request){
        ByteBuffer byteBuffer = mappedByteBuffer.slice();
        byteBuffer.position(request.getStartOffset());
        try {
            byte[] bytes = EncoderUtil.encoder(request.getValue());

            ByteBuffer dataBuffer = ByteBuffer.allocate(bytes.length);
            dataBuffer.limit(Constans.OS_PAGE);
            dataBuffer.putInt(bytes.length);
            dataBuffer.put(bytes);

            byteBuffer.put(dataBuffer.array(),0,dataBuffer.array().length);

            return BufferResult.createBufferResult().offset(request.getStartOffset())
                    .offset(request.getStartOffset()).size(bytes.length);
        }catch (Throwable e){
            throw new IllegalArgumentException(e);
        }
    }

    public GetBufferResult getBuffer(int physicalOffset, int size){
        try {
            byte[] bytes = new byte[size];
            ByteBuffer byteBuffer = mappedByteBuffer.slice();
            byteBuffer.position(physicalOffset);
            ByteBuffer newByteBuffer = byteBuffer.slice();
            newByteBuffer.limit(bytes.length);
            newByteBuffer.get(bytes);
            return GetBufferResult.createGetBufferResult().bytes(bytes)
                    .value(DecoderUtil.decoder(bytes));
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    public int getSize(int physicalOffset){
        try{
            ByteBuffer byteBuffer =mappedByteBuffer.slice();
            byteBuffer.position(physicalOffset);
            ByteBuffer newByteBuffer = byteBuffer.slice();
            int size = newByteBuffer.getInt();
            return size;
        }catch (Exception e){
            throw new IllegalArgumentException(e);
        }
    }

    public void cleanup(){
        if((mappedByteBuffer == null) |(!mappedByteBuffer.isDirect())){
            return ;
        }
        try {
            invoke(invoke(viewed(mappedByteBuffer), "cleaner"), "clean");
            fileChannel.close();
            randomAccessFile.close();
            boolean isDelete =file.delete();
            System.out.println(isDelete);
        }catch (Throwable e){
            e.printStackTrace();
        }


    }
    public static ByteBuffer viewed(ByteBuffer buffer)throws Throwable{
        String methodName="viewedBuffer";
        Method[] methods = buffer.getClass().getMethods();
        for (int i=0;i<methods.length;i++){
            if("attachment".equals(methods[i].getName())){
                methodName="attachment";
                break;
            }
        }
        ByteBuffer viewBuffer = (ByteBuffer)invoke(buffer,methodName);
        if(viewBuffer == null){
            return buffer;
        } else{
            return viewed(viewBuffer);
        }
    }
    public static Object invoke(Object obj,String methodName, Class... paramTypes){
        return AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                try{
                    Method method = obj.getClass().getMethod(methodName,paramTypes);
                    method.setAccessible(true);
                    return method.invoke(obj);
                }catch (Throwable e){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        });
    }

    protected void init(){
        int writeOffset = getSize(Constans.START_OFFSET);
        this.writeOffset = new AtomicInteger(writeOffset);
    }

}
