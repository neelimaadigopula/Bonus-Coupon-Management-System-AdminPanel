package com.wise.project.dto;

import java.sql.Date;

import com.wise.project.dto.Customer;

public class Order {
	private int id;
	private Date date;
	private Customer customer;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public char[] getTransactionId() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
