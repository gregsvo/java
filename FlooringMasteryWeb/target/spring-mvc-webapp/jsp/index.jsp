<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Olivieri Flooring</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}css/animate.css" rel="stylesheet" type="text/css" media="all">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet" type='text/css' />

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">


        <!--Javascript for date range picker by daterangepicker.com-->
        <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
        <!--        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>-->
        <script src="${pageContext.request.contextPath}/js/moment.min.js"></script>         
        <script src="${pageContext.request.contextPath}/js/daterangepicker.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/daterangepicker.css" />






        <script type="text/javascript">
            $(function () {
                $('input[name="date"]').daterangepicker({
                    singleDatePicker: true,
                    showDropdowns: true
                });
            });

            function getDate() {
                var today = new Date();
                var dd = today.getDate();
                var mm = today.getMonth() + 1;
                var yy = today.getFullYear();

            }

            document.getElementById('date').value = getDate();
        </script>


        <!---- animated-css ---->
        <!---- start-smoth-scrolling---->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/move-top.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/easing.js"></script>
        <script type="text/javascript">
            jQuery(document).ready(function ($) {
                $(".scroll").click(function (event) {
                    event.preventDefault();
                    $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
                });
            });
        </script>
        <!---- start-smoth-scrolling---->
        <link href="${pageContext.request.contextPath}/css/style.css" rel='stylesheet' type='text/css' />
        <!-- Custom Theme files -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    </script>
    <!----webfonts-->
    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
    <!---//webfonts--->
    <!----start-top-nav-script---->
    <script>
        $(function () {
            var pull = $('#pull');
            menu = $('nav ul');
            menuHeight = menu.height();
            $(pull).on('click', function (e) {
                e.preventDefault();
                menu.slideToggle();
            });
            $(window).resize(function () {
                var w = $(window).width();
                if (w > 320 && menu.is(':hidden')) {
                    menu.removeAttr('style');
                }
            });
        });
    </script>
    <!----//End-top-nav-script---->

</head>
<body>

    <div class="bg">
        <!----<jsp:include page="navbar.jsp"></jsp:include>---->


            <!----- //End-header---->
            <!---- banner ---->

            <div class="container">
                <div class="banner-info text-center">
                    <h1>Olivieri Flooring</h1><br />
                    <span> </span>
                    <p>We are a flooring company who's work is unparalleled.</p>
                </div>
            </div>
        </div>

        <div id="vieworder" class="services">
            <div class="container">
                <div class="service-head text-center">
                    <h2>View Orders</h2>
                    <span> </span>
                </div>

            </div>
        </div>
        <div class="container">
            <div class="col-md-12">
                <div class="container">
                <form:form class="form-horizontal" action="${pageContext.request.contextPath}/" method="get">
                    <div class="form-group">
                        <div class="col-md-2">
                            <label for="add-date" class="col-md-4 control-label">Date:</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="date" class="form-control" value=""/> 
                        </div> 
                        <div class="col-md-2">
                            <input type="submit" class="btn btn-default pull-right" value="View" />
                        </div>
                    </div> 
                </div>
            </form:form>
            <table id="contactsTable" class="table">                
                <tr>
                    <th width="10%">Order #</th>
                    <th width="80%">Name</th>
                    <th width="5%"></th>
                    <th width="5%"></th>              
                </tr>
                <c:forEach items="${orderList}" var="order">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/flooring/view/${order.orderNumber}/${order.date}">#${order.orderNumber}</a></td>
                        <td><a href="${pageContext.request.contextPath}/flooring/view/${order.orderNumber}/${order.date}">${order.customerName}</a></td>
                        <td><a href="${pageContext.request.contextPath}/flooring/showEdit/${order.orderNumber}/${order.date}">Edit</a></td>
                        <td><a href="${pageContext.request.contextPath}/flooring/delete/${order.orderNumber}/${order.date}">Delete</a></td>                           
                    </tr>
                </c:forEach>                     
            </table>
        </div>
    </div>
    <div id="neworder" class="services">
        <div class="container">
            <div class="service-head text-center">
                <h2>Add A New Order</h2>
                <span> </span>
            </div>

        </div>
    </div>
    <div class="container">
        <div class="col-md-12">
            <form:form class="form-horizontal" action="${pageContext.request.contextPath}/flooring/create" method="post">
                <div class="form-group">
                    <label for="add-customer-name" class="col-md-4 control-label">Customer Name:</label>
                    <div class="col-md-6">
                        <input type="text" name="customerName" class="form-control" id="add-customer-name" placeholder="Customer Name"/> 
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-state" class="col-md-4 control-label">State:</label>
                    <div class="col-md-6">
                        <select class="form-control" name="state" id="add-state">
                            <option disabled selected hidden>Add state ...</option>
                            <option value="OH">OH</option>
                            <option value="PA">PA</option>
                            <option value="IN">IN</option>
                            <option value="MI">MI</option>
                        </select>
                    </div>
                </div>                   
                <div class="form-group">
                    <label for="add-product-type" class="col-md-4 control-label">Product Type:</label>
                    <div class="col-md-6">
                        <select class="form-control" name="productType" id="add-state">
                            <option disabled selected hidden>Add product ...</option>
                            <option value="Laminate">Laminate</option>
                            <option value="Carpet">Carpet</option>
                            <option value="Wood">Wood</option>
                            <option value="Tile">Tile</option>


                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-area" class="col-md-4 control-label">Area:</label>
                    <div class="col-md-6">
                        <input type="text" name="area" class="form-control" id="add-area" placeholder="Square Feet"/> 
                    </div>
                </div> 
                <div class="form-group">
                    <label for="add-date" class="col-md-4 control-label">Date:</label>
                    <div class="col-md-6">
                        <input type="text" name="date" class="form-control" value=""/> 
                    </div>
                </div> 
                <input type="submit" class="btn btn-default pull-right" value="Create" />
            </form:form>
        </div>
    </div>
    <!---- team --->
    <div id="team" class="team-members">
        <div class="wrap"> 
            <div class="tm-head">
                <h3>The Olivieri Team</h3>
                <p>We're three good-lookin' guys who do floors.</p>
            </div>
            <div class="tm-head-grids">
                <div class="tm-head-grid">
                    <img src="${pageContext.request.contextPath}/images/g.jpg" alt="">
                    <h4>Greg Svoboda</h4>
                    <h5>Installations</h5>
                    <ul class="top-social-icons">
                        <li><a class="twitter" href="#"> </a></li>
                        <li><a class="facebook" href="#"> </a></li>
                        <li><a class="pin" href="#"> </a></li>
                        <div class="clear"> </div>
                    </ul>
                </div>
                <div class="tm-head-grid">
                    <img src="${pageContext.request.contextPath}/images/o.jpg" alt="">
                    <h4>Anthony Olivieri</h4>
                    <h5>Owner</h5>
                    <ul class="top-social-icons">
                        <li><a class="twitter" href="#"> </a></li>
                        <li><a class="facebook" href="#"> </a></li>
                        <li><a class="pin" href="#"> </a></li>
                        <div class="clear"> </div>
                    </ul>
                </div>
                <div class="tm-head-grid">
                    <img src="${pageContext.request.contextPath}/images/j.jpg" alt="">
                    <h4>John Moriarty</h4>
                    <h5>Debt Collections</h5>
                    <ul class="top-social-icons">
                        <li><a class="twitter" href="#"> </a></li>
                        <li><a class="facebook" href="#"> </a></li>
                        <li><a class="pin" href="#"> </a></li>
                        <div class="clear"> </div>
                    </ul>
                </div>
                <div class="clearfix"> </div>
            </div>
            <p class="team-info">With over 6 months of combined experience, Olivieri floors will amaze you with our design, installation, and professionalism.</p>
        </div>
    </div>
    <!---- team --->
    <!---- contact --->
    <div id="contact" class="contact">
        <div class="container">
            <div class="contact-grids">
                <div class="col-md-6">
                    <div class="contact-left">
                        <h3>Contact Us</h3>
                        <label>Olivieri Flooring is here to serve!</label>
                        <div class="contact-left-grids">
                            <div class="col-md-6">
                                <div class="contact-left-grid">
                                    <p><span class="c-mobi"> </span>(216) 555-5555</p>
                                    <p><span class="c-twitter"> </span><a href="#">@olivierifloors</a></p>
                                    <p><span class="c-pluse"> </span><a href="#">plus.com/olivieri</a></p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="contact-right-grid">
                                    <p><span class="c-msg"> </span><a href="mailto:olivierifloors@olivierifloors.com">hello@dreams.com</a></p>
                                    <p><span class="c-face"> </span><a href="#">/olliefloors</a></p>
                                    <p><span class="c-pin"> </span><a href="#">/olliefloors</a></p>
                                </div>
                            </div>
                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="contact-right">
                        <form>
                            <input type="text" class="text" value="Name..." onfocus="this.value = '';" onblur="if (this.value == '') {
                                                                            this.value = 'Name...';
                                                                        }">
                            <input type="text" class="text" value="Email..." onfocus="this.value = '';" onblur="if (this.value == '') {
                                                                            this.value = 'Email...';
                                                                        }">
                            <textarea value="Message:" onfocus="this.value = '';" onblur="if (this.value == '') {
                                                                            this.value = 'Message';
                                                                        }">Message..</textarea>
                            <input type="submit" value="Send Message" />
                        </form>
                    </div>
                </div>
                <div class="clearfix"> </div>
            </div>
            <!--- copy-right ---->
            <div class="copy-right text-center">
                <p>Made By <a href="">Greg, Ollie, and John</a></p>
                <script type="text/javascript">
                    $(document).ready(function () {

                        $().UItoTop({easingType: 'easeOutQuart'});

                    });
                </script>
                <a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
            </div>
            <!--- copy-right ---->
        </div>
    </div>
    <!---- contact --->
</div>
<!-- Placed at the end of the document so the pages load faster -->
<!--        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>-->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

