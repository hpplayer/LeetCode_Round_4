import java.util.*;

/*
166. Fraction to Recurring Decimal

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
Hint:

No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.
*/

/**
 * HashMap solution
 * 
 * Things need attention: 1) overflow problem 2) pos/neg sign 3)recurring digits may be 1, 2, 3, etc.(ex: 1/99, we should get 0.(01))
 * 
 * For 1), we need convert input to long typ1
 * For 2), we use (numerator < 0) ^ (denominator < 0) to catch negative cases. In case numerator = 0, our checker will
 * not work here, so we need manually check it in the beginning
 * For 3) we have to use a HashMap, key is remainder, value is size of sb at this moment, therefore next time when we 
 * get same remainder again, we will stop the loop and add a "(" at the index == size of sb recorded in Hashmap 
 * 
 * Time complexity: O(N) where N is the length of decimal part
 * Space complexity: O(N) where N is the len of decimal part
 * Since input is integer and len is known, actually both time and space complexity have O(1) complexity 
 * 
 * @author hpPlayer
 * @date Jun 25, 2016 5:41:14 PM
 */
public class Fraction_to_Recurring_Decimal_p166_sol1 {
    public String fractionToDecimal(int numerator, int denominator) {
        //HashMap solution
        
        //boundary check
        if(numerator == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        //key is remainder, value is len of curr sb, which is starting index of this remainder 
        HashMap<Long, Integer> hs = new HashMap<Long, Integer>();
        
        //convert to long to prevent overflow
        long n = (long) numerator;
        long d = (long) denominator;
        
        //catch negative sign
        if( (n < 0) ^ (d < 0) ){
            sb.append("-");
            n = Math.abs(n);
            d = Math.abs(d);
        }
        
        sb.append(n/d);
        long remainder = n%d;
        
        if(remainder == 0) return sb.toString();
        
        sb.append(".");
        
        //we will process inputs until we face a visited remainder again
        //in case inputs are dividable, we will return result inside the loop
        while( !hs.containsKey(remainder) ){
        	//each loop we will start with a new remainder from last loop
        	
            //record curr remainder and its starting index
            hs.put( remainder, sb.length() );
            
            //update remainder like what we did in math division
            remainder *= 10;
            
            sb.append( remainder/d );
            
            //update remainder to get next remainder
            remainder %= d;
            
            //inputs are dividable
            if(remainder == 0) return sb.toString();
        }
        
        //we get here only when we have Recurring Decimal, add "(" based on hashMap value 
        sb.insert( hs.get(remainder), "("  ).append(")");
        
        return sb.toString();
    }
}
