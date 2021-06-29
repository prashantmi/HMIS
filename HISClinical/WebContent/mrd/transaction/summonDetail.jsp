<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.hisconfig.Config"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<%@page import="mrd.MrdConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function noOfDays(a,b)
{
	valid=true;
	var day=0;
	var aArray=a.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	var bArray=b.split("-");
	var bday=bArray[0];
	var bmonth=bArray[1];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);
	day=(adate-bdate)/86400000;
	return day;
}

function enableSpouseName()
{
	if(document.getElementsByName("patAgeType")[0].value=="Y")
	{
		if(document.getElementsByName("age")[0].value><%=MrdConfig.MIN_AGE_FOR_GETTING_MARRIED%> && document.getElementsByName("patGenderCode")[0].value==<%=Config.GENDER_TYPE_FEMALE%>)
		{
			document.getElementsByName("spouseName")[0].disabled=false;
		}
		else
		{
			document.getElementsByName("spouseName")[0].value="";
			document.getElementsByName("spouseName")[0].disabled=true;
		}
	}
	else
	{
		document.getElementsByName("spouseName")[0].value="";
		document.getElementsByName("spouseName")[0].disabled=true;
	}	
	
}

function enableMotherName()
{
	if(document.getElementsByName("patAgeType")[0].value=="D")
	{
		if(document.getElementsByName("age")[0].value><%=MrdConfig.MAX_VALUE_OF_DAYS%>)
		{
			alert("Number of days can not be greater than" + <%=MrdConfig.MAX_VALUE_OF_DAYS%>);
			document.getElementsByName("age")[0].focus();
			return false;
		}
	}
	
	if(document.getElementsByName("patAgeType")[0].value=="W")
	{
		if(document.getElementsByName("age")[0].value><%=MrdConfig.MAX_VALUE_OF_WEAK%>)
		{
			alert("Number of weak can not be greater than "+<%=MrdConfig.MAX_VALUE_OF_WEAK%>);
			document.getElementsByName("age")[0].focus();
			return false;
		}
	}
	
	if(document.getElementsByName("patAgeType")[0].value=="M")
	{
		if(document.getElementsByName("age")[0].value><%=MrdConfig.MAX_VALUE_OF_MONTH%>)
		{
			alert("Number of month can not be greater than "+<%=MrdConfig.MAX_VALUE_OF_MONTH%>);
			document.getElementsByName("age")[0].focus();
			return false;
		}
	}
	
	if(document.getElementsByName("patAgeType")[0].value=="Y")
	{
		if(document.getElementsByName("age")[0].value<<%=MrdConfig.CHILD_AGE_IN_YEAR_FOR_ASKING_MOTHER_NAME%>)
		{
			document.getElementsByName("motherName")[0].disabled=false;
		}
		else
		{
			document.getElementsByName("motherName")[0].value="";
			document.getElementsByName("motherName")[0].disabled=true;
		}
	}
	else
	{
		document.getElementsByName("motherName")[0].disabled=false;
	}
}

function validateForm(mode)
{
	if(document.getElementsByName("recevingDate")[0].value=="")
	{
		if(document.getElementsByName("cidNoFlag")[0].value==<%=MrdConfig.ENABLE%>)
		{
			alert("Please select CID NO"+'\n'+"For this click on plus botton");
			document.getElementById("addButtonPlus")[0].focus();
		}
		else
		{
			alert("Please select receiving date");
			document.getElementsByName("recevingDate")[0].focus();
		}
		
		return false;
	}
	
	var noOfDay3=noOfDays(document.getElementsByName("sysDate")[0].value,document.getElementsByName("recevingDate")[0].value)
	if(noOfDay3<0)
	{
		alert("Receving date can not be greater than system date");
		document.getElementsByName("recevingDate")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("recevingTimeHr")[0].value=="")
	{
		alert("Please enter receiving time hour");
		document.getElementsByName("recevingTimeHr")[0].focus();
		return false;
	}
	if(document.getElementsByName("recevingTimeHr")[0].value>23)
	{
		alert("Hours can not be greater than 23");
		document.getElementsByName("recevingTimeHr")[0].focus();
		return false;
	}
	if(document.getElementsByName("recevingTimeMin")[0].value=="")
	{
		alert("Please enter receiving time minute");
		document.getElementsByName("recevingTimeMin")[0].focus();
		return false;
	}
	if(document.getElementsByName("recevingTimeMin")[0].value>59)
	{
		alert("Minute can not be greater than 59");
		document.getElementsByName("recevingTimeMin")[0].focus();
		return false;
	}
	
	var noOfDay4=noOfDays(document.getElementsByName("sysDate")[0].value,document.getElementsByName("recevingDate")[0].value)
	if(noOfDay4==0)
	{
		if(document.getElementsByName("recevingTimeHr")[0].value>document.getElementsByName("sysHour")[0].value)
		{
			alert("Receving time can not be greater than system time");
			document.getElementsByName("recevingTimeHr")[0].focus();
			return false;
		}
	
		if(document.getElementsByName("recevingTimeHr")[0].value==document.getElementsByName("sysHour")[0].value)
		{
			if(document.getElementsByName("recevingTimeMin")[0].value>document.getElementsByName("sysMinute")[0].value)
			{
				alert("Receving time can not be greater than system time");
				document.getElementsByName("recevingTimeMin")[0].focus();
				return false;
			}
		}
	}
	
	
	
	if(document.getElementsByName("summonTypeId")[0].value=="-1")
	{
		alert("Please select summon type");
		document.getElementsByName("summonTypeId")[0].focus();
		return false;
	}
	if(document.getElementsByName("summonDateTime")[0].value=="")
	{
		alert("Please select summon date");
		document.getElementsByName("summonDateTime")[0].focus();
		return false;
	}
	
	var noOfDay2=noOfDays(document.getElementsByName("summonDateTime")[0].value,document.getElementsByName("recevingDate")[0].value)
	
	if(noOfDay2>0)
	{
		alert("Summon date can not be greater than receving date");
		document.getElementsByName("summonDateTime")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("hearingDate")[0].value=="")
	{
		alert("Please enter hearing date");
		document.getElementsByName("hearingDate")[0].focus();
		return false;
	}
	var noOfDay=noOfDays(document.getElementsByName("summonDateTime")[0].value,document.getElementsByName("hearingDate")[0].value)
	
	if(noOfDay>=0)
	{
		alert("Hearing Date should be greater than Summon Date");
		document.getElementsByName("hearingDate")[0].focus();
		return false;
	}
	
	var noOfDay1=noOfDays(document.getElementsByName("recevingDate")[0].value,document.getElementsByName("hearingDate")[0].value)
	if(noOfDay1>=0)
	{
		alert("Hearing Date should be greater than Summon Receive Date");
		document.getElementsByName("hearingDate")[0].focus();
		return false;
	}
	
	if(noOfDay1==0)
	{
		if(document.getElementsByName("recevingTimeHr")[0].value>document.getElementsByName("hearingTimeHr")[0].value)
		{
			alert("Hearing time can not be less than receving time");
			document.getElementsByName("hearingTimeHr")[0].focus();
			return false;
		}
		if(document.getElementsByName("recevingTimeHr")[0].value==document.getElementsByName("hearingTimeHr")[0].value)
		{
			if(document.getElementsByName("recevingTimeMin")[0].value>document.getElementsByName("hearingTimeMin")[0].value)
			{
				alert("Hearing time can not be less than receving time");
				document.getElementsByName("hearingTimeMin")[0].focus();
				return false;
			}
		}
	}
	
	if(document.getElementsByName("hearingTimeHr")[0].value>23)
	{
		alert("Hours can not be greater than 23");
		document.getElementsByName("hearingTimeHr")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("hearingTimeMin")[0].value>59)
	{
		alert("Minute can not be greater than 59");
		document.getElementsByName("hearingTimeMin")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("courtName")[0].value=="")
	{
		alert("Please enter court name");
		document.getElementsByName("courtName")[0].focus();
		return false;
	}
	if(document.getElementsByName("judgeName")[0].value=="")
	{
		alert("Please enter judge name");
		document.getElementsByName("judgeName")[0].focus();
		return false;
	}
	if(document.getElementsByName("constableName")[0].value=="")
	{
		alert("Please enter police officer name");
		document.getElementsByName("constableName")[0].focus();
		return false;
	}
	if(document.getElementsByName("policeStation")[0].value=="")
	{
		alert("Please enter police station address");
		document.getElementsByName("policeStation")[0].focus();
		return false;
	}
	if(document.getElementsByName("cidNoFlag")[0].value==<%=MrdConfig.ENABLE%>)
	{
		if(document.getElementsByName("CIDNo")[0].value=="")
		{
			alert("Please select CID No"+'\n'+"For this click on plus button");
			document.getElementsByName("CIDNo")[0].focus();
			return false;
		}
	}
	
	
	if(document.getElementsByName("summonFlag")[0].value==<%=MrdConfig.IS_MANUAL_SUMMON_DTL%>)
	{
		if(document.getElementsByName("patName")[0].value=="")
		{
			alert("Please enter patient name");
			document.getElementsByName("patName")[0].focus();
			return false;
		}
		if(document.getElementsByName("age")[0].value=="")
		{
			alert("Please enter patient age");
			document.getElementsByName("age")[0].focus();
			return false;
		}
		if(document.getElementsByName("patAgeType")[0].value=="D")
		{
			if(document.getElementsByName("age")[0].value><%=MrdConfig.MAX_VALUE_OF_DAYS%>)
			{
				alert("Number of days can not be greater than" + <%=MrdConfig.MAX_VALUE_OF_DAYS%>);
				document.getElementsByName("age")[0].focus();
				return false;
			}
		}
		
		if(document.getElementsByName("patAgeType")[0].value=="W")
		{
			if(document.getElementsByName("age")[0].value><%=MrdConfig.MAX_VALUE_OF_WEAK%>)
			{
				alert("Number of weak can not be greater than "+<%=MrdConfig.MAX_VALUE_OF_WEAK%>);
				document.getElementsByName("age")[0].focus();
				return false;
			}
		}
		
		if(document.getElementsByName("patAgeType")[0].value=="M")
		{
			if(document.getElementsByName("age")[0].value><%=MrdConfig.MAX_VALUE_OF_MONTH%>)
			{
				alert("Number of month can not be greater than "+<%=MrdConfig.MAX_VALUE_OF_MONTH%>);
				document.getElementsByName("age")[0].focus();
				return false;
			}
		}
		if(document.getElementsByName("patAgeType")[0].value=="-1")
		{
			alert("Please select age type");
			document.getElementsByName("patAgeType")[0].focus();
			return false;
		}
		
		if(document.getElementsByName("fatherName")[0].value=="")
		{
			alert("Please enter patient father name");
			document.getElementsByName("fatherName")[0].focus();
			return false;
		}
		if(document.getElementsByName("patGenderCode")[0].value=="-1")
		{
			alert("Please enter patient gender");
			document.getElementsByName("patGenderCode")[0].focus();
			return false;
		}
	}
	else
	{
		if(document.getElementsByName("postmortemId")[0].value=="" && document.getElementsByName("MLCNo")[0].value=="" && document.getElementsByName("patAdmNo")[0].value=="")
		{
			alert("Please enter patient info"+'\n'+"For this use search button");
			return false;
		}
	}
	
	
	
	submitPage(mode);
	
}

function showSummonDetail(mode)
{
	submitPage(mode);
}
function hideUnhide(imgObj)
{
	var divObj=document.getElementById("div"+imgObj.id.substr(3));
	if(divObj.style.display=="none")
	{
		divObj.style.display="block";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-up.png";
	}
	else if(divObj.style.display=="block")
	{
		divObj.style.display="none";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-down.png";
	}
}

function doPagination(e,p)
{	
	
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function shiftReceiveTimeTab()
{
	var receivingHour=document.getElementsByName("recevingTimeHr")[0].value;
	var len=receivingHour.length;
	//alert("len "+len);
	if(len==2)
	{
		document.getElementsByName("recevingTimeMin")[0].focus();
	}
	
}

function shiftHearingTimeTab()
{
	var hearingHour=document.getElementsByName("hearingTimeHr")[0].value;
	var len=hearingHour.length;
	if(len==2)
	{
		document.getElementsByName("hearingTimeMin")[0].focus();
	}
	
} 
</script>


		<his:SubTitleTag name="Summon Detail">
		</his:SubTitleTag>
		<div id="divSummonDetail" style="display: block;">
		<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<font color="#FF0000">*</font>
		 						<bean:message key="recevingDate"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td> 
	      		<logic:equal value="<%=MrdConfig.DISABLE %>" name="SummonDtlFB" property="cidNoFlag">
	      		<bean:define id="receiveDateId" name="SummonDtlFB" property="sysDate" type="java.lang.String"></bean:define>
	      		
	      		<td width="25%" class="tdfont">
					<div align="left">
						<his:date name="recevingDate" dateFormate="%d-%b-%Y" value="<%=receiveDateId%>"></his:date>
					</div>
				</td>
				</logic:equal>
				<logic:equal value="<%=MrdConfig.ENABLE %>" name="SummonDtlFB" property="cidNoFlag">
				<td width="25%" class="tdfont">
					<div align="left">
						<html:text property="recevingDate" name="SummonDtlFB" size="15" readonly="true"></html:text>
					</div>
				</td>
				</logic:equal>
				<td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<font color="#FF0000">*</font>
		 						<bean:message key="recevingTime"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td>
	      		<logic:equal value="<%=MrdConfig.DISABLE %>" name="SummonDtlFB" property="cidNoFlag">
	      		<td width="25%" class="tdfont">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<span id='divStarthrcontrol' align="left">	            
				   			<html:text name="SummonDtlFB" tabindex="1" property="recevingTimeHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" onkeyup="shiftReceiveTimeTab()"/>	
				  				<b>
				  			 		<bean:message key="colon"/>
				  			 	</b>
						</span>
				 		<span id='divStartMinControl' align="left">         
							<html:text name="SummonDtlFB" tabindex="1" property="recevingTimeMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)"/>				
								<bean:message key="timeFormat"/>
		    			</span>
					</font>
				</td> 
				</logic:equal>
				<logic:equal value="<%=MrdConfig.ENABLE %>" name="SummonDtlFB" property="cidNoFlag">
				<td width="25%" class="tdfont">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<span id='divStarthrcontrol' align="left">	            
				   			<html:text name="SummonDtlFB" tabindex="1" property="recevingTimeHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" readonly="true"/>	
				  				<b>
				  			 		<bean:message key="colon"/>
				  			 	</b>
						</span>
				 		<span id='divStartMinControl' align="left">         
							<html:text name="SummonDtlFB" tabindex="1" property="recevingTimeMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)" readonly="true"/>				
								<bean:message key="timeFormat"/>
		    			</span>
					</font>
				</td>
				</logic:equal>
			</tr>
			<tr>
				 <logic:equal value="<%=MrdConfig.ENABLE %>" name="SummonDtlFB" property="cidNoFlag">
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<font color="#FF0000">*</font>
			 					<bean:message key="CIDNo"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="CIDNo" value="" size="20" tabindex="1" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" readonly="true"/>
				 		<img class="button" id="addButtonPlus" style="cursor: pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' alt="Add CID No" title="Add CID No" onkeypress="if(event.keyCode==13) openPopup('/HISClinical/mrd/computerizedSummonDetail.cnt?hmode=CIDNO',event,400,700);" onclick="openPopup('/HISClinical/mrd/computerizedSummonDetail.cnt?hmode=CIDNO',event,400,700);">
				 	</div>
				 </td>
				 </logic:equal>	
				 <logic:equal value="<%=MrdConfig.DISABLE %>" name="SummonDtlFB" property="cidNoFlag">
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="fileTrackingNo"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="CIDNo" value="" size="20" tabindex="1" maxlength="30" onkeypress="return validateNumeric(event)"/>
				 	</div>
				 </td>
				 </logic:equal>
				  <td class="tdfonthead" width="25%" >
				  </td>
				   <td class="tdfont" width="25%" >
				   </td>	
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<font color="#FF0000">*</font>
		 						<bean:message key="summonType"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td> 
	      		<td width="25%" class="tdfont" width="25%"  >
					<div align="left" >
						<html:select name="SummonDtlFB" property="summonTypeId" tabindex="1" > 
							<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MrdConfig.ALL_SUMMON_TYPE_LIST%>">
									<html:options collection="<%=MrdConfig.ALL_SUMMON_TYPE_LIST%>" property="value" labelProperty="label"/>	
								</logic:present>
						</html:select>
					</div>
				</td>
				<td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<font color="#FF0000">*</font>
		 						<bean:message key="summonDate"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td> 
	      		<td width="25%" class="tdfont">
					<div align="left">
						<his:date name="summonDateTime" dateFormate="%d-%b-%Y" ></his:date>
					</div>
				</td>	
			</tr>
			
			<tr>
				<td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<font color="#FF0000">*</font>
		 						<bean:message key="hearingDate"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td> 
	      		<td width="25%" class="tdfont">
					<div align="left">
						<his:date name="hearingDate" dateFormate="%d-%b-%Y" ></his:date>
					</div>
				</td>
				<td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<bean:message key="hearingTime"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td>
	      		<td width="25%" class="tdfont">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<span id='divStarthrcontrol' align="left">	            
				   			<html:text name="SummonDtlFB" tabindex="1" property="hearingTimeHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" onkeyup="shiftHearingTimeTab()"/>	
				  				<b>
				  			 		<bean:message key="colon"/>
				  			 	</b>
						</span>
				 		<span id='divStartMinControl' align="left">         
							<html:text name="SummonDtlFB" tabindex="1" property="hearingTimeMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)"/>				
								<bean:message key="timeFormat"/>
		    			</span>
					</font>
				</td> 
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<font color="#FF0000">*</font>
			 					<bean:message key="courtName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td width="25%" class="tdfont"  >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="courtName" value="" size="20" tabindex="1" maxlength="50" onkeypress="return validateAlphaOnly(this,event)" />
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<font color="#FF0000">*</font>
			 					<bean:message key="judgeName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td  class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="judgeName" value="" size="20" tabindex="1" maxlength="50" onkeypress="return validateAlphaOnly(this,event)" />
				 	</div>
				 </td>
		    </tr>
		    <tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="courtAddress"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3" >
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="SummonDtlFB" tabindex="1" rows="2" cols="100" property="courtAddress" onkeypress="return (validateTextArea(event,this,'60'))">
						</html:textarea>
					</font>
				</td> 
				
		    </tr>
		    <tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<font color="#FF0000">*</font>
			 					<bean:message key="policeOfficerName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td width="25%" class="tdfont" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="constableName" value="" size="20" tabindex="1" maxlength="60" onkeypress="return validateAlphaOnly(this,event)" />
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="designation"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="constableDesig" value="" size="20" tabindex="1"  maxlength="50" onkeypress="return validateAlphaOnly(this,event)" />
				 	</div>
				 </td>
		    </tr>
		    
		    <tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="badgeNo"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td width="25%" class="tdfont" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="constableBadgeNo" value="" size="20" tabindex="1"  maxlength="20" onkeypress="return validateAlphaNumOnly(this,event)" />
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="caseNo"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td  class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="caseNo" value="" size="20" tabindex="1"  maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
				 	</div>
				 </td>
		    </tr>
		     <tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<font color="#FF0000">*</font>
			 					<bean:message key="policeStationAddress"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="SummonDtlFB" tabindex="1" rows="2" cols="100" property="policeStation" onkeypress="return (validateTextArea(event,this,'100'))">
						</html:textarea>
					</font>
				</td>
					
		    </tr>
		    <tr>
		    	
		    	 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="empName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td width="25%" class="tdfont"  >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="empName" value="" size="20"  tabindex="1" maxlength="100" onkeypress="return validateAlphaOnly(this,event)" />
				 	</div>
				 </td>
				<td class="tdfonthead" width="25%" >
				  </td>
				   <td class="tdfont" width="25%" >
				   </td>
		    </tr>
		   
		</table>
		</his:ContentTag>
		</div>
		<logic:notEqual value="<%=MrdConfig.IS_COMPUTERIZED_SUMMON_DTL %>" name="SummonDtlFB" property="summonFlag">		
		<his:SubTitleTag name="Patient Detail">
		</his:SubTitleTag>
		<div id="divPatientDetail" style="display: block;">
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<font color="#FF0000">*</font>
			 					<bean:message key="patientName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="patName" value="" size="20" tabindex="1" maxlength="60" onkeypress="return validateAlphaOnly(this,event)" />
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<font color="#FF0000">*</font>
			 					<bean:message key="age"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" nowrap="nowrap">
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="age" value="" size="4" tabindex="1" maxlength="3" onkeypress="return validateNumeric(event)" onkeyup="enableSpouseName();enableMotherName()"/>
				 		<html:select name="SummonDtlFB" property="patAgeType" tabindex="1" onchange="enableMotherName();enableSpouseName()"> 
							<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MrdConfig.ALL_AGE_TYPE_LIST%>">
									<html:options collection="<%=MrdConfig.ALL_AGE_TYPE_LIST%>" property="value" labelProperty="label"/>
								</logic:present>
						</html:select>
				 	</div>
				 	
				 	
				 </td>	
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<font color="#FF0000">*</font>
			 					<bean:message key="fatherName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="fatherName" value="" size="20" tabindex="1" maxlength="60" onkeypress="return validateAlphaOnly(this,event)" />
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<font color="#FF0000">*</font>
		 						<bean:message key="gender"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td> 
	      		<td width="25%" class="tdfont" width="25%"  >
					<div align="left" >
						<html:select name="SummonDtlFB" property="patGenderCode" tabindex="1"  onchange="enableSpouseName()"> 
							<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MrdConfig.ALL_GENDER_LIST%>">
									<html:options collection="<%=MrdConfig.ALL_GENDER_LIST%>" property="value" labelProperty="label"/>
								</logic:present>
						</html:select>
					</div>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="spouseName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="spouseName" value="" size="20" tabindex="1" maxlength="50" onkeypress="return validateAlphaOnly(this,event)" disabled="true"/>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="motherName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="motherName" value="" size="20" tabindex="1" maxlength="50" onkeypress="return validateAlphaOnly(this,event)" disabled="true"/>
				 	</div>
				 </td>	
			</tr>
			 <tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="patientAddress"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="SummonDtlFB" tabindex="1" rows="2" cols="100" property="patAddress" onkeypress="return (validateTextArea(event,this,'100'))">
						</html:textarea>
					</font>
				</td>
					
		    </tr>
		    <tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="crNo"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="patCrNo" value="" size="14" tabindex="1" maxlength="13" onkeypress="return validateNumeric(event)" />
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="mlcNo"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="MLCNo" value="" size="11" tabindex="1" maxlength="10" onkeypress="return validateAlphaNumOnly(this,event)" />
				 	</div>
				 </td>	
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="postMortemNo"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="postmortemId" value="" size="11" tabindex="1" maxlength="10" onkeypress="return validateNumeric(event)" />
				 	</div>
				 </td>	
				 <td class="tdfonthead" width="25%" >
				 	<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="adimissionNo"/>
			 				</b>	
			  			</font>
			  		</div>
				 </td>
				 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="patAdmNo" value="" size="12" tabindex="1" maxlength="11" onkeypress="return validateNumeric(event)" />
				 	</div>
				 </td>
			</tr>
			 <tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="summonRemarks"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="SummonDtlFB" tabindex="1" rows="2" cols="100" property="summonRemarks" onkeypress="return (validateTextArea(event,this,'200'))">
						</html:textarea>
					</font> 
				</td>
					
		    </tr>
		</table>
		</div>	
		</logic:notEqual>
		<logic:equal value="<%=MrdConfig.IS_COMPUTERIZED_SUMMON_DTL%>" name="SummonDtlFB" property="summonFlag">
		<his:SubTitleTag name="Patient Detail">
		<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<div align="right">
					<img class="button" style="cursor: pointer" alt="Search" title="Search"	src='<his:path src="/hisglobal/images/btn-search.png"/>' onclick="openPopup('/HISClinical/mrd/computerizedSummonDetail.cnt?hmode=SEARCHPAT',event,500,800);">
				</div>
			</tr>
		</table>
		</his:SubTitleTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="patientName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="patName" value="" size="20" tabindex="1" maxlength="60" onkeypress="return validateAlphaOnly(this,event)" readonly="true"/>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="age"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" nowrap="nowrap">
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="age" value="" size="20" tabindex="1" maxlength="60" onkeypress="return validateAlphaOnly(this,event)" readonly="true"/>
				 		<html:hidden name="SummonDtlFB" property="patDOB"/>
				 	</div>
				 	
				 	
				 </td>	
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="fatherName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="fatherName" value="" size="20" tabindex="1" maxlength="60" onkeypress="return validateAlphaOnly(this,event)" readonly="true"/>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<bean:message key="gender"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td> 
	      		<td width="25%" class="tdfont" width="25%"  >
					<div align="left" >
						<html:text name="SummonDtlFB" property="patGender" value="" size="20" tabindex="1" maxlength="60" onkeypress="return validateAlphaOnly(this,event)" readonly="true"/>
						<html:hidden name="SummonDtlFB" property="patGenderCode"/>
					</div>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="spouseName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="spouseName" value="" size="20" tabindex="1" maxlength="50" onkeypress="return validateAlphaOnly(this,event)" readonly="true"/>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="motherName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="motherName" value="" size="20" tabindex="1" maxlength="50" onkeypress="return validateAlphaOnly(this,event)" readonly="true"/>
				 	</div>
				 </td>	
			</tr>
			 <tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="patientAddress"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="SummonDtlFB" tabindex="1" rows="2" cols="100" property="patAddress" onkeypress="return (validateTextArea(event,this,'100'))" readonly="true">
						</html:textarea>
					</font>
				</td>
					
		    </tr>
		    <tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="crNo"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="patCrNo" value="" size="14" tabindex="1" maxlength="13" onkeypress="return validateNumeric(event)" readonly="true"/>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="mlcNo"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="MLCNo" value="" size="11" tabindex="1" maxlength="10" onkeypress="return validateAlphaNumOnly(this,event)" readonly="true"/>
				 	</div>
				 </td>	
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="postMortemNo"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="postmortemId" value="" size="11" tabindex="1" maxlength="10" onkeypress="return validateNumeric(event)" readonly="true"/>
				 	</div>
				 </td>	
				 <td class="tdfonthead" width="25%" >
				 	<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="adimissionNo"/>
			 				</b>	
			  			</font>
			  		</div>
				 </td>
				 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonDtlFB" property="patAdmNo" value="" size="12" tabindex="1" maxlength="11" onkeypress="return validateNumeric(event)" readonly="true" />
				 	</div>
				 </td>
			</tr>
			 <tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="summonRemarks"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="SummonDtlFB" tabindex="1" rows="2" cols="100" property="summonRemarks" onkeypress="return (validateTextArea(event,this,'200'))">
						</html:textarea>
					</font> 
				</td>
					
		    </tr>
		</table>
			<html:hidden name="SummonDtlFB" property="episodeCode"/>
			<html:hidden name="SummonDtlFB" property="episodeVisitNo"/>
		</logic:equal>
		
		
		<his:ButtonToolBarTag>
			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick ="validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('NEW');" onkeypress="if(event.keyCode==13) submitPage('NEW');">
		</his:ButtonToolBarTag>
		

	<html:hidden name="SummonDtlFB" property="hmode" value="unspecified"/>
	<html:hidden name="SummonDtlFB" property="sysHour"/>
	<html:hidden name="SummonDtlFB" property="sysMinute"/>
	<html:hidden name="SummonDtlFB" property="sysDate"/>
	<html:hidden name="SummonDtlFB" property="summonFlag"/>
	<html:hidden name="SummonDtlFB" property="cidNoFlag"/>
	
