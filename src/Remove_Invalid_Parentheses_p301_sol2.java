import java.util.*;

/**
 * optimzied DFS solution
 * 
 * In the first round of recursion we will just remove ")"s.
 * We optimized the DFS solution by adding several observations
 * 1) we will always remove the first extra ")" if there are consecutive ")"s
 * ex: (a))
 * 2) we will use record the last removal index, and all following recursions will only remove extra ")" after 
 * this index
 * ex: ()a))
 * if not record last removal index
 * a) ()a)) => (a)) removing extra ")" at index 1 => (a) removing extra ")" at index 3 
 * b) ()a)) => ()a) removing extra ")" at index 3 => (a) removing extra ")" at index 1
 * 3) We treat removing extra "(" as a special case of ")", we just need to reverse input and treat ")" as
 * "(" and "(" as "), so we can still use same code to remove extra virtual ")" which now is actually ")"
 * ex: (() => reverse: )(( => we treat it virtually as : ()) 
 * Here we use an array pair[] to help us do the conversion 
 * 
 * Time complexity: recursion, must be exponential
 * Space complexity: same as above
 * 
 * @author hpPlayer
 * @date Jul 4, 2016 4:30:42 PM
 */
public class Remove_Invalid_Parentheses_p301_sol2 {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<String>();
        DFS(result, s, 0, 0, new char[]{'(', ')'});
        return result;
    }
    
    public void DFS(List<String> result, String s, int last_i, int last_j, char[] pair ){
        
        //we use count to find extra ")"
        int count = 0;
        
        //last recursion has taken care of prefix, so curr recursion will start after prefix
        for(int i = last_i; i < s.length(); i++ ){
            char c = s.charAt(i);
            if( c == pair[0] ) count++;
            if( c == pair[1] ) count--;
            
            //if prefix does not contain extra ")", then we just skip it
            if( count < 0 ){
                //found extra ")"
                //we start searching extra ")" from last_j until curr index i
                for(int j = last_j; j <= i; j++){
                    //we remove extra ")" after last_j, and we only remove the first ")" in consecutive order
                    //either j is first ")" or its prev char is not ")"
                    if( s.charAt(j) == pair[1] && (j == last_j || (s.charAt(j - 1) != pair[1]))  ){
                        DFS( result, s.substring(0, j) + s.substring(j+1), i, j, pair );
                    }
                    
                }
                //we have tried all ways to remove extra "(" in prefix, return directly
                return;
            }
        }
        
        //all chars in prefix have been checked without problem, no lets check if we need check "("s or
        //we have done whole string
        String reverse = new StringBuilder(s).reverse().toString();
        
        if(pair[0] == '('){
            //reach end, start checking "("s
            DFS( result, reverse, 0, 0, new char[]{')', '('} );
        }else{
            //reach final end, add to result
            result.add( reverse );
        }

    }
}
