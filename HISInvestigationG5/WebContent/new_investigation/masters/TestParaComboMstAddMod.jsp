<!-- 
 /******************************************Start of program*************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	TEST PARAMETER COMBO MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:
 ## Purpose								:	This is used to define the Test Parameter combo values
  											for Test Parameter Master whose Element Type is combo i.e. D 
 ## Date of Creation					: 	27-Feb-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/***********************************************************************************************************/

-->



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="new_investigation.vo.TestParaComboMasterVO"%>
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
<his:javascript src="/new_investigation/js/TestParaComboMstAddMod.js" />
<his:javascript src="/new_investigation/js/jquery-3.3.1.min.js" />
<script>
$(document).ready(function(){
	
	
	if($('#defaultcheckbox').length){
		// alert ("checkedbox exist");
	 if(document.getElementsByName("setdefault")[0].value=="1"){
		$('#defaultcheckbox').prop('checked',true);
		$('#defaultcheckbox').attr('checked',true);
	 }else {
		$('#defaultcheckbox').prop('checked',false);
		$('#defaultcheckbox').attr('checked',false);
	 }
	} else {
		 //alert ("checkedbox doesn't exist");
	}
	
	
});

	function setcheckbox(obj){
		//alert ("called");
		//alert("setdefault value from the backend1="+document.getElementsByName("setdefault")[0].value);
		if(obj.checked){
			document.getElementsByName("setdefault")[0].value="1";
		}
		else{
			document.getElementsByName("setdefault")[0].value="0";
		}
		//alert(document.getElementsByName("setdefault")[0].value);
	}
	
function onLoad()
{
//alert(document.getElementsByName("paraType")[0].value);
// document.getElementsByClassName('newtestrow').onfocus();

for(var i=0;i<document.getElementsByName("paraType").length;i++)
	{

   if(document.getElementsByName("paraType")[i].checked==true)
	   {
		var a=document.getElementsByName("paraType")[i].value;
		showhidedata1(a);

	   }

	
	}


}

function showhidedata(obj)
{
	var values=obj.value;
	//alert(values);
	document.getElementsByName('testCode')[0].value="-1";
	document.getElementsByName('parameterCode')[0].value="-1";
	document.getElementsByName('paraType')[0].value=values;
	document.getElementById('showdata').style.display='';
	document.getElementById('showhide1').style.display='none';
}


function showhidedata1(obj)
{
	var values=obj;
	//alert(values);
	document.getElementsByName('paraType')[0].value=values;
	document.getElementById('showdata').style.display='';
	document.getElementById('showhide1').style.display='';
}
</script>

<body onload="onLoad()">
	<script type="text/javascript">

function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].testparaValue,"testparaValue"))
     {
         valid=true ;
     }
  return valid;
}


function getparameter() {
	//calll new mode in action GETPARAMETER

	var testCode = document.getElementsByName("testCode")[0].value;

	if (testCode != -1) {
		submitPage('GETPARAMETER');
	} else {
		submitPage('ADD');
	}
}

  
function displaydata()
{
	//alert("hi");
	var testCode=document.getElementsByName("testCode")[0].value;
	
	if(testCode!=-1)
	{	//alert("hi if");
		submitPage('DISPLAYDATA');
	}
	else
	{ //	alert("hi if");
		submitPage('ADD');
	} 
	

}
 
function clearaddForm()
{
	document.getElementsByName('testCode')[0].value="-1";
	document.getElementsByName('parameterCode')[0].value="-1";

    document.getElementsByName('testparaValue')[0].value="";
    
	var tableObj=document.getElementById('tableComponentDetailId');
  	var numRows=document.forms[0].numberOfRow.value;
  	var i=0;
  	
  	for(i=0;i<numRows;i++)
  		{
  		
  	    document.getElementsByName("testparaValue")[i].value="";
  		}
  	
	submitPage('ADD');

}



  
</script>

		<html:form action="/masters/TestParaComboMstACTION">
		<html:hidden name="TestParaComboMstFB" property="hmode" />
		<html:hidden name="TestParaComboMstFB" property="testparaSeq" />
		<html:hidden name="TestParaComboMstFB" property="selectedChk" />
		
		

		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>

		<logic:equal name="TestParaComboMstFB" property="hmode" value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>
<his:TransactionContainer>
		<logic:equal name="TestParaComboMstFB" property="hmode" value="DISPLAYDATA">
			<% this.readOnly=true; %>
		</logic:equal>
		

		<logic:equal name="TestParaComboMstFB" property="hmode" value="ADD">

			<his:TitleTag name="Test Parameter Combo Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>



				<table width="100%" border="0" cellspacing="1" cellpadding="0">

                 <tr>
                 
                  <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Test Type&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					  
				       
						<html:radio name="TestParaComboMstFB"  tabindex="1" property="paraType" value="0" onclick="showhidedata(this)" ></html:radio>
						<bean:message key="testPara"/>
						<html:radio name="TestParaComboMstFB" tabindex="1" property="paraType" value="1" onclick="showhidedata(this)"></html:radio>
						<bean:message key="deptPara"/>
					    <html:radio name="TestParaComboMstFB" tabindex="1" property="paraType" value="2" onclick="showhidedata(this)"></html:radio>
						<bean:message key="resourceForm"/> 
					    
				  </div>
			     </td>
                 
                 </tr>
                 
               </table>

						<%-------------------- test name combo values -----------------------%>
                    <div style="display: none" id="showdata">
                    <table width="100%" border="0" cellspacing="1" cellpadding="0"> 
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="testCombo" />&nbsp;</b>
								</font>
							</div>
						</td>



						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_TEST_COMBO %>">
								<div align="left">

									<html:select name="TestParaComboMstFB"
										property="testCode" tabindex="1" onchange="getparameter()" style="width:150px;">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_TEST_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>

					
					</tr>
					
				<%-------------------- TestPara combo values -----------------------%>
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="TestParaCombo" />&nbsp;</b>
								</font>
							</div>
						</td>



						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_TESTPARA_COMBO %>">
								<div align="left">

									<html:select name="TestParaComboMstFB"
										property="parameterCode" tabindex="1" onchange="displaydata()" style="width:150px;">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_TESTPARA_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>

					
					</tr>
					
					
					
	</table>
			</div>		
					
					
					
					<%-----------------------logic to display the saved records!! --%>

				<logic:present
					name="<%=InvestigationConfig.DISPLAY_DATA_TEST_PARA %>">
				
						<%
     
  	 List<TestParaComboMasterVO> testparaComboMasterVOLst=(List<TestParaComboMasterVO>)session.getAttribute(InvestigationConfig.DISPLAY_DATA_TEST_PARA);
  	 
 	if(testparaComboMasterVOLst!=null && testparaComboMasterVOLst.size()>0 )
 	{
 		int testparaComboLstSize=testparaComboMasterVOLst.size();
 		int count=1;
 		
					%>	
					<table width="100%">
					
					<tr>
					<td class="HEADER" width='100%' colspan="6" style="font-size: 14px; font-weight: bold;">
					 <b>PRESENT TEST PARAMETER VALUES</b>
					</td>
					</tr>
					
					<%
						for(int k=0;k<testparaComboLstSize;k++)
				 		{
				 			TestParaComboMasterVO voTestParaCombo=testparaComboMasterVOLst.get(k);
				 	 	    int tempVal=count%5;
				 	 	    if(tempVal==1)
				 	 	    {%><tr>
				 	 	    <%}%>
				 				<td class="tdfont" >
								<div align="center">
								<%if(voTestParaCombo.getSetdefault().equals("1")){%>
								<%=count+". "+voTestParaCombo.getTestparaValue() %>
									<label id="defaultestparalabel" style="color:Blue;"> Default</label>
							<%	} else { %>
								  <%=count+". "+voTestParaCombo.getTestparaValue() %>
								<%	}%>
								</div>
								</td>
							<%if(tempVal==0)
				 	 	    {%></tr>
							<%} count++;
							}%>
						</table>
		<%				
 		}
 		else 
 		{
 		%>
 		
 		<table width="100%">
					
					<tr>
					<td class="HEADER" width='100%' colspan="6" style="font-size: 14px; font-weight: bold;">
					 <b>No Test Parameter Values Exist. Enter New Values</b>
					</td>
					</tr>
					<tr></tr>
					<tr></tr>
					
					</table>	<%				
 		}
 		 
 		
 		%>

				</logic:present>
				
				
			<div id="showhide1" style="display:none">
				<table width="100%" id="tableComponentDetailId" cellspacing="1"
			cellpadding="0">
			
			<tr>
					<td class="HEADER" width='100%' colspan="6" style="font-size: 14px; font-weight: bold;">
					 <b>Enter New Test Parameter Values &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Default </b>
					</td>
					
					</tr>
						
			
					<tr class="newtestrow">
						<td width="30%" class="tdfonthead">
							<div align="right">
							<font color="RED" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
									key="TestParaValue" />&nbsp;</b>
							</font>
							</div>
						</td>
						<td width="40%" class="tdfont">
							<div align="left" class="newtestrow tdfont">
							<html:text name="TestParaComboMstFB" property="testparaValue"
							 size="30" readonly="<%=this.readOnly=false %>"
					         onkeypress="return validateAlphaNumericOnly(event,this)"
							tabindex="1">
							</html:text>
							<html:hidden name="TestParaComboMstFB" property="testParameterValue"/>
							</div>
						</td>
							
<%------------------------------------------------------------------------------------------------- --%>
						<td>
							<div align="center" width='30%' class="tdfont"  style="display: block;">
							<input type="radio"  name="setdefaultradio"  id="defaultradio0" onclick="setlabel();" value="0"></input>
							<html:hidden name="TestParaComboMstFB" property="setdefault" value=""/>
						    <b><label id='defaultlabel0' ></label></b>
							</div>
						</td>							
								<td>
							<div align="center" width="30%" class="tdfont">
								<img src='/HISInvestigationG5/hisglobal/images/plus.gif'
									alt="Add TestPara Combo" title="Add TestPara Combo"
									name="addRow"
									onkeypress="if(event.keyCode==13)AddRowToTable() ;"
									onclick="return AddRowToTable();">

							</div>
						</td>						
					</tr>
					
			</table>
			
             </div>
			</his:ContentTag>


		</logic:equal>



		<logic:equal name="TestParaComboMstFB" property="hmode"
			value="MODIFY">


			<his:ContentTag>


				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="testCombo" />&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_TEST_COMBO%>">
									<div align="left">

										<html:select name="TestParaComboMstFB" property="testCode"
											tabindex="1" disabled="true">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_TEST_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
												<html:hidden name="TestParaComboMstFB" property="testCode" />										
									</div>
								</logic:present></td>
					</tr>
					
					<%-------------------- TestPara combo values -----------------------%>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="TestParaCombo" />&nbsp;</b>
								</font>
							</div>
						</td>



						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_TESTPARA_COMBO %>">
								<div align="left">

									<html:select name="TestParaComboMstFB"
										property="parameterCode" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_TESTPARA_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
					<html:hidden name="TestParaComboMstFB" property="parameterCode" />										
							</div>
							</logic:present></td>

					
					</tr>
					
					
				 	<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
									key="TestParaValue" />&nbsp;</b>
								</font>
							</div>
						</td>

						<td width="30%" class="tdfont">
							<div align="left">
								<html:text name="TestParaComboMstFB" property="testParameterValue"
							 size="30" readonly="<%=this.readOnly=false %>"
					onkeypress="return validateAlphaNumericOnly(event,this)"
							tabindex="1">
								</html:text>
								<html:hidden name="TestParaComboMstFB" property="testparaValue"/>
							
						<input type="checkbox" onchange="setcheckbox(this);" id="defaultcheckbox"></input><label style="color:Blue;"> Default</label>
						<html:hidden name="TestParaComboMstFB" property="setdefault" />
						</div>
						</td>
							
							

<%------------------------------------------------------------------------------------------------- --%>							
							
							
												
					</tr>
				
				</table>
				</his:ContentTag>
			
		
					</logic:equal>
	
		
		
					
			

			<his:ButtonToolBarTag>
				<span id="saveDiv"> 
				
				<logic:notEqual
					name="TestParaComboMstFB" property="hmode" value="MODIFY">
						<logic:notEqual name="TestParaComboMstFB" property="hmode"
						value="VIEW">
							<img class="button"
							src='<his:path src="/hisglobal/images/btn-sv.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
							onclick="finalSubmit('SAVE')" tabindex="1">
		<img class="button"	src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearaddForm()"  tabindex="1">
						</logic:notEqual>
					</logic:notEqual> 
					
					<logic:equal name="TestParaComboMstFB" property="hmode"
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
			



<html:hidden property="numberOfRow" />
</his:TransactionContainer>
	</html:form>
</body>
</html>
