<!-- 
 /****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	LOCAL LAB MASTER
 ## Name of Developer		 			:	Jatin Kumar	
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	This process is used for mapping of lab test wise consumable items.  
 ## Date of Creation					: 	29-Jul-2016
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/**************************************************************************************************************/ 
-->


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.presentation.WebUTIL" %>
<html>
<head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/new_investigation/js/labRequisition.js" />
<his:javascript src="/new_investigation/js/itemLabTestMappingMst.js" />
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<script>
function validateTodayOrDate()
{
	success=false;        	   
   // alert('success');
    valExpiryDate=document.getElementsByName('expiryDate')[0];
	valUsageStartDate=document.getElementsByName('usageStartDate')[0];
	valUsageEndDate=document.getElementsByName('usageEndDate')[0];
	<%String systemdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
	document.getElementsByName('sysDate')[0].value="<%=systemdate%>";
	valSysDate=document.getElementsByName('sysDate')[0];
      
    if(compareDateCall(valUsageStartDate,valUsageEndDate,2,"Usage Start Date","Usage End Date")&&compareDateCall(valUsageStartDate,valExpiryDate,2,"Usage Start Date","Expiry Date")&&compareDateCall(valUsageEndDate,valExpiryDate,2,"Usage End Date","Expiry Date") )
    {
    	//alert(success);
	    success=true;
    }             
    return success;        
}
function getElement()
{
	if(document.getElementsByName('entry')[0].checked)
		return document.getElementsByName('entry')[0];
	else
		return document.getElementsByName('entry')[1];
	
}
</script>
</head>
<body onload="showInfo(getElement());getItemCombo(document.getElementsByName('storeID')[0]);setDimensions();">
<html:form action="/masters/ItemLabTestMappingMstACT">
<his:TransactionContainer>
<bean:define name="ItemLabTestMappingMstFB" property="expiryDate" id="expiryDate" type="java.lang.String"/>
<bean:define name="ItemLabTestMappingMstFB" property="usageStartDate" id="useStartDate" type="java.lang.String"/>
<bean:define name="ItemLabTestMappingMstFB" property="usageEndDate" id="useEndDate" type="java.lang.String"/>
<%
         if(expiryDate==null||expiryDate.equalsIgnoreCase(""))
         {  
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	expiryDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
  
		  if(useStartDate==null||useStartDate.equalsIgnoreCase(""))
		  {  
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	useStartDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   
		  }
		  if(useEndDate==null||useEndDate.equalsIgnoreCase(""))
		  {  
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	useEndDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }
    	%> 


	<his:TitleTag name="Item Uses Detail">
				
			</his:TitleTag>
<table width="100%" border="0" cellspacing="1" cellpadding="0">

					

					<%-- <tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="labCode"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="labCode" name="ItemLabTestMappingMstFB"></html:text>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="testCode"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="testCode" name="ItemLabTestMappingMstFB"></html:text>
								</div>
						</td>
						</tr> --%>
						<%-- <tr style="display:none;">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="itemName"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="itemName" readonly="true" name="ItemLabTestMappingMstFB"></html:text>
								</div>
						</td>
						</tr> --%>
						<tr >
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="otherItemId"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								
								

									<html:select name="ItemLabTestMappingMstFB" onchange="setItemName(this)" property="itemID" style="width : 29%"
										tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=InvestigationConfig.OTHER_ID_LIST%>">
										<html:options
											collection="<%=InvestigationConfig.OTHER_ID_LIST%>"
											property="value" labelProperty="label" />
											</logic:present>
									</html:select>
								<html:hidden property="itemName"  name="ItemLabTestMappingMstFB"/>
							
								
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="entry"/>-
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<bean:message key="store"/>
								<html:radio property="entry" value="store" onclick='showInfo(this)'></html:radio>
								<bean:message key="manual"/>
								<html:radio property="entry" value="manual" onclick='showInfo(this)'></html:radio>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="lotNo"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="lotNo" onblur="lotDuplicacyCheck(this)" name="ItemLabTestMappingMstFB"></html:text>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								 <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="manufactur"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="manufacture" name="ItemLabTestMappingMstFB"></html:text>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="expiryDate"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<his:date  name='expiryDate' dateFormate="%d-%b-%Y" value="<%=expiryDate%>"/>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="usageStartDate"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<his:date  name='usageStartDate' dateFormate="%d-%b-%Y" value="<%=useStartDate%>"/>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="usageEndDate"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<his:date  name='usageEndDate'  dateFormate="%d-%b-%Y" value="<%=useEndDate%>"/>
								</div>
						</td>
						</tr>
						<tr id="storeId">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="storeName"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:select name="ItemLabTestMappingMstFB" style="width:29%"
										property="storeID" tabindex="1" onchange="getItemCombo(this)">
										<html:option value="-1">Select Value</html:option>
										<logic:notEmpty name="<%=InvestigationConfig.LIST_FILM_STORENAME_COMBO %>">
										<html:options
											collection="<%=InvestigationConfig.LIST_FILM_STORENAME_COMBO %>"
											property="value" labelProperty="label" />
											</logic:notEmpty>
									</html:select>
								
								
								
								</div>
						</td>
						</tr>
						<tr id="itemIdFromStore">
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="storeItemName"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:select name="ItemLabTestMappingMstFB" style="width:29%"
										property="itemIdFromStore" tabindex="1"  onchange="getItemValues(this)">
										<html:option value="-1">Select Value</html:option>
										
									</html:select>
								
								
								
								</div>
						</td>
						</tr>
						
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="usageStatus"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:select property="usage" name="ItemLabTestMappingMstFB" style="width : 29%">
								<html:option value="-1">Select</html:option>
								<html:option value="<%=InvestigationConfig.USAGE_STATUS_INUSE_VALUE %>"><%=InvestigationConfig.USAGE_STATUS_INUSE_LABEL %> </html:option>
								<html:option value="<%=InvestigationConfig.USAGE_STATUS_USED_VALUE %>"> <%=InvestigationConfig.USAGE_STATUS_USED_LABEL %> </html:option>
								<html:option value="<%=InvestigationConfig.USAGE_STATUS_STOP_USING_VALUE %>"> <%=InvestigationConfig.USAGE_STATUS_STOP_USING_LABEL %></html:option>
								</html:select>
								</div>
						</td>
						</tr>
						<%-- <tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="itemType"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:select name="ItemLabTestMappingMstFB" style="width:29%"
										property="itemType" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:notEmpty name="<%=InvestigationConfig.ITEMTYPE_COMBO_HIVT_ITEMTYPE_MST %>">
										<html:options
											collection="<%=InvestigationConfig.ITEMTYPE_COMBO_HIVT_ITEMTYPE_MST %>"
											property="value" labelProperty="label" />
											</logic:notEmpty>
									</html:select>
								
								</div>
						</td>
						</tr> --%>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								 <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="remarks"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="remarks" name="ItemLabTestMappingMstFB"></html:text>
								</div>
						</td>
						</tr>
						<%-- <tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="storeName"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="storeName" name="ItemLabTestMappingMstFB"></html:text>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="storeItemName"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="storeItemName" name="ItemLabTestMappingMstFB"></html:text>
								</div>
						</td> --%>
						</tr>
						
						
</table>

<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="ItemLabTestMappingMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="ItemLabTestMappingMstFB" property="hmode" value="VIEW">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-sv.png"/>'
								style="cursor: pointer"
								onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
								onclick="finalSubmit('SAVE')" tabindex="1">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-clr.png"/>'
								style="cursor: pointer" onclick="clearAddForm()"
								onkeypress="if(event.keyCode==13) clearAddForm()" tabindex="1">
						</logic:notEqual>
					</logic:notEqual> <logic:equal name="ItemLabTestMappingMstFB" property="hmode" value="MODIFY">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-mo.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')"
							onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-clr.png"/>'
							style="cursor: pointer" onclick="submitForm('CLEAR')"
							onkeypress="if(event.keyCode==13) submitForm('CLEAR')" tabindex="1">
					</logic:equal> <img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
					style="cursor: pointer" onclick="submitForm('CANCEL')"
					onkeypress="if(event.keyCode==13) submitForm('CANCEL')"
					tabindex="1">
				</span>
			</his:ButtonToolBarTag>
</his:TransactionContainer>

<his:status/>
<html:hidden name="ItemLabTestMappingMstFB" property="sysDate"/>
<html:hidden name="ItemLabTestMappingMstFB" property="hmode"/>
<html:hidden  name='ItemLabTestMappingMstFB' property="selectedChk"/>
<html:hidden  name='ItemLabTestMappingMstFB' property="tempOtherID"/>
<html:hidden  name='ItemLabTestMappingMstFB' property="tempLotNo"/>

</html:form>
</body>
</html>