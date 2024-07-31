<%@ page contentType="text/html; charset=UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h1>Matches</h1>
    <form class="search-player-form" action="${pageContext.request.contextPath}/new-match" method="get">
        <label for="search-player" id="search-label">Player: </label>
        <input type="text" id="search-player" name="search-player" placeholder="Enter player name" required>
        <button type="submit">Search</button>
        <button type="reset">Clear</button>
    </form>
    <table class="matches-table">
        <thead>
        <tr>
            <th>№</th>
            <th>Player 1</th>
            <th>Player 2</th>
            <th>Winner</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="match" items="${requestScope.matches}" varStatus="idx">
            <tr>
                <td><c:out value="${idx.count}"/></td>
                <td><c:out value="${match.player1.name}"/></td>
                <td><c:out value="${match.player2.name}"/></td>
                <td><c:out value="${match.winner.name}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- TODO: make links instead of form -->
    <div class="pagination">
        <c:if test="${requestScope.page > 1}">
            <form class="prev-page"
                  action="${pageContext.request.contextPath}/matches?page=${requestScope.page - 1}&filter_by_player_name=${requestScope.filter}"
                  method="get">
                <button type="submit">< Prev</button>
            </form>
        </c:if>
        <p>${requestScope.page}</p>
        <c:if test="${requestScope.page < requestScope.maxPage}">
            <form class="next-page"
                  action="${pageContext.request.contextPath}/matches?page=${requestScope.page + 1}&filter_by_player_name=${requestScope.filter}"
                  method="get">
                <button type="submit">Next ></button>
            </form>
        </c:if>

    </div>

</div>
</body>
</html>