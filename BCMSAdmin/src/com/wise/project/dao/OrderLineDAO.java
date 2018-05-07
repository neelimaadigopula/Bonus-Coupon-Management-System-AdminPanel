package com.wise.project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.project.dto.Customer;
import com.wise.project.dto.Order;
import com.wise.project.dto.OrderLine;
import com.wise.project.dto.Product;
import com.wise.project.util.DBUtility;

public class OrderLineDAO {
	
	public int add(OrderLine orderLine) {
		int status = 0;
		final String QUERY = "insert into order_line(qty,product_id,orders_id,price) values(?,?,?,?)";
		try(Connection connection = DBUtility.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,orderLine.getQuantity());			
			preparedStatement.setInt(2,orderLine.getProduct().getId());
			preparedStatement.setInt(3,orderLine.getOrder().getId());
			preparedStatement.setDouble(4,orderLine.getPrice());
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	public List<OrderLine> get(int customerId,int orderId) {
		final String QUERY = "select ol.orders_id,  "
				+ "p.name, p.picture, ol.qty, ol.price from orders o, orders_line ol, "
				+ "products p where o.customer_id = ? and  ol.orders_id = o.orders_id and "
				+ "ol.products_id=p.product_id and ol.orders_id=? ";
		ResultSet resultSet =  null;
		List<OrderLine> list = new ArrayList<OrderLine>();
		PreparedStatement preparedStatement = null; 
		
		try(Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, customerId);
			preparedStatement.setInt(2, orderId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				OrderLine orderLine = new OrderLine();
				Order order=new Order();
				Product product = new Product();
				order.setId(resultSet.getInt(1));
				orderLine.setOrder(order);
				product.setName(resultSet.getString(2));
				product.setPicture(resultSet.getString(3));
				orderLine.setProduct(product);
				orderLine.setQuantity(resultSet.getInt(4));
				orderLine.setPrice(resultSet.getDouble(5));
				list.add(orderLine);
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
}
