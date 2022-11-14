package com.example.userchat;

import com.example.user.UserDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserRegisterCheckServlet extends HttpServlet {
    private static final long serialVersionUID=1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=UTF-8");
     String userID=request.getParameter("userID");//Userid객체를 가져옴
     response.getWriter().write(new UserDao().registerCheck(userID)+"");
    }
}
