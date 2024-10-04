import java.util.*;

class Solution {
    private static final String[] OPERATORS = {"+", "-", "*"};
    
    public long solution(String expression) {
        List<String> tokens = tokenize(expression);
        
        // 가능한 모든 연산자 우선순위 조합
        List<List<String>> priorities = generatePriorities();
        
        System.out.println(priorities);
        
        long maxResult = 0;
        
        // 각 우선순위 조합에 대해 계산
        for (List<String> priority : priorities) {
            List<String> expressionCopy = new ArrayList<>(tokens);
            
            for (String operator : priority) {
                expressionCopy = calculate(expressionCopy, operator);
            }
            
            // 최종 결과의 절댓값으로 최댓값 갱신
            maxResult = Math.max(maxResult, Math.abs(Long.parseLong(expressionCopy.get(0))));
        }
        
        return maxResult;
    }
    
        
    private List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder number = new StringBuilder();
        
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                number.append(c);
            } else {
                tokens.add(number.toString());
                number = new StringBuilder();
                tokens.add(String.valueOf(c));
            }
        }
        
        if (number.length() > 0) {
            tokens.add(number.toString());
        }
        
        return tokens;
    }
    
    private List<List<String>> generatePriorities() {
        List<List<String>> priorities = new ArrayList<>();
        generatePrioritiesHelper(new ArrayList<>(), new boolean[3], priorities);
        return priorities;
    }
    
    private void generatePrioritiesHelper(List<String> current, boolean[] used, List<List<String>> result) {
        if (current.size() == 3) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < 3; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(OPERATORS[i]);
                generatePrioritiesHelper(current, used, result);
                current.remove(OPERATORS[i]);
                used[i] = false;
            }
        }
    }
    
    private List<String> calculate(List<String> expression, String operator) {
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < expression.size(); i++) {
            if (expression.get(i).equals(operator)) {
                long left = Long.parseLong(result.remove(result.size() - 1));
                long right = Long.parseLong(expression.get(++i));
                long calculated = performOperation(left, right, operator);
                result.add(String.valueOf(calculated));
            } else {
                result.add(expression.get(i));
            }
        }
        
        return result;
    }
    
    private long performOperation(long left, long right, String operator) {
        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}