<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<!-- 
## 		Modification Log							
##		Modify Date				:16thDec'14 
##		Reason	(CR/PRS)		:Bug fix 7734
##		Modify By				:Sheeldarshi
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script> -->
</head>
<script>
	$("#handoverDateTimeId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
</script>
<body>
	<div class="div-table" >
		<div class="div-table-row title">
			<div class="div-table-col width100">HandOver To Detail</div>
		</div>
	</div>
	<div class="div-table" >
		<div class="div-table-row">
			<div class="div-table-col width25 label">Name</div>
			<div class="div-table-col width25 control">
				<input type="text" class="input60prcnt" name="handoverOffName">
			</div>
			<div class="div-table-col width25 label">Designation</div>
			<div class="div-table-col width25 control">
				<input type="text" class="input60prcnt" name="handoverOffDesg">
			</div>
		</div>
		<div class="div-table-row">
			<div class="div-table-col width25 label">Belt No</div>
			<div class="div-table-col width25 control">
				<input type="text" class="input60prcnt" name="handoverOffBadgeNo">
			</div>
			<div class="div-table-col width25 label">HandOver Date</div>
			<div class="div-table-col width25 control">
				<input type="text" class="input30prcnt" name="handoverDateTime" id="handoverDateTimeId"  readonly="readonly">&nbsp;
			</div>
		</div>
	</div>
</body>
</html>