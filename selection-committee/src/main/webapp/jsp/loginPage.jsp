<%@include file="/jsp/jspf/head.jspf" %>

<body class="text-center">

<%@include file="jspf/header.jspf" %>


<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">

            <div class="card border-0 shadow rounded-3 my-5">
                <div class="card-body p-4 p-sm-5">
                    <h5 class="card-title text-center mb-5 fw-light fs-5">
                        <fmt:message key="loginPage.title"/>
                    </h5>

                    <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post"
                          autocomplete="on">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingInput" name="username"
                                   placeholder="Username">
                            <label for="floatingInput"><fmt:message key="loginPage.login"/></label>
                        </div>

                        <c:if test="${requestScope.authenticationException}">
                            <div class="alert alert-warning" role="alert">
                                <fmt:message key="loginPage.exception.authenticationException"/>
                            </div>
                        </c:if>

                        <c:if test="${requestScope.accountIsBlocked}">
                            <p class="text-center error-message mt-3"><fmt:message
                                    key="loginPage.exception.accountIsBlocked"/></p>
                        </c:if>

                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="floatingPassword" name="password"
                                   placeholder="Password">
                            <label for="floatingPassword"><fmt:message key="loginPage.password"/></label>
                        </div>


                        <hr class="my-4">

                        <div class="d-grid">
                            <button class="btn btn-primary btn-login text-uppercase fw-bold" type="submit">
                                <fmt:message key="loginPage.loginButton"/>
                            </button>
                        </div>

                        <div class="form-text text-center mt-4 mb-2">
                        <span><fmt:message key="loginPage.dontHaveAccount"/> <a href="/registration"><fmt:message
                                key="loginPage.signUp"/></a></span>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
</html>