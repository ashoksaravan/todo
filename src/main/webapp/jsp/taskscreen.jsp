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
</head>
<script type="text/javascript">
	$(function() {
		urlHolder.history = '${historyUrl}';
	});

	window.onload = function() {
		$.post(urlHolder.history, {
			taskid : '<%=request.getParameter("task")%>'
		}, function(response) {
			if (response != null) {
				for ( var i = response.taskHistory.length - 1; i >= 0; i--) {
					var row = '<table>';
					row += '<th>';
					row += '<h4 style="background-color: #2191c0">' +'version'+ response.taskHistory[i].version + '</h4>';
					row += '</th>';
					row += '<tr>'
					row += '<td><h5>' +'Assigned TO: ' +response.taskHistory[i].username + '</h5></td>';
					row += '<td><h5>' +'TaskName: ' +response.taskHistory[i].taskname + '</h5></td></tr>';
					row += '<tr><td>'
					row += '<h5>' +'Task Description: ' +response.taskHistory[i].taskdesc + '</h5></td>';
					row += '</tr></table>';
					$('#taskHistoryContainer').append(row);
				}
			}else{
				alert("Error");
			}
		});
	}
</script>
<body>
	<div align="center"><h4>History For the Task </h4>
	<div id="taskHistoryContainer"></div>
	</div>
</body>
</html>