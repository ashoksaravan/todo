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

	// usrName = document.getElementById('login-box-usrName').value;
	// password = document.getElementById('login-box-password').value;
	// if (usrName != '' && password != '') {
	// window.location.href = 'jsp/taskmanager.jsp'
	// } else {
	// if (usrName == '' && password == '') {
	// alert('Enter the username and password');
	// } else {
	// if (usrName == '') {
	// alert('Enter the username');
	// }
	// if (password == '') {
	// alert('Enter the password');
	// }
	// }
	// }
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