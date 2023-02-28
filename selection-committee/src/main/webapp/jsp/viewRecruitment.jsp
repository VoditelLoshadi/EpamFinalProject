<%@include file="/jsp/jspf/head.jspf" %>


<body>

<%@include file="/jsp/jspf/header.jspf" %>

<div class="container">
    <h1>
        <c:out value="${recruitment.name}"/>
    </h1>

    <div class="row">
        <div class="col-12">
            <table class="table table-bordered">
                <thead class="bg-info ">
                <tr>
                    <th scope="col">
                        <fmt:message key="recruitment.name"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="recruitment.surname"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="recruitment.patronymic"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="recruitment.score"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="recruitment.status"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="recruitment.actions"/>
                    </th>
                </tr>
                </thead>
                <c:forEach var="application" items="${applicationList}">
                <tbody class="bg-light">
                <tr>
                    <th >${application.user.firstName}</th>
                    <td>${application.user.secondName}</td>
                    <td>${application.user.patronymic}</td>
                    <td>${application.averageGrade}</td>
                    <td>${application.state}</td>

                    <td>
                        <a href="/application?id=${application.id}" class=" btn btn-primary">
                            <i class="fa fa-eye"></i>
                        </a>
                    </td>
                </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>



</div>

</body>
</html>
