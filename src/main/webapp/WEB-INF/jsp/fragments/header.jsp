<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="costs" class="navbar-brand"><fmt:message key="app.title"/></a>

        <div class="collapse navbar-collapse">
            <form:form class="navbar-form navbar-right" action="logout" method="post">
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a class="btn btn-info" role="button" href="users"><fmt:message key="users.title"/></a>
                    </sec:authorize>
                    <c:if test="${pageContext.request.servletPath=='/WEB-INF/jsp/costs.jsp'}">
                        <a class="btn btn-info" role="button" href="groups"><fmt:message key="users.typeCosts"/></a>
                        <a class="btn btn-info" role="button" href="accounts"><fmt:message key="common.accounts"/></a>
                    </c:if>
                    <a class="btn btn-info" role="button" href="profile">${user.sirname} <fmt:message key="app.profile"/></a>
                    <input type="submit" class="btn btn-primary" value="<fmt:message key="app.logout"/>">
                </sec:authorize>
            </form:form>
        </div>
    </div>
</div>
