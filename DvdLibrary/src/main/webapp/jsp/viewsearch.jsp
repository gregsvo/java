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
            <h1>Dvd Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/dvd/search">Search</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6" style="text-align: left">
                        <c:choose>
                            <c:when test="${empty dvdList}">
                                <p>I couldn't find any DVD's from the search term: ${search}.
                                </p>
                                <p>
                                    <a href="${pageContext.request.contextPath}/dvd/search">Try again?</a>
                                </p>
                            </c:when>    
                            <c:otherwise>
                                <c:forEach items="${dvdList}" var="dvd">
                                    <h3>${dvd.title} (${dvd.releaseYear})</h3>
                                    <p>${dvd.director}</p>
                                    <p>${dvd.studio}</p>
                                    <p>${dvd.mpaaRating}</p>
                                    <p>${dvd.releaseYear}</p>
                                </c:forEach>
                                    <br />
                                    <a href="${pageContext.request.contextPath}/dvd/search">Back to search ...</a>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

        </body>
    </html>

