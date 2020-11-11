import java.util.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class DesignInMemoryFileSystem {

    public static void main(String[] args){
        Result result = JUnitCore.runClasses(DesiginInMemoryFileSystemTest.class);
        if(result.wasSuccessful()) System.out.println("All test cases are passed");
        for(Failure failure: result.getFailures()){
            System.out.println(failure.toString());
        }


    }

    class Node{
        Map<String, Node> map;
        boolean isFile;
        String fileName;
        StringBuilder content;
        public Node(String name){
            fileName = name;
            isFile = false;
            map = new TreeMap<String, Node>();
            content = new StringBuilder();
        }
    }

    Node root;

    public void DesignInMemoryFileSystem() {
        root = new Node("/");
    }

    public List<String> ls(String path) {
        Node node = traverse(path);

        List<String> result = new ArrayList<>();

        if(node.isFile){
            result.add(node.fileName);
        }else{
            for(String f: node.map.keySet()){
                result.add(f);
            }
        }

        return result;
    }

    public void mkdir(String path) {
        traverse(path);
    }

    public void addContentToFile(String filePath, String content) {
        Node node = traverse(filePath);
        node.isFile = true;
        node.content.append(content);
    }

    public String readContentFromFile(String filePath) {
        Node node = traverse(filePath);
        return node.content.toString();

    }

    private Node traverse(String path){
        String[] arr = path.split("/");
        Node cur = root;
        for(int i=1; i<arr.length; i++){
            if(!cur.map.containsKey(arr[i])){
                cur.map.put(arr[i], new Node(arr[i]));
            }

            cur = cur.map.get(arr[i]);
        }

        return cur;

    }

    public boolean remove(String path){
        String[] arr = path.split("/");
        Node cur = root;
        for(int i=1; i<arr.length; i++){
            //if(!cur.map.containsKey(arr[i])) return false;
            if(i == arr.length-1){
                if(!cur.map.containsKey(arr[i])) return false;
                cur.map.remove(arr[i]);
                break;
            }

            cur = cur.map.get(arr[i]);
        }
        return true;

    }




}
