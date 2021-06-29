package mrd.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.RecordLostInMrdDATA;
import mrd.transaction.controller.fb.RecordLostInMrdFB;
import mrd.vo.CaseSheetEnquiryVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordLostFoundDtlVO;

public class RecordLostInMrdUTL extends ControllerUTIL
{
	
	public static void getLostRecordReportedByList(RecordLostInMrdFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		
		try
		{
			setSysdate(request);
			String sys=(String)session.getAttribute(Config.SYSDATE);
			String time=sys.split(" ")[1];
			fb.setHiddenTimeHr(time.split(":")[0]);
			fb.setHiddenTimeMin(time.split(":")[1]);
			
			List lstReportedBy=RecordLostInMrdDATA.getLostRecordReportedByList(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.LIST_LOST_RECORD_REPORTED_BY_EMP, lstReportedBy);
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
	
	public static void openPopupForSearchRecord(RecordLostInMrdFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			List lstRecordType=RecordLostInMrdDATA.getListAllRecordType(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_TYPE, lstRecordType);
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
	
	public static void searchRecord(RecordLostInMrdFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		MrdRecordDtlVO[] commoncaseSheetVO=null;
		CaseSheetEnquiryVO caseSheetEnqVO=new CaseSheetEnquiryVO();
		List<MrdRecordDtlVO> lstSearchVO=new ArrayList<MrdRecordDtlVO>();
		
		try
		{
			HelperMethods.populate(caseSheetEnqVO,fb);
			commoncaseSheetVO=RecordLostInMrdDATA.searchLostRecord(caseSheetEnqVO,getUserVO(request));
			for(int i=0;i<commoncaseSheetVO.length;i++)
			{
				lstSearchVO.add(commoncaseSheetVO[i]);
			}
			
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_CASESHEET_SEARCHED_RECORD_VO, lstSearchVO);
			if(lstSearchVO.size()==0)
				objStatus.add(Status.TRANSINPROCESS,"","No Record Found Matching The Search Criteria");
			else
				objStatus.add(Status.TRANSINPROCESS);
			
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
	
	public static void saveMrdRecordLostDetail(RecordLostInMrdFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		MrdRecordLostFoundDtlVO mrdRecordLostVO=new MrdRecordLostFoundDtlVO();
		
		try
		{
			HelperMethods.populate(mrdRecordLostVO, fb);
			mrdRecordLostVO.setLastSeenDateTime(fb.getLastSeenDate()+" "+fb.getLastSeenTimeHr()+":"+fb.getLastSeenTimeMin());
			mrdRecordLostVO.setLostType(MrdConfig.RECORD_LOST_TYPE_COMPLETE);
			
			RecordLostInMrdDATA.saveMrdRecordLostDetail(mrdRecordLostVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Lost Detail Saved Successfully");
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
}
