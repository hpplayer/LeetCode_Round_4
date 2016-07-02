/*
245. Shortest Word Distance III

This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in
the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = ¡°makes¡±, word2 = ¡°coding¡±, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.
*/

/**
 * Two pointer solution
 * 
 * The main idea is similar to Shortest_Word_Distance_I_p243_sol1. But now we need to handle duplicate cases
 * 
 * The basic idea is that we only update index2 when two inputs are not same. If inputs are same, then we 
 * just need to compare curr index and index1. So in the if block that words[i].equals(word1), we need
 * add this special case
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jul 1, 2016 9:03:40 PM
 */
public class Shortest_Word_Distance_III_p245_sol1 {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int index1 = -1, index2 = -1;
        int result = Integer.MAX_VALUE;
        
        for(int i = 0; i < words.length; i++){
            if( words[i].equals(word1) ){
                //handle duplicate case here
                if( word1.equals(word2) && index1 != -1 ){
                    result = Math.min( result, i - index1 );
                }
                
                //general cases
                if( index2 != -1){
                    result = Math.min( result, i - index2 );
                }
                index1 = i;                
            }else if(words[i].equals(word2)){
                //general cases
                if( index1 != -1){
                    result = Math.min( result, i - index1 );
                }
                index2 = i;                  
            }
        }
        
        return result;
    }
}
