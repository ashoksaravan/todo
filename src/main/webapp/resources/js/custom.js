/**
 * Contains custom JavaScript code
 */
var urlHolder = new Object();
var selectedProj = [];
var tempProjectArr = [];

function loadTable() {
	$.get(urlHolder.records, function(response) {

		$('#tableUsers').find('tbody').remove();

		for ( var i = 0; i < response.users.length; i++) {
			var row = '<tr>';
			row += '<td><input type="radio" name="index" id="index" value="'
				+ i + '"></td>';
			row += '<td>' + response.users[i].username + '</td>';
			row += '<td>' + response.users[i].firstName + '</td>';
			row += '<td>' + response.users[i].lastName + '</td>';
			row += '<td>' + getRole(response.users[i].role.role) + '</td>';
			row += '<td>' + response.users[i].mailId + '</td>';
			row += '</tr>';
			$('#tableUsers').append(row);
		}

		$('#tableUsers').data('model', response.users);
		toggleForms('hide');
		;
	});
	
	$.post(urlHolder.refdataProject, function(response) {
		$('#tableProjects').find('tbody').remove();
		if (response) {
			for ( var i = 0; i < response.project.length; i++) {
				var row = '<tr>';
				row += '<td><input type="radio" name="index" id="index" value="'
					+ i + '"></td>';
				row += '<td>' + response.project[i].projectName + '</td>';
				row += '<td>' + response.project[i].projectDesc + '</td>';
				row += '</tr>';
				$('#tableProjects').append(row);
			}
				$('#tableProjects').data('model', response.project);
		}
	});
}

function submitNewRecord() {
	$.post(urlHolder.add, {
		username : $('#newUsername').val(),
		password : $('#newPassword').val(),
		firstName : $('#newFirstName').val(),
		lastName : $('#newLastName').val(),
		role : $('#newRole').val(),
		status : $('#newStatus').val(),
		mailID : $('#newMailId').val()
	}, function(response) {
		if (response != null) {
			loadTable();
			toggleForms('hide');
			toggleCrudButtons('show');
			alert('Success! Record has been added.');
		} else {
			alert('Failure! An error has occurred!');
		}
	});
}

function submitDeleteRecord() {
	var selected = $('input:radio[name=index]:checked').val();

	$.post(urlHolder.del, {
		username : $('#tableUsers').data('model')[selected].username
	}, function(response) {
		if (response == true) {
			loadTable(urlHolder.records);
			alert('Success! Record has been deleted.');
		} else {
			alert('Failure! An error has occurred!');
		}
	});
}

function submitUpdateRecord(mailId) {
	$.post(urlHolder.edit, {
		username : $('#editUsername').val(),
		firstName : $('#editFirstName').val(),
		lastName : $('#editLastName').val(),
		role : $('#editRole').val(),
		mailId : mailId
	}, function(response) {
		if (response != null) {
			loadTable();
			toggleForms('hide');
			;
			toggleCrudButtons('show');
			alert('Success! Record has been edited.');
		} else {
			alert('Failure! An error has occurred!');
		}
	});
}

function login() {
	$.post(urlHolder.login, {
		username : $('#username').val(),
		password : $('#password').val()
	}, function(response) {
		if (response == 'success') {
			window.open('/todo/users', '_self');
		} else {
			alert('Failure! An error has occurred!');
		}
	});
}
function getRole(role) {
	if (role == 1) {
		return 'Admin';
	} else if (role == 2) {
		return 'Regular';
	} else {
		return 'Unknown';
	}
}

function hasSelected() {
	var selected = $('input:radio[name=index]:checked').val();
	if (selected == undefined) {
		alert('Select a record first!');
		return false;
	}

	return true;
}

function fillEditForm() {
	var selected = $('input:radio[name=index]:checked').val();
	$('#editUsername').val($('#tableUsers').data('model')[selected].username);
	$('#editFirstName').val($('#tableUsers').data('model')[selected].firstName);
	$('#editLastName').val($('#tableUsers').data('model')[selected].lastName);
	$('#editRole').val($('#tableUsers').data('model')[selected].role.role);
}

function resetNewForm() {
	$('#newUsername').val('');
	$('#newPassword').val('');
	$('#newFirstName').val('');
	$('#newLastName').val('');
	$('#newRole').val('2');
}

function resetEditForm() {
	$('#editFirstName').val('');
	$('#editLastName').val('');
	$('#editRole').val('2');
}

function toggleForms(id) {
	if (id == 'hide') {
		$('#newForm').hide();
		$('#editForm').hide();
		$('#tableUsers').show();
		$('#assignProject').hide();
		$('#controlBar').show();

	} else if (id == 'new') {
		resetNewForm();
		$('#tableUsers').hide();
		$('#assignProject').hide();
		$('#newForm').show();
		$('#editForm').hide();
		$('#controlBar').hide();

	} else if (id == 'edit') {
		resetEditForm();
		$('#newForm').hide();
		$('#assignProject').hide();
		$('#editForm').show();
	} else if (id == 'assign') {
		$('#tableUsers').hide();
		$('#controlBar').hide();
		$('#newForm').hide();
		$('#editForm').hide();
		$('#assignProject').show();
	}
}

function toggleCrudButtons(id) {
	if (id == 'show') {
		$('#newBtn').removeAttr('disabled');
		$('#editBtn').removeAttr('disabled');
		$('#deleteBtn').removeAttr('disabled');
		$('#reloadBtn').removeAttr('disabled');
		$('#assignBtn').removeAttr('disabled');

	} else if (id == 'hide') {
		$('#newBtn').attr('disabled', 'disabled');
		$('#editBtn').attr('disabled', 'disabled');
		$('#deleteBtn').attr('disabled', 'disabled');
		$('#reloadBtn').attr('disabled', 'disabled');
		$('#assignBtn').attr('disabled', 'disabled');
	}
}

function saveUserProfile(username,role) {
	var firstName = $('#editfirstName').val();
	var lastName = $('#editlastName').val();
	var mailId = $('#editEmailId').val();
	
	if(firstName !="" && lastName!=""&& mailId !=""){
		$.post(urlHolder.edit, {
			username : username,
			firstName : $('#editfirstName').val(),
			lastName : $('#editlastName').val(),
			role : role,
			mailId : $('#editEmailId').val()
			
		}, function(response) {
			if (response != null) {
				alert('Success! UserProfile has been edited.');
				window.location.reload();
			}
		});
	}
	else{
		alert("Please enter the missing values!!!");
	}
}

function submitAddProjRecord() {
	$.post(urlHolder.addProject, {
		projectName : $('#projName').val(),
		projectDesc : $('#projDesc').val()
	}, function(response) {
		if (response != null) {
			alert('Success! Record has been added.');
			window.location.reload();
		} 
	});
}

function submitEditProjRecord() {
	$.post(urlHolder.editProject, {
		projectName : $('#editprojName').val(),
		projectDesc : $('#editprojDesc').val()
	}, function(response) {
		if (response != null) {
			alert('Success! Record has been edited.');
			window.location.reload();
		} 
	});
}

function fillProjectForm() {
	var selected = $('input:radio[name=index]:checked').val();
	$('#editprojName').val($('#tableProjects').data('model')[selected].projectName);
	$('#editprojDesc').val($('#tableProjects').data('model')[selected].projectDesc);
}

function selectedUser(){
	var selected = $('input:radio[name=index]:checked').val();
	$.post(urlHolder.userProj, {
		username : $('#tableUsers').data('model')[selected].username
	}, function(response) {
		if (response) {
			tempProjectArr = [];
			$('#assignProjectDetails').find('tbody').remove();
			for ( var i = 0; i < response.project.length; i++) {
				tempProjectArr.push(response.project[i]);
				var row = '<tr>';
				row += '<td><input type="checkbox" name="boxIndex" id="index" value="'
					+ i +'"';
				if(response.project[i].checked){
					row += ' checked="checked"';
				}
				row +='></td>'
				row += '<td>' + response.project[i].projectName + '</td>';
				row += '<td>' + response.project[i].projectDesc + '</td>';
				row += '</tr>';
				$('#assignProjectDetails').append(row);
			}
				$('#assignProjectDetails').data('model', response.project);
		}
	});
}

function associateProject(){
	var userProjArr = [];
	var selected = $('input:radio[name=index]:checked').val();
	for ( var i = 0; i < tempProjectArr.length; i++) {
		if($("#assignProjectDetails input:checkbox")[i].checked){
			userProjArr.push(tempProjectArr[i]);
		}
	}
	$.ajax({
		contentType : "application/json",
		dataType : 'json',
		type : "POST",
		url : urlHolder.addEditUserProj,
		data : JSON.stringify({"username" : $('#tableUsers').data('model')[selected].username, "project": userProjArr}),

		success : function(response) {
			if (response) {
				alert("Success");
			}
		},
		error : function(request, status, error) {
			alert('Error: ' + error);
		}
	});
	
}