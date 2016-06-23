/*
132. Palindrome Partitioning II

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

/**
 * DP solution
 * 
 * We use a dp array to record the minCut we need to get palindrome partitions at index i
 * Therefore we can reuse the previous results to find the minCut for curr index
 * 
 * Time complexity: O(n^2). We need go through O(N) indexes, and we may need expand O(N) chars from each idnex
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date Jun 22, 2016 11:17:10 PM
 */
public class Palindrome_Partitioning_II_p132_sol1 {
    public int minCut(String s) {
        //dp solution. Reuse previous minCut value to get curr minCut
        
        int n = s.length();
        
        //use n + 1, so we can cover whole string
        int[] dp = new int[n+1];
        
        //set initial value for each index
        //for a string with len n, we need at most n-1 cuts to split it into palindrome Partitionings
        for(int i = 0; i <= n; i++){
            dp[i] = i - 1;
        }
        
        //check each char in input and try to expand from it
        for(int i = 1; i <= n; i++){
            //single core case
            for(int left = i, right = i; left - 1 >= 0 && right - 1 < n && s.charAt(left-1) == s.charAt(right-1); left--, right++  ){
                
                //from index left - 1 to index right, we just need one more cut (cut between left - 1 and left) 
                dp[right] = Math.min( dp[right], dp[left-1] + 1 );
            }
            
            //double core case
            for(int left = i, right = i+1; left - 1 >= 0 && right - 1 < n && s.charAt(left-1) == s.charAt(right-1); left--, right++  ){
                
                //from index left - 1 to index right, we just need one more cut (cut between left - 1 and left) 
                dp[right] = Math.min( dp[right], dp[left-1] + 1 );
            }
            
        }
        
        return dp[n];
    }
}
