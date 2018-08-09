
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <br><br>
    <title> Pregled usera</title>
    <jsp:include page="" />
</head>

<body>
<%@include file="part/menu_left.jsp" %>
${loggedInUser.email}
<div class="container">
    <br /><br /><br />
        <div class="row">
            <label for="username">Username:</label>
            <span> ${loggedInUser.email} </span>
        </div>
        <div class="row">
            <label for="firstName">Name:</label>
            <span> ${loggedInUser.firstName} ${loggedInUser.lastName} </span>
        </div>

        <div class="row">
            <label for="image">Image:</label>
            <img src="${loggedInUser.image}" style="width: 150px; height: 150px;"/>
        </div>
    <br />
    <a href="/login" th:href="@{/login}" class="btn btn-info btn-lg">
        <span class="glyphicon glyphicon-chevron-left"></span> Login using  other social Providers
    </a>

</div>

</body>
</html>
