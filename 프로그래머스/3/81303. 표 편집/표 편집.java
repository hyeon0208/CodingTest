import java.util.Stack;

class Solution {

    static class Node {
        int index;
        Node prev;
        Node next;

        Node(int index) {
            this.index = index;
        }
    }

    public static String solution(int n, int k, String[] cmd) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 1; i < n; i++) {
            nodes[i-1].next = nodes[i];
            nodes[i].prev = nodes[i-1];
        }

        Node current = nodes[k];
        Stack<Node> removed = new Stack<>();

        for (String command : cmd) {
            char type = command.charAt(0);

            if (type == 'U') {
                int x = Integer.parseInt(command.substring(2));
                for (int i = 0; i < x; i++) {
                    if (current.prev != null) {
                        current = current.prev;
                    }
                }
            } else if (type == 'D') {
                int x = Integer.parseInt(command.substring(2));
                for (int i = 0; i < x; i++) {
                    if (current.next != null) {
                        current = current.next;
                    }
                }
            } else if (type == 'C') {
                removed.push(current);

                Node prev = current.prev;
                Node next = current.next;

                if (prev != null) {
                    prev.next = next;
                }

                if (next != null) {
                    next.prev = prev;
                    current = next;
                } else {
                    current = prev;
                }
            } else if (type == 'Z') {
                if (!removed.isEmpty()) {
                    Node node = removed.pop();
                    Node prev = node.prev;
                    Node next = node.next;

                    if (prev != null) {
                        prev.next = node;
                    }

                    if (next != null) {
                        next.prev = node;
                    }
                }
            }
        }

        boolean[] isDeleted = new boolean[n];
        while (!removed.isEmpty()) {
            isDeleted[removed.pop().index] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(isDeleted[i] ? "X" : "O");
        }

        return sb.toString();
    }
}