<!-- 
 /****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	COLLECTION AREA MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	This master is used to define the collection areas for 
 											sample collection area process
 ## Date of Creation					: 	20-Feb-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/**************************************************************************************************************/ 
-->



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.CollectionAreaMasterVO"%>
<%@page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/CollectionAreaMstAddMod.js" />
<body onload="checktypevalue()">

	<script type="text/javascript">

	
function checktypevalue(){
	
	var displaydiv=document.getElementById('wardlist');
	var displaydivcol=document.getElementById('wardcol');
	
	if(document.getElementsByName("collectionareaType")[1].checked==true)	
	{
		displaydiv.style.display="";
		displaydivcol.style.display="";                     
	}
	
	
}


function clearaddForm()
{
 
  document.getElementsByName('collectionareaName')[0].value="";
  document.getElementsByName('collectionareaType')[0].checked="true";
  document.getElementsByName('locationCode')[0].value="-1";
  document.getElementsByName('remarks')[0].value="";
  document.getElementsByName('wardCode')[0].value="-1";
 
  document.getElementsByName('sopbased')[0].checked="true";
  
  
	var displaydiv=document.getElementById('wardlist');
	var displaydivcol=document.getElementById('wardcol');
	displaydiv.style.display='none';
	displaydivcol.style.display='none';




  
}
	
function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].collectionareaName,"collectionareaName"))
     {
         valid=true ;
     }
  return valid;
}

var displayward=function(){
	var displaydiv=document.getElementById('wardlist');
	
	if(displaydiv.style.display=='none'||displaydiv.style.display=='')
		displaydiv.style.display='block';
	
	var displaydivcol=document.getElementById('wardcol');
	
	if(displaydivcol.style.display=='none'||displaydivcol.style.display=='')
		displaydivcol.style.display='block';
}
 
 
 
var hideward=function(){
	var displaydiv=document.getElementById('wardlist');
	
	if(displaydiv.style.display=='block'||displaydiv.style.display=='')
		displaydiv.style.display='none';
	
	
	var displaydivcol=document.getElementById('wardcol');
	
	if(displaydivcol.style.display=='block'||displaydivcol.style.display=='')
		displaydivcol.style.display='none';
	
}



</script>

		<html:form action="/masters/CollectionAreaMstACTION">
		<html:hidden name="CollectionAreaMstFB" property="hmode" />
		<html:hidden name="CollectionAreaMstFB" property="collectionareaCode" />
		<html:hidden name="CollectionAreaMstFB" property="selectedChk" />
		

		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>

		<logic:equal name="CollectionAreaMstFB" property="hmode" value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>
		

											<!-- Logic for ADD Page -->

	
	<his:TransactionContainer>
			<his:TitleTag name="Collection Area Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>



				<table width="100%" border="0" cellspacing="1" cellpadding="0">

										<!-- Collection Area Name -->
										
					<tr>
						
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="CollectionAreaName" />&nbsp;</b>
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="CollectionAreaMstFB" property="collectionareaName"
							maxlength="60" size="30" readonly="<%=this.readOnly=false %>"
					onkeypress="return validateAlphaNumericOnly(event,this)"
							tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
						
										
					
					
									<!-- Location Combo -->
										
					<tr>
						
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="locationCombo" />&nbsp;</b>
								</font>
							</div>
						</td>

						
						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_LOCATION_COMBO %>">
								<div align="left">

									<html:select name="CollectionAreaMstFB"
										property="locationCode" tabindex="1" style="width:200px;" >
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_LOCATION_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present>
						</td>
					</tr>
					
					
											<!-- REMARKS -->
											
					<tr>
						<td width="50%" class="tdfonthead">
			        		<div align="right">
			             	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
 						 	</font> 
						 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="remarks"/>&nbsp;</b>
							 </font>
				     		</div>
			     		</td>
		
		      			<td width="50%" class="tdfont">
			      			<div align="left">
					 		<html:textarea name="CollectionAreaMstFB" property="remarks"  tabindex="1"  readonly="<%=this.readOnly %>"
								onkeypress="return CheckMaxLength(event,this,50,1)" cols="28">
					 		</html:textarea>
				  			</div>
		     			</td>		
		     		</tr>					
										
										<!-- Collection Area Type -->
										
					 <tr>
			   			 <td width="50%" class="tdfonthead">
			   	  			   <div align="right">
			   			          <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
								 </font> 
								 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
										<b><bean:message key="CollectionAreaType"/>&nbsp;</b>
								 </font>
						     </div>
			  		    </td>
			
			
					    <td width="50%" class="tdfont">
			   					   <div align="left">
					  		<html:radio name="CollectionAreaMstFB"  tabindex="1" property="collectionareaType" value="1" onclick="hideward();" ></html:radio>
									<bean:message key="General"/>
							<html:radio name="CollectionAreaMstFB" tabindex="1" property="collectionareaType" value="2" onclick="displayward();"></html:radio>
									<bean:message key="Ward"/>
								  </div>
			     		</td>
			     	</tr>
			     	
			     	<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b>Area Type&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">

								<html:radio name="CollectionAreaMstFB" tabindex="1"
									property="sopbased" value="1"></html:radio>
								 Sample Based
								
								<html:radio name="CollectionAreaMstFB" tabindex="1"
									property="sopbased" value="2"></html:radio>
									Patient Based
								
							</div>
						</td>
					</tr>
								
							
									<!-- Ward Combo -->
										
										
										
					<tr>
						
						<td width="50%" class="tdfonthead">
							<div align="right" id="wardcol" style="display:none;">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="wardCombo" />&nbsp;</b>
								</font>
							</div>
						</td>

						
						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_WARD_COMBO %>">
								 <div align="left" id="wardlist" style="display:none;">

									<html:select name="CollectionAreaMstFB"
										property="wardCode" tabindex="1" style="width:200px;">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_WARD_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
			
							</logic:present>
						</td>
					</tr>	
					
				</table>
			</his:ContentTag>
					
			

			<his:ButtonToolBarTag>
				<span id="saveDiv"> 
				
				<logic:notEqual
					name="CollectionAreaMstFB" property="hmode" value="MODIFY">
						<logic:notEqual name="CollectionAreaMstFB" property="hmode"
						value="VIEW">
							<img class="button"
							src='<his:path src="/hisglobal/images/btn-sv.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
							onclick="finalSubmit('SAVE')" tabindex="1">
							<img class="button"
							src='<his:path src="/hisglobal/images/btn-clr.png"/>'
							style="cursor: pointer" onclick="clearaddForm()"
							onkeypress="if(event.keyCode==13) clearaddForm()" tabindex="1">
						</logic:notEqual>
					</logic:notEqual> 
					
					<logic:equal name="CollectionAreaMstFB" property="hmode"
					value="MODIFY">
						<img class="button"
						src='<his:path src="/hisglobal/images/btn-mo.png"/>'
						style="cursor: pointer"
						onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')"
						onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
						<img class="button"
						src='<his:path src="/hisglobal/images/btn-clr.png"/>'
						style="cursor: pointer" onclick="clearForm()"
						onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
					</logic:equal> 
					
					<img class="button"
				src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
				style="cursor: pointer" onclick="submitForm('CANCEL')"
				onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
				
				</span>
			</his:ButtonToolBarTag>
			<his:status />
			
	</his:TransactionContainer>




	</html:form>
</body>
</html>
