
/*
Binary Tree Upside Down
        Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
        Example:
        Input: [1,2,3,4,5]

        1
        / \
        2   3
        / \
        4   5

        Output: return the root of the binary tree [4,5,2,#,#,3,1]

        4
        / \
        5   2
        / \
        3   1

        Clarification:
        Confused what [4,5,2,#,#,3,1] means? Read more below on how binary tree is serialized on OJ.
        The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
        Here's an example:
        1
        / \
        2   3
        /
        4
        \
        5

        The above binary tree is serialized as [1,2,3,#,#,4,#,#,5].
        思路：
        1
        / \
        2 --3
        / \
        4-- 5

        进行树的切割和连接 最后返回根节点(root)
        这题Iterative更好理解
        
 */
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {

        TreeNode cur = root;
        TreeNode nextNode = null;
        TreeNode left = null;
        TreeNode right = null;

        while(cur != null){
            //use nextNode to denote the next node to process and link new left node and new right node.
            //use "left" to denote the new left node to be linked, "right" to denote the new right node to be linked.
            nextNode = cur.left;
            cur.left = left;
            left = cur.right;
            cur.right = right;
            right = cur;
            cur = nextNode;
        }

        return right; //after the loop, right node is the final current node, which is actually the root to be returned

    }
}



class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {

        if(root == null || root.left == null && root.right == null){
            return root;
        }

        TreeNode newNode = upsideDownBinaryTree(root.left);

        root.left.left = root.right;
        root.left.right = root;

        root.left = null;
        root.right = null;

        return newNode;
    }
}
