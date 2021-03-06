<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/users/records" var="recordsUrl" />
<c:url value="/users/update" var="editUrl" />
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

<%-- <script src='<c:url value="/resources/js/jquery.jqGrid.min.js"/>'
	type="text/javascript"></script> --%>
<script src='<c:url value="/resources/js/jquery.jqGrid.src.js"/>'
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
		urlHolder.edit = '${editUrl}';
		urlHolder.task = '${taskUrl}';
		urlHolder.readProjects = '${readProjectsUrl}';
		urlHolder.addProject = '${addProjUrl}';
		urlHolder.editProject = '${editProjUrl}';
		urlHolder.assignedProj = '${assignedProjUrl}';
		urlHolder.userProj = '${userProjUrl}';
		urlHolder.addEditUserProj = '${addEditUserProjUrl}';
		loadTable();

		$('#addnewProject').hide();
		$('#editProject').hide();
		$('#assignProject').hide();
		$('#importUser').fancybox();

		$('#closeAssignProject').click(function() {
			$('#v-div').show();
			$('#assignProject').hide();
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
			$('#importUser').hide();
		});
		$('#tab1').click(function() {
			$('#importUser').show();
		});
	});

	$(function() {
		var lastsel;
		jQuery("#tableUsers")
				.jqGrid(
						{
							url : urlHolder.records,
							datatype : "json",
							colNames : [ 'Username', 'First Name', 'Last Name',
									'Role', 'MailId' ],
							colModel : [ {
								name : 'username',
								index : 'username',
								width : '100%',
								sortable : false
							}, {
								name : 'firstName',
								index : 'firstName',
								width : '100%',
								editable : true,
								sortable : false
							}, {
								name : 'lastName',
								index : 'lastName',
								editable : true,
								width : '100%',
								sortable : false
							}, {
								name : 'role.desc',
								index : 'role',
								editable : true,
								edittype : "select",
								editoptions : {
									value : "1:Admin;2:Regular"
								},
								width : '100%',
								sortable : false
							}, {
								name : 'mailId',
								index : 'mailId',
								editable : true,
								autowidth : true,
								sortable : false
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
							height : '40%',
							onSelectRow : function(username) {
								if (username && username !== lastsel) {
									jQuery('#tableUsers').jqGrid('restoreRow',
											lastsel);
									jQuery('#tableUsers').jqGrid('editRow',
											username, true);
									lastsel = username;
								}
							},
							editurl : urlHolder.edit,
							caption : "Record System"
						});
		jQuery("#tableUsers").jqGrid('navGrid', '#userPager', {
			edit : true,
			add : true,
			del : true
		});

		jQuery("#assignBtn").click(function() {
			var id = jQuery("#tableUsers").jqGrid('getGridParam', 'selrow');
			if (id) {
				$('#v-div').hide();
				$('#assignProject').show();
				selectedUser(id);
			} else {
				alert("Please select row");
			}
		});
	});

	function importUser() {
		$('#import').show();
	}
</script>
</head>

<body>
	<jsp:include page='/jsp/header.jsp' />
	<div>${message}</div>
	<div align="right" style="padding-top: 10px;">
		<img src="/todo/resources/css/images/home_btn.png" align="right"
			title="Home" onclick="back('${pageContext.request.contextPath}')"
			style="cursor: pointer; padding-top: 1mm; padding-right: 1mm; vertical-align: middle;" />
		<a href="#import" id="importUser" title="Import Users"
			style="width: 10%; padding-top: 30px;" onclick="importUser();"><img
			src="/todo/resources/css/images/upload.png" align="right"
			style="cursor: pointer; width: 30px; height: 30px; padding-top: 1mm; padding-right: 1mm; vertical-align: middle;" /></a>
	</div>

	<div id="usersScreen" align="left" style="width: 90%; height: 75%">
		<section id="wrapper" class="wrapper" style="width: 100%;">
			<h4 class="title">Administrator</h4>
			<div id="import" align="right" hidden="hidden"
				style="padding-top: 20px;">
				<form:form
					action="${pageContext.request.contextPath}/upload/save.html"
					method="post" modelAttribute="uploadForm"
					enctype="multipart/form-data">
					Please select a file to upload : <input type="file" name="file" />
					<input type="submit" value="upload" />
					<span> </span>

				</form:form>
			</div>

			<div id="v-nav">
				<ul>
					<li tab="tab1" id="tab1" class="first current">Manage Users</li>
					<li tab="tab2" id="tab2" class="last">Add Project</li>
				</ul>
				<div align="center" class="tab-content" style="padding-top: 5%;">
					<div id="v-div">
						<table id="tableUsers">
						</table>
						<div id="userPager"></div>
						<div id='controlBar' style="padding-top: 2%; width: 20%"
							align="left">
							<input type='button' value='Assign Project' id='assignBtn' />
						</div>
					</div>
					<div id='assignProject' class="tab-content" hidden="hidden">
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

			</div>
		</section>
	</div>
</body>
</html>