package net.hyperj.gist.leetcode;

public class Q83RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode list = head;
        while (list != null && list.next != null) {
            if (list.val == list.next.val) {
                list.next = list.next.next;
            } else {
                list = list.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode.print(new Q83RemoveDuplicatesFromSortedList().deleteDuplicates(ListNode.init(new int[]{1, 1, 2, 3, 3})));
    }
}
