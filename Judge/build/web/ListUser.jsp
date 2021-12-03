<%-- 
    Document   : ListUser
    Created on : Oct 22, 2021, 11:55:20 PM
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
        <style>
            .body input, .body button{
                color: black;
            }
        </style>
    </head>

    <body>

        <!--Nội dung Menu-->
        <%@include file="menu.jsp" %>
        
        <div class="container">
            <div class="body">
                <h2>List User (Lower)</h2>
                <h4 class="erro">${requestScope.error}</h4>
                <div class="table-block">
                    <form action="list-user?page=1" method="post">
                        <input name="search" placeholder="Enter user ID">
                        <button type="submit" name="action" value="Search">Search</button>
                        <c:set value="${requestScope.user}" var="user">
                        </c:set>
                        <div style="margin-top: 10px; display: <%= (request.getAttribute("user") != null ? "block" : "none")%>">
                            <input value="${user.userName}" readonly > 
                            <input name="${user.userID}" value="${user.role}">
                            <button name="action" type="submit" value="${user.userID}">Edit</button>
                        </div>

                        <table class="table">
                            <tr>
                                <th>User Name</th>
                                <th>Role</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach items="${requestScope.list}" var="u">
                                <tr>
                                    <td>${u.userName}</td>
                                    <td><input name="${u.userID}" value="${u.role}"></td>
                                    <td><button name="action" type="submit" value="${u.userID}">Edit</button></td>
                                </tr>
                            </c:forEach>

                        </table>
                    </form>
                </div>
                <div class="button">
                    <%  int currentPage = Integer.parseInt(request.getParameter("page"));
                        int pagenum = (int) request.getAttribute("page");
                        int startPage = ((currentPage - 5 > 1) ? currentPage - 5 : 1);
                        int endPage = startPage + 10;
                        if (endPage > pagenum) {
                            endPage = pagenum;
                            startPage = endPage - 10;
                        }
                        if (startPage < 1) {
                            startPage = 1;
                        }
                        for (int i = startPage; i <= endPage; i++) {
                    %>
                    <a href="list-user?page=<%= i%>"
                       <%
                           if (i == currentPage) {
                       %>
                       style="color: whitesmoke"
                       <%
                           }
                       %>
                       ><%=i%></a>
                    &nbsp;
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
        <!--Kết thúc contianer-->
        <!--nội dung footer-->
        <%@include file="footer.jsp" %>
        <!--Thư viên javascript của Bootstrap-->
    </body>

</html>