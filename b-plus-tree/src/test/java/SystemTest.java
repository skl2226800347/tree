import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class SystemTest {

    @Test
    public void arraycopy(){
        String[] src=new String[4];
        src[0]="a";
        src[1]="b";
        src[2]="c";
        src[3]="d";
        String[] dest=new String[4];
        System.arraycopy(src,1,dest,0,3);
        System.out.println("src:"+JSONObject.toJSONString(src));
        System.out.println("dest:"+JSONObject.toJSONString(dest));
    }
}
