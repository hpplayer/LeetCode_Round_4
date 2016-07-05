import java.util.Arrays;

/*
 * 
307. Range Sum Query - Mutable 

Given an integer array nums, find the sum of the elements between indices i and j (i ¡Ü j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8

Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.

*/

/**
 * Segment Tree solution
 * 
 * We use array to represent Segment tree. Very basic implementation of segment tree in array
 * 
 * In this solution, we will create a tree array with len 2 * n, where n is len of input
 * That means our array is presenting an actual tree, not a complete tree. To fill the array, we will fill the array
 * bottom-up, instead of using recursions and split indexes top->bot. Since we at most have 2*n - 1 nodes, and our array
 * has len of 2 * n, it means cell 0 will always be empty
 * 
 * In this representation, we use index i to locate node. if i is even, then i is a left child. if i is odd, then i 
 * is a right child. The parent of i, no matter even or odd, will always be i/2. 
 * 
 * Sol1 is segmentTree solution, which is not easy to understand but only requires N space
 * Sol2 is BinaryIndexedTree solution, which same at time complexity, easy to understand, but requires 2N space
 * 
 * @author hpPlayer
 * @date Jul 4, 2016 8:54:07 PM
 */
public class Range_Sum_Query_1D_Mutable_p307_sol1 {
	public static void main(String[] args){
		Range_Sum_Query_1D_Mutable_p307_sol1 test = new Range_Sum_Query_1D_Mutable_p307_sol1(new int[]{1,3,5} );
		test.sumRange(0, 2);
	}
    int[] trees;
    int[] nums;
    //n is len of input, we put it here for convenience
    int n;
    
    public Range_Sum_Query_1D_Mutable_p307_sol1(int[] nums) {
        n = nums.length;
        //we have n leaf nodes, and the tree would totally have 2 * n - 1 nodes
        //therefore we can use an array with 2 * n to store all nodes, and trees[0] will always be empty
        trees = new int[2*n];
        this.nums = nums;    
        buildTree();
        
    }

    void buildTree(){
        
        //firstly fill the leaf nodes
        //i is pointer in nums, j is pointer in trees
        for(int i = 0, j = n; i < n; i++, j++){
            trees[j] = nums[i];
        }
        
        //secondly fill the nodes inside the tree bottom-up
        for(int i = n - 1; i > 0; i--){
            //for node i, its left child would be 2 * n, and right child would be 2 * n + 1
            //ex: input[1,2], trees[0, 3, 1, 2]
            //for root node 3 in index 1, 1 is left child in index 2, 2 is right child in index 3
            trees[i] = trees[i*2] + trees[i*2+1];
        }
    }
    
    void update(int i, int val) {
        //convert index i
        i += n;
        //update leaf node
        trees[i] = val;
        
        //updating the tree bottom up, until we reach the root (index 1)
        
        while(i >= 1){
            //we need check if the input is left or right child of its parent node
            //so we can update parent node accordingly
            
            int left = i;
            int right = i;
            
            if( (i&1) == 1 ){
                //right child, we need find left child
                left--;
            }else{
                //left child, we need find right child
                right++;
            }
            
            trees[i/2] = trees[left] + trees[right];
            i /= 2;
        }
    }

    public int sumRange(int i, int j) {
    	
        //convert i and j to be index in trees
        i += n;
        j += n;
        
        int sum = 0;
        
        //we keep updating sum until j < i
        //basically, we want go up and find the node that contains sum from i to j
        //if we don't have a such node, then we will collect sums partially until we have covered range from i to j
        while( i <= j ){
            //i is left boundary and now i is right child of parent node
            //so we cannot go up on this path anymore, we need jump to its next node 
            if( (i&1) == 1 ){
                //right child
                
                //collecting partial sum from initial i to curr i 
                sum += trees[i];
                //jump to next node
                i++;
            }
            
            //j is right boundary and now j is left child of parent node
            //so we cannot go up on this path anymore, we need jump to its prev node 
            if( (j&1) == 0 ){
                //left child
                
                //collecting partial sum from curr j to initial j 
                sum += trees[j];
                //jump to prev node
                j--;
            }            
            
            //let i and j go above along the path
            //the parent node is just i/2 and j/2
            
            i /= 2;
            j /= 2;
        }
        
        return sum;
    }
}
//Your NumArray object will be instantiated and called as such:
//NumArray numArray = new NumArray(nums);
//numArray.sumRange(0, 1);
//numArray.update(1, 10);
//numArray.sumRange(1, 2);