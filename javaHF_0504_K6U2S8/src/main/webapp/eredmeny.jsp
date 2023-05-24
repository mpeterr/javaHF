<%@ page import="myServlets.Servlet" %>
<%@ page import="java.io.BufferedWriter" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.nio.charset.Charset" %>
<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: Péter
  Date: 2023. 05. 06.
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eredmény</title>
</head>
<body>
<%
    double a = (double)request.getAttribute("a");
    double b = (double)request.getAttribute("b");
    double c = (double)request.getAttribute("c");
    double diszkriminans = (double)request.getAttribute("diszkriminans");
%>
<h1>Eredmény</h1>
A megadott számok:<br>
<table border = "1">
    <tr> <td>a</td><td><%=a%></td></tr>
    <tr> <td>b</td><td><%=b%></td></tr>
    <tr> <td>c</td><td><%=c%></td></tr>
</table><br>
<%
    if (diszkriminans > 0) {
        %>
        Paraméterek: a: <%=a%> b: <%=b%> c: <%=c%><br>
        Megoldás: x1=<%=request.getAttribute("gyok1")%>, x2=<%=request.getAttribute("gyok2")%>
<%
    } else if (diszkriminans == 0) {
        %>
        Paraméterek: a: <%=a%> b: <%=b%> c: <%=c%><br>
        Megoldás: x1=x2=<%=request.getAttribute("gyok")%>
<%
    } else {
        %>
        Eredmény: nincs gyök
<%
    }
%><br><br>
<a href="masodfokuegyenletmegoldo.jsp">Vissza</a>
</body>
</html>
