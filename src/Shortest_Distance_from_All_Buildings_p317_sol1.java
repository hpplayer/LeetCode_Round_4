import java.util.*;

/*
317. Shortest Distance from All Buildings

You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.

For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
*/

/**
 * BFS solution
 * 
 * We use two matrixes to find the target. count[][] counts how many buildings we have scanned that can 
 * reach current empty spot, our final choice should be reachable by all buildings. distance[][] counts
 * the sum of distances from buildings to current empty spot
 *  
 * This solution is similar to Walls_and_Gates_p286_sol1. In both of them, instead of starting from empty
 * space, we start from the gates or buildings, and use BFS to update distances
 * 
 * Time complexity: O(mn)
 * Space complexity: O(mn)
 * 
 * @author hpPlayer
 * @date Jul 6, 2016 8:21:16 PM
 */
public class Shortest_Distance_from_All_Buildings_p317_sol1 {
    public int shortestDistance(int[][] grid) {
        //boundary check
        if(grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        
        //count[i][j] means how many buildings we have visited that having access to this empty spot 
        int[][] count = new int[m][n];
        //distance[i][j] means the sum of disances from all buildings to this empty spot
        int[][] distance = new int[m][n];
        
        List<MyNode> buildings = new ArrayList<MyNode>();
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                //we use a trick here to avoid the updates of non-empty spot
                //we set those non-empty spots' values to be -1, so they will not be updated later 
                if( grid[i][j] != 0 ) count[i][j] = -1;
                
                if(grid[i][j] == 1) buildings.add( new MyNode(i, j, 0) );
            }
        }
        
        for(int i = 0; i < buildings.size(); i++){
            BFS(buildings.get(i), count, distance, i);
        }
        
        int result = Integer.MAX_VALUE;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if( count[i][j] == buildings.size() ) result = Math.min(result, distance[i][j] );
            }
        }
        
        return result == Integer.MAX_VALUE? -1 : result;
    }
    
    private void BFS( MyNode node, int[][] count, int[][] distance, int index ){
        int[] xOffset = {1, -1, 0, 0};
        int[] yOffset = {0, 0, 1, -1};
        
        Queue<MyNode> que = new LinkedList<MyNode>();
        que.offer(node);
        
        while( !que.isEmpty() ){
            MyNode curr = que.poll();
            
            for(int i = 0; i < xOffset.length; i++){
                int newX = curr.x + xOffset[i];
                int newY = curr.y + yOffset[i];
                
                //after visiting each building, the count[i][j] for all accessible building will be + 1
                //and we set count value to non-empty spot to be -1, so by checking if count[i] == index,
                //we can check if curr node is an empty spot. (we will discard empty spots that cannot be 
                //accessed by all buildings as well)
                if( newX >= 0 && newX < count.length && newY >= 0 && newY < count[0].length && count[newX][newY] == index){
                    
                    count[newX][newY]++;
                    //curr.dis + 1 is the distance from building "buildings[index]" to curr empty spot
                    //distance[newX][newY] is the accumulative sum of distances
                    distance[newX][newY] += curr.dis + 1;
                    que.offer(new MyNode(newX, newY, curr.dis + 1));
                }   
            }
        }
        
        return;
    }
    
    private class MyNode{
        int x;
        int y;
        int dis;
        
        MyNode(int x, int y, int dis){
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
        
    }
}
