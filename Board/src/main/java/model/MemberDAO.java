package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void getCon() {
		
		try {
			
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource)envctx.lookup("jdbc/pool");
			con = ds.getConnection();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
	
public int join(MemberBean bean ) {
	getCon();
	String SQL = "insert into member values(0,?,?,?)";
	try {
		
		
		
	pstmt = con.prepareStatement(SQL);
	pstmt.setString(1, bean.getId());
	pstmt.setString(2,bean.getPassword());
	pstmt.setString(3,bean.getEmail());
		
		return pstmt.executeUpdate();
	}catch(Exception e) {
		
		e.printStackTrace();
		
	}
	return -1;	
}	
		
		
	
public int login(String userID,String userPassword) {
	
	getCon();
	
	String SQL = "select password from member where id = ?";
	
	try {
		
		pstmt = con.prepareStatement(SQL);
		pstmt.setString(1, userID);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
		if(rs.getString(1).equals(userPassword)) 
			return 1;
		else 
			return 0;
		
		}
		return -1;
	}catch(Exception e) {
		
		
		e.printStackTrace();
		
		
	}
	return -2;
}
	
}


	
	
	
	
	
	

