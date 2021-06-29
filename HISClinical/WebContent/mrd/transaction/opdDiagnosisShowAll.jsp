<!-- 
/**
 * @copyright CDAC
 * @developer Hruday Meher
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
	
<%@ page import ="opd.*" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.EpisodeDiagnosisVO"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>

<his:javascript src="/opd/opdJs/opdAjax.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">


	
function createHiddenElement(obj)
{

	//var elem=opener.document.createElement("input");
	//elem.setAttribute('type','hidden')
	//elem.setAttribute('name','selectedDiagnosis')
	//elem.setAttribute('value',obj.value)
	
	//opener.document.forms[0].appendChild(elem)
	//opener.document.forms[0].selectedDiagnosis.value=obj.value
	//alert("success"+obj.value)
	
	//opener.document.forms[0].selectedDiagnosis.value=obj.value
}

function createInputTypeElement()
{
	var elem=opener.document.createElement("input");
			elem.setAttribute('type','hidden')
			elem.setAttribute('name','selectedDiagnosis')
			opener.document.forms[0].appendChild(elem)
}

function validateIt()
{	var i=0
	var j=0;
	var len=0;
	alert((''+opener.document.forms[0].selectedDiagnosis!='undefined'));
	if(opener.document.forms[0].selectedDiagnosis!=null && opener.document.forms[0].selectedDiagnosis!='undefined')
		len=parseInt(opener.document.forms[0].selectedDiagnosis.length);
		
		
	alert(len);	
	for(i=0;i<document.forms[0].selectedDiagnosis.length;i++)
	{
		if(document.getElementsByName("selectedDiagnosis")[i].checked==true)
		{
			createInputTypeElement()
			opener.document.getElementsByName("selectedDiagnosis")[len].value=document.forms[0].selectedDiagnosis[i].value
			alert("success"+opener.document.getElementsByName('selectedDiagnosis')[len].value)
			len++;
		}
	}
	opener.document.getElementsByName("hmode")[0].value='REPEAT';
	alert("len  "+opener.document.getElementsByName('selectedDiagnosis').length)
	opener.document.forms[0].submit();
	self.close();
}

function closeForm()
{
	self.close();
}

function getPrevDiagnosisDetail(event,path)
{
	//openDependentPopup(path,event,600,700,'yes');
	openPopup(createFHashAjaxQuery(path),event,300,600);
}

function showRemarks(obj)
{
	document.getElementsByName("showRemarksTextArea")[0].value=obj;
	document.getElementById("divRemarks").style.display="block";
}

</script>
<html:form action="/opdDiagnosis" >
<body>
<his:TitleTag name="Revoked Diagnosis">

</his:TitleTag>

<his:ContentTag>
<table width="100%" cellpadding="0" cellspacing="1">
	<tr>

		<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						<bean:message key="visitDate"/>
					</b>	
				</font>
			</div>
		</td>
		<td width="7%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						<bean:message key="diagnosisCode"/>
					</b>	
				</font>
			</div>
		</td>
		<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						<bean:message key="diagnosisName"/>
					</b>	
				</font>
			</div>
		</td>
		<td width="8%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						<bean:message key="diagnosisCodeType"/>
					</b>	
				</font>
			</div>
		</td>
		<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						<bean:message key="diagonosisType"/>
					</b>	
				</font>
			</div>
		</td>
		<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						<bean:message key="diagnosisSite"/>
					</b>	
				</font>
			</div>
		</td>
		<td width="8%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						<bean:message key="status"/>
					</b>	
				</font>
			</div>
		</td>
		<td width="7%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						<bean:message key="remarks"/>
					</b>	
				</font>
			</div>
		</td>
	</tr>
	<%
		Map revokeMap=(Map)session.getAttribute(OpdConfig.ALL_REVOKED_DIAGNOSIS_VO);
		Iterator itr=revokeMap.keySet().iterator();
		while(itr.hasNext())
		{
			String dCode=(String)itr.next();
			List revokedList=(List)revokeMap.get(dCode);
			for(int i=0;i<revokedList.size();i++)
			{
				EpisodeDiagnosisVO arrayPrevDiagnosis=(EpisodeDiagnosisVO)revokedList.get(i);
				
				
	%>
		
	  		<tr>
				
				<td class="tdfontheadExam" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=(String) arrayPrevDiagnosis.getEntryDate() %>
						</font>	
	     			</div>
				</td>
				<td class="tdfontheadExam" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=(String) arrayPrevDiagnosis.getDiagnosticCode() %>
						</font>	
	     			</div>
				</td>
				<td class="tdfontheadExam" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=(String) arrayPrevDiagnosis.getDignosisName() %>
						</font>	
					</div>
				</td>
				<td class="tdfontheadExam" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=(String) arrayPrevDiagnosis.getDiagnosisCodeLabel() %>
						</font>	
	     			</div>
				</td>
				<td class="tdfontheadExam" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=(String) arrayPrevDiagnosis.getDiagnosticTypeName() %>
						</font>	
					</div>
				</td>
				<td class="tdfontheadExam" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=(String) arrayPrevDiagnosis.getDiagnosisSiteLabel()==null?"-":(String) arrayPrevDiagnosis.getDiagnosisSiteLabel() %>
						</font>	
					</div>
				</td>
				<td class="tdfontheadExam" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=OpdConfig.DIAGNOSIS_IS_REPEAT_ARRAY[Integer.parseInt(arrayPrevDiagnosis.getIsRepeat())] %>
							
						</font>	
					</div>
				</td>		
				<td class="tdfontheadExam" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<%String str=((String) arrayPrevDiagnosis.getRemarks()==null?"No Remarks":(String) arrayPrevDiagnosis.getRemarks()); %>
							<a style="cursor: pointer;" onclick="showRemarks('<%=str %>')">VIEW</a></b>
						</font>	
					</div>
				</td>
			</tr>		
							
		
	<%}} %>
	
   
</table>

</his:ContentTag>
<logic:equal value="PREV" property="hmode" name="OpdDiagnosisFB">
<his:SubTitleTag name="Revoked Diagnosis Details">
</his:SubTitleTag>
		<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="visitDate"/>
							</font>
						</div>
					</td>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="diagnosisCode"/>
							</font>
						</div>
					</td>
					<td width="40%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="diagnosisName"/>
							</font>
						</div>
					</td>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="status"/>
							</font>
						</div>
					</td>
				</tr>
				
				<logic:iterate id="allPrevDiagnosis" name="<%=OpdConfig.PREVIOUS_ALL_DIAGNOSIS_VO%>" indexId="idx" type="hisglobal.vo.EpisodeDiagnosisVO">
					<tr>
						<td class="tdfont" width="20%" >
							<div align="center">
								<%=allPrevDiagnosis.getEntryDate()  %>
							</div>
						</td>
						<td class="tdfont"  width="20%">
							<div align="center">
								<%=allPrevDiagnosis.getDiagnosticCode()  %>
							</div>
						</td>	
						<td class="tdfont"  width="40%">
							<div align="center">
								<%=allPrevDiagnosis.getDignosisName()  %>
							</div>
						</td>	
						<td class="tdfont"  width="20%">
							<div align="center">
								<logic:equal name="allPrevDiagnosis" property="isRepeat" value="<%=OpdConfig.DIAGNOSIS_IS_REPEAT_NEW %>">
									Added
								</logic:equal>
								<logic:equal name="allPrevDiagnosis" property="isRepeat" value="<%=OpdConfig.DIAGNOSIS_IS_REPEAT_REPEAT %>">
									Repeated
								</logic:equal>
								<logic:equal name="allPrevDiagnosis" property="isRepeat" value="<%=OpdConfig.DIAGNOSIS_IS_REPEAT_STOP %>">
									Revoked
								</logic:equal>
							</div>
						</td>		
					</tr>
					
				</logic:iterate>
			</table>
	</logic:equal>	
<his:ButtonToolBarTag>
		<!--  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "validateIt();" onkeypress="if(event.keyCode==13)validateIt();")> -->
		 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
          
</his:ButtonToolBarTag>
	<div id="divRemarks" style="display: none;">
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="100%" class="tdfont" >
						<div align="center">
							<html:textarea name="OpdDiagnosisFB" property="showRemarksTextArea" rows="3" cols="150" readonly="true">
							</html:textarea>
						</div>	
					</td>
				</tr>
			</table>	
		</his:ContentTag>
	</div>

	<html:hidden name="OpdDiagnosisFB" property="hmode" />
	<html:hidden name="OpdDiagnosisFB" property="unitDiagnosisCodeType"/>
	<html:hidden name="OpdDiagnosisFB" property="showRemarksTextArea"/>


<his:status/>
</body>
</html:form>