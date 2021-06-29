
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.RecordTypeWiseEnclosureMstVO"%>
<%@page import="mrd.transaction.controller.fb.CertificateAcceptFB"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="hisglobal.vo.RecordCheckListDtlVO"%>
<%@page import="hisglobal.vo.RecordTypeCheckListMstVO"%>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/master.css"/>
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

window.onload=function()
{
	if(document.getElementsByName("hmode")[0].value == "ADDENCLOSURE")
	{
		var index=document.getElementsByName("index")[0].value;
		opener.document.getElementsByName("verifiedEnclosure")[index].value=<%=MrdConfig.YES%>;
		//opener.document.getElementsByName("selectEnclosure")[index].value="Modify Enclosure";
		self.close();
	}

	checkedSelectedValue();
	
	
	
	
	
	 var compulArr=document.getElementsByName('hiddenIsComp');
		for(var k=0;k<compulArr.length;k++)
		{
		if(document.getElementsByName('hiddenIsComp')[k].value==<%=MrdConfig.IS_COMPULSORY_YES%>)
		 	{
		 		document.getElementsByName('chkArchivalCheckList')[k].checked=true;
		 		// document.getElementsByName('remarks')[k].disabled=false;
		 		//showPageTextBox(document.getElementsByName('selectedCheckListId')[i]);
			}
		}
		
		   
	
}

function checkedSelectedValue()
{
	var str=document.getElementsByName("archivalChkValue")[0].value;
	var arr=str.split("@");
	var chks=document.getElementsByName('chkArchivalCheckList');
	for(var i=0;i<arr.length;i++)
	{
		for(var j=0;j<chks.length;j++)
		{
			if(chks[j].value==arr[i])
			{
				chks[j].checked=true;
			}
		}
	}
}

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

function verifyEnclosure()
{
	var index=document.getElementsByName("index")[0].value;
	
	for(var i=0;i<document.getElementsByName("enclosurePages").length;i++)
	{
		if(document.getElementsByName("enclosurePages")[i].value=="")
		{
			alert("Please Enter The No of Pages Received for '"+document.getElementsByName("hiddenEnclosureName")[i].value+ "' In MRD")
			document.getElementsByName("enclosurePages")[i].focus();
			return false;
		}
		if(parseInt(document.getElementsByName("enclosurePages")[i].value)>parseInt(document.getElementsByName("hiddenByPeonPages")[i].value))
		{
			alert("Entered No of Pages for '"+document.getElementsByName("hiddenEnclosureName")[i].value+ "' Cannot Be Greater Than "+document.getElementsByName("hiddenByPeonPages")[i].value)
			document.getElementsByName("enclosurePages")[i].focus();
			return false;
		}
	}
	return true;
	
	/*opener.document.getElementsByName("verifiedEnclosure")[index].value=<%=MrdConfig.YES%>;
	submitPage('ADDENCLOSURE');
	self.close();*/
}

function verifyCheckList()
{
	var index=document.getElementsByName("index")[0].value;
	
	for(var i=0;i<document.getElementsByName("chkArchivalCheckList").length;i++)
	{
		if(document.getElementsByName("hiddenIsComp")[i].value=='<%= MrdConfig.YES%>')
		{
			if(!document.getElementsByName("chkArchivalCheckList")[i].checked)
			{
				alert("Please Checked The Compulsory Check List For Archival Level.");	
				return false;
			}
		}
	}
	
	return true;
}

function verify()
{
	if(verifyEnclosure() && verifyCheckList())
	{
		submitPage('ADDENCLOSURE');
	}
}
</script>

<html:form action="/certificateAcceptInMrd">
	<body>
		<his:SubTitleTag name="Enclosure Detail">
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="enclosure"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="dispatchPages"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<%-- <bean:message key="verifyByPeon"/> --%>Verified by concern person 
								</b>	
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<%-- <bean:message key="verifyByMRD"/> --%>Verified By MRD concern person
								</b>	
							</font>
						</div>
					</td>
				</tr>
				<logic:iterate name="<%=MrdConfig.ENCLOSURE_DETAIL_ACCEPT_IN_MRD_VO %>" id="enclosureVO" type="hisglobal.vo.RecordTypeWiseEnclosureMstVO">
					<tr>
						<td width="40%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=enclosureVO.getEnclosure() %>
								</font>
							</div>
						</td>
						<html:hidden name="CertificateAcceptFB" property="hiddenEnclosureName" value="<%=enclosureVO.getEnclosure() %>"/>
						<html:hidden name="CertificateAcceptFB" property="hiddenEnclosureId" value="<%=enclosureVO.getEnclosureId() %>"/>
						<td width="20%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=enclosureVO.getPages() %>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=enclosureVO.getVerifiedByPeon() %>
								</font>
							</div>
							<html:hidden name="CertificateAcceptFB" property="hiddenByPeonPages" value="<%=enclosureVO.getVerifiedByPeon() %>"/>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
								<%Map map = (HashMap)session.getAttribute(MrdConfig.ENCLOSURE_ACCEPTED_IN_MRD_MAP);
									String str=enclosureVO.getVerifiedByPeon();	
									String dispatchId=(String)(((CertificateAcceptFB)pageContext.findAttribute("CertificateAcceptFB")).getDispatchId());
								
									if(map!=null)
									{
										List lstEnclosureInMrdVO=(List)map.get(dispatchId);
										if(lstEnclosureInMrdVO!=null)
										{
											for(int i=0;i<lstEnclosureInMrdVO.size();i++)
											{
												RecordTypeWiseEnclosureMstVO enclosureDtlVO=new RecordTypeWiseEnclosureMstVO();
												enclosureDtlVO=(RecordTypeWiseEnclosureMstVO)lstEnclosureInMrdVO.get(i);
												if(enclosureVO.getEnclosureId().equals(enclosureDtlVO.getEnclosureId()))
													str=enclosureDtlVO.getVerifiedByMrd();
											}
										}	
									}		
									%>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:text name="CertificateAcceptFB" property="enclosurePages" maxlength="3" size="4" value="<%=str %>"></html:text>
								</font>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>	
		</his:ContentTag>
		<% RecordCheckListDtlVO[] arrChkListVO=(RecordCheckListDtlVO[])session.getAttribute(MrdConfig.ARR_CHECKED_CHECKLIST_BY_DISPATCH_ID);
		if(arrChkListVO==null){}
		else{%>
		<his:SubTitleTag name="CheckList Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="checklist"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="dispatchLevel"/>
								</b>		
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	
									<bean:message key="approvalLevel"/>
								</b>		
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="receivingLevel"/>
								</b>		
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="archivalLevel"/>
								</b>		
							</font>
						</div>
					</td>
				</tr>
				
				<%	
					RecordTypeCheckListMstVO[] recordTypeCheckListMstVO=(RecordTypeCheckListMstVO[])session.getAttribute(MrdConfig.ARR_CHECKLIST_BY_RECORD_TYPE_N_MODE_VO);
					RecordCheckListDtlVO[] arrCheckListVO=(RecordCheckListDtlVO[])session.getAttribute(MrdConfig.ARR_CHECKED_CHECKLIST_BY_DISPATCH_ID);
					List lstCheckList=(List)session.getAttribute(MrdConfig.LIST_CHECKLIST_BY_RECORD_TYPE);
					boolean flag=true;
					if(lstCheckList.size()>0){
						for(int i=0;i<lstCheckList.size();i++)
						{
							Entry ent=(Entry)lstCheckList.get(i);
								
				%>
					<tr>
						<td width="40%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%for(int k=0;k<recordTypeCheckListMstVO.length;k++){
									if(ent.getValue().equals(recordTypeCheckListMstVO[k].getChecklistId()) && recordTypeCheckListMstVO[k].getIsCompulsory().equals(MrdConfig.YES) ){
									%>
										<font color="#FF0000" size="1">*</font>
								<% flag=true;}} 
									if(flag){ %>
									<%=ent.getLabel() %>
									<%} %>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
								<%for(int j=0;j<arrCheckListVO.length;j++)
								{
								if(ent.getValue().equals(arrCheckListVO[j].getCheckListId())){
								if(arrCheckListVO[j].getCheckListBy().equals(MrdConfig.CHECKLIST_MODE_DISPATCH_LEVEL)){ %>
									<input type="checkbox" name="dispatchLevel" checked="checked" disabled="disabled">
								<%}}} %>
								
							</div>
						</td>	
						<td width="15%" class="tdfont">
							<div align="center">
								<%for(int j=0;j<arrCheckListVO.length;j++)
								{
								if(ent.getValue().equals(arrCheckListVO[j].getCheckListId())){
								if(arrCheckListVO[j].getCheckListBy().equals(MrdConfig.CHECKLIST_MODE_APPROVAL_LEVEL)){ %>
									<input type="checkbox" name="approvalLevel" checked="checked" disabled="disabled">
								<%}}} %>
								
							</div>
						</td>	
						<td width="15%" class="tdfont">
							<div align="center">
								<%for(int j=0;j<arrCheckListVO.length;j++)
								{
								if(ent.getValue().equals(arrCheckListVO[j].getCheckListId())){
								if(arrCheckListVO[j].getCheckListBy().equals(MrdConfig.CHECKLIST_MODE_HANDOVER_LEVEL)){ %>
									<input type="checkbox" name="receivingLevel" checked="checked" disabled="disabled">
								<%}}} %>
								
							</div>
						</td>	
						<td width="15%" class="tdfont">
							<div align="center">
								<%for(int k=0;k<recordTypeCheckListMstVO.length;k++){
									if(ent.getValue().equals(recordTypeCheckListMstVO[k].getChecklistId())){
									%>
								<html:checkbox name="CertificateAcceptFB" property="chkArchivalCheckList" value="<%=ent.getValue() %>"></html:checkbox>
								<html:hidden name="CertificateAcceptFB" property="hiddenIsComp" value="<%= recordTypeCheckListMstVO[k].getIsCompulsory()%>"/>
								<%}} %>
							</div>
						</td>	
					</tr>
				
				<%}}%>
			</table>	
		</his:ContentTag>
		<%} %>
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) verify();" tabindex="1" onclick ="verify();">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) closeForm();" tabindex="1" onclick ="closeForm();">
		</his:ButtonToolBarTag>
	</body>
	
	<html:hidden name="CertificateAcceptFB" property="hmode"/>
	<html:hidden name="CertificateAcceptFB" property="dispatchId"/>
	<html:hidden name="CertificateAcceptFB" property="recordType"/>
	<html:hidden name="CertificateAcceptFB" property="index"/>
	<html:hidden name="CertificateAcceptFB" property="archivalChkValue"/>
	<html:hidden name="CertificateAcceptFB" property="recordId"/>
	
</html:form>