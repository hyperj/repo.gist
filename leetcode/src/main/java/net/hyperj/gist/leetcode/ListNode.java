package net.hyperj.gist.leetcode;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static ListNode init(int[] arrs) {
        if (arrs != null && arrs.length > 0) {
            ListNode list = new ListNode(0);
            ListNode temp = list;
            for (int i : arrs) {
                temp.next = new ListNode(i);
                temp = temp.next;
            }
            return list.next;
        }
        return null;
    }

    public static void print(ListNode l) {
        while (l != null) {
            System.out.print(l.val);
            if (l.next != null) {
                System.out.print(", ");
            }
            l = l.next;
        }
    }
}