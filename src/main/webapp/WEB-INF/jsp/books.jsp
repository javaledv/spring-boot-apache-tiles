<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="dropdown">
    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show
        <span class="caret"></span></button>
    <ul class="dropdown-menu">
        <li><a href="/books">All</a></li>
        <li><a href="books/free">Free</a></li>
    </ul>
</div>

<c:if test="${not empty books}">

    <table class="table">
        <thead>
        <tr class="tab-header-area">
            <td>id</td>
            <td>name</td>
            <td>description</td>
            <td>isbn</td>
            <td>author</td>
            <td>publisher</td>
            <td>content</td>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="listValue" items="${books}">
            <tr>
                <td>${listValue.id}</td>
                <td>${listValue.name}</td>
                <td>${listValue.description}</td>
                <td>${listValue.isbn}</td>
                <td>${listValue.author}</td>
                <td>${listValue.publisher}</td>
                <td>${listValue.content}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</c:if>
