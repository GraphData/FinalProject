package com.GraphData;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.GraphData.Model.AccountModel;
import com.neo4j.Utils.NewsfeedHelper;
import com.neo4j.Utils.UserHelper;

/**
 * Servlet implementation class UserCancelFollow
 */
@WebServlet("/UserCancelFollow")
public class UserCancelFollow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCancelFollow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("here");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		AccountModel account = (AccountModel) session.getAttribute("account");
		String cancelFollowPeople = request.getParameter("name");
		System.out.println("cancel fellow people:" + cancelFollowPeople);
		UserHelper.cancelFollowPeople(account.getUsername(), cancelFollowPeople);
		
		request.setAttribute("follows", UserHelper.getFollowList(account.getUsername()));   
        request.setAttribute("news", NewsfeedHelper.getNewsfeedList(account.getUsername()));
		String follow_suc = "home.jsp";
		request.getRequestDispatcher(follow_suc).forward(request, response);
	}

}
