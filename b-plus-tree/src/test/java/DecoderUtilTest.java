import com.skl.tree.utils.DecoderUtil;
import com.skl.tree.utils.EncoderUtil;
import org.junit.Test;

public class DecoderUtilTest {
    @Test
    public void decoder()throws  Exception{
        Object obj = DecoderUtil.decoder("abc".getBytes());
        System.out.println(obj);
    }
}
