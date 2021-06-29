package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.controller.fb.InpatientDeskFB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class PatNursingDeskDataFlagsMenuWiseUTL extends ControllerUTIL 
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static StringBuffer getMenuWisePateintNursingDeskDataFlags(InpatientDeskFB objFB_p, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		PatientDetailVO[] dailyPatientVOs = null;

		try
		{
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
			HttpSession session = objRequest_p.getSession();
			
			List<DeskDetailVO> lstLeftMenus, lstRightMenus, lstBottomMenus;
			lstLeftMenus = (List<DeskDetailVO>) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_LEFT_MENU_DTL);
			lstRightMenus = (List<DeskDetailVO>) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_RIGHT_MENU_DTL);
			lstBottomMenus = (List<DeskDetailVO>) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_BOTTOM_MENU_DTL);
			
			// Setting Desk Essentials
			// Episode, Visit, Admission No
			/*PatientDetailVO[] al = (PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(objFB_p.getPatCrNo()))
				{
					voDP = vo;
					break;					
				}*/

			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(objFB_p.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (objFB_p.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
			objFB_p.setPatCrNo(voDP.getPatCrNo());

			sbAjaxRes.append("[");
			for(DeskDetailVO vo : lstLeftMenus)
			{
				String flg = GetMenuPateintNursingDataFlag(vo.getDeskMenuId(), voDP, voUser, objRequest_p);
				if(flg!=null && !flg.equals("-1"))
				{
					sbAjaxRes.append("{");
					sbAjaxRes.append("deskMenuId");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getDeskMenuId());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("menuURL");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getDeskUrl());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("flag");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(flg);sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");
				}
			}
		
			for(DeskDetailVO vo : lstRightMenus)
			{
				String flg = GetMenuPateintNursingDataFlag(vo.getDeskMenuId(), voDP, voUser, objRequest_p);
				if(flg!=null && !flg.equals("-1"))
				{
					sbAjaxRes.append("{");
					sbAjaxRes.append("deskMenuId");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getDeskMenuId());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("menuURL");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getDeskUrl());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("flag");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(flg);sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");
				}
			}

			for(DeskDetailVO vo : lstBottomMenus)
			{
				String flg = GetMenuPateintNursingDataFlag(vo.getDeskMenuId(), voDP, voUser, objRequest_p);
				if(flg!=null && !flg.equals("-1"))
				{
					sbAjaxRes.append("{");
					sbAjaxRes.append("deskMenuId");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getDeskMenuId());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("menuURL");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getDeskUrl());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("flag");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(flg);sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");
				}
			}

			if(sbAjaxRes.length()>3)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
			sbAjaxRes.append("]");
		}
		catch (Exception e)
		{
			strMsgText = "CommonDietDetailUTIL.getMealTimesList() -> " + e.getMessage();
			//HisException eObj = 
			new HisException("dietkitchen", "CommonDietDetailUTIL.getMealTimesList() --> ", strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
		return sbAjaxRes;
	}
	
	private static String GetMenuPateintNursingDataFlag(String deskMenuId, PatientDetailVO voPat, UserVO voUser, HttpServletRequest objRequest_p)
	{
		String strFlag = "-1";
		try
		{
			Map<String,String> mapDataFlag = (Map<String,String>)objRequest_p.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_NURSING_DATA_FLAG_MAP);
			//InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
			if(mapDataFlag!=null)
			{
				String count = mapDataFlag.get(deskMenuId);
				System.out.println("menu url count::"+ mapDataFlag.get(deskMenuId));
				
				if(count!=null && Integer.parseInt(count)>0 )
					strFlag ="1";
				else
					strFlag ="0";
			}
			
			/*if(menuURL.equals("DRUGADMINIS"))
			{
				String count = delegate.getDrugAdminData(voPat, voUser);
				if(count!=null && Integer.parseInt(count)>0 )
					strFlag ="1";
				else
					strFlag="0";
			}
			if(menuURL.equals("NURSEROUND"))
			{
				String count = delegate.getProgressNotesData(voPat, voUser);
				if(count!=null && Integer.parseInt(count)>0 )
					strFlag ="1";
				else
					strFlag="0";
			}*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
			strFlag = "-1";
		}
		return strFlag;
	}
	public static String setMenuDetailFlag(HttpServletRequest objRequest_p, String deskMenuId)
	{
		String Flag = "-1";
		
		HttpSession session = objRequest_p.getSession();
		
		List<DeskDetailVO> lstLeftMenus, lstRightMenus, lstBottomMenus;
		lstLeftMenus = (List<DeskDetailVO>) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_LEFT_MENU_DTL);
		lstRightMenus = (List<DeskDetailVO>) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_RIGHT_MENU_DTL);
		lstBottomMenus = (List<DeskDetailVO>) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_BOTTOM_MENU_DTL);

		DeskDetailVO voMenu = null;
		System.out.println("desk menu id::::"+deskMenuId);
		for(DeskDetailVO vo : lstLeftMenus)
			if(vo.getDeskMenuId().equals(deskMenuId))
			{
				voMenu = vo;
				break;
			}
		if(voMenu==null)
		{
			for(DeskDetailVO vo : lstRightMenus)
				if(vo.getDeskMenuId().equals(deskMenuId))
				{
					voMenu = vo;
					break;
				}
		}
		if(voMenu==null)
		{
			for(DeskDetailVO vo : lstBottomMenus)
				if(vo.getDeskMenuId().equals(deskMenuId))
				{
					voMenu = vo;
					break;
				}
		}
		if(voMenu!=null)
		{
			Map<String,String> mapDataFlag = (Map<String,String>)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_NURSING_DATA_FLAG_MAP);
			if(mapDataFlag==null) mapDataFlag = new HashMap<String, String>();
			
			mapDataFlag.put(voMenu.getDeskMenuId(), "1");
			
			DynamicDeskUTIL.setAttributeInSession(objRequest_p,DynamicDeskConfig.DYNAMIC_DESK_NURSING_DATA_FLAG_MAP,mapDataFlag);
			
		}
		return Flag;
	}
	
	
	
	
}
