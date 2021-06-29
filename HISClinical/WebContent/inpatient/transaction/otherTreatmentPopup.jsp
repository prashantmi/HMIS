<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="inpatient.InpatientConfig"%>
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
function viewRemarks(obj)
{
	document.getElementsByName("showRemarks")[0].value=obj;
	document.getElementById("divViewRemarks").style.display="block"; 
}

function showDetail()
{
	document.getElementById("divViewRemarks").style.display="none"; 
	
	if(document.getElementsByName("choice")[0].checked)
	{
		document.getElementById("divExtTreatId").style.display="";
		document.getElementById("divActivityId").style.display="none";
	}
	else
	{
		document.getElementById("divActivityId").style.display="";
		document.getElementById("divExtTreatId").style.display="none";
	}
}

</script>

<html:form action="/nurExtTreatAdministration">
	<body onload="showDetail()">
		<his:TitleTag name="Executed Other Treatment/Activity ">
		</his:TitleTag>
		
		<table width="100%"  cellspacing="1" cellpadding="0">  
			<tr>
				<td class="tdfont" nowrap width="65%">       	      
		    		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      			<bean:message key="otherTreatment"/>
		      		</font>
		      		<html:radio name="ExtAdministrationFB" property="choice" tabindex="1" value="0" onclick="showDetail()"/> 
		        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       				<bean:message key="activity"/>
		       			</font> 
		        	<html:radio name="ExtAdministrationFB" property="choice" tabindex="1" value="1" onclick="showDetail()"/>
			</td> 	
			</tr>
			</table>
		
		
		
		<his:ContentTag>
		
		<div id="divExtTreatId" style="display: none;">
		<logic:present name="<%=InpatientConfig.EXECUTED_PAT_EXT_TREAT_LIST %>">
		<logic:notEmpty name="<%=InpatientConfig.EXECUTED_PAT_EXT_TREAT_LIST %>">
				
		<his:SubTitleTag name="Executed Other Treatment">
		</his:SubTitleTag>
		
		<table width="100%" id="drugExecutionDetailId" cellspacing="1" cellpadding="0">  
			 <tr>
			 	<td width="7%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 <b>
						 	<bean:message key="treatmentName" />
						 	</b>
						</font>
					</div>
				 </td>
			 	<td width="7%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 <b>
						 	<bean:message key="administrationDate" />
						 	</b>
						</font>
					</div>
				 </td>
				 <td width="10%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="startTime(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="16%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="endTime(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="16%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
					 		<bean:message key="remark" />
					 		</b>
					 	</font>
					</div>
				</td>
			</tr>	
			<logic:iterate id="extAdminDtlVO" indexId="i" name="<%=InpatientConfig.EXECUTED_PAT_EXT_TREAT_LIST %>" type="hisglobal.vo.ExtAdminDtlVO">
			<tr>
				<td class="tdfont" width="7%" >
					<div align="center">
						<%=extAdminDtlVO.getExtTreatmentName() %>
					</div>
				</td>
				<td class="tdfont" width="7%" >
					<div align="center">
						<%=extAdminDtlVO.getAdministrationDate() %>
					</div>
				</td>
				<td class="tdfont" width="10%" >
					<div align="center">
						<%=extAdminDtlVO.getAdministrationTime() %>
					</div>
				</td>
				<td class="tdfont" width="16%" >
					<div align="center">
						<%
							if(extAdminDtlVO.getAdministrationEndTime()==null)
							{
						%>
						-
						<%		
							}
							else
							{
						%>
						<bean:write name="extAdminDtlVO" property="administrationEndTime"/>
						<%		
							}
						%>
					</div>
				</td>
				<td class="tdfont" width="10%" >
							<div align="center">
								<a style="cursor:pointer" onclick="viewRemarks('<%=(extAdminDtlVO.getRemarks()==null)?"No Remark":extAdminDtlVO.getRemarks() %>')" >
								VIEW
								</a>
							</div>
						</td>
				
			</tr>		
		</logic:iterate> 
		 </table> 
		 </logic:notEmpty>	 
		</logic:present>
		<logic:empty name="<%=InpatientConfig.EXECUTED_PAT_EXT_TREAT_LIST%>">
		<table width="100%" cellpadding="0" cellspacing="1" >
					<tr>
						<td>
						<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>No Other Treatment</b>
						</font>
						</td>
					</tr>
				</table>
	</logic:empty>
		</div>
		
		<div id="divActivityId" style="display: none;">
		<logic:present name="<%=InpatientConfig.EXECUTED_PAT_ACTIVITY_LST %>">
		<logic:notEmpty name="<%=InpatientConfig.EXECUTED_PAT_ACTIVITY_LST %>">
		
		<his:SubTitleTag name="Executed Activity">
		</his:SubTitleTag>
		
		
		<table width="100%" id="drugExecutionDetailId" cellspacing="1" cellpadding="0">  
			 <tr>
			 	<td width="7%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 <b>
						 	<bean:message key="treatmentName" />
						 	</b>
						</font>
					</div>
				 </td>
			 	<td width="7%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 <b>
						 	<bean:message key="administrationDate" />
						 	</b>
						</font>
					</div>
				 </td>
				 <td width="10%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="startTime(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="16%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
					 		<bean:message key="remark" />
					 		</b>
					 	</font>
					</div>
				</td>
			</tr>	
			<logic:iterate id="extAdminDtlVO" indexId="i" name="<%=InpatientConfig.EXECUTED_PAT_ACTIVITY_LST %>" type="hisglobal.vo.ExtAdminDtlVO">
			<tr>
				<td class="tdfont" width="7%" >
					<div align="center">
						<%=extAdminDtlVO.getExtTreatmentName() %>
					</div>
				</td>
				<td class="tdfont" width="7%" >
					<div align="center">
						<%=extAdminDtlVO.getAdministrationDate() %>
					</div>
				</td>
				<td class="tdfont" width="16%" >
					<div align="center">
						<%=extAdminDtlVO.getAdministrationTime() %>
					</div>
				</td>
				<td class="tdfont" width="10%" >
							<div align="center">
								<a style="cursor:pointer" onclick="viewRemarks('<%=(extAdminDtlVO.getRemarks()==null)?"No Remark":extAdminDtlVO.getRemarks() %>')" >
								VIEW
								</a>
							</div>
						</td>
				
			</tr>		
		</logic:iterate> 
		 </table> 
		 </logic:notEmpty>	 
		</logic:present>
		<logic:empty name="<%=InpatientConfig.EXECUTED_PAT_EXT_TREAT_LIST%>">
		<table width="100%" cellpadding="0" cellspacing="1" >
					<tr>
						<td>
						<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>No Activity</b>
						</font>
						</td>
					</tr>
				</table>
	</logic:empty>
		</div>
		</his:ContentTag>
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
    </body>	
     <div id="divViewRemarks" style="display: none;">
       			<his:SubTitleTag name="Remarks">
       			</his:SubTitleTag>
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="ExtAdministrationFB" property="showRemarks" rows="5" cols="130" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>
</html:form>