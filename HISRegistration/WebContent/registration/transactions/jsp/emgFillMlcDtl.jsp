<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">

<script src='/HIS/hisglobal/js/qtip/jquery.qtip.min.js'></script>
<script src='/HIS/hisglobal/js/qtip/writeOnToolQTip.js'></script>
</head>
<body>
	<div class="div-table">
		<div class="div-table-row title">
			<div class="div-table-col width100"><s:text name="mlc"/>&nbsp;<s:text name="detail"/> </div>
		</div>
	</div>
	<div class="div-table">
		<div class="div-table-row">
			<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="mlc"/>&nbsp;<s:text name="number"/> </div>
			<div class="div-table-col width25 control">
				<input type="text" class="input60prcnt" name="patMlcNo" id="patMlcNoId" tabindex="1" maxlength="10">
			</div>
		 <div class="div-table-col width25 label"><s:text name="Print Certificate"/></div>
			<div class="div-table-col width25 control">
										<input type="checkbox" name="printFlag" id="printFlagId" checked="checked"/>
			</div>
		</div>
		<div class="div-table-row">
			<div class="div-table-col width25 label"><s:text name="mlr"/>&nbsp;<s:text name="book"/>&nbsp;<s:text name="number"/> </div>
			<div class="div-table-col width25 control">
				<input type="text" class="input60prcnt" name="mlcBookNo" id="bookNoId" tabindex="2" maxlength="20">
			</div>
			<div class="div-table-col width25 label"><s:text name="mlr"/>&nbsp;<s:text name="page"/>&nbsp;<s:text name="number"/></div>
			<div class="div-table-col width25 control">
				<input type="text" class="input60prcnt" name="mlcPageNo" id="pageNoId" tabindex="2" maxlength="4">
			</div>
		</div>
		
		<div class="div-table-row">
			<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="mlc"/>&nbsp;<s:text name="date"/> </div>
			<div class="div-table-col width25 control">
				<input type="text" class="input30prcnt" name="mlcDate" id="mlcDateId" tabindex="1" readonly="readonly">
			</div>
		
			<div class="div-table-col width25 label"><font color="#FF0000">*</font><s:text name="mlc"/>&nbsp;<s:text name="time"/> </div>
			<div class="div-table-col width25 control colorRed">
				<input type="text" class="input10prcnt" name="mlcTimeHr" id="mlcTimeHrId" tabindex="1" maxlength="2" value='<s:property value="hiddenTimeHr"/>' onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)"> :
				<input type="text" class="input10prcnt" name="mlcTimeMin" id="mlcTimeMinId" tabindex="1" maxlength="2" value='<s:property value="hiddenTimeMin"/>'> (HH:MM 24 Hrs)
			</div>
		</div>
		
		<div class="div-table-row">
			<div class="div-table-col width25 label"><s:text name="final"/>&nbsp;<s:text name="opinion"/> </div>
			<div class="div-table-col width25 control">
				<input type="text" class="tooltipClass1" style="width:60%" name="finalOpinion" id="finalOpinionId" maxlength="100" tabindex="2" onkeypress="checkToolTip(this)">
			</div>
			<div class="div-table-col width25 label"><s:text name="provisional"/>&nbsp;<s:text name="opinion"/> </div>
			<div class="div-table-col width25 control">
				<input type="text" class="input60prcnt" name="provisionalOpinion" tabindex="2" maxlength="100">
			</div>
		</div>
		<!-- 
		<div class="div-table-row">
			<div class="div-table-col width25 label"></div>
			<div class="div-table-col width50 control colorRed">
				(MLC Type Modification cannot be done if final opinion is entered)
			</div>
		</div>
		 -->
		<div class="div-table-row">
			<div class="div-table-col width25 label"><s:text name="global.patient"/>&nbsp;<s:text name="status"/> </div>
			<div class="div-table-col width25 control">
				<s:set name="optionsStatusList" value="#session[@registration.config.RegistrationConfig@PATIENT_STATUS_LIST]"></s:set>
				<s:if test="%{#optionsStatusList!= null}">
					<s:select id="patMlcStatusCodeId" name="patMlcStatusCode" tabindex="2"
						cssClass="select61prcnt" list="optionsStatusList"  
						 listKey="value" listValue="label" 
					   headerKey="-1"  headerValue="Select Value" />
				</s:if>
				<s:else>
					<select id="patStatusCodeId" name="patStatusCode" class="select70prcnt" tabindex="2">
						<option value="-1">Select Value</option>
					</select>
				</s:else>
			</div>
			<div class="div-table-col width25 label"><s:text name="fit"/>&nbsp;<s:text name="to"/>&nbsp;<s:text name="make"/>&nbsp;<s:text name="statement"/></div>
			<div class="div-table-col width25 control">
				<input type="radio" name="fitnessStatus" tabindex="2" value="1" checked="checked"><s:text name="fit"/>
				<input type="radio" name="fitnessStatus" tabindex="2" value="0"><s:text name="unfit"/>
			</div>
		</div>
		
		<div class="div-table-row">
			<s:set name="patVO" value="#session[@registration.config.RegistrationConfig@PATIENT_VO]"/>
			<div class="div-table-col width25 label"><s:text name="identitymark1"/>  </div>
			<div class="div-table-col width25 control">
				<s:if test="#patVO != null">
					<input type="text" class="input60prcnt" name="patIdMark1" tabindex="2" value="<s:property value="#patVO.patIdMark1"/>">
				</s:if>
				<s:else>
					<input type="text" class="input60prcnt" name="patIdMark1" tabindex="2">
				</s:else>
				
			</div>
			<div class="div-table-col width25 label"><s:text name="identitymark2"/> </div>
			<div class="div-table-col width25 control">
				<s:if test="#patVO != null">
					<input type="text" class="input60prcnt" name="patIdMark2" tabindex="2" value="<s:property value="#patVO.patIdMark2"/>">
				</s:if>
				<s:else>
					<input type="text" class="input60prcnt" name="patIdMark2" tabindex="2" >
				</s:else>
			</div>
		</div>
		
		<div class="div-table-row">
			<div class="div-table-col width25 label"><s:text name="global.patient"/> &nbsp;<s:text name="condition"/> </div>
			<div class="div-table-col width25 control">
				<textarea name="patCondition" class="textarea60prcnt" tabindex="2"></textarea>
			</div>
			<div class="div-table-col width25 label"><s:text name="Diagnosis"/></div>
			<div class="div-table-col width25 control">
				<textarea name="diagnosis" class="textarea60prcnt" tabindex="2"></textarea>
			</div>
		</div>
		
		<div class="div-table-row">
			<div class="div-table-col width25 label"><s:text name="referredBy"/>   </div>
			<div class="div-table-col width25 control">
				<s:set name="optionsRefByDoctrDtl" value="#session[@registration.config.RegistrationConfig@ESSENTIALBO_OPTION_CONSULTANT_DTL]"></s:set>
				<s:if test="%{#optionsRefByDoctrDtl!= null}">
					<s:select id="referDoctorCodeId" name="referDoctorCode" tabindex="2"
						cssClass="select61prcnt" list="optionsRefByDoctrDtl"  
						 listKey="value" listValue="label" 
					   headerKey="-1"  headerValue="Select Value" />
				</s:if>
				<s:else>
					<select id="referDoctorCodeId" name="referDoctorCode" class="select61prcnt" tabindex="2">
						<option value="-1">Select Value</option>
						<option value="0">Other</option>
					</select>
				</s:else>
			</div>
			<div class="div-table-col width25 label"><s:text name="referred"/>&nbsp;<s:text name="global.remarks"/></div>
			<div class="div-table-col width25 control">
				<textarea id="textArea"  tabindex="2"  name="referDocotorRemarks" class="textarea60prcnt" ></textarea>
			</div>
		</div>
		
	</div>
	<s:hidden name="printFlag" value="%{printFlag}" id="printFlagId"></s:hidden>
</body>
</html>