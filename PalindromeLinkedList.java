/*
Palindrome Linked List

        Given a singly linked list, determine if it is a palindrome.
        Example 1:
        Input: 1->2
        Output: false
        Example 2:
        Input: 1->2->2->1
        Output: true
        Follow up:
        Could you do it in O(n) time and O(1) space?

        答案：

        This can be solved by reversing the 2nd half and compare the two halves. Let's start with an example [1, 2, 2, 1].
        In the beginning, set two pointers fast and slow starting at the head.
        1 -> 2 -> 2 -> 1 -> null
        sf

        (1) Move: fast pointer goes to the end, and slow goes to the middle.
        1 -> 2 -> 2 -> 1 -> null
        s          f

        (2) Reverse: the right half is reversed, and slow pointer becomes the 2nd head.
        1 -> 1 -> null <- 2 <- 1
        h                      s

        (3) Compare: run the two pointers head and slow together and compare.
        1 -> 1 -> null <- 2 <- 1
        h            s


 */
class Solution {
    public boolean isPalindrome(ListNode head) {

        ListNode slow = head, fast = head;


        //find the mid point and set slow as the starting point of the second half of list via the reverse function.
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast != null){//if list has odd length, move slow pointer one more step.
            slow = slow.next;
        }

        slow = reverse(slow);
        fast = head;

        //take fast as the starting point of first half, slow as the starting point of second half, and then compare one by one.
        while(slow != null){
            if(slow.val != fast.val) return false;
            slow = slow.next;
            fast = fast.next;
        }

        return true;
    }

    private ListNode reverse(ListNode slow){
        ListNode prev = null;
        while(slow != null){
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        return prev;
    }




}
