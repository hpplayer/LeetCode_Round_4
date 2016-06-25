/*
153. Find Minimum in Rotated Sorted Array

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

/**
 * Binary Search + Observation solution
 * 
 * We observe that min value is always at rotated part, otherwise input is not rotated
 * If input is not rotated, then min value is always in left part
 * ex: 12345 vs 45123. Rotate means we put larger nums front and smaller nums back 
 * So we can do binary search on the inputs and find the rotated part based on the observation above
 * 
 * Time complexity: O(logN)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 23, 2016 11:47:01 PM
 */
public class Find_Minimum_in_Rotated_Sorted_Array_I_p153_sol1 {
    public int findMin(int[] nums) {
        //bindary search + observation solution
        
        //boundary check
        if( nums.length == 0 ) return 0;
        
        int left = 0, right = nums.length - 1;
        
        //we will stop binary search once we found the remaining array is not rotated
        while( left < right &&  nums[left] > nums[right] ){
            int mid = left + (right - left)/2;
            
            //Notice: in case left part has only one element, we should treat it as not rotated part as well!!!
            //ex: [2, 1]. left part [2], we should go right part
            if( nums[mid] >= nums[left] ){
                //left is not rotated, search right part
                //nums[mid] is not smallest num, skip it
                left = mid + 1;
            }else{
                //left is rotated, search left part
                //nums[mid] maybe the smallest num, keep it
                right = mid;
            }    
        }
        
        //we either have one elment remaining or the remaining part is not rotated
        //in either case, nums[left] will be the smallest num
        return nums[left];
    }
}
