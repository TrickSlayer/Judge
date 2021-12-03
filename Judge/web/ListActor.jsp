<%-- 
    Document   : ListActor
    Created on : Oct 13, 2021, 9:40:33 PM
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
                <h2>List Actor</h2>
                <form action="list-actor" method="post">
                    <input name="Search" style="color:black" placeholder="Enter name">
                    <button type="submit" style="color:black">Search</button>
                    <c:set value="${requestScope.actor}" var="actor">
                    </c:set>
                    <div style="margin-top: 10px;color:black; display: ${actor!= null ? "block":"none"}">
                        <input readonly value="${actor.getName()}">
                        <% if (user1 != null && user1.getPower() >= 5) {%>
                        <a href="delete-actor?id=${actor.getActorID()}">Delete</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="edit-actor?id=${actor.getActorID()}">Edit</a>
                        &nbsp;&nbsp;&nbsp;
                        <% } %>
                        <a href="search-actor?page=1&id=${actor.getActorID()}&order=desc">View</a>
                    </div>
                </form>
                <div class="table-block">
                    <table class="table">
                        <tr>
                            <th>Actor Name</th>
                            <th>DOB</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach items="${requestScope.list}" var="a">
                            <tr>
                                <td>${a.getName()}</td>
                                <td>${a.getDOB()}</td>
                                <td>${a.getStatus()}</td>
                                <td>
                                    <% if (user1 != null && user1.getPower() >= 5) {%>
                                    <a href="delete-actor?id=${a.getActorID()}">Delete</a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="edit-actor?id=${a.getActorID()}">Edit</a>
                                    &nbsp;&nbsp;&nbsp;
                                    <% } %>
                                    <a href="search-actor?page=1&id=${a.getActorID()}&order=desc">View</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="button">
                    <%
                        int currentPage = Integer.parseInt(request.getParameter("page"));
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
                    <a href="list-actor?page=<%= i%>"
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
        <script src="js/Jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>

</html>
