/**
 * Contains custom JavaScript code
 */
var urlHolder = new Object();
var selectedProj = [];
var tempProjectArr = [];
var user = new String();

function loadTable() {
	$.post(urlHolder.readProjects, function(response) {
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

function hasSelected() {
	var selected = $('input:radio[name=index]:checked').val();
	if (selected == undefined) {
		alert('Select a record first!');
		return false;
	}

	return true;
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

function selectedUser(name){
	user = name;
	$.post(urlHolder.userProj, {
		username : name
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
		data : JSON.stringify({"username" : user, "project": userProjArr}),

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

function saveUserProfile(username, role) {
	if ($('#editfirstName').val() != "" && $('#editlastName').val() != ""
			&& $('#editEmailId').val() != "") {
		$.post(urlHolder.edit, {
			id : username,
			firstName : $('#editfirstName').val(),
			lastName : $('#editlastName').val(),
			mailId : $('#editEmailId').val(),
			oper : 'edit'

		}, function(response) {
			if (response != null) {
				alert('Success! UserProfile has been edited.');
				window.location.reload();
			}
		});
	} else {
		alert("Please enter the missing values!!!");
	}
}
