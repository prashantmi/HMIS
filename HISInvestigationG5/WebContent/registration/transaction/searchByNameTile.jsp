<%@ page import ="registration.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="registration.controller.fb.SearchByNameFB"%>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js" />
<script type="text/javascript">

function crNoSelected(crNoObj){
// alert(crNoObj.value);
//var crno1=(crNoObj.value).substr(0,3);
//var crno2=(crNoObj.value).substr(3,2);
//var crno3=(crNoObj.value).substr(5,8);
// alert(crno1+"-"+crno2+"-"+crno3)
//opener.document.getElementsByName("crNo1")[0].value=crno1;
//opener.document.getElementsByName("crNo2")[0].value=crno2;
//opener.document.getElementsByName("crNo3")[0].value=crno3;
opener.document.getElementsByName("patCrNo")[0].value=crNoObj.value;

opener.document.getElementsByName("hmode")[0].value='GETPATDTL';
opener.document.forms[0].submit();
self.close();
}

function validateLocationOrFileNumber()
{
var client=<%=Config.CLIENT%>
	var clinetPGIMER=<%=Config.CLIENT_PGIMER%>
	var valid=false;
	if(client!=clinetPGIMER)
	{
		if(document.forms[0].gnum_city_loc_code.value!="-1")
		{
			valid= true
		} 
	}
	else
	{
		if(document.forms[0].hrgstr_file_no.value!="") 
		{
			valid= true
		}
	}
	return valid
}

function submitSearch(){
	var valid=false;
	
	if(document.forms[0].hrgstr_fname.value!="" ||
	document.forms[0].hrgstr_mname.value!="" ||
	document.forms[0].hrgstr_lname.value!="" ||
	document.forms[0].gstr_gender_code.value!="-1" ||
	document.forms[0].hrgstr_age.value!="-1" ||
	//validateLocationOrFileNumber() ||
	document.forms[0].hrgstr_father_name.value!="" ||
	//document.forms[0].hrgnum_id_no.value!=""||
	document.forms[0].fromDate.value!="" ||
	//document.forms[0].toDate.value!="" 
	document.forms[0].hancstr_mcts_no.value!=""	
	)
	{
	valid=true
		if(validateDate()){
			document.getElementsByName("hmode")[0].value='SEARCHBYNAME';
			document.forms[0].submit();
		}
	}
	else
	{
		valid=false
		//alert("Please Enter Value In Atleast One Field")
		alert("Please Enter Search Criteria");
		
	}
	return valid
}

function closeForm()
{
	self.close();
}

function validateDate(){
	var fromDate=document.getElementsByName("fromDate")[0]
	var toDate=document.getElementsByName("toDate")[0]
	var sysdate=document.getElementsByName("sysdate")[0]
	if(fromDate.value!="" && toDate.value!=""){
		if(!compareDate(fromDate,toDate,2)){
			alert("From Date cannot be greater than To Date")
			return false;
		}
	}	
	if(!compareDate(fromDate,sysdate,2)){
			alert("From Date cannot be greater than Current Date")
			return false;
	}
	if(!compareDate(toDate,sysdate,2)){
			alert("To Date cannot be greater than Current Date")
			return false;
	}
	return true;
	
}

</script>
<html:form action="/searchByNamePopup" >
<his:TitleTag>
<table width="100%">
<tr><td width="40%" >
		  <his:name>
		  <bean:message key="search"/>
		  </his:name></td>
		  <td width="60%" >
			<td width="30%">
			<div align="center">
			<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<B><bean:message key="hospital"/></B>
			</font></div>
			</td>
			<td width="30%" class="tdfonthead">
			<div align="center">
			<html:select name="SearchByNameFB" property="gnum_hospital_code" styleClass="regcbo" tabindex="1">
			<!--			<html:option value="%">All</html:option>-->
			<logic:present name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST %>">
			<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST %>" labelProperty="label" property="value"/>
			</logic:present>
		</html:select>
		</div>
		</td>
			
		  </td>
</tr>
</table>			  
      	  
</his:TitleTag>
<his:SubTitleTag name="">
	<tr>
		<td width="30%" >
		<div align="center">
		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<B><bean:message key="firstName"/></B>
		</font></div>
		</td>
		<td width="30%" >
		<div align="center">
		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<B><bean:message key="middleName"/></B>
		</font></div>
		</td>
		<td width="30%" >
		<div align="center">
		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<B><bean:message key="lastName"/></B>
		</font></div>
		</td>
</tr>
	
</his:SubTitleTag>
<his:ContentTag>
<table width="100%" cellpadding="0" cellspacing="1">

<tr>
		<td width="30%" class="tdfonthead">
		<div align="center">
		<html:text name="SearchByNameFB" property="hrgstr_fname" tabindex="1" maxlength="30" onkeypress="if(event.keyCode!=13) return validateAlphabetsWithDotsOnly(event,this); else submitSearch();"/>
		</div>
		</td>
		<td width="30%" class="tdfonthead">
		<div align="center">
		<html:text name="SearchByNameFB" property="hrgstr_mname" tabindex="1" maxlength="20" onkeypress="if(event.keyCode!=13) return validateAlphabetsWithDotsOnly(event,this); else submitSearch();"/>
		</div>
		</td>
		<td width="30%" class="tdfonthead">
		<div align="center">
		<html:text name="SearchByNameFB" property="hrgstr_lname" tabindex="1" maxlength="30" onkeypress="if(event.keyCode!=13) return validateAlphabetsWithDotsOnly(event,this); else submitSearch();"/>
		</div>
		</td>
</tr>
</table>
</his:ContentTag>
<his:SubTitleTag name="">
	<tr>		
		<td width="30%" >
		<div align="center">
		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<B><bean:message key="gender"/></B>
		</font></div>
		</td>
		<td width="30%"  >
		<div align="center">
		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<B><bean:message key="ageRange"/></B>
		</font></div>
		</td>
		</tr>
	
</his:SubTitleTag>
<his:ContentTag>
<table width="100%" cellpadding="0" cellspacing="1">

<tr>
		
		<td width="30%" class="tdfonthead">
		<div align="center">
		<html:select name="SearchByNameFB" property="gstr_gender_code" styleClass="regcbo" tabindex="1">
			<html:option value="-1">Select Value</html:option>
			<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>">
			<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER %>" labelProperty="label" property="value"/>
			</logic:present>
		</html:select>
		</div>
		</td>
		<td width="30%" class="tdfonthead">
		<div align="center">
		<html:select name="SearchByNameFB" property="hrgstr_age" styleClass="regcbo" tabindex="1">
			<html:option value="-1">Select Value</html:option>
			<html:option value="0-1">Below 1 Year</html:option>
			<html:option value="1-5">1-5 Year</html:option>
			<html:option value="5-12">5-12 Year</html:option>
			<html:option value="12-18">12-18 Year</html:option>
			<html:option value="18-30">18-30 Year</html:option>
			<html:option value="30-40">30-40 Year</html:option>
			<html:option value="40-60">40-60 Year</html:option>
			<html:option value="60-125">60-125 Year</html:option>
			
		</html:select>
		</div>
		</td>
		</tr>
	</table>
</his:ContentTag>
<his:SubTitleTag name="">
	<tr>		
		<td width="30%" >
		<div align="center">
		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<B><bean:message key="fathersName"/></B>
		</font></div>
		</td>
		<%-- <td width="30%"  >
		<div align="center">
		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
<!--		<B><bean:message key="employeeid"/></B>-->
		<B><bean:message key="mctsNo"/></B>
		</font></div>
		</td> --%>
		<td width="30%" >
		<div align="center">
		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<B><bean:message key="registrationDate"/></B>
		</font></div>
		</td>
		
		</tr>
	
</his:SubTitleTag>
<his:ContentTag>
	<table width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="30%" class="tdfonthead" rowspan="2">
			<div align="center">
			<html:text name="SearchByNameFB" property="hrgstr_father_name" maxlength="60" tabindex="1" onkeypress="if(event.keyCode!=13) return validateAlphabetsWithDotsOnly(event,this); else submitSearch();"/>
			</div>
			</td>
<!--			<td width="30%" class="tdfonthead" rowspan="2">-->
<!--			<div align="center">-->
<!--			<html:text name="SearchByNameFB" property="hrgnum_id_no" maxlength="16" tabindex="1" onkeypress="if(event.keyCode!=13) return validateAlphaNumericOnly(event,this); else submitSearch();"/>-->
<!--			</div>-->
<!--			</td>-->
			<%-- <td width="30%" class="tdfonthead" rowspan="2">
			<div align="center">
			<html:text name="SearchByNameFB" property="hancstr_mcts_no" maxlength="20" tabindex="1" onkeypress="if(event.keyCode!=13) return validateAlphaNumericOnly(event,this); else submitSearch();"/>
			</div>
			</td> --%>
			<td width="30%" class="tdfonthead">
			<table width="100%">
			<tr><td class="tdfonthead" width="40%">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<b><bean:message key="fromDate"/></b>
			</font>
			</td>
			<td class="tdfont" width="60%">
			<%SearchByNameFB fb=(SearchByNameFB)pageContext.findAttribute("SearchByNameFB"); %>
			<his:date name="fromDate" value='<%=(fb.getFromDate()!=null?fb.getFromDate():"") %>'></his:date>
			</td></tr></table>
			</td>
		</tr>
		<tr>
			<td width="35%" class="tdfonthead">
			<table width="100%">
			<tr><td class="tdfonthead" width="40%">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<b><bean:message key="toDate"/></b>
			</font>
			</td>
			<td class="tdfont" width="60%">
			<his:date name="toDate" value='<%=(fb.getToDate()!=null?fb.getToDate():"") %>'></his:date>
			</td></tr></table>
			</td>
		</tr>	
	</table>
</his:ContentTag>


<his:ButtonToolBarTag>
	<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onkeypress="if(event.keyCode==13) submitSearch();" onclick="submitSearch()" tabindex="1">
 	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"  style="cursor:pointer" onkeypress="if(event.keyCode==13) closeForm();" onclick ="closeForm();">
 	<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' tabindex="1"  style="cursor:pointer" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
</his:ButtonToolBarTag>
	
<logic:notEmpty name="<%=RegistrationConfig.arrPATIENT_VO%>">
<his:ContentTag>
<table width="100%" cellpadding="0" cellspacing="1">
	<tr>
		<td width="5%" align="center" class="ShadedSubTitleTagImage" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<b>
					<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="select" /> 
					</font>
				</b>
			</div>
		</td>
		<td width="15%" align="center" class="ShadedSubTitleTagImage" style="border-top:outset black 2px; border-bottom:inset black 2px;" >
			<div align="center">
				<b>
					<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="crNo" /> 
							
					</font>
				</b>
			</div>
		</td>
		<td width="25%" align="center" class="ShadedSubTitleTagImage" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<b>
					<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="name" /> 
					</font>
				</b>
			</div>
		</td>
		<td width="10%" align="center" class="ShadedSubTitleTagImage" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<b>
					<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="age" />
						<bean:message key="slash" />
						<bean:message key="gender" />
					</font>
				</b>
			</div>
		</td>
		<td width="15%" align="center" class="ShadedSubTitleTagImage" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<b>
					<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fatherName" />
					</font>
				</b>
			</div>
		</td>
		<!--<td width="15%" align="center" class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<b>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="husbandName" />
					</font>
				</b>
			</div>
		</td>
		--><td width="30%" align="center" class="ShadedSubTitleTagImage" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<b>
					<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="address" />
					</font>
				</b>
			</div>
		</td>
		
		<logic:equal name="SearchByNameFB" property="gnum_hospital_code" value="<%=Config.SUPER_USER_HOSPITAL_CODE%>">
		<td width="10%" align="center" class="ShadedSubTitleTagImage" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<b>
					<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="hospital" />
					</font>
				</b>
			</div>
		</td>
		</logic:equal>
	</tr>
   <logic:iterate id="PATIENT_VO" name="<%=RegistrationConfig.arrPATIENT_VO%>">

							<tr>
								<td width="5%" class="tdfonthead" ><div align="center"><input  type="radio" name="crNoToRetrieve" onclick="crNoSelected(this);" value='<bean:write name="PATIENT_VO" property="patCrNo" />'/></div></td>
								<td width="15%" class="tdfonthead"><div align="center"><bean:write name="PATIENT_VO" property="patCrNo"/></div></td>
								<td width="25%" class="tdfonthead" ><div align="center"><bean:write name="PATIENT_VO" property="patFirstName"/> <bean:write name="PATIENT_VO" property="patMiddleName"/> <bean:write name="PATIENT_VO" property="patLastName"/></div></td>
								<td width="10%" class="tdfonthead"><div align="center"><bean:write name="PATIENT_VO" property="patAge"/>/<bean:write name="PATIENT_VO" property="patGender"/></div></td>
								<td width="15%" class="tdfonthead"><div align="center"><bean:write name="PATIENT_VO" property="patGuardianName"/></div></td>
								<!--<td width="15%" class="tdfont"><div align="center"><bean:write name="PATIENT_VO" property="patHusbandName"/></div></td>
								--><td width="30%" class="tdfonthead" ><div align="center"><bean:write name="PATIENT_VO" property="patAddHNo"/> <bean:write name="PATIENT_VO" property="patAddStreet"/><bean:write name="PATIENT_VO" property="patAddCityLoc"/>  <bean:write name="PATIENT_VO" property="patAddCity"/> <bean:write name="PATIENT_VO" property="patAddDistrict"/><bean:write name="PATIENT_VO" property="patAddState"/></div></td>
								<logic:equal name="SearchByNameFB" property="gnum_hospital_code" value="<%=Config.SUPER_USER_HOSPITAL_CODE%>">
								<td width="10%" class="tdfonthead"><div align="center"><bean:write name="PATIENT_VO" property="patRefHospitalName"/></div></td>
								</logic:equal>
							</tr>		
								
						</logic:iterate>
						</table>
<%session.removeAttribute(RegistrationConfig.arrPATIENT_VO);%>
</his:ContentTag>
</logic:notEmpty>
<%String sysdate=(String)WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy"); %>
<html:hidden name="SearchByNameFB" property="hmode"/>
<input type="hidden" name="sysdate" value="<%=sysdate%>"/>
<his:status/>
</html:form>