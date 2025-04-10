
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Text Quest - Game</title>
</head>
<body>
<p>Игрок: ${gameState.playerName}</p>
<p>Партия: ${gameState.gamesPlayed + 1}</p>

<c:if test="${gameState.currentScene == 'scene1'}">
    <h2>Перекресток</h2>
    <p>Ты стоишь на распутье. Какой путь ты выберешь?</p>
    <form method="post" action="${pageContext.request.contextPath}/game">
        <button type="submit" name="action" value="left">Пойти налево</button>
        <button type="submit" name="action" value="right">Пойти направо</button>
    </form>
</c:if>

<c:if test="${gameState.currentScene == 'scene2'}">
    <h2>Темный лес</h2>
    <p>Ты вошел в темный лес и столкнулся с диким зверем!</p>
    <form method="post" action="${pageContext.request.contextPath}/game">
        <button type="submit" name="action" value="fight">Атаковать зверя</button>
        <button type="submit" name="action" value="run">Убежать от него</button>
    </form>
</c:if>

<c:if test="${gameState.currentScene == 'scene3'}">
    <h2>Заброшенный дом</h2>
    <p>Ты нашел заброшенный дом с таинственным сундуком.</p>
    <form method="post" action="${pageContext.request.contextPath}/game">
        <button type="submit" name="action" value="open">Открыть сундук</button>
        <button type="submit" name="action" value="ignore">Проигнорировать его</button>
    </form>
</c:if>

<c:if test="${gameState.currentScene == 'win'}">
    <h2>Победа!</h2>
    <p>Поздравляю, ${gameState.playerName}! Ты победил в этой игре!</p>
    <form method="post" action="${pageContext.request.contextPath}/game">
        <button type="submit" name="action" value="restart">Сыграть еще</button>
    </form>
</c:if>

<c:if test="${gameState.currentScene == 'lose'}">
    <h2>Конец игры</h2>
    <p>Прости, ${gameState.playerName}, ты проиграл.</p>
    <form method="post" action="${pageContext.request.contextPath}/game">
        <button type="submit" name="action" value="restart">Сыграть еще</button>
    </form>
</c:if>
</body>
</html>
