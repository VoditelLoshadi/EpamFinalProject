<%@include file="/jsp/jspf/head.jspf" %>


<body>

<%@include file="/jsp/jspf/header.jspf" %>

<div class="container">

    <div class="row">
        <div class="col-sm-6 mx-auto">
            <div class="card border-0 shadow">

                <div class="card-header">
                    <h3>
                        <fmt:message key="profile.title"/>
                    </h3>
                </div>

                <div class="card-body">
                    <dl class="row">
                        <dt class="col-sm-4">
                            <fmt:message key="profile.firstName"/>:
                        </dt>
                        <dd class="col-sm-8">${user_profile.firstName}</dd>

                        <dt class="col-sm-4">
                            <fmt:message key="profile.secondName"/>:
                        </dt>
                        <dd class="col-sm-8"> ${user_profile.secondName} </dd>

                        <dt class="col-sm-4">
                            <fmt:message key="profile.patronymic"/>:
                        </dt>
                        <dd class="col-sm-8"> ${user_profile.patronymic} </dd>

                        <dt class="col-sm-4">
                            <fmt:message key="profile.email"/>:
                        </dt>
                        <dd class="col-sm-8"> ${user_profile.email}</dd>

                        <dt class="col-sm-4">
                            <fmt:message key="profile.city"/>:
                        </dt>
                        <dd class="col-sm-8"> ${user_profile.city}</dd>

                        <dt class="col-sm-4">
                            <fmt:message key="profile.region"/>:
                        </dt>
                        <dd class="col-sm-8"> ${user_profile.region}</dd>

                        <dt class="col-sm-4">
                            <fmt:message key="profile.institution"/>:
                        </dt>
                        <dd class="col-sm-8"> ${user_profile.institution}</dd>

                        <dt class="col-sm-4">
                            <fmt:message key="profile.history"/>:
                        </dt>
                        <dd class="col-sm-8"></dd>

                        <c:forEach var="application" items="${applications}">
                            <dt class="col-sm-6">
                                <a href="/application?id=${application.id}">
                                        ${application.recruitment.name}
                                </a>
                            </dt>
                            <dd class="col-sm-6"> ${application.state} </dd>
                        </c:forEach>

                    </dl>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
