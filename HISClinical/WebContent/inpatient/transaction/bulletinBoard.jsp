<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@ page import="java.util.*"%>
<%@ page import="hisglobal.hisconfig.Config,hisglobal.vo.*"%>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function getOrderBy(mode,order){
	document.forms[0].hmode.value=mode;
	document.forms[0].order.value=order;
	document.forms[0].submit();
}
function validateBulletin(mode)
{
//alert("hi");
	var valid=true;
if(
	
	isSelected(document.forms[0].patStatusCode,"Patient Status") &&
	isEmpty(document.forms[0].patRemarks,"Remarks") 
	 )
	{
	 submitForm(mode);
	}
}
function patientRevoke()
{
if(document.forms[0].patStatusCode.disabled==false)
  {
	document.forms[0].patStatusCode.disabled=true;
	document.forms[0].patRemarks.disabled=true;
  }
  else
  if(document.forms[0].patStatusCode.disabled==true)
  {
	document.forms[0].patStatusCode.disabled=false;
	document.forms[0].patRemarks.disabled=false;
  } 

}	

function submitTile(mode)
{
if(mode=="CLEAR")
   {
    document.forms[0].patStatusCode.value=-1;
	document.forms[0].patRemarks.value="";
	document.forms[0].patRevoke.checked=false;
	document.forms[0].patStatusCode.disabled=false;
	document.forms[0].patRemarks.disabled=false;
   }
else
   {
    submitForm(mode);
   }   

}
function getPatientBulletinDetail(obj){
	//alert(obj.value)
	//alert(document.getElementsByName("isDirectCall")[0].value)
	document.getElementsByName("selectedIndex")[0].value=obj.value;
	document.getElementsByName("patCrNo")[0].value=obj.value
	document.getElementsByName("hmode")[0].value='GETPATDTL';
	document.forms[0].submit()
	//document.getElementsByName("InpatientBulletinFB")[0].submit()
}

function addRemarks(event)
{
	var path='/HISClinical/inpatient/inpatientBulletinDetail.cnt?hmode=ADDREMARKS&processId=4';
	openPopup(createFHashAjaxQuery(path),event,300,600);
}

</script>

<%@page import="inpatient.InpatientConfig"%>
<html>
<body>


<logic:equal name="InpatientBulletinFB" property="isDirectCall" value="DIRECT">
	<form name="InpatientBulletinFB" action='<his:path src="/inpatient/inpatientBulletinDetail.cnt"/>'>
</logic:equal>


<%
System.out.println("System date in jsp========="+(Date)session.getAttribute(Config.SYSDATEOBJECT));
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
<his:TitleTag name="Bulletin Board">
	
</his:TitleTag>

<his:InputCrNoTag name="InpatientBulletinFB">
</his:InputCrNoTag>

<his:statusTransactionInProcess>
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	<his:SubTitleTag name="Bulletin Details"> 
	<logic:present name="<%=InpatientConfig.INPATIENT_BULLETIN_DETAIL_VO%>">
	<bean:message key="allBulletinDetail" />
	<img class='button' src='<his:path src="/hisglobal/images/arrdouble-right.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "openDependentPopup('transaction/patientBulletinDetails.jsp',event,300,600,true);" onkeypress="if(event.keyCode==13)openDependentPopup('transaction/patientBulletinDetails.jsp',event,300,600,true);")>
	</logic:present>
	</his:SubTitleTag>
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		<logic:equal name="InpatientBulletinFB" property="transactionMode" value="<%=InpatientConfig.BULLETIN_BOARD %>">
		<td width="15%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="patient"/>
				<bean:message key="status"/></b>
				</font>
				</div>
	  		</td>
	  		
	  	<td width="85%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="remarks"/>
				</b>
				</font>
				</div>
	  		</td>	
	  	</logic:equal>	
	  	<logic:equal name="InpatientBulletinFB" property="transactionMode" value="<%=InpatientConfig.BULLETIN_DETAIL %>">
		<td width="15%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="patient"/>
				<bean:message key="status"/></b>
				</font>
				</div>
	  		</td>
	  		
	  	<td width="60%"  class="tdfonthead" colspan="2">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="remarks"/>
				</b>
				</font>
				</div>
	  		</td>	
	  		<td width="10%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="revoke"/>
				</b>
				</font>
				</div>
	  		</td>
	  	</logic:equal>	
	  	
	  	</tr>
	  	<logic:equal name="InpatientBulletinFB" property="transactionMode" value="<%=InpatientConfig.BULLETIN_BOARD %>">
	  	<logic:present name="<%=InpatientConfig.INPATIENT_BULLETIN_DETAIL_VO %>">
	  	<tr>
	  	<td width="15%" class="tdfont">
	  			<div align="center">
	  			<bean:write name="InpatientBulletinFB" property="patStatusName" />
	  			</div>
	  		</td>
	  		<td width="85%" class="tdfont">
	  			<div align="left">
	  			<bean:write name="InpatientBulletinFB" property="patRemarks" />
	  			
	  			</div>
	  		</td>
	  	</tr>
	  	</logic:present>
	  	</logic:equal>
	  	
	  	<logic:equal name="InpatientBulletinFB" property="transactionMode" value="<%=InpatientConfig.BULLETIN_DETAIL %>">
	  
	  	<tr>
	  	<td width="15%" class="tdfont">
	  			<div align="center">
	  			<html:select name="InpatientBulletinFB" tabindex="1" property="patStatusCode" styleClass="regcbo" >
	  				
	  				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=InpatientConfig.INPATIENT_STATUS_LIST%>" >	
					<html:options  collection="<%=InpatientConfig.INPATIENT_STATUS_LIST%>" property="value" labelProperty="label" />
	  			</logic:present>
	  			</html:select>
	  			</div>
	  		</td>
	  		<td width="60%" class="tdfont">
	  			<div align="center">
	  			<html:textarea property="patRemarks" name="InpatientBulletinFB" styleClass="textarea1" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"/>
	  			</div>
	  		</td>
	  		<td width="15%" class="tdfont">
	  			<div align="center">
	  				<html:button value=" Add "  property="mybutton" onclick="addRemarks(event)" style='cursor:pointer'  tabindex='1'/>
	  			</div>
	  		</td>
	  		<%
	  		boolean disable=true;
	  		%>
	  		
	  		<logic:present name="<%=InpatientConfig.INPATIENT_BULLETIN_DETAIL_VO %>">
	  		<%
	  		disable=false;
	  		%>
	  		</logic:present>
	  		<td width="10%" class="tdfont">
	  			<div align="center">
	  			<html:checkbox property="patRevoke" disabled="<%=disable %>" value="1" name="InpatientBulletinFB" onclick="patientRevoke()"></html:checkbox>
	  			
	  			</div>
	  		</td>
	  		
	  	</tr>
	  	</logic:equal>
	  
	  	
	  	</table>
	</his:ContentTag>
</his:statusTransactionInProcess>



<html:hidden name="InpatientBulletinFB" property="hmode" value="unspecified"/>
<html:hidden name="InpatientBulletinFB" property="patCrNo"/>
<html:hidden name="InpatientBulletinFB" property="transactionMode"/>
<html:hidden name="InpatientBulletinFB" property="selectedIndex"/>
<html:hidden name="InpatientBulletinFB" property="isDirectCall"/>
<html:hidden name="InpatientBulletinFB" property="order"/>

<his:ButtonToolBarTag>   
<his:statusNew>
<logic:equal name="InpatientBulletinFB" property="transactionMode" value="<%=InpatientConfig.BULLETIN_DETAIL %>">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">
</logic:equal>  
<logic:equal name="InpatientBulletinFB" property="transactionMode" value="<%=InpatientConfig.BULLETIN_BOARD %>">
   <logic:equal name="InpatientBulletinFB" property="isDirectCall" value="DESK">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitDesk('NEW')" onkeypress="if(event.keyCode==13) submitDesk('NEW')">
	</logic:equal>
	<logic:equal name="InpatientBulletinFB" property="isDirectCall" value="DIRECT">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
	</logic:equal>
	
  </logic:equal>   
  </his:statusNew>  
 
  <his:statusTransactionInProcess>
  
  <logic:equal name="InpatientBulletinFB" property="transactionMode" value="<%=InpatientConfig.BULLETIN_DETAIL %>">
	<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "validateBulletin('SAVE');" onkeypress="if(event.keyCode==13)validateBulletin('SAVE');")>
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('NEW')" onkeypress="if(event.keyCode==13) submitTile('NEW')">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">
</logic:equal>  

 <logic:equal name="InpatientBulletinFB" property="transactionMode" value="<%=InpatientConfig.BULLETIN_BOARD %>">
	<logic:equal name="InpatientBulletinFB" property="isDirectCall" value="DIRECT">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('unspecified')" onkeypress="if(event.keyCode==13) submitTile('unspecified')">
	</logic:equal>
	<logic:equal name="InpatientBulletinFB" property="isDirectCall" value="DESK">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('NEW')" onkeypress="if(event.keyCode==13) submitTile('NEW')">
	</logic:equal>
  </logic:equal>
  </his:statusTransactionInProcess>     	
</his:ButtonToolBarTag>
<his:statusList>
	<his:ContentTag>	
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="5%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="select"/>
				</b>
				</font>
				</div>
	  		</td>
			<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="crNo"/>
				</b>
				</font>
				</div>
	  		</td>
	  		
	  	<td width="25%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="patientName"/>
				</b>
				<a style="">
					<img src="/HIS/hisglobal/images/avai/arrow_up.gif" style="cursor: pointer;" alt="Ascending order" onclick="getOrderBy('ORDERBYNAME','0')" tabindex="1" border="0" height="10" width="10">
				</a>
				<a style="">
					<img src="/HIS/hisglobal/images/avai/arrow_down.gif" style="cursor: pointer;" alt="Desccending order" onclick="getOrderBy('ORDERBYNAME','1')" tabindex="1" border="0" height="10" width="10">
				</a>
				</font>
				</div>
	  		</td>	
	  	
		<td width="10%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="gender/age"/>
				</b>
				</font>
				</div>
	  		</td>
	  		
	  	<td width="25%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="wardType"/>
				</b>
				<a style="">
					<img src="/HIS/hisglobal/images/avai/arrow_up.gif" style="cursor: pointer;" alt="Ascending order" onclick="getOrderBy('ORDERBYWARDTYPE','0')" tabindex="1" border="0" height="10" width="10">
				</a>
				<a style="">
					<img src="/HIS/hisglobal/images/avai/arrow_down.gif" style="cursor: pointer;" alt="Desccending order" onclick="getOrderBy('ORDERBYWARDTYPE','1')" tabindex="1" border="0" height="10" width="10">
				</a>
				</font>
				</div>
	  		</td>	
	  	<td width="25%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="dept/unit"/>
				</b>
				</font>
				</div>
	  		</td>	
	  		<td width="15%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="addmissionNo"/>
				</b>
				</font>
				</div>
	  		</td>
	  	</tr>
	  	<logic:iterate id="patientBulletinVo" name="<%=InpatientConfig.IN_PATIENT_BUTTETIN_DETAIL_VO_ARRAY %>" indexId="index" type="hisglobal.vo.PatientBulletinDetailVO">
	  	<tr>
	  		<%String crNo=patientBulletinVo.getPatCrNo(); %>
	  		<td width="5%"  class="tdfont">
				<div align="center">	           
				<b>
				<html:radio name="InpatientBulletinFB" property="selectedIndex" value="<%=crNo%>" onclick="getPatientBulletinDetail(this)"></html:radio>
				</b>
				
				</div>
	  		</td>
	  		<td width="20%"  class="tdfont">
				<div align="center">	           
				<b><bean:write name="patientBulletinVo" property="patCrNo" /></b>
				</div>
	  		</td>
	  		<td width="25%"  class="tdfont">
				<div align="center">	           
				<b><bean:write name="patientBulletinVo" property="patName" />
				</b>
				</div>
	  		</td>
	  		<td width="10%"  class="tdfont">
				<div align="center">	           
				<b><bean:write name="patientBulletinVo" property="patAge" />
				</b>
				</div>
	  		</td>
	  		<td width="10%"  class="tdfont">
				<div align="center">	           
				<b><bean:write name="patientBulletinVo" property="wardType" />
				</b>
				</div>
	  		</td>
	  		<td width="25%"  class="tdfont">
				<div align="center">	           
				<b><bean:write name="patientBulletinVo" property="departmentName" />/
				<bean:write name="patientBulletinVo" property="departmentUnitName" />
				</b>
				</div>
	  		</td>
	  		<td width="15%"  class="tdfont">
				<div align="center">	           
				<b>
				<bean:write name="patientBulletinVo" property="patAdmNo" />
				</b>
				</div>
	  		</td>
	  	</tr>
	  	</logic:iterate>
	  	</table>
	  	</his:ContentTag>	
	</his:statusList>

<html:hidden name="InpatientBulletinFB" property="isDirectCall" />

<logic:equal name="InpatientBulletinFB" property="isDirectCall" value="DIRECT">
	</form>
</logic:equal>
<his:status/>
</body>
</html>
