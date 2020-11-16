import java.util.*;
public class DeleteNodeInBST {

    static class TreeNode{
        TreeNode left;
        TreeNode right;
        Integer val;
        public TreeNode(Integer v){
            val = v;
        }
        public TreeNode(){}

    }


    public static void main(String[] args){

        Integer[] nums = {5,3,6,2,4,null,7};
        TreeNode root = new TreeNode(nums[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for(int i=1; i<nums.length; i++){
            TreeNode cur = queue.poll();
            TreeNode left = new TreeNode(nums[i]);
            cur.left = left;
            queue.offer(left);

            i++;
            TreeNode right = new TreeNode(nums[i]);
            cur.right = right;
            queue.offer(right);
        }

        DeleteNodeInBST P = new DeleteNodeInBST();
        TreeNode result = P.deleteNode(root, 3);

        List<Integer> list = new ArrayList<>();
        preOrder(result, list);
        System.out.println(list);
    }

    private TreeNode deleteNode(TreeNode root, int key){

        if(root == null) return null;
        if(key < root.val){
            root.left = deleteNode(root.left, key);
        }else if(key > root.val){
            root.right = deleteNode(root.right, key);
        }else{
            if(root.left == null){
                root = root.right;
            }else if(root.right == null){
                root = root.left;
            }else{
                root.val = minValue(root.right);
                root.right = deleteNode(root.right, root.val);
            }
        }
        return root;

    }

    private static int minValue(TreeNode root){
        int min = Integer.MAX_VALUE;
        while(root != null){
            min = root.val;
            root = root.left;
        }
        return min;
    }

    private static void preOrder(TreeNode root, List<Integer> list){
        if(root == null) return;
        list.add(root.val);
        preOrder(root.left, list);

        preOrder(root.right, list);

    }


}
