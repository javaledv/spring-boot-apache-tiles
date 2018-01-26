<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>Add Book Form</h1>

<c:if test="${not empty error}">
    <p class="text-danger">${error}</p>
</c:if>

<form:form action="/users/add" modelAttribute="userEntity" method="post">
    <div class="form-group">
        <label for="login">Login</label>
        <form:input path="login" class="form-control" id="login"/>
    </div>
    <button type="submit" class="btn btn-outline-info">Add user</button>
</form:form>