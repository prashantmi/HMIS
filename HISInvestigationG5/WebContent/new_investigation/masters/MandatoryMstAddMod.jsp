<!--
 /*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	MANDATORY MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:
 ## Purpose								:	This is used to define the mandatory in Lab
 ## Date of Creation					: 	30-Jan-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*********************************************************************************************************************/

 -->



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
<his:javascript src="/new_investigation/js/MandatoryMstAddMod.js" />
<body>


<script type="text/javascript">

  function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].mandatoryName,"mandatoryName") )
     {
         valid=true ;
     }
  return valid;
}
  
  function clearaddForm()
  {
   
	   document.getElementsByName('mandatoryName')[0].value="";
	   document.getElementsByName("mandatoryType")[0].checked="true";
	   
	   document.getElementsByName('remarks')[0].value="";
    
  }
 
  function clearForm()
  {
   
     submitPage('CLEAR');
  
  }
   
</script>

<html:form action="/masters/MandatoryMstACTION">


	<html:hidden name="MandatoryMstFB" property="hmode" />
	<html:hidden name="MandatoryMstFB" property="mandatoryCode" />
	<html:hidden name="MandatoryMstFB" property="selectedChk" />
	
	
	<%!
		boolean readOnly;
	%>
	<% this.readOnly=false;%>
	
	<logic:equal name="MandatoryMstFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>
	
  
	<his:TransactionContainer>
		<his:TitleTag name="Mandatory Master">
			<%-- <his:insertDateTag/> --%>
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
								<bean:message key="MandatoryName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
					 <html:text name="MandatoryMstFB" property="mandatoryName"  maxlength="100" size="30" readonly="<%=this.readOnly %>" 
					onkeypress="return validateAlphaNumericOnly(event,this)"
					  tabindex="1">
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
								<bean:message key="MandatoryType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
					  
				       
						<html:radio name="MandatoryMstFB"  tabindex="1" property="mandatoryType" value="1"  ></html:radio>
						<bean:message key="TestBox"/>
						<html:radio name="MandatoryMstFB" tabindex="1" property="mandatoryType" value="2" ></html:radio>
						<bean:message key="Combo"/>
					    
				  </div>
			     </td>
			     </tr>
			     
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
					 <html:textarea name="MandatoryMstFB" property="remarks" cols="28" tabindex="1"  readonly="<%=this.readOnly %>" onkeypress="return CheckMaxLength(event,this,50,1)">
					 </html:textarea>
				  </div>
			     </td>
			     </tr>
			    
			     
			    	   
	
			      	 
		 
			     </table>
			      </his:ContentTag>
			      
			      <his:ButtonToolBarTag>
				<span id="saveDiv">
			    <logic:notEqual name="MandatoryMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="MandatoryMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')" tabindex="1">
				  <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearaddForm()" onkeypress="if(event.keyCode==13) clearaddForm()" tabindex="1">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="MandatoryMstFB" property="hmode" value="MODIFY"> 
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