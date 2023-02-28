<%@include file="/jsp/jspf/head.jspf" %>


<body>

<%@include file="/jsp/jspf/header.jspf" %>

<div class="container ">

    <div class="row">
        <h1 class="mb-5">${faculty.name}</h1>

        <div class="card col-4">
            <div class="card-body">
                <h4 class="fw-bold"><fmt:message key="viewFaculty.generalCapacity"/>: ${faculty.generalCapacity}</h4>

                <h4 class="fw-bold"><fmt:message key="viewFaculty.budgetCapacity"/>: ${faculty.budgetCapacity}</h4>

                <h4 class="fw-bold">
                    <fmt:message key="viewFaculty.requiredSubjects"/>:
                </h4>

                <ul class="list-group list-group-flush mt-0 mb-1">
                    <c:forEach var="subject" items="${requiredSubjectList}">
                        <li class="list-group-item">
                            <tagLib:subjectName subject="${subject}"> </tagLib:subjectName>
                        </li>
                    </c:forEach>

                </ul>


                <c:if test="${role == 'ADMIN'}">
                    <div class="row">

                        <div class="col-md-auto">
                            <form action="/view_faculty/delete" method="post">
                                <input type="hidden" name="facultyId" value="${faculty.id}">
                                <button class="btn btn-primary  " type="submit">
                                    <fmt:message key="viewFaculty.delete"/>
                                </button>
                            </form>
                        </div>


                        <div class="col-md-auto">
                            <form action="/change-faculty" method="get">

                                <input type="hidden" name="facultyId" value="${faculty.id}">
                                <button class="btn btn-primary " type="submit">
                                    <fmt:message key="viewFaculty.change"/>
                                </button>
                            </form>
                        </div>

                        <div class="col-md-auto">
                            <form action="/add_recruitment" method="get">
                                <input type="hidden" name="facultyId" value="${faculty.id}">
                                <button class="btn btn-primary " type="submit">
                                    <fmt:message key="viewFaculty.openRecruitment"/>
                                </button>
                            </form>
                        </div>


                    </div>
                </c:if>


            </div>

        </div>

        <div class="col text-center">
            <p>
                <fmt:message key="viewFaculty.openedRecruitments"/>:
            </p>

            <div class="row row-cols-1 row-cols-md-3 g-4">
                <c:forEach var="recruitment" items="${recruitmentsList}">

                    <div class="col">

                        <div class="card h-100">

                            <div class="card-body">
                                <a href="/recruitment?recruitmentId=${recruitment.id}" class="stretched-link"></a>

                                <h5 class="card-title">${recruitment.name}</h5>
                                <p class="card-text">
                                    <tagLib:dateTimeFormat
                                            dateTime="${recruitment.startDate}"> </tagLib:dateTimeFormat>
                                    <tagLib:dateTimeFormat
                                            dateTime="${recruitment.endDate}"> </tagLib:dateTimeFormat>
                                </p>

                                <div class="row">
                                    <div class="col">
                                        <form action="/create-application" method="get">
                                            <input type="hidden" name="recruitmentId" value="${recruitment.id}">
                                            <input type="hidden" name="userId" value="${user.id}">

                                            <c:choose>
                                                <c:when test="${role == 'ADMIN'}">
                                                    <button type="submit" class="btn btn-primary disabled">
                                                        <fmt:message key="recruitments.register"/>
                                                    </button>
                                                </c:when>
                                                <c:when test="${recruitment.closed == true}">
                                                    <button type="submit" class="btn btn-primary disabled">
                                                        <fmt:message key="recruitments.register"/>
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="submit" class="btn btn-primary ">
                                                        <fmt:message key="recruitments.register"/>
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </div>

                                    <c:if test="${role == 'ADMIN'}">
                                        <div class="col">
                                            <form action="/close-recruitment" method="post">
                                                <input type="hidden" name="recruitmentId" value="${recruitment.id}">

                                                <c:choose>
                                                    <c:when test="${recruitment.closed == true}">
                                                        <button type="submit" class="btn btn-primary disabled">
                                                            <fmt:message key="recruitments.close"/>
                                                        </button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button type="submit" class="btn btn-primary ">
                                                            <fmt:message key="recruitments.close"/>
                                                        </button>
                                                    </c:otherwise>
                                                </c:choose>

                                            </form>
                                        </div>
                                    </c:if>

                                </div>


                            </div>

                        </div>

                    </div>

                </c:forEach>


            </div>
        </div>


    </div>

</div>

</body>
</html>
