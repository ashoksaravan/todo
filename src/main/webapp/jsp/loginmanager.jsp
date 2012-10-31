<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:url value="/users/login" var="loginUrl" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Login Box Task Manager</title>
<script type="text/javascript" src="resources/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui-1.8.23.custom.min.js"></script>
<link href="resources/css/jquery-ui-1.8.23.custom.css" rel="stylesheet"
	type="text/css" />
<link href="resources/css/login-box.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/js/action.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#forget-password-box').dialog({
			autoOpen : false,
			height : 200,
			width : 450,
			modal : true,
			resizable : false,
		});
		$('a.forgot-password-window').click(function() {
			$("#forget-password-box").dialog('open');
		});
	});
	$(function(){
		urlHolder.login = '${loginUrl}'
	});
</script>
</head>

<body>
	<div align="center" style="width: 100%; height: 100%; padding-top: 10%">
		<div id="login-box">
			<H2>Login</H2>
			Task Manager for Developers <br /> <br />
			<div id="login-box-name" style="margin-top: 20px;">UserName:</div>
			<div id="login-box-field" style="margin-top: 20px;">
				<input name="q" class="form-login" title="Username" value="" onchange="loginValidation()"
					id="login-box-usrName" size="30" maxlength="2048" />
			</div>
			<div id="login-box-name">Password:</div>
			<div id="login-box-field">
				<input name="q" type="password" class="form-login" title="Password" onchange="loginValidation()"
					id="login-box-password" value="" size="30" maxlength="2048" />
			</div>
			<span class="login-box-options"><input type="checkbox"
				name="1" value="1"> Remember Me <a href="#"
					id="forget-password" class="forgot-password-window"
					style="margin-left: 30px;">Forgot password?</a></span> <br /> <br /> <a id="submitId"
				onclick="submitAction()" href="#"><img id="submitIdImg" disabled="disabled"
				src="resources/css/images/login-btn.png" width="103" height="42"
				align="middle" /></a>
		</div>
		<div id="forget-password-box" title="Forgot Password">
			<div id="login-box-name"
				style="margin-top: 20px; padding-right: 2em;">UserName</div>
			<div id="login-box-field" style="margin-top: 20px;">
				<input name="q" class="form-login" title="Username" value=""
					id="forgot-login-box-usrName" size="30" maxlength="2048" />
			</div>
			<br /> <br /> <br /> <br />
			<div align="center">
				<button id="forgotBtn" style="color: white;"
					onclick="forgotAction()">Change</button>
			</div>
		</div>
	</div>
</body>
</html>
