<%--
  Created by IntelliJ IDEA.
  User: jappe
  Date: 9/03/2021
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <title>Zeker?</title>
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
<h2>Bevestiging</h2>
<p>Ben je zeker dat je je film ${param["naam"]} wil verwijderen</p>
<form action="Controller?command=verwijder&naam=${param["naam"]}" method="POST">
    <input type="submit" value="Ja" id="verw"/>
</form>
<p><a href="Controller?command=overzicht">Cancel </a>Indien je ${param["naam"]} niet wil verwijderen</p>
</main>
<footer>
    <p>Deze website is gemaakt door Jonas Peeters</p>
    <p>Contact met de maker: <a href="mailto:jonas.peeters@student.ucll.be">jonas.peeters@student.ucll.be</a></p>
</footer>
</body>
</html>
