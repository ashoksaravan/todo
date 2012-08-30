<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:url value="/users/login" var="loginUrl" />
<c:url value="/users/records" var="recordsUrl" />
<c:url value="/users/create" var="addUrl" />
<c:url value="/users/update" var="editUrl" />
<c:url value="/users/delete" var="deleteUrl" />
<html>
<head>
<title>Login</title>
<link rel='stylesheet' type='text/css' media='screen'
	href='<c:url value="/resources/css/style.css"/>' />
<script type='text/javascript'
	src='<c:url value="/resources/js/jquery-1.8.0.min.js"/>'></script>
<script type='text/javascript'
	src='<c:url value="/resources/js/custom.js"/>'></script>

<script type='text/javascript'>
	$(function() {
		urlHolder.login = '${loginUrl}'
		urlHolder.records = '${recordsUrl}';
		urlHolder.add = '${addUrl}';
		urlHolder.edit = '${editUrl}';
		urlHolder.del = '${deleteUrl}';
		$('#loginForm').submit(function(event) {
			event.preventDefault();
			login();
		});
	});
</script>
</head>
<body>
	<div id='loginForm'>
		<form>
			<fieldset>
				<legend>Login</legend>
				<label for='newUsername'>Username</label><input type='text'
					id='username' /><br /> <label for='password'>Password</label><input
					type='password' id='password' /><br /> <input type='button'
					value='Cancel' id='closeNewForm' /> <input type='submit'
					value='Login' />
			</fieldset>
		</form>
	</div>
</body>
</html>