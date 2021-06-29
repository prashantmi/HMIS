<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

 <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function closeForm()
{
	var index=document.getElementsByName("drugInstructionIndex")[0].value;
	//alert("index "+index);
	//opener.document.getElementById("drugInstructionButton").title=document.getElementsByName("selFinalInstructions")[0].value;
	
	opener.document.getElementsByName("revokeRemarks")[index].value=document.getElementsByName("revokeRemarks")[0].value
	self.close();
}

function populate(obj)
{
	var val=document.getElementsByName("selFinalInstructions")[0].value;
	
	if(val.length<500)
	{
		if(obj.checked)
		{
			if(document.getElementsByName("selFinalInstructions")[0].value=="")
			{
				document.getElementsByName("selFinalInstructions")[0].value=obj.value;
			}
			else
			{
				var tarValue=document.getElementsByName("selFinalInstructions")[0].value+" , "+obj.value;
				document.getElementsByName("selFinalInstructions")[0].value=tarValue.substring(0,500);
			}
		}
	}
	
}

function populateInstruction()
{
	var index=document.getElementsByName("drugInstructionIndex")[0].value;
	document.getElementsByName("revokeRemarks")[0].value=opener.document.getElementsByName("revokeRemarks")[index].value;
}



</script>

<html:form action="/patTreatmentDetailTile">
	<body onload="populateInstruction()">
		<his:TitleTag name="Revoke Remarks">
		</his:TitleTag>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
					<td class="tdfonthead" width="25%" >
			  			<div align="right">	           
			 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 					<b>
			 						Revoke Remarks
			 					</b>	
			  				</font>
			  			</div>
		      		</td>
					<td class="tdfont">
						<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
							<html:textarea tabindex="1" rows="3" cols="100" property="revokeRemarks" onkeypress="return (validateTextArea(event,this,'500'))">
							</html:textarea>
						</font> 
					</td>
				</tr>
			</table>
			
			<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        	</his:ButtonToolBarTag>
        </his:ContentTag>
       
		
    <html:hidden property="drugInstructionIndex" name="PatientTreatmentDetailFB"/>
    </body>	
</html:form>