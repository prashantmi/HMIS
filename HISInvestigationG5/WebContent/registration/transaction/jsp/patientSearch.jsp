<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page %>
<html>
<!--  
		*Added :For Search/ Advance Feature 
		*By    :Raj Kumar
		* On   :05/11/19
		* -->	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script language="JavaScript" src="/HIS/hisglobal/js/responsiveDataTablewithPlus/jquery-3.3.1.min.js"></script>
<!-- <link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css"> -->
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<!-- <link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">-->
<!-- <link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">  -->


<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/js/basic.js'></script>
<script type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<%-- <script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script> --%>


<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>

<!-- Latest compiled and minified CSS -->

<link rel="stylesheet" href="/HIS/hisglobal/css/BootStrap4/bootstrap.min.css">
<!-- jQuery library -->
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->

<script language="JavaScript" src="/HIS/hisglobal/js/Bootstrap4/bootstrap.min.js"></script>

<link rel="stylesheet" href="/HIS/hisglobal/css/BootStrap4/FontAwesome/css/all.min.css">
<link href="/HIS/hisglobal/css/NewDatePicker/datepicker.css" rel="stylesheet" type="text/css" />
<script src="/HIS/hisglobal/js/NewDatePicker/bootstrap-datepicker.js"></script>
    


    
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/drDeskAssets/SweetAlertNew/sweetalert.min.js"></script>

<title>Patient Search</title>
<script>
	$(window).on("load.loading1", function(){
		patientSearch.fetchDefaultValues();
		
		
	}); 
</script>
 <script>
 $(function(){
	 
	 $("#searchId").css({"visibility": "visible"});
	 $("#submitId").css({"visibility": "hidden"});
	 $("#MoreDId").css({"visibility": "hidden"});
	 $("#AdvanceSearchId").on('show.bs.collapse hidden.bs.collapse', function () {
	        $("#MoreDId").find('.fa').toggleClass('fa-plus fa-minus');
	      
	       
	    });
	    
	 
	 $("#uniqueIdTypeid").on("click", function(){
		 if ($("#uniqueIdTypeid").prop("checked")) {
			   
			 $("#MoreDId").css({"visibility": "visible"});
			 $("#submitId").css({"visibility": "visible"});
			 
			 if ($(window).width() < 1098) {
				    $("#demographicSearchTile").find(".col-sm-2").removeClass("col-sm-2").addClass("col-sm-6");
				    
				      }
				   /* else{
				     $("#demographicSearchTile").find(".col-sm-6").removeClass("col-sm-6").addClass("col-sm-2");  
				    } */
			 
			 
					}
		 else{
			 $("#MoreDId").css({"visibility": "hidden"});
		 			}
	 	});
	 
	 /* $("#MoreDId").on("click", setTimeout(function(){
		 
		 if($("#AdvanceSearchId").hasClass("collapse show"))
			 {
			 //alert("hascls");
			 $("#unkowndivid").show();
			 }
		 else
			 {
			 //alert("hascls not");
			 $("#unkowndivid").hide();
			 }
	 }, 5000)); */
	
 });

 
	
 </script>
 
  
  <script>
$(function(){
			$('.datepicker').datepicker({
			    format: 'dd-M-yyyy',
			    defaultDate: new Date(),
			    autoclose: true,
			    onSelect: function (dateText, inst) {
					onClickDateFn($(this));
					}
			});
		});
		
	 function onClickDateFn(obj){
	obj.focus();
	
						}
</script>

<script>
 $(function(){
		$("#cancelId_new").on("click", function(e){
			window.parent.$("#searchPopupModalID").find(".close").trigger("click");
		});
	});
 </script>
 <script>
     $(window).on("load", function(){
     if ($(window).width() < 1098) {
    $("#demographicSearchTile").find(".col-sm-2").removeClass("col-sm-2").addClass("col-sm-6");
    
      }
        
         
         
    $(window).on("resize", function() {
    	if ($(window).width() < 1098) {
      $("#demographicSearchTile").find(".col-sm-2").removeClass("col-sm-2").addClass("col-sm-6");
      
        }
          /* else{
             $("#demographicSearchTile").find(".col-sm-6").removeClass("col-sm-6").addClass("col-sm-2");  
          } */
    });
      
      

     
});
      
  
  


    </script>
<style>
		
		
    	body {
            
            /* background: #eaeef3;  */
            color:  rgba(75,75,75, 0.7);
            font-family: "Helvetica Neue",Helvetica,Arial,sans-serif !important;
            font-size: 14px;
            font-weight: 500;
        }
        
        .header {
            padding: 5px 4px;
            text-align: center;
            color: white;
            height: 31px;
            font-size: 14px;
            padding-bottom: 8px;
            margin-bottom: 8px;
            border-radius: 2px;
            /*  background: #00b0c0; */
            /* background-image: linear-gradient(to right,#49B2F3, #02629C); */
            background-image: linear-gradient(to right, #49B2F3, #02629C);
            box-shadow: 0 10px 4px -9px #0e0000;



        }

        .formSection {
            margin-left: 0px;
            margin-right: 0px;
            background-color: #f0f0f0;
            padding: 0% 0 2% 0;
            margin: 2% 0;
            transition: 0.5s;
            box-shadow: 0 0.5px 20px 2px #b0acac;

        }

        .formSection:hover {
            box-shadow: 0 2px 20px 2px #000;


        }
     .subHeaders {
            font-weight: 500 !important;
            /* color: rgba(33, 32, 32, 0.7); */
             color: rgba(75,75,75, 0.7);
            font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
            font-size: 19px !important;
            padding-bottom: 6px !important;
            letter-spacing: 1px;
        }

        
         .divSection {
            margin-left: 0px;
            margin-right: 0px;
            background-color: #fff;
            padding: 1% 2% 1% 2%;
            margin: 2% 0;
            transition: 0.5s;
            box-shadow: 0 0.5px 5px 2px #b0acac;
            padding: 5px 20px;
            font-size: 14px;
            font-weight:500;
            border-radius: 5px;
        }

        .divSection:hover {
            box-shadow: 0 2px 20px 2px #b0acac;


        }
         
        select {
        	
            font-weight: 500 !important; 
            height: 100%;
            font-size: 14px !important;
        }

        label, font {
            color:rgba(75,75,75, 0.7);
           font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
            font-weight: 700 !important;
             font-size: 14px !important;

                    
        }
        .fontred {
    		color: red;
    		font-weight: 900;
		}
		.DatePicker_Icon_Inside{ 
			top:10px !important;
			left:169px !important;
		 }
		 .row{
		 padding-bottom: 5px !important;
		 }
		​
    </style>
</head>

<body>
<center>
<s:actionerror/>

<s:form action="PatientSearch">
    <div class="wrapper roundes container-fluid" id="nonprintableDiv1">
	    <!-- <h1>Patient Registration</h1> -->
<div class=""> 
	 <div class="">
		<div id="uniqueIdSearchTile" class="row">
			<div class="col-sm-12">
					<div class="row" id="newUniqueTypeId">
						<div class="col-sm" id="divUniqueMobileNo">
								<input type="radio" name="uniqueIdType"  tabindex="1" value="5" onclick="showSerachBox(this)" checked="checked" />
								<label><s:text name="mobileNo" /></label>	
		   				</div>
						<div class="col-sm" id="divUniqueEmpID">
								<input type="radio" name="uniqueIdType" tabindex="1" value="4" onclick="showSerachBox(this)" />
								<label><s:text name="employeeNo" /></label>
						</div>
						<div class="col-sm" id="divUniqueAlternateID">
								<input type="radio" name="uniqueIdType" tabindex="1" value="3" onclick="showSerachBox(this)" />
								<label><s:text name="alternateId" /></label>
						</div>
						<div class="col-sm">
							<input type="radio" name="uniqueIdType" id="uniqueIdTypeid" tabindex="1" value="7" onclick="showSerachBox(this)" />
								<label><s:text name="Demographic Search" /></label>						
						</div>
					</div>
					<div id="uniqueIdSearchTileControls" class="row" >
						<div id="divCatCardId" style="display: block;margin-top:15px;">
								<div class="row" align="right" style="padding-left:53px;">
									<div id="divCatCardNoLabelId" class="col-sm">
										<label><s:text name="mobileNo" /></label>
										<font class="fontred" >*</font>
									</div>
									<div class="col-sm">
										<input name="patIdNo" tabindex="1" type="text" class="form-control" maxlength="10" />
									</div>
								</div>
							
				        </div>
				        <div class="row" id="divAlternateSearchDtlId" style="display: none;margin-top:15px;padding-left:81px;">
				        	<div class="col-sm-4"><label><s:text name="search" />&nbsp;<s:text name="by" />&nbsp;<s:text name="global.id" /></label><font class="fontred" >*</font></div>
							<div class="col-sm-8">
								<s:select name="patDocType" id="patDocTypeId" tabindex="1"
										list="#session.optionVerificationDoc"  listKey="value" listValue="label" 
										headerKey="-1" headerValue="Select Value" class="form-control"/>
								<input type="hidden" name="patDocTypeName" class="form-control" />
							</div>
								<!-- <div class="col-sm-6"></div> -->
   						</div>
				        <div id="divCardNoId" class="row" style="display: none;margin-top:15px;" align="right">
								<div class="col-sm-4"><label><s:text name="patCardNo" /></label><font class="fontred" >*</font></div>
								<div class="col-sm-8">
										<input name="patCardNo" tabindex="1" type="text" class="form-control" >
								</div>
						</div>
						<!-- <div class="col-sm" style="margin-left:370px; margin-top:-38px;">
							<button type="button" class="btn btn-primary btn-md" id="goPatDtl">Go</button>
						</div> -->
					</div>
				
			</div>
			
		</div>	
				
				<div id="uniqueIdSearchTileOld" class="" style="display:none"> 			
					<div class="">
						<div class="col-sm-12  subHeaders" style="">
							<div class="col-xs-6 col-sm-12 text-left">
								<s:text name="unique"/>&nbsp;<s:text name="global.id"/>&nbsp;<s:text name="search" />
							</div>
						</div>
						<div class="row" style="padding-left:5px;">
							<div class="col-sm-6">
								<s:radio id="isglobal" name="isGlobal" value="%{isGlobal}" list="#{'0':'Hospital Wise','1':'Global'}" onchange="patientSearch.showHospitalTile();"></s:radio>
							</div>
							<div class="col-sm"><label><s:text name="global.hospital"></s:text></label><font class="fontred">*</font>
							</div>
							<div class="col-sm-4" id="divHospitalComboId" style="display:block">
								<s:select name="hospitalCode" id="hospitalCode" tabindex="1" class="form-control"
									list="#session.optionHospitalList"  listKey="value" listValue="label"/>
							</div>
							<div class="col-sm-3" style="display:none"  id="divHospitalAllComboId">
								<s:select name="hospitalCode" id="hospitalCode" disabled="true"  
										class="form-control" list="#{'000':'All'}"/>
							</div>
						</div>
					</div>
					<div style="margin-top:18px"></div>
					<div class="row" style="padding-left:18px;">
						<div class="col-sm" id="divUniqueUHID">
								<input type="radio" name="uniqueIdType" tabindex="1" value="2" onclick="showSerachBox(this)" />
								<label><s:text name="aadharNo" /></label>
								
						</div>
						<div class="col-sm" id="divUniqueAlternateID">
								<input type="radio" name="uniqueIdType" tabindex="1" value="3" onclick="showSerachBox(this)" />
   								<label><s:text name="alternateId" /></label>
						</div>
						<div class="col-sm" id="divUniqueEmpID">
								<input type="radio" name="uniqueIdType" tabindex="1" value="4" onclick="showSerachBox(this)" />
   								<label><s:text name="employeeNo" /></label>
						</div>
						<div class="col-sm" id="divUniqueMobileNo">
								<input type="radio" name="uniqueIdType" tabindex="1" value="5" onclick="showSerachBox(this)" />
   								<label><s:text name="mobileNo" /></label>
						</div> 
						<div class="col-sm" id="divUniqueMobileNo">
								<input type="radio" name="uniqueIdType" tabindex="1" value="6" onclick="showSerachBox(this)" />
								<label><s:text name="global.email" /><s:text name="global.id" /></label>	
						</div>
						
					</div>
					<br>
					<div class="row">
						<div id="divCatCardId" style="display: block;">
								<div class="row"  style="padding-left:19px;">
									<div id="divCatCardNoLabelId" class="col-sm-7">
										<label><s:text name="adhar" /></label>
										<font class="fontred" >*</font>
									</div>
									<div class="col-sm-5">
										<input name="patIdNo" tabindex="1" type="text" class="form-control"/>
									</div>
								</div>
						</div>
				        <div class="col-sm-12" id="divAlternateSearchDtlId" style="display: none;">
				        	<div class="col-sm-3"><label><s:text name="search" />&nbsp;<s:text name="by" />&nbsp;<s:text name="global.id" /></label><font class="fontred" >*</font></div>
							<div class="col-sm-3">
								<s:select name="patDocType" id="patDocTypeId" tabindex="1"
										list="#session.optionVerificationDoc"  listKey="value" listValue="label" 
										headerKey="-1" headerValue="Select Value" class="form-control"/>
								<input type="hidden" name="patDocTypeName" class="form-control" />
							</div>
								<div class="col-sm-6"></div>
   						</div>
				        <div id="divCardNoId" class="row" style="display: none;">
								<div class="col-sm-6"><label><s:text name="patCardNo" /></label><font class="fontred" >*</font></div>
								<div class="col-sm-6">
										<input name="patCardNo" tabindex="1" type="text" class="form-control" >
								</div>
						</div>
					</div>
				</div>
		</div>				
			       
	<!-- <div style="margin-top:24px;"></div> -->		
		
	<div class="text-left" id="demographicSearchTile" style="display:none">
		<div class="">
			<div class="row">
			 
				<div class="col-sm-6 subHeaders">
					<s:text name="demographic" />&nbsp;<s:text name="global.detail" />&nbsp;<s:text name="search" />
				</div>
				<div class="col-sm-6 text-right" id="unkowndivid" style="visibility: hidden; color: #0b3da1; font-size: 14px; font-weight: 600;">
					<s:checkbox name="isUnknown"  id="isUnknownId" onclick="checkUnknown" align="right">&nbsp;&nbsp;&nbsp; <s:text name="unknown" /> &nbsp;&nbsp;&nbsp;</s:checkbox>
				</div>
			
			
			</div>
			<div style="margin-top:15px"></div>
			
			<div class="row" id="divFirstRow">
							<div class="col-sm-2 col-xs-6"><label><s:text name="global.name" /><font class="fontred">*</font> </label></div>
							<div class="col-sm-2 col-xs-6">
								<s:textfield name="patFirstName" class="form-control" maxlength="33" tabindex="1"></s:textfield>
				
							</div>
							
			<div id="patNameSec" style="display:none;">				
			<div class="div-table-col label" style="width: 10%"><s:text name="middle" />&nbsp;<s:text name="global.name" /></div>
			<div class="div-table-col control" style="width: 14%">
			<s:textfield name="patMiddleName" maxlength="33" tabindex="2" cssStyle="width:100%"></s:textfield>
<!-- 					<input name="patMiddleName" maxlength="33" type="text" size="19"> -->
			</div>
<!-- 			</div> -->
<!-- 			<div class="div-table-col" style="width: 25%">			 -->
			<div class="div-table-col label" style="width: 10%"><s:text name="last" />&nbsp;<s:text name="global.name" /> </div>
			<div class="div-table-col control" style="width: 14%">
			<s:textfield name="patLastName" maxlength="33" tabindex="2"  cssStyle="width:100%"></s:textfield>
<!-- 						<input name="patLastName" maxlength="33" type="text" size="19"> -->
			</div>
			</div>
					<div class="col-sm-2 col-xs-6"><label><s:text name="age" />&nbsp;<s:text name="range" /></label> </div>
							<div class="col-sm-2 col-xs-6">
								<s:select name="patAge" id="patAge"  class="form-control"
									list="#session.ageRangeOptionList"  tabindex="2" listKey="value" listValue="label" headerKey="-1" headerValue="Select Value" />
							</div>
							<div class="col-sm-2 col-xs-6"><label><s:text name="gender" /></label><font class="fontred">*</font></div>
							<div class="col-sm-2 col-xs-6">
									<s:select name="patGenderCode" id="patGenderCodeId" tabindex="1"
											class="form-control" list="#session.optionGender"  listKey="value" listValue="label" 
											headerKey="-1" headerValue="Select Value" />
							</div>
							
							
							
							
							
							
				</div>
				<div class="row" id="divSecondRow">
							
							
							<div class="col-sm-2"><label><s:text name="fathersName"/></label></div>
							<div class="col-sm-2">
								<s:textfield name="patGuardianName" class="form-control" maxlength="33" tabindex="2"></s:textfield>
							</div>
							<div class="col-sm-2"><label><s:text name="husbandName"/></label></div>
							<div class="col-sm-2">
								<s:textfield name="patHusbandName" class="form-control" maxlength="33" tabindex="2"></s:textfield>
							</div>
							<div class="col-sm-2"><label><s:text name="motherName" /></label></div>
							<div class="col-sm-2">
								<s:textfield name="patMotherName" class="form-control" maxlength="33" tabindex="2"></s:textfield>
							</div>
				</div>
					
			
		<div class="collapse" id="AdvanceSearchId">
			<div class="row"> 
		<div class="col-sm-2"><label><s:text name="registrationBetween"/></label></div>	
					<!-- <div  id="divFromDate"> -->
					       
					       <div class="col-sm-2" >
					       <input id="patFromDateId" class="form-control datepicker" placeholder="From Date" tabindex="1" type="text" readonly="readonly" name="fromDate"  style="" onblur="patientSearch.checkFromDateValid();">
							</div>
							<div class="col-sm-2" >
							<input id="patToDateId" class="form-control datepicker"  placeholder="To Date" tabindex="1" type="text"  readonly="readonly" name="toDate" style="" onblur="patientSearch.checkToDateValid();">
							</div>					
					       <!-- </div> -->
					       
					</div>
					
				<div class="row" id="divThirdRow">
						<div class="col-sm-2"><label><s:text name="country" /><font class="fontred">*</font></label></div>
						<div class="col-sm-2">
							<s:select name="patAddCountryCode" id="patAddCountryCodeId"
									class="form-control" list="#session.optionCountry"  tabindex="1" listKey="value" listValue="label" 
									headerKey="-1" headerValue="Select Value" onchange="patientSearch.onchange_patAddCountryCode();"/>
							<input name="defaultpatAddCountryCode" class="form-control" tabindex="1" type="hidden">
							<input name="patAddCountry" class="form-control" tabindex="1" type="hidden">		
						</div>

						<div class="col-sm-2"><label><s:text name="state" /></label></div>
						<div class="col-sm-2" id="divStateComboId">
							<s:select name="patAddStateCode" tabindex="1" id="patAddStateCodeId" 
									class="form-control" list="#session.optionState"  listKey="value" listValue="label" 
									headerKey="-1" headerValue="Select Value" onchange="patientSearch.onchange_patAddStateCode();"/>
							<input name="patAddState" class="form-control" tabindex="1"  type="hidden">
							<s:hidden name="defaultpatAddStateCode" class="form-control" tabindex="1" ></s:hidden>
						</div>
						<div class="col-sm-2"  id="divStateTextId" style="display:none ">
							<s:textfield name="patAddState" class="form-control" id="patAddStateId" maxlength="50" tabindex="1"></s:textfield>
						</div>			

						<div class="col-sm-2"><label><s:text name="district" /></label></div>
						<div class="col-sm-2" id="divDistrictComboId">
							<s:select name="patAddDistrictCode" id="patAddDistrictCodeId" 
								class="form-control" tabindex="1" list="#session.stateWiseDistrictList"  listKey="value" listValue="label" headerKey="-1" headerValue="Select Value" />
							<input name="patAddDistrict" tabindex="1" type="hidden">			
							<s:hidden name="defaultpatAddDistrictCode"></s:hidden>
						</div>
						<div class="col-sm-2" id="divDistrictTextId" style="display:none ">
							<s:textfield name="patAddDistrict" class="form-control" tabindex="1" id="patAddDistrictId" maxlength="15"></s:textfield>
			
						</div>	
						
				</div>
			
			<div class="row" id="divFourthRow">

				<div class="col-sm-2"><label><s:text name="city" /></label></div>
						<div class="col-sm-2">
							<s:textfield name="patAddCity" class="form-control" maxlength="33" tabindex="2"></s:textfield>
						</div>
						<div class="col-sm-2"><label><s:text name="street" /></label> 
				</div>
				<div class="col-sm-2">
				<s:textfield name="patAddStreet" class="form-control" maxlength="33" tabindex="2"></s:textfield>

				</div>
				<div class="col-sm-2"><label><s:text name="hno" /></label> 
				</div>
				<div class="col-sm-2">
					<s:textfield name="patAddHNo" class="form-control" maxlength="33" tabindex="2"></s:textfield>

				</div>

		

				
			</div>
			
			<div class="row" id="dateRow" id="divFifthRow" >
			<div class="col-sm-2"><label><s:text name="location" /></label>
				</div>
				<div class="col-sm-2">
				<s:textfield name="patAddCityLoc" class="form-control" maxlength="33" tabindex="2"></s:textfield>

				</div>

				<div class="col-sm-2"><label><s:text name="pin" /></label></div>
				<div class="col-sm-2">
				<s:textfield name="patAddPIN" class="form-control" maxlength="6" tabindex="2"></s:textfield>

				</div>
				
					
					
			    </div> 
	</div>				<!-- End:Advance Search-->
			</div>
		</div>
		<div class="row text-center">
			<div class="col-sm-12" align="center">
				<a href="#" tabindex="1" class="btn btn-success" id="submitId"><span class="save"><s:text name="search" /></span></a>
				<a href="#" tabindex="1" class="btn btn-success" id="searchId"><span class="save"><s:text name="search" /></span></a> 	
				<a href="#" tabindex="1" class="btn btn-warning" id="clearId" ><span class="clear"><s:text name="clear" /></span></a>
				<a href="#" tabindex="1" class="btn btn-danger"  id="cancelId_new"><span class="button" data-dismiss="modal" aria-label="Close"><s:text name="cancel" /></span></a>
			
			<span class="" id="MoreDId" type="button"  style=" visibility: hidden; background: none; border: none; color: #ff7a00; font-weight: 600; font-size: 16px; padding: 5px; float: right;" data-toggle="collapse" data-target="#AdvanceSearchId" aria-expanded="false" aria-controls="AdvanceSearchId">
                                <i class="fa fa-plus" aria-hidden="true"></i> Advance Search..	
                            </span>
                            
			</div>
			
		</div>
		
		
		
		
					<div  class="" id="divPatDetilId"></div>
	 		 </div> <!-- divSection close -->	
	 	</div><!-- Container div close -->
	 				
	
	<input type="hidden" name="hiddenPatUniqueIdType" />
	<input type="hidden" name="hiddenPatDocType" />
	<cmbPers:cmbPers></cmbPers:cmbPers>	
<s:token></s:token>	
</s:form>
		<script type="text/javascript"
			src="./../../registration/transactions/js/patientSearch.js" /></script>
		<script type="text/javascript">
// alert(!$("#uniqueIdSearchTile").is(':hidden'));
// if($("#uniqueIdSearchTile").is(':hidden')){
// if($('[name="isDemographicSearch"]')[0].checked){

// }}
$(window).on("load.loading1", function() 
		{
patientSearch.showDivDate();
		});
</script>
</center>
<div id='loadingmessage' style='display:none'>
       <img src='/HISRegistration/hisglobal/images/ajax-loader.gif'/>
</div>
</body>
</html>