import java.util.*;

/*
336. Palindrome Pairs

Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words,
i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
*/

/**
 * HashMap solution
 * 
 * For a string pair, we only have two ways to make it a new word. So the tricky part how to find the counter part faster?
 * The solution is using hashMap. For each string, we split it into all possible ways, and then we use a hashMap to check
 * if we have another string that matches the counterpart. The pair can make palindrome if the remaining part is palindrome
 * 
 * However there is a boundary check we need to be careful:
 * 
 * String x = "ab"
 * String y = "ba"
 * 
 * We can split x into following way:
 * "ab" and ""
 * we have "ba" in hashmap
 * so we will have a string "abba" in the result, the pair is (0, 1) 
 * However,
 * we can also split y into following way:
 * "" and "ba"
 * we have "ab" in hashMap
 * so we will have a string "abba" in the result, the pair is also (0, 1) 
 * 
 * To avoid this situation, we only allow whole String appear once in split ways, either in left or right part
 * In this solution, we let whole string only appear in left part
 * 
 * Time complexity: O(n*k^2) where n is input len, k is avg word len. 
 * We have a nested loop, and in inner loop, we also need O(k) time to validate palindrome
 * Space complexity: O(N)
 * 
 * Remark:
 * We need to search pairs of words, so of course we don't allow an input string to match itself, except for the case
 * we have "" in the input list. Therefore we need hashMap to mark the index of each word
 * 
 * @author hpPlayer
 * @date Jul 9, 2016 2:34:18 PM
 */
public class Palindrome_Pairs_p336_sol1 {
    public List<List<Integer>> palindromePairs(String[] words) {
        HashMap<String, Integer> hs = new HashMap<>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        for(int i = 0; i < words.length; i++) hs.put(words[i], i);
        
        for(int i = 0; i < words.length; i++){
            String word = words[i];
            for(int j = 0; j <= word.length(); j++){
                //find counter part of left or right
                String left = new StringBuilder( word.substring(0, j) ).reverse().toString();
                String right = new StringBuilder( word.substring(j) ).reverse().toString();
                
                if( hs.containsKey(left) && hs.get(left) != i && isPalin(right) ){
                    //if we have a counter part of left, and right part is palin, then we found a target pair
                    result.add( Arrays.asList(i, hs.get(left)) );
                }
                
                if( j != 0 && hs.containsKey(right) && hs.get(right) != i && isPalin(left) ){
                    //we add j != 0 here since we only allow whole string to appear once in left part to avoid duplicate
                    //if we have a counter part of right, and left part is palin, then we found a target pair
                    result.add( Arrays.asList(hs.get(right), i) );
                }
            }
        }
        
        return result;
    }
    
    public boolean isPalin(String s){
        int left = 0, right = s.length() - 1;
        while( left < right ){
            if(s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
}
