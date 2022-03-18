<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jappe
  Date: 14/03/2021
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <title>Zoeken</title>
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
        <jsp:param name="current" value="Zoeken"/>
    </jsp:include>
    </div>

    <c:if test="${not empty errors}">
        <div id="error" class="alert alert-danger">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>
                            ${error}
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
<main class="zoeken">
    <h2>Zoeken</h2>
    <form method="post" action="Controller?command=zoekenerror">
            <p class="${zoekenClass}">
            <label for="titel">Titel</label>
            <input type="text" id="titel"  name="titel">
            </p>
            <input type="Submit" id="submit" value="Zoeken">
    </form>
</main>
<footer>
    <p>Deze website is gemaakt door Jonas Peeters</p>
    <p>Contact met de maker: <a href="mailto:jonas.peeters@student.ucll.be">jonas.peeters@student.ucll.be</a></p>
</footer>
</body>
</html>
