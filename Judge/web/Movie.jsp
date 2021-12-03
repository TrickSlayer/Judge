<%-- 
    Document   : movie
    Created on : Oct 3, 2021, 9:58:40 PM
    Author     : Acer
--%>

<%@page import="dal.UserDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Rate"%>
<%@page import="model.Actor"%>
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
        <link href="css/moviestyle.css" rel="stylesheet">
    </head>
    <body>
        <!--Nội dung Menu-->
        <%@include file="menu.jsp" %>
        <c:set value="${requestScope.movie}" var="m"></c:set>
            <div class="container">

                <div class="body" style="min-height: 400px;">
                    <div class="row">
                        <div class="col-md-3">
                            <img src="${m.image}">
                    </div>
                    <div class="col-md-9 content">
                        <h2>${m.name} - ${m.year}</h2>
                        <span style="color: gold" class="glyphicon glyphicon-star"> ${m.getScore()} - ${m.getNumScore()} votes</span> 
                        <br>
                        <p class="des" style="font-size: 20px;">${m.description}</p>
                        <div>
                            <table>
                                <tr>
                                    <td> Star:</td>
                                    <td>
                                        <c:set value="${m.actors}" var="actor"></c:set>
                                        <c:forEach items="${actor}" var="a">
                                            <a href="search-actor?page=1&id=${a.actor.actorID}&order=desc">${a.actor.name} (${a.role})</a>,
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td> Genre:</td>
                                    <td>
                                        <c:set value="${m.genres}" var="genre"></c:set>
                                        <c:forEach items="${genre}" var="g">
                                            <a href="search-genre?page=1&id=${g.genreId}&order=desc">${g.genre}</a>,
                                        </c:forEach>
                                    </td>
                                </tr>
                            </table>
                            <br>
                            <button style="color: black" onclick="window.location.href = 'add-watch-list?id=${m.movieID}'">Add to Watch List</button>

                            <%   if (user1 != null && user1.getPower() >= 5) {
                            %>
                            <form class="form" action="list" method="post" style="margin-top:10px">
                                <div class="button-block" style="color:black">
                                    <button type="submit" name="action" value="Delete ${m.movieID}">Delete</button>
                                    <button type="submit" name="action" value="Edit ${m.movieID}">Edit</button>   

                                </div>
                            </form>
                            <button style="color: black; margin-top: 10px" onclick="window.location.href = 'statistics?id=${m.movieID}'">Statistics</button>
                            <%
                                }
                            %>

                        </div>
                    </div>
                </div>   
            </div>
            <div class="body trailer">
                <video style="color:white" src="${m.trailer}" controls>
                </video>
                <c:set value="${sessionScope.account}" var="a"></c:set>
                <%  int currentPage = Integer.parseInt(request.getParameter("page"));
                    if (currentPage == 1) {
                %>
                <c:if test="${a!=null}">
                    <form action="movie?id=${m.getMovieID()}" method="post">
                        <div class="row comment">
                            <div class="col-md-2 avata">
                                <img src="${a.getAvata()}">
                                <h4>${a.getUserName()}</h4>
                            </div>
                            <div class="col-md-10 rate">
                                <h4 class="erro">${requestScope.error}</h4>
                                <%  Rate ur = (Rate) request.getAttribute("UserRate");
                                    int urRate = -1;
                                    String comment = "";
                                    if (ur != null) {
                                        urRate = ur.getRate() - 1;
                                        comment = ur.getComment();
                                    }
                                    for (int i = 0; i < 10; i++) {
                                %>
                                <nav class="glyphicon glyphicon-star" id="star<%= i%>"></nav>
                                    <%
                                        }
                                        for (int i = 0; i < 10; i++) {
                                    %>
                                <input type="radio" class="rating" name="rating" id="btn<%= i%>" value="<%= i%>"
                                       <%
                                           if (i == urRate) {
                                       %>
                                       checked
                                       <%
                                           }
                                       %>       
                                       >
                                <%
                                    }
                                %>
                                <div class="comment-area">
                                    <textarea type="text" class="comment-box" name="comment" placeholder="Enter Comment"><%= comment%></textarea>
                                    <div class="comment-btn-block" style="color:black">
                                        <input class="comment-btn" type="submit" name="action" value="Post">
                                        <%
                                            if (urRate > -1) {
                                        %>
                                        <input class="comment-btn" type="submit" name="action" value="Delete">
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:if>
                <% } %>
                <%
                    List<Rate> list = (List<Rate>) request.getAttribute("list");
                    for (Rate rate : list) {
                        UserDAO u = new UserDAO();
                        User user = u.get(rate.getUserID());
                %>
                <div class="row comment-line">
                    <div class="col-md-2 avata">
                        <img src="<%= user.getAvata()%>">
                        <h4><%= user.getUserName()%></h4>
                    </div>
                    <div class="col-md-10 rate">
                        <p style="color:whitesmoke;margin-bottom: 0px"><%= rate.getTime()%></p>
                        <%
                            for (int j = 0; j < 10; j++) {
                        %>
                        <span class="glyphicon glyphicon-star" style="color:<%= (j < rate.getRate() ? "gold" : "whitesmoke")%>"></span>
                        <%
                            }
                        %>
                        <div >
                            <textarea readonly class="comment-box"><%= rate.getComment()%></textarea>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
                <div class="button">
                    <%
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

            <link href="css/liststyle.css" rel="stylesheet">
            <style>
                .movie .content{
                    margin-left: -10px;
                }
            </style>
            <div class="body">
                <h2>Related Movie</h2>               
                <div class="movies">
                    <c:forEach items="${requestScope.listRelate}" var="movie">
                        <div class="movie-block">
                            <a href="movie?page=1&id=${movie.movieID}" style="text-decoration: none" class="movie-link">
                                <div class="movie">

                                    <div class="image">
                                        <img src="${movie.image}">
                                    </div>

                                    <div class="content">
                                        <h4>${movie.name}</h4>
                                        <span class="glyphicon glyphicon-star"> ${movie.getScore()} - ${movie.getNumScore()} votes</span>
                                        <br>
                                        <p>${movie.description}</p>
                                    </div>
                                </div>
                            </a>    
                        </div>
                    </c:forEach>
                </div>
            </div>


        </div>
        <!--Kết thúc contianer-->
        <!--nội dung footer-->
        <%@include file="footer.jsp" %>
        <!--Thư viên javascript của Bootstrap-->
        <script src="js/Jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/movie.js"></script>
    </body>
</html>
