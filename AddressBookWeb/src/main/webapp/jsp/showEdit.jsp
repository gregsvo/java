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
            
            
           

            
            
            <div class="col-md-6">
                
                <h2>Edit Address</h2>
                
                <form:form class="form-horizontal" commandName="address" action="${pageContext.request.contextPath}/address/edit/${address.id}" method="post">
                    
                    <div class="form-group">
                        <label for="add-first-name" class="col-md-4 control-label">First Name:</label>
                        <div class="col-md-8">
                            <form:input path="firstName" type="text" name="firstName" class="form-control" id="add-first-name" placeholder="First Name"/> 
                            <form:errors path="firstName"/>
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label for="add-last-name" class="col-md-4 control-label">Last Name:</label>
                        <div class="col-md-8">
                            <form:input path="lastName" type="text" name="lastName" class="form-control" id="add-last-name" placeholder="Last Name"/> 
                            <form:errors path="lastName"/>
                        </div>
                    </div>                   
                    
                    <div class="form-group">
                        <label for="add-address" class="col-md-4 control-label">Address:</label>
                        <div class="col-md-8">
                            <form:input path="address" type="text" name="address" class="form-control" id="add-address" placeholder="Address"/> 
                            <form:errors path="address"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="add-city" class="col-md-4 control-label">City:</label>
                        <div class="col-md-8">
                            <form:input path="city" type="text" name="city" class="form-control" id="add-city" placeholder="City"/>
                            <form:errors path="city"/>
                        </div>
                    </div> 
                    
                    <div class="form-group">
                        <label for="add-phone" class="col-md-4 control-label">State:</label>
                        <div class="col-md-8">
                            <form:input path="state" type="text" name="state" class="form-control" id="add-state" placeholder="State"/> 
                            <form:errors path="state"/>
                        </div>
                    </div>  
                        
                    <div class="form-group">
                        <label for="add-zip" class="col-md-4 control-label">Zip:</label>
                        <div class="col-md-8">
                            <form:input path="zip" type="text" name="zip" class="form-control" id="add-zip" placeholder="Zip"/>
                            <form:errors path="zip"/>
                        </div>
                    </div>                           
                    
                   <input type="submit" class="btn btn-default pull-right" value="Create Address" />
                    
                    
                </form:form>
                
                
                
                
                
                
                
                
                
            </div>
           
                
           

            
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

