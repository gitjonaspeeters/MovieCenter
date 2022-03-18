<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.db.FilmDB" %>
<%@ page import="model.domain.Film" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: jappe
  Date: 17/02/2021
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<!DOCTYPE html>
<html lang="nl">
<head>
    <title>Overzicht</title>
    <jsp:include page="head.jsp"><jsp:param name="css" value="head"/></jsp:include>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Libre+Baskerville:ital@1&display=swap" rel="stylesheet">
</head>
<body>
<div>
    <jsp:include page="header.jsp">
        <jsp:param name="current" value="Overzicht"/>
    </jsp:include>
</div>
<main  class="overzicht">
    <section>
        <h2>Overzicht</h2>
    </section>
    <c:choose>
    <c:when test="${not empty Films}">
    <table>
        <thead>
        <tr>
            <th>Titel</th>
            <th>Genre</th>
            <th>Rating</th>
            <th>Duur</th>
            <th>Verwijder</th>
        </tr>
        </thead>
        <c:forEach var="film" items="${Films}">
        <tbody>
        <tr>
            <td>${film.naam}</td>
            <td>${film.genre}</td>
            <td>${film.rating}</td>
            <td>${film.duur}</td>
            <td><a class="verwijder" href="Controller?command=verwijdercon&naam=${film.naam}" >Verwijder</a></td>
            <td><a class="aanpassen" href="Controller?command=naaraanpassen&naam=${film.naam}&genre=${film.genre}&rating=${film.rating}&duur=${film.duur}" >Aanpassen</a></td>
        </tr>
        </tbody>
        </c:forEach>
    </table>
        <p>De langste film is ${Langstefilm.naam} </p>
    </c:when>

    </c:choose>
    <form action=""></form>
</main>
<footer>
    <p>Deze website is gemaakt door Jonas Peeters</p>
    <p>Contact met de maker: <a href="mailto:jonas.peeters@student.ucll.be">jonas.peeters@student.ucll.be</a></p>
</footer>
</body>
</html>
