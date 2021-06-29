<!--  ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PUNEET SINGH
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Investigation Requisition Raising for external investigation
 ## Purpose						        : 
 ## Date of Creation					: 02-Feb-2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 

 -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page
	import="new_investigation.transactions.controller.fb.externalInvestigationCaptureFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@page import="hisglobal.utility.Entry"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>

<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>

<%@page import="java.util.*"%>
<his:javascript src="/hisglobal/js/calendar.js" />
<%@page import="hisglobal.hisconfig.Config"%>


<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>


<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>



<his:javascript src="/new_investigation/js/appointment.js" />
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<%-- <his:css src="/hisglobal/css/easyui.css" /> --%>
<!--  calender problem -->
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/jquery/bookMark.css" />
<his:css src="/hisglobal/css/jquery/search.css" />
<his:css src="/hisglobal/css/InvBookMark.css" />
<his:css src="/hisglobal/css/invPopup.css" />
<his:css src="/hisglobal/css/invPopup.css" />
<his:css src="/hisglobal/css/newHisRadioStyle.css" />
<his:css src="/hisglobal/css/tutorstyle.css" />
<his:css src="/hisglobal/css/flexcrollstyles.css" />

<!--styleTextBox.css  newHisRadioStyle.css
 -->



<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/css-pop-inv.js" />
<his:javascript src="/hisglobal/js/flexcrolle.js" />




<!-- <script type="text/javascript" src="/HIS/appointment/js/jquery/jquery-2.1.1.min.js"></script>
 -->
<script type="text/javascript"
	src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true"> </script>
<script src="media/misc/datepicker1.js" type="text/javascript"></script>
<script>


function validateCRNoCHE(hospitalCode)
{
	 var crno =document.getElementsByName("patCrNo")[0].value;
     var textLength = crno.length;
     var hosCodeLen=hospitalCode.length;
     
     if(hosCodeLen==3)
		{ 
        	  if(textLength==13)
	           {
         	document.getElementsByName('hmode')[0].value='GETPATDTL';
         	document.forms[0].submit();
	            }
        	   
             

	          else
	          {     
	            alert("InValid CR No");
	            if(document.getElementsByName("patCrNo")[0])
	            {
	            document.getElementsByName("patCrNo")[0].focus()
	             }    
	           
	          }
       }
      if(hosCodeLen==5)
    	{ 
			   
			    	
			    	if(textLength==15)
						 {
						   
						document.getElementsByName('hmode')[0].value='GETPATDTL';
						document.forms[0].submit();
					     }
					
			     else
			     {     
				   		    alert("InValid CR No");
				     		if(document.getElementsByName("patCrNo")[0]){
				            document.getElementsByName("patCrNo")[0].focus()
				                      }    
			          
			     }
          }
      
  
 
   return true;
}


</script>

<script>
function showPatDetailsPatient()
{
document.getElementById("showhidePatient").style.display="";
document.getElementById("hidePatient").style.display="";
document.getElementById("showPatient").style.display="none";	
	
}

function hidePatDetailsPatient()
{
document.getElementById("showhidePatient").style.display="none";
document.getElementById("hidePatient").style.display="none";
document.getElementById("showPatient").style.display="";	
}
</script>



<script>

function validateForm()
{
	
	for (var i=0; i<document.getElementsByName("externalLabCode").length;i++)
		{
		
		
		if(document.getElementsByName("externalLabCode")[i].value=="-1")
			{
			alert("Select the External Laboratory");
			document.getElementsByName("externalLabCode")[i].focus();
			return false;
			}
		

		if(document.getElementsByName("labCode")[i].value=="-1")
			{
			alert("Select the Laboratory");
			document.getElementsByName("labCode")[i].focus();
			return false;
			}
		

		if(document.getElementsByName("testCode")[i].value=="-1")
			{
			alert("Select the Test");
			document.getElementsByName("testCode")[i].focus();
			return false;
			}
		

		if(document.getElementsByName("parameterCode")[i].value=="-1")
			{
			alert("Select the Parameter");
			document.getElementsByName("parameterCode")[i].focus();
			return false;
			}
		

		if(document.getElementsByName("sampleCode")[i].value=="-1")
			{
			alert("Select the Sample");
			document.getElementsByName("sampleCode")[i].focus();
			return false;
			}
		
		if(document.getElementsByName("result")[i].value=="")
		{
		alert("Enter Result");
		document.getElementsByName("result")[i].focus();
		return false;
		}
		
		if(document.getElementsByName("labName")[i].value=="")
		{
		alert("Enter Lab Name");
		document.getElementsByName("labName")[i].focus();
		return false;
		}
	
		
		if(document.getElementsByName("testName")[i].value=="")
		{
		alert("Enter Test Name");
		document.getElementsByName("testName")[i].focus();
		return false;
		}
	
	
		

		if(document.getElementsByName("parameterName")[i].value=="")
		{
		alert("Enter Parameter Name");
		document.getElementsByName("parameterName")[i].focus();
		return false;
		}
	
		

		if(document.getElementsByName("sampleName")[i].value=="")
		{
		alert("Enter Sample Name");
		document.getElementsByName("sampleName")[i].focus();
		return false;
		}
	
		
		
		
		var k=0;
		for(k=i+1;k<document.getElementsByName("externalLabCode").length;k++)
			if(document.getElementsByName("externalLabCode")[i].value==document.getElementsByName("externalLabCode")[k].value)
				if(document.getElementsByName("labCode")[i].value==document.getElementsByName("labCode")[k].value)
					if(document.getElementsByName("testCode")[i].value==document.getElementsByName("testCode")[k].value)
						if(document.getElementsByName("parameterCode")[i].value==document.getElementsByName("parameterCode")[k].value)
							if(document.getElementsByName("sampleCode")[i].value==document.getElementsByName("sampleCode")[k].value)
			{	alert("Duplicate Row Exists. Select different Value for it.");
				document.getElementsByName("externalLabCode")[k].focus();
				return false;
			}
		
		
		
		
		
		
		
		
		
		
		
		}
	
	
	
	
	return true;
	
	
	
	
	
	
	}


function AddRowToTable()
{ 
		
	    var nRow=0;
	  	var tableObj=document.getElementById('externalCapture');
	  	var numRows=tableObj.rows.length;
	  	
	  	
	  	if(numRows>2)
	  	{
	  		nRow=tableObj.rows[numRows-1].id;
	  	}
	  	else
	  	{
	  		nRow=numRows;
	  	}
		var rowValue=numRows;
	  	var tabRow=tableObj.insertRow(numRows);
	  	tabRow.id=parseInt(nRow)+1;

	  	var indexVolSpecific=numRows-1;

	  	var index=parseInt(numRows-1);
	  	

	  	var td1=document.createElement("TD");
	  	var td2=document.createElement("TD");
	  	var td3=document.createElement("TD");
		var td4=document.createElement("TD");
		var td5=document.createElement("TD");
		var td6=document.createElement("TD");
		var td7=document.createElement("TD");
	 	
	  	  	
		td1.innerHTML=getExternalLabList(rowValue);
	  	td1.className='tdfont';
	  	td1.colspan="1";
	  	
	  	td2.innerHTML=getLabList(rowValue);
	  	td2.className='tdfont';
	  	td2.colspan="1";
	  	
	  	
	  	td3.innerHTML=getTestList(rowValue);
	  	td3.className='tdfont';
	  	td3.colspan="1";
	  	
		td4.innerHTML=getParameterList(rowValue);
	  	td4.className='tdfont';
	  	td4.colspan="1";
	  	
		
		td5.innerHTML=getSampleList(rowValue);
	  	td5.className='tdfont';
	  	td5.colspan="1";
	
		
		td6.innerHTML="<div align='left'><input type='text' name='result'	maxlength='25' size='17' onkeypress='return validateAlphaNumericWithSpecialCharacterOnly(event,this)'	tabindex='1'>	</input></div>";
	  	td6.className='tdfont';
	  	td6.colspan="1";
	
   		  	
	  	td7.className='tdfont';
	  	td7.colspan="1";
	  	td7.innerHTML="<div align='center'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' onClick='deleteRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"))'></div>";
	  	
	  	tabRow.appendChild(td1);  
	  	tabRow.appendChild(td2);
	  	tabRow.appendChild(td3);
	  	tabRow.appendChild(td4);
	  	tabRow.appendChild(td5);
	  	tabRow.appendChild(td6);
	  	tabRow.appendChild(td7);
	  	
	  	document.forms[0].numberOfRow.value=numRows;
}


function deleteRow(Str)
{	
    
	
	var numRows=parseInt(document.getElementsByName('numberOfRow')[0].value);
	var tableObj=document.getElementById('externalCapture');
    var temp=Str;
 
    tableObj.deleteRow(temp.rowIndex);
    document.forms[0].numberOfRow.value=(numRows-1);
        
    return true;
    
}





function getExternalLabList(rowValue)
{
	var labCombo = "<div align='left'><select name= 'externalLabCode' style='width:81%;'><option value='-1'>Select Value</option>";
	<%java.util.List<Entry>  lstExternalLab= (java.util.List<Entry>)session.getAttribute(InvestigationConfig.ARRAY_LABNAMES_EXTERNAL);
	if(lstExternalLab!=null)
	{
	for(int i=0;i<lstExternalLab.size();i++)
	{
		%>
		labCombo+="<option value = '<%=lstExternalLab.get(i).getValue()%>'><%=lstExternalLab.get(i).getLabel()%></option>"
		<%
	}
	}
	%>
	labCombo+="</select></div>";
      return labCombo;
}



function getLabList(rowValue)
{
	var labCombo = "<div align='left'><select name = 'labCode' onchange='getLabName(this,"+rowValue+")' style='width:81%;'><option value='-1'>Select Value</option>";
	<%java.util.List<Entry>  lstLab= (java.util.List<Entry>)session.getAttribute(InvestigationConfig.ARRAY_LABNAMES);
	if(lstLab!=null)
	{
	for(int i=0;i<lstLab.size();i++)
	{
		%>
		labCombo+="<option value = '<%=lstLab.get(i).getValue()%>'><%=lstLab.get(i).getLabel()%></option>"
		<%
	}
	}
	%>
	labCombo+="<option value='-2'>Other</option></select></div><div align='left' id='labNameDiv"+rowValue+"' style='display:none'><input type='text' name='labName' id='labNameId"+rowValue+"'	maxlength='25' size='17' onkeypress='return validateAlphaNumericWithSpecialCharacterOnly(event,this)'	tabindex='1'>	</input></div>";
      return labCombo;
}





function getTestList(rowValue)
{
	var testCombo = "<div align='left'><select name= 'testCode' onchange='getTestName(this,"+rowValue+")' style='width:81%;'><option value='-1'>Select Value</option>";
	<%java.util.List<Entry>  lstTest= (java.util.List<Entry>)session.getAttribute(InvestigationConfig.ARRAY_TESTNAMES);
	if(lstTest!=null)
	{
	for(int i=0;i<lstTest.size();i++)
	{
		%>
		testCombo+="<option value = '<%=lstTest.get(i).getValue()%>'><%=lstTest.get(i).getLabel()%></option>"
		<%
	}
	}
	%>
	testCombo+="<option value='-2'>Other</option></select></div><div align='left' id='testNameDiv"+rowValue+"' style='display:none'><input type='text' name='testName' id='testNameId"+rowValue+"'	maxlength='25' size='17' onkeypress='return validateAlphaNumericWithSpecialCharacterOnly(event,this)'	tabindex='1'>	</input></div>";
      return testCombo;
}


function getParameterList(rowValue)
{
	var parameterCombo = "<div align='left'><select name= 'parameterCode' onchange='getParameterName(this,"+rowValue+")' style='width:81%;'><option value='-1'>Select Value</option>";
	<%java.util.List<Entry>  lstParameter= (java.util.List<Entry>)session.getAttribute(InvestigationConfig.ARRAY_PARAMETERNAME);
	if(lstParameter!=null)
	{
	for(int i=0;i<lstParameter.size();i++)
	{
		%>
		parameterCombo+="<option value = '<%=lstParameter.get(i).getValue()%>'><%=lstParameter.get(i).getLabel()%></option>"
		<%
	}
	}
	%>
	parameterCombo+="<option value='-2'>Other</option></select></div><div align='left' id='parameterNameDiv"+rowValue+"' style='display:none'><input type='text' name='parameterName' id='parameterNameId"+rowValue+"'	maxlength='25' size='17' onkeypress='return validateAlphaNumericWithSpecialCharacterOnly(event,this)'	tabindex='1'>	</input></div>";
      return parameterCombo;
}




function getSampleList(rowValue)
{
	var sampleCombo = "<div align='left'><select name= 'sampleCode' onchange='getSampleName(this,"+rowValue+")' style='width:81%;'><option value='-1'>Select Value</option>";
	<%java.util.List<Entry>  lstSample= (java.util.List<Entry>)session.getAttribute(InvestigationConfig.ARRAY_SAMPLENAMES);
	if(lstSample!=null)
	{
	for(int i=0;i<lstSample.size();i++)
	{
		%>
		sampleCombo+="<option value = '<%=lstSample.get(i).getValue()%>'><%=lstSample.get(i).getLabel()%></option>"
		<%
	}
	}
	%>
	sampleCombo+="<option value='-2'>Other</option></select></div><div align='left' id='sampleNameDiv"+rowValue+"' style='display:none'><input type='text' name='sampleName' id='sampleNameId"+rowValue+"'	maxlength='25' size='17' onkeypress='return validateAlphaNumericWithSpecialCharacterOnly(event,this)'	tabindex='1'>	</input></div>";
      return sampleCombo;
}




function getLabName(obj,i)
{
	if(obj.value=="-2")
		{
		document.getElementById("labNameDiv"+i).style.display="";
		document.getElementById("labNameId"+i).value="";	
		document.getElementById("labNameId"+i).focus();
		}
	else
		{
		document.getElementById("labNameDiv"+i).style.display="none";
		document.getElementById("labNameId"+i).value=obj.options[obj.selectedIndex].innerHTML;
	
		}
	//alert(obj.options[obj.selectedIndex].innerHTML);
	
}

function getTestName(obj,i)
{
	
	if(obj.value=="-2")
	{
		document.getElementById("testNameDiv"+i).style.display="";

	document.getElementById("testNameId"+i).value="";	
	document.getElementById("testNameId"+i).focus();
	}
else
	{document.getElementById("testNameDiv"+i).style.display="none";

	document.getElementById("testNameId"+i).value=obj.options[obj.selectedIndex].innerHTML;
	}
	//alert(obj.options[obj.selectedIndex].innerHTML);
	
}

function getParameterName(obj,i)
{
	if(obj.value=="-2")
	{
		document.getElementById("parameterNameDiv"+i).style.display="";

	document.getElementById("parameterNameId"+i).value="";	
	document.getElementById("parameterNameId"+i).focus();
	}
else
{	document.getElementById("parameterNameDiv"+i).style.display="none";

	document.getElementById("parameterNameId"+i).value=obj.options[obj.selectedIndex].innerHTML;
}	//alert(obj.options[obj.selectedIndex].innerHTML);
	
}

function getSampleName(obj,i)
{
	if(obj.value=="-2")
	{
		document.getElementById("sampleNameDiv"+i).style.display="";

	document.getElementById("sampleNameId"+i).value="";	
	document.getElementById("sampleNameId"+i).focus();
	}
else
{	document.getElementById("sampleNameDiv"+i).style.display="none";

	document.getElementById("sampleNameId"+i).value=obj.options[obj.selectedIndex].innerHTML;
}//alert(obj.options[obj.selectedIndex].innerHTML);
	
}



function upload(e)
{
	//var processID = document.getElementsByName("processID")[0].value;
	//var crNo = document.getElementsByName("crNoHosCode")[0].value;
	//var crNo = document.getElementsByName("crNo")[0].value;
	//var filname = document.getElementsByName("filename")[0].value;
	//var filname = document.getElementsByName("filname")[0].value;
	//var url = "/HISGlobal/hisglobal/filetransfer/UploadFile.action?strProcessId="+processID+"&strCRNoHospCode="+crNo+"&strFileName="+filname;
	var url = "/hisglobal/utility/filetransfer/action/Upload.cnt";
	openURLInPopup(url,600,400,0,0);
}

function uploadValue()
{
	
	document.getElementsByName('hmode')[0].value='UPLOAD';
 	document.forms[0].submit();
}

function openURLInPopup( url, windowWidth , windowHeight, winLeft, winTop) {
	var blankHeight;
	var blankWidth;
	if(!windowWidth)
		windowWidth =( $(window).width() * 0.8);
	if(!windowHeight)
		windowHeight = ($(window).height() * 0.8);
	
	blankWidth = ($(window).width()-windowWidth);
	blankHeight =($(window).height() - windowHeight)  ;
	var windowLeft = Math.round(blankWidth / 2)+ "px";
	var windowTop = Math.round(blankHeight / 2 ) + "px";
	windowHeight = windowHeight + "px";
	windowWidth = windowWidth + "px";
	$.modal('<iframe src="' + url + '" width="'+windowWidth+'" height="'+windowHeight+'" style="border:0">');
	$('#simplemodal-container').width(windowWidth).height(windowHeight);
	if(typeof winLeft == "undefined")	winLeft =0;
	if(typeof winTop == "undefined")	winTop =0;
	$('#simplemodal-container').css({'left':windowLeft+winLeft,'top':windowTop+winTop});
}


</script>
<body>
	<div class="divStyle">

		<html:form action="/externalInvestigationCapture" method="post" enctype="multipart/form-data">

			<his:statusNew>

				<his:TitleTag key="invRaising">
				</his:TitleTag>



				<his:InputCrNoInvestigationTag name="externalInvestigationCaptureFB">
				</his:InputCrNoInvestigationTag>

			</his:statusNew>
			<his:statusTransactionInProcess>

				<!-- DISPLAY PATIENT DETAILS -->

				<logic:present name="<%=InvestigationConfig.PATIENT_VO%>">



					<%
						Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
					%>

					<his:SubTitleTag name="Patient Details">
						<%
							String showDetail="showPatDetailsPatient()";
							 String hideDetail="hidePatDetailsPatient()";
						%>
						<img class="button" title="Show Patinet Details"
							src='<his:path src="/hisglobal/images/plusinv.png"/>'
							id="showPatient" tabindex="1" onclick="<%=showDetail%>">
						<img class="button" title="Hide Patient Details"
							src='<his:path src="/hisglobal/images/Minus.png"/>'
							id="hidePatient" style="display: none;" tabindex="1"
							onclick="<%=hideDetail%>">
					</his:SubTitleTag>
					<table width="100%">
						<tr>
							<td class="tdfont" width="25%">
								<div align="right">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="Name" />
									</font>
								</div>

							</td>
							<td class="tdfonthead" width="25%">
								<div align="left">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=lstPatVO.getPatFirstName()%>
									</font>
								</div>
							</td>
							<td class="tdfont" width="25%">
								<div align="right">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="CRNo." />
									</font>
								</div>
							</td>
							<td class="tdfonthead" width="25%">
								<div align="left">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=lstPatVO.getPatCRNo()%>
									</font>
								</div>
							</td>


						</tr>
					</table>
					<div id="showhidePatient" style="display: none;">
						<table width="100%">
							<tr>
								<td class="tdfont" width="25%">
									<div align="right">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="Age/Gender" />
										</font>
									</div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="left">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=lstPatVO.getPatAge()+"/"+lstPatVO.getPatGenderCode()%>
										</font>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div align="right">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="PrimaryCategory" />
										</font>
									</div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="left">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=lstPatVO.getPatCategory()%>
										</font>
									</div>
								</td>
								<html:hidden name="externalInvestigationCaptureFB"
									value="<%=lstPatVO.getPatCategoryCode()%>"
									property="patCatCode" />
							</tr>
							<tr>
								<td class="tdfont" width="25%">
									<div align="right">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="FatherName" />
										</font>
									</div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="left">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=lstPatVO.getPatGuardianName()%>
										</font>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div align="right">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="MobileNo." />
										</font>
									</div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="left">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <%=lstPatVO.getPatMobileNo()%>
										</font>
									</div>
								</td>

							</tr>
						</table>
					</div>

				</logic:present>




				<!-- IPD Logic LIST_APTBASED_TEST -->

				<logic:equal name="externalInvestigationCaptureFB"
					property="patStatus" value="IPD">


					<logic:present name="<%=InvestigationConfig.LIST_ADMISSION_VO%>">
						<his:SubTitleTag name="Admission Details"></
  </his:SubTitleTag>
						<his:ContentTag>

							<%
								List<Inv_PatientAdmissionDtlVO> lstPatAdmissionVO=(List<Inv_PatientAdmissionDtlVO>)session.getAttribute(InvestigationConfig.LIST_ADMISSION_VO);
																											  	 
																											 	if(lstPatAdmissionVO!=null && lstPatAdmissionVO.size()>0 )
																											 	{
																											 		int sizePatAdmVO=lstPatAdmissionVO.size();
																											 	
																											  
																											 		for(int k=0;k<sizePatAdmVO;k++)
																											 		{
																											 			Inv_PatientAdmissionDtlVO voPatAdm=lstPatAdmissionVO.get(k);
																											 			String patCrNo=voPatAdm.getPatcrno();
							%>
							<table width="100%">
								<tr>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="AdmittedDepartmentName" />
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=voPatAdm.getAdmitteddepartmentname()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="WardName" />
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=voPatAdm.getPatwardname()%>
										</div>
									</td>
								</tr>
								<tr>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="RoomName" />
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=voPatAdm.getPatroomname()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="BedName" />
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=voPatAdm.getBedname()%>
										</div>
									</td>
								</tr>
								<tr>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="ConsultantName" />
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=voPatAdm.getConsultantName()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div align="right">
											<bean:message key="Diagnosis" />
										</div>
									</td>
									<td class="tdfonthead" width="25%">
										<div align="left">
											<%=voPatAdm.getDiagnosis()%>
										</div>
									</td>
								</tr>
							</table>
							<%
								}
																											 	}
							%>

						</his:ContentTag>


					</logic:present>
				</logic:equal>


				<!-- OPD/Emergency Logic -->
				<div id="container">



					<div id="blanket" style="display: none;"></div>
					<div id="popUpDiv" style="display: none;">
						<table id="addMoreValue" width="100%">
							<tr>
							</tr>







						</table>
						<div align="center">
							<img src='/HISInvestigationG5/hisglobal/images/ok.gif'
								id='minusButton' onclick="popupIn('popUpDiv')"'>

						</div>
					</div>
					<!-- end #mainContent -->
				</div>



				<logic:notEqual name="externalInvestigationCaptureFB"
					property="patStatus" value="IPD">
					<logic:present name="<%=InvestigationConfig.LIST_EPISODE_VO%>">




						<his:SubTitleTag name="Episode Details">
							<%
								String showDetail="showPatDetails()";
																												 String hideDetail="hidePatDetails()";
							%>
							<img class="button" title="Show Patinet Details"
								src='<his:path src="/hisglobal/images/plusinv.png"/>' id="show"
								tabindex="1" onclick="<%=showDetail%>">
							<img class="button" title="Hide Patient Details"
								src='<his:path src="/hisglobal/images/Minus.png"/>' id="hide"
								style="display: none;" tabindex="1" onclick="<%=hideDetail%>">

						</his:SubTitleTag>

						<his:ContentTag>
							<table width="100%" id="episodeTable">
								<tr>
									<td class="tdfont" width="5%">
										<div>
											<b><bean:message key="Select" /></b>
										</div>
									</td>
									<td class="tdfont" width="25%"><bean:message
											key="VisitDate" /></td>
									<td class="tdfont" width="25%"><bean:message
											key="Department/Unit" /></td>
									<td class="tdfont" width="25%"><bean:message
											key="Diagnosis" /></td>
								</tr>
							</table>
							<%
								boolean isEpisodeChecked=true;
																											     if(InvestigationConfig.IS_EPISODE_BOUND.equals(InvestigationConfig.IS_EPISODE_BOUND_FALSE))
																											    	 isEpisodeChecked=false;
																											     
																											  	 List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
																											  	 
																											 	if(lstPatEpisodeVO!=null && lstPatEpisodeVO.size()>0 )
																											 	{
																											 		boolean firstTimeTravesa=true;
																											 		if(lstPatEpisodeVO.size()==1)
																											 		{
							%>

							<script type="text/javascript">
																						 			
																						 			document.getElementById("show").style.display="none";
																						 			
																						 			
																						 			</script>

							<%
								}
																											 		int sizePatEpisodeVO=lstPatEpisodeVO.size();
																											 		for(int k=0;k<sizePatEpisodeVO;k++)
																											 		{
																											 			Inv_EpisodeVO voPatEpisode=lstPatEpisodeVO.get(k);
																											 			String patCrNo=voPatEpisode.getPatCRNo();
																											 		
																											 			if(firstTimeTravesa)
																											 			{
							%>
							<table width="100%">
								<tr>
									<td class="tdfont" width="5%">
										<div>
											<input type="radio" checked="checked" name="radioEpisode"
												value="<%=voPatEpisode.getPatepisodecode()%>"
												onclick="showRequisition()" />
										</div>
									</td>

									<td class="tdfont" width="25%">
										<div>
											<%=voPatEpisode.getPatvisitdate()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div>
											<%=voPatEpisode.getDepartment()+" / "+voPatEpisode.getPatdeptunit()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div>
											<%=voPatEpisode.getDiagnosis()%>
										</div>
									</td>
								</tr>
							</table>
							<%
								}
																												   
																													if(!firstTimeTravesa)
																													{
							%>

							<div id="showhide" style="display: none;">
								<table width="100%">
									<tr>
										<td class="tdfonthead" width="5%" align="left">
											<div align="left">
												<input type="radio" name="radioEpisode"
													value="<%=voPatEpisode.getPatepisodecode()%>"
													onclick="showRequisition()" <%if(isEpisodeChecked){%>
													checked="checked" <%}%> />
											</div>
										</td>

										<td class="tdfonthead" width="25%" align="left">
											<div align="left">
												<%=voPatEpisode.getPatvisitdate()%>
											</div>
										</td>
										<td class="tdfonthead" width="25%" align="left">
											<div align="left">
												<%=voPatEpisode.getDepartment()+" / "+voPatEpisode.getPatdeptunit()%>
											</div>
										</td>
										<td class="tdfonthead" width="25%" align="left">
											<div align="left">
												<%=voPatEpisode.getDiagnosis()%>
											</div>
										</td>
									</tr>
								</table>

							</div>

							<%
								}	firstTimeTravesa=false;
																												isEpisodeChecked=false; // for only one selection
																											 		}
																											 	}
																											 	
																											 	else
																											 	{
							%>

							<script type="text/javascript">
																					 			
																					 			document.getElementById("episodeTable").style.display="none";
																					 			document.getElementById("show").style.display="none";
																					 			
																					 			
																					 			</script>
																					 			
																					 			No Episode Details
																					 			
																					 			<%
								}
							%>



						</his:ContentTag>

					</logic:present>
				</logic:notEqual>


				<!-- END PATIENT DETAILS -->
				
				
				<!-- upload functionality -->
				
				
				
				<!-- end upload functionality --> 
				
				<his:SubTitleTag name="Upload Investigation Result">
				</his:SubTitleTag>
				 <br>
				
				<%-- 	 <div align="center">
						<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/uploadfile.png"/>'
							alt="Capture Report" title="Capture Report" onkeypress="if(event.keyCode==13) upload(event)" 
							onclick="upload(event)" >
					</div>	
 --%>

				<br>
			 


			 	<div align="center">
					<html:file property="file" />
				</div> 

	
				

				<!-- start investigation details -->

				<his:SubTitleTag name="Investigation Capture for External Lab">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" id="externalCapture" border="1">

						<tr>



							<td width="15%" class="tdfont">
								<div align="left">External Lab Institute</div>
							</td>

							<td width="15%" class="tdfont">
								<div align="left">Lab</div>
							</td>


							<td width="15%" class="tdfont">
								<div align="left">Test</div>
							</td>


							<td width="15%" class="tdfont">
								<div align="left">Parameter</div>
							</td>


							<td width="15%" class="tdfont">
								<div align="left">Sample</div>
							</td>


							<td width="15%" class="tdfont">
								<div align="left">Result</div>
							</td>

							<td class='tdfonthead' width="1%">
								<div align='right'></div>
							</td>




						</tr>




						<tr>


							<td width="15%" class="tdfont">

								<div align="left">
									<html:select name="externalInvestigationCaptureFB"
										property="externalLabCode" tabindex="1" style="width:81%;">
										<html:option value="-1">Select Value</html:option>
										<logic:present
											name="<%=InvestigationConfig.ARRAY_LABNAMES_EXTERNAL%>">
											<html:options
												collection="<%=InvestigationConfig.ARRAY_LABNAMES_EXTERNAL%>"
												property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>



							</td>


							<td width="15%" class="tdfont">

								<div align="left">
									<html:select name="externalInvestigationCaptureFB"
										property="labCode" tabindex="1" onchange="getLabName(this,0);"
										style="width:81%;">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=InvestigationConfig.ARRAY_LABNAMES%>">
											<html:options
												collection="<%=InvestigationConfig.ARRAY_LABNAMES%>"
												property="value" labelProperty="label" />
											<html:option value="-2">Other</html:option>
										</logic:present>

									</html:select>

								</div>


								<div align="left" id="labNameDiv0" style="display:none">
									<html:text name="externalInvestigationCaptureFB"
										property="labName" maxlength="25" size="17"
										styleId="labNameId0"
										onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
										tabindex="1">
									</html:text>

								</div>

							</td>


							<td width="15%" class="tdfont">
								<div align="left">

									<html:select name="externalInvestigationCaptureFB"
										property="testCode" tabindex="1"
										onchange="getTestName(this,0);" style="width:81%;">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=InvestigationConfig.ARRAY_TESTNAMES%>">
											<html:options
												collection="<%=InvestigationConfig.ARRAY_TESTNAMES%>"
												property="value" labelProperty="label" />
											<html:option value="-2">Other</html:option>
										</logic:present>
									</html:select>
								</div>

								<div align="left" id="testNameDiv0" style="display:none">
									<html:text name="externalInvestigationCaptureFB"
										property="testName" maxlength="25" size="17"
										styleId="testNameId0"
										onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
										tabindex="1">
									</html:text>

								</div>
							</td>


							<td width="15%" class="tdfont">
								<div align="left">

									<html:select name="externalInvestigationCaptureFB"
										property="parameterCode" tabindex="1"
										onchange="getParameterName(this,0);" style="width:81%;">
										<html:option value="-1">Select Value</html:option>
										<logic:present
											name="<%=InvestigationConfig.ARRAY_PARAMETERNAME%>">
											<html:options
												collection="<%=InvestigationConfig.ARRAY_PARAMETERNAME%>"
												property="value" labelProperty="label" />
											<html:option value="-2">Other</html:option>
										</logic:present>
									</html:select>
								</div>


								<div align="left" id="parameterNameDiv0" style="display:none">
									<html:text name="externalInvestigationCaptureFB"
										property="parameterName" maxlength="25" size="17"
										styleId="parameterNameId0"
										onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
										tabindex="1">
									</html:text>

								</div>


							</td>




							<td width="15%" class="tdfont">
								<div align="left">

									<html:select name="externalInvestigationCaptureFB"
										property="sampleCode" tabindex="1"
										onchange="getSampleName(this,0);" style="width:81%;">
										<html:option value="-1">Select Value</html:option>
										<logic:present
											name="<%=InvestigationConfig.ARRAY_SAMPLENAMES%>">
											<html:options
												collection="<%=InvestigationConfig.ARRAY_SAMPLENAMES%>"
												property="value" labelProperty="label" />
											<html:option value="-2">Other</html:option>
										</logic:present>
									</html:select>
								</div>

								<div align="left" id="sampleNameDiv0" style="display:none">
									<html:text name="externalInvestigationCaptureFB"
										property="sampleName" maxlength="25" size="17"
										styleId="sampleNameId0"
										onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
										tabindex="1">
									</html:text>

								</div>


							</td>


							<td width="15%" class="tdfont">
								<div align="left">
									<html:text name="externalInvestigationCaptureFB"
										property="result" maxlength="25" size="17"
										onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
										tabindex="1">
									</html:text>

								</div>
							</td>


							<td class='tdfonthead' width="1%">
								<div align='right'>
									<img src="/HISInvestigationG5/hisglobal/images/plus.gif"
										id="addImg" onclick="AddRowToTable()">
								</div>
							</td>







						</tr>
					</table>

				</his:ContentTag>


			</his:statusTransactionInProcess>


			<html:hidden name="externalInvestigationCaptureFB" property="hmode" />
			<html:hidden name="externalInvestigationCaptureFB" property="patCrNo" />
			<html:hidden name="externalInvestigationCaptureFB"
				property="numberOfRow" />
			<html:hidden name="externalInvestigationCaptureFB"
				property="patStatus" />





			<his:ButtonToolBarTag>

				<his:statusNew>
					<img class="button"
						src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"
						style="cursor: pointer"
						onkeypress="if(event.keyCode==13) cancelFunc();" tabindex="1"
						onclick="cancelFunc();">
				</his:statusNew>

				<his:statusTransactionInProcess>




					<logic:present name="<%=InvestigationConfig.LIST_EPISODE_VO%>">


						<img class="button" id="saveDiv"
							src='<his:path src="/hisglobal/images/btn-sv.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13 && validateForm()) submitForm('SAVE');"
							tabindex="1" onclick="if(validateForm()) submitForm('SAVE');">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
							tabindex="1" style="cursor: pointer"
							onkeypress="if(event.keyCode==13) submitForm('NEW');"
							tabindex="1" onclick="submitForm('NEW');">

					</logic:present>

				</his:statusTransactionInProcess>

			</his:ButtonToolBarTag>
			<his:statusTransactionInProcess>



			</his:statusTransactionInProcess>

		</html:form>

	</div>
	<his:status />
</body>