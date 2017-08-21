<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>
    <div class="jumbotron">
        <div class="container">
            <p/>
            <form method="post" action="users">
                <fmt:message key="app.login"/>: <select name="userId">
                <option value="1">Admin</option>
                <option value="2" selected>User</option>
            </select>
                <button type="submit"><fmt:message key="common.select"/></button>
            </form>
            <ul>
                <li><a href="users"><fmt:message key="users.title"/></a></li>
                <li><a href="costs"><fmt:message key="costs.title"/></a></li>
            </ul>
        </div>
    </div>
</body>
</html>
