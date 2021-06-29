<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.vo.*" %>
<%@page import="java.util.*" %>
<%@page import="hisglobal.utility.Entry" %>


<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<his:javascript src="/hisglobal/js/time_validator.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
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

function conv(time)
{
	
	var timeobj= convertStrToDate(time,"hh:mm");
	//alert("timeobj"+timeobj);
	return timeobj;
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
		/* 
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
	 } */
	 if(document.getElementsByName('drugRequirmentTimeHrs')[i].value<0 || document.getElementsByName('drugRequirmentTimeHrs')[i].value>23)
	 {
		alert("Hours can be in 0-23");
		document.getElementsByName('drugRequirmentTimeHrs')[i].focus();
		return false;
	 }
	 if(document.getElementsByName('drugRequirmentTimeMin')[i].value<0 || document.getElementsByName('drugRequirmentTimeMin')[i].value>59)
	 {
		alert("Minute can be in 0-59");
		document.getElementsByName('drugRequirmentTimeMin')[i].focus();
		return false;
	 }
	 if(i+1<freqCount)
	{
	 	if(document.getElementsByName('drugRequirmentTimeHrs')[i].value>document.getElementsByName('drugRequirmentTimeHrs')[i+1].value)
	 	{
		 	alert("Dose Timing Should be Less than the next Dose Time");
		 	document.getElementsByName('drugRequirmentTimeHrs')[i].focus();
		 	return false;
	 	}
	 	if(document.getElementsByName('drugRequirmentTimeHrs')[i].value==document.getElementsByName('drugRequirmentTimeHrs')[i+1].value)
	 	{
		 	if(document.getElementsByName('drugRequirmentTimeMin')[i].value>document.getElementsByName('drugRequirmentTimeMin')[i+1].value)
		 	{
			 	alert("Dose Timing Should be Less than the next Dose Time");
			 	document.getElementsByName('drugRequirmentTimeMin')[i].focus();
			 	return false;
		 	}
	 	}
	}
	 var cutbfr = conv(document.getElementsByName('cutOffTimeBefore')[i].value);
	 var cutaftr = conv(document.getElementsByName('cutOffTimeAfter')[i].value);
	 if(cutbfr<conv("01:00")||cutbfr>conv("12:00"))
	 {
		 alert("Cut Off Time can be between 1-12");
		 document.getElementsByName('cutOffTimeBefore')[i].focus();
		 return false;
	 }
	 if(cutaftr<conv("01:00")|| cutaftr>conv("12:00"))
	 {
		 alert("Cut Off Time can be between 1-12");
		 document.getElementsByName('cutOffTimeAfter')[i].focus();
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

<html:form action="/ehrSection_DESKTREATMENT">
	<body >
		<his:TitleTag name="DOSAGE SCHEDULE">
		</his:TitleTag>
	<bean:define id="popupFreId" name="EHRSection_TreatmentFB" property="popupFreqId"></bean:define>	
	<bean:define id="popupRowIndex" name="EHRSection_TreatmentFB" property="popupRowIndex"></bean:define>	
	<bean:define id="popupItemTypeId" name="EHRSection_TreatmentFB" property="popupItemTypeId"></bean:define>	
	<bean:define id="popupIsEmpStomach" name="EHRSection_TreatmentFB" property="popupIsEmptyStomach"></bean:define>
	<bean:define id="popupDoseId" name="EHRSection_TreatmentFB" property="popupDoseId" type="java.lang.String"></bean:define>
	<bean:define id="popupDoseName" name="EHRSection_TreatmentFB" property="popupDoseName" type="java.lang.String"></bean:define>
	<bean:define id="popupDrugId" name="EHRSection_TreatmentFB" property="popupDrugId" type="java.lang.String"></bean:define>
	
	
	
	<%
		List lstMain = null;
		List entryObject=new ArrayList();
		lstMain = (List) session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
		if (lstMain!=null && popupItemTypeId!=null && !popupItemTypeId.equals(""))
			for(int x=0; x<lstMain.size(); x++)
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
		session.setAttribute(OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP,entryObject);
	
	%>
	
	
	<%
		List drugSheduleLst=new ArrayList();
		
		DrugSheduleDtlVO drugSheduleDtlVO=null;
		String freqCount="0";
		Map drugSchedule=(Map)session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
			if(drugSchedule!=null && drugSchedule.isEmpty()==false )
			{
				// drugSheduleLst=(List)drugSchedule.get(popupRowIndex);
				 drugSheduleLst=(List)drugSchedule.get(popupDrugId+"#"+popupFreId+"#"+popupDoseId);
				if(drugSheduleLst!=null )
				{
					
					 
		
	 %>
							 
				<table width="100%" cellpadding="0" cellspacing="1" >
				
					
					<tr >
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dtime"/>
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
							<html:text name="EHRSection_TreatmentFB"  property="drugRequirmentTimeHrs" value="<%=time[0] %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
							:
							<html:text name="EHRSection_TreatmentFB" property="drugRequirmentTimeMin"  value="<%=time[1]%>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
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
						<td class="tdfont" width="25%" id="firstEmptyStomach">
						<div align="left">
							<% if(drugSheduleDtlVO.getIsEmptyStomach()!=null && drugSheduleDtlVO.getIsEmptyStomach().equals("1"))	{ %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   checked="checked">
							<% }else { %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"  >
							<%} %>
						</div>
					</td>
					<% } %>
					</tr>
					<tr style="display: none;">
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
						<html:text property="popupDoseNameArray" name="EHRSection_TreatmentFB" size="6" value="<%=drugSheduleDtlVO.getDoseName() %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="EHRSection_TreatmentFB" property="popupDoseIdArray" tabindex="1" value="<%=drugSheduleDtlVO.getDoseId()%>" >
						<html:option value="-1">Select</html:option >
						<html:options collection="<%=OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP%>" property="value" labelProperty="label"/>
						</html:select>	
						</div>
					<% } %>
					</td>
					<% } %>
					</tr>
					<tr style="display: none;">
				<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dtime"/>
							</font>	
						</div>
					</td>
					<% int i=0;
						String cutOffBefore="",cutOffAfter="";
						for(int k=0;k<drugSheduleLst.size();k++){
							drugSheduleDtlVO=(DrugSheduleDtlVO)drugSheduleLst.get(k);
							String hrs="",mins="";
							String[] time= drugSheduleDtlVO.getDoseTime().split(":");
							//String[] duration=drugSheduleDtlVO.getDoseDuration().split(":");
					%>
				<td class="tdfont" width="25%">
						<div align="left">
							<%
							//int timehr= Integer.parseInt(time[0])+(Integer.parseInt(duration[0])*i);
							//int timemins= Integer.parseInt(time[1])+(Integer.parseInt(duration[1])*i);
							//if(timehr<10)
							//	hrs = "0"+Integer.toString(timehr) ;
							//else
							//	hrs=Integer.toString(timehr) ;
							//if(timemins<10)
							//	mins = "0"+Integer.toString(timemins) ;
							//else
							//	mins=Integer.toString(timemins) ;
							//hrs=conv((hrs.concat(":")).concat(mins));
							%>
							
						<html:text name="EHRSection_TreatmentFB"  property="drugRequirmentTimeHrs" value="<%=time[0] %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
							:
							<html:text name="EHRSection_TreatmentFB" property="drugRequirmentTimeMin"  value="<%=time[1] %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
						
						</div>
					</td>
					<% i=i+1;
					};
					%>
				</tr>
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="cutoffbef"/>
							</font>	
						</div>
					</td>
					<% i=0;
				
					for(int k=0;k<drugSheduleLst.size();k++){
						drugSheduleDtlVO=(DrugSheduleDtlVO)drugSheduleLst.get(k);
						cutOffBefore=drugSheduleDtlVO.getCutOffTime();
					%>
					<td td class="tdfonthead" width="25%" id="cutoffBefore">
					<div align="left">
						<html:text property="cutOffTimeBefore" name="EHRSection_TreatmentFB" size="10" value="<%=cutOffBefore %>" onkeypress="return validateNumeric(event)"></html:text>
						</div>
					</td>
					<% i=i+1;
					};
					%>
				</tr>
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="cutofaft"/>
							</font>	
						</div>
					</td>
					<% i=0;
				
					for(int k=0;k<drugSheduleLst.size();k++){
						drugSheduleDtlVO=(DrugSheduleDtlVO)drugSheduleLst.get(k);
						cutOffAfter=drugSheduleDtlVO.getCutOffTimeafter();
					%>
					<td td class="tdfonthead" width="25%" id="cutoffAfter">
					<div align="left">
						<html:text property="cutOffTimeAfter" name="EHRSection_TreatmentFB" size="10" value="<%=cutOffAfter %>" onkeypress="return validateNumeric(event)"></html:text>
						</div>
					</td>
					<% i=i+1;
					};
					%>
				</tr>
					</table>	 
				<html:hidden name="EHRSection_TreatmentFB" property="popupFreqCount" value="<%=drugSheduleDtlVO.getFrequency() %>"/>			 
							 
					<%						 
						 
					 }
				else
				{
					%>
					<%
	
		
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
				<!-- Commented By Shruti to Remove the Shift -->
				<tr style="display: none;">
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="shift"/>
							</font>	
						</div>
					</td>
					
					
					<%	if(drugFreqVO.getMorDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="EHRSection_TreatmentFB" property="drugFreqShift" value="0"/>
						<input name="butShift" type="button" id="0" value="Morning" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getAftDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="EHRSection_TreatmentFB" property="drugFreqShift" value="1"/>
						<input name="butShift" type="button" id="1" value="Noon" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getEveDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="EHRSection_TreatmentFB" property="drugFreqShift" value="2"/>
						<input name="butShift" type="button" id="2" value="Evening" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getNgtDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="EHRSection_TreatmentFB" property="drugFreqShift" value="3"/>
						<input name="butShift" type="button" id="3" value="Night" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					
				</tr>
				
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
				<tr style="display: none;">
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
						<html:text property="popupDoseNameArray" name="EHRSection_TreatmentFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>
						<div align="left">
						<html:select name="EHRSection_TreatmentFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>"  >
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
						<html:text property="popupDoseNameArray" name="EHRSection_TreatmentFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="EHRSection_TreatmentFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>">
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
						<html:text property="popupDoseNameArray" name="EHRSection_TreatmentFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="EHRSection_TreatmentFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>" >
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
						<html:text property="popupDoseNameArray" name="EHRSection_TreatmentFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="EHRSection_TreatmentFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>">
						<html:option value="-1">Select</html:option >
						<html:options collection="<%=OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP%>" property="value" labelProperty="label"/>
						</html:select>	
						</div>
					<%} %>
					</td>
				<% }  %>		
					
					
				</tr>
				<tr>
				<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dtime"/>
							</font>	
						</div>
					</td>
					<% int i=0;
						String cutOffBefore=drugFreqVO.getCutOffTime();
						String cutOffAfter=drugFreqVO.getCutOffTime();
						String hrs,mins;
					do{
					%>
				<td class="tdfont" width="25%">
						<div align="left">
							<%String[] time= drugFreqVO.getFirstDoseTime().split(":");
							String[] duration=drugFreqVO.getDoseDuration().split(":");
							int timehr= Integer.parseInt(time[0])+(Integer.parseInt(duration[0])*i);
							int timemins= Integer.parseInt(time[1])+(Integer.parseInt(duration[1])*i);
							if(timehr<10)
								hrs = "0"+Integer.toString(timehr) ;
							else
								hrs=Integer.toString(timehr) ;
							if(timemins<10)
								mins = "0"+Integer.toString(timemins) ;
							else
								mins=Integer.toString(timemins) ;
							%>
							
						<html:text name="EHRSection_TreatmentFB"  property="drugRequirmentTimeHrs" value="<%=hrs %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
							:
							<html:text name="EHRSection_TreatmentFB" property="drugRequirmentTimeMin"  value="<%=mins %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
						
						</div>
					</td>
					<% i=i+1;
					}while(i< Integer.parseInt(freqCount));
					%>
				</tr>
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="cutoffbef"/>
							</font>	
						</div>
					</td>
					<% i=0;
				
					do{
					%>
					<td td class="tdfonthead" width="25%" id="cutoffBefore">
					<div align="left">
						<html:text property="cutOffTimeBefore" name="EHRSection_TreatmentFB" size="10" value="<%=cutOffBefore %>" onkeypress="return validateNumeric(event)" ></html:text>
						</div>
					</td>
					<% i=i+1;
					}while(i< Integer.parseInt(freqCount));
					%>
				</tr>
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="cutofaft"/>
							</font>	
						</div>
					</td>
					<% i=0;
				
					do{
					%>
					<td td class="tdfonthead" width="25%" id="cutoffAfter">
					<div align="left">
						<html:text property="cutOffTimeAfter" name="EHRSection_TreatmentFB" size="10" value="<%=cutOffAfter %>" onkeypress="return validateNumeric(event)" > </html:text>
						</div>
					</td>
					<% i=i+1;
					}while(i< Integer.parseInt(freqCount));
					%>
				</tr>
			</table>
		<html:hidden name="EHRSection_TreatmentFB" property="popupFreqCount" value="<%=freqCount %>"/>	
			
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
	
	freqCount="0";	
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
		
				<!-- Commented By Shruti to Remove the Shift -->
				<tr style="display: none;">
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="shift"/>
							</font>	
						</div>
					</td>
					
					
					<%	if(drugFreqVO.getMorDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="EHRSection_TreatmentFB" property="drugFreqShift" value="0"/>
						<input name="butShift" type="button" id="0" value="Morning" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getAftDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="EHRSection_TreatmentFB" property="drugFreqShift" value="1"/>
						<input name="butShift" type="button" id="1" value="Noon" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getEveDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="EHRSection_TreatmentFB" property="drugFreqShift" value="2"/>
						<input name="butShift" type="button" id="2" value="Evening" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					<%	if(drugFreqVO.getNgtDoseTime()!=null)	{	%>
					<td class="tdfont" width="25%">
						<html:hidden name="EHRSection_TreatmentFB" property="drugFreqShift" value="3"/>
						<input name="butShift" type="button" id="3" value="Night" onclick="onCLickShiftBut(this)" />
					</td>
					<%	}	%>
					
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
					
					
				
					
					
				<tr style="display: none;">
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
						<html:text property="popupDoseNameArray" name="EHRSection_TreatmentFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>
						<div align="left">
						<html:select name="EHRSection_TreatmentFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>"  >
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
						<html:text property="popupDoseNameArray" name="EHRSection_TreatmentFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="EHRSection_TreatmentFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>">
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
						<html:text property="popupDoseNameArray" name="EHRSection_TreatmentFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="EHRSection_TreatmentFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>" >
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
						<html:text property="popupDoseNameArray" name="EHRSection_TreatmentFB" size="10" value="<%=popupDoseName %>"></html:text>
						</div>
						
					<%}else{ %>	
						<div align="left">
						<html:select name="EHRSection_TreatmentFB" property="popupDoseIdArray" tabindex="1" value="<%=popupDoseId%>">
						<html:option value="-1">Select</html:option >
						<html:options collection="<%=OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP%>" property="value" labelProperty="label"/>
						</html:select>	
						</div>
					<%} %>
					</td>
				<% }  %>		
					
				
				</tr>
					<tr>
				<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="dtime"/>
							</font>	
						</div>
					</td>
					<% int i=0;
					String cutOffBefore=drugFreqVO.getCutOffTime();
					String cutOffAfter=drugFreqVO.getCutOffTime();
					String hrs,mins;
					do{
					%>
				<td class="tdfont" width="25%">
						<div align="left">
							<% String[] time= drugFreqVO.getFirstDoseTime().split(":");
							String[] duration=drugFreqVO.getDoseDuration().split(":");
							int timehr= Integer.parseInt(time[0])+(Integer.parseInt(duration[0])*i);
							int timemins= Integer.parseInt(time[1])+(Integer.parseInt(duration[1])*i);
							if(timehr<10)
								hrs = "0"+Integer.toString(timehr) ;
							else
								hrs=Integer.toString(timehr) ;
							if(timemins<10)
								mins = "0"+Integer.toString(timemins) ;
							else
								mins=Integer.toString(timemins) ;
							//hrs=conv((hrs.concat(":")).concat(mins));
							%>
							<html:text name="EHRSection_TreatmentFB"  property="drugRequirmentTimeHrs" value="<%=hrs%>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
							:
							<html:text name="EHRSection_TreatmentFB" property="drugRequirmentTimeMin"  value="<%=mins %>" size="3" tabindex="1" maxlength="2"  onkeypress="return validateNumeric(event)"  disabled="false"/>
						
						</div>
					</td>
					<% i=i+1;
					}while(i< Integer.parseInt(freqCount));
					%>
				</tr>
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="cutoffbef"/>
							</font>	
						</div>
					</td>
					<% i=0;
				
					do{
					%>
					<td td class="tdfonthead" width="25%" id="cutoffBefore">
					<div align="left">
						<html:text property="cutOffTimeBefore" name="EHRSection_TreatmentFB" size="10" value="<%=cutOffBefore %>" onkeypress="return validateNumeric(event)" ></html:text>
						</div>
					</td>
					<% i=i+1;
					}while(i< Integer.parseInt(freqCount));
					%>
				</tr>
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="cutofaft"/>
							</font>	
						</div>
					</td>
					<% i=0;
				
					do{
					%>
					<td td class="tdfonthead" width="25%" id="cutoffAfter">
					<div align="left">
						<html:text property="cutOffTimeAfter" name="EHRSection_TreatmentFB" size="10" value="<%=cutOffAfter %>" onkeypress="return validateNumeric(event)" ></html:text>
						</div>
					</td>
					<% i=i+1;
					}while(i< Integer.parseInt(freqCount));
					%>
				</tr>
			</table>
		<html:hidden name="EHRSection_TreatmentFB" property="popupFreqCount" value="<%=freqCount %>"/>	
			
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
        
        <html:hidden name="EHRSection_TreatmentFB" property="hmode"/>
        <html:hidden name="EHRSection_TreatmentFB" property="popupFreqId"/>
        <html:hidden name="EHRSection_TreatmentFB" property="popupItemTypeId"/>
        <html:hidden name="EHRSection_TreatmentFB" property="popupIsEmptyStomach"/>
        <html:hidden name="EHRSection_TreatmentFB" property="popupDoseId"/>
        <html:hidden name="EHRSection_TreatmentFB" property="popupRowIndex"/>
        <html:hidden name="EHRSection_TreatmentFB" property="popupDrugId"/>
        <html:hidden name="EHRSection_TreatmentFB" property="popupCheckIndex"/>
        <html:hidden name="EHRSection_TreatmentFB" property="popupDoseName"/>
        
        
		
	</body>	
</html:form>