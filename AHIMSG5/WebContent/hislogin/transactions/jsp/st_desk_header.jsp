<%@page import="hissso.config.HISSSOConfig"%>
<%@page import="vo.usermgmt.MenuMasterVO"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="hislogin.config.HISLoginConfig"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="header">
	<div class="headerImage">
		<img src="/HIS/hisglobal/images/headerImage.png">
	</div>
	<%
		String varMenuAssigned="";
		if(request.getSession().getAttribute(HISSSOConfig.SHOW_MENU)!=null && request.getSession().getAttribute(HISSSOConfig.SHOW_MENU).equals("0"))
			varMenuAssigned="none";
		String varAlertRoleAssigned="none";
		if(request.getSession().getAttribute(HISSSOConfig.IS_ALERT_ROLE_ASSIGNED)!=null && request.getSession().getAttribute(HISSSOConfig.IS_ALERT_ROLE_ASSIGNED).equals("1"))
			varAlertRoleAssigned="";
					
	%>
	 <div class="logoutTag">
		<table>
		<tr style="height: 25px;">
		<td width="23%" style="display: <%=varMenuAssigned %>; ">
		<a><img width="20px" height="20px" src="/HIS/hisglobal/images/menuSearch.png" alt="Search Menu"
			 title="Search Menu" onclick="searchMenu()" >
		</a>	 
		</td>
		<!-- Alert Added by Garima -->
		<td width="57%" id="tdAlert" onclick="callMenu('/HISSupport/alertmanagement/alertDesk.cnt','Alert Desk')" style="display: <%=varAlertRoleAssigned %>; ">
		<img src="/HIS/hisglobal/images/alert_button.png" alt="Alert Desk" id="alertBellId"
			width="20px" height="20px" title="Alert Desk">
			<span class="alertCount" id="alertCountId" title="Alert Desk"><font color="#FFFF00" size="2"><b>(<%=request.getSession().getAttribute("alertCount")%>)</b></font></span>
		</td>
		<!-- End Alert Tab-->
		<td width="20%">
		<img src="/HIS/hisglobal/images/logoutIcon.png" alt="logout"
			width="20px" height="20px" title="Logout" onclick="callLogOut(event)">	
		</td>
		</tr>
		</table>
	</div>

	<div class="headerMenu">
		<ul id="menu" style="border: 0; box-shadow: none; background: none;">

			
				
			<li id="lastSeenMenusIcon"><a style="display:none;"> <img src="/HIS/hisglobal/images/lastSeenIcon.png" width="23px"
				height="20px" alt="Last Seen" title="Last Seen"></a>
				<div class="dropdown_1column align_right">
					<div class="col_1">
						<h3>Last Seen</h3>
						<ul id="lastSeenMenusID">
						</ul>
					</div>
				</div></li>
				
			<li><a style="display:none;" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_favourites_page.jsp','Favourites')"><img src="/HIS/hisglobal/images/starIcon.png" width="20px"
				height="20px" alt="favourites" title="Favourites"></a>
				<div class="dropdown_1column align_right">
					<div class="col_1">
						<h3>Favourites</h3>
						<ul>
							<%List<MenuMasterVO> lstFavMenus = (List<MenuMasterVO>) session.getAttribute(HISLoginConfig.LOGGEDIN_USER_FAVAOURITE_LIST); 
								Iterator favIterator= lstFavMenus.iterator();
								while(favIterator.hasNext()){
									MenuMasterVO voMenu= (MenuMasterVO)favIterator.next();
								%>
							<li><a onclick="callMenu('<%=voMenu.getVarURL()%>','<%=voMenu.getVarMenuName()%>')"
								style='cursor: pointer;'><%=voMenu.getVarMenuName()%></a></li>
							<%} %>
						</ul>
					</div>
				</div></li>
			<li><img src="/HIS/hisglobal/images/settingsIcon.png" width="20px"
				height="20px" alt="settings" title="Settings">
				<div class="dropdown_1column align_right">
					<div class="col_1">
						<h3>Settings</h3>
						<ul>
							<!-- <li><a onclick="callMenu('/AHIMSG5/hislogin/initChangePasswordLgnFtr.action','Change Password')">Change Password</a></li>
							<li><a onclick="callMenu('/AHIMSG5/hislogin/initChangeUserDetailsLgnFtr.action','Change User Details')">Change User Details</a></li>
							<li><a onclick="callMenu('/AHIMSG5/hislogin/initUserLogDetailsLgnFtr.action','User Log Report')">User Log Report</a></li>
							Changed by Garima for extension change -->
							<li><a onclick="callMenu('/AHIMSG5/hislogin/initChangePasswordLgnFtr','Change Password')">Change Password</a></li>
							<li><a onclick="callMenu('/AHIMSG5/hislogin/initChangeUserDetailsLgnFtr','Change User Details')">Change User Details</a></li>
							<li><a onclick="callMenu('/AHIMSG5/hislogin/initUserLogDetailsLgnFtr','User Log Report')">User Log Report</a></li>
							<li><a onclick="showHomeTab()">Home</a></li>
							
						</ul>
					</div>
				</div></li>		

		</ul>
	</div>
	<!-- Cash In Hand Dtl Added by Singaravelan on 02-Jun-2015-->
	<%
		String display="none",cashInHand="0.00";
		if(request.getSession().getAttribute(HISSSOConfig.SHOW_USERWISE_CASH_COLLECTED_DTL)!=null && request.getSession().getAttribute(HISSSOConfig.SHOW_USERWISE_CASH_COLLECTED_DTL).equals("1"))
			display="";
		if(request.getSession().getAttribute(HISSSOConfig.USERWISE_TOTAL_CASH_COLLECTED)!=null)
			cashInHand=(String)request.getSession().getAttribute(HISSSOConfig.USERWISE_TOTAL_CASH_COLLECTED);
				
	%>
	<div class="welcomeTag">
		<span> Welcome, <s:property value="varUsrName" /></span>
		<div id="cashCollectedDiv" style="cursor:pointer; display:<%=display %>;"><font size='4 px' color='#F2BC34'>Cash in Hand : <img alt="Rs." src="/HIS/hisglobal/images/INR.png"/><%=cashInHand %></font></div>			
	</div>	
	<div class="dateTag" id="dateTdId"></div>
		
		<input type="hidden" name ="varMenuAssigned"  value="<%=varMenuAssigned %>">
	</div>
