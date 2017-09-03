<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://costs.shokhor.de/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="costs.title"/></h3>
            <div class="view-box">
                <form:form method="post" class="form-horizontal" role="form" id="filter">
                    <div class="form-costGroup">
                        <label class="control-label col-sm-2" for="startDate"><fmt:message key="costs.startDate"/>:</label>

                        <div class="col-sm-2">
                            <input type="date" class="form-control" name="startDate" id="startDate">
                        </div>

                        <label class="control-label col-sm-2" for="endDate"><fmt:message key="costs.endDate"/>:</label>

                        <div class="col-sm-2">
                            <input type="date" class="form-control" name="endDate" id="endDate">
                        </div>
                    </div>
                    <div class="form-costGroup">
                        <label class="control-label col-sm-2" for="group_filter"><fmt:message key="costs.costGroup"/>:</label>

                        <div class="col-sm-2">
                            <select class="btn btn-default" name="groupId" id="group_filter">
                                <option></option>
                                <c:forEach items="${costGroupList}" var="costGroup">
                                    <jsp:useBean id="costGroup" scope="page" type="de.shokhor.costs.model.CostGroup"/>
                                    <option name="groupId" value="${costGroup.id}">${costGroup.costGroup}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-costGroup">
                        <div class="col-sm-6"></div>
                        <div class="col-sm-1">
                            <button class="btn btn-primary"  type="button" onclick="updateTable()"><fmt:message key="costs.filter"/></button>
                        </div>
                    </div>
                </form:form>
                <a class="btn btn-sm btn-info" onclick="add()"><fmt:message key="costs.add"/></a>
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="costs.costGroup"/></th>
                        <th><fmt:message key="costs.price"/></th>
                        <th><fmt:message key="costs.dateTime"/></th>
                        <th><fmt:message key="costs.description"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <%--<c:forEach items="${costList}" var="cost">
                        <jsp:useBean id="cost" scope="page" type="de.shokhor.costs.model.Cost"/>
                        <tr>
                            <td>${cost.costGroup.costGroup}</td>
                            <td>${cost.price}</td>
                            <td>${fn:formatDateTime(cost.date)}</td>
                            <td>${cost.description}</td>
                            <td><a class="btn btn-xs btn-primary edit"><fmt:message key="common.update"/></a></td>
                            <td><a class="btn btn-xs btn-danger delete" onclick="deleteRow(${cost.id})"><fmt:message key="common.delete"/></a></td>
                        </tr>
                    </c:forEach>--%>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key="costs.edit"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-costGroup">
                        <label for="costGroup" class="control-label col-xs-3"><fmt:message key="costs.costGroup"/></label>

                        <div class="col-xs-9">
                            <select class="btn btn-default" name="groupId" id="costGroup">
                                <c:forEach items="${costGroupList}" var="costGroup">
                                    <%--<jsp:useBean id="costGroup" scope="page" type="de.shokhor.costs.model.CostGrouproup"/>--%>
                                    <option name="groupId" value="${costGroup.id}">${costGroup.costGroup}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-costGroup">
                        <label for="price" class="control-label col-xs-3"><fmt:message key="costs.price"/></label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="price" name="price" placeholder="<fmt:message key="costs.price"/>">
                        </div>
                    </div>

                    <div class="form-costGroup">
                        <label for="date" class="control-label col-xs-3"><fmt:message key="costs.dateTime"/></label>

                        <div class="col-xs-9">
                            <input type="date" class="form-control" id="date" name="date" placeholder="<fmt:message key="costs.dateTime"/>">
                        </div>
                    </div>

                    <div class="form-costGroup">
                        <label for="description" class="control-label col-xs-3"><fmt:message key="costs.description"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description" placeholder="<fmt:message key="costs.description"/>">
                        </div>
                    </div>

                    <div class="form-costGroup">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" class="btn btn-primary" onclick="save()"><fmt:message key="common.save"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var i18n = [];
    <c:forEach var='key' items='<%=new String[]{"common.update","common.delete","common.deleted","common.saved","common.failed"}%>'>
    i18n['${key}'] = '<fmt:message key="${key}"/>';
    </c:forEach>
    var edit_title ='<fmt:message key="users.edit"/>';
</script>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/js/datatablesCost.js"></script>
</html>
