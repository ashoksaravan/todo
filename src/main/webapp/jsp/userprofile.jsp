<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<c:url value="/users/records" var="recordsUrl" />
<c:url value="/users/create" var="addUrl" />
<c:url value="/users/update" var="editUrl" />
<c:url value="/users/delete" var="deleteUrl" />
<c:url value="/users/task" var="taskUrl" />
<c:url value="/users/refdataProject" var="refdataProjectUrl" />
<html>
<head>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.23.custom.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/stickystyle.css"></link>
<link
	href="<%=request.getContextPath()%>/resources/css/jquery-ui-1.8.23.custom.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/login-box.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/taskstyle.css"
	rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/resources/css/userstyle.css"
	rel="stylesheet" type="text/css" />


<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/action.js"></script>
	
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/custom.js"></script>
<link
	href="<%=request.getContextPath()%>/resources/fancybox/jquery.fancybox.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/modal-style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/fancybox/jquery.fancybox.js"></script>

<title>User Profile</title>
<script type='text/javascript'>
 
$(function() {
		// init
		urlHolder.records = '${recordsUrl}';
		urlHolder.edit = '${editUrl}';
		urlHolder.task = '${taskUrl}';
		urlHolder.refdataProject = '${refdataProjectUrl}';
		loadTable();
		
		$('.userprofileInpt').hide();
		$('#saveProfile').hide();
		$('#cnclProfile').hide();
		
		$('#editProfile').click(function() {
		
			$('.userprofileLbl').hide();
			$('.userprofileInpt').show();
			$('#editProfile').hide();
			$('#saveProfile').show();
			$('#cnclProfile').show();
		});
		
		$('#saveProfile').click(function(event) {
			event.preventDefault();
			
			saveUserProfile('${user.username}', '${user.role.role}');
		});
		$('#cnclProfile').click(function(event) {
			window.location = "/todo/jsp/userprofile.jsp";
		});
		
	});
		

</script>

</head>
<body>


	<jsp:include page='/jsp/header.jsp'/>
	<img src="/todo/resources/css/images/home.png" align="right"
		onclick="back('${pageContext.request.contextPath}')"
		style="cursor: pointer; padding-top: 1mm; padding-right: 1mm; width: 34px; height: 27px; vertical-align: middle;" />
<div align="center"> 
		<form class="profileEditForm">
		
				 <div class="fieldgroup">
				 
				 
				<label for='newFirstName'>First Name:</label>
				<label for='firstName' class="userprofileLbl">${user.firstName} </label>
				<input type='text' id="editfirstName" required="required" title="Please Enter FirstName" name="firstName" class='userprofileInpt' value="${user.firstName}"  />
				</div> 
				
				<hr color="silver">
				
				 <div class="fieldgroup">
				<label for='newLastName'>Last Name:</label>
				<label for='lastName' class="userprofileLbl"  >${user.lastName}</label>
				<input type='text' id="editlastName" required="required" title="Please Enter LastName" name="lastName" class='userprofileInpt' value="${user.lastName}"  />
				 </div>
				
				<hr color="silver">
				
				 <div class="fieldgroup">
				<label for='newMailId'>EmailId:</label> 
				<label for='mailId' class="userprofileLbl">${user.mailId}</label>
				<input type='text' id="editEmailId"  name="email" required="required" title="Please Enter email!" class='userprofileInpt' value="${user.mailId}"  />
				</div> 
				
				<hr color="silver">
				 
				
				<div class="fieldgroup">
				<input type='button' value='Edit' id='editProfile' /> 
				<input type='submit' value='Save' id='saveProfile'/> 
				<input type='button' value='Cancel' id='cnclProfile' /> 
				</div>
				
		</form>
		</div>
		
</body>
</html>