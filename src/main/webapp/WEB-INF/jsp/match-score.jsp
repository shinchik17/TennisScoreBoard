<%@ page import="com.alexshin.tennisscoreboard.controller.MatchesServlet" %>
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
    <h1 class="match-score">Ongoing Match</h1>
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Set</th>
                <th>Game</th>
                <th>Point</th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td>${requestScope.matchScore.player1}</td>
                <td>${requestScope.matchScore.player1Set}</td>
                <td>${requestScope.matchScore.player1Game}</td>
                <td>${requestScope.matchScore.player1Point}</td>
                <td><form action="${pageContext.request.contextPath}/match-score?uuid=${requestScope.uuid}" method="post">
                    <button class="player-win-point-btn" name="player-won-num" value="1" type="submit">Add point</button>
                </form></td>
            </tr>
            <tr>
                <td>${requestScope.matchScore.player2}</td>
                <td>${requestScope.matchScore.player2Set}</td>
                <td>${requestScope.matchScore.player2Game}</td>
                <td>${requestScope.matchScore.player2Point}</td>
                <td><form action="${pageContext.request.contextPath}/match-score?uuid=${requestScope.uuid}" method="post">
                    <button class="player-win-point-btn" name="player-won-num" value="2" type="submit">Add point</button>
                </form></td>
            </tr>

            </tbody>
        </table>
    </div>


</div>
</body>
</html>