<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function closeForm()
{
	self.close();
}

function clearForm()
{
	document.getElementsByName("hrgstr_fname")[0].value="";
	document.getElementsByName("hrgstr_mname")[0].value="";
	document.getElementsByName("hrgstr_lname")[0].value="";
	document.getElementsByName("hrgnum_puk")[0].value="";
//	document.getElementsByName("hipnum_admno")[0].value="";
//	document.getElementsByName("hrgnum_mlc_no")[0].value="";
	document.getElementsByName("recordType")[0].value="-1";
	/*document.getElementsByName("fromDate")[0].value="";
	document.getElementsByName("toDate")[0].value="";*/
}

function validateSearch()
{
	if(checkSearchField()){
		submitPage('SEARCH');
	}	
}

function populateValue(obj)
{
	//alert(obj.value);
	opener.document.getElementsByName("entryDate")[0].value=obj.value.split("@")[3];
	opener.document.getElementsByName("recordId")[0].value=obj.value.split("@")[2];
	opener.document.getElementsByName("recordDesc")[0].value=obj.value.split("@")[1];
	opener.document.getElementsByName("mrdRecordId")[0].value=obj.value.split("@")[0];
	
	self.close();
}

function checkSearchField(){
	if(document.getElementsByName("hrgstr_fname")[0].value!="" ||
	document.getElementsByName("hrgstr_mname")[0].value!="" ||
	document.getElementsByName("hrgstr_lname")[0].value!="" ||
	document.getElementsByName("hrgnum_puk")[0].value!="" ||
//	document.getElementsByName("hipnum_admno")[0].value!="" ||
//	document.getElementsByName("hrgnum_mlc_no")[0].value!="" ||
	document.getElementsByName("recordType")[0].value!="-1" )/*||
	document.getElementsByName("fromDate")[0].value!="" ||
	document.getElementsByName("toDate")[0].value!="")*/
	{
		return true;
	}
	else{
		alert("Please Enter at least one field");
		return false;
	}

}
</script>

<html:form action="/recordLostInMrd">
<body>  
	<his:SubTitleTag name="Search">
	</his:SubTitleTag>
		
    <his:ContentTag>
    	<table width="100%" border="0"  cellspacing="1" cellpadding="0">
			<tr>
				<td width="34%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="firstName"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="33%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="middleName"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="33%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="lastName"/>
							</b>	
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="34%" class="tdfont">
					<div align="center">
						<html:text name="RecordLostInMrdFB" property="hrgstr_fname" maxlength="30" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
					</div>
				</td>
				<td width="33%" class="tdfont">
					<div align="center">
						<html:text name="RecordLostInMrdFB" property="hrgstr_mname" maxlength="20" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
					</div>
				</td>
				<td width="33%" class="tdfont">
					<div align="center">
						<html:text name="RecordLostInMrdFB" property="hrgstr_lname" maxlength="30" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
					</div>
				</td>
			</tr>
			<tr>
				<td width="34%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="crNo"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="33%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="recordType"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="33%" class="tdfonthead">
				</td>
			</tr>
			<tr>
				<td width="34%" class="tdfont">
					<div align="center">
						<html:text name="RecordLostInMrdFB" property="hrgnum_puk" maxlength="15" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
					</div>
				</td>
				<td width="33%" class="tdfont">
					<div align="center">
						<html:select name="RecordLostInMrdFB" property="recordType">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=MrdConfig.RECORD_TYPE %>">
								<html:options collection="<%=MrdConfig.RECORD_TYPE %>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
					</div>
				</td>
				<td width="33%" class="tdfont">
				</td>
			</tr>
		</table>	
    </his:ContentTag>
    
    <his:ButtonToolBarTag>
    	<img class="button" src='<his:path src="/hisglobal/images/btn-search.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSearch()" onkeypress="if(event.keyCode==13) validateSearch()">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" closeForm()" onkeypress="if(event.keyCode==13)closeForm()">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
    </his:ButtonToolBarTag>
    
    <his:statusTransactionInProcess>
    	<his:SubTitleTag name="Search Result">
    	</his:SubTitleTag>
    	<%if(session.getAttribute(MrdConfig.ARR_CASESHEET_SEARCHED_RECORD_VO)!=null)
		{
			List lstSearchVO=(List)session.getAttribute(MrdConfig.ARR_CASESHEET_SEARCHED_RECORD_VO);
			if(lstSearchVO.size()>0){
		%>
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
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="recordId"/>
								</b>
							</font>
						</div>
					</td>
					<td width="35%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="name"/>
								</b>
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="crNo"/>
								</b>
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="gender/age"/>
								</b>
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="recordStatus"/>
								</b>
							</font>
						</div>
					</td>
				
				</tr>
				<logic:iterate id="searchedRecord" name="<%=MrdConfig.ARR_CASESHEET_SEARCHED_RECORD_VO %>" type="hisglobal.vo.MrdRecordDtlVO" indexId="idx">
					<tr>
						<td width="5%" class="tdfontheadExam" >
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="RecordLostInMrdFB" property="selectRecord" value='<%=searchedRecord.getMrdRecordId()+"@"+searchedRecord.getRecordDesc()+"@"+searchedRecord.getRecordId()+"@"+searchedRecord.getEntryDate()%>' onclick="populateValue(this)" ></html:radio>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfontheadExam" >
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=searchedRecord.getRecordDesc() %>
								</font>
							</div>
						</td>
						<td width="35%" class="tdfontheadExam" >
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=searchedRecord.getPatName() %>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfontheadExam" >
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=searchedRecord.getPatCrNo() %>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfontheadExam" >
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=searchedRecord.getPatGender() %>/
									<%=searchedRecord.getPatAge()%>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfontheadExam" >
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=MrdConfig.MRD_RECORD_STATUS_ARRAY[Integer.parseInt(searchedRecord.getRecordStatus())]%>
								</font>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>		
    	</his:ContentTag>
    	
		<%}} %>
    </his:statusTransactionInProcess>
    
    
    
    
    
    <html:hidden name="RecordLostInMrdFB" property="hmode"/>
</body>
<his:status/>
</html:form>    