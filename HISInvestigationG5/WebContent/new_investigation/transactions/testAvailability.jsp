<!-- 
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PUNEET SINGH KHURANA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Test Availability Process
 ## Purpose						        : Make a test available/unavailable 
 ## Date of Creation					: 29/03/2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


 -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="new_investigation.vo.RequisitionListVO"%>
<%@page import="new_investigation.vo.OnlinePatientAcceptanceVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.transactions.controller.fb.testAvailabilityFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
							
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
 
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/email.css" />
<his:css src="/hisglobal/css/textboxcss.css" />
<his:css src="/hisglobal/css/drop.css" />
 <his:css src="/hisglobal/css/Cannedstyle.css" />
 <link rel="stylesheet" href="/new_investigation/css/Date/site-demos.css"> 
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/commonUtility.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/cannedMacroValidation.js" />
<his:javascript src="/hisglobal/js/cannedMacroAutocomplete.js" />
<his:javascript src="/bloodbank/js/bloodRequisition.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />
<his:javascript src="/new_investigation/js/jquery-1.11.1.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />
<his:javascript src="/new_investigation/js/ckeditor/ckeditor.js"/>
<his:javascript src="/new_investigation/js/wysiwyg.js"/>
<his:javascript src="/new_investigation/js/wysiwyg-settings.js" />
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="new_investigation.vo.testAvailabilityVO"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>

 
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>
<html>
<body>

<script>

function getLabBasedTest()
{
	if(document.getElementsByName("labCode")[0].value=="-1")
	{
	alert("Select the Lab");
	return false;
	}
	
	
	if(document.getElementsByName("testStatus")[0].value=="-1")
	{
	alert("Select the Status");
	return false;
	}
	
	
	
	document.getElementsByName('hmode')[0].value='GETTEST';
	document.forms[0].submit();
	
	
}

function displayDate(){
	
	
	if(document.getElementsByName("isAvailable")[0].checked==true)
	{
		
		document.getElementById("dateDisplay").style.display="none";

	}
	else
		{	
		document.getElementById("dateDisplay").style.display="";
}
	
	
}

function cancelFunc()
{
	window.parent.closeTab();
}


function viewReport()
{
	
	document.getElementsByName('hmode')[0].value='VIEWREPORT';
	document.forms[0].submit();
	
	
	
}


function getMachineResultEntry()
{
	
	
	
	
		
	if(document.getElementsByName("labCode")[0].value=="-1")
	{
	alert("Select the Lab");
	return false;
	}
	
	
	if(document.getElementsByName("testStatus")[0].value=="-1")
	{
	alert("Select the Status");
	return false;
	}
	
	
	document.getElementsByName('hmode')[0].value='GETDETAILS';
	document.forms[0].submit();
	
	
}



function validateEntry()
{
	
	 
		var chkBoxSample=document.getElementsByName("chkTest");
		
		if(chkBoxSample==null || chkBoxSample.length<1)
			{ 
				alert("Please select atleast one Record");
				return false;
			}
		
		
		var cbs =document.getElementsByName('chkTest');
		for(var len=0; len<cbs.length;len++)
			{
			if(cbs[len].checked)
			break;
			}
		
		if(len==cbs.length)
		{	alert("Select at least one record");
			return false;
		}	
		
		
		document.getElementsByName('hmode')[0].value='SAVE';
		document.forms[0].submit();
	
		return true;
}


function submitFor()
{
	document.getElementsByName('showStatus')[0].value='0';
	document.getElementsByName('labCode')[0].value='-1';
	document.getElementsByName('testStatus')[0].value='-1';
	document.getElementsByName('searchTest')[0].value='';
	
	document.getElementsByName('hmode')[0].value='NEW';
	document.forms[0].submit();
	}


var isChecked = false;
function allSelected() 
{
	// this line is for toggle the check
    isChecked = !isChecked;
    //below line refers to 'jpCheckbox' class
    $('input:checkbox.jpCheckbox').attr('checked',isChecked);
    //OR,
    //$('input:checkbox.jpCheckbox').attr('checked','checked');
}


function validateTodayOrDate()
{
	success=false;        	   
	
    valFromDate=document.getElementsByName('fromDate')[0];
	valToDate=document.getElementsByName('toDate')[0];
	
	<%String systemdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
	document.getElementsByName('sysDate')[0].value="<%=systemdate%>";
	valSysDate=document.getElementsByName('sysDate')[0];
      
    if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
    {
	    success=true;
    }             
    return success;        
}


function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

</script>





<html:form action="/testAvailability">

	



		<his:TitleTag name="Test Availability Process">
		</his:TitleTag>

<his:ContentTag>
		
    	 <logic:equal name="testAvailabilityFB" property="showStatus" value="0">
    	<his:SubTitleTag name="Details"></
  			</his:SubTitleTag>
		
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			    
			         
			    
			     <tr>
			    <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LabType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont">
			      <logic:present name="<%=InvestigationConfig.LAB_COMBO_FOR_TEST_AVAILABILITY%>">
			      <div align="left">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="testAvailabilityFB" property="labCode"    tabindex="1" >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LAB_COMBO_FOR_TEST_AVAILABILITY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
			     </td>
			     <td width="25%" class="tdfont">
			     
			      <div align="right">
			               <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						   </font> 
						    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="testStatus"/>&nbsp;
						   </font>
				     </div>
				     
			     
			      </td>
			      <td width="25%" class="tdfont">
			      <div align="left">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
					  			   
				 <html:select name="testAvailabilityFB" property="testStatus"    tabindex="1"  onchange="getLabBasedTest()">
				       					<html:option value="-1">Select Value</html:option>
				       					<html:option value="1">Available</html:option>
				       					<html:option value="0">UnAvailable</html:option>
				       					<html:option value="%">All</html:option>
				      				</html:select>
					   	</span>
				  </div>
				 
				  
				   
				  
				  
			     </td>
			     </tr>

<tr>
						<td width="25%" class="tdfont">
							<div align="right">
								 <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
										key="searchTest" /></b>
								</font>
							</div>
						</td>

						<td width="25%" class="tdfont">
							<div align="left">&nbsp;&nbsp;
								<html:text name="testAvailabilityFB" property="searchTest" 
									maxlength="60" size="26" 
					onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
						
						
							<td width="25%" class="tdfont" ></td>
							<td width="25%" class="tdfont" ></td>
					</tr>



			   <tr>
			   <td class="tdfont" align="left" colspan="4" width="25%">
			  <div align="center">
			  <img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) getMachineResultEntry()" onclick="getLabBasedTest()" tabindex="1">
 			     <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc() " onclick="cancelFunc()" tabindex="1">  </div>
			   </td>
			   </tr>  
			     </table>
			     </logic:equal>
			     </his:ContentTag>




  <his:statusTransactionInProcess>
   
				 		
			<logic:present name="<%=InvestigationConfig.TEST_AVAILABILITY_DETAILS %>">
			
			
			
				<logic:equal name="testAvailabilityFB" property="testStatus" value="1">
				
				<div align="center">
				<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
			
			<td width="25%" align="left"   >
						
					</td>
					
					
					<td width="3%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					<input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" /> </font>
	                  </b>
					</td>
					<td width="22%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="TestName"/> </font></b>
					</td>
					<td width="25%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="testAvailability"/> </font></b>
					</td>
					<td width="25%" align="left"   >
						
					</td>
					

				</tr>
			</table>
			</div>
			</logic:equal>
			
			
			
			
	<logic:notEqual name="testAvailabilityFB" property="testStatus" value="1">
				<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<td width="3%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					<input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" /> </font>
	                  </b>
					</td>
					<td width="22%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="TestName"/> </font></b>
					</td>
					<td width="25%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="testAvailability"/> </font></b>
					</td>
						<td width="25%" align="left"   >
						
							<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/> </font></b>
						
					</td>
						<td width="25%" align="left"   >
						
							<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate"/> </font></b>
					
					</td>

				</tr>
			</table>
			</logic:notEqual>
			<logic:empty name="<%=InvestigationConfig.TEST_AVAILABILITY_DETAILS%>">
				<center>
				<font color="red" size="4">
				<bean:message key="noTest"/></font></center>
			</logic:empty>
			<logic:notEmpty name="<%=InvestigationConfig.TEST_AVAILABILITY_DETAILS %>">
						
					  <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
         %>
      <bean:define name="testAvailabilityFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="testAvailabilityFB" property="toDate" id="tDate" type="java.lang.String"/>          
	   <%
         if(frDate==null||frDate.equalsIgnoreCase(""))
         {  
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
  
		  if(tDate==null||tDate.equalsIgnoreCase(""))
		  {  
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }
    	%> 
			
			
		
					 
					 
					 
			
			<logic:equal name="testAvailabilityFB" property="testStatus" value="1">
			
				<%
					 List<testAvailabilityVO> lstPatVO=(List<testAvailabilityVO>)session.getAttribute(InvestigationConfig.TEST_AVAILABILITY_DETAILS);
					 int size=0;
			 		if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
					
						for(int j=0;j<size;j++)
					{								
						testAvailabilityVO voPat=lstPatVO.get(j);
						String  chkVal=voPat.getLabCode()+"#"+voPat.getTestCode();
										
					%>
					
					<div align="center">
			<table   width="100%" bgcolor="#EBEBEB" cellspacing="0" style="border-spacing: 0;">
					
					
					<tr>
					
						<td width="25%" align="left" >
						  
						
						 
				  		</td>
				  		
				  		
						<td width="3%" align="left"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<input type="checkbox" class="jpCheckbox" name="chkTest" value='<%=chkVal%>'>
							</font>
						</td>
						
							<td width="22%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getTestName()%></font> 
						 
				  		</td>
				  		
				  		
				  				<td width="25%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getAvailableValue()%></font> 
						 
				  		</td>
				  	
				  	
				  		<td width="25%" align="left" >
						  
						
						 
				  		</td>
				  	
				  	
				  	
					
				
					</tr>
				
				
				<%} %>
				<tr></tr>
				<tr></tr>
				<tr>
				
				<td width="3%" >
				
				</td>
						<td colspan="2" >
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="isAvailable" />&nbsp;</b>
								</font>
							</div>
						</td>


						<td  colspan="1"  >
							<div align="left">
								<html:radio name="testAvailabilityFB" tabindex="1"
									property="isAvailable" value="1"
									onchange="displayDate()"></html:radio>
								<bean:message key="yes" />
								<html:radio name="testAvailabilityFB" tabindex="1"
									property="isAvailable" value="0"
									onchange="displayDate()"></html:radio>
								<bean:message key="no" />
							

							</div>
						</td>
					</tr>
					
						<tr id="remarksDisplay" >
						
					<td colspan="3">
						<div align="right">
							<font color="RED" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="remarks" />&nbsp;
							</font>
						</div>
					</td>

					<td colspan="1">
						<div align="left">
							<html:textarea name="testAvailabilityFB" property="remarks"
								cols="28" tabindex="1" 
								onkeypress="return CheckMaxLength(event,this,200,1)">
							</html:textarea>
						</div>
					</td>
					</tr>
					
					
		
			<tr id = "dateDisplay" style="display:none;">            
 			<td colspan="2">
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td colspan="1" >
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left" >	               		 
					&nbsp;&nbsp;&nbsp;<his:date  name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>" />
				</div>
			</td>
 			<td colspan="1">
        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td colspan="1">
	    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
					&nbsp;&nbsp;&nbsp;<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
			</td>
 		</tr> 
			
					
			</table>
			</div>
			</logic:equal>
			
			
			
			
			
			<logic:notEqual name="testAvailabilityFB" property="testStatus" value="1">
			<%
					 List<testAvailabilityVO> lstPatVO=(List<testAvailabilityVO>)session.getAttribute(InvestigationConfig.TEST_AVAILABILITY_DETAILS);
					 int size=0;
			 		if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
					
						for(int j=0;j<size;j++)
					{								
						testAvailabilityVO voPat=lstPatVO.get(j);
						String  chkVal=voPat.getLabCode()+"#"+voPat.getTestCode();
										
					%>
					<div align="center">
			<table   width="100%" bgcolor="#EBEBEB" cellspacing="0" style="border-spacing: 0;">
				
					 
					
					<tr>
						<td width="3%" align="left"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<input type="checkbox" class="jpCheckbox" name="chkTest" value='<%=chkVal%>'>
							</font>
						</td>
						
							<td width="22%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getTestName()%></font> 
						 
				  		</td>
				  		
				  		
				  				<td width="25%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getAvailableValue()%></font> 
						 
				  		</td>
				  	
				  	
				  	
				  	
				  
				  	
				  		<td width="25%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getFromDate()%></font> 
						 
				  		</td>
				  	
				  	
				  		<td width="25%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getToDate()%></font> 
						 
				  		</td>
				  	
				  	
				  	
				  	
					<%} %>
				<tr></tr>
				<tr></tr>
				<tr>
				
				<td width="3%" >
				
				</td>
						<td colspan="2" >
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="isAvailable" />&nbsp;</b>
								</font>
							</div>
						</td>


						<td  colspan="2"  >
							<div align="left">
								<html:radio name="testAvailabilityFB" tabindex="1"
									property="isAvailable" value="1"
									onchange="displayDate()"></html:radio>
								<bean:message key="yes" />
								<html:radio name="testAvailabilityFB" tabindex="1"
									property="isAvailable" value="0"
									onchange="displayDate()"></html:radio>
								<bean:message key="no" />
							

							</div>
						</td>
					</tr>
					
						<tr id="remarksDisplay" >
						
					<td colspan="3">
						<div align="right">
							<font color="RED" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="remarks" />&nbsp;
							</font>
						</div>
					</td>

					<td colspan="2">
						<div align="left">
							<html:textarea name="testAvailabilityFB" property="remarks"
								cols="28" tabindex="1" 
								onkeypress="return CheckMaxLength(event,this,200,1)">
							</html:textarea>
						</div>
					</td>
					</tr>
					
					
		
			
			<tr id = "dateDisplay" style="display:none;">            
 			<td colspan="2">
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td colspan="1" >
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left" >	               		 
					&nbsp;&nbsp;&nbsp;<his:date  name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>" />
				</div>
			</td>
 			<td colspan="1">
        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td colspan="1">
	    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
					&nbsp;&nbsp;&nbsp;<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
			</td>
 		</tr> 
					
			</table>
			</div>
			</logic:notEqual>
			
		
			</logic:notEmpty>
			</logic:present>
			
</his:statusTransactionInProcess>



   <his:statusTransactionInProcess>

 <his:ButtonToolBarTag>
				  
				     
				  
				      
				      
				      
				  
				  
				  				    <logic:present name="<%=InvestigationConfig.TEST_AVAILABILITY_DETAILS %>">
				  
				    <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="saveDiv"    onkeypress="if(event.keyCode==13) validateEntry();"  tabindex="1" onclick ="validateEntry();" >
				  
				    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'id="cancel" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
				    
				    <img class="button" src='<his:path src="/hisglobal/images/btn-rpt.png"/>'id="cancel" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="viewReport();">
				    
				    
				    
				    </logic:present>
				    
				    
				    </his:ButtonToolBarTag>
				    
				    				    </his:statusTransactionInProcess>
				    
				    
				    

	 <html:hidden name="testAvailabilityFB" property="hmode" />	 
	 	 <html:hidden name="testAvailabilityFB" property="showStatus" />	 
	 	 	 	 <html:hidden name="testAvailabilityFB" property="currentPage" />	 
	 	 	 	 	 	 	 	 <html:hidden name="testAvailabilityFB" property="sysDate" />	 
	 	 	 	 
	 	 
	 

		<his:status/>		    
</html:form>
</body>
</html>  