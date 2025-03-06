import java.util.*;

class Solution {
    
    public int[] solution(long begin, long end) {
        int st = (int) begin;
        int en = (int) end; 
        int [] answer = new int [en - st + 1];
        
        // 모든 값을 1로 초기화 (기본값은 블록 1이라고 가정)
        Arrays.fill(answer, 1);
        
        // 각 위치마다 어떤 블록이 놓이는지 계산
        for(int i = st; i <= en; i++){ 
            answer[i - st] = calculate(i);
        }
        
        // 위치 1의 특별 규칙 처리: 블록 0이 놓임
        if(st == 1) answer[0] = 0;
        
        return answer;
    }    
    
    // 위치 input에 최종적으로 놓이는 블록 번호를 계산하는 함수
    public static int calculate(int input){
        // 기본값: 블록 1 (모든 숫자는 1의 배수이므로)
        int max = 1;
        
        // 2부터 sqrt(input)까지 모든 수에 대해 검사
        for(int i = 2; i * i <= input; i++){
            // i가 input의 약수인지 확인
            if(input % i == 0){
                // i는 input의 약수
                max = i; // 일단 i를 저장
                
                // input/i도 input의 약수
                // 이 값이 10,000,000 이하라면 이게 정답
                if(input / i <= 10000000)
                    return input / i;
            }
        }
        
        // 10,000,000 이하의 약수 중 가장 큰 값이 max
        return max;
    }
}