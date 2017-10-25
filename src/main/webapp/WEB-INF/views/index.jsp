<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<spring:url var="css" value="/assets/css" />
<spring:url var="js" value="/assets/js" />
<spring:url var="images" value="/assets/images" />
<%
    String[] dow = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    pageContext.setAttribute("dow", dow);
    int i=0;
%> 
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
	    		<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="javascript:void(0)" class="btn btn-default dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">
								${globalUser.login}
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="/todo/perform-logout">Logout</a>
								</li>
							</ul>
						</li>
				</ul>
    		<div class="row">
    			<div class="col-md-offset-4 col-md-4">
    				<h1 class="text-center">${admin} TODO LIST</h1>
    				<h2 class="text-center">Hello,  ${globalUser.login}</h2>
    			</div>
    		</div>
    		<div class="row">
    			<div class="col-md-offset-3 col-md-6">
    				<form id="weekpickerForm" action="/todo/showSelectedWeek/" method="POST" class="form-inline text-center">
					  <div class="form-group">
					    <label for="weekpicker">Choose week:</label>
					    <input type="text" class="form-control" id="weekpicker" required="required" name="newsdate">
					    <input type="hidden" name="fullWeek" id="fullWeek">
					  </div>
					  <button type="submit" class="btn btn-info">Show week</button>
					  <input type="hidden" name="${_csrf.parameterName}"
															value="${_csrf.token}" />
					</form>
    			</div>
    		</div>
    		<br>
    		<div class="row">
    			<c:forEach var="day" items="${week}">
	    			<div class="col-md-3">
	    				<div class="panel panel-info">
	    					<div class="panel-heading text-center"><%= dow[i] %> ${day.date}</div>
	    					
							<div class="panel-body">
								<c:if test="${empty day.todos}">
									<h4 class="text-center">There are no records for this day yet.</h4>
								</c:if>
								<c:forEach var="todoLine" items="${day.todos}">
									<div class="line ${(todoLine.done)?"doneLine":""}">
										
										<div class="time text-center">
											${todoLine.till}
										</div>
										<div class="todo">
											${todoLine.todo}
										</div>
		
										<div class="done text-center">
											<a href="/todo/${(todoLine.done)?("undo"):("do")}/${todoLine.id}?link=${requestScope['javax.servlet.forward.request_uri']}">
												<c:choose>
													<c:when test="${todoLine.done}"><span class="glyphicon glyphicon-ok"></span></c:when>
													<c:otherwise><span class="glyphicon glyphicon-unchecked"></span></c:otherwise>
												</c:choose>
											</a>
											<c:if test="${todoLine.done}"><a href="/todo/delete/${todoLine.id}?link=${requestScope['javax.servlet.forward.request_uri']}"><span class="glyphicon glyphicon-remove"></span></a></c:if>
										</div>
									</div>
								</c:forEach>
								<div class="addNoteWrapper">
									<button type="button" data-toggle="modal" data-target="#myModal"  data-id="${day.date}" data-backdrop="false" class="btn btn-info btn-xl addButton"><span class="glyphicon glyphicon-plus"></span></button>
								</div>
							</div>
							<% i=i+1; %>
	    				</div>
	    			</div>
    			</c:forEach>
    		</div>
    	</div>
    	<div class="modal fade" id="myModal" role="dialog" tabindex="-1">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" data-backdrop="false">
							<span>&times;</span>
						</button>
						<h4 class="modal-title">Add a note for <span id="showDate"></span></h4>
					</div>
					<div class="modal-body">
						<!-- Category Form -->
						<sf:form id ="todoForm" modelAttribute="todoline" action="/todo/addNote" method="POST" class="form-horizontal">
							<div class="form-group">
								<label for="category_name" class="control-label col-md-4">To do:</label>
								<div class="col-md-8">
									<sf:input type="text" path="todo" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="till" class="control-label col-md-4">Till:</label>
								<div class="col-md-8">
									<sf:input type="text" path="till" class="form-control"/>
								</div>
							</div>
							<sf:input type="hidden" path="date" id="date" name="dateFromForm"/>
							<input type="hidden" name="link" value="${requestScope['javax.servlet.forward.request_uri']}"/>
							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<input type="submit" value="Add Note" class="btn btn-primary" />
								</div>
							</div>
						</sf:form>
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