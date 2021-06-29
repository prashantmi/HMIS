<!-- /*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
 ## Name of Developer		 			:	Basha
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:   Sample Master
 ## Purpose								:	This master is used to define samples used for investigation Process
 ## Date of Creation					:   05-March-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*********************************************************************************************************************/ -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/invSamMstAddMod.js" />

<body>


<script type="text/javascript">

  function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].sampleName,"sampleName") )
     {
         valid=true ;
     }
  return valid;
}
 
  
  function clearForm()
  {
   
     submitPage('CLEAR');
  
  }
</script>

<html:form action="/masters/SampleMstACTION">


	<html:hidden name="InvSampleMstFB" property="hmode" />
	<html:hidden name="InvSampleMstFB" property="sampleCode" />
	<html:hidden name="InvSampleMstFB" property="selectedChk" />
	<%!
		boolean readOnly;
	%>
	<% this.readOnly=false;%>
	
	<logic:equal name="InvSampleMstFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>
	
  
	<his:TransactionContainer>
		<his:TitleTag name="Sample Master">
		<%-- 	<his:insertDateTag/> --%>
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
								<bean:message key="SampleName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
					 <html:text name="InvSampleMstFB" property="sampleName"  maxlength="50" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)" tabindex="1">
					 </html:text>
				  </div>
			     </td>
			     </tr>
			     <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="SampleShortName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
					 <html:text name="InvSampleMstFB" property="sampleSName"  maxlength="25" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1">
					 </html:text>
				  </div>
			     </td>
			     </tr>
			     <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LOINCSystem"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <logic:present name="<%=InvestigationConfig.LIST_LOINIC_COMBO %>">
			      <div align="left">
					 
				 <html:select name="InvSampleMstFB" property="loincSystem" style="width: 65%;" tabindex="1"   > 
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LIST_LOINIC_COMBO %>" property="value" labelProperty="label"/>
				      				</html:select>
				  </div>
				  </logic:present>
			     </td>
			     </tr>
			     <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="remarks"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
					 <html:textarea name="InvSampleMstFB" property="remarks"    cols="28"   tabindex="1"  readonly="<%=this.readOnly %>" onkeypress="return CheckMaxLength(event,this,50,3)" >
					 </html:textarea>
				  </div>
			     </td>
			     </tr>
		
			   
			     
			     
			     </table>
			      </his:ContentTag>
			      
			      <his:ButtonToolBarTag>
				<span id="saveDiv">
			    <logic:notEqual name="InvSampleMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="InvSampleMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')" tabindex="1">
				  <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearAddForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="InvSampleMstFB" property="hmode" value="MODIFY"> 
				   <img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
				   <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				</logic:equal> 
	               <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
			</span>
		</his:ButtonToolBarTag>
		<his:status/>
	</his:TransactionContainer>
	
	
	
		</html:form>
	</body>
</html>