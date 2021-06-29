<!-- 
/**
 * @author CDAC
 */
-->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>


<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>

<script>


function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Seats
	// 2 -> Units
	
	if(listNo=="1")
	{
		source  = document.forms[0].symptomList;
		target = document.forms[0].selectedSymptoms;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}




function moveRightAll(listNo)
{
	var source;
	var target;
	
	if(listNo=="1")
	{
		source  = document.forms[0].symptomList;
		target = document.forms[0].selectedSymptoms;	
	}
	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
		
		targetlen = target.length;							
		target.length = (targetlen+1);				
			
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}
	deleteThis(target,source);
}




function deleteThis(source,target)
{	
	var tarlen = target.length;
	var srclen = source.length;

	var a1 = new Array(tarlen);
	var a2 = new Array(tarlen);
	var a3 = new Array(srclen);

	for(var i=0;i<tarlen;i++)
	{		
		a1[i]= target.options[i].value;
		a2[i]= target.options[i].text;	
	}
	for( i=0;i<srclen;i++)		
		a3[i]= source.options[i].value;

	target.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;

	for(i=0;i<tarlen;i++)
	{		
		flag = 0;
		for(k=0;k<srclen;k++)
			if(a1[i]==a3[k])
				flag = 1;					
		if(flag==0)
		{
			target.length = (counter+1);
			target.options[counter].value = a1[i];
			target.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}




function moveLeftSingle(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].symptomList;
		source = document.forms[0].selectedSymptoms;	
	}
	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}




function moveLeftAll(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].symptomList;
		source = document.forms[0].selectedSymptoms;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;
	
	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
	
		targetlen = target.length;							
		target.length = (targetlen+1);				
		
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}

	deleteThis(target,source);
}



function deskTypeSelected(deskType)
{
	if(deskType != "-1")
	{
		//alert(deskType);
		submitPage("SELECTDESK");
	}
}

function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit()
{
	if(document.forms[0].deskType.value=="-1")
	{
		alert("Select Desk Type ... ");
		document.forms[0].deskType.focus();
		return false;
	}
	if(document.forms[0].deskId.value=="-1")
	{
		alert("Select Desk ... ");
		document.forms[0].deskId.focus();
		return false;
	}
	if(document.forms[0].isValid.value=="-1")
	{
		alert("Select Valid State ... ");
		document.forms[0].isValid.focus();
		return false;
	}
	return true;
}

function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
}
</script>

<%@ page import ="registration.*,opd.*" %>





	<body >
	
		<html:form action="/master/ModifyViewAllergyWiseSymptomMaster.cnt">
			<html:hidden name="AllergyWiseSymptomMasterFB" property="hmode"/>

			<his:TransactionContainer>
			<%
				String varStatus="InProcess";
			%>
			
			
			<his:statusInProcessWithJsp>
			
			
			<logic:equal name="AllergyWiseSymptomMasterFB" property="hmode" value="VIEW">
			<his:TitleTag name="View Allergy Wise Symptom Master">
			</his:TitleTag>
			
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="allergyType"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;
									<bean:write name="AllergyWiseSymptomMasterFB" property="allergyTypeId"/>
			                       	</b>
								</font>
							</div>
						</td>
					</tr>
					</table>
			</his:ContentTag>
			</logic:equal>
		
		
		<logic:equal name="AllergyWiseSymptomMasterFB" property="hmode" value="VIEW">
			<his:SubTitleTag name="Added Symptoms">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<td width="15%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead"></td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%" class="tdfonthead"></td>
					
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="symptom"/>
								</font>
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
						</td>
						
						<td width="15%"  class="tdfont">
							<div align="center">
								<html:select name="AllergyWiseSymptomMasterFB" property="symptomList" disabled="true" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.SYMPTOM_SELECTED_SYMPTOM_LIST%>" >
								<html:options collection="<%=OpdConfig.SYMPTOM_SELECTED_SYMPTOM_LIST%>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="15%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead"></td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%" class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			
			
			
			<logic:equal name="AllergyWiseSymptomMasterFB" property="hmode" value="MODIFY">
				<his:TitleTag name="Modify Allergy Wise Symptom Master">
				</his:TitleTag>
			
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="allergyType"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;
									<bean:write name="AllergyWiseSymptomMasterFB" property="allergyTypeId"/>
			                        	</b>
								</font>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			
			
			<logic:equal name="AllergyWiseSymptomMasterFB" property="hmode" value="MODIFY">
			<his:SubTitleTag name="Select Symptom">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<td width="15%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead"></td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%" class="tdfonthead"></td>
					
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="symptom"/>
								</font>
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="AllergyWiseSymptomMasterFB" property="symptomList" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.SYMPTOM_REMAINING_SYMPTOM_LIST%>" >
										<html:options collection="<%=OpdConfig.SYMPTOM_REMAINING_SYMPTOM_LIST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="AllergyWiseSymptomMasterFB" property="selectedSymptoms" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.SYMPTOM_REMAINING_SYMPTOM_LIST%>" >
								<html:options collection="<%=OpdConfig.SYMPTOM_SELECTED_SYMPTOM_LIST%>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="15%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead"></td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%" class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
	</his:statusInProcessWithJsp>
		
		
		
			<his:ButtonToolBarTag>
				<span id="saveDiv">
					<%if(varStatus.equals("InProcess")){%>
						<logic:notEqual name="AllergyWiseSymptomMasterFB" property="hmode" value="VIEW">
							<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" tabindex="1" onclick="finalSubmit('MODIFYSAVE')">
						</logic:notEqual>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('LIST')" onkeypress="if(event.keyCode==13) submitPage('LIST')">
					<%} else{ %>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitPage('LIST')" onkeypress="if(event.keyCode==13) submitPage('LIST')">
					<%} %>
				</span>
			</his:ButtonToolBarTag>	
			<center><b><his:status/></b></center>
				<html:hidden name="AllergyWiseSymptomMasterFB" property="chk"/>
				<html:hidden name="AllergyWiseSymptomMasterFB" property="allergyTypeId"/>
				
				
	</his:TransactionContainer>
  </html:form>
 </body>
</html>