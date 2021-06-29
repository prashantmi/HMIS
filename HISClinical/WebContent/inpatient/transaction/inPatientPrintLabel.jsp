<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
window.onload=function(){
	var printLabelType=document.getElementsByName("printLabelType")
	//alert(obj.length)
	var obj
	if(printLabelType){
		for(var i=0;i<printLabelType.length;i++){
			if(printLabelType[i].checked){
				obj=printLabelType[i];
				break;
			}
		}
		disableTextBox(obj)
	}	
}
function submitForPrint()
{
	document.getElementsByName("hmode")[0].value="PRINTLABEL"
	var printLabelType=document.getElementsByName("printLabelType")
	var value;
	for(var i=0;i<printLabelType.length;i++){
		if(printLabelType[i].checked){
			value=printLabelType[i].value;
			break;
		}
	}
	//alert(value)
	if(document.getElementsByName("noOfLabel")[value]){
		var noOfLabel=document.getElementsByName("noOfLabel")[value].value
		if(noOfLabel==""){
			alert("Enter No. of Label")
			document.getElementsByName("noOfLabel")[value].focus();
			return false;
}
	document.getElementsByName("noOfLabel")[0].value=noOfLabel;
	}
	document.forms[0].submit();
}

function disableTextBox(obj){
	var value=obj.value;
	//alert(value)
	var noOfLabel=document.getElementsByName("noOfLabel")
	for(var i=0;i<noOfLabel.length;i++){
		if(i!=value){
			document.getElementsByName("noOfLabel")[i].disabled=true;
		}
	}
	document.getElementsByName("noOfLabel")[value].disabled=false;

}
</script>
<his:TransactionContainer>
	<his:TitleTag name="InPatient Print Label">
	</his:TitleTag>
	<%try{%>
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	<%}catch(Exception e){} %>
<his:statusTransactionInProcess>
	<logic:present name="<%=DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO %>">
	<his:SubTitleTag name="Label Print Option">
	</his:SubTitleTag>		
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="10%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
							<b>
								<bean:message key="select"/>
								</b>
							</font>		
						</div>
					</td>
					<td width="50%"  class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="labelType"/>
							</b>
							</font>
						</div>
					</td>
					<td width="40%"  class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="noOfLabel"/>
								</b>
							</font>		
						</div>
					</td>
				</tr>
			 <%String printLabelType[]=InpatientConfig.PRINT_LABEL_TYPE; %>
			 <%for(int index=0;index<printLabelType.length;index++){ %>
			 <tr>
				<td width="10%"  class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:radio property="printLabelType" value="<%=String.valueOf(index) %>" name="inPatientPrintLabelFB" onclick="disableTextBox(this)"></html:radio>
							</font>		
						</div>
					</td>
					<td width="50%"  class="tdfont">
						<div align="center">
							<%=printLabelType[index] %>
						</div>
					</td>
					<td width="40%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<%if(index!=2){%>
									<html:text name="inPatientPrintLabelFB" property="noOfLabel" onkeypress="return validateNumeric(event)" size="5" ></html:text>
									<%} %>
								</b>
							</font>		
						</div>
					</td>
				</tr>
				<%} %>
		</table>
	</his:ContentTag>
	</logic:present>	
</his:statusTransactionInProcess>	
	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
		<img class='button' src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "return submitForPrint();" onkeypress="if(event.keyCode==13)return submitForPrint();")>
		</his:statusTransactionInProcess>		
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
 	</his:ButtonToolBarTag>
	<html:hidden name="inPatientPrintLabelFB" property="hmode"/>
	<html:hidden name="inPatientPrintLabelFB" property="patCrNo"/>

</his:TransactionContainer>

