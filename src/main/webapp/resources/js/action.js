var urlHolder = new Object();

/**
 * Load Task Details.
 */
function loadTaskDetails(){
	$('.single_sticky_notes li').remove();
	createAddNotes(ctx);
	$.post(urlHolder.task, {
	projectId : $('select#projectOption option:selected').val()
	},
	function(response) {
		if (response != null) {
			for ( var i = 0; i < response.length; i++) {
				createNotes(response[i],ctx);
			}
		}
	});
	
}

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
 * Validation for forgot Password.
 */
function forgotAction() {
	if ($('#forgot-login-box-usrName').val() == '') {
		$("#forgot-login-box-usrName").addClass("error");
	} else {
		$.post(urlHolder.forgotpwd, {
			username : $('#forgot-login-box-usrName').val()
		}, function(response) {
			if (response) {
				$("#forgot-login-box-usrName").removeClass("error");
				document.getElementById('forgot-login-box-usrName').value = '';
				alert("Shortly you will get a mail. \n")
			}else{
				alert("Enter the valid username. \n")
			}
			parent.$.fancybox.close();
		});
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
function createAddNotes(ctx) {
	var add = '<h5>'+'Add Task'+'</h5>';
	var refClass = 'refClass'
	var addBtn = '<img src='+ctx+'/resources/css/images/add.png '+ '/>';
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
function createNotes(task, ctx) {
	var refClass = 'refClass'
	var historyClass = 'historyClass';
	var header = '<h5>' + task.taskname + '</h5>';
	var desc = '<p style="text-overflow:ellipsis; overflow:hidden; height:50%">' + task.taskdesc + '</p>';
	var div = '<div style="height:80%">'+header+desc+'</div>';
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
	case "COMPLETED":
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
	// need to change
	var tname = task.taskname.replace(/ /g, '&nbsp;') ;
	var tdesc = task.taskdesc.replace(/ /g, '&nbsp;') ;
	if(task.cclist == null){
		task.cclist = '';
	}
	$("#noresult").hide();
	$('.single_sticky_notes')
	.append(
			'<li class="'
			+ color
			+ '"'
			+ 'onclick = editTask({taskname:"'
			+ tname
			+ '",taskid:"'
			+ task.taskid
			+ '",priorityindex:"'
			+ task.priorityindex
			+ '",statusindex:"'
			+ task.statusindex
			+ '",username:"'
			+ task.username
			+ '",taskdesc:"'
			+ tdesc
			+ '",createduser:"'
			+ task.createduser
			+ '",cclist:"'
			+ task.cclist
			+ '"})>'
			+ '<a href="#addNewTask" class='
			+ refClass
			+ '>'
			+ div
			+ '<div align=right>'
			+ '<a href="#taskHistory" onclick=showTaskHistory({taskid:"'
			+ task.taskid + '"}) class=' + historyClass
			+ '><img src=' + ctx
			+ '/resources/css/images/arrow.png'
			+ '/></a></div>' + '</li>');
}

/**
 * Edit the Task.
 */
function editTask(task) {
	resetTaskWindow();
	$('#hiddenStatus').show();
	$('#addEditHeading').text("Edit Task");
	$('#task-id').val(task.taskid);
	$('#task-name').val(task.taskname);
	$('#task-desc').val(task.taskdesc);
	$('#created-user').val(task.createduser);
	$('#priorityOption option').eq(task.priorityindex).attr('selected', 'selected');
	$('#statusOption option').eq(task.statusindex).attr('selected', 'selected');
	$('#task-assigned').val(task.username);
	$('#cc-list').val(task.cclist);
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
	$('#hiddenStatus').hide();
	$('#addEditHeading').text("Add Task");
	$('#task-id').val('')
	$('#task-name').val('');
	$('#task-desc').val('');
	$('#created-user').val(document.getElementById('task-created-user').value);
	$('#priorityOption option').eq(0).attr('selected', 'selected');
	$('#statusOption option').eq(0).attr('selected', 'selected');
	$('#task-assigned').val('');
	$('#cc-list').val('');
	$("#task-name").removeClass("error");
	$("#task-desc").removeClass("error");
	$("#task-assigned").removeClass("error");
}

/**
 * Add Task and Edit Task.
 */
function submitNewTask() {
	var addEditTask = {
			"taskid" : $('#task-id').val(),
			"taskname" : $('#task-name').val(),
			"taskdesc" : $('#task-desc').val(),
			"priority" : $('select#priorityOption option:selected').val(),
			"taskstatus" : $('select#statusOption option:selected').val(),
			"username" : $('#task-assigned').val(),
			"createduser" : $('#created-user').val(),
			"cclist" : $('#cc-list').val(),
			"editor" : $('#task-editor').val(),
			"projectId" : $('select#projectOption option:selected').val()
	};
	if (taskValidation()) {

		$.ajax({
			contentType : "application/json",
			dataType : 'json',
			type : "POST",
			url : urlHolder.addtask,
			data : JSON.stringify(addEditTask),

			success : function(response) {
				if (response) {
					loadTaskDetails();
				}
			},
			error : function(request, status, error) {
				alert('Error: ' + error);
			}
		});
		parent.$.fancybox.close();
	}
}

/**
 * Modal Box.
 */
$(function() {
	var button = $('#loginButton');
	var box = $('#loginBox');
	var form = $('#loginForm');
	button.removeAttr('href');
	button.mouseup(function(login) {
		box.toggle();
		button.toggleClass('active');
	});
	form.mouseup(function() {
		return false;
	});
	$(this).mouseup(function(login) {
		if (!($(login.target).parent('#loginButton').length > 0)) {
			button.removeClass('active');
			box.hide();
		}
	});
});

/**
 * Task Validation.
 */
function taskValidation() {
	$("#task-name").removeClass("error");
	$("#task-desc").removeClass("error");
	$("#task-assigned").removeClass("error");
	if ($('#task-name').val() == '' || $('#task-desc').val() == ''
		|| $('#task-assigned').val() == '') {
		if ($('#task-name').val() == '') {
			$("#task-name").addClass("error");
		}
		if ($('#task-desc').val() == '') {
			$("#task-desc").addClass("error");
		}
		if ($('#task-assigned').val() == '') {
			$("#task-assigned").addClass("error");
		}
		return false;
	}
	return true;
}

/**
 * Load the history screen.
 */
function showTaskHistory(task) {
	var taskid = task.taskid;
	window.location.href = "/todo/jsp/taskscreen.jsp?task=" + taskid;
}

/**
 * Back to task manager.
 */
function back(ctx) {
	window.location.href = ctx;
};

/**
 * Search Task.
 */
function searchTask() {

	if ($('#search-task-name').val() == ''
		&& $('select#search-priority option:selected').val() == 'SELECT'
			&& $('select#search-status option:selected').val() == 'SELECT'
				&& $('#search-task-assigned').val() == '') {
		$("#searchCriteria").show();
	} else {
		if ($('select#search-priority option:selected').val() == 'SELECT') {
			$('select#search-priority option:selected').val('');
		}
		if ($('select#search-status option:selected').val() == 'SELECT') {
			$('select#search-status option:selected').val('');
		}

		var searchTask = {
				"taskname" : $('#search-task-name').val(),
				"priority" : $('select#search-priority option:selected').val(),
				"taskstatus" : $('select#search-status option:selected').val(),
				"username" : $('#search-task-assigned').val(),
				"projectId" : $('select#projectOption option:selected').val()
		};
		$.ajax({
			contentType : "application/json",
			dataType : 'json',
			type : "POST",
			url : urlHolder.search,
			data : JSON.stringify(searchTask),

			success : function(response) {
				$('.single_sticky_notes li').remove();
				if (response.length > 0) {
					for ( var i = 0; i < response.length; i++) {
						createNotes(response[i], '/todo');
					}
				} else {
					$("#noresult").show();
				}
			},
			error : function(request, status, error) {
				alert('Error: ' + error);
			}
		});
		parent.$.fancybox.close();
	}
}

/**
 * Reset Search window.
 */
function resetSearchWindow() {
	$('#search-task-name').val('');
	$('#search-priority option').eq(0).attr('selected', 'selected');
	$('#search-status option').eq(0).attr('selected', 'selected');
	$('#search-task-assigned').val('');
	$("#searchCriteria").hide();
}

function changePassword(){
	if($('#newPwd').val() != $('#confirmPwd').val()){
		$('#changepwdvalidation').show();
		return false;
	}
	return true;
}
