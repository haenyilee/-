package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public BoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public ArrayList<BoardVO> boardAllData(int page){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnection();
			
			// �� �������� 10���� �����ڴ�!
			int rowSize=5;
			// SQL�������忡�� : ~ BETWEEN start AND end ~
			// int start = (page*rowSize)-(rowSize-1);
			int start = rowSize*(page-1) + 1;
	
			int end = page * rowSize;
			
			String sql="SELECT no,subject,name,regdate,hit,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
					+ "FROM (SELECT no,subject,name,regdate,hit "
					+ "FROM jsp_board ORDER BY no DESC)) "
					+ "WHERE num BETWEEN ? AND ?"; 
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				BoardVO vo =new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.getMessage();
		} finally {
			disConnection();
		}
		
		return list;
		
	}
	
	
	// �� ������ ��������
	public int boardTotalPage()
	{
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/5.0) FROM jsp_board";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			disConnection();
		}
		return total;
	}
	

}
