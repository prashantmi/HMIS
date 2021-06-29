package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MrdMedicalCampDtlVO;
import hisglobal.vo.MrdMedicalCampTeamDtlVO;
import hisglobal.vo.UserVO;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.MrdMedicalCampDATA;
import mrd.transaction.controller.fb.MrdMedicalCampFB;

public class MrdMedicalCampUTL extends ControllerUTIL {
	
	public static boolean getCampListForMedicalCamp(MrdMedicalCampFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		
		try
		{
			setSysdate(request);
			MrdMedicalCampDtlVO mrdMedicalCampDtlVO=new MrdMedicalCampDtlVO();
			List mrdRecordRequestVOList=MrdMedicalCampDATA.getCampListForMedicalCamp(mrdMedicalCampDtlVO,getUserVO(request));
			System.out.println("Medical list Size :"+mrdRecordRequestVOList.size());
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_MEDICAL_CAMP_VO_LIST ,mrdRecordRequestVOList);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
		
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}

	public static void getCampEmpNameForMedicalCamp(MrdMedicalCampFB fb,HttpServletRequest request) 
	{
		List lstEmployeeName = null;
		lstEmployeeName= MrdMedicalCampDATA.getCampEmpNameForMedicalCamp(getUserVO(request));
		WebUTIL.setAttributeInSession(request,MrdConfig.MRD_MEDICAL_CAMP_VO_LIST ,lstEmployeeName);
		
	}

	public static boolean saveCampDetail(MrdMedicalCampFB fb, HttpServletRequest request) {	
		System.out.println("MrdMedicalCampUTL.saveCampDetail()");
		//System.out.println(fb.getStrArrEmployeeName().length);
		HttpSession session=request.getSession();
		boolean flag=true;
		UserVO userVO = getUserVO(request);
		MrdMedicalCampDtlVO medicalCampDtlVO = null;
		MrdMedicalCampTeamDtlVO[] medicalCampTeamDtlVO = null;
		String endTime="";
		
		try 
		{
			medicalCampDtlVO = new MrdMedicalCampDtlVO();
			medicalCampDtlVO.setMode("1");
			medicalCampDtlVO.setStrCampId(fb.getStrCampId());
			medicalCampDtlVO.setStrCampName(fb.getStrCampName());
			medicalCampDtlVO.setStrCampVenue(fb.getStrCampVenue());	
			
			String startTime=fb.getCampStartHr()+':'+fb.getCampStartMin();
			if((fb.getCampEndHr() != "") || (fb.getCampEndMin()) != "")
			{
				endTime=fb.getCampEndHr()+':'+fb.getCampEndMin();
				System.out.println("endTime :"+endTime);
			}
			
			medicalCampDtlVO.setStrCampDescription(fb.getStrCampDescription());
			medicalCampDtlVO.setStrCampStartDateTime(fb.getStrCampStartDateTime()+' '+startTime);
			System.out.println("Camp Start Time :"+fb.getStrCampStartDateTime()+' '+startTime);
			medicalCampDtlVO.setStrCampEndDateTime(fb.getStrCampEndDateTime()+' '+endTime);
			medicalCampDtlVO.setStrIsCampClosed(fb.getStrIsCampClosed());
			medicalCampDtlVO.setStrCampRequisitionDate(fb.getStrCampRequisitionDate());
			medicalCampDtlVO.setStrTotalNoofPatientAttended(fb.getStrTotalNoofPatientAttended());
			medicalCampDtlVO.setStrTotalMalePatientAttended(fb.getStrTotalMalePatientAttended());
			medicalCampDtlVO.setStrTotalFemalePatientAttended(fb.getStrTotalFemalePatientAttended());
			medicalCampDtlVO.setStrTotalMaleChildPatientAttended(fb.getStrTotalMaleChildPatientAttended());
			medicalCampDtlVO.setStrTotalFemaleChildPatientAttended(fb.getStrTotalFemaleChildPatientAttended());
			
			if(fb.getStrArrEmployeeName() != null){
			medicalCampTeamDtlVO= new MrdMedicalCampTeamDtlVO[fb.getStrArrEmployeeName().length];
			for(int i=0;i<fb.getStrArrEmployeeName().length;i++)
			{
				medicalCampTeamDtlVO[i]= new MrdMedicalCampTeamDtlVO();
				medicalCampTeamDtlVO[i].setStrArrEmployeeId(fb.getStrArrEmployeeId()[i]);
				medicalCampTeamDtlVO[i].setStrArrEmployeeName(fb.getStrArrEmployeeName()[i]);
				medicalCampTeamDtlVO[i].setStrArrRole(fb.getStrArrRole()[i]);
				medicalCampTeamDtlVO[i].setStrArrDutyRemarks(fb.getStrArrDutyRemarks()[i]);						
			}	
			}
			MrdMedicalCampDATA.saveCampDetail(medicalCampDtlVO,medicalCampTeamDtlVO,userVO);			
			fb.setStrNormalMsg("Camp saved successfully!");			 
			
		}
		catch (Exception e)
		{
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public static void getCampDetail(MrdMedicalCampFB fb, HttpServletRequest request) {

		UserVO userVO = getUserVO(request);
		MrdMedicalCampDtlVO medicalCampDtlVO = new MrdMedicalCampDtlVO();
		try 
		{
		System.out.println("Camp Id :"+fb.getStrCampId());
		medicalCampDtlVO.setStrCampId(fb.getStrCampId());
		MrdMedicalCampDATA.getCampDetail(medicalCampDtlVO,userVO);	

		String startDateTime = medicalCampDtlVO.getStrCampStartDateTime();
		System.out.println(startDateTime);
		String[] startDate= startDateTime.split(" ");
		String[] time = startDate[1].split(":");
		medicalCampDtlVO.setStrCampStartDateTime(startDate[0]);
		medicalCampDtlVO.setCampStartHr(time[0]);
		medicalCampDtlVO.setCampStartMin(time[1]);
		
		String endDateTime = medicalCampDtlVO.getStrCampEndDateTime();
		//added by:NehaRajgariya Date:9march2017                     											 
		if(endDateTime != null)
		{
			String[] endDate= endDateTime.split(" ");
			String[] endtime = endDate[1].split(":");
			medicalCampDtlVO.setStrCampEndDateTime(endDate[0]);
			medicalCampDtlVO.setCampEndHr(endtime[0]);
			medicalCampDtlVO.setCampEndMin(endtime[1]);
		}
		/*else
		{
			medicalCampDtlVO.setStrCampEndDateTime("");
			medicalCampDtlVO.setCampEndHr("");
			medicalCampDtlVO.setCampEndMin("");
		}*/
		
		System.out.println("Date :"+medicalCampDtlVO.getStrCampStartDateTime()+" Hr :"+medicalCampDtlVO.getCampStartHr()+" Min :"+medicalCampDtlVO.getCampStartMin());
		System.out.println("Date :"+medicalCampDtlVO.getStrCampEndDateTime()+" Hr :"+medicalCampDtlVO.getCampEndHr()+" Min :"+medicalCampDtlVO.getCampEndMin());
		
		HelperMethods.populate(fb, medicalCampDtlVO);
		if(fb.getStrIsCampClosed().equals("0"))
		{
			fb.setChk("0");
		}
		else if(fb.getStrIsCampClosed().equals("1"))
		{
			fb.setChk("1");
		}
		List <MrdMedicalCampTeamDtlVO> mrdMedicalCampTeamDtlVO =MrdMedicalCampDATA.getCampEmpDetail(medicalCampDtlVO,userVO);
		WebUTIL.setAttributeInSession(request,MrdConfig.MRD_MEDICAL_CAMP_EMP_VO_LIST ,mrdMedicalCampTeamDtlVO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static boolean updateCampDetail(MrdMedicalCampFB fb,	HttpServletRequest request) {	
		System.out.println("MrdMedicalCampUTL.updateCampDetail()");
		System.out.println("Selected Camp :"+fb.getStrCampId());
		System.out.println(fb.getStrArrEmployeeName().length);
		HttpSession session=request.getSession();
		boolean flag=true;
		UserVO userVO = getUserVO(request);
		MrdMedicalCampDtlVO medicalCampDtlVO = null;
		MrdMedicalCampTeamDtlVO[] medicalCampTeamDtlVO = null;
		
		try 
		{
			medicalCampDtlVO = new MrdMedicalCampDtlVO();
			medicalCampDtlVO.setMode("2");
			medicalCampDtlVO.setStrCampReqNo(fb.getStrCampId());
			medicalCampDtlVO.setStrCampName(fb.getStrCampName());
			medicalCampDtlVO.setStrCampVenue(fb.getStrCampVenue());
			medicalCampDtlVO.setStrCampDescription(fb.getStrCampDescription());
			medicalCampDtlVO.setStrCampStartDateTime(fb.getStrCampStartDateTime());
			medicalCampDtlVO.setStrCampEndDateTime(fb.getStrCampEndDateTime());
			medicalCampDtlVO.setStrIsCampClosed(fb.getStrIsCampClosed());
			medicalCampDtlVO.setStrCampRequisitionDate(fb.getStrCampRequisitionDate());
			medicalCampDtlVO.setStrTotalNoofPatientAttended(fb.getStrTotalNoofPatientAttended());
			medicalCampDtlVO.setStrTotalMalePatientAttended(fb.getStrTotalMalePatientAttended());
			medicalCampDtlVO.setStrTotalFemalePatientAttended(fb.getStrTotalFemalePatientAttended());
			medicalCampDtlVO.setStrTotalMaleChildPatientAttended(fb.getStrTotalMaleChildPatientAttended());
			medicalCampDtlVO.setStrTotalFemaleChildPatientAttended(fb.getStrTotalFemaleChildPatientAttended());
			
			medicalCampTeamDtlVO= new MrdMedicalCampTeamDtlVO[fb.getStrArrEmployeeName().length];
			for(int i=0;i<fb.getStrArrEmployeeName().length;i++)
			{
				medicalCampTeamDtlVO[i]= new MrdMedicalCampTeamDtlVO();
				medicalCampTeamDtlVO[i].setStrCampReqNo(fb.getStrCampId());
				medicalCampTeamDtlVO[i].setStrArrEmployeeId(fb.getStrArrEmployeeId()[i]);
				medicalCampTeamDtlVO[i].setStrArrEmployeeName(fb.getStrArrEmployeeName()[i]);
				medicalCampTeamDtlVO[i].setStrArrRole(fb.getStrArrRole()[i]);
				medicalCampTeamDtlVO[i].setStrArrDutyRemarks(fb.getStrArrDutyRemarks()[i]);						
			}			
			MrdMedicalCampDATA.saveCampDetail(medicalCampDtlVO,medicalCampTeamDtlVO,userVO);			
			fb.setStrNormalMsg("Camp Updated successfully!");			 
			
		}
		catch (Exception e)
		{
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}		
}
