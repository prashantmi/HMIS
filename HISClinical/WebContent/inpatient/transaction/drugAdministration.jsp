<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="inpatient.InpatientConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>  --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script type="text/javascript">
function showPrevDrugSchedule(index,hmode)
{	
	var path='/HISClinical/inpatient/nurDrugAdministration.cnt?hmode=PREVDRUGSHEDULE&scheduleIndex='+index;
	child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
	child.opener = self;
	return child
}

function AddRowToTable(validate,mode)
{
	if(validate)
	{
		document.getElementsByName('hmode')[0].value=mode
		document.forms[0].submit();
	}
}

function deleteRow(mode,obj)
{
	document.getElementsByName('hmode')[0].value=mode
	document.getElementsByName('index')[0].value=obj
	document.forms[0].submit();
}

function sendDataForDrugRouteList(obj1)
{	
	var itemTypeId=obj1.value.split("#")[1];
	var ivfluidFlag=obj1.value.split("#")[2];
	//alert("ivfluidFlag "+ivfluidFlag);
	showIvfluid(ivfluidFlag);
	var url='/HISClinical/inpatient/nurDrugAdministration.cnt?hmode=DRUGROUTE&itemTypeId='+itemTypeId;
	httpDrugRouteListRequest("GET",url,true);
}

function showIvfluid(ivfluidFlag)
{
	if(ivfluidFlag!=0)
	{
		document.getElementsByName("selEndExecutionTimeHrs")[0].disabled=false;
		document.getElementsByName("selEndExecutionTimeMin")[0].disabled=false;
		/*
		document.getElementById("ivfluidFlagId").style.display="";
		document.getElementById("ivfluidFlagLableId").style.display="";
		*/
	}
	else
	{
		document.getElementsByName("selEndExecutionTimeHrs")[0].disabled=true;
		document.getElementsByName("selEndExecutionTimeMin")[0].disabled=true;
		/*
		document.getElementById("ivfluidFlagId").style.display="none";
		document.getElementById("ivfluidFlagLableId").style.display="none";
		*/
	}
}


function httpDrugRouteListRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		request = new XMLHttpRequest();
		intialDrugRouteListReq(reqType,url,asynch);
	}
	else if(window.ActiveXObject)
	{
		request=new ActiveXObject("Msxml2.XMLHTTP");
		if (! request)
			request=new ActiveXObject("Microsoft.XMLHTTP");
		if(request)
			/* Unlikely to branch here, as IE uses will be able to use either 
			one of the constructors*/
			intialDrugRouteListReq(reqType,url,asynch);
		else
			alert("Your browser does not permit the use of all of this application's features!");
	}
	else
		alert("Your browser does not permit the use of all of this application's features!");
}

function intialDrugRouteListReq(reqType,url,isAsynch)
{
	/* Specify the function that will handle the HTTP response */
	request.onreadystatechange=handleDrugRouteList;
	request.open(reqType,url,isAsynch);
	
	/* set the Content-Type header for a POST request */
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	request.send(null);
}

function handleDrugRouteList()
{
	if(request.readyState == 4)
	{
		popupList=null;
		if(request.status == 200)
		{
			//getting drugRouteId corresponding drug(drug Id )  which is adviced By doctor
			var len=document.getElementsByName('prevDrugRouteId').length;
			var selDrugId=document.getElementsByName("selDrugID")[0].value.split("#")[0];
			var selDrugRouteId="";
			for(var i=0;i<len;i++)
			{
				var drugId=document.getElementsByName('prevDrugRouteId')[i].value.split("#")[1];
				if(drugId==selDrugId)
				{
					selDrugRouteId=document.getElementsByName('prevDrugRouteId')[i].value.split("#")[0];
				}
			}
			
			//setting ooption value in Drug Route Combo
			var comboTextArray = request.responseText.split("$");
			var routeCombo=document.getElementsByName("selDrugRouteId")[0];
			routeCombo.innerHTML="";
			
			if(comboTextArray!=null && comboTextArray.length!="1")
			{
				for(i=0,j=1;i<comboTextArray.length && j<comboTextArray.length;i=i+2,j=j+2)
				{
					var op=document.createElement("option");
					op.value=comboTextArray[i];
					op.innerHTML=comboTextArray[j];
					routeCombo.appendChild(op);
				}
				//selected index means which element u want to show selected
				//routeCombo.selectedIndex=0;
				
				//here setting value of drugRouteId which we want to show selected
				routeCombo.value=selDrugRouteId;
			}
			else
			{
				var op=document.createElement("option");
				op.value="-1";
				op.innerHTML="Select Value";
				routeCombo.appendChild(op);
				selectIndexVal = -1;
			}
		}
		else
		{
			alert("A problem occurred with communicating between "+"the XMLHttpRequest object and the server program.");
		}
	}
}

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

function checkDate()
{
	//alert("inside check date");
	var valid=true;
	var days=0;
	var a=document.getElementsByName("selExpriryDate")[0].value;
	//alert("expiry date "+ a);
	var aArray=a.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	var b=document.getElementsByName("sysDate")[0].value; 
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
function validateHrs()
{
	if(document.getElementsByName("selStartExecutionTimeHrs")[0].value>24)
	{
		alert("Hours can not be greater than 23");
		document.getElementsByName("selStartExecutionTimeHrs")[0].value="00";
		document.getElementsByName("selStartExecutionTimeHrs")[0].focus();
		return false;
	}
	return true;
}

function validateMin()
{
	if(document.getElementsByName("selStartExecutionTimeMin")[0].value>59)
	{
		alert("Minut can not be greater than 59");
		document.getElementsByName("selStartExecutionTimeMin")[0].value="00";
		document.getElementsByName("selStartExecutionTimeMin")[0].focus();
		return false;
	}
	return true;
}

function validate_rows()
{
	//alert("inside validate_rows");
	if(document.getElementsByName("selDrugID")[0].value=="-1")
		{
			alert("Please Select Drug");
			document.getElementsByName("selDrugID")[0].focus();
			return false;
		}
	if(document.getElementsByName("selDrugRouteId")[0].value=="-1")
	{
		alert("Please Select Drug Route");
		document.getElementsByName("selDrugRouteId")[0].focus();
		return false;
	}
	if(document.getElementsByName("selStartExecutionTimeHrs")[0].value=="" || document.getElementsByName("selStartExecutionTimeHrs")[0].value=="00")
	{
		alert("Please Enter Hours");
		document.getElementsByName("selStartExecutionTimeHrs")[0].focus();
		return false;
	}
	if(document.getElementsByName("selStartExecutionTimeHrs")[0].value>23)
	{
		//alert("Hours can not be greater than 24");
		document.getElementsByName("selStartExecutionTimeHrs")[0].focus();
		return false;
	}
	if(document.getElementsByName("selStartExecutionTimeMin")[0].value=="" || document.getElementsByName("selStartExecutionTimeMin")[0].value=="00")
	{
		alert("Please Enter Minute");
		document.getElementsByName("selStartExecutionTimeMin")[0].focus();
		return false;
	}
	if(document.getElementsByName("selStartExecutionTimeMin")[0].value>59)
	{
		//alert("Minut can not be greater than 59");
		document.getElementsByName("selStartExecutionTimeMin")[0].focus();
		return false;
	}
	if(document.getElementsByName("selBatchNo")[0].value=="")
	{
		alert("Please Enter Batch No.");
		document.getElementsByName("selBatchNo")[0].focus();
		return false;
	}
	if(document.getElementsByName("selExpriryDate")[0].value=="")
	{
		alert("Please Enter Expiry Date");
		document.getElementsByName("selExpriryDate")[0].focus();
		return false;
	}
	if(checkDate()>0)
	{
		alert("This drug is expired");
		document.getElementsByName("selExpriryDate")[0].focus();
		return false;
	}
	return true;
}

function validateRows()
{
	/*
	var len=document.getElementsByName("selEndExecutionTimeHrsArray").length;
	alert("len "+ len);
	
	var selDrugIdArrayLen=document.getElementsByName("selDrugIdArray").length;
	alert("selDrugIdArrayLen "+selDrugIdArrayLen);
	
	if(len==0 && selDrugIdArrayLen==0)
	{
		alert("Please Select Drug");
		document.getElementsByName("selDrugID")[0].focus();
		return false;
	}
	*/
	//alert("inside validate row");
	if(document.getElementsByName("selDrugID")[0].value!="-1" || document.getElementsByName("selDrugRouteId")[0].value!="-1" || document.getElementsByName("selStartExecutionTimeHrs")[0].value!="" || document.getElementsByName("selStartExecutionTimeMin")[0].value!="" || document.getElementsByName("selBatchNo")[0].value!="" || document.getElementsByName("selExpriryDate")[0].value!="")
	{
		if(document.getElementsByName("selDrugID")[0].value=="-1")
		{
			alert("Please Select Drug");
			document.getElementsByName("selDrugID")[0].focus();
			return false;
		}
	if(document.getElementsByName("selDrugRouteId")[0].value=="-1")
	{
		alert("Please Select Drug Route");
		document.getElementsByName("selDrugRouteId")[0].focus();
		return false;
	}
	if(document.getElementsByName("selStartExecutionTimeHrs")[0].value=="" || document.getElementsByName("selStartExecutionTimeHrs")[0].value=="00")
	{
		alert("Please Enter Hours");
		document.getElementsByName("selStartExecutionTimeHrs")[0].focus();
		return false;
	}
	if(document.getElementsByName("selStartExecutionTimeHrs")[0].value>23)
	{
		//alert("Hours can not be greater than 24");
		document.getElementsByName("selStartExecutionTimeHrs")[0].focus();
		return false;
	}
	if(document.getElementsByName("selStartExecutionTimeMin")[0].value=="" || document.getElementsByName("selStartExecutionTimeMin")[0].value=="00")
	{
		alert("Please Enter Minute");
		document.getElementsByName("selStartExecutionTimeMin")[0].focus();
		return false;
	}
	if(document.getElementsByName("selStartExecutionTimeMin")[0].value>59)
	{
		//alert("Minut can not be greater than 59");
		document.getElementsByName("selStartExecutionTimeMin")[0].focus();
		return false;
	}
	if(document.getElementsByName("selBatchNo")[0].value=="")
	{
		alert("Please Enter Batch No.");
		document.getElementsByName("selBatchNo")[0].focus();
		return false;
	}
	if(document.getElementsByName("selExpriryDate")[0].value=="")
	{
		alert("Please Enter Expiry Date");
		document.getElementsByName("selExpriryDate")[0].focus();
		return false;
	}
	if(checkDate()>0)
	{
		//alert("checkDate()---> "+checkDate());
		alert("This drug is expired");
		document.getElementsByName("selExpriryDate")[0].focus();
		return false;
	}
	
	//getting drug Name for selected drugId
	var selDrugId=document.getElementsByName("selDrugID")[0].value.split("#");
	var prevDrugNameLen=document.getElementsByName("prevDrugName").length;
	var drugName="";
	for(var i=0;i<prevDrugNameLen;i++)
	{
		var drugId=document.getElementsByName("prevDrugName")[i].value.split("#")[1];
		if(drugId==selDrugId[0])
		{
			drugName=document.getElementsByName("prevDrugName")[i].value.split("#")[0];
		}
	}
	var NoOfDays =checkDate();
	if(NoOfDays>0)
	{
		alert("You Can Not Give "+ drugName + "\n Because It Is Expired");
		document.getElementsByName("selExpriryDate")[0].focus();
		return false;
	}
	var len=document.getElementsByName("prevDrugId").length;
	var drugFrequency=0;	
	for(var i=0;i<len;i++)
	{
		var drugId=document.getElementsByName("prevDrugId")[i].value;
		drugId=drugId.split("#");
		if(drugId[0]==selDrugId[0])
		{
			drugFrequency=drugId[1];
		}
	}
	var drugIdLen=document.getElementsByName("selDrugIdArray").length;
	var count=0;
	for(var i=0;i<drugIdLen;i++)
	{
		var drugId=document.getElementsByName("selDrugIdArray")[i].value.split("#");
		if(drugId[0]==selDrugId[0])
		{
			count++;
		}
	}
	var prevSelDrugIdLen=document.getElementsByName("prevSelDrugIdArray").length;
	for(var i=0;i<prevSelDrugIdLen;i++)
	{
		var drugId=document.getElementsByName("prevSelDrugIdArray")[i].value.split("#")[0];
		//var drugName=document.getElementsByName("prevSelDrugIdArray")[i].value.split("#")[1];
		if(drugId==selDrugId[0])
		{
			count++;
		}
	}
		
	if(count>=drugFrequency)
	{
		alert("You can not give "+drugName+" more than "+drugFrequency+ " times ");
		return false;
	}
	
	var prevExecTimeArrayLen=document.getElementsByName("prevSelDrufExecTimeArray").length;
	for(var i=0;i<prevExecTimeArrayLen;i++)
	{
		var time=document.getElementsByName("prevSelDrufExecTimeArray")[i].value.split("#")[0];
		var drugId=document.getElementsByName("prevSelDrufExecTimeArray")[i].value.split("#")[1];
		hrsTime=time.substring(0,2);
		minTime=time.substring(3,5);
		var selDrugId=document.getElementsByName("selDrugID")[0].value.split("#")[0];
		if(selDrugId==drugId)
		{
			if(document.getElementsByName("selStartExecutionTimeHrs")[0].value==hrsTime && document.getElementsByName("selStartExecutionTimeMin")[0].value==minTime)
			{
				alert(drugName +" already given at "+hrsTime+":"+minTime);
				document.getElementsByName("selStartExecutionTimeHrs")[0].focus();
				return false;
			}
		}
	}
	}
	
	return true;
}

function showInstruction(event,index)
{	
		//alert("index "+index);
		var instruction=document.getElementsByName("prevSelInstructionArray")[index].value;
		//alert("instruction "+instruction);		
		var path='/HISClinical/inpatient/nurDrugAdministration.cnt?hmode=INSTRUCTION&popupInstruction='+instruction;
		child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	  	child.moveTo(250,250);
	 	child.focus(); 
	
		if(!child.opener)
	   child.opener = self;
	 
	 	return child
}

function getDrugGivenDetail(event)
{
	var sysDate=document.getElementsByName("sysDate")[0].value;
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var admissionDate=document.getElementsByName("admissionDate")[0].value;
	//alert("patCrNo "+patCrNo);
	var path='/HISClinical/inpatient/nurDrugAdministration.cnt?hmode=VIEW&treatFromDate='+sysDate+'&treatToDate='+sysDate+'&patCrNo='+patCrNo+'&admissionDate='+admissionDate+'&sysDate='+sysDate;
	//alert("path "+path);
	openDependentPopup(createFHashAjaxQuery(path),event,250,600,true);
}

function getShiftWiseSchdule(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var visitNo=document.getElementsByName("episodeVisitNo")[0].value;
	var path='/HISClinical/inpatient/nurDrugAdministration.cnt?hmode=SCHEDULESHIFTWISE&patCrNo='+patCrNo+'&episodeVisitNo='+visitNo;
	openPopup(createFHashAjaxQuery(path),event,250,600);
}

</script>
	<his:TitleTag name="Drug Administration">
	</his:TitleTag>
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	
		<logic:notEmpty name="<%=InpatientConfig.PAT_LAST_VISIT_DRUG_DETAIL_LST%>">
		<his:SubTitleTag name="Treatment Advice Detail">
			<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<a style="cursor:pointer" onclick="getShiftWiseSchdule(event)" >
	      							<bean:message key="shiftWiseSchdule"/>
	      						</a>
							</b>
						</font>		
					</div>
				</td>
			</tr>
		</table>
		</his:SubTitleTag>		
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="drugname" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="dosage" />
								</b>
							</font>
						</div>
					</td>
					<td width="7%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="frequency" />
								</b>
							</font>
						</div>
					</td>
					
   				<td width="5%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="schedule" />
								</b>
							</font>
						</div>
					</td>
					
					<td width="17%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="drugRoute" />
								</b>
							</font>
						</div>
					</td>
					<td width="5%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="emptyStomach" />
								</b>
							</font>
						</div>
					</td>
					<td width="17%" class="tdfonthead">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="instructions" />
								</b>
							</font>
						</div>
					</td>
				</tr>
			<logic:iterate id="drugDtlVO" indexId="idx" name="<%=InpatientConfig.PAT_LAST_VISIT_DRUG_DETAIL_LST%>" type="hisglobal.vo.PatDrugTreatmentDetailVO">
				<%String ind=idx.toString(); %>
				<tr id="trSpecimen">
				<logic:notEqual value="0" name="drugDtlVO" property="drugConsentStatus">
					<td width="25%" class="tdfont">
						<div align="left">
							<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="drugName"/>
								<html:hidden name="DrugAdministrationFB" property="prevDrugName" value="<%=drugDtlVO.getDrugName()+"#"+drugDtlVO.getDrugId() %>"/>
								<html:hidden name="DrugAdministrationFB" property="prevDrugId" value="<%=drugDtlVO.getDrugId() %>"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="left">
							<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="doseName"/>
								</b>
							</font>
						</div>
					</td>
					<td width="7%" class="tdfont">
						<div align="left">
							<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="frequencyName"/>
								</b>
							</font>	
						</div>
					</td>
<!--					<td width="13%" class="tdfont">-->
<!--						<div align="left">-->
<!--							<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">-->
<!--								<b>-->
<!--								<bean:write name="drugDtlVO" property="startDate"/>-->
<!--								</b>-->
<!--							</font>-->
<!--						</div>-->
<!--					</td>-->
<!--					<td width="12%" class="tdfont">-->
<!--						<div align="left">-->
<!--							<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">-->
<!--								<b>-->
<!--								<bean:write name="drugDtlVO" property="endDate"/>-->
<!--								</b>-->
<!--							</font>-->
<!--						</div>-->
<!--					</td>-->
					<td width="5%" class="tdfont">
						<div align="left">
							<img name="prevDrugSchedule" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Drug Schedule " onclick ="showPrevDrugSchedule(<%=ind %>,'PREVDRUGSHEDULE')" onkeypress="if(event.keyCode==13) showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this));">
						</div>
					</td>
					
					<td width="17%" class="tdfont">
						<div align="left">
							<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="drugRouteName"/>
								<html:hidden name="DrugAdministrationFB" property="prevDrugRouteId" value="<%=drugDtlVO.getDrugRouteId()+"#"+drugDtlVO.getDrugId() %>"/>
								<html:hidden name="DrugAdministrationFB" property="prevDrugRouteName" value="<%=drugDtlVO.getDrugRouteName() %>"/>
								</b>
							</font>
						</div>
					</td>
					<td width="5%" class="tdfont">
						<div align="left">
							<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="isEmptyStomach"/>
								</b>
							</font>
						</div>
					</td>
					<td width="17%" class="tdfont">
						<div align="left">
							<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<logic:notEmpty name="drugDtlVO" property="remarks">
								<img name="instruction" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Instruction " onclick ="showInstruction(event,<%=ind %>)" onkeypress="if(event.keyCode==13) showInstruction(event,<%=ind %>);">
								</logic:notEmpty>
								<html:hidden name="DrugAdministrationFB" property="prevSelInstructionArray" value="<%=drugDtlVO.getRemarks()%>"/>
								</b>
							</font>
						</div>
					</td>
				</logic:notEqual>
				<logic:equal value="0" name="drugDtlVO" property="drugConsentStatus">
					<td width="25%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="drugName"/>
								<html:hidden name="DrugAdministrationFB" property="prevDrugName" value="<%=drugDtlVO.getDrugName()+"#"+drugDtlVO.getDrugId() %>"/>
								<html:hidden name="DrugAdministrationFB" property="prevDrugId" value="<%=drugDtlVO.getDrugId() %>"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="doseName"/>
								</b>
							</font>
						</div>
					</td>
					<td width="7%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="frequencyName"/>
								</b>
							</font>	
						</div>
					</td>
<!--					<td width="13%" class="tdfont">-->
<!--						<div align="left">-->
<!--							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">-->
<!--								<b>-->
<!--								<bean:write name="drugDtlVO" property="startDate"/>-->
<!--								</b>-->
<!--							</font>-->
<!--						</div>-->
<!--					</td>-->
<!--					<td width="12%" class="tdfont">-->
<!--						<div align="left">-->
<!--							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">-->
<!--								<b>-->
<!--								<bean:write name="drugDtlVO" property="endDate"/>-->
<!--								</b>-->
<!--							</font>-->
<!--						</div>-->
<!--					</td>-->
					<td width="5%" class="tdfont">
						<div align="left">
							<img name="prevDrugSchedule" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Drug Schedule " onclick ="showPrevDrugSchedule(<%=ind %>,'PREVDRUGSHEDULE')" onkeypress="if(event.keyCode==13) showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this));">
						</div>
					</td>
					
					<td width="17%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="drugRouteName"/>
								<html:hidden name="DrugAdministrationFB" property="prevDrugRouteId" value="<%=drugDtlVO.getDrugRouteId()+"#"+drugDtlVO.getDrugId() %>"/>
								<html:hidden name="DrugAdministrationFB" property="prevDrugRouteName" value="<%=drugDtlVO.getDrugRouteName() %>"/>
								</b>
							</font>
						</div>
					</td>
					<td width="5%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="isEmptyStomach"/>
								</b>
							</font>
						</div>
					</td>
					<td width="17%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<logic:notEmpty name="drugDtlVO" property="remarks">
								<img name="instruction" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Instruction " onclick ="showInstruction(event,<%=ind %>)" onkeypress="if(event.keyCode==13) showInstruction(event,<%=ind %>);">
								</logic:notEmpty>
								<html:hidden name="DrugAdministrationFB" property="prevSelInstructionArray" value="<%=drugDtlVO.getRemarks()%>"/>
								</b>
							</font>
						</div>
					</td>
				</logic:equal>	
				</tr>
			</logic:iterate>
	</table>
	</his:ContentTag>	
	</logic:notEmpty>	
	
		<logic:notEmpty name="<%=InpatientConfig.PAT_LAST_VISIT_DRUG_DETAIL_LST%>">
		<his:SubTitleTag name="Drug Execution">
		<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<a style="cursor:pointer" onclick="getDrugGivenDetail(event)" >
	      							<bean:message key="viewDrugGiven"/>
	      						</a>
							</b>
						</font>		
					</div>
				</td>
			</tr>
		</table>
		</his:SubTitleTag>
		<his:ContentTag>
		<table width="100%" id="drugExecutionDetailId" cellspacing="1" cellpadding="0">        
			<tr>
				<td width="16%" class="tdfonthead" nowrap="nowrap" >
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="drug" />
							</b>
						</font>
					</div>
				</td>
				<td width="16%" class="tdfonthead" nowrap="nowrap" >
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="drugRoute" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead" nowrap="nowrap" >
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="startTime(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead" nowrap="nowrap" id="ivfluidFlagLableId" ">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="endTime(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="5%" class="tdfonthead" nowrap="nowrap" >
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="batchNo" />
							</b>
						</font>
					</div>
				</td>
				<td width="7%" class="tdfonthead" nowrap="nowrap" >
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="expiryDate" />
							</b>
						</font>
					</div>
				</td>
				<td width="16%" class="tdfonthead" nowrap="nowrap" >
					<div align="left">
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
				<td width="16%" class="tdfont" nowrap="nowrap"  >
					<div align="left" >
						<html:select name="DrugAdministrationFB" property="selDrugID" tabindex="1" onchange="sendDataForDrugRouteList(this)" > 
							<html:option value="-1">Select Value</html:option>
							<html:options collection="<%=InpatientConfig.LAST_DRUG_ADVICED_LST%>" property="value" labelProperty="label" />
						</html:select></div></td>
				<td width="16%" class="tdfont" nowrap="nowrap" >
					<div align="left" id="drugRouteTDId">
						<html:select name="DrugAdministrationFB" property="selDrugRouteId" tabindex="1" > 
							<html:option value="-1">Select Value</html:option>
						</html:select>
					</div>
				</td>		
				<td width="10%" class="tdfont" nowrap="nowrap" >
					<div align="left">
						<html:text name="DrugAdministrationFB" property="selStartExecutionTimeHrs" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateHrs()" />
						:
						<html:text name="DrugAdministrationFB" property="selStartExecutionTimeMin" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateMin()"  />
					</div>
				</td>
				<td width="10%" class="tdfont" nowrap="nowrap" id="ivfluidFlagId" >
					<div align="left">
						<html:text name="DrugAdministrationFB" property="selEndExecutionTimeHrs" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateHrs()" disabled="true"/>
						:
						<html:text name="DrugAdministrationFB" property="selEndExecutionTimeMin" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateMin()" disabled="true" />
					</div>
				</td>
				<td width="5%" class="tdfont" nowrap="nowrap" >
					<div align="left">
						<html:text name="DrugAdministrationFB" property="selBatchNo" value="" size="10" tabindex="1" disabled="false" tabindex="1" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
					</div>
				</td>		
				<td width="7%" class="tdfont" nowrap="nowrap" >
					<div align="left">
						<his:date name="selExpriryDate" dateFormate="%d-%b-%Y"  >
						</his:date>
					</div>
				</td> 
				<td width="16%" class="tdfont" nowrap="nowrap" >
					<div align="left">
						<html:text name="DrugAdministrationFB" property="selExecutionRemark" value="" size="15" tabindex="1" disabled="false" tabindex="1" maxlength="500" onkeypress="return validateAlphaNumOnly(this,event)" />
					</div>
				</td>
				<td  class="tdfont" width="5%">
					<div align="left">  
						<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' alt="Add Component" title="Add Component" onkeypress="if(event.keyCode==13)if(true) AddRowToTable(validate_rows(),'ADDDRUGEXEROW') ;" onclick="if(true)AddRowToTable(validate_rows(),'ADDDRUGEXEROW')">
					</div>
				</td> 
			</tr>
	 		<%if(session.getAttribute(InpatientConfig.PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_ARRAY) !=null){ %>
			<logic:iterate id="drugAdminDtlVO" indexId="i" name="<%=InpatientConfig.PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_ARRAY %>" type="hisglobal.vo.DrugAdminDtlVO">
				<tr>
					<td class="tdfont" width="16%" >
						<div align="left">
							<%=drugAdminDtlVO.getItemName() %>
							<html:hidden name="DrugAdministrationFB" property="selDrugIdArray" value="<%=drugAdminDtlVO.getItemId() %>"/>
							<html:hidden name="DrugAdministrationFB" property="selDrugNameArray" value="<%=drugAdminDtlVO.getItemName() %>"/>
						</div>
					</td>
					<td class="tdfont" width="16%" >
						<div align="left">
							<%=drugAdminDtlVO.getDrugRouteName() %>
						</div>
					</td>
					<td class="tdfont" width="10%" >
						<div align="left">
							<%=drugAdminDtlVO.getAdministrationTime()%>
							<html:hidden name="DrugAdministrationFB" property="selDrugExecTimeArray" value="<%=drugAdminDtlVO.getAdministrationTime()+"#"+drugAdminDtlVO.getItemId()%>"/>
							
						</div>
					</td>
					<td class="tdfont" width="10%" >
						<div align="left">
							<%=drugAdminDtlVO.getAdministrationEndTime()%>
						</div>
					</td>
					<td class="tdfont" width="10%" >
						<div align="left">
							<%=drugAdminDtlVO.getBatchNo() %>
						</div>
					</td>
					<td class="tdfont" width="7%" >
						<div align="left">
							<%=drugAdminDtlVO.getExpiryDate() %>
						</div>
					</td>
					<td class="tdfont" width="16%" >
						<div align="left">
							<%=drugAdminDtlVO.getRemarks() %>
							
						</div>
					</td>
					<td class="tdfont" width="5%" >
						<div align="left">
							<img name="minus" class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('DELETEDRUGEXEROW',findIndex(this)) ;" onclick=" deleteRow('DELETEDRUGEXEROW',findIndex(this))">
						</div>
					</td>
				</tr>		
			</logic:iterate>
			<%} %>	
						
			<%if(session.getAttribute(InpatientConfig.SELECTED_IVFLUIDS_LIST) !=null){ %>
			<logic:iterate id="drugAdminDtlVO" indexId="i" name="<%=InpatientConfig.SELECTED_IVFLUIDS_LIST %>" type="hisglobal.vo.DrugAdminDtlVO">
				<%String ind=i.toString(); %>
				<tr>
					<td class="tdfont" width="16%" >
						<div align="left">
							<%=drugAdminDtlVO.getItemName() %>
						</div>
					</td>
					<td class="tdfont" width="16%" >
						<div align="left">
							<%=drugAdminDtlVO.getDrugRouteName() %>
						</div>
					</td>
					<td class="tdfont" width="10%" >
						<div align="left">
							<%=drugAdminDtlVO.getAdministrationTime()%>
						</div>
					</td>
					<td class="tdfont" width="10%" >
						<div align="left">
							<html:text name="DrugAdministrationFB" property="selEndExecutionTimeHrsArray" value="" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateHrs()" />
							:
							<html:text name="DrugAdministrationFB" property="selEndExecutionTimeMinArray" value="" size="3" tabindex="1" maxlength="2" onkeypress="return validateNumeric(event)" onblur="return validateMin()" />
						</div>
					</td>
					<td class="tdfont" width="10%" >
						<div align="left">
							<%=drugAdminDtlVO.getBatchNo() %>
						</div>
					</td>
					<td class="tdfont" width="7%" >
						<div align="left">
							<%=drugAdminDtlVO.getExpiryDate() %>
						</div>
					</td>
					<td class="tdfont" width="16%" >
						<div align="left">
							<html:text name="DrugAdministrationFB" property="selExecutionRemarkArray" value="<%=drugAdminDtlVO.getRemarks() %>" size="15" tabindex="1" disabled="false" tabindex="1" maxlength="500" onkeypress="return validateAlphaNumOnly(this,event)"  />
						</div>
					</td>
					<td class="tdfont" width="5%" >
						<div align="left">
							<img name="minus" class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('DELETEIVFLUIDROW','<%=ind %>') ;" onclick=" deleteRow('DELETEIVFLUIDROW','<%=ind %>')">
						</div>
					</td>
				</tr>		
			</logic:iterate>
			<%} %>	
		</table>	
	</his:ContentTag>
	</logic:notEmpty>
	<his:ButtonToolBarTag>
			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "submitFormOnValidate(validateRows(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateRows(),'SAVE');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</his:ButtonToolBarTag>
<html:hidden name="DrugAdministrationFB" property="hmode"/>
<html:hidden name="DrugAdministrationFB" property="patCrNo"/>
<html:hidden name="DrugAdministrationFB" property="episodeVisitNo"/>
<html:hidden name="DrugAdministrationFB" property="index"/>
<html:hidden name="DrugAdministrationFB" property="sysDate"/>
<html:hidden name="DrugAdministrationFB" property="admissionDate"/>
<html:hidden name="DrugAdministrationFB" property="drugSource"/>
<html:hidden name="DrugAdministrationFB" property="drugSourceFromPat"/>

