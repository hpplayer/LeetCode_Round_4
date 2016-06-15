/*
50. Pow(x, n)

Implement pow(x, n).
*/

/**
 * Math solution
 * 
 * We will recursively call the function until we reach boundary cases
 * Each time we will shrink power to half, and increase x two times larger.
 * Therefore we need check if n is odd or even. For odd, we need take a power off, then half it
 * 
 * Remark: be careful with negative power!
 * 
 * Time complexity: O(logN), where n is power
 * Time complexity: O(logN) same as above due to stack
 * 
 * @author hpPlayer
 * @date Jun 14, 2016 10:58:30 PM
 */
public class Pow_x_n_p50_sol1 {
    public double myPow(double x, int n) {
        //boundary cases
        if(n == 0) return 1;
        if(n == 1) return x;
        if(n == -1) return 1/x;
        
        
        return (n&1) == 1? x * myPow(x*x, (n-1)/2 ) : myPow(x*x, n/2);
    }
}
