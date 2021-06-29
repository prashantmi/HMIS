  <%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@page import="mrd.MrdConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit()
{
	var valid=true;
	if(document.getElementsByName('enclosure')[0].value=="")
	{
		alert("Enter Enclosure...");
		document.getElementsByName('enclosure')[0].focus();
		valid=false;
	}
	if(document.forms[0].isActive && document.forms[0].isActive.value=="-1")
	{
		alert("Select IsActive Status ... ");
		document.forms[0].isActive.focus();
		valid=false;                          
	}
	
    return valid;
 } 	
	
function finalSubmit(mode)
{
	if (validateFinalSubmit())
	{		
		submitPage(mode);
	}
}

function clearForm()
{
   document.getElementsByName('enclosure')[0].value="";
   document.getElementsByName('remarks')[0].value="";
   document.getElementsByName('recordTypeId')[0].value="-1";
   if(document.forms[0].isActive)
 	   document.forms[0].isActive.value=="-1"
}



function moveRightSingle()
{
	var source;
	var target;
	
	source  = document.forms[0].checkListID;
	target = document.forms[0].selectedCheckListID;	
	
	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}


function moveLeftSingle()
{
	var source;
	var target;

		target  = document.forms[0].checkListID;
		source = document.forms[0].selectedCheckListID;	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}


function moveLeftAll()
{
	var source;
	var target;

		target  = document.forms[0].checkListID;
		source = document.forms[0].selectedCheckListID;	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;
	
	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
	
		targetlen = target.length;							
		target.length = (targetlen+1);				
		
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}

	deleteThis(target,source);
}


function moveRightAll()
{
	var source;
	var target;
	
		source  = document.forms[0].checkListID;
		target = document.forms[0].selectedCheckListID;	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
		
		targetlen = target.length;							
		target.length = (targetlen+1);				
			
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}
	deleteThis(target,source);
}


function deleteThis(source,target)
{	
	var tarlen = target.length;
	var srclen = source.length;

	var a1 = new Array(tarlen);
	var a2 = new Array(tarlen);
	var a3 = new Array(srclen);

	for(var i=0;i<tarlen;i++)
	{		
		a1[i]= target.options[i].value;
		a2[i]= target.options[i].text;	
	}
	for( i=0;i<srclen;i++)		
		a3[i]= source.options[i].value;

	target.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;

	for(i=0;i<tarlen;i++)
	{		
		flag = 0;
		for(k=0;k<srclen;k++)
			if(a1[i]==a3[k])
				flag = 1;					
		if(flag==0)
		{
			target.length = (counter+1);
			target.options[counter].value = a1[i];
			target.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}

function MoveToSelected()
{
	// Unselect Remaining User
	for(var i=0;i<document.getElementsByName("checkListID")[0].length;i++)
		document.getElementsByName("checkListID")[0].options[i].selected=false;
	
	// Select All User in Selected
	for(var i=0;i<document.getElementsByName("selectedCheckListID")[0].length;i++)
		document.getElementsByName("selectedCheckListID")[0].options[i].selected=true;
}
  

function validateOk()
{
	var valid=false;
	var selectedIndex="";
	if(document.getElementsByName("selectedCheckListID")[0].options.length==0)
	{
		alert("Choose at Least One Check List");
		return false;
	}
	else
	{
		for(var i=0;i<document.getElementsByName("selectedCheckListID")[0].options.length;i++)
		{
			selectedIndex=selectedIndex+document.getElementsByName("selectedCheckListID")[0].options[i].value+"@";
		}
		
		//alert("selectedIndex "+selectedIndex);
		
		if(selectedIndex!="") selectedIndex=selectedIndex.substring(0,selectedIndex.length-1);
		opener.document.getElementsByName('hmode')[0].value='POPULATE';
		opener.document.getElementsByName('concatedIndex')[0].value=selectedIndex;
		opener.document.forms[0].submit();
		self.close();
	}
		
}  
</script>

<body>
	<html:form action="/master/recordTypeCheckListMst">
		<his:TransactionContainer>
			<his:SubTitleTag name="Add Check List">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="center">
								<b>
									<bean:message key="checkList"/>
								</b>	
							</div>
						</td>
						<td width="20%" class="tdfont"></td>
						<td width="40%" class="tdfonthead">
							<div align="center">
								<b>
									<bean:message key="selectedCheckList"/>
								</b>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="center"> 
								<html:select name="RecordTypeCheckListMstFB" property="checkListID" multiple="true" size="6">
									<html:options collection ="<%=MrdConfig.LIST_NOT_MAPPED_CHECK_LIST %>" property = "value" labelProperty = "label"/>
								</html:select>
							</div>	
						</td>
						<td width="20%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle();'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll();'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle();'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll();'/>
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="60%" class="tdfonthead">
										<div align="right">
											<html:select name="RecordTypeCheckListMstFB" property="selectedCheckListID" multiple="true" size="6">
												<html:options collection ="<%=MrdConfig.LIST_NEW_ADDEDD_CHECKLIST_VO %>" property = "value" labelProperty = "label"/>
											</html:select>
										</div>
									</td>	
									<td width="40%" class="tdfonthead">
										<div align="left">
											&nbsp;&nbsp;<img src="/HIS/hisglobal/images/avai/arr-up.png" class="link" onClick='moveUP(document.forms[0].selectedCheckListID);'/>
											<br>
											&nbsp;&nbsp;<img src="/HIS/hisglobal/images/avai/arr-dwn.png" class="link" onClick='moveDOWN(document.forms[0].selectedCheckListID);'/>
										</div>
									</td>
								</tr>
							</table>
							
						</td>
						
						
					</tr>
				</table>
			</his:ContentTag>	
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>' style="cursor: pointer" onclick="validateOk() && submitTile('POPULATE')" onkeypress="if(event.keyCode==13)validateOk() && submitTile('POPULATE')">
			</his:ButtonToolBarTag>
			<html:hidden name="RecordTypeCheckListMstFB" property="hmode"/>
			
		</his:TransactionContainer>		
	</html:form>	
</body>