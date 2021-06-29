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
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<%@page import="inpatient.InpatientConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/hisglobal/utility/generictemplate/js/templateValidations.js"/>

<script type="text/javascript">
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


function validateForm(templateValidation,mode)
{	
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
	var noOfDay1=noOfDays(document.getElementsByName("reactionStartDate")[0].value,document.getElementsByName("administrationDate")[0].value)
	if(noOfDay1<0)
	{
		alert("Reaction start date can not be less than administration date");
		document.getElementsByName('reactionStartDate')[0].focus();
		return false;
	}
	
		
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
	
	
	if(noOfDay1==0)
	{
		if(document.getElementsByName('reactionStartHr')[0].value<document.getElementsByName('administrationTimeHour')[0].value)
		{
			alert("Reaction start time can not be less than administration time");
			document.getElementsByName('reactionStartHr')[0].focus();
			return false;
		}
	
		if(document.getElementsByName('reactionStartHr')[0].value==document.getElementsByName('administrationTimeHour')[0].value)
		{
			if(document.getElementsByName('reactionStartMin')[0].value<document.getElementsByName('administrationTimeMinute')[0].value)
			{
				alert("Reaction start time can not be less than administration time");
				document.getElementsByName('reactionStartHr')[0].focus();
				return false;
			}
		}
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
	if(templateValidation)
	{
		submitPage(mode);
	}						
	
}

function getSelectedDrugDetail(event,index)
{
		var path='/HISClinical/inpatient/nurDrugReaction.cnt?hmode=SELECTEDDRUGDETAIL';
		child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	  	child.moveTo(250,250);
	 	child.focus(); 
	
		if(!child.opener)
	   child.opener = self;
	 
	 	return child
}
function showPrevReactionDtl(event,index,itemId)
{
		//alert("itemId "+itemId);
		var patCrNo=document.getElementsByName("patCrNo")[0].value;
		
		var path='/HISClinical/inpatient/nurDrugReaction.cnt?hmode=PREVREACTIONDETAIL&prevItemId='+itemId+'&patCrNo='+patCrNo;
		child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+400+',width='+700+',left=10,top=10');  
	  	child.moveTo(250,250);
	 	child.focus(); 
	
		if(!child.opener)
	   child.opener = self;
	 
	 	return child
}

</script>
<%@page import="hisglobal.vo.DrugAdminDtlVO"%>
<html>
<body>
	<his:TitleTag name="Drug Reaction Detail">
	</his:TitleTag>
		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	
	<logic:present name="<%=InpatientConfig.PREV_DRUG_REACTION_LIST_BY_CRNO%>">	
	<logic:notEmpty name="<%=InpatientConfig.PREV_DRUG_REACTION_LIST_BY_CRNO%>">
	<his:SubTitleTag name="Previously Reacted Drug Detail">
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
			
				<td class="tdfonthead"  >
		  					<div align="center">	           
		 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 							<b>
		 								<bean:message key="drug"/>
		 							</b>	
		  						</font>
		  					</div>
	      				</td> 
	      				<td class="tdfonthead"  >
		  					<div align="center">	           
		 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 							<b>
		 								<bean:message key="drugBrandName"/>
		 							</b>	
		  						</font>
		  					</div>
	      				</td> 
	      		<td class="tdfonthead"  >
		  					<div align="center">	           
		 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 							<b>
		 								<bean:message key="reactionStartDate" />
		 							</b>	
		  						</font>
		  					</div>
	      				</td>
	      		<td class="tdfonthead"  >
		  					<div align="center">	           
		 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 							<b>
		 								<bean:message key="reactionEndDate" />
		 							</b>	
		  						</font>
		  					</div>
	      				</td>				
			</tr>
	<logic:iterate id="entryObj" indexId="idx" name="<%=InpatientConfig.PREV_DRUG_REACTION_LIST_BY_CRNO%>" type="hisglobal.utility.Entry">		
			<tr>
				<td width="10%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<a style="cursor:pointer" onclick="showPrevReactionDtl(event,<%=idx.toString()%>,<%=entryObj.getValue().split("#")[0] %>)" >
										<%=entryObj.getLabel() %>
									</a>
								</b>
							</font>
						</div>
					</td>
					
					<td width="10%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<%-- 	<%=entryObj.getValue().split("#")[3] %> --%>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<%=entryObj.getValue().split("#")[1] %>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<%=entryObj.getValue().split("#")[2] %>
								</b>
							</font>
						</div>
					</td>
			</tr>
	</logic:iterate>		
		</table>
	</his:ContentTag>
	</logic:notEmpty>
	</logic:present>	
		
	<logic:present name="<%=InpatientConfig.ESSENTIALS_ADMINISTRATION_DATE_LST%>">
	<logic:notEmpty name="<%=InpatientConfig.ESSENTIALS_ADMINISTRATION_DATE_LST%>">
	<his:SubTitleTag name="Drug Given Detail">
	</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
					<td width="25%" class="tdfonthead">
		  					<div align="right">	           
		 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 							<b>
		 								<bean:message key="administrationDate"/>
		 							</b>	
		  						</font>
		  					</div>
	      				</td> 
		   		 	<td width="25%" class="tdfont" nowrap="nowrap"  >
						<div align="left" >
							<html:select name="DrugReactionFB" property="selAdministrationDate" tabindex="1" onchange="submitPage('DRUGGIVEN')" > 
								<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=InpatientConfig.ESSENTIALS_ADMINISTRATION_DATE_LST%>">
										<html:options collection="<%=InpatientConfig.ESSENTIALS_ADMINISTRATION_DATE_LST%>" property="value" labelProperty="label" />
									</logic:present>
							</html:select>
						</div>
					</td>
				</tr>	
		</table>
		
		<logic:notEmpty name="<%=InpatientConfig.ESSENTIALS_DRUG_EXECUTION_LIST_BY_ADMINISTRATIONDATE%>">
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<logic:notEqual value="GETREACTIONDTL" name="DrugReactionFB" property="hmode">
					<td width="5%" class="tdfonthead" >
		  				<div align="center">	           
		 					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 						<b>
		 							<bean:message key="select"/>
		 						</b>	
		  					</font>
		  				</div>
	      			</td> 
	      		</logic:notEqual>		
					<td width="14%" class="tdfonthead" >
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="drugname" />
								</b>
							</font>
						</div>
					</td>
					
					<td width="14%" class="tdfonthead" >
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="drugBrandName" />
								</b>
							</font>
						</div>
					</td>
					
					<td width="10%" class="tdfonthead" >
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="dosage" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" >
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="drugRoute" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" >
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="administrationDate" />
								</b>
							</font>
						</div>
					</td>
					<td width="12%" class="tdfonthead" >
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="administrationTime" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" >
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="batchNo" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" >
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="expiryDate" />
								</b>
							</font>
						</div>
					</td>			
			</tr>
			<logic:iterate id="drugAdminDtlVO" indexId="idx" name="<%=InpatientConfig.ESSENTIALS_DRUG_EXECUTION_LIST_BY_ADMINISTRATIONDATE%>" type="hisglobal.vo.DrugAdminDtlVO">
				<%String index=idx.toString(); %>
				<tr>
				<logic:notEqual value="GETREACTIONDTL" name="DrugReactionFB" property="hmode">
					<td width="5%" class="tdfont">
			        	<div align="center">	
			         		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			         			<b> 
									<html:radio name="DrugReactionFB" property="selectedDrug" value="<%=idx.toString() %>" onclick="submitPage('GETREACTIONDTL')" ></html:radio>
			         			</b>
			         		</font>		
			         	</div>
		      		</td> 
		      	</logic:notEqual> 
		      	<logic:equal value="GETREACTIONDTL" name="DrugReactionFB" property="hmode"> 
					<td width="10%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<a style="cursor: pointer" onclick="getSelectedDrugDetail(event,'<%=index %>')"></a>
									<bean:write name="drugAdminDtlVO" property="itemName"/>
									<html:hidden name="DrugReactionFB" property="itemIdArray" value="<%=drugAdminDtlVO.getItemId() %>"/>
									<html:hidden name="DrugReactionFB" property="administrationDate" value="<%=drugAdminDtlVO.getAdministrationDate() %>"/>
									<html:hidden name="DrugReactionFB" property="administrationTimeHour" value='<%=drugAdminDtlVO.getAdministrationTime().split(":")[0] %>'/>
									<html:hidden name="DrugReactionFB" property="administrationTimeMinute" value='<%=drugAdminDtlVO.getAdministrationTime().split(":")[1] %>'/>
								    <html:hidden name="DrugReactionFB" property="drugBrandId" value='<%=drugAdminDtlVO.getDrugBrandId() %>'/>
						   		    <html:hidden name="DrugReactionFB" property="drugBrandName" value='<%=drugAdminDtlVO.getDrugBrandName() %>'/>
							
								</b>
							</font>
						</div>
					</td>
				</logic:equal>
				<logic:notEqual value="GETREACTIONDTL" name="DrugReactionFB" property="hmode">
					<td width="10%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugAdminDtlVO" property="itemName"/>
									<html:hidden name="DrugReactionFB" property="itemIdArray" value="<%=drugAdminDtlVO.getItemId() %>"/>
								</b>
							</font>
						</div>
					</td>
				</logic:notEqual> 
				
					
					<td width="10%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:write name="drugAdminDtlVO" property="drugBrandName"/>
								
							</b>
						</font>
					</div>
				</td>
				
				<td width="5%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:write name="drugAdminDtlVO" property="doseName"/>
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:write name="drugAdminDtlVO" property="drugRouteName"/>
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:write name="drugAdminDtlVO" property="administrationDate"/>
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:write name="drugAdminDtlVO" property="administrationTime"/>
								<html:hidden name="DrugReactionFB" property="drugGivenTimeArray" value="<%=drugAdminDtlVO.getAdministrationTime() %>"/>
							</b>
						</font>
					</div>
				</td>
				<td width="8%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:write name="drugAdminDtlVO" property="batchNo"/>
								<html:hidden name="DrugReactionFB" property="batchNoArray" value="<%=drugAdminDtlVO.getBatchNo() %>"/>
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:write name="drugAdminDtlVO" property="expiryDate"/>
							</b>
						</font>
					</div>
				</td>
			</tr>		
			</logic:iterate>
		</table>
		</logic:notEmpty>
		</his:ContentTag>
		</logic:notEmpty>
		</logic:present>
			
		<logic:equal value="GETREACTIONDTL" name="DrugReactionFB" property="hmode">
			<his:SubTitleTag name="Drug Reaction Detail">
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
				   					<html:text name="DrugReactionFB" tabindex="1" property="reactionStartHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" />	
				  			 			<b>
				  			 				<bean:message key="colon"/>
				  			 			</b>
								</span>
				 				<span id='divStartMinControl' align="left">         
									<html:text name="DrugReactionFB" tabindex="1" property="reactionStartMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)"/>				
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
				   					<html:text name="DrugReactionFB" tabindex="1" property="reactionEndHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" />	
				  			 			<b>
				  			 				<bean:message key="colon"/>
				  			 			</b>
								</span>
				 				<span id='divEndMinControl' align="left">         
									<html:text name="DrugReactionFB" tabindex="1" property="reactionEndMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)"/>				
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
								<html:textarea name="DrugReactionFB" tabindex="1" rows="3" cols="100" property="reactionSummary" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))">
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
								<html:textarea name="DrugReactionFB" tabindex="1" rows="3" cols="100" property="controlSummary" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))"></html:textarea>
							 </font>
						</td>
					</tr>
			</table>
			
			<logic:present name="<%=InpatientConfig.TEMPLATEID_LIST_FOR_DRUG_REACTION%>">
			<logic:iterate id="templateId" indexId="idx" name="<%=InpatientConfig.TEMPLATEID_LIST_FOR_DRUG_REACTION%>" type="java.lang.String">
				<his:GenericTemplateTag templateId="<%=templateId %>"></his:GenericTemplateTag>
			</logic:iterate>
			</logic:present>
			</his:ContentTag>
			</logic:equal>
			
		<his:ButtonToolBarTag>
			<logic:notEmpty name="<%=InpatientConfig.ESSENTIALS_ADMINISTRATION_DATE_LST%>">
				<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "validateForm(validatePARAMETERCompulsoryField(),'SAVE');" onkeypress="if(event.keyCode==13)validateForm(validatePARAMETERCompulsoryField(),'SAVE');")>
			</logic:notEmpty>
			<logic:equal value="GETREACTIONDTL" property="hmode" name="DrugReactionFB">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
			</logic:equal>
			<logic:notEqual value="GETREACTIONDTL" property="hmode" name="DrugReactionFB">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	      	</logic:notEqual>
	      	<logic:notEmpty name="<%=InpatientConfig.ESSENTIALS_ADMINISTRATION_DATE_LST%>">
	        	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
			</logic:notEmpty>
		</his:ButtonToolBarTag>
		

<html:hidden name="DrugReactionFB" property="hmode"/>
<html:hidden name="DrugReactionFB" property="sysDate"/>
<html:hidden name="DrugReactionFB" property="admissionDate" />
<html:hidden name="DrugReactionFB" property="episodeCode"/>
<html:hidden name="DrugReactionFB" property="patCrNo"/>
<html:hidden name="DrugReactionFB" property="episodeVisitNo"/>
<html:hidden name="DrugReactionFB" property="admissionNo"/>


</body>
</html>