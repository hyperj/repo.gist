package net.hyperj.gist.leetcode;

public class Q21MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode curr = root;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                curr.next = l2;
                break;
            } else if (l2 == null) {
                curr.next = l1;
                break;
            }
            if (l1.val < l2.val) {
                curr.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                curr.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            curr = curr.next;
        }
        return root.next;
    }

    public static void main(String[] args) {
        ListNode l1 = ListNode.init(new int[]{1, 3, 5, 7, 9});
        ListNode l2 = ListNode.init(new int[]{2, 4, 6, 8});
        ListNode.print(new Q21MergeTwoSortedLists().mergeTwoLists(l1, l2));
    }
}
