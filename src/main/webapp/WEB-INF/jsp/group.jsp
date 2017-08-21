<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h3><fmt:message key="groups.add"/></h3>
    <hr>
    <jsp:useBean id="group" scope="request" type="de.shokhor.costs.model.Group"/>
    <form method="post" action="costs">
        <input type="hidden" name="id" value="${group.id}">
        <dl>
            <dt><fmt:message key="groups.name"/>:</dt>
            <dd>
                <c:if test="${group.isNew()}">
                    <input type="text" value="${group.group}" name="groupName">
                </c:if>
                <c:if test="${!group.isNew()}">
                    <select name="groupId">
                        <c:forEach items="${groupList}" var="group">
                            <option selected="${group.isNew()?"":group.group}" name="groupId" value="${group.id}">${group.group}</option>
                        </c:forEach>
                    </select>
                </c:if>
            </dd>
        </dl>
        <button type="submit"><fmt:message key="common.save"/></button>
        <button onclick="window.history.back()"><fmt:message key="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
