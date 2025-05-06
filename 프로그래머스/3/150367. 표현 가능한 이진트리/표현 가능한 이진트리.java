import java.util.Arrays;

class Solution {
    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            if (isSubBinaryTree(numbers[i])) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }

    private static boolean isSubBinaryTree(long number) {
        String fullBinary = getFullBinary(Long.toBinaryString(number));
        int len = fullBinary.length();

        int root = len / 2;
        String leftSubTree = fullBinary.substring(0, root);
        String rightSubTree = fullBinary.substring(root + 1);

        if (fullBinary.charAt(root) == '0') {
            return false;
        }

        return isSubBinaryTree(leftSubTree) && isSubBinaryTree(rightSubTree);
    }

    private static boolean isSubBinaryTree(String binary) {
        if (binary.isEmpty()) {
            return true;
        }
        int len = binary.length();

        int root = len / 2;
        String leftSubTree = binary.substring(0, root);
        String rightSubTree = binary.substring(root + 1);

        if (binary.charAt(root) == '0') {
            return isZeroSubBinaryTree(leftSubTree) && isZeroSubBinaryTree(rightSubTree);
        }

        return isSubBinaryTree(leftSubTree) && isSubBinaryTree(rightSubTree);
    }

    private static boolean isZeroSubBinaryTree(String binary) {
        if (binary.isEmpty()) {
            return true;
        }
        
        for (char bit : binary.toCharArray()) {
            if (bit != '0') {
                return false;
            }
        }
        
        return true;
    }

    private static String getFullBinary(String binary) {
        int len = binary.length();
        int level = 1;
        int nodeCount = 1;
        while (len > nodeCount) {
            level *= 2;
            nodeCount += level;
        }

        int offset = nodeCount - len;
        return "0".repeat(offset) + binary;
    }
}