<%@include file="/jsp/jspf/head.jspf" %>


<body>

<%@include file="/jsp/jspf/header.jspf" %>

<div class="container">


    <div class="container">
        <div class="row mb-3">
            <div class="col-sm-8">
                <h2>
                    <fmt:message key="users.users"/>
                </h2>
            </div>
            <div class="col-sm-4 text-end">

                <div class="row ">

                    <div class="dropdown col">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                                data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="users.sortBy"/>
                        </button>

                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">

                            <li>
                                <a class="dropdown-item sort-order" href="#" value="first_name">
                                    <fmt:message key="users.name"/>
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item sort-order" href="#" value="second_name">
                                    <fmt:message key="users.surname"/>
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item sort-order" href="#" value="patronymic">
                                    <fmt:message key="users.patronymic"/>
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item sort-order" href="#" value="email">
                                    <fmt:message key="users.email"/>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <table class="table table-bordered">
                    <thead class="bg-info ">
                    <tr>
                        <th scope="col">
                            <fmt:message key="users.id"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="users.name"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="users.surname"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="users.patronymic"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="users.email"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="users.actions"/>
                        </th>
                    </tr>
                    </thead>
                    <c:forEach var="user" items="${users}">
                    <tbody class="bg-light">
                    <tr>
                        <th scope="row">${user.id}</th>
                        <td> ${user.firstName}</td>
                        <td> ${user.secondName}</td>
                        <td> ${user.patronymic}</td>
                        <td> ${user.email}</td>

                        <td>
                            <div class="row ">
                                <div class="col-md-auto ">
                                    <a href="/profile?userId=${user.id}" class=" btn btn-primary  btn-primary">
                                        <i class="fa fa-eye"></i>
                                    </a>
                                </div>
                                <div class="col-md-auto ">
                                    <form action="/users" class=" mb-0" method="post">

                                        <input type="hidden" name="userId" value="${user.id}">
                                        <input type="hidden" name="userBlocked" value="${user.blocked}">

                                        <c:if test="${user.blocked == 'true'}">
                                            <button class="btn btn-success btn-sm" type="submit">
                                                <fmt:message key="users.unblock"/>
                                            </button>
                                        </c:if>

                                        <c:if test="${user.blocked == 'false'}">
                                            <button class="btn btn-danger btn-sm" type="submit">
                                                <fmt:message key="users.block"/>
                                            </button>
                                        </c:if>
                                    </form>
                                </div>
                            </div>


                        </td>
                    </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="mt-4">
        <tagLib:paginationButtons currentPage="${currentPageNumber}" pages="${pagesNumber}"> </tagLib:paginationButtons>
    </div>


</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pagination.js"></script>
</body>
</html>
