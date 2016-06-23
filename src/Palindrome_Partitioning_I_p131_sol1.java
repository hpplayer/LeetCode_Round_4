import java.util.*;

/*
131. Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
*/

/**
 * DP + backtracking solution
 * 
 * Preprocess the string with palindrome DP solution, then use backtracking to build the list
 * 
 * Time complexity: Get dp table O(n^2), Build String: recursions, masters theorem
 * Space complexity: O(n^2)
 * 
 * @author hpPlayer
 * @date Jun 22, 2016 10:52:20 PM
 */
public class Palindrome_Partitioning_I_p131_sol1 {
    public List<List<String>> partition(String s) {
        boolean[][] dp = getTable(s);
        
        List<List<String>> result = new ArrayList<List<String>>();
        DFS(result, new ArrayList<String>(), 0, s, dp);
        
        return result;
    }
    
    public void DFS( List<List<String>> result, List<String> temp, int index, String s, boolean[][] dp){
        if( index == s.length()  ){
            //boundary case
            result.add( new ArrayList<String>(temp) );
            return;
        }
        
        for(int i = index; i < s.length(); i++){
            if( dp[index][i] ){
                //if substring index -> i is palindrome
                temp.add( s.substring(index, i + 1 ) );
                DFS( result,  temp, i + 1, s, dp   );
                temp.remove( temp.size()-1 );
            }
        }
    }
    
    public boolean[][] getTable(String s){
        int n = s.length();
        
        //dp[i][j] = true means substring from i to j is palindrome
        boolean[][] dp = new boolean[n][n];
        
        //update single core
        for(int i = 0; i < n; i++){
            dp[i][i] = true;
        }
        
        //update double cores
        for(int i = 0; i + 1 < n; i++){
            if( s.charAt(i) == s.charAt(i+1) ) dp[i][i+1] = true;
        }
        
        for(int len = 3; len <= n; len++){
            for(int left = 0; left + len <= n; left++){
                int right = left + len - 1;
                
                if(s.charAt(left) == s.charAt(right) && dp[left+1][right-1] ) dp[left][right] = true;    
            }
        }
        
        return dp;
    }
}
