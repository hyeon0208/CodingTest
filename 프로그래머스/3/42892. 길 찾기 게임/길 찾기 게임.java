import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    
    private static int index;
    
    public static int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];

        List<Node> nodes = new ArrayList<>(nodeinfo.length);
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null));
        }

        nodes.sort((n1, n2) -> {
            if (n1.y == n2.y) {
                return n1.x - n2.x;
            } else {
                return n2.y - n1.y;
            }
        });

        Node root = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            createTree(root, nodes.get(i));
        }

        index = 0;
        answer[0] = preOrder(nodes.get(0), new int[nodes.size()]);

        index = 0;
        answer[1] = postOrder(nodes.get(0), new int[nodes.size()]);
        
        return answer;
    }

    private static void createTree(Node parent, Node child) {
        if (parent.x < child.x) {
            if (parent.right == null) {
                parent.right = child;
            } else {
                createTree(parent.right, child);
            }
        } else {
            if (parent.left == null) {
                parent.left = child;
            } else {
                createTree(parent.left, child);
            }
        }
    }

    private static int[] preOrder(Node curNode, int[] order) {
        if (curNode != null) {
            order[index++] = curNode.value;
            preOrder(curNode.left, order);
            preOrder(curNode.right, order);
        }
        return order;
    }

    private static int[] postOrder(Node curNode, int[] order) {
        if (curNode != null) {
            postOrder(curNode.left, order);
            postOrder(curNode.right, order);
            order[index++] = curNode.value;
        }
        return order;
    }

    public static class Node {
        int x;
        int y;
        int value;
        Node left;
        Node right;

        public Node(int x, int y, int value, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}