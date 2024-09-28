class Solution {
    private static int[] answer = new int[2];
    
    public int[] solution(String s) {
        convertBinary(s);

        return answer;
    }
    
    private void convertBinary(String s) {
        if (s.length() == 1) {
            return;
        }
        answer[0]++;
        String replaced = s.replaceAll("0", "");
        answer[1] += s.length() - replaced.length();

        int n = replaced.length();

        String newS = Integer.toBinaryString(n);
        convertBinary(newS);
    }
}