<%@include file="/jsp/jspf/head.jspf" %>


<body>

<%@include file="/jsp/jspf/header.jspf" %>

<div class="container">


    <div class="row">
        <div class="col-sm-6 mx-auto">
            <div class="card border-0 shadow">

                <div class="card-header">
                    <h5>
                        <fmt:message key="subject.tittle"/>
                    </h5>
                </div>
                <div class="card-body">

                    <form class="mb-3" action="${pageContext.request.contextPath}/add_subject" method="post">
                        <div class="modal-body">



                            <div class="mb-3">
                                <label for="nameEn" class="col-form-label">
                                    <fmt:message key="language.english"/>:
                                </label>
                                <input type="text" class="form-control" id="nameEn" name="nameEn">
                            </div>

                            <div class="mb-3">
                                <label for="nameRu" class="col-form-label">
                                    <fmt:message key="language.russian"/>:
                                </label>
                                <input type="text" class="form-control" id="nameRu" name="nameRu">
                            </div>

                            <div class="mb-3">
                                <label for="nameUk" class="col-form-label">
                                    <fmt:message key="language.ukrainian"/>:
                                </label>
                                <input type="text" class="form-control" id="nameUk" name="nameUk">
                            </div>
                        </div>


                        <c:if test="${requestScope.emptyFieldException}">
                            <div class="alert alert-warning mb-3" role="alert">
                                <fmt:message key="recruitment.exception.emptyFieldException"/>
                            </div>
                        </c:if>

                        <div class="modal-footer">
                            <button id="click" type="submit" class="btn btn-primary"><fmt:message key="subject.ok"/></button>
                        </div>
                    </form>
                </div>



            </div>
        </div>
    </div>

</div>

</body>
</html>
