<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>

<script>
function submitTile(mode)
{
   document.getElementsByName("hmode")[0].value=mode;
   //alert("hmode"+document.getElementsByName("hmode")[0].value);
   document.forms[0].submit();
}

function validatModify()
{
	var len;
	var isValid = true;
	count=0;
	
	len=document.getElementsByName("chk").length;
	
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("chk")[i].checked)
		{
			count++;
		}
	}
	
	if(count==0)
	{    
        alert("Please Select record");
        return;
    }
    if(count>1)
    {    
        alert("Editing multiple records not allowed");
        return;
    }
    else
    {
    	submitTile('MOD');
    }	    
}

function validatDelete()
{
	var len;
	var isValid = true;
	count=0;
	
	len=document.getElementsByName("chk").length;
	
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("chk")[i].checked)
		{
			count++;
		}
	}
	
	if(count==0)
	{    
        alert("Please Select a record");
        return;
    }
    if(count>1)
    {    
        alert("Deleting multiple records not allowed");
        return;
    }
    else
    {
    	submitTile('DELETE');
    }	    
}



</script>

<%@ page import ="java.util.*,opd.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*" %>

<body>
	<html:form action="/master/deskMenuMacroMstList.cnt">
	<his:TransactionContainer>
	<%
  		boolean varIsNewStatus=false;	
  		String varStatus="";
	%>
<his:statusNew>
	<%varIsNewStatus=true;
	 varStatus="New";%>
</his:statusNew>
		<his:TitleTag name="Desk Menu Macro Master">
		</his:TitleTag>
		
		<his:ContentTag>
		
			<table width="100%" border="0"  cellspacing="1" cellpadding="0"> 
				<tr>
					<td width="25%" class="tdfonthead">
	           			<div align="right">	           
	              			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              				<b>
	              					<bean:message key="deskType"/>
	              				</b>
	              			</font>
	            		</div>
      				</td>  
      				<td width="25%" class="tdfont" >
	         			<div align="left">	           
	             			<html:select name="DeskMenuMacroMstListFB" tabindex="1" property="deskType" styleClass="regcbo" onchange="submitTile('GETMENU')">
			 					<html:option value="-1">Select Value</html:option>
			 					<logic:present name="<%=OpdConfig.ESSENTIAL_BO_OPTION_DESK_TYPE_LIST%>" >
  			 					<html:options collection="<%=OpdConfig.ESSENTIAL_BO_OPTION_DESK_TYPE_LIST%>" property="value" labelProperty="label" />
  			 					</logic:present>
	   		 				</html:select>
	         			 </div>
      				</td>
<his:statusTransactionInProcess>      				
      				<td width="25%" class="tdfonthead">
	           			<div align="right">	           
	              			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              				<b>
	              					<bean:message key="deskMenu"/>
	              				</b>
	              			</font>
	            		</div>
      				</td>  
      				
      				<td width="25%" class="tdfont">
	         			<div align="left">	           
	             			<html:select name="DeskMenuMacroMstListFB" tabindex="1" property="deskMenuID" styleClass="regcbo" onchange="submitTile('GETMACROHEAD')">
			 					<html:option value="-1">Select Value</html:option>
			 					<logic:present name="<%=OpdConfig.ESSENTIALBO_DESK_MENU_BASED_ON_DESK_TYPE%>" >
  			 					<html:options collection="<%=OpdConfig.ESSENTIALBO_DESK_MENU_BASED_ON_DESK_TYPE%>" property="value" labelProperty="label" />
  			 					</logic:present>
	   		 				</html:select>
	          			</div>
      				</td>
</his:statusTransactionInProcess>      				
      			</tr>
			</table>
		</his:ContentTag>
		
<his:statusList>
	<logic:notEmpty name="<%=OpdConfig.ARR_DESK_MENU_MACRO_MASTER_VO %>">
		<his:SubTitleTag name="Macro Head Of Desk Menu">
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr>
					<td width="10%" class="tdfonthead">
 						<div align="center">
	          				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  					<b>
			  						<bean:message key="select"/>
			  					</b>
			  				</font>
	    				</div>
 					</td>
 					<td width="60%" class="tdfonthead">
		           		<div align="left">	           
		              		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              			<b>
		              				<bean:message key="macroHead"/>
		              			</b>
		              		</font>
		            	</div>
 					</td>
 					<td width="30%" class="tdfonthead">
		           		<div align="left">	           
		              		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              			<b>
		              				<bean:message key="dept(unit)"/>
		              			</b>
		              		</font>
		            	</div>
 					</td>  
				</tr>
				<logic:iterate name="<%=OpdConfig.ARR_DESK_MENU_MACRO_MASTER_VO %>" id="ARR_DESK_MENU_MACRO_MASTER_VO" indexId="index" type="hisglobal.vo.DeskMenuMacroMstVO">
					<tr>
						<td width="10%" class="tdfont">
							<div align="center">
								 <html:checkbox name="DeskMenuMacroMstListFB" property="chk" tabindex="1" value="<%=ARR_DESK_MENU_MACRO_MASTER_VO.getMacroID()%>"/>
							</div>
						</td>
						<td width="60%" class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="ARR_DESK_MENU_MACRO_MASTER_VO" property="macroHead"/>
								</font>
							</div>
						</td>
						<td width="30%" class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="ARR_DESK_MENU_MACRO_MASTER_VO" property="deptUnitName"/>
								</font>
							</div>
						</td>
					</tr>
							
				</logic:iterate>
				
			</table>
		</his:ContentTag>
	</logic:notEmpty>		
	<%varStatus="List";%>	
</his:statusList>
	<html:hidden name="DeskMenuMacroMstListFB" property="hmode"/>
	<html:hidden name="DeskMenuMacroMstListFB" property="chk"/>
	
	<his:ButtonToolBarTag>
	<%if(varStatus.equals("List")){%>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitTile('ADD');" tabindex="1" onclick="submitTile('ADD')" >
		<logic:notEmpty name="<%=OpdConfig.ARR_DESK_MENU_MACRO_MASTER_VO %>">
			<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validatModify() && submitTile('MOD');" tabindex="1" onclick="validatModify() && submitTile('MOD');" >   
           <img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validatDelete() && submitTile('DELETE');" tabindex="1" onclick="validatDelete() && submitTile('DELETE')" >
		</logic:notEmpty>
			
		 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	<%} else{ %>
	
		<%} %>
		
	</his:ButtonToolBarTag>
	<center><b><his:status/></b></center>
	</his:TransactionContainer>
</html:form>
</body>

</html>
