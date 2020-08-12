package com.sist.join;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	// doGet�Լ��� run()�Լ�? ������ �Լ��� ����... doGet�Լ��� �� �������Լ�... ������ϱ� �ѻ���� �ϳ��� ���� ����
	// doGet�Լ��� �����ϴ� �Լ��ϱ� �̿��ڸ��� ���� �۵��ؾ���
	// ��� �̿��ڰ� ������ �����͸� ������ ������ ���� �۵��� �� �ֵ���....
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=EUC-KR");
		// �����ϴ� �������  out�� �ٸ��� ������ 
		PrintWriter out=response.getWriter();
		
		EmpDAO dao=new EmpDAO();
		
		ArrayList<EmpVO> list = dao.empDeptJoinData();
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>�������</h1>");
		out.println("<table border=1 width=700>");
		
		out.println("<tr>");
		out.println("<th>���</th>");
		out.println("<th>�̸�</th>");
		out.println("<th>����</th>");
		out.println("<th>�Ի���</th>");
		out.println("<th>�޿�</th>");
		out.println("<th>�μ���ȣ</th>");	
		
		out.println("<th>�μ���</th>");
		out.println("<th>�ٹ���</th>");
		out.println("</tr>");
		
		
		// 14���� row�� ���� ������ ����ϱ�
		for(EmpVO vo:list)
		{
			// EmpVO�� ��
			out.println("<tr>");
			out.println("<th>"+vo.getEmpno()+"</th>");
			out.println("<th>"+vo.getEname()+"</th>");
			out.println("<th>"+vo.getJob()+"</th>");
			out.println("<th>"+vo.getHiredate()+"</th>");
			out.println("<th>"+vo.getSal()+"</th>");
			out.println("<th>"+vo.getEmpno()+"</th>");
			
			// DeptVO�� ��
			out.println("<th>"+vo.getDvo().getDname()+"</th>");
			out.println("<th>"+vo.getDvo().getLoc()+"</th>");
			out.println("</tr>");
		}
		
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
