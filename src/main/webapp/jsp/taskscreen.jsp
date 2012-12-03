<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/users/history" var="historyUrl" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="icon" type="image/gif"
	href="<%=request.getContextPath()%>/resources/images/Ebix-small.gif">
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/action.js"></script>
<link
	href="<%=request.getContextPath()%>/resources/fancybox/jquery.fancybox.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/modal-style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/fancybox/jquery.fancybox.js"></script>

</head>
<script type="text/javascript">
	$(function() {
		urlHolder.history = '${historyUrl}';
	});

	window.onload = function() {
		var history = {};
		$.post(urlHolder.history, {
			taskid : '<%=request.getParameter("task")%>'
		}, function(response) {
			if (response != null) {
				for ( var i = response.taskHistory.length - 1; i >= 0; i--) {
					var row = '<table id="tableHistory" class="tableHistory">';
					row += '<tr style="width: 100%;">';
					row += '<td style="width: 12%;"><h5 align="left">' +'Version# '+ response.taskHistory[i].version + '</h5></td>';
					row += '<td style="width: 88%;"><h5 style="width: 100%;font-weight: normal;">' +' TaskName : ' +response.taskHistory[i].taskname+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					row += 'Assigned To : ' +response.taskHistory[i].username+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					row += ' Editor : ' +response.taskHistory[i].editor+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					row += ' Task Status : ' +response.taskHistory[i].taskstatus + '</h5></td></tr>';
					row += '<tr style="width: 100%;">';
					row += '<td><h5>' +'Description ' + '</h5></td>';
					row += '<td><h5 style="font-weight: normal;">'+response.taskHistory[i].taskdesc + '</h5></td></tr>';
					row += '</table>';
					$('#taskHistoryContainer').append(row);
				}
			}else{
				alert("Error");
			}
		});
	}
	
</script>
<body>
<jsp:include page="/jsp/header.jsp"/>
<div style="width: 100%; height: 50%;" align="center" >
	<div style="width: 80%;">
		<table style="width: 100%; height: 100%;">
			<tr>
				<td align="center"><h4 style="color: black; background-color: #6B6BB2; padding-top: 1%">Task
						History</h4></td>
			</tr>
			<tr>
				<td>
					<div id="taskHistoryContainer" style="width: 100%;"></div></td>
			</tr>
			<tr>
				<td align="right">
				<br>
					<button onclick="back('<%=request.getContextPath()%>')" id="backBtn">
						<img
							src="<%=request.getContextPath()%>/resources/css/images/arrow_left.png" />
						Back to Tasks
					</button>
				</td>
			</tr>
		</table>
	</div>
	</div>
</body>
</html>