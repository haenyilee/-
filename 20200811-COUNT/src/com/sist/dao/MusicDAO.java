package com.sist.dao;
// DATA ACCESS
import java.util.*;
import java.sql.*;

public class MusicDAO {
	// ����Ŭ ����
	private Connection conn;
	
	// ��������
	private PreparedStatement ps;
	
	// ����Ŭ �ּ�
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// ����̹� ��ġ
	public MusicDAO()
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
	
	// ���� �������� => 200���� ���� ������ ������ ����
	public ArrayList<MusicVO> musicAllData(){
		ArrayList<MusicVO> list = new ArrayList<MusicVO>();
		
		try {
			// ����Ŭ ����
			getConnection();
			
			// sql���� ����
			String sql="SELECT mno,poster,title,singer,album FROM genie_music ORDER BY mno";
			
			ps=conn.prepareStatement(sql); // excuteQuery()������ ���� �ȵ� ������, ���常 ���� ����(����ġ�� ��)
			
			// ����� �ޱ�
			ResultSet rs= ps.executeQuery(); // �������� rs�� �� ��Ƶл�Ȳ
			while(rs.next()) // �ؿ������� ���� �ö󰡸鼭 ������ �������� previous()
			{
				// 200���� �޸𸮿� ���� ����
				// ArrayList�� �� ä���
				MusicVO vo = new MusicVO();
				vo.setMno(rs.getInt(1));// vo.setMno(mno);
				vo.setPoster(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setSinger(rs.getString(4));
				vo.setAlbum(rs.getString(5));
				
				// 200���� ��Ƽ� �������� ����
				list.add(vo);
			}
			rs.close();
			
			
		} catch (Exception e) {
			// ���� ���� Ȯ��
			System.out.println(e.getMessage());
		} finally {
			// ���� ����
			disConnection();
		}
		
		return list;
	}
	
	// �󼼺���
	public MusicVO musicDetailData(int mno)
	{
		MusicVO vo = new MusicVO();
		
		try {
			getConnection();
			String sql="SELECT mno,title,singer,album,poster,key FROM genie_music WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			
			rs.next();
			// ���� ä���
			vo.setMno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setSinger(rs.getString(3));
			vo.setAlbum(rs.getString(4));
			vo.setPoster(rs.getString(5));
			vo.setKey(rs.getString(6));
			
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			disConnection();
		}
		
		return vo;
	}
	

}
