/*
115. Distinct Subsequences

Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some
(can be none) of the characters without disturbing the relative positions of the remaining characters.
(ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
*/

/**
 * DP solution
 * 
 * The problem asks us the num of ways to get the string t in string s by just removing chars in s
 * It is similar to problem Edit_Distance_p72_sol1, and we need use DP solution to solve it
 * 
 * For curr char a in s and curr char b in t.
 * if ( a == b), then we are freely to choose whether use a in s to match char b in t (i-1 vs j or i-1 vs j-1). 
 * if( a != b), then we cannot use char a in s to match char b in t, therefore we have to delete char a, and 
 * copy prev dp value to curr cell (i-1, j)
 * 
 * Time complexity: O(mn)
 * Space complexity: O(mn)
 * 
 * @author hpPlayer
 * @date Jun 19, 2016 4:30:23 PM
 */
public class Distinct_Subsequences_p115_sol1 {
    public int numDistinct(String s, String t) {
        //dp solution. Use dp matrix table to match chars in s and t
        
        //boundary check
        if( t.length() > s.length() ) return 0;
        
        int m = s.length(), n = t.length();
        
        //add extra col and row ahead to handle empty string cases
        int[][] dp = new int[m+1][n+1];
        
        //Initialize values in extra col/row
        //0s for extra col ( we can't use empty s to match string t)
        //1s for extra row ( only one way to match s to match empty t, i.e. removing all chars)
        for(int i = 0; i <= m; i++){
            dp[i][0] = 1;
        }
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if( s.charAt(i-1) == t.charAt(j-1) ){
                    //chars match, we can either remove curr char in s or not
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else{
                    //chars unmatched, we have to remove char in s, therefore we are using substring(0, i-1) to match (0, j)
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        return dp[m][n];
    }
}
