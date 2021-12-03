<%-- 
    Document   : Login
    Created on : Oct 20, 2021, 11:32:09 PM
    Author     : Acer
--%>

<%@page import="Source.SendMail"%>
<%@page import="dal.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Judge</title>
        <link rel="icon" href="images/Judge-icon.png">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/common.css" rel="stylesheet">
        <link href="css/liststyle.css" rel="stylesheet">
        <!-- Bootstrap -->
    </head>

    <body>

        <!--Nội dung Menu-->
        <%@include file="menu.jsp" %>
        <div class="container">
            <div class="body">
                <h2>Login</h2>
                <h4 class="erro">${requestScope.error}</h4>
                <form class="form" action="login" method="post">
                    <div class="form-group">
                        <label>User Name</label> 
                        <input type="text" 
                               class="form-control" name="Username" placeholder="Enter user name">
                    </div>
                    <div class="form-group">
                        <label>Password</label> 
                        <input type="password" 
                               class="form-control" name="Password" placeholder="Enter password">
                    </div>
                    <button type="submit" name="action" value="login" class="btn btn-primary">Login</button>
                    <button  type="submit" class="btn btn-primary" name="action" value="forgot">Forgot password</button>
                </form>
            </div>
        </div>
        <!--Kết thúc contianer-->
        <!--nội dung footer-->
        <%@include file="footer.jsp" %>
        <!--Thư viên javascript của Bootstrap-->
        <script src="js/Jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            <%  String pop = (String) request.getAttribute("pop");
                if (pop != null) {
            %>
            alert(" <%= pop%>");
            <%
                }
            %>
        </script>
    </body>

</html>
