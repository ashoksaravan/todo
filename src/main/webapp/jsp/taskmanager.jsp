<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

	$(document).ready(function() {
		$('.sticky_notes').dialog({
			autoOpen : false,
			height : 700,
			width : 900,
			modal : true,
			resizable : false,
		});
		$('#refreshNotes').click(function() {
			$(".sticky_notes").dialog('open');
		});
	});

	function clickSelection() {
		$('.single_sticky_notes').dialog({
			autoOpen : false,
			height : 400,
			width : 400,
			modal : true,
			resizable : false,
		});
		$(".single_sticky_notes").dialog('open');
	}
</script>
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
	<div class="container">
		<div align="center">
			<h2 align="center">Task ToDo</h2>

			<table class="newTasks" id="newTasks">
				<tr>
					<th>Title</th>
					<th>Description</th>
					<th></th>
				</tr>
				<tr class="highlight" id="highlight" onclick="clickSelection()">
					<td><input type="text" id="title-1" value="Milk" /></td>
					<td><input type="text" id="description-1"
						value="Need to go shopping to pick up milk." /></td>
				</tr>
				<tr class="highlight">
					<td><input type="text" id="title-2" value="Pay Bills" /></td>
					<td><input type="text" id="description-2"
						value="Gas and electric bills due soon." /></td>
				</tr>
				<tr class="highlight">
					<td><input type="text" id="title-3" value="Take Dogs To Vets" /></td>
					<td><input type="text" id="description-3"
						value="Dog needs taking to vets for injection." /></td>
				</tr>
				<tr class="highlight">
					<td><input type="text" id="title-4" value="Record Tv Program" /></td>
					<td><input type="text" id="description-4"
						value="Saving Private Ryan on tv make sure you record it." /></td>
				</tr>
				<tr class="highlight">
					<td><input type="text" id="title-5" value="Go Doctors" /></td>
					<td><input type="text" id="description-5"
						value="Doctors appointment is tomorrow!" /></td>
				</tr>
				<tr class="highlight">
					<td><input type="text" id="title-6" value="Car Insurance" /></td>
					<td><input type="text" id="description-6"
						value="Car insurance due end of the month." /></td>
				</tr>
				<tr class="highlight">
					<td><input type="text" id="title-7" value="Go Dentist" /></td>
					<td><input type="text" id="description-7"
						value="Dentist appointment is next Week. Make sure you bruch them teeth!" /></td>
				</tr>
				<tr class="highlight">
					<td><input type="text" id="title-8" value="Home Insurance" /></td>
					<td><input type="text" id="description-8"
						value="House insurance finishes tomorrow don't burn the place down." /></td>
				</tr>
			</table>
			<p>
				<input type="button" id="refreshNotes" value="View All Tasks" />
			</p>
			<div class="clear"></div>

			<ul class="sticky_notes">

			</ul>
			<ul class="single_sticky_notes">

			</ul>
		</div>

		<div class="clear"></div>

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
	</div>
</body>
</html>