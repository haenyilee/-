package com.sist.dao;
import java.sql.*;
import java.util.*;

public class MyDAO {
	// ���ᰴü
	private Connection conn;
	
	// SQL���� ��ü
	private PreparedStatement ps;
	
	// URL����Ŭ �ּ� 
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// ����̹� ���
	public MyDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// ����Ŭ ���� �޼ҵ� : ��ɾ�(conn)
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");			
		} catch (Exception e) {}
	}
	
	// ����Ŭ ���� ���� �޼ҵ� : ��ɾ�(exit)
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {}
	}
	
	// JDBC(���üҽ�) => DBCP(Ŀ�ؼǰ���) => ORM(MyBatis,Hibernate)
	// ���
	public String isLogin(String ename,int empno)
	{
		String result="";
		try {
			getConnection();
			// sql���� ����
			String sql="SELECT COUNT(*) FROM emp WHERE ename=?"; // ���� �ִ��� ������ Ȯ���Ϸ��� COUNT(*)����ϱ�!
			ps=conn.prepareStatement(sql);
			
			// ?(����ǥ)�� �� ä���
			ps.setString(1, ename);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			int count=rs.getInt(1); // ������ 0, ������ 1			
			rs.close();
			if(count==0) // ��� �̸��� �������� ������
			{
				result="NONAME";
			}
			else // ����̸��� ����
			{
				sql="SELECT empno FROM emp WHERE ename=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, ename);
				rs=ps.executeQuery();
				rs.next(); // ��� �����Ͱ� �ִ� ��ġ�� Ŀ����ġ�� �̵�
				int db_empno=rs.getInt(1);
				rs.close();
				
				if(empno==db_empno) // �α���
				{
					result=ename;
				}
				else // ����� Ʋ�� ����
				{
					result="NOSABUN";
				}
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			disConnection();
		}
		
		return result;
	}

}
