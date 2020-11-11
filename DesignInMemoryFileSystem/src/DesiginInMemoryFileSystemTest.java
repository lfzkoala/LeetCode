import org.junit.Test;
import java.util.*;
import static org.junit.Assert.assertTrue;


public class DesiginInMemoryFileSystemTest {

    @Test
    public void Test1(){
        DesignInMemoryFileSystem P = new DesignInMemoryFileSystem();
        P.DesignInMemoryFileSystem();
        P.mkdir("/a/b/c");
        P.addContentToFile("/a/b/c", "hello");
        assertTrue(P.readContentFromFile("/a/b/c").equals("hello"));
    }

    @Test
    public void Test2(){
        DesignInMemoryFileSystem P = new DesignInMemoryFileSystem();
        P.DesignInMemoryFileSystem();
        P.mkdir("/a/b/c");
        P.addContentToFile("/a/b/c", "hello");
        assertTrue(P.remove("/a/b/c"));
    }

}
