
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Text Quest - Game</title>
</head>
<body>
<h1>Text Quest</h1>
<p>Player: ${gameState.playerName}</p>

<c:if test="${gameState.currentScene == 'scene1'}">
    <h2>Crossroads</h2>
    <p>You stand at a crossroads. Which path will you take?</p>
    <form method="post" action="${pageContext.request.contextPath}/game">
        <button type="submit" name="action" value="left">Go left</button>
        <button type="submit" name="action" value="right">Go right</button>
    </form>
</c:if>

<c:if test="${gameState.currentScene == 'scene2'}">
    <h2>Dark Forest</h2>
    <p>You entered a dark forest and encountered a wild beast!</p>
    <form method="post" action="${pageContext.request.contextPath}/game">
        <button type="submit" name="action" value="fight">Fight the beast</button>
        <button type="submit" name="action" value="run">Run away</button>
    </form>
</c:if>

<c:if test="${gameState.currentScene == 'scene3'}">
    <h2>Abandoned House</h2>
    <p>You found an abandoned house with a mysterious chest.</p>
    <form method="post" action="${pageContext.request.contextPath}/game">
        <button type="submit" name="action" value="open">Open the chest</button>
        <button type="submit" name="action" value="ignore">Ignore it and move on</button>
    </form>
</c:if>

<c:if test="${gameState.currentScene == 'win'}">
    <h2>Victory!</h2>
    <p>Congratulations, ${gameState.playerName}! You've won the game!</p>
    <form method="post" action="${pageContext.request.contextPath}/game">
        <button type="submit" name="action" value="restart">Play Again</button>
    </form>
</c:if>

<c:if test="${gameState.currentScene == 'lose'}">
    <h2>Game Over</h2>
    <p>Sorry, ${gameState.playerName}, you've lost the game.</p>
    <form method="post" action="${pageContext.request.contextPath}/game">
        <button type="submit" name="action" value="restart">Try Again</button>
    </form>
</c:if>
</body>
</html>
