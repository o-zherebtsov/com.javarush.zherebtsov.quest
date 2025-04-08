<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Text Quest - Start</title>
</head>
<body>
<h1>Для начала игры</h1>

<c:if test="${gameState.gamesPlayed > 0}">
    <p>Партий сыграно: ${gameState.gamesPlayed}</p>
</c:if>

<c:if test="${gameState.gamesWon > 0}">
    <p>Партий выйграно: ${gameState.gamesWon}</p>
</c:if>

<c:if test="${gameState.gamesLost > 0}">
    <p>Партий проиграно: ${gameState.gamesLost}</p>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/start">
    <label for="playerName">Введите ваше имя:</label>
    <input type="text" id="playerName" name="playerName" required>
    <button type="submit">Начать играть</button>
</form>
</body>
</html>
