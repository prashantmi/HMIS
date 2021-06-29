<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page%>
<html>
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/bootstrap/css/bootstrap.min.css">  
	    <script src="/HIS/hisglobal/drDeskAssets/jquery/jquery-3.3.1.min.js"></script> 
	    <script src="/HIS/hisglobal/drDeskAssets/popper/popper.min.js"></script>
	    <script src="/HIS/hisglobal/drDeskAssets/bootstrap/js/bootstrap.min.js"></script>
    	<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/fontawesome/css/all.css">
    	<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/fontawesome/css/fontawesome.min.css">
    	<link rel="stylesheet" href="/HISDRDESK/new_opd/css/mainDesk.css">
		<%-- <script src="/HISDRDESK/new_opd/js/mainDeskScript.js"></script> --%>
		<%-- <script type="text/javascript" src="/HISDRDESK/new_opd/js/myScript.js"></script> --%> 
		<%-- <script type="text/javascript" src="/HISDRDESK/new_opd/js/prescriptionScript.js"></script>
		<script src="/HIS/hisglobal/drDeskAssets/tippy/tippy.all.min.js"></script> --%>
		<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/css/style.css">
		<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/SweetAlertNew/sweetalert.min.css">
    	<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/SweetAlertNew/sweetalert.min.css.map">
    	<script src="/HIS/hisglobal/drDeskAssets/SweetAlertNew/sweetalert.min.js"></script>
    	
    	
		<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
		<!-- <link href="./../../hisglobal/css/layout.css" rel="stylesheet"	type="text/css"> -->
		<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="./../../hisglobal/css/jqueryExtValidationToolTip.css">
		<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
		
		<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
		<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
		<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
		
		<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
		<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>
		
		<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
		<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
		<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script> 
		
		<style>
			select
			{
				max-width:inherit !important;
			}
			.fontErrorDrDesk{
				font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
				font-weight: bold;
				color: red;
				font-size: 22px;
			}
			.fontNormalMessageDrDesk{
				font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
				color: #013157;
			 	font-weight: bold;
			 	font-size: 22px;
			}
			label{
				text-align:right !important;
			}
			.marginTopCls{
				margin-top:10px;
			}
		</style>
		
		<script type="text/javascript">
	
			$(window).on("load.loading1", function() {
					/* var data=document.getElementsByName("printHtml")[0].value;
					var elem = document.getElementById("divPrintId");
					elem.innerHTML= data; */
					
					//alert(document.getElementsByName("afterGo")[0].value);
			/* if(document.getElementsByName("afterGo")[0].value!='0')
			{  
				document.getElementById("divAfterGo").style.display = "";
				document.getElementById("divAfterGoId").style.display ="";
				//patReferral.fetchDefaultValues(); */
			
			
			
			departmentList=(document.getElementsByName("departmentCode")[0]).innerHTML;
			departmentUnitList=(document.getElementsByName("departmentUnitCode")[0]).innerHTML;
			getFilteredDeptUnit(document.getElementsByName("selectedEpisode")[0]); 
			
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
				document.forms[0].action = mode + "PatientReferral.action";
				document.forms[0].submit();
		
			}
			function submitCancelAction(cnt) {
				document.forms[0].action = "cancel" + cnt + ".action";
				document.forms[0].submit();
			}
	
	
			function populate(selectedarray)
			{
				var strHtml ="";
				var elem = document.getElementById("hiddenDivVerification");
				for(i=0; i<selectedarray.length; i++)
				{
					var arrayOfDocsData=selectedarray[i].split("|");
			  	  
					strHtml+="<input type='hidden' name='arrSelectedVerifyDocs' value='"+selectedarray[i]+"'/>	";
					strHtml+=arrayOfDocsData[1]+"&nbsp; &nbsp;";
				}
				elem.innerHTML= ":: &nbsp;" + strHtml;
				
			}  
	
		</script>
		
		<title>Refer Patient</title>
		
		<script type='text/javascript' > 
			window.hasHold = false;
		</script>	
	
		<s:head />

	</head>

	
	<div id="patReferralTileContainer" class="container-fluid prescriptionContainer" style="padding-left: 10px;padding-right: 10px; height:70vh; ">
		
		<p class="fontErrorDrDesk"><s:property value="errorMessage" /></p>
		<p class="fontNormalMessageDrDesk"><s:property value="normalMessage" /></p>
		
		<s:form action="PatientReferral">
			<s:set name="theme" value="simple" scope="page" />
			
			<!-- <div class="wrapper rounded" style="padding-left: 10px;padding-right: 10px;background-color: #eaeef3;" > -->
				<div class="div-table" style="display:none;"> <!-- Hidden but needed -->
					<div class="div-table-row ">
						<div class="div-table-col title width100 ">
						<s:text name="refer"/>&nbsp;<s:text name="global.patient"/></div>
					</div>
				</div>

				<div style="display:none;">	 <!-- Hidden but needed -->
					<his:InputCrNoTag />
				</div>
		
				<s:hidden name="goFlag" value="%{goFlag}" />
				<s:hidden name="AfterGo" value="%{goFlag}" />
				<s:hidden name="isDesk" value="%{isDesk}" />	
				<s:set name="goFlagForJsp" value="goFlag"></s:set>
				<s:hidden name="showAppointmentDateInsidePopup" value="1"/>
				<div id="divAfterGo"> 
					<div style="display:none;">	 <!-- Hidden but needed -->
						<s:if test="%{#goFlagForJsp!=0}">			
							<s:hidden id="printHtmlId" name="printHtml" value="%{errorMessage}"/>
							<%-- <s:action name="patientDetail" executeResult="true"></s:action> --%>
							<his:PatientTileTag crNo="${patCrNo }" ></his:PatientTileTag>
							<%-- <s:set name="voPatDtl" value="#session[@registration.config.RegistrationConfig@PATIENT_VO]"></s:set>
							<s:if test="%{#voPatDtl== null || #voPatRefer.length==0}">
								<s:set name="goFlagForJsp" value="0"></s:set>
							</s:if> --%>
						</s:if>
					</div>
					
					<div class="">  <!-- class="prescriptionTile"  -->
						<s:if test="%{#goFlagForJsp!=0}">			
							<div id="divEpisodeId" style="display:none;">		 <!-- Hidden but needed -->
			                	<div class="row">
			                    	<div class="col-sm-12" style="padding-left: 7px;">
			                        	<p class="patRefVisitDtl" style="text-align:left;"><b>VISIT DETAILS :</b></p>
			                        	
			                        	<div class="table-responsive">
				                        	<table id="patRefVisitDtlTable" class="table table-condensed table-hover" style="margin-bottom: 0px;"> 
				                            	<thead>
				                              		<tr style="background: linear-gradient(to bottom, #dbdbdb /* #acd6ff */, white);">
					                                	<th>Select</th>
					                                	<th>Department</th>
					                                	<th>Unit</th>
													</tr>
			                            		</thead>
					                            <tbody>
					                            	<s:set name="counter" value="0"/>
											    	<s:set name="checkedValue" value=""></s:set>
											    	
											    	<s:iterator value="#session.arrEpisodeVOReferral" status="key" >
														<s:if test="%{#counter==0}">	
															<s:set name="checkedValue" value="checked='checked'"></s:set>
														 	<s:set name="counter" value="%{#counter+1}"/>
														</s:if>	
														<s:else>
															<s:set name="checkedValue" value=""></s:set>
														</s:else>
														<tr>
															<td>
																<div class="radio" style="margin-top:0px; margin-bottom:0px;">
																	<label><input type="radio" name="selectedEpisode" tabindex="1" <s:property value="checkedValue" />  title='<s:property value="#key.index"/>' value='<s:property value="episodeCode"/>' onclick='getFilteredDeptUnit(this);'/></label>
																</div>
															</td>
															<td>
																<font><s:property value="department"/></font>
															</td>
															<td>
																<font><s:property value="departmentUnit"/></font>
															</td>
														</tr>
													</s:iterator>
					                            </tbody>
				                           	</table>
			                          	</div>                     
			                       	</div>
			               
			                     </div>
			                     
			                     <input name="selectedDepartmentUnitType" id="selectedDepartmentUnitTypeId" type="hidden" value='<s:property value="departmentUnitType"/>'>
						    	<input name="selectedDepartmentCode" id="selectedDepartmentCodeId" type="hidden" value='<s:property value="departmentCode"/>'>
						    	<input name="selectedDepartmentUnitCode" id="selectedDepartmentUnitCodeId" type="hidden" value='<s:property value="departmentUnitCode"/>'>
			                </div>
			                
			                <!-- <div class="clearfix visible-xs"></div> -->
			                
	                  		<div class="row" id="divReferTypeId">
                      			<div class="col-sm-12 col-md-12">
                        			<!-- <p class="patReferalTypeDtl" style="text-align:left;">
			                          <b>REFERAL TYPE :</b> 
			                        </p>  -->
			                        <p style="display:block;text-align:left;">
			                        	<label>External</label>
			                        	<label class="switch" style="vertical-align: middle;">
			                        	<input type="checkbox" name="isRefferInOut_chk" value="I" checked>
			                        	<span class="slider round"></span> 
			                        	</label>
			                        	&nbsp;<label>Internal</label>
			                        	<input name="isRefferInOut" id="isRefferInOutId" type="hidden" />
			                        </p> 
			                        <%-- <div class="row">
			                        	<div class="div-table-col control" style="width: 8%;">
											<input type="radio" name="isRefferInOut" tabindex="1" value="I" onclick="showInternal(this)" />
											<font><s:text name="internal" /></font>
										</div>
										<div class="div-table-col control" style="width: 8%;">
											<input type="radio" name="isRefferInOut" tabindex="1" value="E" onclick="showExternal(this)" />
				   							<font><s:text name="external" /></font>
										</div>
			                        </div> --%>
                      			</div>
                    		</div>
                    		
                    		<div class="row"  id="divInternalReferId">
                    			<div class="col-sm-12 col-md-12">
                    				<!-- <p class="patReferalToDtl col-md-2 col-sm-2" style="text-align:left;">
			                          <b>REFER TO :</b> 
			                        </p> -->
			                        <p style="text-align:left;"> <!-- Removed class class="col-md-10 col-sm-10"  -->
			                        	<label><font><s:text name="specialClinic" /></font></label>
			                        	<label class="switch" style="vertical-align: middle;">
			                        	<input type="checkbox" name="choice" value="0" checked>
			                        	<span class="slider round"></span> 
			                        	</label>
			                        	&nbsp;<label><font><s:text name="department" /></font></label>
			                        </p>
                    			</div>
                    			
                    			<div class="row">
                    				<div class="col-md-12 col-sm-12" id="divReferDeptId">
	                    				<div class="form-group">
										    <p><label class="control-label col-sm-4 col-md-4 marginTopCls" for="department">Department<sup style="color:red;">*</sup></label></p>
										    <div class="col-sm-8 col-md-8" style="text-align:left;">
										    	<s:set name="optionRefInternalDeptLocalVar" value="#session.optionRefInternalDept"></s:set>
												<s:if test="%{#optionRefInternalDeptLocalVar!=null && #optionRefInternalDeptLocalVar.size>0}">
													<s:select cssClass="form-control" name="departmentCode"  list="#optionRefInternalDeptLocalVar"  listKey="value" listValue="label" headerKey="-1" headerValue="Select Value"/> 
												</s:if>
												<s:else>
													<select name="departmentCode" class="form-control">
														<option value="-1">Select Value</option>
													</select>
												</s:else> 
										    </div>
									  	</div>
								   </div>
								   
								   <div class="col-md-12 col-sm-12" id="divReferDeptUnitId" style="display:none;">
	                    				<div class="form-group">
										    <p><label class="control-label col-sm-4 col-md-4 marginTopCls" for="spclClinic">Special Clinic<sup style="color:red;">*</sup></label></p>
										    <div class="col-sm-8 col-md-8"  style="text-align:left;">
										    	<s:set name="optionRefSplUnitsLocalVar" value="#session.optionRefSplUnits"></s:set>
													<s:if test="%{#optionRefSplUnitsLocalVar!=null && #optionRefSplUnitsLocalVar.size>0}">
														<s:select cssClass="form-control" name="departmentUnitCode" list="#optionRefSplUnitsLocalVar"  listKey="value" listValue="label"  headerKey="-1" headerValue="Select Value" /> 
													</s:if>
												<s:else>
													<select name="departmentUnitCode"  class="form-control">
														<option value="-1">Select Value</option>
													</select>
												</s:else>
										    </div>
									  	</div>
								   </div>
								
									<div class="col-md-12 col-sm-12 marginTopCls">
										<div class="form-group">
										    <p><label class="control-label col-sm-4 col-md-4 marginTopCls" for="reason">Reason</label></p>
										    <div class="col-sm-8 col-md-8">
										    	<textarea name="remarks" rows="1" cols="15" class="form-control"></textarea>
										    </div>
									    </div>
									</div>
								</div>
                    		</div>
                    		<div id="divExternalReferId" class="row" style="display: none;">
								<div class="col-sm-12 col-md-12">
	                   				<!-- <p class="patReferalToDtl col-md-2 col-sm-2" style="text-align:left;">
			                          <b>REFER TO :</b> 
			                        </p> -->
			                        <p style="text-align:left;"> <!-- Removed class class="col-md-10 col-md-10" -->
			                        	<label><font><s:text name="other" /></font></label>
			                        	<label class="switch" style="vertical-align: middle;">
			                        	<input type="checkbox" name="referringInstType" value="G" checked>
			                        	<span class="slider round"></span> 
			                        	</label>
			                        	&nbsp;<label><font><s:text name="referInternal" /><s:text name="slash" /><s:text name="associatedInst" /></font></label>
			                        	<input name="isAssociated" id="isAssociated" type="hidden">
			                        </p>
	                   			</div>
								<%-- <div class="div-table-row title">
									<div class="div-table-col" style="width: 10%;"><s:text name="referto" /></div>
									<div id="divReferredInstitute" class="div-table-col label" style="width: 50%; display: none;">
								    <input type="radio" name="referringInstType" value="G" onclick="showdivhoscode(this)" checked="checked" /><s:text name="referInternal" /><s:text name="slash" /><s:text name="associatedInst" /> &nbsp;
						    	    <input type="radio" name="referringInstType" value="O" onclick="showdivhoscode(this)" /><s:text name="other" /> &nbsp;
							        <input name="isAssociated" id="isAssociated" type="hidden">
							       </div>
								</div> --%>
							
								<div class="row" style="margin-top:2vh; margin-bottom:2vh;">
									<div class="col-md-12 col-sm-12" id="divRefHosCode">
	                    				<div class="form-group">
										    <p><label class="control-label col-sm-4 col-md-8 marginTopCls" for="institueName">Institute Name<sup style="color:red;">*</sup></label></p>
										    <div class="col-sm-8 col-md-8" style="text-align:left;">
										    	<s:set name="optionRefHospitalLocalVar" value="#session.optionRefHospital"></s:set>
												<s:if test="%{#optionRefHospitalLocalVar!=null && #optionRefHospitalLocalVar.size>0}">
													<s:select cssClass="form-control" name="patRefGnctdHospitalCode" id="patRefGnctdHospitalCodeId"  
														list="#optionRefHospitalLocalVar"  listKey="value" listValue="label" 
														headerKey="-1" headerValue="Select Value" /> 
												</s:if>
												<s:else>
													<select name="patRefGnctdHospitalCode"  class="form-control">
														<option value="-1">Select Value</option>
													</select>
												</s:else>
										    </div>
									  	</div>
								   </div>
								   <div class="col-md-12 col-sm-12" id="divRefHosname" style="display: none;">
	                    				<div class="form-group">
										    <p><label class="control-label col-sm-4 col-md-4 marginTopCls" for="institueName">Institute Name<sup style="color:red;">*</sup></label></p>
										    <div class="col-sm-8 col-md-8" style="text-align:left;">
										    	<input class="form-control" name="patRefHospitalName" maxlength="50" type="text" size="20">
										    </div>
									  	</div>
								   </div>
								   <div class="col-md-12 col-sm-12 marginTopCls">
	                    				<div class="form-group">
										    <p><label class="control-label col-sm-4 col-md-4 marginTopCls" for="reason">Reason</label></p>
										    <div class="col-sm-8 col-md-8" style="text-align:left;">
										    	<textarea class="form-control" name="externalReferRemarks" rows="1" cols="15"></textarea>
										    </div>
									  	</div>
								   </div>
								</div>
			  				</div>
		  				
			  				<div class="row" id="divReferred" style="display: none;">
			  					<div class="row" style="margin-bottom:2vh;">
			  						<div class="col-md-12 col-sm-12" id="divRefDeptforAssociatedInstitutes" >
				  						<div class="form-group">
										    <p><label class="control-label col-sm-4 col-md-4" for="referringInstituteDept">Referring Instituion Department<sup style="color:red;">*</sup></label></p>
										    <div class="col-sm-8 col-md-8" style="text-align:left;">
										    	<select id="patRefGnctdHospitalDeptId" name="patRefGnctdHospitalDeptUnit" class="form-control">
													<option value="-1">Select Value</option>
													<option value="0">Other</option>
												</select>
										    </div>
									  	</div>
				  					</div>
				  					<div class="col-md-12 col-sm-12" id="divRefDeptforOtherInstitutes" style="display: none;">
				  						<div class="form-group">
										    <p><label class="control-label col-sm-4 col-md-4" for="referringInstituteDept">Referring Instituion Department<sup style="color:red;">*</sup></label></p>
										    <div class="col-sm-8 col-md-8" style="text-align:left;">
										    	<input class="form-control" name="patRefGnctdHospitalDept" maxlength="50" type="text" size="20">
										    </div>
									  	</div>
				  					</div>
				  					<div class="col-md-12 col-sm-12 marginTopCls" id="divRefHospitalDeptOtherId" style="display: none;">
				  						<div class="form-group">
										    <p><label class="control-label col-sm-4 col-md-4 marginTopCls" for="otherReferredDept">Other Referred Department<sup style="color:red;">*</sup></label></p>
										    <div class="col-sm-8 col-md-8" style="text-align:left;">
										    	<input class="form-control" name="patRefHospitalDeptOther" maxlength="20" type="text" size="20">
										    </div>
									  	</div>
				  					</div>
			  					</div>
			  				</div>
			
	                        
						</s:if>
						
						<s:if test="%{#goFlagForJsp!=0}">
							<div class="row marginTopCls">
								<div class="col-md-4 col-sm-4">
								</div>
								<div class="col-md-4  col-sm-4" style="text-align:center;">
									<button type="button" class="btn btn-success primary btn-md" id="submitIdBtn">Save</button>
								</div>
								<div class="col-md-4  col-sm-4">
								</div>
							</div>
						</s:if>	
					</div>
				
 		
					<div id="aptTag" class="row">
					</div>
							
				</div>
	
	
				<div class="row" id="divPrintId" style="font-weight: normal;color: black;"></div>
	
			<!-- </div> -->

				<cmbPers:cmbPers></cmbPers:cmbPers>
				<s:token/>
			</s:form>
		
			<%-- <div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col alignLeft fontError">
					<s:property value="errorMessage" />
					</div>
				</div>
			</div>
	
			<div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col alignLeft fontNormalMessage">
						<s:property value="normalMessage" />
					</div>
				</div>
			</div> --%>

			<script type="text/javascript" src="./../../appointment/transactions/js/appointment.js" /></script>	
			<script type="text/javascript" src="./../../registration/transactions/js/patientReferral_new.js" /></script>
			
			<script>
				$(document).ready(function(){
				
					if(localStorage.getItem('refWindowFlag')=='0')
					{
						$('input[name=patCrNo]').val(window.parent.patCrNo);
						//alert(window.parent.patCrNo);
						$('#imgGoCrNoId').attr("onclick", "").unbind("click");
						//alert("unbind....");
						$('#imgGoCrNoId').bind('click', function(){
							submitFormOnValidate(validateCRNoCHECK("15"),"GETPATDTLDRDESK");
						});
						$('#imgGoCrNoId').click(); 
						var errMsg = $('.fontErrorDrDesk').text();
						localStorage.setItem('refWindowFlag','1');
					}
					var normalMsg = $('.fontNormalMessageDrDesk').text();
					if(!normalMsg==''){
						localStorage.setItem('patReferedOrNotFlag','1');
					}
					else{
						localStorage.setItem('patReferedOrNotFlag','0');
					}
					/* $('input[name=patCrNo]').val(window.parent.patCrNo);
					//alert(window.parent.patCrNo);
					$('#imgGoCrNoId').click(); 
					var errMsg = $('.fontError').text();
					var normalMsg = $('.fontNormalMessage').text(); */
					 
					//alert("***"+errMsg.trim()+"**");
					 /* if(errMsg.trim()=='' || errMsg.trim()==null){
						$('input[name=patCrNo]').val(window.parent.patCrNo);
						//alert(window.parent.patCrNo);
						$('#imgGoCrNoId').click();
					}  */

				});
		
			</script>
		
			<script>
				 $('input[name=choice]').on('change',function(){
					if($(this).is(':checked'))
					{
						$(this).val("0");
						console.log("hiii --->> "+$(this).val());
						$('#divReferDeptId').css("display", "block");
						$('#divReferDeptUnitId').css("display", "none");
					}
					else
					{ 
						$(this).val("1");
						console.log("hiii spcl--->> "+$(this).val());
						$('#divReferDeptUnitId').css("display", "block");
						$('#divReferDeptId').css("display", "none");
					}
				});  


				 $('input[name=isRefferInOut_chk]').on('change',function(){
					 console.log("Internal/External 2--->> "+$(this).val());
					if($(this).is(':checked'))
					{
						$(this).val("I");
						console.log("Internal/External --->> "+$(this).val());
						$('#divInternalReferId').css("display", "block");
						$('#divExternalReferId').css("display", "none");
						$('#divReferred').css("display","none");
						$('#divReferredInstitute').css("display","none");
					}
					else
					{ 
						$(this).val("E");
						$('#divExternalReferId').css("display", "block");
						$('#divInternalReferId').css("display", "none");
						$('#divReferred').css("display","block");
						$('#divReferredInstitute').css("display","block");
					}
				}); 

				$('input[name=referringInstType]').on('change',function(){
					if($(this).is(':checked'))
					{
						$(this).val("G");
						console.log("External referral type --->> "+$(this).val());
						$('#divRefHosCode').css("display", "block");
						$('#divRefHosname').css("display", "none");
					}
					else
					{ 
						$(this).val("O");
						$('#divRefHosname').css("display", "block");
						$('#divRefHosCode').css("display", "none");
					}
				}); 
				 
			</script>	
	
	</div>

	
</html>