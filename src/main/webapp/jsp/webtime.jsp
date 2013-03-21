<%@page import="com.todo.domain.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="/webtime/readtimesheet" var="readtimesheetUrl" />
<c:url value="/webtime/timeSheeturl" var="timeSheetUrl" />
<c:url value="/webtime/updateWebtime" var="updateWebtimeUrl" />
<c:url value="/webtime/read" var="readUrl" />
<c:url value="/external/exportTimeSheet" var="writeUrl" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Time Sheet</title>
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

<%-- <script src='<c:url value="/resources/js/jquery.jqGrid.min.js"/>'
	type="text/javascript"></script> --%>
<script src='<c:url value="/resources/js/jquery.jqGrid.src.js"/>'
	type="text/javascript"></script>

<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
	var searchDetail = [];
	var colModelArr = [];
	var timeDetails = [];
	var todayDate = new Date();
	var startDate = new Date();
	var endDate = new Date();

	$(function() {
		var role = '${user.role.role}';
		if(role == 1){
			$('.report').show();
		}
		$("#fromDatepicker").datepicker();
		$("#toDatepicker").datepicker();
	});

	$(function() {
		urlHolder.readtimesheet = '${readtimesheetUrl}';
		urlHolder.timeSheeturl = '${timeSheetUrl}';
		urlHolder.updateWebtime = '${updateWebtimeUrl}';
		urlHolder.read = '${readUrl}';
		var currentDate = new Date();
		$('#fromDatepicker').val('');
		$('#toDatepicker').val('');
		document.getElementById("checkbox_id").checked = false;
		$("#fromDatepicker").removeClass("error");
		$("#toDatepicker").removeClass("error");
		$('#project option').eq(0).attr('selected', 'selected');
		calcDate(currentDate);
		//buildGrid();
	});

	function calcDate(todayDate) {
		searchDetail = [];
		var newDate = new Date();
		var month_names = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
				'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];
		var day_names = [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thur', 'Fri', 'Sat' ];
		if (todayDate.getDay() != 0) {
			todayDate.setDate(todayDate.getDate() - todayDate.getDay());
			startDate = todayDate;
			formDetails(startDate);
		}
		for (i = 1; i < 7; i++) {
			todayDate.setDate(todayDate.getDate() + 1);
		}
		/* if (todayDate.getDay() != 0) {
			var firstDate = new Date();
			todayDate.setDate(todayDate.getDate() - todayDate.getDay());
			firstDate.setDate(todayDate.getDate() - todayDate.getDay());
			searchDetail.push(day_names[firstDate.getDay()] + ' '
					+ month_names[firstDate.getMonth()] + ' '
					+ firstDate.getDate() + ' ' + firstDate.getFullYear());
			startDate.setDate(todayDate.getDate() - todayDate.getDay());

		}
		for (i = 1; i < 7; i++) {
			newDate = new Date();
			todayDate.setDate(todayDate.getDate() + 1);
			newDate.setDate(todayDate.getDate());
			searchDetail.push(day_names[newDate.getDay()] + ' '
					+ month_names[newDate.getMonth()] + ' ' + newDate.getDate()
					+ ' ' + newDate.getFullYear());
		} */
	}

	function lastWeek() {
		todayDate.setDate(todayDate.getDate() - 8);
		calcDate(todayDate);
		//buildGrid();
	}

	function nextWeek() {
		todayDate.setDate(todayDate.getDate() + 2);
		calcDate(todayDate);
		//buildGrid();
	}

	function formDetails(todayDate) {
		/* var start ={"users" : document.getElementById("checkbox_id").checked,
				"from" : document.getElementById("fromDatepicker").value,
				"to" : document.getElementById("toDatepicker").value,
				"list" : searchDetail}; */
		$.post(urlHolder.read, {
			startDate : startDate
		}, function(response) {
			if (response) {
				/* timeDetails = [];
				for ( var i = 0; i < response.rows.length; i++) {
					timeDetails.push(response.rows[i]);
				} */
				buildGrid();
			} else {
				alert("Not valid information");
			}
		});
	}

	function buildGrid() {

		var lastsel;
		$('#tableUsers').jqGrid('GridUnload');
		jQuery("#tableUsers").jqGrid({
			url : urlHolder.readtimesheet,
			datatype : "json",
			colNames : [ 'Date', 'Project', 'Description', 'Hrs' ],
			colModel : [ {
				name : 'date',
				index : 'date',
				width : 100,
				edittype : "text",
				sortable : false
			}, {
				name : 'projectName',
				index : 'projectName',
				width : 200,
				edittype : "text",
				editable : true,
				sortable : false
			}, {
				name : 'desc',
				index : 'desc',
				edittype : "text",
				editable : true,
				width : 400,
				sortable : false
			}, {
				name : 'hrs',
				index : 'hrs',
				editable : true,
				edittype : "text",
				width : 100,
				editoptions : {
					dataInit : function(hrs) {
						$(hrs).keyup(function() {
							var val1 = hrs.value;
							var num = new Number(val1);
							if (isNaN(num) || num > 8) {
								hrs.value = '';
							}
							if(num < 8){
								hrs.value = '';
								alert("Hrs should be 8");
							}
						})
					}
				},
				sortable : false
			} ],
			rowNum : 5,
			pager : '#userPager',
			sortname : 'date',
			jsonReader : {
				repeatitems : false,
				id : "0"
			},

			viewrecords : true,
			sortorder : "asc",
			height : '100%',
			width : 850,
			onSelectRow : function(date) {
				if (date && date !== lastsel) {
					jQuery('#tableUsers').jqGrid('restoreRow', lastsel);
					jQuery('#tableUsers').jqGrid('editRow', date, true);
					lastsel = date;
				}
			},
			editurl : urlHolder.updateWebtime,
			caption : "TimeSheet",

		});

		jQuery("#tableUsers").jqGrid('navGrid', '#userPager', {
			edit : true,
			add : false,
			del : false,
			search : false,
			refresh : false
		});

		/* for ( var i = 0; i < searchDetail.length; i++) {
			var coll = {};
			coll = {
				date : searchDetail[i],
				projectName : "",
				desc : "",
				hrs : ""
			};
			jQuery("#tableUsers").jqGrid('addRowData', i + 1, coll);
		} */
		/* for ( var i = 0; i < timeDetails.length; i++) {
			jQuery("#tableUsers").jqGrid('addRowData', i + 1, timeDetails[i]);
		}  */
	}

	function addTimeSheetProject() {
		window.location.href = "/todo/jsp/projectSheet.jsp";
	}

	function exportWebProject() {
		var startDate = new Date($('#fromDatepicker').val());
		var endDate = new Date($('#toDatepicker').val());
		if ($('select#project option:selected').val() == 'SELECT') {
			$('select#project option:selected').val('');
		}
		if ($('#fromDatepicker').val() == '' || $('#toDatepicker').val() == '') {
			$("#fromDatepicker").addClass("error");
			$("#toDatepicker").addClass("error");
		} else {
			$("#fromDatepicker").removeClass("error");
			$("#toDatepicker").removeClass("error");
			if (startDate >= endDate) {
				alert('End Date should be after Start Date');
			} else {
				var data = {
					"users" : document.getElementById("checkbox_id").checked,
					"from" : document.getElementById("fromDatepicker").value,
					"to" : document.getElementById("toDatepicker").value,
					"projectId" : $('select#project option:selected').val()
				};
				$.post(urlHolder.timeSheeturl, {
					users : document.getElementById("checkbox_id").checked,
					from : document.getElementById("fromDatepicker").value,
					to : document.getElementById("toDatepicker").value,
					projectId : $('select#project option:selected').val()
				}, function(response) {
					if (response) {
						window.location.href = ctx
								+ '/external/exportTimeSheet.xls';
					} else {
						alert("Excel not generated");
					}
				});
			}
		}
	}
	
	function checkAllUser(){
		if(document.getElementById("checkbox_id").checked){
			document.getElementById('project').disabled = false;
		}else{
			document.getElementById('project').disabled = true;
		}
	}
</script>
</head>
<body>
<jsp:include page='/jsp/header.jsp' />
	<div class="tab-content" align="center" style="padding-top: 5%;">
		<div id="v-div">
			<table align="center">
				<tr>

					<td align="right" class="report" hidden="hidden">From: <input
						type="text" id="fromDatepicker" /> To: <input type="text"
						id="toDatepicker" /> <input type="checkbox" name="checkbox"
						id="checkbox_id" value="value" onclick="checkAllUser()"/> <label for="checkbox_id">AllUsers</label>
						<select id='project' name="project" disabled="disabled">
							<option value="SELECT">-Select-</option>
							<c:forEach items="${projectList}" var="projectLst">
								<option value="${projectLst.projectId}">${projectLst.projectName}</option>
							</c:forEach>
						</select>
						<button onclick="exportWebProject()">Go</button>
						<button onclick="addTimeSheetProject()" hidden="hidden">Project</button>
					</td>
				</tr>
				<tr>
					<td align="center">
						<table id="tableUsers" width="100px;">
						</table>
					</td>
				</tr>
				<tr>
					<td align="right"><a href="#lastWeek" onclick="lastWeek()"><img
							src="<%=request.getContextPath()%>/resources/images/back.png"
							style="cursor: pointer; width: 34px; height: 17px;" /></a> <a
						href="#nextWeek" onclick="nextWeek()"><img
							src="<%=request.getContextPath()%>/resources/images/next.png"
							style="cursor: pointer; width: 34px; height: 17px;" /></a></td>
				</tr>
			</table>


			<div id="userPager"></div>
			<div id='controlBar' align="left"></div>
		</div>
</body>
</html>