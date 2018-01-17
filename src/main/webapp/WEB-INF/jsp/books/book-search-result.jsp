<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
                    <c:if test="${!listValue.value}">

                    <form:form action="/books/book-it" method="post">
                    <input hidden name="id" value="${listValue.key.id}">
                    <button type="submit" class="btn btn-success">book it</button>
                    </form:form>

                    </c:if>
                <td>
                    <c:if test="${listValue.value}">
                        <form:form action="/books/cancel-reservation" method="post">
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
