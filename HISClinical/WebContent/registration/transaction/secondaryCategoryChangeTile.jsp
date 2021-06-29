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
<his:javascript src="/registration/js/registration.js" />
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

function getExpiryDays(obj){
	var categoryCode=obj.value
	var elementArraylength=document.getElementsByName('selectEpisode').length;
	var ExpiryDays;
	var index
	
	
	for(j=0;j<document.getElementsByName("newSecCatCode").length;j++)
	{
	if(document.getElementsByName("newSecCatCode")[j]==obj)
		{
		index=j
		}
	}
	
	var idDivDateControl="divDateControl"+index
	var secCatCodeAndExpiryDayObj=document.forms[0].secCatCodeAndExpiryDay.value
	var arrayObj=secCatCodeAndExpiryDayObj.split(':')
	
	if((categoryCode!='-1') && (categoryCode!='0') )
	{
	var i=0
		while(i<arrayObj.length)
		{
			if(arrayObj[i].substring(0,arrayObj[i].indexOf('#'))==categoryCode)
			{
				ExpiryDays=arrayObj[i].substring(arrayObj[i].indexOf('#')+1,arrayObj[i].length)
				if(ExpiryDays=="0")
					ExpiryDays="";
				break
			}
			i++
		}
		document.getElementsByName('arrValidUpto')[index].style.display ="block";
		//document.getElementById(idDivDateControl).style.display ="none";
		document.getElementsByName('categoryExpiryDate')[index].value="";
		document.getElementsByName('arrValidUpto')[index].disabled = false;
		document.getElementsByName('arrValidUpto')[index].value = ExpiryDays;
		document.getElementsByName('hiddenNewSecCatCode')[index].value = document.getElementsByName('newSecCatCode')[index].value;
	}
	else if((categoryCode=='-1') || (categoryCode=='0'))
	{
			document.getElementsByName('arrValidUpto')[index].value ="";
			//document.getElementById(idDivDateControl).style.display ="none";
			//document.getElementById("divDateText" + index).style.display ="none";
			//document.getElementsByName('arrValidUpto')[index].disabled =false;
	}
}
function validateSecondaryCategoryChange(){
	var a = 0;
	var b = 0;
	for(i=0;i<document.getElementsByName('selectEpisode').length;i++){
		if(document.getElementsByName('selectEpisode')[i].checked==true){
			a++;
			if (document.getElementsByName('newSecCatCode')[i].options[document.getElementsByName('newSecCatCode')[i].selectedIndex].value == "-1" ) {
				alert("Please Select Secondary Category");
				document.getElementsByName('newSecCatCode')[i].focus();
				return false;
			}
			else if (document.getElementsByName('arrRemarks')[i].value==null || document.getElementsByName('arrRemarks')[i].value==""){
				alert("Please Enter Reason");
				document.getElementsByName('arrRemarks')[i].focus();
				return false;
			}
		}			
	}
	
	for(i=0;i<document.getElementsByName('revokeChk').length;i++)
	{
		if(document.getElementsByName('revokeChk')[i].checked==true)
		{
			b++;
			if(document.getElementsByName("arrRemarks")[i].value=="")
			{
				alert("Please Enter Reason");
				document.getElementsByName('arrRemarks')[i].focus();
				return false;
			}
			
		}
	}
	
	
	if (a == 0 && b == 0)
	{
		alert('Please Select Atleast One Record');
		return false;
	}
	else 
	{
		//alert("else");
		return true;
	}		
}

function validateSave()
{
	if( validateSecondaryCategoryChange() && validateExtendDate())
		return true;
	else
		return false;	
}

function validateExtendDate()
{
	var valid=true;
	if(document.getElementsByName("isIpdFlag")[1].checked)
	{
		for(i=0;i<document.getElementsByName('selectEpisode').length;i++)
		{
			if(document.getElementsByName('selectEpisode')[i].checked)
			{
				if(document.getElementsByName('newSecCatCode')[i].value!="-1")
				{
					if(validateDate(document.getElementsByName('categoryExpiryDate')[i].value))
					{	
						valid=true;
					}	
					else
					{
						alert("Valid Upto Date Can Not Be Less Than System Date");
						valid=false;
						break;
					}		
				}	
			}
		}
	}
	else
	{
		valid=true;
	}
	//alert (valid)
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

function enableEpisode(i)
{
	k= parseInt(i);
	var elementArraylength=document.getElementsByName('selectEpisode').length;
	
//	for(k=0;k<elementArraylength;k++)
//	{
		var idDivDateControl="divDateControl"+i
		var idDivDateText="divDateText"+i
		
		if(document.getElementsByName('selectEpisode')[i].checked==true)
		{
			//alert(document.getElementById(idDivDateControl).style.display)
			document.getElementById(idDivDateText).style.display ="none";
			//alert(document.getElementsByName('arrValidUpto')[i].style.display)
			if(document.getElementsByName('arrValidUpto')[i].style.display=="none")
			{
				document.getElementById(idDivDateControl).style.display ="block";
			}
			else
			{
				document.getElementById(idDivDateControl).style.display ="none";
			}	
			document.getElementsByName("revokeChk")[i].checked=false;
		}
		else
		{
			document.getElementById(idDivDateControl).style.display ="none";
			document.getElementById(idDivDateText).style.display ="block";
			
		}
		//alert("idDivDateText"+idDivDateText)
		//alert("idDivDateControl"+idDivDateControl)
		if(document.getElementsByName('selectEpisode')[k].checked==true)
		{
			if(document.getElementsByName("newSecCatCode")[k].value=="-1" || 
					document.getElementsByName('revokeChk')[k].disabled==true)
				document.getElementsByName('newSecCatCode')[k].disabled = false;
			document.getElementsByName('arrRemarks')[k].readOnly = false;
			document.getElementsByName('arrValidUpto')[k].disabled = false;
			document.getElementsByName('categoryExpiryDate')[k].disabled = false;
			document.getElementsByName("selectEpisodeVisitNo")[k].disabled=false;
			document.getElementsByName("hiddenNewSecCatCode")[k].disabled=false;
		}
		else
		{
			document.getElementsByName('newSecCatCode')[k].disabled =true;
			document.getElementsByName('arrRemarks')[k].readOnly =true;
			document.getElementsByName('arrValidUpto')[k].disabled = true;
			document.getElementsByName('categoryExpiryDate')[k].disabled = true;
			document.getElementsByName("selectEpisodeVisitNo")[k].disabled=true;
			document.getElementsByName("hiddenNewSecCatCode")[k].disabled=true;
		}		
	//}	
}

function clearForm(){
	var elementArraylength=document.getElementsByName('selectEpisode').length;
	for(k=0;k<elementArraylength;k++)
	{
		if(!document.getElementsByName('newSecCatCode')[k].disabled)
			document.getElementsByName('newSecCatCode')[k].value="-1"
		document.getElementsByName('arrRemarks')[k].value=""
		document.getElementsByName('arrValidUpto')[k].value=""
		document.getElementsByName('selectEpisode')[k].checked=false
		enableEpisode(k)
	}
}

function enableIpdReason()
{
	if(document.getElementsByName("selectEpisode")[0].checked)
	{
	//	if(document.getElementsByName("newSecCatCode")[0].value=="-1")
			document.getElementsByName("newSecCatCode")[0].disabled=false;
		document.getElementsByName("arrRemarks")[0].readOnly=false;
		document.getElementsByName("selectEpisodeVisitNo")[0].disabled=false;
		document.getElementsByName("hiddenNewSecCatCode")[0].disabled=false;
		
		document.getElementsByName("revokeChk")[0].checked=false;
	}
	else
	{
		document.getElementsByName("newSecCatCode")[0].disabled=true;
		document.getElementsByName("arrRemarks")[0].readOnly=true;
		document.getElementsByName("selectEpisodeVisitNo")[0].disabled=true;
		document.getElementsByName("arrRemarks")[0].value="";
		document.getElementsByName("hiddenNewSecCatCode")[0].disabled=true;
		document.getElementsByName("newSecCatCode")[0].value="-1";
	}
}

function validateRevoke()
{
	var count=0;
	
	for(var i=0;i<document.getElementsByName("revokeChk").length;i++)
	{
		if(document.getElementsByName("revokeChk")[i].checked)
		{
			count++;
			if(document.getElementsByName("arrRemarks")[i].value=="")
			{
				alert("Please Enter Reason");
				document.getElementsByName('arrRemarks')[i].focus();
				return false;
			}
		}	
	}
	
	if(count==0)
	{
		alert("Please Select At Least One Record To Revoke ");
		return false;
	}
	else
	{
		submitForm('REVOKE');
	}
	
}

function enableRevokeReason(i)
{
	
	if(document.getElementsByName("revokeChk")[i].checked)
	{
		
		document.getElementsByName("selectEpisode")[i].checked=false;
		enableEpisode(i);
		document.getElementsByName("arrRemarks")[i].readOnly=false;
	}
	else
	{
		document.getElementsByName("arrRemarks")[i].readOnly=true;
	}
}

function enableIpdRevokeReason()
{
	if(document.getElementsByName("revokeChk")[0].checked)
	{
		document.getElementsByName("selectEpisode")[0].checked=false;
		enableIpdReason();
		document.getElementsByName("arrRemarks")[0].readOnly=false;
	}
	else
	{
		document.getElementsByName("arrRemarks")[0].readOnly=true;
	}
}

</script>

<%@page
	import="java.util.*,registration.*,hisglobal.vo.*, registration.controller.fb.SecondaryCategoryChangeFB.*,registration.controller.util.SecondaryCategoryChangeUTIL,hisglobal.utility.Entry"%>
<%
	String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
	%>
<his:TitleTag>
	<his:name>
		<bean:message key="changePatientSecondaryCategory" />
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

<his:InputCrNoTag name="SecondaryCategoryChangeFB">
</his:InputCrNoTag>

<%
if (!flagIsStatusNew) {
%>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />

	<logic:equal name="SecondaryCategoryChangeFB" property="isInvalidCatCode" value="<%=RegistrationConfig.NO %>">
		<his:SubTitleTag name="">
			<div align="left">
				&nbsp;IPD
				<html:radio name="SecondaryCategoryChangeFB" property="isIpdFlag" value="<%=RegistrationConfig.YES %>" onclick="submitForm('GETEPISODE')" tabindex="1"></html:radio>
				&nbsp;OPD
				<html:radio name="SecondaryCategoryChangeFB" property="isIpdFlag" value="<%=RegistrationConfig.NO %>" onclick="submitForm('GETEPISODE')" tabindex="1"></html:radio>
				
			</div>	
		</his:SubTitleTag>
	</logic:equal>

<his:statusTransactionInProcess>

	<!-- .......Code for Selection of Secondary Category........ -->
	<bean:define name="SecondaryCategoryChangeFB" property="sysDate" id="sysDate" type="java.lang.String" />
	<%
		if(sysDate==null||sysDate.equalsIgnoreCase(""))
		{
			sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		}
	%>

	<html:hidden name="SecondaryCategoryChangeFB" property="sysDate" value="<%=sysDate%>"/>
	

	<logic:notEmpty name="<%=RegistrationConfig.ARR_OPEN_EPISODE_VO%>">
		<bean:define id="OPEN_EPISODES" name="<%=RegistrationConfig.ARR_OPEN_EPISODE_VO%>" type="hisglobal.vo.EpisodeVO[]" />

		<%
				System.out.println("OPEN_EPISODES.length... "+ OPEN_EPISODES.length);
		%>

		<logic:equal name="SecondaryCategoryChangeFB" property="isIpdFlag" value="<%=RegistrationConfig.NO %>">
			<his:ContentTag>
	
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<%
					
					int i = 0;
					%>
					<tr>
						<td width="4%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="select" />
								</b>	
							</font>
						</td>
						<td width="8%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="revoke" />
									</b>
								</font>
							</div>
						</td>
	
						<td width="17%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b> 
										<bean:message key="department" />
									</b>
								</font>
							</div>
						</td>
	
						<td width="17%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="department" />
										<bean:message key="unit" />
									</b>
								</font>
							</div>
						</td>
						
						<td width="18%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<b>
										<bean:message key="secondary" />
										<bean:message key="category" />
									</b>
								</font>
							</div>
						</td>
						
						<td width="17%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<b>
										<bean:message key="reason" />
									</b>
								</font>
							</div>
						</td>
						
						<td width="17%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="validUpto" />
									</b>
								</font>
							</div>
						</td>
						
						
	
						
						
					<logic:iterate id="COLL_OPEN_EPISODE_VO" name="<%=RegistrationConfig.COLL_OPEN_EPISODE_VO%>" indexId="j">
						<bean:define name="COLL_OPEN_EPISODE_VO" property="episodeCode" id="episodeCode" />
						<bean:define name="COLL_OPEN_EPISODE_VO" property="newSecCatCode" id="patSecondaryCatCode" />
						<bean:define name="COLL_OPEN_EPISODE_VO" property="episodeVisitNo" id="episodeVisitNo" />
						<bean:define name="COLL_OPEN_EPISODE_VO" property="hiddenNewSecCatCode" id="patSecondaryCatCode" />	
							
						<tr>
							<td width="4%" class="tdfont">
								<div align="center">
									<input type="checkbox" name="selectEpisode" value="<%=j.toString()  %>" onclick="enableEpisode('<%=i%>');"  tabindex="1"/>
								</div>
							</td>
							<%String revokeChkValue=episodeCode+"@"+episodeVisitNo; %>
							<%String str="enableRevokeReason("+i+")"; %>
							<%if(!patSecondaryCatCode.equals("-1")) {%>
							<td width="8%" class="tdfont">
								<div align="center">
									<html:checkbox name="SecondaryCategoryChangeFB" property="revokeChk" value="<%=j.toString() %>" disabled="false" onclick="<%=str %>"></html:checkbox> 									
								</div>
							</td>
							<%}else{ %>
							<td width="8%" class="tdfont">
								<div align="center">
									<html:checkbox name="SecondaryCategoryChangeFB" property="revokeChk" value="<%=j.toString()  %>" disabled="true" onclick="<%=str %>"></html:checkbox>
								</div>
							</td>	
							<%} %>
	
							<td width="17%" class="tdfont">
								<div align="center">
									<bean:write name="COLL_OPEN_EPISODE_VO" property="department" />
								</div>
							</td>
	
							<td width="17%" class="tdfont">
								<div align="center">
									<bean:write name="COLL_OPEN_EPISODE_VO" property="departmentUnit" />
								</div>
							</td>
	
	
							<td width="18%" class="tdfont">
								<div align="center">
									<html:select name="COLL_OPEN_EPISODE_VO" property="newSecCatCode" tabindex="1" styleClass="regcbo" disabled="true" onchange="getExpiryDays(this);">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY%>">
											<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY%>" property="value" labelProperty="label" />
										</logic:present>	
									</html:select>
								</div>
								<%
								String hh = episodeCode + "|" + patSecondaryCatCode;
								%> 
								<html:hidden property="identifySecCatCode" name="SecondaryCategoryChangeFB" value="<%=hh%>" />
							</td>
	
							<td width="17%" class="tdfont">
								<div align="center">
									<html:text name="COLL_OPEN_EPISODE_VO" tabindex="1" property="arrRemarks" maxlength="150" styleClass="textbox" size="20" readonly="true" onkeypress="return CheckMaxLength(event,this,150,3)" />
								</div>
							</td>
							
							<%String valueDisplay="" ;
							String expiryDateValue="";%>
							
							<logic:present name="COLL_OPEN_EPISODE_VO" property="validUpto">
								<%valueDisplay="display:none" ;%>
								<bean:define name="COLL_OPEN_EPISODE_VO" property="validUpto" id="patExpiryDate" type="java.lang.String" />
								<% expiryDateValue=patExpiryDate;%> 
							</logic:present>
							<logic:notPresent name="COLL_OPEN_EPISODE_VO" property="validUpto">
								<%valueDisplay="display:block" ;%>
							</logic:notPresent>
							<td width="17%" class="tdfont" nowrap="nowrap">
								<div align="center">
									<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
										<html:text name="SecondaryCategoryChangeFB" tabindex="1" property="arrValidUpto" maxlength="3" styleClass="textbox" size="5" style="<%= valueDisplay%>"  onkeypress="return validateNumeric(event)"/>
									</font>
								</div>
								<div id='divDateControl<%=i%>'  align="center" style="display:none" >  
									<logic:present name="COLL_OPEN_EPISODE_VO" property="validUpto">
										<his:date name='categoryExpiryDate' dateFormate="%d-%b-%Y" value="<%=expiryDateValue%>"  />
									</logic:present>
									<logic:notPresent name="COLL_OPEN_EPISODE_VO" property="validUpto">
										<html:hidden name="SecondaryCategoryChangeFB" property="categoryExpiryDate"/>
									</logic:notPresent>
	    	 					</div>
		    	 				<div id='divDateText<%=i%>'  align="center" style="display:block" >    
									<bean:write name="COLL_OPEN_EPISODE_VO" property="validUpto"/>
		    	 				</div>
							<%String visitNo=(String)episodeVisitNo; %>
							<input type="hidden" name="selectEpisodeVisitNo" value="<%=visitNo %>" disabled="disabled"/>
							<%String hiddenSecCode=(String)patSecondaryCatCode; %>
							<input type="hidden" name="hiddenNewSecCatCode" value="<%=hiddenSecCode %>" disabled="disabled"/>
							</td>
							
							
						<%i++; %>	
							
	
						</tr>
					</logic:iterate>
	
				</table>
			</his:ContentTag>
		</logic:equal>
		
		<logic:equal name="SecondaryCategoryChangeFB" property="isIpdFlag" value="<%=RegistrationConfig.YES %>">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<%
					
					int k = 0;
					%>
					<tr>
						<td width="4%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="select" />
									</b>
								</font>
							</div>
						</td>
						<td width="8%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="revoke" />
									</b>
								</font>
							</div>
						</td>
	
	
						<td width="22%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b> 
										<bean:message key="department" />
									</b>
								</font>
							</div>
						</td>
	
						<td width="22%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="department" />
										<bean:message key="unit" />
									</b>
								</font>
							</div>
						</td>
						
						<td width="22%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<b>
										<bean:message key="secondary" />
										<bean:message key="category" />
									</b>
								</font>
							</div>
						</td>
						
						<td width="22%" class="tdfonthead" style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<b>
										<bean:message key="reason" />
									</b>
								</font>
							</div>
						</td>
									
						
					<logic:iterate id="COLL_OPEN_EPISODE_VO" name="<%=RegistrationConfig.COLL_OPEN_EPISODE_VO%>" indexId="j">
						<bean:define name="COLL_OPEN_EPISODE_VO" property="episodeCode" id="episodeCode" />
						<bean:define name="COLL_OPEN_EPISODE_VO" property="newSecCatCode" id="patSecondaryCatCode" />
						<bean:define name="COLL_OPEN_EPISODE_VO" property="episodeVisitNo" id="episodeVisitNo" />
						<bean:define name="COLL_OPEN_EPISODE_VO" property="hiddenNewSecCatCode" id="patSecondaryCatCode" />
							
						<tr>
							<%if(patSecondaryCatCode.equals("-1")) {%>
								<td width="4%" class="tdfont">
									<div align="center">
										<html:checkbox name="SecondaryCategoryChangeFB" property="selectEpisode" value="<%=j.toString()  %>" disabled="false" onclick="enableIpdReason()"></html:checkbox>
									</div>
								</td>
							<%}else{ %>
								<td width="4%" class="tdfont">
									<div align="center">
										<html:checkbox name="SecondaryCategoryChangeFB" property="selectEpisode" value="<%=j.toString()  %>" disabled="true" onclick="enableIpdReason()"></html:checkbox>
									</div>
								</td>
							<%} %>
							<%String revokeChkValue=episodeCode+"@"+episodeVisitNo; %>
							<%if(!patSecondaryCatCode.equals("-1")) {%>
							
							<td width="8%" class="tdfont">
								<div align="center">
									<html:checkbox name="SecondaryCategoryChangeFB" property="revokeChk" value="<%=j.toString()  %>" disabled="false" onclick="enableIpdRevokeReason()"></html:checkbox> 									
								</div>
							</td>
							<%}else{ %>
							<td width="8%" class="tdfont">
								<div align="center">
									<html:checkbox name="SecondaryCategoryChangeFB" property="revokeChk" value="<%=j.toString()  %>" disabled="true" onclick="enableIpdRevokeReason()"></html:checkbox>
								</div>
							</td>	
							<%} %>
	
							<td width="22%" class="tdfont">
								<div align="center">
									<bean:write name="COLL_OPEN_EPISODE_VO" property="department" />
								</div>
							</td>
	
							<td width="22%" class="tdfont">
								<div align="center">
									<bean:write name="COLL_OPEN_EPISODE_VO" property="departmentUnit" />
								</div>
							</td>
	
							<td width="22%" class="tdfont">
								<div align="center">
									<html:select name="COLL_OPEN_EPISODE_VO" property="newSecCatCode" tabindex="1" styleClass="regcbo" disabled="true" onchange="getExpiryDays(this);">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY%>">
											<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY%>" property="value" labelProperty="label" />
										</logic:present>	
									</html:select>
								</div>
								<%
								String hh = episodeCode + "|" + patSecondaryCatCode;
								%> 
								<html:hidden property="identifySecCatCode" name="SecondaryCategoryChangeFB" value="<%=hh%>" />
							</td>
	
							<td width="22%" class="tdfont">
								<div align="center">
									<html:text name="COLL_OPEN_EPISODE_VO" tabindex="1" property="arrRemarks" maxlength="150" styleClass="textbox" size="20" readonly="true" onkeypress="return CheckMaxLength(event,this,150,3)" >
									</html:text>
								</div>
							</td>
							
							
							
							<html:hidden name="SecondaryCategoryChangeFB" property="arrValidUpto"/>
							<html:hidden name="SecondaryCategoryChangeFB" property="categoryExpiryDate"/>
							<%String visitNo=(String)episodeVisitNo; %>
							<input type="hidden" name="selectEpisodeVisitNo" value="<%=visitNo %>" disabled="disabled" />
							<%String hiddenSecCode=(String)patSecondaryCatCode; %>
							<input type="hidden" name="hiddenNewSecCatCode" value="<%=hiddenSecCode %>" disabled="disabled"/>
							<%k++; %>	
						
						</tr>
					</logic:iterate>
	
				</table>
			</his:ContentTag>
		</logic:equal>


	</logic:notEmpty>

	<%--  </his:statusNew>	--%>

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
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) if (validateSave()) submitTile('SAVE');" tabindex="1" onclick=" if (validateSave()) submitTile('SAVE');">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" onclick="submitForm('NEW');">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
		
		
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

<his:status />

<html:hidden name="SecondaryCategoryChangeFB" property="hmode" value="unspecified"/>
<html:hidden name="SecondaryCategoryChangeFB" property="secCatCodeAndExpiryDay"/>
<html:hidden name="SecondaryCategoryChangeFB" property="isInvalidCatCode"/>


