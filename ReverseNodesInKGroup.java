/*
Reverse Nodes in k-Group

        Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
        k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
        Example:
        Given this linked list: 1->2->3->4->5
        For k = 2, you should return: 2->1->4->3->5
        For k = 3, you should return: 3->2->1->4->5
        Note:
        Only constant extra memory is allowed.
        You may not alter the values in the list's nodes, only nodes itself may be changed.
        答案：

 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        int len = 0;

        ListNode counter = head;

        //count the length of the linked list
        while(counter != null){
            counter = counter.next;
            len++;
        }

        int round = len/k; //count number of rounds.
        if(round == 0) return head; //remain as it is.

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;

        for(int i=0; i<round; i++){
            ListNode first = pre.next;
            ListNode second = first.next;

            for(int j=0; j<k-1; j++){
                first.next = second.next;
                second.next = pre.next;
                pre.next = second;
                second = first.next;
            }

            pre = first;
        }

        return dummy.next;
    }
}
