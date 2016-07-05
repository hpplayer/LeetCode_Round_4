import java.util.*;

/*
301. Remove Invalid Parentheses

Remove the minimum number of invalid parentheses in order to make the input string valid.
Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
*/

/**
 * BFS solution
 * 
 * This solution is intuitive and easy to come up with.
 * 
 * Here is the basic idea. Each time we will remove only one char from input string. We will stop doing it as long
 * as we found a valid rebuilt string. The char can be any char in input string. So the memory and time consumption
 * would be large. To avoid unnecessary calculation, we will use a HashSet to avoid calculation of same string twice
 * 
 * Time complexity: you have a length n string, every character have 2 states "keep/remove", that is 2^n states and check valid is O(n).
 * All together O(n*2^n)
 * Space complexity: O(N)
 * 
 * Sol1 is intuitive BFS solution that easy to write but having redundant calculations
 * Sol2 is optimized DFS solution that not easy to write but fast in speed
 * 
 * @author hpPlayer
 * @date Jul 4, 2016 4:01:17 PM
 */
public class Remove_Invalid_Parentheses_p301_sol1 {
    public List<String> removeInvalidParentheses(String s) {
        Queue<String> que = new LinkedList<String>();
        List<String> result = new ArrayList<String>();
        HashSet<String> visited = new HashSet<String>(); 
        
        que.offer(s);
        boolean isValid = false;
        
        //we will keep searching as long as que is not empty or we have not found valid string
        while( !que.isEmpty() && !isValid ){
            int size = que.size();
            
            for(int i = 0; i < size; i++){
                //check each string in queue to see if we have found valid string
                String curr = que.poll();
                
                if( isValid(curr) ){
                    result.add(curr);
                    isValid = true;
                    continue;
                }
                
                
                for(int j = 0; j < curr.length(); j++){
                    //remove each char in input to see if we can get a valid string
                    char c = curr.charAt(j);
                    //skip non "(" and ")" chars
                    if( c != '(' && c != ')' ) continue;
                    String temp = (j == 0? "" : curr.substring(0, j)) + (j==curr.length()-1? "" : curr.substring(j+1) );
                    //we only add unvisited string to que
                    if(visited.add(temp)) que.offer(temp);
                }
            }
        }
        
        return result;
    }
    
    private boolean isValid(String s){
        //check if target string is valid
        //"(" increase count while ")" decrease count
        int count = 0;
        
        for(int i = 0; i < s.length(); i++){
            //too many ")"s
            if( count < 0 ) return false;
            char c = s.charAt(i);
            if(c == '(') count++;
            if(c == ')') count--;
        }
        return count == 0;
    }
}
