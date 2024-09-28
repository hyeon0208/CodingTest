class Solution {
    public int solution(int n) {
        int bitCount = Integer.bitCount(n);

        int nextNumber = n;

        while (true) {
            nextNumber++;

            if (bitCount == Integer.bitCount(nextNumber)) {
                break;
            }
        }

        return nextNumber;
    }
}