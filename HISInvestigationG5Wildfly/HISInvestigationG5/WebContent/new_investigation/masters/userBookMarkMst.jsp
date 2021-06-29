<!-- 
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : USER BOOKMARK MASTER
 ## Purpose						        : 
 ## Date of Creation					:19-Mar-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
 -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
<his:javascript src="/new_investigation/js/labRequisition.js" />
<his:javascript src="/new_investigation/js/userBookMarkMaster.js" />


<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<style type="text/css">
#div2 .filterCombo {
	width: 150px;
	line-height: 10px;
	font-family: arial;
	font-size: 11px;
	color: #000000;
	vertical-align: top;
}


.tooltip {
  position: relative;
  display: inline-block;
 
}

.tooltip .tooltiptext {
  visibility: hidden;
  width: 120px;
  background-color: #555;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 5px 0;
  position: absolute;
  z-index: 1;
  bottom: 125%;
  left: 50%;
  margin-left: -60px;
  opacity: 0;
  transition: opacity 0.3s;
}

.tooltip .tooltiptext::after {
  content: "";
  position: absolute;
  top: 100%;
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
  visibility: visible;
  opacity: 1;
}

</style>


<script type="text/javascript">

function clearLabSelected(){
document.getElementsByName('labCode')[0].selectedIndex = "0";
}

function showHideDiv()
{	
	document.getElementsByName('testCode')[0].selectedIndex = "0";
	document.getElementsByName("groupCode")[0].selectedIndex = "0";
	
	var isTestGroup = document.getElementsByName('isTestGroup')[0].value;
	if (document.getElementsByName('isTestGroup')[0].checked){
		//alert("isTestGroup0 is checked= "+document.getElementsByName('isTestGroup')[0].value);
		document.getElementById("div4").style.display="";
		document.getElementById("div5").style.display="none";
	}
	else if (document.getElementsByName('isTestGroup')[1].checked){
		//alert("isTestGroup1  is checked= "+document.getElementsByName('isTestGroup')[1].value);
		document.getElementById("div4").style.display="none";
		document.getElementById("div5").style.display="";	
	}
	else{//alert("isTestGroup0,1 none is checked");
	document.getElementById("div4").style.display="none";
	document.getElementById("div5").style.display="none";
	}
		
var ageBound = document.getElementsByName('bookarkType');
var ageBound_value;
for(var i = 0; i < ageBound.length; i++){
    if(ageBound[i].checked){
    	ageBound_value = ageBound[i].value;
    }
}

//added by prashant
if(ageBound_value=="1")
{
document.getElementById("div1").style.display="";	
document.getElementById("div2").style.display="none";
document.getElementById("div3").style.display="none";
}
else if (ageBound_value=="2")
{
document.getElementById("div1").style.display="none";
filterUnitByDept();
document.getElementById("div2").style.display="";
document.getElementById("div3").style.display="";
}

else{
	document.getElementById("div1").style.display="";
	document.getElementById("div2").style.display="none";
	document.getElementById("div3").style.display="none";
}

 }

function showHideDiv1()
{
	
var ageBound = document.getElementsByName('isTestGroup');
var ageBound_value;
for(var i = 0; i < ageBound.length; i++){
    if(ageBound[i].checked){
    	ageBound_value = ageBound[i].value;
    }
}

//added by prashant


	document.getElementsByName('testCode')[0].selectedIndex = "0";
	document.getElementsByName("groupCode")[0].selectedIndex = "0";
	
if(ageBound_value=="0")
	{ 
	//alert("ageBound_value or isisTestGroup value = "+ageBound_value);
	document.getElementById("div4").style.display="";	
	document.getElementById("div5").style.display="none";
	//alert("value of testCode "+document.getElementsByName('testCode')[0].value);
	//alert("value of groupCode "+document.getElementsByName('groupCode')[0].value);
	}
else if (ageBound_value=="1")
	{
	
	//alert("ageBound_value or isisTestGroup value = "+ageBound_value);
	document.getElementById("div4").style.display="none";
	document.getElementById("div5").style.display="";
	//alert("value of groupCode "+document.getElementsByName('groupCode')[0].value);
	//alert("value of testCode "+document.getElementsByName('testCode')[0].value);
	
	}
else {
	document.getElementById("div4").style.display="none";
	document.getElementById("addButtontr").style.display="none";
	document.getElementById("div5").style.display="none";

}

}

// Ajax call for Unit based on Department
function filterUnitByDept()
{
	var unitList = getUnit();
	setUnit(unitList);
}

function getUnit()
{
		var flg = false;
		var objunitList = null;
		var _mode = "AJX_G_UNIT";
		var dept =document.getElementsByName("deptCode")[0].value;
		var urlNew= "/HISInvestigationG5/new_investigation/masters/UserBookMarkMstACT.cnt?hmode="+_mode+'&dept='+dept;	
		//alert(urlNew);	
		var objXHR = {url: urlNew, sync:true, postData: "", handleAs: "json",
			load: function(data) 
			{
				//alert("DATA= :"+data.length+data[0].roomCode);
				objunitList = data;
				flg = true;
			},
	        error: function(error)
	        {
	            //if(typeof objMenuMealTimeList == 'undefined' || objMenuMealTimeList==null || objMenuMealTimeList=="" || objMenuMealTimeList.length==0)
	            	//alert("No  Type");
	            alert(error+"Error while populating Information");
	            objunitList = null;
	            flg = false;
	        }};
	
		var objDojoAjax = dojo.xhrPost(objXHR);		
		return objunitList;
}

function setUnit(objunitList)
{
	//alert("room No :"+obj.roomId+"  room Name :"+obj.roomName);
	//var objunitList = obj.value;
	if(objunitList !=null)
	{
		var unitList = document.getElementsByName("deptUnitCode")[0];
		unitList.innerHTML="";
		for(var i=0;i<objunitList.length;i++)
			{
				//alert(objunitList.length);
				opt = document.createElement("option");
				opt.value = objunitList[i].deptUnitCode;
				opt.innerHTML = objunitList[i].deptUnitName;
				unitList.appendChild(opt);
			}
	}
}

// Ajax Call for Test Based on Lab 

function filterTestByLab()
{
	var testList = getTest();
	setTest(testList);
}

function getTest()
{
		var flg = false;
		var objtestList = null;
		var _mode = "AJX_G_TEST";
		var labCode =document.getElementsByName("labCode")[0].value;
		var bookmarkCode =document.getElementsByName("bookmarkCode")[0].value;
		var userId =document.getElementsByName("userId")[0].value;
		var urlNew= "/HISInvestigationG5/new_investigation/masters/UserBookMarkMstACT.cnt?hmode="+_mode+'&labCode='+labCode+'&bookMarkCode='+bookmarkCode+'&userId='+userId;;	
		//alert(urlNew);	
		var objXHR = {url: urlNew, sync:true, postData: "", handleAs: "json",
			load: function(data) 
			{
				objtestList = data;
				flg = true;
			},
	        error: function(error)
	        {
	            //if(typeof objMenuMealTimeList == 'undefined' || objMenuMealTimeList==null || objMenuMealTimeList=="" || objMenuMealTimeList.length==0)
	            	//alert("No  Type");
	            alert(error+"Error while populating Information");
	            objtestList = null;
	            flg = false;
	        }};
	
		var objDojoAjax = dojo.xhrPost(objXHR);	
		//alert("objtestList::"+objtestList[0].value);	
		return objtestList;
}

function setTest(objtestList)
{
	//alert("room No :"+obj.roomId+"  room Name :"+obj.roomName);
	//var objtestList = obj.value;
	if(objtestList !=null)
	{
		var testList = document.getElementsByName("testCode")[0];
		testList.innerHTML="";
	//	alert(objtestList.length);
		opt1 = document.createElement("option");
		opt1.value ="-1";
		opt1.innerHTML ="Select Test";
		testList.appendChild(opt1);
		for(var i=0;i<objtestList.length;i++)
			{
			/* console.log("hey objtestList"+[i]+" = "+objtestList[i]); */ /* key value pair of objects objects */
			console.log("hey objtestList"+[i]+" .testCode = "+objtestList[i].testCode);
				opt = document.createElement("option");
				opt.value = objtestList[i].testCode;
				opt.innerHTML = objtestList[i].testName;
				testList.appendChild(opt);
			}
	}
	
	else {
		var testList = document.getElementsByName("testCode")[0];
		testList.innerHTML="";
		opt1 = document.createElement("option");
		opt1.value ="-1";
		opt1.innerHTML ="No Test Available";
		testList.appendChild(opt1);		
	}
}

//added by prashant
//Ajax Call for GroupTest Based on Lab but initially it is independent to lab selected

function filterTestGroupByLab()
{
	var testGroupList = getTestGroup();
	setTestGroup(testGroupList);
}

function getTestGroup()
{
		var flg = false;
		var objtestGroupList = null;
		var _mode = "AJX_G_TESTgroup";
		var labCode =document.getElementsByName("labCode")[0].value;
		var bookmarkCode =document.getElementsByName("bookmarkCode")[0].value;
		var userId =document.getElementsByName("userId")[0].value;
		var urlNew= "/HISInvestigationG5/new_investigation/masters/UserBookMarkMstACT.cnt?hmode="+_mode+'&labCode='+labCode+'&bookMarkCode='+bookmarkCode+'&userId='+userId;;	
		//alert(urlNew);	
		var objXHR = {url: urlNew, sync:true, postData: "", handleAs: "json",
			load: function(data) 
			{
				objtestGroupList = data;
				flg = true;
			},
	        error: function(error)
	        {
	            alert(error+"Error while populating Information");
	            objtestGroupList = null;
	            flg = false;
	        }};
	
		var objDojoAjax = dojo.xhrPost(objXHR);	
		//alert("objtestGroupList::"+objtestGroupList[0].value);	
		return objtestGroupList;
}

function setTestGroup(objtestGroupList)
{
	
	if(objtestGroupList !=null)
	{
		var testGroupList = document.getElementsByName("groupCode")[0];
		testGroupList.innerHTML="";
		//alert(objtestGroupList.length);
		
		opt1 = document.createElement("option");
		opt1.value ="-1";
		opt1.innerHTML ="Select Test Group";
		testGroupList.appendChild(opt1);
		for(var i=0;i<objtestGroupList.length;i++)
			{
			/* console.log("hey objtestGroupList"+[i]+" = "+objtestGroupList[i]); */ //print key value pair of objects objects */
			//console.log("hey objtestGroupList"+[i]+" .testGroupCode = "+objtestGroupList[i].groupCode); //print value of key groupCode
				opt = document.createElement("option");
				opt.value = objtestGroupList[i].groupCode;
				opt.innerHTML = objtestGroupList[i].groupName;
				testGroupList.appendChild(opt);
			}
	}
	
	else {
		var testGroupList = document.getElementsByName("groupCode")[0];
		testGroupList.innerHTML="";
		opt1 = document.createElement("option");
		opt1.value ="-1";
		opt1.innerHTML ="No Test Group Available";
		testGroupList.appendChild(opt1);
	}
}


function getBookMarkType()
{
      var code = document.getElementsByName("bookmarkCode")[0].value;
      if (code != -1) {
            submitForm('GETTYPE');
      } else {
            submitForm('ADD');
      }    
}

 function modifyClear()
  {
   
     submitForm('CLEAR');
  
  }

</script>
<body onload="showHideDiv();">
	<html:form action="/masters/UserBookMarkMstACT">

		<his:TitleTag name="User Bookmark Master">
			<%-- <his:insertDateTag /> --%>
		</his:TitleTag>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="2" cellpadding="1">
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="RED" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="BookmarkName" />&nbsp;
							</font>
						</div>
					</td>
					<logic:notEqual name="UserBookMarkMstFB" property="hmode"
						value="MODIFY">
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="UserBookMarkMstFB" property="bookmarkCode"
									onchange="getBookMarkType();" style="width:30%" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<html:options
										collection="<%=InvestigationConfig.LIST_BOOKMARK_COMBO %>"
										property="value" labelProperty="label" />
								</html:select>
							</div>
						</td>
					</logic:notEqual>
					<logic:equal name="UserBookMarkMstFB" property="hmode"
						value="MODIFY">
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="UserBookMarkMstFB" property="bookmarkName"
									style="width:30%" readonly="true" tabindex="1"></html:text>
							</div>
						</td>
					</logic:equal>
				</tr>
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="RED" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="BookmarkType" />&nbsp;
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<html:radio name="UserBookMarkMstFB" tabindex="1"
								property="bookarkType" disabled="true" value="1"></html:radio>
							<bean:message key="UserWise" />

							<%-- <html:radio name="UserBookMarkMstFB" tabindex="1"
								property="bookarkType" disabled="true" value="2"></html:radio>
							<bean:message key="DeptUnitWise" / >--%>
						</div>
					</td>
				</tr>

				<tr id="div1" style="display: none">
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="RED" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="UserName" />&nbsp;
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<html:select name="UserBookMarkMstFB" property="userId"
								style="width:30%" tabindex="1" onchange="clearLabSelected();">
								<html:option value="-1">Select Value</html:option>
								<html:options
									collection="<%=InvestigationConfig.LIST_USER_COMBO %>"
									property="value" labelProperty="label" />
							</html:select>
						</div>
					</td>
				</tr>

				<tr id="div2" style="display: none">
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="RED" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="DeptName" />&nbsp;
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<html:select name="UserBookMarkMstFB" property="deptCode"
								style="width:30%" styleClass="filterCombo"
								onchange="filterUnitByDept();" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<html:options
									collection="<%=InvestigationConfig.LIST_DEPT_COMBO %>"
									property="value" labelProperty="label" />
							</html:select>
						</div>
					</td>
				</tr>
				<tr id="div3" style="display: none">
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="RED" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="UnitName" />&nbsp;
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<html:select name="UserBookMarkMstFB" property="deptUnitCode"
								style="width:30%" tabindex="1">
								<html:option value="-1">Select Value</html:option>
							</html:select>
						</div>
					</td>
				</tr>

				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="RED" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="labName" />&nbsp;
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<html:select name="UserBookMarkMstFB" property="labCode"
								style="width:30%" onchange="filterTestByLab();filterTestGroupByLab();" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<html:options
									collection="<%=InvestigationConfig.LIST_LAB_COMBO %>"
									property="value" labelProperty="label" />
							</html:select>
						</div>
					</td>
				</tr>
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="RED" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="TestGroupWise" />&nbsp;
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<html:radio name="UserBookMarkMstFB" tabindex="1"
								property="isTestGroup" value="0" onclick="showHideDiv1();"></html:radio>
							<bean:message key="TestWise" />

							<html:radio name="UserBookMarkMstFB" tabindex="1"
								property="isTestGroup" value="1" onclick="showHideDiv1();"></html:radio>
							<bean:message key="TestGroupWise" />
						</div>
					</td>
				</tr>

				<tr id="div4" style="display: none">
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="RED" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="testName" />&nbsp;
							</font>
						</div>
					</td>
					<td width="45%" class="tdfont">
						<div align="left">
							<html:select name="UserBookMarkMstFB" property="testCode"
								style="width:30%" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%= InvestigationConfig.LIST_TEST_COMBO%>">
									<html:options
										collection="<%=InvestigationConfig.LIST_TEST_COMBO %>"
										property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
				</tr>
				
				<!-- commented by prashant -->
				<%-- <logic:notEqual name="UserBookMarkMstFB" property="hmode"
					value="MODIFY">
					<tr id="testPlus" style="display: none">
						<td width="5%" class="tdfont">
							<div align="right"></div>
						</td>
						<td width="5%" class="tdfont">
							<div align="center">
								<img class="button" id="addButton" style="cursor: pointer"
									tabindex="1"
									src='<his:path src="/hisglobal/images/plus_new.png"/>'
									onclick="if(validateOpinionAdd()) submitForm('ADDOPINION')"
									tabindex="1">
							</div>
						</td>
					</tr>
				</logic:notEqual> --%>


				<tr id="div5" style="display: none">
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="RED" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="TestGroupName" />&nbsp;
							</font>
						</div>
					</td>
					<td width="45%" class="tdfont">
						<div align="left">
							<html:select name="UserBookMarkMstFB" property="groupCode"
								style="width:30%" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								 <logic:present
									name="<%= InvestigationConfig.LIST_TEST_GROUP_COMBO%>">
									<html:options
										collection="<%=InvestigationConfig.LIST_TEST_GROUP_COMBO %>"
										property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
				</tr>
				<logic:notEqual name="UserBookMarkMstFB" property="hmode"
					value="MODIFY">
					<tr id="addButtontr" style="display:">
						<td width="50%" class="tdfont">
							<div align="center"></div>
						</td>
						<td width="50%" class="tdfont">
							<div align="center">
								<img class="button" id="addButton" style="cursor: pointer"
									tabindex="1"
									src='<his:path src="/hisglobal/images/plus_new.png"/>'
									onclick="if(validateOpinionAdd()) submitForm('ADDOPINION')"
									tabindex="1">
							</div>
						</td>
					</tr>
				</logic:notEqual>

			</table>
		</his:ContentTag>
		<logic:notEqual name="UserBookMarkMstFB" property="hmode"
			value="MODIFY">
			<%if(session.getAttribute(InvestigationConfig.ESSENTIAL_ALL_TEST_VO)!=null){ %>
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<th><b>Lab Name</b></th>
						<th><b>Test Name</b></th>
						<th><b>Group Name</b></th>
					</tr>
					<logic:iterate id="opinionReqVO"
						name="<%=InvestigationConfig.ESSENTIAL_ALL_TEST_VO%>"
						type="new_investigation.vo.UserBookMarkMstVO">
						<tr class="tabledatarow">
							<td class="tdfont" width="30%">
								<div align="center">
								     
									<%=opinionReqVO.getLabName() %>
								</div>
							</td>
							<td class="tdfont" width="32%">
								<div align="center">
									<%=opinionReqVO.getTestName()==null?"":opinionReqVO.getTestName() %>
								</div>
							</td>
							<td class="tdfont" width="32%">
								<div align="center">
									<%=opinionReqVO.getGroupName()==null?"----":opinionReqVO.getGroupName() %>
								</div>
							</td>
							<td class="tdfont" width="5%">
								<div align="center" class="tooltip">
									<img class="button" style="cursor: pointer" tabindex="1"
										src='<his:path src="/hisglobal/images/minus.gif"/>'
										onkeypress="if(event.keyCode==13) deleteOpinionRow('<%=opinionReqVO.getLabCode() %>','<%=opinionReqVO.getTestCode() %>','<%=opinionReqVO.getGroupCode() %>','<%=opinionReqVO.getIsTestGroup()%>') ;"
										onclick=" deleteOpinionRow('<%=opinionReqVO.getLabCode() %>','<%=opinionReqVO.getTestCode() %>','<%=opinionReqVO.getGroupCode() %>','<%=opinionReqVO.getIsTestGroup()%>')"
										tabindex="1">
									<html:hidden name="UserBookMarkMstFB" property="hiddenLabCode" />
									<html:hidden name="UserBookMarkMstFB" property="hiddenTestCode" />
									<html:hidden name="UserBookMarkMstFB" property="hiddenLabName" />
									<html:hidden name="UserBookMarkMstFB" property="hiddenGroupCode" />
									<html:hidden name="UserBookMarkMstFB" property="hiddenIsTestGroup" />
									
									<span class="tooltiptext">In case of "Group Test" then all the test belonging to that Group will be removed</span>
								</div>
							</td>
						</tr>
					</logic:iterate>
				</table>
			</his:ContentTag>
			<%} %>
		</logic:notEqual>
		<his:ButtonToolBarTag>

			<span id="saveDiv"> <logic:notEqual name="UserBookMarkMstFB"
					property="hmode" value="MODIFY">
					<logic:notEqual name="UserBookMarkMstFB" property="hmode"
						value="VIEW">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-sv.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
							onclick="finalSubmit('SAVE')" tabindex="1">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-clr.png"/>'
							style="cursor: pointer" onclick="clearForm()"
							onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
					</logic:notEqual>
				</logic:notEqual> <logic:equal name="UserBookMarkMstFB" property="hmode"
					value="MODIFY">
					<img class="button"
						src='<his:path src="/hisglobal/images/btn-mo.png"/>'
						style="cursor: pointer"
						onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')"
						onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
					<img class="button"
						src='<his:path src="/hisglobal/images/btn-clr.png"/>'
						style="cursor: pointer" onclick="modifyClear()"
						onkeypress="if(event.keyCode==13) modifyClear()" tabindex="1">
				</logic:equal> <img class="button"
				src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
				style="cursor: pointer" onclick="submitForm('CANCEL')"
				onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
			</span>
		</his:ButtonToolBarTag>
		<his:status />
		<html:hidden name="UserBookMarkMstFB" property="hmode" />
		<html:hidden name="UserBookMarkMstFB" property="testCode" />
		<html:hidden name="UserBookMarkMstFB" property="labCode" />
		<html:hidden name="UserBookMarkMstFB" property="bookmarkCode" />
		<html:hidden name="UserBookMarkMstFB" property="bookarkType" />
		<html:hidden name="UserBookMarkMstFB" property="deptCode" />
		<html:hidden name="UserBookMarkMstFB" property="deptUnitCode" />
		<html:hidden name="UserBookMarkMstFB" property="groupCode" />
		<html:hidden name="UserBookMarkMstFB" property="seqNo" />
	</html:form>

</body>

</html>