<%-- 
    Document   : home
    Created on : Sep 30, 2021, 9:24:41 PM
    Author     : Acer
--%>

<%@page import="model.Movie"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link href="css/homestyle.css" rel="stylesheet">
    </head>

    <body>
        <!--Nội dung Menu-->
        <%@include file="menu.jsp" %>

        <div class="container">
            <!--Nội dung Top Movie-->
            <%  List<Movie> topMovies = (List<Movie>) request.getAttribute("topMovie");
                if (topMovies.size() >= 5) {
            %>
            <!--body top-->
            <div class="body top">
                <div class="row slideblock">
                    <div class="col-md-12">

                        <!--Bắt đầu slider-->
                        <div id="carousel-example-generic" class="carousel slide"
                             data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="4"></li>
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img src="<%= topMovies.get(0).getImage()%>">
                                </div>
                                <%  for (int i = 1; i < 5; i++) {
                                %>
                                <div class="item">
                                    <img src="<%= topMovies.get(i).getImage()%>">
                                </div>
                                <%
                                    }
                                %>
                            </div>

                            <!-- Controls -->
                            <a class="left carousel-control"
                               href="#carousel-example-generic" role="button"
                               data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control"
                               href="#carousel-example-generic" role="button"
                               data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                        <!--Kết thúc slider-->
                    </div>
                </div>
                <div class="top-movies">
                    <h2>Top Raking</h2>
                    <div class="movies">
                        <%  for (int i = 0; i < 5; i++) {
                        %>
                        <a href="movie?page=1&id=<%= topMovies.get(i).getMovieID()%>" class="movie">
                            <div class="item">
                                <p class="name"><%= i + 1%>. <%= topMovies.get(i).getName()%></p>
                                <p class="score">Score: <%= topMovies.get(i).getScore()%>  -  <%= topMovies.get(i).getNumScore()%> Votes</p>
                            </div>
                        </a>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
            <!--body-->
            <div class="body">
                <div class="topTime">
                    <h2>Top Movies Of The Day</h2>
                    <div class="movies">
                        <c:forEach items="${requestScope.topDay}" var="m">
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
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!--end topTime-->
                <div class="topTime">
                    <h2>Top Movies Of The Month</h2>
                    <div class="movies">
                        <c:forEach items="${requestScope.topMonth}" var="m">
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
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!--end topTime-->
                <div class="topTime">
                    <h2>Top Movies Of The Year</h2>
                    <div class="movies">
                        <c:forEach items="${requestScope.topYear}" var="m">
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
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!--end topTime-->
                <div class="topTime">
                    <h2>Top Rate Movies</h2>
                    <div class="movies">
                        <c:forEach items="${requestScope.topRate}" var="m">
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
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!--end topTime-->
            </div>
            <%
            } else {
            %>
            <div class="body">
                <center>
                    <h1>There are not enough rated movies</h1>
                </center>
            </div>
            <%
                }
            %>
        </div>            
        <!--nội dung footer-->
        <%@include file="footer.jsp" %>
        <!--Thư viên javascript của Bootstrap-->
        <script src="js/Jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>

</html>