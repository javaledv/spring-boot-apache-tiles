<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
    <div class="btn-group mr-2" role="group" aria-label="First group">
        <form:form action="/users/add/form" method="get">
            <button type="submit" class="btn btn-secondary">Add user</button>
        </form:form>
    </div>
    <div class="btn-group mr-2 search" role="group" aria-label="Second group">
       <form:form action="#" method="get" cssClass="form-inline">
            <input type="text" name="userLogin" class="form-control" id="userLogin" placeholder="User login"/>
            <button type="submit" class="btn">Find</button>
       </form:form>
    </div>
</div>

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
                <td></td>>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</c:if>