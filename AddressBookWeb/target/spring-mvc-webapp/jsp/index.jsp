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
        <div class="VC/src/test/javacontainer">

            <jsp:include page="navbar.jsp"></jsp:include>
            
            
           
            <div class="col-md-6">
                
                <h2> Address List </h2>
                
                <table id="contactsTable" class="table">                
                    <tr>
                        <th width="40%">Name</th>
                        <th width="30%">State</th>
                        <th width="15%"></th>
                        <th width="15%"></th>              
                    </tr>
                    
                    <c:forEach items="${addressList}" var="address">
                        <tr id="address-row-${address.id}">
                            <td><a data-address-id="${address.id}" data-toggle="modal" data-target="#showAddressModal" style="cursor:pointer">${address.firstName} ${address.lastName}</a></td>
                            <td>${address.state}</td>
                            <td><a data-address-id="${address.id}" data-toggle="modal" data-target="#editAddressModal" style="cursor:pointer">Edit</a></td>
                            <td><a data-address-id="${address.id}" class="delete-link" style="cursor:pointer">Delete</a></td>                           
                        </tr>
                    </c:forEach>                     
                    
                </table>

            </div>
            
            
            <div class="col-md-6">
                
                <h2>Add New Address</h2>
                
                <form id="create-form" class="form-horizontal">
                    
                    <div class="form-group">
                        <label for="add-first-name" class="col-md-4 control-label">First Name:</label>
                        <div class="col-md-8">
                            <input type="text" name="firstName" class="form-control" id="add-first-name" placeholder="First Name"/>
                            <div id="firstName-adderror"class="text-warning"></div>
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label for="add-last-name" class="col-md-4 control-label">Last Name:</label>
                        <div class="col-md-8">
                            <input type="text" name="lastName" class="form-control" id="add-last-name" placeholder="Last Name"/> 
                            <div id="lastName-adderror"class="text-warning"></div>
                        </div>
                    </div>                   
                    
                    <div class="form-group">
                        <label for="add-address" class="col-md-4 control-label">Street:</label>
                        <div class="col-md-8">
                            <input type="text" name="address" class="form-control" id="add-address" placeholder="Address"/>
                            <div id="address-adderror"class="text-warning"></div>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="add-city" class="col-md-4 control-label">City:</label>
                        <div class="col-md-8">
                            <input type="text" name="city" class="form-control" id="add-city" placeholder="City"/> 
                            <div id="city-adderror"class="text-warning"></div>
                        </div>
                    </div> 
                    
                    <div class="form-group">
                        <label for="add-state" class="col-md-4 control-label">State:</label>
                        <div class="col-md-8">
                            <input type="text" name="state" class="form-control" id="add-state" placeholder="State"/>
                            <div id="state-adderror"class="text-warning"></div>
                        </div>
                    </div>    
                    
                      <div class="form-group">
                        <label for="add-zip" class="col-md-4 control-label">Zip:</label>
                        <div class="col-md-8">
                            <input type="text" name="zip" class="form-control" id="add-zip" placeholder="Zip"/>
                            <div id="zip-adderror"class="text-warning"></div>
                        </div>
                    </div>                    
                    
                   <input type="submit" class="btn btn-default pull-right" value="Create Address" />
                    
                    
                </form>
                   

                
            </div>
        
            <!-- Modal -->
    <div id="showAddressModal" class="modal fade" role="dialog">
     <div class="modal-dialog">

     <!-- Modal content-->
        <div class="modal-content">
         <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
         <h4 class="modal-title">Address Details</h4>
         </div>
         <div class="modal-body">
             
           <table class="table table-bordered">
                  
               <tr>
                   <th>First Name</th>
                   <td id="address-firstName"></td>
               </tr>
               <tr>
                   <th>Last Name</th>
                   <td id="address-lastName"></td>
               </tr>
               <tr>
                   <th>Street</th>
                   <td id="address-address"></td>
               </tr>               
               <tr>
                   <th>City</th>
                   <td id="address-city"></td>
               </tr>   
               <tr>
                   <th>State</th>
                   <td id="address-state"></td>
               </tr>
               <tr>
                   <th>Zip</th>
                   <td id="address-zip"></td>
               </tr>
           </table>    
               
             
             
           
         </div>
        <div class="modal-footer">
            
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
        </div>

     </div>
    </div>     
            
            
             <!-- Modal -->
    <div id="editAddressModal" class="modal fade" role="dialog">
     <div class="modal-dialog">

     <!-- Modal content-->
        <div class="modal-content">
         <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
         <h4 class="modal-title">Edit Address</h4>
         </div>
         <div class="modal-body">
             
           <table class="table table-bordered">
                  
               <tr>
                   <th>First Name</th>
                   <td>
                       <input type="text" id="edit-first-name"/>
                       <div id="firstName-error"class="text-warning"></div>
                   </td>
               </tr>
               <tr>
                   <th>Last Name</th>
                   <td>
                       <input type="text" id="edit-last-name"/>
                       <div id="lastName-error"class="text-warning"></div>
                   </td>
               </tr>
               <tr>
                   <th>Street</th>
                   <td>
                       <input type="text" id ="edit-address"/>
                       <div id="address-error"class="text-warning"></div>
                   </td>
               </tr>               
               <tr>
                   <th>City</th>
                   <td>
                       <input type="text" id="edit-city"/>
                       <div id="city-error"class="text-warning"></div>
                   </td>
               </tr>   
               <tr>
                   <th>State</th>
                   <td>
                       <input type="text" id="edit-state"/>
                       <div id="state-error"class="text-warning"></div>
                   </td>
               </tr>
               <tr>
                   <th>Zip</th>
                   <td>
                       <input type="text" id="edit-zip"/>
                       <div id="zip-error"class="text-warning"></div>
                   </td>
               </tr>
           </table>    

             <input type="hidden" id="edit-id" />
            <button id="edit-address-button" class="btn btn-default">Edit Address</> 
             
           
         </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
        </div>

     </div>
    </div>            
            
            
            
            
                
            <script type="text/javascript">
                
                var contextRoot= "${pageContext.request.contextPath}";
                
            </script>    
            
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app.js"></script>

    </body>
</html>

