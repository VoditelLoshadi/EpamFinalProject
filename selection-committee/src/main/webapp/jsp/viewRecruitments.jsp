<%@include file="/jsp/jspf/head.jspf" %>


<body>

<style>
    .card .btn {
        z-index: 2;
        position: relative;
    }
</style>

<%@include file="/jsp/jspf/header.jspf" %>

<div class="container">


    <div class="row mb-4">

        <div class="dropdown col">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                    data-bs-toggle="dropdown" aria-expanded="false">
                <fmt:message key="recruitments.sortBy"/>
            </button>

            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">

                <li>
                    <a class="dropdown-item sort-order" href="#" value="faculty.name">
                        <fmt:message key="recruitments.facultyName"/>
                    </a>
                </li>
                <li>
                    <a class="dropdown-item sort-order" href="#" value="recruitment.name">
                        <fmt:message key="recruitments.name"/>
                    </a>
                </li>
                <li>
                    <a class="dropdown-item sort-order" href="#" value="start_date">
                        <fmt:message key="recruitments.startDate"/>
                    </a>
                </li>
                <li>
                    <a class="dropdown-item sort-order" href="#" value="end_date">
                        <fmt:message key="recruitments.endDate"/>
                    </a>
                </li>
            </ul>
        </div>


        <div class="col col-lg-5 text-end">
            <fmt:message key="recruitments.filter"/>:
            <div class="form-check form-check-inline ">

                <c:if test="${previous}">
                    <input class="form-check-input filter-check" type="checkbox" id="inlineCheckbox3"
                           name="filter-check"
                           value="previous" checked>

                </c:if>
                <c:if test="${!previous}">
                    <input class="form-check-input filter-check" type="checkbox" id="inlineCheckbox3"
                           name="filter-check"
                           value="previous">
                </c:if>

                <label class="form-check-label" for="inlineCheckbox3">
                    <fmt:message key="recruitments.previous"/>
                </label>
            </div>
            <div class="form-check form-check-inline">
                <c:if test="${current}">
                    <input class="form-check-input filter-check" type="checkbox" id="inlineCheckbox2"
                           name="filter-check"
                           value="current" checked>
                </c:if>
                <c:if test="${!current}">
                    <input class="form-check-input filter-check" type="checkbox" id="inlineCheckbox2"
                           name="filter-check"
                           value="current">
                </c:if>

                <label class="form-check-label" for="inlineCheckbox2">
                    <fmt:message key="recruitments.current"/>
                </label>
            </div>
            <div class="form-check form-check-inline">
                <c:if test="${future}">
                    <input class="form-check-input filter-check" type="checkbox" id="inlineCheckbox1"
                           name="filter-check"
                           value="future" checked>
                </c:if>
                <c:if test="${!future}">
                    <input class="form-check-input filter-check" type="checkbox" id="inlineCheckbox1"
                           name="filter-check"
                           value="future">
                </c:if>

                <label class="form-check-label" for="inlineCheckbox1">
                    <fmt:message key="recruitments.future"/>
                </label>
            </div>
        </div>
    </div>
    <div class="row row-cols-1 row-cols-md-4 g-4">

        <c:forEach var="recruitment" items="${recruitmentMap}">


            <div class="col">

                <div class="card h-100">
                    <div class="card-header">
                            ${recruitment.value.name}
                    </div>
                    <div class="card-body">
                        <a href="/recruitment?recruitmentId=${recruitment.key.id}" class="stretched-link"></a>

                        <h5 class="card-title">
                            <fmt:message key="recruitments.recruitment"/>:
                        </h5>
                        <p class="card-text">${recruitment.key.name}</p>
                        <p class="card-text">
                            <tagLib:dateTimeFormat
                                    dateTime="${recruitment.key.startDate}"> </tagLib:dateTimeFormat>
                            <tagLib:dateTimeFormat
                                    dateTime="${recruitment.key.endDate}"> </tagLib:dateTimeFormat>
                        </p>

                        <div class="row text-center">
                            <div class="col">
                                <form action="/create-application" method="get">
                                    <input type="hidden" name="recruitmentId" value="${recruitment.key.id}">
                                    <input type="hidden" name="userId" value="${user.id}">

                                    <c:choose>
                                        <c:when test="${role == 'ADMIN'}">
                                            <button type="submit" class="btn btn-primary disabled">
                                                <fmt:message key="recruitments.register"/>
                                            </button>
                                        </c:when>
                                        <c:when test="${recruitment.key.closed == true}">
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
                                        <input type="hidden" name="recruitmentId" value="${recruitment.key.id}">

                                        <c:choose>
                                            <c:when test="${recruitment.key.closed == true}">
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

    <div class="mt-4">
        <tagLib:paginationButtons currentPage="${currentPageNumber}" pages="${pagesNumber}"> </tagLib:paginationButtons>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pagination.js"></script>

</body>
</html>
