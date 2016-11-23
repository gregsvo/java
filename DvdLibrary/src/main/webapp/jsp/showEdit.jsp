<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">

            <jsp:include page="navbar.jsp"></jsp:include>

                <div class="row">

                    <div class="col-md-6">

                        <h2>Edit Movie</h2>

                    <form:form class="form-horizontal" commandName="dvd" action="${pageContext.request.contextPath}/dvd/edit/${dvd.id}" method="post">

                        <div class="form-group">
                            <label for="add-title" class="col-md-4 control-label">Movie Title:</label>
                            <div class="col-md-8">
                                <form:input path="title" type="text" name="title" class="form-control" id="add-title" placeholder="Title" />
                                <form:errors path="title" />
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="add-director" class="col-md-4 control-label">Director:</label>
                            <div class="col-md-8">
                                <form:input path="director" type="text" name="director" class="form-control" id="add-director" placeholder="Director" />
                                <form:errors path="director"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="add-studio" class="col-md-4 control-label">Studio</label>
                            <div class="col-md-8">
                                <form:input path="studio" type="text" name="studio" class="form-control" id="add-studio" placeholder="Studio" />
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="add-mpaaRating" class="col-md-4 control-label">MPAA Rating:</label>
                            <div class="col-md-8">
                                <form:input path="mpaaRating" type="text" name="mpaaRating" class="form-control" id="add-rating" placeholder="MPAA Rating" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-releaseYear" class="col-md-4 control-label">Release Year:</label>
                            <div class="col-md-8">
                                <form:input path="releaseYear" type="text" name="releaseYear" class="form-control" id="add-releaseYear" placeholder="Year Released" />
                            </div>
                        </div>

                        <input type="submit" class="btn btn-default pull-right" value="Edit DVD Record" />


                    </form:form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

