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
			window.location.href="/todo/jsp/taskmanager.jsp?valnm=" + response;
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
		return false;
	} else {
		if(old_password == new_password){
			alert("Old and New Passwords should differ.");
			return false;
		}else if(new_password != confirm_password){
			alert("New and Confirm Passwords should be same.");
			return false;
		}else{
			return true;
		}
	}
}

/**
 * Creates the add sticky note.
 */
function createAddNotes() {
	var add = '<h5>'+'Add Task'+'</h5>';
	var refClass = 'refClass'
	var addBtn = '<img src='+ '../resources/css/images/add.png '+ '/>';
	var idBtn = 'createAddBtn';
	var classBtn = 'createAddBtn';
	var title = 'Please click the add symbol'
	$('.single_sticky_notes').append(
			'<a href="#addNewTask" class='+refClass+'>'+
			'<li class="' + classBtn
					+ '"id="'+ idBtn +'" onclick = resetTaskWindow()>'+ add + addBtn + '</li>');
}

/**
 * Creates the dynamic sticky notes.
 */
function createNotes(task) {
	var refClass = 'refClass'
	var header = '<h5>' + task.taskname + '</h5>';
	var desc = '<p>' + task.taskdesc + '</p>';
	var taskdesc = task.taskdesc;
	var color = null;
	switch (task.priority) {
	case "L":
		color = 'green';
		task.priorityindex = 0;
		break;
	case "M":
		color = 'orange';
		task.priorityindex = 1;
		break;
	case "H":
		color = 'purple';
		task.priorityindex = 2;
		break;
	case "HS":
		color = 'red';
		task.priorityindex = 3;
		break;
	default:
		color = 'blue';
		task.priorityindex = 0;
		break;
	}
	
	switch (task.taskstatus) {
	case "NEW":
		task.statusindex = 0;
		break;
	case "CANCEL":
		task.statusindex = 1;
		break;
	case "DEV":
		task.statusindex = 2;
		break;
	case "HOLD":
		task.statusindex = 3;
		break;
	default:
		task.statusindex = 0;
		break;
	}
	//need to change
	var tname = task.taskname.replace(/ /g, '&nbsp;') ;
	var tdesc = task.taskdesc.replace(/ /g, '&nbsp;') ;
	$('.single_sticky_notes').append(
			'<li class="'+ color + '"' + 'onclick = editTask({taskname:"'+tname+'",taskid:"'
			+task.taskid+'",priorityindex:"'+task.priorityindex+'",statusindex:"'
			+task.statusindex+'",username:"'+task.username+'",taskdesc:"'+tdesc+'"})>'+'<a href="#addNewTask" class=' + refClass + '>' + header + desc
					+ '</li>');
}

function editTask(task) {
	resetTaskWindow();
	$('#addEditHeading').text("Edit Task");
	$('#task-id').val(task.taskid);
	$('#task-name').val(task.taskname);
	$('#task-desc').val(task.taskdesc);
	$('#priority option').eq(task.priorityindex).attr('selected', 'selected');
	$('#status option').eq(task.statusindex).attr('selected', 'selected');
	$('#task-assigned').val(task.username);
}

/**
 * Reset the Change Password Form
 */
function resetChangePwdForm() {
	document.getElementById('old-password').value = '';
	document.getElementById('new-password').value = '';
	document.getElementById('confirm-password').value = '';
}

function resetTaskWindow() {
	$('#addEditHeading').text("Add Task");
	$('#task-id').val('')
	$('#task-name').val('');
	$('#task-desc').val('');
	$('#priority option').eq(0).attr('selected', 'selected');
	$('#status option').eq(0).attr('selected', 'selected');
	$('#task-assigned').val('');
}

function submitNewTask(){
//	alert($('#task-id').val()+'-'+$('#task-name').val()+'-'+$('#task-desc').val()+'-'+$('select#priority option:selected').val()+'-'+$('select#status option:selected').val()+'-'+$('#task-assigned').val())
	$.post(urlHolder.addtask, {
		taskid : $('#task-id').val(),
		taskname : $('#task-name').val(),
		taskdesc : $('#task-desc').val(),
		priority : $('select#priority option:selected').val(),
		taskstatus : $('select#status option:selected').val(),
		username : $('#task-assigned').val()
	}, function(response) {
		if (response) {
			window.location.href="/todo/jsp/taskmanager.jsp?valnm=" + response;
		}
	});
}
