<!-- /*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
 ## Name of Developer		 			:	Yogender yadav
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:   Canned Master
 ## Purpose								:	This master is used to define canned documents used for investigation Process
 ## Date of Creation					:   05-March-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*********************************************************************************************************************/ -->
<%@page import="new_investigation.masters.controller.fb.CannedMstFB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<his:javascript src="/new_investigation/js/CannedMstAddMod.js" />
<his:javascript src="/new_investigation/js/ckeditor/ckeditor.js"/>
<his:javascript src="/new_investigation/js/wysiwyg.js" />
<his:javascript src="/new_investigation/js/wysiwyg-settings.js" />

<body>

	
	<script type="text/javascript">
	
	

function submitPage(mode)
{
	   /* alert("1");  */
	  /* alert(document.getElementsByName("text1")[0].value);  */
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}
 
function validateFinalSubmit()
{
	/* alert("2"); */
     // These All Fields are Mandatory
     
	if(document.forms[0].cannedName && document.getElementsByName("cannedName")[0].value=="")
	{
		alert("Enter Canned Name ");
	
		document.getElementsByName("cannedName")[0].focus();
		return false;
	}
	
	
	
	if(document.forms[0].text1 && document.getElementsByName("text1")[0].value=="")
	{
		alert("Enter Canned Content ");
	
		document.getElementsByName("text1")[0].focus();
		return false;
	}

	if(document.forms[0].text1 && document.getElementsByName("text1")[0].value.length>12000)
	{
		alert("Canned limit is 12000 with spaces. Please follow the limit.");
	
		document.getElementsByName("text1")[0].focus();
		return false;
	}
	
	return true;   		
} 	
	
function finalSubmit(mode)
{
	  /* alert("3"); */
	  //.contents().find("body").html()
	  
  /*  var iFrame =  document.getElementById('wysiwygtext1');
   var iFrameBody;
   if ( iFrame.contentDocument ) 
   { // FF
     iFrameBody = iFrame.contentDocument.getElementsByTagName('body')[0];
   }
   else if ( iFrame.contentWindow ) 
   { // IE
     iFrameBody = iFrame.contentWindow.document.getElementsByTagName('body')[0];
   }
    //alert(iFrameBody.innerHTML);
    
    document.getElementsByName('text1')[0].value=iFrameBody.innerHTML; */
    
    
    var editor = CKEDITOR.instances.text1;
	
	  
	  document.getElementsByName('text1')[0].value=editor.getData();
	  
	if (validateFinalSubmit()) 
				return	submitPage(mode);
	
}
 
function clearaddForm()
 {
	document.getElementsByName('cannedName')[0].value="";
	document.getElementsByName('remarks')[0].value="";
	document.getElementsByName("text1")[0].value="";
	WYSIWYG.resetTextArea(document.getElementsByName("text1")[0].name);
	
 }

 function clearForm()
    {
     
       submitPage('CLEAR');
    
    }
     
   function validateOnSave()
{
   valid=false;
 
    if( isEmpty(document.forms[0].cannedName,"cannedName") )
     {
         valid=true ;
     }
  return valid;
  
}  
  

  
</script>

	<html:form action="/masters/CannedMstACTION">

		<html:hidden name="CannedMstFB" property="hmode" />
		<html:hidden name="CannedMstFB" property="cannedCde" />
 		<html:hidden name="CannedMstFB" property="selectedChk" />
 		<%-- <html:hidden name="CannedMstFB" property="cannedContent" /> --%>
 		
		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>

		<logic:equal name="CannedMstFB" property="hmode" value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>


		<his:TransactionContainer>
			<his:TitleTag name="Canned Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="20%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="CanedName" />&nbsp;
								</font>
							</div>
						</td>
						<td width="80%" class="tdfont">
							<div align="left">
								<html:text name="CannedMstFB" property="cannedName"
									maxlength="30" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
				
				
						<tr>
						<td width="20%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="CanedContent" />&nbsp;
								</font>
							</div>
						</td>
						<td class='tdfonthead' colspan="3">
							 <div align="center">
								<textarea class="ckeditor" name="text1"	cols="65" rows="15" id="text1"><%=((CannedMstFB)pageContext.findAttribute("CannedMstFB")).getText1()%></textarea>
								<script language="javascript">
									WYSIWYG.attach('text1',full);
								</script>
							</div>
						</td>
					</tr> 
					<tr>
						<td width="20%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="remarks" />&nbsp;
								</font>
							</div>
						</td>
						
					
						<td width="80%" class="tdfont">
							<div align="left">
								<html:textarea name="CannedMstFB" property="remarks"
									tabindex="1" readonly="<%=this.readOnly%>"
									onkeypress="return CheckMaxLength(event,this,50,1)">
								</html:textarea>
							</div>
						</td>
					</tr>
				<%-- 	<logic:equal name="CannedMstFB" property="hmode" value="MODIFY">

					</logic:equal> --%>
				</table>
			</his:ContentTag>

			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="CannedMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="CannedMstFB" property="hmode"
							value="VIEW">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-sv.png"/>'
								style="cursor: pointer"
								onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
								onclick="finalSubmit('SAVE')" tabindex="1">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-clr.png"/>'
								style="cursor: pointer" onclick="clearaddForm()"
								onkeypress="if(event.keyCode==13) clearaddForm()" tabindex="1">
						</logic:notEqual>
					</logic:notEqual> <logic:equal name="CannedMstFB" property="hmode" value="MODIFY">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-mo.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')"
							onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-clr.png"/>'
							style="cursor: pointer" onclick="clearForm()"
							onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
					</logic:equal> <img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
					style="cursor: pointer" onclick="submitForm('CANCEL')"
					onkeypress="if(event.keyCode==13) submitForm('CANCEL')"
					tabindex="1">
				</span>
			</his:ButtonToolBarTag>
			<his:status />
		</his:TransactionContainer>
	</html:form>
</body>
</html>