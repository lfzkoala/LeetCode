import org.junit.Test;
import org.junit.Before;
import java.util.*;
import static org.junit.Assert.*;


public class DesiginInMemoryFileSystemTest {

    @Before
    public void setUp() throws Exception{}

    @Test
    public void mkdirTest(){
        DesignInMemoryFileSystem P = new DesignInMemoryFileSystem();
        assertTrue(P.mkdirCheck("/a"));
    }

    @Test
    public void mkdirNegativeTest(){
        DesignInMemoryFileSystem P = new DesignInMemoryFileSystem();
        P.mkdirCheck("/a");
        assertFalse(P.mkdirCheck("/a"));
    }

    @Test
    public void listFileDirTest(){
        DesignInMemoryFileSystem P = new DesignInMemoryFileSystem();
        P.mkdirCheck("/home");
        P.mkdirCheck("/home/user1");
        P.mkdirCheck("/home/user2");
        P.createFile("/home", "readme.txt", "Hello World!");
        List<String> expected = Arrays.asList("read.txt", "user1", "user2");

        assertEquals(expected, P.ls("/home"));
        System.out.println(P.ls("/home"));
        System.out.println(P.readContentFromFile("/home/readme.txt"));
    }

    @Test
    public void createFileTest(){
        DesignInMemoryFileSystem P = new DesignInMemoryFileSystem();
        P.mkdirCheck("/home");
        P.createFile("/home", "readme.txt", "Hello World!");
        List<String> expected = Arrays.asList("readme.txt");
        assertEquals(expected, P.ls("/home"));
        assertEquals("Hello World!", P.readContentFromFile("/home/read.txt"));
        System.out.println(P.readContentFromFile("/home/readme.txt"));
    }

    @Test
    public void removeTest(){
        DesignInMemoryFileSystem P = new DesignInMemoryFileSystem();
        P.DesignInMemoryFileSystem();
        P.mkdir("/a/b/c");
        P.addContentToFile("/a/b/c", "hello");
        assertTrue(P.remove("/a/b/c"));
    }

}
