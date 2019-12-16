package org.yyb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yyb.dao.LoginDao;
import org.yyb.entity.Login;
//控制器层：接收view请求，并分发给model处理
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理登录
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("uname");
		String psw = request.getParameter("upwd");
		
		Login login = new Login(name,psw);//用户名和密码
		
		//调用模型层的登录功能
		int result = LoginDao.login(login);
		if (result>0) {
			response.sendRedirect("welcome.jsp");
		}else {
			response.sendRedirect("login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
