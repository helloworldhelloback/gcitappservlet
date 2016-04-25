package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO {
	
	private Connection conn;
	private Integer pageNo;
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	private Integer pageSize;
	
	public BaseDAO(Connection conn) {
		this.conn = conn;
	}
	
	public Connection getConnection(){
		return conn;
	}
	/*
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		return DriverManager.getConnection(url, user, pass);
	}
	*/
	public void save(String query, Object[] vals) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		int count = 1;
		
		if(vals!=null){
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
	}
	
	public Integer getCount(String query) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()){
			return rs.getInt(1);
		}else{
			return 0;
		}
	}
	
	public List<?> readAll(String query, Object[] vals) throws SQLException, ClassNotFoundException{
		Connection conn = getConnection();
		if (pageNo != null && pageNo >0) {
			query+=" LIMIT "+(pageNo - 1)*10+", 10";
		}
		PreparedStatement pstmt = conn.prepareStatement(query);
		int count = 1;
		
		if(vals!=null){
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		
		return extractData(rs);
	}

	public Integer saveWithID(String query, Object[] vals) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		int count = 1;
		
		if(vals!=null){
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		
		if(rs.next()){
			return rs.getInt(1);
		}else{
			return null;
		}
	}
	public List<?> readFirstLevel(String query, Object[] vals) throws SQLException, ClassNotFoundException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		int count = 1;
		
		if(vals!=null){
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		
		return extractDataFirstLevel(rs);
	}

	public List<?> readSpecial(String query, Object[] vals) throws SQLException, ClassNotFoundException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		int count = 1;
		
		if(vals!=null){
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();		
		return extractData(rs);
	}

	public abstract List<?> extractDataFirstLevel(ResultSet rs) throws SQLException;
	public abstract List<?> extractData(ResultSet rs) throws SQLException, ClassNotFoundException;
}
