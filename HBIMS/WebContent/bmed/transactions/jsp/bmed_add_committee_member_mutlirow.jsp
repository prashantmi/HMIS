<%--  
/**
 * @author shefali 
 * Date of Creation : 22-Nov-2013
 * Date of Modification : 22-Nov-2013 
 * Version : 
 * Module  : HEMMS Product 1.0
 */
 --%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<form name="multirow">
<div id="rowAdded1" style="display: none">
 <his:ContentTag>
<table class="TABLEWIDTH" width="100%" align="center">
	
		<tr>
						<td class="LABEL">
					<div align="center">
						<select name="strMemberType" id="strMemberType#delIndex#" onchange="getMemberDetails('#delIndex#')"><option value="1">External</option>
                                                       <option value="2">Internal</option></select> 
					</div>						</td>
										
				<td class="LABEL">
				<div id="strComMemberNameDiv#delIndex#" align="center">
                <input type="text" name="strComMemberName" id="strComMemberName#delIndex#"/></div>
				</td>
				<td class="LABEL">
				<div id="strComMemberIdDiv" align="center">
				<input type="text" name="strComMemberId" id="strComMemberId#delIndex#" value="0" readonly/>
				</div>
				</td>
				<td class="LABEL"><div align="center">
				<input type="text" name="strComMemberDesign" id="strComMemberDesign#delIndex#" value="0" readonly/>
				</div></td>
				<td  class="LABEL"><div align="center">
				<input type="text" name="strComMemberMobNo" id="strComMemberMobNo#delIndex#" onfocus="getValueSelected('#delIndex#')" onkeypress="return isNumberKey(event)" onblur="validatePhoneNum('#delIndex#')"/>
				</div>
				</td>
				<td  class="LABEL">
				<div id="strComMemberEmailDiv" align="center">
				<input type="text" name="strComMemberEmail" id="strComMemberEmail#delIndex#" onfocus="getValueSelected('#delIndex#')"  onblur="return validateEmailId('#delIndex#');"/>
				</div>
				</td>
			<td class="LABEL">
			
			<img name=""
			onkeypress="onPressingEnter(this,event)"
			src="../../hisglobal/images/minus.gif" style="cursor: pointer;"
			title="Delete This Row" onclick="deleteRow('#delIndex#',1,0);">
			</td>
		
			
			
		
	</tr>
</table>

</his:ContentTag>
</div>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0">

</form>
