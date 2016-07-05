/*
How does Binary Indexed Tree work?

The idea is based on the fact that all positive integers can be represented as sum of powers of 2.
For example 19 can be represented as 16 + 2 + 1. Every node of BI Tree stores sum of n elements where n is a power of 2.
For example, in the above first diagram for getSum(), sum of first 12 elements can be obtained by sum of last 4 elements
(from 9 to 12) plus sum of 8 elements (from 1 to 8). The number of set bits in binary representation of a number n is O(Logn).
Therefore, we traverse at-most O(Logn) nodes in both getSum() and update() operations.
Time complexity of construction is O(nLogn) as it calls update() for all n elements.
*/

/**
 * BIT solution
 * 
 * BIT compresses the inputs with index ranges based on binary representation. The index range is from 0 to n - 1, totally n
 * indexes, so our BIT[] also has n len. The theory behind it is that each index can be treated as a binary number. We group
 * indexes based on the binary representations. each subtree is differed by a set bit. parent and child nodes have same the most
 * significant bit, and differed in bits after it. Sibling nodes have different the most significant bit. Almost all single 
 * cell have been compressed into a part of index ranges. Therefore if we need to query a cell value at index i, unlike segmen tree
 * now we may have to go through multiple nodes to get the specific cell value at input array.
 * 
 * The basic operation in BIT is just removing the last set bit (get to parent) or adding 1 to the last set bit (go to next node)
 * 
 * @author hpPlayer
 * @date Jul 4, 2016 10:56:34 PM
 */
public class Range_Sum_Query_1D_Mutable_p307_sol2 {
    int[] BIT;
    int[] nums;
    int n;
    
    public Range_Sum_Query_1D_Mutable_p307_sol2(int[] nums) {
        n = nums.length;
        
        //The implementation of BIT is based on the fact that each positive number can be represented by
        //the sum of 2^i. But index 0 is not positive, so we convert indexes from 0 based to 1 based, therefore
        //we need n + 1 len for BIT[]
        BIT = new int[n + 1];        
        this.nums = nums;
        buildTree();
    }
    
    public void buildTree(){
        for(int i = 0; i < n; i++){
            //i is pointer in nums, j is pointer in BIT
            //j is 1 based
            int j = i + 1;
            
            //update all related cells by iteratively adding 1 to the last set bit
            while( j <= n ){
                BIT[j] += nums[i];
                //j & -j gets the mask that only containing the last set bit
                //we add it to j
                j += j & -j;
            }
        }
    }
    
    void update(int i, int val) {
        //update is same to buildTree(), we use j += j & -j to visit all related range cells, which contains
        //this index
        
        //convert to 1 based index in BIT[]
        int j = i + 1;
        int diff = val - nums[i]; 
        //Notice: unlike segment tree where we use Tree[] to include all vals, now we need nums[i] when updating sum
        //So we need to update nums[i]!!!!!!!!!!!!!!
        nums[i] = val;
        
        while( j <= n ){
            BIT[j] += diff;
            j += j & -j;
        }
    }

    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i-1);
    }
    
    public int getSum(int i){
        //getSum is reverse to buildTree(), we use j -= j & -j to collect all range cells, so we can finally 
        //cover range from 0 to i
        
        //convert to 1 based index in BIT[]
        int j = i + 1;
        int sum = 0;
        
        while( j >= 1){
            sum += BIT[j];
            j -= j & -j;
        }
        
        return sum;
    }
}
