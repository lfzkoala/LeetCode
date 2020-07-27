/*
Binary Search Tree Inorder Traversal
        Given a binary tree, return the inorder traversal of its nodes' values.

        Example:

        Input: [1,null,2,3]
        1
        \
        2
        /
        3

        Output: [1,3,2]
        Follow up: Recursive solution is trivial, could you do it iteratively?

        方法1:
        recursive method

 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Inorder(root, result);
        return result;
    }

    private void Inorder(TreeNode root, List<Integer> list){
        if(root == null) return;
        Inorder(root.left, list);
        list.add(root.val);
        Inorder(root.right, list);
    }

}

/*
方法2:
        iterative

 */

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {

        //Iterative method using stack

        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        while(root != null || !stack.isEmpty()){

            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }

        return result;



    }
}
