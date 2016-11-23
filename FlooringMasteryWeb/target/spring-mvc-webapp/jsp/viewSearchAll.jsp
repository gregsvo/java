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

    <jsp:include page="navbardeep.jsp"></jsp:include>
        <div class="container">


            <div class="row">

            <c:forEach items="${orderList}" var="order">
                <h3>Name: ${order.customerName}</h3>
                <p>Order #: ${order.orderNumber}</p>
                <p>State: ${order.state}</p>
                <p>Product: ${order.productType}</p>
                <p>Area: ${order.area}</p><br>
            </c:forEach>    

        </div>    







    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>