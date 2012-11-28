<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<c:url value="/users/records" var="recordsUrl" />
<c:url value="/users/create" var="addUrl" />
<c:url value="/users/update" var="editUrl" />
<c:url value="/users/delete" var="deleteUrl" />
<c:url value="/users/task" var="taskUrl" />

<html>
<head>
<link rel='stylesheet' type='text/css' media='screen'
	href='<c:url value="/resources/css/style.css"/>' />
<link rel='stylesheet' type='text/css' media='screen'
	href='<c:url value="/resources/css/login-box.css"/>' />
<script type='text/javascript'
	src='<c:url value="/resources/js/jquery-1.8.0.min.js"/>'></script>
<script type='text/javascript'
	src='<c:url value="/resources/js/custom.js"/>'></script>

<link rel="icon" type="image/gif"
	href="<%=request.getContextPath()%>/resources/images/Ebix-small.gif">
<title>User Records</title>

<script type='text/javascript'>
	$(function() {
		// init
		urlHolder.records = '${recordsUrl}';
		urlHolder.add = '${addUrl}';
		urlHolder.edit = '${editUrl}';
		urlHolder.del = '${deleteUrl}';
		urlHolder.task = '${taskUrl}';
		loadTable();

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
			submitUpdateRecord();
		});

		$('#closeNewForm').click(function() {
			toggleForms('hide');
			toggleCrudButtons('show');
		});

		$('#closeEditForm').click(function() {
			toggleForms('hide');
			toggleCrudButtons('show');
		});
	});
</script>
</head>

<body>
	<h1 id='banner'>Record System</h1>
	<hr />

	<table id='tableUsers'>
		<caption></caption>
		<thead id='tableHead'>
			<tr>
				<th></th>
				<th>Username</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Role</th>
				<th>MailId</th>
			</tr>
		</thead>
	</table>

	<div id='controlBar'>
		<input type='button' value='New' id='newBtn' /> 
		<input type='button' value='Delete' id='deleteBtn' /> <input type='button' value='Edit'
			id='editBtn' /> <input type='button' value='Reload' id='reloadBtn' />
	</div>

		<form id='newForm' class="newEditForm">
			<fieldset>
				<legend>Create New Record</legend>
				
				 <div class="fieldgroup">
				<label for='newUsername'>UserName</label> 
				<input type='text' id='newUsername' />
				</div>
				
				 <div class="fieldgroup">
				<label for='newPassword'>Password</label>
				<input type='password' id='newPassword' />
				</div> 
				
				 <div class="fieldgroup">
				<label for='newFirstName'>First Name</label> 
				<input type='text' id='newFirstName' /> 
				</div> 
				
				 <div class="fieldgroup">
				<label for='newLastName'>Last Name</label> 
				<input type='text' id='newLastName' /> 
				</div>
				
				 <div class="fieldgroup">
				<label for='newMailId'>MailId</label> 
				<input type='text' id='newMailId' /> 
				</div> 
				
				 <div class="fieldgroup">
				<label for='newRole'>Role</label> 
				<select id='newRole'>
					<option value='1'>Admin</option>
					<option value='2' selected='selected'>Regular</option>
				</select>
				</div>
			</fieldset>
			<input type='button' value='Close' id='closeNewForm' /> <input
				type='submit' value='Submit' />
		</form>

	
		<form id='editForm' class="newEditForm">
			<fieldset>
				<legend>Edit Record</legend>
				 <div class="fieldgroup">
				<input type='hidden' id='editUsername' /> 
				</div>
				 <div class="fieldgroup">
				<label for='editFirstName'>First Name</label>
				<input type='text' id='editFirstName' />
				</div>
				 <div class="fieldgroup">
				<label for='editLastName'>Last Name</label>
				<input type='text' id='editLastName' />
				</div>
				 <div class="fieldgroup"> 
				<label	for='editRole'>Role</label> 
				<select id='editRole'>
					<option value='1'>Admin</option>
					<option value='2' selected='selected'>Regular</option>
				</select>
				</div>
			</fieldset>
			<input type='button' value='Close' id='closeEditForm' /> <input
				type='submit' value='Submit' />
		</form>
	
</body>
</html>