<%-- 
    Document   : EditActor
    Created on : Oct 13, 2021, 10:36:44 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="css/addActorRole.css" rel="stylesheet">
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
                <h2>Edit Actor</h2>
                <c:set var="a" value="${requestScope.actor}"></c:set> 
                    <form action="edit-actor" method="post">
                        <div class="input">
                            <input class="Info" name="id" value="${a.getActorID()}">
                            <div class="form-group">
                                <label>Name</label> 
                                <input type="text" value="${a.getName()}" readonly
                                       class="form-control" name="Name" placeholder="Enter name">
                            </div>
                            <div class="form-group">
                                <label>DOB</label> 
                                <input type="date" value="${a.getDOB()}"
                                       class="form-control" name="DOB" placeholder="Enter DOB">
                            </div>
                            <div class="form-group">
                                <label>Status</label> 
                                <input type="text" value="${a.getStatus()}"
                                       class="form-control" name="Status" placeholder="Enter Status">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary" name="action">Edit Actor</button>
                    </form>
                               
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>