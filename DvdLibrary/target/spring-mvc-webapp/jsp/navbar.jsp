<%-- 
    Document   : navbar
    Created on : Feb 16, 2016, 8:11:26 AM
    Author     : parallels
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>Dvd Library</h1>
<hr/>
<div class="navbar">
     <ul class="nav nav-tabs">
     <li role="presentation" class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/dvd/search">Search</a></li>
    </ul>    
</div>
