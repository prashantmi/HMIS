<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
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
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">


function cancelForm(mode)
{
	document.getElementsByName("deskmode")[0].value=mode;
	document.forms[0].submit();
}


function validateSave(mode)
{

 var valid=false
	if(validateRecordSelect() && 
	comboValidation(document.getElementsByName('handOverToId')[0],'Hand Over To'))
	{
		submitForm(mode)
		valid=true
	
	}
	else
	{
	
		valid=false
	}
	return valid
}

function validateRecordSelect()
{
	var valid=false
	if(document.getElementsByName('mrdRecordId')[0])
	{
		var checkBox=document.getElementsByName('mrdRecordId');
		for(i=0;i<checkBox.length;i++)
		{
			if(checkBox[i].checked==true)
			{
				valid=true
			}
		}
	}
	if(valid==false)
	{
		alert("Please Select Atleast One Record")
	}
	return valid
}


function clearForm()
{
	
	for(i=0;i<document.getElementsByName('mrdRecordId').length;i++)
		{
			if(document.getElementsByName('mrdRecordId')[i].checked==true)
				document.getElementsByName('mrdRecordId')[i].checked=false
				
		}
		document.getElementsByName('handOverToId')[0].value="-1"
		//document.getElementsByName('handOverToName')[0].value=""
		document.getElementsByName('remarks')[0].value=""
		
}

//function showEmployeepopup(e,fieldToPopulate,index)
//{
//	var path="/HISClinical/mrd/employeePopUp.cnt?fieldToPopulate="+fieldToPopulate+"&index="+index
	//alert("path="+path)
//	openPopup(path,e,300,600);
//}
</script>

<body>
	<his:TransactionContainer>
		
		<his:SubTitleTag name="">
			<div align="left">
				<%=session.getAttribute(MrdConfig.MRD_NAME_FOR_TRACKING) %>
			</div>
		</his:SubTitleTag>
		<his:statusRecordFound>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="select"/>
									</b>	
								</font>
							</div>
						</td>
						<td class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="patName"/>
									</b>	
								</font>
							</div>
						</td>
						<td class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="crNo"/>
									</b>	
								</font>
							</div>
						</td>
						<td class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="queueNo"/>
									</b>	
								</font>
							</div>
						</td>
						<td class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="unit"/>
									</b>	
								</font>
							</div>
						</td>
						<td  class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="fileNo"/>
									</b>	
								</font>
							</div>
						</td>
						<td  class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="rackName"/>
									</b>	
								</font>
							</div>
						</td>
						<td class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="shelfName"/>
									</b>	
								</font>
							</div>
						</td>
						<td  class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="recordStatus"/>
									</b>	
								</font>
							</div>
						</td>
					
					</tr>
					<logic:present name="<%=MrdConfig.ARRAY_MRD_RECORD_VOLIST_OPD_FILES_FOR_MOVEMENT %>">
					<logic:iterate id="arrayRecordDtlVO" name="<%=MrdConfig.ARRAY_MRD_RECORD_VOLIST_OPD_FILES_FOR_MOVEMENT %>" indexId="idx" type="hisglobal.vo.MrdRecordDtlVO">
					<%
					String recordStatus=arrayRecordDtlVO.getRecordStatus();
					boolean disable=false;
					if(recordStatus.equals(MrdConfig.MRD_RECORD_STATUS_IN_MRD) || recordStatus.equals(MrdConfig.MRD_RECORD_STATUS_ARCHIVED))
					{
						disable=false;
					}else{
						disable=true;
					}
					%>
						<tr>
							<td width="5%" class="tdfontheadExam">
								<div align="center">
									<html:checkbox name="opdFileMovementFB" property="mrdRecordId" value="<%=arrayRecordDtlVO.getMrdRecordId() %>" disabled="<%=disable %>" onclick=""></html:checkbox>
								</div>
							</td>
							<td  class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="arrayRecordDtlVO" property="patName"/>
									</font>
								</div>
								
							</td>
							<td  class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="arrayRecordDtlVO" property="patCrNo"/>
									</font>
								</div>
								
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="arrayRecordDtlVO" property="visitQueueNo"/>
										
									</font>
								</div>
							</td>
							<td  class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="arrayRecordDtlVO" property="unitName"/>
									</font>
								</div>
								
							</td>
							<td  class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="arrayRecordDtlVO" property="recordId"/>
									</font>
								</div>
								
							</td>
							<td  class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="arrayRecordDtlVO" property="rackLabel"/>
										
									</font>
								</div>
							</td>
							<td class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="arrayRecordDtlVO" property="shelfName"/>
										
									</font>
								</div>
							</td>
							<td  class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="arrayRecordDtlVO" property="recordStatusLabel"/>
										
									</font>
								</div>
							</td>
							
						</tr> 
					</logic:iterate>
					</logic:present>
				</table>
			</his:ContentTag>
			<his:SubTitleTag name="Handed Over Details">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
					<td width="25%" class="tdfonthead" >
							<div align="right">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="handoverto"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="opdFileMovementFB" property="handOverToId" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.LIST_RECORD_HANDOVER_TO_FROM_MRD %>" >
											<html:options collection="<%=MrdConfig.LIST_RECORD_HANDOVER_TO_FROM_MRD %>" property = "value" labelProperty = "label"/>
										</logic:present>
									</html:select>
								</div>
							</td>
						
	     			<td width="25%" class="tdfonthead" >
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="remarks"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont" colspan="1">
		  				<div align="left">	
		  				           
		  				<html:textarea name="opdFileMovementFB" tabindex="1" property="remarks" />
							
		 			</div>
	     			</td>  
					</tr>
				</table>
			
			</his:ContentTag>
			
		</his:statusRecordFound>
		
		<his:ButtonToolBarTag>
			<his:statusRecordFound>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSave('SAVE')" onkeypress="if(event.keyCode==13) validateSave('SAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="cancelForm('CANCEL')" onkeypress="if(event.keyCode==13)cancelForm('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			</his:statusRecordFound>
			<his:statusUnsuccessfull>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
			</his:statusUnsuccessfull>	
		</his:ButtonToolBarTag>
		
		<html:hidden name="opdFileMovementFB" property="hmode" value="unspecified"/>
		<html:hidden name="opdFileMovementFB" property="mrdCode"/>
		
					
	</his:TransactionContainer>


</body>

</html>	
<%}catch(Exception e)
{
e.printStackTrace();	
}%>