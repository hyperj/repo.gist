package net.hyperj.gist.leetcode;

public class Q234PalindromeLinkedList {
    private ListNode headNode;

    private boolean helper(ListNode currentNode) {
        if (currentNode != null) {
            if (!helper(currentNode.next)) {
                return false;
            }
            if (currentNode.val != headNode.val) {
                return false;
            }
            headNode = headNode.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        headNode = head;
        return helper(head);
    }
}
