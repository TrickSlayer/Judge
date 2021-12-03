<%-- 
    Document   : ListPowerRole
    Created on : Oct 22, 2021, 10:29:19 PM
    Author     : Acer
--%>

<%@page import="java.util.List"%>
<%@page import="model.PowerRole"%>
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
                <h2>List Power Role (Lower)</h2>
                <div class="table-block">
                    <form action="list-power-role" method="post">
                        <table class="table">

                            <tr>
                                <th>Power</th>
                                <th>Role</th>
                                <th>Action</th>
                            </tr>
                            <%                                
                                User user = (User) session.getAttribute("account");
                                int power = user.getPower();
                                List<PowerRole> list = (List<PowerRole>) request.getAttribute("list");
                                for (int i = 1; i < power; i++) {
                                    String Role="";
                                    for (int j = 0; j <list.size(); j++) {
                                            if (list.get(j).getPower()==i){
                                                Role = list.get(j).getRole();
                                            }
                                        }
                            %>
                            <tr>
                                <td><%= i%></td>
                                <td><input style="color: black" type="text" name="<%= i%>" value="<%= Role%>"></td>
                                <td>
                                    <button style="color: black" name="action" type="submit" value="<%= i%>">Edit</button>
                                </td>
                            </tr>
                            <%
                                }
                            %>

                        </table>
                    </form>
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

