package com.GraphData;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.GraphData.Model.AccountModel;
import com.neo4j.Utils.UserHelper;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		AccountModel account = new AccountModel();
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		if(pwd.trim().equals(pwd2.trim()))
		{
			account.setUsername(username);
			account.setPassword(pwd);
			UserHelper userHelper = new UserHelper();
			userHelper.createNewUser(username, pwd);
			
			System.out.println("success");
			session.setAttribute("account", account);
			String register_suc = "success.jsp";
			response.sendRedirect(register_suc);
			return;
		}
		else
		{
			String register_fail = "fail.jsp";
			response.sendRedirect(register_fail);
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
