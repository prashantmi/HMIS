<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>    

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<script type="text/javascript">

function getAddedCheckList(mode)
{
	submitForm21(mode);
}

function getCheckList(e)
{
	var recordTypeId=document.getElementsByName("recordTypeId")[0].value;
	var checkListMode=document.getElementsByName("checkListMode")[0].value;
	
	
	var path="/HISClinical/mrd/master/recordTypeCheckListMst.cnt?hmode=GETCHECKLIST&recordTypeId="+recordTypeId+'&checkListMode='+checkListMode;
	openPopup(createFHashAjaxQuery(path),e, "300", "600"); 
}

function validateSaveForm(mode)
{
	var len=document.getElementsByName("isCompulsory").length;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("isCompulsory")[i].value=="-1")
		{
			alert("Please Select Compulsory");
			document.getElementsByName("isCompulsory")[i].focus();
			return false;
		}
	}
	
	submitForm21(mode);
}

</script>
<title>Record Type Check List Master</title>

<body>
	<html:form action="/master/recordTypeCheckListMst">
		<his:TransactionContainer>
		<logic:notEqual value="VIEW" name="RecordTypeCheckListMstFB" property="hmode">
			<his:TitleTag name="Record Type Check List Master">
			</his:TitleTag>
			
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
	  								<b>
	  									<bean:message key="recordType"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<bean:write name="RecordTypeCheckListMstFB" property="recordTypeName"/>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="checkListMode"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="RecordTypeCheckListMstFB" property="checkListMode" onchange="getAddedCheckList('GETADDEDCHECKLST')">
									<html:option value="-1">Select Value</html:option>
									<html:option value="<%=MrdConfig.CHECKLIST_MODE_DISPATCH_LEVEL %>">Dispatch</html:option>
									<html:option value="<%=MrdConfig.CHECKLIST_MODE_APPROVAL_LEVEL %>">Approval</html:option>
									<html:option value="<%=MrdConfig.CHECKLIST_MODE_HANDOVER_LEVEL %>">HandOver</html:option>
									<html:option value="<%=MrdConfig.CHECKLIST_MODE_ARCHIVAL_POINT %>">Archival</html:option>
								</html:select>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>	
			<logic:notEqual value="-1" name="RecordTypeCheckListMstFB" property="checkListMode">	
				<his:SubTitleTag name="Added Check List">
				</his:SubTitleTag>
			<logic:present name="<%=MrdConfig.ALL_ADDED_CHECKLIST_LIST_BY_RECORDTYPE %>">
			<logic:notEmpty name="<%=MrdConfig.ALL_ADDED_CHECKLIST_LIST_BY_RECORDTYPE %>">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="checkList"/>
									</b>
								</font>	
							</div>
						</td>
						<td width="40%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="compulsory"/>
									</b>
								</font>	
							</div>
						</td>
						<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										&nbsp;
									</b>
								</font>	
							</div>
						</td>
					</tr>
					<logic:iterate id="recordTypeCheckListMstVO" indexId="idx" name="<%=MrdConfig.ALL_ADDED_CHECKLIST_LIST_BY_RECORDTYPE%>" type="hisglobal.vo.RecordTypeCheckListMstVO">
						<tr>
							<td width="40%" class="tdfontheadExam">
							  	<div align="center">
							  		<font color="#000000">
							  	 		<bean:write name="recordTypeCheckListMstVO" property="checklistName"/>
							   		</font>
							    </div>   
							</td>
							<td  width="40%" class="tdfontheadExam">
							  	<div align="center">
							  		<font color="#000000">
							  			<bean:write name="recordTypeCheckListMstVO" property="isCompulsoryName"/>
							    	</font>
							    </div>   
							</td>
							<td  width="20%" class="tdfontheadExam">
							  	<div align="center">
							  		
							    </div>   
							</td>
						</tr>
					</logic:iterate>
				</table>		
			</his:ContentTag>
		</logic:notEmpty>	
		</logic:present>
		
		<logic:notPresent name="<%=MrdConfig.ALL_ADDED_CHECKLIST_LIST_BY_RECORDTYPE %>">
		
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="100%" class="tdfont">
						<div align="center">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									No Check List Added
								</b>
							</font>	
						</div>
					</td>
				</tr>
			</table>	
				
		</logic:notPresent>
		
		<logic:present name="<%=MrdConfig.ALL_ADDED_CHECKLIST_LIST_BY_RECORDTYPE %>">
		<logic:empty name="<%=MrdConfig.ALL_ADDED_CHECKLIST_LIST_BY_RECORDTYPE %>">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="100%" class="tdfont">
						<div align="center">
							<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									No Check List Added
								</b>
							</font>	
						</div>
					</td>
				</tr>
			</table>	
		</logic:empty>
		</logic:present>		
		
				
		
		
		<his:SubTitleTagBroad name="Add New Check List">
			<td width="10%" class="tdfonthead">
				<div align="center">
					<html:button value="Add Check List" property="mybutton" onclick="getCheckList(event)"/>
				</div>
			</td>
		</his:SubTitleTagBroad>
		<his:statusTransactionInProcess>
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="checkList"/>
									</b>
								</font>	
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="compulsory"/>
									</b>
								</font>	
							</div>
						</td>
						<td width="20%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										&nbsp;
									</b>
								</font>	
							</div>
						</td>
					</tr>
					<logic:iterate id="newRecordTypeCheckListMstVO" name="<%=MrdConfig.LIST_NEW_ADDEDD_CHECKLIST_VO %>" type="hisglobal.vo.RecordTypeCheckListMstVO">
						<tr>
							<td width="40%" class="tdfontheadExam">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="newRecordTypeCheckListMstVO" property="checklistName"/>
									</font>	
								</div>
							</td>
							<td width="40%" class="tdfontheadExam">
								<div align="center">
									<html:select name="RecordTypeCheckListMstFB" property="isCompulsory" value="<%=newRecordTypeCheckListMstVO.getIsCompulsory() %>">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">No</html:option>
										<html:option value="1">Yes</html:option>
									</html:select>	
								</div>
							</td>
							<td width="20%" class="tdfontheadExam">
								<div align="center">
									
								</div>
							</td>
						</tr>
					</logic:iterate>
				</table>		
			</his:ContentTag>
		</his:statusTransactionInProcess>	
		</logic:notEqual>
		</logic:notEqual>
		
		<logic:equal value="VIEW" name="RecordTypeCheckListMstFB" property="hmode">
			<his:TitleTag name="Record Type Check List Master">
			</his:TitleTag>
			
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
	  								<b>
	  									<bean:message key="recordType"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<bean:write name="RecordTypeCheckListMstFB" property="recordTypeName"/>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="checkListMode"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="RecordTypeCheckListMstFB" property="checkListMode" onchange="getAddedCheckList('GETADDEDCHECKLST')" disabled="true">
									<html:option value="-1">Select Value</html:option>
									<html:option value="<%=MrdConfig.CHECKLIST_MODE_DISPATCH_LEVEL %>">Dispatch</html:option>
									<html:option value="<%=MrdConfig.CHECKLIST_MODE_APPROVAL_LEVEL %>">Approval</html:option>
									<html:option value="<%=MrdConfig.CHECKLIST_MODE_HANDOVER_LEVEL %>">HandOver</html:option>
									<html:option value="<%=MrdConfig.CHECKLIST_MODE_ARCHIVAL_POINT %>">Archival</html:option>
								</html:select>
							</div>
						</td>
					</tr>
				</table>
				</his:ContentTag>	
				<his:SubTitleTag name="Added Check List">
				</his:SubTitleTag>
			<logic:present name="<%=MrdConfig.ALL_ADDED_CHECKLIST_LIST_BY_RECORDTYPE %>">
			<logic:notEmpty name="<%=MrdConfig.ALL_ADDED_CHECKLIST_LIST_BY_RECORDTYPE %>">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="checkList"/>
									</b>
								</font>	
							</div>
						</td>
						<td width="40%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="compulsory"/>
									</b>
								</font>	
							</div>
						</td>
						<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										&nbsp;
									</b>
								</font>	
							</div>
						</td>
					</tr>
					<logic:iterate id="recordTypeCheckListMstVO" indexId="idx" name="<%=MrdConfig.ALL_ADDED_CHECKLIST_LIST_BY_RECORDTYPE%>" type="hisglobal.vo.RecordTypeCheckListMstVO">
						<tr>
							<td width="40%" class="tdfontheadExam">
							  	<div align="center">
							  		<font color="#000000">
							  	 		<bean:write name="recordTypeCheckListMstVO" property="checklistName"/>
							   		</font>
							    </div>   
							</td>
							<td  width="40%" class="tdfontheadExam">
							  	<div align="center">
							  		<font color="#000000">
							  			<bean:write name="recordTypeCheckListMstVO" property="isCompulsoryName"/>
							    	</font>
							    </div>   
							</td>
							<td  width="20%" class="tdfontheadExam">
							  	<div align="center">
							  		
							    </div>   
							</td>
						</tr>
					</logic:iterate>
				</table>		
			</his:ContentTag>
		</logic:notEmpty>	
		</logic:present>
			
			
			
		</logic:equal>
		
		
		
		<his:ButtonToolBarTag>
		<logic:equal value="POPULATE" name="RecordTypeCheckListMstFB" property="hmode">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateSaveForm('SAVE')" onkeypress="if(event.keyCode==13) validateSaveForm('SAVE')">
		</logic:equal>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
		<logic:equal value="POPULATE" name="RecordTypeCheckListMstFB" property="hmode">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick=" submitForm21('ADD')" onkeypress="if(event.keyCode==13)  submitForm21('ADD');">
		</logic:equal>	
		</his:ButtonToolBarTag>
			
			
			
			
			
			
			<html:hidden name="RecordTypeCheckListMstFB" property="hmode"/>
			<html:hidden name="RecordTypeCheckListMstFB" property="recordTypeId"/>
			<html:hidden name="RecordTypeCheckListMstFB" property="recordTypeName"/>
			<html:hidden name="RecordTypeCheckListMstFB" property="concatedIndex"/>
		</his:TransactionContainer>
		
		<his:status/>
	</html:form>
</body>
</html>			