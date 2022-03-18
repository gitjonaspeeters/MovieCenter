<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jappe
  Date: 17/02/2021
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <title>Voeg toe</title>
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
    <jsp:param name="current" value="Voegtoe"/>
</jsp:include>
</div>
<main>
<article>
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
    <div class="voegtoe1">
    <section class="voegtoe">
        <h2>Voeg een film toe aan de lijst</h2>
        <p>Hieronder kan je de form invullen om een film toe te voegen</p>
    </section>
    <form method="post" action="Controller?command=voegtoe" novalidate>
            <p class="${naamClass}">
            <label for="titel">Titel</label>
            <input type="text" id="titel"  name="naam"  value="${naamPreviousValue}">
            </p>
            <p class="${genreClass}">
            <label for="genre">Genre</label>
            <select name="genre" id="genre">
                <option value = "Sci-Fi">Sci-Fi</option>
                <option value = "Family">Family</option>
                <option value = "Anime">Anime</option>
                <option value = "Horror">Horror</option>
                <option value = "Romantiek">Romantiek</option>
                <option value = "Actie">Actie</option>
                <option value = "Avontuur">Avontuur</option>
                <option value = "Andere..">Andere..</option>
            </select >
            </p>

            <p class="${ratingClass}">
            <label for="Rating">Rating</label>
            <input type="number" id="Rating" name="Rating"   value="${ratingPreviousValue}">
            </p>

            <p class="${duurClass}">
            <label for="Duur">Duur</label>
            <input type="number" placeholder="Duur" id="Duur"  name="Duur"  value="${duurPreviousValue}">
            </p>

            <p>
            <input id="Submit" type="Submit" value="Voeg Toe">
            </p>
    </form>
    </div>
</article>
</main>
<footer>
    <p>Deze website is gemaakt door Jonas Peeters</p>
    <p>Contact met de maker: <a href="mailto:jonas.peeters@student.ucll.be">jonas.peeters@student.ucll.be</a></p>
</footer>
</body>
</html>
