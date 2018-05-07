package com.wise.project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.project.dto.Company;
import com.wise.project.dto.Customer;
import com.wise.project.dto.Order;
import com.wise.project.dto.Product;
import com.wise.project.util.DBUtility;

public class OrderDAO {
	public int add(Order order) {
		int status = 0;
		final String QUERY = "insert into orders(id,orderdate,customer_id) values(?,?,?)";
		try(Connection connection = DBUtility.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,order.getId());
			preparedStatement.setDate(2,order.getDate());
			preparedStatement.setInt(3,order.getCustomer().getId());
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	public List<Order> get(int customerId) {
		final String QUERY = "select * from orders where customer_id = ? and status ='successful' ";
		ResultSet resultSet =  null;
		
		List<Order> list = new ArrayList<Order>();
		PreparedStatement preparedStatement = null; 
		
		try(Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, customerId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt(1));
				Customer customer = new Customer();
				customer.setId(customerId);
				order.setCustomer(customer);
				order.setDate(resultSet.getDate(2));
				order.setStatus(resultSet.getString(4));
				
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return list;
		
	}
	public List<Order> get() {
		final String QUERY = "select * from orders";
		int status = 0;
		ResultSet resultSet =  null;
		List<Order> list = new ArrayList<Order>();
		PreparedStatement preparedStatement = null; 
		try(Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer customer = new Customer();
				Order order = new Order();
				order.setId(resultSet.getInt(1));
				order.setDate(resultSet.getDate(2));
				customer.setId(resultSet.getInt(3));
				order.setCustomer(customer);
				order.setStatus(resultSet.getString(4));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return list;
	}
	public Order getOrder(int id) {
		final String QUERY = "select * from orders where id = '"+id+"'";
		Customer customer = new Customer();
		ResultSet resultSet =  null;
		Order order = new Order();
		PreparedStatement preparedStatement = null; 
		try(Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			//Company company = new Company();
			if(resultSet.next()) {
				order.setId(resultSet.getInt(1));
				order.setDate(resultSet.getDate(2));
				customer.setId(resultSet.getInt(3));
				order.setCustomer(customer);
				order.setStatus(resultSet.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return order;
	}
	public int getTransactionId(){
		final String QUERY = "select max(id) from orders";
		int status = 0;
		ResultSet resultSet =  null;
		PreparedStatement preparedStatement = null; 
		int id = 0;
		try(Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				id = resultSet.getInt(1);
				if(id==0){
					id=id+1111;
				}
				else{
					id=id+100;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return id;
		
		
	}
}

