<!--
 /*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	MACRO MASTER
 ## Name of Developer		 			:	Yogender Yadav
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:
 ## Purpose								:	This master is used to capture the Macro used for investigation Process
 ## Date of Creation					: 
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
<his:javascript src="/new_investigation/js/MacroMstAddMod.js" />
<body>


<script type="text/javascript">

  function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].macroText,"macroText") )
     {
         valid=true ;
     }
  return valid;
}
  
  function clearaddForm()
  {
   
	  document.getElementsByName('macroText')[0].value="";
	   document.getElementsByName('remarks')[0].value="";
	      
  }
  

  function clearForm()
   {
    
      submitPage('CLEAR');
   
   }

</script>

	<html:form action="/masters/MacroMstACTION">
	
	<html:hidden name="MacroMstFB" property="hmode" />
	<html:hidden name="MacroMstFB" property="macroCode" />
	 <html:hidden name="MacroMstFB" property="selectedChk" /> 
	
	
	<%!
		boolean readOnly;
	%>
	<% this.readOnly=false;%>
	
	<logic:equal name="MacroMstFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>
	
  
	<his:TransactionContainer>
		<his:TitleTag name="Macro Master">
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
								<bean:message key="macroText"/>&nbsp;
						 </font>
				     </div>
			    </td>
			  
			     <td width="50%" class="tdfont">
			      <div align="left">																			
					 <html:text name="MacroMstFB" property="macroText"  maxlength="50" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)" tabindex="1">
					 </html:text>
				  </div>
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
					 <html:textarea name="MacroMstFB" property="remarks"  cols="28"  tabindex="1"  readonly="<%=this.readOnly %>" onkeypress="return CheckMaxLength(event,this,50,1)" >
					 </html:textarea>
				  </div>
		     </td>
		     </tr>
		     
		     
		<logic:equal name="MacroMstFB" property="hmode"
			value="MODIFY">

				
				</logic:equal>
			     
			     </table>
			      </his:ContentTag>
			      
			    <his:ButtonToolBarTag>
				<span id="saveDiv">
			    <logic:notEqual name="MacroMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="MacroMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')" tabindex="1">
				  <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearaddForm()" onkeypress="if(event.keyCode==13) clearaddForm()" tabindex="1">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="MacroMstFB" property="hmode" value="MODIFY"> 
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