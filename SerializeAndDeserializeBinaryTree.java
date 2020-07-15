/*
Serialize and Deserialize Binary Tree
        Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

        Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

        Example:

        You may serialize the following tree:

        1
        / \
        2   3
        / \
        4   5

        as "[1,2,3,null,null,4,5]"
        Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

        Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.


        方法1：
        BFS 更好些一些 逻辑更通顺

 */
public class Codec {
    //This method uses BFS and Queue data structure
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        //corner case
        if(root == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            TreeNode cur = q.poll();
            if(cur == null){
                sb.append("null");
            }else{
                sb.append(cur.val);
                q.offer(cur.left);
                q.offer(cur.right);
            }
            sb.append(",");
        }

        sb.deleteCharAt(sb.length()-1);
        return sb.toString();

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        //corner case
        if(data == "") return null;

        //String to String array
        String[] arr = data.split(",");

        //Create Queue, queue is not empty
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
        q.offer(root);

        //1.poll parent, 2.create left and right node and connect them, 3.put left and right to queue
        for(int i=1; i<arr.length; i++){
            TreeNode parent = q.poll();
            if(!arr[i].equals("null")){
                TreeNode left = new TreeNode(Integer.valueOf(arr[i]));
                parent.left = left;
                q.offer(left);
            }

            i++;
            if(!arr[i].equals("null")){
                TreeNode right = new TreeNode(Integer.valueOf(arr[i]));
                parent.right = right;
                q.offer(right);
            }
        }

        return root;


    }
}

/*
方法2：
        递归recursive DFS 代码比较简洁 注意题目似乎没有规定一定要按case给的模板 所以DFS的方法给出的模板似乎不一样


 */
public class Codec {

    //recursive DFS method
    private static final String spliter = ",";
    private static final String NN = "X";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb){
        if(node == null){
            sb.append(NN).append(spliter);
        }else{
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes){
        String val = nodes.remove();
        if(val.equals(NN)){
            return null;
        }else{
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
}
/*
方法3：
        Iterative DFS 代码最复杂的一种方法 逻辑比较通顺 尽量记住
        
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        TreeNode x=root;
        Deque<TreeNode> stack=new LinkedList<>();
        while (x!=null || !stack.isEmpty()) {
            if (x!=null) {
                sb.append(String.valueOf(x.val));
                sb.append(' ');
                stack.push(x);
                x=x.left;
            }
            else {
                sb.append("null ");
                x=stack.pop();
                x=x.right;
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length()==0) return null;
        String[] node=data.split(" ");
        int n=node.length;
        Deque<TreeNode> stack=new LinkedList<>();
        TreeNode root=new TreeNode(Integer.valueOf(node[0]));
        TreeNode x=root;
        stack.push(x);

        int i=1;
        while (i<n) {
            while (i<n && !node[i].equals("null")) {
                x.left=new TreeNode(Integer.valueOf(node[i++]));
                x=x.left;
                stack.push(x);
            }
            while (i<n && node[i].equals("null")) {
                x=stack.pop();
                i++;
            }
            if (i<n) {
                x.right=new TreeNode(Integer.valueOf(node[i++]));
                x=x.right;
                stack.push(x);
            }
        }
        return root;
    }
}
