<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="/webtime/webprojRecords" var="webprojRecordsUrl" />
<c:url value="/webtime/updatewebproj" var="updatewebprojUrl" />
<c:url value="/webtime/addwebproj" var="addwebprojUrl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>TimeSheet Projects</title>
<meta http-equiv=Content-Type
	content="application/vnd.ms-excel;charset=ISO-8859-1">
<link rel="icon" type="image/gif"
	href="<%=request.getContextPath()%>/resources/images/Ebix-small.gif">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.9.2.custom.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/stickystyle.css"></link>
<link
	href="<%=request.getContextPath()%>/resources/css/jquery-ui-1.9.2.custom.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/login-box.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/taskstyle.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/userstyle.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/resources/css/administrator-tab.css"
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

<link rel="stylesheet" type="text/css" media="screen"
	href='<c:url value="/resources/css/ui.jqgrid.css" />' />

<script src='<c:url value="/resources/js/grid.locale-en.js"/>'
	type="text/javascript"></script>
<script src='<c:url value="/resources/js/jquery.jqGrid.src.js"/>'
	type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			urlHolder.addwebproj = '${addwebprojUrl}';
			urlHolder.updatewebproj = '${updatewebprojUrl}';
			urlHolder.webprojRecords = '${webprojRecordsUrl}';
			$('#addnewWebProject').hide();
			$('#editWebProject').hide();
			loadWebProjectTable();
			
			$('#webprojaddBtn').click(function() {
				$('#addnewWebProject').show();
				$('#webprojName').val('');
				$('#editWebProject').hide();
				$('#webprojeditBtn').attr('disabled', 'disabled');
			});
			
			$('#webprojeditBtn').click(function() {
				if (hasSelected()) {
					$('#addnewWebProject').hide();
					$('#editWebProject').show();
					$('#webprojaddBtn').attr('disabled', 'disabled');
					fillWebProjectForm();
				}
			});
			
			$('#closeNewWebProject').click(function() {
				$('#addnewWebProject').hide();
				$('#webprojeditBtn').removeAttr('disabled');
			});

			$('#closeEditWebProject').click(function() {
				$('#editWebProject').hide();
				$('#webprojaddBtn').removeAttr('disabled');
			});
			
			$('#addnewWebProject').submit(function(event) {
				event.preventDefault();
				submitAddWebProjRecord();
			});
			
			$('#editWebProject').submit(function(event) {
				event.preventDefault();
				submitEditWebProjRecord();
			});
		});
	</script>
</head>
<body>
	<jsp:include page='/jsp/header.jsp' />
	<br><br>
	<div align="center" id="addWebProject" class="tab-content" >
		<table id='webtimeProjects' class='tableUsers'>
			<caption align="top">
				<b>TimeSheet Projects</b> <br></br>
			</caption>
			<thead id='tableHead'>
				<tr>
					<th></th>
					<th>Project Name</th>
				</tr>
			</thead>
		</table>
		<div id='controlBar' style="width: 80%" align="left">
			<input type='button' value='Add' id='webprojaddBtn' /> <input
				type='button' value='Edit' id='webprojeditBtn' />
		</div>

		<form id='addnewWebProject' class="newEditForm">
			<div class="fieldgroup">
				<label for='webprojName'>Project Name</label> <input type='text'
					id='webprojName' />
			</div>
			<input type='button' value='Close' id='closeNewWebProject' /> <input
				type='submit' value='Submit' />
		</form>


		<form id='editWebProject' class="newEditForm">

			<div class="fieldgroup">
				<label for='editwebprojName'>Project Name</label> <input type='text'
					id='editwebprojName' />
			</div>
			<input type='button' value='Close' id='closeEditWebProject' /> <input
				type='submit' value='Submit' />
		</form>
	</div>
</body>
</html>