package HisGlobal;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//third party API

public class HisRoleAuthorization {

	private static List<String> adminURLList = new ArrayList<String>(
			Arrays.asList("/eAHIMS_user/startup/index.jsp",
					"/eAHIMS_user/startup/content.jsp",
			"/eAHIMS_user/startup/menu.jsp",
			"/eAHIMS_user/HisGlobal/header.jsp",
			"/eAHIMS_user/usermgmt/masters/gbl_view_mst.jsp",
			"/eAHIMS_user/usermgmt/masters/gbl_print_mst.jsp",
			"/eAHIMS_user/usermgmt/masters/result.jsp",
			
			"/eAHIMS_user/usermgmt/masters/umgmtAccessForbidden.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtWarningPage.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtGroup_cntMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtGroup_lstMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtGroup_addMst.jsp",
			
			"/eAHIMS_user/usermgmt/masters/umgmtSeatMst_cnt.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtSeatMst_lst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtSeatMst_add.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtSeat_viewMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtUserMstResponce.jsp",
			
			"/eAHIMS_user/usermgmt/masters/umgmtUser_cntMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtUser_lstMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtUser_addMst.jsp",
			"/eAHIMS_user/usermgmt/masters/addPopUp.jsp",
			"/eAHIMS_user/usermgmt/masters/empPopUp.jsp",
			
			"/eAHIMS_user/usermgmt/masters/resetPassword_cnt.jsp",
			"/eAHIMS_user/usermgmt/masters/resetPassword.jsp",
			
			"/eAHIMS_user/usermgmt/masters/ChangeAdminPassword_cnt.jsp",
			"/eAHIMS_user/usermgmt/masters/ChangeAdminPassword.jsp",
			
			"/eAHIMS_user/usermgmt/masters/umgmtRole_cntMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtRole_lstMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtRole_addMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtRole_modMst.jsp",
			
			"/eAHIMS_user/usermgmt/masters/umgmtGroupRole_cntMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtGroupRole_lstMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtGroupRole_addMst.jsp",
			
			"/eAHIMS_user/usermgmt/masters/umgmtRoleMenuCnt_Mst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtRoleMenuAdd_Mst.jsp",
			
			"/eAHIMS_user/usermgmt/masters/umgmt_seat_role_cntMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmt_seat_role_addmst.jsp",
			
			"/eAHIMS_user/usermgmt/masters/umgmtRoleSeatTableMst_cnt.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtRoleSeatTableMst_add.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtRoleSeatTableMst_addResponse.jsp",
			
			
			"/eAHIMS_user/usermgmt/reports/umgmtUserProfile_cntMst.jsp",
			"/eAHIMS_user/usermgmt/reports/umgmtUserProfile_ListPageMst.jsp",
			"/eAHIMS_user/usermgmt/reports/umgmtUserProfile_ProfileDetailMst.jsp",
			"/eAHIMS_user/usermgmt/reports/user_log.cnt",
			"/eAHIMS_user/usermgmt/reports/inv_Userlog_lst.jsp",
			"/eAHIMS_user/usermgmt/reports/umgmtTreeView.jsp",
			"/eAHIMS_user/usermgmt/reports/seatRoleAuditLogCnt.jsp",
			"/eAHIMS_user/usermgmt/reports/seatRoleAuditLogReport.jsp",
			"/eAHIMS_user/usermgmt/reports/roleMenuAuditLogCnt.jsp",
			"/eAHIMS_user/usermgmt/reports/roleMenuAuditLogReport.jsp",
			"/eAHIMS_user/usermgmt/reports/userAuditLogCnt.jsp",
			"/eAHIMS_user/usermgmt/reports/userAuditLogReport.jsp",
		    "/eAHIMS_user/usermgmt/reports/seatPermissionAuditLogCnt.jsp",
			"/eAHIMS_user/usermgmt/reports/seatPermissionAuditLogReport.jsp",
			
			"/eAHIMS_user/usermgmt/masters/userMgtConfigController.cnt",
			"/eAHIMS_user/usermgmt/masters/umgmtSessionMgtConfig_int.jsp",
			
			"/eAHIMS_user/usermgmt/masters/HospitalMaster_cnt.jsp",
			"/eAHIMS_user/usermgmt/masters/HospitalMaster_mst.jsp",			
			
			"/eAHIMS_user/usermgmt/masters/umgmtMenuAllowedURL_cntMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtMenuAllowedURL_lstMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtMenuAllowedURL_addMst.jsp"
			
			));
					
	private static List<String> superURLList = new ArrayList<String>(
			Arrays.asList("/eAHIMS_user/startup/index1.jsp",
					
			"/eAHIMS_user/startup/content.jsp",
			"/eAHIMS_user/startup/hosConfigMenu.jsp",
		    "/eAHIMS_user/HisGlobal/header.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtAccessForbidden.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtWarningPage.jsp",
			"/eAHIMS_user/usermgmt/masters/gbl_view_mst.jsp",
			
			"/eAHIMS_user/usermgmt/masters/hospital_master_list.jsp",
			"/eAHIMS_user/usermgmt/masters/hospital_master_cnt.jsp",
			"/eAHIMS_user/usermgmt/masters/hospital_master_add.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtMenu_cntMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtMenu_lstMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtMenu_addMst.jsp",
			"/eAHIMS_user/usermgmt/masters/result.jsp",
			"/eAHIMS_user/usermgmt/masters/gbl_print_mst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtMetatableCol_lstMst.jsp",
			
			"/eAHIMS_user/usermgmt/masters/ChangeSuperPassword_cnt.jsp",
			"/eAHIMS_user/usermgmt/masters/ChangeSuperPassword.jsp",
			
					
			"/eAHIMS_user/usermgmt/masters/umgmtJobList_cntMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtJob_lstMst.jsp",
			
			"/eAHIMS_user/usermgmt/masters/umgmtMenuAllowedURL_cntMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtMenuAllowedURL_lstMst.jsp",
			"/eAHIMS_user/usermgmt/masters/umgmtMenuAllowedURL_addMst.jsp",
			"/eAHIMS_user/usermgmt/masters/ConfigSetup.jsp",
			"/eAHIMS_user/usermgmt/masters/ConfigSetup_cnt.jsp"
			));


	//public HisRoleAuthorization() {
		/*adminURLList.add("/eAHIMS_user/startup/index.jsp");
		adminURLList.add("/eAHIMS_user/startup/content.jsp");
		adminURLList.add("/eAHIMS_user/startup/menu.jsp");
		adminURLList.add("/eAHIMS_user/HisGlobal/header.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/gbl_view_mst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/gbl_print_mst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/result.jsp");
		
		adminURLList
				.add("/eAHIMS_user/usermgmt/masters/umgmtAccessForbidden.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtWarningPage.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtGroup_cntMst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtGroup_lstMst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtGroup_addMst.jsp");
		
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtSeatMst_cnt.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtSeatMst_lst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtSeatMst_add.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtSeat_viewMst.jsp");
		
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtUser_cntMst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtUser_lstMst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtUser_addMst.jsp");
		
		adminURLList.add("/eAHIMS_user/usermgmt/masters/resetPassword_cnt.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/resetPassword.jsp");
		
		adminURLList.add("/eAHIMS_user/usermgmt/masters/ChangeAdminPassword_cnt.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/ChangeAdminPassword.jsp");
		
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtRole_cntMst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtRole_lstMst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtRole_addMst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtRole_modMst.jsp");
		
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtGroupRole_cntMst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtGroupRole_lstMst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtGroupRole_addMst.jsp");
		
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtRoleMenuCnt_Mst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtRoleMenuAdd_Mst.jsp");
		
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmt_seat_role_cntMst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmt_seat_role_addmst.jsp");
		
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtRoleSeatTableMst_cnt.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtRoleSeatTableMst_add.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtRoleSeatTableMst_addResponse.jsp");
		
		
		adminURLList.add("/eAHIMS_user/usermgmt/reports/umgmtUserProfile_cntMst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/reports/umgmtUserProfile_ListPageMst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/reports/umgmtUserProfile_ProfileDetailMst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/reports/user_log.cnt");
		adminURLList.add("/eAHIMS_user/usermgmt/reports/inv_Userlog_lst.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/reports/umgmtTreeView.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/reports/seatRoleAuditLogCnt.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/reports/seatRoleAuditLogReport.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/reports/roleMenuAuditLogCnt.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/reports/roleMenuAuditLogReport.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/reports/userAuditLogCnt.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/reports/userAuditLogReport.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/reports/seatPermissionAuditLogCnt.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/reports/seatPermissionAuditLogReport.jsp");
		
		adminURLList.add("/eAHIMS_user/usermgmt/masters/userMgtConfigController.cnt");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/umgmtSessionMgtConfig_int.jsp");
		
		adminURLList.add("/eAHIMS_user/usermgmt/masters/HospitalMaster_cnt.jsp");
		adminURLList.add("/eAHIMS_user/usermgmt/masters/HospitalMaster_mst.jsp");*/

		/*superURLList.add("/eAHIMS_user/startup/index1.jsp");
		superURLList.add("/eAHIMS_user/startup/content.jsp");
		superURLList.add("/eAHIMS_user/startup/hosConfigMenu.jsp");
		superURLList.add("/eAHIMS_user/HisGlobal/header.jsp");
		superURLList
				.add("/eAHIMS_user/usermgmt/masters/umgmtAccessForbidden.jsp");
		superURLList.add("/eAHIMS_user/usermgmt/masters/umgmtWarningPage.jsp");
		superURLList.add("/eAHIMS_user/usermgmt/masters/gbl_view_mst.jsp");
		
		superURLList.add("/eAHIMS_user/usermgmt/masters/hospital_master_list.jsp");
		superURLList.add("/eAHIMS_user/usermgmt/masters/hospital_master_cnt.jsp");
		superURLList.add("/eAHIMS_user/usermgmt/masters/hospital_master_add.jsp");
		superURLList.add("/eAHIMS_user/usermgmt/masters/umgmtMenu_cntMst.jsp");
		superURLList.add("/eAHIMS_user/usermgmt/masters/umgmtMenu_lstMst.jsp");
		superURLList.add("/eAHIMS_user/usermgmt/masters/umgmtMenu_addMst.jsp");
		superURLList.add("/eAHIMS_user/usermgmt/masters/result.jsp");
		superURLList.add("/eAHIMS_user/usermgmt/masters/gbl_print_mst.jsp");
		
		
		superURLList.add("/eAHIMS_user/usermgmt/masters/umgmtMetatableCol_lstMst.jsp");*/

		//map.put("ADMIN", adminURLList);
		//map.put("SUPER", superURLList);
		public static Map<String, List<String>> map  = new HashMap<String, List<String>>() {

	        {
	            put("ADMIN", adminURLList);
	            put("SUPER", superURLList);
	        }

	        ;
	    };
	//}

	public boolean isURLInRole(String url, String role) {

		List<String> values = map.get(role);
		if (values.contains(url))
			return true;
		else
			return false;
		/*
		 * for(String s:map.get(role)){ System.out.println(s);
		 *  }
		 */
		// return true;
	}

}// end of class
