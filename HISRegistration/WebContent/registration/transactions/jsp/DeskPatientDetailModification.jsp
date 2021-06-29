<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet"	type="text/css">
<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>

<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>

<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
 <script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/moment.min.js"></script>

<title>Patient Detail Modification</title>



</head>
<script type="text/javascript">
var registrationDeskDefaultCountryCode = '<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>';
var isAddressAdded  = <%=RegistrationConfig.IS_ADDRESS_ADDED%>;
var isAddressModified = <%=RegistrationConfig.IS_ADDRESS_MODIFIED%>;
var LOCAL_LANGUAGE = "<%= (String)request.getSession().getAttribute(RegistrationConfig.LOCAL_LANGUAGE) %>";
	
	$(window).on("load.loading1", function() {
		initMultilingual(LOCAL_LANGUAGE);
		if(document.getElementsByName("AfterGo")[0].value!='0')
			{
			document.getElementById("divBeforeGo").style.display = "none";
			document.getElementById("divAfterGo").style.display = "";
			document.getElementById("divAfterGoId").style.display ="";
			document.getElementById("divGoId").style.display = "none";
			patDtlModificationDesk.fetchDefaultValues();
			}
		
			callThisOnload();
	});
	
	
	function callMenu(url)
	{
		//alert('menu called with url: '+ url);
		var targetURL = url;// + "?varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
		
		
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
		document.forms[0].action = mode + "PatientDetailModDesk.action";
		document.forms[0].submit();

	}
	
	function submitClear(cnt)
	{
		document.forms[0].action =  cnt + ".action";
		document.forms[0].submit();
	}
	
	function submitCancelAction(cnt) {
		document.forms[0].action = "cancel" + cnt + ".action";
		document.forms[0].submit();
	}
	function submitClearAction(cnt) {
		document.forms[0].action = "Clear" + cnt + ".action";
		document.forms[0].submit();
	}
	function submitFormonRadio(mode,obj) {
		document.forms[0].action = mode + "PatientDetailModDesk.action?crno="+obj.value;
		document.forms[0].submit();

	}
	function populate(selectedarray)
	{
		var strHtml ="";
		var elem = document.getElementById("hiddenDivVerification");
		for( i=0; i<selectedarray.length; i++)
		{
			var arrayOfDocsData=selectedarray[i].split("|");
	  	  
			strHtml+="<input type='hidden' name='arrSelectedVerifyDocs' value='"+selectedarray[i]+"'/>	";
			strHtml+=arrayOfDocsData[1]+"&nbsp; &nbsp;";
		}
		elem.innerHTML= ":: &nbsp;" + strHtml;
		
	}  
	
	function callThisOnload()
	{
		if(document.getElementsByName("patCrNo")[0])	document.getElementsByName("patCrNo")[0].focus();

			enableRelation();
			checkReferDepartment();
			if (document.getElementsByName("AfterGo")[0].value != '0') 
			{
				showDivAgeDobNew();
				$('[name="patAge"]').val($('[name="defaultPatAge"]').val());
				$('[name="patAgeUnit"]').val($('[name="defaultPatAgeUnit"]').val());
				setMotherValidRule();
				//$("#patDOBId").val("");
			}
			
	}
	

	function enableRelation()
	{	
		var obj = document.getElementsByName("requestBy")[0];
		if(!obj)	return;
		if(obj.value=="1")
			document.getElementsByName("requestRelation")[0].disabled=false;
		else 
			document.getElementsByName("requestRelation")[0].disabled=true;
		if(obj.value=="1")
		{
			document.getElementById("policeDetailDiv").style.display="none";
			document.getElementById("requesterDIV").style.display="block";
			document.getElementById("relationshipSelfId").style.display="none";	
			document.getElementById("relationshipRelativeId").style.display="block";
		}
		else if(obj.value=="2")
		{
			document.getElementById("policeDetailDiv").style.display="block";
			document.getElementById("requesterDIV").style.display="block";
		}
		else
		{
			document.getElementById("policeDetailDiv").style.display="none";
			document.getElementById("requesterDIV").style.display="none";
			document.getElementById("relationshipSelfId").style.display="block";	
			document.getElementById("relationshipRelativeId").style.display="none";	
		}
		
	}
	
	function checkReferDepartment()
	{

			if(document.getElementsByName('patRefGnctdHospitalDept')[0]){
			var selectObject=document.getElementsByName('patRefGnctdHospitalDept')[0];
			if(selectObject.value=='0'){
				document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false;
				document.getElementsByName('patRefHospitalDeptOther')[0].focus();
			}
			else{
				document.getElementsByName('patRefHospitalDeptOther')[0].value="";
				document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true;
				document.getElementsByName('patRefGnctdHospitalDeptUnit')[0].focus();
			}
			}
		//}	
	}
	function validateAlphaNumericForEmailOnly(e,obj)
	{//alert("in");
		var key;
		var keychar;

		if (window.event)
		   key = window.event.keyCode;
		else if(e)
		   key = e.which;
		else
		   return true;
		keychar = String.fromCharCode(key);

		keychar = keychar.toUpperCase();
		//alert(key);
		// control keys
		if ((key==null) || (key==0) || (key==8) ||
			(key==9) || (key==13) || (key==27) || (key==95))
		   return true;
		
		else if((getCursorIdex(obj)>0) && (key==32))
			return true
		else if((getCursorIdex(obj)>0) && (key==46))
			return true	
		// alphas and numeric
		else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_@.$").indexOf(keychar) > -1))
		   return true;
			   
		   else if ((("0123456789").indexOf(keychar) > -1))
		   return true;
		else
		   return false;
	}


</script>
<body>
	<center>
		
		<s:actionerror />

		<s:form action="PatientDetailModificationActionDesk">
			<s:set name="theme" value="simple" scope="page" />
			<div class="wrapper rounded ">
				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100 "><s:text name="global.patient"/>&nbsp;<s:text name="detail"/>&nbsp;<s:text name="modification"/></div>
					</div>
				</div>

				<his:InputCrNoTag name="PatienModFB"/>
		
			<div id="divBeforeGo"> 
				<%int i=0; %>
				<s:iterator value="#session.patVoList" status = "key">
				<%i++;  %>
				</s:iterator>
				<%System.out.println(i); %>
			<%if(i>=1){ %>
			<div class="div-table-listing rounded">
			
			<div class="div-table-row ">
								<div class="div-table-col  labelLeft width10 ">
								<b><s:text name="select"/></b>
								</div>
								<div class="div-table-col  labelLeft width20 ">
										<b><s:text name="crNO"/></b>
								</div>
								<div class="div-table-col  labelLeft width20 ">
										<b><s:text name="first"/>&nbsp;<s:text name="global.name"/> </b>
								</div>
								<div class="div-table-col  labelLeft width20 ">
										<b><s:text name="middle"/>&nbsp;<s:text name="global.name"/></b>
								</div>
								<div class="div-table-col  labelLeft width20 ">
										<b><s:text name="last"/>&nbsp;<s:text name="global.name"/></b>
								</div>
						</div>
				<s:iterator value="#session.patVoList" status = "key">
						<div class="div-table-row ">
								<div class="div-table-col  control width10 ">
									<input align="left" type="radio"	name="crNoToModify" onclick="submitFormonRadio('GETPATDTL',this);" value='<s:property value="patCrNo"/>' tabindex="1" />
								</div>
								<div class="div-table-col control width20 ">
										<s:property value="patCrNo"/>
								</div>
								<div class="div-table-col  control width20 ">
										<s:property value="patFirstName"/>
								</div>
								<div class="div-table-col control width20 ">
										<s:property value="patMiddleName"/>
								</div>
								<div class="div-table-col  control width20 ">
										<s:property value="patLastName"/>
								</div>
						</div>
				</s:iterator>
				
				</div>
				<%} %>
			</div>				

	 <div id="divAfterGo" style="display:none"> 
				<div class="div-table">
					<div class="div-table-row title">
						<div class="div-table-col"><s:text name="global.patient"/>&nbsp;<s:text name="details"/> </div>
					</div>

					<div class="div-table-row">
						<div class="div-table-col label" style="width: 16%;"><s:text name="global.patient"/>&nbsp;<s:text name="global.name"/></div>
						<div class="div-table-col" style="width: 16%;">&nbsp;<font color="red">*</font><s:text name="first"/></div>
						<div class="div-table-col" style="width: 16%;">&nbsp;<s:text name="middle"/></div>
						<div class="div-table-col" style="width: 16%;">&nbsp;<s:text name="last"/></div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col" style="width: 16%;"></div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patFirstName"  maxlength="33"
								onkeyup="multilingualConversion(this,document.getElementById('patFirstNameInMultiLangId'));">
							</s:textfield>
						</div>
						<!-- <div class="div-table-col label" style="width: 16%;">Patient Middle Name</div> -->
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patMiddleName"  maxlength="33"
								onkeyup="multilingualConversion(this,document.getElementById('patMiddleNameInMultiLangId'));">
							</s:textfield>
						</div>
						<!-- <div class="div-table-col label" style="width: 16%;">Patient Last Name</div> -->
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patLastName"  maxlength="33"
								onkeyup="multilingualConversion(this,document.getElementById('patLastNameInMultiLangId'));">
							</s:textfield>
						</div>

					</div>
					
					<div class="div-table-row" style="display:none;">
						<div class="div-table-col" style="width: 16%;"></div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patFirstNameInMultiLang" id="patFirstNameInMultiLangId" maxlength="33"
										onblur="callOnBlur();" onfocus="callOnClick();">
							</s:textfield>
						</div>
						<!-- <div class="div-table-col label" style="width: 16%;">Patient Middle Name</div> -->
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patMiddleNameInMultiLang" id="patMiddleNameInMultiLangId"  maxlength="33"
										onblur="callOnBlur();" onfocus="callOnClick();">
							</s:textfield>
						</div>
						<!-- <div class="div-table-col label" style="width: 16%;">Patient Last Name</div> -->
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patLastNameInMultiLang" id="patLastNameInMultiLangId" maxlength="33"
										onblur="callOnBlur();" onfocus="callOnClick();">
							</s:textfield>
						</div>

					</div>

					<div class="div-table-row">
						<div class="div-table-col label" style="width: 16%;"><font color="red">*</font>
							<s:radio label="Age/Dob" name="isActualDob"		list="#{'0':'Age','1':'Dob'}"	onclick="showDivAgeDobNew();setMotherValidRule();" value="isActualDob" />
						</div>
						
						 <div class="div-table-col" style="width: 84%" >
								<div id="divAge"  class="div-table">
									<div class="div-table-row">
										<div class="div-table-col " style="width: 100%;">
											<s:textfield id="patAgeId" name="patAge" maxlength="5" size="5" onblur="setMotherValidRule();"></s:textfield>
											<select id="patAgeUnitId" name="patAgeUnit" style="width: 75px" onblur="setMotherValidRule();showDivAgeDobNew();"><option value="0">Select Value</option></select>
										</div>
									</div>
								</div>
								<div id="divDob">
								
									<div class="div-table-col control" id="" style="width: 16%;">
										<input id="patDOBId" type="text" name="patDOB" onblur="setMotherValidRule();">
									</div>
									
								</div>
						
							</div>
						
					</div>

						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;">	<font color="red">*</font><s:text name="gender"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;" list="#{'0':'Select Value'}" name="patGenderCode" id="patGenderCodeId" />
								<input name="patGender" type="hidden">
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="maritalStatus"/> </div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;" list="#{'0':'Select Value'}" name="patMaritalStatusCode" value="patMaritalStatusCode" />
							</div>
						</div>

					<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="fathersName"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patGuardianName"  maxlength="60">
								</s:textfield>
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="husbandName"/> </div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patHusbandName" maxlength="60">
								</s:textfield>
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="motherName"/> </div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patMotherName"  maxlength="60">
								</s:textfield>
							</div>

						</div>

					<div class="div-table-row">

							<div class="div-table-col label" style="width: 16%;"><s:text name="patCaste"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patCasteCode"
									value="patCasteCode" />
								<input name="patCaste" type="hidden">
							</div>

							<div class="div-table-col label" style="width: 16%;"><s:text name="religion"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select  cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patReligionCode"
									value="patReligionCode" />
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="birplace"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patBirthPlace"  maxlength="50" />
							</div>

						</div>					
					
					
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;"><s:text name="patOccupation"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<select name="patOccupation" id="patOccupationId"
									style="width: 145px">
									<option value="0">Select Value</option>
								</select>
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="monthlyIncome"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patMonthlyIncome"  maxlength="10">
								</s:textfield>
							</div>
							<div class="div-table-col label" style="width: 16%;"></div>
							<div class="div-table-col control" style="width: 16%;">
						
							</div>
						</div>
						
						<%-- <div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;">
								<s:text name="adhar"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patNationalId" maxlength="12">
								</s:textfield>
							</div>
							<div class="div-table-col label" style="width: 16%;">
								<s:text name="otherId"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patDocType" id="patDocTypeId"
									value="patDocType" />
									<input type="hidden" name="patDocTypeName"  />
							</div>
							<div id= "divCardNoId" class="div-table-col control" style="width: 32%;">
								<div class="div-table">
									<div class="div-table-row">
										<div class="div-table-col label" style="width: 50%;"><s:text name="global.cardno"/>
											</div>
										<div   class="div-table-col control" style="width: 50%;">
											<s:textfield name="patCardNo"  >
											</s:textfield>
										</div>
									</div>
								</div>
							</div>
						</div> --%>
				</div>

			
			
			 	<div class='div-table'>
					<div class="div-table-row title">
						<div class="div-table-col">
							<s:text name="address" />&nbsp;<s:text name="detail" />
						</div>
					<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="country"/> 
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patAddCountryCode" id="patAddCountryCodeId"
									value="patAddCountryCode" />
						<input name="defaultpatAddCountryCode" type="hidden">
						<input name="patAddCountry" type="hidden">
					</div>
					<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="state"/>  
					</div>
					<div id="divStateComboId" class="div-table-col control" style="width: 16%;">
						<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patAddStateCode" id="patAddStateCodeId"
									value="patAddStateCode" />
						<input name="defaultpatAddStateCode" type="hidden">
						<input name="patAddState"  type="hidden">
					</div>
					<div id="divStateTextId" class="div-table-col control" style="width: 16%; display: none;">
					<s:textfield name="patAddState"  id="patAddStateId" maxlength="50" />
					</div>			
				    </div>
				</div>
						
				<div class="div-table">

					
				<div class="div-table-row">
						<div class="div-table-col label" style="width: 16%;"><s:text name="hno"/> </div>
						<div class="div-table-col control" style="width: 16%;">
						<s:textfield name="patAddHNo"   size='10' maxlength="60">
							</s:textfield>
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="street"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddStreet" maxlength="60" />
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="location"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddCityLoc" maxlength="60" />
						</div>

					</div>
					<div class="div-table-row">
					
					
					<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="district"/></div>
					
					<div id="divDistrictComboId" class="div-table-col control" style="width: 16%;">
					
					<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patAddDistrictCode" id="patAddDistrictCodeId"
									value="patAddDistrictCode" />
					
					<input name="defaultpatAddDistrictCode" type="hidden">
					<input name="patAddDistrict" type="hidden">
				</div>
				<div id="divDistrictTextId" class="div-table-col control" style="width: 16%; display: none;">
					<s:textfield name="patAddDistrict" maxlength="15" />
				</div>
				
						
						
						<div class="div-table-col label" style="width: 16%;"><s:text name="city"/><s:text name="slash"/><s:text name="village"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddCity" maxlength="60" />
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="pin"/> </div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddPIN" maxlength="6"/>
						</div>

					</div>
					<div class="div-table-row" >
						<div class="div-table-col label" style="width: 16%;"><s:text name="global.landmark"/>  </div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddLandMarks" maxlength="60" />
						</div>
						
						<div class="div-table-col label" style="width: 16%;"> <s:text name="areaCategory"/>	   </div>
						<div class="div-table-col control" style="width: 16%;">
							<select name="patIsUrban" style="width :145px">
								<!-- <option value="-1">Select Value</option> -->
							</select>
						</div>
						
						<div class="div-table-col label" style="width: 16%;"><s:text name="global.email"/>	 </div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddEmailId" maxlength="70" />
						</div>
						
					</div>
					<div class="div-table-row">

						<div class="div-table-col label" style="width: 16%;"><s:text name="global.phone"/>&nbsp;<s:text name="number"/>
							</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddPhoneNo" maxlength="15" />
						</div>

						<div class="div-table-col label" style="width: 16%;"><s:text name="global.phone"/>&nbsp;<s:text name="owner"/>
							</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:select  cssStyle="width: 145px;"  list="#{'1':'Self (patient)','3':'Other','5':'Family member'}" name="patAddPhoneOwner"
								value="patAddPhoneOwner"  />
						</div>

						<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="mobileNo"/>
							</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddMobileNo" maxlength="10" />
						</div>
					</div>
				
				
					
					</div>
					
<%-- </s:if> --%><!-- For transaction in process -->
				</div>
				
							
				<div class="div-table-button">
					<div class="div-table-row footerBar">
					<div class="div-table-col "></div>
					</div>
						<div class="div-table-row emptyBar">
						<div class="div-table-col"></div>
					</div>
					<div class="div-table-row" align="center">
					 <div id="divAfterGoId" style="display:none">  
				 	<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
					<a id="#" class="button"	onclick="submitClearAction('PatientDetailModDesk');"><span class="clear"><s:text name="clear"/></span></a> 
					<a href="#" class="button" onclick="submitCancelAction('PatientDetailModDesk');"><span class="cancel"><s:text name="cancel"/></span></a>
					</div> 
					<div id="divGoId" >  
						<a id="#" class="button"	onclick="submitClear('PatientDetailModDesk');"><span class="clear"><s:text name="clear"/></span></a> 
						<a href="#" class="button" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>
					</div>
					</div>
				</div>
	<div class="div-table">
	
	</div>
<s:hidden name="AfterGo" value="%{AfterGo}" />
<s:hidden name="addModify" />

<s:hidden name="tempIsActualDOB" />
<s:hidden name="defaultPatAge"></s:hidden>
<s:hidden name="defaultPatAgeUnit"></s:hidden>
<s:hidden name="defaultPatDOB"></s:hidden>
<s:hidden name="patPrimaryCatCode"></s:hidden>
<s:hidden name="seniorCitizenAgeLimit"></s:hidden>
<s:hidden name="seniorCitizenCatCode"></s:hidden>
<s:hidden name="patPrimaryCat"></s:hidden>

</div>
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
		</s:form>
		
				<div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col"> 
						<div class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="errorMessage" /></div>
					</div>
				</div>
			</div>
	<div class="div-table-simple" id="fatherorSpouseError" style="display: none">
		<div class="div-table-row">
			<div  class="div-table-col alignLeft fontError" style="width: 100%">Father Name Or Spouse Name Is Compulsary</div>
		</div>
	</div>
		<script type="text/javascript"
	src="./../../registration/transactions/js/dskOpdPatDtlModification.js" /></script>
	</center>
</body>
</html>