<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="mrd.MrdConfig"%>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.transaction.controller.fb.CaseSheetEnquiryFB"%>
<%@page import="hisglobal.vo.CaseSheetDtlVO"%>
<%@page import="mrd.vo.CommonCaseSheetEnquiryVO"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script>

function submitPage(mode){
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function callThisOnload(){	
	unknownChecked();
	
}


function unknownChecked()
{
	 
	if(document.getElementsByName("enquiryType")[0].checked)
	{	 
		if(document.getElementsByName("hrgnum_isunknown")[0].checked)
		{
				
			document.getElementsByName("hrgstr_fname")[0].disabled=true;
			document.getElementsByName("hrgstr_mname")[0].disabled=true;
			document.getElementsByName("hrgstr_lname")[0].disabled=true;
			document.getElementsByName("hrgnum_puk")[0].disabled=true;
			document.getElementsByName("hipnum_admno")[0].disabled=true;
			document.getElementsByName("hrgnum_mlc_no")[0].disabled=true;
			document.getElementsByName("hipdt_disstatus_code")[0].disabled=true;
			
		}
		else
		{
			document.getElementsByName("hrgstr_fname")[0].disabled=false;
			document.getElementsByName("hrgstr_mname")[0].disabled=false;
			document.getElementsByName("hrgstr_lname")[0].disabled=false;
			document.getElementsByName("hrgnum_puk")[0].disabled=false;
			document.getElementsByName("hipnum_admno")[0].disabled=false;
			document.getElementsByName("hrgnum_mlc_no")[0].disabled=false;
			document.getElementsByName("hipdt_disstatus_code")[0].disabled=false;
			
		}
	}
}

function isBroughtDeadChecked()
{
	 
	if(document.getElementsByName("hrgnum_is_broughtdead")[0].checked && document.getElementsByName("hrgnum_isunknown")[0].checked)
	{
		
		document.getElementsByName("hrgstr_fname")[0].disabled=true;
		document.getElementsByName("hrgstr_mname")[0].disabled=true;
		document.getElementsByName("hrgstr_lname")[0].disabled=true;
		document.getElementsByName("hrgnum_puk")[0].disabled=true;
		document.getElementsByName("hipnum_admno")[0].disabled=true;
		document.getElementsByName("hrgnum_mlc_no")[0].disabled=true;
		document.getElementsByName("hipdt_disstatus_code")[0].disabled=true;
		
	}
	else if(!document.getElementsByName("hrgnum_isunknown")[0].checked)
	{
		document.getElementsByName("hrgstr_fname")[0].disabled=false;
		document.getElementsByName("hrgstr_mname")[0].disabled=false;
		document.getElementsByName("hrgstr_lname")[0].disabled=false;
		document.getElementsByName("hrgnum_puk")[0].disabled=false;
		document.getElementsByName("hipnum_admno")[0].disabled=false;
		document.getElementsByName("hrgnum_mlc_no")[0].disabled=false;
		document.getElementsByName("hipdt_disstatus_code")[0].disabled=false;
		
	}
}

function getDischargeDetail(obj)
{
	document.getElementsByName("selectedIndex")[0].value=obj;
	submitPage('GETDISCHARGEDTL');
}

function viewPrintProfile(e,profileStatus,url)
{
	var status="<%=OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED%>";
	
	
		openDependentPopup(createFHashAjaxQuery(url),e,700,700,true);
	
}

function getOrderBy(mode,order){
	document.getElementsByName("hmode")[0].value=mode;
	document.getElementsByName("order")[0].value=order;
	document.forms[0].submit();
}

function validateCaseSheetEnquiry()
{
	var valid =false;
	
	if(document.getElementsByName("enquiryType")[0].checked)
	{
		// alert("inside enq")
			if(document.getElementsByName("hrgstr_fname")[0].value!=""||
			document.getElementsByName("hrgstr_mname")[0].value!="" ||
			document.getElementsByName("hrgstr_lname")[0].value!="" ||
			document.getElementsByName("hrgnum_puk")[0].value!="" ||
			document.getElementsByName("hipnum_admno")[0].value!="" ||
			document.getElementsByName("hrgnum_mlc_no")[0].value!="" ||
			document.getElementsByName("hrgnum_isunknown")[0].checked ||
			document.getElementsByName("hrgnum_is_broughtdead")[0].checked||
			document.getElementsByName("hipdt_disstatus_code")[0].value!="-1" ||
			document.getElementsByName("fromDate")[0].value!="" ||
			document.getElementsByName("toDate")[0].value!="")
			
			{
				// alert("ddd"+valid)
				
				if(document.getElementsByName("hrgnum_puk")[0].value!="")
				{
					//cr  format size
					valid=validateCrCheck('<%=Config.CR_NO_FORMAT_SIZE%>'); 
					// alert("1123"+valid)
				}
				else
				{
					valid=true;
				}	
				
			}
			
			else
			{
				
				alert("Please Enter Value In Atleast One Field")
			}
	}
	if(document.getElementsByName("enquiryType")[1].checked)
	{
			if(document.getElementsByName("hgnum_pat_alert_id")[0].value!="-1")
			{
				valid=true;
			}
			else
			{
				alert("Please Enter Value In Atleast One Field")
			}
	}
	if(document.getElementsByName("enquiryType")[2].checked)
	{
			if(document.getElementsByName("hgnum_allergy_type_code")[0].value!="-1")
			{
				valid=true;
			}
			else
			{
				alert("Please Enter Value In Atleast One Field")
			}
	}
	if(document.getElementsByName("enquiryType")[3].checked)
	{
			if(document.getElementsByName("icdCode")[0].value!="")
			{
				valid=true;
			}
			else
			{
				alert("Please Enter Value In Atleast One Field")
			}
	}
	if(valid==true)
	{
		submitPage('SEARCH');
	}
}


function validateCrCheck(size)
{
	var valid=true;
	if(validateMinLength(document.getElementsByName('hrgnum_puk')[0],size) &&
		validateCheckSumBySize(size))
	{
		valid=true
		// alert("valid"+valid)
	}
	else
	{	
	//alert("invalid Cr no")
	alert("Invalid Cr No");
		valid=false
	}
	// alert("valid 123456 "+valid)
	return valid
}

function validateCheckSumBySize(size)
{
	var valid=true
// 	alert("validateCheckSumBySize")
	if(size==12)
	{
		valid=checkSumValidation()
	}
	if(size==13)
	{
		valid=checkSum()
	}
	return valid
}

//////////////////////for 12 digit cr no
	
	function checkSumValidation(){
	
		var crNum  = document.getElementsByName("hrgnum_puk")[0].value;
		 var checkSum = 0;
		 var power  = 12;
		 
		 for(var i=0;i<crNum.length-1;i++)
		 
		  checkSum += parseInt(crNum.substring(i,i+1)* power--);
		
		 checkSum=checkSum%11;
		 if((checkSum!=0 && checkSum!=1))
		  checkSum=11-checkSum;
		 if(checkSum==crNum.substring(11))
		   return true;
		 else
		 {
		  //alert("InValid CR No");
		  return false;
		 }

}


/////////////////////For 13 digit cr no

function checkSum(){
			var isValid = true;
			/*crNo=document.getElementsByName("hrgnum_puk")[0].value;
			
	modulo_1=crNo.substring(0,1)*7 + crNo.substring(1,2)*6 + 
			crNo.substring(2,3)*5 + crNo.substring(3,4)*4 +  
			crNo.substring(4,5)*3 + crNo.substring(5,6)*2 +  
			crNo.substring(6,7)*7 + crNo.substring(7,8)*6 +
			crNo.substring(8,9)*5 + crNo.substring(9,10)*4 +  
			crNo.substring(10,11)*3 + crNo.substring(11,12)*2 ;

	modulo=modulo_1 % 11;
		checksum = (modulo==0)?1:(11-modulo)%10;
	if(checksum==crNo.charAt(crNo.length-1)){
	isValid = true;
	}
		else
	{
	//alert("InValid CR No");
	isValid = false;
	}*/
	return isValid;
}

function fetchIcdCode(e)
{
// var enquiryType=document.forms[0].enquiryType.value;
var enquiryType=document.getElementsByName("enquiryType")[3].value;
var url='/HISClinical/mrd/caseSheetEnquiry.cnt?hmode=POPUP&enquiryType='+enquiryType;
openPopup(createFHashAjaxQuery(url),e,300,600);
}

</script>

<body>
		<html:form action="/caseSheetEnquiry">
<his:TransactionContainer>
<his:statusNew>
<his:TitleTag name="Case Sheet Enquiry">
</his:TitleTag>
<his:SubTitleTag name="">
			<div align="left">
							<html:radio name="CaseSheetEnquiryFB" property="enquiryType" value="<%=MrdConfig.CASE_SHEET_ENQUIRY_PATIENT_WISE %>" onclick="submitPage('SELECTRADIO')" tabindex="1" ></html:radio>
							<bean:message key="patientWise"/>
							<html:radio name="CaseSheetEnquiryFB" property="enquiryType" value="<%=MrdConfig.CASE_SHEET_ENQUIRY_CHRONIC_DISEASE_WISE %>" onclick="submitPage('SELECTRADIO')" tabindex="1" ></html:radio>
							<bean:message key="chronicDisease"/>
							<html:radio name="CaseSheetEnquiryFB" property="enquiryType" value="<%=MrdConfig.CASE_SHEET_ENQUIRY_ALLERGY_WISE %>" onclick="submitPage('SELECTRADIO')" tabindex="1" ></html:radio>
							<bean:message key="allergy"/>
							<html:radio name="CaseSheetEnquiryFB" property="enquiryType" value="<%=MrdConfig.CASE_SHEET_ENQUIRY_DIAGNOSIS_WISE %>" onclick="submitPage('SELECTRADIO')" tabindex="1" ></html:radio>
							<bean:message key="diagnosis"/>
			</div>
</his:SubTitleTag>

		<logic:equal name="CaseSheetEnquiryFB" property="enquiryType" value="<%=MrdConfig.CASE_SHEET_ENQUIRY_PATIENT_WISE %>">
			<his:ContentTag>
			
				<table  width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="13%"  class="tdfonthead">
							<div align="right">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="fname"/></font></div>
				  		</td>
				  		<td width="20%" class="tdfont">
				  			<div align="left">
							<html:text name="CaseSheetEnquiryFB"  maxlength="30" size="25" property="hrgstr_fname" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
				  			</div>
				  		</td>
				  		<td width="13%"  class="tdfonthead">
							<div align="right">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="mname"/></font></div>
				  		</td>
				  		<td width="20%" class="tdfont">
				  			<div align="left">
							<html:text name="CaseSheetEnquiryFB"  maxlength="20" size="25" property="hrgstr_mname"  styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
				  			</div>
				  		</td>
				  		<td width="13%"  class="tdfonthead">
							<div align="right">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="lname"/></font></div>
				  		</td>
				  		<td width="20%" class="tdfont">
				  			<div align="left">
							<html:text name="CaseSheetEnquiryFB"  maxlength="30" size="25" property="hrgstr_lname" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
				  			</div>
				  		</td>
				  		
				 	</tr>
				 	<tr>
				 				 <td width="13%" class="tdfonthead" >
				 				 	<div align="right">	     	  
						       		    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						                <bean:message key="crNo"/></font>
						              </div>  
						             </td> 
						         <td  width="20%" class="tdfont">
						           <div align="left">
									<html:text name="CaseSheetEnquiryFB"  maxlength="15" size="20" property="hrgnum_puk" styleClass="textbox" tabindex="1" onkeypress="return validateNumeric(event)"  ></html:text>
							  		</div>
							  	</td>
							  	 <td width="13%" class="tdfonthead" >	
							  	 		<div align="right">	       
						       		    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						               <bean:message key="admNo"/></font>
						               </div>
						             </td> 
						         <td  width="20%" class="tdfont">
						           <div align="left">
									<html:text name="CaseSheetEnquiryFB"  maxlength="15" size="20" property="hipnum_admno" styleClass="textbox" tabindex="1" onkeypress="return validateNumeric(event)"  ></html:text>
							  		</div>
							  	</td>
							  	<td width="13%" class="tdfonthead" >	
							  			<div align="right">	       
						       		    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						                <bean:message key="mlcNo"/></font>
						                </div>
						             </td> 
						         <td  width="20%" class="tdfont">
						           <div align="left">
									<html:text name="CaseSheetEnquiryFB"  maxlength="13" size="20" property="hrgnum_mlc_no" styleClass="textbox" tabindex="1" onkeypress="return validateNumeric(event)"  ></html:text>
							  		</div>
							  	</td>
				 	</tr>
				 	
				 	<tr>
				 				 
				 				 
							  	
							  	 <td width="13%" class="tdfonthead" >	
							  	 	<div align="right">	       
						       		    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						                <bean:message key="unknown"/></font>
						              </div> 
					             </td> 
						        <td class="tdfont" align="left" width="20%">
								      <html:checkbox  name="CaseSheetEnquiryFB" property="hrgnum_isunknown" value="1" tabindex="1" onclick="unknownChecked();"/>	      
								   </td>
								 <td width="13%" class="tdfonthead" >	  
								 	<div align="right">	     
						       		    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						                <bean:message key="isbroughtdead"/></font>
						               </div> 
						             </td>
						         <td class="tdfont" align="left" width="20%">
								      <html:checkbox  name="CaseSheetEnquiryFB" property="hrgnum_is_broughtdead" value="1" tabindex="1" onclick="isBroughtDeadChecked();"/>	      
								   </td>  
								   <td width="13%" class="tdfonthead">
								
								</td>
								<td width="20%" class="tdfont">
									
								</td>
								   
				 	</tr>
				 	
				 	<tr>
				 			 
						  <td width="13%" class="tdfonthead" >	  
						       		    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						               <bean:message key="dischargeType"/></font>
						             </td> 
						         
							  	<td width="20%" class="tdfont">
						  			<div align="left" >
						  			<html:select name="CaseSheetEnquiryFB" tabindex="1" property="hipdt_disstatus_code" styleClass="regcbo" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.CASE_SHEET_ENQUIRY_DISCHARGE_TYPE_LIST %>">
					  					<html:options  collection="<%=MrdConfig.CASE_SHEET_ENQUIRY_DISCHARGE_TYPE_LIST%>" property="value" labelProperty="label" />
						  				</logic:present>
						  			</html:select>
						  			</div>
						  		</td>
				             <bean:define name="CaseSheetEnquiryFB" property="fromDate" id="frDate" type="java.lang.String"/>
								<td width="13%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="fromDate"/>
										</font>
									</div>
								</td>
								<td width="20%" class="tdfont">
									<div align="left">
										<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate %>" />
									</div>
								</td>
								 <bean:define name="CaseSheetEnquiryFB" property="toDate" id="tDate" type="java.lang.String"/>
								<td width="13%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="toDate"/>
										</font>
									</div>
								</td>
								<td width="20%" class="tdfont">
									<div align="left">
										<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate %>" />
									</div>
								</td>
								
				             
             
					</tr>
	 	
				</table>
				
			</his:ContentTag>
			</logic:equal>
			<logic:equal name="CaseSheetEnquiryFB" property="enquiryType" value="<%=MrdConfig.CASE_SHEET_ENQUIRY_CHRONIC_DISEASE_WISE %>">
			<his:ContentTag>
			
				<table  width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%"  class="tdfonthead">
							<div align="right">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="chronicDisease"/></font></div>
				  		</td>
				  		<td width="25%" class="tdfont">
						  			<div align="left" >
						  			<html:select name="CaseSheetEnquiryFB" tabindex="1" property="hgnum_pat_alert_id" styleClass="regcbo" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.CASE_SHEET_ENQUIRY_ALERT_TYPE_LIST %>">
					  					<html:options  collection="<%=MrdConfig.CASE_SHEET_ENQUIRY_ALERT_TYPE_LIST%>" property="value" labelProperty="label" />
						  				</logic:present>
						  			</html:select>
						  			</div>
						 </td>
						 <td width="25%"  class="tdfonthead">
							
				  		</td>
				  		<td width="25%" class="tdfont">
						  			
						 </td>
				  	</tr>
				 </table>
				</his:ContentTag>
			</logic:equal>	  	
			<logic:equal name="CaseSheetEnquiryFB" property="enquiryType" value="<%=MrdConfig.CASE_SHEET_ENQUIRY_ALLERGY_WISE %>">
			<his:ContentTag>
			
				<table  width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%"  class="tdfonthead">
							<div align="right">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="allergyType"/></font></div>
				  		</td>
				  		<td width="25%" class="tdfont">
						  			<div align="left" >
						  			<html:select name="CaseSheetEnquiryFB" tabindex="1" property="hgnum_allergy_type_code" styleClass="regcbo" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.CASE_SHEET_ENQUIRY_ALLERGY_TYPE_LIST %>">
					  					<html:options  collection="<%=MrdConfig.CASE_SHEET_ENQUIRY_ALLERGY_TYPE_LIST%>" property="value" labelProperty="label" />
						  				</logic:present>
						  			</html:select>
						  			</div>
						 </td>
						 <td width="25%"  class="tdfonthead">
							
				  		</td>
				  		<td width="25%" class="tdfont">
						  			
						 </td>
				  	</tr>
				 </table>
				</his:ContentTag>
			</logic:equal>	 
			<logic:equal name="CaseSheetEnquiryFB" property="enquiryType" value="<%=MrdConfig.CASE_SHEET_ENQUIRY_DIAGNOSIS_WISE %>">
			<his:ContentTag>
			
				<table  width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%"  class="tdfonthead">
							<div align="right">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="icdCode"/></font></div>
				  		</td>
				  		<td class="tdfont" width="10%">
						<div align="left">
						&nbsp; <html:text name="CaseSheetEnquiryFB" maxlength="6" size="6" property="icdCode" tabindex="1" onkeypress="return validateAlphaNumOnly(this,event)"  onkeyup="gettext(event,this,'CODE');"  onblur="callOnBlur()"></html:text>
								
						</div>
						</td>
						 <td width="65%"  class="tdfonthead" colspan="2">
						 <div align="left">
						 	<html:button value="GET ICD CODE" property="myButton" tabindex="1" style="cursor:pointer" onclick="fetchIcdCode(event);" onkeypress="if(event.keyCode==13) fetchIcdCode(event);"></html:button>
						 								
				  		 </div>
				  		</td>
				  		
				  	</tr>
				 </table>
				</his:ContentTag>
			</logic:equal>	  		 			
			</his:statusNew>
	<%String address; %>
 <his:statusList>
<his:SubTitleTag name="Search Result">
					</his:SubTitleTag>
 	<his:ContentTag>
 	<table width="100%"  cellspacing="1" cellpadding="0">                    
		 <tr>
             <td  class="tdfonthead" width="15%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="crNo"/>
	            </font>
	            <a style="">
					<img src="/HIS/hisglobal/images/avai/arrow_up.gif" style="cursor: pointer;" alt="Ascending order" onclick="getOrderBy('ORDERBYCRNO','0')" tabindex="1" border="0" height="10" width="10">
				</a>
				<a style="">
						<img src="/HIS/hisglobal/images/avai/arrow_down.gif" style="cursor: pointer;" alt="Desccending order" onclick="getOrderBy('ORDERBYCRNO','1')" tabindex="1" border="0" height="10" width="10">
				</a>
	            </div>
             </td>
             <td  class="tdfonthead" width="20%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="patientName"/>
	            </font>
	            <a style="">
					<img src="/HIS/hisglobal/images/avai/arrow_up.gif" style="cursor: pointer;" alt="Ascending order" onclick="getOrderBy('ORDERBYNAME','0')" tabindex="1" border="0" height="10" width="10">
				</a>
				<a style="">
					<img src="/HIS/hisglobal/images/avai/arrow_down.gif" style="cursor: pointer;" alt="Desccending order" onclick="getOrderBy('ORDERBYNAME','1')" tabindex="1" border="0" height="10" width="10">
				</a>
	            </div>
             </td>
              <td  class="tdfonthead" width="15%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="gender/age"/>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="15%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="admNo"/>
	            </font>
	            
	            </div>
             </td>
             <td  class="tdfonthead" width="15%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="mlcNo"/>
	            </font>
	            </div>
             </td>
              <td  class="tdfonthead" width="20%">
				 <div align="center">
				 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			     <bean:message key="recordType"/>
				</font>
				 </div>
				 </td>
            
             
            
 		</tr>
 		<logic:present name="<%=MrdConfig.COMMON_CASE_SHEET_ENQUIRY_VO_ARRAY %>">
 		<logic:iterate indexId="idx" id="commonCaseSheetEnquiryVO" name="<%=MrdConfig.COMMON_CASE_SHEET_ENQUIRY_VO_ARRAY %>" type="mrd.vo.CommonCaseSheetEnquiryVO">
 			
 			<tr>
 			<td class="tdfont" width="15%">
	  			<div align="center" >
	  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  			<%=commonCaseSheetEnquiryVO.getHrgnum_puk() %>	
	  			</div>
	  		</td>
	  		<td class="tdfont" width="20%"> 
	  			<div align="center">
	  			 <a style="cursor:pointer" onclick="getDischargeDetail('<%=idx%>')" >
	  			<%=commonCaseSheetEnquiryVO.getHrgstr_fname()+" "+
	  			commonCaseSheetEnquiryVO.getHrgstr_mname()+" "+
	  			commonCaseSheetEnquiryVO.getHrgstr_lname()%>	
	  			</div>
	  		</td>
	  		<td class="tdfont" width="15%">
	  			<div align="center">
	  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  			<%=commonCaseSheetEnquiryVO.getGstr_gender_name()+"/"+commonCaseSheetEnquiryVO.getPatage() %>	
	  			</div>
	  		</td>
            <td class="tdfont" width="15%">
	  			<div align="center">
	  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  			<%=commonCaseSheetEnquiryVO.getHipnum_admno() %>
	  			</div>
	  		</td>
	  		 <td class="tdfont" width="15%">
	  			<div align="center">
	  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  			<%=commonCaseSheetEnquiryVO.getHrgnum_mlc_no() %>	
	  			</div>
	  		</td>
	  		<td class="tdfont" width="20%" nowrap="nowrap">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<%=commonCaseSheetEnquiryVO.getRecordTypeLabel()%>		
				</div>
			</td>
 </tr>
 		
 		</logic:iterate>
 		</logic:present>
 	</table>
 </his:ContentTag>
 
 </his:statusList>
			
			<his:statusTransactionInProcess>
			<bean:define id="commonCaseSheetEnquiryVOs" name="<%=MrdConfig.CASE_SHEET_ENQUIRY_VO %>" type="mrd.vo.CommonCaseSheetEnquiryVO"></bean:define>
			<logic:present name="<%=MrdConfig.CASE_SHEET_ENQUIRY_VO %>">
 			<his:SubTitleTag name="Patient Detail">
					</his:SubTitleTag>
					<his:ContentTag>
				 	<table width="100%"  cellspacing="1" cellpadding="0">                    
						 <tr>
				             <td  class="tdfonthead" width="25%">
					            <div align="right">
					            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					            <bean:message key="patientName"/>
					            </font>
					           
					            </div>
				             </td>
				             <td class="tdfont" width="25%">
						  			<div align="left">
						  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						  			&nbsp <%=commonCaseSheetEnquiryVOs.getHrgstr_fname()+" "+
						  			commonCaseSheetEnquiryVOs.getHrgstr_mname()+" "+
						  			commonCaseSheetEnquiryVOs.getHrgstr_lname()%>		
						  			</div>
						  		</td>
						  	<td  class="tdfonthead" width="25%">
					            <div align="right">
					            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					            <bean:message key="crNo"/>
					            </font>
					           
					            </div>
				             </td>
				             <td class="tdfont" width="25%">
						  			<div align="left">
						  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						  			&nbsp <%=commonCaseSheetEnquiryVOs.getHrgnum_puk()%>		
						  			</div>
						  		</td>
				     </tr>
				     <tr>
				     		<td  class="tdfonthead" width="8%">
						            <div align="right">
						            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						            <bean:message key="gender/age"/>
						            </font>
						            </div>
					             </td>
				             <td class="tdfont">
						  			<div align="left">
						  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						  			&nbsp <%=commonCaseSheetEnquiryVOs.getGstr_gender_name() %>	
						  			     <%=commonCaseSheetEnquiryVOs.getPatage() %>	
						  			
						  			</div>
						  	</td>
				            
						  		<td  class="tdfonthead" width="25%">
					            <div align="right">
					            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					            <bean:message key="admissionDate"/>
					            </font>
					           
					            </div>
				             </td>
				             <td class="tdfont" width="25%">
						  			<div align="left">
						  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						  			&nbsp <%=commonCaseSheetEnquiryVOs.getHipdt_admdatetime()%>		
						  			</div>
						  		</td>
				     </tr>
				     <tr>
				     		<td  class="tdfonthead" width="25%">
						            <div align="right">
						            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						            <bean:message key="admNo"/>
						            </font>
						            
						            </div>
					             </td>
					             <td class="tdfont" width="25%">
							  			<div align="left">
							  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							  			&nbsp <%=commonCaseSheetEnquiryVOs.getHipnum_admno() %>
							  			</div>
							  		</td>
							  		 
					             <td  class="tdfonthead" width="25%">
						            <div align="right">
						            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						            <bean:message key="mlcNo"/>
						            </font>
						            </div>
					             </td>
					             <td class="tdfont" width="25%">
							  			<div align="left">
							  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							  			&nbsp <%=commonCaseSheetEnquiryVOs.getHrgnum_mlc_no() %>	
							  			</div>
							  		</td>
				     
				     </tr>
				     <tr>
				     		<td  class="tdfonthead" width="25%" rowspan="2">
					            <div align="right">
					            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					            <bean:message key="address"/>
					            </font>
					           
					            </div>
				             </td>
				     		 <td class="tdfont" width="75%" colspan="3" rowspan="2">
						  			<div align="left">
						  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						  			&nbsp <% address="";
						  				if(commonCaseSheetEnquiryVOs.getHrgstr_address()!="")
						  					address=address+commonCaseSheetEnquiryVOs.getHrgstr_address()+",";
						  				if(commonCaseSheetEnquiryVOs.getHrgstr_city()!="")
						  					address=address+commonCaseSheetEnquiryVOs.getHrgstr_city()+",";
						  				if(commonCaseSheetEnquiryVOs.getHrgstr_state_name()!="")
						  					address=address+commonCaseSheetEnquiryVOs.getHrgstr_state_name() +", \n";
						  				if(commonCaseSheetEnquiryVOs.getGstr_countryname()!="")
						  					address=address+commonCaseSheetEnquiryVOs.getGstr_countryname();
						  				%>
						  				<%=address%>
						  				
						  		</div>
						  				
						  		</td>
				     </tr>
				     
				     </table>
				     </his:ContentTag>
				  <his:SubTitleTag name="Discharge Detail">
					</his:SubTitleTag>          
					<his:ContentTag>
				 
				 	<table width="100%"  cellspacing="1" cellpadding="0">                    
						 <tr>
				             <td  class="tdfonthead" width="25%">
					            <div align="right">
					            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					            <bean:message key="dischargeDate"/>
					            </font>
					           
					            </div>
				             </td>
				             <td class="tdfont" width="25%">
						  			<div align="left">
						  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						  			&nbsp <%=commonCaseSheetEnquiryVOs.getHipdt_disdatetime()
							  			%>		
						  			</div>
						  		</td>
						  	<td  class="tdfonthead" width="25%">
					            <div align="right">
					            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					            <bean:message key="dischargeType"/>
					            </font>
					           
					            </div>
				             </td>
				             <td class="tdfont" width="25%">
						  			<div align="left">
						  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						  			&nbsp <%=commonCaseSheetEnquiryVOs.getDischargeType()%>		
						  			</div>
						  		</td>
				     </tr>
				     <tr>
				     		<td  class="tdfonthead" width="25%">
					            	<div align="right">
						            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						            <bean:message key="edischarge"/>
						            </font>
					          		</div>
				             </td>
				             <%if(!commonCaseSheetEnquiryVOs.getProfileStatus().equals(OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED)) { %>
				              		<td class="tdfont" width="25%" colspan="3">
				              		<div align=left>
						            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						            &nbsp <bean:message key="notAvailable"/>
						            </font>
					          		</div>
					          		</td>
				              <%} else {%>
				              <td class="tdfont" width="25%" colspan="3">
						  		<div align="left">
					            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<% String url="/HISClinical/mrd/caseSheetEnquiry.cnt?hmode=VIEWPROFILE&profileId="+commonCaseSheetEnquiryVOs.getProfileId() ; %>
					            <a style="cursor:pointer" onclick="viewPrintProfile(event,'<%=commonCaseSheetEnquiryVOs.getProfileStatus() %>','<%=url%>');" >
					            &nbsp <bean:message key="available" />
					           	</a>
					           	</font>
					            </div>
						  		</td>
						  		<%} %>
				     </tr>
				     
				     </table>
				     </his:ContentTag> 
				     
				     <his:SubTitleTag name="Record Detail">
					</his:SubTitleTag>          
					
				     <%if(!commonCaseSheetEnquiryVOs.getCount().equals("0")) 
				     { %>
					     <logic:present name="<%=MrdConfig.CASE_SHEET_ENQUIRY_RECORD_STORAGE_VO %>">
					     
					     <logic:iterate indexId="idx" id="mrdRecordDtlVOs" name="<%=MrdConfig.CASE_SHEET_ENQUIRY_RECORD_STORAGE_VO %>" type="hisglobal.vo.MrdRecordDtlVO">
						     <his:ContentTag>
						 			<table width="100%"  cellspacing="1" cellpadding="0">                    
										 <tr>
								             <td  class="tdfonthead" width="15%">
									            <div align="center">
									            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									            <bean:message key="recordType"/>
									            </font>
									           </div>
								             </td>
								             <td  class="tdfonthead" width="15%">
									            <div align="center">
									            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									            <bean:message key="mrdName"/>
									            </font>
									           </div>
								             </td>
								             <td  class="tdfonthead" width="15%">
									            <div align="center">
									            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									            <bean:message key="rackName"/>
									            </font>
									           </div>
								             </td>
								             <td  class="tdfonthead" width="15%">
									            <div align="center">
									            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									            <bean:message key="shelfName"/>
									            </font>
									           </div>
								             </td>
								             <td  class="tdfonthead" width="20%" nowrap="nowrap" style="display:none;">
									            <div align="center">
									            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									            <bean:message key="rackLocation"/>
									            </font>
									           </div>
								             </td>
								             <td  class="tdfonthead" width="20%" nowrap="nowrap">
									            <div align="center">
									            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									            <bean:message key="recordStatus"/>
									            </font>
								           
								           	 </div>
				             			</td>
								         </tr>
								         <tr>
								         	<td class="tdfont" width="15%">
									  			<div align="center">
									  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									  			 
									  			 <bean:write name="mrdRecordDtlVOs" property="recordTypeLabel"/>	
									  			</div>
									  		</td>
									  		<td class="tdfont" width="15%">
									  			<div align="center">
									  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									  			
									  			<bean:write name="mrdRecordDtlVOs" property="mrdName"/>		
									  			</div>
									  		</td>
									  		<td class="tdfont" width="15%">
									  			<div align="center">
									  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									  			 <bean:write name="mrdRecordDtlVOs" property="rackLabel"/>
									  			 
									  			</div>
									  		</td>
									  		<td class="tdfont" width="15%">
									  			<div align="center">
									  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									  			 <bean:write name="mrdRecordDtlVOs" property="shelfName"/>
									  			 
									  			</div>
									  		</td>
									  		<td class="tdfont" width="20%" nowrap="nowrap" style="display:none;">
									  			<div align="center">
									  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									  			 <bean:write name="mrdRecordDtlVOs" property="rackLocation"/>
									  					
									  			</div>
									  		</td>
									  		<td class="tdfont" width="20%" nowrap="nowrap">
									  			<div align="center">
									  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									  			  <bean:write name="mrdRecordDtlVOs" property="recordStatusLabel"/>
									  			 	
									  			</div>
									  		</td>
								         </tr>
								     
								     </table>
						     </his:ContentTag> 
						     </logic:iterate>
					   </logic:present>
				   <%} else{%>
				   <his:ContentTag>
				 
				 	<table width="100%"  cellspacing="1" cellpadding="0">                    
						 <tr>
				             <td  class="tdfonthead" width="25%">
					            <div align="right">
					            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					            <bean:message key="recordStatus"/>
					            </font>
					           
					            </div>
				             </td>
				             <td class="tdfont" width="25%">
						  			<div align="left">
						  			 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						  			&nbsp <%=commonCaseSheetEnquiryVOs.getRecordStatusLabel()
							  			%>		
						  			</div>
						  		</td>
						  	<td  class="tdfonthead" width="25%">
					        </td>
				             <td class="tdfont" width="25%">
						  	</td>
						  	
				     </tr>
				     
				     </table>
				     </his:ContentTag> 
				   <% } %>
				 </logic:present>    
				 <html:hidden name="CaseSheetEnquiryFB" property="hrgstr_fname"/>
				<html:hidden name="CaseSheetEnquiryFB" property="hrgstr_mname"/>
				<html:hidden name="CaseSheetEnquiryFB" property="hrgstr_lname"/>
				<html:hidden name="CaseSheetEnquiryFB" property="hrgnum_puk"/>
				<html:hidden name="CaseSheetEnquiryFB" property="hipnum_admno"/>
				<html:hidden name="CaseSheetEnquiryFB" property="hrgnum_mlc_no"/>
				<html:hidden name="CaseSheetEnquiryFB" property="hipdt_disstatus_code"/>
				<html:hidden name="CaseSheetEnquiryFB" property="fromDate"/>
				<html:hidden name="CaseSheetEnquiryFB" property="toDate"/>
				<html:hidden name="CaseSheetEnquiryFB" property="hrgnum_isunknown"/>
				<html:hidden name="CaseSheetEnquiryFB" property="hrgnum_is_broughtdead"/>
				<html:hidden name="CaseSheetEnquiryFB" property="hgnum_pat_alert_id"/>
				<html:hidden name="CaseSheetEnquiryFB" property="hgnum_allergy_type_code"/>
				<html:hidden name="CaseSheetEnquiryFB" property="icdCode"/>
			</his:statusTransactionInProcess>
			
					<his:ButtonToolBarTag>
					<his:statusNew>
						<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="validateCaseSheetEnquiry();" tabindex="1" onkeypress="if(event.keyCode==13) validateCaseSheetEnquiry();" >
					 	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) cancelFunc();" tabindex="1" onclick ="cancelFunc();">
			 		
			 		</his:statusNew>	
			 		<his:statusList>
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW');">			 		
			 		</his:statusList>
			 			<his:statusTransactionInProcess>
			 				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('SEARCH');" tabindex="1" onclick ="submitPage('SEARCH');">
			 			</his:statusTransactionInProcess>
					</his:ButtonToolBarTag>
					
			</his:TransactionContainer>		
				<html:hidden name="CaseSheetEnquiryFB" property="hmode"/>
				<html:hidden name="CaseSheetEnquiryFB" property="selectedIndex"/>
				<html:hidden name="CaseSheetEnquiryFB" property="profileStatus"/>
				<html:hidden name="CaseSheetEnquiryFB" property="profileId"/>
				<html:hidden name="CaseSheetEnquiryFB" property="order"/>
				<html:hidden name="CaseSheetEnquiryFB" property="enquiryType"/>
				
				<html:hidden name="CaseSheetEnquiryFB" property="sortOn"/>
			</html:form>
		</body>
		<his:status/>
		
	</html> 	