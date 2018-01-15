<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>

    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <title><tiles:insertAttribute name="title"/></title>
</head>
<body>

<table cellpadding="2" cellspacing="2" align="center">
    <tr>
        <td><tiles:insertAttribute name="header"/></td>
    </tr>
    <tr>
        <td><tiles:insertAttribute name="body"/></td>
    </tr>
    <tr>
        <td><tiles:insertAttribute name="footer"/></td>
    </tr>
</table>

<script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>