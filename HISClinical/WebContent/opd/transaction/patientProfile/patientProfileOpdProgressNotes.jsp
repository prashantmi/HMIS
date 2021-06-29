<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/opd/js/generic_patient_profile.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function validateAllergies()
{
	var chks = document.getElementsByName('selectedRow');
	var flag = false;
	for(var i=0;i<chks.length;i++)
		if(chks[i].checked)
		{
			flag = true;
			break;
		}
	if(!flag)
	{
		alert("Select At least One Allergies for Profile .. ");
		chks[i].focus();
		return false;
	} 
	return true;
}

function closeForm()
{
	self.close();
}

function viewProgNotes(index)
{
	
	if(document.getElementsByName("hiddenProgNotes")[index].value!="")
	{
		document.getElementsByName("showProgNotes")[0].value=document.getElementsByName("hiddenProgNotes")[index].value;
	}
	else
	{
		document.getElementsByName("showProgNotes")[0].value="No Progress Notes";
	}
			
	
	document.getElementById("divViewProgNotes").style.display="block"; 
}


function validateProgress()
{
	var len=document.getElementsByName("selectedRow").length
	var count=0;
	var progress="";
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("selectedRow")[i].checked)
		{
			count++;
		}
	}
	
	if(count==0)
	{
		alert("Please Select Progress Notes");
	}
	else
	{
		for(var i=0;i<len;i++)
		{
			if(document.getElementsByName("selectedRow")[i].checked)
			{
				progress=progress+document.getElementsByName("selectedRow")[i].value+","+"\n";
			}
		}
	
		progress=progress.substring(0,progress.length-2);
		
		if(document.getElementsByName("remarks")[0].value!="")
		{
			opener.document.getElementsByName("remarks")[0].value=opener.document.getElementsByName("remarks")[0].value+"\n"+progress;
		}
		else
		{
			opener.document.getElementsByName("remarks")[0].value=progress;
		}
		// submitPage('PROFILEFOOTER');
		self.close();
	}
}


</script>

<html:form action="/opdPatientProfile">
	<body>

<his:TitleTag key="patientProfile" width="100%">
</his:TitleTag>

<his:statusTransactionInProcess>

<his:SubTitleTag key="progressNotes">
</his:SubTitleTag>

<logic:notEmpty name="<%=OpdConfig.OPD_PATIENT_PROFILE_PROGRESS_NOTES_ARRAY%>" >
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="5%"  class="tdfonthead">
					<div align="center">	
						<input type="checkbox" name="selectCheck" onclick="selectAllCheckboxes(this,'selectedRow')"> 
					</div>
				</td>
			
				
				<td width="20%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="entryDate"/></b>
						</font>
					</div>
				</td>
				
				<td width="75%"  class="tdfonthead">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="progNote"/></b>
						</font>
					</div>
				</td>
				
			</tr>
		</table>
		
		<table width="100%" cellpadding="0" cellspacing="0">
			<logic:iterate id="episodeSummaryVO" name="<%=OpdConfig.OPD_PATIENT_PROFILE_PROGRESS_NOTES_ARRAY%>" indexId="i" type="hisglobal.vo.EpisodeSummaryDetailVO" >
				<tr>
					<td width="5%"  class="tdfont">
						<div align="center">	           
							<html:checkbox name="GenericPatientProfileFB" property="selectedRow" value="<%=episodeSummaryVO.getVisitNotes()%>" ></html:checkbox>
						</div>
					</td>  
					<td width="20%"  class="tdfont" nowrap="nowrap">
						<div align="center">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="episodeSummaryVO" property="entryDate" />
							</font>
						</div>
					</td>
					<td width="75%"  class="tdfont">
						<div align="center">	           
							<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>'  style=cursor:pointer tabindex="1" onclick ="viewProgNotes('<%=i %>')">
						</div>
						<div id="divProgNotesId" style="display: none;">
							<html:textarea name="GenericPatientProfileFB" property="hiddenProgNotes" rows="5" cols="130"  value="<%=episodeSummaryVO.getVisitNotes()%>"></html:textarea>
						</div>
					</td>
					
					
				</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
</logic:notEmpty>

</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) validateProgress();" onclick="validateProgress();" tabindex="1" />		
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="closeForm()" onkeypress="if(event.keyCode==13) closeForm();">
	</his:statusTransactionInProcess>
</his:ButtonToolBarTag>
<div id="divViewProgNotes" style="display: none;">
       			<his:SubTitleTag name="Progress Notes">
       			</his:SubTitleTag>
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="GenericPatientProfileFB" property="showProgNotes" rows="2" cols="125" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>

</body>

<html:hidden name="GenericPatientProfileFB"	property="remarks" />
<his:status/>
</html:form>
