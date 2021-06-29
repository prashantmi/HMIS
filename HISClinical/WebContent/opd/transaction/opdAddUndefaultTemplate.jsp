
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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function setSelectedTemp()
{
	var selUn="";
	var checks =document.getElementsByName('undefTemp');
	for(var i=0;i<checks.length;i++)
	{
		if(checks[i].checked)
			selUn+=checks[i].id+"#";
	}
	if(selUn!="")selUn=selUn.substring(0,selUn.length-1);
	
	document.getElementsByName('selUndefaultTemp')[0].value=selUn;
	//alert(document.getElementsByName('selUndefaultTemp')[0].value);
}

function validateAddUndefault()
{
	if(document.getElementsByName('selUndefaultTemp')[0].value=="")
	{
		alert("Select at Least One Template to Add ...");
		document.getElementsByName('undefTemp')[0].focus();
		return false;
	}
	opener.document.getElementsByName('selUndefaultTemp')[0].value=document.getElementsByName('selUndefaultTemp')[0].value;
	opener.submitMyForm(true,'ADDUNDEFAULT');
	
	window.close();
	
	return true;
}

</script>

<his:SubTitleTag name='Select Templates To Add'>
</his:SubTitleTag>
	
	<his:ContentTag>
 		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%"  class="tdfonthead"></td>
				<td width="25%"  class="tdfont"></td>
				<td width="25%"  class="tdfonthead"></td>
				<td width="25%"  class="tdfont"></td>
			</tr>
			<logic:iterate name="<%=OpdConfig.OPD_DESKMENU_TEMPLATES_INACTIVE_IDS_LIST%>" id="list" >
	 			<tr>
					<td width="25%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b> <input type="checkbox" name="undefTemp" id='<bean:write name="list" property="value"/>' onclick="setSelectedTemp();"> <bean:write name="list" property="label"/> </input> </b>
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont"></td>
					<td width="25%"  class="tdfonthead"></td>
					<td width="25%"  class="tdfont"></td>
				</tr>
			</logic:iterate>
			<tr>
				<td width="25%"  class="tdfonthead"></td>
				<td width="25%"  class="tdfont"></td>
				<td width="25%"  class="tdfonthead"></td>
				<td width="25%"  class="tdfont"></td>
			</tr>
		</table>
	</his:ContentTag>
<his:statusTransactionInProcess>
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>'  style="cursor:pointer"   onclick ="validateAddUndefault();" onkeypress="if(event.keyCode==13) validateAddUndefault();" >
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer"  onclick ="window.close();" onkeypress="if(event.keyCode==13) window.close();" >
</his:ButtonToolBarTag>

<html:hidden name="OpdTemplateTabFB" property="selUndefaultTemp" value=""/>

