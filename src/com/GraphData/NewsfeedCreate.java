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
 * Servlet implementation class NewsfeedCreate
 */
@WebServlet("/NewsfeedCreate")
public class NewsfeedCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsfeedCreate() {
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
		String content = request.getParameter("content");
		
		NewsfeedHelper.PublishNewsfeed(account.getUsername(), content);
		System.out.println("success create news");
		request.setAttribute("follows", UserHelper.getFollowList(account.getUsername()));   
        request.setAttribute("news", NewsfeedHelper.getNewsfeedList(account.getUsername()));
        //session.setAttribute("account", account);
        String login_suc = "home.jsp";
        request.getRequestDispatcher(login_suc).forward(request, response);
        return;
	}

}
