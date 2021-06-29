<%--  
/**
 * @author T. Saratkumar
 * Date of Creation : 20-Nov-2013
 * Date of Modification : 04-Jun-2014 
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
<table class="TABLEWIDTH" width="100%">
	
	<tr>
		
		
		<td width="10%" style="display: none;" class="LABEL">
					<div align="center">
						<input type="text" maxlength="5" value="" size="5" name="strSpareSeqNo" id="strSpareSeqNo#delIndex#" title="Enter Serial Number" onkeypress="return validateData(event,7); ">
					</div>						
										
				<td width="20%" class="LABEL">
				<div id="sparePartNameDiv" align="center">
				<select name="strSparePartName" id="strSparePartName#delIndex#" title="Select Spare Part Name">
				<bean:write name="hemDeskFB" property="strSparePartNameOptions" filter="false" />
				</select>
				</div>
				</td>
				<td width="20%" style="display: none;" class="LABEL">
				<div id="ManufactorNameDiv" align="center">
				<select name="strSpareManufName" id="strSpareManufName#delIndex#" title="Select Spare Part Manufacturer Name" onchange="">
				<bean:write name="hemDeskFB" property="strManufactureNameOptions" filter="false" />
				</select>
				</div>
				</td>
				<td width="10%" class="LABEL"><div align="center">
				<input type="text" name="strSpareSlNo" id="strSpareSlNo#delIndex#" title="Enter Spare Part Serial Number" maxlength="20" onkeypress="return validateData(event,8);"/>
				</div></td>
				<td width="10%" style="display: none;" class="LABEL"><div align="center">
				<input type="text" name="strSpareManufSlNo" id="strSpareManufSlNo#delIndex#" title="Enter Spare Part Manufacturer Serial Number" maxlength="12" onkeypress="return validateData(event,7);"/>
				</div>
				</td>
				<td width="15%" style="display: none;" class="LABEL">
				<div id="statusDiv" align="center">
				<select name="strStatus" id="strStatus#delIndex#" onchange="" title="Select Spare Part Replacement Status" >
				<bean:write name="hemDeskFB" property="strSparePartStatusOptions" filter="false" />
				</select>
				</div>
				</td>
				<td width="10%" class="LABEL"><div align="center">
				<input type="text" name="strSpareCost" id="strSpareCost#delIndex#" maxlength="10" size="8" onclick="select();" onkeyup="calculateTotal(this);" onblur="calculateTotal(this);" onkeypress="return validateData(event,7);  "/>
				</div></td>
				
			<td width="5%" class="LABEL">
			
			<img name=""
			onkeypress="onPressingEnter(this,event)"
			src="../../hisglobal/images/minus.gif" style="cursor: pointer;"
			title="Delete This Row" onclick="deleteRow('#delIndex#',1,0);calculateTotal('strSpareCost');">
			</td>
		
			
			
		
	</tr>
</table>
</his:ContentTag>
</div>
<input type="hidden" name="rowIndex1" value="0"> 
<input type="hidden" name="rowLength1" value="0">


</form>
