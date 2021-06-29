<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="registration.*"%>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.presentation.WebUTIL"%>

<%@page import="hisglobal.Status"%><his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function validateMCTS()
{
	if((document.getElementsByName('selectEpisode')[0].value=="")||(document.getElementsByName('selectEpisode')[0].value=="0"))
	{
		//alert('Please select at leat one episode')
	}
	if(document.getElementsByName('strMctsNo')[0].value=="")
	{
		alert("Please enter MCTS No.")
		return false;
	}
	if(document.getElementsByName('strMctsNo')[0].value.length!=18)
	{
		alert("Please enter MCTS No. of 18 digits")
		return false;
	}
	
	return true

}
function validateSave()
{
	if( validateMCTS() && validateRegDate())
		return true;
	else
		return false;	
}

function validateRegDate()
{
	
					if(validateDate(document.getElementsByName('strMctsRegistrationDate')[0].value))
					{	
						valid=true;
					}	
					else
					{
						alert("MCTS Registration Date Can Not Be Greater Than System Date");
						valid=false;
						return false;
					}
					if(validateFrmBirthDate(document.getElementsByName('strMctsRegistrationDate')[0].value))
					{	
						valid=true;
					}	
					else
					{
						alert("MCTS Registration Date Can Not Be Less Than Birth Date : "+ document.getElementsByName("patDOB")[0].value);
						valid=false;
						return false;
					}
	return valid;		
}

function validateDate(obj)
{
	valid=true;
	var b=document.getElementsByName("sysDate")[0].value;
	
    var aArray=obj.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
    
      
    var bArray=b.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
    
     day=(adate-bdate)/86400000;
    
     if(day>0)
    	return false;
    else
    	return true;	
}
function validateFrmBirthDate(obj)
{
	valid=true;
	var b=document.getElementsByName("patDOB")[0].value;
	
    var aArray=obj.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
    
      
    var bArray=b.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
    
     day=(adate-bdate)/86400000;
    
     if(day<0)
    	return false;
    else
    	return true;	
}


function submitTile(mode){
	document.getElementsByName("hmode")[0].value = mode;
	document.forms[0].submit();
}
	
function callThisOnload(){
	focusCrNo();
}



function clearForm(){
	document.getElementsByName('strMctsNo')[0].value="";
	document.getElementsByName('strRemarks')[0].value="";
	
}

</script>

<%@page
	import="java.util.*,registration.*,hisglobal.vo.*, registration.controller.fb.MCTSRegistrationFB.*,registration.controller.util.SecondaryCategoryChangeUTIL,hisglobal.utility.Entry"%>
<%
	String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
	%>
<his:TitleTag>
	<his:name>
		<bean:message key="mctsRegistration" />
	</his:name>
	<b> <font  size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> 	
	</font> </b>
</his:TitleTag>



<%
	boolean flagIsStatusNew = false;
	String varStatus = "";
	boolean varIsNewStatus = false;
%>
<his:statusNew>
	<%
		flagIsStatusNew = true;
		varIsNewStatus = true;
		varStatus = "New";
	%>
</his:statusNew>

<his:InputCrNoTag name="MCTSRegistrationFB">
</his:InputCrNoTag>

<%
if (!flagIsStatusNew) {
%>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />

	<logic:present name="MCTSRegistrationFB" property="strMctsNo" >
	<his:TitleTag name="MCTS Registration	already exist!!"><div ><b> <font  size="2"
		face="Verdana, Arial, Helvetica, sans-serif">
		  MCTS No.<bean:write name="MCTSRegistrationFB" property="strMctsNo" />
	</font> </b></div>
	</his:TitleTag>
	</logic:present>
	<logic:present name="MCTSRegistrationFB" property="strIsMale" >
	<his:TitleTag name="MCTS Registration against Male patients of Age>14 can not be done!!">
	</his:TitleTag>
	</logic:present> 
	
<his:statusTransactionInProcess>

	<!-- .......Code for Insertyion of MCTS No........ -->
	
	<bean:define name="MCTSRegistrationFB" property="sysDate" id="sysDate" type="java.lang.String" />
	
<%
		if(sysDate==null||sysDate.equalsIgnoreCase(""))
		{
			sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		}
	%>
	<html:hidden name="MCTSRegistrationFB" property="sysDate" value="<%=sysDate%>"/>
	

	<logic:notEmpty name="<%=RegistrationConfig.ARR_LATEST_EPISODE_VO%>">
		<bean:define id="OPEN_EPISODES" name="<%=RegistrationConfig.ARR_LATEST_EPISODE_VO%>" type="hisglobal.vo.EpisodeVO[]" />
		<his:ContentTag>
	
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="10%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="select" />
								</b>	
							</font>
						</td>
						
	
						<td width="30%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b> 
										<bean:message key="department" />
									</b>
								</font>
							</div>
						</td>
	
						<td width="30%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="department" />
										<bean:message key="unit" />
									</b>
								</font>
							</div>
						</td>
						
						<td width="30%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="lastVisitdate" />
									</b>
								</font>
							</div>
						</td>
					
						</tr>
						
					<logic:iterate id="COLL_LATEST_EPISODE_VO" name="<%=RegistrationConfig.COLL_LATEST_EPISODE_VO%>" indexId="j">
						<bean:define name="COLL_LATEST_EPISODE_VO" property="episodeCode" id="episodeCode" />
							
						<tr>
							<td width="4%" class="tdfont">
								<div align="center">
								<html:radio name="MCTSRegistrationFB" property="selectEpisode" tabindex="1" value="<%=j.toString()  %>" />
								</div>
							</td>
							
	
							<td width="17%" class="tdfont">
								<div align="center">
									<bean:write name="COLL_LATEST_EPISODE_VO" property="department" />
								</div>
							</td>
	
							<td width="17%" class="tdfont">
								<div align="center">
									<bean:write name="COLL_LATEST_EPISODE_VO" property="departmentUnit" />
								</div>
							</td>
	
	
							<td width="18%" class="tdfont">
							<div align="center">
							<bean:write name="COLL_LATEST_EPISODE_VO" property="episodeDate" />
							</div>
							</td>
						</tr>
					</logic:iterate>
	
				</table>
			</his:ContentTag>
		
		</logic:notEmpty>

		<his:ContentTag>
	
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
				
				<tr>
						<td width="25%" class="tdfonthead" >
							<div  align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									MCTS No.
								</b>	
							</font>
						</td>
							<td width="25%" class="tdfont" >
							<div  align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<html:text name="MCTSRegistrationFB" tabindex="1" property="strMctsNo" maxlength="18"  size="25" onkeypress="validateAlphaNumericWithSpecialCharacterOnly(event)"  value=""/>								
								</b>	
							</font>
							</div>
						</td>
						
					<td class="tdfonthead" width="25%">
        		<div id='divfromDate'  align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>	<bean:message key="mctsRegistration"/>	<bean:message key="date"/></b>
					</font>
        		</div>
        		
			</td>
        
 			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' align="left">	               		 
					<his:date name='strMctsRegistrationDate' dateFormate="%d-%b-%Y" value="<%=sysDate%>"/>
					
				</div>
		 		
		
			</td>
			</tr>
			<tr>
						<td width="25%" class="tdfonthead" >
							<div  align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="remarks" />
								</b>	
							</font>
							</div>
						</td>
							<td width="25%" class="tdfont" >
							<div  align="left">
							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								 <html:textarea name="MCTSRegistrationFB" property="strRemarks" tabindex="1"  onkeypress="return CheckMaxLength(event,this,100,3)"  tabindex="1" 
          						  style="width: 180px;font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 12;height:40px;color: #000000;"/>
     
								</b>	
							</font>
							</div>
						</td>
							<td width="25%" class="tdfonthead" >
							<div  align="right">
							
							</div>
						</td>
							<td width="25%" class="tdfont" >
							<div  align="right">
							
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>

	<!--- End ReferInternal ---- Details of all open episodes-->


	<!-- ........End...Code for Selection of Secondary Category..... -->


</his:statusTransactionInProcess>
<%
}
%>


<!-- ...............Code for Button Tool Bar.......... -->
<his:ButtonToolBarTag>

	<his:statusInProcessWithJsp>
		<%
		varStatus = "InProcess";
		%>
	</his:statusInProcessWithJsp>
	<his:statusUnsuccessfull>
		<%
		varStatus = "Unsuccessful";
		%>
	</his:statusUnsuccessfull>

	<%
	if (varStatus.equals("InProcess")) {
	%>
	<div align="center">
		<logic:notPresent name="MCTSRegistrationFB" property="strIsMale" >
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) if (validateSave()) submitTile('SAVE');" tabindex="1" onclick=" if (validateSave()) submitTile('SAVE');">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" onclick="submitForm('NEW');">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
		</logic:notPresent>
		<logic:present name="MCTSRegistrationFB" property="strIsMale" >
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" onclick="submitForm('NEW');">
		</logic:present>
	</div>
	<%
	} else {
	%>
	<div align="center">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" onclick="submitPage('CANCEL');">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</div>
	<%
	}
	%>
</his:ButtonToolBarTag>
<!-- .......End........Code for Button Tool Bar.......... -->

<%System.out.println(request.getAttribute("fromICDENtryForm")); %>
<%if(request.getAttribute("fromICDENtryForm") == null) {
	
%>
	<his:status/>
<% }%>


<html:hidden name="MCTSRegistrationFB" property="hmode" value="unspecified"/>
<html:hidden name="MCTSRegistrationFB" property="patDOB"/>


