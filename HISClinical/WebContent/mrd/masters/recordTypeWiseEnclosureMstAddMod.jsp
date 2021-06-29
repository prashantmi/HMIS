<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
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
    
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/mrd/js/recordTypeWiseEnclosureMstAddMod.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<body>
 <his:TransactionContainer>
	<html:form action="/master/recordTypeWiseEnclosureMst" >
	
   
		<his:TitleTag name="Record Type Wise Enclosure Master">
		</his:TitleTag>
        <his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="recordType"/>
								</b>
							</font>	
						</div>
					</td>
					<td width="75%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<bean:write name="recordTypeWiseEnclosureMstFB" property="recordTypeName"/>
								<html:hidden name="recordTypeWiseEnclosureMstFB" property="recordTypeName"/>
								<html:hidden name="recordTypeWiseEnclosureMstFB" property="recordTypeId"/>
							</font>
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
		
		<his:statusList>
			<his:SubTitleTag name="Added Enclosure">
			</his:SubTitleTag>
			<logic:present name="<%=MrdConfig.ARR_ADDED_RECORD_TYPE_ENCLOSURE_VO %>">
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="40%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="enclosure"/>
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
						<logic:iterate id="addedEnclosureVO" name="<%=MrdConfig.ARR_ADDED_RECORD_TYPE_ENCLOSURE_VO %>" type="hisglobal.vo.RecordTypeWiseEnclosureMstVO">
							<tr>
								<td width="40%" class="tdfontheadExam">
								  	<div align="center">
								  		<font color="#000000">
								  	 		<bean:write name="addedEnclosureVO" property="enclosure"/>
								   		</font>
								    </div>   
								</td>
								<td  width="40%" class="tdfontheadExam">
								  	<div align="center">
								  		<font color="#000000">
								  			<bean:write name="addedEnclosureVO" property="isCompulsoryLabel"/>
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
			</logic:present>
			<logic:notPresent name="<%=MrdConfig.ARR_ADDED_RECORD_TYPE_ENCLOSURE_VO %>">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="100%" class="tdfont">
							<div align="center">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										No Enclosure Added
									</b>
								</font>	
							</div>
						</td>
					</tr>
				</table>		
			</logic:notPresent>
		</his:statusList>
		
		<logic:notEqual name="recordTypeWiseEnclosureMstFB" property="hmode" value="VIEW">
			<his:SubTitleTagBroad name="Add New Enclosure">
				<td width="10%" class="tdfonthead">
					<div align="center">
						<html:button value="Add Enclosure" property="mybutton" onclick="getEnclosure(event)"/>
					</div>
				</td>
			</his:SubTitleTagBroad>
			<his:statusTransactionInProcess>
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="40%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="enclosure"/>
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
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											&nbsp;
										</b>
									</font>	
								</div>
							</td>
						</tr>
						<logic:iterate id="newEnclosureVO" name="<%=MrdConfig.LIST_NEW_ADDEDD_ENCLOSURE_VO %>" type="hisglobal.vo.RecordTypeWiseEnclosureMstVO">
							<tr>
								<td width="40%" class="tdfontheadExam">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="newEnclosureVO" property="enclosure"/>
											<html:hidden name="recordTypeWiseEnclosureMstFB" property="hiddenEnclosure" value="<%=newEnclosureVO.getEnclosure() %>"/>
											<html:hidden name="recordTypeWiseEnclosureMstFB" property="hiddenEncId" value="<%=newEnclosureVO.getEnclosureId() %>"/>
										</font>	
									</div>
								</td>
								<td width="40%" class="tdfontheadExam">
									<div align="center">
										<html:select name="recordTypeWiseEnclosureMstFB" property="isCompulsory" value="<%=newEnclosureVO.getIsCompulsory()  %>">
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
		
		<his:ButtonToolBarTag>
		<logic:equal name="recordTypeWiseEnclosureMstFB" property="hmode" value="VIEW">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
		</logic:equal>
		<logic:notEqual name="recordTypeWiseEnclosureMstFB" property="hmode" value="VIEW">	
			<logic:equal name="recordTypeWiseEnclosureMstFB" property="hmode" value="POPULATE">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSave()" onkeypress="if(event.keyCode==13) validateSave()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm21('ADD')" onkeypress="if(event.keyCode==13) submitForm21('ADD')">
			</logic:equal>	
			<logic:notEqual name="recordTypeWiseEnclosureMstFB" property="hmode" value="POPULATE">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</logic:notEqual>
		</logic:notEqual>	
		</his:ButtonToolBarTag>
	
	
		<html:hidden name="recordTypeWiseEnclosureMstFB" property="hmode"/>
		<html:hidden name="recordTypeWiseEnclosureMstFB" property="serialNo"/>
		
		<html:hidden name="recordTypeWiseEnclosureMstFB" property="enclosureId"/>
		<html:hidden name="recordTypeWiseEnclosureMstFB" property="hiddenControl"/>
		<html:hidden name="recordTypeWiseEnclosureMstFB" property="concatedIndex"/>
		
	</html:form>
	<his:status/>
</his:TransactionContainer>	
</body>
</html>
