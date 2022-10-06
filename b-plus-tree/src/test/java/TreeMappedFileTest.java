import com.skl.tree.TreeMappedFile;
import com.skl.tree.buffer.AddBufferParam;
import org.junit.Test;

public class TreeMappedFileTest {
    private String fileName="";
    @Test
    public void add(){
        TreeMappedFile  treeMappedFile = new TreeMappedFile(fileName);
        AddBufferParam addBufferParam = new AddBufferParam();
        String value="123";
        addBufferParam.setValue(value);
        treeMappedFile.add(addBufferParam);
    }
}
