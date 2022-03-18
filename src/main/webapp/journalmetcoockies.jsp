<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jappe
  Date: 4/05/2021
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <title>Journal</title>
    <jsp:include page="head.jsp">
        <jsp:param name="css" value="head"/>
    </jsp:include>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Libre+Baskerville:ital@1&display=swap" rel="stylesheet">
</head>
<body>
    <div>
        <jsp:include page="header.jsp">
            <jsp:param name="current" value="Journal"/>
        </jsp:include>
    </div>
<main class="journal">
<c:choose>
    <c:when test="${not empty opgezochteFilms}">
    <table>
        <thead>
        <tr>
            <th>Titel</th>
            <th>Datum</th>
            <th>Tijd</th>
        </tr>
        </thead>
        <c:forEach var="Log" items="${opgezochteFilms}">
        <tbody>
        <tr>
            <td>${Log.film.getNaam()}</td>
            <td>${Log.getDate()}</td>
            <td>${Log.getTime()}</td>
        </tr>
        </tbody>
    </c:forEach>
    </table>
    </c:when>
    <c:otherwise>
        <h2>Probeer andere keer opnieuw!</h2>
        <p>Er zijn momenteel geen films in je journal...</p>
    </c:otherwise>
</c:choose>
</main>
</body>
</html>
