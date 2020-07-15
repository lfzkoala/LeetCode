/*
Symmetric Tree
        Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

        For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

        1
        / \
        2   2
        / \ / \
        3  4 4  3


        But the following [1,2,2,null,3,null,3] is not:

        1
        / \
        2   2
        \   \
        3    3

        Note:
        Bonus points if you could solve it both recursively and iteratively.

        类似于Same Tree（上一题）只不过这题是自己和自己比较 time complexity O(n), space complexity O(n).
        参考：https://github.com/cherryljr/LeetCode/blob/master/Symmetric%20Tree.java


 */
class Solution {
    public boolean isSymmetric(TreeNode root) {

        //recursion method
        if(root == null){
            return true;
        }

        return isValid(root, root);
    }


    private boolean isValid(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null){
            return true;
        }

        if(root1 == null || root2 == null || root1.val != root2.val){
            return false;
        }

        boolean outer = isValid(root1.left, root2.right);
        boolean inner = isValid(root1.right, root2.left);

        return outer && inner;


    }

}
