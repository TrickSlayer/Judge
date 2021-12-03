<%-- 
    Document   : WatchList
    Created on : Oct 22, 2021, 9:14:19 PM
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
                <h2>Watch List</h2>
                <form class="form" action="watch-list" method="post">
                    <div class="movies">
                        <c:forEach items="${requestScope.list}" var="m">
                            <div class="movie-block">
                                <a href="movie?page=1&id=${m.movieID}" style="text-decoration: none" class="movie-link">
                                    <div class="movie">

                                        <div class="image">
                                            <img src="${m.image}">
                                        </div>

                                        <div class="content">
                                            <h4>${m.name}</h4>
                                            <span class="glyphicon glyphicon-star"> ${m.getScore()} - ${m.getNumScore()} votes</span>
                                            <br>
                                            <p>${m.description}</p>
                                        </div>
                                    </div>
                                </a>    
                                <div class="button-block">
                                    <button type="submit" class="btn btn-primary" name="action" value="${m.movieID}">Delete</button> 
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </form>
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
                    <a href="movie?page=<%= i%>&id=${m.movieID}"
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
