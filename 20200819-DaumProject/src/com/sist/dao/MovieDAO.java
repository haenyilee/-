package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sist.manager.MovieVO;
import com.sist.manager.NewsVO;

public class MovieDAO {
	     // ���� 
		private Connection conn; // ����Ŭ ���� Ŭ����
		 // SQL������ ���� 
		private PreparedStatement ps; 
		 // ����Ŭ �ּ� ÷�� 
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		 // ���� �غ� 
		 // 1. ����̹� ��� 
		public MovieDAO()
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		}
		// ����/�ݱ� �ݺ� => ����� �ݺ��� ��� => �޼ҵ�� ó��
		public void getConnection()
		{
			try
			{
				conn=DriverManager.getConnection(URL,"hr","happy");
			}catch(Exception ex) {}
		}
		public void disConnection()
		{
			try
			{
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception ex) {}
		}
		
		
		// ���
		// 1. ���� => INSERT , UPDATE , DELETE => ������� �����ϱ� void * SELECT �� �˻� ����� ��ȯ�ϴϱ� void(x)
		public void movieInsert(MovieVO vo) {
			try {
				getConnection();
				String sql="INSERT INTO daum_movie VALUES( "
						+ "(SELECT NVL(MAX(no)+1,1) FROM daum_movie),?,?,?,?,?,?,?,?,?,?,?)";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, vo.getCateno());
				ps.setString(2, vo.getTitle());
				ps.setString(3, vo.getPoster());
				ps.setString(4, vo.getRegdate());
				ps.setString(5, vo.getGenre());
				ps.setString(6, vo.getGrade());
				ps.setString(7, vo.getActor());
				ps.setString(8, vo.getScore());
				ps.setString(9, vo.getDirector());
				ps.setString(10, vo.getStory());
				ps.setString(11, vo.getKey());
				
				ps.executeUpdate();
			
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			} 
			finally {
				disConnection();
			}
		}
		
		public void newsInsert(NewsVO vo) {
			try {
				getConnection();
				String sql="INSERT INTO daum_news VALUES(?,?,?,?,?)";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getTitle());
				ps.setString(2, vo.getPoster());
				ps.setString(3, vo.getLink());
				ps.setString(4, vo.getContent());
				ps.setString(5, vo.getAuthor());
				
				ps.executeUpdate();
			
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			} 
			finally {
				disConnection();
			}
		}
		
		public ArrayList<MovieVO> movieListData(int cno)
		{
			ArrayList<MovieVO> list = new ArrayList<MovieVO>();
			try {
				getConnection();
				String sql="SELECT poster,title,no FROM daum_movie WHERE cateno=?";
				
				ps=conn.prepareStatement(sql);
				ps.setInt(1, cno);
				
				
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					MovieVO vo = new MovieVO();
					vo.setPoster(rs.getString(1));
					vo.setTitle(rs.getString(2));
					vo.setNo(rs.getInt(3));
					
					list.add(vo);
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				disConnection();
			}
			return list;
		}
		
		public ArrayList<NewsVO> newsListData()
		{
			ArrayList<NewsVO> list = new ArrayList<NewsVO>();
			try {
				getConnection();
				String sql="SELECT title,poster,link,content,author FROM daum_news";
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next())
				{
					NewsVO vo = new NewsVO();
					vo.setTitle(rs.getString(1));
					vo.setPoster(rs.getString(2));
					vo.setLink(rs.getString(3));
					vo.setContent(rs.getString(4));
					vo.setAuthor(rs.getString(5));
					
					list.add(vo);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				disConnection();
			}
			
			return list;
		}
		
}
