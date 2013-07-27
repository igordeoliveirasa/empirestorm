<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><decorator:title default="Vraptor Scaffold"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/bootstrap/css/bootstrap.min.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="${pageContext.request.contextPath}/stylesheets/bootstrap/css/bootstrap-responsive.css" rel="stylesheet"/>        
    <decorator:head/>
  </head>
  <body>
              
    <div class="container">
        <h1>Empire Storm</h1>

        <strong>${sm.player.name}</strong>
        <hr/>
        
        <c:forEach var="info" items="${infos}">
            
            <div class="alert alert-info">
                ${info} <button type="button" class="close" data-dismiss="alert">×</button>
            </div>
        </c:forEach>

        <c:forEach var="error" items="${errors}">
            <div class="alert alert-error">
                ${error.message} <button type="button" class="close" data-dismiss="alert">×</button>
            </div>
        </c:forEach>

        <decorator:body/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascripts/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascripts/application.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/stylesheets/bootstrap/js/bootstrap.min.js"></script>
    </div>      
  </body>
</html>
