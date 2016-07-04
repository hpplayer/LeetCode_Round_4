/**
 * Two pointer solution
 * 
 * Since index is from 0 to n (both inclusive) and cell value is from 1 to n (both inclusive), we can actually
 * use the cell value to jump among indexes, and we will finally end with a cycle. And the entry point of this 
 * cycle would be duplicate number.
 * 
 * Why? 
 * Index is from 0-n, and cell value is from 1-n. when we start with index 0, we will never be able to back to index 0.
 * So we at least we have one cell that is not belong to a cycle, which makes it possible to find the entry point of
 * cycle.
 * 
 * And our traversal will be finally in a cycle, and this cycle must contains duplicates. And the entry point must
 * be the duplicate number, otherwise there is no way that we can get to the entry point for a cycle without visiting
 * a duplicate number
 * 
 * ex: 
 * 0 1 2 3
 *   2 1
 * In this case 1, 2 form a cycle, cell[3] can be an entry for this cycle having value 1 or 2
 * Or cell[3] can have value 3, which means it self is a cycle
 * cell[0] have value 1, 2 means 3 will not be visited, and our cycle is between 1, 2
 * cell[0] have value 3 means 3 will be visited, and cell[3] may either be an entry point for cycle 1,2
 * or cell[3] itself may be a cycle
 * In either case, we can start from index0 and find the start index of cycle which is just the duplicate number
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Notice: the way we used to find entry point of cycle is similar to Linked_List_Cycle_II_p142_sol1
 * 
 * @author hpPlayer
 * @date Jul 3, 2016 5:27:17 PM
 */
public class Find_the_Duplicate_Number_p287_sol2 {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while( nums[slow] != nums[fast] );
        
        fast = 0;
        
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
}
