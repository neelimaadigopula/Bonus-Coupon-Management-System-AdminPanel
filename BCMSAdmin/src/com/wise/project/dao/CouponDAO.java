package com.wise.project.dao;

import java.sql.Connection;
import com.wise.project.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.project.dto.Coupon;
import com.wise.project.dto.CustomerCoupon;
import com.wise.project.dto.Denomination;
import com.wise.project.util.DBUtility;
public class CouponDAO {
	public int add(Coupon coupon, int denominationId) {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Denomination denomination = new Denomination();
		//Coupon coupon = new Coupon();
		int status = 0;
		final String QUERY = "insert into coupons(coupon_id, validity, denomination_id, activate) values(?, ?, ?, ?)";
		try(Connection connection = DBUtility.getConnection()){
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, coupon.getId());
			preparedStatement.setString(2, coupon.getValidity());
				preparedStatement.setInt(3, coupon.getDenomination().getId());
				preparedStatement.setInt(4, coupon.getActivate());
				status = preparedStatement.executeUpdate();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}

		return status;
    }
	//gets coupon by id
    public Coupon get(int couponId) {
    	ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Coupon coupon = new Coupon();
		Denomination denomination = new Denomination();
		final String QUERY = "select c.coupon_id, c.validity, c.denomination_id, d.amount from coupons c, denomination d where d.id = c.denomination_id and c.coupon_id = ?";
		try(Connection connection = DBUtility.getConnection()){
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, couponId);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				coupon.setId(resultSet.getInt(1));
				coupon.setValidity(resultSet.getString(2));
				denomination.setId(resultSet.getInt(3));
				denomination.setAmount(resultSet.getDouble(4));
				coupon.setDenomination(denomination);
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return coupon;
	}
    
    //returns list of all coupons
    public List<Coupon> get() {
    	final String QUERY = "select c.coupon_id, c.validity, d.id, d.amount from coupons c, denomination d where d.id = c.denomination_id";
    	ResultSet resultSet = null;
    	List<Coupon> list = new ArrayList<Coupon>();
    	PreparedStatement preparedStatement = null;
    	try(Connection connection = DBUtility.getConnection()) {
    		preparedStatement = connection.prepareStatement(QUERY);
    		resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Coupon coupon = new Coupon();
				Denomination denomination = new Denomination();
				coupon.setId(resultSet.getInt(1));
				coupon.setValidity(resultSet.getString(2));
				denomination.setId(resultSet.getInt(3));
				denomination.setAmount(resultSet.getDouble(4));
				coupon.setDenomination(denomination);
				list.add(coupon);
			}
    	}catch(SQLException exception) {
    		exception.printStackTrace();
    	}
    	finally {
    		DBUtility.close(preparedStatement,resultSet);
    	}
        return list;
    }
    
    //returns coupon details based on denomination id
    public List<Coupon> getCoupons(int denominationId) {
    	final String QUERY = "SELECT coupons.coupon_id, coupons.denomination_id, coupons.validity,denomination.amount, FROM coupons  INNER JOIN denomination ON denomination.id=? and coupons.denomination_id=denomination.id and coupons.activate = ?";
    	ResultSet resultSet = null;
    	List<Coupon> list = new ArrayList<Coupon>();
    	
    	int activate = 1;
    	Denomination denomination = new Denomination(); 
    	PreparedStatement preparedStatement = null;
    	try(Connection connection = DBUtility.getConnection()) {
    		preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			preparedStatement.setInt(1, denominationId);
			preparedStatement.setInt(2, activate);
			while(resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				denomination.setId(resultSet.getInt(2));
				coupon.setValidity(resultSet.getString(3));
				denomination.setAmount(resultSet.getDouble(4));
				coupon.setDenomination(denomination);
				list.add(coupon);
			}
    	}catch(SQLException exception) {
    		exception.printStackTrace();
    	}
    	finally {
    		DBUtility.close(preparedStatement,resultSet);
    	}
    	return list;
    }
    
    //list of coupons based on customer id
    public List<CustomerCoupon> getCustomerCoupons(int customerId) {
    final String QUERY = "select cc.coupon_id, cc.validity_date, cc.month, cc.year, d.amount from customer_coupons cc, coupon c, denomination d where c.coupon_id = cc.coupon_id and d.id = c.denomination_id and cc.customer_id = ?";
    	ResultSet resultSet = null;
    	List<CustomerCoupon> list = new ArrayList<CustomerCoupon>();
    	PreparedStatement preparedStatement = null;
    	try(Connection connection = DBUtility.getConnection()) {
    		preparedStatement = connection.prepareStatement(QUERY);
    		resultSet = preparedStatement.executeQuery();
    		preparedStatement.setInt(1, customerId);
			while(resultSet.next()) {
				CustomerCoupon customerCoupon = new CustomerCoupon();
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				Denomination denomination = new Denomination();
				//customerCoupon.setCoupon(coupon.getId());
				customerCoupon.setValidDate(DBUtility.UtilDateToSQLDate((resultSet.getDate(2))));
				customerCoupon.setMonth(resultSet.getString(3));
				customerCoupon.setYear(resultSet.getInt(4));
				denomination.setAmount(resultSet.getDouble(5));
				coupon.setDenomination(denomination);
				customerCoupon.setCoupon(coupon);
				list.add(customerCoupon);
			}
    	}catch(SQLException exception) {
    		exception.printStackTrace();
    	}
    	finally {
    		DBUtility.close(preparedStatement,resultSet);
    	}
        return list;
    }
}
