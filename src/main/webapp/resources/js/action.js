var urlHolder = new Object();
var name = new String();
var displayName = new Object();

function submitAction() {

	$.post(urlHolder.login, {
		username : $('#login-box-usrName').val(),
		password : $('#login-box-password').val()
	}, function(response) {
		if (response == 'login failed') {
			document.getElementById('login-box-usrName').value = '';
			document.getElementById('login-box-password').value = '';
			alert('Login Failed');
		} else {
			window.location.href="../jsp/taskmanager.jsp?valnm=" + response;
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

/**
 * Creates the sticky notes.
 */
function createNotes(task) {
	var header = '<h5>' + task.taskname + '</h5>';
	var desc = '<p>' + task.taskdesc + '</p>';
	var color = null;

	switch (task.priority) {
	case "Low":
		color = 'green';
		break;
	case "Medium":
		color = 'orange';
		break;
	case "High":
		color = 'purple';
		break;
	case "Highest":
		color = 'red';
		break;
	default:
		color = 'blue';
		break;
	}
	$('.single_sticky_notes').append(
			'<li class="' + color
					+ '">' + header + desc + task.priority +'</li>');
}