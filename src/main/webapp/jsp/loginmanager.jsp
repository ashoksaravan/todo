<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:url value="/users/forgotpwd" var="forgotpwdUrl" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Login Page</title>
<title>Login Box Task Manager</title>

<link rel="icon" type="image/gif"
	href="<%=request.getContextPath()%>/resources/images/Ebix-small.gif" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.9.2.custom.min.js"></script>
<link
	href="<%=request.getContextPath()%>/resources/css/jquery-ui-1.9.2.custom.css"
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
		if ( $.browser.msie ) {
			$('<div></div>').appendTo('body')
            .html('<div><h6>Do you want to continue?</h6></div>')
            .dialog({
                modal: true, title: 'Unsupported browser', zIndex: 10000, autoOpen: true,
                width: 'auto', resizable: false,
                buttons: {
                    Yes: function () {
                        $(this).dialog("close");
                    },
                    No: function () {
                    	window.close();
                        $(this).dialog("close");
                    }
                },
                close: function (event, ui) {
                    $(this).remove();
                }
            });
		}
	});
	$(function() {
		urlHolder.forgotpwd = '${forgotpwdUrl}';
	});
	$(function() {
		$('#forget-password').click(function() {
			$('#forgot-login-box-usrName').val('');
			$("#forgot-login-box-usrName").removeClass("error");
		});
	});
</script>
</head>

<body>
	<div align="center" style="width: 100%; height: 100%; padding-top: 10%">
		<div id="login-box">
			<form action="j_spring_security_check" method="post">
				<h2>Login</h2>
				Task Manager for Developers <br /> <br />

				<div id="login-box-name" style="margin-top: 20px;">UserName:</div>
				<div id="login-box-field" style="margin-top: 20px;">
					<input id="j_username" class="form-login" title="Username" value=""
						name="j_username" size="30" maxlength="50" type="text" />
				</div>

				<div id="login-box-name">Password:</div>
				<div id="login-box-field">
					<input id="j_password" class="form-login" title="Password"
						name="j_password" size="30" maxlength="50" type="password" />
				</div>

				<div style="color: #D69999; font-weight: bold; font-size: 15px"
					id="loginvalidation">${message}</div>
				<span class="login-box-options"><input type='checkbox' name='_spring_security_remember_me'/> Remember Me <a
						href="#forget-password-box" id="forget-password"
						class="forgot-password-window" style="margin-left: 30px;">Forgot
							password?</a></span> <br /> <br /> <input type="image"
					src="<%=request.getContextPath()%>/resources/css/images/login-btn.png"
					alt="Login">
			</form>
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
