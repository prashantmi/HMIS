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

<%@page import="inpatient.InpatientConfig"%>
<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
}

function deleteRow(Str)
{	
   var tableObj=document.getElementById('tableComponentDetailId');
   var temp=Str;
   tableObj.deleteRow(temp.rowIndex);
   var index=parseInt(document.getElementsByName('numberOfRow')[0].value);
	document.getElementsByName('numberOfRow')[0].value=index-1;
   return true;
}


function AddRowToTable()
{
	
	var nRow=0;
	var tableObj=document.getElementById('tableComponentDetailId');
	var numRows=tableObj.rows.length;
	//alert(numRows);
	
	if(numRows>2)
	{
		nRow=tableObj.rows[numRows-1].id;
	}
	else
	{
		nRow=numRows;
	}

	
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow)+1;
	var indexVolSpecific=numRows-1;
	// alert("indexVolSpecific "+indexVolSpecific)

	
	var td2=document.createElement("TD");
	var td3=document.createElement("TD");
	var td4=document.createElement("TD");
	var td5=document.createElement("TD");
	var td6=document.createElement("TD");
	var td7=document.createElement("TD");
	var td8=document.createElement("TD");
	var td9=document.createElement("TD");
	var td10=document.createElement("TD");
	
	var componentComboStr=document.getElementsByName("componentCombo")[0].value;
	var aboComboStr=document.getElementsByName("aboCombo")[0].value;
	var rhComboStr=document.getElementsByName("rhCombo")[0].value;

	
	
	td2.innerHTML="<div align='center'>"+"<input type='text' name='bloodBagNo' maxlength='30' size='12' tabindex='1' onkeypress='return validateAlphaNumOnly(this,event)'> </div>";
	td2.className='tdfont';
	td2.colspan="1";
	
	td3.innerHTML="<div align='center'>"+" <input type='text' name='expiryDate' id='expiryDate" + tabRow.id + "' readonly='readonly' value='' width='115px' size='12' >"
	+"<img src='/HISClinical/hisglobal/images/imgDatepicker.png' id='expiryDate1" + tabRow.id + "' style='cursor: pointer; border: 1px solid red;'  	title='Date selector' "
	+"tabindex='1'	onmouseover=\"this.style.background='red';\" onmouseout=\"this.style.background=''\"></div>" ;     // <script language='JavaScript' > "           " Change Made by Pawan Kumar on 04-11-2011"
	// +"Calendar.setup( { inputField     :    'expiryDate', ifFormat       :    '%d-%b-%Y', button  :    'expiryDate1', singleClick    :    true })"+"<"+"/script>";
	td3.className='tdfont';																													
	td3.colspan="1";
	
	td4.innerHTML="<div align='center'>"+"<select name='bloodComponentID' tabindex='1'  >"+componentComboStr+"</select></div>";
	td4.className='tdfont';
	td4.colspan="1";
	
	td5.innerHTML="<div align='center'>"+"<select name='bloodAbo' tabindex='1'  >"+aboComboStr+"</select></div>";
	td5.className='tdfont';
	td5.colspan="1";
	
	td6.innerHTML="<div align='center'>"+"<select name='rh' tabindex='1'  >"+rhComboStr+"</select></div>";
	td6.className='tdfont';
	td6.colspan="1";
	
	td7.innerHTML="<div align='center'>"+"<input type='text' name='volume' maxlength='3' size='3' tabindex='1' onkeypress='return validateNumeric(event)'> </div>";
	td7.className='tdfont';
	td7.colspan="1";
	
	td9.innerHTML="<div align='center'>"+"<input type='text' name='batchNo' maxlength='50' size='12' tabindex='1' onkeypress='return validateAlphaNumOnly(this,event)'> </div>";
	td9.className='tdfont';
	td9.colspan="1";
	
	td10.innerHTML="<div align='center'>"+"<input type='text' name='tubingNo' maxlength='30' size='12' tabindex='1' onkeypress='return validateAlphaNumOnly(this,event)'> </div>";
	td10.className='tdfont';
	td10.colspan="1";
	
	
	
	td8.className='tdfont';
	td8.colspan="1";
	td8.innerHTML="<div align='center'><img src='/HIS/hisglobal/images/avai/minus.gif' onClick='deleteRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"))'></div>";
   
	
	
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	tabRow.appendChild(td4);
	tabRow.appendChild(td5);
	tabRow.appendChild(td6);
	tabRow.appendChild(td7);
	tabRow.appendChild(td9);
	tabRow.appendChild(td10);
	tabRow.appendChild(td8);
	
	Calendar.setup({inputField:'expiryDate'+ tabRow.id, mapkey:'32',ifFormat:'%d-%b-%Y',button:'expiryDate1'+ tabRow.id, singleClick:true});
	
	 document.forms[0].numberOfRow.value=numRows;
	 //alert(numRows);
}

function validateForm(mode)
{
		//alert("inside validate");
		if(document.getElementsByName('bloodBankName')[0].value=="")
		{
			alert("Please Enter Blood Bank Name");
			document.getElementsByName("bloodBankName")[0].focus();
			return false;
		}
		
		if(document.getElementsByName('requisitionNo')[0].value=="")
		{
			alert("Please Select Requisition No");
			document.getElementsByName("requisitionNo")[0].focus();
			return false;
		}
		
		if(document.getElementsByName('bloodBankAddr')[0].value=="")
		{
			alert("Please Enter Blood Bank Address");
			document.getElementsByName("bloodBankAddr")[0].focus();
			return false;
		}
		
		if(document.getElementsByName('contactNo')[0].value=="")
		{
			alert("Please Enter Contact Number");
			document.getElementsByName("contactNo")[0].focus();
			return false;
		}
		
		var numberOfRow=document.getElementsByName('numberOfRow')[0].value
		//alert(numberOfRow);
			for(i=0;i<numberOfRow;i++)
			{
				//alert("inside Loop");
				
				
				if(document.getElementsByName('bloodBagNo')[i].value=="")
				{
					alert("Please Enter Blood Bag No.");
					document.getElementsByName("bloodBagNo")[i].focus();
					return false;	
				}
				
				if(document.getElementsByName('expiryDate')[i].value=="")
				{
					alert("Please Enter Expiry Date");
					document.getElementsByName("expiryDate")[i].focus();
					return false;	
				}
				
				if(document.getElementsByName('bloodComponentID')[i].value=="" || document.getElementsByName('bloodComponentID')[i].value=="-1")
				{
					alert("Please Select Blood Component");
					document.getElementsByName("bloodComponentID")[i].focus();
					return false;
				}
				
				if(document.getElementsByName('bloodAbo')[i].value=="" || document.getElementsByName('bloodAbo')[i].value=="-1")
				{
					alert("Please Select Blood ABO");
					document.getElementsByName("bloodAbo")[i].focus();
					return false;
				}
				
				if(document.getElementsByName('rh')[i].value=="" || document.getElementsByName('rh')[i].value=="-1")
				{
					alert("Please Select RH");
					document.getElementsByName("rh")[i].focus();
					return false;
				}
				
				if(document.getElementsByName('volume')[i].value=="")
				{
					alert("Please Enter Volume");
					document.getElementsByName("volume")[i].focus();
					return false;	
				}
			}	
		//alert("save");	
		submitForm(mode);			
}
</script>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="registration.RegistrationConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<his:TitleTag name="Stock Entry Of Blood">
	</his:TitleTag>
	
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	
	<logic:present name="<%=InpatientConfig.ALL_REQUESTED_COMPONENT_LIST_BY_CRNO%>">
	<logic:notEmpty name="<%=InpatientConfig.ALL_REQUESTED_COMPONENT_LIST_BY_CRNO%>">
		
		<his:SubTitleTag name="Previous Requisition Detail">
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="requisitionNo" />
								
							</font>
						</div>
					</td>
					
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="component" />
								
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="requirmentDate" />
								
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="unitDemanded" />
								
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="noOfUnitInStock" />
								
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="status" />
								
							</font>
						</div>
					</td>
				</tr>
				<logic:iterate id="bloodRequisitionComponentDtlVO" indexId="idx" name="<%=InpatientConfig.ALL_REQUESTED_COMPONENT_LIST_BY_CRNO%>" type="hisglobal.vo.BloodRequisitionComponentDtlVO">
					<tr>
						<td width="15%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="bloodRequisitionComponentDtlVO" property="requisitionNo" />
									</b>
								</font>
							</div>
						</td>
						
						<td width="20%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="bloodRequisitionComponentDtlVO" property="bloodComponentName" />
									</b>
								</font>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="bloodRequisitionComponentDtlVO" property="requirementDate" />
									</b>
								</font>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="bloodRequisitionComponentDtlVO" property="noOfUnitDemanded" />
									</b>
								</font>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<%
											if(bloodRequisitionComponentDtlVO.getNoOfUnitAccepted()!=null)
											{
										%>		
												<bean:write name="bloodRequisitionComponentDtlVO" property="noOfUnitAccepted" />
										<%
											}
											else
											{
										%>
										-
										<%
											}
										%>
									</b>
								</font>
							</div>
						</td>
						
										
										
	
						
						<td width="15%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="bloodRequisitionComponentDtlVO" property="componentReqStatus" />
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

		<%
			List lst1=(List)session.getAttribute(InpatientConfig.INSTOCK_BLOODBAG_LIST_BYCRNO);
			List lst2=(List)session.getAttribute(InpatientConfig.CROSS_MATCH_LIST_BY_CRNO);
			if((lst1!=null || lst2!=null) && (lst1.size()!=0 || lst2.size()!=0))
			{
		%>
		<his:SubTitleTag name="Bag Detail">
		</his:SubTitleTag>
		<%
			}
		%>
		<logic:present name="<%=InpatientConfig.INSTOCK_BLOODBAG_LIST_BYCRNO%>">
		<logic:notEmpty name="<%=InpatientConfig.INSTOCK_BLOODBAG_LIST_BYCRNO%>">
		<his:ContentTag>
			
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr bgcolor="#FFB468">
					<td colspan="7" style="background-color:#FFEBD6;">
					<b>
					Bag Available/Transfused
					</b>
					</td>
				</tr>
				</table>
				</his:ContentTag>
				
				<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="16%" class="tdfonthead">
						<div align="center">.
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="requisitionNo" />
								
							</font>
						</div>
					</td>
					<td width="16%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="bloodBagNo" />
								
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="bloodGroup" />
								
							</font>
						</div>
					</td>
					<td width="16%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="component" />
								
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="expiryDate" />
								
							</font>
						</div>
					</td>
					<td width="8%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="status" />
								
							</font>
						</div>
					</td>
					<td width="16%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="source" />
								
							</font>
						</div>
					</td>
					
				</tr>
				</table>
				</his:ContentTag>
				<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<logic:iterate id="patBloodStockDtlVO" indexId="idx" name="<%=InpatientConfig.INSTOCK_BLOODBAG_LIST_BYCRNO%>" type="hisglobal.vo.PatBloodStockDtlVO">
					<tr>
						<td width="16%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="patBloodStockDtlVO" property="requisitionNo" />
									</b>
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="patBloodStockDtlVO" property="bloodBagNo" />
									</b>
								</font>
							</div>
						</td>
						<td width="8%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="patBloodStockDtlVO" property="bloodGroup" />
									</b>
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="patBloodStockDtlVO" property="componentName" />
									</b>
								</font>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="patBloodStockDtlVO" property="bloodBagExpiry" />
									</b>
								</font>
							</div>
						</td>
						<td width="8%" class="tdfont">
							<div align="center">
								<%
								if(patBloodStockDtlVO.getStockStatus().equals(InpatientConfig.IN_STOCK))
								{
								%>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="patBloodStockDtlVO" property="transfusionStatus" />
									</b>
								</font>
								<%
									}
									else
									{
								%>
								<font color="#ff0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="patBloodStockDtlVO" property="transfusionStatus" />
									</b>
								</font>
								<%
									}
								 %>
								
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="patBloodStockDtlVO" property="sourceFlag" />
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
			
			
			<logic:present name="<%=InpatientConfig.CROSS_MATCH_LIST_BY_CRNO%>">
			<logic:notEmpty name="<%=InpatientConfig.CROSS_MATCH_LIST_BY_CRNO%>">
			<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr bgcolor="#FFB468">
					<td colspan="5" style="background-color:#FFEBD6;">
					<b>
					CrossMatched Bag
					</b>
					</td>
				</tr>
				</table>
			</his:ContentTag>	
				<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="20%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="requisitionNo" />
								
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="bloodBagNo" />
								
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="bloodGroup" />
								
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="component" />
								
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									<bean:message key="expiryDate" />
								
							</font>
						</div>
					</td>
				</tr>
				</table>
				</his:ContentTag>
				<his:ContentTag>
				<logic:iterate id="patBloodStockDtlVO" indexId="idx" name="<%=InpatientConfig.CROSS_MATCH_LIST_BY_CRNO%>" type="hisglobal.vo.PatBloodStockDtlVO">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="20%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="patBloodStockDtlVO" property="requisitionNo" />
									</b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="patBloodStockDtlVO" property="bloodBagNo" />
									</b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="patBloodStockDtlVO" property="bloodGroup" />
									</b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="patBloodStockDtlVO" property="componentName" />
									</b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:write name="patBloodStockDtlVO" property="bloodBagExpiry" />
									</b>
								</font>
							</div>
						</td>
					</tr>
					</table>
				</logic:iterate>
			
	</his:ContentTag>
	</logic:notEmpty>	
	</logic:present>
		
		
	
	<logic:notEqual value="<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>" property="patDeathStatus" name="StockEntryOfBloodFB">
	<his:SubTitleTag name="External Bag Entry">
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font>
								
									<bean:message key="bloodBankName" />
								
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont" nowrap="nowrap">
				<div align="left">
				<html:text name="StockEntryOfBloodFB"
					property="bloodBankName" value="" size="20" tabindex="1"
					maxlength="100"
					onkeypress="return validateAlphaNumOnly(this,event)" /></div>
				</td>
				<td width="25%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <font
					color="#FF0000">*</font> <bean:message key="requisitionNo" />
				</font></div>
				</td>
				<td width="25%" class="tdfont" nowrap="nowrap">
				<div align="left"><html:select name="StockEntryOfBloodFB"
					property="requisitionNo" tabindex="1">
					<%
						List lst = (List) session
											.getAttribute(InpatientConfig.REQUISITION_NO_COMBO_LIST);
						
						if(lst==null)
						{
							lst=new ArrayList();
						}
						if (lst.size() > 1) {
					%>
					<html:option value="">Select Value</html:option>
					<%
						}
					%>
					<logic:present
						name="<%=InpatientConfig.REQUISITION_NO_COMBO_LIST %>">
						<html:options
							collection="<%=InpatientConfig.REQUISITION_NO_COMBO_LIST%>"
							property="value" labelProperty="label" />
					</logic:present>
				</html:select></div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <font
					color="#FF0000">*</font> <bean:message key="bloodBankAddr" />
				</font></div>
				</td>
				<td width="25%" class="tdfont" nowrap="nowrap">
				<div align="left"><html:text name="StockEntryOfBloodFB"
					property="bloodBankAddr" value="" size="20" tabindex="1"
					maxlength="100"
					onkeypress="return validateAlphaNumOnly(this,event)" /></div>
				</td>
				<td width="25%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <font
					color="#FF0000">*</font> <bean:message key="conctactNo" /> </font>
				</div>
				</td>
				<td width="25%" class="tdfont" nowrap="nowrap">
				<div align="left"><html:text name="StockEntryOfBloodFB"
					property="contactNo" value="" size="12" tabindex="1"
					maxlength="20" onkeypress="return validateNumeric(event)" /></div>
				</td>

			</tr>

		</table>
		<table width="100%" id="tableComponentDetailId" cellspacing="1"
			cellpadding="0">
			<tr>
				<td width="10%" class="tdfonthead" nowrap="nowrap">
				<div align="center"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font
					color="#FF0000">*</font> <bean:message key="bloodBagNo" /> </font></div>
				</td>
				<td width="6%" class="tdfonthead" nowrap="nowrap">
				<div align="center"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font
					color="#FF0000">*</font> <bean:message key="expiryDate" /></font></div>
				</td>
				<td width="10%" class="tdfonthead" nowrap="nowrap">
				<div align="center"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font
					color="#FF0000">*</font> <bean:message key="component" /> </font></div>
				</td>
				<td width="5%" class="tdfonthead" nowrap="nowrap">
				<div align="center"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <font
					color="#FF0000">*</font><bean:message key="bloodabo" /> </font></div>
				</td>
				<td width="5%" class="tdfonthead" nowrap="nowrap">
				<div align="center"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <font
					color="#FF0000">*</font><bean:message key="rh" /> </font></div>
				</td>
				<td width="5%" class="tdfonthead" nowrap="nowrap">
				<div align="center"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font
					color="#FF0000">*</font> <bean:message key="volume" /> </font></div>
				</td>
				<td width="10%" class="tdfonthead" nowrap="nowrap">
				<div align="center"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message
					key="batchNo" /> </font></div>
				</td>
				<td width="10%" class="tdfonthead" nowrap="nowrap">
				<div align="center"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="tubingNo" /> </font></div>
				</td>
				<td class="tdfonthead"></td>
			</tr>
			<tr>
				<td width="10%" class="tdfont" nowrap="nowrap">
				<div align="center"><html:text name="StockEntryOfBloodFB"
					property="bloodBagNo" value="" size="12" tabindex="1"
					maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" /></div>
				</td>
				<td width="6%" class="tdfont" nowrap="nowrap">
				<div align="center"><his:date name="expiryDate"
					dateFormate="%d-%b-%Y"></his:date></div>
				</td>
				<td width="10%" class="tdfont" nowrap="nowrap">
				<div align="center"><html:select name="StockEntryOfBloodFB"
					property="bloodComponentID" tabindex="1">
					<html:option value="">Select Value</html:option>
					<logic:present
						name="<%=InpatientConfig.COMPONENT_LIST_FOR_COMBO %>">
						<html:options
							collection="<%=InpatientConfig.COMPONENT_LIST_FOR_COMBO%>"
							property="value" labelProperty="label" />
					</logic:present>
				</html:select></div>
				</td>
				<td width="5%" class="tdfont" nowrap="nowrap">
				<div align="center"><html:select name="StockEntryOfBloodFB"
					property="bloodAbo" tabindex="1">
					<html:option value="">Select</html:option>
					<logic:present name="<%=InpatientConfig.ALL_ABO_LIST %>">
						<html:options collection="<%=InpatientConfig.ALL_ABO_LIST%>"
							property="value" labelProperty="label" />
					</logic:present>
				</html:select></div>
				</td>
				<td width="5%" class="tdfont" nowrap="nowrap">
				<div align="center"><html:select name="StockEntryOfBloodFB"
					property="rh" tabindex="1">
					<html:option value="">Select</html:option>
					<logic:present name="<%=InpatientConfig.ALL_RH_LIST %>">
						<html:options collection="<%=InpatientConfig.ALL_RH_LIST%>"
							property="value" labelProperty="label" />
					</logic:present>
				</html:select></div>
				</td>
				<td width="5%" class="tdfont" nowrap="nowrap">
				<div align="center"><html:text name="StockEntryOfBloodFB"
					property="volume" value="" size="3" tabindex="1" maxlength="3"
					onkeypress="return validateNumeric(event)" /></div>
				</td>
				<td width="10%" class="tdfont" nowrap="nowrap">
				<div align="center"><html:text name="StockEntryOfBloodFB"
					property="batchNo" value="" size="12" tabindex="1"
					maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" /></div>
				</td>
				<td width="10%" class="tdfont" nowrap="nowrap">
				<div align="center"><html:text name="StockEntryOfBloodFB"
					property="tubingNo" value="" size="12" tabindex="1" 
					maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" /></div>
				</td>
				<td class="tdfont">
				<div align="center"><img class="button" id="addButton"
					style="cursor: pointer"
					src='<his:path src="/hisglobal/images/plus.gif"/>'
					alt="Add Component" title="Add Component"
					onkeypress="if(event.keyCode==13)if(true) AddRowToTable() ;"
					onclick="if(true)AddRowToTable()"></div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	</logic:notEqual>
	<his:ButtonToolBarTag>
		<logic:notEqual value="<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>" property="patDeathStatus" name="StockEntryOfBloodFB">
			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex='2' onclick="validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
		</logic:notEqual>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		<logic:notEqual value="<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>" property="patDeathStatus" name="StockEntryOfBloodFB">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
		</logic:notEqual>
	</his:ButtonToolBarTag>
<html:hidden name="StockEntryOfBloodFB" property="hmode" />
<html:hidden name="StockEntryOfBloodFB" property="patCrNo" />
<html:hidden name="StockEntryOfBloodFB" property="componentCombo" />
<html:hidden name="StockEntryOfBloodFB" property="aboCombo" />
<html:hidden name="StockEntryOfBloodFB" property="rhCombo" />
<html:hidden name="StockEntryOfBloodFB" property="numberOfRow" value="1" />

<logic:equal value="<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>" property="patDeathStatus" name="StockEntryOfBloodFB">
<table>
	<tr>
		<td align="left">
			<font color="#FF0000" size="2">	
				<b>Patient is dead.</b>
			</font>	
		</td>
	</tr>
</table>
</logic:equal>

</body>
</html>



