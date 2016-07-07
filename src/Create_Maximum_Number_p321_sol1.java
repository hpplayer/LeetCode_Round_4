import java.util.Stack;

/*
321. Create Maximum Number

Given two arrays of length m and n with digits 0-9 representing two numbers.
Create the maximum number of length k <= m + n from digits of the two.The relative order of the digits from the same array must be preserved.
Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]
		
*/

/**
 * Greedy solution
 * 
 * We greedily pick the largest number from two input arrays separately. In each array, we greedily replace
 * digit in result array if we found a new digit can make result array larger. Then we try all combinations 
 * from subarrays of num1 and num2 to make the largest number. In the combinations len of subarray of nums1
 * can from Math.max(0, k - nums2.length) to Math.min(nums1.length, k). In case nums2.length < k, then
 * we need k - nums2.length digits from nums1, also digits from nums1 should < k
 * 
 * To check if num1 is larger than num2 we need another function since num1 and num2 are two arrays and
 * num1 is larger than nums2 if they have same values but num1 is longer. 
 *  
 * Time complexity: O((m+n)^3)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jul 6, 2016 9:52:58 PM
 */

public class Create_Maximum_Number_p321_sol1 {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];
        
        //i is subarray len in nums1
        for(int i = Math.max(0, k - nums2.length); i <= Math.min(k, nums1.length); i++){
            //we select i as subarray len in nums1 and k - i as subarray len in nums2
            //we let maxFromOneArray() help us find the maxNum we would get from nums1 with given len
            int[] temp = merge( maxFromOneArray(nums1, i), maxFromOneArray(nums2, k - i), k );    
            
            if( isLarger(temp, 0, result, 0) ) result = temp;
        }
        
        return result;
    }
    
    private int[] merge(int[] nums1, int[] nums2, int k){
        int[] result = new int[k];
        
        int a = 0, b = 0;
        
        for(int i = 0; i < k; i++){
            //we need an extra function here to compare two arrays
            result[i] = isLarger(nums1, a, nums2, b)? nums1[a++] : nums2[b++];
        }
        
        return result;
    }
    
    private boolean isLarger(int[] nums1, int a, int[] nums2, int b){
        while( a < nums1.length && b < nums2.length && nums1[a] == nums2[b] ){
            a++;
            b++;
        }
        
        //if array nums1 is longer with same prefix or curr digit in nums1 is larger than nums2
        //then nums1 is larger
        return b == nums2.length || (a < nums1.length && nums1[a] > nums2[b]);
    }
    
    private int[] maxFromOneArray(int[] nums, int k){
        int[] result = new int[k];
        Stack<Integer> stack = new Stack<Integer>();
        
        for(int i = 0; i < nums.length; i++){
            
            //as long as remaining digits in nums are more than remaining digits we need in k, we can pop
            //all smaller top values from stack
            //len - i => nums left in input ex [0, 1], at index 0, 2 - 0 = 2, we have 2 nums left in array
            while( nums.length - i  > k - stack.size()  && !stack.isEmpty() && stack.peek() < nums[i]  ) stack.pop();
            
            //we will always reach here, and we only add the nums if stack.size() < k, which means adding
            //nums[i] can make the max nums so far
            if( k < stack.size() ) stack.push( nums[i] );
        }
        
        for(int i = k - 1; i >= 0; i--) result[i] = stack.pop();
        
        return result;
    }
}
