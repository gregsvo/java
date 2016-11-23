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

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">

            <jsp:include page="navbar.jsp"></jsp:include>

            
                <div class="col-md-12">   
            
<!--                <form:form class="form-horizontal" action="${pageContext.request.contextPath}/address/viewsearch" method="get">
                    
                    <div class="form-group">
                        <label for="add-search" class="col-md-4 control-label">Search:</label>
                        <div class="col-md-6">
                            <input type="text" name="search" class="form-control" id="add-search" placeholder="Search"/> 

                            <input checked="checked" type="radio" name="name" value="firstName"> First Name<br>
                            <input type="radio" name="name" value="lastName"> Last Name<br>
                            <input type="radio" name="name" value="address"> Address<br>
                            <input type="radio" name="name" value="city"> City<br>
                            <input type="radio" name="name" value="state"> State<br>
                            <input type="radio" name="name" value="zip"> Zip<br>
               
                    </div>
                    
                    
                   <input type="submit" class="btn btn-default pull-right" value="Submit" />
                    
                    
                </form:form>   
                   -->

                   
                <form:form class="form-horizontal" action="${pageContext.request.contextPath}/address/viewsearchall" method="get">
                    
                    <div class="form-group">
                        <label for="add-search2" class="col-md-2 control-label">Search all:</label>
                        <div class="col-md-8">
                            <input type="text" name="search1" class="form-control" id="add-search" placeholder="Please enter a First/Last name, Address, City, State, or Zip"/> 
               
                    </div>
                    
                        
                    
                   <input type="submit" class="btn btn-default pull-center" value="Submit" />
                    
                    </div>
                </form:form>                    
           
                    </div>

            
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>