var urlHolder = new Object();
var name = new String();
var displayName = new Object();

/**
 * Login the user.
 */
function submitAction() {

	$.post(urlHolder.login, {
		username : $('#login-box-usrName').val(),
		password : $('#login-box-password').val()
	}, function(response) {
		if (response) {
			window.location.href="/todo/jsp/taskmanager.jsp";
		} else {
			document.getElementById('login-box-usrName').value = '';
			document.getElementById('login-box-password').value = '';
			$('#loginvalidation').show();
		}
	});
}

/**
 * Validation for Login Form.
 */
function loginValidation() {
	var usrName = document.getElementById('login-box-usrName').value;
	var password = document.getElementById('login-box-password').value;
	if (usrName == '' && password == '') {
	} else {
	}

}

/**
 * Validation for forgot Password.
 */
function forgotAction() {
	if ($('#forgot-login-box-usrName').val() == '') {
		$("#forgot-login-box-usrName").addClass("error");
	} else {
		// Need to code for forgot password.
		$("#forgot-login-box-usrName").removeClass("error");
		document.getElementById('forgot-login-box-usrName').value = '';
		window.alert("Shortly you will get a mail. \n")
	}
}

/**
 * Validation for Change Password Form.
 */
function changePasswordAction() {
	if ($('#old-password').val() == '' || $('#new-password').val() == ''
			|| $('#confirm-password').val() == '') {
		if ($('#old-password').val() == '') {
			$("#old-password").addClass("error");
		}
		if ($('#new-password').val() == '') {
			$("#new-password").addClass("error");
		}
		if ($('#confirm-password').val() == '') {
			$("#confirm-password").addClass("error");
		}
		return false;
	} else {
		if ($('#old-password').val() == $('#new-password').val()) {
			$("#new-password").addClass("error");
			return false;
		} else if ($('#new-password').val() != $('#confirm-password').val()) {
			$("#confirm-password").addClass("error");
			return false;
		} else {
			$("#old-password").removeClass("error");
			$("#new-password").removeClass("error");
			$("#confirm-password").removeClass("error");
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
	var desc = '<p style="text-overflow:ellipsis; overflow:hidden;">' + task.taskdesc + '</p>';
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
	case "COMPLETE":
		task.statusindex = 2;
		break;
	case "DEV":
		task.statusindex = 3;
		break;
	case "HOLD":
		task.statusindex = 4;
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
			+task.statusindex+'",username:"'+task.username+'",taskdesc:"'+tdesc+'",createduser:"'+task.createduser+'"})>'+'<a href="#addNewTask" class=' + refClass + '>' + header + desc
					+ '</li>');
}

/**
 * Edit the Task.
 */
function editTask(task) {
	resetTaskWindow();
	$('#addEditHeading').text("Edit Task");
	$('#task-id').val(task.taskid);
	$('#task-name').val(task.taskname);
	$('#task-desc').val(task.taskdesc);
	$('#created-user').val(task.createduser);
	$('#priority option').eq(task.priorityindex).attr('selected', 'selected');
	$('#status option').eq(task.statusindex).attr('selected', 'selected');
	$('#task-assigned').val(task.username);
}

/**
 * Reset the Change Password Form.
 */
function resetChangePwdForm() {
	document.getElementById('old-password').value = '';
	document.getElementById('new-password').value = '';
	document.getElementById('confirm-password').value = '';
	$("#old-password").removeClass("error");
	$("#new-password").removeClass("error");
	$("#confirm-password").removeClass("error");
}

/**
 * Reset the Task Form.
 */
function resetTaskWindow() {
	$('#addEditHeading').text("Add Task");
	$('#task-id').val('')
	$('#task-name').val('');
	$('#task-desc').val('');
	$('#created-user').val(document.getElementById('task-created-user').value);
	$('#priority option').eq(0).attr('selected', 'selected');
	$('#status option').eq(0).attr('selected', 'selected');
	$('#task-assigned').val('');
}

/**
 * Add Task and Edit Task.
 */
function submitNewTask(){
	$.post(urlHolder.addtask, {
		taskid : $('#task-id').val(),
		taskname : $('#task-name').val(),
		taskdesc : $('#task-desc').val(),
		priority : $('select#priority option:selected').val(),
		taskstatus : $('select#status option:selected').val(),
		username : $('#task-assigned').val(),
		createduser : $('#created-user').val()
	}, function(response) {
		if (response) {
			window.location.href="/todo/jsp/taskmanager.jsp";
		}
	});
}
