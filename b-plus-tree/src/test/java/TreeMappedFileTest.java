import com.skl.tree.TreeMappedFile;
import com.skl.tree.buffer.AddBufferParam;
import com.skl.tree.buffer.AddBufferResult;
import com.skl.tree.buffer.GetBufferResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class TreeMappedFileTest {
    private String fileName="d:/1.log";
    TreeMappedFile  treeMappedFile;
    @Before
    public void init(){
        treeMappedFile = new TreeMappedFile(fileName);
    }
    @Test
    public void addAndGet(){
        AddBufferParam addBufferParam = new AddBufferParam();
        String value="123";
        addBufferParam.setValue(value);
        AddBufferResult addBufferResult = treeMappedFile.add(addBufferParam);
        int size = addBufferResult.getSize();
        int offset = addBufferResult.getOffset();
        GetBufferResult getBufferResult = treeMappedFile.getBuffer(offset,size);
        System.out.println(getBufferResult.getValue());

    }

    @After
    public void cleanup()throws Exception{
        treeMappedFile.cleanup();
    }
}
