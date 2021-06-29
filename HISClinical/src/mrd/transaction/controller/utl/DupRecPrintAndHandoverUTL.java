/* 
## Copyright Information		: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Anant Patel 
 ## Module Name					: MRD
 ## Process/Database Object Name: Duplicate Record Printing And Handover process
 ## Purpose						: Duplicate Record Printing And Handover process
 ## Date of Creation			: 19 Jan 2015
 ## Modification Log			:				
 ##		Modify Date				:  
 ##		Reason	(CR/PRS)		:  
 ##		Modify By				: 

*/


package mrd.transaction.controller.utl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.DupRecPrintAndHandoverDATA;
import mrd.transaction.controller.data.MedicalCertificateDATA;
import mrd.transaction.controller.fb.DuplicateRecordPrintReqFB;
import mrd.transaction.controller.fb.MedicalCertificateFB;
import mrd.transaction.dao.DupRecPrintAndHandoverDAO;
import mrd.vo.DupRecPrintReqVO;
import hisglobal.backutil.HelperMethods;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

public class DupRecPrintAndHandoverUTL extends ControllerUTIL {

	public static boolean getDupRecPrintAndHandoverList(DuplicateRecordPrintReqFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		
		try
		{
			setSysdate(request);
			
			Map map=DupRecPrintAndHandoverDATA.getDupRecPrintAndHandoverDtl(getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(),"");
		
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
	
	public static boolean getDupRecPrintAndHandoverDtl(DuplicateRecordPrintReqFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		String billNoQty="";
		HttpSession session = request.getSession();
		
		try
		{
			DupRecPrintReqVO[] dupRecPrintReqVOList= (DupRecPrintReqVO[])session.getAttribute(MrdConfig.DUPLICATE_RECORD_PRINTING_AND_HANDOVER);
			
			mrd.vo.DupRecPrintReqVO dupRecPrintReqVO=new mrd.vo.DupRecPrintReqVO(); 
			setSysdate(request);
			String recordDtl[]=fb.getSelectRecord().split("@");
			
			fb.setPatName(recordDtl[0]);
			fb.setRecordType(recordDtl[1]);
			fb.setEntryDate(recordDtl[2]);
			fb.setRecordDesc(recordDtl[3]);
			fb.setApprovedBy(recordDtl[4]);
			fb.setPatCrNo(recordDtl[5]);
			fb.setRemarks(recordDtl[6]);
			fb.setReqReason(recordDtl[7]);
			fb.setRequestByName(recordDtl[8]);
			fb.setReqType(recordDtl[9]);
			fb.setPatAge(recordDtl[10]);
			fb.setPatContactNo(recordDtl[11]);
			fb.setPatGenderCode(recordDtl[12]);
			fb.setRequestBy(recordDtl[13]);
			fb.setReqNo(recordDtl[14]);
			fb.setHospitalCode(recordDtl[15]);
			
			HelperMethods.populate(dupRecPrintReqVO, fb);
			Map map=DupRecPrintAndHandoverDATA.getDuplicateRecordHandoverDtl(dupRecPrintReqVO,getUserVO(request));
			billNoQty=DupRecPrintAndHandoverDATA.getBillNoDtl(dupRecPrintReqVO,getUserVO(request));
			fb.setBillNo(billNoQty.replace("^", "#").split("#")[0]);
			fb.setQuantity(billNoQty.replace("^", "#").split("#")[1]);
			System.out.println("billNo:::::"+fb.getBillNo()+"qty:::"+fb.getQuantity());
			WebUTIL.setMapInSession(map, request);
			//objStatus.add(Status.NEW);
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
	
	public static boolean saveDuplicateCertificateIssueDtl(DuplicateRecordPrintReqFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		HttpSession session = request.getSession();
		
		try
		{
			mrd.vo.DupRecPrintReqVO dupRecPrintReqVO=new mrd.vo.DupRecPrintReqVO();
			HelperMethods.populate(dupRecPrintReqVO, fb);
			setSysdate(request);
			DupRecPrintAndHandoverDATA.saveDuplicateRecordPrintHAndover(dupRecPrintReqVO,getUserVO(request));
			//objStatus.add(Status.NEW);\
			objStatus.add(Status.DONE,"","Record Added Successfully");
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

}
