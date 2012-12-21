<html>
<head>
<title>Access denied!</title>

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

</head>
<body>
	<jsp:include page='/jsp/header.jsp' />
	<div align="center">
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	<h4 class="message">You are unauthorized to view this page</h4>
		<input type='button' value='Back to Tasks' id='backBtn'
			onclick="back('<%=request.getContextPath()%>')" />
	</div>
</body>
</html>