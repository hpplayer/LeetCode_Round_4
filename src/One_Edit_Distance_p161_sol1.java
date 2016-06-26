/*
161. One Edit Distance

Given two strings S and T, determine if they are both one edit distance apart.

*/

/**
 * Observation solution
 * 
 * We can't use DP solution here, why?
 * We only allow one edit distance. In dp table, we have to fill all cells which implies multiple edit distances
 * Therefore it would bring us a lot of headache inconvenience
 * 
 * The best way to solve this problem is just the intuitive approach
 * We scan two inputs until we found an unmatch, then we either replace it or delete/add based on len difference,
 * then compare if rest strings are same. Since we are required to have one edit distance, if all chars are matched,
 * then we require one of inputs should have one extra char
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 25, 2016 1:49:19 PM
 */
public class One_Edit_Distance_p161_sol1 {
    public boolean isOneEditDistance(String s, String t) {
        //we assume t's length is longer than s
        if( t.length() < s.length() ) return isOneEditDistance(t, s);
        
        //early pruning, if t's length - s's length > 1, then return false directly
        if(t.length() - s.length() > 1) return false;
        
        int m = s.length(), n = t.length();
        
        for(int i = 0; i < m; i++){
            if( s.charAt(i) != t.charAt(i) ){
                //found a unmatched pair
                
                if( m == n  ){
                    //same len, have to replace
                    return s.substring(i+1).equals(t.substring(i+1));
                }else{
                    //diff len, remove curr char from t or add missing char to s
                    //in either way, we are comparing t_substring(i+1) with s_substring(i)
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
        }
        
        //if every char pair is matched, then we require t has an extra char 
        return n - m == 1;
    }
}
