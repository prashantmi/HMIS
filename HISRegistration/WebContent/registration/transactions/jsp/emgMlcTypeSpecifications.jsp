<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">

<%--
## 		Modification Log							
##		Modify Date				:16thDec'14 
##		Reason	(CR/PRS)		:Bug fix 7734
##		Modify By				:Sheeldarshi
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script> --%>

<script type="text/javascript" src="/HIS/hisglobal/js/multirow.js"></script>
<script type="text/javascript" src="./../../registration/transactions/js/emgMlcTypeSpecifications.js" /></script>
<style type="text/css">
	.tmpClass {
	}
	.div-table-listing1 {
	display: table;
	width: 98%;
	margin: 8px auto;
	border: 1px solid black;
	border-spacing: 3px;
	}
	/*  .div-table-listing > .div-table-row:nth-child(odd){
	background-color: #F1F6F6;
	}
	.div-table-listing > .div-table-row:nth-child(even){
	background-color: #E0EBEB;
	} */
</style>
</head>
<body>
	<div class="div-table">
		<div class="div-table-row title">
			<div class="div-table-col width95"><s:text name="mlc"/>&nbsp;<s:text name="type"/>&nbsp;<s:text name="specification"/> </div>
			<div class="div-table-col width5">
				<img id="imgPlusId" src="/HIS/hisglobal/images/avai/plus.gif" style="cursor: pointer;"/>
			</div>
		</div>
	</div>
	<div class="div-table" id="TBL_MLC_TYPE_ID">
		<div class="div-table-row tmpClass" id="TR_MLC_TYPE_ID_">
			<div class="div-table-col width100">
				<div class="div-table-listing1 rounded">
					<div class="div-table-row title width100 tmpClass" id="CHILD_TR_MLC_TYPE_ID_">
						<div class="div-table-col width95"><s:text name="mlc"/>&nbsp;<s:text name="type"/>&nbsp;<s:text name="specification"/>&nbsp;<s:text name="record"/>   </div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="mlc"/>&nbsp;<s:text name="type"/></div>
						<div class="div-table-col width25 control">
							<s:set name="optionsMlcTypeEntryList" value="#session[@registration.config.RegistrationConfig@ESSENTIAL_INJURY_TYPE_LIST]"></s:set>
							<s:if test="%{#optionsMlcTypeEntryList!= null}">
								<s:select id="mlcTypeCodeId" name="mlcTypeCode" tabindex="1"
									cssClass="select61prcnt" list="optionsMlcTypeEntryList"  
									 listKey="value" listValue="label" 
								   headerKey="-1"  headerValue="Select Value" />
							</s:if>
							<s:else>
								<select id="mlcTypeCodeId" name="mlcTypeCode" class="select61prcnt" tabindex="1">
									<option value="-1">Select Value</option>
								</select>
							</s:else>
							<input type="hidden" name="mlcParamSerialNo">
						</div>
						<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="injury"/>&nbsp;<s:text name="site"/></div>
						<div class="div-table-col width25 control">
							<s:set name="optionsDiseaseSideEntryList" value="#session[@registration.config.RegistrationConfig@ESSENTIAL_DISEASE_SIDE_LIST]"></s:set>
							<s:if test="%{#optionsDiseaseSideEntryList!= null}">
								<s:select id="injurySideId"  name="injurySide" tabindex="1"
									cssClass="select61prcnt" list="optionsDiseaseSideEntryList"  
									 listKey="value" listValue="label" 
								   headerKey="-1"  headerValue="Select Value" />
							</s:if>
							<s:else>
								<select id="injurySideId" name="injurySide" class="select61prcnt" tabindex="1">
									<option value="-1">Select Value</option>
								</select>
							</s:else>
						</div>
					</div>
					
					<div class="div-table-row">
						<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="nature"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="injury"/></div>
						<div class="div-table-col width25 control">
							<s:set name="optionsInjuryNatureEntryList" value="#session[@registration.config.RegistrationConfig@ESSENTIAL_INJURY_NATURE_LIST]"></s:set>
							<s:if test="%{#optionsInjuryNatureEntryList!= null}">
								<s:select id="injuryNatureCodeId" name="injuryNatureCode" tabindex="1"
									cssClass="select61prcnt" list="optionsInjuryNatureEntryList"  
									 listKey="value" listValue="label" 
									headerKey="-1"  headerValue="Select Value" />
								</s:if>
							<s:else>
								<select id="injuryNatureCodeId" name="injuryNatureCode" class="select61prcnt" tabindex="1">
									<option value="-1">Select Value</option>
								</select>
							</s:else>
						</div>
						<div class="div-table-col width25 label"><s:text name="type"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="weapon"/></div>
						<div class="div-table-col width25 control">
							<input type="text" class="input60prcnt" name="typeOfWeapon" id="typeOfWeaponId" tabindex="2">
						</div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width25 label"><s:text name="injury"/>&nbsp;<s:text name="size"/> </div>
						<div class="div-table-col width25 control">
							<input type="text" class="input60prcnt" name="injurySize" id="injurySizeId" tabindex="2">
						</div>
						<div class="div-table-col width25 label"><s:text name="injury"/>&nbsp;<s:text name="depth"/></div>
						<div class="div-table-col width25 control">
							<input type="text" class="input60prcnt" name="injuryDepth" id="injuryDepthId" tabindex="2">
						</div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width25 label"><s:text name="burn"/>&nbsp;<s:text name="percentage"/> </div>
						<div class="div-table-col width25 control">
							<input type="text" class="input60prcnt" name="burnPercentage" id="burnPercentageId" tabindex="2">
						</div>
						<div class="div-table-col width25 label"><s:text name="poison"/>&nbsp;<s:text name="global.remarks"/> </div>
						<div class="div-table-col width25 control">
							<input type="text" class="input60prcnt" name="poisonRemarks" id="poisonRemarksId" tabindex="2">
						</div>
					</div>
				</div>
				
			</div>
			
		</div>
		
	</div>
</body>
</html>