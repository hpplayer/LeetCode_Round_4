/*
123. Best Time to Buy and Sell Stock III

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/**
 * DP solution
 * 
 * We will store buy value as a negative value, so that when we considering the second buy after first sell
 * We can use Math.max() to get the value.
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 22, 2016 9:28:26 PM
 */
public class Best_Time_to_Buy_and_Sell_Stock_III_p123_sol1 {
    public int maxProfit(int[] prices) {
        //boundary check
        
        if(prices.length == 0) return 0;
        
        //update first and second buy value
        //we set buy value to be a negative value so that we can always use Math.max() to get optimized value
        int firstBuy = -prices[0];
        int secondBuy = -prices[0];
        
        //update first and second sell value
        int firstSell = 0;
        int secondSell = 0;
        
        for(int i = 1; i < prices.length; i++){
            firstBuy = Math.max(firstBuy, -prices[i] );
            firstSell = Math.max( firstSell, prices[i] + firstBuy );
            
            //second buy is based on the value of firstSell
            secondBuy = Math.max(secondBuy, firstSell - prices[i] );
            secondSell = Math.max(secondSell, prices[i] + secondBuy );
        }
        
        //if one transaction can make the max profit then we will have secondSell = firstSell
        //if two transactions can make max profit, then secondSell will have larger value
        return secondSell;
    }
}
