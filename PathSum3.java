/*
Path Sum III
        You are given a binary tree in which each node contains an integer value.

        Find the number of paths that sum to a given value.

        The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

        The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

        Example:

        root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

        10
        /  \
        5   -3
        / \    \
        3   2   11
        / \   \
        3  -2   1

        Return 3. The paths that sum to 8 are:

        1.  5 -> 3
        2.  5 -> 2 -> 1
        3. -3 -> 11

        方法：这题不一定是root到leave的path 任意的path都可以

 */
class Solution {

    public int pathSum(TreeNode root, int sum) {

        if(root == null) return 0;

        return pathSum(root.left, sum) + pathSum(root.right, sum) + findPath(root, sum);
    }

    private int findPath(TreeNode root, int sum){

        if(root == null) return 0;
        int count = 0;
        if(sum-root.val == 0) count++;

        count += findPath(root.left, sum-root.val);
        count += findPath(root.right, sum-root.val);

        return count;
    }

}

/*
方法2：prefix sum + backtracking 比较难记

 */

class Solution {
    public int pathSum(TreeNode root, int sum) {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        return backtrack(map, 0, sum, root);
    }

    private int backtrack(Map<Integer, Integer> map, int curSum, int sum, TreeNode root){

        if(root == null) return 0;

        curSum += root.val;

        int res = map.getOrDefault(curSum-sum,0);
        map.put(curSum, map.getOrDefault(curSum, 0)+1);

        res += backtrack(map, curSum, sum, root.left);
        res += backtrack(map, curSum, sum, root.right);

        map.put(curSum, map.get(curSum)-1);

        return res;
    }

}
