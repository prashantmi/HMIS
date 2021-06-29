<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Query Report</title>

<link href="../../hisglobal/css/dwh.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/control_qry.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/js/jquery-1.10.1.min.js"></script>
<script language="JavaScript" src="../../hisglobal/js/jquery-ui.min.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/validationCommon_qry.js"></script>
<script language="Javascript" src="../../hisglobal/js/commonFunctions_qryReport.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<style type="text/css" >

#boxHeaderNoScrollForPrint {
	width: 100%;
	border: solid 0px #BBBBBB;
	border-top: none;
	
}



</style>

<script type="text/javascript">

function goToHome() {
	document.forms[0].hmode.value = "CANCELPAGE";
	showMenuFrame();
	document.forms[0].submit();
}


function getExcel(){
	
	
	var lQuery = trimAll(document.forms[0].lastQuery.value);
	
	if(lQuery.length <= 0){
		
		alert("First Execute the Query to download in Excel");
		return false;
	}
		
	document.forms[0].hmode.value = "GETEXCEL";
	document.forms[0].submit();
}


function pageResetMethod(){
	
	document.forms[0].qName.value = "";
	document.forms[0].fRows[0].checked = true;
	document.forms[0].nRows.value = 5;
	document.getElementById("resultDivAreaId").style.display = "none";
}

function enableFetchValue(obj){
	
	if(obj.value == 1){
		
		document.forms[0].nRows.disabled = false;
		document.forms[0].nRows.value = 5;
		document.forms[0].sFRows.value = 1;
		
	}else{
		
		document.forms[0].nRows.disabled = true;
		document.forms[0].nRows.value = 5;
		document.forms[0].sFRows.value = 2;
	}
	
}



function go()
{
	
	var queryValue = document.forms[0].qName.value;
	
	if(trimAll(queryValue).length == 0){
		alert("Query is a Mandatory Field");
		return false;
	}
	
	if(queryValue.indexOf("INSERT") >= 0 || queryValue.indexOf("insert") >= 0 || queryValue.indexOf("UPDATE") >= 0 || 
	   queryValue.indexOf("update") >= 0 || queryValue.indexOf("DELETE") >= 0 || queryValue.indexOf("delete") >= 0 || 
	   queryValue.indexOf("TRUNCATE") >= 0 || queryValue.indexOf("truncate") >= 0 || queryValue.indexOf("SLEEP") >= 0 || 
	   queryValue.indexOf("sleep") >= 0 ){
		
		alert("Only SELECT Query is allowed");
		return false;
		
	}
	
	if(queryValue.indexOf("SELECT") < 0 && queryValue.indexOf("select") < 0  ){
		
		alert("Only SELECT Query is allowed");
		return false;
		
	}
	
	if(document.forms[0].sFRows.value == '1' ){
		
		if(trimAll(document.forms[0].nRows.value).length == 0){
			
			alert("No. of Rows is a Mandatory Field");
			return false;
		}
		
		if(document.forms[0].nRows.value <= 0){
			
			alert("No. of Rows should be Greater than Zero");
			return false;
			
		}
				
	} 
	
	queryValue = queryValue.replace(/%/g , "@@$@@");
	queryValue = queryValue.replace(/\+/g , "^^@^^");
	 
	var url ="QueryReportCNT.cnt?hmode=GO&qName="+queryValue+"&fRows="+document.forms[0].sFRows.value+"&nRows="+document.forms[0].nRows.value; 
	
 //	alert("url :: "+url);
	
	ajaxFunction(url,"1");
	
	
}



function getAjaxResponse(res,mode)
{ 		
	    if(mode=='1')
	    {   
	    	
	    	var obj = document.getElementById("qResultsDivId");
	    	var err = document.getElementById("errMsg");
				  
		 		var temp1 = res.split("####");
		 		
		 		if(temp1[0] == "GERROR")
	       		{
		 			err.innerHTML = temp1[1];
		 			
		 			document.getElementById("resultDivAreaId").style.display = "none";
	       			document.getElementById("printImg").style.display = "none";
	       			document.forms[0].lastQuery.value = "";
		 			
        		}
				else if(temp1[0] == "ERROR")
	       		{
	       			obj.innerHTML = "<font color=red><center><b>Error : "+temp1[1]+"</b></center></font>";
	       			document.getElementById("resultDivAreaId").style.display = "block";
	       			document.getElementById("printImg").style.display = "none";
	       			document.forms[0].lastQuery.value = "";
        		}
				else
				{
					
					var tmp = temp1[1].split('^');
					
					document.getElementById("totalRowsId").innerHTML = tmp[1];
					document.getElementById("totalFetchedRowsId").innerHTML = tmp[0];
					obj.innerHTML = tmp[2];		
					document.getElementById("resultDivAreaId").style.display = "block";
					document.getElementById("printImg").style.display = "block";
					
					document.forms[0].lastQuery.value = document.forms[0].qName.value.replace(/%/g , "@@$@@");
					
					var divHeight = 250;
					var initialHeight = 716;
					var heightPer = (divHeight * 100) / initialHeight;
					var newHeight = parseInt((window.innerHeight * heightPer) / 100, 10);

					$('#qResultsDivId').fixedHeaderTableTransAnnaulDemand({
						tableHeight : newHeight,
						wrapperDivId : 'wrapper',
						mainTableRptId : 'mainTableRptId',
						tableHeaderId : 'tableHeaderId'
					});
					
				}
	       		 
	       		
		}
}


function printData(mode) 
{

	newwin = window.open('', 'printwin',
			'left=100,top=100,width=700,height=700,scrollbars=yes');
	newwin.document.write('<HTML><HEAD>');
	newwin.document.write((document.getElementsByTagName("head")[0]).innerHTML);
	newwin.document.write('<style type="text/css"> .hidecontrol { display: none; } </style>\n');
	newwin.document.write('<script>\n');
	newwin.document.write('function chkstate(){ \n');
	// newwin.document.write('if(document.readystate=="complete" ||
	// document.readystate=="undefined"){\n');
	newwin.document.write('window.close();\n');
	// newwin.document.write('}\n');
	// newwin.document.write('else{\n');
	// newwin.document.write('setTimeout("chkstate()",2000)\n');
	// newwin.document.write('}\n');
	newwin.document.write('}\n');
	newwin.document.write('function print_win(){\n');
	newwin.document.write('window.print();\n');
	newwin.document.write('chkstate();\n');
	newwin.document.write('}\n');

	newwin.document.write('<\/script>\n');
	newwin.document.write('</HEAD>\n');
	newwin.document.write('<BODY onload="print_win()">\n');
	if(mode=='1')
	{
	  newwin.document.write(document.getElementById('content').innerHTML.replace("boxHeaderFixedNew" , "boxHeaderNoScrollForPrint").replace("right-border" , ""));
	}
	
	
	newwin.document.write('</BODY>\n');
	newwin.document.write('</HTML>\n');
	newwin.document.close();

} 


</script>

</head>
<body class="background" onload="hideMenuFrame();">
	<html:form name="queryReportBean" action="/reports/QueryReportCNT" type="mms.reports.controller.fb.QueryReportFB"
		styleClass="formbgnew">
		
		<div id="errMsg" class="errMsg">
			<bean:write name="queryReportBean" property="strErrMsg" />
		</div>
		<div id="warningMsg" class="warningMsg">
			<bean:write name="queryReportBean" property="strWarningMsg" />
		</div>
		<div id="normalMsg" class="normalMsg">
			<bean:write name="queryReportBean" property="strNormalMsg" />
		</div>


		<table class="NEWTABLEWIDTH" align="center" cellspacing="1px">


			<tr class="HEADER">

				<td colspan="4" style="width: 433px">Query Report</td>

			</tr>

		</table>


		<table class="NEWTABLEWIDTH" align="center" cellpadding="1px"
			cellspacing="1px">

			<tr>
				<td width="25%" class="LABEL"><font color="red">*</font> Query</td>
				<td width="75%" colspan="3" class="CONTROL"><textarea name="qName" rows="4" cols="140"></textarea> </td>
				
			</tr>
			
			<tr>
				<td width="25%" class="LABEL"><font color="red">*</font> Fetch</td>
				<td width="25%" class="CONTROL"> <input type="radio" name="fRows" value="1" onclick="enableFetchValue(this);"  checked="checked"/> No. of Rows 				   
				  <input type="text" name="nRows" class="txtFldMin" value="5"></td>
				<td width="25%" class="CONTROL"> <input type="radio" name="fRows" value="2" onclick="enableFetchValue(this);"/> All Rows </td>
		        	 
			<td width="25%">
			   <div class="control_button">
			        <div id='goDivId'>
			         <a href="#" class="button"
									title="Click to Fetch Query Results" onClick="go();"><span
									class="save"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Go</span></a>
			        </div>
			</div>
			</tr>
			
		</table>
		
		 <div id="resultDivAreaId" style="display: none;">
		  
		  <div class="linenew" id="recDetailsDivId">
			<table cellspacing="1" cellpadding="1" align="center" class="NEWTABLEWIDTH">
				<tbody>
					<tr>
						<td colspan="2">Query Result</td>
					</tr>
				</tbody>
			</table>
		</div>
		
			<table class="NEWTABLEWIDTH" align="center" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td width="25%" class="LABEL">Total Rows </td>
				<td width="25%" class="CONTROL"><div id='totalRowsId' style="font-size: large;color: blue;"></div> </td> 
				
				<td width="25%" class="LABEL">Total Fetched Rows </td>
				<td width="25%" class="CONTROL"><div id='totalFetchedRowsId' style="font-size: large;color: blue;"></div> </td>
				
			</tr>
			
			</table>
			
			<div id="printImg" style="width: 99%;display: none;"><img align="right" onclick="getExcel();" src="../../hisglobal/images/excel1.png" title="Save As Excel" style="cursor: pointer;height: 20px;width: 20px;">  <img align="right" onclick="printData(1);" src="../../hisglobal/images/print.gif" title="Print Report" style="cursor: pointer; width: 20px; height: 20px; margin-bottom:2px; margin-right:5px;">  </div>
			 
		<div   style="width: 100%;overflow: auto;">
		<div id="qResultsDivId" style="width: 100%;" ></div>
		</div>
		
		 </div>
  
			<table class="NEWTABLEWIDTH" align="center" cellpadding="1px"
			cellspacing="1px">
		<tr class="FOOTER">
				<td colspan="2"></td>
			</tr>
</table>
		<div>
			<div class="legends"></div>
			<div class="control_button">
				<table border="0" class="NEWTABLEWIDTH" align="center"
					cellspacing="1px" cellpadding="1px">
					<tr id="saveId">
						<td align="center">
							<div>
								 <a href="#" class="button"
									title="Click to Clear Page" onClick="pageResetMethod();"><span
									class="clear">Clear</span></a>
								<a href="#" class="button" title="Click to Cancel"
									onClick="goToHome();"><span class="cancel">Cancel</span></a>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="legends" style="width: 95%;margin-left:0;">
			<ul>
				<li><font color="red">*</font> Mandatory Fields</li>
				<li><font color="red">---</font> NULL Value</li>		  
			</ul>
		</div>


		<input type="hidden" name="hmode" />
 <input type="hidden" name="sFRows" value='1' />
 <input type="hidden" name="lastQuery" value='' />
	</html:form>

</body>
</html>