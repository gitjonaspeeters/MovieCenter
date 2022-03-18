<%@ page import="model.domain.Film" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <title>Home | Must_Watch</title>
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
<main class="in dex">
    <article class="intro">
        <h2>Mijn films</h2>
        <section>Welkom op deze movie center. Hier vind je al mijn must watch movies. Met genre, rating , etc.</section>
        <section>De langste film is ${Langstefilm.naam} </section>
    </article>
</main>
<footer>
    <div>
    <p>Deze website is gemaakt door Jonas Peeters</p>
    <p>Contact met de maker: <a href="mailto:jonas.peeters@student.ucll.be">jonas.peeters@student.ucll.be</a></p>
    <p>Current theme: ${mode}</p>
    </div>
    <div>
    <a href="Controller?command=show" id="switch">Switch</a>
    </div>
</footer>
</body>
</html>