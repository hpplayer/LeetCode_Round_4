/*
4. Median of Two Sorted Arrays

There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted
arrays. The overall run time complexity should be O(log (m+n)).
*/

/**
 * Divide-Conquer solution
 * 
 * We firstly get the index(es) of median element, then recursively find kth element in two arrays.
 * To get kth element in two arrays, we will compare k/2 th element in two arrays. Then move pointer
 * in the array with smaller k/2 th element since we have found k/2 smaller elements in the two sorted arrays!
 * Then we will continue search (k-k/2) element until we found target.
 * 
 *   x a *
 * x T F T 0a
 * a F T T 1a
 * a F F T 2a 
 * a F F T 3a
 * 
 * Remark:
 * Kth element must start with 1 not 0. Otherwise we will stuck in infinite loop when searching for 1th element
 * 1/2 => 0, remaining => k - k/2 = 1, no change at all
 * 
 * Time complexity: O(log(m+n))
 * Space complexity: O(log(m+n))
 * 
 * @author hpPlayer
 * @date Jun 12, 2016 3:35:59 PM
 */
public class Median_of_Two_Sorted_Arrays_p4_sol1 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        
        if( (len&1) == 1 ){
            return getKthElement(nums1, nums2, 0, 0, len/2 + 1) * 1.0;
        }else{
            return (getKthElement(nums1, nums2, 0, 0, len/2) + getKthElement(nums1, nums2, 0, 0, len/2 + 1))/2.0;
        }
        
    }
    
    public int getKthElement(int[] n1, int[] n2, int p, int q, int k){
        //p, q is starting index in two arrays (0-based), k is index of element we are looking for (1-based)
        
        //boundary cases
        if( p >= n1.length ) return n2[q + k - 1];
        if( q >= n2.length ) return n1[p + k - 1];
        
        if( k == 1 ) return Math.min( n1[p], n2[q] );
        
        //compare k/2 element in new searching range in two arrays
        int v1 = p + k/2 - 1 >= n1.length? Integer.MAX_VALUE : n1[p + k/2 - 1];
        int v2 = q + k/2 - 1 >= n2.length? Integer.MAX_VALUE : n2[q + k/2 - 1];
        
        if( v1 > v2){
            //we are comparing p + k/2 - 1 with q + k/2 - 1 th element, therefore new search will start after
            //those indexes, i.e. p + k/2 or q + k/2
            //use k/2 always takes flooring value, therefore we use k - k/2 to get the remaining value
            return getKthElement(n1, n2, p, q + k/2, k - k/2 );
        }else{
            return getKthElement(n1, n2, p + k/2, q, k - k/2 );
        }
        
    }
}
