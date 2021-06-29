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
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.UserVO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mrd.MrdConfig;
import mrd.transaction.controller.data.CaseSheetApprovalDATA;
import mrd.transaction.controller.fb.CaseSheetApprovalFB;

public class CaseSheetApprovalUTL extends ControllerUTIL {
	
	public static boolean getDeptUnitList(CaseSheetApprovalFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		
		try
		{
			List lstUnit=CaseSheetApprovalDATA.getDeptUnitList(getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_DEPT_UNIT_LIST ,lstUnit);
		
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
	
	public static boolean getWardOnBasisOfUnitCode(CaseSheetApprovalFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		List listWard = new ArrayList();
		//HttpSession session = request.getSession();
		
		try
		{
			String unitCode = fb.getDepartmentUnitCode();
			
			listWard=CaseSheetApprovalDATA.getWardOnBasisOfUnitCode(unitCode,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT, listWard);
			fb.setWardCode("-1");
					
			//objStatus.add(Status.INPROCESS);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT, listWard);
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
	 * get the detail of the case sheet of the patient by cr no. whose case sheet has been delayed send
	 * @param fb
	 * @param request
	 */
	public static boolean getPatientCaseSheetDtlByCrNo(CaseSheetApprovalFB fb,
			HttpServletRequest request) {
		Status objStatus = new Status();
		RecordDispatchDtlVO []recordDispatchVOArray=null;
		boolean flag=false;
		try
		{
			RecordDispatchDtlVO recordDispatchVO=new RecordDispatchDtlVO();
			recordDispatchVO.setRecordType(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
			recordDispatchVO.setPatCrNo(fb.getPatCrNo());
			recordDispatchVO.setRecordStatus(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_DELAY_SEND);
			recordDispatchVOArray=CaseSheetApprovalDATA.getRecordListByPatCrNo(recordDispatchVO,getUserVO(request));
			flag=true;
			WebUTIL.setAttributeInSession(request,MrdConfig.RECORD_DISPATCH_VO_ARRAY,recordDispatchVOArray);
			objStatus.add(Status.RECORDFOUND);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW,"", e.getMessage());
			WebUTIL.setAttributeInSession(request,MrdConfig.RECORD_DISPATCH_VO_ARRAY,null);
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
	 * get the list of the case sheet which are delay send 
	 * @param fb
	 * @param request
	 */
	
	public static void getRecordDispatchList(CaseSheetApprovalFB fb,
			HttpServletRequest request) {
		Status objStatus = new Status();
		RecordDispatchDtlVO []recordDispatchVOArray=null;
		try
		{
			String deptUnitCode = fb.getDepartmentUnitCode();
			String wardCode = fb.getWardCode();
			RecordDispatchDtlVO recordDispatchVO=new RecordDispatchDtlVO();
			recordDispatchVO.setDeptUnitCode(deptUnitCode);
			recordDispatchVO.setWardCode(wardCode);
			recordDispatchVO.setRecordType(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
			recordDispatchVO.setRecordStatus(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_DELAY_SEND);
			recordDispatchVOArray=CaseSheetApprovalDATA.getRecordDispatchList(recordDispatchVO,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.RECORD_DISPATCH_VO_ARRAY,recordDispatchVOArray);
			
			objStatus.add(Status.RECORDFOUND);
			//objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			//objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(request,MrdConfig.RECORD_DISPATCH_VO_ARRAY,null);
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

	
	/**
	 * the record dispatch dtl table is updated with the approval detail
	 * and the record status is updated to READY_TO_DISPATCH and 
	 * the case sheet status is updated as READY_TO_DISPATCH
	 * @param fb
	 * @param request
	 */
	public static void saveCaseSheetApprovalDetail(CaseSheetApprovalFB fb,HttpServletRequest request) {
		Status objStatus = new Status();
		RecordDispatchDtlVO []recordDispatchVOArray=null;
		List <RecordDispatchDtlVO> recordDispatchVOList=null;
		List <CaseSheetDtlVO> caseSheetDtlVOList=null;
		RecordDispatchDtlVO recordDispatchVO=null;
		CaseSheetDtlVO caseSheetDtlVO=null;
		UserVO userVO=getUserVO(request);
		try
		{
			recordDispatchVOArray=(RecordDispatchDtlVO [])request.getSession().getAttribute(MrdConfig.RECORD_DISPATCH_VO_ARRAY);
			if(fb.getSelectedRecord()!=null){
				recordDispatchVOList=new ArrayList<RecordDispatchDtlVO>();
				caseSheetDtlVOList =new ArrayList<CaseSheetDtlVO>();
				for(int i=0;i<fb.getSelectedRecord().length;i++){
					recordDispatchVO=recordDispatchVOArray[Integer.parseInt(fb.getSelectedRecord()[i])];
					recordDispatchVO.setRecordStatus(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_READY_TO_DIPATCH);
					recordDispatchVO.setApprovedRemarks(fb.getApprovalRemarks());
					recordDispatchVO.setApprovedSeatId(userVO.getSeatId());
					recordDispatchVO.setApprovedEmpNo(userVO.getUserEmpID());
					recordDispatchVOList.add(recordDispatchVO);
					caseSheetDtlVO=new CaseSheetDtlVO();
					HelperMethods.populate(caseSheetDtlVO, recordDispatchVO);
					caseSheetDtlVO.setCaseSheetId(recordDispatchVO.getRecordId());
					caseSheetDtlVO.setCaseSheetStatus(MrdConfig.CASE_SHEET_READY_TO_DISPATCH);
					caseSheetDtlVOList.add(caseSheetDtlVO);
				}
			}
			
			CaseSheetApprovalDATA.saveCaseSheetApprovalDetail(recordDispatchVOList,caseSheetDtlVOList,userVO);
			
			objStatus.add(Status.DONE,"","Record Saved Successfully");
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
	
	public static void getPatientCaseSheetDtlByAdmNo(CaseSheetApprovalFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		RecordDispatchDtlVO[] recordDispatchVOArray = null;
		boolean flag = false;
		try
		{
			RecordDispatchDtlVO recordDispatchVO = new RecordDispatchDtlVO();
			recordDispatchVO.setRecordType(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
			recordDispatchVO.setPatCrNo(fb.getPatCrNo());
			recordDispatchVO.setPatAdmNo(fb.getPatAdmNo());
			// recordDispatchVO.setRecordStatus(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_READY_TO_DIPATCH);
			
			recordDispatchVO.setRecordStatus(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_DELAY_SEND);
			
			recordDispatchVOArray = CaseSheetApprovalDATA.getRecordListByPatAdmNo(recordDispatchVO, getUserVO(request));
		//	getEssentialForCaseSheetHandOver(recordDispatchVOArray[0].getDeptUnitCode(), recordDispatchVOArray[0].getWardCode(), request);
			fb.setIsAccept("1");
			//flag = true;
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

		
	}
		
}
