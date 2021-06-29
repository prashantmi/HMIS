package new_investigation.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.controller.data.invListingReportNewDATA;
import new_investigation.transactions.controller.fb.invListingReportNewFB;
import new_investigation.transactions.controller.fb.jQueryDataTableParamModel;
import new_investigation.vo.template.invListingReportNewVO;

public class invListingReportNewUTIL {
	public static void getInvestigaationListingNew(invListingReportNewFB fb, HttpServletRequest request) {
		Status objStatus= new Status();
		UserVO userVO = ControllerUTIL.getUserVO(request);
		invListingReportNewVO invistingReportvo = new invListingReportNewVO();
		invistingReportvo.setFromDate(fb.getFromDate());
		invistingReportvo.setToDate(fb.getToDate());
		invistingReportvo.setIsRadioDignoProcess(fb.getIsRadioDignoProcess());
		invistingReportvo.setStartIndex(fb.getStartIndex());
		invistingReportvo.setRowLimit(fb.getRowLimit());
		try{
			Map mp=new HashMap();
			ControllerUTIL.setSysdate(request);
			mp=invListingReportNewDATA.getInvestigationListingNew(invistingReportvo, userVO);
			WebUTIL.setMapInSession(mp, request);
			objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}
	}


	public static List<invListingReportNewVO> getInvestigaationListingNew_Ajax_Server(invListingReportNewFB fb, HttpServletRequest request, jQueryDataTableParamModel dtparam) {
		Status objStatus= new Status();
		UserVO userVO = ControllerUTIL.getUserVO(request);
		invListingReportNewVO invistingReportvo = new invListingReportNewVO();
		invistingReportvo.setFromDate(fb.getFromDate());
		invistingReportvo.setToDate(fb.getToDate());
		invistingReportvo.setIsRadioDignoProcess(fb.getIsRadioDignoProcess());
		invistingReportvo.setStartIndex(fb.getStartIndex());
		invistingReportvo.setRowLimit(fb.getRowLimit());

		List<invListingReportNewVO> lst = new ArrayList<>();
		try{
			//Map mp=new HashMap();
			ControllerUTIL.setSysdate(request);
			List invListing=new ArrayList();
		//	lst = (List<invListingReportNewVO>)
					invListing=invListingReportNewDATA.getInvestigationListingNew_Ajax_Server(invistingReportvo, userVO);
					lst = (List<invListingReportNewVO>)invListing;
					//lst.get(0)
					//for(invListingReportNewVO invistingReportvo : lst.get(index)) {}

			objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}
		return lst;
	}

	public static List<invListingReportNewVO> getInvestigaationListingNew_Ajax_Client(invListingReportNewFB fb, HttpServletRequest request) {
		Status objStatus= new Status();
		UserVO userVO = ControllerUTIL.getUserVO(request);
		invListingReportNewVO invistingReportvo = new invListingReportNewVO();
		invistingReportvo.setFromDate(fb.getFromDate());
		invistingReportvo.setSearchDateType(fb.getSearchDateType());
		invistingReportvo.setToDate(fb.getToDate());
		invistingReportvo.setIsRadioDignoProcess(fb.getIsRadioDignoProcess());
		//invistingReportvo.setStartIndex(fb.getStartIndex());
		//invistingReportvo.setRowLimit(fb.getRowLimit());

		List<invListingReportNewVO> lst = new ArrayList<>();
		try{
			//Map mp=new HashMap();
			ControllerUTIL.setSysdate(request);
			List invListing=new ArrayList();
		//	lst = (List<invListingReportNewVO>)
					invListing=invListingReportNewDATA.getInvestigationListingNew_Ajax_Client(invistingReportvo, userVO);
					lst = (List<invListingReportNewVO>)invListing;
					//lst.get(0)
					//for(invListingReportNewVO invistingReportvo : lst.get(index)) {}

			objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}
		return lst;
	}


}
