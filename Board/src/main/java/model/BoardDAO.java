package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

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
	
	public void insertBoard(BoardBean bean) {
		
		getCon();
		
		int ref= 0;
		int re_step = 1;
		int re_level = 1;
		
		try {
		String refsql = "select max(ref) from board ";
		pstmt = con.prepareStatement(refsql);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			
			ref = rs.getInt(1)+1;
			
			
		}
		
		String sql = "insert into board values(0,?,?,?,?,sysdate(),?,?,?,0,?)";
		pstmt= con.prepareStatement(sql);
		
		
		pstmt.setString(1, bean.getWriter());

		pstmt.setString(2, bean.getEmail());

		pstmt.setString(3, bean.getSubject());

		pstmt.setString(4, bean.getPassword());
		
		pstmt.setInt(5, ref);
		pstmt.setInt(6, re_step);
		pstmt.setInt(7, re_level);
		
		pstmt.setString(8, bean.getContent());
		
		pstmt.executeUpdate();
		
		con.close();
		
	}catch(Exception e) {
		
		e.printStackTrace();
	}
	
	}
	
		public Vector <BoardBean> getAllBoard(){
			
			Vector<BoardBean> v = new Vector<BoardBean>();
			getCon();
			
			
			try {
				
				String sql = "select * from board order by ref desc,re_step asc";
				
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					BoardBean bean = new BoardBean();
					bean.setNum(rs.getInt(1));
					bean.setWriter(rs.getString(2));
					bean.setEmail(rs.getString(3));
					bean.setSubject(rs.getString(4));
					bean.setPassword(rs.getString(5));
					bean.setReg_date(rs.getDate(6).toString());
					bean.setRef(rs.getInt(7));
					bean.setRe_step(rs.getInt(8));
					bean.setRe_level(rs.getInt(9));
					bean.setReadcount(rs.getInt(10));
					bean.setContent(rs.getString(11));
					
					v.add(bean);
					
				}
				con.close();
				
			}catch (Exception e) {

				e.printStackTrace();
			
			}
			
		return v;
		
		
	}
		
	//BoardInfo 하나의 게시글을 리턴하는 메소드 	
	public BoardBean getOneBoard(int num) {
		
		BoardBean bean = new BoardBean();
		
		getCon();
		
		try {
			
			String readsql = "update board set read_count = read_count+1 where num = ?";
			pstmt = con.prepareStatement(readsql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
			
			
			String sql = "select * from board where num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1,num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				
				
			}
			con.close();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			
		}
		
		return bean;
		
	}
	
	//답변글이 저장되는 메소드 
	public void reWriteBoard(BoardBean bean) {
		
		//부모글그룹과 글레벨 글스텝을 읽어드림 
		int ref = bean.getRef();
		int re_step = bean.getRe_step();
		int re_level = bean.getRe_level();
		
		getCon();
		
		try {
//////////////////////핵심 코드////////////////////////
			//부모글보다 큰 re_level의 값을 전부 1씩 증가시켜준다. 
			String levelsql = "update board set re_level = re_level+1 where ref=? and re_level>?";
			
			pstmt = con.prepareStatement(levelsql);
			
			pstmt.setInt(1, ref);
			pstmt.setInt(2, re_level);
			
			pstmt.executeUpdate();
			//답변글 데이터를 저장 
			String sql = "insert into board values(0,?,?,?,?,sysdate(),?,?,?,0,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bean.getWriter());

			pstmt.setString(2, bean.getEmail());

			pstmt.setString(3, bean.getSubject());

			pstmt.setString(4, bean.getPassword());
			
			pstmt.setInt(5, ref);
			
			pstmt.setInt(6, re_step+1);
			
			pstmt.setInt(7, re_level+1);
			
			pstmt.setString(8,bean.getContent());
			
			pstmt.executeUpdate();
			
			con.close();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			
		}
		
		
		
	}
	
	//boardUpdate용 하나의 게시글을 리턴 
	
	public BoardBean getOneUpdateBoard(int num) {
		
		BoardBean bean = new BoardBean();
		
		getCon();
		
		try {
			

			String sql = "select * from board where num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1,num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				
				
			}
			con.close();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			
		}
		
		return bean;
		
	}
	
	//update와 delete시 필요한 패스워드 값을 리턴해주는 메소드 
	public String getPass(int num) {
		//리턴할 변수 객체 선언
		String pass = "";
		getCon();
		try {
			
			//쿼리 준비 
			String sql = "select password from board where num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			//패스워드값을 저장 
			if(rs.next()) {
				
				pass = rs.getString(1);
				
			}
			//자원반납 
			con.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pass;
		
	}
	
	
	public void updateBoard(BoardBean bean) {
		
		
		getCon();
		try {
			String sql = "update board set subject = ?,content = ? where num = ?";
			pstmt = con.prepareStatement(sql);
			//값을 대입 
			pstmt.setString(1, bean.getSubject());

			pstmt.setString(2, bean.getContent());

			pstmt.setInt(3, bean.getNum());
			
			pstmt.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
