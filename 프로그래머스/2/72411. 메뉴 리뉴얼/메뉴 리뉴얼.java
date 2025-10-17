import java.util.*;

class Solution {
	private static Map<String, Integer> combi;

	public String[] solution(String[] orders, int[] course) {
		List<String> results = new ArrayList<>();

		for (int c : course) {
			combi = new HashMap<>();

			for (String order : orders) {
				char[] arr = order.toCharArray();
				Arrays.sort(arr);
				String sortedOrder = new String(arr);

				dfs(sortedOrder, 0, c, "");
			}

			int maxCount = 0;
			for (int count : combi.values()) {
				if (count >= 2) {
					maxCount = Math.max(maxCount, count);
				}
			}

			if (maxCount >= 2) {
				for (Map.Entry<String, Integer> entry : combi.entrySet()) {
					if (entry.getValue() == maxCount) {
						results.add(entry.getKey());
					}
				}
			}
		}

		Collections.sort(results);
		return results.toArray(String[]::new);
	}

	private void dfs(String order, int idx, int limit, String current) {
		if (current.length() == limit) {
			combi.put(current, combi.getOrDefault(current, 0) + 1);
			return;
		}

		if (idx >= order.length()) {
			return;
		}

		// 현재 문자 선택
		dfs(order, idx + 1, limit, current + order.charAt(idx));

		// 현재 문자 선택하지 않음
		dfs(order, idx + 1, limit, current);
	}
}
