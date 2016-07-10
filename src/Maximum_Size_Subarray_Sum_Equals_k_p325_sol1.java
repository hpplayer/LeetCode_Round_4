import java.util.*;

/*
363. Max Sum of Rectangle No Larger Than K

Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
*/

/**
 * DP + binarySearch solution
 * 
 * We use two pointers to locate the left and right boundaries of all possible rectangles.
 * Then use third pointer to update the col values from newly added right boundary. 
 * 
 * Now we have converted 2D sum problem to 1D sum problem. We can then use subarray sum solution
 * to solve the problem. 
 * 
 * Since now we have to find the subarray sum <= k, we need use binary search to find the solution
 * in O(logN) time. To make subarray sum sorted, we will use TreeSet and use a variable to hold 
 * accumulate sum
 * 
 * Time complexity: O( Min(m, n)^2 * Max(m, n) * log(Max(m,n)) 
 * Space complexity: O( max(m,n))
 * 
 * @author hpPlayer
 * @date Jul 9, 2016 11:48:53 PM
 */
public class Maximum_Size_Subarray_Sum_Equals_k_p325_sol1 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        //boundary check
        if( matrix.length == 0 ) return 0;
        
        int m = matrix.length, n = matrix[0].length;
        //sum may be negative, so initialize to int_min
        int result = Integer.MIN_VALUE;
        
        for(int left = 0; left < n; left++){
            int[] sums = new int[m];
            for(int right = left; right < n; right++){
                for(int i = 0; i < m; i++){
                    sums[i] += matrix[i][right];
                }
                
                TreeSet<Integer> set = new TreeSet<>();
                //add 0 so we can cover all sum ranges from 0 to curr index
                set.add(0);
                
                //like other 1D subarray sum problems, we need accumulate sum from index 0 to curr index
                int currSum = 0;
                
                for(int sum : sums){
                    //update currSum to include sum in curr index
                    currSum += sum;
                    //we need find the largest subarray sum "a" that gives currSum - k <= a, so we use ceiling
                    Integer num = set.ceiling( currSum - k  );
                    
                    //if we have a such num, then we can calculate the sum of target subarray
                    if(num != null) result = Math.max(result, currSum - num);
                    //add curr subarray sum from 0 to curr index
                    set.add(currSum);
                }
            }
        }
        
        return result;
    }
}
