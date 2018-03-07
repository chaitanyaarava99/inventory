package com.x.inventory;

import com.x.inventory.utils.InventoryUtils;

public class LineItem {

	
	String product;
	double costPrice = 0.0;
	double sellingPrice = 0.00;
	int quantity =0;
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	
	
	public String printLineItemForReport(){
		return product+"\t\t"+InventoryUtils.formatNumber(costPrice)+"\t\t"+InventoryUtils.formatNumber(sellingPrice)+"\t\t\t"+quantity+"\t\t"+InventoryUtils.formatNumber(costPrice*quantity);
	}
	@Override
	public String toString() {
		return "LineItem [product=" + product + ", costPrice=" + costPrice + ", sellingPrice=" + sellingPrice
				+ ", quantity=" + quantity + "]";
	}
	
}
