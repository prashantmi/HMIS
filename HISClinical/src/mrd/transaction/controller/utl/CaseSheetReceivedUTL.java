package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.CaseSheetDispatchVO;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.RackShelfMstVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.CaseSheetReceivedDATA;
import mrd.transaction.controller.fb.CaseSheetReceivedFB;
/**
 * @author Administrator
 *
 */
public class CaseSheetReceivedUTL extends ControllerUTIL
{
	/** Getting the List Of Case Sheet
	 * @param fb
	 * @param request
	 */
	public static void getEssentialForCaseSheetAcceptence(CaseSheetReceivedFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		Map essentialMap=new HashMap();
	//	List rackList=new ArrayList();
		
		try
		{
			essentialMap=CaseSheetReceivedDATA.getEssentialForCaseSheetAcceptence(getUserVO(request));
			WebUTIL.setMapInSession(essentialMap, request);
			objStatus.add(Status.LIST);
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
	
	
	/** Getting the List Of Shelf On Basis of Rack
	 * @param fb
	 * @param request
	 * @param _resp
	 */
	public static void getShelf(CaseSheetReceivedFB fb, HttpServletRequest request,HttpServletResponse _resp)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List selfList=new ArrayList();
		//String result="";
		String rackId=request.getParameter("rackId");
		String resp="";
		
		try
		{
			CertificateIssueDtlVO _certificateIssueDtlVO = new CertificateIssueDtlVO();
			
			_certificateIssueDtlVO.setRackId(rackId);
		//	selfList=CaseSheetReceivedDATA.getSelf(_certificateIssueDtlVO,getUserVO(request));
			
			RackShelfMstVO[] rackShelfMstVO=(RackShelfMstVO[])session.getAttribute(MrdConfig.ALL_RACK_SHELF_LIST_VO);
			for(int i=0;i<rackShelfMstVO.length;i++)
			{
				if(rackId.equals(rackShelfMstVO[i].getRackId()))
				{
					Entry ent=new Entry();
					ent.setValue(rackShelfMstVO[i].getRackShelfId());
					ent.setLabel(rackShelfMstVO[i].getShelfLabel());
					selfList.add(ent);
				}
			}
			
			Iterator itr = selfList.iterator();
			while(itr.hasNext())
			{
				Entry itrSelfList=(Entry)itr.next();
			//	result=result + itrSelfList.getValue() + "$" + itrSelfList.getLabel() + "$";	
				resp = resp + "<option value='" + itrSelfList.getValue() + "'>" + itrSelfList.getLabel() + "</option>";
			}
		//	if(!result.equals(""))	result = result.substring(0,result.length()-1);
								
			try
			{
				PrintWriter writer = _resp.getWriter();
				System.out.println("resp "+resp);
				writer.write(resp);	
				writer.flush();
				writer.close();
			}												
			catch (IOException e)				
			{								
				e.printStackTrace();					
			}
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
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
	
	public static void getAllEnclosureDetails(CaseSheetReceivedFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session = WebUTIL.getSession(request);
		Map essentialMap=null;
		try
		{
			String recordId=fb.getRecordId().split("#")[0];
			String patCrNo=fb.getRecordId().split("#")[1];
			fb.setPatCrNo(patCrNo);
			essentialMap=CaseSheetReceivedDATA.getAllEnclosureChecklistsByRecordId(recordId,getUserVO(request));
			List<RecordTypeWiseEnclosureMstVO> enclosureDtlVOList=(List)essentialMap.get(MrdConfig.CASE_SHEET_ENCLOSURE_LIST);
			List<RecordTypeCheckListMstVO> checkListDtlVOList=(List)essentialMap.get(MrdConfig.CASE_SHEET_CHECKLIST);
			//PatientDetailVO patientDetailVo=(PatientDetailVO)essentialMap.get(InpatientConfig.INPATIENT_ADMISSION_VO);
			//patientDetailVo.setIsIpd(InpatientConfig.DESK_IPD_TILE)	;
			//fb.setDepartmentCode(patientDetailVo.getDepartmentCode());
			//fb.setPatAdmNo(patientDetailVo.getPatAdmNo());
			
			WebUTIL.setMapInSession(essentialMap, request);
			WebUTIL.setAttributeInSession(request,MrdConfig.ENCLOSURE_NAMES_CASESHEET_DISPATCH_VO_ARRAY , enclosureDtlVOList);
			WebUTIL.setAttributeInSession(request,MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY , checkListDtlVOList);
			
			
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
	
	
	public static void saveCaseSheetAcceptence(CaseSheetReceivedFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session = WebUTIL.getSession(request);
		//Map essentialMap=null;
		CaseSheetDispatchVO caseSheetDispatchVO=new CaseSheetDispatchVO();
		RecordTypeWiseEnclosureMstVO []recordTypeEnclosureVOArray=null;
		RecordTypeCheckListMstVO[] recChecklistDetailVO=null;
		MrdRecordDtlVO mrdRecordDtlVO=new MrdRecordDtlVO(); 
		try
		{
			String recordId=fb.getRecordId().split("#")[0];
			String patCrNo=fb.getRecordId().split("#")[1];
//			String slNo=fb.getRecordId().split("#")[2];
			caseSheetDispatchVO.setRecordId(recordId);
//			caseSheetDispatchVO.setSlNo(slNo);
			caseSheetDispatchVO.setRecordType(MrdConfig.RECORD_TYPE_CASESHEET);
			caseSheetDispatchVO.setCaseSheetId(fb.getRecordId().split("#")[3]);
			if(fb.getIsAccept().equals(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_RETURN)){
				caseSheetDispatchVO.setRecordStatus(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_RETURN);
				caseSheetDispatchVO.setReturnReason(fb.getReturnReason());
			}
			else{
				HelperMethods.populate(mrdRecordDtlVO, fb);
				mrdRecordDtlVO.setRecordId(recordId);
				mrdRecordDtlVO.setRecordDesc(recordId);
				mrdRecordDtlVO.setRecordType(MrdConfig.RECORD_TYPE_CASESHEET);
				//mrdRecordDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_RECEIVED_RECORD_STATUS_IN_MRD);
				mrdRecordDtlVO.setRemarks("");
				mrdRecordDtlVO.setPatCrNo(patCrNo);
				mrdRecordDtlVO.setPatAdmNo(recordId);
				
				recordTypeEnclosureVOArray=new RecordTypeWiseEnclosureMstVO[fb.getSelectedEnclosureId().length];
				for(int i=0;i<fb.getSelectedEnclosureId().length;i++){
					recordTypeEnclosureVOArray[i]=new RecordTypeWiseEnclosureMstVO();
					recordTypeEnclosureVOArray[i].setEnclosureId(fb.getSelectedEnclosureId()[i].split("#")[0]);
					recordTypeEnclosureVOArray[i].setSlNo(fb.getSelectedEnclosureId()[i].split("#")[1]);
					recordTypeEnclosureVOArray[i].setRecordTypeId(MrdConfig.RECORD_TYPE_CASESHEET);
					recordTypeEnclosureVOArray[i].setRecordId(recordId);
					recordTypeEnclosureVOArray[i].setVerifiedByMrd(fb.getReceivedPages()[i]);
				}
				//caseSheetDispatchVO.setRecordStatus(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_RECEIVED);
				recChecklistDetailVO=new RecordTypeCheckListMstVO[fb.getSelectedCheckListId().length];
				for(int i=0;i<recChecklistDetailVO.length;i++){
					recChecklistDetailVO[i]=new RecordTypeCheckListMstVO();
					recChecklistDetailVO[i].setChecklistId(fb.getSelectedCheckListId()[i]);
					recChecklistDetailVO[i].setRecordTypeId(MrdConfig.RECORD_TYPE_CASESHEET);
					recChecklistDetailVO[i].setRecordId(recordId);
					//recChecklistDetailVO[i].setCheckListBy(MrdConfig.CHECKLIST_BY_PEON);
					recChecklistDetailVO[i].setRemarks(fb.getCheckListremarks()[i]);
				}
				
			}
			CaseSheetReceivedDATA.saveCaseSheetAcceptence(caseSheetDispatchVO,fb.getIsAccept(),recordTypeEnclosureVOArray,mrdRecordDtlVO,recChecklistDetailVO,getUserVO(request));
			
			objStatus.add(Status.LIST);
			
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
