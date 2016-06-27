import java.util.Random;

/*
215. Kth Largest Element in an Array

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ¡Ü k ¡Ü array's length.
*/

/**
 * Quick sort solution
 * 
 * We use the proprty of quick sort to find the Kth Largest Element.
 * The advantage of quickSort is that we can return result immediately, once we have sort and get k largest number ahead.
 * To handle extreme case and give a more universal solution, we will shuffle the array in the beginning with Fisher¨CYates shuffle method
 * 
 * @author hpPlayer
 * @date Jun 26, 2016 9:20:17 PM
 */
public class Kth_Largest_Element_in_an_Array_p215_sol1 {
    public int findKthLargest(int[] nums, int k) {
        //firstly shuffle the input
        Random rand = new Random();
        for(int i = nums.length - 1; i >= 1; i--){
            int index = rand.nextInt(i + 1);
            int temp = nums[i];
            nums[i] = nums[index];
            nums[index] = temp;
        }
        
        int start = 0, end = nums.length - 1;
        
        while(true){
            
            int index = quickSort(nums, start, end);
            
            //k is 1 based, we convert it to 0 based
            if( k - 1 == index ){
                return nums[index];
            }else if( k - 1 < index ){
                //we found too many large elements, look first half subarray before index 
                end = index - 1;
            }else{
                //we found too less large elemetns, we look second half subarray after index
                start = index + 1;
            }
            
        }
        
        
    }
    
    public int quickSort(int[] nums, int start, int end){
        //for convenience, we pick the num in index "start" as pivotal
        int pivotal = nums[start];
        //record the index of pivotal
        int index = start;
        //then sort the array from start + 1 to end
        start++;
        
        while(start <= end){
            //like Valid_Palindrome_p125_sol1, we skip all valid nums[start] and nums[end] until we found a pair
            while( start <= end && nums[start] >= pivotal ) start++;
            while( start <= end && nums[end] <= pivotal ) end--;
            
            //if we found a valid pair that we can swap
            
            if( start <= end  &&  nums[start] < pivotal && nums[end] > pivotal ){
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
        
        //then we place pivotal into the sorted array, we just need to swap it with the last index in first half nums, which is now pointed by pointer end after we execute while(start <= end) loop
        int temp = nums[end];
        nums[end] = pivotal;
        nums[index] = temp;
        
        //return curr index of pvital in array, as we have found "end" len of large nums ahead
        //Notice first half and second half subarray may not be sorted inside. But it doesn't matter,
        //we just care about the ith element, which is pivotal now 
        return end;
    }
}
