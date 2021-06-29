<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
}

function validate(mode)
{
	if(document.getElementsByName("rosterCatId")[0].value=="-1")
	{
		mode="NEW";
	}
	
	submitPage(mode);
}


</script>

<%@page import="dutyroster.DutyRosterConfig"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="hisglobal.vo.RosterDtlVO"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<body>
<his:TransactionContainer>
	<his:TitleTag name="Nurse Role Detail">
	</his:TitleTag>
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td width="50%" class="tdfonthead">
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b><bean:message key="dutyType"/></b>	
		  				</font>
		  			</div>
	      		</td>
	      		<td width="50%" class="tdfont" nowrap="nowrap"  >
					<div align="left" >
						<html:select name="NurseRoleDetailFB" property="rosterCatId" tabindex="1" onchange="validate('GETDETAIL')" > 
							<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=DutyRosterConfig.ESSENTIAL_CATEGORY_LIST_FOR_NURSE%>">
									<html:options collection="<%=DutyRosterConfig.ESSENTIAL_CATEGORY_LIST_FOR_NURSE%>" property="value" labelProperty="label" />
								</logic:present>
						</html:select>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	<logic:present name="<%=DutyRosterConfig.ROSTER_DETAIL_MAP%>">
	<bean:define id="map" name="<%=DutyRosterConfig.ROSTER_DETAIL_MAP%>" type="java.util.Map"> </bean:define>
	<%
		List allDutyRoleList=(List)session.getAttribute(DutyRosterConfig.ESSENTIAL_ALL_DUTY_ROLE_LIST);
		
		if(map.size()>0)
		{
			
	%>
		<his:SubTitleTag name="Employee List">
		</his:SubTitleTag>
		<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B><bean:message key="employeename"/></B>
							</font>
						</div>
					</td>
					<td width="70%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B><bean:message key="dutyRole"/></B>
							</font>
						</div>
					</td>
				</tr>
		</table>			
		
	<%		
			Set keys = map.keySet();
			Iterator itr=keys.iterator();
			while(itr.hasNext())
			{
				String key=(String)itr.next();
				List rosterDetailVOLst=(List)map.get(key);
				
				String shiftDesc=key.split("#")[1];
				String shiftId=key.split("#")[0];
				String startTime="";
				String endTime="";
				
				for(int i=0;i<rosterDetailVOLst.size();i++)
				{
					RosterDtlVO vo=(RosterDtlVO)rosterDetailVOLst.get(i);
					if(vo.getShiftId().equals(shiftId))
					{
						startTime=vo.getStartDateTime();
						endTime=vo.getEndDateTime();
					}
					
				}
		%>
		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr bgcolor="#c56b39 "> 
					<td width="20%" colspan="3" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><%=shiftDesc+" ("+startTime+" - "+endTime+")"%></b>	
							</font>
						</div>
					</td>
				</tr>
		</table>		
		<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
		<%
			for(int i=0;i<rosterDetailVOLst.size();i++)
			{
				RosterDtlVO vo=(RosterDtlVO)rosterDetailVOLst.get(i);
				
				String dutyRoleId=vo.getRoleId();
				String[] dutyRoleIdArray=dutyRoleId.split("#");
				List dutyRoleNameList=new ArrayList();
				
				String roleNames="";
				
				for(int j=0;j<dutyRoleIdArray.length;j++)
				{
					for(int k=0;k<allDutyRoleList.size();k++)
					{
						Entry obj=(Entry)allDutyRoleList.get(k);
						if(dutyRoleIdArray[j].equals(obj.getValue()))
						{
							roleNames=roleNames +" , "+obj.getLabel();
							//dutyRoleNameList.add(obj.getLabel());
						}
					}
					//int index=roleNames.indexOf(',');
					
				}
				roleNames=roleNames.substring(2);
				//String aa=roleNames;
		%>
			<tr>
				<td width="30%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	
							<%=vo.getEmpName() %>
						</font>		
					</div>	
				</td>
				<td width="70%"  style="overflow: visible" class="tdfonthead" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	
							<%=roleNames %>
						</font>	
					</div>	
				</td>
			</tr>
		<%		
				
			}		
		%>	
			
		</table>
		</his:ContentTag>
		<%		
			}
		}
	%>
			
	</logic:present>	
	
	
			
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW');">
		</his:ButtonToolBarTag>
</his:TransactionContainer>	
<html:hidden name="NurseRoleDetailFB" property="hmode"/>
<html:hidden name="NurseRoleDetailFB" property="patCrNo"/>
<html:hidden name="NurseRoleDetailFB" property="episodeCode" />
<html:hidden name="NurseRoleDetailFB" property="episodeVisitNo" />
</body>
</html>