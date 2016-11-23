<%-- 
    Document   : navbar
    Created on : Feb 16, 2016, 11:11:27 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>Address Book</h1>
<hr/>
<div class="navbar">
    <ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/address/search">Search</a></li>
    </ul>    
</div>