package com.commerce.stock.view;

public class SoldProduct {
	
	private String productId;
	private int quantity;
	
	
	public SoldProduct(String productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public String getProductId() {
		return productId;
	}
	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "SoldProduct [productId=" + productId + ", quantity=" + quantity + "]";
	}
	
	
}
