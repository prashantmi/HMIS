<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<form name="multirow">

<div id="rowAdded1" style="display: none">
<table class="TABLEWIDTH" align="center" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td class="multiControl" width="25%"><input type=text autocomplete='false'
			title="Please Enter Disease Code" class="txtFldMin" onmouseover="Tip(this.value,SHADOW,true)" onmouseout="UnTip()"
			onkeypress="return setSelectValue1('strProvisionDiagnosis#delIndex#',1,event,'setIcdCodes^tariffFullNameDiv','#delIndex#');"
			onkeyup="getIcdCodeValues1('1', this , event, '#delIndex#');"
			id='strProvisionDiagnosis#delIndex#' name='strProvisionDiagnosis'>(Code)</td>
		<td class="multiControl" width="67%" align="left"><div align="left">
		&nbsp;<input type=text class="txtFldBig" autocomplete='false'
			name='strIcdCode' id='strIcdCode#delIndex#' style='width: 450px'  onmouseover="Tip(this.value,SHADOW,true)" onmouseout="UnTip()"
			onkeypress="return setSelectValue1('strIcdCode#delIndex#','1',event,'setIcdCodes^tariffFullNameDiv','#delIndex#');"
			onkeyup="getIcdCodeValues1(2,this,event ,'#delIndex#');"
			title='Enter Disease Code'><INPUT TYPE='HIDDEN'
			NAME='strICD10CodeHidden' id='strICD10CodeHidden#delIndex#'></div></td>
		<td class="multiControl" width="6%" align="left">
		<select name="strDiagTypeCode" style="width: 60px">
		<bean:write name="patientDischargeBean" property="strDiagnosticTypeValues" filter="false"/></select>
		<input type='hidden' name='strDiagCodeType' id='strDiagCodeType#delIndex#'>
		<input type='hidden' name='strDiseaseSite' value=''>
		<input type='hidden' name='strisRepaeat' value='0'>
		<input type='hidden' name='strDiagRemarks' value=''></td>
		<td class="multiControl" width="2%" align="right"><div align="center"><img name="icd10IdplusForDelete"
			onkeypress="onPressingEnter(this,event)"
			src="/AHIMS/hisglobal/images/minus.gif" style="cursor: pointer;"
			title="Delete Row" onclick="deleteRow('#delIndex#',1,0);"></div></td>		
	</tr>
</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> <input
	type="hidden" name="rowLength1" value="0"></form>