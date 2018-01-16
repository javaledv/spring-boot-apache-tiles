<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/app.css">
    <title><tiles:insertAttribute name="title"/></title>
</head>

<body>
<div class="header">
    <tiles:insertAttribute name="header"/>
</div>

<div class="content">
    <tiles:insertAttribute name="body"/>
</div>

<div class="footer">
    <tiles:insertAttribute name="footer"/>
</div>

<script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>