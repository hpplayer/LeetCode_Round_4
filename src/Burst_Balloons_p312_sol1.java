/*
312. Burst Balloons

Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ¡Ü n ¡Ü 500, 0 ¡Ü nums[i] ¡Ü 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
*/


/**
 * DP solution
 * 
 * We think the problem backward, we will burst the boundary balloons first, so we can have fixed values in boundaries
 * and we can pick the next balloon with confidence
 * 
 * The basic idea is that we create a dp matrix, in which dp[i][j] means the best value we can get from index i to j
 * when we are looking at a point at x = i, y = j. We will scan all points between i and j, and pick the one 
 * that gives best value. The value is calculated as dp[left][i-1] + nums[i]*nums[x]*nums[j] + dp[x+1][right] as 
 * we are updating the max value we can get for range [left, right]. The index value may be varied based on how we
 * indexing the dp matrix, but the basic idea should be same
 * 
 * Finally we just need to return the value from dp[0][len]
 * 
 * Time complexity: O(N^3)
 * Space complexity: O(n^2)
 * 
 * @author hpPlayer
 * @date Jul 5, 2016 9:58:04 PM
 */
public class Burst_Balloons_p312_sol1 {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        
        //for convenience we create a new array with n + 2 len, so we can include nums[-1] and nums[len]
        int[] copy = new int[n+2];
        for(int i = 1; i <= n; i++) copy[i] = nums[i-1];
        copy[0] = copy[n+1] = 1;
        
        //dp matrix, dp[i][j] means the maxCoins we can get from index i to j
        //since we will use copy array, len of dp is also n+2 * n+2
        int[][] dp = new int[n+2][n+2];
        
        //In the beginning, we only have two fixed boundary (-1, len), and we need to firstly pick the ballon
        //that we will burst last.
        for(int len = 1; len <= n;len++){
            //the search range would starts from including one balloon to including all n balloons
            
            //in our new copy array, our target index should from 1 to left + len - 1 <= n
            //since left + len - 1 is right index, and right index should be < n, so we have left + len -1 < n
            //but in our new array, we leftshift all indexes by 1 so left is [1, n+1-len]
            for(int left = 1; left + len -1 <= n; left++ ){
                int right = left + len - 1;
                
                //scan any balloon from left to right and pick the one that gives maxCoins
                for(int i = left; i <= right; i++){
                    dp[left][right] = Math.max(dp[left][right], dp[left][i-1] + copy[left-1] * copy[i] * copy[right+1] + dp[i+1][right] );
                }
            }
            
        }
        
        return dp[1][n];
    }
}
