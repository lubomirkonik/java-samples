package demo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalDemo {

	public static void main(String[] args) {
		// create 2 BigDecimal Objects
        BigDecimal bg1, bg2;

        bg1 = new BigDecimal("123.1267");

        try {
        	bg2 = bg1.setScale(2);  //123.12 != 123.1267
		} catch (ArithmeticException arithmeticException) {
			bg2 = bg1.round(new MathContext(2)).setScale(2);
        	// result = 120.00
			bg2 = bg1.round(new MathContext(2));
			// result = 1.2E+2
		}
        
        //bg2 = new BigDecimal("35.3456").round(new MathContext(4, RoundingMode.HALF_UP));
        bg2 = new BigDecimal("35.3456").round(new MathContext(4));
        //result = 35.35
        bg2 = new BigDecimal("35.3456").round(new MathContext(4)).setScale(3);
        //result = 35.350
        
        bg2 = bg1.setScale(2, RoundingMode.HALF_UP);
        // result = 123.13
        
        // set scale of bg1 to 6 in bg2
        bg2 = bg1.setScale(6);
        
        String str = "The value of " +bg1+ " after changing the scale to 6 is " +bg2;

        // print bg2 value
        System.out.println( str );
	}
}
