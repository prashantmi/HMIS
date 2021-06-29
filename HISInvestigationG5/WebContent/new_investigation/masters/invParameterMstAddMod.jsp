<!--
 /*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	PARAMETER MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:
 ## Purpose								:	This master is used to capture the Parameters used for investigation Process
 ## Date of Creation					: 	28-Jan-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*********************************************************************************************************************/
 -->





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
<%-- <his:javascript src="/hisglobal/js/validation.js" /> --%>
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/invParMstAddMod.js" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>

<body onload="oncall()">


	<script type="text/javascript">
		
	function oncall()
	{
		 var parameterName= document.getElementsByName("parameterName")[0].value;

         if(parameterName!="")
       	  document.getElementById("parameterName").value=parameterName;
 
		}
	
		function clearaddForm() {

			document.getElementsByName('parameterName')[0].value = "";
			document.getElementsByName('remarks')[0].value = "";

		}

		function clearForm() {

			submitPage('CLEAR');

		}
		  function blockSpecialChar(){
		        var k;
		        document.all ? k = e.keyCode : k = e.which;
		        
		        if(((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 48 && k <= 57))==false)
				{
					alert("ParameterName can only have alphanumeric characters");
					//return false;
				}
				
				return true;
				
	        } 

		 

	        
</script>

<html:form action="/masters/ParameterMstACTION">

		<html:hidden name="InvParameterMstFB" property="hmode" />
		<html:hidden name="InvParameterMstFB" property="parameterCode" />
		<html:hidden name="InvParameterMstFB" property="selectedChk" />
         <html:hidden name="InvParameterMstFB" property="parameterName" />

		<%!boolean readOnly;%>
		<%
			this.readOnly = false;
		%>

		<logic:equal name="InvParameterMstFB" property="hmode" value="VIEW">
			<%
				this.readOnly = true;
			%>
		</logic:equal>


		<his:TransactionContainer>
			<his:TitleTag name="Parameter Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>

				<table width="100%" border="0" cellspacing="1" cellpadding="0">

					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="ParameterName" />&nbsp;
								</font>
							</div>
						</td>

						 <%-- <td width="50%" class="tdfont">
							<div align="left">
								<html:text name="InvParameterMstFB" property="parameterName" style="width=62%" 
									maxlength="60" size="30" readonly="<%=this.readOnly%>"
					                onkeypress="return validateAlphaNumericOnly(event,this)" 
					                
					               tabindex="1">
									
								</html:text>
							</div>
						</td>  --%>
						 <td width="50%" class="tdfont">
							<div align="left">
								 <input  type="text"  id="parameterName"  style="width:62%" maxlength="60" size="30" 
					                onkeypress="return validateAlphaNumericOnly(event,this)" onpaste="return false;" onCopy="return false" 
					               tabindex="1" />
					              
							</div>
							</td> 
					</tr>
					<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="RED" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="remarks" />&nbsp;
							</font>
						</div>
					</td>

					<td width="50%" class="tdfont">
						<div align="left">
							<html:textarea name="InvParameterMstFB" property="remarks"
								cols="28" tabindex="1" readonly="<%=this.readOnly%>"
								onkeypress="return CheckMaxLength(event,this,50,1)">
							</html:textarea>
						</div>
					</td>
					</tr>




				</table>
			</his:ContentTag>

			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="InvParameterMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="InvParameterMstFB" property="hmode"
							value="VIEW">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-sv.png"/>'
								style="cursor: pointer"
								onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
								onclick="finalSubmit('SAVE')"  tabindex="1">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-clr.png"/>'
								style="cursor: pointer" onclick="clearaddForm()"
								onkeypress="if(event.keyCode==13) clearaddForm()" tabindex="1">
						</logic:notEqual>
					</logic:notEqual> <logic:equal name="InvParameterMstFB" property="hmode"
						value="MODIFY">
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