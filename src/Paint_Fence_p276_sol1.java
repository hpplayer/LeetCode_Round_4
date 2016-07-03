/*
276. Paint Fence

There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
*/

/**
 * Since we allow at most two nearby fences have same color, and we are asked to get the total sum of ways to paint
 * fences, the problem is obviously a dp problem
 * 
 * We need to be careful about the word "total" which means we are not required to get the max or min value, but 
 * the sum of values.
 * 
 * In case we want to paint curr fence same color with prev fence, we have to use the prev value of diff color 
 * In case we want to paint curr fence diff color with prev fence, then we are free to choose between diff and same color
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jul 2, 2016 8:03:33 PM
 */
public class Paint_Fence_p276_sol1 {
    public int numWays(int n, int k) {
        //boundary case that we have 0 or 1 fence
        
        if(n == 0) return 0;
        if(n == 1) return k;
        
        //we choose curr color to be same with prev one
        int sameColor = k;
        //we choose curr color to be diff with prev one
        int diffColor = k * (k-1);
        
        //we have define prev value for n = 2 cases, now starting with 3
        for(int i = 3; i <= n; i++){
            int temp = sameColor;
            
            //if we choose curr color to be same with prev one, then we have to pick the prev diff value 
            sameColor = diffColor;
            //if we choose curr color to be diff with prev one, then we are free to pick prev diff or same value
            diffColor = (temp + diffColor) * (k -1);
        }
        
        //we are asked to return the total ways, so return sameColor + diffColor
        return sameColor + diffColor;
    }
}
