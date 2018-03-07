package com.x.inventory;

import com.x.inventory.utils.InventoryUtils;

public class ProfitCalculator {

	double profitSinceLastReport = 0;
	
	public void sellItem(LineItem item, int qty){
		double profit = (item.getSellingPrice() - item.getCostPrice()) * qty;
		
		profitSinceLastReport = profitSinceLastReport + profit;
	}
	
	public void deleteItem(LineItem item){
		double profit = item.getCostPrice() * item.quantity;
		profitSinceLastReport = profitSinceLastReport - profit;
	}

	public String printProfitForReport(){
		String profit = InventoryUtils.formatNumber(profitSinceLastReport);
		profitSinceLastReport = 0;
		return profit;
	}
	
}
