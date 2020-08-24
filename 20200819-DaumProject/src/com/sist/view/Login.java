package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.MovieDAO;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<style type=text/css>");
		out.println(".row{");
		out.println("margin:0px auto;");
		out.println("width:300px;");
		out.println("}");
		
		out.println("</style>");

		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class=container>");
		out.println("<h1 class=text-center>�α���</h1>");
		out.println("<div class=row>");
		
		out.println("<form method=post action=Login>"); // ����Ʈ�� ȣ�� => ����Ʈ�� ���� �ִ� �ְ� ������? => �α��� => �α��ο��� ȣ��
		out.println("<table class=table>");
		
		out.println("<tr>");
		out.println("<td width=20% class=text-right>ID</td>");
		out.println("<td width=75%>");
		out.println("<input type=text size=15 class=input name=id>"); // name�̶� �Ӽ��� �ڹٿ��� �� �ޱ� ���� ����
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=20% class=text-right>Password</td>");
		out.println("<td width=75%>");
		out.println("<input type=password size=15 class=input name=pwd>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td colspan=2 class=text-center>");
		out.println("<input type=submit value=�α��� class=\"btn btn-sm btn-success\">");
		out.println("<input type=button value=��� class=\"btn btn-sm btn-danger\">");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</form>");
		
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id"); // ����ڰ� input�� id(name=id)�� response�� ���
		String pwd=request.getParameter("pwd");
//		System.out.println("ID:"+id);
//		System.out.println("PWD:"+pwd);
		MovieDAO dao = new MovieDAO();
		String result=dao.isLogin(id, pwd);
		
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out=response.getWriter();
		
		if(result.equals("NOID"))
		{
			//out.println("<>");
			out.println("<script>");
			out.println("alert(\"ID�� �������� �ʽ��ϴ�\");");
			out.println("history.back()");
			out.println("</script>");
		}
		else if(result.equals("NOPWD"))
		{
			out.println("<script>");
			out.println("alert(\"PWD�� �������� �ʽ��ϴ�\");");
			out.println("history.back()");
			out.println("</script>");

		}
		else {
			// id�� ������ �����ؾ��� & ���α׷� ����ñ��� ����
			HttpSession session = request.getSession(); // ��������
			session.setAttribute("id",id); // ������ ����
			
			
			// �α��εǸ� ������� �������� �̵�
			response.sendRedirect("MovieMain");
		}
		
	}

}