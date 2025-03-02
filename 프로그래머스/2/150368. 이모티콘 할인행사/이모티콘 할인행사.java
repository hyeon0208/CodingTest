import java.util.Arrays;

class Solution {
    
    private static final int[] DISCOUNT = {10, 20, 30, 40};
    private static int maxPlusSubscribers = 0;
    private static int maxTotalSales = 0;

    public static int[] solution(int[][] users, int[] emoticons) {
        int[] discounts = new int[emoticons.length];
        backTracking(users, 0, emoticons, discounts);

        return new int[]{maxPlusSubscribers, maxTotalSales};
    }

    private static void backTracking(int[][] users, int depth, int[] emoticons, int[] discounts) {
        if (depth == emoticons.length) {
            calculateResult(users, emoticons, discounts);
            return;
        }

        for (int d : DISCOUNT) {
            discounts[depth] = d;
            backTracking(users, depth + 1, emoticons, discounts);
        }
    }

    private static void calculateResult(int[][] users, int[] emoticons, int[] discounts) {
        int plusSubscribers = 0;
        int totalSales = 0;

        for (int[] user : users) {
            int userDiscountRate = user[0];
            int userSubscribeAmount = user[1];
            int total = 0;

            for (int i = 0; i < emoticons.length; i++) {
                if (discounts[i] >= userDiscountRate) {
                    int discountedPrice = emoticons[i] * (100 - discounts[i]) / 100;
                    total += discountedPrice;
                }
            }

            if (total >= userSubscribeAmount) {
                plusSubscribers++;
            } else {
                totalSales += total;
            }
        }

        if (plusSubscribers > maxPlusSubscribers || (plusSubscribers == maxPlusSubscribers && totalSales > maxTotalSales)) {
            maxPlusSubscribers = plusSubscribers;
            maxTotalSales = totalSales;
        }
    }
}