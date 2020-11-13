import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class AnalyzeUserWebsiteVisitSystemTest {

    @Test
    public void Test(){
        AnalyzeUserWebsiteVisitSystem P = new AnalyzeUserWebsiteVisitSystem();

        String[] username = {"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"};
        int[] timestamp = {1,2,3,4,5,6,7,8,9,10};
        String[] website = {"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"};
        List<String> result = P.mostVisitedPattern(username, timestamp, website);
        List<String> test = new ArrayList<>();
        test.add("home");
        test.add("about");
        test.add("career");
        assertEquals(test, result);

    }



}
