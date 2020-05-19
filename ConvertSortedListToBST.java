/*
Convert Sorted List to BST

        Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

        For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

        Example:

        Given the sorted linked list: [-10,-3,0,5,9],

        One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

        0
        / \
        -3   9
        /   /
        -10  5



        方法1：




/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode sortedListToBST(ListNode head) {

        if(head == null) return null;
        if(head.next == null) return new TreeNode(head.val);

        ListNode prev = head, slow = head, fast = head;

        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

//最关键的是slow pointer, 永远对准根节点, 记得考虑null节点
        TreeNode left = sortedListToBST(head);
        TreeNode right = sortedListToBST(slow.next);
        TreeNode mid = new TreeNode(slow.val);

        mid.left = left;
        mid.right = right;

        return mid;
    }
}

/*
方法2：更加直接O(n) time, O(n) space

 */

class Solution {
    public TreeNode sortedListToBST(ListNode head) {

        if(head == null) return null;
        if(head.next == null) return new TreeNode(head.val);

        //O(n) space complexity, O(n) time complexity
        List<Integer> list = new ArrayList<>();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }

        return Helper(list, 0, list.size()-1);
    }

    private TreeNode Helper(List<Integer> list, int left, int right){
        if(left > right) return null;

        int mid = (left+right)/2;

        TreeNode root = new TreeNode(list.get(mid));

        //if(left == right) return root;

        root.left = Helper(list, left, mid-1);
        root.right = Helper(list, mid+1, right);

        return root;

    }
}
