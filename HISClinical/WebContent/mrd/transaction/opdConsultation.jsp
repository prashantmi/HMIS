<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.*"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />

<his:javascript src="/opd/opdJs/opd.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function validateIt()
{// confirm("Do You Want To Close The Episode ?");
	
	var flag=false;
	
	var subject=document.getElementsByName("subject")[0].value
	var i=0;
	//alert("subject.length  "+subject.length);
	var subLen=subject.length;
	while(i<subLen){
	subject=subject.replace(" ","")
	i=i+1;
	}
	//alert("subject.length  "+subject.length);
	var contentText=document.getElementsByName("content")[0].value;
	var textLength=contentText.length;
	if(document.getElementsByName("toDoctorSeatId")[0].value=="-1")
	{
		alert("Please Select Doctor Name")
	}
	else
	{
		if(textLength>1000){
	alert("Only 1000 Character can be send and your data is of "+contentText.length+" Character");
	}
	else
	{
		if(subject=="" && contentText.length==0){
			 flag=confirm("Do You Want To Send Mail Without Subject And Message ?");
		}
		else{
			if(subject==""){
				flag=confirm("Do You Want To Send Mail Without Subject ?");
			}
			else{
			if(contentText.length==0){
				flag=confirm("Do You Want To Send Mail Without Message ?");
			}
			else{
				flag =true;
			}
			}
		}
		// flag=true;
	}
		
}
	

	return flag;
}
</script>
<his:TransactionContainer>
<his:TitleTag>
		<his:name>
			<bean:message key="opdCosultation" />
		</his:name>
	</his:TitleTag>
	
<his:statusInProcess>


<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
	<his:SubTitleTag name="New Mail">
			
		</his:SubTitleTag>
		

 <his:ContentTag>
 <table width="100%" cellspacing="1" cellpadding="0">
 <tr>
 					<td width="10%"  class="tdfonthead">
					<div align="left">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font>
					<bean:message key="to"/></b>
					</font>
					</div>
					</td>      

 					<td   class="tdfont">
 					</td>
 					<td   class="tdfont">
					<div align="left" >	           
					<html:select name="OpdConsultationFB" property="toDoctorSeatId" tabindex="1" >
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=OpdConfig.OPD_ECONSULTANT_DETAIL_LIST%>" >
					<html:options collection ="<%=OpdConfig.OPD_ECONSULTANT_DETAIL_LIST%>" property = "value" labelProperty = "label"/>
					</logic:present>
					</html:select>
					</div>
	  				</td>      
</tr>
</table>


<table width="100%" cellspacing="1" cellpadding="0">
<tr>
					      
	  				<td width="10%" class="tdfonthead">
					<div align="left">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="subject"/></b>
					</font>
					</div>
	  				</td>      
	  			<td   class="tdfont">
					<div >	      
					<html:text name="OpdConsultationFB" property="subject" tabindex="1"  size="50" 
					maxlength="50" onkeypress="return notSpecChar(this,event)" ></html:text>     
					</div>
				</td>
</tr>
</table>
<table width="100%" cellspacing="1" cellpadding="0">
<tr>
				<td width="10%" class="tdfonthead">
				</td>
				<td width="90%"  class="tdfont">
					<div >	      
					<html:textarea name="OpdConsultationFB" property="content" tabindex="1" cols="120" rows="15" 
					onkeypress="return (validateTextArea(event,this,'1000') && notSpecChar(this,event))" ></html:textarea>  
					</div>
				</td>
</tr>
</table>
</his:ContentTag>
</his:statusInProcess>

<his:ButtonToolBarTag>
		  <img class='button' src='<his:path src="/hisglobal/images/btn-go.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "submitFormOnValidate(validateIt(),'SEND');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt(),'SEND');")>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
</his:ButtonToolBarTag>
</his:TransactionContainer>
<html:hidden name="OpdConsultationFB" property="hmode"/>
<html:hidden name="OpdConsultationFB" property="patCrNo"/>