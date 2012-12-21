<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<c:url value="/users/records" var="recordsUrl" />
<c:url value="/users/create" var="addUrl" />
<c:url value="/users/update" var="editUrl" />
<c:url value="/users/delete" var="deleteUrl" />
<c:url value="/users/getName" var="getNameUrl" />

<c:url value="/taskservice/task" var="taskUrl" />

<c:url value="/metaData/readProjects" var="readProjectsUrl" />

<c:url value="/projects/add" var="addProjUrl" />
<c:url value="/projects/edit" var="editProjUrl" />
<c:url value="/projects/assignedProj" var="assignedProjUrl" />
<c:url value="/projects/userProj" var="userProjUrl" />
<c:url value="/projects/addEditUserProj" var="addEditUserProjUrl" />

<html>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.9.2.custom.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/stickystyle.css"></link>
<link
	href="<%=request.getContextPath()%>/resources/css/jquery-ui-1.9.2.custom.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/login-box.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/taskstyle.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/userstyle.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/resources/css/administrator-tab.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/action.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/custom.js"></script>
<link
	href="<%=request.getContextPath()%>/resources/fancybox/jquery.fancybox.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/modal-style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/fancybox/jquery.fancybox.js"></script>

<link rel="stylesheet" type="text/css" media="screen"
	href='<c:url value="/resources/css/ui.jqgrid.css" />' />

<script src='<c:url value="/resources/js/grid.locale-en.js"/>'
	type="text/javascript"></script>

<script src='<c:url value="/resources/js/jquery.jqGrid.min.js"/>'
	type="text/javascript"></script>


<title>User Records</title>

<script type='text/javascript'>
	$(function() {
		$('#v-nav>div.tab-content').hide().eq(0).show('medium');
		var items = $('#v-nav>ul>li').each(
				function() {
					$(this).click(
							function() {
								$('#v-nav>div.tab-content').hide().eq(
										items.index($(this))).show('medium');
							});
				});
	});

	$(function() {
		// init
		urlHolder.records = '${recordsUrl}';
		urlHolder.add = '${addUrl}';
		urlHolder.edit = '${editUrl}';
		urlHolder.del = '${deleteUrl}';
		urlHolder.task = '${taskUrl}';
		urlHolder.readProjects = '${readProjectsUrl}';
		urlHolder.getName = '${getNameUrl}';
		urlHolder.addProject = '${addProjUrl}';
		urlHolder.editProject = '${editProjUrl}';
		urlHolder.assignedProj = '${assignedProjUrl}';
		urlHolder.userProj = '${userProjUrl}';
		urlHolder.addEditUserProj = '${addEditUserProjUrl}';
		loadTable();

		$('#addnewProject').hide();
		$('#editProject').hide();
		$('#assignProject').hide();
		$('#searchUser').fancybox();

		$('#newBtn').click(function() {
			toggleForms('new');
			toggleCrudButtons('hide');
		});

		$('#editBtn').click(function() {
			if (hasSelected()) {
				toggleForms('edit');
				toggleCrudButtons('hide');
				fillEditForm();
			}
		});

		$('#assignBtn').click(function() {
			if (hasSelected()) {
				toggleForms('assign');
				toggleCrudButtons('hide');
				selectedUser();
			}
		});

		$('#reloadBtn').click(function() {
			loadTable();
		});

		$('#deleteBtn').click(function() {
			if (hasSelected()) {
				submitDeleteRecord();
			}
		});

		$('#newForm').submit(function(event) {
			event.preventDefault();
			submitNewRecord();
		});

		$('#editForm').submit(function(event) {
			event.preventDefault();
			submitUpdateRecord('${user.mailId}');
		});

		$('#editForm').submit(function(event) {
			event.preventDefault();
			submitUpdateRecord('${user.mailId}');
		});

		$('#closeNewForm').click(function() {
			toggleForms('hide');
			toggleCrudButtons('show');
		});

		$('#closeEditForm').click(function() {
			toggleForms('hide');
			toggleCrudButtons('show');
		});

		$('#closeAssignProject').click(function() {
			toggleForms('hide');
			toggleCrudButtons('show');
		});

		$("#newUsername").focusout(function() {
			$.post(urlHolder.getName, {
				username : $("#newUsername").val()
			}, function(response) {
				if (response == true) {
					$("#newUsername").addClass("error");
				} else {
					$("#newUsername").removeClass("error");
				}
			});
		});

		//for Project Details
		$('#projaddBtn').click(function() {
			$('#addnewProject').show();
			$('#editProject').hide();
			$('#assignProject').hide();
			$('#projeditBtn').attr('disabled', 'disabled');
		});

		$('#projeditBtn').click(function() {
			if (hasSelected()) {
				$('#addnewProject').hide();
				$('#assignProject').hide();
				$('#editProject').show();
				$('#projaddBtn').attr('disabled', 'disabled');
				fillProjectForm();
			}
		});

		$('#closeNewProject').click(function() {
			$('#addnewProject').hide();
			$('#projeditBtn').removeAttr('disabled');
		});

		$('#closeEditProject').click(function() {
			$('#editProject').hide();
			$('#projaddBtn').removeAttr('disabled');
		});

		$('#addnewProject').submit(function(event) {
			event.preventDefault();
			submitAddProjRecord();
		});

		$('#editProject').submit(function(event) {
			event.preventDefault();
			submitEditProjRecord();
		});
		$('#tab2').click(function() {
			$('#searchUser').hide();
		});
		$('#tab1').click(function() {
			$('#searchUser').show();
		});
	});

	$(function() {
		var lastsel;
		jQuery("#tableUsers").jqGrid(
				{
					url : urlHolder.records,
					datatype : "json",
					colNames : [ 'Username', 'First Name', 'Last Name', 'Role',
							'MailId' ],
					colModel : [ {
						name : 'username',
						index : 'username',
						editable:true,
						width : 150

					}, {
						name : 'firstName',
						index : 'firstName',
						editable:true,
						width : 150
					}, {
						name : 'lastName',
						index : 'lastName',
						editable:true,
						width : 150
					}, {
						name : 'role.role',
						index : 'role',
						editable:true,
						edittype:"select",
						editoptions:{value:"1:Admin;2:Regular"},
						width : 100
					}, {
						name : 'mailId',
						index : 'mailId',
						editable:true,
						width : 300
					} ],
					rowNum : 10,
					rowList : [ 10, 20, 30 ],
					pager : '#userPager',
					sortname : 'username',
					jsonReader : {
						repeatitems : false,
						id : "0"
					},
					
					viewrecords : true,
					sortorder : "asc",
					height : '38%',
					width : '100%',
					onSelectRow : function(username) {
						if (username && username !== lastsel) {
							jQuery('#tableUsers').jqGrid('restoreRow', lastsel);
							jQuery('#tableUsers').jqGrid('editRow', username, true);
							lastsel = username;
						}
					},
					editurl : urlHolder.edit,
					caption: "Record System"
				});
		jQuery("#tableUsers").jqGrid('navGrid', '#userPager', {
			edit : true,
			add : true,
			del : true
		});
		toggleForms('hide');
	});

	//for users search
	function userSearchWindow() {
		$('#search-user-name').val('');
		$('#search-first-name').val('');
		$('#search-last-name').val('');
		$("#searchCriteria").hide();
	}

	function searchUser() {
		$.post(urlHolder.records, {
			userName : $("#search-user-name").val(),
			firstName : $("#search-first-name").val(),
			lastName : $("#search-last-name").val()
		}, function(response) {
			if (response.users.length == 0) {
				$('#noresult').show();
				$('#tableUsers').hide();
				$('#controlBar').hide();
			} else {
				$('#noresult').hide();
				$('#tableUsers').show();
				$('#controlBar').show();
				buildUserTable(response);
			}
			parent.$.fancybox.close();
		});
	}
</script>
</head>

<body>
	<jsp:include page='/jsp/header.jsp' />
	<div align="right">
		<a href="#searchUserDetails" id="searchUser" title="Search Users"
			style="width: 10%; padding-top: 20px;" onclick="userSearchWindow();"><img
			src="<%=request.getContextPath()%>/resources/css/images/search_btn.png"
			style="cursor: pointer; height: 27px; padding-top: 2mm;" /></a> <img
			src="/todo/resources/css/images/home_btn.png" align="right"
			title="Home" onclick="back('${pageContext.request.contextPath}')"
			style="cursor: pointer; padding-top: 1mm; padding-right: 1mm; vertical-align: middle;" />
	</div>

	<div id="usersScreen" align="left" style="width: 90%; height: 75%">
		<section id="wrapper" class="wrapper" style="width: 100%;">
			<h4 class="title">Administrator</h4>

			<div id="v-nav">
				<ul>
					<li tab="tab1" id="tab1" class="first current">Manage Users</li>
					<li tab="tab2" id="tab2" class="last">Add Project</li>
				</ul>
				<div align="center" class="tab-content" style="padding-top: 5%;">
					<table id="tableUsers">
					</table>
					<div id="userPager"></div>
					<div id='controlBar' style="width: 80%" align="left">
						<input type='button'
							value='Assign Project' id='assignBtn' />
					</div>


					<form id='newForm' class="newEditForm">
						<div class="fieldgroup">
							<label for='newUsername'>UserName</label> <input type='text'
								id='newUsername' />
						</div>

						<div class="fieldgroup">
							<label for='newPassword'>Password</label> <input type='password'
								id='newPassword' />
						</div>

						<div class="fieldgroup">
							<label for='newFirstName'>First Name</label> <input type='text'
								id='newFirstName' />
						</div>

						<div class="fieldgroup">
							<label for='newLastName'>Last Name</label> <input type='text'
								id='newLastName' />
						</div>

						<div class="fieldgroup">
							<label for='newMailId'>MailId</label> <input type='text'
								id='newMailId' />
						</div>

						<div class="fieldgroup">
							<label for='newRole'>Role</label> <select id='newRole'>
								<option value='1'>Admin</option>
								<option value='2' selected='selected'>Regular</option>
							</select>
						</div>

						<div class="fieldgroup">
							<input type='button' value='Close' id='closeNewForm' /> <input
								type='submit' value='Submit' />
						</div>
					</form>


					<form id='editForm' class="newEditForm">

						<div class="fieldgroup">
							<input type='hidden' id='editUsername' />
						</div>
						<div class="fieldgroup">
							<label for='editFirstName'>First Name</label> <input type='text'
								id='editFirstName' />
						</div>
						<div class="fieldgroup">
							<label for='editLastName'>Last Name</label> <input type='text'
								id='editLastName' />
						</div>
						<div class="fieldgroup">
							<label for='editRole'>Role</label> <select id='editRole'>
								<option value='1'>Admin</option>
								<option value='2' selected='selected'>Regular</option>
							</select>
						</div>

						<input type='button' value='Close' id='closeEditForm' /> <input
							type='submit' value='Submit' />
					</form>

					<div id='assignProject' class="tab-content">
						<table id="assignProjectDetails" class='tableUsers'>
							<caption align="top">
								<b>Assign Projects</b> <br></br>
							</caption>
							<thead id='tableHead'>
								<tr>
									<th>Select</th>
									<th>Project Name</th>
									<th>Project Description</th>
								</tr>
							</thead>
						</table>
						<div id='controlBar' style="width: 80%" align="left">
							<input type='button' value='Close' id='closeAssignProject' /> <input
								type='button' value='Save' onclick="associateProject()" />
						</div>
					</div>
				</div>
				<div align="center" id="addProject" class="tab-content"
					hidden="hidden">
					<table id='tableProjects' class='tableUsers'>
						<caption align="top">
							<b>Project Details</b> <br></br>
						</caption>
						<thead id='tableHead'>
							<tr>
								<th></th>
								<th>Project Name</th>
								<th>Project Description</th>
							</tr>
						</thead>
					</table>
					<div id='controlBar' style="width: 80%" align="left">
						<input type='button' value='Add' id='projaddBtn' /> <input
							type='button' value='Edit' id='projeditBtn' />
					</div>

					<form id='addnewProject' class="newEditForm">
						<div class="fieldgroup">
							<label for='projName'>Project Name</label> <input type='text'
								id='projName' />
						</div>

						<div class="fieldgroup">
							<label for='projDesc'>Project Description</label> <input
								type='text' id='projDesc' />
						</div>

						<input type='button' value='Close' id='closeNewProject' /> <input
							type='submit' value='Submit' />
					</form>


					<form id='editProject' class="newEditForm">

						<div class="fieldgroup">
							<label for='editprojName'>Project Name</label> <input type='text'
								id='editprojName' />
						</div>
						<div class="fieldgroup">
							<label for='editprojDesc'>Project Description</label> <input
								type='text' id='editprojDesc' />
						</div>

						<input type='button' value='Close' id='closeEditProject' /> <input
							type='submit' value='Submit' />
					</form>
				</div>

				<div id="searchUserDetails" hidden="hidden">
					<h4 align="left" id="searchHeading">Search User</h4>
					<table>
						<tr>
							<td class="pwd-box-name">UserName:</td>
							<td class="pwd-box-field"><input name="search-user-name"
								type="text" class="pwd_form-login" id="search-user-name"
								title="Please enter the user name" value="" size="30"
								maxlength="2048" /></td>
						</tr>
						<tr>
							<td class="pwd-box-name" id="priority">First Name:</td>
							<td class="pwd-box-field"><input name="search-first-name"
								type="text" class="pwd_form-login" id="search-first-name"
								title="Please enter the first name" value="" size="30"
								maxlength="2048" /></td>
						</tr>
						<tr>
							<td class="pwd-box-name" id="priority">Last Name:</td>
							<td class="pwd-box-field"><input name="search-last-name"
								type="text" class="pwd_form-login" id="search-last-name"
								title="Please enter the last name" value="" size="30"
								maxlength="2048" /></td>
						</tr>

						<tr>
							<td colspan="2"><h6 hidden="hidden" style="color: red;"
									id="searchCriteria">Please enter atleast one search
									criteria</h6></td>
						</tr>
					</table>
					<div align="center">
						<button id="searchBtn" class="searchBtn" style="color: white;"
							onclick="searchUser()">Search</button>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>