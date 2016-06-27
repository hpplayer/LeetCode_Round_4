import java.util.*;

/*
220. Contains Duplicate III

Given an array of integers, find out whether there are two distinct indices i and j in the array such that the
difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
*/


/**
 * Bucketing solution
 * 
 * We assign each input into different buckets. Inside each bucket, we always have diff < t, in consecutive buckets
 * we may have diff > t. 
 * 
 * We also need to notice about overflow when t = Integer.MIN_VALUE, so we always use long type here
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jun 27, 2016 12:10:52 AM
 */

public class Contains_Duplicate_III_p220_sol1 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //we will assign buckets based on t. In case t < 0, we return false directly, so is k < 0
        
        if( k < 0 || t < 0 ) return false;
        
        //to handle negative num case, we will convert it to positive by subtracting int_min
        HashMap<Long, Integer> buckets = new HashMap<>();
        
        for(int i = 0; i < nums.length; i++){
            if( i > k ){
                //in case our sliding win size > k, we will remove the leftmost num
                
                //convert val to positive by -int_min in case nums[i - k - 1] is negative
                long val = (long) nums[i - k - 1] - Integer.MIN_VALUE;
                //in case t = int_max, we cast it to long
                //we need t + 1 here, since we want nums in bucket has diff <= t
                //ex: t = 3, [0, 1, 2, 3] diff in bucket <= t, they all have num/4 < 1
                long bucket = val / ( (long) t + 1 );
                
                buckets.remove(bucket);
            }
            
            long val = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = val / ( (long) t + 1 );            
            
            //if we already have this bucket, or value in Math.abs(consecutive bucket - nums[i]) <= t, return true 
            if(  buckets.containsKey(bucket) || (buckets.containsKey(bucket-1) && (long) nums[i] - buckets.get(bucket-1) <= t ) ||
            		(buckets.containsKey(bucket+1) && (long) buckets.get(bucket+1) - nums[i] <= t )  ){
                return true;
            }
            
            
            buckets.put( bucket, nums[i] );
            
        }
        
        return false;
    }
}
