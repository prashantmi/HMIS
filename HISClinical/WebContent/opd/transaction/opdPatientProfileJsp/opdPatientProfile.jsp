
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>

<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/dateFunctions.js" />

<his:javascript src="/opd/opdJs/opd.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function submitToCancle()
{
	// alert("dsdsdsdsdddsdsd "+self.close())
	self.close();
}
function showPreviousProfile(path)
{
	window.open(createFHashAjaxQuery(path),'popupWindow1','status=yes,scrollbars=yes,height='+screen.availHeight+',width='+screen.availWidth+ ',left=10,top=10,,dependent=yes,resizable=yes');
}
function showPatientProfileMenu()
{
	
}

function modifyDate(profileId,serialNo,choice)
{
	// alert("in modifyDate serilano "+serialNo+" profileId "+profileId)
	document.getElementsByName("selectedProfileSerialNo")[0].value=serialNo;
	document.getElementsByName("selectedProfileId")[0].value=profileId;
	document.getElementsByName("modifyChoice")[0].value=choice;
	// alert("sdsdsd "+document.getElementsByName("selectedProfileSerialNo")[0].value+" sdfdfdfd"+document.getElementsByName("selectedProfileId")[0].value)
	submitFormOnValidate(true,'MODIFYPROFILE');
	
}

function showEffectiveDate(obj)
{
	var choice=obj.value;
	var modifyDivId="modify"+choice;	
	if(obj.checked)
	{
		//alert(modifyDivId)
		document.getElementsByName("effectiveFrom")[choice].disabled=false;
		document.getElementById(modifyDivId).style.display="";
	}
	else
	{
		document.getElementsByName("effectiveFrom")[choice].disabled=true;	
		document.getElementById(modifyDivId).style.display="none";
	}
	
}


function show(obj,event)
{
	
	var spanObj=document.getElementById("spanId");
	var o=obj;
		var l=0,t=0;
		while(o.offsetParent)
		{	// alert(t+" "+l+"vcv ")
			l+=o.offsetLeft;
			t+=o.offsetTop+obj.offsetHeight;
			o=o.offsetParent;
		}
		// alert(t+" "+l+"vcv ")
	spanObj.style.top=event.offsetY;
	spanObj.style.Left=event.offsetX;
	spanObj.style.display="";
}

function selectProfileMenu(obj)
{
	// alert("select profilemenu")
	document.getElementsByName("hmode")[0].value=obj.value;
	// alert("hmode"+document.getElementsByName("hmode")[0].value)
}

function submitSelectedProfileMenu()
{
	// alert
	document.forms[0].submit();
}

function validate()
{
	var valid=false;
	// var profileHeader=(document.getElementsByName("patProfileHeader")[0].value).trim();
	// var profileremark=(document.getElementsByName("patProfileRemark")[0].value).trim();
	// alert("document.getElementsByName(entryDate)[0]"+document.getElementsByName("entryDate")[0].value);
	//  alert("document.getElementsByName(effectiveFrom)[0]"+document.getElementsByName("effectiveFrom")[0].value);
//	alert("document.forms[0].patProfileHeader "+document.getElementsByName("patProfileHeader")[0].value)

	if(checkEmpty(document.getElementsByName("patProfileHeader")[0].value,"Profile Header") 
		&& checkEmpty(document.getElementsByName("patProfileRemark")[0].value,"Profile Remark") 
		&& compareDateCall(document.getElementsByName("entryDate")[0],document.getElementsByName("effectiveFrom")[0],2,"Current Date","Effective From")
		&& compareDateCall(document.getElementsByName("effectiveFrom")[0],document.getElementsByName("effectiveTo")[0],2,"Effective From","Effective To")
		&& compareDateCall(document.getElementsByName("fromDate")[0],document.getElementsByName("entryDate")[0],2,"From Date","Current Date")
		&& compareDateCall(document.getElementsByName("fromDate")[0],document.getElementsByName("toDate")[0],2,"From Date","To Date")
		 ) {
	  valid=true;   
	 }
	       
	 else{
	 valid=false;     
	 }
		 return valid;
}

function setHtml(obj)
{
	var htmlValue=document.getElementsByName("htmlValue")[0].value;
//	alert(obj.name)
//	alert(obj.value)
	
		htmlValue=htmlValue+"<head><center><b><h1>"+obj.value+"<h1></b></center></head><p>&nbsp;</p><body></br>";
		htmlValue=htmlValue+"<center><b>Patient CrNo. :: "+document.getElementsByName("patCrNo")[0].value+"</b></center>&nbsp";
		document.getElementsByName("htmlValue")[0].value=htmlValue;
	
}

</script>

<his:TitleTag width="100%">
		<his:name>
			<bean:message key="patientProfile" />
		</his:name>
</his:TitleTag>
<his:statusRecordFound>
	<his:SubTitleTag name=""  >
	    		<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="previousProfileDtl"/></b>
				</font>
				</div>
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="5%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="SNo"/>
				</font>
				</div>
				</td>
				
				<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="profileId"/>
				</font>
				</div>
				</td>
				
				<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<bean:message key="remarks"/>
				</font>
				</div>
				</td>
				
			<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<bean:message key="effectiveFrom"/>
				</font>
				</div>
				
	  		</td>
				
				<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<bean:message key="effectiveTo"/>
				</font>
				</div>
				<td width="5%"  class="tdfonthead">
				<div align="center">	           
				
				</div>
	  		</td>    
	  	</tr>
	  	<logic:iterate id="previousProfileId" name="<%=OpdConfig.OPD_PATIENT_PREVIOUS_PROFILE_ARRAY%>" indexId="idx" type="hisglobal.vo.PatProfileDtlVO" >
	  	<tr>
	  	<%String path="/HISClinical/ShowFileFromDir?"+OpdConfig.DOCUMENT_STORAGE_PATH+"="+Config.OPD_PATIENT_PROFILE_STORAGE_PATH+"&"+OpdConfig.FILE_NAME+"="+previousProfileId.getPatProfileId()+previousProfileId.getSerialNo()+".html"
  			+"&"+OpdConfig.IS_EXTENSION+"="+OpdConfig.IS_EXTENSION_FALSE;
	  	String modifyDivId="modify"+idx.toString();
  			%>
	  		<td width="5%"  class="tdfont" nowrap="nowrap" >
					<div align="center">	           
					<%=String.valueOf(idx.intValue()+1)%>
					</div>
	  		</td>
	  		
	  		<td width="20%"  class="tdfont" nowrap="nowrap" >
					<div align="center">	           
				<a style="cursor: pointer;" onclick="showPreviousProfile('<%=path%>');" >	<bean:write name="previousProfileId" property="patProfileId" /> </a>
					</div>
	  		</td>
	  		<td width="20%"  class="tdfont" nowrap="nowrap" >
					<div align="center">	           
					<bean:write name="previousProfileId" property="patProfileRemark" />
					</div>
	  		</td>
	  		<td width="20%"  class="tdfont" nowrap="nowrap" >
					<div align="center">	           
			<%--		<bean:define
					name="previousProfileId" property="effectiveFrom" id="effectFrm" 
					type="java.lang.String" /> 
					<his:date name='<%="selectedEffectiveFrom"%>'
					dateFormate="%d-%b-%Y" value='<%=effectFrm%>' /> --%>
					<html:text name="previousProfileId" property="prevEffectiveFrom" disabled="true" ></html:text>
					</div>
	  		</td>
	  		<td width="20%"  class="tdfont" nowrap="nowrap" >
					<div align="center">	
					      
					<bean:define
					name="previousProfileId" property="prevEffectiveTo" id="effectTo" 
					type="java.lang.String" /> 
					
					<his:date name='<%="selectedEffectiveTo"%>' dateFormate="%d-%b-%Y" value='<%=effectTo%>' />
					</div>
	  		</td>
	  		<td width="5%"  class="tdfont" nowrap="nowrap" >
					<div id="<%=modifyDivId%>" align="center"  >	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<a  style="cursor:pointer;" onclick="modifyDate('<bean:write name="previousProfileId" property="patProfileId" />','<bean:write name="previousProfileId" property="serialNo" />','<%=idx.toString()%>')" >	<bean:message key="modify"/></a>
					</font>
					</div>
	  		</td>
	  	</tr>
	  	</logic:iterate>
	  </table>
	</his:ContentTag>  
</his:statusRecordFound>
<his:statusNew>
	<his:SubTitleTag name=""  >
	    		<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="ProfileDtl"/></b>
				</font>
				</div>
	</his:SubTitleTag>
	<his:ContentTag>
	
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="20%"  class="tdfonthead">
				<div align="right">	      
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>     
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="profileHeader"/>
				</font>
				</div>
				
	  		</td>
	  		<td width="20%"  class="tdfont" nowrap="nowrap" >
					<div align="left">	           
					<html:text name="OpdPatientProfileFB" property="patProfileHeader" tabindex="1" size="50" maxlength="50" onblur="setHtml(this)" ></html:text>
					</div>
	  				</td>
	  		</tr>
	  		<tr style="background-color:white;" height="5%">
	  		<td>
	  		</td>
	  		</tr>
	  		<tr>
			<td width="20%"  class="tdfonthead">
				<div align="right">	       
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>    
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="remarks"/>
				</font>
				</div>
				
	  		</td>
	  		<td width="20%"  class="tdfont" nowrap="nowrap" >
					<div align="left">	           
					<html:textarea name="OpdPatientProfileFB" property="patProfileRemark" tabindex="1" cols="47" rows="4" ></html:textarea>
					</div>
	  				</td>     
			</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
			<td width="20%"  class="tdfonthead">
				<div align="right">	    
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>       
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="effectiveFrom"/>
				</font>
				</div>
				
	  		</td>
	  		
	  		<td width="20%"  class="tdfont" nowrap="nowrap" >
					<div ><bean:define
					name="OpdPatientProfileFB" property="effectiveFrom" id="effectFrm"
					type="java.lang.String" /> <his:date name='<%="effectiveFrom"%>' 
					dateFormate="%d-%b-%Y" value='<%=effectFrm%>' /></div>
	  				</td> 
	  		<td width="20%"  class="tdfonthead">
				<div align="right">	   
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>        
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="effectiveTo"/>
				</font>
				</div>
				
	  		</td>    
	  		<td width="20%"  class="tdfont" nowrap="nowrap" >
					<div ><bean:define
					name="OpdPatientProfileFB" property="effectiveTo" id="effectTo"
					type="java.lang.String" /> <his:date name='<%="effectiveTo"%>' 
					dateFormate="%d-%b-%Y" value='<%=effectTo%>' /></div>
	  				</td> 
			</tr>
		</table>
		
	</his:ContentTag>
	<his:SubTitleTag name=""  >
	    		<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="selectDateToShowPatientDetailForProfileGenration"/></b>
				</font>
				</div>
	</his:SubTitleTag>
	<his:ContentTag>
	
	<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="20%"  class="tdfonthead">
				<div align="right">	    
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>       
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="fromDate"/>
				</font>
				</div>
				
	  		</td>
	  		
	  		<td width="20%"  class="tdfont" nowrap="nowrap" >
					<div ><bean:define
					name="OpdPatientProfileFB" property="fromDate" id="dateFrom"
					type="java.lang.String" /> <his:date name='<%="fromDate"%>' 
					dateFormate="%d-%b-%Y" value='<%=dateFrom%>' /></div>
	  				</td> 
	  		<td width="20%"  class="tdfonthead">
				<div align="right">	   
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>        
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="toDate"/>
				</font>
				</div>
				
	  		</td>    
	  		<td width="20%"  class="tdfont" nowrap="nowrap" >
					<div ><bean:define
					name="OpdPatientProfileFB" property="toDate" id="dateTo"
					type="java.lang.String" /> <his:date name='<%="toDate"%>' 
					dateFormate="%d-%b-%Y" value='<%=dateTo%>' /></div>
	  				</td> 	  </tr>
	</table>
	
</his:ContentTag>
</his:statusNew>

<his:statusTransactionInProcess>
<his:SubTitleTag name=""  >
	    		<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="selectEpisode"/></b>
				</font>
				</div>
	</his:SubTitleTag>
	<his:ContentTag>
	
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="20%"  class="tdfonthead">
				<div align="center">	
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="select"/></b>
				</font>
				</div>
				</td>
				<td width="20%"  class="tdfonthead">
				<div align="center">	   
				   
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="episode"/></b>
				</font>
				</div>
				</td>
				
				<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="episodeDate"/></b>
				</font>
				</div>
				</td>
				
				<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="lastVisitdate"/></b>
				</font>
				</div>
				</td>
			</tr>
		</table>
		<table width="100%" cellpadding="0" cellspacing="0">
		<logic:iterate id="episodeVoArray" name="<%=OpdConfig.OPD_PATIENT_PROFILE_EPISODEVO_ARRAY %>" type="hisglobal.vo.EpisodeVO" >
			<tr>
				<td width="20%"  class="tdfont" nowrap="nowrap">
					<div align="center">	           
					<html:radio name="OpdPatientProfileFB" property="selectedEpisode" value="<%=episodeVoArray.getEpisodeCode() %>" ></html:radio>
					</div>
	  				</td>  
				<td width="20%"  class="tdfont">
					<div align="center">	           
					<bean:write name="episodeVoArray" property="episodeCode" />
					</div>
	  				</td>   
	  			<td width="20%"  class="tdfont">
					<div align="center">	           
					<bean:write name="episodeVoArray" property="episodeDate" />
					</div>
	  				</td>   
	  			<td width="20%"  class="tdfont">
					<div align="center">	           
					<bean:write name="episodeVoArray" property="lastVisitDate" />
					</div>
	  				</td>   
			</tr>
		</logic:iterate>
		</table>
	</his:ContentTag>
</his:statusTransactionInProcess>
<his:statusList>
	<his:SubTitleTag name=""  >
	    		<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="selectToEnterParticularDetailInPatientProfile"/></b>
				</font>
				</div>
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0">
		<logic:iterate id="menuEntry" name="<%="PROFILE_MENU"%>" indexId="i" type="hisglobal.utility.Entry">
			
			<tr>
				<td width="20%"  class="tdfont">
					<div align="center">	           
					<html:radio name="OpdPatientProfileFB" property="selectedMenu" value="<%=menuEntry.getValue()%>" onclick="selectProfileMenu(this)" ></html:radio>
					</div>
	  				</td>  
				<td width="20%"  class="tdfont">
					<div align="center">	           
					<%=menuEntry.getLabel()%>
					</div>
	  				</td> 
			</tr>
		</logic:iterate>
		</table>
	</his:ContentTag>
</his:statusList>
<his:ButtonToolBarTag>
<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				 <td width="20%" style="background-color:#FFFFFF;background-repeat:repeat-x" nowrap="nowrap" >
				 <div align="left">	           
				 <his:statusTransactionInProcess>
			 	 <img class="button" src='<his:path src="/hisglobal/images/backward.gif"/>' tabindex="1"  style="cursor:pointer;" onkeypress="if(event.keyCode==13)submitForm('NEW');" onclick ="submitForm('NEW');" tabindex="1"/>
				 </his:statusTransactionInProcess> 
				 </div>
				 </td >
				 <td width="20%"  style="background-color:#FFFFFF;background-repeat:repeat-x" nowrap="nowrap" >
				 <div align="center">	           
				 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	        	 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	        	 <his:statusList>
	        	 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-generate.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm('GENRATEPROFILE')" onkeypress="if(event.keyCode==13) submitForm('GENRATEPROFILE');">
	        	 </his:statusList>
				 </div>
				 </td>
				 <td width="20%"  style="background-color:#FFFFFF;background-repeat:repeat-x" nowrap="nowrap" >
				 <div align="right">	           
				 <his:statusNew> 
				 <img class="button" src='<his:path src="/hisglobal/images/forwardward.gif"/>' tabindex="1" style=cursor:pointer onkeypress="if(event.keyCode==13)submitFormOnValidate(validate(),'GETEPISODEORVISIT');" onclick ="submitFormOnValidate(validate(),'SHOWPROFILEMENU');" tabindex="1"/>
				 </his:statusNew>
				 <his:statusTransactionInProcess>
				  <img class="button" src='<his:path src="/hisglobal/images/forwardward.gif"/>' tabindex="1" style=cursor:pointer onkeypress="if(event.keyCode==13)submitForm('SHOWPROFILEMENU');" onclick="submitForm('SHOWPROFILEMENU');" tabindex="1"/>
				 </his:statusTransactionInProcess>
				 <his:statusList>
				 <img class="button" src='<his:path src="/hisglobal/images/forwardward.gif"/>' tabindex="1" style=cursor:pointer onkeypress="if(event.keyCode==13)submitForm('SHOWPROFILEDIAGNOSIS');" onclick="submitSelectedProfileMenu();" tabindex="1"/>
				 </his:statusList>
				 </div>
				 </td>
			</tr>
</table>
</his:ButtonToolBarTag>



<html:hidden name="OpdPatientProfileFB" property="hmode"/>
<html:hidden name="OpdPatientProfileFB" property="patCrNo"/>
<html:hidden name="OpdPatientProfileFB" property="profileType"/>
<html:hidden name="OpdPatientProfileFB" property="selectedEpisode"/>
<html:hidden name="OpdPatientProfileFB" property="patProfileHeader"/>
<html:hidden name="OpdPatientProfileFB" property="patProfileRemark"/>
<html:hidden name="OpdPatientProfileFB" property="effectiveFrom"/>
<html:hidden name="OpdPatientProfileFB" property="effectiveTo"/>
<html:hidden name="OpdPatientProfileFB" property="selectedProfileSerialNo"/>
<html:hidden name="OpdPatientProfileFB" property="selectedProfileId"/>
<html:hidden name="OpdPatientProfileFB" property="modifyChoice"/>
<html:hidden name="OpdPatientProfileFB" property="htmlValue"/>
<html:hidden name="OpdPatientProfileFB" property="fromDate"/>
<html:hidden name="OpdPatientProfileFB" property="toDate"/>
<html:hidden name="OpdPatientProfileFB" property="entryDate"/>