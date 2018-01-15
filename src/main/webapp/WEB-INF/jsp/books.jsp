<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
        <button type="button" class="btn btn-secondary">Add book</button>
    </div>
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
                <td>${listValue.key.id}</td>
                <td>${listValue.key.name}</td>
                <td>${listValue.key.description}</td>
                <td>${listValue.key.isbn}</td>
                <td>${listValue.key.author}</td>
                <td>${listValue.key.publisher}</td>
                <td>${listValue.key.content}</td>
                <c:if test="${isFree}">
                    <td>
                        <button type="button" class="btn btn-outline-info">book it</button>
                    </td>
                </c:if>
                <td>
                    <c:if test="${listValue.value}">
                        <button type="button" class="btn btn-outline-info">de-reserve</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</c:if>
