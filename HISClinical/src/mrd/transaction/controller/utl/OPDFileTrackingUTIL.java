package mrd.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mrd.MrdConfig;
import mrd.transaction.controller.data.OPDFileTrackingDATA;
import mrd.transaction.controller.fb.OPDFileTrackingFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;

	public class OPDFileTrackingUTIL extends ControllerUTIL
	{
		
		public static boolean getMrdList(OPDFileTrackingFB fb,HttpServletRequest request)
		{
			Status objStatus = new Status();
			boolean flag = true;
			
			try
			{
				List lstMrd=OPDFileTrackingDATA.getMrdBasedOnRecordType(MrdConfig.RECORD_TYPE_OPD_FILE,getUserVO(request));
				WebUTIL.setAttributeInSession(request, MrdConfig.LIST_ALL_OPD_FILE_MRD_USER_BASED, lstMrd);
				if(lstMrd.size()==1)
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
		
		
		
		public static void setDetails(OPDFileTrackingFB fb,HttpServletRequest request)
		{
			Status objStatus = new Status();
			HttpSession session=request.getSession();
			String mrdName="";
			
			try
			{
				List mrdList=(ArrayList)session.getAttribute(MrdConfig.LIST_ALL_OPD_FILE_MRD_USER_BASED);
				for(int i=0;i<mrdList.size();i++)
				{
					Entry entry = (Entry) mrdList.get(i);
					if(fb.getMrdCode().equals(entry.getValue()))
					{	
						mrdName=entry.getLabel();
						break;
					}	
				}
			String mrdCode=fb.getMrdCode();
			WebUTIL.setAttributeInSession(request, MrdConfig.MRD_NAME_FOR_TRACKING, mrdName);
			WebUTIL.setAttributeInSession(request, MrdConfig.MRD_CODE_FOR_TRACKING, mrdCode);
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

