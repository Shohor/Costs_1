<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://costs.shokhor.de/functions" %>
<html>
    <jsp:include page="fragments/headTag.jsp"/>
    <link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">
    <body>
        <jsp:include page="fragments/header.jsp"/>
        <div class="jumbotron">
            <div class="container">
                <div class="shadow">
                    <h3><fmt:message key="users.title"/></h3>
                    <div class="view-box">
                        <a class="btn btn-sm btn-info" onclick="add()"><fmt:message key="users.add"/></a>
                        <table class="table table-striped display" id="datatable">
                            <thead>
                                <tr>
                                    <th><fmt:message key="users.name"/></th>
                                    <th><fmt:message key="users.sirname"/></th>
                                    <th><fmt:message key="users.age"/></th>
                                    <th><fmt:message key="users.email"/></th>
                                    <th><fmt:message key="users.date"/></th>
                                    <th><fmt:message key="users.roles"/></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <c:forEach items="${userList}" var="user">
                                <jsp:useBean id="user" scope="page" type="de.shokhor.costs.model.User"/>
                                <tr>
                                    <td>${user.firstName}</td>
                                    <td>${user.sirname}</td>
                                    <td>${user.age}</td>
                                    <td><a href="mailto:${user.email}">${user.email}</a></td>
                                    <td>${fn:formatDateTime(user.registred)}</td>
                                    <%--<td><fmt:formatDate value="${user.registred}" pattern="dd-MMMM-yyyy"/></td>--%>
                                    <td>${user.role}</td>
                                    <td><a class="btn btn-xs btn-default costs" href="<c:url value='costs'/>"><fmt:message key="users.costs"/></a></td>
                                    <td><a class="btn btn-xs btn-primary edit"><fmt:message key="common.update"/></a></td>
                                    <td><a class="btn btn-xs btn-danger delete" onclick="deleteRow(${user.id})"><fmt:message key="common.delete"/></a></td>
                                </tr>
                            </c:forEach>
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
                        <h2 class="modal-title"><fmt:message key="users.edit"/></h2>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" method="post" id="detailsForm">
                            <input type="text" hidden="hidden" id="id" name="id">

                            <div class="form-group">
                                <label for="firstName" class="control-label col-xs-3"><fmt:message key="users.name"/></label>

                                <div class="col-xs-9">
                                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="<fmt:message key="users.name"/>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="sirname" class="control-label col-xs-3"><fmt:message key="users.sirname"/></label>

                                <div class="col-xs-9">
                                    <input type="text" class="form-control" id="sirname" name="sirname" placeholder="<fmt:message key="users.sirname"/>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="age" class="control-label col-xs-3"><fmt:message key="users.age"/></label>

                                <div class="col-xs-9">
                                    <input type="number" class="form-control" id="age" name="age" placeholder="<fmt:message key="users.age"/>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="email" class="control-label col-xs-3"><fmt:message key="users.email"/></label>

                                <div class="col-xs-9">
                                    <input type="email" class="form-control" id="email" name="email" placeholder="<fmt:message key="users.email"/>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="password" class="control-label col-xs-3"><fmt:message key="users.password"/></label>

                                <div class="col-xs-9">
                                    <input type="password" class="form-control" id="password" name="password" placeholder="<fmt:message key="users.password"/>">
                                </div>
                            </div>

                            <div class="form-group">
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
    <script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
    <script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
    <script type="text/javascript">

        var ajaxUrl = 'ajax/admin/users/';
        var datatableApi;

        function updateTable() {
            $.get(ajaxUrl, updateTableByData);
        }

        // $(document).ready(function () {
        $(function () {
            datatableApi = $('#datatable').DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "firstName"
                    },
                    {
                        "data": "sirname"
                    },
                    {
                        "data": "age"
                    },
                    {
                        "data": "email"
                    },
                    {
                        "data": "registred"
                    },
                    {
                        "data": "role"
                    },
                    {
                        "defaultContent": "<fmt:message key="users.costs"/>",
                        "orderable": false
                    },
                    {
                        "defaultContent": "<fmt:message key="common.update"/>",
                        "orderable": false
                    },
                    {
                        "defaultContent": "<fmt:message key="common.delete"/>",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ]
            });
            makeEditable();
        });
    </script>
</html>