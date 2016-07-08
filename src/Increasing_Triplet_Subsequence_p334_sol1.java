/*
334. Increasing Triplet Subsequence

Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ¡Ü i < j < k ¡Ü n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.
*/	

/**
 * Brainstorming solution
 * 
 * We need record the smallest first number and second number.
 * 1)We greedily update smallest first number if we find a number smaller than it 
 * This is eligible since getting a smaller number would not harm the way we find triplet
 * Note: here we also need to catch nums[i] == min1 case, otherwise we may have min2 = nums[i]
 * as well
 * 
 * 2)We also greedily update smallest second number if we find a number smaller than it
 * This is eligible if we can do it after 1) so we can get a number > min1
 * 
 * 3)If at any point we have nums[i] > min2 > min1, then we return true directly 
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jul 7, 2016 11:07:21 PM
 */
public class Increasing_Triplet_Subsequence_p334_sol1 {
    public boolean increasingTriplet(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        
        for(int num : nums){
            if( num > min2 ) return true;
            
            if( num <= min1 ){
                //we need catch min1 == num case here, otherwise we may have num = min1 = min2
                //we need make smallest num smaller, it wouldn't harm we find the second and third smallest num
                min1 = num;
            }else if( num < min2 ){
                min2 = num;
            }
        }
        
        return false; 
    }
}
