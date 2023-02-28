<%@include file="/jsp/jspf/head.jspf" %>


<body>

<%@include file="/jsp/jspf/header.jspf" %>

<div class="container">

    <%-- code --%>


    <div class="row">
        <div class="col-sm-6 mx-auto">
            <div class="card border-0 shadow">

                <div class="card-header">
                    <h5> <fmt:message key="addFaculty.newFaculty"/></h5>
                </div>
                <div class="card-body">
                    <form action="/add_faculty" method="post">
                        <div class="modal-body">

                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingInput"
                                       placeholder="name@example.com"
                                       name="facultyName">
                                <label for="floatingInput">
                                    <fmt:message key="addFaculty.facultyName"/>
                                </label>
                            </div>

                            <div class="form-floating mb-3">
                                <input type="number" class="form-control" id="floatingPassword" placeholder="Password"
                                       name="generalCapacity">
                                <label for="floatingPassword">
                                    <fmt:message key="addFaculty.generalCapacity"/>
                                </label>
                            </div>


                            <div class="form-floating mb-3">
                                <input type="number" class="form-control" id="floatingPassword12" placeholder="Password"
                                       name="budgetCapacity">
                                <label for="floatingPassword12">
                                    <fmt:message key="addFaculty.budgetCapacity"/>
                                </label>
                            </div>


                            <div class="form-floating mb-3">
                                <div class="row  row-cols-lg-3 g-2 g-lg-3 ">
                                    <c:forEach var="subject" items="${subjectList}">


                                        <input type="checkbox" class="btn-check" value="${subject.id}"
                                               id="${subject.id}"
                                               autocomplete="off" name="subjectId">
                                        <label class="btn  p-3 border btn-outline-secondary"
                                               for="${subject.id}">
                                            <tagLib:subjectName subject="${subject}"> </tagLib:subjectName>
                                        </label><br>

                                    </c:forEach>
                                </div>
                            </div>

                            <c:if test="${requestScope.generalCapacityIncorrectException}">
                                <div class="alert alert-warning" role="alert">
                                    <fmt:message key="faculty.exception.generalCapacityIncorrectException"/>
                                </div>
                            </c:if>

                            <c:if test="${requestScope.budgetCapacityIncorrectException}">
                                <div class="alert alert-warning" role="alert">
                                    <fmt:message key="faculty.exception.budgetCapacityIncorrectException"/>
                                </div>
                            </c:if>

                            <c:if test="${requestScope.generalCapacityLessBudgetCapacityException}">
                                <div class="alert alert-warning" role="alert">
                                    <fmt:message key="faculty.exception.generalCapacityLessBudgetCapacityException"/>
                                </div>
                            </c:if>

                            <c:if test="${requestScope.emptyNameFieldException}">
                                <div class="alert alert-warning" role="alert">
                                    <fmt:message key="faculty.exception.emptyNameFieldException"/>
                                </div>
                            </c:if>

                            <c:if test="${requestScope.facultyNameIsReserved}">
                                <div class="alert alert-warning" role="alert">
                                    <fmt:message key="faculty.exception.facultyNameIsReserved"/>
                                </div>
                            </c:if>

                            <c:if test="${requestScope.fewRequiredSubjects}">
                                <div class="alert alert-warning" role="alert">
                                    <fmt:message key="faculty.exception.fewRequiredSubjects"/>
                                </div>
                            </c:if>

                        </div>

                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">
                                <fmt:message key="addFaculty.ok"/>
                            </button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
