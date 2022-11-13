<%--
  Created by IntelliJ IDEA.
  User: launc
  Date: 2022-11-13
  Time: 오전 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ page import="java.sql.*,javax.sql.*,java.io.*,javax.naming.*,javax.naming.*"%>

</head>
<body>
<%
    InitialContext initCtx=new InitialContext();
    Context envContext=(Context) initCtx.lookup("java:comp/env");
    DataSource ds=(DataSource) envContext.lookup("jdbc/UserChat");
    Connection conn=ds.getConnection();
    Statement stmt=conn.createStatement();
    ResultSet rset=stmt.executeQuery("SELECT VERSION()");
    while (rset.next()){
        PrintWriter printWriter = response.getWriter();
        printWriter.println("MYSQL VERSION"+rset.getString("version()"));
    }
    rset.close();
    stmt.close();
    conn.close();
    initCtx.close();
%>
</body>
</html>
