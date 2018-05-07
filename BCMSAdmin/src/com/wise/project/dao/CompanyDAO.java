package com.wise.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.project.dto.Company;
import com.wise.project.util.DBUtility;

public class CompanyDAO {
	public static void main(String [] args) {
		
	}
	
	public int add(Company company) {
		int status = 0;
		final String QUERY = "insert into company(id, name, doe, dor, password, email, surveyNo, landmark, location, city, state, country, pincode) values(?, ?, ?, ?, ?, ?, ?, ? ,? ,?, ?, ?, ?)";
		try(Connection connection = DBUtility.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, company.getId());
			preparedStatement.setString(2, company.getName());
			preparedStatement.setDate(3, DBUtility.UtilDateToSQLDate(company.getDoe()));
			preparedStatement.setDate(4, DBUtility.UtilDateToSQLDate(company.getDor()));
			preparedStatement.setString(5, company.getPassword());
			preparedStatement.setString(6, company.getEmail());
			preparedStatement.setInt(7, company.getSurveyNo());
			preparedStatement.setString(8, company.getLandmark());
			preparedStatement.setString(9, company.getLocation());
			preparedStatement.setString(10, company.getCity());
			preparedStatement.setString(11, company.getState());
			preparedStatement.setString(12, company.getCountry());
			preparedStatement.setInt(13, company.getPincode());
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	//return company of a particular id
	public Company get(int companyId){
		ResultSet resultSet = null;
		Company company = new Company();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from company where id = ?";
		try (Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, companyId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				company.setId(resultSet.getInt(1));
				company.setName(resultSet.getString(2));
				company.setDoe(DBUtility.SQLDateToUtilDate(resultSet.getDate(3)));
				company.setDor(DBUtility.SQLDateToUtilDate(resultSet.getDate(4)));
				company.setEmail(resultSet.getString(5));
				company.setPassword(resultSet.getString(6));
				company.setLandmark(resultSet.getString(7));
				company.setCity(resultSet.getString(8));
				company.setLocation(resultSet.getString(9));
				company.setCountry(resultSet.getString(10));
				company.setSurveyNo(resultSet.getInt(11));
				company.setPincode(resultSet.getInt(12));
				company.setState(resultSet.getString(13));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet, preparedStatement);
		}
		return company;
	}
	
	// returns all the companies
	public List<Company> get() {
		List<Company> list = new ArrayList<Company>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		final String QUERY = "select id,name,doe,dor,password,email,surveyNo,landmark,location,city,state,country,pincode from company";
		try (Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Company company = new Company();
				company.setId(resultSet.getInt(1));
				company.setName(resultSet.getString(2));
				company.setDoe(DBUtility.SQLDateToUtilDate(resultSet.getDate(3)));
				company.setDor(DBUtility.SQLDateToUtilDate(resultSet.getDate(4)));
				company.setPassword(resultSet.getString(5));
				company.setEmail(resultSet.getString(6));
				company.setSurveyNo(resultSet.getInt(7));
				company.setLandmark(resultSet.getString(8));
				company.setLocation(resultSet.getString(9));
				company.setCity(resultSet.getString(10));
				company.setState(resultSet.getString(11));
				company.setCountry(resultSet.getString(12));
				company.setPincode(resultSet.getInt(13));
				list.add(company);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet, preparedStatement);
		}
		return list;
	}
}

