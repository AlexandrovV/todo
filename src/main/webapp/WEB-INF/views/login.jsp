<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			<%-- this will be displayed if the credentials are wrong --%>
			<c:if test="${not empty message}">
				<div class="row">
		
					<div class="col-md-offset-3 col-md-6">
		
						<div class="alert alert-danger">${message}</div>
		
					</div>
				</div>
		
			</c:if>
			<%-- this will be displayed only when user has logged out --%>
			<c:if test="${not empty logout}">
				<div class="row">
		
					<div class="col-md-offset-3 col-md-6">
		
						<div class="alert alert-success">${logout}</div>
		
					</div>
				</div>
			</c:if>
			<div class="row">
			
				<div class="col-md-offset-3 col-md-6">
				
					<div class="panel panel-primary">
				
					<div class="panel-heading">
						<h4>Login</h4>
					</div>
			
					<div class="panel-body">
						<form action="/todo/login" method="POST"	class="form-horizontal" id="loginForm">
							<div class="form-group">
								<label for="username" class="col-md-4 control-label">Login:</label>
								<div class="col-md-8">
									<input type="text" name="username" id="username"
										class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-md-4 control-label">Password:</label>
								<div class="col-md-8">
									<input type="password" name="password" id="password"
										class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<input type="submit" value="Login" class="btn btn-primary" />
									<input type="hidden" name="${_csrf.parameterName}"
															value="${_csrf.token}" />
								</div>
							</div>
						</form>
					</div>
					<div class="panel-footer">
						<div class="text-right">
							New User - <a href="/todo/register">Register Here</a>
						</div>
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