package mrd.transaction.controller.utl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.ReceiveCertificateInitialDATA;
import mrd.transaction.controller.fb.ReceiveCertificateInitialFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;

public class ReceiveCertificateInitialUTL extends ControllerUTIL
{
	
	public static boolean getMrdList(ReceiveCertificateInitialFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		
		try
		{
			List lstMrd=ReceiveCertificateInitialDATA.getMrdList(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.LIST_ALL_MRD_USER_BASED, lstMrd);
			if(lstMrd.size()==0)
			{
				Entry entry = (Entry) lstMrd.get(0);
				String mrdCode = entry.getValue();
				fb.setMrdCode(mrdCode);
				fb.setIsMrdListOne(MrdConfig.YES);
				flag = true;
			}	
			else
			{
				fb.setIsMrdListOne(MrdConfig.NO);
				flag = false;
			}	
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
	
	public static boolean getRecordType(ReceiveCertificateInitialFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = false;
		
		try
		{
			List lstRecordType=ReceiveCertificateInitialDATA.getRecordTypeBasedOnMrd(fb.getMrdCode(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_TYPE, lstRecordType);
			if(lstRecordType.size()==1)
			{
				Entry entry = (Entry) lstRecordType.get(0);
				String recordTypeCode = entry.getValue();
				String recordTypeName=entry.getLabel();
				fb.setRecordType(recordTypeCode);
				fb.setRecordTypeName(recordTypeName);
				System.out.println("recordtype:::::"+recordTypeName);
				flag = true;
			}	
			else
				flag = false;
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
	
	public static void setRecordType(ReceiveCertificateInitialFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String recordTypeName="";
		
		String admno="";
		
		String crNo="";
		
		try
		{
			
			admno=fb.getPatAdmNo();
			crNo=fb.getStrCrNo();
			if(admno.equals("") && crNo.equals("")){
			List lstRecordType=(List)session.getAttribute(MrdConfig.RECORD_TYPE);
			if(lstRecordType.size()!=0){
			for(int i=0;i<lstRecordType.size();i++)
			{
				Entry entry = (Entry) lstRecordType.get(i);
				if(fb.getRecordType().equals(entry.getValue()))
				{	
					recordTypeName=entry.getLabel();
					break;
				}	
			}
			}
			else{
				objStatus.add(Status.UNSUCESSFULL, "", "No Record Found");
				
			}
				
			
			}
			
			String recordType=fb.getRecordType();
			String mrdCode=fb.getMrdCode();
			System.out.println("RECORD_TYPE_FOR_ACCEPTANCE::::"+recordType);
			WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_TYPE_FOR_ACCEPTANCE, recordType);
			WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_TYPE_NAME_FOR_ACCEPTANCE, recordTypeName);
			WebUTIL.setAttributeInSession(request, MrdConfig.MRD_CODE_FOR_ACCEPTANCE, mrdCode);
			WebUTIL.setAttributeInSession(request, MrdConfig.ADM_NO_FOR_ACCEPTANCE, fb.getPatAdmNo());//ADDED BY SWATI ON DATE 10-MAY-2019S
			WebUTIL.setAttributeInSession(request, MrdConfig.CR_NO_FOR_ACCEPTANCE, fb.getStrCrNo());//ADDED BY SWATI ON DATE 13-MAY-2019S
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
