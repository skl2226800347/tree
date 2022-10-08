import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class FileTest {
    @Test
    public void delete()throws Exception{
        String fileName ="d:/2.log";
        File file = new File(fileName);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,1024*1024);
        invoke(invoke(viewed(mappedByteBuffer), "cleaner"), "clean");
        fileChannel.close();
        randomAccessFile.close();
        boolean flag = file.delete();
        System.out.println(flag);
    }

    public static ByteBuffer viewed(ByteBuffer buffer) {
        String methodName = "viewedBuffer";

        Method[] methods = buffer.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals("attachment")) {
                methodName = "attachment";
                break;
            }
        }

        ByteBuffer viewedBuffer = (ByteBuffer) invoke(buffer, methodName);
        if (viewedBuffer == null)
            return buffer;
        else
            return viewed(viewedBuffer);
    }

    public static Object invoke(Object object,String methodName,Class<?> ...paramTypes){
        return AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                try{
                    Method method = object.getClass().getMethod(methodName,paramTypes);
                    method.setAccessible(true);
                    return method.invoke(object);
                }catch (Throwable e){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
