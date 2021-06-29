
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.PatExtTreatmentDetailVO"%>
<%@page import="java.util.ArrayList"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function closeForm()
{
	self.close();
}

function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];  
	elmt.value=mode;
	document.forms[0].submit();
	self.close();
}
/*
function showDetail()
{
	//alert(obj.value);
	if(document.getElementsByName("instActivityType")[0].checked)
	{
		document.getElementById("divDeptUnitInstId").style.display="";
		document.getElementById("divDeptUnitActivityId").style.display="";
		document.getElementById("divAllInstId").style.display="none";
		document.getElementById("divAllActivityId").style.display="none";
	}
	else
	{
		document.getElementById("divDeptUnitInstId").style.display="none";
		document.getElementById("divDeptUnitActivityId").style.display="none";
		document.getElementById("divAllInstId").style.display="";
		document.getElementById("divAllActivityId").style.display="";
	}
}
*/

function getAllInstructionDetail(obj)
{
	var path='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=ALLINSTRUCTION';
	//alert("path "+path);
	child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}

function getDeptWiseInstDetail(obj)
{
	var path='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=DEPTWISEINSTRUCTION';
	//alert("path "+path);
	child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}

function selectChoice()
{
	var choice=document.getElementsByName('instActivityType')[0].value;
	//alert("choice "+choice);
	if(choice=="0")
	{
		
		
		alert(document.getElementsByName('instActivityType')[0].checked);
	}
	else
	{
		
		document.getElementsByName('instActivityType')[1].checked="true"
	}
}


</script>

<html:form action="/patTreatmentDetailTile">
	<body >
		<his:TitleTag name="OTHER INSTRUCTION/ACTIVITY">
		</his:TitleTag>
		
		<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td width="50%" class="tdfonthead" >
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						DepartmentUnitWise<html:radio property="instActivityType" value="0" onclick="getDeptWiseInstDetail(this)"></html:radio>
					</font>	
				</div>	
				</td>
				<td width="50%" class="tdfonthead" >
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						All<html:radio property="instActivityType" value="1" onclick="getAllInstructionDetail(this)"></html:radio>
					</font>	
				</div>	 
				</td>
			</tr>
		</table>
		
		<logic:notEmpty name="<%=OpdConfig.OTHER_INSTRUCTION_LIST_FOR_BOTH%>">
		<his:SubTitleTag name="Instruction">
		</his:SubTitleTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="select" />
								</b>
							</font>
						</div>
					</td>
			<td width="90%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="instructions" />
								</b>
							</font>
						</div>
					</td>		
			</tr>
			<logic:iterate id="entryObj" indexId="i" name="<%=OpdConfig.OTHER_INSTRUCTION_LIST_FOR_BOTH%>" type="hisglobal.utility.Entry">
			<tr>
				
				<td width="10%" class="tdfont">
						<div align="center">
						<%
								List otherInstList=(List)session.getAttribute(OpdConfig.PREV_OTHER_INSTRUCTION_LIST);
								if(otherInstList==null)
								{
									otherInstList=new ArrayList();
								}
								boolean flag=false;
								if(otherInstList.size()!=0)
								{
									for(int j=0;j<otherInstList.size();j++)
									{
										PatExtTreatmentDetailVO vo=(PatExtTreatmentDetailVO)otherInstList.get(j);
																			
										if(vo.getExtTreatmentId().equals(entryObj.getValue()))
										{
								
											flag=true;
							%>
							<input type="checkbox" name="otherInstructionExtId" value="<%=entryObj.getValue()%>" checked="checked">
							<%			
										}
									}
								}
								if(!flag)
								{
							%>
							<input type="checkbox" name="otherInstructionExtId" value="<%=entryObj.getValue()%>" >
							<%		
								}
										
							%>
							
							
							
						</div>
					</td>
				<td width="90%" class="tdfont">
						<div align="left">
							<%=entryObj.getLabel() %>
							<html:hidden name="PatientTreatmentDetailFB" property="otherInstruction" value="<%=entryObj.getLabel() %>"/>
						</div>
					</td>
				
			</tr>
			</logic:iterate>
		</table>
		</logic:notEmpty>
		
		
		<logic:notEmpty name="<%=OpdConfig.ONE_TIME_ACTIVITY_LIST_FOR_BOTH%>">
		<his:SubTitleTag name="One Time Activity">
		</his:SubTitleTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="select" />
								</b>
							</font>
						</div>
					</td>
			<td width="90%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="activity" />
								</b>
							</font>
						</div>
					</td>		
			</tr>
			<logic:iterate id="entryObj" indexId="i" name="<%=OpdConfig.ONE_TIME_ACTIVITY_LIST_FOR_BOTH%>" type="hisglobal.utility.Entry">
			<tr>
				
				<td width="10%" class="tdfont">
						<div align="center">
							
							<%
								List activityListList=(List)session.getAttribute(OpdConfig.PREV_OTHER_ACTIVITY_LIST);
								if(activityListList==null)
								{
									activityListList=new ArrayList();
								}
								boolean flag=false;
								
								if(activityListList.size()!=0)
								{
									for(int j=0;j<activityListList.size();j++)
									{
										PatExtTreatmentDetailVO vo=(PatExtTreatmentDetailVO)activityListList.get(j);
																			
										if(vo.getExtTreatmentId().equals(entryObj.getValue()))
										{
											flag=true;
							%>
							<input type="checkbox" name="activityExtId" value="<%=entryObj.getValue()%>" checked="checked">
							<%			
										}
										
									}
								}
								
								if(!flag)
								{
							%>
							<input type="checkbox" name="activityExtId" value="<%=entryObj.getValue()%>" >
							<%		
								}
								
							%>
							
						
							
						</div>
					</td>
				<td width="90%" class="tdfont">
						<div align="left">
							<%=entryObj.getLabel() %>
							<html:hidden name="PatientTreatmentDetailFB" property="activityExt" value="<%=entryObj.getLabel() %>"/>
						</div>
					</td>
				
			</tr>
			</logic:iterate>
		</table>
		</logic:notEmpty>
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/OK1.jpg"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('SAVEOTHERINSTRUCTION')" onkeypress="if(event.keyCode==13) submitPage('SAVEOTHERINSTRUCTION')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
        <html:hidden name="PatientTreatmentDetailFB" property="hmode"/>
        
    </body>	
</html:form>