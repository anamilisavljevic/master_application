

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meni</title>
        <jsp:include page="ude.jsp" />
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
          
<!--            -->
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/">Job positions</a></li>
                <li><a href="/">Companies</a></li>
                <li><a href="/">Apply for job</a></li>
                <c:if test="${user = 'admin'}" >
                    <li><a href="/">Admin part</a></li>
                    <li><a href="/">Applicant</a></li>
                </c:if>
                <li><a href="/">Logout</a></li>
            </ul>
           <ul class="nav navbar-nav navbar-right">
                <li><a href="/action/logout">Izloguj se <span class="sr-only">(current)</span></a></li>
            </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    </body>
</html>
