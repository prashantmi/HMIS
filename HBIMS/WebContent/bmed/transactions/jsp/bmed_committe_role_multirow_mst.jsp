<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<form name="multirow" style="formbg">
<div id="rowAdded1" style="display: none">
<table class="TABLEWIDTH" >


<tr>
		
		<td width="20%" class="CONTROL">
				<div align="center">
					<input type="text" maxlength="30" size="5" name="strMemberId" id="strMemberId" title="Enter Employee ID" onkeypress="return validateData(event,7); ">
				</div>
			</td>
			<td width="20%" class="CONTROL">
				<div align="center">
					<input type="text" maxlength="30" size="5" name="strMemberName" id="strMemberName" title="Enter Member Name" onkeypress="return validateData(event,7); ">
				</div>
			</td>
			<td width="20%" class="CONTROL" >
				<div align="center">
					<input type="text" maxlength="50" size="5" name="strDesignation" id="strDesignation" title="Enter Designation" onkeypress="return validateData(event,7); ">
				</div>								
			</td>
			<td width="10%" class="CONTROL" >
				<div align="center">
					<input type="text" maxlength="10" size="5" name="strDepartment" id="strDepartment" title="Enter Department." onkeypress="return validateData(event,7); ">
				</div>						
			</td>
			<td width="10%" colspan="3" class="CONTROL">
				<div align="center">
					<input type="text" maxlength="30" size="5" name="strContNo" id="strContNo" title="Enter Contact No" onkeypress="return validateData(event,7); ">
				</div>						
			</td>
			<td width="30%" class="CONTROL">
				<div align="center">
					<input type="text" maxlength="50" name="strEmail" id="strEmail" title="Enter Email Address" onkeypress="return validateData(event,7); ">
				</div>						
			</td>
				<td width="33%" class="CONTROL">
			
			<img name=""
			onkeypress="onPressingEnter(this,event)"
			src="../../hisglobal/images/minus.gif" style="cursor: pointer;"
			title="Delete Row" onclick="deleteRow('#delIndex#',1,0);">
			</td>
		</tr>	


</table>
</div>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0">


</form>


