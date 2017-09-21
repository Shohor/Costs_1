<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cost" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/header.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3>
                <c:if test="${register}">
                    <fmt:message key="app.register"/>
                    <fmt:message key="common.add" var="saveButton"/>
                </c:if>
                <c:if test="${not register}">
                    ${userTo.name} <fmt:message key="app.profile"/>
                    <fmt:message key="common.update" var="saveButton"/>
                </c:if>
            </h3>

            <div class="view-box">
                <form:form modelAttribute="user" class="form-horizontal" method="post"
                           action="${register ? 'register' : 'profile'}" charset="utf-8" accept-charset="UTF-8">

                    <fmt:message key="users.name" var="firstName"/>
                    <cost:inputField label='${firstName}' name="firstName"/>

                    <fmt:message key="users.sirname" var="sirname"/>
                    <cost:inputField label='${sirname}' name="sirname"/>

                    <fmt:message key="users.age" var="age"/>
                    <cost:inputField label='${age}' name="age" inputType="number"/>

                    <fmt:message key="users.email" var="email"/>
                    <cost:inputField label='${email}' name="email"/>

                    <fmt:message key="users.password" var="password"/>
                    <cost:inputField label='${password}' name="password" inputType="password"/>


                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-10">
                            <button type="submit" class="btn btn-primary">${saveButton}</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
