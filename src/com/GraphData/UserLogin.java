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
 * Servlet implementation class AccountBean
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountModel account = new AccountModel();
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        account.setPassword(pwd);
        account.setUsername(username);
        if(UserHelper.checkPassword(username, pwd))
        {
        	System.out.println("success login");
            session.setAttribute("account", account);
            
            request.setAttribute("follows", UserHelper.getFollowList(username));   
            request.setAttribute("news", NewsfeedHelper.getNewsfeedList(username));
            
            String login_suc = "home.jsp";
            request.getRequestDispatcher(login_suc).forward(request, response);
            return;
        }

        String login_fail = "fail.jsp";
        response.sendRedirect(login_fail);
        return;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
