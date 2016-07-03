import java.util.*;

/*
271. Encode and Decode Strings

Design an algorithm to encode a list of strings to a string.
The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:
The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
*/

/**
 * String solution
 * 
 * The most convenient way is to record the len of substring, add delimiter, then add the substring
 * In the decode() step, we just need to locate the delimiter to find the substrings. Since we know the
 * length ahead we can skip the "delimiter" inside the substring without confusion
 * 
 * This solution can also be solved by replacing "a" with "aa", then use "#a#" as new delimiter
 * But then we may have trouble in using split() to split the input when input is [] or [""]. So it 
 * is better to use above solution
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jul 2, 2016 3:59:42 PM
 */
public class Encode_and_Decode_Strings_p271_sol1 {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        
        for(String str : strs){
            sb.append( str.length() + "#" + str );
        }
        
        return sb.toString();        
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<String>();
        int index = 0;
        while(index < s.length()){
            int curr = s.indexOf( "#", index );
            //we can't start search next "#" at index + 1, since the string size maybe 0
            int len = Integer.valueOf( s.substring(index, curr) );
            result.add( s.substring(curr + 1,  curr + 1 + len ) );
            
            index = curr + len + 1;
        }
        
        return result;
    }
}
