/*
Path Sum
        Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

        Note: A leaf is a node with no children.

        Example:

        Given the below binary tree and sum = 22,

            5
           / \
          4   8
         /   / \
        11  13  4
        /  \      \
        7    2      1
        return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

        方法1：递归

 */
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {

        if(root == null) return false;
        if(root.left == null && root.right == null && sum - root.val == 0) return true;

        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);



    }
}

/*
方法2：Iterative using Stack
*/


class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {

        if(root == null) return false;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if(cur.left == null && cur.right == null && cur.val == sum) return true;

            if(cur.left != null){
                cur.left.val = cur.val + cur.left.val;
                stack.push(cur.left);
            }

            if(cur.right != null){
                cur.right.val = cur.val + cur.right.val;
                stack.push(cur.right);
            }
        }

        return false;


    }
}
