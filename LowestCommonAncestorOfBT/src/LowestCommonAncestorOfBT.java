import java.util.*;

public class LowestCommonAncestorOfBT {

    public static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int v){
            val = v;
        }
    }

    public static void main(String[] args){

        LowestCommonAncestorOfBT P = new LowestCommonAncestorOfBT();

        int[] nums = {3,5,1,6,2,0,8,-1,-1, 7, 4};
        TreeNode root = new TreeNode(nums[0]);


        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for(int i=1; i<nums.length; i++){
            TreeNode cur = queue.poll();
            TreeNode left = new TreeNode(nums[i]);
            if(cur.left == null) cur.left = left;
            queue.offer(cur.left);
            i++;

            TreeNode right = new TreeNode(nums[i]);
            if(cur.right == null) cur.right = right;
            queue.offer(cur.right);
        }

        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);
        TreeNode LCA = P.lowestCommonAncestor(root, p, q);

        System.out.println(LCA.val);


    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){

        if(root == null || root.val == p.val || root.val == q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null){
            return root;
        }else if(left != null){
            return right;
        }else{
            return left;
        }



    }





}
