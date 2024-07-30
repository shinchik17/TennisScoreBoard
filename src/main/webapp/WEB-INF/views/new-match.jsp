<%@ page contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Score Board</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles.css">
</head>

<body>
<nav class="navbar">
    <ul>
        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/matches">Matches</a></li>
    </ul>
</nav>
<div class="container">
    <h1 class="new-match-h1">New Match</h1>
    <form class="new-match-form" action="${pageContext.request.contextPath}/new-match" method="post">
        <div class="first-player">
            <label for="player1-name">First player name</label>
            <input type="text" name="player1-name" id="player1-name" placeholder="Enter name" required>
        </div>
        <div class="second-player">
            <label for="player2-name">Second player name</label>
            <input type="text" name="player2-name" id="player2-name" placeholder="Enter name" required>
        </div>
        <button class="start-match-btn" type="submit">Start</button>
    </form>
</div>
</body>
</html>