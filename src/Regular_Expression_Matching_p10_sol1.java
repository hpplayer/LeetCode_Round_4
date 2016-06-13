/*
10. Regular Expression Matching

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") ¡ú false
isMatch("aa","aa") ¡ú true
isMatch("aaa","aa") ¡ú false
isMatch("aa", "a*") ¡ú true
isMatch("aa", ".*") ¡ú true
isMatch("ab", ".*") ¡ú true
isMatch("aab", "c*a*b") ¡ú true
*/

/**
 * DP solution
 * 
 * We build a dp matrix, and dp[i][j] means whether String_substring(0, i-1) matches Pattern_substring(0, j-1)
 * We use i - 1 and j -1 here since we add an extra row/col ahead to help handle boundary cases
 * 
 * When curr char in pattern is "*", then there would be two cases:
 * 1) case 1, zero use of preceding element. Then we need check whether String_substring(0, i-1) matches 
 * Pattern_substring(0, j-3). We need skip "*" and preceding element
 * 2) case 2, multiple use of preceding elements. Then we need check whether String_substring(0, i-2) matches
 * Pattern_substring(0, j-1). It means we use * to match curr char in String, and then we check String_substring(0, i-2)
 * with Pattern_substring(0, j-1) to see if we have a match there.
 * Notice: in case 2, we need make sure preceding_element is able to match char in string.
 * 
 * Time complexity: O(mn)
 * Space complexity: O(mn)
 * 
 * @author hpPlayer
 * @date Jun 12, 2016 7:22:11 PM
 */
public class Regular_Expression_Matching_p10_sol1 {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        
        //we add an extra row/col ahead to handle boundary cases (ex, s = "")
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        
        for(int i = 0; i <= m; i++){
            //we cant use "" pattern to match any string, except for boundary case "" <=> "" 
            for(int j = 1; j <= n; j++){
                //i,j is 1 based, convert to 0 based to check real char
                if( i > 0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') ){
                    //curr char pair matches, check prev substring
                    dp[i][j] = dp[i-1][j-1];
                }else if( p.charAt(j-1) == '*' ){
                    //zero use check s.substring(i-1) with p.substring(j-3)
                    boolean zeroUse = j > 1 && dp[i][j-2];
                    //multi use check s.substring(i-2) with p.substring(j-1)
                    //it means we use a * to match curr char in s, and we check if prev substring in s can
                    //match p.substring(j-1).
                    boolean multiUse = i > 0 && j > 1 && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') && dp[i-1][j];
                    
                    dp[i][j] = zeroUse || multiUse;
                }
                
            }
        }
        
        return dp[m][n];
    }
}
