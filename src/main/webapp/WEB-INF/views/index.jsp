<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Appointment Management</title>
<link rel="stylesheet" href="/resources/css/main.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Date and time picker  -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>



<link href="http://www.jqueryscript.net/css/jquerysctipttop.css"
	rel="stylesheet" type="text/css">
<link href="/resources/css/mdtimepicker.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript" src="/resources/js/AppointmentJS.js"></script>

</head>
<body>
	<div id="mainForm">
		<div class="row">
			<div class="col-md-8">
				<button id="newBtn" type="button" class="btn btn-info"
					data-toggle="collapse" data-target="#demo">New</button>
				<div id="demo" class="collapse">

					<form:form action="/saveAppointment" method="POST"
						modelAttribute="appointment" onsubmit="return validateForm()">
						<div><input type="submit" class="btn btn-info" value="ADD"/>
						</div>
						<br/>
						<div>
							<button id="cancelBtn" type="button" class="btn btn-danger"
								data-toggle="collapse" data-target="#demo">Cancel</button>
						</div>
						<br/>
						<div class="input-group">
							<label for="date">Date: </label>
							<form:input type="text" id="datepicker" path="appointmentDate"></form:input>
						</div>
						<br/>
						<div class="input-group clockpicker">
							<label for="time">Time: </label>
							<form:input type="text" id="timepicker" path="appointmentTime"></form:input>
						</div>
						<div>
							<label for="desciption">Description</label>
							<form:input type="text" name="description" class="form-control"
								id="description" path="description" required=""></form:input>
						</div>
						<div id="error"></div>
					</form:form>
				</div>
			</div>
		</div>
		<br />

		<div class="span4">
			<div class="input-append">
				<input name="srchDescription" id="srchDescription" type="text"
					class="span2" />
				<button id="searchBtn" class="btn btn-info">Search</button>
			</div>
		</div>

		<br />




		<table class=table>
			<thead>
				<tr>
					<th>Date</th>
					<th>Time</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody id="tblBody">
				<%-- <c:forEach items="${appointments}" var="appointment">
				
					<tr>
						<td>${appointment.appointmentDate}</td>
						<td>${appointment.appointmentTime}</td>
						<td>${appointment.description}</td>
					</tr>
				</c:forEach> --%>
			</tbody>
		</table>


	</div>

	<!-- date and time picker -->
	<script src="/resources/js/mdtimepicker.js"></script>

	<script>
		$(document).ready(function() {
			$("#datepicker").datepicker({
				minDate : 0,
				dateFormat : "yy-mm-dd"
			});
			$('#timepicker').mdtimepicker(); //Initializes the time picker
			$('#searchBtn').click(function() {
				getAppointments();
			});
			getAppointments();
			$('#newBtn').click(function() {
				$('#newBtn').hide();
			});
			$('#cancelBtn').click(function() {
				$('#newBtn').show();
			});
		});
	</script>


</body>
</html>