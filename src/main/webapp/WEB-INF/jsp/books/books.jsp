<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
    <div class="btn-group mr-2" role="group" aria-label="First group">
        <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show
                <span class="caret"></span></button>
            <ul class="dropdown-menu">
                <li><a href="/books">All</a></li>
                <li><a href="/books/free">Free</a></li>
            </ul>
        </div>
    </div>
    <div class="btn-group mr-2" role="group" aria-label="Second group">
        <form:form action="/books/add/form" method="get">
            <button type="submit" class="btn btn-secondary">Add book</button>
        </form:form>
    </div>
    <div class="btn-group mr-2 search" role="group" aria-label="Thread group">
        <form:form action="/books/search" method="get" cssClass="form-inline">
            <input type="text" name="bookName" class="form-control" id="bookName" placeholder="Book name"/>
            <button type="submit" class="btn">Find</button>
        </form:form>
    </div>
</div>

<h1>Books</h1>

<c:if test="${not empty books}">

    <table class="table">
        <thead>
        <tr class="tab-header-area bg-info">
            <td>id</td>
            <td>name</td>
            <td>description</td>
            <td>isbn</td>
            <td>author</td>
            <td>publisher</td>
            <td>content</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="listValue" items="${books}">
            <tr>
                <td>${listValue.key.id}</td>
                <td>${listValue.key.name}</td>
                <td>${listValue.key.description}</td>
                <td>${listValue.key.isbn}</td>
                <td>${listValue.key.author}</td>
                <td>${listValue.key.publisher}</td>
                <td>${listValue.key.content}</td>
                <td>
                    <c:if test="${listValue.value}">
                        <form:form action="/books/users" method="post">
                            <input hidden name="id" value="${listValue.key.id}">
                            <button type="submit" class="btn btn-success">book it</button>
                        </form:form>
                    </c:if>
                </td>
                <td>
                    <c:if test="${not listValue.value}">
                        <form:form action="/books/reservation/cancel" method="post">
                            <input hidden name="bookId" value="${listValue.key.id}">
                            <button type="submit" class="btn btn-success">de-reserve</button>
                        </form:form>
                    </c:if>
                </td>
                <td>
                    <form:form action="/books/remove" method="post">
                        <input hidden name="bookId" value="${listValue.key.id}">
                        <button type="submit" class="btn btn-danger">x</button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
