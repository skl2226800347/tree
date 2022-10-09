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
        AddBufferResult addBufferResult1 = treeMappedFile.add(addBufferParam);
        GetBufferResult getBufferResult = treeMappedFile.getBuffer(addBufferResult1.getOffset(),addBufferResult1.getSize());
        System.out.println(getBufferResult.getValue());
        addBufferParam.setValue("456");
        AddBufferResult addBufferResult2= treeMappedFile.add(addBufferParam);
        GetBufferResult getBufferResult2 = treeMappedFile.getBuffer(addBufferResult2.getOffset(),addBufferResult2.getSize());
        System.out.println(getBufferResult2.getValue());
    }

    @After
    public void cleanup()throws Exception{
        treeMappedFile.cleanup();
    }

    @Test
    public void getValue(){
        int position=3;
        System.out.println("position="+(position<<0));
    }
}
