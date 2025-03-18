import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String expression = br.readLine();

        List<Character> operators = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();

        // 숫자와 연산자 분리
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) { 
                numbers.add(expression.charAt(i) - '0');
            } else {
                operators.add(expression.charAt(i));
            }
        }

        // 백트래킹을 통한 모든 가능한 괄호 배치 탐색
        long result = backtrack(numbers, operators);
        System.out.println(result);
    }

    private static int backtrack(List<Integer> numbers, List<Character> operators) {
        // 숫자가 1개만 남으면 그 값이 결과
        if (numbers.size() == 1) {
            return numbers.get(0);
        }

        int maxResult = Integer.MIN_VALUE;

        // 모든 연산자에 대해 괄호를 추가하는 경우를 시도
        for (int i = 0; i < operators.size(); i++) {
            // 현재 연산자로 두 숫자를 계산
            int result = calculate(numbers.get(i), numbers.get(i + 1), operators.get(i));

            // 계산 결과로 새로운 숫자와 연산자 리스트 생성
            List<Integer> newNumbers = new ArrayList<>(numbers);
            List<Character> newOperators = new ArrayList<>(operators);

            // 계산에 사용된 두 숫자 제거하고 결과 삽입
            newNumbers.remove(i);
            newNumbers.remove(i); // 인덱스가 하나 줄어든 상태에서 i번째 다시 제거
            newNumbers.add(i, (int) result); // 계산 결과 삽입

            // 사용한 연산자 제거
            newOperators.remove(i);

            // 남은 식에 대해 재귀적으로 백트래킹 수행
            int subResult = backtrack(newNumbers, newOperators);

            maxResult = Math.max(maxResult, subResult);
        }

        return maxResult;
    }

    private static int calculate(int a, int b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        }
        return a * b;
    }
}
