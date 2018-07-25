package tiaomao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tianmao.dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//System.out.println("���յ����Կͻ��˵���Ϣ");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String psw = new UserDao().findUserName(username);
		if(psw == null) {
			request.setAttribute("msg", "û������û�");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		if(psw != null && !psw.equals(password)) {
			request.setAttribute("msg", "���������������������");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		if(psw.equals(password)) {//�û���½�ɹ�
//			request.setAttribute("msg", "�û�:"+username+",��ӭ����");
//			request.getRequestDispatcher("/index.html").forward(request, response);
			HttpSession session = request.getSession();
			session.setAttribute("loginName", username);
			response.sendRedirect("IndexServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
