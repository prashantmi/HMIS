<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>



<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.CDBurnFB"%>
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
window.onload=function(){
	if(document.getElementsByName("hmode")[0].value=="SETRECORDTOBURN"){
		var top=(screen.height-250)/2
		var left=(screen.width-400)/2
		var winPath=document.getElementsByName("winPath")[0].value;
		var linuxPath=document.getElementsByName("linuxPath")[0].value;
		
		var path="/HISClinical/hisglobal/utility/burnDisc/burnDisc.jsp?winPath="+ winPath + "&linuxPath="+ linuxPath;
		window.open(createFHashAjaxQuery(path),"Burn","height=250,top="+ top + ",left="+ left + ",width=400" );
	}

}
function burnCD()
{
	var chks = document.getElementsByName("selectedRecord");
	var flg = false;
	for(var i=0; i<chks.length; i++)
		if(chks[i].checked)
		{
			flg = true;
			break;
		}
	if(!flg)
	{
		alert("Select at least one Profile to burn");
		chks[0].focus();
		return;
	}

	document.getElementsByName("hmode")[0].value="SETRECORDTOBURN";
	document.forms[0].submit();
}

function showProfile(index){
	
	var path="/HISClinical/mrd/cdBurn.cnt?hmode=SHOWPROFILE&index=" + index;
	window.open(createFHashAjaxQuery(path),"View Profile","height=600,width=600,scrollbars=yes");
		
}

function validateForm(){
	var valid=false;
	var selectedRecord=document.getElementsByName("selectedRecord");
	//alert(selectedRecord.length)
	for(var i=0;i<selectedRecord.length;i++){
		if(selectedRecord[i].checked){
			valid=true;
			break;
		}
	}
	//alert(valid)

	if(valid){
		if(validateExpectedReturnDate() && isEmpty(document.getElementsByName("handoverTo")[0],"Handed Over To"))
		{
			return true;
		
		}
		else{
			return false;
		}	
	}
	else{
		alert("Please Select at least one record")
		return false;	
	}
	

}


function clearForm(){
	var selectedRecord=document.getElementsByName("selectedRecord")
	for(var i=0;i<selectedRecord.length;i++){
		selectedRecord[i].checked=false;
	}
	document.getElementsByName("expectedReturnDate")[0].value=""
	document.getElementsByName("handoverTo")[0].value=""
	document.getElementsByName("handoverToName")[0].value=""
	document.getElementsByName("remarks")[0].value=""
}	

function updateStatus(){
	//alert("burn successfull")
}
</script>
<body>
<his:TransactionContainer>
<html:form action="/cdBurn">
	<his:TitleTag name="CD Burn">
	</his:TitleTag>
		<his:statusNew>
			<his:InputCrNoTag name="cdBurnFB">
			</his:InputCrNoTag>
		</his:statusNew>
		
		<his:statusRecordFound>
			<jsp:include page="/registration/patientDetail.cnt" flush="true"></jsp:include>
			<his:ContentTag>
				<his:SubTitleTag name="Record List">
				</his:SubTitleTag>
		     	<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="select"/></b>
								</font>
								</div>
							</td>
							<td width="90%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="profileHeader"/>
									</b>	
									</font>
								</div>
							</td>
					</tr>
					<logic:iterate id="patientProfileVO" name="<%=MrdConfig.PATIENT_PROFILE_VO_LIST%>" indexId="idx" type="hisglobal.vo.PatientProfileDetailVO">
			 		 <tr>
			 		 	<td class="tdfont">
						<div align="center">
						<html:checkbox name="cdBurnFB" property="selectedRecord" value="<%=String.valueOf(idx)%>" />
						</div>
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <a onclick="showProfile('<%=String.valueOf(idx)%>')" style="cursor: pointer;"><bean:write name="patientProfileVO" property="profileHeader"/></a>
					    	</font>
					     </div>   
						</td>
					 </tr>
              	</logic:iterate>
                </table>
               	<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="remarks"/>
							</font>
							</div>
						</td>
						<td width="70%" class="tdfont" >
							<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<html:textarea property="remarks" cols="90" rows="3"/>
							</font>
							</div>
						</td>
					</tr>
				</table>		
            	</his:ContentTag>
		</his:statusRecordFound>
		
		<his:statusDone>
			<%String codebase=request.getContextPath()+ "/hisglobal/utility/burnDisc/"; %> 
			<%CDBurnFB fb=(CDBurnFB)pageContext.findAttribute("cdBurnFB");%>
			<%//String path=MrdConfig.BURN_FILE_DIRECTORY_WINDOWS;
			String winPath=fb.getWinPathToBurn();
			String linuxPath=fb.getLinuxPathToBurn();%>
			<applet id="createFileApplet" name="createFileApplet" code="hisglobal.utility.burnDisc.CreateFileApplet" height="0" width="0"
					archive="JStarBurn.jar,burnDisc.jar" codebase='<%=codebase%>'>
			<param name="winPath" value="<%=winPath%>"/>		
			<param name="linuxPath" value="<%=linuxPath%>"/>		
			<param name="serverOS" value="<%=fb.getServerOS()%>"/>		
			<param name="fileName" value="<%=fb.getFileName()%>"/>		
		</applet>
		</his:statusDone>
		<his:ButtonToolBarTag>
			<his:statusRecordFound>
<!--			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" validateSave()" onkeypress="if(event.keyCode==13)validateSave()">
			<input type="button" name="burn" value="Burn" onclick="burnCD()"/>-->
			<img class="button" src='<his:path src="/hisglobal/images/Burn.png"/>'  style="cursor:pointer" tabindex="1" onclick =" burnCD()" onkeypress="if(event.keyCode==13) burnCD()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
			</his:statusRecordFound>
			<his:statusNew>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
			</his:statusNew>
			<his:statusUnsuccessfull>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
			</his:statusUnsuccessfull>
			
		</his:ButtonToolBarTag>

	<html:hidden name="cdBurnFB" property="hmode"/>
	<html:hidden name="cdBurnFB"  property="patCrNo" />
	<html:hidden name="cdBurnFB"  property="fileName" />
	<html:hidden name="cdBurnFB"  property="winPathToBurn" />
	<html:hidden name="cdBurnFB"  property="linuxPathToBurn" />
	<html:hidden name="cdBurnFB"  property="serverOS" />
	<html:hidden name="cdBurnFB"  property="hiddenRemarks" />
	
		
</html:form>
<his:status/>
</his:TransactionContainer>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();} %>
