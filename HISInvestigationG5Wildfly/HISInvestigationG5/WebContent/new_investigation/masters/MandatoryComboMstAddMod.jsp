<!-- 
 /*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	MANDATORY COMBO MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:
 ## Purpose								:	This is used to define the mandatory combo values for mandatory Code
 											whose Mandatory Type is Combo i.e. 2 
 ## Date of Creation					: 	13-Feb-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*********************************************************************************************************************/

-->



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.MandatoryComboMasterVO"%>
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
<his:javascript src="/new_investigation/js/MandatoryComboMstAddMod.js" />
<body>
	<script type="text/javascript">

function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].mandatoryName,"mandatoryValue"))
     {
         valid=true ;
     }
  return valid;
}

  
function displaydata()
{
	
	var mandatoryCode=document.getElementsByName("mandatoryCode")[0].value;
	
	if(mandatoryCode!=-1)
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

	document.getElementsByName('mandatoryCode')[0].value="-1";
    document.getElementsByName('mandatoryValue')[0].value="";
    
	var tableObj=document.getElementById('tableComponentDetailId');
  	var numRows=document.forms[0].numberOfRow.value;

  	var i=0;
  	
  	for(i=0;i<numRows;i++)
  		{
  		document.getElementsByName("mandatoryValue")[i].value="";
  		}
	
  	submitPage('ADD');
}
 
  
</script>

		<html:form action="/masters/MandatoryComboMstACTION">
		<html:hidden name="MandatoryComboMstFB" property="hmode" />
		<html:hidden name="MandatoryComboMstFB" property="mandatorySeq" />
		<html:hidden name="MandatoryComboMstFB" property="selectedChk" />
		

		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>

		<logic:equal name="MandatoryComboMstFB" property="hmode" value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>
<his:TransactionContainer>
		<logic:equal name="MandatoryComboMstFB" property="hmode" value="DISPLAYDATA">
			<% this.readOnly=true; %>
		</logic:equal>
		

		<logic:equal name="MandatoryComboMstFB" property="hmode" value="ADD">

			<his:TitleTag name="Mandatory Combo Master">
			<%-- 	<his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>



				<table width="100%" border="0" cellspacing="1" cellpadding="0">


					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="MandatoryCombo" />&nbsp;</b>
								</font>
							</div>
						</td>

						<%-------------------- mandatory combo values -----------------------%>


						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_MAND_COMBO %>">
								<div align="left">

									<html:select name="MandatoryComboMstFB"
										 property="mandatoryCode" tabindex="1" onchange="displaydata()" style="width:58%;">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_MAND_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>

						
					
					</tr>
	</table>
					
					
					
					
					<%-----------------------logic to display the saved records!! --%>

				<logic:present
					name="<%=InvestigationConfig.DISPLAY_DATA_MAND_CODE %>">
				
						<%
     
  	 List<MandatoryComboMasterVO> mandatoryComboMasterVOLst=(List<MandatoryComboMasterVO>)session.getAttribute(InvestigationConfig.DISPLAY_DATA_MAND_CODE);
  	 
 	if(mandatoryComboMasterVOLst!=null && mandatoryComboMasterVOLst.size()>0 )
 	{
 		int manComboLstSize=mandatoryComboMasterVOLst.size();
 		int count=1;
 		
					%>	
					<table width="100%">
					
					<tr>
					<td class="HEADER" width='100%' colspan="6" style="font-size: 14px; font-weight: bold;">
					 <b>Present Mandatory Values</b>
					</td>
					</tr>
					
					<%
						for(int k=0;k<manComboLstSize;k++)
				 		{
				 			MandatoryComboMasterVO voMandatoryCombo=mandatoryComboMasterVOLst.get(k);
				 	 	    int tempVal=count%5;
				 	 	    if(tempVal==1)
				 	 	    {%><tr>
				 	 	    <%}%>
				 				<td class="tdfont" >
								<div align="center">
									<%=count+". "+voMandatoryCombo.getMandatoryValue() %>
								</div>
								</td>
							<%if(tempVal==0)
				 	 	    {%></tr>
							<%} count++;
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
					 <b>No Mandatory Values Exist. Enter New Values</b>
					</td>
					</tr>
					<tr></tr>
					<tr></tr>
					
					</table>	<%				
 		}
 		 
 		
 		%>

				</logic:present>
				
				
				
				<table width="100%" id="tableComponentDetailId" cellspacing="1"
			cellpadding="0">
			
			<tr>
					<td class="HEADER" width='100%' colspan="6" style="font-size: 14px; font-weight: bold;">
					 <b>Enter New Mandatory Values</b>
					</td>
					</tr>
						
			
					<tr>
						<td width="50%" class="tdfonthead" style="font-size: 14px; font-weight: bold;">
							<div align="right">
								<font color="RED" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> * </font> Mandatory Value&nbsp;
								
							</div>
						</td>
						<td width="35%" class="tdfont">
							<div align="left">
								<html:text name="MandatoryComboMstFB" property="mandatoryValue"
							maxlength="25" size="30" readonly="<%=this.readOnly=false %>"
					onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
							tabindex="1">
								</html:text>
								<html:hidden name="MandatoryComboMstFB" property="storeValue" />
								
							</div>
							</td>
							
							

<%------------------------------------------------------------------------------------------------- --%>							
								<td>
							<div align="center" width="35%" class="tdfont">
								<img src='/HISInvestigationG5/hisglobal/images/plus.gif'
									alt="Add Mandatory Combo" title="Add Mandatory Combo"
									name="addRow"
									onkeypress="if(event.keyCode==13)AddRowToTable() ;"
									onclick="return AddRowToTable();">

							</div>
						</td>
							
												
					</tr>
			</table>

			</his:ContentTag>


		</logic:equal>



		<logic:equal name="MandatoryComboMstFB" property="hmode"
			value="MODIFY">


			<his:ContentTag>


				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="MandatoryCombo" />&nbsp;</b>
								</font>
							</div>
						</td>
											
						
						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_MAND_COMBO %>">
								<div align="left">

									<html:select name="MandatoryComboMstFB"
										property="mandatoryCode" tabindex="1" disabled="true" >
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_MAND_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
									<html:hidden name="MandatoryComboMstFB" property="mandatoryCode" />
									</div>
							</logic:present></td>
					</tr>
					
				 	<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
									key="MandatoryValue" />&nbsp;</b>
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MandatoryComboMstFB" property="storeValue"
							maxlength="25" size="30" readonly="<%=this.readOnly=false %>"
					onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
							tabindex="1">
								</html:text>
								<html:hidden name="MandatoryComboMstFB" property="mandatoryValue"/>
								
							</div>
						</td>
							
				</tr>
				
				</table>
				</his:ContentTag>
			
		
					</logic:equal>
	
		
		
					
			

			<his:ButtonToolBarTag>
				<span id="saveDiv"> 
				
				<logic:notEqual
					name="MandatoryComboMstFB" property="hmode" value="MODIFY">
						<logic:notEqual name="MandatoryComboMstFB" property="hmode"
						value="VIEW">
							<img class="button"
							src='<his:path src="/hisglobal/images/btn-sv.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
							onclick="finalSubmit('SAVE')" tabindex="1">
							<img class="button"
							src='<his:path src="/hisglobal/images/btn-clr.png"/>'
							style="cursor: pointer" onclick="clearaddForm()"
							onkeypress="if(event.keyCode==13) clearaddForm()" tabindex="1">
						</logic:notEqual>
					</logic:notEqual> 
					
					<logic:equal name="MandatoryComboMstFB" property="hmode"
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
			



<html:hidden property="numberOfRow" />
</his:TransactionContainer>
	</html:form>
</body>
</html>
