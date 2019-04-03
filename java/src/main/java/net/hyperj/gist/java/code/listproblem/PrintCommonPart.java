package net.hyperj.gist.java.code.listproblem;

public class PrintCommonPart {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {
        Node node1 = getNode(new int[]{2, 4, 6, 8});
        Node node2 = getNode(new int[]{0, 1, 2, 3, 4, 5});
        printCommonPart(node1, node2);
    }

    public static Node getNode(int[] arr) {
        if (arr != null && arr.length > 0) {
            Node node = new Node(0);
            Node temp = node;
            System.out.print("Node: ");
            for (int i : arr) {
                System.out.print(i + " ");
                temp.next = new Node(i);
                temp = temp.next;
            }
            System.out.println();
            return node.next;
        }
        throw new RuntimeException("err, arr is empty!");
    }

    public static void printCommonPart(Node node1, Node node2) {
        System.out.print("Common Part: ");
        while (node1 != null && node2 != null) {
            if (node1.value > node2.value) {
                node2 = node2.next;
            } else if (node1.value < node2.value) {
                node1 = node1.next;
            } else {
                System.out.print(node1.value + " ");
                node1 = node1.next;
                node2 = node2.next;
            }
        }
        System.out.println();
    }

}
