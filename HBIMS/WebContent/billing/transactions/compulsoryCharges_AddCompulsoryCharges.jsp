<%try{ %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/accountDtl.tld" prefix="acDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/onlineClientDtl.tld" prefix="bDtl"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.*"%>
<html>
<head>
<meta charset=utf-8>
<title>Compulsory Charges By Consultant</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"	type="text/css">
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multiRowTLD.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/dropdown.js"></script>
<!-- <script language="Javascript" src="../../billing/js/IpdBillMangTrans.js"></script> -->
<!-- <script language="Javascript" src="../../billing/js/tariffSearch.js"></script>
<script language="Javascript" src="../../billing/js/tariffCodeSearch.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script> -->
<script language="Javascript" src="../../billing/js/billing.js"></script>
<script language="Javascript" src="/HIS/hisglobal/js/utilityFunctions.js"></script>



<script type="text/javascript">

 function getInvisbleforAddService()
 {   
  
  	document.forms[0].hmode.value="ADDCOMPULSORYCHARGES";
 	document.forms[0].submit();
     
     
 }
function getTariff(idx)
{
	//alert(document.getElementsByName("strStrTariffCode")[0].value);
	//alert(document.getElementsByName("strDefaultCompTariffId")[0].value);
	//alert(idx);
	var input = document.getElementById('strStrTariffCode'+idx).value;
	//alert(input);
	document.getElementsByName("strCompulsoryTariffCode")[idx].value  = input;

	//alert(document.getElementsByName("strCompulsoryTariffCode")[idx].value);
	//alert(document.getElementsByName("strCompulsoryTariffCode")[0].value);
}



	 function goFuncAddService()
	 {
		 var flag="false";	
		 
	  		//var hisValidator = new HISValidator("CompulsoryChargesByConsultantBean");  
	  		//var newcharge=document.getElementsByName("strStrTariffCode")[0].value;
	  		//var defcharge=document.getElementsByName("strDefaultCompTariffId")[0].value;
			var  len=document.getElementsByName('strNumRows')[0].value;
			//alert(len);
	  		 for (var i=0;i<len;i++)
		  	 {
			  	 
	  			var newcharge= document.getElementsByName("strCompulsoryTariffCode")[i].value;
	  			var defcharge=document.getElementsByName("strDefaultCompTariff")[i].value;
	  			//	alert("nc:"+newcharge);alert("dc"+defcharge);
	 			if(newcharge!=defcharge && newcharge!="")
	 				{
	 				    flag="true";	
	 					if(confirm("Are You Sure to Save it?"))
	 					{	
		 				document.forms[0].hmode.value="ADDCOMPCHARGESINSERT";
	 					document.forms[0].submit();	
	 					break;				
	 					}
	 					
	 				}
		  	 }
		  	

		 		if(flag=="false")
			 		alert("No New Charges Applied");
	 				
	 }

</script>
</head>

<body>
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>




<html:form action="transactions/CompulsoryChargesByConsultantCNT.cnt" method="post">
 <div id="SHORTCUTDIV1" align="center"></div>
 <div id="blanket" style="display:none;"></div>
 <div id="consumableChargeDiv" style="display: none;"></div>
 
 
	<!--      Drop Down Untility  End              -->
 
    
 <tag:tab tabLabel="AUTO CHARGES"  onlyTabIndexing="1"  selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	
   
 <bean:write name="CompulsoryChargesByConsultantBean" property="strPatientDetailsView" filter="false"/>
  <!--<aDtl:addDtl crNo="${ipdBillManagementTransBean.strCrNo}"></aDtl:addDtl>-->
 
 <table class="TABLEWIDTH" align="center" cellspacing ="1px" style="display:none;">
  <tr> 
    <td width="5%" class="TITLE" align="center"><input type='hidden' name='button3' value="0">
    <img src="../../hisglobal/images/plus.gif"  width="15" height="15" id="plus3" style="display:block;cursor:pointer;cursor:hand" onClick="ftn33()">
    <img src="../../hisglobal/images/minus.gif" id="minus3" style="display:none;cursor:pointer;cursor:hand" onClick="ftn33()"></td>
    <td colspan="3" class="TITLE"><b>Account Detail</b></td> 
   </tr>
 </table>

 <div id = "detailsdivid3" style="display:none;">
 <table class="TABLEWIDTH" align="center" cellspacing ="1px">
  <tr>
          <bean:write name="CompulsoryChargesByConsultantBean" property="strClientAcctDtl" filter="false"/>
  </tr>
 </table>
 </div>

   
<div id='otherDetailsDivId' style="display: block;">   
<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">
  
	<tr>
	    <td class="LABEL" width="25%">Treatment Category</td>
        <td class="CONTROL" width="25%">
        	<div id="treatCatComboDivId" >
               	<select class="comboNormal" name="strNewTreatmentCategory" disabled="disabled"  >
               		<bean:write name="CompulsoryChargesByConsultantBean" property="strTreatmentCategoryValues" filter="false"   />
               	</select>
            </div>
            <div id="treatCatLabelDivId" style="display: none;" ></div>                  
        </td> 
        <td class="LABEL" width="25%">Charge Type</td>
		<td class="CONTROL" width="25%"  >
      		<div id="wardTypeDivId" >
     			<select class="comboNormal" name="strWardType"  disabled="disabled" >
       				<bean:write name="CompulsoryChargesByConsultantBean" property="strWardTypeValues" filter="false" />
				</select> 
			</div>
			<div id="wardTypeLabelDivId" style="display: none;" ></div>
		</td>		
	</tr>
	
	<tr>
	
	<td class="LABEL" width="25%">Admission Date:</td>
        <td class="CONTROL" width="25%">
	        <div id="addmdtDivId" >
		      
		        	<bean:write name="CompulsoryChargesByConsultantBean" property="strAdmissionDate" filter="false"   />
		       
	        </div>
            <div id="admDivId" style="display: none;" ></div>                  
      	</td>
      	<td class="LABEL" width="25%">Current Ward:</td>
		<td class="CONTROL" width="25%">
 			<div id="wardNameDivId" >
			
					<bean:write name="CompulsoryChargesByConsultantBean" property="strWardName" filter="false"   />
 				                  
			</div>
   			<div id="wWardDivId" style="display: none;" ></div>                  
 		</td>  
	</tr>
	
	                 
 </table> 
 <table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px" style="display: none;">
  <tr>                       
   <td class="multiControl" colspan="2" width="50%">                      
   	<img src="../../hisglobal/images/Go.png" tabindex="1" id="goButtonId" style="cursor: pointer;" onkeypress="goFuncValidation();" onclick="goFuncValidation();"   title="Click here to get Compulsory Charges and Special Charges">                      
 </td>
</tr>
</table>   
</div>
   
    <table class="TABLEWIDTH" border="0" cellpadding="1px" cellspacing ="1px" align="center">
				<tr class="FOOTER">
					<td width='3%'>
					
	</table>
     
      
  	
	   	<bean:write name="CompulsoryChargesByConsultantBean" property="strAllTrfHLP" filter="false"/>
	  <%--  <div id='otherDetailsDivId' style="display: block;">   
<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px" width="75%">
  
	<tr align="center">
	    <td class="LABEL" width="33%" align="center"><div align="center" >Date</div></td>
	     <td class="LABEL" width="33%" align="center"><div align="center" >Charge</div></td>
	      <td class="LABEL" width="33%" align="center"> <div align="center" >Category</div></td>
	      </tr>
	      <tr>
        <td class="CONTROL" width="33%" align="center">
        	<div id="treatCatComboDivId" align="center" >
        	  <%	String systemDate =  WebUTIL.getCustomisedSysDate(new Date(), "dd-MMM-yyyy"); %>
      	     		
      		<input type="text" name="sysdate" value="<%=systemDate %>" size="10" readonly="readonly"/>
               		
            </div>
            <div id="treatCatLabelDivId" style="display: none;" ></div>                  
        </td> 
       
        <td class="CONTROL" width="33%" align="center">
        	<div id="treatCatComboDivId" align="center" > Daily Pharmacy Charges
               
               	
               
            </div>
            <div id="treatCatLabelDivId" style="display: none;" ></div>                  
        </td> 
        
		<td class="CONTROL" width="33%"  align="center" >
      		<div id="wardTypeDivId"  align="center">
     			<select class="comboNormal" name="strStrTariffCode" onchange="getTariff();" style="width:225px;"  >
     			  <bean:write name="CompulsoryChargesByConsultantBean" property="strTariffNameCombo" filter="false"  /> 
       		
				</select> 
			</div>
			<div id="wardTypeLabelDivId" style="display: none;" ></div>
		</td>		
	</tr>
	
	
	
	                   
 </table> 
 <table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px" style="display: none;">
  <tr>                       
   <td class="multiControl" colspan="2" width="50%">                      
   	<img src="../../hisglobal/images/Go.png" tabindex="1" id="goButtonId" style="cursor: pointer;" onkeypress="goFuncValidation();" onclick="goFuncValidation();"   title="Click here to get Compulsory Charges and Special Charges">                      
 </td>
</tr>
</table>   


  </div> --%>
 
  
    <bean:define id="userValueId"  value='<%=(String)session.getAttribute("USERVALUE") %>' ></bean:define>
	
   
   <table class="TABLEWIDTH" border="0" cellpadding="1px" cellspacing ="1px" align="center">
				<tr class="FOOTER">
					<td width='3%'>
					
	</table>
	<div id="HelpId" style="display:none">
	<table class="TABLEWIDTH" border="0" cellspacing="1px" align="center">
		
	</table>
	</div>   
  
   <table border="0" class="TABLEWIDTH" align="center">
		<tr>
        	<td align="center">
        		<a href="#" class="button" id="btn-sv" onclick='return goFuncAddService();'><span class="save">Save</span></a>
			<a href="#" class="button" id="btn-clr" onclick="getInvisbleforAddService();"><span class="clear">Clear</span></a> 
			<logic:notEqual name="CompulsoryChargesByConsultantBean"  property="strIsDesk" value="true">
			<a href="#" class="button" id="btn-ccl" onClick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</logic:notEqual>
			
        	</td>
    	</tr>
	</table>
	
	  <div id="errMsg" class="errMsg"><bean:write     name="CompulsoryChargesByConsultantBean" property="strErrMsg"/></div> 
   	 <div id="warningMsg" class="warningMsg"><bean:write name="CompulsoryChargesByConsultantBean" property="strWarning"/></div>
	 <div id="normalMsg" class="normalMsg"><bean:write  name="CompulsoryChargesByConsultantBean" property="strMsg"/></div>
	
	<div id="loadingmessage" class="wrapper rounded" style="display:none;width:180px;height:100px;position:absolute;top:40%;left:42%;padding:2px;z-index:100;">
				<div class="div-table">
					<div class="div-table-row">
						<div class="div-table-col title width100 ">
								Please Wait...
						</div>
					</div>
					<div class="div-table-row alignCenter" >
						<div class="div-table-col width100 " style="margin-top: 7px;">
								<img src="/HIS/hisglobal/images/ajax-loader.gif" width="50" height="50" />
						</div>
					</div>
				</div>
	</div>


	<input type="hidden" name="hmode">
	<input type="hidden" name="strChk" value="${CompulsoryChargesByConsultantBean.strChk}">
	<input type="hidden" name="chk" value="${CompulsoryChargesByConsultantBean.strChk}">
	<input type="hidden" name="strChkBoxComboValue" value="${CompulsoryChargesByConsultantBean.strChkBoxComboValue}">
	<input type="hidden" name="strWardCode" value="${CompulsoryChargesByConsultantBean.strWardCode}">
	<input type="hidden" name="strHospitalCode" value="${CompulsoryChargesByConsultantBean.strHospitalCode}">
	<input type="hidden" name="strIpdRoundOff" value="${CompulsoryChargesByConsultantBean.strIpdRoundOff}">
	<input type="hidden" name="strIpdThirdPartyBilling" value="${CompulsoryChargesByConsultantBean.strIpdThirdPartyBilling}">	
	<input type="hidden" name="strExcessCreditLimit" value="${CompulsoryChargesByConsultantBean.strExcessCreditLimit}" />	 
	<input type="hidden" name="strCtDate" value="${CompulsoryChargesByConsultantBean.strCtDate}" />	
	<input type="hidden" name="strIpdBillManagementMode" value="${CompulsoryChargesByConsultantBean.strIpdBillManagementMode}" />
	<input type="hidden" name="strTempTreatCat" value="0" />
	<input type="hidden" name="strTempWardCode" value="0" />	
	<input type="hidden" name="searchColumn" value='<%=request.getParameter("searchColumn") %>' />
	<input type="hidden" name="search" value='<%=request.getParameter("search") %>' />
	<input type="hidden" name="blockNo" value='<%=request.getParameter("blockNo") %>' />
	<input type="hidden" name="prevNext" value='<%=request.getParameter("prevNext") %>' />
	<input type="hidden" name="rowNum" value='<%=request.getParameter("rowNum") %>'/>
	<input type="hidden" name="divisionId" value='<%=request.getParameter("divisionId") %>' />
	<input type="hidden" name="dietChargeId" value="${CompulsoryChargesByConsultantBean.dietChargeId}" />
	<input type="hidden" name="strConsumableCharge" value="${CompulsoryChargesByConsultantBean.strConsumableCharge}" />
	<input type="hidden" name="strOfflineTotalPayAmountWithoutConsumable" value="${CompulsoryChargesByConsultantBean.strOfflineTotalPayAmountWithoutConsumable}" />
	<input type="hidden" name="strGroupIdForConsumableConcatenated" value="${CompulsoryChargesByConsultantBean.strGroupIdForConsumableConcatenated}" />
	<input type="hidden" name="strConsumableChargesGroupId" value="${CompulsoryChargesByConsultantBean.strConsumableChargesGroupId}" />
	<input type="hidden" name="strConsumableChargesTariffCode" value="${CompulsoryChargesByConsultantBean.strConsumableChargesTariffCode}" />
	<input type="hidden" name="strUrgSur" value="${CompulsoryChargesByConsultantBean.strUrgSur}">
	<input type="hidden" name="strNumRows" value="${CompulsoryChargesByConsultantBean.strNumRows}">
	<input type="hidden" name="selectedTab">
	<input type="hidden" name="strIsCalledFromIpdBillNew" value="${CompulsoryChargesByConsultantBean.strIsCalledFromIpdBillNew}" />
	<input type="hidden" name="strAcctStatMode" value="${CompulsoryChargesByConsultantBean.strAcctStatMode}" />
	<input type="hidden" name="grpid" value="${CompulsoryChargesByConsultantBean.grpid}" />
	<input type="hidden" name="strAdvanceamt" value="${CompulsoryChargesByConsultantBean.strAdvanceamt}" />
	<input type="hidden" name="strArogyaIpdCreditLimit" value="${CompulsoryChargesByConsultantBean.strArogyaIpdCreditLimit}" />
	<input type="hidden" name="strCurrentRowIndex" value="" />
	<input type="hidden" name="strCurrentRowTrfCode" value="" />
	<input type="hidden" name="serviceFlag" value="${CompulsoryChargesByConsultantBean.serviceFlag}" />
		
	<input type="hidden" name="strCompulsoryTariffCode" value="${CompulsoryChargesByConsultantBean.strCompulsoryTariffCode}" />
	<input type="hidden" name="strDefaultCompTariffId" value="${CompulsoryChargesByConsultantBean.strDefaultCompTariffId}" />
	<input type="hidden" name="strDefaultCompTariff" value="${CompulsoryChargesByConsultantBean.strDefaultCompTariff}" />
	<input type="hidden" name="strGrpId" value="${CompulsoryChargesByConsultantBean.strGrpId}" />
	
	
	<input type="hidden" name="strWardName" value="${CompulsoryChargesByConsultantBean.strWardName}">
	<input type="hidden" name="strAdmissionDate" value="${CompulsoryChargesByConsultantBean.strAdmissionDate}">
	<input type="hidden" name="strIsDesk" value="${CompulsoryChargesByConsultantBean.strIsDesk}">
	
	
	
	
		<cmbPers:cmbPers/>
</html:form>
 
</body>
</html>
<%}catch(Exception e)
{
e.printStackTrace();	
}%>