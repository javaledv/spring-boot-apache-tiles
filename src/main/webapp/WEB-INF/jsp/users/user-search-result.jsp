<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
                    <form:form action="/users/remove" method="post">
                        <input hidden name="userId" value="${listValue.id}">
                        <button type="submit" class="btn btn-danger">x</button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>