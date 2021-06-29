<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dynamic Report</title>

<link href="../../hisglobal/css/newpopup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/dynamicReport.css" rel="stylesheet" type="text/css">
<link href="../css/master.css" rel="stylesheet" type="text/css">
<script language="Javascript" src="../../hisglobal/js/util.js"></script>


<script language="JavaScript" src="../../dynamicreports/js/advance_popup.js"></script>
<script language="JavaScript" src="../../dynamicreports/js/jquery-1.2.6.pack.js"></script>
<script language='Javascript' src='../../hisglobal/js/innerxhtml.js'></script>
<script language='Javascript' src='../../hisglobal/js/commonFunctions.js'></script>
<script src="../../hisglobal/js/jquery.nicescroll.js"></script>


<script type="text/javascript">
	function printData(level) {

		newwin = window.open('', 'printwin',
				'left=100,top=100,width=700,height=700,scrollbars=yes');
		newwin.document.write('<HTML><HEAD>');
		newwin.document
				.write((document.getElementsByTagName("head")[0]).innerHTML);
		newwin.document
				.write('<style type="text/css"> .hidecontrol { display: none; } </style>\n')
		newwin.document.write('<script>\n');
		newwin.document.write('function chkstate(){ \n');
		//newwin.document.write('if(document.readystate=="complete" || document.readystate=="undefined"){\n');
		newwin.document.write('window.close();\n');
		//newwin.document.write('}\n');
		//newwin.document.write('else{\n');
		//newwin.document.write('setTimeout("chkstate()",2000)\n');
		//newwin.document.write('}\n');
		newwin.document.write('}\n');
		newwin.document.write('function print_win(){\n');
		newwin.document.write('window.print();\n');
		newwin.document.write('chkstate();\n');
		newwin.document.write('}\n');

		newwin.document.write('<\/script>\n');
		newwin.document.write('</HEAD>\n');
		newwin.document.write('<BODY onload="print_win()">\n');

		if (level == undefined) {

			newwin.document
					.write((document.getElementsByTagName("body")[0]).innerHTML);

		} else {

			newwin.document
					.write((document.getElementById("levelId" + level)).innerHTML);
		}

		newwin.document.write('</BODY>\n');
		newwin.document.write('</HTML>\n');
		newwin.document.close();

	}

	var gblLevel = "0";
	var gblRptContent = "";

	function getNextLevelReport(count, level , param) {

			
		gblLevel = level;

		var strOutPramValuesHidden = document
				.getElementById("strOutPramValuesHidden" + level + "" + count).value;
		var strTemplateProcName = document.getElementById("strTemplateProcName"
				+ level).value;
		var strInParamHiddenValues = document
				.getElementById("strInParamHiddenValues" + level).value;

		var strInParamIdHiddenValues = document
				.getElementById("strInParamIdHiddenValues" + level).value;

		var strTemplateProcId = document.getElementById("strTemplateProcId"
				+ level).value;

		var strReportTypeId = document.getElementById("strReportTypeId").value;
		var strReportTemplateId = document
				.getElementById("strReportTemplateId").value;
		var strTemplateProcDisplayName = document
				.getElementById("strTemplateProcDisplayName" + level).value;

		
		var strInParamDisplayNameObj = document.getElementsByName("strInParamDisplayName" + level);
		var strInParamDisplayValueObj = document.getElementsByName("strInParamDisplayValue" + level);
		
	
		var strInParamDisplayNameObjVal = "";
		var strInParamDisplayValueObjVal = "";
		
		if(strInParamDisplayNameObj != null && strInParamDisplayNameObj.length > 0){
			
			
			
			for(var i = 0 ; i < strInParamDisplayNameObj.length ; i++ ){
				
				 if(param.split('^')[0] != strInParamDisplayNameObj[i].value){
					 
					 if(i == 0){
							strInParamDisplayNameObjVal = strInParamDisplayNameObj[i].value;
							strInParamDisplayValueObjVal = strInParamDisplayValueObj[i].value;
							
						}else{
							
							if(strInParamDisplayNameObjVal.length == 0){
								
								strInParamDisplayNameObjVal = strInParamDisplayNameObj[i].value;
								strInParamDisplayValueObjVal = strInParamDisplayValueObj[i].value;
								
							}else{
							
								strInParamDisplayNameObjVal = strInParamDisplayNameObjVal+"^"+strInParamDisplayNameObj[i].value;
								strInParamDisplayValueObjVal = strInParamDisplayValueObjVal+"^"+strInParamDisplayValueObj[i].value;
							}
							
						
							
						}
					 
				 }
				 
			}
			
		}
		
		if(param.length > 1){
						
			strInParamDisplayNameObjVal = strInParamDisplayNameObjVal+"^"+param.split('^')[0];
			strInParamDisplayValueObjVal = strInParamDisplayValueObjVal+"^"+param.split('^')[1];
			
		}
		
		var strReportWidth = document.getElementById("strReportWidth").value;
		var strReportWidthUnit = document.getElementById("strReportWidthUnit").value;
		var strReportBorderReq = document.getElementById("strReportBorderReq").value;

		var url = "DynamicReportsTransCNT.cnt?hmode=GETDRILLDOWNRPT&strOutPramValuesHidden="
				+ strOutPramValuesHidden
				+ "&strTemplateProcName="
				+ strTemplateProcName
				+ "&strInParamHiddenValues="
				+ strInParamHiddenValues
				+ "&strTemplateProcId="
				+ strTemplateProcId
				+ "&strReportTypeId="
				+ strReportTypeId
				+ "&strReportTemplateId="
				+ strReportTemplateId
				+ "&strTemplateProcDisplayName="
				+ strTemplateProcDisplayName
				+ "&strReportWidth="
				+ strReportWidth
				+ "&strReportWidthUnit="
				+ strReportWidthUnit
				+ "&strReportBorderReq="
				+ strReportBorderReq
				+ "&strLevel="
				+ level
				+ "&strInParamIdHiddenValues=" + 
				strInParamIdHiddenValues
				+ "&strInParamDisplayNameObjVal=" + 
				strInParamDisplayNameObjVal
				+ "&strInParamDisplayValueObjVal=" + 
				strInParamDisplayValueObjVal;

		
		ajaxFunction(url, "1");

	}
	

	function getAjaxResponse(res, mode) {
		var objVal;
		if (mode == "1") {
			var err = document.getElementById("errMsg");
			var temp1 = res.split("####");
			if (temp1[0] == "ERROR") {
				err.innerHTML = temp1[1];
			} else {

				objVal = document.getElementById("innerRptDtls");

				if (parseInt(gblLevel) > 0) {

					gblRptContent = gblRptContent + "@@##@@" + objVal.innerHTML;

					objVal.innerHTML = res;

					window.scrollTo("0", "0");
 
					
				} else {

					gblRptContent = "";
					objVal.innerHTML = res;

					popup('popUpDiv', "150", "150");
					window.scrollTo("0", "0");

				}

			}

		}

	}

	function hidepopup() {

		hide_popup('popUpDiv');
	}

	function backpopup(level) {

	 	
		var contentVal = gblRptContent.split('@@##@@');
 
		var levelValue = parseInt(level);
		 
		objVal = document.getElementById("innerRptDtls");
		objVal.innerHTML = contentVal[levelValue];

	  if(levelValue == 1)
		gblRptContent = "";
		 
		
	}
</script>

</head>
<body class="background">
	<div id="normalMsg" class="normalMsg">
		<bean:write name="drptBean" property="strNormalMsg" />
	</div>

	<table width="90%" border="0" cellspacing="0" cellpadding="0"
		height="69">
		<tr>
			<td>
				<div id="id2" class="hidecontrol" align="right">
					<img src='../../hisglobal/images/printer_symbol.gif'
						title='Click here to Print the Report' style="cursor: pointer"
						id="printId" style="cursor:pointer;" onClick='printData();'
						onKeyPress='printData();'> <img
						src='../../hisglobal/images/stop.png'
						title='Click here to Go Back' style="cursor: pointer"
						style="cursor:pointer;" onClick='window.close();'
						onKeyPress="window.close();">
				</div>
			</td>
		</tr>
	</table>

	<html:form action="/reports/DynamicReportsTransCNT"
		name="drptBean"
		type="dynamicreports.reports.controller.fb.DynamicReportsTransFB">

		<bean:write name="drptBean" property="strReportContents"
			filter="false" />

	</html:form>

	
	<div id="blanket" style="display: none;"></div>
	<div class="popUpDiv" id="popUpDiv" style="display: none;">
		<table bgcolor="white" width="1000" align="center">
			<tr>
				<td width="100%">

					<div id="innerRptDtls" style="display: block;"></div>
				</td>
			</tr>
		</table>
	</div>


</body>
</html>