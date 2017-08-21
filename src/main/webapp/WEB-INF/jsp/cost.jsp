<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>
    <section>
        <h3><fmt:message key="${cost.isNew() ? 'costs.add' : 'costs.edit'}"/></h3>
        <hr>
        <jsp:useBean id="cost" type="de.shokhor.costs.model.Cost" scope="request"/>
        <form method="post" action="costs">
            <input type="hidden" name="id" value="${cost.id}">
            <dl>
                <dt><fmt:message key="costs.dateTime"/>:</dt>
                <dd><input type="datetime-local" value="${cost.date}" name="dateTime"></dd>
            </dl>
            <dl>
                <dt><fmt:message key="costs.description"/>:</dt>
                <dd><input type="text" value="${cost.description}" size=40 name="description"></dd>
            </dl>
            <dl>
                <dt><fmt:message key="costs.price"/>:</dt>
                <dd><input type="number" value="${cost.price}" name="price"></dd>
            </dl>
            <dl>
                <dt><fmt:message key="costs.group"/>:</dt>
                <dd>
                    <select name="groupId">
                        <c:forEach items="${groupList}" var="group">
                            <jsp:useBean id="group" class="de.shokhor.costs.model.Group"/>
                            <option selected="${cost.isNew()?"":cost.group}" name="groupId" value="${group.id}">${group.group}</option>
                        </c:forEach>
                    </select>
                </dd>
            </dl>
            <button type="submit"><fmt:message key="common.save"/></button>
            <button onclick="window.history.back()"><fmt:message key="common.cancel"/></button>
        </form>
    </section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
