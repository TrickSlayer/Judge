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
        <div class="container">
            <div class="body">
                <h2>Add Movies</h2>
                <h4 class="erro">${requestScope.error}</h4>
                <form action="add-movie" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>Name</label> 
                        <input type="text" 
                               class="form-control" name="Name" placeholder="Enter name">
                    </div>
                    <div class="form-group">
                        <label>Year</label> 
                        <input type="number" 
                               class="form-control" name="Year" placeholder="Enter year">
                    </div>

                    <div class="form-group">
                        <label>Description</label> 
                        <textarea type="text" 
                                  class="form-control" name="Description" placeholder="Enter description"></textarea>
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
                                <%  int count = 0;
                                %>
                                <tr>
                                    <c:forEach items="${requestScope.genres}" var="g">
                                        <td>
                                            <div class="box"><input type="checkbox" name="Genre" value="${g.genreId}"/> ${g.genre}</div>     
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
                                    </c:forEach>
                                </tr>
                            </table>

                        </div>
                        <button type="button"><a href="add-genre?action=add-movie">Add New Genre</a></button>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                    <button type="reset" class="btn btn-primary">Cancel</button>
                </form>                
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
