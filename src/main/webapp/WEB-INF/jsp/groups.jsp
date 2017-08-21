<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 22.07.2017
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section>
    <a href="groups/create"><fmt:message key="costs.add"/></a>
    <table border="1", cellpadding="8", cellspacing="0">
        <thead>
        <tr>
            <td>Group name</td>
            <td></td>
            <td></td>
        </tr>
        </thead>
        <c:forEach items="${groupList}" var="group">
            <jsp:useBean id="group" scope="page" type="de.shokhor.costs.model.Group"/>
            <tr>
                <td>${group.group}</td>
                <td><a href="groups/update?id=${group.id}"><fmt:message key="common.update"/></a></td>
                <td><a href="groups/delete?id=${group.id}"><fmt:message key="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
