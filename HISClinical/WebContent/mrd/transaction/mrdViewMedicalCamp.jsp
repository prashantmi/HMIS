<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>

<%@page import="mrd.transaction.controller.fb.MrdMedicalCampFB"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date;"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/mrd/js/medicalCamp.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
var selectedMedicalCamp=0;

</script>
<body onload="OnLoadFunction()">
<%String sysdate=(String)WebUTIL.getSysdate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy"); %>
<%int nRow=1; %>
<his:TransactionContainer>
<html:form action="/medicalCamp">
	<his:TitleTag name="Medical Camp">
	</his:TitleTag>
	<%MrdMedicalCampFB fb=(MrdMedicalCampFB)pageContext.findAttribute("mrdMedicalCampFB"); %>
	<his:ContentTag>
		<his:SubTitleTag name="Camp Requisition Detail">
		</his:SubTitleTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="red" size="2">*</font><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campName" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div><html:textarea name="mrdMedicalCampFB"
								property="strCampName" tabindex="1" onkeypress="return validateAlphaNumericOnly(event , this)" disabled="true">
							</html:textarea></div>
							</td>
	
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="red" size="2">*</font><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campReqDate" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div>
							<html:text name="mrdMedicalCampFB" tabindex="1" property="strCampRequisitionDate"   maxlength="2" size="15"  disabled="true"/>
							</div>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="red" size="2">*</font><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campStartDate" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div>
							<html:text name="mrdMedicalCampFB" tabindex="1" property="strCampStartDateTime"   maxlength="2" size="15"  disabled="true"/>															
							</div>
							</td>
														
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campEndDate" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div>
							<html:text name="mrdMedicalCampFB" tabindex="1" property="strCampEndDateTime"   maxlength="2" size="15"  disabled="true"/>						
							</div>
							</td>													
						</tr>
						
						
						
						<tr>
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="red" size="2">*</font><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campStartTime" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div>							
								<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
									<span id='divStarthrcontrol' align="left">	            
						   			<html:text name="mrdMedicalCampFB" tabindex="1" property="campStartHr"  maxlength="2" size="3"  onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)" disabled="true"/>	
						  			<bean:message key="colon"/>
						  			</span>
						 			<span id='divStartMinControl' align="left">         
									<html:text name="mrdMedicalCampFB" tabindex="1" property="campStartMin"   maxlength="2" size="3"  onkeypress="return onkeyTimeMin(this,document.getElementsByName('inTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)" disabled="true"/>				
									<bean:message key="timeFormat"/>
				    				</span>
								</font>							
							</div>
							</td>
														
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campEndTime" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div>							
								<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
									<span id='divEndhrcontrol' align="left">	            
						   			<html:text name="mrdMedicalCampFB" tabindex="1" property="campEndHr"  maxlength="2" size="3"  onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)" disabled="true" />					  			
						  		    <bean:message key="colon"/>				  		    
									</span>
						 			<span id='divEndMinControl' align="left">         
									<html:text name="mrdMedicalCampFB" tabindex="1" property="campEndMin"   maxlength="2" size="3"  onkeypress="return onkeyTimeMin(this,document.getElementsByName('outTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)" disabled="true"/>				
									<bean:message key="timeFormat"/>
				    				</span>
								</font>							
							</div>
							</td>
													
						</tr>
						
						<tr>
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="red" size="2">*</font><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campVenue" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div><html:textarea name="mrdMedicalCampFB"
								property="strCampVenue" tabindex="1" onkeypress="return validateAlphaNumericOnly(event , this)" disabled="true">
							</html:textarea></div>
							</td>
							
							<td class="tdfonthead" width="22%">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="campDescription" /> </font></div>
							</td>
							<td class="tdfont" width="28%">
							<div><html:textarea name="mrdMedicalCampFB"
								property="strCampDescription" tabindex="1" onkeypress="return validateAlphaNumericOnly(event , this)" disabled="true">
							</html:textarea></div>
							</td>						
						</tr>
			</table>
		<his:SubTitleTag name="Camp Team Member Detail">
		</his:SubTitleTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" id="tableParamCampId">
				<tr>
					<td width="30%" class="tdfonthead"><div align="center"><font color="red" size="2">*</font><b><bean:message key="employeename" /></b></div></td>
                  	<td width="30%" class="tdfonthead"><div align="center"><font color="red" size="2">*</font><b><bean:message key="role" /></b></div></td>
                  	<td width="30%" class="tdfonthead"><div align="center"><b><bean:message key="dutyRemarks" /></b></div></td>
                  	<td width="10%" class="tdfonthead"></td>
                </tr>  
				<logic:iterate id="mrdMedicalCampTeamDtlVO" name="<%=MrdConfig.MRD_MEDICAL_CAMP_EMP_VO_LIST%>" indexId="idx" type="hisglobal.vo.MrdMedicalCampTeamDtlVO">
					<tr>
						<td width="30%" class="tdfont" >	
							<div align="center">
							<div align="center">								
								<html:text name="mrdMedicalCampFB" maxlength="30" value="<%=mrdMedicalCampTeamDtlVO.getStrArrEmployeeName() %>"
								property="strArrEmployeeName" tabindex="1" readonly="true" >										
							</html:text>
							<html:hidden name="mrdMedicalCampFB" property="strArrEmployeeId" value="<%=mrdMedicalCampTeamDtlVO.getStrArrEmployeeId() %>"/>
							</div>
							</div>													
						</td>
						<td class="tdfont" width="30%">
							<div align="center"><html:text name="mrdMedicalCampFB" maxlength="30" 
								property="strArrRole" tabindex="1" value="<%=mrdMedicalCampTeamDtlVO.getStrArrRole() %>" onkeypress="return validateAlphaNumericOnly(event , this)" disabled="true">
							</html:text></div>
						</td>
						<td class="tdfont" width="30%">
							<div align="center"><html:text name="mrdMedicalCampFB" maxlength="50"
								property="strArrDutyRemarks" tabindex="1" value="<%=mrdMedicalCampTeamDtlVO.getStrArrDutyRemarks() %>" onkeypress="return validateAlphaNumericOnly(event , this)" disabled="true">
							</html:text>
							</div>
						</td>
						<td width="10%" class="tdfont">
						</td>
					</tr>
				</logic:iterate>
			</table>
		<his:SubTitleTag name="Camp Closure Detail">
		</his:SubTitleTag>
		
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>							
					<td class="tdfonthead" width="50%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="isCampClosed" /> </font></div>
					</td>		
					<td width="50%" class="tdfont" >	
					<b>NO</b>													
					</td>
				</tr>
				<tr>							
					<td class="tdfonthead" width="50%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="noOfPatAttendedCamp" /> </font></div>
					</td>
					<td class="tdfont" width="50%">
					<div><html:text name="mrdMedicalCampFB" maxlength="7" size="8"
						property="strTotalNoofPatientAttended" tabindex="1" onkeypress="return validateNumeric(event)" disabled="true">
					</html:text></div>
					</td>
				</tr>
				<tr>					
					<td class="tdfonthead" width="50%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="noOfMalePatAttendedCamp" /> </font></div>
					</td>
					<td class="tdfont" width="50%">
					<div><html:text name="mrdMedicalCampFB" maxlength="7" size="8"
						property="strTotalMalePatientAttended" tabindex="1" onkeypress="return validateNumeric(event)" disabled="true">
					</html:text></div>
					</td>
				</tr>
				<tr>							
					<td class="tdfonthead" width="50%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="noOfFemalePatAttendedCamp" /> </font></div>
					</td>
					<td class="tdfont" width="50%">
					<div><html:text name="mrdMedicalCampFB" maxlength="7" size="8"
						property="strTotalFemalePatientAttended" tabindex="1" onkeypress="return validateNumeric(event)" disabled="true">
					</html:text></div>
					</td>
				</tr>
				<tr>							
					<td class="tdfonthead" width="50%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="noOfMaleChildPatAttendedCamp" /> </font></div>
					</td>
					<td class="tdfont" width="50%">
					<div><html:text name="mrdMedicalCampFB" maxlength="7" size="8"
						property="strTotalMaleChildPatientAttended" tabindex="1" onkeypress="return validateNumeric(event)" disabled="true">
					</html:text></div>
					</td>
				</tr>
				<tr>							
					<td class="tdfonthead" width="50%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="noOfFemaleChildPatAttendedCamp" /> </font></div>
					</td>
					<td class="tdfont" width="50%"> 
					<div><html:text name="mrdMedicalCampFB" maxlength="7" size="8"
						property="strTotalFemaleChildPatientAttended" tabindex="1" onkeypress="return validateNumeric(event)" disabled="true">
					</html:text></div>
					</td>
				</tr>
				
			</table>
	</his:ContentTag>
	
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">			
		</his:ButtonToolBarTag>

	<input type="hidden" name="numberOfRow" value="<%=(nRow-1)%>" />
	<html:hidden name="mrdMedicalCampFB" property="hmode"/>
	<html:hidden name="mrdMedicalCampFB" property="strCampId"/>
	<html:hidden name="mrdMedicalCampFB" property="strIsCampClosed"/>
	<input type="hidden" name="sysdate" value="<%=sysdate %>">	
</html:form>
<his:status/>
</his:TransactionContainer>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();} %>
