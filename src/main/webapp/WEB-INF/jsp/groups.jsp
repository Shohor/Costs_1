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
            <h3><fmt:message key="groups.title"/></h3>
            <div class="view-box">
                <a class="btn btn-sm btn-info" onclick="add()"><fmt:message key="groups.add"/></a>
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="groups.name"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <%--<c:forEach items="${typeCostList}" var="typeCosts">
                        <jsp:useBean id="typeCosts" scope="page" type="de.shokhor.costs.model.CostGrouproup"/>
                        <tr>
                            <td>${typeCosts.typeCost}</td>
                            <td><a class="btn btn-xs btn-primary edit"><fmt:message key="common.update"/></a></td>
                            <td><a class="btn btn-xs btn-danger delete" onclick="deleteRow(${typeCosts.id})"><fmt:message key="common.delete"/></a></td>
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
                <h2 class="modal-title"><fmt:message key="groups.edit"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-typeCost">
                        <label for="typeCost" class="control-label col-xs-3"><fmt:message key="groups.add"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="typeCost" name="typeCost" placeholder="<fmt:message key="costs.typeCost"/>">
                        </div>
                    </div>

                    <div class="form-typeCost">
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
<script type="text/javascript" src="resources/js/datatablesGroup.js"></script>
</html>