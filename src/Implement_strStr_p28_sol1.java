/*
28. Implement strStr()

Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/

/**
 * KMP solution
 * 
 * Firstly build the KMP table, then move pointer in needle string wisely
 * 
 * time complexity: 
 * O(len(haystack) + len(needle)) time. (KMP is linear algorithm)
 * Space complexity: O( len(needle) )
 * 
 * @author hpPlayer
 * @date Jun 13, 2016 10:51:45 PM
 */
public class Implement_strStr_p28_sol1 {
    public int strStr(String haystack, String needle) {
        //boundary cases
        if(needle.length() > haystack.length()) return -1;
        if(needle.length() == 0) return 0;
        
        int[] table = buildTable(needle);
        
        //j is pointer in needle, i is pointer in haystack
        int j = 0;
        for(int i = 0; i < haystack.length(); i++){
            if( haystack.charAt(i) == needle.charAt(j) ){
                j++;
                if( j == needle.length() ) return i - j + 1;
            }else{
                
                //rightshift needle to start a new match.
                if( j - 1 >= 0 ){
                    j = table[j-1];
                    i--;
                }
            }
        }
        
        return -1;
    }
    
    public int[] buildTable(String needle){
        int[] table = new int[needle.length()];
        
        //pointer used to scan prefix
        int index = 0;
        
        //we will not compare single char
        for(int i = 1; i < needle.length(); i++){
            if( needle.charAt(i) == needle.charAt(index) ){
                //record the len of matched prefix/suffix
                table[i] = ++index;
            }else{
                
                //update index to be the the char after last matched prefix 
                index = table[i-1];
                
                //we will iteratively search the char after matched prefix, until we reach head or found a matching
                //char. table[index-1] always contains the len of last matched prefix/suffix, it is also the 
                //index of char after matched prefix
                while( index >= 1 && needle.charAt(index) != needle.charAt(i) ){
                    index = table[index-1];
                }
                
                //if we found a matching pair, then update table accordingly
                if( needle.charAt(index) == needle.charAt(i) ){
                    table[i] = ++index;
                }
                
            }
        }
        
        return table;
    }
}
