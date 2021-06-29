<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
 <his:javascript src="/registration/js/registration.js" />
<his:javascript src="/inpatient/js/doctorCall.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
 <%@page import="inpatient.InpatientConfig"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script>
/*  function submitForm21(mode)
{
    
  //  alert("submitform...."+mode);
   //  document.getElementsByName("hmode")[0].value=
   //  alert(document.getElementsByName("hmode")[0].value);
     document.getElementsByName("hmode")[0].value=mode;
    // doHomeWork();  
  //   alert("submit form action is"+document.forms[0].action);  
     //alert("hmode "+document.getElementsByName("hmode")[0].value);  
    
	 document.forms[0].submit();
	 
}  */

//Added by Vasu on 28.Aug.2018 for Select Consultant
function validateSave()
{
	var t = document.getElementById("consultantId");
	var selectedText = t.options[t.selectedIndex].text;
	if(selectedText=="All" || selectedText=="Select Value") 
	{
		alert("Please Select Consultant");
		return false;
		}
	return true;
	}
</script>
<his:TitleTag>
	<his:name>
		<bean:message key="doctorCallBook"/>
	</his:name>
</his:TitleTag>



	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<font color="#FF0000">*</font><bean:message key="unit"/>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
			          <div align="left">
			            <html:select name="DoctorCallBookFB" styleId='deptUnitComoboId' property="unitCode" style="width:160;" onkeypress="if(event.keyCOde==13)submitForm21('GETCONSULTANT');hideFirst();" onchange="submitForm21('GETCONSULTANT');hideFirst();" styleClass="registrationCmb" > 
								<html:option value="-1">Select Value</html:option>
								<html:option value="0">All</html:option>
								<html:options collection="<%=InpatientConfig.DEPT_UNIT_LIST %>" labelProperty="label" property="value"  />
							</html:select> 
						</div>				
			        </td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<font color="#FF0000">*</font><bean:message key="con"/>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
			    	<div align="left">
			            <html:select name="DoctorCallBookFB" styleId='consultantId' property="empNo" onchange="submitForm21('GETPHONE')" >
							<html:option value="-1">Select Value</html:option>
								 <%-- <html:option value="0">All</html:option> --%> 
								<logic:present name="<%=InpatientConfig.CONSULTANT_LIST%>" >
								<html:options collection="<%=InpatientConfig.CONSULTANT_LIST %>" labelProperty="label" property="value"  />
							</logic:present>
						</html:select> 
					</div>				
			    </td>
			</tr>
			<tr>
			    <td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="priority"/>
						</font>
					</div>
				</td>
			    <td width="25%" class="tdfont" >
			    	<div align="left">
				    	<html:select name="DoctorCallBookFB" property="callPriority"  style="width:160;" styleClass="registrationCmb" >
						    <html:option value="1">Low</html:option>
							<html:option value="2">Medium</html:option>
							<html:option value="3">High</html:option>
						 </html:select>
				    </div>
			    </td>
			        <td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="phone"/>
						</font>
					</div>
				</td>
			    <td width="25%" class="tdfont">
			    	<div align="left">
				     <logic:present name="<%=InpatientConfig.CONSULTANT_PHONE_LIST%>">
              			<logic:iterate indexId="idx" name="<%=InpatientConfig.CONSULTANT_PHONE_LIST%>" id="phoneList" type="hisglobal.utility.Entry">
              	          <bean:write name="phoneList" property="value" /> &nbsp;         
              			</logic:iterate>
              		</logic:present>
				    </div>
			    </td>  
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<font color="#FF0000">*</font><bean:message key="callRemarks"/>
						</font>
					</div>
				</td>
				<td width="75%" class="tdfont" colspan="3" style="vertical-align: middle;">
					<div align="left">
						&nbsp;<html:textarea name="DoctorCallBookFB" property="callRemarks" rows="2" cols="70" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" style="vertical-align: middle;" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<img class="button" src='<his:path src="/hisglobal/images/AddMacro.png"/>' title="Call Remarks Suggestions" style="cursor: pointer;vertical-align: middle;" tabindex="1" onclick="addProgressNotes(event)" onkeypress="if(event.keyCode==13) addProgressNotes(event)">
					</div>				
			    </td>
			</tr>
		</table>	       
	</his:ContentTag>
	<his:SubTitleTag name="">
		<div align="left">
			<html:checkbox name="DoctorCallBookFB" property="isDocCallByPeon" onclick="togglePeon(this)"></html:checkbox>
			<bean:message key="isCallByPeon" />
		</div>
	</his:SubTitleTag>
	 <div id="showPeon" style="display:none;">	
		<his:ContentTag>	
	       <table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
				    <td width="20%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="status"/>
							</font>
						</div>
					</td>
					<td width="80%" class="tdfont">
						<div align="left">
						    <html:radio name="DoctorCallBookFB" property="docCallByPeonStatus" tabindex="1"  value="<%=InpatientConfig.CALL_COMMUNICATED_TO_DR%>" />Call Communicated to Doctor
							<html:radio name="DoctorCallBookFB" property="docCallByPeonStatus" tabindex="1"  value="<%=InpatientConfig.DOCTOR_NOT_AVAILABLE%>" />Doctor not Available
						</div>
					</td>
				</tr>
				<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<font color="#FF0000">*</font><bean:message key="peon"/>
						</font>
					</div>
				</td>
				<td width="75%" class="tdfont">
			          <div align="left">
			          &nbsp;<html:select name="DoctorCallBookFB" property="peonEmpNo" style="width:160;" styleClass="registrationCmb" > 
								<html:option value="-1">Select Value</html:option>
								  <logic:present name="<%=InpatientConfig.PEON_LIST%>">
								<html:options collection="<%=InpatientConfig.PEON_LIST %>" labelProperty="label" property="value"  />
								</logic:present>
							</html:select> 
						</div>				
			        </td>
			</tr>
				<tr>	
					<td width="25%" class="tdfonthead">
						<div align="right">
						
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remarks"/>
							</font>
						</div>
					</td>
				<td width="25%" class="tdfont" colspan="2">
						<div align="left">
											  
						   &nbsp;<html:textarea name="DoctorCallBookFB" property="docCallByPeonRemarks" rows="2" cols="70" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))">
						   </html:textarea> 
						</div>
					</td>
					 <td width="10%"  class="tdfonthead"></td>
				</tr>
			</table>
		</his:ContentTag>
	</div>		
	<his:SubTitleTag name="">
		<div align="left">
			<html:checkbox name="DoctorCallBookFB" property="isDocCallbyPhone" onclick="togglePhone(this)"></html:checkbox>
			<bean:message key="isCallByPhone" />
		</div>
	</his:SubTitleTag>
	<div id="showPhone" style="display:none;">
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
				    <td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="status"/>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont">
						<div align="left">
						   <html:radio name="DoctorCallBookFB" property="docCallByPhoneStatus" tabindex="1"  value="<%=InpatientConfig.CALL_COMMUNICATED_TO_DR%>" />Call Communicated to Doctor
						   <html:radio name="DoctorCallBookFB" property="docCallByPhoneStatus" tabindex="1"  value="<%=InpatientConfig.DOCTOR_NOT_AVAILABLE%>" />Doctor not Available
						</div>
					</td>
				</tr>
				<tr>	
					 <td width="25%" class="tdfonthead">
						<div align="right">
						
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remarks"/>
							</font>
						</div>
					</td>
				<td width="25%" class="tdfont" colspan="2">
						<div align="left">
						   
						    &nbsp;<html:textarea name="DoctorCallBookFB" property="docCallByPhoneRemarks" rows="2" cols="70" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))">
						   </html:textarea>
						</div>
					</td>
					<td width="10%"  class="tdfonthead"></td>
				</tr>
			</table>	
		</his:ContentTag>
	</div>		
	
<his:ButtonToolBarTag>
		  <%-- <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer onclick ="submitForm21('SAVE')" onkeypress="if(event.keyCode==13) submitForm21('SAVE')" tabindex="1"> --%>
		  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer onclick ="submitFormOnValidate(validateSave(),'SAVE');" onkeypress="if(event.keyCode==13) submitForm21('SAVE')" tabindex="1">
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW')" tabindex="1">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();" tabindex="1">
</his:ButtonToolBarTag>

<html:hidden name="DoctorCallBookFB" property="hmode"/>
