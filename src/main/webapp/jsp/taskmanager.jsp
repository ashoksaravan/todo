<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../resources/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery-ui-1.8.23.custom.min.js"></script>
<link rel="stylesheet" type="text/css" href="../resources/css/style.css"></link>
<link href="../resources/css/jquery-ui-1.8.23.custom.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/login-box.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../resources/js/action.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#taskmanager_yellow').draggable();
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
</script>
<style>
body {
	height: 88%;
	width: 98%;
	padding: 0 0 0 0;
}
</style>
</head>
<body>
	<div style="padding-top: 2em">
		<table>
			<tr>
				<td>
					<h6>Welcome!</h6>
				</td>
				<td style="padding-left: 50em;"><span
					class="change-login-box-options"><a href="#"
						class="change-password-window" id="changePassword"
						style="margin-left: 30px;">Change password</a> </span></td>
			</tr>
		</table>
	</div>
	<div style="overflow: auto; height: 100%">
		<c:forEach var="i" begin="1" end="6">
			<c:if test="${i%2 == 0}">
				<div id="taskmanager_yellow" style="background-color: fuchsia;">
					<c:out value="${'The task id is '}"></c:out>
					<c:out value="${i}"></c:out>
				</div>
			</c:if>
			<c:if test="${i%2 == 1}">
				<div id="taskmanager" style="background-color: #2191c0;">
					<c:out value="${i}"></c:out>
				</div>
			</c:if>
		&nbsp;
		</c:forEach>
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
</body>
</html>