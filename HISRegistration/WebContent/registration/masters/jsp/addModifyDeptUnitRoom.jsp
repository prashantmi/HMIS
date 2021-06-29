<%@page import="vo.registration.DeptUnitRoomVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 08-Jan-2014 		-->

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>

<link href="./../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="./../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../hisglobal/css/jqueryExtValidationToolTip.css">
<!-- <link rel="stylesheet" href="/HISRegistration/struts/xhtml/styles.css" type="text/css" /> -->

<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>
<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript"	src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript"	src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script>
	function validateCheck() 
	{
		var valid = true;
		valid = insertCheckTwo();
		if (valid)
			valid = insertCheckOne();
		return valid;

	}

	function insertCheckOne() 
	{
		var valid = true;
		for ( var i = 0; i < document.getElementsByName("deptUnitRoomModel.capacity").length; i++) 
		{
			
			if (document.getElementsByName("deptUnitRoomModel.capacity")[i].value == "") 
			{
				document.getElementsByName("deptUnitRoomModel.capacity")[i].focus();
				valid = false;
				if (!valid)
					break;
			}
			else
			{
				valid= !(isNaN(document.getElementsByName("deptUnitRoomModel.capacity")[i].value));
			}
		}
		return valid;
	}

	function updateCheckOne() 
	{
		var valid = true;
		if (document.getElementsByName("deptUnitRoomModel.strCapacity")[0].value == "") 
		{
			document.getElementsByName("deptUnitRoomModel.strCapacity")[0].focus();
			valid = false;
		}
		else
		{
			valid= !(isNaN(document.getElementsByName("deptUnitRoomModel.strCapacity")[0].value));
		}
		return valid;
	}

	function insertCheckTwo() 
	{
		var valid = true;
		if (document.getElementsByName("deptUnitRoomModel.capacity").length == 0) 
		{
			$("#isRoomSelected").hide();
			$("#isRoomAdded").show();
			document.getElementsByName("deptUnitRoomModel.strRoomCode")[0].focus();
			valid = false;
		}
		return valid;
	}

	function saveDeptUnitRoomAction() 
	{
		capModeValue();
		if (validateCheck()) 
		{
			document.forms[0].action = "saveDeptUnitRoomMst.action";
			document.forms[0].submit();
		}
	}

	function capModeValue() 
	{
		for ( var i = 0; i < document.getElementsByName("capacityMode").length; i++) 
		{
			if (document.getElementsByName("capacityMode")[i].checked)
				document.getElementsByName("deptUnitRoomModel.capacityMode")[i].value = "true";
			else
			{
				document.getElementsByName("deptUnitRoomModel.capacityMode")[i].value = "false";
			}
		}

	}

	function updateDeptUnitRoom() 
	{
		if (updateCheckOne()) 
		{
			document.forms[0].action = "updateDeptUnitRoomMst.action";
			document.forms[0].submit();
		}
	}

	function cancelDeptUnitRoom() 
	{
		document.forms[0].action = "cancelDeptUnitRoomMst.action";
		document.forms[0].submit();
	}

	function getRoomDetails() 
	{
		retValue = true;
		capModeValue();
		if (document.getElementsByName("deptUnitRoomModel.strRoomCode")[0].value == "-1") 
		{
			$("#isRoomAdded").hide();
			$("#isRoomSelected").show();
			document.getElementsByName("deptUnitRoomModel.strRoomCode")[0].focus();
			retValue = false;
		}
		if (retValue) 
		{
			document.forms[0].action = "putDeptUnitRoomMst.action";
			document.forms[0].submit();
		}

	}

	function showInit() 
	{
		for ( var i = 0; i < document.getElementsByName("deptUnitRoomModel.capacity").length; i++) 
		{
			if (document.getElementsByName("deptUnitRoomModel.capacity")[i].value == "") 
			{
				$('[name="deptUnitRoomModel.capacity"]').validatebox({
					required : true,
					validType : 'numeric'
				});
			}
		}
		if(document.getElementsByName("flagAddMod")[0].value=="MODIFY")
		{
			$('[name="deptUnitRoomModel.strCapacity"]').validatebox({
				required : true,
				validType : 'numeric'
			});
		}
	}

	function removeRooms(index) 
	{
		var elmt = document.getElementsByName('deptUnitRoomModel.strRemoveRoom')[0];
		elmt.value = index;
		document.forms[0].action = "removeDeptUnitRoomMst.action";
		document.forms[0].submit();
	}
</script>
</head>
<body onload="showInit();">
	<s:form action="DeptUnitRoomMst">
		<div class="wrapper rounded">
			<div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col title width100 ">
						<s:text name="global.department" />
						&nbsp;
						<s:text name="global.unit" />
						&nbsp;
						<s:text name="global.room" />
						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add" />
						</s:if>
						<s:else>>><s:text name="global.modify" />
						</s:else>
					</div>
				</div>

				<div class="div-table-row ">
					<div class="div-table-col label  width25">
						<font color="#FF0000">*</font>&nbsp;
						<s:text name="global.department" />
						&nbsp;:
					</div>
					<div class="div-table-col control width25">
						<s:property value="%{deptUnitRoomModel.strDeptName}"></s:property>
						<s:hidden key="strDeptName" name="deptUnitRoomModel.strDeptName" value="%{deptUnitRoomModel.strDeptName}"></s:hidden>
						<s:hidden key="strDeptCode" name="deptUnitRoomModel.strDeptCode" value="%{deptUnitRoomModel.strDeptCode}"></s:hidden>
					</div>
					<div class="div-table-col label  width25">
						<font color="#FF0000">*</font>&nbsp;
						<s:text name="global.unit" />
						&nbsp;:
					</div>
					<div class="div-table-col control width25">
						<s:property value="%{deptUnitRoomModel.strUnitName}"></s:property>
						<s:hidden key="strUnitName" name="deptUnitRoomModel.strUnitName" value="%{deptUnitRoomModel.strUnitName}"></s:hidden>
						<s:hidden key="strUnitCode" name="deptUnitRoomModel.strUnitCode" value="%{deptUnitRoomModel.strUnitCode}"></s:hidden>
					</div>
				</div>
			</div>
			<%-- <s:if test="%{#session.existingRooms!=null && #session.existingRooms.size>0}"> --%>
			<s:if test="flagAddMod=='ADD'">
				<s:if
					test="%{#session.existingRooms!=null && #session.existingRooms.length>0}">
					<div class="div-table" style="">
						<div class="div-table-row ">
							<div class="div-table-col title width100 ">
								<s:text name="global.existing" />
								&nbsp;
								<s:text name="global.room" />
							</div>
						</div>
						<div class="div-table-listing rounded width100">
							<div class="div-table-row listHeader">
								<div class="div-table-col width50" align="center">
									<s:text name="global.room" />
									&nbsp;
									<s:text name="global.name" />
								</div>
								<div class="div-table-col width50" align="center">
									<s:text name="global.room" />
									&nbsp;
									<s:text name="global.sequence" />
								</div>
							</div>
							<s:iterator status="status" value="deptUnitRoomModel">
								<s:iterator status="i" value="existingRoomName">
									<div class="div-table-row listData">
										<div class="div-table-col width50" align="center">
											<s:property
												value="%{deptUnitRoomModel.existingRoomName[#i.index]}" ></s:property>
										</div>
										<div class="div-table-col width50" align="center">
											<s:property
												value="%{deptUnitRoomModel.existingsequenceNo[#i.index]}"></s:property>
											<s:hidden name="deptUnitRoomModel.existingsequenceNo"
												key="existingsequenceNo" id="sequenceNo"></s:hidden>
										</div>
									</div>
								</s:iterator>
							</s:iterator>
						</div>
					</div>
				</s:if>

				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100 ">
							<s:if test="flagAddMod=='ADD'">
								<s:text name="global.add" />&nbsp;<s:text name="global.room" />
							</s:if>
							<s:else>>><s:text name="global.modify" />&nbsp;<s:text
									name="global.room" />
							</s:else>
						</div>
					</div>
					<div class="div-table-row ">
						<div class="div-table-col label  width25">
							Select&nbsp;
							<s:text name="global.room" />
							&nbsp;&nbsp;
							<s:select key="strRoomCode" value="%{deptUnitRoomModel.strRoomCode}" headerKey="-1" headerValue="Select Value" list="%{#session.roomList}" listKey="value" listValue="label" name="deptUnitRoomModel.strRoomCode" cssStyle="width:198px">
							</s:select>
						</div>
						<div class="div-table-col column  width25">
							<img src="../hisglobal/images/plus.png" onclick="getRoomDetails();">
						</div>
						<div class="div-table-col column  width25"></div>
						<div class="div-table-col column  width25"></div>
					</div>
				</div>

				<s:if test="%{#session.newRooms!=null && #session.newRooms.size>0}">
					<div id="deptUnitRoomModel.roomdetails"
						class="div-table-listing rounded width100" style="">
						<div class="div-table-row listHeader">
							<div class="div-table-col width20">
								<s:text name="global.room" />
								&nbsp;
								<s:text name="global.name" />
							</div>
							<div class="div-table-col width10">
								<s:text name="global.sequence" />
							</div>
							<div class="div-table-col width20">
								<font color="#FF0000">*</font>&nbsp;
								<s:text name="global.capacity" />
							</div>
							<div class="div-table-col width30">
								<font color="#FF0000"></font>&nbsp;
								<s:text name="deptUnitRoom.daywisecapcitymode" />
							</div>
							<div class="div-table-col width20">
								<s:text name="global.remove" />
							</div>
						</div>

						<s:iterator status="status" value="%{#session.newRooms}">
							<div class="div-table-row listData">
								<div class="div-table-col width20">
									<s:property value="%{strRoomName}"></s:property>
									<s:hidden key="strRoomName" name="deptUnitRoomModel.strRoomName" value="%{strRoomName}"></s:hidden>
								</div>
								<div class="div-table-col width10">
									<s:property value="%{strRoomSequence}"></s:property>
									<s:hidden key="strRoomSequence"	name="deptUnitRoomModel.strRoomSequence" value="%{strRoomSequence}"></s:hidden>
								</div>
								<div class="div-table-col width20">
									<s:textfield key="capacity[#status.index]" name="deptUnitRoomModel.capacity" value="%{strCapacity}" maxlength="2" size="5" cssStyle="width:198px">
									</s:textfield>
								</div>
								<div class="div-table-col width30">
									<s:checkbox name="capacityMode" value="%{strCapacityMode}" key="capacityMode[#status.index]"  cssStyle="widt:197pxh"></s:checkbox>
									<s:hidden name="deptUnitRoomModel.capacityMode"></s:hidden>
								</div>
								<div class="div-table-col width10">&nbsp;</div>
								<div class="div-table-col width10">
									<img src="../hisglobal/images/Minus.png" onclick="removeRooms('<s:property value="%{#status.index}" />')" />
								</div>
							</div>
						</s:iterator>

					</div>
				</s:if>
			</s:if>

			<s:if test="flagAddMod=='MODIFY'">
				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100 ">
							<s:text name="global.modify" />
							&nbsp;
							<s:text name="global.room" />
						</div>
					</div>
					<div id="deptUnitRoomModel.roomdetails" class="div-table" style="">
						<div class="div-table-row ">
							<div class="div-table-col label  width50">
								<s:text name="global.room" />
								&nbsp;
								<s:text name="global.name" />
							</div>
							<div class="div-table-col column  width50">
								<s:property value="%{deptUnitRoomModel.strRoomName}"></s:property>
								<s:hidden key="strRoomName" name="deptUnitRoomModel.strRoomName" value="%{deptUnitRoomModel.strRoomName}"></s:hidden>
							</div>
						</div>

						<div class="div-table-row ">
							<div class="div-table-col label  width50">
								<s:text name="global.sequence" />
							</div>
							<div class="div-table-col column  width50">
								<s:property value="%{deptUnitRoomModel.strRoomSequence}"></s:property>
								<s:hidden key="strRoomSequence"	name="deptUnitRoomModel.strRoomSequence" value="%{deptUnitRoomModel.strRoomSequence}"></s:hidden>
							</div>
						</div>

						<div class="div-table-row ">
							<div class="div-table-col label  width50">
								<font color="#FF0000">*</font>&nbsp;
								<s:text name="global.capacity" />
							</div>
							<div class="div-table-col column  width50">
								<s:textfield key="strCapacity" name="deptUnitRoomModel.strCapacity" value="%{deptUnitRoomModel.strCapacity}" maxlength="2" size="5" cssStyle="width:197px">
								</s:textfield>
							</div>
						</div>

						<div class="div-table-row ">
							<div class="div-table-col label  width50">
								<font color="#FF0000"></font>&nbsp;
								<s:text name="deptUnitRoom.daywisecapcitymode" />
							</div>
							<div class="div-table-col column  width50">
								<s:checkbox name="deptUnitRoomModel.strCapacityMode" value="deptUnitRoomModel.strCapacityMode"></s:checkbox>
							</div>
							<s:hidden key="strRoomCode" name="deptUnitRoomModel.strRoomCode" value="%{deptUnitRoomModel.strRoomCode}"></s:hidden>
						</div>
					</div>
				</div>

			</s:if>

			<div class="div-table-button">
			<div class="div-table-row">
					<div class="div-table-col footerBar"></div>
					<div class="div-table-col emptyBar"></div>
				</div>
				<div class="div-table-row" align="center">
					<s:if test="flagAddMod=='ADD'">
						<a href="#" class="button" onclick="saveDeptUnitRoomAction();"><span class="save"><s:text name="save"/></span></a>
						<a href="#" class="button" onclick="submitCancelAction('<%=request.getSession().getAttribute("cnt_page")%>');"><span class="cancel"><s:text name="cancel"/></span></a>
						<a href="#" class="button" id="clearId"><span class="clear"><s:text name="clear"/></span></a>
					</s:if>
					<s:else>
						<a href="#" class="button" onclick="updateDeptUnitRoom();"><span class="save"><s:text name="save"/></span></a>
						<a href="#" class="button" onclick="cancelDeptUnitRoom();"><span class="cancel"><s:text name="cancel"/></span></a>
						<a href="#" class="button" id="reloadId"><span class="clear"><s:text name="clear"/></span></a>
					</s:else>
				</div>
			</div>

		</div>

		<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>
		<s:hidden key="strDeptUnitCode"	name="deptUnitRoomModel.strDeptUnitCode" value="%{deptUnitRoomModel.strDeptUnitCode}"></s:hidden>
		<s:hidden key="strRemoveRoom" name="deptUnitRoomModel.strRemoveRoom" value="%{deptUnitRoomModel.strRemoveRoom}"></s:hidden>

		<cmbPers:cmbPers />
		<div id="isRoomAdded" style="display: none">
			<h3>
				<font color="red">Please Add Room!</font>
			</h3>
		</div>
		<div id="isRoomSelected" style="display: none">
			<h3>
				<font color="red">Please Select Room!</font>
			</h3>
		</div>
		    <cmbPers:cmbPers></cmbPers:cmbPers>
			<s:token></s:token>
	</s:form>

	<s:actionerror/>

	
	 <div  class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="%{deptUnitRoomModel.StrWarning}"/></div>
 <div  class="div-table-col alignLeft fontNormalMessage" style="width: 100%"><s:property value="message"/></div>
	
	
	


	<div class="div-table">
	<div class="div-table-row   fontError">
	<s:fielderror ></s:fielderror>
	</div>
	</div>

	<%-- <h4>
		<font color="#FF0000"><s:property
				value="%{deptUnitRoomModel.StrWarning}" /></font>
	</h4>--%>
	
	<script type="text/javascript">
		$('#clearId').click(function(e) {
			$('[name="deptUnitRoomModel.capacity"]').val('');
			$('[name="deptUnitRoomModel.strRoomCode"]').val('-1');
			$('[name="capacityMode"]').prop('checked',false);
			$("#isRoomAdded").hide();
			$("#isRoomSelected").hide();
		});
		$('#reloadId').click(function(e){
			location.reload("true");
		});
	</script>
</body>
</html>