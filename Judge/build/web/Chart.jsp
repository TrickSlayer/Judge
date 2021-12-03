<%@page import="model.Statistics"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String name = (String) request.getAttribute("name");
            List<Statistics> list = (List<Statistics>) request.getAttribute("list");
        %>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Judge</title>
        <link rel="icon" href="images/Judge-icon.png">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/common.css" rel="stylesheet">
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages': ['line']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {

                var data = new google.visualization.DataTable();
                data.addColumn('date', 'Date');
                data.addColumn('number', 'Reviews per day');
                data.addColumn('number', 'Score');

                data.addRows([
            <%
                for (int i = 0; i < list.size(); i++) {
                    int day = list.get(i).getDay();
                    int month = list.get(i).getMonth();
                    int year = list.get(i).getYear();
            %>
                    [new Date(<%= year %>, <%= month %>, <%= day %>), <%= list.get(i).getCount()%>, <%= list.get(i).getRate()%>],
            <%
                }
            %>
                ]);

                var options = {
                    chart: {
                        title: '<%= name%>'
                    },
                    width: 1098,
                    height: 500,
                    series: {
                        0: {axis: "Reviews  per day"},
                        1: {axis: "Score"}
                    },
                    axes: {
                        y: {
                            Rate: {label: 'Reviews  per day'},
                            Score: {label: 'Score'}
                        }
                    }
                };

                var chart = new google.charts.Line(document.getElementById('curve_chart'));

                chart.draw(data, google.charts.Line.convertOptions(options));
            }
        </script>
    </head>
    <body>
        <%@include file="menu.jsp" %>

        <div class="container">
            <div class="body">
                <h2>Statistics information in this year</h2>
                <div style="padding: 10px; background-color: white">
                    <div id="curve_chart" ></div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
