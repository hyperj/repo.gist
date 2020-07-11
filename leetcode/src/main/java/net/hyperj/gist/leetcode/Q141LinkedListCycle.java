package net.hyperj.gist.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Q141LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> nodes = new HashSet<>();
        while (head != null) {
            if (nodes.contains(head)) {
                return true;
            } else {
                nodes.add(head);
            }
            head = head.next;
        }
        return false;
    }
}
