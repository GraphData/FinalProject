package com.GraphData;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.GraphData.Model.AccountModel;
import com.GraphData.Model.AccountProfile;
import com.neo4j.Utils.UserHelper;

/**
 * Servlet implementation class UserFollow
 */
@WebServlet("/UserFollow")
public class UserFollow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFollow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		AccountModel account = (AccountModel) session.getAttribute("account");
		String followPeople = request.getParameter("name");
		System.out.println("fellow people:" + followPeople);
		UserHelper.followPeople(account.getUsername(), followPeople);
		
		String follow_suc = "home.jsp";
		request.getRequestDispatcher(follow_suc).forward(request, response);
	}

}
