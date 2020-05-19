/*
Maximal Level Sum of Binary Tree
        Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
        Return the smallest level X such that the sum of all the values of nodes at level X is maximal.

        Example 1:

        Input: [1,7,0,7,-8,null,null]
        Output: 2
        Explanation:
        Level 1 sum = 1.
        Level 2 sum = 7 + 0 = 7.
        Level 3 sum = 7 + -8 = -1.
        So we return the level with the maximum sum which is level 2.
        Note:
        The number of nodes in the given tree is between 1 and 10^4.
        -10^5 <= node.val <= 10^5

        方法：自己写的 BFS with queue 类似于Max Depth of Binary Tree的方法

 */
class Solution {
    public int maxLevelSum(TreeNode root) {

        int result = Integer.MAX_VALUE;
        int level = 0;
        int max = Integer.MIN_VALUE;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            int sum = 0;
            for(int i=0; i<size; i++){
                TreeNode cur = queue.poll();
                sum += cur.val;
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
            level++;

            if(sum > max){
                max = sum;
                result = level;
            }
        }

        return result;




    }
}
