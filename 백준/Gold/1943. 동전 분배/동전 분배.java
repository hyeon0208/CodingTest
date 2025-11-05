import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 3; i++) {
			N = Integer.parseInt(br.readLine());
			Coin[] coins = new Coin[N + 1];
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int price = Integer.parseInt(st.nextToken());
				int amount = Integer.parseInt(st.nextToken());
				coins[j] = new Coin(price, amount);
				sum += price * amount;
			}

			if (sum % 2 == 1) {
				System.out.println(0);
				continue;
			}

			int target = sum / 2; // 두그룹이 나누어 가지는 수까지만 탐색하면 됨.
			boolean[] dp = new boolean[50001];
			dp[0] = true; // 동전을 사용하지 않았을 경우

			for (int x = 1; x <= N; x++) {
				int p = coins[x].price;
				int a = coins[x].amount;
				for (int money = target; money >= p; money--) {
					if (dp[money - p]) { // 이미 만들 수 있는 금액이라면 동전 개수를 추가해도 만들 수 있음.
						for (int k = 1; k <= a; k++) {
							int curPrice = money - p;
							int curSum = p * k;
							if (curPrice + curSum > target) {
								break;
							}
							dp[curPrice + curSum] = true;
						}
					}
				}
			}
			System.out.println(dp[target] ? 1 : 0);
		}
	}

	private static class Coin {
		int price;
		int amount;

		public Coin(int price, int amount) {
			this.price = price;
			this.amount = amount;
		}
	}
}
