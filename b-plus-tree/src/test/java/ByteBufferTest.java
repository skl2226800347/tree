import org.junit.Test;

import java.nio.ByteBuffer;

public class ByteBufferTest {

    @Test
    public void put(){
        ByteBuffer byteBuffer = ByteBuffer.allocate("abc".getBytes().length);
        byteBuffer.put("abc".getBytes(),0,2);
        System.out.println(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        System.out.println(byteBuffer.array().length);
    }
}
