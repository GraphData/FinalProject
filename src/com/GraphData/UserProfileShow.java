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
 * Servlet implementation class UserProfileShow
 */
@WebServlet("/UserProfileShow")
public class UserProfileShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileShow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		AccountProfile account = new AccountProfile();
		AccountModel accountModel = (AccountModel) session.getAttribute("account");
		System.out.println("查看个人资料:用户名:" + accountModel.getUsername());
		
		AccountProfile accountProfile = UserHelper.getProfile(accountModel.getUsername());
		accountProfile.setUsername(accountModel.getUsername());
		accountProfile.setPassword(accountModel.getPassword());
		request.setAttribute("profile", accountProfile);
		request.setAttribute("doubi", "doubi3");
		String search_suc = "profile.jsp";
		//session.setAttribute("account", accountModel);
		request.getRequestDispatcher(search_suc).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
