package net.hyperj.gist.leetcode;

public class Q121BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null && prices.length <= 1) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > profit) {
                profit = prices[i] - min;
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.printf(String.valueOf(new Q121BestTimeToBuyAndSellStock().maxProfit(new int[]{1, 2})));
    }
}
