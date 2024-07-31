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
    <h1 class="winner">${requestScope.matchScore.winner} won</h1>
    <div class="final-table-container">
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Set</th>
                <th>Game</th>
                <th>Point</th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td>${requestScope.matchScore.player1}</td>
                <td>${requestScope.matchScore.player1Set}</td>
                <td>${requestScope.matchScore.player1Game}</td>
                <td>${requestScope.matchScore.player1Point}</td>
            </tr>
            <tr>
                <td>${requestScope.matchScore.player2}</td>
                <td>${requestScope.matchScore.player2Set}</td>
                <td>${requestScope.matchScore.player2Game}</td>
                <td>${requestScope.matchScore.player2Point}</td>
            </tr>

            </tbody>
        </table>
    </div>
</div>
<div class="end-match-menu">
    <form class="new-match-group" action="${pageContext.request.contextPath}/new-match" method="get">
        <button class="new-match-btn" type="submit">New Match</button>
    </form>
    <form class="view-matches-group" action="${pageContext.request.contextPath}/matches" method="get">
        <button class="matches-btn" type="submit">View Matches</button>
    </form>
</div>
</body>
</html>