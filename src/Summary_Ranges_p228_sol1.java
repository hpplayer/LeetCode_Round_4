import java.util.*;

/*
228. Summary Ranges

Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
*/

/**
 * String solution
 * 
 * Nothing special, just use two pointers to scan the array
 * The tricky part is how to move pointer
 * In this solution, we will always move left pointer in outer loop and move right pointer in inner loop,
 * in next round of outer loop we will set left = right + 1
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 29, 2016 9:41:22 PM
 */
public class Summary_Ranges_p228_sol1 {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();
        
        for(int left = 0, right = 0; left < nums.length; left = right + 1){
            //set right to start from left
            right = left;
            while( right + 1 < nums.length && nums[right + 1] == nums[right] + 1 ) right++;
            
            if( left == right ){
                //single num range
                result.add( nums[left] + "");
            }else{
                //multiple num range
                result.add( nums[left] + "->" + nums[right]);
            }
        }
        
        return result;
    }
}
