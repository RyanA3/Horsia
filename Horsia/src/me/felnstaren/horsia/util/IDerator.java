package me.felnstaren.horsia.util;

import java.math.BigDecimal;
import java.math.MathContext;

import me.felnstaren.horsia.util.logger.Level;
import me.felnstaren.horsia.util.logger.Logger;

public class IDerator {

	private static int id_upper_bound = 8;
	private static int id_lower_bound = 3;
	
	public static int getID(double jump) {
		Logger.log(Level.DEBUG, " --- ID CALCULATION --- ");
		Logger.log(Level.DEBUG, "Calculating id from double... " + jump);
		int shift_upper = (int) Math.pow(10, id_upper_bound);
		int shift_lower = (int) Math.pow(10, id_lower_bound);
		int shift_difference = (int) Math.pow(10, id_upper_bound - id_lower_bound);
		
		int remove = (int) Math.floor(jump * shift_lower);
		remove *= shift_difference;
		
		int id = (int) Math.floor(jump * shift_upper);
		int newid = id - remove;
		
		Logger.log(Level.DEBUG, id + " - " + remove + " = " + newid);
		Logger.log(Level.DEBUG, " --- END CALCULATION --- ");
		
		return newid;
	}
	
	public static double reverseID(int id, double rounded_jump) {
		Logger.log(Level.DEBUG, " --- REVERSE ID CALCULATION --- ");
		Logger.log(Level.DEBUG, "Combining id with double... " + rounded_jump);
		
		BigDecimal base = BigDecimal.valueOf(rounded_jump);
		BigDecimal add = BigDecimal.valueOf(id);
		add = add.movePointLeft(id_upper_bound);
		base = base.add(add, MathContext.DECIMAL64);
		base = base.setScale(id_upper_bound, BigDecimal.ROUND_FLOOR);
		
		Logger.log(Level.DEBUG, rounded_jump + " + " + add.doubleValue() + " = " + base.doubleValue());
		Logger.log(Level.DEBUG, " --- END REVERSE CALCULATION --- ");
		
		return base.doubleValue();
	}
	
}
