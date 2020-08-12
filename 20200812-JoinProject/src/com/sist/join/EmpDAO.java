package com.sist.join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	// EmpVO�� DeptVO�� ��ü�� ���� ArrayList ����
	public ArrayList<EmpVO> empDeptJoinData()
	{
		ArrayList<EmpVO> list=
				new ArrayList<EmpVO>();
		
		// ���� �ۼ� & ���� & �÷��� ������ ���� ����
		try {
			getConnection();
			
			// ���� �ۼ�
			// �� �Ʒ������� ��������?
			// String sql="SELECT empno,ename,job,hiredate,TO_CHAR(sal,'$9,999'),emp.deptno,dname,loc FROM emp,dept WHERE emp.deptno=dept.deptno";
			String sql="SELECT empno,ename,job,hiredate,sal,emp.deptno,dname,loc FROM emp,dept WHERE emp.deptno=dept.deptno";
					// �ڹٿ����� ���� �� �ڿ� ; �ۼ��ϸ� ������
			
			// ���� ����
			ps=conn.prepareStatement(sql);
			
			// ���� ���� �� ���� ��������� rs �޸𸮿� �޾Ƶα�
			ResultSet rs = ps.executeQuery();
			
			// EmpVO�� �Ѱ��� ���� ���� �����ϰ� �����Ƿ�, ���� ������ŭ �ݺ��ؼ� �� �࿡ ������ �־��ֱ�
			while(rs.next())
			{
				EmpVO vo=new EmpVO();
				
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				
				// DeptVO�� ��ü�鿡 rs�� ����ֱ�
				vo.setSal(rs.getInt(5));
				vo.setDeptno(rs.getInt(6));
				vo.getDvo().setDname(rs.getString(7));
				vo.getDvo().setLoc(rs.getString(8));
				
				// ����Ʈ vo�� �� �߰�
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