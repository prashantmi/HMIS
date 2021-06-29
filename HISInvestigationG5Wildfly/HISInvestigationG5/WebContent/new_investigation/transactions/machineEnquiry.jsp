<!--  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Puneet
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Machine Result  Enquiry
 ## Purpose						        : Check and Validate Machine entered values
 ## Date of Creation					: 28/09/2015
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
<%@page import="new_investigation.transactions.controller.fb.machineEnquiryFB"%>
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
<%@page import="new_investigation.vo.machineEnquiryVO"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>

 
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>
<html>
<body >

<!-- onload display date -->

<script>

function getSampleNo()
{
	 if(!validateTodayOrDate())
	 {return false;}

	if(document.getElementsByName("machineCode")[0].value=="-1")
	{
		alert("Select the Machine");
		return false;
	}
	
	document.getElementsByName('hmode')[0].value='GETSAMPLE';
	document.forms[0].submit();
	

}



function submitFor()
{
	document.getElementsByName('showStatus')[0].value='0';
	document.getElementsByName('machineCode')[0].value='-1';
	document.getElementsByName('testStatus')[0].value='%';
	document.getElementsByName('machineLabSampleNo')[0].value='%';
	document.getElementsByName('hmode')[0].value='NEW';
	document.forms[0].submit();
	}

function cancelFunc()
{
	window.parent.closeTab();
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

function getmachineEnquiry()
{
	
	
	if(!validateTodayOrDate())
	 {return false;}

	if(document.getElementsByName("machineCode")[0].value=="-1")
	{
		alert("Select the Machine");
		return false;
	}
	
	document.getElementsByName('hmode')[0].value='GETDETAILS';
	document.forms[0].submit();
	
	
	}

</script>





<html:form action="/machineEnquiry">

	



		<his:TitleTag name="Machine Result Entry Process">
		</his:TitleTag>

<his:ContentTag>
		  <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
         %>
      <bean:define name="machineEnquiryFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="machineEnquiryFB" property="toDate" id="tDate" type="java.lang.String"/>          
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
    	 <logic:equal name="machineEnquiryFB" property="showStatus" value="0">
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
									<bean:message key="machine"/>&nbsp;
						   </font>
				     </div>
				     
			     
			      </td>
			      <td width="25%" class="tdfont">
			      
			      
			      <logic:present
								name="<%=InvestigationConfig.LIST_MACHINE_COMBO_MACHINE_ENQUIRY%>">
								<div align="left">
									&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
											name="machineEnquiryFB" property="machineCode" tabindex="1"
											onchange="getSampleNo()">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_MACHINE_COMBO_MACHINE_ENQUIRY%>"
												property="value" labelProperty="label" />
										</html:select>
								</span>
							</div>


							</logic:present>
							
							 <logic:notPresent
								name="<%=InvestigationConfig.LIST_MACHINE_COMBO_MACHINE_ENQUIRY%>">
								<div align="left">
									&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
											name="machineEnquiryFB" property="machineCode" tabindex="1">
											<html:option value="-1">Select Machine</html:option>
										</html:select>
									</span>
								</div>
							</logic:notPresent>


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
				 <html:select name="machineEnquiryFB" property="testStatus"    tabindex="1" >
				       					<html:option value="3">Completed</html:option>
				       					<html:option value="1">In Process</html:option>
				       					<html:option value="2">Partially Completed</html:option>
				       					<html:option value="0">Not Found</html:option>
				       				
				 	   					
				      				</html:select>
				      				</span>
				  </div>
				
			     </td>
			     
			     
			     
			     </tr>
			      <tr id = "dateDisplay">            
 			<td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromSampleCollDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td class="tdfont" width="25%" >
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left" >	               		 
					&nbsp;&nbsp;&nbsp;<his:date  name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>" onchange="getSampleNo()" />
				</div>
			</td>
 			<td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toSampleCollDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
					&nbsp;&nbsp;&nbsp;<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>" onchange="getSampleNo()" />
				</div>
			</td>
 		</tr>
 		 <tr  >
			    <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="sampleNo"/>&nbsp;
						 </font>
				     </div>
			      </td>

			     
			     
			     			      <td width="25%" class="tdfont">
			      
			      
			      <logic:present
								name="<%=InvestigationConfig.SAMPLE_NO_MACHINE_ENQUIRY%>">
								<div align="left">
									&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
											name="machineEnquiryFB" property="machineLabSampleNo" tabindex="1"
											>
											<html:option value="%">All</html:option>
											<html:options
												collection="<%=InvestigationConfig.SAMPLE_NO_MACHINE_ENQUIRY%>"
												property="value" labelProperty="label" />
										</html:select>
								</span>
							</div>


							</logic:present>
							
							 <logic:notPresent
								name="<%=InvestigationConfig.SAMPLE_NO_MACHINE_ENQUIRY%>">
								<div align="left">
									&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
											name="machineEnquiryFB" property="machineLabSampleNo" tabindex="1">
											<html:option value="%">All</html:option>
										</html:select>
									</span>
								</div>
							</logic:notPresent>


						</td>
			     
			     
			     </tr>

			   <tr>
			   <td class="tdfont" align="left" colspan="4" width="25%">
			  <div align="center">
			  <img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) getmachineEnquiry()" onclick="getmachineEnquiry()" tabindex="1">
			    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc() " onclick="cancelFunc()" tabindex="1">   </div>
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
					fbPage.setCurrentPage(((machineEnquiryFB)request.getAttribute("machineEnquiryFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.MACHINE_RESULT_ENQUIRY_VO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=10;
					fbPage.setMaxRecords(maxRecord);
				 
				 %>
				 
				 <logic:equal name="machineEnquiryFB" property="showStatus" value="0">
				 		<his:PaginationTag name="fbPagination"></his:PaginationTag>
			<logic:present name="<%=InvestigationConfig.MACHINE_RESULT_ENQUIRY_VO %>">
			<% flag=true; %>
			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<!-- <td width="3%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					<input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" /> </font>
	                  </b>
					</td> -->
					<td width="15%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="CrNo"/> </font></b>
					</td>
					<td width="15%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patName"/> </font></b>
					</td>
					<td width="15%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="TestName"/> </font></b>
					</td>
					<td width="10%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="labNo"/> </font></b>
					</td>
					<td width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="machineTestCode"/></font></b>
					</td>


					<td width="15%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="machineParameter"/></font></b>
					</td>
					<td width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="machineResult"/></font></b>
					</td>
					<td width="15%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="resultEntryDate"/></font></b>
					</td>
			
				</tr>
			</table>
			<logic:empty name="<%=InvestigationConfig.MACHINE_RESULT_ENQUIRY_VO %>">
				<center>
				<font color="red" size="4">
				<bean:message key="noRecord"/></font></center>
			</logic:empty>
			<logic:notEmpty name="<%=InvestigationConfig.MACHINE_RESULT_ENQUIRY_VO %>">
			<table   width="100%" bgcolor="#EBEBEB" cellspacing="0" style="border-spacing: 0;">
					<%
					 List<machineEnquiryVO> lstPatVO=(List<machineEnquiryVO>)session.getAttribute(InvestigationConfig.MACHINE_RESULT_ENQUIRY_VO);
					 int i,size=0;
			 		if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
					int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						String grpCode="";
						for(int j=startIndex;j<=endIndex;j++)
					{
						//int i=j-startIndex;
						boolean firstTimeTravesall=true;
					if(j<size)
									{
						machineEnquiryVO voPat=lstPatVO.get(j);
						String  chkVal=voPat.getReqDNo()+"#"+voPat.getMachineRecordId()+"#"+voPat.getMachineCode()+"#"+voPat.getMachineResult()+"#"+voPat.getMachineReqId()+"#"+voPat.getTestCode()+"#"+voPat.getParameterCode()+"#"+voPat.getMachineLabSampleNo()+"#"+voPat.getmachineTestParameterParaCount();
						//String labCode=voPat.getLabCode();
							 

						
					%>
					 
					
					<tr>
					<%-- 	<td width="3%" align="left"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<input type="checkbox" class="jpCheckbox" name="chkSamplePatient" value='<%=chkVal%>' onclick="ValidateSameCrNo(this)" >
							</font>
						</td> --%>
						
							<td width="15%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatCrNo()%></font> 
						 
				  		</td>
				  		
				  				<td width="15%" align="left" >
						    <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatName()%></font> 
						 
				  		</td>
				  				</td>
				  		
				  				<td width="15%" align="left" >
						    <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getTestName()%></font> 
						 
				  		</td>
				  		
				  		
						<td width="10%" align="left" >
						    <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineLabSampleNo() %></font> 
						 
				  		</td>
				  		
				  		<td width="15%" align="left"  >
				  		 		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineTestParameterCode() %></font>
						  
				  		
				  		</td>
				  		<td width="15%" align="left">
				  		 <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineTestParameterName()%></font>
						  
				  		</td>
				  		 <td  width="15%" align="left">
				  			<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMachineResult() %></font>
						  
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
				     
				  
				      
				      
				      
				    <logic:present name="<%=InvestigationConfig.MACHINE_RESULT_ENQUIRY_VO %>">
				  	  
				    				  
				    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'id="cancel" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
				    
				    
				    
				    </logic:present>
				    
				    
				    </his:statusTransactionInProcess>
				    </his:ButtonToolBarTag>

	 <html:hidden name="machineEnquiryFB" property="hmode" />	 
	 	 <html:hidden name="machineEnquiryFB" property="showStatus" />	 
	 	 	 	 <html:hidden name="machineEnquiryFB" property="currentPage" />	 
	 	 	 	 	 	 	 	 <html:hidden name="machineEnquiryFB" property="sysDate" />	 
	 	 	 	 
	 	 
	 

		<his:status/>		    
</html:form>
</body>
</html>  