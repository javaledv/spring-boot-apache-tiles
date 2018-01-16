<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Add Book Form</h1>

<c:if test="${not empty error}">
    <p class="text-danger">${error}</p>
</c:if>

<form:form action="/books/add" modelAttribute="bookEntity" method="post">
    <div class="form-group">
        <label for="name">Name</label>
        <form:input path="name" class="form-control" id="name"/>
        <label for="description">Description</label>
        <form:textarea path="description" rows="5" class="form-control" id="description"/>
        <label for="isbn">isbn</label>
        <form:input path="isbn" class="form-control" id="isbn"/>
        <label for="author">Author</label>
        <form:input path="author" class="form-control" id="author"/>
        <label for="publisher">Publisher</label>
        <form:input path="publisher" class="form-control" id="publisher"/>
        <label for="content">Content</label>
        <form:input path="content" class="form-control" id="content"/>
    </div>
    <button type="submit" class="btn btn-outline-info">Add book</button>
</form:form>