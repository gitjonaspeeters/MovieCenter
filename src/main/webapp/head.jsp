<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:if test="${mode == 'horror'}"><link rel="stylesheet" href="css/horror.css"></c:if>
    <c:if test="${mode == 'family'}"><link rel="stylesheet" href="css/sample.css"></c:if>
    <c:if test="${mode == 'scifi'}"><link rel="stylesheet" href="css/scifi.css"></c:if>
    <c:if test="${mode == 'love'}"><link rel="stylesheet" href="css/love.css"></c:if>