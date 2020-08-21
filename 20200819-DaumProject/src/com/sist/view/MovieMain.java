package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*; // arraylist (������ �迭)
import com.sist.dao.*; // movieDAO
import com.sist.manager.*; // movieVO

@WebServlet("/MovieMain")
public class MovieMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ��Ĺ�� ������ �ְ� ���ξȿ� doget�Լ��� �ִ� ����...doget���� ��µǴ� HTML�� ���� �޸𸮿� ����ǰ� ����� ���� ���� �о ���
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * HttpServletRequest request
		 * 	=> ������� ��û��
		 * 	=> ������ ���� (������� IP)
		 * HttpServletResponse response
		 */
		
		// ������ �غ� (HTML , XML)
		//response.setContentType("text/xml;charset=EUC-KR");
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out = response.getWriter();
		// out.println("<?xml version=\"1.0\" encoding=\"EUC-KR\"?>");
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("</head>");
		out.println("<body>");
		// ��ü �׵θ� : container
		// �׵θ� ���� ū�ܶ� : row
		out.println("<div class=container>");
		out.println("<h1 class=text-center>��ȭ���</h1>");
		
		out.println("<div class=row>");
		out.println("<a href=MovieMain?no=1 class=\"btn btn-sm btn-primary\">����󿵿�ȭ</a>");
		out.println("<a href=MovieMain?no=2 class=\"btn btn-sm btn-danger\">����������ȭ</a>");
		out.println("<a href=MovieMain?no=3 class=\"btn btn-sm btn-info\">�ڽ����ǽ�(�ְ�)</a>");
		out.println("<a href=MovieMain?no=4 class=\"btn btn-sm btn-warning\">�ڽ����ǽ�(����)</a>");
		out.println("<a href=MovieMain?no=5 class=\"btn btn-sm btn-success\">�ڽ����ǽ�(����)</a>");
		out.println("<a href=NewsMain class=\"btn btn-sm btn-success\">��������</a>");
		out.println("</div>");
		
		out.println("<div class=row>");
		String no=request.getParameter("no");
		if(no==null)
			no="1";
		
		
		MovieDAO dao = new MovieDAO();
		
		
		ArrayList<MovieVO> list = dao.movieListData(Integer.parseInt(no));
		for(MovieVO vo:list)
		{
			out.println("<div class=\"col-md-3\">");
			out.println("<div class=\"thumbnail\">");
			out.println("<a href=MovieDetail?no="+vo.getNo()+">");
			out.println("<img src="+vo.getPoster()+" alt=\"Lights\" style=\"width:100%\">");
			out.println("<div class=\"caption\">");
			String str=vo.getTitle();
			if(str.length()>18)
			{
				str=str.substring(0,18)+"...";
			}
			out.println("<p>"+str+"</p>");
			out.println("</div>");
			out.println("</a>");
			out.println("</div>");
			out.println("</div>");
		}

		
		/*
		  <div class="col-md-3"> 		//=> col-md-���� : ������ ���� 12�� �Ѿ�� �����ٿ� ��µ�
		    <div class="thumbnail">
		      <a href="/w3images/lights.jpg">
		        <img src="/w3images/lights.jpg" alt="Lights" style="width:100%">
		        <div class="caption">
		          <p>Lorem ipsum...</p>
		        </div>
		      </a>
		    </div>
		  </div>
		*/
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		/*
		 * out.println("<>");
		 */
	}

}
