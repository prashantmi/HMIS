package opd.master.controller.util;

import javax.servlet.http.HttpServletRequest;

import opd.master.controller.data.ImagePointerMasterDATA;
import opd.master.controller.fb.ImagePointerMasterFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ImagePointerMasterVO;
import hisglobal.vo.UserVO;

public class ImagePointerMasterUTIL extends ControllerUTIL
{
	public static void saveDetail(ImagePointerMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		ImagePointerMasterVO imagePointerMasterVO=new ImagePointerMasterVO();
		try
		{
			
			HelperMethods.populate(imagePointerMasterVO, _fb);
			ImagePointerMasterDATA.saveDetail(imagePointerMasterVO,userVO);
			objStatus.add(Status.DONE,"Record Saved Successfully","");		
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void getModifyDetail(ImagePointerMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		
		ImagePointerMasterVO ImagePointerMasterVO=new ImagePointerMasterVO();
		try
		{
			//HttpSession session=WebUTIL.getSession(request);
			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String sImageDescID = concatid[0];
			String slno=concatid[2];
			ImagePointerMasterVO.setImageDescId(sImageDescID);
			ImagePointerMasterVO.setSlNo(slno);
			
			ImagePointerMasterVO=ImagePointerMasterDATA.getModifyDetail(ImagePointerMasterVO,userVO);
			HelperMethods.populate(_fb, ImagePointerMasterVO);
			_fb.setSlNo(slno);
			_fb.setImageDescId(sImageDescID);
			
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
		
	}
	
	public static boolean saveModifyDetail(ImagePointerMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		boolean flag=false;
		ImagePointerMasterVO ImagePointerMasterVO=new ImagePointerMasterVO();
		try
		{	
			
			
			HelperMethods.populate(ImagePointerMasterVO, _fb);
			ImagePointerMasterDATA.saveModifyDetail(ImagePointerMasterVO,userVO);
			objStatus.add(Status.DONE,"Record Modified Successfully","");	
			flag=true;
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
		return flag;
	}

}
