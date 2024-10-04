import java.util.*;

class Solution {
    private static final Map<Integer, int[]> keypadCoordinates = new HashMap<>();
    
    static {
        keypadCoordinates.put(1, new int[]{0, 0});
        keypadCoordinates.put(2, new int[]{0, 1});
        keypadCoordinates.put(3, new int[]{0, 2});
        keypadCoordinates.put(4, new int[]{1, 0});
        keypadCoordinates.put(5, new int[]{1, 1});
        keypadCoordinates.put(6, new int[]{1, 2});
        keypadCoordinates.put(7, new int[]{2, 0});
        keypadCoordinates.put(8, new int[]{2, 1});
        keypadCoordinates.put(9, new int[]{2, 2});
        keypadCoordinates.put(0, new int[]{3, 1});
    }

    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        int[] leftHand = {3, 0};
        int[] rightHand = {3, 2};

        for (int number : numbers) {
            if (number == 1 || number == 4 || number == 7) {
                answer.append("L");
                leftHand = keypadCoordinates.get(number);
            } else if (number == 3 || number == 6 || number == 9) {
                answer.append("R");
                rightHand = keypadCoordinates.get(number);
            } else {
                int[] target = keypadCoordinates.get(number);
                int leftDistance = getDistance(leftHand, target);
                int rightDistance = getDistance(rightHand, target);

                if (leftDistance < rightDistance || (leftDistance == rightDistance && hand.equals("left"))) {
                    answer.append("L");
                    leftHand = target;
                } else {
                    answer.append("R");
                    rightHand = target;
                }
            }
        }

        return answer.toString();
    }

    private int getDistance(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }
}

