/*
Path Sum II
        Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

        Note: A leaf is a node with no children.

        Example:

        Given the below binary tree and sum = 22,

        5
        / \
        4   8
        /   / \
        11  13  4
        /  \    / \
        7    2  5   1
        Return:

        [
        [5,4,11,2],
        [5,8,4,5]
        ]

        方法：

 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        addPath(result, temp, root, sum);
        return result;
    }

    private void addPath(List<List<Integer>> result, List<Integer> temp, TreeNode root, int sum){
        if(root == null) return;
        temp.add(root.val);

        if(root.left == null && root.right == null && sum-root.val == 0){
            result.add(new ArrayList<>(temp));
            temp.remove(temp.size()-1);
            return;
        }

        addPath(result, temp, root.left, sum-root.val);
        addPath(result, temp, root.right, sum-root.val);

        temp.remove(temp.size()-1);
    }
}

