
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page autoFlush="true" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/tab.css"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style type="text/css"> 
@media print { 
		#noprint 
		{
		 display: none; 
		}
		#tabHeader 
		{
			display: none;
		}
		#divCRNoLabel 
		{
			display: block;
		}
		#divEpisodeDateLabel 
		{
			display: block;
		}
		#divPatientNameLabel 
		{
			display: block;
		}
		#divAgeLabel 
		{
			display: block;
		}
		#divGenderLabel 
		{
			display: block;
		}
		#divAreaLabel 
		{
			display: block;
		}
		#divTokenLabel 
		{
			display: block;
		}
		#divConsultantLabel 
		{
			display: block;
		}
		#divDiagnosisLabel 
		{
			display: block;
		}
		#divOccupationLabel 
		{
			display: block;
		}
		#divOPDCardLabel 
		{
			display: block;
		}
		#divOPDCardLogo 
		{
			display: block;
		}
		#divOPDCardHeader 
		{
			display: block;
		}
		#divPatientVisitedLabel 
		{
			display: none;
		}
		#divBlank1 
		{
			display: block;
		}
		#divBlank2 
		{
			display: block;
		}
		
}
 td.TdBorder{
 		 border:2px solid black;
		}
		#divFooter 
		{
            width:779px; 
            margin-top:20px;
        }
 
 </style>
<script>
function validateDepartments()
{
	var len;
	var isValid = true;
	//int count=0;
	count=0;
	if(!(document.getElementsByName('choice')[2].checked))
	{
	//	alert("before assignment");
	len=document.getElementsByName("departmentToGenerateDupCard").length;
	//alert("before for");
				for(i=0;i<len;i++)
				{
				if(document.getElementsByName("departmentToGenerateDupCard")[i].checked){
				count++;
					}
				}
	
    if(count==0){
    	isValid = false;
    alert("Select department");
    	}
    else if(document.getElementsByName("duplicateRenewRemarks")[0].value=="")
    {
	    alert("Enter the Reason");
	    document.getElementsByName("duplicateRenewRemarks")[0].focus();
	    isValid = false; 
	}	
	else
		isValid = true;

	}
return isValid;
}

function printPage() 
{
var frameElement = parent.document.getElementById("frmMain"); 
var win = frameElement.contentWindow ;
win.print();
}

function callThisOnload(){
	focusCrNo();
	//alert("onload")
	//alert(document.getElementsByName('print')[0].value)
	if(document.getElementsByName('print')[0].value=='1')
	{
		//document.getElementById('divCardPrint').style.display="block"
		printPage();
		document.getElementsByName('print')[0].value='0'
		//document.getElementById('divCardPrint').style.display="none"
	}
	//document.getElementsByName("departmentCode")[0].focus()
	//var patCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	//showState();
	//showLocation();
	//document.getElementsByName("departmentCode")[0].focus;
	//sendData();
	//checkPatientCategory();
	//enableSpouseField();
	if(document.getElementsByName("errorMessage")[0].value!=""){
		alert(document.getElementsByName("errorMessage")[0].value)
		document.getElementsByName("errorMessage")[0].value=""
	}
	
}


function displayAmount(obj,amount){
// alert(obj.value);

if(obj.checked==true)
{
//document.getElementsByName('duplicateRenewCost')[0].value=parseInt(document.getElementsByName('duplicateRenewCost')[0].value)+parseInt(amount);
document.getElementsByName('duplicateRenewCost')[0].value=parseInt(amount);
}
else
document.getElementsByName('duplicateRenewCost')[0].value=parseInt(document.getElementsByName('duplicateRenewCost')[0].value)-parseInt(amount);
}
  




function submitByChoice()
{
	submitForm('CHOICE');
}
</script>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*" %>
<div id="noprint">
<%
boolean varIsNewStatus=false;
String varStatus="";

String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
<his:statusNew>
<%varIsNewStatus=true;
varStatus="New";%>	

</his:statusNew>



	<his:TitleTag name="Duplicate Card Generation">

		
	<b><font size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			
	</font></b>



	</his:TitleTag>
	
	

	<his:InputCrNoTag name="DuplicateCardGenerationFB">
	</his:InputCrNoTag>
	
	
<his:statusTransactionInProcess>

<input type='hidden' name='crNoToRetrieve'/>
<bean:define id="status_object" name="<%=Config.STATUS_OBJECT%>" type="hisglobal.presentation.Status"/>



<jsp:include page="/registration/patientDetail.cnt" flush="true"/>		


<his:ContentTag>
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="25%" class="tdfonthead">
				<div align="left">
					<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif"> 
						<b>
							<bean:message key="cardprint" /> 
						</b> 
					</font>
				</div>
			</td>
			<td class="tdfont">
				<div width="75%" align="right">		
					<html:radio name="DuplicateCardGenerationFB" property="choice" tabindex="1" value="<%=RegistrationConfig.DUPLICATE_CARD%>" onclick="submitByChoice()" />
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b>	
							<bean:message key="duplicate" />
						</b>	 
					</font> 
					
					<html:radio name="DuplicateCardGenerationFB" property="choice" tabindex="1" value="<%=RegistrationConfig.REPRINT_REGISTRATION%>" onclick="submitByChoice()" />
					
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b>
							<bean:message key="reprintNew" />
						</b>	 
					</font>
					
					<html:radio name="DuplicateCardGenerationFB" property="choice" tabindex="1" value="<%=RegistrationConfig.PRINT_BACK_PAGE%>" onclick="submitByChoice()" />
					
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b>
							<bean:message key="BackPagePrint" />
						</b>	 
					</font>
				<!-- 	
					<html:radio name="DuplicateCardGenerationFB" property="choice" tabindex="1" value="<%=RegistrationConfig.REPRINT_OLD_VISIT_SLIP%>" onclick="submitByChoice()" />
					
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b>
							<bean:message key="reprintOpd" />
						</b>	 
					</font>
				-->
					
					<%if(Config.CARD_PRINTING_REQUIRED_FOR_RENEWAL.equals(Config.PRINT_REQUIRED_TRUE)) 
					{%> 
				
				<!-- 	
					<html:radio name="DuplicateCardGenerationFB" property="choice" tabindex="1" value="<%=RegistrationConfig.REPRINT_RENEWAL%>" onclick="submitByChoice()" />
					
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b>
							<bean:message key="reprintRenewal" />
						</b>	 
					</font>
				-->		
					<%} %>
				</div>
			</td>
			
		</tr>
		
	</table>
</his:ContentTag>


<his:SubTitleTag>
<his:name>
<bean:message key="episode"/> <bean:message key="detail"/>
</his:name>
</his:SubTitleTag>


<his:ContentTag>
<table width="100%" border="0" cellpadding="0" cellspacing="1">	
<tr><%varStatus="InProcess";%>
<%String billAmount = (String) session.getAttribute(RegistrationConfig.BILL_AMOUNT);%>
							<td width="8%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
								<div align="center">	
									<b>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="select"/>
										
										</font>
									</b>
								</div>	
							</td>
						    
						    <td width="17%" align="center" Class = "tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">    
								    <b>
									    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										    <bean:message key="department"/>
									    </font>
								    </b>
								</div>    
						    </td>
						    
						    <td width="25%" align="center" Class = "tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">    
								    <b>
									    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										    <bean:message key="unit"/>
									    </font>
							    	</b>
							    </div>	
						    </td>
						    
						    <td width="25%" align="center" Class = "tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">   
								    <b>
									    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										    <bean:message key="lastVisitdate"/>
									    </font>
							    	</b>
							    </div>	
						    </td>
						   
						    <td width="25%" align="center" Class = "tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">    
								    <b>
									    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										    <bean:message key="unitStatus"/>
									    </font>
							    	</b>
							    </div>	
						    </td>
						</tr>
					</table>
				
					
				
					<logic:equal name="DuplicateCardGenerationFB" property="choice" value="<%=RegistrationConfig.DUPLICATE_CARD %>">
					<logic:present name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_DUPLICATE%>">
					    <logic:iterate id="duplicateEpisode" name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_DUPLICATE%>" type="hisglobal.vo.EpisodeVO">
						   
						    <table width="100%" border="0" cellspacing="1" cellpadding="0">
								<tr>
								    <td width="8%" class="tdfont" >
									    <div align="center">
										    <input type="radio" name="departmentToGenerateDupCard" tabindex="1" value='<bean:write name="duplicateEpisode" property="departmentUnitCode" />' onclick="displayAmount(this,'<%=billAmount%>');"/>
						    			</div>
						    		</td>
						    
						    		<td width="17%" Class = "tdfont" >
									    <div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="duplicateEpisode" property="department"/>
											</font>
										</div>
									</td>
							
									<td width="25%" Class = "tdfont">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="duplicateEpisode" property="departmentUnit"/>
											</font>
										</div>
									</td>
							
									<td width="25%" Class = "tdfont">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="duplicateEpisode" property="episodeDate"/>
											</font>
										</div>
									</td> 
							
									<td width="25%" Class = "tdfont">
										<div align="center">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="duplicateEpisode" property="deptUnitIsOnDuty"/>
											</font>
										</div>
									</td>
								</tr>
							</table>
						</logic:iterate>
						
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="25%" Class = "tdfonthead">
								<div align="right">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*
									</font>
									<b>
										<font size="2">
											<bean:message key="reason"/>
										</font>
									</b>
								</div>
							</td>
							<td  width="25%" Class = "tdfont">
								<div align="left">
									<html:textarea name="DuplicateCardGenerationFB" tabindex="1" property="duplicateRenewRemarks" onkeypress="return CheckMaxLength(event,this,150,1)" cols="25" rows="1">
									</html:textarea>
								</div>
							</td>
							 
							<td width="25%" Class = "tdfonthead" >
							
								<div align="right" id="labelRegFee">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*
									</font>
									<b>
										<font size="2">
											<bean:message key="printFee"/>
										</font>
									</b>
								</div>
								
								</td>
							<td width="25%" Class = "tdfont" >
							
								<div align="left" id="controlRegFee">
									<html:text styleClass="textbox" name="DuplicateCardGenerationFB" property="duplicateRenewCost" tabindex="1" value="0" readonly="true"/>
								</div>
							
								</td>
							
							
						</tr>
						
						</table>
						
					</logic:present>
					
					</logic:equal>
					
							
					<logic:equal name="DuplicateCardGenerationFB" property="choice" value="<%=RegistrationConfig.REPRINT_REGISTRATION %>">
					<logic:present name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_REGISTRATION %>">
						
							<logic:iterate id="reprintCardEpisode" name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_REGISTRATION %>" type="hisglobal.vo.EpisodeVO">
								<table width="100%" border="0" cellspacing="1" cellpadding="0">
									<tr>
									    <td width="8%" class="tdfont" >
										    <div align="center">
										    	<input type="radio" name="departmentToGenerateDupCard" tabindex="1" value='<bean:write name="reprintCardEpisode" property="departmentUnitCode" />' />
									    	</div>
								    	</td>
						    
									    <td width="17%" Class = "tdfont">
										    <div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="reprintCardEpisode" property="department"/>
												</font>
											</div>
										</td>
							
										<td width="25%" Class = "tdfont">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="reprintCardEpisode" property="departmentUnit"/>
												</font>
											</div>
										</td>
							
										<td width="25%" Class = "tdfont">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="reprintCardEpisode" property="episodeDate"/>
												</font>
											</div>
										</td> 
							
										<td width="25%" Class = "tdfont">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="reprintCardEpisode" property="deptUnitIsOnDuty"/>
												</font>
											</div>
										</td>
									</tr>
								</table>
							</logic:iterate>
					
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="25%" Class = "tdfonthead">
								<div align="right">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*
									</font>
									<b>
										<font size="2">
											<bean:message key="reason"/>
										</font>
									</b>
								</div>
							</td>
							<td  width="25%" Class = "tdfont">
								<div align="left">
									<html:textarea styleClass="textarea2" name="DuplicateCardGenerationFB" tabindex="1" property="duplicateRenewRemarks" onkeypress="return CheckMaxLength(event,this,150,1)">
									</html:textarea>
								</div>
							</td>
							 
							
							
							
						</tr>
						
						</table>
					
					</logic:present>
					</logic:equal>	
	
			<logic:equal name="DuplicateCardGenerationFB" property="choice" value="<%=RegistrationConfig.REPRINT_OLD_VISIT_SLIP %>">
					<logic:present name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_OLD_PATIENT %>">
						
							<logic:iterate id="reprintOldEpisode" name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_OLD_PATIENT%>" type="hisglobal.vo.EpisodeVO">
								<table width="100%" border="0" cellspacing="1" cellpadding="0">
									<tr>
									    <td width="8%" class="tdfont" >
										    <div align="center">
										    	<input type="checkbox" name="departmentToGenerateDupCard" tabindex="1" value='<bean:write name="reprintOldEpisode" property="departmentUnitCode" />' />
									    	</div>
								    	</td>
						    
									    <td width="17%" Class = "tdfont">
										    <div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="reprintOldEpisode" property="department"/>
												</font>
											</div>
										</td>
							
										<td width="25%" Class = "tdfont">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="reprintOldEpisode" property="departmentUnit"/>
												</font>
											</div>
										</td>
							
										<td width="25%" Class = "tdfont">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="reprintOldEpisode" property="episodeDate"/>
												</font>
											</div>
										</td> 
							
										<td width="25%" Class = "tdfont">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="reprintOldEpisode" property="deptUnitIsOnDuty"/>
												</font>
											</div>
										</td>
									</tr>
								</table>
							</logic:iterate>
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="25%" Class = "tdfonthead">
								<div align="right">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*
									</font>
									<b>
										<font size="2">
											<bean:message key="reason"/>
										</font>
									</b>
								</div>
							</td>
							<td  width="25%" Class = "tdfont">
								<div align="left">
									<html:textarea styleClass="textarea2" name="DuplicateCardGenerationFB" tabindex="1" property="duplicateRenewRemarks" onkeypress="return CheckMaxLength(event,this,150,1)">
									</html:textarea>
								</div>
							</td>
							 
							
							
						</tr>
						
						</table>
					
					</logic:present>
			</logic:equal>	
			
			<logic:equal name="DuplicateCardGenerationFB" property="choice" value="<%=RegistrationConfig.REPRINT_RENEWAL %>">
					<logic:present name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_RENEWAL %>">
						
							<logic:iterate id="reprintRenewalEpisode" name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_RENEWAL%>" type="hisglobal.vo.EpisodeVO">
								<table width="100%" border="0" cellspacing="1" cellpadding="0">
									<tr>
									    <td width="8%" class="tdfont" >
										    <div align="center">
										    	<input type="checkbox" name="departmentToGenerateDupCard" tabindex="1" value='<bean:write name="reprintRenewalEpisode" property="departmentUnitCode" />' />
									    	</div>
								    	</td>
						    
									    <td width="17%" Class = "tdfont">
										    <div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="reprintRenewalEpisode" property="department"/>
												</font>
											</div>
										</td>
							
										<td width="25%" Class = "tdfont">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="reprintRenewalEpisode" property="departmentUnit"/>
												</font>
											</div>
										</td>
							
										<td width="25%" Class = "tdfont">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="reprintRenewalEpisode" property="episodeDate"/>
												</font>
											</div>
										</td> 
							
										<td width="25%" Class = "tdfont">
											<div align="center">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="reprintRenewalEpisode" property="deptUnitIsOnDuty"/>
												</font>
											</div>
										</td>
									</tr>
								</table>
							</logic:iterate>
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="25%" Class = "tdfonthead">
								<div align="right">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*
									</font>
									<b>
										<font size="2">
											<bean:message key="reason"/>
										</font>
									</b>
								</div>
							</td>
							<td  width="25%" Class = "tdfont">
								<div align="left">
									<html:textarea styleClass="textarea2" name="DuplicateCardGenerationFB" tabindex="1" property="duplicateRenewRemarks" onkeypress="return CheckMaxLength(event,this,150,1)">
									</html:textarea>
								</div>
							</td>
							 
							
						</tr>
						
						</table>
					
					</logic:present>
					
			</logic:equal>	
					

</his:ContentTag>





</his:statusTransactionInProcess>
<his:ButtonToolBarTag>
	<%if(varStatus.equals("InProcess")){%>
        

          <div align="center">
          <his:statusRecordFound>
	          <img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>' style=cursor:pointer tabindex="1" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateDepartments(),'SAVE');" onclick =  "submitFormOnValidate(validateDepartments(),'SAVE');" >
	        </his:statusRecordFound>
			  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick ="submitForm('NEW');">
	          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer   tabindex="1" onclick ="submitForm('GETPATDTL')" onkeypress="if(event.keyCode==13) submitForm('GETPATDTL');">
          </div>
        
            <%} else{ %>                   
           
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
         
        
          	  <%} %>
</his:ButtonToolBarTag>
</div>
<his:status/>

<input type='hidden' name='crNoToRetrieve'/>   
<input type='hidden' name='patCrNo'/>    
<input type="hidden" name="hmode" value="unspecified"/>
<html:hidden name="DuplicateCardGenerationFB" property="print" />
