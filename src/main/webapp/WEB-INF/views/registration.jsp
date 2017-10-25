<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<spring:url var="css" value="/assets/css" />
<spring:url var="js" value="/assets/js" />
<spring:url var="images" value="/assets/images" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>To Do Application</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link href="${css}/bootstrap.min.css" rel="stylesheet">
    <link href="${css}/App.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="wrapper">
<div class="container">
		
<div class="row">
 
 <div class="col-md-6 col-md-offset-3">

  <div class="panel panel-primary">
  
   <div class="panel-heading">
    <h4>Registration</h4>
   </div>
   
   <div class="panel-body">
        
    <sf:form action="/todo/registerUser" method="POST" class="form-horizontal" id="registerForm" modelAttribute="user">

    <div class="form-group">
     <label class="control-label col-md-4">Login:</label>
     <div class="col-md-8">
      <sf:input type="text" path="login" class="form-control" name="login"
       placeholder="Login" />
		<sf:errors path="login" cssClass="help-block" element="em"/>        
     
     </div>
    </div>
       
       <div class="form-group">
        <label class="control-label col-md-4">Password:</label>
        <div class="col-md-8">
         <sf:input type="password" path="password" class="form-control" name="password"
          placeholder="Password" />
          
          <sf:errors path="password" cssClass="help-block" element="em"/> 
          
          
        </div>
       </div>

       <div class="col-md-offset-4 col-md-8">
			<input type="submit" value="Sign Up" class="btn btn-primary" />
		</div>       
      </sf:form>     
     </div>
    </div>
   </div>
  </div>
</div>
</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${js}/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="${js}/weekpicker.js"></script>
    <script src="${js}/App.js"></script>
    <script src="${js}/jquery.validate.min.js"></script>
</body>
</html>