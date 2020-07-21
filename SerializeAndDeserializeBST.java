/*
Serialize and Deserialize Binary Search Tree

        Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

        Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

        The encoded string should be as compact as possible.

        Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

        方法：

 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if(root == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if(cur == null){
                sb.append("null");
                sb.append(",");
            }else{
                sb.append(cur.val);
                sb.append(",");
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }

        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data == "") return null;

        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for(int i=1; i<nodes.length; i++){
            TreeNode cur = queue.poll();
            if(!nodes[i].equals("null")){
                cur.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.offer(cur.left);
            }
            i++;
            if(!nodes[i].equals("null")){
                cur.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.offer(cur.right);
            }
        }


        return root;



    }
}
