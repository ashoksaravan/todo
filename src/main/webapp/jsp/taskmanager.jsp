<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<c:url value="/users/task" var="taskUrl" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../resources/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery-ui-1.8.23.custom.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../resources/css/stickystyle.css"></link>
<link href="../resources/css/jquery-ui-1.8.23.custom.css"
	rel="stylesheet" type="text/css" />
<link href="../resources/css/login-box.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="../resources/js/action.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$("input").attr('readonly', true);
		$('#change-password-box').dialog({
			autoOpen : false,
			height : 500,
			width : 510,
			modal : true,
			resizable : false,
		});
		$('a.change-password-window').click(function() {
			$("#change-password-box").dialog('open');
		});
	});
	$(function(){
		urlHolder.task = '${taskUrl}';
	});
	
	window.onload = function(){
		var displayName = '<%=request.getParameter("valnm")%>'
		$.post(urlHolder.task, {
			username : displayName,
		}, function(response) {
			if (response != null) {
				for ( var i = 0; i < response.tasks.length; i++) {
					createNotes(response.tasks[i])
				}
			}
		});
	}
</script>
</head>
<body>
	<div style="padding-top: 2em;">
		<table>
			<tr>
				<td>
					<h6>Welcome!</h6>
				</td>
				<td>
					<h6><%=request.getParameter("valnm")%></h6>
				</td>
				<td>
					<h5 style="padding-left: 20em;" align="center">Task ToDo</h5>
				</td>
				<td style="padding-left: 25em;"><span
					class="change-login-box-options"><a href="#"
						class="change-password-window" id="changePassword"
						style="margin-left: 30px;">Change password</a> </span></td>
			</tr>
		</table>
	</div>
	<!-- div class="container"> -->
		<div align="center">
			<ul class="single_sticky_notes" id="single_sticky_notes">
			</ul>
		</div>
		<div id="change-password-box" title="Change Password">
			<div id="login-box" style="vertical-align: middle;">
				<div>
					<br /> <br /> <br /> <br />
				</div>
				<div id="login-box-name">Old Password:</div>
				<div id="login-box-field">
					<input name="old-password" type="password" class="form-login"
						id="old-password" title="Old Password" value="" size="30"
						maxlength="2048" />
				</div>
				<div>
					<br /> <br /> <br /> <br />
				</div>
				<div id="login-box-name">New Password:</div>
				<div id="login-box-field">
					<input name="new-password" type="password" class="form-login"
						id="new-password" title="New Password" value="" size="30"
						maxlength="2048" />
				</div>
				<div>
					<br /> <br /> <br /> <br />
				</div>
				<div id="login-box-name">Confirm Password:</div>
				<div id="login-box-field">
					<input name="confirm-password" type="password" class="form-login"
						id="confirm-password" title="Confirm Password" value="" size="30"
						maxlength="2048" />
				</div>
				<div>
					<br /> <br /> <br /> <br />
				</div>
				<div align="center">
					<button id="changeBtn" style="color: white;"
						onclick="changePasswordAction()">Change</button>
				</div>
			</div>
		</div>
	<!-- /div> -->
</body>
</html>