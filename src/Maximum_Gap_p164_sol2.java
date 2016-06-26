/*
164. Maximum Gap

Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
*/

/**
 * Bucketing solution
 * 
 * We firstly get the max and min inputs, calculate the max gap, then use the size of inputs to calculate the 
 * least size of gap we should get. 
 * 
 * Based on the size of gap, we will get the num of buckets we need for the input
 * 
 * Then we assign inputs into buckets so that the gap inside a gap < the size of gap we got above.
 * Therefore we just need to check the gap between diff buckets to get the maxGap
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Remark:
 * To get the ceiling of division, (if inputs are positive !!!, think about 0), we just need to use (a - 1)/b + 1 
 * To get the flooring of division, we don't need do anything, "/" taks flooring value by default
 * 
 * @author hpPlayer
 * @date Jun 25, 2016 4:12:41 PM
 */
public class Maximum_Gap_p164_sol2 {
    public int maximumGap(int[] nums) {
        //boundary check
        if(nums.length < 2 ) return 0;
        
        //get max and min of inputs, to calculate the max gap from inputs
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for(int num : nums ){
            min = Math.min( min, num );
            max = Math.max( max, num );
        }
        
        //get the least gap value. ( the least gap we should have for inputs)
        int gap = (max-min - 1) / (nums.length - 1)  + 1;
        
        //get the numOfBuckets, it would be num of gaps + 1
        int numOfBuckets = ((max - min - 1) / gap + 1) + 1;
        
        Bucket[] buckets = new Bucket[numOfBuckets];
        
        //initialize buckets
        for( int i = 0; i < numOfBuckets; i++ ) buckets[i] = new Bucket();
        
        //assign inputs into buckets
        for(int num : nums){
            //we need index of bucket, which is flooring num of (num - min)/gap;
            int index = (num - min)/gap; 
            
            buckets[index].max = Math.max( buckets[index].max, num );
            buckets[index].min = Math.min( buckets[index].min, num );
        }
        
        
        //check buckets and get result
        
        //all inputs are positives, the smallest gap would be 0
        int result = 0;
        //prev is the max num if prev gap
        int prev = min;
        
        for(Bucket b : buckets){
            
            //skip empty bucket
            if( b.max == Integer.MIN_VALUE ) continue;
            
            result = Math.max( result, b.min - prev );
            
            prev = b.max;
        }
        
        return result;
    }
    
    private class Bucket{
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
    }
}
