<%@page import="hisglobal.hisconfig.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>


<%--  
/**
 * @author Amit Kumar
 * Date of Creation : 18-April-2011
 * Date of Modification :  
 * Version : 
 * Module  : BMED
 */
 --%>
<html>
<head>

<his:css src="/hisglobal/css/Color.css" />

<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:css src="/hisglobal/css/newpopup.css" />

<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript src="/hisglobal/js/newpopup.js"/>

<script language="JavaScript"
	src="/HEMMS_ODISHA/bmed/transactions/js/hemms_budgetcontroldelegation_desk_trans.js"></script>

<script type="text/javascript">

</script>

</head>
<body marginheight="0" marginwidth="0"  onLoad="getOnLoad();">
<html:form name="budgetControlDelegationDeskFB"
	action="/transactions/BudgetControlDelegationDeskACTION"
	type="bmed.transactions.controller.fb.BudgetControlDelegationDeskFB" enctype="multipart/form-data">
	<his:TransactionContainer>
		<div id="TitleHide1" style="display:block;">		
	         <his:TitleTag name="Centralized Budget Control Delegation Classification Detail">
	         </his:TitleTag>
	    </div> 
	   
	   		<div id="itemHide2" style="display:block;">
				<his:ContentTag>
				   <table class="TABLE_STYLE">
						<tr>
							<td width="25%" class="LABEL_TD"><font color="red">*</font>Classification Code</td>
							<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MAX" name="strClassificationCode" maxlength="250"
						value=""  onkeypress="return validateData(event,9);">
						</td>
		
							<td width="25%" class="LABEL_TD">Classification
							Name</td>
							<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MAX" name="strClassificationName" maxlength="250"
						value=""  onkeypress="return validateData(event,9);">
						</td>
						</tr>
                  </table>
                  </his:ContentTag>
				</div>
			<his:ContentTag>
               <table class="TABLE_STYLE">
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Effective Date</td>
					<td width="25%" class="CONTROL_TD">
					<dateTag:date
						name="strEffectiveDate"
						value=""></dateTag:date>
					</td>
					<td width="25%" class="LABEL_TD">Remarks</td>
					<td width="25%" class="CONTROL_TD">
					<textarea name="strRemarks" 
						cols="25" rows="2" ></textarea>
					</td>
				</tr>

			</table>
		</his:ContentTag>

		<his:ContentTag>
			<table class="TABLE_STYLE">

				<tr class="FOOTER_TR">
					<td><font size="2" color="red">*</font> Mandatory Fields</td>
				</tr>
			</table>
		</his:ContentTag>


		<his:ButtonToolBarTag>
			<img style="cursor: pointer;"
				src="/HEMMS_ODISHA/hisglobal/images/btn-sv.png"
				onClick="return validate1();" />
			<img style="cursor: pointer;"
				src="/HEMMS_ODISHA/hisglobal/images/btn-clr.png" onClick="ClearPage();">
			<img style="cursor: pointer;"
				src="/HEMMS_ODISHA/hisglobal/images/btn-ccl.png" onClick="cancelPage();">
		</his:ButtonToolBarTag>


		<div class="ERR_DIV" id="errMsg"><bean:write
			name="budgetControlDelegationDeskFB" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write
			name="budgetControlDelegationDeskFB" property="strWarningMsg" /></div>
		<div class="NORMAL_DIV" id="normalMsg"><bean:write
			name="budgetControlDelegationDeskFB" property="strNormalMsg" /></div>

		<input type="hidden" name="hmode" />
		<input type="hidden" name="strIsBudgetControlDesk" value="${budgetControlDelegationDeskFB.strIsBudgetControlDesk}" />
		<input type="hidden" name="strCheck" value="0" />
		<input type="hidden" name="strIsCancel" value="0" />



		<div id="blanket" style="display: none;"></div>
		<div class="popUpDiv" id="popUpDiv" style="display: none;">
		<table bgcolor="white">
			<tr>
				<td>

				<div id="strRenew" style="display: block;"></div>
				</td>
			</tr>
		</table>
		</div>
	</his:TransactionContainer>
</html:form>
</body>
</html>