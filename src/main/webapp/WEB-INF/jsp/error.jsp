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
    <h1>Error</h1>
    <p class="error">${requestScope.error_info}</p>

</div>
</body>
</html>