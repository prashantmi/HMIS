<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.*" %>
<%@page import="java.util.Date"%>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>

<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/datepicker1.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

function closeForm()
{
	self.close();
}
function submitPage(mode)
{
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit();
}

function showDateWise()
{
	
	document.getElementById("dateWiseTrId").style.display="";
	document.getElementById("drugWiseTrId").style.display="none";
	submitPage('DRUGGIVEN');
}

function showDrugwise()
{
	document.getElementById("dateWiseTrId").style.display="none";
	document.getElementById("drugWiseTrId").style.display="";
	submitPage('DRUGGIVEN');
}

function showDrugOrDateWise()
{
	//var choice=document.getElementsByName('viewTreatAdminChoice')[0].value;
	//alert("choice "+choice);
	if(document.getElementsByName('viewTreatAdminChoice')[0].checked)
	{
		document.getElementById("dateWiseTrId").style.display="";
		document.getElementById("drugWiseTrId").style.display="none";
	}
	else
	{
		
		document.getElementById("dateWiseTrId").style.display="none";
		document.getElementById("drugWiseTrId").style.display="";
	}
	
}
function checkDate_from()
{
	var valid=true;
	var days=0;
	var a=document.getElementsByName("treatFromDate")[0].value;
	var aArray=a.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	var b=document.getElementsByName("sysDate")[0].value; 
	//var b=new Date;
	var bArray=b.split("-");
	var bday=bArray[0];
	var bmonth=bArray[1];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);
	days=((bdate-adate)/86400000);
	return days;
}

function checkDate()
{
	var valid=true;
	var days=0;
	var a=document.getElementsByName("treatFromDate")[0].value;
	var aArray=a.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	var b=document.getElementsByName("admissionDate")[0].value; 
	//var b=new Date;
	var bArray=b.split("-");
	var bday=bArray[0];
	var bmonth=bArray[1];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);
	days=((bdate-adate)/86400000);
	return days;
}

function checkDate_toDate()
{
	var valid=true;
	var days=0;
	var a=document.getElementsByName("treatFromDate")[0].value;
	var aArray=a.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	var b=document.getElementsByName("treatToDate")[0].value; 
	//var b=new Date;
	var bArray=b.split("-");
	var bday=bArray[0];
	var bmonth=bArray[1];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);
	days=((bdate-adate)/86400000);
	return days;
}

function validateDrugWiseForm(mode)
{
	if(document.getElementsByName("selDrugID")[0].value=="-1")
	{
		alert("Please select drug");
		document.getElementsByName("selDrugID")[0].focus();
		return false;
	}
	
	submitPage(mode);
	
}
function validateForm(mode)
{
	if(checkDate()>0)
	{
		var admissionDate=document.getElementsByName("admissionDate")[0].value;
		alert("From Date can not be less than "+ admissionDate + " (Admission Date)");
		document.getElementsByName("treatFromDate")[0].focus();
		return false;
	}
	if(checkDate_from()<0)
	{
		alert("From Date can not be greater than Today Date");
		document.getElementsByName("treatFromDate")[0].focus();
		return false;
	}
	if(checkDate_toDate()<0)
	{
		alert("To Date can not be greater than From Date ");
		document.getElementsByName("treatFromDate")[0].focus();
		return false;
	}
	
	
	submitPage(mode);
}

function viewRemarks(obj)
{
	document.getElementsByName("showRemarks")[0].value=obj;
	document.getElementById("divViewRemarks").style.display="block"; 
	//document.getElementById("divViewSchidule").style.display="none";
	
}

</script>

<html:form action="/nurDrugAdministration">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body onload="showDrugOrDateWise()">
		<his:TitleTag name="Drug Given">
		</his:TitleTag>
		
		
		<his:ContentTag>
		<table width="100%"  cellspacing="1" cellpadding="0">  
			<tr>
				<td class="tdfonthead" width="33%" nowrap="nowrap" >
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 		 	<bean:message key="viewTreatmentAdministration" />
		         	</font>
		    		</div>
		   		 </td>
		   		 <td class="tdfont" nowrap width="65%">       	      
		    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      		<bean:message key="dateWise"/>
		      	</font>
		      		<html:radio name="DrugAdministrationFB" property="viewTreatAdminChoice" tabindex="1" value="<%=InpatientConfig.DATEWISE_TREAT_INFO %>" onclick="showDateWise('DRUGGIVEN')"/> 
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="drugWise"/></font> 
		        	<html:radio name="DrugAdministrationFB" property="viewTreatAdminChoice" tabindex="1" value="<%=InpatientConfig.DRUGWISE_TREAT_INFO %>" onclick="showDrugwise('DRUGGIVEN')"/>
			</td> 	
			</tr>
			</table>
			<table width="100%"  cellspacing="1" cellpadding="0"> 
			<tr id="dateWiseTrId">
				 <td class="tdfonthead" width="17%" nowrap="nowrap">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 		 	<bean:message key="fromDate"/>
		         	</font>
		    		</div>
		   		 </td>
				     	 
            	 <td width="17%" class="tdfont" nowrap="nowrap">
					<div align="left">
					<bean:define id="treatFromDate" name="DrugAdministrationFB" property="treatFromDate" type="java.lang.String" ></bean:define>
					<%
					if(treatFromDate==null||treatFromDate.equalsIgnoreCase(""))
					{ 
						treatFromDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-Mon-yyyy");
					}
					%>
					<his:date name="treatFromDate"  dateFormate="%d-%b-%Y" value="<%=treatFromDate%>"  ></his:date>
					</div>
				</td >
				
				 <td class="tdfonthead" width="17%" nowrap="nowrap">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 		 	<bean:message key="toDate"/>
		         	</font>
		    		</div>
		   		 </td>
				     	 
            	 <td width="17%" class="tdfont" nowrap="nowrap">
					<div align="left">
					<bean:define id="treatToDate" name="DrugAdministrationFB" property="treatToDate" type="java.lang.String" ></bean:define>
					<%
					if(treatToDate==null||treatToDate.equalsIgnoreCase(""))
					{ 
						treatToDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-Mon-yyyy");
					}
					%>
					<his:date name="treatToDate"  dateFormate="%d-%b-%Y" value="<%=treatToDate%>"  ></his:date>
					</div>
				</td >
				<td width="66%"  class="tdfonthead" nowrap="nowrap">
            	 	<div align="left" >
            	 		<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' style="cursor: pointer" onclick="validateForm('DRUGGIVEN');" onkeypress="if(event.keyCode==13) validateForm('DRUGGIVEN')">
					</div>	
					</td>
			</tr>
			<tr id="drugWiseTrId">
				<td class="tdfonthead" width="17%" nowrap="nowrap">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 		 	<bean:message key="drug"/>
		         	</font>
		    		</div>
		   		 </td>
		   		 <td width="16%" class="tdfont" nowrap="nowrap"  >
					<div align="left" >
						<html:select name="DrugAdministrationFB" property="selDrugID" tabindex="1" onchange="sendDataForDrugRouteList(this)" > 
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.EXECUTED_DRUG_LST_BY_CRNO%>">
							<html:options collection="<%=InpatientConfig.EXECUTED_DRUG_LST_BY_CRNO%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select></div></td>
            	 <td width="66%"  class="tdfonthead" nowrap="nowrap">
            	 	<div align="left" >
            	 		<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' style="cursor: pointer" onclick="validateDrugWiseForm('DRUGGIVEN');" onkeypress="if(event.keyCode==13) validateDrugWiseForm('DRUGGIVEN')">
					</div>	
					</td>
					
			</tr>
		</table>
		<logic:present name="<%=InpatientConfig.ESSENTIALS_DRUG_EXEC_LIST_BY_CRNO %>">
		<logic:notEmpty name="<%=InpatientConfig.ESSENTIALS_DRUG_EXEC_LIST_BY_CRNO %>">
		<logic:equal value="<%=InpatientConfig.DATEWISE_TREAT_INFO %>" name="DrugAdministrationFB" property="viewTreatAdminChoice">
		<table width="100%" id="drugExecutionDetailId" cellspacing="1" cellpadding="0">  
			 <tr>
			 	<td width="7%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 <b>
						 	<bean:message key="administrationDate" />
						 	</b>
						</font>
					</div>
				 </td>
				 <td width="10%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="time(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="16%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="drug" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="16%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="drugBrand" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="10%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="drugRoute" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="volume" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="5%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="batchNo" />
							</b>
					 	</font>
					</div>
				 </td>
				 <td width="8%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 <b>
						 	<bean:message key="expiryDate" />
						 	</b>
						</font>
					</div>
				 </td>
				<td width="16%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
					 		<bean:message key="remark" />
					 		</b>
					 	</font>
					</div>
				</td>
			</tr>	
			<logic:iterate id="drugAdminDtlVO" indexId="i" name="<%=InpatientConfig.ESSENTIALS_DRUG_EXEC_LIST_BY_CRNO %>" type="hisglobal.vo.DrugAdminDtlVO">
			<tr>
				<td class="tdfont" width="7%" >
					<div align="center">
						<%=drugAdminDtlVO.getAdministrationDate() %>
					</div>
				</td>
				<td class="tdfont" width="10%" >
					<div align="center">
						<%=drugAdminDtlVO.getAdministrationTime() %>
						<html:hidden name="DrugAdministrationFB" property="prevSelDrufExecTimeArray" value='<%=drugAdminDtlVO.getAdministrationTime()+"#"+drugAdminDtlVO.getItemId() %>'/>
						
					</div>
				</td>
				<td class="tdfont" width="16%" >
					<div align="center">
						<%=drugAdminDtlVO.getItemName() %>
						<html:hidden name="DrugAdministrationFB" property="prevSelDrugIdArray" value='<%=drugAdminDtlVO.getItemId()+"#"+drugAdminDtlVO.getItemName() %>'/>
					</div>
				</td>
				
				<td class="tdfont" width="16%" >
					<div align="center">
						<%=drugAdminDtlVO.getDrugBrandName() %>
						<html:hidden name="DrugAdministrationFB" property="prevSelDrugIdArray" value='<%=drugAdminDtlVO.getItemId()+"#"+drugAdminDtlVO.getItemName() %>'/>
					</div>
				</td>
				
				<td class="tdfont" width="16%" >
					<div align="center">
						<%=drugAdminDtlVO.getDrugRouteName() %>
					</div>
				</td>
				<td class="tdfont" width="16%" >
					<div align="center">
						<%
							if(drugAdminDtlVO.getVolume()==null)
							{
						%>
						-
						<%		
							}
							else
							{
						%>
						<bean:write name="drugAdminDtlVO" property="volume"/>
						<%		
							}
						%>
					</div>
				</td>
				
				<td class="tdfont" width="10%" >
					<div align="center">
						<%=drugAdminDtlVO.getBatchNo() %>
					</div>
				</td>
				<td class="tdfont" width="10%" >
					<div align="center">
						<%=drugAdminDtlVO.getExpiryDate() %>
					</div>
				</td>
				<td class="tdfont" width="10%" >
							<div align="center">
								<% String remOnclick = "viewRemarks('"+((drugAdminDtlVO.getRemarks()==null)?"No Remark":drugAdminDtlVO.getRemarks())+"')"; %>
								<a style="cursor:pointer" onclick="<%=remOnclick%>" >
								VIEW
								</a>
							</div>
						</td>
				
			</tr>		
		</logic:iterate> 
		 </table> 
		</logic:equal> 
		 </logic:notEmpty>	 
		</logic:present>
		
		<logic:present name="<%=InpatientConfig.ESSENTIALS_DRUG_EXEC_LIST_DRUGWISE %>">
		<logic:notEmpty name="<%=InpatientConfig.ESSENTIALS_DRUG_EXEC_LIST_DRUGWISE %>">
		<logic:equal value="<%=InpatientConfig.DRUGWISE_TREAT_INFO %>" name="DrugAdministrationFB" property="viewTreatAdminChoice">
		<table width="100%" id="drugExecutionDetailId" cellspacing="1" cellpadding="0">  
			 <tr>
				<td width="16%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="drug" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="16%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="drugBrand" />
							</b>
						</font>
					</div>
				</td>
				
				
				<td width="10%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="drugRoute" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="volume" />
							</b>
						</font>
					</div>
				</td>
				 <td width="7%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 <b>
						 	<bean:message key="administrationDate" />
						 	</b>
						</font>
					</div>
				 </td>
				<td width="10%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="time(HrsMin)" />
							</b>
						</font>
					</div>
				</td>
				<td width="5%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="batchNo" />
							</b>
					 	</font>
					</div>
				 </td>
				
				<td width="10%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 <b>
						 	<bean:message key="expiryDate" />
						 	</b>
						</font>
					</div>
				 </td>
				<td width="16%" class="tdfonthead" nowrap="nowrap" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
					 		<bean:message key="remark" />
					 		</b>
					 	</font>
					</div>
				</td>
			</tr>	
			<logic:iterate id="drugAdminDtlVO" indexId="ind" name="<%=InpatientConfig.ESSENTIALS_DRUG_EXEC_LIST_DRUGWISE %>" type="hisglobal.vo.DrugAdminDtlVO">
			<tr>
				<td class="tdfont" width="16%" >
					<div align="center">
						<%=drugAdminDtlVO.getItemName() %>
						
					</div>
				</td>
				
				<td class="tdfont" width="16%" >
					<div align="center">
						<%=drugAdminDtlVO.getDrugBrandName() %>
						<html:hidden name="DrugAdministrationFB" property="prevSelDrugIdArray" value='<%=drugAdminDtlVO.getItemId()+"#"+drugAdminDtlVO.getItemName() %>'/>
					</div>
				</td>
				
				<td class="tdfont" width="10%" >
					<div align="center">
						<%=drugAdminDtlVO.getDrugRouteName() %>
					</div>
				</td>
				<td class="tdfont" width="10%" >
					<div align="center">
						<%
							if(drugAdminDtlVO.getVolume()==null)
							{
						%>
						-
						<%		
							}
							else
							{
						%>
						<bean:write name="drugAdminDtlVO" property="volume"/>
						<%		
							}
						%>
						
					</div>
				</td>
				<td class="tdfont" width="7%" >
					<div align="center">
						<%=drugAdminDtlVO.getAdministrationDate() %>
					</div>
				</td>
				<td class="tdfont" width="10%" >
					<div align="center">
						<%=drugAdminDtlVO.getAdministrationTime() %>
						<html:hidden name="DrugAdministrationFB" property="prevSelDrufExecTimeArray" value='<%=drugAdminDtlVO.getAdministrationTime()+"#"+drugAdminDtlVO.getItemId() %>'/>
						
					</div>
				</td>
				<td class="tdfont" width="10%" >
					<div align="center">
						<%=drugAdminDtlVO.getBatchNo() %>
					</div>
				</td>
				
				<td class="tdfont" width="10%" >
					<div align="center">
						<%=drugAdminDtlVO.getExpiryDate() %>
					</div>
				</td>
				<td class="tdfont" width="10%" >
							<div align="center">
								<% String remOnclick1 = "viewRemarks('"+((drugAdminDtlVO.getRemarks()==null)?"No Remark":drugAdminDtlVO.getRemarks())+"')"; %>
								<a style="cursor:pointer" onclick="<%=remOnclick1%>" >
								VIEW
								</a>
							</div>
						</td>
			</tr>		
		</logic:iterate> 
		 </table> 
		 </logic:equal>
		 </logic:notEmpty>	 
		</logic:present>
		</his:ContentTag> 
		
		
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
        
        <html:hidden name="DrugAdministrationFB" property="hmode"/>
        <html:hidden name="DrugAdministrationFB" property="patCrNo"/>
         <html:hidden name="DrugAdministrationFB" property="viewTreatAdminChoice"/>
        <html:hidden name="DrugAdministrationFB" property="admissionDate"/>
        <html:hidden name="DrugAdministrationFB" property="sysDate"/>
    </body>	
    <div id="divViewRemarks" style="display: none;">
       			<his:SubTitleTag name="Remark">
       			</his:SubTitleTag>
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="DrugAdministrationFB" property="showRemarks" rows="5" cols="130" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>
		<his:status/>	
</html:form>
