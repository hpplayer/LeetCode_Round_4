import java.util.LinkedList;

/*
316. Remove Duplicate Letters

Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once.
You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
*/

/**
 * Deq solution
 * 
 * To obtain a new string that has the smallest lexicographical order, we need to maintain a stack that contains scanned
 * chars with smallest lexicographical order. We say smallest lexicographical order, it does not necessarily mean 
 * we have to follow "a->b->c", in case z only appears once in head, we can have result like "z->a->b"
 * 
 * Why using deq here then? since we want to maintain stack by using pollLast(), but we also want to build 
 * string by using pollFirst()
 * 
 * This solution is similar to Largest_Rectangle_in_Histogram_p84_sol1
 * 
 * Time complexity: O(N)
 * Space complexity: O(1) as our stack size will <= 26
 * 
 * @author hpPlayer
 * @date Jul 6, 2016 12:30:41 AM
 */
public class Remove_Duplicate_Letters_p316_sol1 {
    public String removeDuplicateLetters(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        
        //using a boolean[] to record whether we have char c in stack
        //so we can skip chars that already in stack.
        //Once a char is in stack, adding a same char to stack will not decrease the lexicographical order
        boolean[] inStack = new boolean[256];
        int[] count = new int[256];
        int len = s.length();
        
        //update count[]
        for(int i = 0; i < len; i++) count[s.charAt(i)]++;
        
        for(int i = 0; i < len; i++){
            char c = s.charAt(i);
            
            count[c]--;
            //we will skip chars that already in stack
            if( inStack[c] ) continue;
            
            //we keep poping chars that higher than c, and still have duplicates later
            while( !stack.isEmpty() && stack.peekLast() > c && count[stack.peekLast()] > 0 ) inStack[stack.pollLast()] = false;
            
            stack.offerLast(c);
            inStack[c] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        
        while( !stack.isEmpty() ){
            sb.append( stack.pollFirst() );
        }
        
        return sb.toString();        
    }
}
