import java.util.TreeSet;

/**
 * TreeSet solution
 * 
 * We use the feature of TreeSet to find the floor and ceill value of target, then check if diff <= t
 * 
 * Time complexity: O( Nlog(k) ), each insertion on TreeSet costs O(log(k)) and we have N nums
 * Space complexity: O( k ), TreeSet has size k
 * 
 * Remark:
 * To preven overflow when t = int_min, we need case type to long
 * 
 * @author hpPlayer
 * @date Jun 27, 2016 12:07:22 AM
 */
public class Contains_Duplicate_III_p220_sol2 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        
        for(int i = 0; i < nums.length; i++){
            Integer floor = set.floor(nums[i]);
            Integer ceil = set.ceiling(nums[i]);
            
            if( (floor != null && ( (long) nums[i] - floor <= t)) || (ceil != null && (ceil - (long)nums[i] <= t) ) ){
                return  true;
            }
            
            set.add(nums[i]);
            
            if( set.size() == k + 1 ) set.remove(nums[i-k]);
            
        }
        
        return false;
    }
}
