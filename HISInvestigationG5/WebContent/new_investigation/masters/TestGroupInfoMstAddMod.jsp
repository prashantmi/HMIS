<!-- 
 /****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	TEST GROUP INFO MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	Mapping master to map test to Test Groups
 ## Date of Creation					: 	16-Mar-4515
 ## Modification Log					:				
 ##		Modify Date						: 30-NOV-2015
 ##		Reason	(CR/PRS)				: Addition of Printing Template
 ##		Modify By						: Akshita Topre
/**************************************************************************************************************/ 
-->



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.TestGroupInfoMasterVO"%>
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
<his:javascript src="/new_investigation/js/TestGroupInfoMstAddMod.js" />
<body >

	<body onload="checktypevalue()">

	<script type="text/javascript">

	
function checktypevalue(){
	
	var displaydiv=document.getElementById('div1');
	var displaydivcol=document.getElementById('div2');
	
	if(document.getElementsByName("isDynamicTemplate")[0].checked==true)	
	{
		displaydiv.style.display="";
		displaydivcol.style.display="";                     
	}
	
	
}


function cleardownvalues()
	{

	  	document.getElementsByName('labCode')[0].value="-1";
	  	document.getElementsByName("mappedList")[0].length=0;
	  	document.getElementsByName("unmappedList")[0].length=0;
	    
	}
		
function clearaddForm()
{
	var isDynamicTemplate = document.getElementsByName('isDynamicTemplate');
	var isDynamicTemplate_value;
	for(var i = 0; i < isDynamicTemplate.length; i++){
	    if(isDynamicTemplate[i].checked){
	    	isDynamicTemplate_value = isDynamicTemplate[i].value;
	    }
	}
//	if(isDynamicTemplate_value=="0")
//	{
//		document.getElementById("div1").style.display="";
//		document.getElementById("div2").style.display="";	
//		}
///	else
	//	{
		document.getElementById("div1").style.display="none";
		document.getElementById("div2").style.display="none";
 //		}
	

	document.getElementsByName('groupCode')[0].value="-1";
  	document.getElementsByName('labCode')[0].value="-1";
  	document.getElementsByName("mappedList")[0].length=0;
  	document.getElementsByName("unmappedList")[0].length=0;
  	document.getElementsByName('printingTemplateCode')[0].value="-1";
  	document.getElementsByName('isDynamicTemplate')[1].checked="true";
  	document.getElementsByName('isDynamicResult')[1].checked="true";
    
}
	
function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].collectionareaName,"collectionareaName"))
     {
         valid=true ;
     }
  return valid;
}

function gettest()
{
	if(document.forms[0].labCode.value=="-1" )
	{
	document.forms[0].hmode.value = "ADD";
	document.forms[0].submit();
	}
	else
	{
	document.forms[0].hmode.value = "GETTEST";
	document.forms[0].submit();
	}
}


function showHideDiv()
{
var isDynamicTemplate = document.getElementsByName('isDynamicTemplate');
var isDynamicTemplate_value;
for(var i = 0; i < isDynamicTemplate.length; i++){
    if(isDynamicTemplate[i].checked){
    	isDynamicTemplate_value = isDynamicTemplate[i].value;
    }
}
if(isDynamicTemplate_value=="1")
	{
	document.getElementById("div1").style.display="";
	document.getElementById("div2").style.display="";	
	}
else
	{
	document.getElementById("div1").style.display="none";
	document.getElementById("div2").style.display="none";
	}
}



</script>

		<html:form action="/masters/TestGroupInfoMstACTION">
		<html:hidden name="TestGroupInfoMstFB" property="hmode" />
		<html:hidden name="TestGroupInfoMstFB" property="selectedChk" />
		
		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>

		<logic:equal name="TestGroupInfoMstFB" property="hmode" value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>
		

											

	
	<his:TransactionContainer>
			<his:TitleTag name="Global Test Group Information Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>



				<table width='100%' border="0" cellspacing="1" cellpadding="0">
<logic:notEqual name="TestGroupInfoMstFB" property="hmode"
									value="MODIFY">
				<!-- Group Combo -->
										
					<tr>
						
						<td width='50%' class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="GroupName" />&nbsp;</b>
								</font>
							</div>
						</td>

						
						<td width='50%' class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_GROUP_COMBO %>">
								<div align="left">

									<html:select name="TestGroupInfoMstFB"
										property="groupCode" tabindex="1" style="width:150px;" onchange="cleardownvalues();">

										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_GROUP_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present>
						</td>
					</tr>
										<!-- Lab Combo -->
										
					<tr>
						
						<td width='50%' class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="LaboratoryName" />&nbsp;</b>
								</font>
							</div>
						</td>

						
						<td width='50%' class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_LAB_COMBO %>">
								<div align="left">

									<html:select name="TestGroupInfoMstFB"
										property="labCode" tabindex="1" style="width:150px;" onchange="gettest();">

										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_LAB_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present>
						</td>
					</tr>
					
					
					<tr>
			     <td width="50%" class="tdfonthead">
			     	<div align="right">
			     	
			     		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			     				<b><bean:message key="IsDynamicPrinting"/>&nbsp;</b>
			     		</font>
			     	</div>
			     </td>
			     <td width="50%" class="tdfont">
			  		<div align="left">
			  		
			  		<html:radio name="TestGroupInfoMstFB" tabindex="1" property="isDynamicTemplate" value="1" onclick="showHideDiv();"></html:radio>
			  		<bean:message key="IsDynamicPrintingYes"/>&nbsp;
			  		<html:radio name="TestGroupInfoMstFB" tabindex="1" property="isDynamicTemplate" value="0" onclick="showHideDiv();"></html:radio>
			  		<bean:message key="IsDynamicPrintingNo"/>&nbsp;
			  		
			  		</div>
			  	</td>
			  	</tr>
			  	
					<tr id="div1"  style="display:none">
			     <td width="50%" class="tdfonthead">
			     	<div align="right">
			     	
			     		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			     				<b><bean:message key="IsDynamicResult"/>&nbsp;</b>
			     		</font>
			     	</div>
			     </td>
			     <td width="50%" class="tdfont">
			  		<div align="left">
			  		
			  		<html:radio name="TestGroupInfoMstFB" tabindex="1" property="isDynamicResult" value="1"></html:radio>
			  		<bean:message key="IsDynamicResultYes"/>&nbsp;
			  		<html:radio name="TestGroupInfoMstFB" tabindex="1" property="isDynamicResult" value="0"></html:radio>
			  		<bean:message key="IsDynamicResultNo"/>&nbsp;
			  		
			  		</div>
			  	</td>
			  	</tr>
			  	
			  	
			  	<tr id="div2"  style="display:none">
						
						<td width='50%' class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font>  <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="PrintingTemplateName" />&nbsp;</b>
								</font>
							</div>
						</td>

						
						<td width='50%' class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_PRINTING_TEMPLATE_COMBO %>">
								<div align="left">
									
									<html:select name="TestGroupInfoMstFB"
										property="printingTemplateCode" tabindex="1" style="width:150px;">

										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_PRINTING_TEMPLATE_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
				
								</div>
							</logic:present>
							<logic:notPresent name="<%=InvestigationConfig.LIST_PRINTING_TEMPLATE_COMBO %>">
							<html:select name="TestGroupInfoMstFB"
										property="printingTemplateCode" tabindex="1" style="width:150px;">

										<html:option value="-1">Select Value</html:option>
										
										</html:select>
									</logic:notPresent>
						</td>
					</tr>
					</logic:notEqual>
					
					<logic:equal name="TestGroupInfoMstFB" property="hmode"
									value="MODIFY">
									
									
									<!-- Group Combo -->
										
					<tr>
						
						<td width='50%' class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="GroupName" />&nbsp;</b>
								</font>
							</div>
						</td>

						
						<td width='50%' class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_GROUP_COMBO %>">
								<div align="left">

									<html:select name="TestGroupInfoMstFB"
										property="groupCode" tabindex="1" style="width:150px;" disabled="true">

										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_GROUP_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
									<html:hidden name="TestGroupInfoMstFB" property="groupCode" />
								</div>
							</logic:present>
						</td>
					</tr>
					
					
					
										<!-- Lab Combo -->
										
					<tr>
						
						<td width='50%' class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="LaboratoryName" />&nbsp;</b>
								</font>
							</div>
						</td>

						
						<td width='50%' class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_LAB_COMBO %>">
								<div align="left">

									<html:select name="TestGroupInfoMstFB"
										property="labCode" tabindex="1" style="width:150px;" disabled="true">

										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_LAB_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
									<html:hidden name="TestGroupInfoMstFB" property="labCode" />
									
								</div>
							</logic:present>
						</td>
					</tr>
					
					<tr>
			     <td width="50%" class="tdfonthead">
			     	<div align="right">
			     	
			     		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			     				<b><bean:message key="IsDynamicPrinting"/>&nbsp;</b>
			     		</font>
			     	</div>
			     </td>
			     <td width="50%" class="tdfont">
			  		<div align="left">
			  		
			  		<html:radio name="TestGroupInfoMstFB" tabindex="1" property="isDynamicTemplate" value="1" onclick="showHideDiv();"></html:radio>
			  		<bean:message key="IsDynamicPrintingYes"/>&nbsp;
			  		<html:radio name="TestGroupInfoMstFB" tabindex="1" property="isDynamicTemplate" value="0" onclick="showHideDiv();"></html:radio>
			  		<bean:message key="IsDynamicPrintingNo"/>&nbsp;
			  		
			  		</div>
			  	</td>
			  	</tr>
			  	
					<tr id="div1"  style="display:none">
			     <td width="50%" class="tdfonthead">
			     	<div align="right">
			     	
			     		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			     				<b><bean:message key="IsDynamicResult"/>&nbsp;</b>
			     		</font>
			     	</div>
			     </td>
			     <td width="50%" class="tdfont">
			  		<div align="left">
			  		
			  		<html:radio name="TestGroupInfoMstFB" tabindex="1" property="isDynamicResult" value="1"></html:radio>
			  		<bean:message key="IsDynamicResultYes"/>&nbsp;
			  		<html:radio name="TestGroupInfoMstFB" tabindex="1" property="isDynamicResult" value="0"></html:radio>
			  		<bean:message key="IsDynamicResultNo"/>&nbsp;
			  		
			  		</div>
			  	</td>
			  	</tr>
			  	
			  	
			  	<tr id="div2"  style="display:none">
						
						<td width='50%' class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font>  <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="PrintingTemplateName" />&nbsp;</b>
								</font>
							</div>
						</td>

						
						<td width='50%' class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_PRINTING_TEMPLATE_COMBO %>">
								<div align="left">
									
									<html:select name="TestGroupInfoMstFB"
										property="printingTemplateCode" tabindex="1" style="width:150px;">

										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_PRINTING_TEMPLATE_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
				
								</div>
							</logic:present>
							<logic:notPresent name="<%=InvestigationConfig.LIST_PRINTING_TEMPLATE_COMBO %>">
							<html:select name="TestGroupInfoMstFB"
										property="printingTemplateCode" tabindex="1" style="width:150px;">

										<html:option value="-1">Select Value</html:option>
										
										</html:select>
									</logic:notPresent>
						</td>
					</tr>
					</logic:equal>
					</table>
										<!-- list name -->
				<%-- 	<logic:present name="not required">		 --%>		
						
			<table width='100%' border="0" cellspacing="1" cellpadding="0">
					
					<tr>
						
						<td width='45%' class="tdfonthead">
							<div align="center">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="testName" />&nbsp;</b>
								</font>
							</div>
						</td>	
						
						<td width='10%' class="tdfonthead"></td>
										
						<td width='45%' class="tdfonthead">
							<div align="center">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="SelectedTestName" />&nbsp;</b>
								</font>
							</div>
						</td>					
						</tr>
										
								<!--left list values -->
								
					
					
					<tr>
						<td width='45%' class="tdfont">

							<div align="center">
								<logic:notEqual name="TestGroupInfoMstFB" property="hmode"
									value="GETTEST">
<logic:notEqual name="TestGroupInfoMstFB" property="hmode"
									value="MODIFY">
									<html:select name="TestGroupInfoMstFB"
										property="unmappedList" size="5" tabindex="1"
										style="width:200px">
									</html:select>
								</logic:notEqual></logic:notEqual>
								<logic:equal name="TestGroupInfoMstFB" property="hmode"
									value="GETTEST">
									<logic:present name="<%=InvestigationConfig.LIST_TEST_COMBO%>">
										<html:select name="TestGroupInfoMstFB"
											property="unmappedList" size="5" tabindex="1" multiple="true"
											style="width:200px">
											<html:options
												collection="<%=InvestigationConfig.LIST_TEST_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
								<logic:equal name="TestGroupInfoMstFB" property="hmode"
									value="MODIFY">
									<logic:present name="<%=InvestigationConfig.LIST_TEST_COMBO%>">
										<html:select name="TestGroupInfoMstFB"
											property="unmappedList" size="5" tabindex="1" multiple="true"
											style="width:200px">
											<html:options
												collection="<%=InvestigationConfig.LIST_TEST_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
							</div>
						</td>

						<!-- images -->
						
						<td width="10%"  class="tdfont">
							<div align="center">
								<img src="/HISInvestigationG5/hisglobal/images/forward3.gif"   class="link"  onClick='moveRightSingle();'/>  	&nbsp;&nbsp;
								<img src="/HISInvestigationG5/hisglobal/images/forwardward.gif"   class="link"  onClick='moveRightAll();'/>
								<br><br>
								<img src="/HISInvestigationG5/hisglobal/images/back3.gif"   class="link"  onClick='moveLeftSingle();'/> 		&nbsp;&nbsp;
								<img src="/HISInvestigationG5/hisglobal/images/backward.gif"   class="link"  onClick='moveLeftAll();'/>
							</div>
						</td>
						
						<!--right list values -->
						
						
								<td width='45%' class="tdfont">

							<div align="center">
								<logic:notEqual name="TestGroupInfoMstFB" property="hmode"
									value="GETTEST">
<logic:notEqual name="TestGroupInfoMstFB" property="hmode"
									value="MODIFY">
									<html:select name="TestGroupInfoMstFB"
										property="mappedList" size="5" tabindex="1"
										style="width:200px">
									</html:select>
								</logic:notEqual></logic:notEqual>
								<logic:equal name="TestGroupInfoMstFB" property="hmode"
									value="GETTEST">
									<logic:present name="<%=InvestigationConfig.LIST_SELECTED_COMBO%>">
										<html:select name="TestGroupInfoMstFB"
											property="mappedList" size="5" tabindex="1" multiple="true"
											style="width:200px">
											<html:options
												collection="<%=InvestigationConfig.LIST_SELECTED_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
								<logic:equal name="TestGroupInfoMstFB" property="hmode"
									value="MODIFY">
									<logic:present name="<%=InvestigationConfig.LIST_SELECTED_COMBO%>">
										<html:select name="TestGroupInfoMstFB"
											property="mappedList" size="5" tabindex="1" multiple="true"
											style="width:200px">
											<html:options
												collection="<%=InvestigationConfig.LIST_SELECTED_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
							</div>
						</td>
						
					
					
					</tr>
				</table>
										
			</his:ContentTag>
					
			

			<his:ButtonToolBarTag>
				<span id="saveDiv"> 
				
				<logic:notEqual
					name="TestGroupInfoMstFB" property="hmode" value="MODIFY">
						<logic:notEqual name="TestGroupInfoMstFB" property="hmode"
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
					
					<logic:equal name="TestGroupInfoMstFB" property="hmode"
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
			
			<logic:present name="<%=InvestigationConfig.LIST_TEST_COMBO %>"> 
        <logic:empty name="<%=InvestigationConfig.LIST_TEST_COMBO  %>"> 
            <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
                <b>No New Tests Found </b> 
            </font>     
        </logic:empty>
        </logic:present>
			<his:status />
			
	</his:TransactionContainer>




	</html:form>
</body>
</html>
			  		