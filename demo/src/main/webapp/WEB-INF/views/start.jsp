<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Text Quest - Start</title>
</head>
<body>
<h1>Welcome to the Text Quest!</h1>

<c:if test="${gameState.gamesPlayed > 0}">
    <p>Games played: ${gameState.gamesPlayed}</p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/start">
    <label for="playerName">Enter your name:</label>
    <input type="text" id="playerName" name="playerName" required>
    <button type="submit">Start Game</button>
</form>
</body>
</html>
