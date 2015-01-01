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
 * Servlet implementation class UserProfile
 */
@WebServlet("/UserProfile")
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfile() {
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
		System.out.println("fillprofile");
		System.out.println(accountModel.getPassword());
		String birthday = request.getParameter("birthday");
		String college = request.getParameter("college");
		String major = request.getParameter("major");
		String hobby = request.getParameter("hobby");
		
		account.setBirthday(birthday);
		account.setCollege(college);
		account.setMajor(major);
		account.setHobby(hobby);
		
		UserHelper userHelper = new UserHelper();
		userHelper.createUserProfile(account);
		
		if(true)
		{	
			//System.out.println("success");
			session.setAttribute("account", account);
			String register_suc = "home.jsp";
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
