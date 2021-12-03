<%-- 
    Document   : menu
    Created on : Oct 3, 2021, 7:12:39 PM
    Author     : Acer
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/headfoot.css" rel="stylesheet">
        <script src="js/headfoot.js"></script>
    </head>
    <body>
        <%  User user1 = (User) session.getAttribute("account");
            String className = "";
            if (user1!= null){
                className = "Log";
            }
        %>
        <div class="menu">
            <div class="container oneline <%= className %>">
                <!--phần tử bên trái-->
                <div class="oneline">           
                    <!--logo-->
                    <div>
                        <img class="logo" src="images/Judge.png">
                    </div>
                    <!--menu-->                           
                    <div class="button dropdown">
                        <button class="dropdown_button">
                            <img class="button_icon" src="images/menu.png"> 
                            <h4 class="button_name">Menu</h4>
                        </button>
                        <div class="dropdown_content">
                            <a href="home">Home</a>
                            <%
                                if (user1 != null && user1.getPower() >= 5) {
                            %>
                            <a href="add-movie">Add Movie</a>
                            <%
                                }
                                if (user1 != null && user1.getPower() >= 8) {
                            %>
                            <a href="list-power-role?page=1">List Power Role</a>
                            <a href="list-user?page=1">List User</a>
                            <%
                                }

                            %>

                            <a href="list?page=1&order=desc">List Movie</a>
                            <a href="list-actor?page=1">List Actor</a>
                            <a href="list-genre?page=1">List Genre</a>
                            <a href="search-by-genre?page=1">Search By Genre</a>
                        </div>
                    </div>
                    <!--search-->
                    <div id="search" class="search">
                        <form action="search-movie" method="post">
                            <input type="text" name="name" placeholder="Enter movie name">
                            <button type="submit"> <img src="images/black_search.png"> </button>
                            <button onclick="hide()" > <img src="images/cancel.png"> </button>
                        </form>
                    </div>

                    <div id="search-button" class="search-button">
                        <button onclick="show()"> <img src="images/white_search.png"> </button>
                    </div> 
                </div>
                <!--phần tử bên phải-->
                <div class="oneline">

                    <!--watch list-->
                    <div class="button watchlist">
                        <button onclick="window.location.href = 'watch-list?page=1'">
                            <img class="button_icon" src="images/watchlist.png">
                            <h4 class="button_name">Watch List</h4>
                        </button>
                    </div>
                    <%                        if (user1 == null) {
                    %>
                    <div>
                        <a href="sign-up">Sign up</a>
                        /
                        <a href="login">Sign in</a>
                    </div>
                    <%
                    } else {
                    %>
                    <div class="space"></div>
                    <div class="login">
                        <div class="user-block">
                            <img src="<%= user1.getAvata()%>">
                            <div>
                                <p><%= user1.getUserName()%></p>
                                <p>UID: <%= user1.getUserID()%></p>
                            </div>
                        </div>
                        <a href="edit-user">Edit Account</a> 
                        &nbsp;&nbsp;&nbsp;
                        <a href="logout">Log Out</a> 
                    </div>    

                    <%
                        }
                    %>                    
                </div>
            </div>
        </div>
    </body>
</html>
