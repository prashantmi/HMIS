<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@page import="inpatient.InpatientConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function showReactionDetail()
{
	document.getElementsByName("reactionStartHr")[0].value="";
	document.getElementsByName("reactionStartMin")[0].value="";
	document.getElementsByName("reactionEndHr")[0].value="";
	document.getElementsByName("reactionEndMin")[0].value="";
	document.getElementsByName("reactionSummary")[0].value="";
	document.getElementsByName("controlSummary")[0].value="";
	
	document.getElementById("divReactionDetailID").style.display="";
}

function getBagDetail(event,index)
{
		//alert("index "+index);
		var bloodGroup=document.getElementsByName("bloodGroupArray")[index].value;
		var componentName=document.getElementsByName("componentNameArray")[index].value;
		var bloodBagNo=document.getElementsByName("bagNoArray")[index].value;
		var bagExpiry=document.getElementsByName("expiryDateArray")[index].value;
		var source=document.getElementsByName("sourceFlagArray")[index].value;
		//alert("bagExpiry "+bagExpiry);		
		var path='/HISClinical/inpatient/nurBloodTransfusionReaction.cnt?hmode=BAGDETAIL&popupBloodgroup='+bloodGroup+'&popupcomponent='+componentName+'&popupBagExpiry='+bagExpiry+'&popupBloodBagNo='+bloodBagNo+'&popupSource='+source;
		
		
		child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	  	child.moveTo(250,250);
	 	child.focus(); 
	
		if(!child.opener)
	   child.opener = self;
	 
	 	return child
}

function submitPage(mode)
{
	document.getElementsByName('hmode')[0].value=mode
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

function validateForm(mode)
{	
	
	var reactionStartDateLimit=<%=InpatientConfig.BLOOD_TRANSFUSION_REACTION_START_DATE_LIMIT%>;
	var len=document.getElementsByName("selectedBagArray").length;
	var selectFlag=false;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("selectedBagArray")[i].checked)
		{
			selectFlag=true;
			document.getElementsByName("selectedBag")[0].value=i;
			if(document.getElementsByName("reactionStartDate")[0].value!="")
			{
				var noOfDay=dateDifference(
					convertStrToDate(document.getElementsByName("transfussionDateArray")[i].value,'dd-Mon-yyyy'),
					convertStrToDate(document.getElementsByName("reactionStartDate")[0].value,'dd-Mon-yyyy')
						);
				if(noOfDay>reactionStartDateLimit)
				{
					alert("Reaction start date can not be after "+reactionStartDateLimit+" days of transfusion");
					document.getElementsByName('reactionStartDate')[0].focus();
					return false;
				}
			}
		}
	}
	if(!selectFlag)
	{
		alert("Please select bag");
		document.getElementsByName('selectedBagArray')[0].focus();
		return false;
	}
	
	/*
	alert("ffff--> "+document.getElementsByName('selectedDrug')[0]);
	if(document.getElementsByName('selectedDrug')[0].value=="")
	{
		alert("Please Select Drug");
		document.getElementsByName('selectedDrug')[0].focus();
		return false;
	}
	*/
	if(document.getElementsByName('reactionStartDate')[0].value=="")
	{
		alert("Please Enter Reaction Start Date");
		document.getElementsByName('reactionStartDate')[0].focus();
		return false;
	}
	//alert("sysdate "+ document.getElementsByName("sysDate")[0].value);
	var noOfDay=noOfDays(document.getElementsByName("reactionStartDate")[0].value,document.getElementsByName("sysDate")[0].value)
	if(noOfDay>0)
	{
		alert("Start date can not be greater than today date");
		document.getElementsByName('reactionStartDate')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('reactionEndDate')[0].value=="")
	{
		alert("Please Enter Reaction End Date");
		document.getElementsByName('reactionEndDate')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('reactionStartHr')[0].value=="")
	{
		alert("Please Enter Reaction Start Hours ");
		document.getElementsByName('reactionStartHr')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('reactionStartHr')[0].value>23)
	{
		alert("Reaction Start Hours Can Not Be Greater Than 23");
		document.getElementsByName('reactionStartHr')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('reactionStartMin')[0].value=="")
	{
		alert("Please Enter Reaction Start Min");
		document.getElementsByName('reactionStartMin')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('reactionStartMin')[0].value>59)
	{
		alert("Reaction Start Min Can Not Be Greater Than 59");
		document.getElementsByName('reactionStartMin')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('reactionEndHr')[0].value=="")
	{
		alert("Please Enter Reaction End Hours ");
		document.getElementsByName('reactionEndHr')[0].focus();
		return false;
	}
	if(document.getElementsByName('reactionEndHr')[0].value>23)
	{
		alert("Reaction End Hours Can Not Be Greater Than 23");
		document.getElementsByName('reactionEndHr')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('reactionEndMin')[0].value=="")
	{
		alert("Please Enter Reaction End Min");
		document.getElementsByName('reactionEndMin')[0].focus();
		return false;
	}
	if(document.getElementsByName('reactionEndMin')[0].value>59)
	{
		alert("Reaction End Min Can Not Be Greater Than 59");
		document.getElementsByName('reactionEndMin')[0].focus();
		return false;
	}
	
	
			var days=noOfDays(document.getElementsByName("reactionStartDate")[0].value,document.getElementsByName("reactionEndDate")[0].value)
			//alert("days "+days);
			
			if(days>0)
			{
				alert("End date can not be less than start date");
				document.getElementsByName("reactionEndDate")[0].focus();
				return false;
			}
			if(days=="0")
			{
				if(parseInt(document.getElementsByName("reactionStartHr")[0].value)>parseInt(document.getElementsByName("reactionEndHr")[0].value))
				{
					alert("End time can not be less than or equal to start time");
					document.getElementsByName("reactionEndHr")[0].focus();
					return false;
				}
				
				if(parseInt(document.getElementsByName("reactionStartHr")[0].value)==parseInt(document.getElementsByName("reactionEndHr")[0].value))
				{
					//alert("inside hour");
					if(parseInt(document.getElementsByName("reactionEndMin")[0].value)<=parseInt(document.getElementsByName("reactionStartMin")[0].value))
					{
						alert("End time can not be less than or equal to start time");
						document.getElementsByName("reactionEndHr")[0].focus();
						return false;
					}
				}
				
			}					
	 submitPage(mode);
}
function openReactionSummaryMacros(processId,event,targetField)
{
	//alert("processId "+ processId);
	//alert("targetField "+targetField);
	var path="/HISClinical/inpatient/nurBloodTransfusionReaction.cnt?hmode=ADDMACRO&macroProcessId="+processId+"&targetField="+targetField;
	//alert("path "+path);
	openPopup(createFHashAjaxQuery(path),event,300,600);
}


</script>

<%@page import="opd.OpdConfig"%>
<html>
<body>
	<his:TitleTag name="Blood Transfusion Reaction">
	</his:TitleTag>
	
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	
	<logic:present name="<%=InpatientConfig.BLOOD_TRANSFUSION_VO_LIST %>">
	<logic:notEmpty name="<%=InpatientConfig.BLOOD_TRANSFUSION_VO_LIST %>">
	<his:ContentTag>
	<his:SubTitleTag name="Transfused Blood Bag Detail">
	</his:SubTitleTag>
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="4%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						
					 		<bean:message key="select" />
					 	
					 </font>
				</div>
			 </td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						
					 		<bean:message key="bloodBagNo" />
					 	
					 </font>
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						
					 		<bean:message key="transfusionDate" />
					 	
					</font>
				</div>
			</td>
			<td width="12%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						
							<bean:message key="startTime" /><br>
						 	<bean:message key="timeFormat" />
						
					</font>
				</div>
			</td>
			<td width="12%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						
							<bean:message key="endTime" /><br>
							<bean:message key="timeFormat" />
						
					</font>
				</div>
			</td>
		</tr>
		<logic:iterate id="bloodTransfusionDtlVO" indexId="idx" name="<%=InpatientConfig.BLOOD_TRANSFUSION_VO_LIST%>" type="hisglobal.vo.BloodTransfusionDtlVO">
		<%String index=idx.toString(); %>
		<tr>
			<td width="5%" class="tdfont">
				<div align="center">	
			    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			        	<b> 
							<html:radio property="selectedBagArray" name="BloodTransfusionReactionFB"  value="<%=idx.toString() %>" onclick="showReactionDetail()" ></html:radio>
			         		<html:hidden property="bagNoArray" name="BloodTransfusionReactionFB" value="<%=bloodTransfusionDtlVO.getBloodBagNo() %>"/>
			         		<html:hidden property="bagSeqNoArray" name="BloodTransfusionReactionFB" value="<%=bloodTransfusionDtlVO.getBloodBagSequenceNo() %>"/>
			         		<html:hidden property="transfusionStatusArray" name="BloodTransfusionReactionFB" value=""/>
			         		<html:hidden property="sourceFlagArray" name="BloodTransfusionReactionFB" value="<%=bloodTransfusionDtlVO.getSourceFlag() %>"/>
			         		<html:hidden property="componentNameArray" name="BloodTransfusionReactionFB" value="<%=bloodTransfusionDtlVO.getComponentName() %>"/>
			         		<html:hidden property="bloodGroupArray" name="BloodTransfusionReactionFB" value="<%=bloodTransfusionDtlVO.getBloodGroup() %>"/>
			         		<html:hidden property="expiryDateArray" name="BloodTransfusionReactionFB" value="<%=bloodTransfusionDtlVO.getBloodBagExpiry()%>"/>
			         		<html:hidden property="requisitionNoArray" name="BloodTransfusionReactionFB" value="<%=bloodTransfusionDtlVO.getRequisitionNo()%>"/>
			         		<html:hidden property="stockBagSerialNoArray" name="BloodTransfusionReactionFB" value="<%=bloodTransfusionDtlVO.getStockBagSerialNo()%>"/>
			         		<html:hidden property="stockDateArray" name="BloodTransfusionReactionFB" value="<%=bloodTransfusionDtlVO.getStockDate()%>"/>
			         	</b>
			        </font>		
			   </div>
		    </td>
			  <td width="10%" class="tdfont">
			  	<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<a style="cursor: pointer" onclick="getBagDetail(event,'<%=index %>')"> <%=bloodTransfusionDtlVO.getBloodBagNo() %> </a>
							
						</b>
					</font>
				</div>
			 </td>
			 <td width="10%" class="tdfont">
			  	<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:write name="bloodTransfusionDtlVO" property="transfussionDate"/>
							<html:hidden name="BloodTransfusionReactionFB" property="transfussionDateArray" value="<%=bloodTransfusionDtlVO.getTransfussionDate() %>"/>
						</b>
					</font>
				</div>
			 </td>
			 <td width="10%" class="tdfont">
			  	<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:write name="bloodTransfusionDtlVO" property="transfussionStartTime"/>
						</b>
					</font>
				</div>
			 </td>
			 <td width="10%" class="tdfont">
			  	<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:write name="bloodTransfusionDtlVO" property="transfussionEndTime"/>
						</b>
					</font>
				</div>
			 </td>
		 </tr>
		</logic:iterate>
		<tr>
		</tr>
	</table>
	</his:ContentTag>
	</logic:notEmpty>
	</logic:present>
	
			<div id="divReactionDetailID" style="display: none;">
			<his:SubTitleTag name="Blood Transfusion Reaction Detail">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td class="tdfonthead" width="25%">
							<div align="right" >
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 						<bean:message key="reactionStartDate" />
				 				</font>
				 	 		</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<his:date name="reactionStartDate"  dateFormate="%d-%b-%Y" ></his:date>
							</div>
						</td>	
						<td class="tdfonthead" width="25%">
							<div align="right" >
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 						<bean:message key="reactionEndDate" />
				 				</font>
				 	 		</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<his:date name="reactionEndDate" dateFormate="%d-%b-%Y" ></his:date>
							</div>
						</td>		
					</tr>
					<tr>
						<td class="tdfonthead" width="25%">
							<div align="right" >
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 		 				<bean:message key="reactionStartTime" />
				 				</font>
				 	 		</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
								<span id='divStarthrcontrol' align="left">	            
				   					<html:text name="BloodTransfusionReactionFB" tabindex="1" property="reactionStartHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" />	
				  			 			<b>
				  			 				<bean:message key="colon"/>
				  			 			</b>
								</span>
				 				<span id='divStartMinControl' align="left">         
									<html:text name="BloodTransfusionReactionFB" tabindex="1" property="reactionStartMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)"/>				
										<bean:message key="timeFormat"/>
		    					</span>
							</font>
						</td>
						<td class="tdfonthead" width="25%">
							<div align="right" >
						 		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 						<bean:message key="reactionEndTime" />
				 			 	 	</font>
				 	 		</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
								<span id='divEndhrcontrol' align="left">	            
				   					<html:text name="BloodTransfusionReactionFB" tabindex="1" property="reactionEndHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" />	
				  			 			<b>
				  			 				<bean:message key="colon"/>
				  			 			</b>
								</span>
				 				<span id='divEndMinControl' align="left">         
									<html:text name="BloodTransfusionReactionFB" tabindex="1" property="reactionEndMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)"/>				
										<bean:message key="timeFormat"/>
		    					</span>
							 </font>
						</td>
					</tr>
					<tr>
						<td class="tdfonthead" >
							<div align="right" >
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 					<bean:message key="reactionSummary" />
				 				</font>
				 	 		</div>
						</td>
						<td  class="tdfont" colspan="3">
							<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
								<html:textarea name="BloodTransfusionReactionFB" tabindex="1" rows="3" cols="100" property="reactionSummary" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))">
								</html:textarea>
							</font>
						</td>
						
					</tr>		 
					<tr>
						<td class="tdfonthead" >
							<div align="right" >
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 					<bean:message key="controlSummary" />
				 				</font>
				 	 		</div>
						</td>
						<td  class="tdfont" colspan="3">
							<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
								<html:textarea name="BloodTransfusionReactionFB" tabindex="1" rows="3" cols="100" property="controlSummary" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
							 </font>
						</td>
						
					</tr>
			</table>
			<logic:present name="<%=InpatientConfig.TEMPLATEID_LIST_FOR_TRANSFUSION_REACTION%>">
			<logic:iterate id="templateId" indexId="idx" name="<%=InpatientConfig.TEMPLATEID_LIST_FOR_TRANSFUSION_REACTION%>" type="java.lang.String">
				<his:GenericTemplateTag templateId="<%=templateId %>"></his:GenericTemplateTag>
			</logic:iterate>
			</logic:present>
			
			</his:ContentTag>
			</div>
			
		<his:ButtonToolBarTag>
			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
		</his:ButtonToolBarTag>
<html:hidden name="BloodTransfusionReactionFB" property="hmode"/>
<html:hidden name="BloodTransfusionReactionFB" property="patCrNo"/>
<html:hidden name="BloodTransfusionReactionFB" property="episodeCode" />
<html:hidden name="BloodTransfusionReactionFB" property="episodeVisitNo" />
<html:hidden name="BloodTransfusionReactionFB" property="admissionNo" />
<html:hidden name="BloodTransfusionReactionFB" property="sysDate"/>
<html:hidden name="BloodTransfusionReactionFB" property="selectedBag"/>
</body>
</html>