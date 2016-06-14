import java.util.*;

/**
 * Advanced version of sliding window
 * 
 * We use HashMap as count table. We need two hashTable, one for expected, and one for actual
 * Our sliding window can start any where between [0, words[0].length). Each time we will move word[0].length steps
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date Jun 13, 2016 11:43:48 PM
 */

public class Substring_with_Concatenation_of_All_Words_p30_sol1 {
    public List<Integer> findSubstring(String s, String[] words) {
        //boundary case
        if(s.length() == 0 || words.length == 0 || words.length > s.length()) return new ArrayList<Integer>();
        
        List<Integer> result = new ArrayList<Integer>();
        
        //expected count table
        HashMap<String, Integer> expected = new HashMap<String, Integer>();
        
        //update count table
        for(String word : words){
            if( !expected.containsKey(word) ){
                expected.put(word, 0);
            }
            expected.put( word, expected.get(word) + 1 );
        }
        
        int len = words[0].length();
        
        //starting index of sliding win can be any where between [0, words[0].length)
        for(int i = 0; i < len; i++ ){
            int left = i;
            //count records how many words we have included in curr window
            int count = 0;
            //actual count table
            HashMap<String, Integer> actual = new HashMap<String, Integer>();
            
            for(int right = left; right + len <= s.length(); right += len ){
                String word = s.substring(right, right + len);
                
                //if word is not expected, then our sliding window will start after this word 
                if( !expected.containsKey(word) ){
                    count = 0;
                    left = right + len;
                    actual = new HashMap<String, Integer>();
                    continue;
                }
                
                if(!actual.containsKey(word)){
                    actual.put(word, 0);
                }
                
                //include this word in our window
                count++;
                actual.put(word, actual.get(word) + 1);
                
                //an expected word, check if we have correct occurences of this word
                while( actual.get(word) > expected.get(word) ){
                    String temp = s.substring(left, left + len);
                    actual.put(temp, actual.get(temp) - 1 );
                    left += len;
                    count--;
                }
                
                if(count == words.length){
                    //if we found all words
                    result.add( left );
                    //move left leftward, to start next search
                    String temp = s.substring(left, left + len);
                    actual.put(temp, actual.get(temp) - 1 );
                    left += len;
                    count--;                    
                } 
            }
        }
        
        return result;
    }
}
