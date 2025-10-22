class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n + 1;

        // 카드 보유 여부 (1~n까지)
        boolean[] hasCard = new boolean[n + 1];
        // 무료 카드인지 (초기 손 카드는 무료)
        boolean[] isFree = new boolean[n + 1];

        // 초기 손 카드 (처음 n/3장은 무료)
        for (int i = 0; i < n / 3; i++) {
            hasCard[cards[i]] = true;
            isFree[cards[i]] = true;
        }

        int round = 1;
        int cardIndex = n / 3;

        while (cardIndex < n) {
            // 매 라운드마다 카드 2장 뽑기
            hasCard[cards[cardIndex]] = true;
            cardIndex++;
            if (cardIndex < n) {
                hasCard[cards[cardIndex]] = true;
                cardIndex++;
            }

            // 가능한 모든 쌍 중 가장 저렴한 쌍 찾기
            int minCost = Integer.MAX_VALUE;
            int bestCard1 = -1;
            int bestCard2 = -1;

            for (int i = 1; i <= n; i++) {
                if (hasCard[i]) {
                    int complement = target - i;
                    if (complement > 0 && complement <= n && complement != i && hasCard[complement]) {
                        // 이 쌍의 비용 계산
                        int cost = (isFree[i] ? 0 : 1) + (isFree[complement] ? 0 : 1);

                        // 더 저렴한 쌍이면 업데이트
                        if (cost < minCost) {
                            minCost = cost;
                            bestCard1 = i;
                            bestCard2 = complement;
                        }
                    }
                }
            }

            // 현재 코인으로 구매 가능한 쌍이 있는지 확인
            if (minCost <= coin) {
                hasCard[bestCard1] = false;
                hasCard[bestCard2] = false;
                coin -= minCost;
                round++;
            } else {
                break;
            }
        }

        return round;
    }
}