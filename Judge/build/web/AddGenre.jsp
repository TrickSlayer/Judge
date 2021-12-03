<%-- 
    Document   : AddGenre
    Created on : Oct 9, 2021, 10:38:41 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Judge</title>
        <link rel="icon" href="images/Judge-icon.png">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/common.css" rel="stylesheet">
        <style>
            .body form{
                padding-top: 80px;
                padding-bottom: 90px;
            }
        </style>
        <%
            User user = (User) session.getAttribute("account");
            if (user == null || user.getPower()<5) {
                response.sendRedirect("home");
            }
        %>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="container">
            <div class="body">
                <h2>Add Genre</h2> 
                <h4 class="erro">${requestScope.error}</h4>
                <form action="add-genre" method="post">
                    <input class="Info" name="action" value="${param.action}">
                    <div class="form-group">
                        <label>Genre</label> 
                        <input type="text" 
                               class="form-control" name="Genre" placeholder="Enter Genre">
                    </div>
                    <div class="form-group">
                        <label>Description</label> 
                        <input type="text" 
                               class="form-control" name="Description" placeholder="Enter Description">
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                    <button type="reset" class="btn btn-primary">Cancel</button>
                </form>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
