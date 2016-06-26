/*
164. Maximum Gap

Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
*/

/**
 * Radix Sort solution
 * 
 * Since input is positive, we can apply original radix sort to solve the problem in linear time
 * 
 * Time complexity: O(kn), where k is len of max input, n is input len
 * Space complexity: O(n)
 * 
 * Remark:
 * RadixSort is illustrated in this solution
 * QuickSort can be found in Kth_Largest_Element_in_an_Array_p215_sol1
 * 
 * @author hpPlayer
 * @date Jun 25, 2016 3:19:44 PM
 */
public class Maximum_Gap_p164_sol1 {
    public int maximumGap(int[] nums) {
    	radixSort(nums);
        
        //since inputs are all positives, the min gap after sorting would be 0
        int result = 0;
        
        for(int i = 1; i < nums.length; i++){
            result = Math.max(result, nums[i] - nums[i-1] );
        }
        
        return result;
    }
    
    
    public void radixSort(int[] nums){
        //get max input
        int max = 0;
        
        //get max input, since we are going to sort the input based on digits, we need to know where to stop
        for(int num : nums) max = Math.max( max, num );
        
        //variable that helps us get digit in target index
        int exp = 1;
        
        //we will change order of nums[] while still need its origial order
        //so we use a temp[] to help us
        int[] temp = new int[nums.length];
        
        //we need sort digits on each index, therefore we will stop only when num/exp <= 0, i.e. exp > num
        while( max/exp > 0 ){
            //count table for each digit in curr index
            int[] count = new int[10];
            
            //update count[]
            for(int num : nums){
                count[ num/exp%10 ]++;
            }
            
            //update count[] for larger digits
            for(int i = 1; i < 10; i++){
                count[i] += count[i-1];
            }
            
            //reassign inputs based on digits in curr index
            //we want keep the order we have sorted, so we read backward
            for(int i = nums.length - 1; i >= 0; i--){
                //num in count[] is size, so we minus 1 to get the index
                temp[ --count[ nums[i]/exp%10 ] ] = nums[i];
            }
            
            //copy temp to nums
            for(int i = 0; i < nums.length; i++){
            	nums[i] = temp[i];
            }
            
            //update exp
            exp *= 10;
        }
    }
}
