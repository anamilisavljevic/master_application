<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Master application for applying for job</title>
    <jsp:include page="connect/part/include.jsp"/>
</head>
<body>
<div class="container">

    <h1>Prijavite se na sistem</h1>
    <form name="myform" action="/action/login" method="POST" onsubmit="return validate()">
        <table border="0">
            <tbody>
            <tr>
                <td>
                    <input class="form-control" modelAttribute="ki" type="text" name="korisnickoIme" value="" size="80" placeholder="Korisničko ime"/>
                </td>
            </tr>
            <tr>
                <td>
                    <br>
                    <input class="form-control" modelAttribute="ks" type="password" name="korisnickaSifra" value="" size="40" placeholder="Korisnička šifra"/>
                </td>
            </tr>
            <tr>
                <td>
                    <br>
                    <input class="btn btn-primary" type="submit" value="Prijavi se na sistem"/>
                </td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </form>

    <form action="/connect/facebook" method="POST" style="display: inline">
        <input type="hidden" name="scope" value="public_profile,email" />
        <button type="submit" class="btn btn-primary">
            Facebook <span class="fa fa-facebook"></span>
        </button>
    </form>
    <div class="container">
        <form action="/connect/google" method="POST" style="display: inline">
            <input type="hidden" name="scope" value="profile email" />
            <button type="submit" class="btn btn-danger">
                Google <span class="fa fa-google-plus"></span>
            </button>
        </form>
        <form action="/connect/linkedin" method="POST" style="display: inline">
            <input type="hidden" name="scope"
                   value="r_basicprofile,r_emailaddress" />
            <button type="submit" class="btn btn-primary">
                LinkedIn <span class="fa fa-linkedin"></span>
            </button>
        </form>
    </div>
    <div>
</body>
</html>

