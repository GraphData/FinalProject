package com.GraphData;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.GraphData.Model.AccountModel;
import com.GraphData.Model.AccountProfile;
import com.neo4j.Utils.NewsfeedHelper;
import com.neo4j.Utils.UserHelper;

/**
 * Servlet implementation class UserSearch
 */
@WebServlet("/UserSearch")
public class UserSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String name = request.getParameter("name");
		List<AccountModel> accounts = UserHelper.searchUser(name);
		System.out.println(name);
		
		System.out.println("search success:" + accounts.size());
		request.setAttribute("accounts", accounts);
		request.setAttribute("follows", UserHelper.getFollowList(((AccountModel)session.getAttribute("account")).getUsername()));   
        request.setAttribute("news", NewsfeedHelper.getNewsfeedList(((AccountModel)session.getAttribute("account")).getUsername()));
		String search_suc = "home.jsp";
		request.getRequestDispatcher(search_suc).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
