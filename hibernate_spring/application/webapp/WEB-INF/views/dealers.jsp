<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Dealers</title>
        <link href="/static/css/bootstrap.css" rel="stylesheet"></link>
        <link href="/static/css/app.css" rel="stylesheet"></link>
    </head>

    <body>
        <div class="generic-container">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="lead">List of Dealers</span>
                </div>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Address</th>
                            <th width="100"></th>
                            <th width="100"></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${dealers}" var="dealers">
                        <tr>
                            <td>${dealers.name}</td>
                            <td>${dealers.address}</td>
                            <td><a href="<c:url value='/dealer/${id}' />" class="btn btn-success custom-width">edit</a></td>
                            <td><a href="<c:url value='/dealer/${id}' />" class="btn btn-danger custom-width">delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="well">
                <a href="<c:url value='/dealer' />">Add dealer</a>
            </div>
        </div>
    </body>
</html>