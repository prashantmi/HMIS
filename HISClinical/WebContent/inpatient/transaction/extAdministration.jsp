<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.*" %>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
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
<!--

//-->
/*
function (obj1)
{	
	var itemTypeId=obj1.value.split("#")[1];
	var ivfluidFlag=obj1.value.split("#")[2];
	//alert("ivfluidFlag "+ivfluidFlag);
	showIvfluid(ivfluidFlag);
	var url='/HISClinical/inpatient/nurDrugAdministration.cnt?hmode=DRUGROUTE&itemTypeId='+itemTypeId;
	httpDrugRouteListRequest("GET",url,true);
}
*/
function findIndex(obj)
{
	var ctrlArr = document.getElementsByName(obj.name);
	var index=-1;
	for(var i=0;i<ctrlArr.length;i++)
	{
		if(ctrlArr[i]==obj)
		{
			index=i;
			break;
		}
	}
	return index;
}

function validateForm(mode)
{
	
	var extLen=document.getElementsByName('extSelIndexArray').length;
	
	var saveFlag=false;
	
	for(var i=0;i<extLen;i++)
	{
		if(document.getElementsByName('extSelIndexArray')[i].checked)
		{
			saveFlag=true;
		}
	}
	
	if(typeof document.getElementsByName("activityExtId")[0] != 'undefined')
	{ 
		//alert("hiiiiiiiiiiiiii");
		if(document.getElementsByName("activityExtId")[0].value!="")
		{
			saveFlag=true;
		}
	}
	
	if(saveFlag==false)
	{
		alert("Please select Treatment/Activity");
		return false;
	}
	
	//validating first row of activity
	if(typeof document.getElementsByName("activityExtId")[0] != 'undefined')
	{
		if(document.getElementsByName("activityExtId")[0].value!="" || document.getElementsByName("activitySelStartExecTimeHrs")[0].value!="" || document.getElementsByName("activitySelStartExecTimeMin")[0].value!="")
		{
			if(document.getElementsByName("activityExtId")[0].value=="")
			{
				alert("Please Select Activity");
				document.getElementsByName("activityExtId")[0].focus();
				return false;
			}
			if(document.getElementsByName("activitySelStartExecTimeHrs")[0].value=="")
			{
				alert("Please Enter Hours");
				document.getElementsByName("activitySelStartExecTimeHrs")[0].focus();
				return false;
			}
			if(document.getElementsByName("activitySelStartExecTimeMin")[0].value=="")
			{
				alert("Please Enter Minute");
				document.getElementsByName("activitySelStartExecTimeMin")[0].focus();
				return false;
			}
		}
	}
	// validate ext treatment exec
	
	for(var i=0;i<extLen;i++)
	{
			var doseTime=document.getElementsByName("extSelDoseTimeArray")[i].value;
			var doseTimeHours=trimNum(doseTime.split(":")[0]);
			var doseTimeMinut=doseTime.split(":")[1];
			//alert("doseTimeHours "+doseTimeHours);
			//alert("doseTimeMinut "+doseTimeMinut);
			
			var sysHours=document.getElementsByName("sysHours")[0].value;
			var sysMinut=document.getElementsByName("sysMinut")[0].value;
			//alert("sysHours "+sysHours);
			//alert("sysMinut "+sysMinut);
			
			var timeLimit=document.getElementsByName("timeLimit")[0].value;
			//alert("timeLimit "+timeLimit);
			
			var totalTimeLimitHours=parseInt(doseTimeHours)+parseInt(timeLimit);
			var totalTimeLimitMinut=doseTimeMinut;
			//alert("totalTimeLimitHours "+totalTimeLimitHours);
			//alert("totalTimeLimitMinut "+totalTimeLimitMinut);
			
			var beforeTimeLimit=document.getElementsByName("beforeTimeLimit")[0].value;
			
			var totalBefTimeLmtHour=parseInt(doseTimeHours)-parseInt(beforeTimeLimit);
			var totalBefTimeLmtMinut=doseTimeMinut;
		
		
		if(document.getElementsByName('extSelIndexArray')[i].checked)
		{
			if(document.getElementsByName("extSelStartExecutionTimeHrs")[i].value=="")
			{
				alert("Please Enter Start Time Hours");
				document.getElementsByName("extSelStartExecutionTimeHrs")[i].focus();
				return false;
			}
			
			if(document.getElementsByName("extSelStartExecutionTimeHrs")[i].value>23)
			{
				alert("Hours can not be greater than 23");
				document.getElementsByName("extSelStartExecutionTimeHrs")[i].focus();
				return false;
			}
			
			if(document.getElementsByName("extSelStartExecutionTimeMin")[i].value=="")
			{
				alert("Please Enter Start Time Minute");
				document.getElementsByName("extSelStartExecutionTimeMin")[i].focus();
				return false;
			}
			if(document.getElementsByName("extSelStartExecutionTimeMin")[i].value>59)
			{
				alert("Minut can not be greater than 59");
				document.getElementsByName("extSelStartExecutionTimeMin")[i].focus();
				return false;
			}
			if(document.getElementsByName("extSelStartExecutionTimeHrs")[i].value<totalBefTimeLmtHour)
			{
				alert("Treatment can not be given befor "+ document.getElementsByName("beforeTimeLimit")[0].value +" hours of schedule.");
				document.getElementsByName("extSelStartExecutionTimeHrs")[i].focus();
				return false;
			}
			
			if(document.getElementsByName("extSelStartExecutionTimeHrs")[i].value==totalBefTimeLmtMinut)
			{
				if(document.getElementsByName("extSelStartExecutionTimeMin")[i].value<totalBefTimeLmtMinut)
				{
					alert("Treatment can not be given befor "+ document.getElementsByName("beforeTimeLimit")[0].value +" hours of schedule.");
					document.getElementsByName("extSelStartExecutionTimeMin")[i].focus();
					return false;
				}
			}
			
			
			if(document.getElementsByName("extSelStartExecutionTimeHrs")[i].value>totalTimeLimitHours)
			{
				alert("Treatment can not be given after "+ document.getElementsByName("timeLimit")[0].value +" hours of schedule.");
				document.getElementsByName("extSelStartExecutionTimeHrs")[i].focus();
				return false;
			}
			if(document.getElementsByName("extSelStartExecutionTimeHrs")[i].value==totalTimeLimitHours)
			{
				if(document.getElementsByName("extSelStartExecutionTimeMin")[i].value>totalTimeLimitMinut)
				{
					alert("Treatment can not be given after "+ document.getElementsByName("timeLimit")[0].value +" hours of schedule.");
					document.getElementsByName("extSelStartExecutionTimeMin")[i].focus();
					return false;
				}
				
			}
			
			if(document.getElementsByName("extSelIsDurationBound")[i].value=="1")
			{
				if(document.getElementsByName("extSelEndExecutionTimeHrs")[i].value=="")
				{
					alert("Please Enter End Time Hours");
					document.getElementsByName("extSelEndExecutionTimeHrs")[i].focus();
					return false;
				}
				
				if(document.getElementsByName("extSelEndExecutionTimeHrs")[i].value>23)
				{
					alert("Hours can not be greater than 23");
					document.getElementsByName("extSelEndExecutionTimeHrs")[i].focus();
					return false;
				}
			
				if(document.getElementsByName("extSelEndExecutionTimeMin")[i].value=="")
				{
					alert("Please Enter End Time Minute");
					document.getElementsByName("extSelEndExecutionTimeMin")[i].focus();
					return false;
				}
				if(document.getElementsByName("extSelEndExecutionTimeMin")[i].value>59)
				{
					alert("Minut can not be greater than 59");
					document.getElementsByName("extSelEndExecutionTimeMin")[i].focus();
					return false;
				}
				
			}
		}
	}
	
	submitPage(mode);
}


function trimNum(n)
{
	if(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}

function submitPage(mode)
{
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
}

function getInstructionDetail(event)
{
	var path='/HISClinical/inpatient/nurExtTreatAdministration.cnt?hmode=INSTRUCTION';
	//alert("path "+path);
	openDependentPopup(createFHashAjaxQuery(path),event,250,500,true);
}

function getOtherTreatDetail(event)
{
	var path='/HISClinical/inpatient/nurExtTreatAdministration.cnt?hmode=OTHERTREATMENT';
	//alert("path "+path);
	openDependentPopup(createFHashAjaxQuery(path),event,400,600,true);

}

function AddRowToActivityTable(validate,mode)
{
	//alert(validate);
	if(validate)
	{
		//alert("inside");
		document.getElementsByName('hmode')[0].value=mode
		document.forms[0].submit();
	}
	
}
function deleteActivityRow(mode,obj)
{
	document.getElementsByName('hmode')[0].value=mode
	document.getElementsByName('index')[0].value=obj
	document.forms[0].submit();
}

function validateActivityRow()
{
	if(document.getElementsByName("activityExtId")[0].value=="")
	{
		alert("Please Select Activity");
		document.getElementsByName("activityExtId")[0].focus();
		return false;
	}
	if(document.getElementsByName("activitySelStartExecTimeHrs")[0].value=="")
	{
		alert("Please Enter Hours");
		document.getElementsByName("activitySelStartExecTimeHrs")[0].focus();
		return false;
	}
	if(document.getElementsByName("activitySelStartExecTimeMin")[0].value=="")
	{
		alert("Please Enter Minute");
		document.getElementsByName("activitySelStartExecTimeMin")[0].focus();
		return false;
	}
	
	return true;
}



function checkDate(a,b)
{
	//alert("inside check date");
	var valid=true;
	var days=0;
	//var a=document.getElementsByName("sosSelExpriryDate")[0].value;
	//alert("expiry date "+ a);
	var aArray=a.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	//var b=document.getElementsByName("sysDate")[0].value; 
	//alert("sysdate -> "+b);
	//var b=new Date;
	var bArray=b.split("-");
	var bday=bArray[0];
	var bmonth=bArray[1];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);
	days=((bdate-adate)/86400000);
	//alert("days-------> "+days);
	return days;
}
</script>

	<his:TitleTag name="External Treatment Administration">
	</his:TitleTag>
	
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	
		<his:SubTitleTag name="Today Other Treatment Detail">
			
			<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td width="55%">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<a style="cursor:pointer" onclick="getInstructionDetail(event)" >
	      							<U><bean:message key="instructions"/></U>
	      						</a>
							</b>
						</font>		
					</div>
				</td>
				<td width="45%">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<a style="cursor:pointer" onclick="getOtherTreatDetail(event)" >
	      							<U><bean:message key="treatment/activityGiven"/></U>
	      						</a>
							</b>
						</font>		
					</div>
				</td>
				
			</tr>
			
		</table>
		</his:SubTitleTag>
		
	<logic:notEmpty name="<%=InpatientConfig.TODAY_EXT_TREATMENT_LIST_FOR_PAT %>">
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td width="5%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="select" />
								</b>
							</font>
						</div>
					</td>
			<td width="15%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="treatmentName" />
								</b>
							</font>
						</div>
					</td>
					
					<td width="15%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="time" />
								<bean:message key="timeFormat" />
								
								</b>
							</font>
						</div>
					</td>
					
					<td width="10%" class="tdfonthead" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="startTime(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead" id="ivfluidFlagLableId" ">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="endTime(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="16%" class="tdfonthead" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="remark" />
							</b>
						</font>
					</div>
				</td>
		</tr>
		<logic:iterate id="extAdminDtlVO" indexId="idx" name="<%=InpatientConfig.TODAY_EXT_TREATMENT_LIST_FOR_PAT%>" type="hisglobal.vo.ExtAdminDtlVO">
		<tr>
			<td width="5%" class="tdfont" nowrap="nowrap" >
					<div align="center">
						<%
								String doseTime=extAdminDtlVO.getDoseTime();
								String index=idx.toString();
																
								String doseTimeHours=doseTime.split(":")[0];
								String doseTimeMinut=doseTime.split(":")[1];
								
								String sys=(String)session.getAttribute(Config.SYSDATE);
								String time=sys.split(" ")[1];
								String sysHours=time.split(":")[0];
								String sysMinut=time.split(":")[1];
								String timeLimit=InpatientConfig.TIME_LIMIT_FOR_DRUG;
								
								Integer totalTimeLimitHours=Integer.parseInt(sysHours)+Integer.parseInt(timeLimit);
								Integer totalTimeLimitMinut=Integer.parseInt(sysMinut);
								if(Integer.parseInt(doseTimeHours)<totalTimeLimitHours)
								{
							%>
							<html:checkbox property="extSelIndexArray" value="<%=idx.toString()%>" name="ExtAdministrationFB"></html:checkbox>
							<%		
								}
								else
								{
									if(Integer.parseInt(doseTimeHours)==totalTimeLimitHours)
									{
										if(Integer.parseInt(doseTimeMinut)<totalTimeLimitMinut)
										{
											//enabled
							%>
							<html:checkbox property="extSelIndexArray" value="<%=idx.toString()%>" name="ExtAdministrationFB"></html:checkbox>
							<%				
										}
										else
										{
							%>
							<html:checkbox property="extSelIndexArray" value="<%=idx.toString()%>" name="ExtAdministrationFB" disabled="true"></html:checkbox>
							<%			
											//disabled
										}
									}
									else
									{
							%>
							<html:checkbox property="extSelIndexArray" value="<%=idx.toString()%>" name="ExtAdministrationFB" disabled="true"></html:checkbox>
							<%			
										//disabled
									}
								}	
							%>
							
						
						<html:hidden name="" property="extSelTreatmentNameArray" value="<%=extAdminDtlVO.getExtTreatmentName() %>"/>
						<html:hidden name="" property="extSelDoseTimeArray" value="<%=extAdminDtlVO.getDoseTime() %>"/>
						<%
							if(extAdminDtlVO.getIsDurationBound()!=null )
							{
						%>		
								<html:hidden name="" property="extSelIsDurationBound" value="<%=extAdminDtlVO.getIsDurationBound() %>"/>
						<%	
							}
							else
							{
						%>
								<html:hidden name="" property="extSelIsDurationBound" value="<%=InpatientConfig.IS_DURATION_BOUND_NO %>"/>
						<%		
							}
						%>
						
						<html:hidden name="" property="extSelAdviceDateArray" value="<%=extAdminDtlVO.getAdviceDate() %>"/>
						<html:hidden name="" property="extSelSerealNoArray" value="<%=extAdminDtlVO.getSerialNo() %>"/>
					</div>
				</td>
			<td width="15%" class="tdfont" nowrap="nowrap" >
					<div align="center">
						<bean:write name="extAdminDtlVO" property="extTreatmentName"/>
					</div>
				</td>
			<td width="10%" class="tdfont" nowrap="nowrap" >
					<div align="center">
						<bean:write name="extAdminDtlVO" property="doseTime"/>
					</div>
				</td>
			<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<html:text name="ExtAdministrationFB" property="extSelStartExecutionTimeHrs" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateHrs()" />
							:
							<html:text name="ExtAdministrationFB" property="extSelStartExecutionTimeMin" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateMin()"  />
						</div>
				</td>
				<td width="10%" class="tdfont" nowrap="nowrap" id="ivfluidFlagId" >
					<div align="center">
						<%
							
							if(extAdminDtlVO.getIsDurationBound()==null)
							{
								extAdminDtlVO.setIsDurationBound("");
							}
							if(extAdminDtlVO.getIsDurationBound().equals(InpatientConfig.IS_DURATION_BOUND_YES))
							{
						%>
						<html:text name="ExtAdministrationFB" property="extSelEndExecutionTimeHrs" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateHrs()" disabled="false"/>
						:
						<html:text name="ExtAdministrationFB" property="extSelEndExecutionTimeMin" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateMin()" disabled="false" />
						<%		
							}
							else
							{
						%>
						<html:text name="ExtAdministrationFB" property="extSelEndExecutionTimeHrs" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateHrs()" readonly="true"/>
						:
						<html:text name="ExtAdministrationFB" property="extSelEndExecutionTimeMin" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateMin()" readonly="true" />
						<%		
							}
						%>
						
					</div>
				</td>
				<td width="20%" class="tdfont" nowrap="nowrap" >
					<div align="center">
						<html:text name="ExtAdministrationFB" property="extSelRemarksArray" value="" size="20" tabindex="1"  maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
					</div>
				</td>	
		</tr>
		</logic:iterate>
	</table>
	</logic:notEmpty>	
	
	<logic:notEmpty name="<%=InpatientConfig.PREV_ONE_TIME_ACTIVITY_LIST_FOR_PAT %>">
	<his:SubTitleTag name="Activity">
	</his:SubTitleTag>
		
		<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td width="15%" class="tdfonthead">
					<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<font color="#FF0000">*</font>
								<bean:message key="activity" />
								</b>
							</font>
						</div>
					</td>
					
					<td width="10%" class="tdfonthead" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="time(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="16%" class="tdfonthead" nowrap="nowrap" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="remark" />
							</b>
						</font>
					</div>
				</td>
				<td width="5%" class="tdfonthead" nowrap="nowrap" >
				</td>
		</tr>
			
		<tr>
			<td width="15%" class="tdfont" >
					<div align="center">
						<html:select name="ExtAdministrationFB" property="activityExtId" tabindex="1" value="" onchange="sendDataForSOSDrugDoseList(this)" > 
							<html:option value="">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.PREV_ONE_TIME_ACTIVITY_LIST_FOR_PAT %>" >
							<html:options collection="<%=InpatientConfig.PREV_ONE_TIME_ACTIVITY_LIST_FOR_PAT %>" property="value" labelProperty="label"/>
							</logic:present>
							
						</html:select>
					</div>
				</td>
			<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							
							<html:text name="ExtAdministrationFB" property="activitySelStartExecTimeHrs" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateHrs()" />
							:
							<html:text name="ExtAdministrationFB" property="activitySelStartExecTimeMin" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateMin()"  />
						</div>
				</td>	
			<td width="10%" class="tdfont" nowrap="nowrap" >
					<div align="center">
						<html:text name="ExtAdministrationFB" property="activitySelRemarks" value="" size="25"  tabindex="1" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
					</div>
				</td>
			<td  class="tdfont" width="5%">
					<div align="left">  
						<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' alt="Add Component" title="Add Component" onkeypress="if(event.keyCode==13)if(true) AddRowToActivityTable(validateActivityRow(),'ADDDACTIVITYROW') ;" onclick="if(true)AddRowToActivityTable(validateActivityRow(),'ADDDACTIVITYROW')">
					</div>
				</td> 			
		</tr>
		<logic:notEmpty name="<%=InpatientConfig.SELECTED_ACTIVITY_DETAIL_LIST%>">	
			<logic:iterate id="extAdminDtlVO" indexId="idx" name="<%=InpatientConfig.SELECTED_ACTIVITY_DETAIL_LIST%>" type="hisglobal.vo.ExtAdminDtlVO">	
				<tr>
					<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<bean:write name="extAdminDtlVO" property="extTreatmentName"/>
						</div>
					</td>
					
					<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<bean:write name="extAdminDtlVO" property="administrationTime"/>
						</div>
					</td>
					
					<td width="10%" class="tdfont" nowrap="nowrap" >
						<div align="center">
							<bean:write name="extAdminDtlVO" property="remarks"/>
						</div>
					</td>
					<td class="tdfont" width="5%" >
						<div align="left">
							<img name="minus" class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteActivityRow('DELETEDACTIVITYROW',findIndex(this)) ;" onclick=" deleteActivityRow('DELETEDACTIVITYROW',findIndex(this))">
						</div>
					</td>	
				</tr>
			</logic:iterate>
			</logic:notEmpty>
	</table>	
	</logic:notEmpty>
	

	
	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	     </his:statusTransactionInProcess> 
		<his:statusDone>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:statusDone>
		
	</his:ButtonToolBarTag>
	

<html:hidden name="ExtAdministrationFB" property="hmode"/>
<html:hidden name="ExtAdministrationFB" property="patCrNo"/>
<html:hidden name="ExtAdministrationFB" property="episodeVisitNo"/>
<html:hidden name="ExtAdministrationFB" property="episodeCode"/>
<html:hidden name="ExtAdministrationFB" property="admissionNo"/>
<html:hidden name="ExtAdministrationFB" property="sysDate"/>
<html:hidden name="ExtAdministrationFB" property="admissionDate"/>
<html:hidden name="ExtAdministrationFB" property="index"/>
<html:hidden name="ExtAdministrationFB" property="sysHours"/>
<html:hidden name="ExtAdministrationFB" property="sysMinut"/>
<html:hidden name="ExtAdministrationFB" property="timeLimit"/>
<html:hidden name="ExtAdministrationFB" property="beforeTimeLimit"/>

	