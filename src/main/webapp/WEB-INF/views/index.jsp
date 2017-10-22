<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>To Do Application</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/App.css" rel="stylesheet">

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
    			<div class="col-md-offset-4 col-md-4">
    				<h1 class="text-center">TODO LIST</h1>
    			</div>
    		</div>
    		<div class="row">
    			<div class="col-md-offset-3 col-md-6">
    				<form class="form-inline text-center">
					  <div class="form-group">
					    <label for="weekpicker">Choose week:</label>
					    <input type="text" class="form-control" id="weekpicker" required="required" name="newsdate">
					  </div>
					  <button type="submit" class="btn btn-primary">Show week</button>
					</form>
    			</div>
    		</div>
    		<br>
    		<div class="row">
    			<div class="col-md-3">
    				<div class="panel panel-info">
    					<div class="panel-heading text-center">Monday</div>
						<div class="panel-body">
							<div class="line">
								<div class="time text-center">
									11:00
								</div>
								<div class="todo">
									Call parents
								</div>

								<div class="done text-center">
									O
									<!-- <input type="checkbox" class="checkbox-primary"/> -->
								</div>
							</div>
							<!-- <div class="well">
								<div class="row">
									<div class="col-md-3 line">11:30</div>
									<div class="col-md-6">
										<div class="line">
											Call parents
										</div>
									</div>
									<div class="col-md-3 line">O</div>
								</div>
							</div> -->
						</div>
    				</div>
    			</div>
    			<div class="col-md-3">
    				<div class="panel panel-info">
    					<div class="panel-heading text-center">Tuesday</div>
						<div class="panel-body">Panel Footer</div>
    				</div>
    			</div>
    			<div class="col-md-3">
    				<div class="panel panel-info">
    					<div class="panel-heading text-center">Wednesday</div>
						<div class="panel-body">Panel Footer</div>
    				</div>
    			</div>
    			<div class="col-md-3">
    				<div class="panel panel-info">
    					<div class="panel-heading text-center">Thursday</div>
						<div class="panel-body">Panel Footer</div>
    				</div>
    			</div>
    			<div class="col-md-3">
    				<div class="panel panel-info">
    					<div class="panel-heading text-center">Friday</div>
						<div class="panel-body">Panel Footer</div>
    				</div>
    			</div>
    			<div class="col-md-3">
    				<div class="panel panel-info">
    					<div class="panel-heading text-center">Saturday</div>
						<div class="panel-body">Panel Footer</div>
    				</div>
    			</div>
    			<div class="col-md-3">
    				<div class="panel panel-info">
    					<div class="panel-heading text-center">Sunday</div>
						<div class="panel-body">Panel Footer</div>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="js/weekpicker.js"></script>
</body>
</html>