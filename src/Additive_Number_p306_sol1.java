import java.math.BigInteger;

/*
306. Additive Number

Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers,
each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Follow up:
How would you handle overflow for very large input integers?
*/

/**
 * Big Integer solution
 * 
 * We firstly pick the starting num1 and num2, then using recursive isValid() to test remaining strings
 * This is possible since num3 and all followings nums are fixed once we have fixed num1 and num2
 *  
 * very large inputs can be covered by using build-in BigInteger type.
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jul 4, 2016 8:05:03 PM
 */
public class Additive_Number_p306_sol1 {
    public boolean isAdditiveNumber(String num) {
        for(int num1Len = 1; num1Len + 1 <= num.length() - num1Len; num1Len++){
            //after num1, our remaining string length should at least be num1Len + 1 
            //ex: 909
            
            //skip num1 starting 0 if len > 1 
            if(num.charAt(0) == '0' && num1Len > 1 ) return false;
            
            String num1 = num.substring(0, num1Len);
            for(int num2Len = 1; Math.max(num1Len, num2Len) <= num.length() - num1Len - num2Len; num2Len++){
                //after num2, our remaining string should at least contain num3
                //num3's len should be at least Math.max(num1, num2)
                String num2 = num.substring(num1Len, num1Len + num2Len);
                
                //skip num2 starting 0 if len > 1 
                if(num2.charAt(0) == '0' && num2Len > 1 ) break;
                
                if( isValid( new BigInteger(num1), new BigInteger(num2), num.substring(num2Len + num1Len) ) ) return true;
            }
        }
        
        return false;
    }
    
    private boolean isValid(BigInteger num1, BigInteger num2, String remaining){
        if( remaining.length() == 0 ) return true;
        
        BigInteger sum = num1.add(num2);
        String sum_s = sum.toString();
        
        if( remaining.startsWith(sum_s) && isValid( num2, sum, remaining.substring(sum_s.length() ) )  ) return true;
        
        return false;
    }
}
