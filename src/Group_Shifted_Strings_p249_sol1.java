import java.util.*;

/*
249. Group Shifted Strings

Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd".
We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
A solution is:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
*/

/**
 * HashMap solution
 * 
 * The key is to figure out how to generate the key for each pattern
 * Each pattern may differ in length and gap, and the gap maybe negative
 * 
 * So we just record each gap and convert negative gap value to positive gap value(+ 26 fix this problem)
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jul 1, 2016 10:39:51 PM
 */
public class Group_Shifted_Strings_p249_sol1 {
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> hs = new HashMap<>();
        
        for(String str : strings){
            String key = buildKey(str);
            if( !hs.containsKey(key) ) hs.put( key, new ArrayList<String>());
            hs.get(key).add(str);
        }
        
        List<List<String>> result = new ArrayList<List<String>>();
        for(String str : hs.keySet()){
            result.add(hs.get(str));
        }
        
        return result;
    }
    
    public String buildKey(String s){
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i < s.length(); i++){
            int diff = s.charAt(i) - s.charAt(i-1);
            if(diff < 0) diff += 26;
            sb.append(diff + "#");
        }
        
        return sb.toString();
    }
}
