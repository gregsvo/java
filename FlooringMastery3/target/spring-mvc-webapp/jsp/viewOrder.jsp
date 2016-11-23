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

        <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
        <script>
            new WOW().init();
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

    <jsp:include page="navbardeep.jsp"></jsp:include>


        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <div class="invoice-title">
                        <h2>OLIVIERI FLOORING</h2><h3 class="pull-right">INVOICE <a href="${pageContext.request.contextPath}/flooring/showEdit/${order.orderNumber}/${order.date}">Order #${order.orderNumber}</a></h3>

                </div>

                <hr>
                <div class="row">
                    <div class="col-xs-6">
                        <address>
                            <strong>Billed To:</strong><br>
                            ${order.customerName}<br>
                            1234 Main Street<br>
                            Apt. 4B<br>
                            Sometown, ${order.state} 44321
                        </address>
                    </div>
                    <div class="col-xs-6 text-right">
                        <address>
                            <strong>Installation:</strong><br>
                            ${order.customerName}<br>
                            1234 Main Street<br>
                            Apt. 4B<br>
                            Sometown, ${order.state} 44321
                        </address>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <address>
                            <strong>Payment Method:</strong><br>
                            Visa ending **** 4242<br>
                            ${order.customerName}@email.com
                        </address>
                    </div>
                    <div class="col-xs-6 text-right">
                        <address>
                            <strong>Order Date:</strong><br>
                            March 7, 2014<br><br>
                        </address>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><strong>Order summary</strong></h3>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-condensed">
                                <thead>
                                    <tr>
                                        <td><strong>Material</strong></td>
                                        <td class="text-center"><strong>Price per Sq/Ft</strong></td>
                                        <td class="text-center"><strong>Labor per Sq/Ft</strong></td>
                                        <td class="text-center"><strong>Install Area (SQ FT)</strong></td>
                                        <td class="text-right"><strong>State Tax Rate %</strong></td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                    <tr>
                                        <td>${order.productType}</td>
                                        <td class="text-center">$${order.materialCost}</td>
                                        <td class="text-center">$${order.laborCost}</td>
                                        <td class="text-center">${order.area}</td>
                                        <td class="text-right">${order.taxRate}</td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td class="text-center"></td>
                                        <td class="text-center"></td>
                                        <td class="text-right"></td>

                                        <td class="text-right"><strong>TOTALS</strong></td>
                                    </tr>
                                    <tr>
                                        <td class="thick-line"></td>
                                        <td class="thick-line"></td>
                                        <td class="thick-line"></td>
                                        <td class="thick-line text-center"><strong>Materials</strong></td>
                                        <td class="thick-line text-right">$${order.materialCost}</td>
                                    </tr>
                                    <tr>
                                        <td class="no-line"></td>
                                        <td class="no-line"></td>
                                        <td class="no-line"></td>
                                        <td class="no-line text-center"><strong>Labor</strong></td>
                                        <td class="no-line text-right">$${order.laborCost}</td>
                                    </tr>
                                    <tr>
                                        <td class="no-line"></td>
                                        <td class="no-line"></td>
                                        <td class="no-line"></td>
                                        <td class="no-line text-center"><strong>Tax</strong></td>
                                        <td class="no-line text-right">$${order.tax}</td>
                                    </tr>
                                    <tr>
                                        <td class="no-line"></td>
                                        <td class="no-line"></td>
                                        <td class="no-line"></td>
                                        <td class="no-line text-center"><strong>Total</strong></td>
                                        <td class="no-line text-right">$${order.total}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- <div class="container">


            <div class="row">

                <p><h1>#${order.orderNumber} - ${order.customerName}</h1></p>

            <p>State: ${order.state} - ${order.taxRate} (Tax Rate)</p>
            <p>Product Type: ${order.productType} ($${order.costPerSquareFoot}/Sq Ft) - ${order.area} Sq Ft</p>

            <p>Labor: $${order.laborCostPerSquareFoot}/Sq Ft</p>
            <p>Materials Total: $${order.materialCost}</p>
            <p>Labor Total: $${order.laborCost}</p>
            <p>Grand Total: $${order.total}</p>

        </div>    

    </div> -->

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>