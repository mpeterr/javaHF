<%--
  Created by IntelliJ IDEA.
  User: Péter
  Date: 2023. 05. 06.
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Másodfokú egyenlet megoldó</title>
</head>
<body>
<h1>Másodfokú egyenlet megoldó</h1><br>
Add meg a 3 ismeretlent!
<br>
    <form action="Servlet" method="post">
        a: <input type="number" name="a"/><br>
        b: <input type="number" name="b"/><br>
        c: <input type="number" name="c"/><br>
        <input type="submit" value="Számítás!" />
    </form>
<br>
<form action="Servlet" method="get">
    <input type="submit" value="Eddigi eredmények" />
</form>
<br>
<a href="index.jsp">Vissza</a>
</body>
</html>
