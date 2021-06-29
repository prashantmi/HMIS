<!-- 
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PUNEET SINGH KHURANA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Machine Result Entry
 ## Purpose						        : Validate Machine Result Entry Values
 ## Date of Creation					: 20/10/2015
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
<%@page import="new_investigation.transactions.controller.fb.machineResultEntryFB"%>
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
<%@page import="new_investigation.vo.machineResultEntryVO"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>

 
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>

 <style> 
  #disable:hover{
  	background-color: yellow;
  }
</style> 


<html>
<body onload="displayDate();">


<script>

function getLabBasedMachine()
{
	

	if(document.getElementsByName("labCode")[0].value!="-1")
	{
	document.getElementsByName('hmode')[0].value='GETMACHINE';
	document.forms[0].submit();
	}
	
	document.getElementsByName('record')[0].value='1';
}

function displayDate(){
	
 if(document.getElementsByName("flag")!=null && document.getElementsByName("flag")[0].value=='1')
   getMachineResultEntry();
	
	if(document.getElementsByName("record")[0].value=="0")
	{
		document.getElementById("dateDisplay").style.display="none";
	}
	else
		{	document.getElementById("dateDisplay").style.display="";}
	
	
	
	
	
	
	
	
	
}

function cancelFunc()
{
	window.parent.closeTab();
}



function getMachineResultEntry()
{
	
	
	if(document.getElementsByName("record")[0].value=="0")
	{
		document.getElementById("dateDisplay").style.display="none";
	}
	else
		{	document.getElementById("dateDisplay").style.display="";}
	
	
	
	
	
	if(document.getElementsByName("labCode")[0].value=="-1")
	{
	alert("Select the Lab");
	return false;
	}
	
	
	if(document.getElementsByName("machineCode")[0].value=="-1")
	{
	alert("Select the Machine");
	return false;
	}
	
	
	if(document.getElementsByName("machineCode")[0].value!="-1")
	{
		  if(!validateTodayOrDate())
			 {return false;}
		  
		  
	document.getElementsByName('hmode')[0].value='GETDETAILS';
	document.forms[0].submit();
	}
	
}



function validateEntry()
{
	
	 
		var chkBoxSample=document.getElementsByName("chkSamplePatient");
		
		if(chkBoxSample==null || chkBoxSample.length<1)
			{ 
				alert("Please select atleast one Record");
				return false;
			}
		
		
		var cbs =document.getElementsByName('chkSamplePatient');
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
	document.getElementsByName('machineCode')[0].value='-1';
	document.getElementsByName('record')[0].value='1';
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
	
    valFromDate=document.getElementsByName('sampleCollDate')[0];
	valToDate=document.getElementsByName('resultEntryDate')[0];
	
	<%String systemdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
	document.getElementsByName('sysDate')[0].value="<%=systemdate%>";
	valSysDate=document.getElementsByName('sysDate')[0];
      
    if(compareDateCall(valFromDate,valToDate,2,"Sample Collection Date","Result Entry Date") && compareDateCall(valToDate,valSysDate,2,"Result Entry Date","System Date"))
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





<html:form action="/machineResultEntry">

	



		<his:TitleTag name="Machine Result Entry Process">
		</his:TitleTag>

<his:ContentTag>
		  <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
         %>
      <bean:define name="machineResultEntryFB" property="sampleCollDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="machineResultEntryFB" property="resultEntryDate" id="tDate" type="java.lang.String"/>          
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
			UserVO uservo=ControllerUTIL.getUserVO(request);

		  String val=uservo.getHospitalCode();
    	%> 
    	 <logic:equal name="machineResultEntryFB" property="showStatus" value="0">
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
			      <logic:present name="<%=InvestigationConfig.LAB_COMBO_FOR_MACHINE_RESULT_ENTRY%>">
			      <div align="left">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="machineResultEntryFB" property="labCode"    tabindex="1"  onchange="getLabBasedMachine()">
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LAB_COMBO_FOR_MACHINE_RESULT_ENTRY%>" property="value" labelProperty="label"/>
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
									<bean:message key="machine"/>&nbsp;
						   </font>
				     </div>
				     
			     
			      </td>
			      <td width="25%" class="tdfont">
			    <logic:present name="<%=InvestigationConfig.LIST_MACHINE_COMBO%>">
			      <div align="left">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
					  			   
				 <html:select name="machineResultEntryFB" property="machineCode"    tabindex="1"  onchange="getMachineResultEntry()">
				       					<html:option value="-1">Select Value</html:option>
				       					<html:option value="%">All</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LIST_MACHINE_COMBO%>" property="value" labelProperty="label"/>
				      				</html:select>
					   
					   
					   
				      				</span>
				  </div>
				  </logic:present>
				  
				    <logic:notPresent name="<%=InvestigationConfig.LIST_MACHINE_COMBO%>">
			      <div align="left">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
					  			   
				
				  				
				      				      				
				      					   
					    <html:select name="machineResultEntryFB" property="machineCode"    tabindex="1"  >
				       					<html:option value="-1">Select Machine</html:option>
					   </html:select>
					  
					   
					   
					   
				      				</span>
				  </div>
				  </logic:notPresent>
				  
				  
			     </td>
			     </tr>
			      <tr id = "dateDisplay">            
 			<td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="sampleCollDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td class="tdfont" width="25%" >
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left" >	               		 
					&nbsp;&nbsp;&nbsp;<his:date  name='sampleCollDate' dateFormate="%d-%b-%Y" value="<%=frDate%>" />
				</div>
			</td>
 			<td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="resultEntryDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
					&nbsp;&nbsp;&nbsp;<his:date name='resultEntryDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
			</td>
 		</tr>
 		 <tr  >
			    <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="records"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont" >

   <div align="left">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="machineResultEntryFB" property="record"    tabindex="1"  onchange="getMachineResultEntry()">
				       					<html:option value="1">Mapped</html:option>
				       					<html:option value="0">Un Mapped</html:option>
				      				</html:select>
				      				</span>
				  </div>


			     </td>
			     
			      <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Sample No&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont" colspan="3">

   <div align="left">
			      &nbsp;&nbsp;
					    <%-- <html:text name="machineResultEntryFB" property="samplenoo" readonly="true" style="" tabindex="1" /> --%>
                   <input type="text" id="textBoxCss"
											name="samplenoo"  
											onkeypress="" tabindex="1">
				  </div>


			     </td>
			     
			     
			     </tr>

<tr  >
			    
			     
			      <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								CR No&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont" >

   <div align="left">
			      &nbsp;&nbsp;
			      
			       <input type="text" id="textBoxCss"
											name="patcrno1" value="<%=val%>" maxlength="15" size="20"
											onkeypress="return validateNumeric(event,this)" tabindex="1">
											
					    <%-- <%-- <html:text name="machineResultEntryFB" property="patcrno" readonly="true" style="" tabindex="1" /> --%>

				  </div>


			     </td>
			     
			     
			      <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Repeated Test&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont" >

   <div align="left">
			      &nbsp;&nbsp;
					    <%-- <html:text name="machineResultEntryFB" property="samplenoo" readonly="true" style="" tabindex="1" /> --%>
                   <input type="radio" 	name="isrepeattest"  value="1" onkeypress="" tabindex="1">
				  </div>


			     </td>
			     
			     
			     </tr>
			     
			   <tr>
			   <td class="tdfont" align="left" colspan="4" width="25%">
			  <div align="center">
			  <img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) getMachineResultEntry()" onclick="getMachineResultEntry()" tabindex="1">
			     <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc() " onclick="cancelFunc()" tabindex="1">  </div>
			   </td>
			   </tr>  
			     </table>
			     </logic:equal>
			     </his:ContentTag>




  <his:statusTransactionInProcess>
     <%boolean flag=false; %>
  	 <%
				//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((machineResultEntryFB)request.getAttribute("machineResultEntryFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.MACHINE_RESULT_ENTRY_ESSENTIALS_VO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=50;
					fbPage.setMaxRecords(maxRecord);
				 
				 %>
				 
				 <logic:equal name="machineResultEntryFB" property="showStatus" value="0">
				 		<his:PaginationTag name="fbPagination"></his:PaginationTag>
			<logic:present name="<%=InvestigationConfig.MACHINE_RESULT_ENTRY_ESSENTIALS_VO %>">
			<% flag=true; %>
			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<td width="3%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					<input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" /> </font>
	                  </b>
					</td>
					<td width="13%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="CrNo"/> </font></b>
					</td>
					<td width="10%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="labNo"/> </font></b>
					</td>
					<td width="10%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Test Name </font></b>
					</td>
					<td width="10%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Parameter Name </font></b>
					</td>
						<td width="10%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Machine Name </font></b>
					</td>
					<td width="5%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="machineTestCode"/></font></b>
					</td>


					<td width="10%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="machineParameter"/></font></b>
					</td>
					<td width="5%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="machineResult"/></font></b>
					</td>
						<td width="10%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Range</font></b>
					</td>
					<td width="15%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="resultEntryDate"/></font></b>
					</td>
				<%-- 	<td width="11%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="labName"/></font></b>
					</td>
					<td width="11%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patStatus"/></font></b>
					</td> --%>
				</tr>
			</table>
			<logic:empty name="<%=InvestigationConfig.MACHINE_RESULT_ENTRY_ESSENTIALS_VO %>">
				<center>
				<font color="red" size="4">
				<bean:message key="noRecord"/></font></center>
			</logic:empty>
			<logic:notEmpty name="<%=InvestigationConfig.MACHINE_RESULT_ENTRY_ESSENTIALS_VO %>">
			<table   width="100%" bgcolor="#EBEBEB" cellspacing="0" style="border-spacing: 0;">
					<%
					 List<machineResultEntryVO> lstPatVO=(List<machineResultEntryVO>)session.getAttribute(InvestigationConfig.MACHINE_RESULT_ENTRY_ESSENTIALS_VO);
					 int i,size=0;
			 		if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
					int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						String grpCode="";
						String key="";
						for(int j=startIndex;j<=endIndex;j++)
					{
						//int i=j-startIndex;
						boolean firstTimeTravesall=true;
					if(j<size)
									{
						machineResultEntryVO voPat=lstPatVO.get(j);
						String  chkVal=voPat.getReqDNo()+"#"+voPat.getMachineRecordId()+"#"+voPat.getMachineCode()+"#"+voPat.getMachineResult()+"#"+voPat.getMachineReqId()+"#"+voPat.getTestCode()+"#"+voPat.getParameterCode()+"#"+voPat.getMachineLabSampleNo()+"#"+voPat.getmachineTestParameterParaCount()+"#"+voPat.getPatCrNo()+"#"+(voPat.getPatGender()==null?"":voPat.getPatGender())+"#"+(voPat.getPatAge()==null?"":voPat.getPatAge());
						
						
						String parameters=voPat.getRanges();
						
						String[] paraValues=parameters.split("#@");

						String paracode=paraValues[0];
						String paraName=paraValues[1];
						String refRange="";
						
						if(paraValues.length>2)
							 refRange=paraValues[2];
							String displayRef="";
							String rangeTypeFinal = "";
							String[] refreValueFinal = null;
					        
							//updated by krishnan nema on 17/10/2018
							String paraEntry="";
							
							if(voPat.getMachineResult()!=null)
							{
								paraEntry=voPat.getMachineResult();
							}
							/* if(paraValues.length>3)
							{
								paraEntry=paraValues[3];
							if(paraValues[3].equals("--")  )
							{
								 paraEntry="";
							}
							else
							{
								 
							}
							} */
							
							if(!paraEntry.contains("<"))
							paraEntry=paraEntry.replace("\r\n","<br>");
							if(paraEntry.contains("$"))
								paraEntry=paraEntry.replace("$","<br>");
							
							boolean flagg=false;
							 if(refRange!=null||!refRange.equals(""))
							{
							refRange=refRange.replace("$", "@");
							String[] refValues=refRange.split("@");
							refreValueFinal = refValues;
							 if(refValues.length>1)
							 {
								 String checkRangetyp=refValues[0];
								 rangeTypeFinal = checkRangetyp;
								 if(checkRangetyp.equals("1"))
								 {
									 String highValue=refValues[1];
										
										String lowValue=refValues[2];
										
										if((highValue.matches("\\d*\\.?\\d+") ) && (lowValue.matches("\\d*\\.?\\d+") ))
										{
											
											flagg=true;
										}
										String highValueUom=refValues[3];
										String lowValueUom=refValues[4];
										 displayRef=lowValue+" "+lowValueUom+" - "+highValue+" "+highValueUom;
								 }
								 else if(checkRangetyp.equals("2"))
								 {
									 
									 String rangetyp=">";
										
										String tovalue=refValues[2];
										String tovalueunit=refValues[1];
										
										if( (tovalue.matches("\\d*\\.?\\d+") ))
										{
											
											flagg=true;
										}
										
										 displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
										
								 } 
									 
								 else if(checkRangetyp.equals("3"))
								 {
									 String rangetyp=">=";
										
										String tovalue="";
												if(refValues.length>2)
												tovalue=refValues[2];
										String tovalueunit="";
										if(refValues.length>1)
												tovalueunit=	refValues[1];
										
										if( (tovalue.matches("\\d*\\.?\\d+") ))
										{
											
											flagg=true;
										}
										
										 displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
										 
								 }
								 else if(checkRangetyp.equals("4"))
								 {
									 String rangetyp="<";
										
									 String tovalue="";
										if(refValues.length>2)
										tovalue=refValues[2];
								String tovalueunit="";
								if(refValues.length>1)
										tovalueunit=	refValues[1];
										if( (tovalue.matches("\\d*\\.?\\d+") ))
										{
											
											flagg=true;
										}
										 displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
										 
								 }
								 else if(checkRangetyp.equals("5"))
								 {
									 String rangetyp="<=";
										
									 String tovalue="";
										if(refValues.length>2)
										tovalue=refValues[2];
								String tovalueunit="";
								if(refValues.length>1)
										tovalueunit=	refValues[1];
										if( (tovalue.matches("\\d*\\.?\\d+") ))
										{
											
											flagg=true;
										}
										 displayRef=rangetyp+" "+tovalue+"  "+tovalueunit;
										 
								 }
							
							 }
							}
							else  
								displayRef="";
							
							/*  String paraEntry=paraValues[3];
							if(paraValues[3].equals("--")  )
							{
								 paraEntry="";
							}
							else
							{
								 
							}
							
							
							if(!paraEntry.contains("<"))
							paraEntry=paraEntry.replace("\r\n","<br>");
							if(paraEntry.contains("$"))
								paraEntry=paraEntry.replace("$","<br>"); */
						
								
								//updated by krishnan nema on 17/10/2018
								
								boolean numeric = false;
								try {
	            					//Double num = Double.parseDouble(paraEntry);
	            					if (paraEntry.matches("\\d*\\.?\\d+") ) 
	            			        {
	            						numeric = true;
	            			        }
	        					} catch (NumberFormatException e) {
	            				numeric = false;
	        					}
								
					
							
									
									
							
								
								
								
								
							
							
							
							
							
							
							
							
						//String labCode=voPat.getLabCode();
							 
                           /*   if(key.equals(""))
						      key=voPat.getPatCrNo()+voPat.getgr */
					%>
					 
					
					<tr id="disable">
						<td width="3%" align="left"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<input type="checkbox" class="jpCheckbox" name="chkSamplePatient" value='<%=chkVal%>' onclick="ValidateSameCrNo(this)" >
							</font>
						</td>
						
							<td width="13%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatCrNo()%></font> 
						 
				  		</td>
				  		
				  		
						<td width="10%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineLabSampleNo() %></font> 
						 
				  		</td>
				  		
				  		<td width="10%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
							<% if(voPat.getTestname()!=null && voPat.getShortgrpcode()!=null){ %>
						 	<%=voPat.getTestname()+"(Group:"+voPat.getShortgrpcode()+")" %></font> 
						 <%}else{ %>
						 <%=voPat.getTestname()==null?"-":voPat.getTestname() %></font>
						 <%} %>
				  		</td>
				  		
				  		<td width="10%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getParaname()==null?"-":voPat.getParaname() %></font> 
						 
				  		</td>
				  		
				  		
				  		<td width="10%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						  <%-- <%=displayRef%></font> --%>
						 	 <%=voPat.getMachinename() %></font> 
						 
				  		</td>
				  		
				  		<td width="5%" align="left"  >
				  		 
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineTestParameterCode() %></font>
						  
				  		
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineTestParameterName()%></font>
						  
				  		</td>
				  		
				  		
				  		
				  		<%
				  		
				  				if(numeric && flagg)
							{
								
								
								if(refreValueFinal!=null)
								{
									
									
									
									if(rangeTypeFinal.equals("1"))  {
										
										 String highValue="";
												 if(refreValueFinal.length>1)
										 highValue= refreValueFinal[1];
										 String lowValue="";
										 if(refreValueFinal.length>2)
										 lowValue= refreValueFinal[2];
										 
										 
										 if((Float.valueOf(paraEntry) > Float.valueOf(highValue)) || (Float.valueOf(paraEntry) < Float.valueOf(lowValue))){
											
											 %>
											 	<td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#FF0000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> 
												
											<% 
										 }else{
											 %>
											 	<td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> 
												
											<% 
										 }
										 
									
									}else if(rangeTypeFinal.equals("2")){
										
										String tovalue=refreValueFinal[2];
										if((Float.valueOf(paraEntry) < Float.valueOf(tovalue))){
											
											 %>
											 
											<td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#FF0000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> 
												
												
											<% 
										 }else{
											
											 
											 
											 %>
											
											<%
											
											if(Float.compare(Float.valueOf(paraEntry),Float.valueOf(tovalue))==0)
											{
											%>
											 <td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#FF0000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td>
											 
											 <%}else{ %>
												<td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> <%} %>
											
											<%
											
											
										}
										
									}else if(rangeTypeFinal.equals("3")){
										
										String tovalue=refreValueFinal[2];
										if((Float.valueOf(paraEntry) <= Float.valueOf(tovalue))){
											 
											 if(Float.compare(Float.valueOf(paraEntry),Float.valueOf(tovalue))==0)
													{
											 %>
											 
											 <td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> 
						
											 <%} else{ %>
											 	
											 	<td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#FF0000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> 
									<%} %>			
											<% 
										 }else{
											 %>
											 
												<td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> 
												
											<%
										}
										
										
									}else if(rangeTypeFinal.equals("4")){
										
										
										String tovalue=refreValueFinal[2];
										if((Float.valueOf(paraEntry) > Float.valueOf(tovalue))){
											 
											 %>
											 
											 	<td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#FF0000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> 
												
											<% 
										 }else{
											 %>
											 
											 
											<% if(Float.compare(Float.valueOf(paraEntry),Float.valueOf(tovalue))==0)
											{
											%>
											 <td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#FF0000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td>
											 
											 <%}else{ %>
											 
											 
												<td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> <%} %>
												
											<%
										}
										
									}else if(rangeTypeFinal.equals("5")){
									
										String tovalue=refreValueFinal[2];
										if((Float.valueOf(paraEntry) >= Float.valueOf(tovalue))){
											 
											  if(Float.compare(Float.valueOf(paraEntry),Float.valueOf(tovalue))==0)
													{
											 %>
											 
											 <td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> 
						
											 <%} else{ %>
											 
											 	<td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#FF0000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> 
												<%} %>
												
											<% 
										 }else{
											 %>
											 
												<td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> 
												
											<%
										}
										
										
									}
									else
									{%>
										
											<td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> 
										
								<%	}
									
									
								
								
								}else{
			
			
						%>

	<td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> 
											


											<%
											}
								}else{
									%>

	
									<td id="paraEntryColor" width="5%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> 

									<%
									
								}%>
				  				
				  		 <%-- <%-- <td  width="10%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
				  		</td> --%> 
				  		
				  		
				  		<td width="10%" align="left">
				  		 
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<%=displayRef%></font>
				  	
					
					
					
				  		</td>
				  		
				  		<td width="15%" align="left">
				  		 
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResultEntryDate()%></font>
					
						  <%} }%>
				  		</td>
				<%--   		<td width="11%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatLabName() %></font>
						 
				  		</td>
				  		<td width="11%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatStatus() %></font>
						  
				  		</td> --%>
					</tr>
				
				
					
					
			</table>
			</logic:notEmpty>
			</logic:present>
			 </logic:equal>
</his:statusTransactionInProcess>


 <his:ButtonToolBarTag>
				     <his:statusTransactionInProcess>
				     
				  
				      
				      
				      
				    <logic:present name="<%=InvestigationConfig.MACHINE_RESULT_ENTRY_ESSENTIALS_VO %>">
				  
				  
				  
				    <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="saveDiv"    onkeypress="if(event.keyCode==13) validateEntry();"  tabindex="1" onclick ="validateEntry();" >
				  
				    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'id="cancel" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
				    
				    
				    
				    
				    </logic:present>
				    
				    
				    </his:statusTransactionInProcess>
				    </his:ButtonToolBarTag>

	 <html:hidden name="machineResultEntryFB" property="hmode" />
	 <html:hidden name="machineResultEntryFB" property="patcrno1" />
	 <html:hidden name="machineResultEntryFB" property="samplenoo" />	
	 <html:hidden name="machineResultEntryFB" property="isrepeattest" />	

	 <html:hidden name="machineResultEntryFB" property="flag" />	 
	 	 <html:hidden name="machineResultEntryFB" property="showStatus" />	 
	 	 	 	 <html:hidden name="machineResultEntryFB" property="currentPage" />	 
	 	 	 	 	 	 	 	 <html:hidden name="machineResultEntryFB" property="sysDate" />	 
	 	 	 	 
	 	 
	 

		<his:status/>		    
</html:form>
</body>
</html>  