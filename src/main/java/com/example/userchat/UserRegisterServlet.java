package com.example.userchat;

import com.example.user.UserDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRegisterServlet extends HttpServlet {
    private static final long serialVersionUID=1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String userID=request.getParameter("userID");
        String userPassword1=request.getParameter("userPassword1");
        String userPassword2=request.getParameter("userPassword2");
        String userName=request.getParameter("userName");
        String userAge=request.getParameter("userAge");
        String userGender=request.getParameter("userGender");
        String userEmail=request.getParameter("userEmail");
        String userProfile=request.getParameter("userProfile");
        if(userID==null||userID.equals("")||userPassword1==null||userPassword1.equals("")
                || userPassword2==null||userPassword2.equals("")||userName==null||userName.equals("")
                || userAge==null||userAge.equals("")||userGender==null||userGender.equals("")
                ||userEmail==null||userEmail.equals("")
                ){
            request.getSession().setAttribute("messageType","오류메세지");
            request.getSession().setAttribute("messageContent","모든내용을 입력해주세요");
            response.sendRedirect("join.jsp");
            return;
        }
        if (!userPassword1.equals(userPassword2)){
            request.getSession().setAttribute("messageType","오류메세지");
            request.getSession().setAttribute("messageContent","비밀번호가 서로 다릅니다");
            response.sendRedirect("join.jsp");
            return;
        }
        int result=new UserDao().register(userID,userPassword1,userName,userAge,userName,userAge,userGender,userEmail,userProfile);
        if (result==1){
            request.getSession().setAttribute("messageType","성공메세지");
            request.getSession().setAttribute("messageContent","회원가입에 성공했습니다.");
        }
        else {
            request.getSession().setAttribute("messageType","실패메세지");
            request.getSession().setAttribute("messageContent","이미 존재하는 회원입니다!.");
        }
    }
}
