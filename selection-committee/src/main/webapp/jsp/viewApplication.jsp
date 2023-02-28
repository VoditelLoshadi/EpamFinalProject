<%@include file="/jsp/jspf/head.jspf" %>


<body>

<%@include file="/jsp/jspf/header.jspf" %>

<div class="container">

    <div class="row">
        <div class="col-sm-5 mx-auto">
            <div class="card border-0 shadow">

                <div class="card-header">
                    <h3> ${recruitment.name} </h3>
                </div>

                <div class="card-body">

                    <dl class="row">
                        <dt class="col-sm-4">
                            <fmt:message key="application.name"/>:
                        </dt>
                        <dd class="col-sm-8">
                            ${application.user.firstName}
                        </dd>

                        <dt class="col-sm-4">
                            <fmt:message key="application.surname"/>:
                        </dt>
                        <dd class="col-sm-8">
                            ${application.user.secondName}
                        </dd>

                        <dt class="col-sm-4">
                            <fmt:message key="application.patronymic"/>:
                        </dt>
                        <dd class="col-sm-8">
                            ${application.user.patronymic}
                        </dd>

                        <dt class="col-sm-4">
                            <fmt:message key="application.averageScore"/>:
                        </dt>
                        <dd class="col-sm-8">
                            ${application.averageGrade}
                        </dd>

                        <dt class="col-sm-4">
                            <fmt:message key="application.state"/>:
                        </dt>
                        <dd class="col-sm-8">
                            ${application.state}
                        </dd>

                        <dt class="col-sm-4">
                            <fmt:message key="application.subjects"/>:
                        </dt>
                        <dd class="col-sm-8">
                            <dl class="row">
                                <c:forEach var="subject" items="${application.subjects}">
                                    <dt class="col-sm-8">
                                       <tagLib:subjectName subject="${subject}"> </tagLib:subjectName> :
                                    </dt>
                                    <dd class="col-sm-4">
                                        <c:out value="${subject.grade.grade}"/>
                                    </dd>
                                </c:forEach>
                            </dl>
                        </dd>

                    </dl>


                    <div class="modal-footer">
                        <a class="btn btn-secondary" href="/recruitment?recruitmentId=${recruitment.id}">
                            <fmt:message key="application.close"/>
                        </a>
                    </div>


                </div>
            </div>
        </div>
    </div>


</div>

</body>
</html>
