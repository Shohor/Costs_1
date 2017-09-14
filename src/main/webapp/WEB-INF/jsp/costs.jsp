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
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    Параметры фильтрации
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                            <div class="panel-body">
                                <form:form method="post" class="form-horizontal" role="form" id="filter">
                                    <div class="form-group">
                                        <label class="control-label col-sm-2" for="startDate"><fmt:message key="costs.startDate"/>:</label>

                                        <div class="col-sm-2">
                                            <input type="date" class="form-control" name="startDate" id="startDate">
                                        </div>

                                        <label class="control-label col-sm-2" for="endDate"><fmt:message key="costs.endDate"/>:</label>

                                        <div class="col-sm-2">
                                            <input type="date" class="form-control" name="endDate" id="endDate">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-2" for="cashAccountsAndCards_filter"><fmt:message key="costs.typeCost"/>:</label>

                                        <div class="col-xs-9">
                                            <select class="btn btn-default" name="cashAccountsAndCardsId" id="cashAccountsAndCards_filter">
                                                <option></option>
                                                <c:forEach items="${CashAccountsAndCardsList}" var="cashAccountsAndCards">
                                                    <jsp:useBean id="cashAccountsAndCards" scope="page" type="de.shokhor.costs.model.CashAccountsAndCards"/>
                                                    <option name="type" value="${cashAccountsAndCards.id}">${cashAccountsAndCards.type}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div id="incomeFilter">
                                            <label class="control-label col-sm-2" for="typeIncome_filter"><fmt:message key="costs.typeCost"/>:</label>

                                            <div class="col-sm-2">
                                                <select class="btn btn-default" name="typeIncomeId" id="typeIncome_filter">
                                                    <option></option>
                                                    <c:forEach items="${typeIncomeList}" var="typeIncome">
                                                        <jsp:useBean id="typeIncome" scope="page" type="de.shokhor.costs.model.Income.TypeIncome"/>
                                                        <option name="typeIncome" value="${typeIncome.id}">${typeIncome.type}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div id="costFilter">
                                            <label class="control-label col-sm-2" for="typeCost_filter"><fmt:message key="costs.typeCost"/>:</label>

                                            <div class="col-sm-2">
                                                <select class="btn btn-default" name="typeCostId" id="typeCost_filter">
                                                    <option></option>
                                                    <c:forEach items="${typeCostList}" var="typeCost">
                                                        <jsp:useBean id="typeCost" scope="page" type="de.shokhor.costs.model.Cost.TypeCost"/>
                                                        <option name="typeCost" value="${typeCost.id}">${typeCost.type}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-6"></div>
                                        <div class="col-sm-6">
                                            <button class="btn btn-danger" type="reset">Сброс</button>
                                            <button class="btn btn-primary"  type="button" onclick="updateTableFilter()"><fmt:message key="costs.filter"/></button>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="btn btn-sm btn-info" onclick="add()"><fmt:message key="costs.add"/></a>
                <p/>
                <div class="form-group">
                    <div class="btn-group " role="group" aria-label="...">
                        <button type="button" class="btn btn-primary" onclick="costs_and_incomes()">Summary</button>
                        <button type="button" class="btn btn-primary" onclick="costs()">Costs</button>
                        <button type="button" class="btn btn-primary" onclick="incomes()">Incomes</button>
                    </div>
                </div>
                <hr color="">
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="costs.dateTime"/></th>
                        <th><fmt:message key="costs.typeCost"/></th>
                        <th><fmt:message key="costs.amount"/></th>
                        <th>Cards</th>
                        <th><fmt:message key="costs.description"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th></th>
                            <th>Итого:</th>
                            <th id="summ"></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </tfoot>
                    <%--<c:forEach items="${costList}" var="costAndIncome">
                        <jsp:useBean id="costAndIncome" scope="page" type="de.shokhor.costs.model.Cost.Cost"/>
                        <tr>
                            <td>${costAndIncome.typeCost.typeCost}</td>
                            <td>${costAndIncome.amount}</td>
                            <td>${fn:formatDateTime(costAndIncome.date)}</td>
                            <td>${costAndIncome.description}</td>
                            <td><a class="btn btn-xs btn-primary edit"><fmt:message key="common.update"/></a></td>
                            <td><a class="btn btn-xs btn-danger delete" onclick="deleteRow(${costAndIncome.id})"><fmt:message key="common.delete"/></a></td>
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
                    <div class="form-group">
                        <label for="costOrIncome" class="control-label col-xs-3"><fmt:message key="costs.typeCost"/></label>

                        <select class="btn btn-default" id="costOrIncome" onChange="selectCostOrIncome(this.options[this.selectedIndex].value)">
                            <option value="cost">Cost</option>
                            <option value="income" selected>Income</option>
                        </select>
                    </div>
                    <hr color="black"/>
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div id="costTypeSelect">
                        <div class="form-group">
                            <label for="typeCost" class="control-label col-xs-3"><fmt:message key="costs.typeCost"/></label>

                            <div class="col-xs-9">
                                <select class="btn btn-default" name="typeId" id="typeCost">
                                    <c:forEach items="${typeCostList}" var="typeCost">
                                        <%--<jsp:useBean id="typeCost" scope="page" type="de.shokhor.costs.model.CostGrouproup"/>--%>
                                        <option name="type" value="${typeCost.id}">${typeCost.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div hidden="hidden" id="incomeTypeSelect">
                        <div class="form-group">
                            <label for="typeCost" class="control-label col-xs-3"><fmt:message key="costs.typeCost"/></label>

                            <div class="col-xs-9">
                                <select class="btn btn-default" name="typeId" id="typeIncome">
                                    <c:forEach items="${typeIncomeList}" var="typeIncome">
                                        <%--<jsp:useBean id="typeIncome" scope="page" type="de.shokhor.costs.model.Income.TypeIncome"/>--%>
                                        <option name="type" value="${typeIncome.id}">${typeIncome.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="typeCost" class="control-label col-xs-3"><fmt:message key="costs.typeCost"/></label>

                        <div class="col-xs-9">
                            <select class="btn btn-default" name="cashAccountsAndCardsId" id="cashAccountsAndCards">
                                <c:forEach items="${CashAccountsAndCardsList}" var="cashAccountsAndCards">
                                    <%--<jsp:useBean id="cashAccountsAndCards" scope="page" type="de.shokhor.costs.model.CashAccountsAndCards"/>--%>
                                    <option name="type" value="${cashAccountsAndCards.id}">${cashAccountsAndCards.type}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="amount" class="control-label col-xs-3"><fmt:message key="costs.amount"/></label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="amount" name="amount" placeholder="<fmt:message key="costs.amount"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="date" class="control-label col-xs-3"><fmt:message key="costs.dateTime"/></label>

                        <div class="col-xs-9">
                            <input type="date" class="form-control" id="date" name="date" placeholder="<fmt:message key="costs.dateTime"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3"><fmt:message key="costs.description"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description" placeholder="<fmt:message key="costs.description"/>">
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
