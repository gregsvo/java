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

                <div class="row">
                    <div class="col-md-6">

                        <h2>Dvd Library</h2>

                        <table id="dvdTable" class="table">
                            <tr>

                                <th width="45%">Title</th>
                                <th width="40%">Director</th>
                                <th width="10%"></th>
                                <th width="5%"></th>
                            </tr>

                        <c:forEach items="${dvdList}" var="dvd"> <!-- This new row will be added, and each dvd object created in constructor will be printed below-->
                            <tr id="dvd-row-${dvd.id}"> 
                                <td><a data-dvd-id="${dvd.id}" data-toggle="modal" data-target="#showDvdModal" style="cursor:pointer;">${dvd.title} ${dvd.releaseYear}</a></td>
                                <td>${dvd.director}</td>
                                <td><a data-dvd-id="${dvd.id}" data-toggle='modal' data-target='#editDvdModal' style="cursor:pointer;">Edit</a></td> 
                                <td><a data-dvd-id="${dvd.id}" class='delete-link' style="cursor:pointer;">Delete</a></td>
                            </tr>
                        </c:forEach>

                    </table>

                </div>
                <div class="col-md-6" style="text-align: right">

                    <h2>Add New Dvd</h2>

                    <form data-toggle="validator" role="form"class="form-horizontal" id="create-form">

                        <div class="form-group">
                            <label for="add-title" class="col-md-4 control-label">Title:</label>
                            <div class="col-md-8">
                                <input type="text" name="title" class="form-control" id="add-title" placeholder="Title" required></input>

                            </div>
                        </div>


                        <div class="form-group">
                            <label for="add-director" class="col-md-4 control-label">Director:</label>
                            <div class="col-md-8">
                                <input type="text" name="director" class="form-control" id="add-director" placeholder="Director" required></input>

                            </div>
                        </div>


                        <div class="form-group">
                            <label for="add-studio" class="col-md-4 control-label">Studio:</label>
                            <div class="col-md-8">
                                <input type="text" name="studio" class="form-control" id="add-studio" placeholder="Studio" required></input>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="add-mpaaRating" class="col-md-4 control-label">MPAA Rating:</label>
                            <div class="col-md-8">
                                <input type="text" name="mpaaRating" class="form-control" id="add-mpaaRating" placeholder="MPAA Rating" required></input>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-releaseYear" class="col-md-4 control-label">Year Released:</label>
                            <div class="col-md-8">
                                <input type="text" name="releaseYear" class="form-control" id="add-releaseYear" placeholder="Release Year" required></input>
                            </div>
                        </div>

                        <div id="validationErrors" style="color: red"/>
                        <input type="submit" class="btn btn-default pull-right" value="Create DVD" required></input>

                    </form>

                </div>
            </div>
        </div>

        <!--Below is the "showDvdModal", created by bootstrap and populated with information received by app.js and the "showModal" function
        contained therin. The &times within the button makes an "x" symbol within the close button-->

        <div id="showDvdModal" class= "modal fade" role="dialog">
            <div class="modal-dialog">
                <!--Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Dvd Info</h4>

                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered">
                            <tr>
                                <th>Title: </th>
                                <td id="dvd-title"></td>
                            </tr>
                            <tr>
                                <th>Director: </th>
                                <td id="dvd-director"></td>
                            </tr>
                            <tr>
                                <th>Studio: </th>
                                <td id="dvd-studio"></td>
                            </tr>
                            <tr>
                                <th>MPAA Rating: </th>
                                <td id="dvd-mpaaRating"></td>
                            </tr>
                            <tr>
                                <th>Release Year: </th>
                                <td id="dvd-releaseYear"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <!--End Modal Maker-->

        <!--EDIT DVD Modal-->

        <div id="editDvdModal" class= "modal fade" role="dialog">
            <div class="modal-dialog">
                <!--Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Edit Dvd</h4>

                    </div>
                    <div class="modal-body">

                        <form data-toggle="validator" role="form">
                            <div class="form-group">
                                <label for="inputName" class="control-label">Title</label>
                                <input type="text" class="form-control" id="edit-title" required>
                            </div>
                            <div class="form-group">
                                <label for="inputName" class="control-label">Director</label>
                                <input type="text" class="form-control" id="edit-director" required>
                            </div>
                            <div class="form-group">
                                <label for="inputName" class="control-label">Studio</label>
                                <input type="text" class="form-control" id="edit-studio" required>
                            </div>
                            <div class="form-group">
                                <label for="inputName" class="control-label">MPAA Rating</label>
                                <input type="text" class="form-control" id="edit-mpaaRating" required>
                            </div>
                            <div class="form-group">
                                <label for="inputName" class="control-label">Year Released</label>
                                <input type="text" class="form-control" id="edit-releaseYear" required>
                            </div>

                            <div class="form-group">
                                <input type="hidden" id="edit-id"></input>
                                <button type="button" class="btn btn-primary"id="edit-dvd-button">Edit Dvd</button>

                            </div>
                        </form>
                        <div id="editValidationErrors" style="color: red"/>
                        <div class="modal-footer">


                            <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Close</button>

                        </div>
                    </div>
                </div>
            </div>





            <!-- Placed at the end of the document so the pages load faster -->
            <script type="text/javascript"> var contextRoot = "${pageContext.request.contextPath}"</script>
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/app.js"></script>

    </body>
</html>

