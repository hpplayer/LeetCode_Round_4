import java.util.*;

/*
You are given an integer array nums and you have to return a new counts array.
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].
*/

/**
 * MergeSort solution
 * 
 * We can use merge sort to handle input array, then the num of nums after self will pop up without extra cost
 * However, merge sort is based on values, and we want output array based on index, so we need use an extra
 * class that contains both index and value
 * 
 * Time complexity: P(nLogN)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jul 5, 2016 11:36:07 PM
 */
public class Count_of_Smaller_Numbers_After_Self_p315_sol1 {
    private class MyNode{
        int val;
        int idx;
        MyNode(int v, int i){
            val = v;
            idx = i;
        }
    }
    
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        
        //boundary check
        if(nums.length == 0) return result;
        
        int[] count = new int[nums.length];
        MyNode[] temp = new MyNode[nums.length];
        
        //initialize temp[]
        for(int i = 0; i < nums.length; i++) temp[i] = new MyNode(nums[i], i);
        mergeSort(temp, count);
        
        for(int c : count) result.add(c);
        
        return result;    
    }
    
    public MyNode[] mergeSort(MyNode[] nums, int[] count){
        //boundary case if nums.length = 1 or = 0, we just return it
        if(nums.length <= 1 ) return nums;
        
        int mid = nums.length / 2;
        
        //in case mid = 1 and len = 2, we want the recursion ends in next layer, so we need pass range(0, 0) and (1, 2) 
        //otherwise we will always have stackoverflow error 
        MyNode[] left = mergeSort( Arrays.copyOfRange(nums, 0, mid), count );
        MyNode[] right = mergeSort( Arrays.copyOfRange(nums, mid, nums.length), count );
        
        int left_index = left.length - 1;
        int right_index = right.length - 1;
        
        //we merge child array and at same time we can update the count[] free
        //we want count smaller nums after each index, so we merge backward
        for(int i = nums.length - 1; i >= 0; i--){
            if( right_index < 0 ||  (left_index >= 0 && left[left_index].val > right[right_index].val )){
                //case that we need to add a left num to nums[]. It means all nums before right_index will
                //be smaller than it, so we add right_index + 1 to count table
                
                count[ left[left_index].idx ] += right_index + 1 ;
                nums[i] = left[left_index];
                left_index--;
            }else{
                //case that we need to add a right num to nums[], we do nothing to count[], as left[] is before
                //right[], smaller lefts do not impact count[]
                nums[i] = right[right_index];
                right_index--;
            }
        }
        
        return nums;
    } 
}
