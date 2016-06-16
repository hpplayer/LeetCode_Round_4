/*
69. Sqrt(x)

Implement int sqrt(int x).

Compute and return the square root of x.
*/

/**
 * Math + binary search solution
 * 
 * We just need to find the num among [1,n] that can have n * n == x.
 * If no n can give n * n == x, then we return the largest n that have n * n < x
 * Therefore we need use binary search here
 * 
 * Time complexity: O(logN)
 * space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 15, 2016 10:13:37 PM
 */
public class Sqrt_x_p69_sol1 {
    public int mySqrt(int x) {
        //boundary cases
        if( x < 0 ) throw new IllegalArgumentException();
        if( x == 0 ) return 0;
        
        //use binary search to find the n that gives n * n <= x
        int left = 1, right = x;
        
        while(left <= right){
            int mid = left + (right-left)/2;
            
            //to prevent overflow we use x/n vs n
            if( mid == x/mid ){
                return mid;
            }else if( mid > x/mid ){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        
        //return the largest n gives n * n < x
        return right;
    }
}
