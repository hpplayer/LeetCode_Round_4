/*
214. Shortest Palindrome

Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".
*/

/**
 * KMP + Observation solution
 *
 * We only allow to insert chars in the head, therefore inputs like "abcb" will not get a short solution by 
 * simply adding an extra char in the tail
 * 
 * The key is to realize that we need to find the length of palindrome substring starting from index, and we 
 * need to add the reverse of rest substring to the head to build a complete palindrome substring
 * 
 * In this solution, we firstly build the string in the form of s + "#" + reverse(s), then building KMP table
 * for this new String. The KMP value in the last cell indicates the len of palindrome substring starting from
 * index 0. We just need to reverse the substring after this palindrome substring then append it to head, then
 * we can get a palindrome string
 * 
 * Time complexity: KMP: O(N)
 * Space complexity: Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jun 26, 2016 8:36:50 PM
 */
public class Shortest_Palindrome_p214_sol1 {
    public String shortestPalindrome(String s) {
        //get a new String, using "#" delimiter to split input and reversed input 
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        
        int[] table = getTable(temp);
        
        //using the value in last cell of table[] to find the len of palindrome substring starting from index 0
        //then reverse the remaining part and attach it ahead
        return new StringBuilder( s.substring(table[table.length-1]) ).reverse().toString() + s; 
    }
    
    public int[] getTable(String s){
        
        //table[i] means the len of matched prefix/suffix
        int[] table = new int[s.length()];
        //pointer in prefix, i in loop is pointer in suffix
        int index = 0;
        
        //skip cell 0, as we don't compare char with itself
        for(int i = 1; i < s.length(); i++){
            if( s.charAt(i) == s.charAt(index) ){
                //in case chars match
                index++;
                table[i] = index;
            }else{
                
                //we get the char after prev matched prefix
                index = table[i-1];
                
                //while we still have prefix left, and char is still not matched
                while( index > 0 && s.charAt(index) != s.charAt(i) ){
                    
                    //since value in table[] is size of matched prefix, we let index = table[] means 
                    //we are comparing the char after prev matched prefix with curr char
                    index = table[index-1];
                }
                
                //if found match
                if( s.charAt(i) == s.charAt(index) ){
                    index++;
                    table[i] = index;   
                }
            }
        }
        
        return table;
    }
}
