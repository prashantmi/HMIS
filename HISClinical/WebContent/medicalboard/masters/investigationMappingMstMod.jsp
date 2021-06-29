<!-- 
/**
 * @author CDAC
 */
--> 

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>

<%@page import="medicalboard.MedicalBoardConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>


<script>
function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Seats
	// 2 -> Units
	
	if(listNo=="1")
	{
		source  = document.forms[0].labTestList;
		target = document.forms[0].selLabTestList;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function moveRightAll(listNo)
{
	var source;
	var target;
	
	if(listNo=="1")
	{
		source  = document.forms[0].labTestList;
		target = document.forms[0].selLabTestList;	
	}
	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
		
		targetlen = target.length;							
		target.length = (targetlen+1);				
			
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}
	deleteThis(target,source);
}

function deleteThis(source,target)
{	
	var tarlen = target.length;
	var srclen = source.length;

	var a1 = new Array(tarlen);
	var a2 = new Array(tarlen);
	var a3 = new Array(srclen);

	for(var i=0;i<tarlen;i++)
	{		
		a1[i]= target.options[i].value;
		a2[i]= target.options[i].text;	
	}
	for( i=0;i<srclen;i++)		
		a3[i]= source.options[i].value;

	target.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;

	for(i=0;i<tarlen;i++)
	{		
		flag = 0;
		for(k=0;k<srclen;k++)
			if(a1[i]==a3[k])
				flag = 1;					
		if(flag==0)
		{
			target.length = (counter+1);
			target.options[counter].value = a1[i];
			target.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}

function moveLeftSingle(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].labTestList;
		source = document.forms[0].selLabTestList;	
	}
	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function moveLeftAll(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].labTestList;
		source = document.forms[0].selLabTestList;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;
	
	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
	
		targetlen = target.length;							
		target.length = (targetlen+1);				
		
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}

	deleteThis(target,source);
}

function FormValidate(mode)
{
	if(document.forms[0].selmenuIdLst && document.forms[0].selmenuIdLst.options.length==0)
	{
		alert("You Can't Modify This Record");
		document.forms[0].menuIdLst.focus();
		return false;
	}
	else
	{
		submitPage(mode);
	}
	
}

function MoveToSelected()
{
	
	 //Unselect Remaining Symptoms
	if(document.forms[0].labTestList)
	{	
		for(var i=0;i<document.forms[0].labTestList.options.length;i++)
			document.forms[0].labTestList.options[i].selected=false;
	}
	
	
	// Select All Symptoms in Selected
	if(document.forms[0].selLabTestList)
	{
		for(var i=0;i<document.forms[0].selLabTestList.options.length;i++)
			document.forms[0].selLabTestList.options[i].selected=true;
	}
		
}

function submitPage(mode)
{
	//MoveToSelected();	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateAddForm(mode)
{
	if(document.getElementsByName("labTestCode")[0].value=="-1")
	{
		alert("Please select lab test");
		document.getElementsByName("labTestCode")[0].focus();
		return false;
	}
	if(document.getElementsByName("isOptional")[0].value=="-1")
	{
		alert("Please select is optional");
		document.getElementsByName("isOptional")[0].focus();
		return false;
	}
	
	submitPage(mode);
}

function removeListData(index,mode)
{
	document.getElementsByName("index")[0].value=index;
	submitPage(mode);
}

function validateModifyForm(mode)
{
	var len=document.getElementsByName("indexArray").length;
	if(len==0)
	{
		alert("Please add Lab Test");
		document.getElementById('addButton').focus();
		return false;
	}
	submitPage(mode);
}

</script>

	<body>
		<html:form action="/masters/InvetigationMappingMstACTION">
		<his:TransactionContainer>
		
		<his:TitleTag name="Investigation Mapping Master">
		<his:insertDateTag/>
		</his:TitleTag>
		<logic:equal value="MODIFY" name="InvestigationMappingMstFB" property="hmode">
	 	<table width="100%" border="0" cellspacing="1" cellpadding="0">
			  <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="certificatetype"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
				      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						     &nbsp;&nbsp;<b><bean:write name="InvestigationMappingMstFB"  property="certificateTypeName" /> </b>
						</font> 
				   </div>
			     </td>
			     </tr>
			 </table> 
			  <table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
			  		<td width="42%"  class="tdfonthead">
						<div align="center">	           
							<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="labTest"/></b>
							</font>
						</div>
			  		</td>
			  		<td width="42%" class="tdfonthead">
				        <div align="center">
				             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font> 
							 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="isOptional"/>&nbsp;
							 </font>
					     </div>
			      	</td>
			      	<td width="6%" class="tdfonthead">
			      	</td>
			 	</tr>
			 	<tr>
			  	 	<td class="tdfont" width="42%">
						<div align="center">
							<html:select name="InvestigationMappingMstFB" property="labTestCode" style="width:140;" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MedicalBoardConfig.ALL_LAB_TEST_LIST%>">
							  	<html:options collection="<%=MedicalBoardConfig.ALL_LAB_TEST_LIST%>" property="value" labelProperty="label" />
							  	</logic:present>
							</html:select>
						</div>
					</td>
					<td class="tdfont" width="42%">
						<div align="center">
			          		<html:select name="InvestigationMappingMstFB" property="isOptional" style="width:140;" tabindex="1">
								<html:option value="-1">Select Value</html:option>
							     <html:option value="<%=MedicalBoardConfig.MEDICAL_BOARD_INVESTIGATION_IS_OPTIONAL_NO %>">No</html:option>
							     <html:option value="<%=MedicalBoardConfig.MEDICAL_BOARD_INVESTIGATION_IS_OPTIONAL_YES %>">Yes</html:option>
							</html:select> 
				   		</div>
					</td>
					<td class="tdfont">
						<div align="center">
							<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.png"/>' alt="Add Value" title="Add Value" onclick="validateAddForm('MODIFYADDLIST');" tabindex="1"/>
						</div>
					</td>
				</tr>		
			 </table>
			 <logic:present name="<%=MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST %>">
			 <logic:notEmpty name="<%=MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST %>">
			 	<table width="100%"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="42%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<bean:message key="labTest"/>
							</div>
						</td>
					
						<td width="42%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<bean:message key="isOptional"/>
							</div>
						</td>
						<td width="6%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" >
						</td>
					</tr>
				<logic:iterate indexId="idx" id="investMappingVO" name="<%=MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST %>" type="hisglobal.vo.MbInvestigationMappingMstVO">
					<%String index=idx.toString(); %>
					<tr>
						<td width="42%" class="tdfont">
							<div align="center">
								<bean:write name="investMappingVO" property="labTestName" />
								<html:hidden name="InvestigationMappingMstFB" property="indexArray" value="<%=index%>"/>
							</div>
						</td>
						<td width="42%" class="tdfont">
							<div align="center">
								<bean:write name="investMappingVO" property="isOptionalName" />
							</div>
						</td>
						<td width="6%" class="tdfont">
							<div align="center">
								<img class="button" id="minusButton"  style="cursor:pointer" src='<his:path src="/hisglobal/images/Minus.png"/>' alt="Remove Value" title="Remove Value" onclick="removeListData(<%=index%>,'MODIFYREMOVEDATA');" tabindex="1"/>
							</div>
					 	</td>
					</tr>
				
				</logic:iterate>
				</table>	
				 
			 </logic:notEmpty>
			 </logic:present>
	</logic:equal>
	
	<logic:equal value="VIEW" name="InvestigationMappingMstFB" property="hmode">
	<his:ContentTag>
	 	<table width="100%" border="0" cellspacing="1" cellpadding="0">
			  <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="certificatetype"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
				      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						     &nbsp;&nbsp;<b><bean:write name="InvestigationMappingMstFB"  property="certificateTypeName" /> </b>
						</font> 
				   </div>
			     </td>
			     </tr>
			 </table> 
			 <logic:present name="<%=MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST %>">
			 <logic:notEmpty name="<%=MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST %>">
			 	<table width="100%"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="42%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<bean:message key="labTest"/>
							</div>
						</td>
					
						<td width="42%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<bean:message key="isOptional"/>
							</div>
						</td>
						
					</tr>
				<logic:iterate indexId="idx" id="investMappingVO" name="<%=MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST %>" type="hisglobal.vo.MbInvestigationMappingMstVO">
					<%String index=idx.toString(); %>
					<tr>
						<td width="42%" class="tdfont">
							<div align="center">
								<bean:write name="investMappingVO" property="labTestName" />
								<html:hidden name="InvestigationMappingMstFB" property="indexArray" value="<%=index%>"/>
							</div>
						</td>
						<td width="42%" class="tdfont">
							<div align="center">
								<bean:write name="investMappingVO" property="isOptionalName" />
							</div>
						</td>
						
					</tr>
				
				</logic:iterate>
				</table>	
				 
			 </logic:notEmpty>
			 </logic:present>
		</his:ContentTag>	 
		</logic:equal>	 
	
	
	<his:ButtonToolBarTag>
	<logic:equal value="MODIFY" name="InvestigationMappingMstFB" property="hmode">
	 	<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) validateModifyForm('MODIFYSAVE')" onclick="validateModifyForm('MODIFYSAVE')" tabindex="1">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
    </logic:equal>
    <logic:equal value="VIEW" name="InvestigationMappingMstFB" property="hmode">
    	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
    </logic:equal>
    </his:ButtonToolBarTag>		
	
	<his:status/>
<html:hidden name="InvestigationMappingMstFB" property="hmode"/>
<html:hidden name="InvestigationMappingMstFB" property="chk"/>
<html:hidden name="InvestigationMappingMstFB" property="certificateTypeName" />
<html:hidden name="InvestigationMappingMstFB" property="certificateTypeID" />
<html:hidden name="InvestigationMappingMstFB" property="index" />
	
		</his:TransactionContainer>
		</html:form>
	</body>
</html>