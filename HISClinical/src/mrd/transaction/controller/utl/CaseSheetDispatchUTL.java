package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.CaseSheetDispatchDATA;
import mrd.transaction.controller.fb.CaseSheetDispatchFB;

public class CaseSheetDispatchUTL extends ControllerUTIL {
	
	public static boolean getDeptUnitList(CaseSheetDispatchFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		
		try
		{
		
			List lstUnit=CaseSheetDispatchDATA.getDeptUnitList(getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_DEPT_UNIT_LIST ,lstUnit);
			
			objStatus.add(Status.INPROCESS);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
		
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
	
	public static boolean getWardOnBasisOfUnitCode(CaseSheetDispatchFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		List listWard = new ArrayList();
		//HttpSession session = request.getSession();
		
		try
		{
			String unitCode = fb.getDepartmentUnitCode();;
			
			listWard=CaseSheetDispatchDATA.getWardOnBasisOfUnitCode(unitCode,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT, listWard);
			fb.setWardCode("-1");
					
			objStatus.add(Status.INPROCESS);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT, listWard);
			//fb.setUnitList("0");
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
	
	public static void getCaseSheetListToReady(CaseSheetDispatchFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//boolean flag=false;
		List<CaseSheetDtlVO> caseSheetDtlVOList=new ArrayList<CaseSheetDtlVO>();
		try
		{
			
			System.out.println("hellooooo");
			String unitCode =fb.getDepartmentUnitCode();
			caseSheetDtlVOList=CaseSheetDispatchDATA.getCaseSheetListToReady(unitCode,fb.getWardCode(),getUserVO(request));
			//flag=true;
			if(caseSheetDtlVOList!=null && caseSheetDtlVOList.size()==0){
				throw new HisRecordNotFoundException("No Record Found");
			}
			setIsDelay(caseSheetDtlVOList);
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

	//get Enclosure based on record type 
	public static void getAllEnclosureDetails(CaseSheetDispatchFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		Map essentialMap=null;
		List<RecordTypeWiseEnclosureMstVO> enclosurePreAdded=null;
		List<RecordTypeWiseEnclosureMstVO> enclosureNotAdded=new ArrayList<RecordTypeWiseEnclosureMstVO>();
		//List<RecordTypeCheckListMstVO> checklistNotAdded=new ArrayList<RecordTypeCheckListMstVO>();
		//List<RecordTypeCheckListMstVO> checklistPreAdded=null;
		//RecordCheckListDtlVO checklistVOArray[]=null;
		List<CaseSheetDtlVO> caseSheetDtlVOList=new ArrayList<CaseSheetDtlVO>();
		 
		try
		{
			/*String []array=fb.getSelectCRNo().split("#");
			String crNo=fb.getSelectCRNo().split("#")[0];
			String patAdmNo=fb.getSelectCRNo().split("#")[1];
			String recordStatus="";
			if(array.length>2)
				recordStatus=fb.getSelectCRNo().split("#")[2];
			CaseSheetDtlVO caseSheetDtlVO=new CaseSheetDtlVO();
			caseSheetDtlVO.setPatCrNo(crNo);
			caseSheetDtlVO.setPatAdmNo(patAdmNo);
			caseSheetDtlVO.setRecordStatus(recordStatus);*/
			String index=request.getParameter("index");
			String []array=fb.getSelectedPatient().split("@");
			
			String crNo=fb.getSelectedPatient().split("@")[0];
			String caseSheetId=fb.getSelectedPatient().split("@")[1];
			fb.setCaseSheetId(caseSheetId);
			String recordStatus="";
			if(array.length>2)
				recordStatus=fb.getSelectedPatient().split("@")[2];
			CaseSheetDtlVO caseSheetDtlVO=new CaseSheetDtlVO();
			caseSheetDtlVO.setPatCrNo(crNo);
			caseSheetDtlVO.setCaseSheetId(caseSheetId);
			caseSheetDtlVO.setPatAdmNo(caseSheetId.substring(0, 11));
			caseSheetDtlVO.setRecordStatus(recordStatus);
			caseSheetDtlVOList=(List)session.getAttribute(MrdConfig.PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY);
			
			for(int i=0;i<caseSheetDtlVOList.size();i++){
				if(caseSheetDtlVOList.get(i).getCaseSheetId().equals(caseSheetId)){
					caseSheetDtlVO=caseSheetDtlVOList.get(i);
					break;
				}
			}
			
			//RecordTypeWiseEnclosureMstVO[] enclosureDtlVO=CaseSheetDispatchDATA.getAllEnclosureDetails(getUserVO(request));
			//RecordTypeCheckListMstVO[] checkListDtlVO=CaseSheetDispatchDATA.getAllChecklistDetails(getUserVO(request));
			//PatientDetailVO[] patientDetailVO = (PatientDetailVO[]) session.getAttribute(MrdConfig.PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY);
			essentialMap=CaseSheetDispatchDATA.getAllEnclosureDetails(caseSheetDtlVO,getUserVO(request));
			RecordTypeWiseEnclosureMstVO[] enclosureDtlVO=(RecordTypeWiseEnclosureMstVO[])essentialMap.get(MrdConfig.CASE_SHEET_ENCLOSURE_LIST);
			RecordTypeCheckListMstVO[] checkListDtlVO=(RecordTypeCheckListMstVO[])essentialMap.get(MrdConfig.CASE_SHEET_CHECKLIST);
			
			//get the list of previously added enclosure and checklist
			if(recordStatus.equals(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_RETURN)){
				enclosurePreAdded=(List)essentialMap.get(MrdConfig.CASE_SHEET_ENCLOSURE_PRE_ADDED_VO_LIST);
				for(int i=0;i<enclosureDtlVO.length;i++){
					enclosureNotAdded.add(enclosureDtlVO[i]);
				}
				if(enclosurePreAdded!=null)
				for(int j=0;j<enclosurePreAdded.size();j++){
				for(int i=0;i<enclosureNotAdded.size();i++){
						if(enclosureNotAdded.get(i).getEnclosureId().equals(enclosurePreAdded.get(j).getEnclosureId())){
							enclosureNotAdded.remove(i);
							break;
						}
					}
				}
				//creating list of enclosure which are not previously added
				enclosureDtlVO=new RecordTypeWiseEnclosureMstVO[enclosureNotAdded.size()];
				for(int i=0;i<enclosureNotAdded.size();i++){
					enclosureDtlVO[i]=enclosureNotAdded.get(i);
				}
				
				//checklistVOArray=new RecordCheckListDtlVO[checkListDtlVO.length];
				
			}
			//fb.setPatCrNo(fb.getSelectCRNo());
			//PatientDetailVO patientDetailVo=(PatientDetailVO)essentialMap.get(InpatientConfig.INPATIENT_ADMISSION_VO);
			//patientDetailVo.setIsIpd(InpatientConfig.DESK_IPD_TILE)	;
			//fb.setDepartmentCode(patientDetailVo.getDepartmentCode());
			//fb.setPatAdmNo(patientDetailVo.getPatAdmNo());
			
			fb.setIndex(index);
			setpreviouslyAddedEnclosures(enclosureDtlVO,caseSheetId,fb,request);
			setpreviousChecklist(checkListDtlVO,caseSheetId, fb, request);
			
			WebUTIL.setAttributeInSession(request,MrdConfig.ENCLOSURE_NAMES_CASESHEET_DISPATCH_VO_ARRAY , enclosureDtlVO);
			WebUTIL.setAttributeInSession(request,MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY , checkListDtlVO);
			//WebUTIL.setAttributeInSession(request,MrdConfig.CASE_SHEET_ENCLOSURE_PRE_ADDED_VO_LIST , enclosurePreAdded);
			//WebUTIL.setAttributeInSession(request,MrdConfig.CASE_SHEET_CHECKLIST_PRE_ADDED_VO_LIST, checklistPreAdded);
			//WebUTIL.setAttributeInSession(request,InpatientConfig.INPATIENT_ADMISSION_VO , patientDetailVo);
			
			//objStatus.add(Status.TRANSINPROCESS);
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
	
	//added by swati
	//date:01-may-2019
	
	public static void getCaseSheetListToReadyADMNOWISE(CaseSheetDispatchFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//boolean flag=false;
		List<CaseSheetDtlVO> caseSheetDtlVOList=new ArrayList<CaseSheetDtlVO>();
		CaseSheetDtlVO casesheetDtlVO=null;
		try
		{
			String admno =fb.getPatAdmNo();
			caseSheetDtlVOList=CaseSheetDispatchDATA.getCaseSheetListToReadyADMNOWISE(admno,getUserVO(request));
			//flag=true
			if(caseSheetDtlVOList!=null && caseSheetDtlVOList.size()==0){
				throw new HisRecordNotFoundException("No Record Found");
			}
			
			if(caseSheetDtlVOList!=null && caseSheetDtlVOList.size()==0){
				fb.setClearmsg("kliuhkih");
			}
			
			
			
			
			setIsDelay(caseSheetDtlVOList);
			for(int i=0;i<caseSheetDtlVOList.size();i++){
				casesheetDtlVO=caseSheetDtlVOList.get(i);
				fb.setDepartmentCode(casesheetDtlVO.getDepartmentCode());
				fb.setDepartmentUnitCode(casesheetDtlVO.getDepartmentUnitCode());
				fb.setWardCodeNew(casesheetDtlVO.getWardCode());
				//fb.setWardCode(casesheetDtlVO.getWardCode());//added by swati sagar on date:15-may-2019
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
	//date:02-may-2019
	
	public static void getCaseSheetListToReadyCRNOWISE(CaseSheetDispatchFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//boolean flag=false;
		List<CaseSheetDtlVO> caseSheetDtlVOList=new ArrayList<CaseSheetDtlVO>();
		CaseSheetDtlVO casesheetDtlVO=null;
		try
		{
			String crno =fb.getStrCrNo();
			caseSheetDtlVOList=CaseSheetDispatchDATA.getCaseSheetListToReadyCRNOWISE(crno,getUserVO(request));
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
				fb.setWardCode(casesheetDtlVO.getWardCode());
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
	
	
	
	
	//get the detail of previously added checklist during the process
	//set the detail on the form
	private static void setpreviouslyAddedEnclosures(RecordTypeWiseEnclosureMstVO[] enclosureDtlVO,String caseSheetId, CaseSheetDispatchFB fb, HttpServletRequest request) {
		
		Map enclosureChecklistMap=null;
		try{
			String checkedItem="";
			enclosureChecklistMap=(Map)request.getSession().getAttribute(MrdConfig.CASESHEET_ENCLOSURE_CHECKLIST_MAP);
			if(enclosureChecklistMap!=null){
			List <RecordTypeWiseEnclosureMstVO> enclosureList=(List)((List)enclosureChecklistMap.get(caseSheetId)).get(0);
			String []noOfPages=new String[enclosureDtlVO.length];
				for(int j=0;j<enclosureDtlVO.length;j++){
					for(int i=0;i<enclosureList.size();i++){
						if(enclosureList.get(i).getEnclosureId().equals(enclosureDtlVO[j].getEnclosureId())){
							checkedItem=checkedItem+"#"+enclosureDtlVO[j].getEnclosureId();
							noOfPages[j]=enclosureList.get(i).getPages();
							break;
						}
						noOfPages[j]="";
					}
				}
				checkedItem=checkedItem.substring(1);
				fb.setPages(noOfPages);
			}	
			fb.setCheckedItem(checkedItem);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//add enclosures for the selected patient
	//and put it in map with key as casesheetid and save it in session
	public static void addEnclosures(CaseSheetDispatchFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		//Map essentialMap=null;
		List<RecordTypeWiseEnclosureMstVO> enclosureMstVOList=new ArrayList<RecordTypeWiseEnclosureMstVO>();
		RecordTypeWiseEnclosureMstVO enclosureMstVO=null;
		List<RecordCheckListDtlVO> checklistVOList=new ArrayList<RecordCheckListDtlVO>();
		RecordCheckListDtlVO recChecklistDetailVO=null;
		Map enclosureChecklistMap=new HashMap();
		List<CaseSheetDtlVO> caseSheetDtlVOList=new ArrayList<CaseSheetDtlVO>();
		CaseSheetDtlVO caseSheetDtlVO=null;
		try
		{
			enclosureChecklistMap=(Map)session.getAttribute(MrdConfig.CASESHEET_ENCLOSURE_CHECKLIST_MAP);
			caseSheetDtlVOList=(List)session.getAttribute(MrdConfig.PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY);
			if(fb.getCaseSheetId()!=null){
				for(int i=0;i<caseSheetDtlVOList.size();i++){
					if(caseSheetDtlVOList.get(i).getCaseSheetId().equals(fb.getCaseSheetId())){
						caseSheetDtlVO=caseSheetDtlVOList.get(i);
						break;
					}
				}
			}
			
			if(fb.getSelectedCheckListId()!=null){
				for(int i=0;i<fb.getSelectedCheckListId().length;i++)
				{
					recChecklistDetailVO=new RecordCheckListDtlVO();
					recChecklistDetailVO.setCheckListId(fb.getSelectedCheckListId()[i]);
					recChecklistDetailVO.setCheckListBy(MrdConfig.CHECKLIST_BY_WARD);
					if(caseSheetDtlVO.getCaseSheetType().equals(MrdConfig.CASESHEET_TYPE_GENERAL))
						recChecklistDetailVO.setRecordType(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
					else
						recChecklistDetailVO.setRecordType(MrdConfig.RECORD_TYPE_MLC_CASESHEET);
					recChecklistDetailVO.setRemarks(fb.getRemarks()[i]);
					recChecklistDetailVO.setRecordId(fb.getCaseSheetId());
					checklistVOList.add(recChecklistDetailVO);
				}
			}
			
			if(fb.getSelectedEnclosureId()!=null){
				for(int i=0;i<fb.getSelectedEnclosureId().length;i++)
				{
					enclosureMstVO=new RecordTypeWiseEnclosureMstVO();
					enclosureMstVO.setEnclosureId(fb.getSelectedEnclosureId()[i]);
					if(caseSheetDtlVO.getCaseSheetType().equals(MrdConfig.CASESHEET_TYPE_GENERAL))
						enclosureMstVO.setRecordTypeId(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
					else
						enclosureMstVO.setRecordTypeId(MrdConfig.RECORD_TYPE_MLC_CASESHEET);
					enclosureMstVO.setPages(fb.getPages()[i]);
					enclosureMstVO.setRecordId(fb.getCaseSheetId());
					enclosureMstVOList.add(enclosureMstVO);
				}
			}
			
			List list=new ArrayList();
			list.add(enclosureMstVOList);
			list.add(checklistVOList);
			
			if(enclosureChecklistMap==null){
				enclosureChecklistMap=new HashMap();
				enclosureChecklistMap.put(fb.getCaseSheetId(),list);
			}	
			else{
				enclosureChecklistMap.put(fb.getCaseSheetId(), list);
			}
			
			WebUTIL.setAttributeInSession(request,MrdConfig.CASESHEET_ENCLOSURE_CHECKLIST_MAP , enclosureChecklistMap);
			
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
	
	
	/**
	 * Save the case sheet dispatch detail
	 * the enclosure detail is saved in enclosure_summary table
	 * the checklist detail is saved in checklist_dtl
	 * the case sheet status of the casesheet_dtl table is updated.
	 * a new record is saved into record_dispatch_dtl table
	 * if the case sheet has been delayed then save the delay reason and set the status as DELAY_SEND
	 * @param _fb
	 * @param request
	 * @return
	 */
	
	public static boolean saveCaseSheetDispatch(CaseSheetDispatchFB _fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		RecordDispatchDtlVO []dispatchVO = null;
		CaseSheetDtlVO [] caseSheetDtlVOArray=null;
		List<CaseSheetDtlVO> caseSheetDtlVOList=new ArrayList<CaseSheetDtlVO>();
		List<RecordCheckListDtlVO> checklistVOList=new ArrayList<RecordCheckListDtlVO>();
		//RecordCheckListDtlVO recChecklistDetailVO=null;
		try
		{
			UserVO userVO = getUserVO(request);
			Map enclosureMap=(Map)session.getAttribute(MrdConfig.CASESHEET_ENCLOSURE_CHECKLIST_MAP);
			caseSheetDtlVOList=(List)session.getAttribute(MrdConfig.PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY); 
			
			//create dispatchVo for selected patients.
			if(_fb.getSelectCRNo()!=null){
				dispatchVO=new RecordDispatchDtlVO[_fb.getSelectCRNo().length];
				for(int i=0;i<_fb.getSelectCRNo().length;i++){
					for(int j=0;j<caseSheetDtlVOList.size();j++){
						String []array=_fb.getSelectCRNo()[i].split("@");
						//String crNo=array[0];
						String caseSheetId=array[1];
						if(caseSheetId.equals(caseSheetDtlVOList.get(j).getCaseSheetId())){
							dispatchVO[i]=new RecordDispatchDtlVO();
							HelperMethods.populate(dispatchVO[i], caseSheetDtlVOList.get(j));
							/*dispatchVO[i].setPatCrNo(crNo);
							dispatchVO[i].setEpisodeCode(array[3]);
							dispatchVO[i].setEpisodeVisitNo(array[4]);
							dispatchVO[i].setPatAdmNo(caseSheetId.substring(0,11));*/
							dispatchVO[i].setRecordId(caseSheetId);
							dispatchVO[i].setRecordDesc(caseSheetId);
							String unitCode = _fb.getDepartmentUnitCode();
							//dispatchVO[i].setDeptUnitCode(unitCode); -------
							//dispatchVO[i].setDeptCode(unitCode.substring(0,3));
							dispatchVO[i].setHandoverTo(_fb.getHandoverTo());
							if(caseSheetDtlVOList.get(j).getCaseSheetType().equals(MrdConfig.CASESHEET_TYPE_GENERAL))
								dispatchVO[i].setRecordType(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
							else
								dispatchVO[i].setRecordType(MrdConfig.RECORD_TYPE_MLC_CASESHEET);
							dispatchVO[i].setRecordStatus(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_READY_TO_DIPATCH);
							//if the case sheet has been delayed
							if(caseSheetDtlVOList.get(j).getIsDelay().equals("1")){
								dispatchVO[i].setRecordStatus(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_DELAY_SEND);
								dispatchVO[i].setDelayReason(_fb.getDelayReason());
							}	
							dispatchVO[i].setEntryMode(MrdConfig.CERTIFICATE_DISPATCH_ENTRY_MODE_ONLINE);
							setTotalNoOfPages(dispatchVO[i],enclosureMap);
						}
					}	
				}
			}
			
			//create case sheet detail VO for selected patients.
			if(_fb.getSelectCRNo()!=null){
				caseSheetDtlVOArray=new CaseSheetDtlVO[_fb.getSelectCRNo().length];
				for(int i=0;i<caseSheetDtlVOList.size();i++){
					for(int j=0;j<_fb.getSelectCRNo().length;j++){
						String []array=_fb.getSelectCRNo()[j].split("@");
						String caseSheetId=array[1];
						if(caseSheetDtlVOList.get(i).getCaseSheetId().equals(caseSheetId)){
							caseSheetDtlVOArray[j]=caseSheetDtlVOList.get(i);
							//if the case sheet has not been delayed
							caseSheetDtlVOArray[j].setCaseSheetStatus(MrdConfig.CASE_SHEET_READY_TO_DISPATCH);
							//if the case sheet has been delayed
							//set the case sheet status as DELAY_SEND
							if(caseSheetDtlVOArray[j].getIsDelay().equals("1"))
								caseSheetDtlVOArray[j].setCaseSheetStatus(MrdConfig.CASE_SHEET_DELAY_SEND);
						}
					}
				}
			}
			
			//checklist detail
			checklistVOList=(List)session.getAttribute(MrdConfig.SELECTED_CHECKLIST_VO_LIST);
		
			CaseSheetDispatchDATA.saveCaseSheetDispatch(dispatchVO,caseSheetDtlVOArray,enclosureMap,checklistVOList,userVO);
														
			objStatus.add(Status.DONE,"Record Saved Successfully","");
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
		return true;
	}
	
	//set total no of pages of enclosure in dispatchVO
	private static void setTotalNoOfPages(
			RecordDispatchDtlVO recordDispatchDtlVO, Map enclosureMap) {
		RecordTypeWiseEnclosureMstVO enclosureMstVO=null;
		List list=(List)enclosureMap.get(recordDispatchDtlVO.getRecordId());
		try{
			int totalRecordPages=0;
			List<RecordTypeWiseEnclosureMstVO> enclosureVOList=(List)list.get(0);
			for(int i=0;i<enclosureVOList.size();i++){
				enclosureMstVO=enclosureVOList.get(i);
				totalRecordPages=totalRecordPages+Integer.parseInt(enclosureMstVO.getPages());
			}
			recordDispatchDtlVO.setRecordPages(String.valueOf(totalRecordPages));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//get all checklist based on recordType
	public static boolean getChecklistForCaseSheet(CaseSheetDispatchFB _fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(request);
			//String recordType=MrdConfig.RECORD_TYPE_GENERAL_CASESHEET;
			RecordTypeCheckListMstVO checklistVOArray[]= CaseSheetDispatchDATA.getCaseSheetChecklist(MrdConfig.CHECKLIST_MODE_DISPATCH_LEVEL,userVO);
			setpreviousChecklist(checklistVOArray,null,_fb,request);
			WebUTIL.setAttributeInSession(request,MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY , checklistVOArray);
			objStatus.add(Status.TRANSINPROCESS);
		}
		
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
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
		return true;
	}
	
	
	//add selected checklist in list and save it in session
	public static boolean addChecklist(CaseSheetDispatchFB _fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		List<RecordCheckListDtlVO> checklistVOList=null;
		RecordCheckListDtlVO recChecklistDetailVO=null;
		try
		{
			//UserVO userVO = getUserVO(request);
			if(_fb.getSelectedCheckListId()!=null){
				checklistVOList=new ArrayList<RecordCheckListDtlVO>();
				for(int i=0;i<_fb.getSelectedCheckListId().length;i++)
				{
					recChecklistDetailVO=new RecordCheckListDtlVO();
					recChecklistDetailVO.setCheckListId(_fb.getSelectedCheckListId()[i]);
					recChecklistDetailVO.setCheckListBy(MrdConfig.CHECKLIST_BY_WARD);
					//if(_fb.getCaseSheetType().equals(MrdConfig.CASESHEET_TYPE_GENERAL))
						recChecklistDetailVO.setRecordType(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
					//else
					//	recChecklistDetailVO.setRecordType(MrdConfig.RECORD_TYPE_MLC_CASESHEET);
					recChecklistDetailVO.setRemarks(_fb.getRemarks()[i]);
					//recChecklistDetailVO.setRecordId(_fb.getCaseSheetId());
					checklistVOList.add(recChecklistDetailVO);
				}
			}
			
			Map enclosureChecklistMap=(Map)request.getSession().getAttribute(MrdConfig.CASESHEET_ENCLOSURE_CHECKLIST_MAP);
			if(enclosureChecklistMap!=null){
				Set keySet=enclosureChecklistMap.keySet();
				Iterator itr=keySet.iterator();
				while(itr.hasNext()){
					List list=(List)enclosureChecklistMap.get(itr.next());
					if(list==null){
						list=new ArrayList();
						list.add(1, checklistVOList);
					}
					else
						list.add(1, checklistVOList);
				}
			}
			WebUTIL.setAttributeInSession(request,MrdConfig.SELECTED_CHECKLIST_VO_LIST , checklistVOList);
			objStatus.add(Status.TRANSINPROCESS);
		}
		
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Inside Exception");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}

	//set the previously added checklist detail on the form
	private static void setpreviousChecklist(
			RecordTypeCheckListMstVO[] checklistVOArray,
			String caseSheetId, CaseSheetDispatchFB _fb, HttpServletRequest request) {
		List<RecordCheckListDtlVO> selectedChecklist=null;
		Map enclosureChecklistMap=null;
		try{
			enclosureChecklistMap=(Map)request.getSession().getAttribute(MrdConfig.CASESHEET_ENCLOSURE_CHECKLIST_MAP);
			if(caseSheetId!=null && enclosureChecklistMap!=null)
				selectedChecklist=(List)((List)enclosureChecklistMap.get(caseSheetId)).get(1);
			else	
				selectedChecklist=(List)request.getSession().getAttribute(MrdConfig.SELECTED_CHECKLIST_VO_LIST);
			String checkedItem="";
			String []remarks=new String[checklistVOArray.length];
			if(selectedChecklist!=null){
				for(int j=0;j<checklistVOArray.length;j++){
					for(int i=0;i<selectedChecklist.size();i++){
						if(selectedChecklist.get(i).getCheckListId().equals(checklistVOArray[j].getChecklistId())){
							checkedItem=checkedItem+"#"+checklistVOArray[j].getChecklistId();
							remarks[j]=selectedChecklist.get(i).getRemarks();
							break;
						}
						remarks[j]="";
					}
				}
				checkedItem=checkedItem.substring(1);
				_fb.setRemarks(remarks);
			}	
			_fb.setCheckedChecklist(checkedItem);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	
}
