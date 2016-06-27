import java.util.*;

/*
 * 
 * 218. The Skyline Problem
 * 
 * 
* A city's skyline is the outer contour of the silhouette formed by all the buildings 
* in that city when viewed from a distance. Now suppose you are given the locations and 
* height of all the buildings as shown on a cityscape photo (Figure A), write a program 
* to output the skyline formed by these buildings collectively (Figure B).
* 
*  ^                                        ^                                                                   
*  |                                        |                                                                   
*  |                                        |                                                                   
*  |    +-----+                             |    O-----+                                                        
*  |    |     |                             |    |     |                                                        
*  |    |     |                             |    |     |                                                        
*  |    |  +--+------+                      |    |     O------+                                                 
*  |    |  |         |                      |    |            |                                                 
*  |  +-+--+----+    |   +------+           |  O-+            |   O------+                                      
*  |  |         |    |   |      |           |  |              |   |      |                                      
*  |  |         |    |   |    +-+--+        |  |              |   |      O--+  
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  |  |         |    |   |    |    |        |  |              |   |         |                                   
*  +--+---------+----+---+----+----+--->    +--+--------------O---+---------O--->                               
*  
*   https://leetcode.com/static/images/problemset/skyline1.jpg  
*   https://leetcode.com/static/images/problemset/skyline2.jpg  
* 
* The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
* where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, 
* and Hi is its height. It is guaranteed that 0 ¡Ü Li, Ri ¡Ü INT_MAX, 0 , and Ri - Li > 0. 
* You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
* 
* For instance, the dimensions of all buildings in Figure A are recorded as: 
*  [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
* 
* The output is a list of "key points" (red dots in Figure B) in the format of 
* [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. 
* A key point is the left endpoint of a horizontal line segment. 
*
* Note that the last key point, where the rightmost building ends, is merely used to mark 
* the termination of the skyline, and always has zero height. Also, the ground in between 
* any two adjacent buildings should be considered part of the skyline contour.
* 
* For instance, the skyline in Figure B should be represented as:
*  [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
* 
* Notes:
* 
*  - The number of buildings in any input list is guaranteed to be in the range [0, 10000].
*  - The input list is already sorted in ascending order by the left x position Li. 
*  - The output list must be sorted by the x position. 
*  - There must be no consecutive horizontal lines of equal height in the output skyline. 
*    For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; 
*    the three lines of height 5 should be merged into one in the final output as such: 
*    [...[2 3], [4 5], [12 7], ...]
* 
*/

/**
 * Merge sort solution
 * 
 * We do mergeSort on inputs, so that we can split the inputs, do analysis on inputs, then merge them if necessary
 * 
 * The tricky part is about the merge. We need 4 variables, two of them record the curr height from two lists
 * so that we can have the height information from prev results, two of them records the height and x-axis of new 
 * point, which will be merged to list if possible
 * 
 * Time complexity: O(nlogN)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jun 26, 2016 10:29:56 PM
 */
public class The_Skyline_Problem_p218_sol1 {
    public List<int[]> getSkyline(int[][] buildings) {
        return mergeSort(buildings, 0, buildings.length - 1);
    }
    
    public LinkedList<int[]> mergeSort(int[][] buildings, int start, int end){
        LinkedList<int[]> result = new LinkedList<int[]>();
        
        //boundary check
        if(start > end) return result;
        
        if(start == end){
            int[] building = buildings[start];
            
            //create two points for left and right edge respectively
            //left edge has height
            result.add(new int[]{building[0], building[2]});
            //right edge does not have height
            result.add(new int[]{building[1], 0});
            
            return result;
        }
        
        //general case, cut in half
        int mid = start + (end - start)/2;
        
        //in case input size is 1, assign (start, mid) can get appropriate list
        return merge( mergeSort(buildings, start, mid), mergeSort(buildings, mid + 1, end)  );
    }
    
    public LinkedList<int[]> merge( LinkedList<int[]> l1, LinkedList<int[]> l2  ){
        LinkedList<int[]> result = new LinkedList<int[]>();
        
        //curr height limit from l1
        int h_l1 = 0;
        //curr height limit from l2
        int h_l2 = 0;
        
        while( !l1.isEmpty() && !l2.isEmpty() ){
            //x, y are the axis of new point
            int x = 0, y = 0;
            
            int[] building_l1 = l1.peekFirst();
            int[] building_l2 = l2.peekFirst();
            
            if( building_l1[0] == building_l2[0] ){
                //x point is same, pop from both lists
                l1.pollFirst();
                l2.pollFirst();
                //update height limit for both lists
                h_l1 = building_l1[1];
                h_l2 = building_l2[1];
                
                y = Math.max(h_l1, h_l2);
                x = building_l1[0];
            }else if( building_l1[0] < building_l2[0] ){
                //building_l1 has smaller x, pop from list1
                l1.pollFirst();
                //update height limit for list1
                h_l1 = building_l1[1];
                y = Math.max(h_l1, h_l2);
                x = building_l1[0];                
            }else{
                //building_l2 has smaller x, pop from list2
                l2.pollFirst();
                //update height limit for list1
                h_l2 = building_l2[1];
                y = Math.max(h_l1, h_l2);
                x = building_l2[0];                    
            }
            
            //We have only two cases that can add new point 
            //1) result is empty 2) new point has different height with last building in result
            //since we have height limit h_l1/h_l2, the height of incoming building will always be taken of,
            //even if we have a higher or lower building at same x 
            if( result.isEmpty() || result.peekLast()[1] != y){
                result.offerLast(new int[]{x, y});
            }
        }
        
        if(!l1.isEmpty()) result.addAll(l1);
        if(!l2.isEmpty()) result.addAll(l2);
        
        return result;
    }
}
