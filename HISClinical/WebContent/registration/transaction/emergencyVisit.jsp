<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>


<%@ page import ="java.util.*,registration.*, hisglobal.presentation.Status" %>
<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

var fromDeptUnitCode="";
 var refIndex="";
 
function disableAmount()
{
	obj=document.getElementsByName("isFree")[0];
	//alert("obj="+obj)
	if(obj!=null && obj!="undefined")
	{
		if(obj.checked==true)
		{
			document.getElementsByName("patAmountCollected")[0].disabled=true;
		}
		else
		{
			document.getElementsByName("patAmountCollected")[0].disabled=false;
		}
	}
} 

 function checkReferDepartment()
{
	//if(<%=Config.CLIENT%>==<%=Config.CLIENT_PGIMER%>){
		if(document.getElementsByName('patRefGnctdHospitalDept')[0].value=='0')
		{
			document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false
			//document.getElementsByName('patRefHospitalDeptOther')[0].value=""
		}
		else
		{
			document.getElementsByName('patRefHospitalDeptOther')[0].value=""
			document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true
		}
	//}	
}
 
 
function getBillAmountForGrouping(fromDeptUnit,index)
{	
	 //  alert("refferringOPDEpisode "+document.getElementsByName("refferringOPDEpisode")[0].value)
	//alert(document.getElementsByName("isRefferInOut")[0].checked)
	//alert("isRefferInOut "+document.getElementsByName("isRefferInOut")[0].value)
	var referInOut=document.getElementsByName("isRefferInOut")[0].checked;
	var toDeptUnitCode=document.getElementsByName("departmentUnitCode")[0].value;
	//var isFree=document.getElementsByName("isFree")[0];
	// alert("toDeptUnitCode "+toDeptUnitCode);
	if(fromDeptUnit!=="")
	{
// 	alert("nnnnnnnnnnnnnnnnnnnnnnnn")
	// document.getElementsByName("selectedFromDeptUnit")[0].value=fromDept;
	fromDeptUnitCode=fromDeptUnit;
	refIndex=index;
	}
	else{}
	var isrefer=document.getElementsByName("isReferred")[0].checked;
// 	alert("isrefer "+isrefer)
	// alert("referInOut "+referInOut)
	// alert("fromDeptUnitCode "+fromDeptUnitCode)
	// alert("toDeptUnitCode "+toDeptUnitCode+" isrefer "+isrefer+" fromDeptUnitCode "+fromDeptUnitCode+" referInOut "+referInOut )
	//if( ((toDeptUnitCode!="") && (toDeptUnitCode!="-1") ) && (isrefer==true) && (fromDeptUnitCode!="") && (referInOut==true) && (isFree.checked==false) )
	if( ((toDeptUnitCode!="") && (toDeptUnitCode!="-1") ) && (isrefer==true) && (fromDeptUnitCode!="") && (referInOut==true) )
	{
		//alert("inside If ")	
		document.getElementsByName("selectedFromDeptUnit")[0].value=fromDeptUnitCode;
		document.getElementsByName("referingRowIndex")[0].value=refIndex;
		submitForm("GETREGFEE"); 
	//	alert("selectedFromDeptUnit"+document.getElementsByName("selectedFromDeptUnit")[0].value)
		
	}
	else
	{
	//	alert("inside Else patBillAmountWithoutGrouping "+document.getElementsByName("patBillAmountWithoutGrouping")[0].value)	
		document.getElementsByName("patAmountCollected")[0].value=document.getElementsByName("patBillAmountWithoutGrouping")[0].value;
		
	}
	
}



function getRefer()
{  //  alert("index "+index)
	var isRef=document.getElementsByName("isReferred")[0];
	
	if(isRef!=null && isRef!="undefined")
	{
	
	
	var isRefInternalExternal=document.getElementsByName("referInternalExternal")[0].value;
	var refValue=document.getElementsByName("checkref")[0].value;
	var index=document.getElementsByName("referingRowIndex")[0].value;
// 	alert("isref "+isRef +" refvalue "+refValue)
	if((refValue=="1") || (isRef.checked==true) ){
	// 	alert("zxcxcxcx")
	// 	document.getElementById("addImage").style.display="none";
		document.getElementsByName("isReferred")[0].checked=true;
		document.getElementById("checkReferral").style.display="block";
		if(isRefInternalExternal=="I")
		{	
			
			document.getElementsByName("isRefferInOut")[0].checked=true;
		 	showInternalForGrouping();
		//  	alert(document.form[0].getElementsByName("isRefferInOut").value)
		 	if(index!="")
		 	{
		 // 		alert("index "+index)
		 		document.getElementsByName("refferringOPDEpisode")[parseInt(index)].checked=true;
		 		fromDeptUnitCode=document.getElementsByName("selectedFromDeptUnit")[0].value;
		 		refIndex=document.getElementsByName("referingRowIndex")[0].value;
		 	}
 		}
 	} 
 	}        
} 

function checkIsReferred(e){
if (e.checked==true){
// 	alert("check");
	//document.getElementById("common").style.display="none";
	document.getElementsByName("isReferred")[0].value="1";
	document.getElementById("checkReferral").style.display="block";
	//document.getElementById("addImage").style.display="none";
	getBillAmountForGrouping('','');
}
else{	
		if(refIndex!="")
	{
	document.getElementsByName("refferringOPDEpisode")[parseInt(refIndex)].checked=false;
	}
	//document.getElementById("common").style.display="block";
	document.getElementById("checkReferral").style.display="none";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("associated").style.display="none";
	document.getElementById("externalRefer").style.display="none";
	document.getElementById("internalRefer").style.display="none";
	document.getElementsByName("referringInstType")[0].checked=false;
	document.getElementsByName("referringInstType")[1].checked=false;
	document.getElementsByName("isRefferInOut")[0].checked=false;
	document.getElementsByName("isRefferInOut")[1].checked=false;
	//document.getElementById("addImage").style.display="";
	document.getElementsByName("isReferred")[0].value="0";
	fromDeptUnitCode="";
 	refIndex="";	
 	getBillAmountForGrouping('','');
	}
}

function showInternal1(e){
	// alert("showInternal"+document.getElementsByName("isRefferInOut")[0].value);
	
	document.getElementById("internalRefer").style.display="block";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("commonReferInternal").style.display="none";
	document.getElementById("commonReferInternal_1").style.display="none";	
	document.getElementById("commonReferExternal").style.display="none";
	document.getElementById("commonReferExternal_associated").style.display="none";
	document.getElementById("commonReferExternal_other").style.display="none";
	document.getElementById("associated").style.display="none";
	document.getElementById("externalRefer").style.display="none";
	getBillAmountForGrouping("","");
}

function showInternalForGrouping(){
	
	document.getElementById("internalRefer").style.display="block";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("commonReferInternal").style.display="none";
	document.getElementById("commonReferInternal_1").style.display="none";	
	document.getElementById("commonReferExternal").style.display="none";
	document.getElementById("commonReferExternal_associated").style.display="none";
	document.getElementById("commonReferExternal_other").style.display="none";
	document.getElementById("associated").style.display="none";
	document.getElementById("externalRefer").style.display="none";
	
}


function showExternal(e){
	
	//document.getElementById("common").style.display="none";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("commonReferInternal").style.display="none";
	document.getElementById("commonReferInternal_1").style.display="none";
	document.getElementById("internalRefer").style.display="none";
	//document.getElementById("commonReferExternal").style.display="block";
	//document.getElementById("commonReferExternal_associated").style.display="none";
	//document.getElementById("commonReferExternal_other").style.display="none";
	//document.getElementById("associated").style.display="none";

	document.getElementById("externalRefer").style.display="block";
	//document.getElementById("internalRefer").style.display="none";
	document.getElementsByName("referringInstType")[0].checked=true;
	showAssociated(document.getElementsByName("referringInstType")[0]);
	getBillAmountForGrouping("","");
}

function showAssociated(e){

	//document.getElementById("common").style.display="block";
	document.getElementById("commonRefer").style.display="block";
	document.getElementById("commonReferExternal").style.display="block";
	document.getElementById("commonReferExternal_associated").style.display="block";		
	document.getElementById("associated").style.display="block";
	document.getElementById("commonReferExternal_other").style.display="none";
	document.getElementById("internalRefer").style.display="none";

}

function showOthers(e){

	//document.getElementById("common").style.display="block";
	document.getElementById("commonRefer").style.display="block";
	document.getElementById("commonReferExternal").style.display="block";
	document.getElementById("commonReferExternal_other").style.display="block";
	document.getElementById("commonReferExternal_associated").style.display="none";		
	document.getElementById("associated").style.display="none";
	document.getElementById("internalRefer").style.display="none";
}

function removeField(hiddenRemove){
// 	alert("remove Field"); 
	document.forms[0].removeField.value=hiddenRemove;
// 	alert("feiled value==="+document.forms[0].removeField.value);
	return true;
}

function validateSpecialClinic1()
{
	var len;
	var isValid=true;

	count=0;

	val=document.getElementsByName("departmentUnitCode")[0].value;
// 	alert(val);
	//alert("before for");
				if(val=="-1"){
				alert("Please Select Unit");
				
				isValid=false;
			}
			//else{
		//if(isEmpty(document.forms[0].fileNo,"File NO."))
		
				//isValid=true;
//alert("isvalid==="+isValid);
	// 	alert("vali spec");
		document.getElementsByName("isReferred").disabled="true";

return isValid; 
}


function validateToSave(){
// 	alert("validatetosave");
	var isValid; 
	
	
	if(validateSpecialClinic1() &&
	isSelected(document.getElementsByName('patRegCatCode')[0],'Emergency Type')){
// 	alert("inside valdate to save  + true ");
	
	isValid=true;
	}
	else{
	isValid=false;
// 	alert("inside valdate to save + false");
	}
	//if(!validateAgeForDeptToVisit('<%//=RegistrationConfig.MAX_AGE_TO_REGISTER_IN_CHILD_DEPT%>',
	//		'<%//=RegistrationConfig.CHILD_DEPT_CODE%>',document.getElementsByName("departmentUnitCode")[0])){
   // 		return false;
  // 	}
// alert("validatetosave"+isValid);

return isValid;
}
function check(){
//alert("hh"+document.getElementsByName("checkAddStatus")[0].value);
if(document.getElementsByName("checkAddStatus")[0].value=="check")
 		{
 	// 	alert("call on load");
 			document.getElementsByName("isReferred")[0].disabled ="true";
 		}
}
function callThisOnload(){
	// check();
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
	getRefer();
	disableAmount();
 }

function showRefer(){
// 	alert("show refer"+document.getElementsByName("checkAddStatus")[0].value);
	document.getElementsByName("checkAddStatus")[0].value="check";
// alert("show refer"+document.getElementsByName("checkAddStatus")[0].value);
	return true;
	}

function ValidateDepartmentNewDeptVisit() {
         var isValid = true;
   if (document.getElementsByName("isReferred")[0].checked==true)
{	
	// alert("referringInstType "+document.getElementsByName("referringInstType")[1].checked)
	document.getElementsByName("isReferred")[0].value=<%=RegistrationConfig.IS_REFERRED_TRUE%>;
	if (document.getElementsByName("isRefferInOut")[1].checked==true)
	{	
		document.getElementsByName("isRefferInOut")[1].value=<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL%>;
		if(document.getElementsByName("patRefDoctor")[0].value=="")
			{
			alert("Enter the Doctor Name");
			document.getElementsByName("patRefDoctor")[0].focus();
			return false;
			}
		if (document.getElementsByName("referringInstType")[1].checked==true)
		{	
			document.getElementsByName("isAssociated")[0].value=<%=RegistrationConfig.IS_ASSOCIATED_FALSE%>;
			if(document.getElementsByName("patRefHospitalName")[0].value=="")
			{
			alert("Please Enter the Institute Name");
			document.getElementsByName("patRefHospitalName")[0].focus();
			return false;
			}
		}	
		else if (document.getElementsByName("referringInstType")[0].checked==true)
			{
			document.getElementsByName("isAssociated")[0].value=<%=RegistrationConfig.IS_ASSOCIATED_TRUE%>;
			//alert('document.getElementsByName("patRefGnctdHospitalCode")[0].options['+document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex+'].value   ='+document.getElementsByName("patRefGnctdHospitalCode")[0].options[document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex].value)
			if(document.getElementsByName("patRefGnctdHospitalCode")[0].options[document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex].value=="-1")
			{
			alert("Please Select the Institute Name");
			document.getElementsByName("patRefGnctdHospitalCode")[0].focus();
			return false;
			}
			}
			else
			{
			alert("select Referring Institute Type");
			return false;
			}
	}
	else if (document.getElementsByName("isRefferInOut")[0].checked==true)
		{
			var flag=false;
			document.getElementsByName("isRefferInOut")[0].value=<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL%>;
		// 	alert('internal referral');
			var index=0;
			var refferringOPDEpisodeLength=document.getElementsByName("refferringOPDEpisode").length;
	// 	alert('internal referral gfgfgf '+refferringOPDEpisodeLength);
		
			while(index<refferringOPDEpisodeLength)
			{
				if(document.getElementsByName("refferringOPDEpisode")[index].checked==true)
				{
				flag=true;
				break;
				}
				index=index+1;
			}
				if(flag==true)
				{
					return true;
				}
				else
				{
					alert("Please Select Internal Reffered Department");
					return false;
				}
		
		}
		else
		{
			alert("Select Reffering Type");
			return false;
		}
		
		
	}
	else
		document.getElementsByName("isReferred")[0].value=<%=RegistrationConfig.IS_REFERRED_FALSE%>;     
  return isValid;
        

       } 

function populate(e){ 
document.getElementsByName('crNoToRetrieve')[0].value=e;
submitForm("DGNDETAIL"); 

} 
</script> 

<his:css src="/hisglobal/css/tab.css"/>

<%
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
<his:TitleTag name="Emergency Visit">
		
	
	   
	 <b><font size="2" face="Verdana, Arial, Helvetica, sans-serif">
            </font></b>    
		
</his:TitleTag>
<%boolean flagIsStatusNew = false;
			String varStatus = "";
			boolean varIsNewStatus = false;%>
<his:statusNew>
	<%flagIsStatusNew = true;
				varIsNewStatus = true;
				varStatus = "New";%>
				
</his:statusNew>
<his:InputCrNoTag name="EmergencyVisitFB"></his:InputCrNoTag>

<bean:define id="crNo" name="EmergencyVisitFB" property="patCrNo"
	type="java.lang.String" />




<his:statusTransactionInProcess>
<%if (!crNo.trim().equals("")) {%>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />

<%} %>
<input type='hidden' name='crNoToRetrieve'/>
<his:SubTitleTag name="Emergency Visit Stamp">

	<table width="100%">
		   <tr><%--
             <td>	  
             		  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
                      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                      <bean:message key="emrgencytype"/></font>
             </td>       
 			 <td>
 			 	         <html:select name="EmergencyVisitFB"  property="patRegCatCode"  tabindex="1"  styleClass ="regcbo" >   
		  <html:option value="-1">Select Value</html:option>
		  <html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY%>" property = "value" labelProperty = "label"/>
          </html:select>

             </td>
             --%>
             <td width="70%" >
             		  <div align="right">
             		  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             		  		<bean:message key="mlc"/>
             		  </font>
             		  </div>
             </td>
             <td>        
             		<div align="left">              
                     <html:checkbox tabindex="1" name="EmergencyVisitFB" property="isMLC" value="<%=RegistrationConfig.IS_MLC_TRUE%>" />
                    </div>                     
             </td>
             <%-- 
              <td>
             		  <div align="right">
             		  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             		  		<bean:message key="isFree"/>
             		  </font>
             		  </div>
             </td>
             <td>        
             		<div align="left">              
                     <html:checkbox tabindex="1" name="EmergencyVisitFB" property="isFree" value="<%=RegistrationConfig.IS_MLC_TRUE%>" onclick="disableAmount()" />
                    </div>                     
             </td>
             --%>
             </tr>
             
             </table>

	<his:name>
		<bean:message key="lastVisitdate"/>
	</his:name>
</his:SubTitleTag>

	<his:ContentTag>

					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<%int i = 0;%>
						<tr>
						
						<td width="30%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<div align="center"><b>  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="emergencyClinic" /> </font> </b></div></td><!--
						
							<td width="30%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
								 			
							<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="fileNo" /> </font> </b></div></td>
							

							
							
							--><td width="30%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="amountCollected" /> </font> </b></div></td>
								
			<%--				<td width="10%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
								<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> ADD
								 </font> </b></div></td>
								--%>
						</tr>
						</table>
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
						  <td width="30%" class="tdfont">
						  <div align="center">
	       <html:select name="EmergencyVisitFB" property="departmentUnitCode" onchange="getBillAmountForGrouping('','')"  tabindex="1" >
	       <html:option value="-1">Select Value</html:option>
		   <html:options collection = "unitsColl" property  = "value" labelProperty = "label"/>
		   </html:select>
		   </div>
       </td> 	
       				<%--
						<td width="30%" class="tdfont">
						<div align="center">
						<html:text name="EmergencyVisitFB"  readonly="false" styleClass="textbox"  maxlength ="30" property="fileNo"></html:text>
						</div>
						
						</td> 
					--%>
					<td width="30%" class="tdfont">
						<div align="center">
						<html:text name="EmergencyVisitFB"  readonly="true" styleClass="textbox"  maxlength ="30" property="patAmountCollected"></html:text>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<%---					<img class="button" id="addImage"
		src='<his:path src="/hisglobal/images/icn-add.png"/>' style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitFormOnValidate(validateSpecialClinic1(),'ADD');" 
		tabindex="1"  onclick="submitFormOnValidate(validateSpecialClinic1() && showRefer(),'ADD');">
		<html:hidden name="EmergencyVisitFB" property="checkAddStatus" />		
						</div>
						</td> --%>
	<%--					<td width="10%" class="tdfont">
			<div align="center" id="im">
						<img class="button" id="addImage"
		src='<his:path src="/hisglobal/images/icn-add.png"/>' style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitFormOnValidate(validateSpecialClinic1(),'ADD');" 
		tabindex="1"  onclick="submitFormOnValidate(validateSpecialClinic1() && showRefer(),'ADD');">
		<html:hidden name="EmergencyVisitFB" property="checkAddStatus" />
		</div>
						</td> --%>
						</tr>
						</table>
						
						
						
		</his:ContentTag>
		
		
					<%Boolean flag=(Boolean)session.getAttribute("flag"); 
						if(flag.compareTo(new Boolean("true"))==0){					
						%>
			
			
			
			
			
		<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
				
				<% Map outermap=(Map)session.getAttribute("map");
					Map innerMap=(Map)outermap.get("map0");
					System.out.println("innermap======"+innerMap);
				%>
				
				
				<logic:iterate id="outerMap" 
							name="map" indexId="j" type="java.util.Map.Entry"  >
				 <%String ss= outerMap.getClass().getName();
				 System.out.println("vfgffgfg"+ss);
				// System.out.println("vfgffgfg"+outerMap.getValue());
				 %>							
				 
			  
							
			<bean:define  name="outerMap" property="value"  id="innermap" type="java.util.Map"/> 
		<%--	<logic:iterate id="innMap" name="innermap" indexId="k">				
				
		<bean:define name="innMap" property="value" id="textValue" type="java.lang.String"/>
				
				<td width="30%" class="tdfont">
						<div align="center">
						<html:text name="innMap"  readonly="true" styleClass="textbox"  maxlength ="30" property="" value="<%=textValue%>"></html:text>
								
						</div>
						</td> 
				
				</logic:iterate>
				--%>
				
					<tr> 
             	 <td width="60%" class="tdfonthead" ><div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
    	<%=(String)innermap.get("deptName")%>
		</div></td>
		<%--
		     <td width="30%"  class="tdfonthead">
			 <div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		        <bean:message key="fileNo"/>
		        </font>
		     </div>
	       </td>
	
		<td class="tdfont">
		<html:text  name="EmergencyVisitFB" property="fileNo" readonly="true" tabindex="1"  maxlength="30" value="<%=(String)innermap.get("fileNo")%>"/>
        </td>    
        --%>	
	  <td width="10%" class="tdfont"> <img class="button"
		src='<his:path src="/hisglobal/images/icn-min.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) submitFormOnValidate(validateSpecialClinic(),'ADD');"
		tabindex="1" onclick="submitFormOnValidate(removeField('<bean:write name="outerMap" property="key"/>'),'REMOVE')"></td>
		   </tr>
	
				
				
				
				</logic:iterate>
				</table>
				</his:ContentTag>
					<html:hidden name="EmergencyVisitFB" property="removeField" value=""/>
				
				<%} %> 
				
			 <his:ContentTag>
 <table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		<td width="15%" nowrap>
			<div align="right">
			<bean:message key="isreferred"/>
			</div>
		</td>
		<td width="5%">
			<div align="left" id="refer">
			<html:checkbox name="EmergencyVisitFB" property="isReferred" onclick="checkIsReferred(this)" onkeypress="if(event.keyCode==13) checkIsReferred(this);" tabindex="1"/>
			</div>
		</td>
		<td width="40%">
		<div id="checkReferral" style="display:none">
			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="referInternal" />
			</font>
			<html:radio name="EmergencyVisitFB" property="isRefferInOut" tabindex="1" value="I" onclick="showInternal1(this)" />
		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="referExternal" />
			</font>
			<html:radio name="EmergencyVisitFB" property="isRefferInOut" tabindex="1" value="E" onclick="showExternal(this)" />
		</div>
		</td>
		<td width="40%">
			<div id="externalRefer" style="display:none">
				<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="gnctd" />
				</font>
				<html:radio name="EmergencyVisitFB" property="referringInstType" tabindex="1" value="G" onclick="showAssociated(this)" />
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="other" />
				</font>
				<html:radio name="EmergencyVisitFB" property="referringInstType" value="O" tabindex="1" onclick="showOthers(this)" />
				<html:hidden name="EmergencyVisitFB" property="isAssociated" />
				</div>
		</td>
		</tr>
	</table>
	</his:ContentTag>
	
	
		<div id="commonRefer" style="display:none"> 
	<his:ContentTag>
		
			<table width="100%" cellspacing="1" cellpadding="1">
				<tr>
					<td width="25%" class="tdfonthead">
					<div align="right"><font color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referredBy" /></font></div>
					</td>
					<td width="25%" class="tdfont"><html:text name="EmergencyVisitFB"
						styleClass="textbox" maxlength="60" property="patRefDoctor"
						onblur="isAlpha(this,'Referred By Doctor')" tabindex="1"
						onkeypress="return validateAlphabetsOnly(event,this)" /></td>
					<td width="25%" class="tdfonthead">
					<div id="commonReferInternal" style="display:none">&nbsp; &nbsp;
					&nbsp; &nbsp;</div>

					<div id="commonReferExternal" style="display:none">
					<div align="right"><font color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="hospital" /> <bean:message key="name" /></font></div>
					</div>
					</td>

					<td width="25%" class="tdfont">
					<div id="commonReferInternal_1" style="display:none">&nbsp; &nbsp;
					&nbsp; &nbsp;</div>

					<div id="commonReferExternal_associated" style="display:none"><html:select
						name="EmergencyVisitFB" tabindex="1"
						property="patRefGnctdHospitalCode" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>"
							property="value" labelProperty="label" />
					</html:select></div>

					<div id="commonReferExternal_other" style="display:none"><html:text
						name="EmergencyVisitFB" tabindex="1" property="patRefHospitalName"
						onkeypress="return validateAlphabetsOnly(event,this)" maxlength="100"
						styleClass="textbox" size="20" /></div>
					</td>
					</tr>
					
					<tr>
					
					<td width="25%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referFromDept" /> </font></div>
					</td>
					<td width="25%" class="tdfont">
						<html:select name="EmergencyVisitFB" property="patRefGnctdHospitalDept" tabindex="1" styleClass="regCbo" onchange="checkReferDepartment(this)">
						<html:option value="">Select Value</html:option>
						<html:option value="0">Other</html:option>
						<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL%>" property="value" labelProperty="label" /> 
						</html:select> 
					</td>
					<td width="25%" class="tdfonthead" >
    			 	<div  align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="other" />
					<bean:message key="department" /> </font>
					</div>
					
					</td>
					<td width="25%" class="tdfont">
					<html:text name="EmergencyVisitFB"  property="patRefHospitalDeptOther" maxlength="20"  tabindex="1" styleClass="textbox" disabled="true" onkeypress="return validateAlphabetsOnly(event,this)"/>
					</td>
				
				</tr>
			</table>
		
		
		
		<div id="associated" style="display:none">		
		<table width="100%" cellspacing="1" cellpadding="1">
			<tr>
				<td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="referring" /> <bean:message key="crNo" />
				</font></div>
				</td>
				<td width="25%" %  class="tdfont">
				<html:text name="EmergencyVisitFB" tabindex="1" property="patRefGnctdHospitalCrno" maxlength="12" tabindex="1" onkeydown="setPrevValue(this, event);" onkeyup="moveToRight(this, event);" onkeypress="return validateNumeric(event)" styleClass="textbox" />
				</td>
			    <td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="referring" /> <bean:message key="unit" />
				</font></div>
				</td>
				<td width="25%"  class="tdfont">
				<html:text name="EmergencyVisitFB" property="patRefGnctdHospitalDeptUnit" tabindex="1" maxlength="15" styleClass="textbox" onkeypress="return validateAlphaNumericOnly(event,this)"/>
				</td>
				
			</tr>
		</table>
		</div> 
 
 </his:ContentTag>
 </div>


<div id="internalRefer" style="display:none">
			<!--- ReferInternal -------  Details of all open episodes-->

		<%-- <logic:empty name="<%=RegistrationConfig.ARR_EPISODE_REFER_VO%>">

			<%System.out.println("empty ARR_EPISODE_REFER_VO");

							%> --%>

			
				

				<his:ContentTag>
				<logic:notEmpty
				name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>">
				<bean:define id="OPD_OPEN_EPISODES"
					name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>"
					type="hisglobal.vo.EpisodeRefDtlVO[]" />
				

					<table width="100%" border="0" cellspacing="1" cellpadding="0">
					
						<tr>
							<td width="5%" class="tdfonthead"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"></td>

							<td width="45%" class="tdfonthead" ><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="referFromDept" /> </font> </b></div></td>



							<td width="45%" class="tdfonthead"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="referFromDeptUnit" /> </font> </b></div></td>



						</tr>

						<logic:iterate id="ARR_OPD_OPEN_EPISODE_VO"
							name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>" indexId="index" >
							<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="episodeCode" id ="epCode"/>
							<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="fromDepartment" id ="frmDep"/>
							<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="fromDepartmentUnit" id ="frmDepUnit"/>
							<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="fromDepartmentCode" id ="frmDepCode"/>
							<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="fromDepartmentUnitCode" id ="frmDepUnitCode"/>
<%String hh=(String)epCode; %>
<%String strFrmDep=(String)frmDep; %>
<%String strFrmDepUnit=(String)frmDepUnit; %>
<%String strFrmDepCode=(String)frmDepCode; %>
<%String strFrmDepUnitCode=(String)frmDepUnitCode; %>
							<tr>
								<td width="5%" class="tdfont">
								<div align="center"><input type="radio"
									name="refferringOPDEpisode"
									onclick="getBillAmountForGrouping('<%=strFrmDepUnitCode%>','<%=String.valueOf(index.intValue())%>');"
									value="<%=hh %>" tabindex="1"/></div>
									
								</td>
								
								<html:hidden
									name="EmergencyVisitFB" property="episodeCode"
									value="<%=hh%>"/>
									
								<td width="45%" class="tdfont">
								<div align="center"><bean:write name="ARR_OPD_OPEN_EPISODE_VO"
									property="fromDepartment" />
									<html:hidden
									name="EmergencyVisitFB" property="fromDepartment"
									value="<%=strFrmDepCode%>"/> 
									</div>
								</td>


								<td width="45%" class="tdfont">
								<div align="center"><bean:write name="ARR_OPD_OPEN_EPISODE_VO"
									property="fromDepartmentUnit" />
									<html:hidden
									name="EmergencyVisitFB" property="fromDepartment"
									value="<%=strFrmDepUnitCode%>"/>
									</div>

								</td>

							</tr>
						</logic:iterate>

					</table>
					
			</logic:notEmpty>
				</his:ContentTag>
				
			</div>	
							<%
		varStatus="InProcess";
	%>
</his:statusTransactionInProcess>

	
	<his:statusInProcessWithJsp>
		
	</his:statusInProcessWithJsp>
	<!-- ...............Code for Button Tool Bar.......... -->
	
	
	
	 
	
<his:ButtonToolBarTag>
	<%
	Status objStatus= (Status) request.getAttribute(Config.STATUS_OBJECT);
	if(objStatus!= null){
		HashSet statuslist= objStatus.getStatusList();
	System.out.println(statuslist.contains(objStatus.TRANSINPROCESS));
		if (statuslist.contains(objStatus.TRANSINPROCESS)&& (!statuslist.contains(objStatus.ERROR_DA)) )
		{%>

	<div align="center"><img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer 
		onkeypress="if(event.keyCode==13) submitFormOnValidate(<%=(Boolean)session.getAttribute("flag")%> || validateToSave() && ValidateDepartmentNewDeptVisit(),'SAVE');"
		tabindex="1" onclick="submitFormOnValidate(<%=(Boolean)session.getAttribute("flag")%> || validateToSave() && ValidateDepartmentNewDeptVisit(),'SAVE');">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
		tabindex="1" style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');"> <img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
		tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
	<%} else{ %>
	<div align="center"><img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"
		style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');"> <img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
		tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
	<%} }%>
</his:ButtonToolBarTag>
<!-- .......End........Code for Button Tool Bar.......... -->

<his:status />


<html:hidden name="EmergencyVisitFB" property="hmode"  value="unspecified"/>
<html:hidden name="EmergencyVisitFB" property="selectedFromDeptUnit" />
<html:hidden name="EmergencyVisitFB" property="patBillAmountWithoutGrouping" />
<html:hidden name="EmergencyVisitFB" property="patCrNo"/>	
<html:hidden name="EmergencyVisitFB" property="referingRowIndex" />
<html:hidden name="EmergencyVisitFB" property="referInternalExternal" />
<html:hidden name="EmergencyVisitFB" property="checkref" />		
<html:hidden name="EmergencyVisitFB" property="patAge" />		
