class Solution {
    public int solution(int n, int k) {
        int servicePrice = (n / 10) * 2000;
        return (n * 12000) + (k * 2000) - servicePrice;
    }
}