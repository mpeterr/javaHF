<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Péter
  Date: 2023. 05. 10.
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eddigi eredmények</title>
</head>
<body>
<h1>Eddigi eredmények</h1><br>
<%
    List<String> eredmenyek = (List<String>) request.getAttribute("eredmenyek");
%>
<table border=" 1">
    <%
        int sorszam = 0;
        for (int i = 0; i < eredmenyek.size()/2; i++) {
            %>
            <tr><td><%=eredmenyek.get(sorszam)%><%sorszam++;%></td><td><%=eredmenyek.get(sorszam)%><%sorszam++;%></td></tr>
    <%
        }
    %>
</table>
<a href="masodfokuegyenletmegoldo.jsp">Vissza az egyenletmegoldóhoz</a><br>
<a href="index.jsp">Vissza a főoldalra</a>
</body>
</html>
