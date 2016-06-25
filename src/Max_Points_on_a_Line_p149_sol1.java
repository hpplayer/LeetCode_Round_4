import java.util.*;

/*
149. Max Points on a Line

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

/**
 * HashMap + Math solution
 * 
 * We use a nested loop to scan the input points. We fix point a, then scan all points after a in the input array
 * We will record the slope for each two points. Then use a HashMap to store the count of point b(s) with same slope with
 * point a. (Notice: here we use diffX + "#" + diffY to replace real slope, so we can be more accurate
 * 
 * To make sure that we have collected all points b with same slope, we will firstly use Euclid way to get the GCD,
 * then process slope with GCD
 * 
 * Other than that, there is a small issue that we need to notice. In case we get a duplicate point b with point a,
 * there will be an error when we process their slope, so we have to use an extra counter to count the num of duplicates
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date Jun 23, 2016 10:42:32 PM
 */

public class Max_Points_on_a_Line_p149_sol1 {
	public int maxPoints(Point[] points) {
	    //HashMap + Math solution
	    
	    int result = 0;
	    
	    for(int i = 0; i < points.length; i++){
	        //hs collects count of point b that shares same slope when lining with point a
	        HashMap<String, Integer> hs = new HashMap<String, Integer>();
	        int localMax = 0;
	        //duplicates records the num of points that same with point a 
	        int duplicates = 1;
	        Point a = points[i];
	        for(int j = i + 1; j < points.length; j++){
	            Point b = points[j];
	            int diffX = a.x - b.x;
	            int diffY = a.y - b.y;
	            
	            if(diffX == 0 && diffY == 0){
	                //handle duplicate cases, so we can safely process slope later
	                duplicates++;
	                continue;
	            }
	            
	            //get offset so that we can reduce slope to the simplest form
	            int offset = GCD(diffX, diffY);
	            
	            diffX /= offset;
	            diffY /= offset;
	            
	            //use string as slope so we can be more accurate
	            String key = diffX + "#" + diffY;
	            
	            if( !hs.containsKey(key) ){
	                hs.put(key, 1);
	                localMax = Math.max( localMax, 1 );
	            }else{
	                int val = hs.get(key) + 1;
	                hs.put( key, val );
	                localMax = Math.max( val, localMax );
	            }
	            
	        }
	        
	        //update result if possible
	        result = Math.max(result, localMax + duplicates );
	        
	    }
	    
	    return result;
	}
	
	public int GCD(int a, int b){
	    //Euclid way to get GCD
	    
	    while(b != 0){
	        int temp = b;
	        b = a%b;
	        a = temp;
	    }
	    
	    return a;
	}
}
