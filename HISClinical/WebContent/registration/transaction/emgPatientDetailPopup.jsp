<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="registration.RegistrationConfig"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>


function callThisOnload(){	
alert('popup')
}
function  closeWindow(){
	opener.document.getElementsByName('hmode')[0].value="NEW";
	opener.document.forms[0].submit();
	self.close();
}

function  submitForm(mode){
	document.getElementsByName('hmode')[0].value=mode;
	opener.document.getElementsByName('hmode')[0].value=mode;
	//alert("close");
	//alert("before submit form");
	opener.document.forms[0].submit();
	//alert("form submit");
	self.close();

}

function  submitFormForNewDeptVisit(mode,obj){
	//alert(obj.value)
	opener.document.getElementsByName('patCrNo')[0].value=obj.value;
	opener.document.getElementsByName('hmode')[0].value=mode;
	//alert("close");
	//alert("before submit form");
	opener.document.forms[0].submit();
	//alert("form submit");
	self.close();

}

</script>
	<body>
		<html:form action="/emgNewPatRegistration">
	<%try{ %>
		
			 <his:TitleTag name="Duplicate Patient Detail"> 
			 </his:TitleTag>
			<his:ContentTag>
			 <table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
				
					
					<td width="15%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="crNo"/></b></font>
					</div>
		  			</td>
					<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="patient"/><bean:message key="name"/></b></font>
					</div>
		  			</td>
					<td width="10%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="gender/age"/></b></font></div>
		  			</td>
					<td width="10%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="fatherName"/></b></font></div>
		  			</td>
					<td width="23%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="address"/></b></font></div>
		  			</td>
					<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="dateOfRegistration"/></b></font></div>
		  			</td>
					</tr>
				<logic:iterate id="patientVo" name="<%=RegistrationConfig.ALL_PATIENT_VO_LIST %>" type="hisglobal.vo.PatientVO">	
				  <tr>
				 
					
					<td width="15%"  class="tdfont">
					<div align="center">	           
						<b><bean:write name="patientVo" property="patCrNo"/></b>
					</div>
		  			</td>
					<td width="20%"  class="tdfont">
					<div align="center">	           
						<b><bean:write name="patientVo" property="patFirstName"/> 
						<bean:write name="patientVo" property="patMiddleName"/> 
						<bean:write name="patientVo" property="patLastName"/></b>
					</div>
		  			</td>
					<td width="10%"  class="tdfont">
					<div align="center">	           
						<b><bean:write name="patientVo" property="patGender"/>/
						<bean:write name="patientVo" property="patAge"/></b>
					</div>
		  			</td>
					<td width="10%"  class="tdfont">
					<div align="center">	           
						<b><bean:write name="patientVo" property="patGuardianName"/></b>
					</div>
		  			</td>
					<td width="23%"  class="tdfont">
					<div align="center">	           
						<b>
						<bean:write name="patientVo" property="patAddCity"/>
						</b>
					</div>
		  			</td>
					<td width="20%"  class="tdfont">
					<div align="center">	           
						<b><bean:write name="patientVo" property="registerDate"/></b>
					</div>
		  			</td>
					
					</tr>
				</logic:iterate>	
				</table>
			</his:ContentTag>
		 	<his:ButtonToolBarTag>
<!--		 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('SAVEASNEWPATIENT');" onclick ="submitForm('SAVEASNEWPATIENT');"> 
		 		<input type="button" value="Save as New Patient" onclick ="submitForm('SAVEASNEWPATIENT');"/> -->
		 		<img class="button" src='<his:path src="/hisglobal/images/SaveAsNewPatient.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('SAVEASNEWPATIENT');" onclick ="submitForm('SAVEASNEWPATIENT');">
		 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) closeWindow();" onclick ="closeWindow();">
		 	</his:ButtonToolBarTag>
		 	<html:hidden name="EmgNewRegFB" property="hmode"/>
		 	<html:hidden name="EmgNewRegFB" property="isDuplicatePatientPopup"/>
		 	
	 	<%} catch(Exception e){
			e.printStackTrace();
		} %>
	 		</html:form>
	  
	  
	  
	    <his:status/>
	</body>
	
		
		
	
</html>