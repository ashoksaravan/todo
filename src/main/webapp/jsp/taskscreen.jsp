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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/action.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/style.css"
	rel="stylesheet" type="text/css" />
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
					row += '<td style="width: 10%;"><h5 align="left">' +'Version# '+ response.taskHistory[i].version + '</h5></td>';
					row += '<td style="width: 90%;"><h5 style="width: 100%;font-weight: normal;">' +' TaskName : ' +response.taskHistory[i].taskname+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
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
	

	function back(){
		window.location.href="<%=request.getContextPath()%>/jsp/taskmanager.jsp";
	};
</script>
<body>
	<div align="center">
		<table style="width: 100%; height: 50%;">
			<tr style="background-color: #6B6BB2;">
				<td align="center"><h4 style="padding-top: 1%; color: #f2f2f2;">Task
						History</h4></td>
			</tr>
			<tr>
				<td><br>
					<div id="taskHistoryContainer" style="width: 100%;"></div></td>
			</tr>
			<tr>
				<td align="right">
				<br>
					<button onclick="back()" id="backBtn">
						<img
							src="<%=request.getContextPath()%>/resources/css/images/arrow_left.png" />
						Back to Tasks
					</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>