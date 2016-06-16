import java.util.*;

/*
68. Text Justification

Given an array of words and a length L, format the text such that each line has exactly L characters and is fully
(left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not
divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.
*/


/**
 * String solution
 * 
 * There is no algorithm to solve this problem. The solution here is intuitive. We use a pointer to scan the input
 * list, and use another pointer to scan the input words that can be added to current line. 
 * Boundary cases are 1) last line 2) a line with single word 
 * In boundary cases, we will use maxWidth - sb.length() to get the num of spaces we need add after last word
 * 
 * Time complexity: O(n) each word will be scanned twice. One for space calculation, one for building result
 * Space complexity:O(n)
 * 
 * @author hpPlayer
 * @date Jun 15, 2016 9:38:01 PM
 */
public class Text_Justification_p68_sol1 {
	public List<String> fullJustify(String[] words, int maxWidth) {
	    //boundary check
	    if(words == null || words.length == 0) return new ArrayList<String>();
	    
	    List<String> result = new ArrayList<String>();
	    
	    for(int i = 0, j = 0; i < words.length; i = j ){
	        //set to -1 since we dont have space after last word in general case
	        int len = - 1;
	        for(; j < words.length && len + words[j].length() + 1 <= maxWidth; j++ ){
	            len += words[j].length() + 1;    
	        }
	        
	        int averageNumOfSpaces = 0;
	        int extraNumOfSpaces = 0;
	        
	        //special cases: we have single word a line or last line
	        if( i + 1 == j || j == words.length  ){
	            //last line each word still has a space
	            //but single word a line would not have such case
	            averageNumOfSpaces = i + 1 == j? 0 : 1;
	        }else{
	            //+1 for space for each word
	            //j is the word after last word in curr line. so use j-1-i to get gaps
	            averageNumOfSpaces = ( maxWidth - len  )/(j - i - 1) + 1;
	            extraNumOfSpaces = ( maxWidth - len  )%(j - i - 1);
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        
	        //apend word and spaces
	        for(int k = i; k < j; k++){
	            sb.append( words[k] );
	            
	            if( k == j - 1){
	                //last word case
	                
	                //we wouldn't have spaces after last word in general case
	                //but we would have spaces after last word in special cases
	                if( i + 1 == j || j == words.length ){
	                     //we just get remaining spaces, and append
	                     int diff = maxWidth - sb.length();
	                     while(diff-- > 0){
	                         sb.append(" ");
	                     }
	                }
	                break;
	            }
	            
	            //append spaces with avg num
	            for(int m = 0; m < averageNumOfSpaces; m++){
	                sb.append(" ");
	            }
	            
	            //we will only append extra spaces, if we still have remaining
	            //since extraNumOfSpaces couldn't be assigned to each gap, otherwise it would become averageSpaces
	            //therefore those target gaps will only have 1 extra Space
	            if(extraNumOfSpaces-- > 0){
	                sb.append(" ");
	            }
	        }
	        
	        result.add( sb.toString() );
	    }
	     return result;
	}
}
