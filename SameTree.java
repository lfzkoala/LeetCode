/*
Same Tree:
        Given two binary trees, write a function to check if they are the same or not.
        Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
        Example 1:
        Input:     1         1
        / \       / \
        2   3     2   3

        [1,2,3],   [1,2,3]

        Output: true

        Example 2:
        Input:     1         1
        /           \
        2             2

        [1,2],     [1,null,2]

        Output: false

        Example 3:
        Input:     1         1
        / \       / \
        2   1     1   2

        [1,2,1],   [1,1,2]

        Output: false
        对树中每个节点进行递归 并且比较对应节点的val, 比较的方式为
        p.val == q.val && isSameTree(p.left,q.left) && isSameTree(p.right, q.right)
        如果true，返回true， 如果false， 返回false

        需要考虑一个节点为空的情况，这种情况返回false
        如果两个节点都为空 则返回true

        递归的边界条件为p, q均为null, 如果不是则返回false
        递归的前进段：如果p，q不为null，则继续调用自身的left和right
        递归的返回段：从树的root向下 如果期中任意一个节点为false，则都会返回false，如果期中任意一个节点都是true，则最后返回true.


        方法1：

 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q != null){
            return false;
        }

        if (p != null && q == null){
            return false;
        }

        if (p == null && q == null){
            return true;
        }

        if (p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)){
            return true;
        }else{
            return false;
        }
    }
}
/*
方法2：

 */
//更好的方法
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if(p == null && q == null){
            return true;
        }

        if(p == null || q == null || p.val != q.val){
            return false;
        }

        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);

        return left && right;
    }
}
