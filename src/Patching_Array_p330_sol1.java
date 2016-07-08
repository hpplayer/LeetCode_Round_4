/*
330. Patching Array

Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.

Example 1:
nums = [1, 3], n = 6
Return 1.

Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.

Example 2:
nums = [1, 5, 10], n = 20
Return 2.
The two patches can be [2, 4].

Example 3:
nums = [1, 2, 2], n = 5
Return 0.
*/

/**
 * Greedy solution
 * 
 * When creating a number, the num from input array cannot be used more than once
 * ex: [1] 2=> we need one more number
 * 
 * We use a variable to record the number we can reach so far by using nums (with paths maybe) from input array
 * The next reachable number is depending on the next num in the input array. If it is larger than curr reachable
 * number, then there is no way we can build nums among curr reachable and next num in input array as we reachable
 * number is the largest number we can reach by using nums in input array. So we at least need one patch here.
 * To maximize the range we can reach after patching, we pick the patch number to be curr reachable num. So we can
 * reach all nums between 1 to 2 * curr reach num
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jul 7, 2016 11:24:20 PM
 */
public class Patching_Array_p330_sol1 {
    public int minPatches(int[] nums, int n) {
        //reachableNum should from 1 to n (both inclusive)
        long reachableNum = 1;
        int index = 0;
        int patches = 0;
        
        while( reachableNum <= n ){
            if( index < nums.length && nums[index] <= reachableNum ){
                //if we still have nums left in input nums, and curr input num can help us increase the reachable range starting from curr reachableNum + 1
                reachableNum += nums[index];
                index++;
            }else{
                //if we don't have inputs left or can't help us increase the reachable range starting from curr reachableNum + 1, then we need at least one patch
                //to make this new patch can maximize our new range, we will pick "reachableNum" as its value
                
                patches++;
                reachableNum *= 2;
            }
        }
        
        return patches;
    }
}
