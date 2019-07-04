package net.hyperj.gist.leetcode;

public class Q2AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode curr = root;
        while (l1 != null || l2 != null) {
            int sum = ((l1 != null) ? l1.val : 0) + ((l2 != null) ? l2.val : 0);
            if (curr.next != null) curr.next.val = ++sum % 10;
            else curr.next = new ListNode(sum % 10);
            if (sum >= 10) curr.next.next = new ListNode(1);
            curr = curr.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return root.next;
    }

    public static void main(String[] args) {
        ListNode l1 = ListNode.init(new int[]{1, 3, 5, 7, 9});
        ListNode l2 = ListNode.init(new int[]{2, 4, 6, 8});
        ListNode.print(new Q2AddTwoNumbers().addTwoNumbers(l1, l2));

    }


}