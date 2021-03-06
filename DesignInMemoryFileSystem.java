
/*
Design In-Memory File System
        Design an in-memory file system to simulate the following functions:
        ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.
        mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.
        addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.
        readContentFromFile: Given a file path, return its content in string format.

        Example:
        Input:
        ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
        [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]

        Output:
        [null,[],null,null,["a"],"hello"]

        Explanation:



        Note:
        You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
        You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
        You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.
        答案：

        方法1：

 */
public class FileSystem {
    class File {
        boolean isFile = false;
        Map<String, File> children = new HashMap<>();
        String content = "";
    }

    File root = null;

    public FileSystem() {
        root = new File();
    }

    public List<String> ls(String path) {
        String[] dirs = path.split("/");
        File node = root;
        List<String> result = new ArrayList<>();
        String name = "";
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.children.containsKey(dir)) {
                return result;
            }
            node = node.children.get(dir);
            name = dir;
        }

        if (node.isFile) {
            result.add(name);
        }
        else {
            for (String key : node.children.keySet()) {
                result.add(key);
            }
        }

        Collections.sort(result);

        return result;
    }

    public void mkdir(String path) {
        String[] dirs = path.split("/");
        File node = root;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.children.containsKey(dir)) {
                File file = new File();
                node.children.put(dir, file);
            }
            node = node.children.get(dir);
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] dirs = filePath.split("/");
        File node = root;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.children.containsKey(dir)) {
                File file = new File();
                node.children.put(dir, file);
            }
            node = node.children.get(dir);
        }
        node.isFile = true;
        node.content += content;
    }

    public String readContentFromFile(String filePath) {
        String[] dirs = filePath.split("/");
        File node = root;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.children.containsKey(dir)) {
                File file = new File();
                node.children.put(dir, file);
            }
            node = node.children.get(dir);
        }

        return node.content;
    }
}
/*
方法2： 用TreeMap
        Used TreeMap to avoid sorting the list as insertion takes O(logN).
        GetList method takes O(N)

 */

public class FileSystem {
    private FileNode root;

    public FileSystem() {
        root = new FileNode("");
    }

    public List<String> ls(String path) {
        return findNode(path).getList();
    }

    public void mkdir(String path) {
        findNode(path);
    }

    public void addContentToFile(String filePath, String content) {
        findNode(filePath).addContent(content);
    }

    public String readContentFromFile(String filePath) {
        return findNode(filePath).getContent();
    }

    //-- private method section --//
    private FileNode findNode(String path){
        String[] files = path.split("/");

        FileNode cur = root;
        for(String file : files){
            if(file.length() == 0) continue;

            cur.children.putIfAbsent(file, new FileNode(file));
            cur = cur.children.get(file);

            if(cur.isFile()) break;
        }

        return cur;
    }

    // Private class
    private class FileNode{
        private TreeMap<String, FileNode> children;
        private StringBuilder file;
        private String name;

        public FileNode(String name) {
            children = new TreeMap<>();
            file = new StringBuilder();
            this.name = name;
        }

        public String getContent(){
            return file.toString();
        }

        public String getName(){
            return name;
        }

        public void addContent(String content){
            file.append(content);
        }

        public boolean isFile(){
            return file.length() > 0;
        }

        public List<String> getList(){
            List<String> list = new ArrayList<>();
            if(isFile()){
                list.add(getName());
            }else{
                list.addAll(children.keySet());
            }

            return list;
        }
    }
}
/*
方法3：Using TreeNode


 */
class FileSystem {

    public class TrieNode {
        boolean isFile = false;
        String data = "";
        Map<String, TrieNode> children = new HashMap<>();
    }

    TrieNode root = null;
    public FileSystem() {
        root = new TrieNode();
    }

    public void mkdir(String path) {
        String[] dirs = path.split("/");
        TrieNode node = root;
        for(String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.children.containsKey(dir)) {
                TrieNode newNode = new TrieNode();
                node.children.put(dir, newNode);
            }
            node = node.children.get(dir);
        }
    }


    public void addContentToFile(String filePath, String content) {
        String[] dirs = filePath.split("/");
        TrieNode node = root;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.children.containsKey(dir)) {
                TrieNode newNode = new TrieNode();
                node.children.put(dir, newNode);
            }
            node = node.children.get(dir);
        }
        node.isFile = true;
        node.data += content; // append

    }

    public String readContentFromFile(String filePath) {
        String[] dirs = filePath.split("/");
        TrieNode node = root;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.children.containsKey(dir)) {
                TrieNode newNode = new TrieNode();
                node.children.put(dir, newNode);
            }
            node = node.children.get(dir);
        }
        if (!node.isFile) return "";
        return node.data;
    }

    public List<String> ls(String path) {
        List<String> result = new ArrayList<>();

        String[] dirs = path.split("/");
        TrieNode node = root;

        String name = "";
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.children.containsKey(dir)) {
                return result;
            }
            node = node.children.get(dir);
            name = dir; // saving this - watch this!!!
        }

        if (node.isFile) {
            result.add(name);  // adding the name above!!1
        } else {
            for (String key : node.children.keySet()) {
                result.add(key);
            }
        }

        Collections.sort(result);

        return result;
    }
}
