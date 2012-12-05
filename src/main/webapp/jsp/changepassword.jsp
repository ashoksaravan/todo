<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:url value="/users/login" var="loginUrl" />
<c:url value="/users/forgotpwd" var="forgotpwdUrl" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Change Password</title>

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
			<form action="<%=request.getContextPath()%>/users/changePassword"
				method="post" onsubmit="return changePassword()">
				<table style="padding: 10%" width="100%">
					<tr>
						<td colspan="2" align="center"><h3>Change Password</h3></td>
					</tr>
					<tr>
						<td style="padding: 2%">Old Password:</td>
						<td style="padding: 2%"><input id="oldPwd" class="form-login"
							value="" name="oldPwd" size="30" maxlength="50" type="password"
							required="required" /></td>
					</tr>
					<tr>
						<td style="padding: 2%">New Password:</td>
						<td style="padding: 2%"><input id="newPwd" class="form-login"
							required="required" name="newPwd" size="30" maxlength="50"
							type="password" /></td>
					</tr>
					<tr>
						<td style="padding: 2%">Confirm Password:</td>
						<td style="padding: 2%"><input id="confirmPwd"
							class="form-login" required="required" name="confirmPwd"
							size="30" maxlength="50" type="password" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><div style="color: #D69999;"
								id="changepwdvalidation" hidden="hidden">New and Confirm
								Password must match!</div>
							<div style="color: #D69999;">${message}</div></td>
					</tr>
					<tr>
						<td colspan="2" align="center" style="padding: 2%"><button
								type="submit" id="changePwdBtn" style="color: white;">Change</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
