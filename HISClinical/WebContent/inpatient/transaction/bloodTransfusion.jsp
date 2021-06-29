<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@page import="inpatient.InpatientConfig"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
}

function getBagDetail(event,index)
{
		//alert("index "+index);
		var bloodGroup=document.getElementsByName("bloodGroup")[index].value;
		var componentName=document.getElementsByName("componentName")[index].value;
		var bagExpiry=document.getElementsByName("bloodBagExpiry")[index].value;
		var bloodBagNo=document.getElementsByName("bloodBagNo")[index].value;
		//alert("bagExpiry "+bagExpiry);		
		var path='/HISClinical/inpatient/nurBloodTransfusion.cnt?hmode=BAGDETAIL&popupBloodgroup='+bloodGroup+'&popupcomponent='+componentName+'&popupBagExpiry='+bagExpiry+'&popupBloodBagNo='+bloodBagNo;
		
		
		child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	  	child.moveTo(250,250);
	 	child.focus(); 
	
		if(!child.opener)
	   child.opener = self;
	 
	 	return child
}


function openTransfusionReactionPopup(index)
{
	    
	if(document.getElementsByName("isTransfusionReaction")[parseInt(index)].value=="<%= InpatientConfig.IS_TRANSFUSION_YES %>" )
	{
			 	
	 	var bagNo=document.getElementsByName("bloodBagNo")[index].value;
		var count=document.getElementsByName("countChk")[index].value;
		var requisitionNo=document.getElementsByName("requisitionNo")[0].value;
		var startDate=document.getElementsByName("transfusionStartDate")[index].value;
		var startHr=document.getElementsByName("startHr")[count].value;
		var startMin=document.getElementsByName("startMin")[count].value;
		var path='/HISClinical/inpatient/nurBloodTransfusion.cnt?hmode=GETTRANSFUSIONDTL&popupBloodBagNo='+bagNo+'&popupCount='+count+'&requisitionNo='+requisitionNo+'&popupTransfusionDate='+startDate+'&popupStartHr='+startHr+'&popupStartMin='+startMin;
 		child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+600+',width='+900+',left=10,top=10');  
		child.moveTo(250,250);
		child.focus();
	 	
	}
	
}


function showCheckedValues(checkBloodBag)
{
	if(checkBloodBag.checked)
	{
		//alert("checkBloodBag.value "+checkBloodBag.value);
		document.getElementsByName("startHr")[checkBloodBag.value].disabled=false;
		document.getElementsByName("startMin")[checkBloodBag.value].disabled=false;
		document.getElementsByName("endHr")[checkBloodBag.value].disabled=false;
		document.getElementsByName("endMin")[checkBloodBag.value].disabled=false;
		document.getElementsByName("remarks")[checkBloodBag.value].disabled=false;
		document.getElementsByName("phlebotomyArm")[checkBloodBag.value].disabled=false;
		document.getElementsByName("transfussionRate")[checkBloodBag.value].disabled=false;
		document.getElementsByName("qtyTransfussed")[checkBloodBag.value].disabled=false;
		document.getElementsByName("qtyTransfussed")[checkBloodBag.value].value=document.getElementsByName("bagVolume")[checkBloodBag.value].value;
		
		document.getElementById("divEndDateTextId"+checkBloodBag.value).style.display="none";
		document.getElementById("divEndDateId"+checkBloodBag.value).style.display="";
		document.getElementById("divStartDateTextId"+checkBloodBag.value).style.display="none";
		document.getElementById("divStartDateId"+checkBloodBag.value).style.display="";
		
	}
	else
	{
		document.getElementsByName("startHr")[checkBloodBag.value].value="";
		document.getElementsByName("startMin")[checkBloodBag.value].value="";
		document.getElementsByName("endHr")[checkBloodBag.value].value="";
		document.getElementsByName("endMin")[checkBloodBag.value].value="";
		document.getElementsByName("remarks")[checkBloodBag.value].value="";
		document.getElementsByName("qtyTransfussed")[checkBloodBag.value].value="";
		document.getElementsByName("phlebotomyArm")[checkBloodBag.value].value="";
		document.getElementsByName("transfussionRate")[checkBloodBag.value].value="";
		document.getElementsByName("transfussionRate")[checkBloodBag.value].disabled="";
		document.getElementsByName("qtyTransfussed")[checkBloodBag.value].disabled="";
		
		
		document.getElementsByName("startHr")[checkBloodBag.value].disabled=true;
		document.getElementsByName("startMin")[checkBloodBag.value].disabled=true;
		document.getElementsByName("endHr")[checkBloodBag.value].disabled=true;
		document.getElementsByName("endMin")[checkBloodBag.value].disabled=true;
		document.getElementsByName("remarks")[checkBloodBag.value].disabled=true;
		document.getElementsByName("phlebotomyArm")[checkBloodBag.value].disabled=true;
		document.getElementsByName("transfussionRate")[checkBloodBag.value].disabled=true;
		document.getElementsByName("qtyTransfussed")[checkBloodBag.value].disabled=true;
		
		document.getElementById("divEndDateTextId"+checkBloodBag.value).style.display="";
		document.getElementById("divEndDateId"+checkBloodBag.value).style.display="none";
		document.getElementById("divStartDateTextId"+checkBloodBag.value).style.display="";
		document.getElementById("divStartDateId"+checkBloodBag.value).style.display="none";
	}
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
	var len=document.getElementsByName("selectedBag").length;
	//alert("len "+len);
	flag=false;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("selectedBag")[i].checked)
		{
			flag=true;
		}
	}
	if(!flag)
	{
		alert("Please Select Blood Bag");
		return false;
	}
	
	for(var i=0;i<len;i++)
	{
		//alert(document.getElementsByName("selectedBag")[i].value)
		if(document.getElementsByName("selectedBag")[i].checked)
		{
			
			var days=noOfDays(document.getElementsByName("sysdate")[0].value,document.getElementsByName("transfusionStartDate")[i].value)
			if(days<0)
			{
				alert("Transfusion date can not be greater than today date");
				document.getElementsByName("transfusionStartDate")[i].focus();
				return false;
			}
			
			var expirydays=noOfDays(document.getElementsByName("bloodBagExpiry")[i].value,document.getElementsByName("transfusionStartDate")[i].value)	
			if(expirydays<0)
			{
				alert("This blood bag is expired");
				document.getElementsByName("transfusionEndDate")[i].focus();
				return false;
			}
			if(document.getElementsByName("startHr")[i].value=="")
			{
				alert("Please Enter Start Time(Hour)");
				document.getElementsByName("startHr")[i].focus();
				return false;
			}
			if(document.getElementsByName("startMin")[i].value=="")
			{
				alert("Please Enter Start Time(Minute)");
				document.getElementsByName("startMin")[i].focus();
				return false;
			}
			if(document.getElementsByName("endHr")[i].value=="")
			{
				alert("Please Enter End Time(Hour)");
				document.getElementsByName("endHr")[i].focus();
				return false;
			}
			if(document.getElementsByName("endMin")[i].value=="")
			{
				alert("Please Enter End Time(Minute)");
				document.getElementsByName("endMin")[i].focus();
				return false;
			}
						
			if(parseInt(document.getElementsByName("startHr")[i].value)>23)
			{
				alert("Hour can not be greater than 23");
				document.getElementsByName("startHr")[i].focus();
				return false;
			}
			
			if(parseInt(document.getElementsByName("startMin")[i].value)>59)
			{
				alert("Minut can not be greater than 59");
				document.getElementsByName("startMin")[i].focus();
				return false;
			}
			
			if(parseInt(document.getElementsByName("endHr")[i].value)>23)
			{
				alert("Hour can not be greater than 23");
				document.getElementsByName("endHr")[i].focus();
				return false;
			}
			
			if(parseInt(document.getElementsByName("endMin")[i].value)>59)
			{
				alert("Minut can not be greater than 59");
				document.getElementsByName("endMin")[i].focus();
				return false;
			}
			
			
			
			var days=noOfDays(document.getElementsByName("transfusionStartDate")[i].value,document.getElementsByName("transfusionEndDate")[i].value)
			if(days>0)
			{
				alert("End date can not be less than start date");
				document.getElementsByName("transfusionEndDate")[i].focus();
				return false;
			}
			if(days=="0")
			{
				if(parseInt(document.getElementsByName("startHr")[i].value)>parseInt(document.getElementsByName("endHr")[i].value))
				{
					alert("End time can not be less than or equal to start time");
					document.getElementsByName("endHr")[i].focus();
					return false;
				}
				
				if(parseInt(document.getElementsByName("startHr")[i].value)==parseInt(document.getElementsByName("endHr")[i].value))
				{
					if(parseInt(document.getElementsByName("endMin")[i].value)<=parseInt(document.getElementsByName("startMin")[i].value))
					{
						alert("End time can not be less than or equal to start time");
						document.getElementsByName("endHr")[i].focus();
						return false;
					}
				}
				
			}
			
			if(document.getElementsByName("phlebotomyArm")[i].value=="")
			{
				alert("Please Select Phlebotomy Arm");
				document.getElementsByName("phlebotomyArm")[i].focus();
				return false;
			}
			
			if(document.getElementsByName("qtyTransfussed")[i].value=="")
			{
				alert("Please Enter Actual Quantity Transfused");
				document.getElementsByName("qtyTransfussed")[i].focus();
				return false;
			}
			
			
			if(document.getElementsByName("qtyTransfussed")[i].value > parseInt(document.getElementsByName("bagVolume")[i].value))
			{
				alert("Qty Transfuussed cannot be greater than Bag Volume (" + document.getElementsByName("bagVolume")[i].value+ ")");
				document.getElementsByName("qtyTransfussed")[i].focus();
				return false;
			}
		}
	}
	submitPage(mode);
}
function onclickImage(imgObj)
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
</script>
<html>
<body>
	<his:TitleTag name="Blood Transfusion Detail">
	</his:TitleTag>
	
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	
	<his:SubTitleTag name="Previous Transfusion Details">
	<img id="imgPreviousTransfusionDtl" tabindex="1" style="cursor: pointer; vertical-align: middle;" src="/HIS/hisglobal/images/avai/arrow-up.png"; onclick="onclickImage(this)"/>
		</his:SubTitleTag>
		<his:ContentTag>
		<div id="divPreviousTransfusionDtl" style="display: none;">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 			<b>
					 				<bean:message key="dateOfTransfusion" />
					 			</b>
					 		 </font>
					 	 </div>
					  </td>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
					 				<bean:message key="unitNo" />
					 			</b>
					 		</font>
					 	</div>
					</td>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="typeOfComponent" />
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="adverseEffect" />
								</b>
							</font>
						</div>
					</td>
				</tr>
				<logic:present name="<%=InpatientConfig.PREVIOUS_TRANSFUSION_REACTION_DETAIL%>">
				<logic:iterate id="bldTransfusionVO" indexId="idx" name="<%=InpatientConfig.PREVIOUS_TRANSFUSION_REACTION_DETAIL%>" type="hisglobal.vo.BloodTransfusionDtlVO">
				   <%String index=idx.toString(); %>	
				   <tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 			<bean:write name="bldTransfusionVO" property="transfussionDate" />
					 		 </font>
					 	 </div>
					  </td>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="bldTransfusionVO" property="strUnitNo" />
					 		</font>
					 	</div>
					</td>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="bldTransfusionVO" property="componentName" />
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="bldTransfusionVO" property="strAdverseEffect" />
							</font>
						</div>
					</td>
				   </tr>
				</logic:iterate>
				</logic:present>
		</table>
		</div>
		</his:ContentTag>
		
	
	<logic:present name="<%=InpatientConfig.INSTOCK_NOTTRANSFUSED_BLOODBAG_LIST_BYCRNO%>">
	<logic:notEmpty name="<%=InpatientConfig.INSTOCK_NOTTRANSFUSED_BLOODBAG_LIST_BYCRNO%>">
		<his:SubTitleTag name="Bag Available For Transfusion">
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="4%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 			<b>
					 				<bean:message key="select" />
					 			</b>
					 		 </font>
					 	 </div>
					  </td>
					<td width="10%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
					 				<bean:message key="bloodBagNo" />
					 			</b>
					 		</font>
					 	</div>
					</td>
					<td width="21%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="startDate" />
								</b>
							</font>
						</div>
					</td>
					<td width="12%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						 			<b>
						 				<bean:message key="startTime" /><br>
						 				<bean:message key="timeFormat" />
						 			</b>
								</font>
						 </div>
					</td>
					<td width="21%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="endDate" />
								</b>
							</font>
						</div>
					</td>
					<td width="12%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<b>
										<bean:message key="endTime" /><br>
										<bean:message key="timeFormat" />
									</b>
								</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<b>
										<bean:message key="phlebotomyArm" />
									</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="transfussionRate" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" >
						<div align="center">
						<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="qtyTransfussed" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="remark" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
							<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="transfusionReaction" />
							</font>
							</div>
					</td>
				</tr>
				<logic:iterate id="patBloodStockDtlVO" indexId="idx" name="<%=InpatientConfig.INSTOCK_NOTTRANSFUSED_BLOODBAG_LIST_BYCRNO%>" type="hisglobal.vo.PatBloodStockDtlVO">
				<%String index=idx.toString(); %>	
					<tr>
						<td width="4%" class="tdfont" nowrap="nowrap">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<html:checkbox property="selectedBag" name="BloodTransfusionFB" value="<%=index%>" onclick="showCheckedValues(this);">
									</html:checkbox>	
							 	</font>
							</div>
						</td>
						<td width="10%" class="tdfont" nowrap="nowrap">
							<div align="center">
								<a style="cursor: pointer" onclick="getBagDetail(event,'<%=index %>')"> <%=patBloodStockDtlVO.getBloodBagNo() %> </a>
								<html:hidden name="BloodTransfusionFB" property="bloodBagNo" value="<%=patBloodStockDtlVO.getBloodBagNo() %>"/>	
								<html:hidden name="BloodTransfusionFB" property="bloodBagSequenceNo" value='<%=patBloodStockDtlVO.getBloodBagSequenceNo()==null?"":patBloodStockDtlVO.getBloodBagSequenceNo() %>'/>
								<html:hidden name="BloodTransfusionFB" property="bloodBagExpiry" value="<%=patBloodStockDtlVO.getBloodBagExpiry() %>"/>
								<html:hidden name="BloodTransfusionFB" property="bloodGroup" value="<%=patBloodStockDtlVO.getBloodGroup()%>"/>
								<html:hidden name="BloodTransfusionFB" property="componentName" value="<%=patBloodStockDtlVO.getComponentName()%>"/>
								<html:hidden name="BloodTransfusionFB" property="stockBagSerialNo" value="<%=patBloodStockDtlVO.getSerialNo()%>"/>
								<html:hidden name="BloodTransfusionFB" property="stockDate" value="<%=patBloodStockDtlVO.getStockDate()%>"/>
								<html:hidden name="BloodTransfusionFB" property="requisitionNo" value="<%=patBloodStockDtlVO.getRequisitionNo()%>"/>
								<html:hidden name="BloodTransfusionFB" property="bagVolume" value="<%=patBloodStockDtlVO.getBagVolume()%>"/>
								<html:hidden name="BloodTransfusionFB" property="countChk" value="<%=index %>" />
							</div>
						</td>
						<td width="20%" class="tdfont" nowrap="nowrap">
							<div align="center" id="divStartDateId<%=index%>" style="display: none;">
								<his:date name="transfusionStartDate" dateFormate="%d-%b-%Y" value="<%=patBloodStockDtlVO.getTransfusionStartDate()%>" >
								</his:date>
							</div>
						
							<div align="center" id="divStartDateTextId<%=index%>" >
								<html:text property="transfusionStartDateForView" name="BloodTransfusionFB" value="<%=patBloodStockDtlVO.getTransfusionEndDate() %>" readonly="true" size="12">
								</html:text>
							</div>
						</td>
						
						<td width="12%" class="tdfont" nowrap="nowrap">
							<div align="center">
								<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
									<span id='divEndhrcontrol' align="left">	            
				   						<html:text name="BloodTransfusionFB" tabindex="1" property="startHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)"  disabled="true"/>	
				  			 				<b>
				  			 					<bean:message key="colon"/>
				  			 				</b>
								    </span>
				 					<span id='divEndMinControl' align="left">         
										<html:text name="BloodTransfusionFB" tabindex="1" property="startMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)" disabled="true"/>				
									</span>
							 		</font>
							 </div>
						</td>
						<td width="20%" class="tdfont" nowrap="nowrap">
							<div align="center" id="divEndDateId<%=index%>" style="display: none;">
								<his:date name="transfusionEndDate" dateFormate="%d-%b-%Y" value="<%=patBloodStockDtlVO.getTransfusionEndDate() %>">
								</his:date>
							</div>
							<div align="center" id="divEndDateTextId<%=index%>" >
								<html:text property="transfusionEndDateForView" name="BloodTransfusionFB" value="<%=patBloodStockDtlVO.getTransfusionEndDate() %>" readonly="true" size="12"></html:text>
							</div>
						</td>
						<td width="12%" class="tdfont" nowrap="nowrap">
							<div align="center">
								<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
									<span id='divEndhrcontrol' align="left">	            
				   						<html:text name="BloodTransfusionFB" tabindex="1" property="endHr"  maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeHourCheck(this)" disabled="true"/>	
				  			 				<b>
				  			 					<bean:message key="colon"/>
				  			 				</b>
									</span>
					 				<span id='divEndMinControl' align="left">         
										<html:text name="BloodTransfusionFB" tabindex="1" property="endMin"   maxlength="2" size="3"  onkeypress="return validateNumeric(event)" onblur="onblurTimeMinCheck(this)" disabled="true"/>				
									</span>
							 	</font>
							 </div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b> 
										<html:select name="BloodTransfusionFB" property="phlebotomyArm" tabindex="1" disabled="true" >
											<html:option value="">Select</html:option >
											<html:option value="<%=InpatientConfig.PHLEBOTOMY_ARM_LEFT %>">LEFT</html:option >
											<html:option value="<%=InpatientConfig.PHLEBOTOMY_ARM_RIGHT %>">RIGHT</html:option >
										</html:select>
									</b>
								</font>
							 </div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b> 
										<html:text property="transfussionRate" name="BloodTransfusionFB" maxlength="2" size="3" onkeypress="return validateNumeric(event)" disabled="true" tabindex="1"></html:text>
									</b>
								</font>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b> 
										<html:text property="qtyTransfussed" name="BloodTransfusionFB" maxlength="3" onkeypress="return validateNumeric(event)" size="4" disabled="true" tabindex="1" > </html:text>
									</b>
								</font>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b> 
										<html:text property="remarks" name="BloodTransfusionFB" maxlength="500"  size="15" disabled="true" tabindex="1"></html:text>
									</b>
								</font>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b> 
										<html:select name="BloodTransfusionFB" property="isTransfusionReaction" tabindex="1" onchange='<%="openTransfusionReactionPopup(\'"+index+"\')"%>'>
											<html:option value="">Select</html:option >
											<html:option value="<%= InpatientConfig.IS_TRANSFUSION_YES %>" >Yes</html:option >
											<html:option value="<%= InpatientConfig.IS_TRANSFUSION_NO %>" >No</html:option >
										</html:select>
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
	
	<his:ButtonToolBarTag>
		<logic:present name="<%=InpatientConfig.INSTOCK_NOTTRANSFUSED_BLOODBAG_LIST_BYCRNO%>">
		<logic:notEmpty name="<%=InpatientConfig.INSTOCK_NOTTRANSFUSED_BLOODBAG_LIST_BYCRNO%>">
			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex='2' onclick="validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
		</logic:notEmpty>
		</logic:present>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		<logic:present name="<%=InpatientConfig.INSTOCK_NOTTRANSFUSED_BLOODBAG_LIST_BYCRNO%>">
		<logic:notEmpty name="<%=InpatientConfig.INSTOCK_NOTTRANSFUSED_BLOODBAG_LIST_BYCRNO%>">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
		</logic:notEmpty>
		</logic:present>
	</his:ButtonToolBarTag>
<html:hidden name="BloodTransfusionFB" property="hmode" />
<html:hidden name="BloodTransfusionFB" property="patCrNo" />
<html:hidden name="BloodTransfusionFB" property="episodeCode" />
<html:hidden name="BloodTransfusionFB" property="episodeVisitNo" />
<html:hidden name="BloodTransfusionFB" property="admissionNo" />
<html:hidden name="BloodTransfusionFB" property="sysdate" />
<html:hidden name="BloodTransfusionFB" property="selBagNSeqNo" />

</body>
</html>