<!-- 
 
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: CHANDAN GUPTA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : ORGANISM ANTIBIOTIC MAPPING MST PROCESS
 ## Purpose						        : 
 ## Date of Creation					: 1/08/2016
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 

  -->
 <%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.vo.InvValueAuditVO"%>
<%@page
	import="new_investigation.transactions.controller.fb.InvValueAuditFB"%>
 <%@page import="new_investigation.vo.InvValueAuditVO"%>
	
<%@page import="new_investigation.vo.LabTestVO"%>
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
<%@page import="new_investigation.vo.invOrganicAntibioticMappingMstVO"%>
<%@page import="new_investigation.transactions.controller.fb.InvValueAuditFB"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<his:javascript src="/new_investigation/js/invOrganismAntibioticMappingMst.js" />

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
	 
	 
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="new_investigation.vo.template.ResultEntryVO"%>
<%@page import="new_investigation.vo.template.ResultEntryVOGroupByValidatedBy"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="new_investigation.transactions.controller.fb.InvValueAuditFB"%>

 
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>

<html>

<script type="text/javascript">


function getarea() {
	if (document.forms[0].organicName.value == "-1") {
		document.forms[0].hmode.value = "ADD";
		document.forms[0].submit();
	} else {
		document.forms[0].hmode.value = "GETLABCANNED";
		document.forms[0].submit();
	}
}


</script>

<body>
<html:form action="/masters/organicantibioticprocess">
<his:TransactionContainer>
	<html:hidden name="invOrganicAntibioticMappingMstFB" property="hmode" />
				<html:hidden name="invOrganicAntibioticMappingMstFB" property="numberOfRow" />
	
<html:hidden name="invOrganicAntibioticMappingMstFB" property="selectedChk" />
<his:SubTitleTag name="Details"></
  			</his:SubTitleTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			    
			     
			     <logic:notEqual name="invOrganicAntibioticMappingMstFB" property="hmode"
						value="MODIFY">
						<tr>
			    <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							Organic	Name
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_ORGANIC_NAME_COMBO %>">
								<div align="left">

									<html:select name="invOrganicAntibioticMappingMstFB" style="width:58%"
										property="organicName" tabindex="1" onchange="getarea();">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_ORGANIC_NAME_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present>
				 </td>
				 </tr>
				 </logic:notEqual>
				 

			     <logic:equal name="invOrganicAntibioticMappingMstFB" property="hmode" value="MODIFY">
			    <tr>
			    <td width="25%" class="tdfont">
			        <div align="right">
			        
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							Organic	Name
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <logic:present
								name="<%=InvestigationConfig.LIST_ORGANIC_NAME_COMBO %>">
								<div align="left">

									<html:select name="invOrganicAntibioticMappingMstFB" style="width:58%"
										property="organicName" tabindex="1"  disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_ORGANIC_NAME_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
									<html:hidden  name="invOrganicAntibioticMappingMstFB" property="organicName"></html:hidden>
								</div>
							</logic:present>
				 </td>
				 </tr>
				 </logic:equal>
				 				 
				 
			     
			    <%--  <tr>
			    <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Antibiotic Name
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_ANTIBIOTIC_NAME_COMBO%>">
								<div align="left">

									<html:select name="invOrganicAntibioticMappingMstFB" style="width:58%"
										property="antibioticName" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_ANTIBIOTIC_NAME_COMBO%>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present>
					</td>
			      </tr> --%>
			        
			      </table>
			      
			      <table width='100%' border="0" cellspacing="1" cellpadding="0">

					<tr>

						<td width='40%' class="tdfonthead">
							<div align="center">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b>
									UnMapped Antibiotic Name</b>
								</font>
							</div>
						</td>

						<td width='20%' class="tdfonthead"></td>

						<td width='40%' class="tdfonthead">
							<div align="center">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b>Mapped Antibiotic Name</b>
								</font>
							</div>
						</td>
					</tr>

					<!--left list values -->



					<tr>
						<td width='40%' class="tdfont">

							<div align="center">
								<logic:notEqual name="invOrganicAntibioticMappingMstFB" property="hmode"
									value="GETLABCANNED">
									<logic:notEqual name="invOrganicAntibioticMappingMstFB" property="hmode"
										value="MODIFY">
										<html:select name="invOrganicAntibioticMappingMstFB"
											property="antibioticName" size="5" tabindex="1"
											style="width:200px">
										</html:select>
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal name="invOrganicAntibioticMappingMstFB" property="hmode"
									value="GETLABCANNED">
									<logic:present name="<%=InvestigationConfig.LIST_CANNED_COMBO%>">
										<html:select name="invOrganicAntibioticMappingMstFB" property="unmappedList"
											size="5" tabindex="1" style="width:200px">
											<html:options
												collection="<%=InvestigationConfig.LIST_CANNED_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
								<logic:equal name="invOrganicAntibioticMappingMstFB" property="hmode"
									value="MODIFY">
									<logic:present name="<%=InvestigationConfig.LIST_CANNED_COMBO%>">
										<html:select name="invOrganicAntibioticMappingMstFB" property="unmappedList"
											size="5" tabindex="1" style="width:200px">
											<html:options
												collection="<%=InvestigationConfig.LIST_CANNED_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
							</div>
						</td>

						<!-- images -->

						<td width="20%" class="tdfont">
							<div align="center">
								<img src="/HISInvestigationG5/hisglobal/images/forward3.gif" class="link"
									onClick='moveRightSingle();' /> &nbsp;&nbsp;
								<img src="/HISInvestigationG5/hisglobal/images/forwardward.gif"   class="link"  onClick='moveRightAll();'/>
								<br><br>
								<img src="/HISInvestigationG5/hisglobal/images/back3.gif"   class="link"  onClick='moveLeftSingle();'/> 		&nbsp;&nbsp;
								<img src="/HISInvestigationG5/hisglobal/images/backward.gif"   class="link"  onClick='moveLeftAll();'/>
							</div>
						</td>

						<!--right list values -->


						<td width='40%' class="tdfont">

							<div align="center">
								<logic:notEqual name="invOrganicAntibioticMappingMstFB" property="hmode"
									value="GETLABCANNED">
									<logic:notEqual name="invOrganicAntibioticMappingMstFB" property="hmode"
										value="MODIFY">

										<html:select name="invOrganicAntibioticMappingMstFB"
											property="antibioticName" size="5" tabindex="1"
											style="width:200px">
										</html:select>
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal name="invOrganicAntibioticMappingMstFB" property="hmode"
									value="GETLABCANNED">
										<html:select name="invOrganicAntibioticMappingMstFB" property="mappedList"
											size="5" tabindex="1" multiple="true" style="width:200px">
											<html:options
												collection="<%=InvestigationConfig.LIST_SELECTED_CANNED_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									
								</logic:equal>
								<logic:equal name="invOrganicAntibioticMappingMstFB" property="hmode"
									value="MODIFY">
									
										<html:select name="invOrganicAntibioticMappingMstFB" property="mappedList"
											size="5" tabindex="1" multiple="true" style="width:200px">
											<html:options
												collection="<%=InvestigationConfig.LIST_SELECTED_CANNED_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									
								</logic:equal>
							</div>
						</td>



					</tr>
				</table>
				
				
				<!-- start for test seq	 -->
			
				<table width="100%" id="seqNumber" border="0" cellspacing="1" cellpadding="0" >

	
					<tr>
					<td class="HEADER" width='100%' colspan="6" style="font-size: 14px; font-weight: bold;">
					 <b>Antibiotic Sequence Number</b>
					</td>
					</tr>
					
<%-----------------------logic to display the saved records!! --%>


<logic:notEqual name="invOrganicAntibioticMappingMstFB"
						property="hmode" value="SAVE">
				<logic:present
					name="<%=InvestigationConfig.LIST_TEST_SEQ_NO_ANTIBOTIC %>">
				
						<%
     
  	 List<invOrganicAntibioticMappingMstVO> lstSelectedTests=(List<invOrganicAntibioticMappingMstVO>)session.getAttribute(InvestigationConfig.LIST_TEST_SEQ_NO_ANTIBOTIC);
  	 
 	if(lstSelectedTests!=null && lstSelectedTests.size()>0 )
 	{
 		int lstSize=lstSelectedTests.size();
 	
 		
					%>	
					
				

	
					
				
					<%
						for(int k=0;k<lstSize;k++)
				 		{
							invOrganicAntibioticMappingMstVO volabCanned=lstSelectedTests.get(k);
				 	 	    
				 	 	    %>
				 	 	    
				 	 	    
				 	 	    <tr id='<%=volabCanned.getAntibioticNameCode()%>'>

							<td class="tdfont" width='50%'>
								<div align="center">
									<%=volabCanned.getAntibioticName()%>
								</div>
							</td>


							<td class="tdfont"  width='50%'>
								<div align="center">
								
								<html:text name="invOrganicAntibioticMappingMstFB" property="antibioticSeqNo"
										maxlength="10" size="60" 
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1" style="width:200px;" value="<%=volabCanned.getAntibioticSeqNo()%>">
								</html:text> 
								
								
									
													
													
								</div>
							</td>
						</tr>
							
							<%
															}
														%>
					
		
					<%} %>
		

				</logic:present>
				
				</logic:notEqual>
</table>
				
				
			<!-- end for test seq -->
			
			      
			      		<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="invOrganicAntibioticMappingMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="invOrganicAntibioticMappingMstFB" property="hmode" value="VIEW">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-sv.png"/>'
								style="cursor: pointer"
								onkeypress="if(event.keyCode==13) validatesave();"
								onclick="validatesave();" tabindex="1">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-clr.png"/>'
								style="cursor: pointer" onclick="clearAddForm()"
								onkeypress="if(event.keyCode==13) clearAddForm()" tabindex="1">
						</logic:notEqual>
					</logic:notEqual> <logic:equal name="invOrganicAntibioticMappingMstFB" property="hmode" value="MODIFY">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-mo.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) validatemodify()"
							onclick="validatemodify()" tabindex="1">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-clr.png"/>'
							style="cursor: pointer" onkeypress="if(event.keyCode==13) clearMODIFYForm()" 
							onclick="clearMODIFYForm()"
							 tabindex="1">
					</logic:equal> <img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
					style="cursor: pointer" onclick="submitForm('CANCEL')"
					onkeypress="if(event.keyCode==13) submitForm('CANCEL')"
					tabindex="1">
				</span>
			</his:ButtonToolBarTag>
</his:TransactionContainer>
<his:status/>
</html:form>
</body>

</html>