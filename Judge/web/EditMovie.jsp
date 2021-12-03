<%-- 
    Document   : EditMovie
    Created on : Oct 12, 2021, 8:47:39 PM
    Author     : Acer
--%>

<%@page import="model.Movie"%>
<%@page import="model.Genre"%>
<%@page import="java.util.List"%>
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
        <link href="css/addmovie.css" rel="stylesheet">
        <%
            User user = (User) session.getAttribute("account");
            if (user == null || user.getPower() < 5) {
                response.sendRedirect("home");
            }
        %>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <c:set var="m" value="${requestScope.movie}"/>

        <div class="container">
            <div class="body">
                <h2>Edit Movies</h2> 
                <h4 class="erro">${requestScope.error}</h4>
                <form action="edit-movie" method="post" enctype="multipart/form-data">
                    <input name="id" class="Info" value="${requestScope.id}">
                    <div class="form-group">
                        <label>Name</label> 
                        <input type="text" 
                               class="form-control" name="Name" placeholder="Enter name" value="${m.name}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Year</label> 
                        <input type="number" 
                               class="form-control" name="Year" placeholder="Enter year" value="${m.year}" readonly>
                    </div>

                    <div class="form-group">
                        <label>Description</label> 
                        <textarea type="text" 
                                  class="form-control" name="Description" placeholder="Enter description">${m.description}</textarea>
                    </div>

                    <div class="form-group">
                        <label>Photo</label> <br/>
                        <input type="file" 
                               class="form-control" name="Photo" placeholder="Enter photo">
                    </div>

                    <div class="form-group">
                        <label>Trailer</label> <br/>
                        <input type="file" 
                               class="form-control" name="Trailer" placeholder="Enter trailer">
                    </div>

                    <div class="form-group">
                        <label>Genre</label> <br/>
                        <div class="box-area">
                            <table>
                                <tr>
                                    <%  List<Genre> g = (List<Genre>) request.getAttribute("genres");
                                        List<Genre> mg = ((Movie) request.getAttribute("movie")).getGenres();
                                        int count = 0;
                                        for (Genre i : g) {
                                            int a = i.getGenreId();
                                    %>                                                          
                                    <td>
                                        <div style="margin-right: 40px">
                                            <input type="checkbox" name="Genre" value="<%= a%>"
                                                   <%
                                                       for (Genre j : mg) {
                                                           int b = j.getGenreId();
                                                           if (a == b) {
                                                   %>
                                                   checked
                                                   <%
                                                           }
                                                       }
                                                   %>                                     
                                                   /> <%= i.getGenre()%>
                                        </div>
                                    </td>   
                                    <%
                                        count++;
                                        if (count == 6) {
                                            count = 0;
                                    %>
                                </tr>
                                <tr>
                                    <%
                                        }
                                    %>
                                    <%
                                        }
                                    %>
                                </tr>
                            </table> 
                        </div>
                        <button type="button"><a href="add-genre?action=edit-movie?id=${requestScope.id}">Add New Genre</a></button>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                    <button type="reset" class="btn btn-primary">Cancel</button>
                </form>                
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
