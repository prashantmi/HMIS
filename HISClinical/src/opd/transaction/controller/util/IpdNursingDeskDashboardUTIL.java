package opd.transaction.controller.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.DoctorDeskDashboardDATA;
import opd.transaction.controller.fb.DoctorDeskFB;

import org.apache.struts.action.ActionMapping;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.PatientDetailVO;

public class IpdNursingDeskDashboardUTIL extends ControllerUTIL
{

	public static void getDeskPatDtl(DoctorDeskFB objFB_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p,
			ActionMapping objMapping_p)
	{
		DoctorDeskDashboardDATA doctorDeskDashboardDATA = new DoctorDeskDashboardDATA();
		PatientDetailVO patientDetailVO = new PatientDetailVO();	
		try
		{
			HelperMethods.populate(patientDetailVO, objFB_p);
			Map<String, Object> mapEssential = doctorDeskDashboardDATA.getDeskPatDtl(patientDetailVO, ControllerUTIL.getUserVO(objRequest_p),objFB_p.getDeskType());
			WebUTIL.setMapInSession(mapEssential, objRequest_p);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	
	public static StringBuffer AJX_G_PATIENTS_VISIT_SUMMARY(DoctorDeskFB doctorDeskFB, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p,
			ActionMapping objMapping_p) {
				String strMsgText = "";
				 String dataShouldShow = "1";
				HttpSession objSession = objRequest_p.getSession();
				StringBuffer sbAjaxRes = new StringBuffer();
				DoctorDeskDashboardDATA doctorDeskDashboardDATA = new DoctorDeskDashboardDATA();
				HttpSession session = objRequest_p.getSession();
				PatientDetailVO patientDetailVO = new PatientDetailVO();	

				try
				{
					String episodeCode = doctorDeskFB.getEpisodeCode();
					String episodeVisitNo = doctorDeskFB.getEpisodeVisitNo();
					String patCrNo = doctorDeskFB.getPatCrNo();
					patientDetailVO.setPatCrNo(patCrNo);
					patientDetailVO.setEpisodeVisitNo(episodeVisitNo);
					patientDetailVO.setEpisodeCode(episodeCode);
					Map<String, Object> mapEssential = doctorDeskDashboardDATA.getDailyPatientDetail(patientDetailVO, ControllerUTIL.getUserVO(objRequest_p));
					try
					{
						//List<PatientDetailVO> patDetail = (List<PatientDetailVO>)objSession.getAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO);
						List<PatientDetailVO> patDetail = (List<PatientDetailVO>) mapEssential.get(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO);
						if(patDetail!=null && patDetail.size()>0)
						{
							sbAjaxRes.append("[");
							for(PatientDetailVO vo : patDetail)
							{
								if((vo.getEpisodeVisitNo().equals("1")) && (vo.getIsConfirmed().equals("1")))
								{
									dataShouldShow="0";
								}
								sbAjaxRes.append("{");
								sbAjaxRes.append("strHeader");sbAjaxRes.append(":");
								sbAjaxRes.append("\'");sbAjaxRes.append("Visit Summary");sbAjaxRes.append("\'");sbAjaxRes.append(",");
								sbAjaxRes.append("strIsDataPresent");sbAjaxRes.append(":");
								sbAjaxRes.append("\'");sbAjaxRes.append("1");sbAjaxRes.append("\'");sbAjaxRes.append(",");
								sbAjaxRes.append("strShowprocessFlag");sbAjaxRes.append(":");
								sbAjaxRes.append("\'");sbAjaxRes.append(dataShouldShow);sbAjaxRes.append("\'");sbAjaxRes.append(",");
								sbAjaxRes.append("}");sbAjaxRes.append(",");
							}
							if(patDetail.size()>0)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
							sbAjaxRes.append("]");
							System.out.println("JSON Object value is :-"+sbAjaxRes);
						}
					}
					catch (Exception e)
					{
						strMsgText = "OpdBayDeskDashboardUTIL.getDailyPatientDetail1() -> " + e.getMessage();
						HisException eObj = new HisException("opd DoctorDesk", "DoctorDeskDashboardUTIL.AJX_G_PATIENTS_VISIT_SUMMARY() --> ", strMsgText);
						eObj = null;
					}
					System.out.println("JSON Object value is :-"+sbAjaxRes);
				}
				catch (NumberFormatException e)
				{
					e.printStackTrace();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				return sbAjaxRes;	
			}

			public static void VISITDETAIL(DoctorDeskFB doctorDeskFB, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p,
					ActionMapping objMapping_p) {
				String strMsgText = "";
				 String dataShouldShow = "1";
				DoctorDeskDashboardDATA doctorDeskDashboardDATA = new DoctorDeskDashboardDATA();
				PatientDetailVO patientDetailVO = new PatientDetailVO();	

				try
				{
					String episodeCode = doctorDeskFB.getEpisodeCode();
					String episodeVisitNo = doctorDeskFB.getEpisodeVisitNo();
					String patCrNo = doctorDeskFB.getPatCrNo();
					patientDetailVO.setPatCrNo(patCrNo);
					patientDetailVO.setEpisodeVisitNo(episodeVisitNo);
					patientDetailVO.setEpisodeCode(episodeCode);
					Map<String, Object> mapEssential = doctorDeskDashboardDATA.getDailyPatientDetail(patientDetailVO, ControllerUTIL.getUserVO(objRequest_p));
					WebUTIL.setMapInSession(mapEssential, objRequest_p);
				}
				catch (NumberFormatException e)
				{
					e.printStackTrace();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
}
