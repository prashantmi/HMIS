<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="opd.OpdConfig"%>.
<%@page import="hisglobal.vo.*" %>
<%@page import="java.util.*" %>
<%@page import="hisglobal.utility.Entry" %>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

<%
	StringBuffer shiftname = new StringBuffer("[");
	for(int i=0; i<OpdConfig.TREATMENT_ADVICE_SHIFT_NAME_ARRAY.length; i++)
	{
		shiftname.append("\"");
		shiftname.append(OpdConfig.TREATMENT_ADVICE_SHIFT_NAME_ARRAY[i]);
		shiftname.append("\",");
	}
	
	int lastIndex=shiftname.toString().lastIndexOf(',');
	shiftname=shiftname.deleteCharAt(lastIndex);
	shiftname.append("]");
%>

<%
	StringBuffer shiftStartHrsTime = new StringBuffer("[");
	for(int i=0; i<OpdConfig.TREATMENT_ADVICE_SHIFT_START_HOURS_TIME.length; i++)
	{
		shiftStartHrsTime.append("\"");
		shiftStartHrsTime.append(OpdConfig.TREATMENT_ADVICE_SHIFT_START_HOURS_TIME[i]);
		shiftStartHrsTime.append("\",");
	}
	
	int lastIndex1=shiftStartHrsTime.toString().lastIndexOf(',');
	shiftStartHrsTime=shiftStartHrsTime.deleteCharAt(lastIndex1);
	shiftStartHrsTime.append("]");
%>

<%
	StringBuffer shiftEndHrsTime = new StringBuffer("[");
	for(int i=0; i<OpdConfig.TREATMENT_ADVICE_SHIFT_END_HOURS_TIME.length; i++)
	{
		shiftEndHrsTime.append("\"");
		shiftEndHrsTime.append(OpdConfig.TREATMENT_ADVICE_SHIFT_END_HOURS_TIME[i]);
		shiftEndHrsTime.append("\",");
	}
	
	int lastIndex2=shiftEndHrsTime.toString().lastIndexOf(',');
	shiftEndHrsTime=shiftEndHrsTime.deleteCharAt(lastIndex2);
	shiftEndHrsTime.append("]");
%>

//var shiftStartTime = ["06:00", "12:00", "17:00", "20:00"];
//var shiftEndTime = ["11:59", "16:59", "19:59", "06:00"];

//var shiftStartHrsTime = ["06", "12", "17", "20"];
//var shiftStartMinTime = ["00", "00", "00", "00"];

//var shiftEndHrsTime = ["11", "16", "19", "06"];
//var shiftEndMinTime = ["59", "59", "59", "00"];

function onCLickShiftBut(but)
{
	
	//alert("shiftName "+shiftName[0]);
	var a=but.id;
	//alert("current button Id "+ a);
	var b=document.getElementById(a).value;
	var count=0;
	var j = parseInt(but.id);
		nextId = (j+1)%4;
	//alert("next id 1 ---> "+ nextId);
		
	var nextName=<%=shiftname%>[nextId];
	
	var length=document.getElementsByName('drugFreqShift').length;
	//alert("length "+length);
	
	for(var i=0;i<length;i++)
	{
		//alert(document.getElementsByName('drugFreqShift')[i].value);
		if(nextId==document.getElementsByName('drugFreqShift')[i].value )
		{
			//alert("inside if");
			nextId = (nextId+1)%4;
			for(var i=0;i<length;i++)
			{
				if(nextId==document.getElementsByName('drugFreqShift')[i].value)
				{
					//alert("recursip if");
					nextId = (nextId+1)%4;
				}
			}
			
		}
		
		
	}
	
	
	//alert("final id "+nextId);
		
		but.id = nextId;
		//alert("but.id "+but.id);
		document.getElementsByName('drugFreqShift')[findIndex(but)].value = nextId;
		
		//var hrsTime=document.getElementsByName('drugRequirmentTimeHrs')[findIndex(but)].value;
		//var minTime=document.getElementsByName('drugRequirmentTimeMin')[findIndex(but)].value
		//var popFreqId=document.getElementsByName('popupFreqId')[0].value;
		
		//alert("but.id "+but.id);
		document.getElementsByName('drugRequirmentTimeHrs')[findIndex(but)].value="00";
		document.getElementsByName('drugRequirmentTimeMin')[findIndex(but)].value="00";
		
		but.value = <%=shiftname%>[nextId];	
	
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

function validateMinTime(Min)
{
	if(document.getElementsByName('drugRequirmentTimeMin')[findIndex(Min)].value<0 || document.getElementsByName('drugRequirmentTimeMin')[findIndex(Min)].value>59)
	{
		alert("Minute can be in 0-59");
		document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Min)].focus();
		return false;
	}
	 
}

function validateHrsTime(Hrs)
{
	
	if(document.getElementsByName('butShift')[findIndex(Hrs)].value==<%=shiftname%>[0])
	{
		if(document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].value<<%=shiftStartHrsTime%>[0] || document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].value><%=shiftEndHrsTime%>[0])
		{
			alert("Morrning Shift can be between 06:00-11:00");
			document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].focus();
			return false;
		}
	}
	
	if(document.getElementsByName('butShift')[findIndex(Hrs)].value==<%=shiftname%>[1])
	{
		if(document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].value<<%=shiftStartHrsTime%>[1] || document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].value><%=shiftEndHrsTime%>[1])
		{
			if(document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].value==<%=shiftEndHrsTime%>[1])
			{
				if(document.getElementsByName('drugRequirmentTimeMin')[findIndex(Hrs)].value>0)
				{
					alert("Noon Shift can be between 12:00-16:00");
					document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].focus();
					return false;
				}
			}
			
		}
	}
	
	if(document.getElementsByName('butShift')[findIndex(Hrs)].value==<%=shiftname%>[2])
	{
		if(document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].value<<%=shiftStartHrsTime%>[2] || document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].value><%=shiftEndHrsTime%>[2])
		{
			alert("Evening Shift can be between 17:00-19:00");
			document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].focus();
			return false;
		}
	}
	if(document.getElementsByName('butShift')[findIndex(Hrs)].value==<%=shiftname%>[3])
	{
		if((document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].value>=<%=shiftStartHrsTime%>[3] && document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].value<"24") || (document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].value>=1 && document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].value<<%=shiftEndHrsTime%>[3]))
		{
			
		}
		else
		{
			alert("Night Shift can be between 20:00-06:00");
			document.getElementsByName('drugRequirmentTimeHrs')[findIndex(Hrs)].focus();
			return false;
		}
	 }
	
}

function validateHrsTimeSave()
{
	
	var freqCount=document.getElementsByName('popupFreqCount')[0].value;
	for(var i=0;i<freqCount;i++)
	{
		if(document.getElementsByName('butShift')[i].value==<%=shiftname%>[0])
		{
			if(document.getElementsByName('drugRequirmentTimeHrs')[i].value<<%=shiftStartHrsTime%>[0] || document.getElementsByName('drugRequirmentTimeHrs')[i].value><%=shiftEndHrsTime%>[0])
			{
				alert("Morrning Shift can be between 06:00-11:00");
				document.getElementsByName('drugRequirmentTimeHrs')[i].focus();
				return false;
			}
			else
			{
				
				if(document.getElementsByName('drugRequirmentTimeHrs')[i].value==<%=shiftEndHrsTime%>[0])
				{
					if(document.getElementsByName('drugRequirmentTimeMin')[i].value>0)
					{
						alert("Morrning Shift can be between 06:00-11:00");
						document.getElementsByName('drugRequirmentTimeHrs')[i].focus();
						return false;
					}
				}
			}
		}
	
	if(document.getElementsByName('butShift')[i].value==<%=shiftname%>[1])
	{
		if(document.getElementsByName('drugRequirmentTimeHrs')[i].value<<%=shiftStartHrsTime%>[1] || document.getElementsByName('drugRequirmentTimeHrs')[i].value><%=shiftEndHrsTime%>[1])
		{
			alert("Noon Shift can be between 12:00-16:00");
			document.getElementsByName('drugRequirmentTimeHrs')[i].focus();
			return false;
		}
		else
		{
			if(document.getElementsByName('drugRequirmentTimeHrs')[i].value==<%=shiftEndHrsTime%>[1])
			{
				if(document.getElementsByName('drugRequirmentTimeMin')[i].value>0)
				{
					alert("Noon Shift can be between 12:00-16:00");
					document.getElementsByName('drugRequirmentTimeHrs')[i].focus();
					return false;
				}
			}
		}
	}
	
	if(document.getElementsByName('butShift')[i].value==<%=shiftname%>[2])
	{
		if(document.getElementsByName('drugRequirmentTimeHrs')[i].value<<%=shiftStartHrsTime%>[2] || document.getElementsByName('drugRequirmentTimeHrs')[i].value><%=shiftEndHrsTime%>[2])
		{
			alert("Evening Shift can be between 17:00-19:00");
			document.getElementsByName('drugRequirmentTimeHrs')[i].focus();
			return false;
		}
		else
		{
			if(document.getElementsByName('drugRequirmentTimeHrs')[i].value==<%=shiftEndHrsTime%>[2])
			{
				if(document.getElementsByName('drugRequirmentTimeMin')[i].value>0)
				{
					alert("Evening Shift can be between 17:00-19:00");
					document.getElementsByName('drugRequirmentTimeHrs')[i].focus();
					return false;
				}
			}
		}
	}
	if(document.getElementsByName('butShift')[i].value==<%=shiftname%>[3])
	{
		if((document.getElementsByName('drugRequirmentTimeHrs')[i].value>=<%=shiftStartHrsTime%>[3] && document.getElementsByName('drugRequirmentTimeHrs')[i].value<"24") || (document.getElementsByName('drugRequirmentTimeHrs')[i].value>=1 && document.getElementsByName('drugRequirmentTimeHrs')[i].value<<%=shiftEndHrsTime%>[3]))
		{
			
		}
		else
		{
			alert("Night Shift can be between 20:00-06:00");
			document.getElementsByName('drugRequirmentTimeHrs')[i].focus();
			return false;
		}
	 }
	 if(document.getElementsByName('drugRequirmentTimeMin')[i].value<0 || document.getElementsByName('drugRequirmentTimeMin')[i].value>59)
	 {
		alert("Minute can be in 0-59");
		document.getElementsByName('drugRequirmentTimeMin')[i].focus();
		return false;
	 }
	}
	
	return true;
	
	
}

function submitPage(validateTime,mode)
{
	if(validateTime)
	{
		var len=document.getElementsByName("popupIsEmptyStomachArray").length;
		var checkIndex="";
		for(i=0;i<len;i++)
		{
			if(document.getElementsByName("popupIsEmptyStomachArray")[i].checked)
			{	
				checkIndex=checkIndex+"1"+"#";// 1 for empty stomach
			}
			else
			{
				checkIndex=checkIndex+"0"+"#";// 0 for not empty stomach
			}
		}
		document.getElementsByName("popupCheckIndex")[0].value=checkIndex;
		
		elmt=document.getElementsByName("hmode")[0];  
		elmt.value=mode;
	    document.forms[0].submit();
	    self.close();
	}
	
}
function closeForm()
{
	self.close();
}


function show()
{
	alert(opener.document.getElementsByName("selFrequencyId")[0].value);
}


/*

window.onload = function()
{
	
	var FreqImageInd = parseInt(opener.document.getElementsByName("frequencyImageIndex")[0].value);
		alert("FreqImageInd "+FreqImageInd);
	alert("sssssssssss"+opener.document.getElementsByName("selFrequencyId")[FreqImageInd].value);	
					
					//alert("FreqImageInd-------"+FreqImageInd);
				//	var freqId=opener.document.getElementsByName("selFrequencyId")[FreqImageInd].value;
				//	alert("freqId  "+);
		//document.getElementsByName("popupFreqId")[0].value=opener.document.getElementsByName("selFrequencyId")[FreqImageInd].value;			
					
}
*/
</script>

<html:form action="/opdPatientProfile">
	<body >
		<his:TitleTag name="DRUG SHEDULE">
		</his:TitleTag>
	<bean:define id="popupFreId" name="GenericPatientProfileFB" property="popupFreqId"></bean:define>	
	<bean:define id="popupRowIndex" name="GenericPatientProfileFB" property="popupRowIndex"></bean:define>	
	<bean:define id="popupItemTypeId" name="GenericPatientProfileFB" property="popupItemTypeId"></bean:define>	
	<bean:define id="popupIsEmpStomach" name="GenericPatientProfileFB" property="popupIsEmptyStomach"></bean:define>
	<bean:define id="popupDoseId" name="GenericPatientProfileFB" property="popupDoseId" type="java.lang.String"></bean:define>
	<bean:define id="popupDoseName" name="GenericPatientProfileFB" property="popupDoseName" type="java.lang.String"></bean:define>
	<bean:define id="popupDrugId" name="GenericPatientProfileFB" property="popupDrugId" type="java.lang.String"></bean:define>
	
	
	<%
		List lstMain = null;
		List entryObject=new ArrayList();
		lstMain = (List) session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
		if (lstMain!=null && popupItemTypeId!=null && !popupItemTypeId.equals(""))
		{
			for(int x=0;x<lstMain.size();x++)
			{	
				DrugDoseVO vo = (DrugDoseVO)lstMain.get(x);
				if(vo.getItemTypeId().equals(popupItemTypeId))
				{
					Entry obj=new Entry();
					obj.setValue(vo.getDoseId().split("\\^")[0]);
					obj.setLabel(vo.getDoseName());
					entryObject.add(obj);
				}
			}
		}
		session.setAttribute(OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP,entryObject);
	
	%>
	
	
	<%
		List drugSheduleLst=new ArrayList();
		DrugSheduleDtlVO drugSheduleDtlVO=null;
		Map drugSchedule=(Map)session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
			if(drugSchedule!=null && drugSchedule.isEmpty()==false )
			{
				// drugSheduleLst=(List)drugSchedule.get(popupRowIndex);
				 drugSheduleLst=(List)drugSchedule.get(popupDrugId+"#"+popupFreId+"#"+popupDoseId);
				if(drugSheduleLst!=null )
				{
					
					 
		
	 %>
							 
				<table width="100%" cellpadding="0" cellspacing="1" >
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="shift"/>
							</font>	
						</div>
					</td>
					<% 
						for(int k=0;k<drugSheduleLst.size();k++)
						 {
							 drugSheduleDtlVO=(DrugSheduleDtlVO)drugSheduleLst.get(k);
					%>
					<td class="tdfont" width="25%">
						<html:hidden name="GenericPatientProfileFB" property="drugFreqShift" value="<%=drugSheduleDtlVO.getDoseShift() %>"/>
						<% String shift="";
							if(drugSheduleDtlVO.getDoseShift().equals(OpdConfig.MORNING_SHIFT_ID))
									shift="Morning";
							if(drugSheduleDtlVO.getDoseShift().equals(OpdConfig.NOON_SHIFT_ID))
								shift="Noon";
							if(drugSheduleDtlVO.getDoseShift().equals(OpdConfig.EVENING_SHIFT_ID))
								shift="Evening";
							if(drugSheduleDtlVO.getDoseShift().equals(OpdConfig.NIGHT_SHIFT_ID))
								shift="Night";
							
							%>
						<input name="butShift" type="button" id="<%=drugSheduleDtlVO.getDoseShift() %>" value="<%=shift %>" onclick="onCLickShiftBut(this)" />
					</td>
					<% } %>
					</tr>
					<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="time(HrsMin)"/>
							</font>	
						</div>
					</td>
					<% 
						for(int k=0;k<drugSheduleLst.size();k++)
						 {
							 drugSheduleDtlVO=(DrugSheduleDtlVO)drugSheduleLst.get(k);
					%>
						<td class="tdfont" width="25%">
						<div align="left">
						<%String[] time= drugSheduleDtlVO.getDoseTime().split(":");%>
							<html:text name="GenericPatientProfileFB"  property="drugRequirmentTimeHrs" value="<%=time[0] %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
							:
							<html:text name="GenericPatientProfileFB" property="drugRequirmentTimeMin"  value="<%=time[1]%>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
						</div>
						
					</td>
					<% } %>
					</tr>
<!--					This tr not in use for display purpose (but value of empty stomach going in table)-->
					<tr style="display: none;">
						<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="emptyStomach"/>
							</font>	
						</div>
					</td>
					<% 
						for(int k=0;k<drugSheduleLst.size();k++)
						 {
							 drugSheduleDtlVO=(DrugSheduleDtlVO)drugSheduleLst.get(k);
					%>
						<%-- <td class="tdfont" width="25%" id="firstEmptyStomach">
						<div align="left">
							<% if(drugSheduleDtlVO.getIsEmptyStomach().equals("1"))	{ %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   checked="checked">
							<% }else { %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"  >
							<%} %>
						</div>
						</td>--%>
					<% } %>
					</tr>
					<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dosage"/>
							</font>	
						</div>
					</td>
					<% 
						for(int k=0;k<drugSheduleLst.size();k++)
						 {
							 drugSheduleDtlVO=(DrugSheduleDtlVO)drugSheduleLst.get(k);
					%>
					<td class="tdfonthead" width="25%" id="firstDosageId">
					<% if(drugSheduleDtlVO.getDoseId().equals("0")) { %>
						<div align="left">
						<html:text property="popupDoseNameArray" name="GenericPatientProfileFB" size="6" value="<%=drugSheduleDtlVO.getDoseName() %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="GenericPatientProfileFB" property="popupDoseIdArray" tabindex="1" value="<%=drugSheduleDtlVO.getDoseId()%>" >
						<html:option value="-1">Select</html:option >
						<html:options collection="<%=OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP%>" property="value" labelProperty="label"/>
						</html:select>	
						</div>
					<% } %>
					</td>
					<% } %>
					</tr>
					</table>	 
				<html:hidden name="GenericPatientProfileFB" property="popupFreqCount" value="<%=drugSheduleDtlVO.getFrequency() %>"/>			 
							 
					<%						 
						 
					 }
				else
				{
					%>
					<%
	
	String freqCount="0";	
	DrugFrequencyMstVO[] arr = (DrugFrequencyMstVO[]) session.getAttribute(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY);
	DrugFrequencyMstVO drugFreqVO = null;
	for(int j=0;j<arr.length;j++)
		if(arr[j].getFrequencyId().equals(popupFreId))
		{
			drugFreqVO=arr[j];
		}
	if(drugFreqVO!=null)
	{
		freqCount=drugFreqVO.getFrequency();
		
%>


		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1" >
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="shift"/>
							</font>	
						</div>
					</td>
					
					
					<%	if(drugFreqVO.getMorDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="GenericPatientProfileFB" property="drugFreqShift" value="0"/>
						<input name="butShift" type="button" id="0" value="Morning" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getAftDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="GenericPatientProfileFB" property="drugFreqShift" value="1"/>
						<input name="butShift" type="button" id="1" value="Noon" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getEveDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="GenericPatientProfileFB" property="drugFreqShift" value="2"/>
						<input name="butShift" type="button" id="2" value="Evening" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getNgtDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="GenericPatientProfileFB" property="drugFreqShift" value="3"/>
						<input name="butShift" type="button" id="3" value="Night" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					
				</tr>
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="time(HrsMin)"/>
							</font>	
						</div>
					</td>
					
					
					<%	if(drugFreqVO.getMorDoseTime()!=null)	{	
						String morDoseTime=drugFreqVO.getMorDoseTime();
						String[] totalTime= morDoseTime.split(":");
						String morHrs=totalTime[0];
						String morMin=totalTime[1];
					
					%>
					
					<td class="tdfont" width="25%">
						<div align="left">
							<html:text name="GenericPatientProfileFB"  property="drugRequirmentTimeHrs" value="<%=morHrs %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
							:
							<html:text name="GenericPatientProfileFB" property="drugRequirmentTimeMin"  value="<%=morMin %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
						</div>
						
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getAftDoseTime()!=null)	{	
						String morDoseTime=drugFreqVO.getAftDoseTime();
						String[] totalTime= morDoseTime.split(":");
						String aftHrs=totalTime[0];
						String aftMin=totalTime[1];
					
					%>
					<td class="tdfont" width="25%">
						<div align="left">
							<html:text name="GenericPatientProfileFB"  property="drugRequirmentTimeHrs" value="<%=aftHrs %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
							:
							<html:text name="GenericPatientProfileFB" property="drugRequirmentTimeMin" value="<%=aftMin %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
						</div>
						
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getEveDoseTime()!=null)	{	
						String morDoseTime=drugFreqVO.getEveDoseTime();
						String[] totalTime= morDoseTime.split(":");
						String eveHrs=totalTime[0];
						String eveMin=totalTime[1];
					
					%>
					<td class="tdfont" width="25%">
						<div align="left">
							<html:text name="GenericPatientProfileFB"  property="drugRequirmentTimeHrs" value="<%=eveHrs %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
							:
							<html:text name="GenericPatientProfileFB" property="drugRequirmentTimeMin" value="<%=eveMin %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
						</div>
						
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getNgtDoseTime()!=null)	{	
						String morDoseTime=drugFreqVO.getNgtDoseTime();
						String[] totalTime= morDoseTime.split(":");
						String ngtHrs=totalTime[0];
						String ngtMin=totalTime[1];
					
					%>
					<td class="tdfont" width="25%">
						<div align="left">
							<html:text name="GenericPatientProfileFB"  property="drugRequirmentTimeHrs" value="<%=ngtHrs %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
							:
							<html:text name="GenericPatientProfileFB" property="drugRequirmentTimeMin" value="<%=ngtMin %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
						</div>
						
					</td>
					<%	}	%>
<!--					This tr not in use for display purpose (but value of empty stomach going in table)-->					
					<tr style="display: none;">
					<td class="tdfonthead" width="25%" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="emptyStomach"/>
							</font>	
						</div>
					</td>
					<%	if(drugFreqVO.getMorDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%" id="firstEmptyStomach">
						<div align="left">
							<% if(popupIsEmpStomach.equals("on"))	{ %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   checked="checked" >
							<% }else { %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"  >
							<%} %>
						</div>
					</td>
					<% }  %>
					
					<%	if(drugFreqVO.getAftDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%" id="firstEmptyStomach">
						<div align="left">
							<% if(popupIsEmpStomach.equals("on"))	{ %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   checked="checked" >
							<% }else { %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   >
							<%} %>
						</div>
					</td>
					<% }  %>
					
					<%	if(drugFreqVO.getEveDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%" id="firstEmptyStomach">
						<div align="left">
							<% if(popupIsEmpStomach.equals("on"))	{ %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   checked="checked" >
							<% }else { %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   >
							<%} %>
						</div>
					</td>
					<% }  %>
					
					<%	if(drugFreqVO.getNgtDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%" id="firstEmptyStomach">
						<div align="left">
							<% if(popupIsEmpStomach.equals("on"))	{ %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   checked="checked" >
							<% }else { %>
							<input type="checkbox"  name="popupIsEmptyStomachArray" >
							<%} %>
						</div>
					</td>
					<% }  %>
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dosage"/>
							</font>	
						</div>
					</td>
				
				<%	if(drugFreqVO.getMorDoseTime()!=null)	{	%>	
					<td class="tdfonthead" width="25%" id="firstDosageId">
					<% if(popupDoseId.equals("0")) { %>
						<div align="left">
						<html:text property="popupDoseNameArray" name="GenericPatientProfileFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>
						<div align="left">
						<html:select name="GenericPatientProfileFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>"  >
						<html:option value="-1">Select</html:option >
						<html:options collection="<%=OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP%>" property="value" labelProperty="label"/>
						</html:select>	
						</div>
					<%} %>
					</td>
				<% }  %>	
				
				<%	if(drugFreqVO.getAftDoseTime()!=null)	{	%>	
					<td class="tdfonthead" width="25%" id="firstDosageId">
					<% if(popupDoseId.equals("0")) { %>
						<div align="left">
						<html:text property="popupDoseNameArray" name="GenericPatientProfileFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="GenericPatientProfileFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>">
						<html:option value="-1">Select</html:option >
						<html:options collection="<%=OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP%>" property="value" labelProperty="label"/>
						</html:select>	
						</div>
					<%} %>
					</td>
				<% }  %>	
				
				<%	if(drugFreqVO.getEveDoseTime()!=null)	{	%>	
					<td class="tdfonthead" width="25%" id="firstDosageId">
					<% if(popupDoseId.equals("0")) { %>
						<div align="left">
						<html:text property="popupDoseNameArray" name="GenericPatientProfileFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="GenericPatientProfileFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>" >
						<html:option value="-1">Select</html:option >
						<html:options collection="<%=OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP%>" property="value" labelProperty="label"/>
						</html:select>	
						</div>
					<%} %>
					</td>
				<% }  %>	
				
				<%	if(drugFreqVO.getNgtDoseTime()!=null)	{	%>	
					<td class="tdfonthead" width="25%" id="firstDosageId">
					<% if(popupDoseId.equals("0")) { %>
						<div align="left">
						<html:text property="popupDoseNameArray" name="GenericPatientProfileFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="GenericPatientProfileFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>">
						<html:option value="-1">Select</html:option >
						<html:options collection="<%=OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP%>" property="value" labelProperty="label"/>
						</html:select>	
						</div>
					<%} %>
					</td>
				<% }  %>		
					
					
				</tr>
			</table>
		<html:hidden name="GenericPatientProfileFB" property="popupFreqCount" value="<%=freqCount %>"/>	
			
		</his:ContentTag>	
<%
	}
%>
					
					<%
				}
			}
					else
					{
					%>
					
					
					
					
				
					

<%
	
	String freqCount="0";	
	DrugFrequencyMstVO[] arr = (DrugFrequencyMstVO[]) session.getAttribute(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY);
	DrugFrequencyMstVO drugFreqVO = null;
	for(int j=0;j<arr.length;j++)
		if(arr[j].getFrequencyId().equals(popupFreId))
		{
			drugFreqVO=arr[j];
		}
	if(drugFreqVO!=null)
	{
		freqCount=drugFreqVO.getFrequency();
		
%>


		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1" >
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="shift"/>
							</font>	
						</div>
					</td>
					
					
					<%	if(drugFreqVO.getMorDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="GenericPatientProfileFB" property="drugFreqShift" value="0"/>
						<input name="butShift" type="button" id="0" value="Morning" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getAftDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="GenericPatientProfileFB" property="drugFreqShift" value="1"/>
						<input name="butShift" type="button" id="1" value="Noon" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getEveDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="GenericPatientProfileFB" property="drugFreqShift" value="2"/>
						<input name="butShift" type="button" id="2" value="Evening" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getNgtDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="GenericPatientProfileFB" property="drugFreqShift" value="3"/>
						<input name="butShift" type="button" id="3" value="Night" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					
				</tr>
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="time(HrsMin)"/>
							</font>	
						</div>
					</td>
					
					
					<%	if(drugFreqVO.getMorDoseTime()!=null)	{	
						String morDoseTime=drugFreqVO.getMorDoseTime();
						String[] totalTime= morDoseTime.split(":");
						String morHrs=totalTime[0];
						String morMin=totalTime[1];
					
					%>
					
					<td class="tdfont" width="25%">
						<div align="left">
							<html:text name="GenericPatientProfileFB"  property="drugRequirmentTimeHrs" value="<%=morHrs %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
							:
							<html:text name="GenericPatientProfileFB" property="drugRequirmentTimeMin"  value="<%=morMin %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
						</div>
						
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getAftDoseTime()!=null)	{	
						String morDoseTime=drugFreqVO.getAftDoseTime();
						String[] totalTime= morDoseTime.split(":");
						String aftHrs=totalTime[0];
						String aftMin=totalTime[1];
					
					%>
					<td class="tdfont" width="25%">
						<div align="left">
							<html:text name="GenericPatientProfileFB"  property="drugRequirmentTimeHrs" value="<%=aftHrs %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
							:
							<html:text name="GenericPatientProfileFB" property="drugRequirmentTimeMin" value="<%=aftMin %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
						</div>
						
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getEveDoseTime()!=null)	{	
						String morDoseTime=drugFreqVO.getEveDoseTime();
						String[] totalTime= morDoseTime.split(":");
						String eveHrs=totalTime[0];
						String eveMin=totalTime[1];
					
					%>
					<td class="tdfont" width="25%">
						<div align="left">
							<html:text name="GenericPatientProfileFB"  property="drugRequirmentTimeHrs" value="<%=eveHrs %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
							:
							<html:text name="GenericPatientProfileFB" property="drugRequirmentTimeMin" value="<%=eveMin %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
						</div>
						
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getNgtDoseTime()!=null)	{	
						String morDoseTime=drugFreqVO.getNgtDoseTime();
						String[] totalTime= morDoseTime.split(":");
						String ngtHrs=totalTime[0];
						String ngtMin=totalTime[1];
					
					%>
					<td class="tdfont" width="25%">
						<div align="left">
							<html:text name="GenericPatientProfileFB"  property="drugRequirmentTimeHrs" value="<%=ngtHrs %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
							:
							<html:text name="GenericPatientProfileFB" property="drugRequirmentTimeMin" value="<%=ngtMin %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
						</div>
						
					</td>
					<%	}	%>
<!--					This tr not in use for display purpose (but value of empty stomach going in table)-->					
					<tr style="display: none;">
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="emptyStomach"/>
							</font>	
						</div>
					</td>
					<%	if(drugFreqVO.getMorDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%" id="firstEmptyStomach">
						<div align="left">
							<% if(popupIsEmpStomach.equals("on"))	{ %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   checked="checked" >
							<% }else { %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"  >
							<%} %>
						</div>
					</td>
					<% }  %>
					
					<%	if(drugFreqVO.getAftDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%" id="firstEmptyStomach">
						<div align="left">
							<% if(popupIsEmpStomach.equals("on"))	{ %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   checked="checked" >
							<% }else { %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"  >
							<%} %>
						</div>
					</td>
					<% }  %>
					
					<%	if(drugFreqVO.getEveDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%" id="firstEmptyStomach">
						<div align="left">
							<% if(popupIsEmpStomach.equals("on"))	{ %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   checked="checked" >
							
							<% }else { %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"  >
							<%} %>
						</div>
					</td>
					<% }  %>
					
					<%	if(drugFreqVO.getNgtDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%" id="firstEmptyStomach">
						<div align="left">
							<% if(popupIsEmpStomach.equals("on"))	{ %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   checked="checked" >
							<% }else { %>
							<input type="checkbox"  name="popupIsEmptyStomachArray" >
							<%} %>
						</div>
					</td>
					<% }  %>
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dosage"/>
							</font>	
						</div>
					</td>
				
				<%	if(drugFreqVO.getMorDoseTime()!=null)	{	%>	
					<td class="tdfonthead" width="25%" id="firstDosageId">
					<% if(popupDoseId.equals("0")) { %>
						<div align="left">
						<html:text property="popupDoseNameArray" name="GenericPatientProfileFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>
						<div align="left">
						<html:select name="GenericPatientProfileFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>"  >
						<html:option value="-1">Select</html:option >
						<html:options collection="<%=OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP%>" property="value" labelProperty="label"/>
						</html:select>	
						</div>
					<%} %>
					</td>
				<% }  %>	
				
				<%	if(drugFreqVO.getAftDoseTime()!=null)	{	%>	
					<td class="tdfonthead" width="25%" id="firstDosageId">
					<% if(popupDoseId.equals("0")) { %>
						<div align="left">
						<html:text property="popupDoseNameArray" name="GenericPatientProfileFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="GenericPatientProfileFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>">
						<html:option value="-1">Select</html:option >
						<html:options collection="<%=OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP%>" property="value" labelProperty="label"/>
						</html:select>	
						</div>
					<%} %>
					</td>
				<% }  %>	
				
				<%	if(drugFreqVO.getEveDoseTime()!=null)	{	%>	
					<td class="tdfonthead" width="25%" id="firstDosageId">
					<% if(popupDoseId.equals("0")) { %>
						<div align="left">
						<html:text property="popupDoseNameArray" name="GenericPatientProfileFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="GenericPatientProfileFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>" >
						<html:option value="-1">Select</html:option >
						<html:options collection="<%=OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP%>" property="value" labelProperty="label"/>
						</html:select>	
						</div>
					<%} %>
					</td>
				<% }  %>	
				
				<%	if(drugFreqVO.getNgtDoseTime()!=null)	{	%>	
					<td class="tdfonthead" width="25%" id="firstDosageId">
					<% if(popupDoseId.equals("0")) { %>
						<div align="left">
						<html:text property="popupDoseNameArray" name="GenericPatientProfileFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="GenericPatientProfileFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>">
						<html:option value="-1">Select</html:option >
						<html:options collection="<%=OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP%>" property="value" labelProperty="label"/>
						</html:select>	
						</div>
					<%} %>
					</td>
				<% }  %>		
					
					
				</tr>
			</table>
		<html:hidden name="GenericPatientProfileFB" property="popupFreqCount" value="<%=freqCount %>"/>	
			
		</his:ContentTag>	
<%
	}
%>
	
	<%	
					
			}
			
	%>					
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage(validateHrsTimeSave(),'SAVEDRUGSHEDULE')" onkeypress="if(event.keyCode==13) submitPage('SAVEDRUGSHEDULE')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
        
        <html:hidden name="GenericPatientProfileFB" property="hmode"/>
        <html:hidden name="GenericPatientProfileFB" property="popupFreqId"/>
        <html:hidden name="GenericPatientProfileFB" property="popupItemTypeId"/>
        <html:hidden name="GenericPatientProfileFB" property="popupIsEmptyStomach"/>
        <html:hidden name="GenericPatientProfileFB" property="popupDoseId"/>
        <html:hidden name="GenericPatientProfileFB" property="popupRowIndex"/>
        <html:hidden name="GenericPatientProfileFB" property="popupDrugId"/>
        <html:hidden name="GenericPatientProfileFB" property="popupCheckIndex"/>
        <html:hidden name="GenericPatientProfileFB" property="popupDoseName"/>

	</body>	
</html:form>
<%}catch(Exception e){e.printStackTrace();}%>