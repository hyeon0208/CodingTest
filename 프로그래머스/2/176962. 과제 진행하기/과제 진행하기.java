import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        List<String> result = new ArrayList<>();
        Stack<Task> stack = new Stack<>();
        
        List<Task> tasks = new ArrayList<>(plans.length);
        for (String[] plan : plans) {
            tasks.add(new Task(plan[0], timeToMinutes(plan[1]), Integer.parseInt(plan[2])));
        }
        
        tasks.sort((a, b) -> a.startTime - b.startTime);
        
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            
            // 다음 과제 시작 전까지 할 수 있는 과제들 처리
            if (i < tasks.size() - 1) {
                int nextStartTime = tasks.get(i + 1).startTime;
                int availableTime = nextStartTime - currentTask.startTime;
                
                if (currentTask.duration <= availableTime) {
                    // 현재 과제를 완료할 수 있는 경우
                    result.add(currentTask.name);
                    availableTime -= currentTask.duration;
                    
                    // 남은 시간으로 이전에 중단된 과제들 처리
                    while (!stack.isEmpty() && availableTime > 0) {
                        Task pausedTask = stack.peek();
                        if (pausedTask.duration <= availableTime) {
                            result.add(stack.pop().name);
                            availableTime -= pausedTask.duration;
                        } else {
                            pausedTask.duration -= availableTime;
                            break;
                        }
                    }
                } else {
                    // 현재 과제를 완료할 수 없는 경우
                    currentTask.duration -= availableTime;
                    stack.push(currentTask);
                }
            } 
            
            if (i == tasks.size() - 1) {
                result.add(currentTask.name);
                while (!stack.isEmpty()) {
                    result.add(stack.pop().name);
                }
            }
        }
        
        return result.toArray(new String[0]);
    }
    
    private static class Task {
        String name;
        int startTime;
        int duration;
        
        Task(String name, int startTime, int duration) {
            this.name = name;
            this.startTime = startTime;
            this.duration = duration;
        }
    }
    
    private int timeToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}