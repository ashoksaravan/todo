<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:url value="/users/login" var="loginUrl" />
<c:url value="/users/forgotpwd" var="forgotpwdUrl" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Login Page</title>
<title>Login Box Task Manager</title>

<link rel="icon" type="image/gif"
	href="<%=request.getContextPath()%>/resources/images/Ebix-small.gif"/>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.23.custom.min.js"></script>
<link
	href="<%=request.getContextPath()%>/resources/css/jquery-ui-1.8.23.custom.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/login-box.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/action.js"></script>
<link
	href="<%=request.getContextPath()%>/resources/fancybox/jquery.fancybox.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/fancybox/jquery.fancybox.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/taskstyle.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#forget-password").fancybox();
	});
	$(function() {
		urlHolder.login = '${loginUrl}';
		urlHolder.forgotpwd = '${forgotpwdUrl}';
	});
	$(function(){
		$('#forget-password').click(function(){
			$('#forgot-login-box-usrName').val('');
			$("#forgot-login-box-usrName").removeClass("error");
		});
	});
</script>
</head>

<body>
	<div align="center" style="width: 100%; height: 100%; padding-top: 10%">
		<div id="login-box">
			<h2>Login</h2>
			Task Manager for Developers <br /> <br />
			<div id="login-box-name" style="margin-top: 20px;">UserName:</div>
			<div id="login-box-field" style="margin-top: 20px;">
				<input name="q" class="form-login" title="Username" value=""
					id="login-box-usrName" size="30"
					maxlength="2048" />
			</div>
			<div id="login-box-name">Password:</div>
			<div id="login-box-field">
				<input name="q" type="password" class="form-login" title="Password"
					id="login-box-password" value=""
					size="30" maxlength="2048" />
			</div>
			<div style="color: red; font-weight: bold;" hidden="hidden"
				id="loginvalidation">Login Failed</div>
			<span class="login-box-options"><input type="checkbox"
				name="1" value="1"> Remember Me <a
					href="#forget-password-box" id="forget-password"
					class="forgot-password-window" style="margin-left: 30px;">Forgot
						password?</a></span> <br /> <br /> <a id="submitId"
				onclick="submitAction()" href="#"><img id="submitIdImg"
				disabled="disabled"
				src="<%=request.getContextPath()%>/resources/css/images/login-btn.png"
				width="103" height="42" align="middle" /></a>
		</div>
		<div id="forget-password-box" class="forget-password-box"
			hidden="hidden">
			<table>
				<tr>
					<td>UserName</td>
					<td class="pwd-box-field"><input name="q"
						class="pwd_form-login" title="Enter the Username" value=""
						id="forgot-login-box-usrName" size="30" maxlength="2048" /></td>
				</tr>
			</table>
			<div align="center">
				<button id="forgotBtn" style="color: white;"
					onclick="forgotAction()">Change</button>
			</div>
		</div>
	</div>
</body>
</html>
