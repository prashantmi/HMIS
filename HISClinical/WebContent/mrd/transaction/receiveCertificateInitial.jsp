<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

function submitPage(mode)
{
//	alert(mode);
	elmt=document.getElementsByName("mode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}
function showDesk()
{
	submitPage('DESK');
}

function showRecordType()
{
	submitPage('RECORDTYPE');
}

//added by swati 
//date:14-05-2019
function showRecordADMNOwise()
{
	submitPage('RECORDTYPEADMNOWISE');
}

//added by swati 
//date:14-05-2019
function showRecordCRNOwise()
{
	submitPage('RECORDTYPECRNOWISE');
}


//added by swati 
//date:30-04-2019
function changeUI()
{
	
   
  if(document.getElementById("r2").checked == true)
  {
      document.getElementById("CRNOID").style.display="block";
      document.getElementById("wardwisediv").style.display="none";
      document.getElementById("ADMID").style.display="none";
  }
  if(document.getElementById("r3").checked == true)
  {
      document.getElementById("ADMID").style.display="block";
      document.getElementById("wardwisediv").style.display="none";
      document.getElementById("CRNOID").style.display="none";
  }
   
  
  if(document.getElementById("r1").checked == true)
  {
      document.getElementById("CRNOID").style.display="none";
      document.getElementById("ADMID").style.display="none";
      document.getElementById("wardwisediv").style.display="block";
      //document.getElementsByName("strCrNo")[0].value = "";
      //submitForm('NEW');
  }
  
  
   
  
  
  
}


//added by swati on date:01-05-2019

function checkMode()
{


	if(document.getElementsByName("mode")[0].value == "RECORDTYPE"  )
	  {
	       document.getElementById("CRNOID").style.display="none";
	       document.getElementById("ADMID").style.display="none";
	       document.getElementById("wardwisediv").style.display="block";
	       document.getElementById("r1").checked=true;
	  } 
		
	if(document.getElementsByName("mode")[0].value == "DESK"  )
  {
       document.getElementById("CRNOID").style.display="none";
       document.getElementById("ADMID").style.display="none";
       document.getElementById("wardwisediv").style.display="block";
       document.getElementById("r1").checked=true;
  } 

	
   if(document.getElementsByName("mode")[0].value == "RECORDTYPEADMNOWISE"  )
   {
        document.getElementById("CRNOID").style.display="none";
        document.getElementById("ADMID").style.display="block";
        document.getElementById("wardwisediv").style.display="none";
        document.getElementById("r3").checked=true;
       // document.getElementById("abc").style.display="none";
   } 

   if(document.getElementsByName("mode")[0].value == "RECORDTYPECRNOWISE"  )
   {
  	  document.getElementById("ADMID").style.display="none";
        document.getElementById("CRNOID").style.display="block";
        document.getElementById("wardwisediv").style.display="none";
        document.getElementById("r2").checked=true;
   } 

   
}



</script>
<body onload="checkMode();">
	<html:form action="/receiveCertificateInitial">
	<his:TransactionContainer>
		<his:SubTitleTagBroad name="Case Sheet Archival"><%-- Acceptance In MRD"> --%>
		</his:SubTitleTagBroad>
		
		
		
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
				

					<!-- 				added by swati sagar on date:30-04-2019 -->
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfont">
							<div align="right">
								<input type="radio" value="1" id="r1" name="strCheckMode"
									onclick="changeUI();">Ward/Unit Wise &nbsp; <input
									type="radio" value="3" id="r3" name="strCheckMode"
									onclick="changeUI();">Admission No. Wise <input
									type="radio" value="2" id="r2" name="strCheckMode"
									onclick="changeUI();">CR No. Wise

							</div>
						</td>
					</tr>
					<!-- 				-- -->
			</table>
		
		
		<div id='wardwisediv'>
			<logic:equal name="ReceiveCertificateInitialFB" value="<%=MrdConfig.NO %>" property="isMrdListOne">
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead" >
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="mrd"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="ReceiveCertificateInitialFB" property="mrdCode" onchange="showRecordType()">
									<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.LIST_ALL_MRD_USER_BASED%>">
											<html:options collection="<%=MrdConfig.LIST_ALL_MRD_USER_BASED%>" property="value" labelProperty="label" />
										</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
				</table>	
			</logic:equal>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="50%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="recordType"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont" >
						<div align="left">
							<html:select name="ReceiveCertificateInitialFB" property="recordType" onchange="showDesk()">
								<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.RECORD_TYPE%>">
										<html:options collection="<%=MrdConfig.RECORD_TYPE%>" property="value" labelProperty="label" />
									</logic:present>
							</html:select>
						</div>
					</td>
				</tr>
			</table>	
			</div>
			
			<!-- 				added by swati sagar on date:30-04-2019 -->


			<div id="ADMID" style="display: none;">
				<table class="TABLEWIDTH" width="100%">
					<tr>
						<td width="50%" class="tdfonthead"><font color="red"
							id="mandCRId">*</font>Admission No.</td>
						<td width="50%" class="tdfont">
							<div >
								<html:text property="patAdmNo" name="ReceiveCertificateInitialFB"
									onkeypress='return numbersonly(event);' maxlength="15"></html:text>

								<img src="../hisglobal/images/GO.png"
									onClick="showRecordADMNOwise();" align="top"
									style="cursor: pointer;">
							</div>
						</td>
						<!-- <td width="15%" class="tdfonthead"></td>
						<td width="25%" class="tdfont"></td> -->
					</tr>
				</table>
			</div>


			<div id="CRNOID" style="display: none;">
				<table class="TABLEWIDTH" width="100%">
					<tr>
						<td width="50%" class="tdfonthead"><font color="red"
							id="mandCRId">*</font>CR No.</td>
						<td width="50%" class="tdfont">
							<div >
								<html:text property="strCrNo" name="ReceiveCertificateInitialFB"
									onkeypress='return numbersonly(event);' maxlength="15"></html:text>

								<img src="../hisglobal/images/GO.png"
									onClick="showRecordCRNOwise();" align="top"
									style="cursor: pointer;">
							</div>
						</td>
						<!-- <td width="15%" class="tdfonthead"></td>
						<td width="25%" class="tdfont"></td> -->
					</tr>
				</table>
			</div>
			
			
			
			
			
			
			
			
				
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
		</his:ButtonToolBarTag>
		
		<html:hidden name="ReceiveCertificateInitialFB" property="mode" />
		<html:hidden name="ReceiveCertificateInitialFB" property="isMrdListOne" />
		<html:hidden name="ReceiveCertificateInitialFB" property="mrdCode" />
		
	</his:TransactionContainer>
	
	</html:form>
	<his:status/>
</body>		
</html>