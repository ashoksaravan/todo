<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<c:url value="/users/records" var="recordsUrl" />
<c:url value="/users/create" var="addUrl" />
<c:url value="/users/update" var="editUrl" />
<c:url value="/users/delete" var="deleteUrl" />
<c:url value="/users/task" var="taskUrl" />

<html>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.23.custom.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/stickystyle.css"></link>
<link
	href="<%=request.getContextPath()%>/resources/css/jquery-ui-1.8.23.custom.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/login-box.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/taskstyle.css"
	rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/resources/css/userstyle.css"
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
<jsp:include page='/jsp/header.jsp'/>

<!-- 	<h3 id='banner' style="height:30px">Record System</h3> -->
	<div align="center">
		<table id='tableUsers'>
			<caption align="top"><b>Record System</b></caption>
			<thead id='tableHead'>
				<tr>
					<th></th>
					<th>Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Role</th>
					<th>EmailId</th>
				</tr>
			</thead>
		</table>
			<div id='controlBar' style="width: 80%" align="left">
			<input type='button' value='New' id='newBtn' /> 
			<input type='button' value='Delete' id='deleteBtn' /> <input type='button' value='Edit'
				id='editBtn' /> <input type='button' value='Reload' id='reloadBtn' />
		</div>
	

	

		<form id='newForm' class="newEditForm">
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
			
			<input type='button' value='Close' id='closeEditForm' /> <input
				type='submit' value='Submit' />
		</form>
		</div>
	
</body>
</html>