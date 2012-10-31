var urlHolder = new Object();
var name;

function submitAction() {

	$.post(urlHolder.login, {
		username : $('#login-box-usrName').val(),
		password : $('#login-box-password').val()
	}, function(response) {
		if (response == 'success') {
			window.location.href = 'jsp/taskmanager.jsp'
		} else {
			document.getElementById('login-box-usrName').value = '';
			document.getElementById('login-box-password').value = '';
			alert('Login Failed');
		}
	});
}

function loginValidation() {
	var usrName = document.getElementById('login-box-usrName').value;
	var password = document.getElementById('login-box-password').value;
	if (usrName == '' && password == '') {
	} else {
	}

}

function forgotAction() {
	name = document.getElementById('forgot-login-box-usrName').value;
	if (name == '') {
		window.alert("Please enter the Username. \n"
				+ "Password will send to your mail.");
	} else {
		// Need to code for forgot password.
		document.getElementById('forgot-login-box-usrName').value = '';
		window.alert("Shortly you will get a mail. \n")
	}
}

function changePasswordAction() {
	var old_password = document.getElementById('old-password').value;
	var new_password = document.getElementById('new-password').value;
	var confirm_password = document.getElementById('confirm-password').value;
	if (old_password == '' || new_password == '' || confirm_password == '') {
		alert('Please enter the passwords to change');
	} else {
		document.getElementById('old-password').value = '';
		document.getElementById('new-password').value = '';
		document.getElementById('confirm-password').value = '';
		alert('Successfully Changed');
	}
}

// Function which runs on page load
$(document).ready(function() {

	// Refresh notes button click event
	$('#refreshNotes').click(function() {
		refreshNotes();
	});

	$('#highlight').click(function() {
		var title = $(this).find('input[id^="title"]').val();
		var description = $(this).find('input[id^="description"]').val();

		if (title != undefined && description != undefined) {
			createSingleNotes(title, description);
		}
	});
});

/**
 * Click event function to start the creation of the task sticky notes Get each
 * of the rows in the task list and create a sticky note for each of them
 */
function refreshNotes() {
	var tableRows = $('#newTasks tr');

	$('.sticky_notes li').remove();

	$.each(tableRows, function(i) {
		var title = $(this).find('input[id^="title"]').val();
		var description = $(this).find('input[id^="description"]').val();

		if (title != undefined && description != undefined) {
			createNotes(title, description);
		}
	});
}

/**
 * Creates the sticky notes and gives it a random colour.
 */
function createNotes(title, description) {
	var header = '<h5>' + title + '</h5>';
	var desc = '<p>' + description + '</p>';

	var colours = new Array();
	colours[0] = 'green';
	colours[1] = 'blue';
	colours[2] = 'yellow';
	colours[3] = 'red';
	colours[4] = 'purple';
	colours[5] = 'orange';

	$('.sticky_notes').append(
			'<li class="' + colours[randomFromTo(0, (colours.length - 1))]
					+ '">' + header + description + '</li>');
}

/**
 * Get a random number between 2 numbers
 * 
 * @return Randon Number
 */
function randomFromTo(from, to) {
	return Math.floor(Math.random() * (to - from + 1) + from);
}

function createSingleNotes(title, desc1) {
	var header = '<h5>' + title + '</h5>';
	var desc = '<p>' + desc1 + '</p>';

	var colours = new Array();
	colours[0] = 'green';
	colours[1] = 'blue';
	colours[2] = 'yellow';
	colours[3] = 'red';
	colours[4] = 'purple';
	colours[5] = 'orange';
	$('.single_sticky_notes').append(
			'<li class="' + colours[randomFromTo(0, (colours.length - 1))]
					+ '">' + header + desc1 + '</li>');
}