


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="new_investigation.vo.MachineTestParameterMasterVO"%>
<%@page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/TestParaComboMstAddMod.js" />
<body>
	<script type="text/javascript">
	



	function validateFinalSubmit(){
	     
	     // These All Fields are Mandatory
	   var numberOfRow=document.getElementsByName("noOfParameter")[0].value;
	     
		if(document.getElementsByName("machineCode")[0].value=="-1")
		{
			alert("Select Machine Name ");
			document.getElementsByName("machineCode")[0].focus();
			return false;                          
		}
		
		if(document.getElementsByName("testCode")[0].value=="-1")
		{
			alert("Select Test Name ");
			document.getElementsByName("testCode")[0].focus();
			return false;                         
		}
		
		for(var i=0;i<document.getElementsByName("machineParameterCode").length;i++)
			{
			
			var k=0;
			for(k=i+1;k<document.getElementsByName("machineParameterCode").length;k++)
				if(document.getElementsByName("machineParameterCode")[i].value==document.getElementsByName("machineParameterCode")[k].value)
				{	alert("Duplicate Code Exists. Enter different code.");
					document.getElementsByName("machineParameterCode")[k].focus();
					return false;
				}
			
			
			
			}
		
		
		
		
		
/* 		alert("validate final submit");
		
		var tableObj=document.getElementById('tableComponentDetailId');
	  	//var numRows=document.getElementsByName("numberOfRow")[0].value;

	  	var i=0;
	  	
	  	for(i=0;i<numberOfRow;i++)
	  		{
	  		
	  		if(document.getElementsByName("machineParameterCode")[0].value=="")
	  		{
	  			alert("Enter Machine Parameter Code");
	  			document.getElementsByName("machineParameterCode")[0].focus();
	  			

	  			return false;
	  		}
	  		 */
	  		
	  		
	  	
	  		
		return true;
	 } 		
	function finalSubmit(mode)
	{
		if (!validateFinalSubmit()) return;
		submitPage(mode);
	}
	function submitPage(mode)
	{
		document.forms[0].hmode.value=mode;
		document.forms[0].submit();
	}

function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].testparaValue,"testparaValue"))
     {
         valid=true ;
     }
  return valid;
}


function getparameter() {
	//calll new mode in action GETPARAMETER

	var testCode = document.getElementsByName("testCode")[0].value;

	if (testCode != -1) {
		submitPage('GETPARAMETER');
	} else {
		submitPage('ADD');
	}
}

  
function displaydata()
{
	
	var testCode=document.getElementsByName("testCode")[0].value;
	
	if(testCode!=-1)
	{
		submitPage('DISPLAYDATA');
	}
	else
	{
		submitPage('ADD');
	} 
	

}
 
function clearaddForm()
{
	
	document.getElementsByName('machineCode')[0].value="-1";
	document.getElementsByName('testCode')[0].value="-1";
	//document.getElementsByName('testparaValue')[0].value="";
	document.getElementById('tableComponentDetailId').style.display="none";
	
	submitPage('ADD');

}
  
</script>

		<html:form action="/masters/MachineTestParameterMstACTION">
		<html:hidden name="MachineTestParameterMstFB" property="hmode" />
		<html:hidden name="MachineTestParameterMstFB" property="selectedChk" />
		
		
		
		

			<his:ContentTag>

		<logic:notEqual name="MachineTestParameterMstFB" property="hmode" value="MODIFY">


				<table width="100%" border="0" cellspacing="1" cellpadding="0">

						<%-------------------- machine name combo values -----------------------%>

					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="ParameterMachineCombo" />&nbsp;</b>
								</font>
							</div>
						</td>



						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.PARAMETER_MACHINE_COMBO %>">
								<div align="left">

									<html:select name="MachineTestParameterMstFB"
										property="machineCode" tabindex="1" style="width:150px;">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.PARAMETER_MACHINE_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>

					
					</tr>
					
				<%-------------------- test name combo values -----------------------%>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="ParameterTestCombo" />&nbsp;</b>
								</font>
							</div>
						</td>



						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.PARAMETER_TEST_COMBO %>">
								<div align="left">

									<html:select name="MachineTestParameterMstFB"
										property="testCode" tabindex="1" onchange="getparameter()" style="width:150px;">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.PARAMETER_TEST_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present>
						</td>				
					</tr>
		</table>
	<%-----------------------logic to display the saved records!! --%>

				<logic:present
					name="<%=InvestigationConfig.DISPLAY_DATA_MACHINE_TEST_PARAM_ADDED%>">
				
						<%
     
  	 List<MachineTestParameterMasterVO> testparaComboMasterVOLst=(List<MachineTestParameterMasterVO>)session.getAttribute(InvestigationConfig.DISPLAY_DATA_MACHINE_TEST_PARAM_ADDED);
  	 
 	if(testparaComboMasterVOLst!=null && testparaComboMasterVOLst.size()>0 )
 	{
 		int testparaComboLstSize=testparaComboMasterVOLst.size();
 		int count=1;
 		
					%>	
					<table width="100%">
					
					<tr>
					<td class="HEADER" width='100%' colspan="6" style="font-size: 14px; font-weight: bold;">
					 <b>PRESENT MACHINE PARAMETER VALUES</b>
					</td>
					</tr>
					<tr>
					<td class="HEADER" width='35%'  style="font-size: 14px; font-weight: bold;">
					 <b>Parameter Name</b>
					</td>
					<td class="HEADER" width='35%'  style="font-size: 14px; font-weight: bold;">
					 <b>Machine Parameter Code</b>
					</td>
					<td class="HEADER" width='35%'  style="font-size: 14px; font-weight: bold;">
					 <b>Machine Parameter Name</b>
					</td>
					</tr>
					
					<%
						for(int k=0;k<testparaComboLstSize;k++)
				 		{
							MachineTestParameterMasterVO voTestParaCombo=testparaComboMasterVOLst.get(k);
				 	 	  %><tr>
				 	 	    
				 				<td class="tdfont" width='35%'  style="font-size: 14px;" >
								<div align="left">
									<%=voTestParaCombo.getParameterName() %>
								</div>
								</td>
								<td class="tdfont" width='35%'  style="font-size: 14px;" >
								<div align="left">
									<%=voTestParaCombo.getMachineParameterCode() %>
								</div>
								</td>
								<td class="tdfont" width='35%'  style="font-size: 14px;" >
								<div align="left">
								<%
									if(voTestParaCombo.getMachineParameterName()==null)
										
								{%>
										NA
										<%}else{ %>
									<%=voTestParaCombo.getMachineParameterName()%>
								</div><% } %>
								</td>
							</tr>
							<%
							}%>
						</table>
		<%				
 		}
 		else 
 		{
 		%>
 		
 		<table width="100%">
					
					<tr>
					<td class="HEADER" width='100%' colspan="6" style="font-size: 14px; font-weight: bold;">
					 <b>No Test Parameter Values Exist. Enter New Values</b>
					</td>
					</tr>
					<tr></tr>
					<tr></tr>
					
					</table>	<%				
 		}
 		 
 		
 		%>

				</logic:present>
				
	
	
	
	<%-----------------------logic to display the  records to be saved!! --%>

				<logic:present
					name="<%=InvestigationConfig.DISPLAY_DATA_MACHINE_TEST_PARAM %>">
				
						<%
     
  	 List<MachineTestParameterMasterVO> machineTestParamMst_listVO=(List<MachineTestParameterMasterVO>)session.getAttribute(InvestigationConfig.DISPLAY_DATA_MACHINE_TEST_PARAM);
  	 
 	if(machineTestParamMst_listVO!=null && machineTestParamMst_listVO.size()>0 )
 	{
 		int machineTestParamMstLstSize=machineTestParamMst_listVO.size();
 		int count=1;
 		
					%>	
					
					
					
					
					 <input type="hidden" value="<%=machineTestParamMst_listVO.size()%>" name="noOfParameter" id="params"/>
					
					
					
					<table width="100%" id="tableComponentDetailId">
					<tr>
					<td class="HEADER" width='35%'  style="font-size: 14px; font-weight: bold;">
					 <b>PARAMETER NAME</b>
					</td>
					<td class="HEADER" width='35%'  style="font-size: 14px; font-weight: bold;">
					 <b>Machine Parameter Code</b>
					</td>
					<td class="HEADER" width='35%'  style="font-size: 14px; font-weight: bold;">
					 <b>Machine Parameter Name</b>
					</td>
					</tr>
					<%
						for(int k=0;k<machineTestParamMstLstSize;k++)
				 		{
							MachineTestParameterMasterVO machineTestParaMstVO=machineTestParamMst_listVO.get(k);
							%>
					<tr>
					<td class="tdfont" >
					<div align="center">
						<%=machineTestParaMstVO.getParameterName() %>
						<input type="hidden" value="<%=machineTestParaMstVO.getParameterCode()%>" name="parameterCode" />
						
						
					</div>
					</td>
													
						<td width="35%" class="tdfont">
							<div align="left">
							<input type="text" name="machineParameterCode" tabindex="1" maxlength="20" >
							</div>
							</td>
					<td width="35%" class="tdfont">
							<div align="left">
							<input type="text" name="machineParameterName" tabindex="1" maxlength="20" >
							</div>
							</td>
							</tr>
				 		<%} %>
						</table>
		<%				
 		}
 		else 
 		{
 		%>
 		
 		<table width="100%">
					
					<tr>
					<td class="HEADER" width='100%' colspan="6" style="font-size: 14px; font-weight: bold;">
					 <b>No Parameter Values Exist</b>
					</td>
					</tr>
					<tr></tr>
					<tr></tr>
					
					</table>	<%				
 		}
 		 
 		
 		%>

				</logic:present>
				</logic:notEqual>
				
						<logic:equal name="MachineTestParameterMstFB" property="hmode" value="MODIFY">
				
				
				
				<table width="100%" border="0" cellspacing="1" cellpadding="0">

						<%-------------------- machine name combo values -----------------------%>

					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="ParameterMachineCombo" />&nbsp;</b>
								</font>
							</div>
						</td>



						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.PARAMETER_MACHINE_COMBO %>">
								<div align="left">

									<html:select name="MachineTestParameterMstFB"
										property="machineCode" tabindex="1" disabled="true" style="width:150px;">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.PARAMETER_MACHINE_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
									  <html:hidden name="MachineTestParameterMstFB" property="machineCode" />
								</div>
							</logic:present></td>

					
					</tr>
					
				<%-------------------- test name combo values -----------------------%>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="ParameterTestCombo" />&nbsp;</b>
								</font>
							</div>
						</td>



						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.PARAMETER_TEST_COMBO %>">
								<div align="left">

									<html:select name="MachineTestParameterMstFB"
										property="testCode" tabindex="1" disabled="true" onchange="getparameter()" style="width:150px;">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.PARAMETER_TEST_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
									  <html:hidden name="MachineTestParameterMstFB" property="testCode" />
								</div>
							</logic:present>
						</td>				
					</tr>
		</table>
	
	
	<%-----------------------logic to display the saved records!! --%>

				<logic:present
					name="<%=InvestigationConfig.DISPLAY_DATA_MACHINE_TEST_PARAM %>">
				
						<%
     
  	 List<MachineTestParameterMasterVO> machineTestParamMst_listVO=(List<MachineTestParameterMasterVO>)session.getAttribute(InvestigationConfig.DISPLAY_DATA_MACHINE_TEST_PARAM);
  	 
 	if(machineTestParamMst_listVO!=null && machineTestParamMst_listVO.size()>0 )
 	{
 		int machineTestParamMstLstSize=machineTestParamMst_listVO.size();
 		int count=1;
 		
					%>	
					<table width="100%" >
					
					<tr>
					<td class="HEADER" width='100%' colspan="6" style="font-size: 14px; font-weight: bold;">
					 <b>PARAMETER DETAILS</b>
					 <input type="hidden" value="<%=machineTestParamMst_listVO.size()%>" name="noOfParameter" id="params"/>
					</td>
					</tr>
					</table>
					<table width="100%" id="tableComponentDetailId">
					<tr>
					<td class="HEADER" width='35%'  style="font-size: 14px; font-weight: bold;">
					 <b>PARAMETER NAME</b>
					</td>
					<td class="HEADER" width='35%'  style="font-size: 14px; font-weight: bold;">
					 <b>PARAMETER CODE</b>
					</td>
					<td class="HEADER" width='35%'  style="font-size: 14px; font-weight: bold;">
					 <b>MACHINE NAME</b>
					</td>
					</tr>
					<%
						for(int k=0;k<machineTestParamMstLstSize;k++)
				 		{
							MachineTestParameterMasterVO machineTestParaMstVO=machineTestParamMst_listVO.get(k);
							%>
					<tr>
					<td class="tdfont" >
					<div align="center">
						<%=machineTestParaMstVO.getParameterName() %>
						<input type="hidden" value="<%=machineTestParaMstVO.getParameterCode()%>" name="parameterCode" />
						
						
					</div>
					</td>
													
						<td width="35%" class="tdfont">
							<div align="left">
							<input type="text" name="machineParameterCode" value="<%=machineTestParaMstVO.getMachineParameterCode()%>" tabindex="1" maxlength="20" >
							</div>
							</td>
					<td width="35%" class="tdfont">
							<div align="left">
							<input type="text" name="machineParameterName" value="<%=machineTestParaMstVO.getMachineParameterName()%>" tabindex="1" maxlength="20" >
							</div>
							</td>
							</tr>
				 		<%} %>
						</table>
		<%				
 		}
 		else 
 		{
 		%>
 		
 		<table width="100%">
					
					<tr>
					<td class="HEADER" width='100%' colspan="6" style="font-size: 14px; font-weight: bold;">
					 <b>No Parameter Values Exist</b>
					</td>
					</tr>
					<tr></tr>
					<tr></tr>
					
					</table>	<%				
 		}
 		 
 		
 		%>

				</logic:present>
				</logic:equal>
					
	
	
			</his:ContentTag>		
		<his:ButtonToolBarTag>
				<span id="saveDiv"> 
				
				<logic:notEqual
					name="MachineTestParameterMstFB" property="hmode" value="MODIFY">
						<logic:notEqual name="MachineTestParameterMstFB" property="hmode"
						value="VIEW">
							<img class="button"
							src='<his:path src="/hisglobal/images/btn-sv.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
							onclick="finalSubmit('SAVE')" tabindex="1">
		<img class="button"	src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearaddForm()"  tabindex="1">
						</logic:notEqual>
					</logic:notEqual> 
					
					<logic:equal name="MachineTestParameterMstFB" property="hmode"
					value="MODIFY">
						<img class="button"
						src='<his:path src="/hisglobal/images/btn-mo.png"/>'
						style="cursor: pointer"
						onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')"
						onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
						<img class="button"
						src='<his:path src="/hisglobal/images/btn-clr.png"/>'
						style="cursor: pointer" onclick="clearForm()"
						onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
					</logic:equal> 
					
					<img class="button"
				src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
				style="cursor: pointer" onclick="submitForm('CANCEL')"
				onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
				
				</span>
			</his:ButtonToolBarTag>
			<his:status />			
					
					
					
	</html:form>
</body>
</html>
