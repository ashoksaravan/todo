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
	href="<%=request.getContextPath()%>/resources/images/Ebix-small.gif" />

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

<link href="<%=request.getContextPath()%>/resources/css/taskstyle.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<div align="center" style="width: 100%; height: 100%; padding-top: 10%">
		<div id="login-box">
			<form action="changePassword" method="post">

				<div id="login-box-name" style="margin-top: 20px;">Old
					Password:</div>
				<div id="login-box-field" style="margin-top: 20px;">
					<input id="oldpwd" class="form-login" title="Old Password" value=""
						name="oldpwd" size="30" maxlength="50" type="text" />
				</div>

				<div id="login-box-name">New Password:</div>
				<div id="login-box-field">
					<input id="newPwd" class="form-login" title="New Password"
						name="newPwd" size="30" maxlength="50" type="password" />
				</div>

				<div id="login-box-name">Confirm Password:</div>
				<div id="login-box-field">
					<input id="confirmPwd" class="form-login" title="Confirm Password"
						name="confirmPwd" size="30" maxlength="50" type="password" />
				</div>

				<br /> <br />
				<button id="forgotBtn" style="color: white;"
					onclick="forgotAction()">Change Password</button>
			</form>
		</div>
	</div>
</body>
</html>
