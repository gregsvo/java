<%-- 
    Document   : navbar
    Created on : Feb 16, 2016, 11:11:27 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!----- start-header---->
<div class="top-header">
    <div class="container">

        <!----start-top-nav---->
        <nav class="top-nav">
            <ul class="top-nav">
                <li class="active"><a href="#home" class="scroll">Home</a></li>
                <li><a href="#vieworder" class="scroll">View</a></li>
                <li><a href="#neworder" class="scroll">Submit</a></li>
                <li><a href="${pageContext.request.contextPath}/flooring/search">Search</a></li>
                <li><a href="#team" class="scroll">Team</a></li>
                <li><a href="#contact" class="scroll">Contact</a></li>
            </ul>
            <a href="#" id="pull"><img src="${pageContext.request.contextPath}/images/menu-icon.png" title="menu" /></a>
        </nav>
        <div class="clearfix"> </div>
    </div>
</div>