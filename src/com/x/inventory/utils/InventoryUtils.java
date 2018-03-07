package com.x.inventory.utils;

import java.text.DecimalFormat;

public class InventoryUtils {

	static DecimalFormat format = new DecimalFormat("##.00");

	public static String formatNumber(double d){
		return format.format(d);
	}
}
