package com.example.user;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    DataSource dataSource;

    public UserDao(){
        try {
            InitialContext initCtx=new InitialContext();
            Context envContext=(Context) initCtx.lookup("java:comp/env");
            dataSource =(DataSource) envContext.lookup("jdbc/UserChat");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int login(String userID,String userPassword){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        String SQL="SELECT * FROM USER WHERE userID =?";
        try {
            conn=dataSource.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1,userID);
            if(rs.next()){
                if (rs.getString("userPassowrd").equals(userPassword)){
                    return 1;//로그인 성공 반환!
                }
                return 2;//비밀번호 틀림 반환!
            }else {
                return 0;//해당 사용자 존재X
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return -1;
        //데이터 베이스 오류 반환!
    }
    public int registerCheck(String userID){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        String SQL="SELECT * FROM USER WHERE userID =?";
        try {
            conn=dataSource.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1,userID);
            if(rs.next()||userID.equals("")){
                return 0;//아이디 존재함
            }else {
                return 1;//가입 가능한 회원아이디
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return -1;
        //데이터 베이스 오류 반환!
    }
    public int register(String userID, String userPassword, String userName,
                        String userAge, String userGender, String userEmail, String userProfile, String email, String profile){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        String SQL="INSERT INTO user VALUES (?,?,?,?,?,?,?)";
        try {
            conn=dataSource.getConnection();
            pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1,userID);
            pstmt.setString(2,userPassword);
            pstmt.setString(3,userName);
            pstmt.setInt(4,Integer.parseInt(userAge));
            pstmt.setString(5,userGender);
            pstmt.setString(6,userEmail);
            pstmt.setString(7,userProfile);
            if(rs.next()||userID.equals("")){
                return 0;//아이디 존재함
            }else {
                return 1;//가입 가능한 회원아이디
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return -1;
        //데이터 베이스 오류 반환!
    }
}
