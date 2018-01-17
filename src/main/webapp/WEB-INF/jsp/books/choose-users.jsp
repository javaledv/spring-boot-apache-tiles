<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
    <div class="btn-group mr-2 search-3" role="group" aria-label="First group">
        <form:form action="/books/users/search" method="get" cssClass="form-inline">
            <input type="text" name="userLogin" class="form-control" id="userLogin" placeholder="User login"/>
            <input hidden type="text" name="bookId" value="${bookId}"/>
            <button type="submit" class="btn">Find</button>
        </form:form>
    </div>
</div>

<c:if test="${not empty bookId}">
    <p>Add book with id "${bookId}" for user: </p>
</c:if>

<h1>Users</h1>
<c:if test="${not empty users}">

    <table class="table">
        <thead>
        <tr class="tab-header-area bg-info">
            <td>id</td>
            <td>login</td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="listValue" items="${users}">
            <tr>
                <td>${listValue.id}</td>
                <td>${listValue.login}</td>
                <td>
                    <form:form action="/books/book-it/reservation" method="post">
                        <input hidden name="bookId" value="${bookId}">
                        <input hidden name="userId" value="${listValue.id}">
                        <button type="submit" class="btn btn-outline-info">+</button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

