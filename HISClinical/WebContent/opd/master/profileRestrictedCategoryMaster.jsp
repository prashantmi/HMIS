
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page info="Used for addition , modification and view of disaster type master" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="hisglobal.hisconfig.*" %>

<%@page import="opd.OpdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/opd/js/profileRestrictedCatMaster.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />
<script>
function finalSubmit(mode){
	if(validateSave()){
		submitToSave(mode)
	}
	else{
		return false;
	}	
}


function validateSave(){
	var valid=false;
	if(comboValidation(document.getElementsByName("profileType")[0],"Profile Type")
	){
		valid=true;
	}
	else{
		valid=false;
	}
	
	return valid;

}

function submitPage(mode){
	document.getElementsByName("hmode")[0].value=mode
	document.forms[0].submit();
}

</script>

<his:TransactionContainer>
 <body>

  <html:form action="/master/restrictedCategoryMaster" > 
    <html:hidden name="ProfileRestrictedCategoryMasterFB" property="hmode"/>
    <html:hidden name="ProfileRestrictedCategoryMasterFB" property="serialNo"/>
    <html:hidden name="ProfileRestrictedCategoryMasterFB" property="chk"/>
    <html:hidden name="ProfileRestrictedCategoryMasterFB" property="patientCategoryName"/>
    <html:hidden name="ProfileRestrictedCategoryMasterFB" property="fetchedList"/>
   <%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
   <logic:equal name="ProfileRestrictedCategoryMasterFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   </logic:equal>
  
        
	   		<his:TitleTag name="Category Wise Restricted Profile">
			</his:TitleTag>
  	   
       
	 <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="profileType"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp; <html:select name="ProfileRestrictedCategoryMasterFB" property="profileType" tabindex="1" styleClass="regcbo" onchange="getCategory(this)">
						<html:option value="-1">Select Value</html:option>
						<html:options collection="<%=OpdConfig.PROFILE_TYPE_LIST %>" property="value" labelProperty="label"  />
						</html:select>
					</div>
				</td>
		      </tr>
		</table>			
		  <table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
						<td width="40%"  class="tdfonthead">
						</td>
						<td width="20%"  class="tdfonthead">
							<div align="center"><b>Select Patient Category </b></div>
						</td>
						<td width="40%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfont">
							<div align="right">
								<html:select name="ProfileRestrictedCategoryMasterFB" property="patientCategoryCode" tabindex="1" multiple="true" size="6">
									<logic:notEqual name="ProfileRestrictedCategoryMasterFB" property="hmode"  value="NEW">
										<logic:present name="<%=OpdConfig.PATIENT_PRIMARY_CATEGORY_NOT_MAPPED_WITH_PROFILE_TYPE_LIST%>">
										<html:options  collection="<%=OpdConfig.PATIENT_PRIMARY_CATEGORY_NOT_MAPPED_WITH_PROFILE_TYPE_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</logic:notEqual>
								</html:select>		
							</div>
						</td>
						<td width="20%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");' /> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:select name="ProfileRestrictedCategoryMasterFB" property="selectedPatientCategoryCode" tabindex="1" multiple="true" size="6">
									<logic:notEqual name="ProfileRestrictedCategoryMasterFB" property="hmode"  value="NEW">
										<logic:present name="<%=OpdConfig.PATIENT_PRIMARY_CATEGORY_MAPPED_WITH_PROFILE_TYPE_LIST%>">
										<html:options  collection="<%=OpdConfig.PATIENT_PRIMARY_CATEGORY_MAPPED_WITH_PROFILE_TYPE_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</logic:notEqual>
								</html:select>
								
							</div>
						</td>
					</tr>
			</table>
      </his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) return finalSubmit('SAVE')" onclick="return finalSubmit('SAVE')">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"   style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
		 
		 <center><b><his:status/></b></center>
        
   </html:form>
  </body>
  </his:TransactionContainer>
</html>
		     
		   
		  