import java.util.*;
public class PathSum2 {
    public static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        public TreeNode(int v){
            val = v;
        }
    }

    public static void main(String[] args){
        PathSum2 P = new PathSum2();
        int[] nums = {5,4,8,11,-1,13,4,7,2,-1,-1,-1,-1,5,1};

        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode root = new TreeNode(nums[0]);
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

        int sum = 22;
        List<List<Integer>> result = P.pathSum(root, sum);
        System.out.println(result);



    }




    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        if(root == null) return new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();

        helper(result, root, sum, new ArrayList<>());

        return result;
    }

    private void helper(List<List<Integer>> result, TreeNode root, int sum, List<Integer> temp){
        if(root == null) return;
        if(root.left == null && root.right == null && sum == root.val){
            temp.add(root.val);
            result.add(new ArrayList<>(temp));
            temp.remove(temp.size()-1);
            return;
        }

        temp.add(root.val);
        helper(result, root.left, sum-root.val, temp);
        helper(result, root.right, sum-root.val, temp);
        temp.remove(temp.size()-1);


    }

}
