function isFutureDate(appDate) {
   var today = new Date().getTime(),
      appDate = appDate.split("-");
   appDate = new Date(appDate[0], appDate[1] - 1, appDate[2]).getTime();
   return (today - appDate) < 0 ? true : false;
}

function validateForm() {
	return validateDescription() && validateDate() && validateTime();

}

function validateDescription() {
	var descInput = $('#description').val();
	$("#error").empty();

	if (descInput != "") {
		return true;

	} else {
		$("#error").append("<p>Please enter a valid Description!</p>");
		return false;
	}

}
function validateTime() {
	var timeInput = $('#timepicker').val();
	$("#error").empty();

	if (timeInput != "") {
		return true;

	} else {
		$("#error").append("<p>Please enter a valid Time!</p>");
		return false;
	}

}

function validateDate() {
	var dateInput = $('#datepicker').val();

	var checkDate = /^((19|20)\d{2})\-(0[1-9]|1[0-2])\-(0[1-9]|1\d|2\d|3[01])$/
			.test(dateInput);
	$("#error").empty();
	// alert(isValidDt);
	if (checkDate && isFutureDate(dateInput)) {

		return true;

	} else {
		$("#error").append("<p>Please enter a valid Date in the future!</p>");
		return false;
	}

}

function getDate(date) {
	var d = new Date(date);
	var day = d.getDate();
	var month = d.getMonth() + 1;
	var year = d.getFullYear();
	var result = year + "-" + day + "-" + month;
	return result;
}

function getTime(date) {
	var d = new Date(date);
	var hours = d.getHours();
	var minutes = d.getMinutes();
	var seconds = d.getSeconds();
	var time = hours + ":" + minutes + ":" + seconds;
	return time;
}

function formatDate(date) {
	date = date.split("-");
	var month = parseInt(date[1]);

	var day = parseInt(date[2]);

	var monthNames = ["January ", "February ", "March ", "April ", "May ",
			"June ", "July ", "August ", "September ", "October ", "November ",
			"December "];
	var result = monthNames[month - 1].concat(day.toString());
	return result;
}

function getAppointments() {
	$("#error").empty();
	var toSelect = $('#srchDescription').val();

	$('#tblBody').empty();
	$
			.ajax({
				type : "GET",
				url : "/getAppointments?desc=" + toSelect,

				async : false,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(jsonData) {
					console.log(jsonData.data)
					if (Object.keys(jsonData).length == 0) {

						$("#error").append("<p>No result !</p>");
					} else {
						var tr;
						tr = $("#tblBody");

						for (var i = 0; i < jsonData.data.length; i++) {

							tr.append("<tr> ");
							tr
									.append("<td>"
											+ formatDate(jsonData.data[i].appointmentDate)
											+ "</td>");

							tr.append("<td>" + jsonData.data[i].appointmentTime
									+ "</td>");
							tr.append("<td>" + jsonData.data[i].description
									+ "</td> ");
							tr.append("</tr>");

						}

					}
				},
			});
}
