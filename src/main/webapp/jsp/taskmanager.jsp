<%@page import="com.todo.domain.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<c:url value="/users/task" var="taskUrl" />
<c:url value="/users/changepwd" var="changepwdUrl" />
<c:url value="/users/checkpwd" var="checkpwdUrl" />
<c:url value="/users/addtask" var="addtaskUrl" />
<c:url value="/users/records" var="recordsUrl" />
<c:url value="/users/search" var="searchUrl" />
<c:url value="/users/refdataPriority" var="refdataPriorityUrl" />
<c:url value="/users/refdataTaskStatus" var="refdataTaskStatusUrl" />
<c:url value="/users/refdataProject" var="refdataProjectUrl" />
<html>
<head>
<title>Task Manager</title>
<link rel="icon" type="image/gif"
	href="<%=request.getContextPath()%>/resources/images/Ebix-small.gif">
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/action.js"></script>
<link
	href="<%=request.getContextPath()%>/resources/fancybox/jquery.fancybox.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/modal-style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/fancybox/jquery.fancybox.js"></script>
<script type="text/javascript">
var ctx = "${pageContext.request.contextPath}";
	$(function() {
		urlHolder.task = '${taskUrl}';
		urlHolder.changepwd = '${changepwdUrl}';
		urlHolder.addtask = '${addtaskUrl}';
		urlHolder.checkpwd = '${checkpwdUrl}';
		urlHolder.records = '${recordsUrl}';
		urlHolder.search = '${searchUrl}';
		urlHolder.refdataPriority = '${refdataPriorityUrl}';
		urlHolder.refdataTaskStatus = '${refdataTaskStatusUrl}';
		urlHolder.refdataProject = '${refdataProjectUrl}';
	});
	
	var availableTags = [];
	$(document).ready(function() {
		$(".change-password-window").fancybox();
		$('.refClass').fancybox();
		$('#searchQuery').fancybox();

		$.post(urlHolder.records, function(response) {
			if (response != null) {
				for ( var i = 0; i < response.users.length; i++) {
					var obj = {
						id : i,
						label : response.users[i].firstName
								+ ','
								+ response.users[i].lastName,
						value : response.users[i].username
					};
					availableTags.push(obj);
				}
				$("#task-assigned")
						.autocomplete(
								{
									source : availableTags,
									autoFocus : true
								});
				$("#search-task-assigned")
				.autocomplete(
						{
							source : availableTags,
							autoFocus : true,
							selectFirst: true
						});

				$("#cc-list")
						// don't navigate away from the field on tab when selecting an item
						.bind(
								"keydown",
								function(event) {
									if (event.keyCode === $.ui.keyCode.TAB
											&& $(
													this)
													.data(
															"autocomplete").menu.active) {
										event
												.preventDefault();
									}
								})
						.autocomplete(
								{
									minLength : 0,
									source : function(
											request,
											response) {
										// delegate back to autocomplete, but extract the last term
										response($.ui.autocomplete
												.filter(
														availableTags,
														extractLast(request.term)));
									},
									focus : function() {
										// prevent value inserted on focus
										return false;
									},
									select : function(
											event,
											ui) {
										var terms = split(this.value);
										// remove the current input
										terms
												.pop();
										// add the selected item
										terms
												.push(ui.item.value);
										// add placeholder to get the comma-and-space at the end
										terms
												.push("");
										this.value = terms
												.join(",");
										return false;
									}
								});
			}
		});
	});

	function split(val) {
		return val.split(/,\s*/);
	}
	function extractLast(term) {
		return split(term).pop();
	}

	$(function() {
		$(".changeBtn").click(function() {
			if (changePasswordAction()) {
				$.post(urlHolder.changepwd, {
					username : '${user.username}',
					confirmpass : $('#confirm-password').val()
				}, function(response) {
					if (response) {
						alert('Successfully Changed');
						resetChangePwdForm();
					}
					parent.$.fancybox.close();
				});
			}
		});
		$(".userroles").click(function() {
			window.location = "/todo/jsp/users.jsp";
		});

		$("#old-password").focusout(function() {
			$.post(urlHolder.checkpwd, {
				username : '${user.username}',
				pwd : $('#old-password').val()
			}, function(response) {
				if (response) {
					$("#old-password").removeClass("error");
				} else {
					$("#old-password").addClass("error");
				}
			});
		});
		$("#new-password").focusout(function() {
			if ($('#old-password').val() == $('#new-password').val()) {
				$("#new-password").addClass("error");
			} else {
				$("#new-password").removeClass("error");
			}
		});
		$("#confirm-password").focusout(function() {
			if ($('#new-password').val() == $('#confirm-password').val()) {
				$("#confirm-password").removeClass("error");
			} else {
				$("#confirm-password").addClass("error");
			}
		});
	});

	window.onload = function() {
		loadRefData();
		$.post(urlHolder.task, function(response) {
			if (response != null) {
				createAddNotes(ctx);
				for ( var i = 0; i < response.tasks.length; i++) {
					createNotes(response.tasks[i],ctx);
				}
			}
		});
	}
</script>
</head>
<body>

	<jsp:include page='/jsp/header.jsp' />
	<div style="width: 100%; padding-top: 1%">
		<table style="width: 100%">
			<tr>
				<td><select id='projectOption' name="project"
					style="width: 30%"></select></td>
				<td align="right"><a href="#searchTask" id="searchQuery"
					style="width: 10%; padding-right: 3%;"
					onclick="resetSearchWindow();">Search</a> <a href="#"
					id="refreshQuery" onclick="refreshTask()" style="width: 10%;">Home</a>
				</td>
			</tr>
		</table>
	</div>
	<div><h5 style="color: red;" align="center" hidden="hidden" id="noresult" style="width: 80%">No search results found.</h5></div>
	<div align="center" style="width: 100%;">
		<ul class="single_sticky_notes" id="single_sticky_notes">
		</ul>
	</div>
	<div id="addNewTask" hidden="hidden">
		<h4 align="left" id="addEditHeading">Add Task</h4>
		<table>
			<tr>
				<td class="pwd-box-name">Task Name:</td>
				<td class="pwd-box-field"><input name="task-name" type="text"
					class="pwd_form-login" id="task-name"
					title="Please enter the task name" value="" size="30"
					maxlength="2048" /></td>
			</tr>
			<tr>
				<td class="pwd-box-name">Task Description:</td>
				<td class="pwd-box-field"><textarea id="task-desc"
						name="task-desc" class="pwd_form-login"
						title="Please enter the task description" maxlength="2048"></textarea></td>
			</tr>
			<tr>
				<td class="pwd-box-name">Created User:</td>
				<td class="pwd-box-field"><input id="created-user"
					disabled="disabled" name="created-user" class="pwd_form-login"
					value="${user.username}" maxlength="2048" /></td>
			</tr>
			<tr>
				<td class="pwd-box-name" id="priority">Priority:</td>
				<td><select id='priorityOption' name="priority"
					class="pwd_form-login">
				</select></td>
			</tr>
			<tr>
				<td class="pwd-box-name" id="status">Task Status:</td>
				<td><select id='statusOption' name="status" class="pwd_form-login">
				</select></td>
			</tr>
			<tr>
				<td class="pwd-box-name">Assigned To:</td>
				<td class="pwd-box-field"><input name="task-assigned"
					class="pwd_form-login" id="task-assigned" size="30"
					maxlength="2048" /></td>
			</tr>
			<tr>
				<td class="pwd-box-name">CC List:</td>
				<td class="pwd-box-field"><input name="cc-list"
					class="pwd_form-login" id="cc-list" size="30" maxlength="2048" /></td>
			</tr>
		</table>
		<div>
			<a hidden="hidden" id='task-id'></a> <br /> <br /> <input
				type="text" id="task-created-user" value="${user.username}"
				hidden="hidden" />
				<input
				type="text" id="task-editor" value="${user.username}"
				hidden="hidden" />
		</div>
		<div align="center">
			<button id="submitBtn" class="submitBtn" style="color: white;"
				onclick="submitNewTask()">Submit</button>
		</div>
	</div>
	<div id="searchTask" hidden="hidden">
		<h4 align="left" id="addEditHeading">Search Task</h4>
		<table>
			<tr>
				<td class="pwd-box-name">Task Name:</td>
				<td class="pwd-box-field"><input name="search-task-name"
					type="text" class="pwd_form-login" id="search-task-name"
					title="Please enter the task name" value="" size="30"
					maxlength="2048" /></td>
			</tr>
			<tr>
				<td class="pwd-box-name" id="priority">Priority:</td>
				<td><select id='search-priority' name="search-priority"
					class="pwd_form-login">
				</select></td>
			</tr>
			<tr>
				<td class="pwd-box-name" id="status">Task Status:</td>
				<td><select id='search-status' name="search-status"
					class="pwd_form-login">
				</select></td>
			</tr>
			<tr>
				<td class="pwd-box-name">Assigned To:</td>
				<td class="pwd-box-field"><input name="search-task-assigned"
					class="pwd_form-login" id="search-task-assigned" size="30"
					maxlength="2048" /></td>
			</tr>
			<tr>
				<td colspan="2"><h6 hidden="hidden" style="color: red;"
						id="searchCriteria">Please enter atleast one search criteria</h6></td>
			</tr>
		</table>
		<div align="center">
			<button id="searchBtn" class="searchBtn" style="color: white;"
				onclick="searchTask()">Search</button>
		</div>
	</div>
</body>
</html>