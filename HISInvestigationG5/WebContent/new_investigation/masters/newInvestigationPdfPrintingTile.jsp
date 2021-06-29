

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@taglib uri="/WEB-INF/HISInvestigationTools.tld" prefix="Investigation" %>
<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="new_investigation.masters.controller.fb.PDFPrintingFB"%>

<%@page import="new_investigation.vo.template.ResultEntryVOGroupByValidatedBy"%>
<%@page import="java.util.List"%>

<his:javascript src="/new_investigation/js/inv_popup.js" />
<%-- <his:javascript src="/investigation/js/investigation.js" /> --%>


<%try{%>


<script language="javascript">
function handleResponse(){
	 
	 if(request.readyState == 4)
	  {
	  if(request.status == 200)
	   {
		 var id;
		 var str=request.responseText;		 	 
		 if(str!=null || str!="")
		 {
			// HISAlert('info','',"str----------->"+str);
			if(str=='true')
			{
			 var url="OpenPdf.jsp?";
			 window.open(url,"MyWindows","200","200");
			}
			else
			{
				var strResult=new Array();
			 	strResult.length=2;
			 	strResult=str.split("^",2);
				//HISAlert('info','',"strResult[1]----------->"+strResult[1]);
			 	Popup.hide('DIV_ID'+strResult[1]);
			 	var url="OpenPdf.jsp?";
				window.open(url,"MyWindows","200","200");
			}	 
		 }
		  	
		 } else {
		 HISAlert('info','',"A problem occurred with communicating between "+
		 "the XMLHttpRequest object and the server program.");
		 }
		 }//end outer if
	}
function SelectedPdf(Index,isConfidential)
{
	//HISAlert('info','',document.getElementsByName("enteredPassword")[Index].value);
	//var url='/AHIMS/investigation/transaction/pdfPrinting.cnt?hmode=GETBYTEARRAY&Index='+Index+'&isConfidential='+isConfidential+'&enteredPassword=administrator'+document.getElementsByName("enteredPassword")[Index].value;
	
	var url='/AHIMS/investigation/transaction/pdfPrinting.cnt?hmode=GETBYTEARRAY&Index='+Index+'&isConfidential='+isConfidential+'&enteredPassword=administrator';
	init(url);	
	req.onreadystatechange = ajaxResponseForDocument;
	req.send(null);
		
}
function ShowSelectedPdf(Index,isConfidential)
{
	//HISAlert('info','',isConfidential);
	if(isConfidential==0)
	{
		var url='/AHIMS/investigation/transaction/pdfPrinting.cnt?hmode=GETBYTEARRAY&Index='+Index+'&isConfidential='+isConfidential;
		init(url);	
		req.onreadystatechange = ajaxResponseForDocument;
		req.send(null);
	}
	else
	{
		Popup.showModal('DIV_ID'+Index);
	}
}
function init(url)
{
	//HISAlert('info','',url);
try
  { 
   // Firefox, Opera 8.0+, Safari  
  req=new XMLHttpRequest();  }
  catch (e)  
  {  
  // Internet Explorer 
   try
    {    req=new ActiveXObject("Msxml2.XMLHTTP");    }
  catch (e)
    {    
    try
      {      req=new ActiveXObject("Microsoft.XMLHTTP");      }
    catch (e)
      {      HISAlert('info','',"Your browser does not support AJAX!");      return false;      }    }  }  

//HISAlert('info','','hiiiiiiiiiiii');
  req.open("GET", url, true);
req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
}
function ajaxResponseForDocument()
{
if (req.readyState == 4)
	 {
	if (req.status == 200) 
		{
		 var id;
		 var str=req.responseText;		 	 
		 if(str!=null || str!="")
		 {
			// HISAlert('info','',"str----------->"+str);
			if(str=='true')
			{
			 var url="/AHIMS/investigation/transaction/OpenPdf.jsp";
			 window.open(url,"MyWindows","200","200");
			}
			else
			{
				var strResult=new Array();
			 	strResult.length=2;
			 	strResult=str.split("^",2);
				//HISAlert('info','',"strResult[1]----------->"+strResult[1]);
				if(strResult[1]!=null)
				{
			 		Popup.hide('DIV_ID'+strResult[1]);
			 		var url="/AHIMS/investigation/transaction/OpenPdf.jsp";
					window.open(url,"MyWindows","200","200");
				}
				else
				{
					HISAlert('info','',strResult[0]);
				}
				
			}	 
		
		}
		}	
}}

function cancelToResult(hmodeValue)
{
	document.forms[0].hmode.value=hmodeValue;
	document.forms[0].submit();
}
function PageSubmit(pageNo,hmode)
{
document.forms[0].currentPageNo.value=pageNo;
document.forms[0].hmode.value='RESULTPRINTINGLISTING';
document.forms[0].submit();
}
function nextSubmit()
{
document.forms[0].currentBlock.value=parseInt(document.forms[0].currentBlock.value)+1;
document.forms[0].currentPageNo.value=<%=((PdfPrintingFB)pageContext.findAttribute("PdfPrintingFB")).getPagesPerBlock()%>*parseInt(document.forms[0].currentBlock.value)+1;
document.forms[0].currentPageNo.value=pageNo;
document.forms[0].hmode.value='RESULTPRINTINGLISTING';
document.forms[0].submit();
}
function prevSubmit()
{
document.forms[0].currentBlock.value=parseInt(document.forms[0].currentBlock.value)-1;
document.forms[0].currentPageNo.value=<%=((investigation.controller.transaction.fb.PdfPrintingFB)pageContext.findAttribute("PdfPrintingFB")).getPagesPerBlock()%>*parseInt(document.forms[0].currentBlock.value)+1;
document.forms[0].currentPageNo.value=pageNo;
document.forms[0].hmode.value='RESULTPRINTINGLISTING';
document.forms[0].submit();
}
function changeReferredByHospitalSelectionType()
{
if(document.getElementsByName('referredByHospitalCode')[0].options[document.getElementsByName('referredByHospitalCode')[0].selectedIndex].value=='')
{
document.getElementsByName('referredByHospitalName')[0].disabled=false;
document.getElementsByName('referredByHospitalName')[0].style.display='block';
}
else
{
document.getElementsByName('referredByHospitalName')[0].disabled=true;
document.getElementsByName('referredByHospitalName')[0].style.display='none';
}
}
function resultPrintingSubmit(hmodeValue,flag1)
{
populateLabDetails();
if(flag1=="1")
document.forms[0].hmode.value="";

if(document.forms[0].selectedLab.value==-1 || document.forms[0].selectedLab.value=="")
HISAlert('info','',"Please select the lab");
else if(datecheck("fromDate","toDate"))
{
	
	if(document.forms[0].resultPrintingType[0].checked || document.forms[0].resultPrintingType[1].checked || document.forms[0].resultPrintingType[2].checked || document.forms[0].resultPrintingType[3].checked||document.forms[0].resultPrintingType[4].checked)
	{
		if(document.forms[0].resultPrintingType[0].checked && document.forms[0].hmode.value=="PATIENTWISERESULTPRINTING")
		{
			if(checkingCrNo(document.forms[0].patientCrNo)==true)
			{
			document.forms[0].hmode.value=hmodeValue;
			document.forms[0].submit();
			}
		}		
		/*else if(document.forms[0].resultPrintingType[1].checked && document.forms[0].hmode.value=="TESTWISERESULTPRINTING")
		{
			if(document.forms[0].resultPrintingType.value=="-1" || document.forms[0].selectedLabTestCode.value=="")
			{
			HISAlert('info','',"Please select the test name");
			return false;
			}
			else
			{
			document.forms[0].hmode.value=hmodeValue;
			document.forms[0].submit();
			}
		}
		else if(document.forms[0].resultPrintingType[2].checked  && document.forms[0].hmode.value=="SAMPLEWISERESULTPRINTING")
		{	
			if(document.forms[0].selectedLabSampleCode.value=="-1" || document.forms[0].selectedLabSampleCode.value=="")
			{
			HISAlert('info','',"Please select the sample name");
			return false;
			}
			else
			{
			document.forms[0].hmode.value=hmodeValue;
			document.forms[0].submit();
			}
		}
		else if(document.forms[0].resultPrintingType[3].checked && document.forms[0].hmode.value=="OUTSIDEPATIENTWISERESULTPRINTING")
		{
			if(document.forms[0].firstName.value=="")
			{
			HISAlert('info','',"Please enter the patient first name");
			return false;
			}
			else
			{
			document.forms[0].hmode.value=hmodeValue;
			document.forms[0].submit();
				
			}
		}*/
		else if(document.forms[0].resultPrintingType[4].checked && document.forms[0].hmode.value=="OUTSIDESAMPLERESULTPRINTING")
		{
			/*if(document.forms[0].labTestSampleCode.value=="")
			{
				HISAlert('info','',"Please enter the sample number");
			}
			else
			{*/
				document.forms[0].hmode.value=hmodeValue;
			HISAlert('info','',document.forms[0].hmode.value);
				document.forms[0].submit();
				
			//}
		}
		else
		{
			
			
			document.forms[0].hmode.value=hmodeValue;
			if(document.forms[0].hmode.value=='GOFORRESULTPRINTING')
			{
				document.forms[0].mode.value='RESULTPRINTINGREPORT';	
			}
			
			document.forms[0].submit();
		}
	}
	else 
	HISAlert('info','',"Please select Result Printing Type");
}
}

function SubmitPage(obj){ 
if(obj.checked==true)
{
document.getElementById("enR").style.display='block';
}
else
{
var check=false;
for(var i=0;i<document.getElementsByName(obj.name).length;i++)
{
if(document.getElementsByName(obj.name)[i].checked)
{
check=true;
}
}

if(check==false)
{
document.getElementById("enR").style.display='none';
}
else
{
document.getElementById("enR").style.display='block';
}

}


}

function checkPrintAll(obj)
{
populateLabDetails();
document.forms[0].hmode.value=obj;
document.forms[0].submit();
}

function populateLabDetails()
{
//HISAlert('info','','  A '+document.forms[0].selectedLab.selectedIndex);
//HISAlert('info','',document.forms[0].selectedLab.options[document.forms[0].selectedLab.selectedIndex].text)
document.forms[0].laboratoryName.value=(document.forms[0].selectedLab.options[document.forms[0].selectedLab.selectedIndex].text).split('-')[1]
document.forms[0].departmentName.value=(document.forms[0].selectedLab.options[document.forms[0].selectedLab.selectedIndex].text).split('-')[0]

}



</script>
<table width='100%' align="center" cellspacing="0" cellpadding="0">
<tr>
<td  width='100%'>
<table width='100%' align="center" cellspacing="0" cellpadding="0">
  <tr><td  width='120%'><his:TitleTag name="Pdf Printing Process"><b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">  </font></b></his:TitleTag></td>
  </tr>
 </table>
<his:ContentTag>
<table width='100%' cellspacing="0" cellpadding="0" align="center">
<tr>
<td width="100%">


<table width='100%'>
<tr>
<td class='tdfonthead' colspan='1' width='25%'>Laboratory</td>
<td class='tdfont' colspan='5' width='75%'><div align="left"><html:select name="PdfPrintingFB" property="selectedLab"><html:options collection="<%=investigation.InvestigationConfig.sessionOptionUserLaboratoryList%>" property="label" labelProperty="value"/></html:select></div></td>
</tr>
<tr>
<td class='tdfonthead' colspan='1' width='25%'>From Date</td>
<td class='tdfont' colspan='2' width='25%'><div align="left"><Investigation:date beanName="PdfPrintingFB" property="fromDate" onChange='datecheck("fromDate","toDate");'/></div></td>
<td class='tdfonthead' colspan='1' width='25%'>To Date</td>
<td class='tdfont' colspan='2' width='25%'><div align="left"><Investigation:date beanName="PdfPrintingFB" property="toDate" onChange='datecheck("fromDate","toDate");'/></div></td>
</tr>
<tr>
<td class='tdfonthead' colspan='1' width='25%'>Result Printing Type</td>
<td class='tdfont' colspan='5' width='80%'>
	<html:radio name="PdfPrintingFB" property="resultPrintingType" value="PATIENTWISERESULTPRINTING" onclick="resultPrintingSubmit('PATIENTWISERESULTPRINTING','1')">Patient Wise</html:radio>
	<html:radio name="PdfPrintingFB" property="resultPrintingType" value="TESTWISERESULTPRINTING" onclick="resultPrintingSubmit('TESTWISERESULTPRINTING','1')" style="display:none;"></html:radio>
	<html:radio name="PdfPrintingFB" property="resultPrintingType" value="SAMPLEWISERESULTPRINTING"  onclick="resultPrintingSubmit('SAMPLEWISERESULTPRINTING','1')" style="display:none;"></html:radio>
	<html:radio name="PdfPrintingFB" property="resultPrintingType" value="OUTSIDEPATIENTWISERESULTPRINTING" onclick="resultPrintingSubmit('OUTSIDEPATIENTWISERESULTPRINTING','1')" >Out-Side Patient</html:radio>
	<html:radio name="PdfPrintingFB" property="resultPrintingType" value="OUTSIDESAMPLERESULTPRINTING" onclick="resultPrintingSubmit('OUTSIDESAMPLERESULTPRINTING','1')" style="display:none;"></html:radio>
</td>
</tr>
<logic:present name="PdfPrintingFB" property="resultPrintingType">
<!-- Test Wise Result Validation -->
<!--<logic:equal name="PdfPrintingFB" property="resultPrintingType" value="TESTWISERESULTPRINTING">
<tr>
<td class='tdfonthead' colspan='1' width='25%'>Test Name</td>
<td class='tdfont' colspan='2' width='25%'><html:select name="PdfPrintingFB" property="selectedLabTestCode">
<logic:present name="<%=investigation.InvestigationConfig.sessionOptionTestList%>">
<logic:empty name="<%=investigation.InvestigationConfig.sessionOptionTestList%>"><html:option value="-1">No Test Available</html:option></logic:empty>
<logic:notEmpty name="<%=investigation.InvestigationConfig.sessionOptionTestList%>"><html:option value="-1">Select Test</html:option></logic:notEmpty>
<html:options collection="<%=investigation.InvestigationConfig.sessionOptionTestList%>" property="label" labelProperty="value"/>
</logic:present>
<logic:notPresent name="<%=investigation.InvestigationConfig.sessionOptionTestList%>"><html:option value="-1">No Test Available</html:option></logic:notPresent>
</html:select></td>
<td class='tdfonthead' colspan='1' width='25%'></td>
<td class='tdfont' colspan='2' width='25%'></td>
</tr>
</logic:equal>



--><!-- Sample Wise Result Entry -->
<!--<logic:equal name="PdfPrintingFB" property="resultPrintingType" value="SAMPLEWISERESULTPRINTING">
<tr>
<td class='tdfonthead' colspan='1' width='16%'>Sample No.</td>
<td class='tdfont' colspan='2' width='34%'><html:select name="PdfPrintingFB" property="selectedLabSampleCode">
<logic:present name="<%=investigation.InvestigationConfig.sessionOptionTestList%>">
<logic:empty name="<%=investigation.InvestigationConfig.sessionOptionTestList%>"><html:option value="-1">No Sample Available</html:option></logic:empty>
<logic:notEmpty name="<%=investigation.InvestigationConfig.sessionOptionTestList%>"><html:option value="-1">Select Sample No</html:option></logic:notEmpty>
<html:options collection="<%=investigation.InvestigationConfig.sessionOptionTestList%>" property="label" labelProperty="value"/>
</logic:present>
<logic:notPresent name="<%=investigation.InvestigationConfig.sessionOptionTestList%>"><html:option value="-1">No Sample Available</html:option></logic:notPresent>
</html:select></td>
<td class='tdfonthead' colspan='1' width='16%'></td>
<td class='tdfont' colspan='2' width='34%'></td>
</tr>
</logic:equal>
 Patient Wise Result Entry -->
<logic:equal name="PdfPrintingFB" property="resultPrintingType" value="PATIENTWISERESULTPRINTING">
<tr>
<td class='tdfonthead' colspan='1' width='16%'>CR No.</td>
<td class='tdfont' colspan='2' width='34%'><html:text name="PdfPrintingFB" property="patientCrNo" maxlength="13"/></td>
<td class='tdfonthead' colspan='1' width='16%'></td>
<td class='tdfont' colspan='2' width='34%'></td>
</tr>
</logic:equal>
<!-- Out side Patient Wise Result Entry -->
<logic:equal name="PdfPrintingFB" property="resultPrintingType" value="OUTSIDEPATIENTWISERESULTPRINTING">
<tr>
<td class='tdfonthead' colspan='1' width='12%'>First Name</td>
<td class='tdfont' colspan='1' width='13%'><html:text name="PdfPrintingFB" property="firstName" maxlength="13"/></td>
<td class='tdfonthead' colspan='1' width='12%'>Middle Name</td>
<td class='tdfont' colspan='1' width='13%'><html:text name="PdfPrintingFB" property="middleName" maxlength="13"/></td>
<td class='tdfonthead' colspan='1' width='12%'>Last Name</td>
<td class='tdfont' colspan='1' width='13%'><html:text name="PdfPrintingFB" property="lastName" maxlength="13"/></td>
</tr>
<tr>
		<td class="tdfonthead" colspan='1' width="16%">Hospital Name</td>
		<td class="tdfonthead" colspan='2' width="34%"><div align='left'><html:select name="PdfPrintingFB" property="referredByHospitalCode" onchange="changeReferredByHospitalSelectionType()"><html:option value="-1">Select Hospital</html:option><html:options collection="<%=investigation.InvestigationConfig.sessionOptionTestList%>" labelProperty="label" property="value" /><html:option value="" >Other Hospital</html:option></html:select></div></td>
		<td class='tdfonthead' colspan='1' width='16%' ><logic:equal name="PdfPrintingFB" property="referredByHospitalCode" value=""><html:text name="PdfPrintingFB" property="referredByHospitalName" disabled="false" style="display:block"/></logic:equal><logic:notEqual name="PdfPrintingFB" value=""><html:text name="PdfPrintingFB" property="referredByHospitalName" disabled="true" style="display:none"/></logic:notEqual></td>
		<td class='tdfonthead' colspan='2' width='34%'>&nbsp;</td>
</tr>
</logic:equal>
<!-- Outside Sample Wise Result Entry -->
<!--<logic:equal name="PdfPrintingFB" property="resultPrintingType" value="OUTSIDESAMPLERESULTPRINTING">
<tr>
<td class='tdfonthead' colspan='1' width='16%'></td>
<td class='tdfont' colspan='5' width='84%'></td>
</tr>
<tr>
		<td class="tdfonthead" colspan='1' width="16%"><div align='right'>Hospital Name</div></td>
		<td class="tdfonthead" colspan='2' width="34%"><div align='left'><html:select name="PdfPrintingFB" property="referredByHospitalCode" onchange="changeReferredByHospitalSelectionType()"><html:option value="-1">Select Hospital</html:option><html:options collection="<%=investigation.InvestigationConfig.sessionOptionTestList%>" labelProperty="label" property="value" /><html:option value="" >Other Hospital</html:option></html:select></div></td>
		<td class='tdfonthead' colspan='1' width='16%' ><logic:equal name="PdfPrintingFB" property="referredByHospitalCode" value=""><html:text name="PdfPrintingFB" property="referredByHospitalName" disabled="false" style="display:block"/></logic:equal><logic:notEqual name="PdfPrintingFB" value=""><html:text name="PdfPrintingFB" property="referredByHospitalName" disabled="true" style="display:none"/></logic:notEqual></td>
		<td class='tdfonthead' colspan='2' width='34%'>&nbsp;</td>
</tr>
</logic:equal>-->
</logic:present>
<tr>
<td class="tdfonthead" colspan='6' width="100%"><div align='center'><b><img src="/AHIMS/hisglobal/images/btn-search.png" value="SEARCH" onClick="resultPrintingSubmit('RESULTPRINTINGLISTING','2')"></b></div></td>
</tr>
</table>
<logic:present name="PAGELIST">
<table width="100%">
<% int i=0; %>
<!--<logic:equal name='PdfPrintingFB' property="resultPrintingType" value="TESTWISERESULTPRINTING">
			<tr>
	<td width="100%" class='HEADER' colspan='8'>
			<table width="100%">
			<tr>
			<td  colspan='1' width="1%" class="tdFormat"><div align='center'><b></b></div></td>
			<td  colspan='1' width="11%" class="tdFormat"><div align='center'>CR No</div></td>
			<td  colspan='1' width="16%" class="tdFormat"><div align='center'>Patient Name</div></td>
			<td  colspan='1' width="10%" class="tdFormat"><div align='center'>Age/Gender</div></td>
			<td  colspan='1' width="13%" class="tdFormat"><div align='center'>Dept-Unit</div></td>
			<td  colspan='1' width="12%" class="tdFormat"><div align='center'>Test Name</div></td>
			<td  colspan='1' width="15%" class="tdFormat"><div align='center'>Lab No</div></td>
			<td  colspan='1' width="11%" class="tdFormat"><div align='center'>Type</div></td>
			
			</tr>
			
			</table>
			</td>
			</tr>
			<logic:iterate  id="workOrder" name="PAGELIST">
			<tr>
			<td colspan='1' width="1%"><div align='center'><input type="checkbox" name="selectedWorkOrderNo"  value='<%=i++ %>' onclick="SubmitPage(this)"></div></td>
			<td colspan='1' width="11%"><div align='center'><bean:write name="workOrder" property="patCRNo"/></div></td>
			<td colspan='1'  width="16%"><div align='center'><bean:write name="workOrder" property="patName"/></div></td>
			<td colspan='1' width="10%"><div align='center'><bean:write name="workOrder" property="patAge"/>/<bean:write name="workOrder" property="patGenderShortName"/></div></td>
			<td colspan='1' width="13%"><div align='center'><bean:write name="workOrder" property="patDeptUnit"/> </div></td>
			 <td colspan='1' width="11%"><div align='center'><logic:notEqual name="workOrder" property="sessionId" value="0"><bean:write name="workOrder" property="sessionId"/></logic:notEqual></div></td>
			<td colspan='1' width="12%"><div align='center'><bean:write name="workOrder" property="testName"/></div></td>
			<td colspan='1' width="15%"><div align='center'><bean:write name="workOrder" property="labNo"/></div></td>
			<td colspan='1' width="11%"><div align='center'><bean:write name="workOrder" property="requisitionTypeName"/></div></td>
			</tr>
			</logic:iterate>
			<logic:empty name="PAGELIST">
			<tr>
			<td class="tdfont" colspan='8' width="100%"><div align='center'><font color="red"><b>No Record Found</b></font></div></td>
			</tr>
</logic:empty>
			<tr><td class='HEADER' colspan='8' align="right"><%=((investigation.controller.transaction.fb.PdfPrintingFB)pageContext.findAttribute("PdfPrintingFB")).getPageString() %></td></tr>
</logic:equal>

--><!--<logic:equal name='PdfPrintingFB' property="resultPrintingType" value="SAMPLEWISERESULTPRINTING">
			<tr>
	<td width="100%" class='HEADER' colspan='8'>
			<table width="100%">
			<tr>
			<td class="tdFormat" colspan='1' width="1%"><div align='center'></div></td>
			<td  colspan='1' width="11%" class="tdFormat"><div align='center'>CR No</div></td>
			<td  colspan='1' width="16%" class="tdFormat"><div align='center'>Patient Name</div></td>
			<td  colspan='1' width="10%" class="tdFormat"><div align='center'>Age/Gender</div></td>
			<td  colspan='1' width="13%" class="tdFormat"><div align='center'>Dept-Unit</div></td>
			<td  colspan='1' width="12%" class="tdFormat"><div align='center'>Test Name</div></td>
			<td  colspan='1' width="15%" class="tdFormat"><div align='center'>Lab No</div></td>
			<td  colspan='1' width="11%" class="tdFormat"><div align='center'>Type</div></td>
			</tr>
			</table>
			</td>
			</tr>
			<logic:iterate  id="workOrder" name="PAGELIST">
			<tr>
			<td colspan='1'  width="1%"><div align='center'><input type="checkbox" name="selectedWorkOrderNo"  value='<%=i++ %>' onclick="SubmitPage(this)"></div></td>
			<td colspan='1' width="11%"><div align='center'><bean:write name="workOrder" property="patCRNo"/></div></td>
			<td colspan='1'  width="16%"><div align='center'><bean:write name="workOrder" property="patName"/></div></td>
			<td colspan='1' width="10%"><div align='center'><bean:write name="workOrder" property="patAge"/>/<bean:write name="workOrder" property="patGenderShortName"/></div></td>
			<td colspan='1' width="13%"><div align='center'><bean:write name="workOrder" property="patDeptUnit"/> </div></td>
			 <td colspan='1' width="11%"><div align='center'><logic:notEqual name="workOrder" property="sessionId" value="0"><bean:write name="workOrder" property="sessionId"/></logic:notEqual></div></td>
			<td colspan='1' width="12%"><div align='center'><bean:write name="workOrder" property="testName"/></div></td>
			<td colspan='1' width="15%"><div align='center'><bean:write name="workOrder" property="labNo"/></div></td>
			<td colspan='1' width="11%"><div align='center'><bean:write name="workOrder" property="requisitionTypeName"/> </div></td>
			</tr>
			</logic:iterate>
			<logic:empty name="PAGELIST">
			<tr>
			<td class="tdfont" colspan='8' width="100%"><div align='center'><font color="red"><b>No Record Found</b></font></div></td>
			</tr>
</logic:empty>
			<tr><td class='HEADER' colspan='10' align="right"><%=((investigation.controller.transaction.fb.PdfPrintingFB)pageContext.findAttribute("PdfPrintingFB")).getPageString() %></td></tr>
			
</logic:equal>

--><logic:equal name='PdfPrintingFB' property="resultPrintingType" value="PATIENTWISERESULTPRINTING">
			<tr>
	<td width="100%" class='HEADER' colspan='7'>
			<table width="100%">
			<tr>
			<td class="tdFormat" colspan='1' width="1%"><div align='center'></div></td>
			<td colspan='1' width="20%" class="tdFormat"><div align='center'>CR No</div></td>			
			<td  colspan='1' width="16%" class="tdFormat"><div align='center'>Patient Name</div></td>
			<td  colspan='1' width="10%" class="tdFormat"><div align='center'>Age/Gender</div></td>			
			<td  colspan='1' width="12%" class="tdFormat"><div align='center'>Test Name</div></td>
			<td  colspan='1' width="15%" class="tdFormat"><div align='center'>Lab Name</div></td>			
			<td  colspan='1' width="11%" class="tdFormat"><div align='center'>Pdf</div></td>
			</tr>
			</table>
			</td>
			</tr>
			<logic:iterate  id="workOrder" name="PAGELIST" indexId="k">
			<tr>
			<td class="tdfont" colspan='1'  width="1%"><div align='center'><input type="checkbox" name="selectedWorkOrderNo"  value='<%=i++ %>' onclick="SubmitPage(this)"></div></td>
			<td  class="tdfont" colspan='1' width="20%"><div align='center'><bean:write name="workOrder" property="patCRNo"/></div></td>			
			<td class="tdfont" colspan='1'  width="16%"><div align='center'><bean:write name="workOrder" property="patName"/></div></td>
			<td class="tdfont" colspan='1' width="10%"><div align='center'><bean:write name="workOrder" property="patAge"/>/<bean:write name="workOrder" property="patGenderShortName"/></div></td>			
			<td class="tdfont" colspan='1' width="12%">
			<a  onclick="Popup.showModal('DIVTEST<%=k%>')" style="cursor: pointer;"><u>PDF Detail</u></a> <div align='center' id="DIVTEST<%=k%>" style="border:3px;border-style:solid;background-color:#ffffff;padding:2px; font-size:50%;width:50%;center;display:none;">
				<table width="100%"  align="center" cellspacing="0" cellpadding="0">
			<logic:present name="workOrder" property="resultEntryVOListValidatedBy">
			<tr>
				<td class="HEADER" width="50%"><div align="center">Test</div></td>
				<td class="HEADER" width="50%"><div align="center">Sample</div></td>
			</tr>
			<logic:iterate id="testDtl" name="workOrder" property="resultEntryVOListValidatedBy" >
			
				<tr>
					<td class="tdfont" ><div align="center"><bean:write name="testDtl" property="testName"/></div></td>
				<td class="tdfont" ><div align="center"><bean:write name="testDtl" property="sampleName"/></div></td>
				</tr>
			</logic:iterate>
			</logic:present>
			<tr>				
				<td width="100%" align="center" colspan="2" class="HEADER"></td>
				
			</tr>
			<tr>				
				<td width="100%" align="center" colspan="2"><div align="center"><img src="/AHIMS/hisglobal/images/ok.gif"  onClick="Popup.hide('DIVTEST<%=k%>')"></div></td>
				
			</tr>
			</table>
			
			</div>
			
			
			</td>			
			<td class="tdfont" colspan='1' width="15%"><div align='center'><bean:write name="workOrder" property="laboratoryName"/></div></td>
			<!--<td class="tdfont"  colspan='1' width="13%"><div align='center'><bean:write name="workOrder" property="patDeptUnit"/> </div></td>
			<td class="tdfont" colspan='1' width="11%"><div align='center'><bean:write name="workOrder" property="requisitionTypeName"/> </div></td>
			--><td class="tdfont" colspan='1' width="11%"><div align='center'><img src="/AHIMS/hisglobal/images/PDF_Small.png" onclick="ShowSelectedPdf(<%=k%>,'<bean:write name="workOrder" property="isconfidential"/>')" ></div>
			<logic:equal name="workOrder" property="isconfidential" value="1">
			<div id="DIV_ID<%=k%>" style="border:3px;border-style:solid;background-color:#ffffff;padding:2px; font-size:50%;width:30%;center;display:none;">
			
			<table  width="100%"  align="center" cellspacing="0" cellpadding="0">
				<tr>				
				<td width="100%" align="center" colspan="2" class="HEADER">
				<div align="center">Paswword</div>
				</td>
				<tr >
					
					<td colspan="2" class="tdfont" width="100%">
					<div align="center">
					<input type="password" align="middle" name="enteredPassword">
					</div>
					</td>
				</tr>		
				<tr>				
				<td width="100%" align="center" colspan="2" class="HEADER">				
				</td>
				<tr >										
					<td width="100%" align="center"><div align="center"><img src="/AHIMS/hisglobal/images/ok.gif"  onClick="SelectedPdf(<%=k%>,'<bean:write name="workOrder" property="isconfidential"/>');"/><img src="/AHIMS/hisglobal/images/btn-ccl.png"  onClick="Popup.hide('DIV_ID'+<%=k%>);"/>
					</div>
					</td>					
				</tr>
				
				
			</table>
			</div>
			</logic:equal>
			</td>
			</tr>
			</logic:iterate>
			<logic:empty name="PAGELIST">
			<tr>
			<td class="tdfont" colspan='7' width="100%"><div align='center'><font color="red"><b>No Record Found</b></font></div></td>
			</tr>
</logic:empty>
			<tr><td class='HEADER' colspan='7' align="right"><%=((investigation.controller.transaction.fb.PdfPrintingFB)pageContext.findAttribute("PdfPrintingFB")).getPageString() %></td></tr>			
</logic:equal>
<logic:equal name='PdfPrintingFB' property="resultPrintingType" value="OUTSIDEPATIENTWISERESULTPRINTING">
			<tr>
	<td width="100%" class='HEADER' colspan='7'>
			<table width="100%">
			<tr>
			<td class="tdFormat" colspan='1' width="1%"><div align='center'></div></td>
			<td colspan='1' width="20%" class="tdFormat"><div align='center'>Requisition No</div></td>			
			<td  colspan='1' width="16%" class="tdFormat"><div align='center'>Patient Name</div></td>
			<td  colspan='1' width="10%" class="tdFormat"><div align='center'>Age/Gender</div></td>			
			<td  colspan='1' width="12%" class="tdFormat"><div align='center'>Tests</div></td>
			<td  colspan='1' width="15%" class="tdFormat"><div align='center'>Lab Name</div></td>			
			<td  colspan='1' width="11%" class="tdFormat"><div align='center'>Pdf</div></td>
			</tr>
			</table>
			</td>
			</tr>
			<logic:present  name="PAGELIST">
			<logic:iterate  id="requisition"  name="PAGELIST" indexId="k">
			<tr>
		 	<td class="tdfont" colspan='1' width="1%"><div align='center'><input type="checkbox" name="selectedWorkOrderNo"  value='<%=i++ %>' onclick="SubmitPage(this);"></div></td>
		 	<% 
		 	String requisitonNo=((ResultEntryVOGroupByValidatedBy)pageContext.findAttribute("requisition")).getRequisitionDNo().substring(0,18); %>
			<td class="tdfont" colspan='1' width="20%"><div align='center'><%=requisitonNo%></div></td>					
			<td class="tdfont" colspan='1' width="16%"><div align='center'><bean:write name="requisition" property="patName"/></div></td>
			<td class="tdfont" colspan='1' width="10%"><div align='center'><bean:write name="requisition" property="patAge"/>/<bean:write name="requisition" property="patGenderShortName"/></div></td>			
			<td class="tdfont" colspan='1' width="12%"><a  onclick="Popup.showModal('DIVTEST<%=k%>')" style="cursor: pointer;"><u>PDF Detail</u></a> <div align='center' id="DIVTEST<%=k%>" style="border:3px;border-style:solid;background-color:#ffffff;padding:2px; font-size:50%;width:50%;center;display:none;">
			<table width="100%"  align="center" cellspacing="0" cellpadding="0">
			<logic:present name="requisition" property="resultEntryVOListValidatedBy">
			<tr>
				<td class="HEADER" width="50%"><div align="center">Test</div></td>
				<td class="HEADER" width="50%"><div align="center">Sample</div></td>
			</tr>
			<logic:iterate id="testDtl" name="requisition" property="resultEntryVOListValidatedBy" >
			
				<tr>
					<td class="tdfont" ><div align="center"><bean:write name="testDtl" property="testName"/></div></td>
				<td class="tdfont" ><div align="center"><bean:write name="testDtl" property="sampleName"/></div></td>
				</tr>
			</logic:iterate>
			</logic:present>
			<tr>				
				<td width="100%" align="center" colspan="2" class="HEADER"></td>
				
			</tr>
			<tr>				
				<td width="100%" align="center" colspan="2"><div align="center"><img src="/AHIMS/hisglobal/images/ok.gif"  onClick="Popup.hide('DIVTEST<%=k%>')"></div></td>
				
			</tr>
			</table>
			
			
			</div></td>
			<td class="tdfont" colspan='1' width="15%"><div align='center'><bean:write name="requisition" property="laboratoryName"/></div></td>			
			<td class="tdfont" colspan='1' width="11%"><div align='center'><img src="/AHIMS/hisglobal/images/PDF_Small.png" onclick="ShowSelectedPdf(<%=k%>,'<bean:write name="requisition" property="isconfidential"/>')" ></div>
			<logic:equal name="requisition" property="isconfidential" value="1">
			<div id="DIV_ID<%=k%>" style="border:3px;border-style:solid;background-color:#ffffff;padding:2px; font-size:50%;width:30%;center;display:none;">
			
			<table  width="100%"  align="center" cellspacing="0" cellpadding="0">
				<tr>				
				<td width="100%" align="center" colspan="2" class="HEADER">
				<div align="center">Paswword</div>
				</td>
				
				</tr>
				<tr >
					<td colspan="2" class="tdfont" width="100%">
					<div align="center">
					<input type="password" align="middle" name="enteredPassword">
					</div>
					</td>
				</tr>		
				<tr>				
				<td width="100%" align="center" colspan="2" class="HEADER"></td>				
				</tr>
				
				<tr >										
					<td width="100%" align="center"><div align="center"><img src="/AHIMS/hisglobal/images/ok.gif"  onClick="SelectedPdf(<%=k%>,'<bean:write name="requisition" property="isconfidential"/>');"/><img src="/AHIMS/hisglobal/images/btn-ccl.png"  onClick="Popup.hide('DIV_ID'+<%=k%>);"/>
					</div>
					</td>					
				</tr>
				
				
			</table>
			</div>
			</logic:equal>
			</td>
			
			</tr>
			</logic:iterate>
			<logic:empty name="PAGELIST">
			<tr>
			<td class="tdfont" colspan='7' width="100%"><div align='center'><font color="red"><b>No Record Found</b></font></div></td>
			</tr>
</logic:empty>
			</logic:present>
			<tr><td class='HEADER' colspan='7' align="right"><%=((investigation.controller.transaction.fb.PdfPrintingFB)pageContext.findAttribute("PdfPrintingFB")).getPageString() %></td></tr>
</logic:equal>
<!--<logic:equal name='PdfPrintingFB' property="resultPrintingType" value="OUTSIDESAMPLERESULTPRINTING">
			<tr>
	<td width="100%" class='HEADER' colspan='6'>
			<table width="100%">
			<tr>
			<td class="tdFormat" colspan='1' width="4%"><div align='center'></div></td>
			<td class="tdFormat" colspan='1' width="19%"><div align='center'>Laboratory</div></td>
			<td class="tdFormat" colspan='1' width="19%"><div align='center'>Test </div></td>
			<td class="tdFormat" colspan='1' width="19%"><div align='center'>Sample No.</div></td>
			<td class="tdFormat" colspan='1' width="19%"><div align='center'>Hospital </div></td>
			<td class="tdFormat" colspan='1' width="19%"><div align='center'>Lab No</div></td>
			</tr>
			</table>
			</td>
			</tr>
			<logic:present  name="PAGELIST">
			<logic:iterate  id="requisition"  name="PAGELIST">
			<tr>
		 	<td colspan='1' width="4%"><div align='center'><input type="checkbox" name="selectedWorkOrderNo"  value='<%=i++ %>' onclick="SubmitPage(this);"></div></td>
			<td colspan='1' width="19%"><div align='center'><bean:write name="requisition" property="laboratoryName"/></div></td>
			<td colspan='1' width="19%"><div align='center'><bean:write name="requisition" property="testName"/></div></td>
			<td colspan='1' width="19%"><div align='center'><bean:write name="requisition" property="sampleNo"/></div></td>
			<td colspan='1' width="19%"><div align='center'><bean:write name="requisition" property="referredHospitalName"/></div></td>
			<td colspan='1' width="19%"><div align='center'><bean:write name="requisition" property="labNo"/></div></td>
			</tr>
			</logic:iterate>
			<logic:equal name="i" value="0">
			<tr>
			<td class="tdfont" colspan='6' width="100%"><div align='center'><font color="red"><b>No Record Found</b></font></div></td>
			</tr>
			</logic:equal>
			</logic:present>
			<tr><td class='HEADER' colspan='5' align="right"><%=((investigation.controller.transaction.fb.PdfPrintingFB)pageContext.findAttribute("PdfPrintingFB")).getPageString() %></td></tr>
</logic:equal>

--><!-- <tr><td class='HEADER' colspan='10'><div align="center"><input type="button" name="enterR" id="enterR" value=" Enter the Results Printing" onclick='resultPrintingSubmit("GOFORRESULTPRINTING")' style="display: none"></div></td></tr>-->
</table>

<logic:empty name="PAGELIST">
<table width='100%'>


</table>
</logic:empty>
</logic:present>

</td></tr></table>
</his:ContentTag>


</td></tr></table>

<logic:present name="PAGELIST">
<logic:notEmpty name="PAGELIST">
<table id="enterR" cellpadding="0" cellspacing="0" width="100%" style="vertical-align: top; display: inline" >  
			<tbody>
				<tr>
					<td width="5"><img	src="/AHIMS/hisglobal/images/rnd-trans-ffffff-bdr.gif"></td>
					<td	style="background: transparent url(/AHIMS/hisglobal/images/border-top.gif) repeat-x scroll center top; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td width="5"><img src="/AHIMS/hisglobal/images/rnd-trans-ffffff-bdr-RT.gif"></td>
				</tr>
				<tr>
					<td	style="background: transparent url(/AHIMS/hisglobal/images/border-left.gif) repeat-y scroll left center; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td style="background-color: rgb(255, 255, 255);" width="100%">
					<div align="center"><!-- Button toolbar Finished -->
					<table width="100%">
					<tr>					
					<td width="10%" align="center"><img src="/AHIMS/hisglobal/images/btn-ccl.png" value="Save Details" onClick="cancelToResult('RESULTPRINTING');"/></td>					
					</tr>
					</table>
					</div>				 
					</td>
					<td style="background: transparent url(/AHIMS/hisglobal/images/border-right.gif) repeat-y scroll right center; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
				</tr>
				<tr>
					<td  width="5"><img
						src="/AHIMS/hisglobal/images/rnd-trans-ffffff-bdr-LB.gif"></td>
					<td
						style="background: transparent url(/AHIMS/hisglobal/images/border-bottom.gif) repeat-x scroll center bottom; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td width="5"><img
						src="/AHIMS/hisglobal/images/rnd-trans-ffffff-bdr-RB.gif"></td>
				</tr>
			</tbody>
		</table>
</logic:notEmpty>
</logic:present>
<%}catch(Exception e) {%>

<%e.printStackTrace();} %>
<fieldset id="fieldSetID" style="border-width: 0px;"><legend onclick="enableLegends()" style="cursor: pointer;" align="right"><u>Legends</u></legend>
 <table width="100%" height="100%" style="vertical-align: top;display: none;border-color: #FFFFFF;" id="legendID" align="right" bgcolor="">
 <tr>
 <td width="4%" bgcolor="#F1ECE2">&nbsp;</td><td width="30%"> 		 Color represents normal priority Test</td>
 <td width="3%" bgcolor="#FFcccc">&nbsp;</td><td width="30%">		 Color represents high priority Test</td>
 <td width="3%" bgcolor="#BDE4B8">&nbsp;</td><td width="30%">		 Color represents low priority Test</td>
 </tr>
 
</table>
</fieldset>
<html:hidden name="PdfPrintingFB" property="currentPageNo"/>
<html:hidden name="PdfPrintingFB" property="recordPerPage"/>
<html:hidden name="PdfPrintingFB" property="pagesPerBlock"/>
<html:hidden name="PdfPrintingFB" property="currentblock"/>
<html:hidden name="PdfPrintingFB" property="departmentName"/>
<html:hidden name="PdfPrintingFB" property="laboratoryName"/>
<html:hidden name="PdfPrintingFB" property="hmode"/>