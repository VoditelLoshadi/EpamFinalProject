<%@include file="/jsp/jspf/head.jspf" %>


<body>

<%@include file="/jsp/jspf/header.jspf" %>

<div class="container">

    <div class="row mb-4">

        <div class="dropdown col">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                    data-bs-toggle="dropdown" aria-expanded="false">
                <fmt:message key="faculties.sortBy"/>
            </button>

            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">

                <li><a class="dropdown-item sort-order" href="#" value="name_a"> <fmt:message key="faculties.name"/> &#129045;</a></li>
                <li><a class="dropdown-item sort-order" href="#" value="name_z"> <fmt:message key="faculties.name"/> &#129047;</a></li>
                <li><a class="dropdown-item sort-order" href="#" value="general_capacity"> <fmt:message key="faculties.generalCapacity"/></a></li>
                <li><a class="dropdown-item sort-order" href="#" value="budget_capacity"> <fmt:message key="faculties.budgetCapacity"/></a></li>
            </ul>
        </div>


    </div>


    <div class="row row-cols-1 row-cols-md-4 g-4">

        <c:forEach var="faculty" items="${facultyList}">

            <div class="col">
                <div class="card h-100">
                    <div class="card-header">
                        <a style="color: darkcyan" href="/view_faculty?id=${faculty.id}">
                            <c:out value="${faculty.name}"/></a>
                    </div>

                    <div class="card-body">
                        <h5 class="card-title"> <fmt:message key="faculties.info"/> </h5>

                        <p class="card-text"> <fmt:message key="faculties.generalCapacity"/>: ${faculty.generalCapacity}</p>
                        <p class="card-text"> <fmt:message key="faculties.budgetCapacity"/>: ${faculty.budgetCapacity}</p>


                    </div>
                </div>
            </div>


        </c:forEach>

        <c:if test="${role == 'ADMIN'}">

            <div class="col">
                <div class="card h-100">
                    <div class="card-header">
                        <fmt:message key="faculties.addNewFaculty"/>
                    </div>

                    <div class="card-body">

                        <form action="/add_faculty" method="get">

                            <button type="submit" class=" btn  h-100 w-100 fs-1">
                                <i class="fa fa-plus"></i>
                            </button>

                        </form>

                    </div>
                </div>
            </div>

        </c:if>


    </div>

    <div class="mt-4">
        <tagLib:paginationButtons currentPage="${currentPageNumber}" pages="${pagesNumber}"> </tagLib:paginationButtons>
    </div>


</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pagination.js"></script>
</body>
</html>
