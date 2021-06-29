<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import="registration.config.RegistrationConfig"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<link rel="stylesheet" href="../../hisglobal/css/jquery-ui.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.border .div-table-col{
border: 1px solid black;
}
</style>

<script language="JavaScript" type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/js/basic.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/js/popup.js'></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script type="text/javascript">

function callMenu(url)
{
	var targetURL = url;
	var elemFrame = parent.document.getElementById("frmMain");
	if(elemFrame!=null){
		elemFrame.src=targetURL;
		elemFrame.refresh();
	}
	else{
		if(typeof $('#tabframe')!='undefined'){
			var tab = window.parent.$('#tabframe').tabs('getSelected');
			var index = window.parent.$('#tabframe').tabs('getTabIndex',tab);
			window.parent.$('#tabframe').tabs('close',index);			
		}
	}

}


function submitForm(mode) {
	document.forms[0].action = mode + "AppointmentCancellation.action";
	document.forms[0].submit();

}



function aptcanclePopup(obj)
{
	//alert("123");
	//alert(obj);
	$('[name=patAptNo]')[0].value=obj;
	var aptNo = obj;	
	var action = "/HISRegistration/registration/transactions/openPopupAppointmentCancellation.action?aptNo="+ aptNo;
	openURLInPopup(action, '500', '200');
	//openPopup(action);
}


</script>
</head>
<body>

<s:form action="AppointmentCancellation">
    
    <div class="wrapper rounded">
	    
		<div class="div-table">
			  <div class="div-table-row ">
				<div id="divTitleId" class="div-table-col title width100 ">
						<s:text name="Apt_appointment"/>&nbsp;<s:text name="cancellation"/>  
				</div>
			  </div>
			  
			 
			  <s:if test="%{#session.patVoList.length>0}">
			  <div class="div-table-listing rounded width100 height100">
				<div class="div-table-row listHeader">
					
					
					<div class="div-table-col width18" align="center">
					<s:text name="Apt_appointment" />&nbsp;<s:text name="number" />						
					</div>
					<div class="div-table-col width12" align="center">
					<s:text name="name" />					
					</div>
					<div class="div-table-col width8" align="center">
					<s:text name="age" />					
					</div>
					<div class="div-table-col width18" align="center">
					<s:text name="contactNo" />					
					</div>
					<div class="div-table-col width10" align="center">
					<s:text name="Apt_slot" />					
					</div>
					<div class="div-table-col width18" align="center">
					<s:text name="department" />&nbsp;<s:text name="unit" />											
					</div>
				</div>
				
				
				
				<s:iterator status="status" value="%{#session.patVoList}">
				<div class="div-table-row listData">
					<!--  <div  class="div-table-col control" style="width:4%;">
								<input type="radio"	name="aptNoToCancel" onclick="submitFormonRadio(this);" value='<s:property value="patAptNo"/>' tabindex="1" />
					</div>-->
					
					<div class="div-table-col width18" align="center">
					
						<s:property value="%{patAptNo}"/>
					</div>
					
					
					<div class="div-table-col width12" align="center">
						<s:property value="%{patFirstName}"/>
					</div>
					<div class="div-table-col width8" align="center">
						<s:property value="%{patAge}"/>
					</div>
					<div class="div-table-col width18" align="center">
						<s:property value="%{patAddContactNo}"/>
					</div>
					<div class="div-table-col width10" align="center">
						<s:property value="%{patAptSlot}"/>
					</div>
					<div class="div-table-col width18" align="center">
						<s:property value="%{department}"/>
					</div>
					<div id="divaptcancelId" class="div-table-col control" style="width:5%;" >
					<img id="aptcancelid"  name="aptcancelFlag"  style="cursor: pointer;" align="right" src="../../hisglobal/images/cancel.png" onclick="aptcanclePopup(<s:property value="patAptNo"/>)"/>
				     </div>
				</div>
				
				</s:iterator>

               </div>
               	</s:if>
               	</div>
             
			<div class="div-table-row " align="right">
			<div class="div-table-col control"  style="width:30%;">
          
					<s:select cssStyle="width: 75%" key="searchId" name="searchId"
				 				list="#{'1':'Appointment No','2':'Name','3':'Contact No'}"   >
				 	</s:select>	

					
				</div>
				
				<div class="div-table-col control" style="width: 25%;">
					<input name="searchValue" maxlength="10" type="text" class="input75prcnt"  tabindex="1" >
				</div>
			  <a  href="#" class="button" id="searchid"><span class="save" onclick="submitForm('SEARCH');"><s:text name="search" /></span></a>
			  <a  tabindex="1" href="#" class="button" id="clearId" onclick="submitForm('NEW');"><span class="clear"><s:text name="clear" /></span></a>	
			</div>
			
	
			
			
		
		       <s:else>
					<div class="div-table-listing rounded width100 height100">
					<div  class="div-table-row " align="center">
					<div  class="div-table-col width100" align="center">
					     <font color="red">No Appointments are available for cancellation</font>
                    </div>
			    	</div>
					</div>
				</s:else>
				

		
		   <div class="div-table-button">
			<div class="div-table-row footerBar">
			<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
			</div>
			<div  class="div-table-row " align="center">
			<a href="#" class="button" id="cancelId" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a> 
			</div>	
			
		</div>
		
		<s:hidden name="patAptNo"/> 
		<s:hidden name="remarks"/> 
		
	    </div>
	    <cmbPers:cmbPers></cmbPers:cmbPers>
	    <s:token/>
	    </s:form>


</body>
</html>