<%-- 
    Document   : EditUser
    Created on : Oct 21, 2021, 6:41:49 PM
    Author     : Acer
--%>

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
                <h2>Edit User</h2>
                <h4 class="erro">${requestScope.error}</h4>
                <form class="form" action="edit-user" method="post" enctype="multipart/form-data">
                    <c:set value="${sessionScope.account}" var="u"></c:set>
                    <div class="form-group">
                        <label>User Name</label> 
                        <input type="text" 
                               class="form-control" name="Username" placeholder="Enter user name" value="${u.getUserName()}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Password</label> 
                        <input type="password" 
                               class="form-control" name="Password" placeholder="Enter password" value="${u.getPassword()}">
                    </div>
                    <div class="form-group">
                        <label>Avatar</label> <br/>
                        <input type="file" 
                               class="form-control" name="Avata" placeholder="Enter avatar">
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                    <button type="reset" class="btn btn-primary">Cancel</button>
                </form>
            </div>
        </div>
        <!--Kết thúc contianer-->
        <!--nội dung footer-->
        <%@include file="footer.jsp" %>
        <!--Thư viên javascript của Bootstrap-->
        <script src="js/Jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>

</html>
