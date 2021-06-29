package usermgmt.reports;

import java.util.ArrayList;
import java.util.List;

import usermgmt.FuncLib;
import HisGlobal.HisResultSet;


public class UmgmtUserProfileBean extends FuncLib
{
	
	int countUser=0;
	int countLoggedIn=0;
	public String getUserInfo(String userID,String hCode)
	{
	
		String html="";
		List userInfo=null;
		if(userID!=null)
		{
			try
			{
				userInfo=super.getDetail("select RPAD(NVL(GSTR_USR_NAME,'Not Available'),30)||'       ['||trim(RPAD(decode(PSRSTR_EMP_NO,'','Non-Employee','Employee'),20))||']' ,GNUM_USER_SEATID, PSRSTR_EMP_NO from GBLT_USER_MST where GNUM_HOSPITAL_CODE='"+hCode+"' and GNUM_USERID='"+userID+"'");
				if(userInfo!=null&&userInfo.size()>=1)
				{
					html=(String)userInfo.get(0);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(html==null||html.equals(""))
			html="Not Available";
		return html;
	}

	public List getLoginUserList(String search,String hCode)
	{
		List loginUserList=null;
		
	
		
		
		/*Query changed by Ankur On 14-02-2009 ,bcoz.
		 * if user1,user2,user3 are given same seat1
		 * then if all the users login at the same time then only the last login time 
		 * user is shown
		 * 
		 * String query=	" Select distinct initcap(c.GSTR_USER_NAME),c.GNUM_USERID,c.GNUM_USER_SEATID "+
					 	" from GBLT_USER_LOG b,GBLT_USER_MST c "+
					 	" where lower(c.GSTR_USER_NAME) like lower('"+search+"%')"+
					 	" and b.GNUM_SEAT_ID=c.GNUM_USER_SEATID "+
					 	" and b.gnum_userid = c.gnum_userid "+
					 	" AND b.gdt_login_date = (	SELECT MAX(gdt_login_date)  FROM GBLT_USER_LOG WHERE GNUM_SEAT_ID = 	c.gnum_user_seatid	)"+
					 	" AND TO_CHAR(b.gdt_login_date ,'dd-mm-yyyy')=(SELECT TO_CHAR(SYSDATE,'dd-mm-yyyy') FROM dual)"+
					 	" and to_date(b.GDT_LOGIN_DATE,'dd-mm-yyyy')=to_date(sysdate,'dd-mm-yyyy') "+
					 	" and c.GNUM_HOSPITAL_CODE='"+hCode+"'"+
					 	" and b.GNUM_HOSPITAL_CODE=c.gnum_hospital_code "+
					 	" and b.GDT_LOGUTT_DATE is null "+
					 	" and c.GNUM_ISVALID!=0 order by initcap(c.GSTR_USER_NAME)";
		*/
		
		
		String query=	" Select distinct initcap(c.GSTR_USER_NAME),c.GNUM_USERID,c.GNUM_USER_SEATID,b.GSTR_IP_NUMBER "+
	 	" from GBLT_USER_LOG b,GBLT_USER_MST c "+
	 	" where lower(c.GSTR_USER_NAME) like lower('"+search+"%')"+
	 	" and b.GNUM_SEAT_ID=c.GNUM_USER_SEATID "+
	 	" and b.gnum_userid = c.gnum_userid "+
		" AND b.gdt_login_date = (	SELECT MAX(gdt_login_date)  FROM GBLT_USER_LOG WHERE GNUM_USERID = c.gnum_userid and GSTR_IP_NUMBER=b.GSTR_IP_NUMBER	)"+
	 	" AND TO_CHAR(b.gdt_login_date ,'dd-mm-yyyy')=(SELECT TO_CHAR(SYSDATE,'dd-mm-yyyy') FROM dual)"+
	 	" and TO_CHAR(b.GDT_LOGIN_DATE,'dd-mm-yyyy')=TO_CHAR(sysdate,'dd-mm-yyyy') "+
	 	" and c.GNUM_HOSPITAL_CODE='"+hCode+"'"+
	 	" and b.GNUM_HOSPITAL_CODE=c.gnum_hospital_code "+
	 	" and b.GDT_LOGUTT_DATE is null "+
	 	" and c.GNUM_ISVALID!=0 order by initcap(c.GSTR_USER_NAME)";
		
		
		System.out.println("query inside getLoginUserList ="+query);
		try
		{
			loginUserList=new ArrayList();
			HisResultSet rs=super.getRecord(query);
			while(rs.next())
			{
				UserProfileBean bean=new UserProfileBean(true);
				bean.setUserName(rs.getString(1));
				bean.setUserID(rs.getString(2));
				bean.setUserSeatID(rs.getString(3));
				bean.setIpAddress(rs.getString(4));
				loginUserList.add(bean);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		countLoggedIn=loginUserList.size();//Getting value of no of user log-in
		return loginUserList;
	}

	public List getLogoffUserList(String search,String hCode)
	{
		String query	=	"	Select distinct initcap(c.GSTR_USER_NAME),c.GNUM_USERID,c.GNUM_USER_SEATID from GBLT_USER_MST c where lower(c.GSTR_USER_NAME) like lower('"+search+"%')and c.gnum_hospital_code='"+hCode+"' and c.GNUM_ISVALID!=0  ORDER BY INITCAP (c.gstr_user_name) ";
		System.out.println("query inside getLogoffUserList ="+query);
		List loggedUsers=this.getLoginUserList(search,hCode);
		System.out.println("loggedUsers ="+loggedUsers);
		List logoffUserList	=	new ArrayList();
		
		try
		{
			
			HisResultSet rs	=	super.getRecord(query);
			while(rs.next())
			{
				boolean exists=false;
				String userID=rs.getString(2);
				String userSeatID=rs.getString(3);
				System.out.println("The value of UserId and UserSeatID:-"+userID+" "+userSeatID);
				for(int i=0;i<loggedUsers.size();i++)
				{
					if(userID.equals(((UserProfileBean) loggedUsers.get(i)).getUserID()))
					{
						exists=true;
						break;
					}
				}
				if(!exists)
				{
					UserProfileBean bean=new UserProfileBean(false);
					bean.setUserName(rs.getString(1));
					bean.setUserID(rs.getString(2));
					bean.setUserSeatID(rs.getString(3));
					logoffUserList.add(bean);
				}

			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		loggedUsers.addAll(loggedUsers.size(),logoffUserList);
		return loggedUsers;
	}

	public String getUserList(String search,String hCode)
	{
		StringBuffer buffer	=		new StringBuffer(500);
		String userString 	=		"";
		List userList		=		this.getLogoffUserList(search,hCode);
		System.out.println("userList="+userList);
		countUser			=	userList.size();
		
		for(int i=0;i<userList.size();i++)
		{
			UserProfileBean bean=(UserProfileBean)userList.get(i);
			if(bean.isLoggedIn())
			{
				buffer.append("<tr bgcolor='#EDE6D9'><td >&nbsp;&nbsp;&nbsp;<img src='../../images/green.JPG' align='middle'><a target='frm2' href='umgmtUserProfile_ProfileDetailMst.jsp?uid="+bean.getUserID()+"&userSid="+bean.getUserSeatID()+"&userName="+bean.getUserName()+"&ipAddress="+bean.getIpAddress()+"' onMouseOver='onMouseScroll(this)'  onMouseOut='onMouseScroll(this)' onMouseFocus='onMouseScroll(this)'><font face='verdana' size='2' color='green'>"+bean.getUserName()+"("+bean.getIpAddress()+")"+"</font></a></td></tr>");
			}
			else
				buffer.append("<tr bgcolor='#EDE6D9'><td >&nbsp;&nbsp;&nbsp;<img src='../../images/red.JPG'   align='middle'><a target='frm2' href='umgmtUserProfile_ProfileDetailMst.jsp?uid="+bean.getUserID()+"&userSid="+bean.getUserSeatID()+"&userName="+bean.getUserName()+"&ipAddress="+bean.getIpAddress()+"' onMouseOver='onMouseScroll(this)' onMouseOut='onMouseScroll(this)' onMouseFocus='onMouseScroll(this)'><font face='verdana' size='2' color='red'>"+bean.getUserName()+"</font></a></td></tr>");

		}
		userString	=	buffer.toString();
		buffer.setLength(0);
		buffer	=	null;
		return userString;
	}
	
	/////////////////////////////////////////////////////
	public String getUserRoles(String userSid,String userName,String uid,String hCode,String ipAddress) throws Exception
	{
		StringBuffer bf=new StringBuffer(50);
		if(userSid!=null)
		{
						
			String query=	"select GNUM_ROLE_ID,GSTR_ROLE_NAME from "+
			" GBLT_ROLE_MST where GNUM_ROLE_ID in (select distinct GNUM_ROLE_ID "+
			"   FROM GBLT_SEAT_ROLE_MST where "+
			" GNUM_SEATID = '"+userSid+"' and GNUM_HOSPITAL_CODE='"+hCode+"' and GBL_ISVALID=1) and GNUM_HOSPITAL_CODE='"+hCode+"' and GBL_ISVALID=1 ";
			System.out.println("User role QUERY IS ---->: "+query);
		//	System.out.println("userName///uid:-"+userName+" "+uid);
			
			String role="";
			String module="";
			
			boolean exists=false;
			try
			{
				HisResultSet rs=super.getRecord(query);
			
				while(rs.next())
				{
					role+="<a href='umgmtUserProfile_ProfileDetailMst.jsp?roleId="+rs.getString(1)+"&userSid="+userSid+"+&userName="+userName+"+&uid="+uid+"&ipAddress="+ipAddress+"'>"+rs.getString(2)+"</a>,";
					
					///module+=rs.getString(3)+",";
					System.out.println("The value of roleid,rolename,username,subString:-"+rs.getString(1)+" "+rs.getString(2)+" "+userName+" "+role.substring(0,role.length()-1));
					exists=true;
				}
				if(exists)
				{
					bf.append("<table width='100%' cellspacing='1' cellpadding='0'><tr><td width='30%' bgcolor ='#EDE6D9'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Role Alloted</font></b></td><td width='30%' bgcolor ='#EDE6D9'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'></font></b></td></tr>");
					bf.append("<tr><td width='30%' bgcolor ='#EDE6D9'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'></font></b></td><td width='70%' bgcolor='#EDE6D9'><font size='2' face='verdana'>"+role.substring(0,role.length()-1)+"</font></td></tr></table>");
					//bf.append("<tr><td  width='30%' bgcolor ='#F5F3F3'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Module</font></b></td><td width='30%' bgcolor ='#F5F3F3'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'></font></b></td></tr><tr><td width='30%' bgcolor ='#F5F3F3'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'></font></b></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>"+module.substring(0,module.length()-1)+"</font></td></tr></table>");
				}
				else
				{
					bf.append("<table width='100%'><tr><td width='30%' bgcolor ='#EDE6D9'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Role Alloted</font></b></td>");
					bf.append("<td width='70%' bgcolor='#EDE6D9'><font size='2' face='verdana' color='red'>Not Available</font></td></tr></table>");
					//bf.append("<tr><td  width='30%' bgcolor ='#F5F3F3'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Module</font></b></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>No Module Access</font></td></tr></table>");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return bf.toString();
	}
	///////////////////////////////////////////////////
	
	///public String getUserRoles(String userSid) throws Exception
	///{
	///	String RID="";
	///String RoleName="";
	///
	///StringBuffer bf=new StringBuffer(50);
	///if(userSid!=null)
	///{
	///				
	///	String query=	" SELECT b.GNUM_ROLE_ID,"+
	///					" ( "+ 
	///					" 		select initcap(GSTR_ROLE_NAME) "+
	///					" 		from GBLT_ROLE_MST "+
	//////					" 		where GNUM_ROLE_ID = b.GNUM_ROLE_ID "+
	///				" 		and gnum_module_id = b.gnum_module_id "+
	///					" ),"+
	///					" ("+
	//////					" 		select distinct initcap(GSTR_MODULE_NAME)"+
	///				"   	from GBLT_METATABLE_TYPE_MST "+
	///					" 		where GNUM_MODULE_ID=b.GNUM_MODULE_ID "+
	///					" ) "+
	///					" FROM gblt_seat_mst a , gblt_seat_role_mst b  "+
	///					" WHERE a.GNUM_ISVALID!=0 and b.GBL_ISVALID!=0 "+
	///					" and a.GNUM_SEATID = b.GNUM_SEATID "+
	///					" and a.GNUM_HOSPITAL_CODE='108' and b.GNUM_SEATID='"+userSid+"'";
	///	
	///		System.out.println("User role QUERY IS : "+query);
	///		
	///		String role="";
	///			String module="";
	///		boolean exists=false;
	///		try
	///		{
	///			HisResultSet rs=super.getRecord(query);
	///		
	///			while(rs.next())
	///			{
	///				role+="<a href='umgmtUserProfile_ProfileDetailMst.jsp?roleId="+rs.getString(1)+"&userSid="+userSid+"'>"+rs.getString(2)+"</a>,";
	///				module+=rs.getString(3)+",";
	///				System.out.println("The value of roleid,USerseatId,moduleId,subString:-"+rs.getString(1)+rs.getString(2)+rs.getString(3)+role.substring(0,role.length()-1));
	///				exists=true;
	///			}
	///			if(exists)
	///			{
	///				bf.append("<table width='100%' cellspacing='1' cellpadding='0'><tr><td width='30%' bgcolor ='#F5F3F3'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Role Alloted</font></b></td><td width='30%' bgcolor ='#F5F3F3'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'></font></b></td></tr>");
	///				bf.append("<tr><td width='30%' bgcolor ='#F5F3F3'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'></font></b></td><td width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>"+role.substring(0,role.length()-1)+"</font></td></tr></table>");
	///				//bf.append("<tr><td  width='30%' bgcolor ='#F5F3F3'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Module</font></b></td><td width='30%' bgcolor ='#F5F3F3'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'></font></b></td></tr><tr><td width='30%' bgcolor ='#F5F3F3'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'></font></b></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>"+module.substring(0,module.length()-1)+"</font></td></tr></table>");
	///			}
	///			else
	///			{
	///				bf.append("<table width='100%'><tr><td width='30%' bgcolor ='#F5F3F3'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Role Alloted</font></b></td>");
	///				bf.append("<td width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana' color='red'>Not Available</font></td></tr></table>");
	///				//bf.append("<tr><td  width='30%' bgcolor ='#F5F3F3'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Module</font></b></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>No Module Access</font></td></tr></table>");
	///			}
	///	}
	///		catch(Exception e)
	///		{
	///			e.printStackTrace();
	///		}
	///	}
	///	return bf.toString();
	///}

	public String getMenuDetail(String uid)
	{
		StringBuffer buffer=new StringBuffer(100);
		boolean exists=false;
		if(uid!=null)
		{
			try
			{
				String query="select initcap(GSTR_MENU_NAME) from GBLT_ROLE_SEAT_MENU_DTL b,GBLT_MENU_MST c "+
				" where b.GNUM_ISVALID=1 and c.GNUM_ISVALID=1 and b.GNUM_MENU_ID=c.GNUM_MENU_ID and c.GNUM_PARENT_ID is not null and b.GNUM_SEATID ='"+uid+"'";
				
				
				buffer.append("<table width='100%' cellspacing='1' cellpadding='0' border='0'><tr><td  width='30%' class='adddatalabelNewRight' >&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Menu Detail</font></b></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>&nbsp;</font></td></tr>");
				HisResultSet rs=super.getRecord(query);
				while(rs.next())
				{
					exists=true;
					buffer.append("<tr><td  width='30%' bgcolor='#F5F3F3'></td><td  width='70%' class='adddatalabelNewRight'><font size='2' face='verdana'>"+rs.getString(1)+"</font></td></tr>");
				}
				buffer.append("</table>");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(!exists)
			return "";
		return buffer.toString();
	}
	
	public String getMenuDetail(String userSid,String roleId,String hCode)
	{
		StringBuffer buffer=new StringBuffer(100);
		
		StringBuffer servicesBuffer=new StringBuffer(100);
		StringBuffer reportsBuffer=new StringBuffer(100);
		StringBuffer setupBuffer=new StringBuffer(100);
		StringBuffer helpBuffer=new StringBuffer(100);
		boolean exists=false;
		
		
		if(userSid!=null && roleId!=null && !roleId.equals(""))
		{
			try
			{
				String moduleQuery = 	" SELECT DISTINCT INITCAP(GSTR_MODULE_NAME)"+
			   							" FROM GBLT_METATABLE_TYPE_MST  a,GBLT_ROLE_MST b"+
										" WHERE a.GNUM_MODULE_ID=b.GNUM_MODULE_ID"+
										" AND b.GNUM_ROLE_ID = '"+roleId+"'	"+
									//	"AND a.GNUM_HOSPITAL_CODE=b.GNUM_HOSPITAL_CODE "+
										"and a.GBL_ISVALID=b.GBL_ISVALID "+
										"AND b.GNUM_HOSPITAL_CODE='"+hCode+"' and b.GBL_ISVALID!='0'";
										

				
				System.out.println("MODULE QUERY IS :---> "+moduleQuery);
				
				HisResultSet rs2 = super.getRecord(moduleQuery); 
				rs2.next();
				String moduleName = rs2.getString(1);
				System.out.println("The name of module:-"+moduleName);
				
				buffer.append("<table width='100%' cellspacing='1' cellpadding='0' border='0'>");
				buffer.append("<tr>");
				buffer.append("<td  width='30%' class ='adddatalabelNewRight'>&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Module</font></b></td>");
				buffer.append("<td width='70%'  class ='adddatalabelNewRight'><b><font size='2' face='verdana'>"+moduleName+"</font></b></td>");
				buffer.append("</tr>");


				
				
				String menuQuery=	" select b.GNUM_MENU_ID,initcap(GSTR_MENU_NAME) "+
								" from GBLT_ROLE_MENU_MST b,GBLT_MENU_MST c "+
								" where b.GNUM_ISVALID!=0 "+
								" and c.GNUM_ISVALID!=0 "+
								" and b.GNUM_MENU_ID=c.GNUM_MENU_ID "+   //" and c.GNUM_PARENT_ID is not null "+
								//" AND c.GNUM_HOSPITAL_CODE='"+hCode+"' " +
								" AND b.GNUM_HOSPITAL_CODE='"+hCode+"'"+
								" and b.GNUM_ROLE_ID = '"+roleId+"'";
				
                System.out.println("MENU QUERY IS :---> "+menuQuery);
				
				
				
				
		
				
				//buffer.append("<tr><td  width='30%' bgcolor ='#F5F3F3' >&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Menu Detail</font></b></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>&nbsp;</font></td></tr>");
				
				servicesBuffer.append("<tr>");
				servicesBuffer.append("<td  width='30%'  class ='adddatalabelNewRight' >&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Services</font></b></td>");
				servicesBuffer.append("<td  width='70%'  class ='adddatalabelNewRight'><font size='2' face='verdana'>&nbsp;</font></td>");
				servicesBuffer.append("</tr>");
				
				reportsBuffer.append("<tr>");
				reportsBuffer.append("<td  width='30%' class ='adddatalabelNewRight' >&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Reports</font></b></td>");
				reportsBuffer.append("<td  width='70%'  class ='adddatalabelNewRight'><font size='2' face='verdana'>&nbsp;</font></td>");
				reportsBuffer.append("</tr>");
				

				setupBuffer.append("<tr>");
				setupBuffer.append("<td  width='30%'  class ='adddatalabelNewRight' >&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Setup</font></b></td>");
				setupBuffer.append("<td  width='70%'  class ='adddatalabelNewRight'><font size='2' face='verdana'>&nbsp;</font></td>");
				setupBuffer.append("</tr>");
				
				helpBuffer.append("<tr>");
				helpBuffer.append("<td  width='30%'  class ='adddatalabelNewRight' >&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Help</font></b></td>");
				helpBuffer.append("<td  width='70%'  class ='adddatalabelNewRight'><font size='2' face='verdana'>&nbsp;</font></td>");
				helpBuffer.append("</tr>");
				
				
				HisResultSet rs=super.getRecord(menuQuery);
				
				boolean serviceMenuFound = false;
				boolean setUpMenuFound = false;
				boolean reportsMenuFound = false;
				boolean helpMenuFound = false;
				
				int serviceCounter = 1;
				int reportCounter = 1;
				int setupCounter = 1;
				int helpCounter = 1;
				
				while(rs.next())
				{
					
					 
					String menuId = rs.getString(1);
					 System.out.println("menuId.substring(0,1)---> "+menuId.substring(0,1));
					if(menuId.substring(0,1).equals("1"))
					{
						servicesBuffer.append("<tr><td  width='30%' class='adddatavalueNew'></td><td  width='70%' class='adddatavalueNew'><font size='2' face='verdana'>"+serviceCounter+".&nbsp;&nbsp;"+rs.getString(2)+"</font></td></tr>");
						serviceMenuFound = true;
						serviceCounter++;
					}
					else if(menuId.substring(0,1).equals("3"))
					{
						reportsBuffer.append("<tr><td  width='30%' class='adddatavalueNew'></td><td  width='70%' class='adddatavalueNew'><font size='2' face='verdana'>"+reportCounter+".&nbsp;&nbsp;"+rs.getString(2)+"</font></td></tr>");
						reportsMenuFound = true;
						reportCounter++;
					}
					else if(menuId.substring(0,1).equals("2"))
					{
						setupBuffer.append("<tr><td  width='30%' class='adddatavalueNew'></td><td  width='70%' class='adddatavalueNew'><font size='2' face='verdana'>"+setupCounter+".&nbsp;&nbsp;"+rs.getString(2)+"</font></td></tr>");
						setUpMenuFound = true;
						setupCounter++;
					}
					else if(menuId.substring(0,1).equals("4"))
					{
						helpBuffer.append("<tr><td  width='30%' class='adddatavalueNew'></td><td  width='70%' class='adddatavalueNew'><font size='2' face='verdana'>"+helpCounter+".&nbsp;&nbsp;"+rs.getString(2)+"</font></td></tr>");
						helpMenuFound = true;
						helpCounter++;
					}
					exists=true;
					//buffer.append("<tr><td  width='30%' bgcolor='#F5F3F3'></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>"+rs.getString(2)+"</font></td></tr>");
				}
				if(serviceMenuFound || reportsMenuFound || setUpMenuFound || helpMenuFound)
					buffer.append("<tr><td  width='30%' class='adddatalabelNewRight' >&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Menu Detail</font></b></td><td  width='70%' class='adddatalabelNewRight'><font size='2' face='verdana'>&nbsp;</font></td></tr>");
				else
					buffer.append("<tr><td  width='30%' class='adddatalabelNewRight' >&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Menu Detail</font></b></td><td  width='70%' class='adddatalabelNewRight'><font size='2' face='verdana' color='red'>Not Available</font></td></tr>");
				
				
				
				if(setUpMenuFound)
					buffer.append(setupBuffer);		
								
				if(serviceMenuFound)
				buffer.append(servicesBuffer);
				
				if(reportsMenuFound)
				buffer.append(reportsBuffer);
											
				if(helpMenuFound)
				buffer.append(helpBuffer);
				
				buffer.append("</table>");
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//if(!exists)
		//	return "";
		return buffer.toString();
	}
	
	
	public String getSeatName(String userSid,String hCode)
	{
		String html="";
		if(userSid!=null)
		{
			try
			{
				List list=super.getDetail("SELECT initcap(GSTR_SEAT_NAME) from GBLT_SEAT_MST where GNUM_ISVALID!=0 and GNUM_HOSPITAL_CODE='"+hCode+"' and GNUM_SEATID="+userSid);
				if(list!=null&&list.size()!=0)
					html=(String)list.get(0);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(html==null||html.equals(""))
			html="Not Available";
		return html;
	
	}
	
	
	
	
	public int getCountUsers()
	{
		return countUser;
	}
	public int getCountLoggedIn()
	{
		return countLoggedIn;
	}

	public String getLastLoginTime(String userID,String hCode,String ipAddress)
	{

		String html="";
		if(userID!=null)
		{
			try
			{
				String query=" SELECT TO_CHAR (b.gdt_login_date, 'dd-Mon-yyyy HH:MI:SS AM') "+
								" FROM gblt_user_log b WHERE b.gdt_login_date= "+
								" (SELECT MAX (gdt_login_date) "+
								" FROM gblt_user_log WHERE gnum_hospital_code = b.gnum_hospital_code  "+
								" AND gnum_userid = b.gnum_userid AND gstr_ip_number = b.gstr_ip_number ) " +
								" and b.GNUM_HOSPITAL_CODE='"+hCode+"' "+
								" AND gnum_userid = '"+userID+"'   AND gstr_ip_number = '"+ipAddress+"' ";
				
				System.out.println("Last Login time query------"+query);
				html=super.getField(query);
				System.out.println("Last Login time value------"+html);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(html==null||html.equals(""))
		{
			html="Not Available";
		}
		return html;
	}
}
