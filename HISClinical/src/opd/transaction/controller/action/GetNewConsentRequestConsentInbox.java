package opd.transaction.controller.action;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.PatientDetailVO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.bo.delegate.OpdPatientDelegate;

public class GetNewConsentRequestConsentInbox extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	static final long serialVersionUID = 1L;

	public GetNewConsentRequestConsentInbox()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String patCrNo = request.getParameter("PatCrNo");

			HttpSession session = request.getSession();
			PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			
			if(arrayDailyPatVO!=null)
			{
				// here we retrive the selected patient from the (desk)list
				for (int i = 0; i < arrayDailyPatVO.length; i++)
				{
					if (patCrNo.equals(arrayDailyPatVO[i].getPatCrNo()))
					{
						selectedPatientVO = arrayDailyPatVO[i];
					}
				}
			}
			if(selectedPatientVO!=null)
			{
				ConsentRequestVO consentRequestVO = new ConsentRequestVO();
				consentRequestVO.setPatCrNo(patCrNo);
				consentRequestVO.setEpisodeCode(selectedPatientVO.getEpisodeCode());
				consentRequestVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
	
				String hospitalcode = ControllerUTIL.getUserVO(request).getHospitalCode();
				
				OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();				
				String count = opdPatientDelegate.getNoOfNewConsentRequest(consentRequestVO, hospitalcode);
				
				PrintWriter printWriter = response.getWriter();
				printWriter.write(count);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
