<!-- 
/**
 * @author CDAC
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function setDedeasedNo(obj)
{
	document.getElementsByName("deceasedNo")[0].value=obj;
}
</script>

<%@page import="mortuary.MortuaryConfig"%>

<his:statusTransactionInProcess>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1" border="0">
			<tr>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<B>
							<bean:message key="select"/>
						</B>
					</div>
				</td>
				<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<B>
							<bean:message key="deceasedNo"/>
						</B>
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<B>
							<bean:message key="name"/>
						</B>
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<B>
							<bean:message key="receiveDate"/>
						</B>
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<B>
							<bean:message key="requestDate"/>
						</B>
					</div>
				</td>
			</tr>
			<logic:iterate id="arrDeceasedListVO" name="<%=DynamicDeskConfig.POSTMORTEM_DESK_DECEASED_LIST %>" type="hisglobal.vo.DeceasedDetailVO" indexId="idx">
				<tr>
					<td width="5%" class="tdfont">
						<div align="center">

						<%String str="setDedeasedNo("+arrDeceasedListVO.getDeceasedNo()+");checkForOnSelectDeskListItem(this);"; %>
							<html:radio name="PostmortemDeskFB" property="postmortemId" value="<%=arrDeceasedListVO.getPostmortemId()%>" tabindex="1" onclick="<%=str %>" onkeypress="checkForOnSelectDeskListItem(this);" ></html:radio>
							<html:hidden name="PostmortemDeskFB" property="deceasedNo" value="<%=arrDeceasedListVO.getDeceasedNo() %>"/>
						</div>
					</td>
					<td width="20%" class="tdfont">
						<div align="center">
							<font color="<%=arrDeceasedListVO.getColor() %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=arrDeceasedListVO.getDeceasedNo() %>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="<%=arrDeceasedListVO.getColor() %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=arrDeceasedListVO.getPatFirstName()==null?"":arrDeceasedListVO.getPatFirstName() %>
								<%=arrDeceasedListVO.getPatMiddleName()==null?"":arrDeceasedListVO.getPatMiddleName() %>
								<%=arrDeceasedListVO.getPatLastName()==null?"":arrDeceasedListVO.getPatLastName() %>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="<%=arrDeceasedListVO.getColor() %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=arrDeceasedListVO.getReceivedDateTime() %>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="<%=arrDeceasedListVO.getColor() %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=arrDeceasedListVO.getRequestDateTime() %>
							</font>	
						</div>
					</td>
				</tr>
			
			</logic:iterate>
		</table>		
	</his:ContentTag>
</his:statusTransactionInProcess>

<html:hidden name="PostmortemEntryListFB" property="hmode"/>
