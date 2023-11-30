import java.io.*;
import java.util.*;

public class Main {
    static List<String> grades = List.of("A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0", "F", "P");
    static List<Double> scores = List.of(4.5, 4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.0, 0.0, 0.0);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalScore = 0;  // 학점의 총합
        double gradeSum = 0.0;  // 전공평점의 합

        for (int i = 0; i < 20; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String subject = st.nextToken(); // 과목
            double score = Double.parseDouble(st.nextToken()); // 학점
            String grade = st.nextToken(); // 등급

            if (!grade.equals("P")) {
                totalScore += score; // 총학점을 계산하기 위함
                if (grade.contains(grade)) {
                    gradeSum += score * scores.get(grades.indexOf(grade));
                }
            }
        }
        double result = gradeSum / totalScore;
        System.out.printf("%.6f", result);
    }
}

