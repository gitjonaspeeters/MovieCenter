<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jappe
  Date: 22/05/2021
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <title>Aanpassen</title>
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
        <jsp:param name="current" value="Home"/>
    </jsp:include>
</div>
<main class="verwijder1">
    <c:if test="${not empty errorsaanpassen}">
        <div id="error" class="alert alert-danger">
            <ul>
                <c:forEach items="${errorsaanpassen}" var="error">
                    <li>
                            ${error}
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <h2>Aanpassen</h2>
    <p>Wil je ${param["naam"]} aanpassen?</p>
    <form method="post" action="Controller?command=aanpassen&naam=${param["naam"]}" novalidate>
            <label for="genre">Genre</label>
            <select name="genre" id="genre"  >
                <option value = "Sci-Fi" selected>Sci-Fi</option>
                <option value = "Family">Family</option>
                <option value = "Anime">Anime</option>
                <option value = "Horror">Horror</option>
                <option value = "Romantiek">Romantiek</option>
                <option value = "Actie">Actie</option>
                <option value = "Avontuur">Avontuur</option>
                <option value = "Andere..">Andere..</option>
            </select >

            <label for="Rating">Rating</label>
            <input type="number" id="Rating" name="Rating"   value="${param["rating"]}">


            <label for="Duur">Duur</label>
            <input type="number" placeholder="Duur" id="Duur"  name="Duur"  value="${param["duur"]}">

            <input id="Submit" type="Submit" value="Voeg Toe">

    </form>

</main>
<footer>
    <p>Deze website is gemaakt door Jonas Peeters</p>
    <p>Contact met de maker: <a href="mailto:jonas.peeters@student.ucll.be">jonas.peeters@student.ucll.be</a></p>
</footer>
</body>
</html>
