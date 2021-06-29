<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset="utf-8" />
<title>Insert Title Here</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">

function validate(){
	
		
	
		document.forms[0].hmode.value = "SHOWRPT";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		
	}
	
function cancel()
 {
       	document.forms[0].hmode.value = "CANCEL";
  	    document.forms[0].submit();
 }
</script>	
</head>
<body >
<html:form action="/reports/DischargeSummaryCNT.cnt" method="post">
	<div class="normalMsg" id="normalMsg"></div>

	<center>
	<tag:tab tabLabel="Discharge Summary" selectedTab="FIRST" align="center" width="85%">
	</tag:tab>
	</center>

	<table class="TABLEWIDTH" align="center">   
	<tr class="HEADER">
			<td colspan="2">Discharge Summary&gt;&gt; Add</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">
			<font size="1" color="red">*</font>Admission No.
			</td>
			<td width="50%" class="CONTROL">
			<input class="txtFldMax" type="text" name="strAdmNo" onkeypress="return validateData(event,5);" >
			</td>
			
		</tr>
		<tr>
			<td width="50%" class="LABEL">
			<font size="1" color="red">*</font>Hospital Code
			</td>
			<td width="50%" class="CONTROL">
			<input class="txtFldMin" type="text" name="strHospCode" onkeypress="return validateData(event,5);">
			</td>
			
		</tr>
		
		
		<tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="dischargeSum" value="1"></html:checkbox>
			</td>
			
		</tr>
		
		<tr>
			<td width="50%" class="LABEL">
			User Remarks
			</td>
			<td width="50%" class="CONTROL">
			<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>
			
		</tr>
		<tr class="FOOTER">
			 <td colspan="2"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="center">
			<!-- <img style="cursor :hand;cursor :pointer"
				src="../../hisglobal/images/btn-sv.png" onClick="return validate();" />
			<img style="cursor :hand;cursor :pointer" src="../../hisglobal/images/btn-clr.png" onClick="" >
			<img style="cursor :hand;cursor :pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancel();" >
			 -->
			<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
							<a href="#" class="button"	onClick=" "><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel();"><span class="cancel">Cancel</span></a>

			</td>
		</tr>
	</table>
<input type="hidden" name="hmode"/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>