import java.util.*;

/*
358. Rearrange String k Distance Apart

Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
str = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
str = "aaabc", k = 3 

Answer: ""

It is not possible to rearrange the string.
Example 3:
str = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.
*/

/**
 * Greedy + heap solution
 * 
 * We want rearrange char with highest frequency as early as possible since we can have more chars to fill the gap.
 * We use a heap to pop chars based on frequencies. To keep same char k distances away, we will attach chars by groups
 * We will not use same char in same group twice. We use a queue to cache poped chars so that we won't use them in same
 * group.
 * 
 * Since the heap will adjust the order of chars when we build the string, our final string should always be the 
 * optimal. 
 * ex: aaabbbcc k=2 => ab ab ca bc  in the third group, c is put ahead since at that time c has the most frequency
 *
 * Time complexity: O(NlogN) as follows:
 * count frequency: O(N), build PriorityQueue: O(NlogN), rebuild string: O(NlogN)
 * Space complexity: O(NlogN)
 * 
 * @author hpPlayer
 * @date Jul 9, 2016 10:13:32 PM
 */
public class Rearrange_String_k_Distance_Apart_p358_sol1 {
    public String rearrangeString(String str, int k) {
        //boundary case
        if(k == 0) return str;
        
        int[] count = new int[256];
        
        for(char c : str.toCharArray()){
            count[c]++;
        }
                
        PriorityQueue<Character> pq = new PriorityQueue<Character>(new Comparator<Character>(){
            public int compare(Character a, Character b){
                if(count[a] != count[b]){
                    return count[b] - count[a];
                }else{
                    //for same occurences, we want smaller appear first, so order of each char can be kept
                    return a - b;
                }
            }    
        });
        
        for(int i = 0; i < 256; i++){
            if( count[i] > 0 ) pq.offer( (char) i );
        }
        
        //que we used to store chars temporarily
        Queue<Character> que = new LinkedList<Character>();
        //most groups have fixed length, but the last group does not
        //so we use charsLeft to catch this case
        int charsLeft = str.length();
        StringBuilder sb = new StringBuilder();
        
        while( !pq.isEmpty() ){
            int len = Math.min(charsLeft, k);
            //fill chars for each group
            for(int i = 0; i < len; i++){
                //if we don't have enough chars to fill the group, then return false 
                if(pq.isEmpty()) return "";
                char c = pq.poll();
                sb.append( c );
                if( --count[c] > 0 ) que.offer(c);
            }
            
            charsLeft -= len;
            //we have filled the group, now put chars from que to pq            
            while( !que.isEmpty() ) pq.offer(que.poll());
        }
        
        
        return sb.toString();
    }
}
