<%@ page import="ua.epam.elearn.selection.committee.model.entity.Subject" %>
<%@include file="/jsp/jspf/head.jspf" %>


<body>

<%@include file="/jsp/jspf/header.jspf" %>

<div class="container">

    <%-- code --%>

    <div class="container">
        <div class="row mb-3">
            <div class="col-sm-8">
                <h2><fmt:message key="subjects.subjects"/> <b><fmt:message key="subjects.details"/></b></h2>
            </div>
            <div class="col-sm-4 text-end">
                <a href="/add_subject" class="btn btn-info add-new"><i class="fa fa-plus"></i>
                    <fmt:message key="subjects.addNewSubject"/>
                </a>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <table class="table table-bordered">
                    <thead class="bg-info ">
                    <tr>
                        <th scope="col">
                            <fmt:message key="subjects.id"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="subjects.nameEn"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="subjects.nameRu"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="subjects.nameUk"/>
                        </th>
                        <th scope="col">
                            <fmt:message key="subjects.actions"/>
                        </th>
                    </tr>
                    </thead>
                    <c:forEach var="subject" items="${subjectList}">
                    <tbody class="bg-light">
                    <tr>
                        <th scope="row">${subject.id}</th>
                        <td>${subject.nameEn}</td>
                        <td>${subject.nameRu}</td>
                        <td>${subject.nameUk}</td>

                        <td>
                            <button type="button" class=" btn btn-primary"><i class="fa fa-eye"></i></button>
                            <button type="button" class="btn btn-success"><i class="fas fa-edit"></i></button>
                            <button type="button" class="btn btn-danger"><i class="far fa-trash-alt"></i></button>
                        </td>
                    </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>


</div>

</body>
</html>
