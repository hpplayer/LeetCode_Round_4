import java.util.*;

/*
244. Shortest Word Distance II

This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and
your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words
word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = ¡°coding¡±, word2 = ¡°practice¡±, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

/**
 * HashMap + mergeSort solution
 * 
 * We use a hashMap to record the indexes of all words.
 * Then we get the lists of index for input words. 
 * We will move two pointers in two lists wisely like mergeSort that is we will only move pointer on the list
 * that returns smaller index. In this way, we are making two words closer
 * 
 * Time complexity: O(N) for shortest()
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jul 1, 2016 8:38:27 PM
 */
public class Shortest_Word_Distance_II_p244_sol1 {
    HashMap<String, List<Integer>> hs;
    
    public Shortest_Word_Distance_II_p244_sol1(String[] words) {
        hs = new HashMap<>();
        
        for(int i = 0; i < words.length; i++){
            if( !hs.containsKey(words[i]) ){
                hs.put(words[i], new ArrayList<Integer>());
            }    
            hs.get(words[i]).add(i);
        }    
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = hs.get(word1);
        List<Integer> l2 = hs.get(word2);
        
        int i = 0, j = 0;
        int result = Integer.MAX_VALUE;
        
        while( i < l1.size() && j < l2.size() ){
            result = Math.min(result, Math.abs(l1.get(i) - l2.get(j) ) );
            if(l1.get(i) < l2.get(j)) i++;
            else j++;
        }
        
        return result;
    }
}
