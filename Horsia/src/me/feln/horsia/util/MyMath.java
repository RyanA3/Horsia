package me.feln.horsia.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyMath {

	public static double round(double number, int decimal) {
		//int shift = (int) Math.pow(10, decimal);
		//number *= shift;
		//number = Math.round(number);
		//number /= shift;
		//return number;
		
		BigDecimal precisenum = new BigDecimal(number);
		precisenum.setScale(decimal, RoundingMode.HALF_UP);
		return precisenum.doubleValue();
	}
	
	public static double roundDown(double number, int decimal) {
		//int shift = (int) Math.pow(10, decimal);
		//number *= shift;
		//number = Math.floor(number);
		//number /= shift;
		//return number;
		
		BigDecimal precisenum = new BigDecimal(number);
		precisenum = precisenum.setScale(decimal, RoundingMode.FLOOR);
		return precisenum.doubleValue();
	}
	
}
