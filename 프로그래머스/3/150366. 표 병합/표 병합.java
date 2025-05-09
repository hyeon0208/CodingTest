import java.util.*;

class Solution {
    
    public static String[] solution(String[] commands) {
        List<String> results = new ArrayList<>();
        Map<String, String> parent = new HashMap<>(); // 각 셀의 부모 셀 저장
        Map<String, String> values = new HashMap<>(); // 각 병합 그룹의 값 저장

        // 모든 셀이 자신을 부모로 가짐
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                String cell = cellKey(i, j);
                parent.put(cell, cell);
            }
        }

        for (String command : commands) {
            String[] info = command.split(" ");
            
            if (info[0].equals("UPDATE")) {
                if (info.length == 4) { // "UPDATE r c value"
                    int r = Integer.parseInt(info[1]);
                    int c = Integer.parseInt(info[2]);
                    String value = info[3];
                    String cellKey = cellKey(r, c);
                    String root = findRoot(parent, cellKey);
                    values.put(root, value);
                } else { // "UPDATE value1 value2"
                    String value1 = info[1];
                    String value2 = info[2];
                    // ConcurrentModificationException 방지
                    List<String> keysToUpdate = new ArrayList<>();
                    for (Map.Entry<String, String> entry : values.entrySet()) {
                        if (entry.getValue().equals(value1)) {
                            keysToUpdate.add(entry.getKey());
                        }
                    }
                    for (String key : keysToUpdate) {
                        values.put(key, value2);
                    }
                }
            }
            
            else if (info[0].equals("MERGE")) { // "MERGE r1 c1 r2 c2"
                int r1 = Integer.parseInt(info[1]);
                int c1 = Integer.parseInt(info[2]);
                int r2 = Integer.parseInt(info[3]);
                int c2 = Integer.parseInt(info[4]);

                if (r1 == r2 && c1 == c2) {
                    continue;
                }

                String cell1 = cellKey(r1, c1);
                String cell2 = cellKey(r2, c2);
                String root1 = findRoot(parent, cell1);
                String root2 = findRoot(parent, cell2);

                if (root1.equals(root2)) {
                    continue;
                }

                String value1 = values.getOrDefault(root1, "");
                String value2 = values.getOrDefault(root2, "");
                
                // 문제 요구사항에 따른 값 결정
                String mergedValue = "";
                if (!value1.isEmpty()) {
                    mergedValue = value1;  // r1, c1의 값 우선
                } else if (!value2.isEmpty()) {
                    mergedValue = value2;
                }
                
                // 병합 - 대표 셀 선택 및 값 관리
                parent.put(root2, root1);  // root1을 새 그룹의 대표로
                
                if (!mergedValue.isEmpty()) {
                    values.put(root1, mergedValue);
                }
                values.remove(root2);  // 더 이상 대표가 아닌 셀의 값 제거
            }
            
            else if (info[0].equals("UNMERGE")) { // "UNMERGE r c"
                int r = Integer.parseInt(info[1]);
                int c = Integer.parseInt(info[2]);
                String cellKey = cellKey(r, c);
                String root = findRoot(parent, cellKey);
                String value = values.getOrDefault(root, "");
                
                // 병합 그룹에 속한 모든 셀 찾기
                List<String> cellsToUnmerge = new ArrayList<>();
                for (int i = 1; i <= 50; i++) {
                    for (int j = 1; j <= 50; j++) {
                        String cell = cellKey(i, j);
                        if (findRoot(parent, cell).equals(root)) {
                            cellsToUnmerge.add(cell);
                        }
                    }
                }
                
                // 그룹 값 제거
                values.remove(root);
                
                // 모든 셀 병합 해제
                for (String cell : cellsToUnmerge) {
                    parent.put(cell, cell);
                }
                
                // 지정된 셀에만 값 설정 (값이 있는 경우만)
                if (!value.isEmpty()) {
                    values.put(cellKey, value);
                }
            }
            
            else if (info[0].equals("PRINT")) { // "PRINT r c"
                int r = Integer.parseInt(info[1]);
                int c = Integer.parseInt(info[2]);
                String cellKey = cellKey(r, c);
                String root = findRoot(parent, cellKey);
                
                String value = values.getOrDefault(root, "");
                if (value.isEmpty()) {
                    results.add("EMPTY");
                } else {
                    results.add(value);
                }
            }
        }

        String[] answer = new String[results.size()];
        for (int i = 0; i < results.size(); i++) {
            answer[i] = results.get(i);
        }

        return answer;
    }

    private static String findRoot(Map<String, String> parent, String cellKey) {
        if (parent.get(cellKey).equals(cellKey)) {
            return cellKey;
        }
        String root = findRoot(parent, parent.get(cellKey));
        parent.put(cellKey, root); // 경로 압축
        return root;
    }

    private static String cellKey(int r, int c) {
        return r + "," + c;
    }
}