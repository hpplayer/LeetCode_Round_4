import java.util.*;

/*
251. Flatten 2D Vector

Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Hint:

How many variables do you need to keep track?
Two variables is all you need. Try with x and y.
Beware of empty rows. It could be the first few rows.
To write correct code, think about the invariant to maintain. What is it?
The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
Not sure? Think about how you would implement hasNext(). Which is more complex?
Common logic in two different places should be refactored into a common method.

Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.

*/

/**
 * Two pointer solution
 * 
 * We can also use iterator to solve this problem, but that would cost extra time
 * We just need to use two pointers to achieve the same goal
 * 
 * One pointer points to the rows in 2D-vector
 * One pointer points to the col in each row
 * 
 * Time complexity: O(mn)
 * Space complexity: O(mn)
 * 
 * @author hpPlayer
 * @date Jul 1, 2016 11:14:46 PM
 */
public class Flatten_2D_Vector_p251_sol1 implements Iterator<Integer> {
    int row;
    int col;
    List<List<Integer>> vec2d;
    
    public Flatten_2D_Vector_p251_sol1(List<List<Integer>> vec2d) {
        row = 0;
        col = 0;
        this.vec2d = vec2d;
    }

    @Override
    public Integer next() {
        return vec2d.get(row).get(col++);
    }

    @Override
    public boolean hasNext() {
        //skip invalid row or finished row
        while( row < vec2d.size() && (col >= vec2d.get(row).size()) ){
            //move to next row
            row++;
            //reset col
            col = 0;
        }
        
        //check if two pointers are valid
        return row < vec2d.size() && col < vec2d.get(row).size();
    }
}
/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */