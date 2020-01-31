package me.felnstaren.horsia.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyMath {

	public static double round(double number, int decimal) {
		BigDecimal precisenum = new BigDecimal(number);
		precisenum = precisenum.setScale(decimal, RoundingMode.HALF_UP);
		return precisenum.doubleValue();
	}
	
	public static double roundDown(double number, int decimal) {
		BigDecimal precisenum = new BigDecimal(number);
		precisenum = precisenum.setScale(decimal, RoundingMode.FLOOR);
		return precisenum.doubleValue();
	}
	
}
