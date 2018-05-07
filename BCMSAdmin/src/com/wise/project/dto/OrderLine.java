package com.wise.project.dto;

import com.wise.project.dto.Order;
import com.wise.project.dto.Product;

public class OrderLine {
	private int quantity;
	private Product Product;
	private	Order order;
	private double price;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return Product;
	}
	public void setProduct(Product product) {
		Product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "OrderLine [quantity=" + quantity + ", Product=" + Product + ", order=" + order + ", price=" + price
				+ "]";
	}
	
	

}
