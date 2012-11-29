<%@page import="com.todo.domain.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>

<c:url value="/users/changepwd" var="changepwdUrl" />
<c:url value="/users/checkpwd" var="checkpwdUrl" />
<c:url value="/users/records" var="recordsUrl" />
<html>
<head>

<script type="text/javascript">
$(function() {
	urlHolder.changepwd = '${changepwdUrl}';
	urlHolder.checkpwd = '${checkpwdUrl}';
	urlHolder.records = '${recordsUrl}';
});

$(function() {
	$(".change-password-window").fancybox();
	$('.refClass').fancybox();
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

</script>
</head>
<body>
	<div
		style="width: 100%; height: 67px; padding-top: 0px; padding-left: 0px; padding-right: 0px; padding-bottom: 0px;">

		<table
			style="width: 100%; height: 100%; border-left: 0px; border-right: 0px; border-top: 0px; padding-left: 0px; padding-right: 0px;"
			id="header">
			<tr>
				<td
					style="border-right: 0px; border-left: 0px; border-bottom: 0px; vertical-align: top; padding-right: 0px"><img
					src="<%=request.getContextPath()%>/resources/images/ebixlogo.PNG"
					align="top" height="70%" /></td>
				<td align="right"
					style="border-left: 0px; border-right: 0px; border-top: 0px; border-bottom: 0px; padding-top: 0px; padding-left: 0px;">
					<div id="container">
						<!-- Login Starts Here -->
						<div id="loginContainer">
							<a href="#" id="loginButton"><span>${user.firstName},
									${user.lastName}</span></a>
							<div style="clear: both"></div>
							<div id="loginBox">
								<form id="loginForm" action="logout">
									<fieldset id="body">
										<fieldset>
											<label
												style="font-size: 14px; text-align: left; font-weight: bold;">${user.firstName}</label>
										</fieldset>
										<fieldset>
											<label style="text-align: left;">${user.mailId}</label>
										</fieldset>
										<a href="#" class="userroles">Profile</a> <input type="submit"
											id="login" value="Sign out" />
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
	<div align="center" style="width: 100%;">
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
</body>
</html>