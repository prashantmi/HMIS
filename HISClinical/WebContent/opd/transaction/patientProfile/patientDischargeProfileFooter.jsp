<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<his:javascript src="/opd/js/generic_patient_profile.js"/>

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/jquery-ui.css">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtool.js"></script>
<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/js/demo.js"></script> -->
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>
<!-- <link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet"> -->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script type="text/javascript">

window.onload = function()
{

	var callbck_index =function(ret_OUT){setValue(ret_OUT);};
	selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','1',callbck_index);

	

}

function setValue(selectedSNOMEDTerm)
{
		
	
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
	{
//		alert(selectedSNOMEDTerm);		
		/* var arr=selectedSNOMEDTerm.split("@@@");
		var str=arr[0];
		var str1=arr[1];
		 */
		 var str=selectedSNOMEDTerm.term;
		 var str1=selectedSNOMEDTerm.conceptId;
		
		 if(document.getElementsByName("remarks")[0].value=="") document.getElementsByName("remarks")[0].value = str;
		else document.getElementsByName("remarks")[0].value = document.getElementsByName("remarks")[0].value + "; " + str;
  		
	
		 if(document.getElementsByName("snomdPTRemarks")[0].value=="") document.getElementsByName("snomdPTRemarks")[0].value = str;
		else document.getElementsByName("snomdPTRemarks")[0].value = document.getElementsByName("snomdPTRemarks")[0].value + "; " + str;
			
		 if(document.getElementsByName("snomdCIdRemarks")[0].value=="") document.getElementsByName("snomdCIdRemarks")[0].value = str1;
		else document.getElementsByName("snomdCIdRemarks")[0].value = document.getElementsByName("snomdCIdRemarks")[0].value + "; " + str1;
			

	}
}





function tog(v) { return v ? 'addClass' : 'removeClass'; }
$(document).on('mouseover', '.clearable', function () {
    $(this)[tog(this.value)]('x');
}).on('mousemove', '.x', function (e) {
    $(this)[tog(this.offsetWidth - 18 < e.clientX - this.getBoundingClientRect().left)]('onX');
}).on('touchstart click', '.onX', function (ev) {
    ev.preventDefault();
    $(this).removeClass('x onX').val('').change();
    var id = this.id;
    //alert("hi");
  		document.getElementsByName("snomdPTRemarks")[0].value="";  //preffered term
		document.getElementsByName("snomdCIdRemarks")[0].value="";   //concept Id
		document.getElementsByName("remarks")[0].value="";
	

});



function setfreeText()
{

	document.getElementsByName("remarks")[0].value=document.getElementsByName("txt-snomed-ct-search_1")[0].value;   //for free text
}

function validateFooterDetail()
{
	setfreeText();
	var valid=false;
	//if(document.getElementsByName('remarks')[0].value=="")
	//{
	//	alert("Please Enter Footer Remarks");
	//	document.getElementsByName('remarks')[0].focus();
	//	return false;
	//}
	// return true;
	var profileType=document.getElementsByName("profileType")[0].value;
	if(profileType==<%=OpdConfig.PROFILE_TYPE_DISCHARGE%>)
	{
		if( isEmpty(document.getElementsByName("remarks")[0],"Remarks")
			&& datecheck())
			
			{
				valid=true;
			}
		else
		{
				valid=false;
		}	
	}
	else
	{
			if( isEmpty(document.getElementsByName("remarks")[0],"Remarks"))
			{
				valid=true;
			}
			else
			{
					valid=false;
			}	
	
	}
	

			if(valid==true)
			{
				setfreeText();
			return true;
			}
}

	
function datecheck()
{
		
	  var aArray=(document.getElementsByName("reviewDate")[0].value).split("-");
      var amonth=aArray[1];
      var aday=aArray[0];
      var ayear=aArray[2];
      var adate=new Date(amonth +" "+ aday+" "+ayear);
      
      
      var bArray=(document.getElementsByName("entryDate")[0].value).split("-");
      var bmonth=bArray[1];
      var bday=bArray[0];
      var byear=bArray[2];
      var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
      if(adate<bdate)
      {
     alert("Review Date Cannot Be Less Than System Date");
     document.forms[0].reviewDate.focus(); 
      return false;
      }
 return true;
}	

function getDoctorProgressNotes(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var admissionNo=document.getElementsByName("admissionNo")[0].value;
	var remarks=document.getElementsByName("remarks")[0].value;
	
	var path=createFHashAjaxQuery('/HISClinical/opd/opdPatientProfile.cnt?hmode=PROGRESSNOTES&patCrNo='+patCrNo+'&admissionNo='+admissionNo+'&remarks='+remarks);
	openPopup(createFHashAjaxQuery(path),event,600,700);
}

function clearForm()
{
	document.getElementsByName("remarks")[0].value="";
	document.getElementsByName("reviewDate")[0].value="";
	
}
</script>

<bean:define id="menuURL" name="GenericPatientProfileFB" property="hmode" type="java.lang.String"></bean:define>
<%	String targetHmode = "SET" + menuURL; %>
<his:TitleTag key="patientProfile" width="100%">
</his:TitleTag>

<his:statusTransactionInProcess>

<his:SubTitleTag name="Profile Footer">
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td>
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<a style="cursor:pointer" onclick="getDoctorProgressNotes(event)" title="click to view the progress note">	
								<bean:message key="progressNotes"/>
							</a>	
						</b>
					</font>
				</div>
			</td>
			
		</tr>
		</table>
</his:SubTitleTag>

	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="15%"  class="tdfonthead">
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="summary"/></b>
						</font>
					</div>
				</td>
				<td width="85%"  class="tdfont" colspan="3">
					<div align="left">
						
						
					<%-- 	<html:textarea name="GenericPatientProfileFB" property="remarks"  rows="18" cols="105"  tabindex="1"  onkeypress="return (validateTextArea(event,this,'2000') && validateAlphaNumericOnly(event))" ></html:textarea>
					 --%>
					
				
							<html:hidden name="GenericPatientProfileFB" property="snomdPTRemarks" ></html:hidden>
					        <html:hidden name="GenericPatientProfileFB" property="snomdCIdRemarks" ></html:hidden>
							 <html:hidden name="GenericPatientProfileFB" property="remarks" ></html:hidden>
						 		
				   <div id="dialog-form_1" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<textarea   id="txt-snomed-ct-search_1" class="clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_1" tabindex="1"    style="width:58%;color:#000000;" autocomplete="off" rows="5" cols="20" ></textarea>
					</div>
					 
                     			</div></div>
				</td>
			</tr>
			
			<tr>
				<td width="15%"  class="tdfonthead">
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="reviewOn"/></b>
						</font>
					</div>
				</td>
				<td width="85%"  class="tdfont" colspan="3">
					<div align="left">
						<div align="left"><his:date name="reviewDate"  dateFormate="%d-%b-%Y"></his:date></div>
					</div>
				</td>
			</tr>
			<tr>
				<td width="15%"  class="tdfonthead">
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="signature"/></b>
						</font>
					</div>
				</td>
			 
			   <td width="85%" class="tdfont">
			   </td>
			</tr>	
			<tr>
				<td width="15%"  class="tdfonthead">
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="seniorResident"/></b>
						</font>
					</div>
				</td>
			  
			    <td width="85%" class="tdfont">
			   </td>
			</tr>	
			<tr>
				<td width="15%"  class="tdfonthead">
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="date1"/></b>
						</font>
					</div>
				</td>
			   
			    <td width="85%" class="tdfont">
			   </td>
			</tr>	
			<logic:notEmpty name="GenericPatientProfileFB" property="disclaimerDesc1">
			<tr>
				<td width="100%"  class="tdfonthead" colspan="2">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:write name="GenericPatientProfileFB" property="disclaimerDesc1"/></b>
						</font>
					</div>
				</td>
			 </tr>	
			 </logic:notEmpty>
			 <logic:notEmpty name="GenericPatientProfileFB" property="disclaimerDesc2">
			 <tr>
				<td width="100%"  class="tdfonthead" colspan="2">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:write name="GenericPatientProfileFB" property="disclaimerDesc2"/></b>
						</font>
					</div>
				</td>
			 </tr>	
			 </logic:notEmpty>
			 <logic:notEmpty name="GenericPatientProfileFB" property="disclaimerDesc3">
			<tr>
				<td width="100%"  class="tdfonthead" colspan="2">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:write name="GenericPatientProfileFB" property="disclaimerDesc3"/></b>
						</font>
					</div>
				</td>
			 </tr>	
			 </logic:notEmpty>
		</table>
	</his:ContentTag>

</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateFooterDetail(),'<%=targetHmode%>');" onclick="submitFormOnValidate(validateFooterDetail(),'<%=targetHmode%>');" tabindex="1" />		
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm21('PROFILEOPTIONS')" onkeypress="if(event.keyCode==13) submitForm21('PROFILEOPTIONS');">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
	</his:statusTransactionInProcess>
</his:ButtonToolBarTag>

<html:hidden name="GenericPatientProfileFB" property="hmode" />

<html:hidden name="GenericPatientProfileFB" property="patCrNo" />
<html:hidden name="GenericPatientProfileFB" property="episodeCode" />
<html:hidden name="GenericPatientProfileFB" property="episodeVisitNo" />
<html:hidden name="GenericPatientProfileFB" property="departmentUnitCode" />
<html:hidden name="GenericPatientProfileFB" property="wardCode" />
<html:hidden name="GenericPatientProfileFB" property="admissionNo" />
<html:hidden name="GenericPatientProfileFB" property="deskType" />
<html:hidden name="GenericPatientProfileFB" property="deskId" />
<html:hidden name="GenericPatientProfileFB" property="profileId" />
<html:hidden name="GenericPatientProfileFB" property="serialNo" />
<html:hidden name="GenericPatientProfileFB" property="entryDate" />
<html:hidden name="GenericPatientProfileFB" property="disclaimerDesc1" />
<html:hidden name="GenericPatientProfileFB" property="disclaimerDesc2" />
<html:hidden name="GenericPatientProfileFB" property="disclaimerDesc3" />
<html:hidden name="GenericPatientProfileFB" property="dischargeModifyFlag" />
<html:hidden name="GenericPatientProfileFB"	property="profileType" />
<html:hidden name="GenericPatientProfileFB"	property="profileHeader" />
<html:hidden name="GenericPatientProfileFB" property="accessType" />
<html:hidden name="GenericPatientProfileFB" property="userLevel" />
<html:hidden name="GenericPatientProfileFB" property="reportMode" />
<html:hidden name="GenericPatientProfileFB" property="selectedMenu" />
<html:hidden name="GenericPatientProfileFB" property="selectedMenuId" />
<html:hidden name="GenericPatientProfileFB" property="isDesclaimerRequired" />