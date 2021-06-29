<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.presentation.Status"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
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

function validateOk()
{
	var valid=true;
	var lenChk=document.getElementsByName("selectedCheckList").length;
	
	for(var i=0;i<lenChk;i++)
	{
		if(document.getElementsByName("isCompulsory")[i].value=='<%= MrdConfig.YES%>')
		{
			if(!document.getElementsByName("selectedCheckList")[i].checked)
			{
				alert("Please Checked The Compulsory Check List ");	
				return false;
			}
		}
	} 
	
	var str="";
	for(var i=0;i<lenChk;i++)
	{
		if(document.getElementsByName("selectedCheckList")[i].checked)
			str=str+document.getElementsByName("selectedCheckList")[i].value+"@";
	}
	str=str.substring(0,str.length-1);
	opener.document.getElementsByName("compCheckListId")[0].value=str;
	opener.document.getElementsByName('hmode')[0].value='CLOSECHECKLIST';
	opener.document.forms[0].submit();
	self.close();
}


function isFormClose()
{
	var formclose=true;
	 
  	<%     
		Status objStatus=(Status)request.getAttribute(Config.STATUS_OBJECT);
    	if(objStatus.contains(Status.NEW)){
    	%>    	
    		formclose=false;  
    		 	   	
    	<%
    }    
    %>
    
    if(formclose)
    {
    	if(!window.opener.closed)
    	{
    		self.close();
    	}
    }
}

function checkedSelectedValue()
{
	var str=document.getElementsByName("compCheckListId")[0].value;
	var arr=str.split("@");
	var chks=document.getElementsByName('selectedCheckList');
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

window.onload=function()
{
	checkedSelectedValue();
}

</script>

<html:form action="/certificateMovement">
	<body >
		<his:TitleTag name="Check List">
		</his:TitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="select"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="80%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									&nbsp;&nbsp;<bean:message key="checklist"/>
								</b>	
							</font>
						</div>
					</td>
				</tr>
				<logic:iterate id="arrCheckListVO" name="<%=MrdConfig.ARR_CHECKLIST_FOR_MEDICAL_CERTIFICATE_VO %>" type="hisglobal.vo.RecordTypeCheckListMstVO" indexId="idx">
					<tr>
						<td width="20%" class="tdfont">
							<div align="center">
								<html:checkbox name="CertificateMovementFB" property="selectedCheckList" value="<%=arrCheckListVO.getChecklistId()%>" tabindex="1" ></html:checkbox>
								<html:hidden name="CertificateMovementFB" property="isCompulsory" value="<%=arrCheckListVO.getIsCompulsory() %>"/>
							</div>
						</td>
						<td width="80%" class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									&nbsp;&nbsp;<%if(arrCheckListVO.getIsCompulsory().equals(MrdConfig.YES)){ %>
										<font color="#FF0000" size="1">*</font>
									<%} %>
									<%=arrCheckListVO.getChecklistName() %>
								</font>
							</div>
						</td>
					</tr>
				</logic:iterate>		
				
			</table>
		</his:ContentTag>
		
		<his:ButtonToolBarTag>
			<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  onclick="validateOk()" tabindex="1" onkeypress="if(event.keyCode==13) validateOk()" >
		 	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) closeForm();" tabindex="1" onclick ="closeForm();">
		</his:ButtonToolBarTag>
		
		<his:status/>
		
	</body>
		<html:hidden name="CertificateMovementFB" property="hmode"/>
		<html:hidden name="CertificateMovementFB" property="compCheckListId"/>
		
</html:form>		