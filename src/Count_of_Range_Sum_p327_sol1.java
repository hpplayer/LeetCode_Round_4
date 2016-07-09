/*
327. Count of Range Sum

Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ¡Ü j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
*/

/**
 * Merge sort solution 
 * 
 * This solution is similar to Count_of_Smaller_Numbers_After_Self_p315_sol1. In both solutions, we use merge
 * sort to count the results. We will move pointer smartly so we can reduce the work of duplicate calculations
 * 
 * In this solution we firstly calculate the sum of subarray from index 0 to each index. Then use sums[j] - sums[i]
 * to get the target sum ranges
 * 
 * In each mergeSort loop, left and right subarray should already be sorted due to child mergeSort loops.
 * So we can move pointer wisely to find the ranges that can match requirements
 * 
 * Time complexity: O(nlogn)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jul 9, 2016 1:26:32 PM
 */
public class Count_of_Range_Sum_p327_sol1 {
    //use long to prevent overflow
    long[] sums;
    int lower;
    int upper;
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        //boundary check
        if(nums.length == 0) return 0;
        
        //we put an extra 0 cell ahead, so we can easily calculate the sum from 0 to any index without boundary condition
        sums = new long[nums.length + 1];
        this.lower = lower;
        this.upper = upper;
        
        for(int i = 0; i < nums.length; i++){
            sums[i+1] = sums[i] + nums[i];
        }
        
        return mergeSort(0, sums.length - 1);
    }
    
    private int mergeSort(int left, int right){
        //boundary condition. We need at least two sums to get the subarray sum
        if(left >= right) return 0;
        //the basic condition, two sums
        if(left + 1 == right){
            //we need to sort and count in each step
            long mn = Math.min(sums[left], sums[right]);
            long mx = Math.max(sums[left], sums[right]);
            
            long val = sums[right] - sums[left];
            sums[left] = mn;
            sums[right] = mx;
            return val >= lower && val <= upper? 1 : 0;
        }
        
        int mid = left + (right - left)/2;
        
        int count = mergeSort(left, mid) + mergeSort(mid + 1, right);
        
        //a is pointer in right subarray that find the last index before target ranges
        //b is pointer in right subarray that find the last index in target ranges
        //so b - a would be the nums of range that meet requirements
        //c is pointer in right subarray that find the nums in right subrray < num in left subarray, it can 
        //help us put curr left num in correct position
        int a = mid + 1, b = mid + 1, c = mid + 1;
        long[] temp = new long[right - left + 1];
        //pointer in temp array
        int index = 0;
        
        //search each index in left subarray
        //Left subarray and right subarray should already be sorted from child mergeSorts
        //and we are moving pointers a and b wisely in right subarray. We only move a and b when we need find a
        //larger right num. Next left num will > curr left num, so its corresponding right num should also
        //after the updated a and b indexes
        //Therefore we don't need to reset a and b in each loop
        for(int i = left; i <= mid; i++){
            //find the last subarray sum that < lower  
            while(a <= right && sums[a] - sums[i] < lower) a++;
            //find the last subarray sum that <= upper
            while(b <= right && sums[b] - sums[i] <= upper) b++;
            //fill all nums in right subarray that < sums[i] to the temp array 
            while(c <= right && sums[c] < sums[i]) temp[index++] = sums[c++];
            //fill curr num in left subbarray to the temp array
            temp[index++] = sums[i];
            count += b - a;
        }
        
        //indexes means how many cells we updated in above cells
        //so we update sums based on length of index
        for(int i = 0; i < index; i++){
            sums[left + i] = temp[i];
        }
        
        return count;
    }
}
