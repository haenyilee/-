package com.sist.dao;
import java.sql.*;
import java.util.*;
import java.util.Date;
public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// ����̹� ��ġ
	public EmpDAO()
	{
		// �������� ���� (1) ��������� �ʱ�ȭ (2) ��Ʈ��ũ ���� ���� -> �������ڸ��� ���� ��������
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// ����
	public void getConnection() {
		try {
			// ���� ��ɾ� : conn hr/happy
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {}
	}
	
	// ���� ����
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {}
	}
	
	public ArrayList<EmpVO> empAllData(){
		
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		
		try {			
			getConnection();
			
			// sql ���� �ڿ� ; ������ �ȵ�!!!!
			String sql="SELECT * FROM emp";			
			ps=conn.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			
			/*
			private int empno;
			private String ename;
			private String job;
			private int mgr; // ���
			private Date hiredate;
			private double sal;
			private double comm;
			private int deptno;
			 */
			while(rs.next())
			{
				EmpVO vo= new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setMgr(rs.getInt(4));
				vo.setHiredate(rs.getDate(5));
				vo.setSal(rs.getDouble(6));
				vo.setComm(rs.getDouble(7));
				vo.setDeptno(rs.getInt(8));
				
				list.add(vo);			
			}
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			disConnection();
		}
		
		
		
		return list;
		
		
	}

}
