/*
Find the Duplicate Number

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
prove that at least one duplicate element must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant extra space.
Your runtime complexity should be less than O(n2).
*/

/**
 * Binary Search solution
 * 
 * First of all we need to realize that the input allow has one duplicate number and it can appear multiple times
 * 
 * The problem also limits the value range is from 1 to n (both inclusive), and input has len n + 1
 * So we can make use of index from 1 to n (both inclusive) to find the target value
 * Notice that index 0 can be the mid but we will never stop at it. If input size < 2, then we do not have solutions
 * 
 * In this solution, we will use binary search on our index, each binary search will scan all inputs and count how
 * many nums <= mid. Say we are looking at index 3, then we at most would have 1,2,3, three numbers smaller than it.
 * If not, then it means one of 1,2,3 will be duplicate, and we can shrink our range by half. If yes, then it means 
 * non of 1,2,3 will be duplicate, so we can look numbers > 3, and we still shrink our range by half
 * 
 * Time complexity: O(NlogN)
 * Space complexity: O(1)
 * 
 * Sol1 is binary-search solution
 * Sol2 is two pointer solution
 * 
 * @author hpPlayer
 * @date Jul 3, 2016 4:52:24 PM
 */
public class Find_the_Duplicate_Number_p287_sol1 {
    public int findDuplicate(int[] nums) {
        int start = 0, end = nums.length - 1;
        
        //since input is guaranteed to have a solution, we can stop when we have only one element remaining
        while(start < end){
            int mid = start + (end - start)/2;
            
            int count = 0;
            
            //count how many nums in array <= mid. 
            for(int num : nums){
                if( num <= mid ) count++;
            }
            
            if( count <= mid ){
                //count <= mid indicates nums <= mid do not contain duplicate, so we can search nums > mid
                //ex: mid = 3, we can have 1, 2, 3 in array 
                start = mid + 1;
            }else{
                //count > mid indicates num < mid contains duplicate, so we shrink search range to be 1-mid(both inclusive)
                end = mid;
            }
        }
        
        //start == end now, return either start or end
        return start;
    }
}
