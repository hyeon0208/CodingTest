import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> list = new ArrayList<>();
		HashMap<Integer, Integer> hashmap = new HashMap<>();
		int loop = Integer.parseInt(br.readLine());
		double sum = 0;
		for(int i=0; i<loop; i++) {
			int temp = Integer.parseInt(br.readLine());
			sum += (double) temp;
			list.add(temp);
		}
		Collections.sort(list);

		int max = Collections.max(list);
		int min = Collections.min(list);

		if(max < 0 && min < 0) {
			min = Math.abs(min);
		}
		else if(max > 0 && min > 0) {
			min = min*-1;
		}
		else {
			max = Math.abs(max);
			min = Math.abs(min);
		}

		double dle = sum / loop;
		int average = (int) Math.round(dle);
		int median = list.get(loop/2);
		int mode = 0;
		int range = max + min;

		for(int num : list) {
			hashmap.put(num, hashmap.getOrDefault(num, 0) + 1);
		}

		int numbers = 0;
		for(int key : hashmap.values()) {
			numbers = Math.max(numbers, key);
		}

		list.clear();
		for(int key : hashmap.keySet()) {
			if(hashmap.get(key) == numbers) {
				list.add(key);
			}
		}
		Collections.sort(list);

		if(list.size() >= 2) {
			mode = list.get(1);
		}
		else {
			mode = list.get(0);
		}

		System.out.println(average);
		System.out.println(median);
		System.out.println(mode);
		System.out.println(range);
	}
}