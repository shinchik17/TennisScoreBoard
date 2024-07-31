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
    <form class="search-player-form" action="${pageContext.request.contextPath}/matches" method="get">
        <label for="search-player" id="search-label">Player: </label>
        <input type="text" id="search-player" name="filter_by_player_name" placeholder="Enter player name">
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
                <td id="idx"><c:out value="${idx.count + requestScope.max_row_num * (requestScope.page - 1)}"/></td>
                <td><c:out value="${match.player1.name}"/></td>
                <td><c:out value="${match.player2.name}"/></td>
                <td><c:out value="${match.winner.name}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- TODO: make links instead of form -->
    <div class="pagination">
            <form class="prev-page" action="${pageContext.request.contextPath}/matches" method="get">
                <input type="hidden" name="page" value="${requestScope.page - 1}">
                <input type="hidden" name="filter_by_player_name" value="${requestScope.filter_by_player_name}">
                <c:if test="${requestScope.page > 1}">
                    <button type="submit">< Prev</button>
                </c:if>
                <c:if test="${requestScope.page < 2}">
                    <button disabled type="submit">< Prev</button>
                </c:if>
            </form>

        <p>${requestScope.page}</p>
            <form class="next-page" action="${pageContext.request.contextPath}/matches" method="get">
                <input type="hidden" name="page" value="${requestScope.page + 1}">
                <input type="hidden" name="filter_by_player_name" value="${requestScope.filter_by_player_name}">
                <c:if test="${requestScope.matches.size() == requestScope.max_row_num}">
                    <button type="submit">Next ></button>
                </c:if>
                <c:if test="${requestScope.matches.size() < requestScope.max_row_num}">
                    <button disabled type="submit">Next ></button>
                </c:if>
            </form>

    </div>

</div>
</body>
</html>