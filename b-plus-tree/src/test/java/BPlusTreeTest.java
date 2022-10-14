import com.skl.tree.BPlusTree;
import com.skl.tree.file.TreeMappedFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BPlusTreeTest {
    private  int DEGREE = 4;
    private  String FILE_NAME ="d:/2.log";
    protected   TreeMappedFile treeMappedFile;
    @Before
    public void init() {
        treeMappedFile = new TreeMappedFile(FILE_NAME);
    }

    @Test
    public void insert(){
        BPlusTree bPlusTree = new BPlusTree(DEGREE,treeMappedFile);
        bPlusTree.insert("333");
        bPlusTree.insert("333");
    }

    @After
    public void destroy(){
        //treeMappedFile.cleanup();
    }
}
