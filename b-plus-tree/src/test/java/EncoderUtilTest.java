import com.skl.tree.utils.DecoderUtil;
import com.skl.tree.utils.EncoderUtil;
import org.junit.Test;

public class EncoderUtilTest {
    @Test
    public void encoder()throws  Exception{
        Object obj = "xxx";
        byte[] bytes = EncoderUtil.encoder(obj);
        Object decoderResult = DecoderUtil.decoder(bytes);
        System.out.println(decoderResult);
    }
}
