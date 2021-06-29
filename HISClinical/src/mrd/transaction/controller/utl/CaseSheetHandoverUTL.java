package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordEnclosureDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.CaseSheetDispatchDATA;
import mrd.transaction.controller.data.CaseSheetHandoverDATA;
import mrd.transaction.controller.data.CertificateAcceptDATA;
import mrd.transaction.controller.fb.CaseSheetDispatchFB;
import mrd.transaction.controller.fb.CaseSheetHandoverFB;

public class CaseSheetHandoverUTL extends ControllerUTIL
{

	public static boolean getDeptUnitList(CaseSheetHandoverFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;

		try
		{
			 setSysdate(request);
			// String sys=(String)request.getSession().getAttribute(Config.SYSDATE);
			// String time=sys.split(" ")[1];
			List lstUnit = CaseSheetHandoverDATA.getDeptUnitList(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.MRD_DEPT_UNIT_LIST, lstUnit);

			// objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");

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

	public static boolean getWardOnBasisOfUnitCode(CaseSheetHandoverFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		List listWard = new ArrayList();
		// HttpSession session = request.getSession();

		try
		{
			 setSysdate(request);
			String unitCode = fb.getDepartmentUnitCode();

			listWard = CaseSheetHandoverDATA.getWardOnBasisOfUnitCode(unitCode, getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT, listWard);
			fb.setWardCode("-1");

			// objStatus.add(Status.INPROCESS);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(request, MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT, listWard);
			e.printStackTrace();
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

	//added by swati
	//date:01-may-2019
	
	private static void setIsDelay(List<CaseSheetDtlVO> caseSheetDtlVOList) {
		try{
			CaseSheetDtlVO casesheetDtlVO=null;
			for(int i=0;i<caseSheetDtlVOList.size();i++){
				casesheetDtlVO=caseSheetDtlVOList.get(i);
				if(casesheetDtlVO.getRecordStatus()!=null && 
						casesheetDtlVO.getRecordStatus().split("#")[0].equals(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_RETURN)){
					casesheetDtlVO.setIsDelay("3");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	
	//added by swati
		//date:06-may-2019
		
		public static void getCaseSheetListToReadyADMNOWISE(CaseSheetHandoverFB fb,HttpServletRequest request)
		{
			Status objStatus = new Status();
			//boolean flag=false;
			List<CaseSheetDtlVO> caseSheetDtlVOList=new ArrayList<CaseSheetDtlVO>();
			CaseSheetDtlVO casesheetDtlVO=null;
			try
			{
				String admno =fb.getPatAdmNo();
				caseSheetDtlVOList=CaseSheetHandoverDATA.getCaseSheetListToReadyADMNOWISE(admno,getUserVO(request));
				//flag=true
				if(caseSheetDtlVOList!=null && caseSheetDtlVOList.size()==0){
					throw new HisRecordNotFoundException("No Record Found");
				}
				
				if(caseSheetDtlVOList!=null && caseSheetDtlVOList.size()==0){
					fb.setClearmsg("kliuhkih");
				}
				
				
				
				
				//setIsDelay(caseSheetDtlVOList);
				for(int i=0;i<caseSheetDtlVOList.size();i++){
					casesheetDtlVO=caseSheetDtlVOList.get(i);
					fb.setDepartmentCode(casesheetDtlVO.getDepartmentCode());
					fb.setDepartmentUnitCode(casesheetDtlVO.getDepartmentUnitCode());
					fb.setWardCodeNew(casesheetDtlVO.getWardCode());
				}
				WebUTIL.setAttributeInSession(request,MrdConfig.PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY , caseSheetDtlVOList);
				objStatus.add(Status.TRANSINPROCESS);
				 
				
			}
			catch (HisRecordNotFoundException e)
			{
				e.printStackTrace();
				WebUTIL.setAttributeInSession(request,MrdConfig.PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY , null);
				objStatus.add(Status.NEW);
				objStatus.add(Status.INPROCESS, e.getMessage(), "");
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
		}
		
		//added by swati sagar
		//date:06-may-2019
		
		public static void getCaseSheetListToReadyCRNOWISE(CaseSheetHandoverFB fb,HttpServletRequest request)
		{
			Status objStatus = new Status();
			//boolean flag=false;
			List<CaseSheetDtlVO> caseSheetDtlVOList=new ArrayList<CaseSheetDtlVO>();
			CaseSheetDtlVO casesheetDtlVO=null;
			try
			{
				String crno =fb.getStrCrNo();
				caseSheetDtlVOList=CaseSheetHandoverDATA.getCaseSheetListToReadyCRNOWISE(crno,getUserVO(request));
				//flag=true
				if(caseSheetDtlVOList!=null && caseSheetDtlVOList.size()==0){
					throw new HisRecordNotFoundException("No Record Found");
				}
				setIsDelay(caseSheetDtlVOList);
				for(int i=0;i<caseSheetDtlVOList.size();i++){
					casesheetDtlVO=caseSheetDtlVOList.get(i);
					fb.setDepartmentCode(casesheetDtlVO.getDepartmentCode());
					fb.setDepartmentUnitCode(casesheetDtlVO.getDepartmentUnitCode());
					fb.setWardCodeNew(casesheetDtlVO.getWardCode());
				}
				WebUTIL.setAttributeInSession(request,MrdConfig.PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY , caseSheetDtlVOList);
				objStatus.add(Status.TRANSINPROCESS);
				 
				
			}
			catch (HisRecordNotFoundException e)
			{
				e.printStackTrace();
				WebUTIL.setAttributeInSession(request,MrdConfig.PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY , null);
				objStatus.add(Status.NEW);
				objStatus.add(Status.INPROCESS, e.getMessage(), "");
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
		}
		
		
		
	
	
	
	
	
	
	/**
	 * get the patient case sheet status by crno who are either admitted or discharged
	 * 
	 * @param fb
	 * @param request
	 */
	public static boolean getPatientCaseSheetDtlByCrNo(CaseSheetHandoverFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		RecordDispatchDtlVO[] recordDispatchVOArray = null;
		boolean flag = false;
		try
		{ 
			 setSysdate(request);
			RecordDispatchDtlVO recordDispatchVO = new RecordDispatchDtlVO();
			recordDispatchVO.setRecordType(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
			recordDispatchVO.setPatCrNo(fb.getPatCrNo());
			recordDispatchVO.setRecordStatus(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_READY_TO_DIPATCH);
			recordDispatchVOArray = CaseSheetHandoverDATA.getRecordListByPatCrNo(recordDispatchVO, getUserVO(request));
			getEssentialForCaseSheetHandOver(recordDispatchVOArray[0].getDeptUnitCode(), recordDispatchVOArray[0].getWardCode(), request);
			fb.setIsAccept("1");
			flag = true;
			WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_DISPATCH_VO_ARRAY, recordDispatchVOArray);
			objStatus.add(Status.RECORDFOUND);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW, "", e.getMessage());
			WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_DISPATCH_VO_ARRAY, null);
			e.printStackTrace();
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

	/**
	 * get the list of the case sheet which are ready to dispatch
	 * 
	 * @param fb
	 * @param request
	 */

	public static void getRecordDispatchList(CaseSheetHandoverFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		RecordDispatchDtlVO[] recordDispatchVOArray = null;
		HttpSession session=request.getSession();
		try
		{
			String deptUnitCode = fb.getDepartmentUnitCode();
			String wardCode = fb.getWardCode();
			RecordDispatchDtlVO recordDispatchVO = new RecordDispatchDtlVO();
			recordDispatchVO.setDeptUnitCode(deptUnitCode);
			recordDispatchVO.setWardCode(wardCode);
			recordDispatchVO.setRecordType(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
			recordDispatchVO.setRecordStatus(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_READY_TO_DIPATCH);
			
			String recordType=(String)session.getAttribute(MrdConfig.RECORD_TYPE_FOR_ACCEPTANCE);
			String recordTypeName=(String)session.getAttribute(MrdConfig.RECORD_TYPE_NAME_FOR_ACCEPTANCE);
			String mrdCode=(String)session.getAttribute(MrdConfig.MRD_CODE_FOR_ACCEPTANCE);
			fb.setMrdCode(mrdCode);
			fb.setRecordType(recordType);
			fb.setRecordTypeName(recordTypeName);
			
			recordDispatchVOArray = CaseSheetHandoverDATA.getRecordDispatchList(recordDispatchVO, getUserVO(request));
			getEssentialForCaseSheetHandOver(deptUnitCode, wardCode, request);
			fb.setIsAccept("1");
			WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_DISPATCH_VO_ARRAY, recordDispatchVOArray);
			objStatus.add(Status.RECORDFOUND);
			// objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			// objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_DISPATCH_VO_ARRAY, null);
			objStatus.add(Status.NEW, e.getMessage(), "");
			e.printStackTrace();
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

	}
//ADDED BY SWATI DTL  ON DATE:06-MAY-2019
	//ADM NO WISE DTL
	
	public static void getAdmNoWiseDtl(CaseSheetHandoverFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		RecordDispatchDtlVO[] recordDispatchVOArray = null;
		HttpSession session=request.getSession();
		try
		{	
			String admno =fb.getPatAdmNo();
			recordDispatchVOArray = CaseSheetHandoverDATA.getCaseSheetListToReadyADMNOWISE2(admno, getUserVO(request));
			//getEssentialForCaseSheetHandOver(deptUnitCode, wardCode, request);
			fb.setIsAccept("1");
			WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_DISPATCH_VO_ARRAY, recordDispatchVOArray);
			objStatus.add(Status.RECORDFOUND);
			// objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			// objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_DISPATCH_VO_ARRAY, null);
			objStatus.add(Status.NEW, e.getMessage(), "");
			e.printStackTrace();
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

	
		}
	
	
	
	
	//ADDED BY SWATI  ON DATE:07-MAY-2019
	// CR NO wise dtl
	
		public static void getCrNoWiseDtl(CaseSheetHandoverFB fb, HttpServletRequest request)
		{
			Status objStatus = new Status();
			RecordDispatchDtlVO[] recordDispatchVOArray = null;
			HttpSession session=request.getSession();
			try
			{	
				String crno =fb.getStrCrNo();
				recordDispatchVOArray = CaseSheetHandoverDATA.getCaseSheetListToReadyCRNOWISE2(crno, getUserVO(request));
				//getEssentialForCaseSheetHandOver(deptUnitCode, wardCode, request);
				fb.setIsAccept("1");
				WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_DISPATCH_VO_ARRAY, recordDispatchVOArray);
				objStatus.add(Status.RECORDFOUND);
				// objStatus.add(Status.NEW);
			}
			catch (HisRecordNotFoundException e)
			{
				// objStatus.add(Status.NEW);
				WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_DISPATCH_VO_ARRAY, null);
				objStatus.add(Status.NEW, e.getMessage(), "");
				e.printStackTrace();
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

		
			}
		
	
	// get all Enclosures of the dispatchId from enclosure summary table
	public static void getAllEnclosureDetails(CaseSheetHandoverFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		Map essentialMap = new HashMap();
		RecordDispatchDtlVO[] recordDispatchVOArray = null;
		RecordDispatchDtlVO recordDispatchVO = null;
		try
		{
			recordDispatchVOArray = (RecordDispatchDtlVO[]) session.getAttribute(MrdConfig.RECORD_DISPATCH_VO_ARRAY);
			int index = Integer.parseInt(request.getParameter("index"));
			recordDispatchVO = recordDispatchVOArray[index];
			String disptachId = recordDispatchVO.getDispatchId();

			essentialMap = CaseSheetHandoverDATA.getAllEnclosureDetails(recordDispatchVO, getUserVO(request));
			RecordTypeWiseEnclosureMstVO[] enclosureDtlVO = (RecordTypeWiseEnclosureMstVO[]) essentialMap
					.get(MrdConfig.RECORD_ENCLOSURE_SUMMARY_VO_ARRAY);
			RecordTypeCheckListMstVO[] checkListDtlVO = (RecordTypeCheckListMstVO[]) essentialMap
					.get(MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY);

			fb.setIndex(String.valueOf(index));
			fb.setDispatchId(disptachId);

			setpreviouslyAddedEnclosures(enclosureDtlVO, disptachId, fb, request);
			setpreviousChecklist(checkListDtlVO, disptachId, fb, request);
			
			WebUTIL.setMapInSession(essentialMap, request);
			if((enclosureDtlVO==null || enclosureDtlVO.length==0) && (checkListDtlVO==null || checkListDtlVO.length==0))
				objStatus.add(Status.DONE, "No Record Found for Checklist and Enclosures","");
			else
				objStatus.add(Status.DONE);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
	}

	// get the detail of previously added Enclosure during the this process
	// set the detail on the form
	private static void setpreviouslyAddedEnclosures(RecordTypeWiseEnclosureMstVO[] enclosureDtlVO, String dispatchId, CaseSheetHandoverFB fb,
			HttpServletRequest request)
	{
		Map enclosureMap = null;
		try
		{
			String[] noOfPages = new String[enclosureDtlVO.length];
			for (int j = 0; j < enclosureDtlVO.length; j++)
				noOfPages[j] = "";

			enclosureMap = (Map) request.getSession().getAttribute(MrdConfig.CASESHEET_ENCLOSURE_CHECKLIST_MAP);
			if (enclosureMap != null && enclosureMap.get(dispatchId) != null)
			{
				List<RecordTypeWiseEnclosureMstVO> enclosureList = (List) ((List) enclosureMap.get(dispatchId)).get(0);
				if (enclosureList != null)
				{
					for (int i = 0; i < enclosureList.size(); i++)
					{
						for (int j = 0; j < enclosureDtlVO.length; j++)
						{
							if (enclosureList.get(i).getEnclosureId().equals(enclosureDtlVO[j].getEnclosureId()))
							{
								noOfPages[j] = enclosureList.get(i).getVerifiedByPeon();
								break;
							}
						}
					}
				}
			}
			else
			{
				for (int j = 0; j < enclosureDtlVO.length; j++)
					noOfPages[j] = "";
			}
			fb.setPages(noOfPages);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// add enclosures for the selected patient
	// and put it in map with key as dispatchId and save it in session
	public static void addEnclosures(CaseSheetHandoverFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		List<RecordTypeWiseEnclosureMstVO> enclosureMstVOList = new ArrayList<RecordTypeWiseEnclosureMstVO>();
		RecordTypeWiseEnclosureMstVO enclosureMstVO = null;
		Map enclosureChecklistMap = new HashMap();
		RecordCheckListDtlVO recChecklistDetailVO = null;
		RecordDispatchDtlVO[] recordDispatchVOArray = null;
		RecordDispatchDtlVO recordDispatchVO = null;
		List<RecordCheckListDtlVO> checklistVOList = new ArrayList<RecordCheckListDtlVO>();
		try
		{
			enclosureChecklistMap = (Map) session.getAttribute(MrdConfig.CASESHEET_ENCLOSURE_CHECKLIST_MAP);

			recordDispatchVOArray = (RecordDispatchDtlVO[]) session.getAttribute(MrdConfig.RECORD_DISPATCH_VO_ARRAY);
			int index = Integer.parseInt(fb.getIndex());
			recordDispatchVO = recordDispatchVOArray[index];

			RecordTypeWiseEnclosureMstVO[] enclosureDtlVO = (RecordTypeWiseEnclosureMstVO[]) session.getAttribute(MrdConfig.RECORD_ENCLOSURE_SUMMARY_VO_ARRAY);
			//RecordTypeCheckListMstVO[] checkListDtlVO = (RecordTypeCheckListMstVO[]) session.getAttribute(MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY);
			
			if (fb.getSelectedCheckListId() != null)
			{
				for (int i = 0; i < fb.getSelectedCheckListId().length; i++)
				{
					recChecklistDetailVO = new RecordCheckListDtlVO();
					recChecklistDetailVO.setCheckListId(fb.getSelectedCheckListId()[i]);
					recChecklistDetailVO.setCheckListBy(MrdConfig.CHECKLIST_BY_RECEIVING_LEVEL);
					recChecklistDetailVO.setRecordType(recordDispatchVO.getRecordType());
					recChecklistDetailVO.setRemarks(fb.getRemarks()[i]);
					recChecklistDetailVO.setRecordId(fb.getDispatchId());
					recChecklistDetailVO.setDispatchId(fb.getDispatchId());
					checklistVOList.add(recChecklistDetailVO);
				}
			}

			if (fb.getPages() != null) 
			{
				for (int i = 0; i < enclosureDtlVO.length; i++)
				{
					enclosureMstVO = new RecordTypeWiseEnclosureMstVO();
					enclosureMstVO.setEnclosureId(fb.getSelectedEnclosureId()[i]);
					enclosureMstVO.setDispatchId(recordDispatchVO.getDispatchId());
					enclosureMstVO.setRecordTypeId(recordDispatchVO.getRecordType());
					enclosureMstVO.setVerifiedByPeon(fb.getPages()[i]);
					enclosureMstVOList.add(enclosureMstVO);
				}
			}
			List list = new ArrayList();
			list.add(enclosureMstVOList);
			list.add(checklistVOList);

			if (enclosureChecklistMap == null)
			{
				enclosureChecklistMap = new HashMap();
				enclosureChecklistMap.put(fb.getDispatchId(), list);
			}
			else
			{
				enclosureChecklistMap.put(fb.getDispatchId(), list);
			}

			WebUTIL.setAttributeInSession(request, MrdConfig.CASESHEET_ENCLOSURE_CHECKLIST_MAP, enclosureChecklistMap);

			objStatus.add(Status.DONE);

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
	}

	// add selectedRecord for the in session
	public static void setSelectedRecord(CaseSheetHandoverFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		Map selectedRecordMap = null;
		List<RecordDispatchDtlVO> recordDispatchVOList = null;;
		try
		{
			RecordDispatchDtlVO[] recordDispatchVOArray = (RecordDispatchDtlVO[]) session.getAttribute(MrdConfig.RECORD_DISPATCH_VO_ARRAY);
			selectedRecordMap = (Map) session.getAttribute(MrdConfig.SELECTED_RECORD_MAP);
			if (fb.getSelectedRecord() != null)
			{
				recordDispatchVOList = new ArrayList<RecordDispatchDtlVO>();
				for (int i = 0; i < fb.getSelectedRecord().length; i++)
				{
					RecordDispatchDtlVO dispatchVO = recordDispatchVOArray[i];
					String dispatchId = fb.getCheckedDispatchId()[Integer.parseInt(fb.getSelectedRecord()[i])];
					if (dispatchVO.getDispatchId().equals(dispatchId))
					{
						recordDispatchVOList.add(dispatchVO);
					}
				}
			}

			if (selectedRecordMap == null)
			{
				selectedRecordMap = new HashMap();
			}
			if (recordDispatchVOList != null) selectedRecordMap.put(fb.getLastSelectedPage(), recordDispatchVOList);
			getSelectedRecord(fb, request);
			fb.setLastSelectedPage(String.valueOf(fb.getCurrentPage()));
			WebUTIL.setAttributeInSession(request, MrdConfig.SELECTED_RECORD_MAP, selectedRecordMap);

			objStatus.add(Status.RECORDFOUND);

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
	}

	private static void getSelectedRecord(CaseSheetHandoverFB fb, HttpServletRequest request)
	{

		HttpSession session = request.getSession();
		try
		{
			Map selectedRecordMap = (Map) session.getAttribute(MrdConfig.SELECTED_RECORD_MAP);
			String checkedItem = "";
			if (selectedRecordMap != null)
			{
				List<RecordDispatchDtlVO> recordDispatchVOList = (List) selectedRecordMap.get(String.valueOf(fb.getCurrentPage()));
				for (int i = 0; i < recordDispatchVOList.size(); i++)
				{
					checkedItem += "#" + recordDispatchVOList.get(i).getDispatchId();
				}
				checkedItem = checkedItem.substring(1);
			}
			fb.setCheckedItem(checkedItem);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void getEssentialForCaseSheetHandOver(String deptUnitCode, String wardCode, HttpServletRequest request)
	{
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(request);
			// String deptUnitCode = _fb.getDepartmentUnitCode().replace("^", "#").split("#")[0];
			// String wardCode = _fb.getWardCode();
			RecordDispatchDtlVO recordDispatchVO = new RecordDispatchDtlVO();
			recordDispatchVO.setDeptUnitCode(deptUnitCode);
			recordDispatchVO.setWardCode(wardCode);
			recordDispatchVO.setRecordType(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
			Map essentialMap = CaseSheetHandoverDATA.getEssentialForCaseSheetHandOver(recordDispatchVO, userVO);
			WebUTIL.setMapInSession(essentialMap, request);
			objStatus.add(Status.RECORDFOUND);
		}

		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.RECORDFOUND);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}

	}

	// get checklist based on recordType and compulsory at handover
	public static void getChecklist(CaseSheetHandoverFB _fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(request);
			String recordType = MrdConfig.RECORD_TYPE_GENERAL_CASESHEET;
			RecordTypeCheckListMstVO checklistVOArray[] = CaseSheetHandoverDATA.getChecklistForHandOver(recordType,
					MrdConfig.CHECKLIST_MODE_HANDOVER_LEVEL, userVO);
			WebUTIL.setAttributeInSession(request, MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY, checklistVOArray);
			objStatus.add(Status.RECORDFOUND);
		}

		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.RECORDFOUND);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}

	}

	/************************************************************************************************************************
	 * save the hand over detail of the case sheet the record status of the record dispatch table is updated as SEND/HANDOVER
	 * if the case sheet is accepted and RETURNED/NOT RECEIVED if the case sheet is rejected the checklist detail is saved if
	 * any selected (if Accepted) update the VerifiedByPeon of the enclosure summary table (if Accepted) update the casesheet
	 * status as INWARD if case sheet is rejected
	 ***********************************************************************************************************************/

	public static void save(CaseSheetHandoverFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		List<RecordDispatchDtlVO> recordDispatchVOList = new ArrayList<RecordDispatchDtlVO>();
		RecordDispatchDtlVO[] recordDispatchVOArray = null;
		RecordDispatchDtlVO recordDispatchVO = null;
		Map enclosureMap = null;
		// RecordTypeCheckListMstVO [] checklistVOArray=null;
		List<RecordCheckListDtlVO> checklistVOList = null;
		// RecordCheckListDtlVO checklistVO=null;
		UserVO userVO = getUserVO(request);
		
		try
		{
			recordDispatchVOArray = (RecordDispatchDtlVO[]) session.getAttribute(MrdConfig.RECORD_DISPATCH_VO_ARRAY);
			// checklistVOArray=(RecordTypeCheckListMstVO[])session.getAttribute(MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY);
			String isAccepted = fb.getIsAccept();
			// create record dispatch vo for the selected records
			if (fb.getSelectedRecord() != null)
			{
				int j = 0;
				for (int i = 0; i < recordDispatchVOArray.length; i++)
				{
					if (j < fb.getSelectedRecord().length && i == Integer.parseInt(fb.getSelectedRecord()[j]))
					{
						recordDispatchVO = recordDispatchVOArray[i];
						if (isAccepted.equals("1"))
						{
							recordDispatchVO.setRecordStatus(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_SEND);
							recordDispatchVO.setSenderEmpNo(fb.getReceivedFrom());
							recordDispatchVO.setRecipentEmpNo(userVO.getUserEmpID());
							recordDispatchVO.setRecipientSeatId(userVO.getSeatId());
						}
						else
						{
							recordDispatchVO.setReturnReason(fb.getReturnReason());
							recordDispatchVO.setRecordStatus(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_RETURN);
						}
						recordDispatchVOList.add(recordDispatchVO);
						j++;
					}
				}
			}

			// create checklistDtl vo for selected checklist
			
			/* * if(fb.getSelectedCheckListId()!=null){ checklistVOList=new ArrayList<RecordCheckListDtlVO>(); int k=0;
			 * for(int i=0;i<checklistVOArray.length;i++){ if(k<fb.getSelectedCheckListId().length &&
			 * checklistVOArray[i].getChecklistId().equals(fb.getSelectedCheckListId()[k])){ checklistVO=new
			 * RecordCheckListDtlVO(); checklistVO.setCheckListId(checklistVOArray[i].getChecklistId());
			 * checklistVO.setCheckListBy(MrdConfig.CHECKLIST_BY_RECEIVING_LEVEL);
			 * checklistVO.setRecordType(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
			 * checklistVO.setRemarks(fb.getRemarks()[k]); checklistVOList.add(checklistVO); k++; } } }
			 
*/
			if (isAccepted.equals("1"))
			{
				enclosureMap = (Map) session.getAttribute(MrdConfig.CASESHEET_ENCLOSURE_CHECKLIST_MAP);
			}

			CaseSheetHandoverDATA.saveHandoverDetail(recordDispatchVOList, checklistVOList, enclosureMap, isAccepted, userVO);
			objStatus.add(Status.DONE, "", "Record Saved Successfully");

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
		
		/*Status objStatus = new Status();
		HttpSession session=request.getSession();
		List<MrdRecordDtlVO> lstMrdRecordVO=new ArrayList<MrdRecordDtlVO>();
		List<RecordTypeWiseEnclosureMstVO> lstEnclosure=new ArrayList<RecordTypeWiseEnclosureMstVO>();
		List<RecordCheckListDtlVO> lstCheckList=new ArrayList<RecordCheckListDtlVO>();
		List<RecordEnclosureDtlVO> lstRecordEnclosureDtl=new ArrayList<RecordEnclosureDtlVO>();
		RecordDispatchDtlVO[] arrRecordDispatchVO=null;
		
		
		try
		{
			arrRecordDispatchVO=(RecordDispatchDtlVO[])session.getAttribute(MrdConfig.ARR_RECORD_LIST_TO_ACCEPT_IN_MRD_BY_RECORD_TYPE_VO);
			for(int i=0;i<fb.getSelectedRecord().length;i++)
			{
				for(int j=0;j<arrRecordDispatchVO.length;j++)
				{
					if(fb.getSelectedRecord()[i].equals(arrRecordDispatchVO[j].getDispatchId()))
					{
						MrdRecordDtlVO mrdRecordVO=new MrdRecordDtlVO();
						mrdRecordVO.setRecordId(arrRecordDispatchVO[j].getRecordId());
						mrdRecordVO.setRecordDesc(arrRecordDispatchVO[j].getRecordDesc());
						mrdRecordVO.setRecordType(fb.getRecordType());
						mrdRecordVO.setRecordStatus(MrdConfig.MRD_RECORD_STATUS_IN_MRD);
						mrdRecordVO.setMrdCode(fb.getMrdCode());
						mrdRecordVO.setPatCrNo(arrRecordDispatchVO[j].getPatCrNo());
						mrdRecordVO.setEpisodeCode(arrRecordDispatchVO[j].getEpisodeCode());
						mrdRecordVO.setEpisodeVisitNo(arrRecordDispatchVO[j].getEpisodeVisitNo());
						mrdRecordVO.setPatAdmNo(arrRecordDispatchVO[j].getPatAdmNo());
						mrdRecordVO.setDispatchId(arrRecordDispatchVO[j].getDispatchId());
						
						lstMrdRecordVO.add(mrdRecordVO);
						
						/////For Enclosure
						Map enclosureListMap=(Map)session.getAttribute(MrdConfig.ENCLOSURE_ACCEPTED_IN_MRD_MAP);
						if(enclosureListMap!=null)
						{
							List lstEnclosureInMrdVO=(List)enclosureListMap.get(fb.getSelectedRecord()[i]);
							for(int k=0;k<lstEnclosureInMrdVO.size();k++)
							{
								RecordTypeWiseEnclosureMstVO enclosureDtlVO=new RecordTypeWiseEnclosureMstVO();
								enclosureDtlVO=(RecordTypeWiseEnclosureMstVO)lstEnclosureInMrdVO.get(k);
								
								lstEnclosure.add(enclosureDtlVO);
								
								RecordEnclosureDtlVO recEnclosureDtlVO=new RecordEnclosureDtlVO();
								recEnclosureDtlVO.setEnclosureId(enclosureDtlVO.getEnclosureId());
								recEnclosureDtlVO.setPages(enclosureDtlVO.getVerifiedByMrd());
								recEnclosureDtlVO.setIsLost(MrdConfig.NO);
								
								lstRecordEnclosureDtl.add(recEnclosureDtlVO);
							}
						}
						
						////For CheckList
						Map checkListMap=(Map)session.getAttribute(MrdConfig.CHECKLIST_ARCHIVED_IN_MRD_MAP);
						if(checkListMap!=null)
						{
							List lstArchivalCheckList=(List)checkListMap.get(fb.getSelectedRecord()[i]);
							for(int k=0;k<lstArchivalCheckList.size();k++)
							{
								RecordCheckListDtlVO checkListDtlVO=new RecordCheckListDtlVO();
								checkListDtlVO=(RecordCheckListDtlVO)lstArchivalCheckList.get(k);
								
								lstCheckList.add(checkListDtlVO);
							}
						}
					}
				}
				
			}
			

			CaseSheetHandoverDATA.saveHandoverDetail(lstMrdRecordVO,lstEnclosure,lstCheckList,lstRecordEnclosureDtl,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Accepted In Mrd");
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}*/
	}

	// get checklist based on recordType and compulsory at handover
	public static void setSelectedChecklist(CaseSheetHandoverFB _fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		String[] checkedChecklist = null;
		String checkedItem = "";
		String[] remarks = null;
		RecordTypeCheckListMstVO[] checklistVOArray = null;
		try
		{
			checklistVOArray = (RecordTypeCheckListMstVO[]) request.getSession().getAttribute(MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY);

			if (_fb.getSelectedCheckListId() != null && checklistVOArray != null)
			{
				checkedChecklist = new String[checklistVOArray.length];
				// remarks=new String[checklistVOArray.length];
				int k = 0;
				for (int i = 0; i < checklistVOArray.length; i++)
				{
					if (k < _fb.getSelectedCheckListId().length
							&& checklistVOArray[i].getChecklistId().equals(_fb.getSelectedCheckListId()[k].trim()))
					{
						checkedChecklist[i] = _fb.getSelectedCheckListId()[k];
						checkedItem += "#" + _fb.getSelectedCheckListId()[k];
						// remarks[i]=_fb.getRemarks()[k];
						k++;
					}
					else
					{
						checkedChecklist[i] = "";
						remarks[i] = "";
					}
				}
				checkedItem = checkedItem.substring(1);
				_fb.setCheckedItem(checkedItem);
				// _fb.setRemarks(remarks);
			}

		}

		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}

	}

	// set the previously added checklist detail on the form
	private static void setpreviousChecklist(RecordTypeCheckListMstVO[] checklistVOArray, String recordDispatchId, CaseSheetHandoverFB _fb,
			HttpServletRequest request)
	{
		List<RecordCheckListDtlVO> selectedChecklist = null;
		Map enclosureChecklistMap = null;
		try
		{
			String checkedItem = "";
			String[] remarks = new String[checklistVOArray.length];
			for (int j = 0; j < checklistVOArray.length; j++)
				remarks[j] = "";

			enclosureChecklistMap = (Map) request.getSession().getAttribute(MrdConfig.CASESHEET_ENCLOSURE_CHECKLIST_MAP);
			if (recordDispatchId != null && enclosureChecklistMap != null && enclosureChecklistMap.get(recordDispatchId) != null)
			{
				selectedChecklist = (List) ((List) enclosureChecklistMap.get(recordDispatchId)).get(1);
				if (selectedChecklist != null)
				{
					for (int i = 0; i < selectedChecklist.size(); i++)
					{
						for (int j = 0; j < checklistVOArray.length; j++)
						{
							if (selectedChecklist.get(i).getCheckListId().equals(checklistVOArray[j].getChecklistId()))
							{
								checkedItem = checkedItem + "#" + selectedChecklist.get(i).getCheckListId();
								remarks[j] = selectedChecklist.get(i).getRemarks();
								break;
							}
						}
					}
				}
			}
			 if(!checkedItem.equals("")) checkedItem = checkedItem.substring(1);
			_fb.setCheckedChecklist(checkedItem);
			_fb.setRemarks(remarks);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
