<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Olivieri Flooring - SEARCH</title>

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style.css" rel='stylesheet' type='text/css' />


        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

        <!----webfonts-->
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
    </head>

    <body>
        <jsp:include page="navbardeep.jsp"></jsp:include>

            <div class="container">

                <div class="banner-info text-center">

                    <h1><font color="black">SEARCH</h1></font><br />

                    <div class="col-md-12">   

                    <form class="form-horizontal" method="post" id="searchForm">

                        <div class="form-group">

                            <label for="add-search" class="col-md-2 control-label">Search all:</label>

                            <div class="col-md-8">

                                <input type="text" name="search1" class="form-control" id="add-search" placeholder="Search here"/>

                            </div>

                            <input type="submit" class="btn btn-default pull-center" value="Submit" />

                        </div>

                    </form> 

                </div>

            </div>
                <table id="searchResults" class="table table-bordered" style="margin-bottom: 30px;">
                    
                </table>

        </div>

    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript">

                            var contextRoot = '${pageContext.request.contextPath}';

        </script>
        <script src="${pageContext.request.contextPath}/js/appSearch.js"></script>
</body>
</html>