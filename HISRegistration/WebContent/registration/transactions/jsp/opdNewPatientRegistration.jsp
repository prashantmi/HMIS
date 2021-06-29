<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./../../registration/transactions/js/opdNewPatientRegistration.js" /></script>
<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>


<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script> 
<title>Patient Registration</title>
<s:head/>
</head>

<body>
<center><br><br><br><br>
<s:actionerror/>

<s:form action="NewPatientRegistration">
    <%-- <s:set name="theme" value="simple" scope="page" /> --%>
    <div class="wrapper rounded">
	    <!-- <h1>Patient Registration</h1> -->
		<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="new"/>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="registration"/>   
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 48%">
						<font color="red">*</font><s:text name="visiting"/>&nbsp;<s:text name="department"/></div>
				<div class="div-table-col width control" style="width: 52%">
						<s:select name="departmentCode" cssStyle="width:145px" list="#session.optionDepartmentList"  listKey="value" listValue="label" />
				</div>
			</div>
		</div>

		<div class="div-table">
			<div class="div-table-row title">
				<div class="div-table-col">
						<s:text name="global.patient"/>&nbsp;<s:text name="detail"/>  
				</div>
			</div>

			<div class="div-table-row">
				<div class="div-table-col width label" style="width: 48%">
						<s:text name="global.patient"/>&nbsp;<s:text name="category"/>   
				</div>
				<div class="div-table-col width control" style="width: 52%">
						<s:select name="patPrimaryCatCode" cssStyle="width:145px" label="Patient Category" list="#session.optionPrimaryCategory"  listKey="value" listValue="label" />
				</div>
			</div>
			
				<div class="div-table-row">
					<div class="div-table-col label" style="width: 16%;"><s:text name="global.patient"/>&nbsp;<s:text name="global.name"/>:</div>
					<div class="div-table-col" style="width: 16%;">&nbsp;<s:text name="first"/></div>
					<div class="div-table-col" style="width: 16%;">&nbsp;<s:text name="middle"/></div>
					<div class="div-table-col" style="width: 16%;">&nbsp;<s:text name="last"/></div>
				</div>
				<div class="div-table-row">
					<div class="div-table-col" style="width: 16%;"></div>
					<!-- <div class="div-table-col label" style="width: 16%;">Patient First Name</div> -->
					<div class="div-table-col control" style="width: 16%;">
						<input name="patFirstName" maxlength="50" type="text">
					</div>
					<!-- <div class="div-table-col label" style="width: 16%;">Patient Middle Name</div> -->
					<div class="div-table-col control" style="width: 16%;">
						<input name="patMiddleName" maxlength="50" type="text">
					</div>
					<!-- <div class="div-table-col label" style="width: 16%;">Patient Last Name</div> -->
					<div class="div-table-col control" style="width: 16%;">
					<input name="patLastName" maxlength="50" type="text">
					</div>
					
				</div>
				
				<div class="div-table-row">
				    <div class="div-table-col label" style="width: 16%;">
				    	<s:radio label="age" name="isActualDob" list="#{'1':'Age','2':'Dob'}" value="1" onclick="ageSelection();" />
				    </div>
				    <div id="divAge">
				    	<div class="div-table-col control" style="width: 16%;">
					       <input name="patAge" maxlength="3" type="text" size="4">
					    </div>
					    <div class="div-table-col control" style="width: 16%;">
							<s:select name="patAgeUnit" cssStyle="width:100px" list="#session.optionAgeType"  listKey="value" listValue="label" />
						</div>	
				    </div>
				    
				    <div id="divDob" style="display: none;">
				    	<s:set name="dob" value="patDOB"></s:set>
				    	<s:property value="dob" />
				    	<div id="divPatDOB"></div>
				    	<script type="text/javascript">DateValidator.setup("divPatDOB","patDOB",'<s:property value="dob" />',"dd-MM-yyyy","textbox");</script>
				    </div>			
					
				</div>
		
				
				<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><s:text name="gender"/>
				</div>
				<div class="div-table-col control"  style="width: 16%;">
						<s:select name="patGenderCode" cssStyle="width:145px" list="#session.optionGender"  listKey="value" listValue="label" />
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="maritalStatus"/> 
				</div>
				<div class="div-table-col control"  style="width: 16%;">
						<s:select name="patMaritalStatusCode" cssStyle="width:145px" list="#session.optionMaritalStatus"  listKey="value" listValue="label" />
				</div>
			
			    </div>
			    
				<div class="div-table-row">
					<div class="div-table-col label" style="width: 16%;"><s:text name="fathersName"/>  
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<input name="patGuardianName" maxlength="50" type="text">
					</div>
					<div class="div-table-col label" style="width: 16%;"><s:text name="husbandName"/>   
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<input name="patHusbandName" maxlength="50" type="text">
					</div>
					<div class="div-table-col label" style="width: 16%;"><s:text name="motherName"/></div>
					<div class="div-table-col control" style="width: 16%;">
					<input name="patMotherName" maxlength="50" type="text">
					</div>
					
				</div>
				<div class="div-table-row">
					
				<div class="div-table-col label" style="width: 16%;"><s:text name="patCaste"/> 
				</div>
				<div class="div-table-col control" style="width: 16%;">
						<s:select name="patCasteCode" cssStyle="width:145px" list="#session.optionCaste"  listKey="value" listValue="label" />
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="religion"/> 
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<s:select name="patReligionCode" cssStyle="width:145px" list="#session.optionReligion"  listKey="value" listValue="label" />
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="birplace"/>  
				</div>
				<div class="div-table-col control" style="width: 16%;">
				<input name="patBirthPlace" maxlength="50" type="text">
				</div>
					
				</div>
				<div class="div-table-row">
					<div class="div-table-col label" style="width: 16%;"><s:text name="patOccupation"/>   
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<s:select name="patOccupation" cssStyle="width:145px" list="#session.optionOccupationDetail"  listKey="value" listValue="label" />
					</div>
					<div class="div-table-col label" style="width: 16%;"><s:text name="monthlyIncome"/>  
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<input name="patMonthlyIncome" maxlength="50" type="text">
					</div>
					<div class="div-table-col label" style="width: 16%;"><s:text name="amountCollected"/>   
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<%-- <s:textfield name="patAmountCollected"></s:textfield> --%>
						<input name="patAmountCollected" type="text">
					</div>
				</div>
				<div class="div-table-row">
					<div class="div-table-col label" style="width: 16%;"><s:text name="nationalid"/> 
					</div>
					<div class="div-table-col control" style="width: 16%;">
					<input name="patNationalId" maxlength="16" type="text">
					
					</div>
					<div class="div-table-col label" style="width: 16%;"><s:text name="otherId"/> </div>
					<div class="div-table-col control" style="width: 16%;">
						<s:select name="patDocType" cssStyle="width:145px" list="#session.optionVerificationDoc"  listKey="value" listValue="label" />
					</div>
					<div class="div-table-col label" style="width: 16%;"><s:text name="global.cardno"/> 
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<s:textfield name="patCardNo"></s:textfield>
					</div>
					
				</div>
		</div>
	
		<div class="div-table">
			<div class="div-table-row title">
				<div class="div-table-col" style="width:32%;">
						<s:text name="adddetail"/>   
				</div>
				<div class="div-table-col label" style="width: 16%;"> <s:text name="country"/>  
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<s:select name="patAddCountryCode" cssStyle="width:145px" list="#session.optionCountry"  listKey="value" listValue="label" />
					</div>
					<div class="div-table-col label" style="width: 16%;"><s:text name="state"/>   
					</div>
					<div class="div-table-col control" style="width: 16%;">
					<s:select name="patAddStateCode" cssStyle="width:145px" headerKey="0" headerValue="Select State"  list="#session.optionState"  listKey="value" listValue="label" />
					</div>
			</div>

				<div class="div-table-row">
					<div class="div-table-col label" style="width: 16%;"><s:text name="hno"/> 
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<input name="patAddHNo" maxlength="50" type="text">
					</div>
					<div class="div-table-col label" style="width: 16%;"><s:text name="street"/>
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<input name="patAddStreet" maxlength="50" type="text">
					</div>
					<div class="div-table-col label" style="width: 16%;"><s:text name="location"/>
					</div>
					<div class="div-table-col control" style="width: 16%;">
					<input name="patAddCityLoc" maxlength="50" type="text">
					</div>
					
				</div>
				<div class="div-table-row">
					<div class="div-table-col label" style="width: 16%;"><s:text name="district"/>
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<s:select name="patAddDistrictCode" label="District" list="#session.stateWiseDistrictList"  listKey="value" listValue="label" />
					</div>
					<div class="div-table-col label" style="width: 16%;"><s:text name="city"/><s:text name="slash"/><s:text name="village"/>
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<input name="patAddCity" maxlength="50" type="text">
					</div>
					<div class="div-table-col label" style="width: 16%;"><s:text name="pin"/>
					</div>
					<div class="div-table-col control" style="width: 16%;">
					<input name="patAddPIN" maxlength="50" type="text">
					</div>
					
				</div>
				
				<div class="div-table-row" id="divRefDtlId" style="display: none;" >

					<div class="div-table-col label" style="width: 16%;"><s:text name="global.landmark"/>  </div>
					<div class="div-table-col control" style="width: 16%;">
						<input name="patAddLandMarks" maxlength="50" type="text">
					</div>
					
					<div class="div-table-col label" style="width: 16%;"> <s:text name="areaCategory"/> </div>
					<div class="div-table-col control" style="width: 16%;">
						<select name="patIsUrban" >
							<option value="0">Select Area Category</option>
						</select>
					</div>
					
					<div class="div-table-col label" style="width: 16%;"><s:text name="global.email"/> </div>
					<div class="div-table-col control" style="width: 16%;">
						<input name="patAddEmail" maxlength="50" type="text">
					</div>
					
				</div>
				<div class="div-table-row">
							
					<div class="div-table-col label" style="width: 16%;"><s:text name="global.phone"/>&nbsp;<s:text name="number"/> </div>
					<div class="div-table-col control" style="width: 16%;">
						<input name="patAddPhoneNo" maxlength="50" type="text">
					</div>
					
					<div class="div-table-col label" style="width: 16%;"> <s:text name="global.phone"/>&nbsp;<s:text name="owner"/>   
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<select name="patAddPhoneOwner">
							<option value="1">Self (patient)</option>
							<option value="2">ANM</option>
							<option value="3">Doctor or any other health provider</option>
							<option value="4">Neighbour</option>
							<option value="5">Family member</option>
						</select>
						<input name="" maxlength="50" type="text">
					</div>
					
					<div class="div-table-col label" style="width: 16%;">Mobile No</div>
					<div class="div-table-col control" style="width: 16%;">
						<input name="patAddMobileNo" maxlength="10" type="text">
					</div>
				</div>
				
				
		</div>
		<div class="div-table" >
				<div class="div-table-row title">
					<div class="div-table-col" style="width: 80%">
							Refer Detail
					</div>
					<div class="div-table-col label" style="width: 20%">
							Is Referred<input type="checkbox">
					</div>
				</div>
		</div>
		<div class="div-table">
			<div class="div-table-row">
				<div id="divReferredInstitute" class="div-table-col label" style="width: 50%;">
					<s:radio name="referringInstType"
							list="#{'G':'Associated Institute','O':'Other'}"
							onclick="showdivhoscode(this)"
							value="%{referringInstType}" />
				</div>
				<div id='divRefHosCode'>
					<div class="div-table-col label" style="width: 25%;"> 	Institute Name  </div>
					<div class="div-table-col control" style="width: 25%;">
						<s:select name="patRefGnctdHospitalCode" cssStyle="width:145px" list="#session.optionRefHospital"  listKey="value" listValue="label" />
					</div>
				</div>
				
				<div id='divRefHosname' style='display: none;'>
					<div class="div-table-col label" style="width: 50%;">
						Institute Name
					</div>
					<div class="div-table-col control" style="width: 100%;">
						<input name="patRefHospitalName" maxlength="50" type="text" size="20">
					</div>
				</div>
			</div>
		</div>
		
		<div class="div-table" id="divReferred">
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%;">
					Referring Institute CR No.
				</div>
				<div class="div-table-col control" style="width: 25%;">
					<input type	  ="text" 	name="patRefGnctdHospitalCrno" 
						maxlength ="13"  
						onkeydown ="setPrevValue(this, event);"
						onkeyup   ="moveToRight(this, event);"
						onkeypress="return validateNumeric(event)">
				</div>
				<div class="div-table-col label" style="width: 25%;">
					Doctor Name
				</div>
				<div class="div-table-col control" style="width: 25%;">
					<input type	  ="text" 	name="patRefDoctor" 
						maxlength ="13"  
						onkeydown ="isAlpha(this,'Referred By Doctor')"
						onkeypress="return validateAlphabetsOnly(event)">
				</div>
			</div>
			
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%;">
					Referring Institute Department.
				</div>
				<div class="div-table-col control" style="width: 25%;">
					<s:select name="patRefGnctdHospitalDept" cssStyle="width:145px" onchange="checkReferDepartment(this)" 
							  list="#session.optionReferDepartmentList"  listKey="value" listValue="label" />
				</div>
				<div class="div-table-col label" style="width: 25%;">
					Other Department.
				</div>
				<div class="div-table-col control" style="width: 25%;">
					<input name="patRefHospitalDeptOther" maxlength="20" type="text" size="20" onkeypress="return validateAlphabetsOnly(event,this)" disabled="disabled">
				</div>
			</div>
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 25%;">
					Referring Institute Unit.
				</div>
				<div class="div-table-col control" style="width: 25%;">
					<input name="patRefGnctdHospitalDeptUnit" maxlength="15" type="text" size="20" onkeypress="return validateAlphabetsOnly(event,this)" >
				</div>
				<div class="div-table-col label" style="width: 25%;"></div>
				<div class="div-table-col control" style="width: 25%;"></div>
				
			</div>
		</div>
		

		
		<div class="div-table-button">
			<div class="div-table-row">
				<div class="div-table-col footerBar">
				</div>
			</div>
			<div class="div-table-row">
			<div class="div-table-col  width30 ">
				</div>
			<div class="div-table-col  width10 ">
				<a href="#" class="button" onclick="return validateNewDemand();"><span class="save">Save</span></a>
				</div>
				<div class="div-table-col  width10 ">
				<a href="#" class="button" onclick="initPage();"><span class="clear">Clear</span></a>
				</div>
				<div class="div-table-col  width10 ">
				<a href="#" class="button" onclick="initPage();"><span class="cancel">Cancel</span></a>
			</div>
			</div>
			
		</div>
	</div>
	
		<cmbPers:cmbPers></cmbPers:cmbPers>
		<s:token/>
	
</s:form>
</center>
</body>
</html>