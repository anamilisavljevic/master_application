
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <br><br>
    <title> Pregled usera</title>
    <jsp:include page="connect/part/include.jsp" />
    <script type="text/javascript">
        $(document).ready(function() {

            $("#obrisiId").click(function () {
                alert("Sistem je obrisao proces");
            });
        });
    </script>
</head>

<body>
<%@include file="connect/part/menu_left.jsp" %>
<div class="container">
    <br>
    <h1>Pregled procesa</h1>
    <br><br>
    <table class = "table table-striped" border="1" width="600">
        <thead>
        <tr>
            <th style="visibility: hidden;">Id</th>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Akcije</th>


        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${list}">
        <tr>
            <td style="visibility: hidden;">${user.idUser}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>
                <button class="btn btn-success"
                        onclick="location.href = '<c:url value="../action/unesi_proces" >
                            <c:param name="id" value="${proces.idProcesa}"></c:param>
                        </c:url>'"></button>

                <button class="btn btn-info"
                        onclick="location.href = '<c:url value="../action/izmeni_proces" >
                            <c:param name="id" value="${proces.idProcesa}"></c:param>
                        </c:url>'"></button>
            </td>

            </c:forEach>
        </tbody>
    </table>

    <!-- Social Sign In Buttons -->
    <div class="panel panel-default">
        <div class="panel-body">
            <h2>Sign in</h2>
            <div class="row social-button-row">
                <div class="col-lg-4">
                    <!-- Add Facebook sign in button -->
                    <a href="${pageContext.request.contextPath}/my-facebook-url"><button class="btn btn-facebook"><i class="icon-facebook"></i> Sign in </button></a>
                </div>
            </div>
            <div class="row social-button-row">
                <div class="col-lg-4">
                    <!-- Add Twitter sign in Button -->
                    <a href="${pageContext.request.contextPath}/auth/twitter"><button class="btn btn-twitter"><i class="icon-twitter"></i> | Sign in </button></a>
                </div>
            </div>
        </div>
    </div>

</div>
<a href="/action/pocetna">
    <img class="svaka-stranica-logo-forme" src="http://ebec.best.rs/wp-content/uploads/2015/01/fon.jpg" />
</a>
</body>
</html>
