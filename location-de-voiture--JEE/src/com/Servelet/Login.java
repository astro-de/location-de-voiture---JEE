package com.Servelet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.implementations.UserDaoImp;
import com.dao.interfaces.UserDao;
import com.modules.User;

/**
 * Servlet implementation class Login
 */

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userdao= new UserDaoImp();
	String forward="/WEB-INF/login.jsp";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// test valid

		request.getRequestDispatcher("/WEB-INF/login.jsp?email=").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login");
		String  email=request.getParameter("email");
		String  password=request.getParameter("password");
		Boolean b=false;
		forward="/WEB-INF/login.jsp?error=e&email="+email;
		try {
			b=userdao.findSpecificByEmail(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(b){
			System.out.println("existe");
			User user=new User();
			try {
				user=userdao.findSpecificUserByEmail(email);
				if(user.getPassword().equals(password)) {
					System.out.println("true");
				forward="/WEB-INF/done.jsp";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			
		}
		request.getRequestDispatcher(forward).forward(request, response);

	}

}
