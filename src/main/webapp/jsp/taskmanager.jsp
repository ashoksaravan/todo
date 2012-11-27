<%@page import="com.todo.domain.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<c:url value="/users/task" var="taskUrl" />
<c:url value="/users/changepwd" var="changepwdUrl" />
<c:url value="/users/checkpwd" var="checkpwdUrl" />
<c:url value="/users/addtask" var="addtaskUrl" />
<c:url value="/users/records" var="recordsUrl" />
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
	$(function() {
		urlHolder.task = '${taskUrl}';
		urlHolder.changepwd = '${changepwdUrl}';
		urlHolder.addtask = '${addtaskUrl}';
		urlHolder.checkpwd = '${checkpwdUrl}';
		urlHolder.records = '${recordsUrl}';
	});

	var availableTags = [];
	$(document).ready(
			function() {
				$(".change-password-window").fancybox();
				$('.refClass').fancybox();

				$.post(urlHolder.records, function(response) {
					if (response != null) {
						for ( var i = 0; i < response.users.length; i++) {
							var obj = {
								id : i,
								label : response.users[i].firstName + ','
										+ response.users[i].lastName,
								value : response.users[i].username
							};
							availableTags.push(obj);
						}
						$("#task-assigned").autocomplete({
							source : availableTags,
							autoFocus : true,
						});
					}
				});
			});

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
		$.post(urlHolder.task, function(response) {
			if (response != null) {
				createAddNotes();
				for ( var i = 0; i < response.tasks.length; i++) {
					createNotes(response.tasks[i])
				}
			}
		});
	}

	function showlayer(layer) {
		var myLayer = document.getElementById(layer);
		if (myLayer.style.display == "none" || myLayer.style.display == "") {
			myLayer.style.display = "block";
		} else {
			myLayer.style.display = "none";
		}
	}
</script>
</head>
<body>
	<div style="width: 100%;">
		<table style="width: 100%" border="1">
			<tr>
				<td
					style="border-left: 0px; border-right: 0px; border-top: 0px; border-bottom: 0px"><img
					src="<%=request.getContextPath()%>/resources/images/ebix_logo.jpg"
					width="140" height="55" /></td>
				<td align="right"
					style="border-left: 0px; border-right: 0px; border-top: 0px; border-bottom: 0px; padding-top: 1%; padding-left: 0px;">
					<div id="container">
						<!-- Login Starts Here -->
						<div id="loginContainer">
							<a href="#" id="loginButton"><span>${user.firstName},
									${user.lastName}</span></a>
							<div style="clear: both"></div>
							<div id="loginBox">
								<form id="loginForm">
									<fieldset id="body">
										<fieldset>
											<label
												style="font-size: 14px; text-align: left; font-weight: bold;">${user.firstName}</label>
										</fieldset>
										<fieldset>
											<label style="text-align: left;">${user.mailId}</label>
										</fieldset>
										<a href="#" class="userroles">Profile</a> <input type="submit" id="login"
											value="Sign out" />
									</fieldset>
									<span><a href="#change-password-box"
										class="change-password-window" id="changePassword"
										style="margin-left: 30px;" onclick="resetChangePwdForm();">Change
											password</a></span>
								</form>
							</div>
						</div>
					</div>
		</table>
	</div>
	<div align="center" style="width: 100%; padding-left: 5%">
		<ul class="single_sticky_notes" id="single_sticky_notes">
		</ul>
	</div>
	<div id="change-password-box" hidden="hidden">
		<h5>Change Password</h5>
		<div id="change-login-box">
			<table>
				<tr>
					<td class="pwd-box-name">Old Password:</td>
					<td class="pwd-box-field"><input name="old-password"
						type="password" class="pwd_form-login" id="old-password"
						title="Old Password" value="" size="30" maxlength="2048" /></td>
				</tr>
				<tr>
					<td class="pwd-box-name">New Password:</td>
					<td class="pwd-box-field"><input name="new-password"
						type="password" class="pwd_form-login" id="new-password"
						title="New Password" value="" size="30" maxlength="2048" /></td>
				</tr>
				<tr>
					<td class="pwd-box-name">Confirm Password:</td>
					<td class="pwd-box-field"><input name="confirm-password"
						type="password" class="pwd_form-login" id="confirm-password"
						title="Confirm Password" value="" size="30" maxlength="2048" /></td>
				</tr>
			</table>
			<div>
				<br /> <br />
			</div>
			<div align="center">
				<button id="changeBtn" class="changeBtn" style="color: white;">Change</button>
			</div>
		</div>
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
				<td><select id='priority' name="priority"
					class="pwd_form-login">
						<option value='L' selected="selected">Low</option>
						<option value='M'>Medium</option>
						<option value='H'>High</option>
						<option value='HS'>Highest</option>
				</select></td>
			</tr>
			<tr>
				<td class="pwd-box-name" id="status">Task Status:</td>
				<td><select id='status' name="status" class="pwd_form-login">
						<option value='NEW' selected="selected">New</option>
						<option value='CANCEL'>Cancelled</option>
						<option value='COMPLETE'>Completed</option>
						<option value='DEV'>Development</option>
						<option value='HOLD'>Hold</option>
				</select></td>
			</tr>
			<tr>
				<td class="pwd-box-name">Assigned To:</td>
				<td class="pwd-box-field"><input name="task-assigned"
					class="pwd_form-login" id="task-assigned" size="30"
					maxlength="2048" /></td>
			</tr>
		</table>
		<div>
			<a hidden="hidden" id='task-id'></a> <br /> <br /> <input
				type="text" id="task-created-user" value="${user.username}"
				hidden="hidden" />
		</div>
		<div align="center">
			<button id="submitBtn" class="submitBtn" style="color: white;"
				onclick="submitNewTask()">Submit</button>
		</div>
	</div>
</body>
</html>