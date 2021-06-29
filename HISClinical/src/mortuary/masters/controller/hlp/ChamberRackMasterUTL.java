package mortuary.masters.controller.hlp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ChamberRackMasterVO;
import hisglobal.vo.UserVO;
import javax.servlet.http.HttpServletRequest;

import mortuary.MortuaryConfig;


public class ChamberRackMasterUTL extends ControllerUTIL
{
	
	
	// For fetching the mortuary name and area name from selection
	
	public static void getChamberRackEssentials(ChamberRackMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		
		String chamberId="";
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			
				   chamberId=_fb.getControls()[0];
					
			String chamberName="";
			
			_fb.setChamberId(_fb.getControls()[0]);

			chamberName=ChamberRackMasterDATA.getChamberRackEssentials(chamberId, userVO);
		
			
			String[] arrayOfChamberDetails=chamberName.split("@");
			
		if(arrayOfChamberDetails!=null)			
			{
			_fb.setChamberName(arrayOfChamberDetails[0]);
			_fb.setRackNumbers(arrayOfChamberDetails[1]);
			}
			
			
		
			
			objStatus.add(Status.NEW);
		}
		catch (HisDuplicateRecordException e)
		{
			
			
			String[] arrayOfChamberDetails=((String)e.getEssentialMap().get(chamberId)).split("@");
			
			if(arrayOfChamberDetails!=null)			
				{
				_fb.setChamberName(arrayOfChamberDetails[0]);
				_fb.setRackNumbers("-1");
				}
			
			
			e.printStackTrace();
			System.out.println("Inside HisDuplicateRecordException---"+e.getEssentialMap().get(chamberId));
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
	}
	
	
	
	// On Add Page saving Values into Database
	public static boolean saveChamberRack(ChamberRackMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			ChamberRackMasterVO[] _chamberMstVO=new ChamberRackMasterVO[Integer.parseInt(_fb.getRackNumbers())];
			
			for(int i=0; i < _chamberMstVO.length; i++)
				{
				
				String statusName="rackStatus_"+(i+1);
				
				_chamberMstVO[i]=new ChamberRackMasterVO();
				
				
				_chamberMstVO[i].setRackName(_fb.getRackName()[i]);
				_chamberMstVO[i].setChamberId(_fb.getChamberId());
				_chamberMstVO[i].setMaxCapacity(_fb.getRackCapacity()[i]);				
				_chamberMstVO[i].setRackStatus(_request.getParameter(statusName));
				
				
				}
		
			 

			
				
				
			_fb.setHmode("ADD");
			
			hasFlag=ChamberRackMasterDATA.saveChamberRack(_chamberMstVO, userVO);
			
			if(hasFlag)
			{
				//_fb.setHmode("LIST");
				objStatus.add(Status.DONE,"","Record Added Successfully");
			}
			
			
				
			           	
    	}
		catch (HisDuplicateRecordException e)
		{
			//_fb.setHmode("NEW");
			//System.out.println("Inside HisDuplicateRecordException");
			objStatus.add(Status.INPROCESS, "", e.getMessage());
			hasFlag = false;
			e.printStackTrace();
		}
		catch (HisRecordNotFoundException e)
		{
			//System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			hasFlag = false;
		}
		catch (HisDataAccessException e)
		{
			//System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			hasFlag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			//System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			hasFlag = false;
		}
		catch (HisException e)
		{
			//System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
			hasFlag = false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			//System.out.println("   -----> objStatus in finally  : " + objStatus);
		//	System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}

	// on modify Page for Showing Data of Selected Record
	public static void getChamberRackDetails(ChamberRackMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		
		//passed as a parmeter
		ChamberRackMasterVO _chamberMstVO=new ChamberRackMasterVO();

		//received from a function
		ChamberRackMasterVO chamberMstVO=new ChamberRackMasterVO();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String chamberRackId = concatid[0];
			String chamberSlno = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo
			
			_chamberMstVO.setChamberRackId(chamberRackId);
			_chamberMstVO.setSerialNo(chamberSlno);
			_chamberMstVO.setIsActive(_fb.getIsActive());
			
			
			chamberMstVO=ChamberRackMasterDATA.getChamberRackDetails(_chamberMstVO, userVO);
			
			String[] rackName={chamberMstVO.getRackName()};
			
			String[] rackStatus={chamberMstVO.getRackStatus()};
			
			
			String[] rackCapacity={chamberMstVO.getMaxCapacity()};
			
			
			_fb.setRackName(rackName);
			_fb.setRackStatus(rackStatus);
			_fb.setRackCapacity(rackCapacity);
			_fb.setChamberId(chamberMstVO.getChamberId());
			_fb.setChamberName(chamberMstVO.getChamberName());
			_fb.setIsActive(chamberMstVO.getIsActive());
			
			
			_fb.setChk(chk);

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
	}

	// for Updating The old Record
	public static boolean updateChamberRack(ChamberRackMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			//passed as a parmeter
			ChamberRackMasterVO _chamberMstVO=new ChamberRackMasterVO();

				

			String[] rackName=_fb.getRackName();
			
			String rackStatus=_request.getParameter("rackStatus_1");
			
			
			String[] rackCapacity=_fb.getRackCapacity();
			
			
			
			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String chamberRackId = concatid[0];
			String chamberRackSlno = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo
			
			_chamberMstVO.setChamberRackId(chamberRackId);
			_chamberMstVO.setSerialNo(chamberRackSlno);
	
			///putting the form details
			
			_chamberMstVO.setRackName(rackName[0]);
			_chamberMstVO.setRackStatus(rackStatus);
			_chamberMstVO.setMaxCapacity(rackCapacity[0]);
			_chamberMstVO.setChamberId(_fb.getChamberId());
			_chamberMstVO.setIsActive(_fb.getIsActive());
			
			
			
			
			
			
			flag=ChamberRackMasterDATA.updateChamberRack(_chamberMstVO, userVO);
			if(flag)
			{
				_fb.setHmode("LIST");
				objStatus.add(Status.DONE,"","Record Modified Successfully");
			}
			
			
		}
		catch (HisDuplicateRecordException e)
		{
			String [] rackStatus={_request.getParameter("rackStatus_1")};
			
		//	_fb.setRackName(_fb.getRackName());
			_fb.setRackStatus(rackStatus);
	//		_fb.setRackCapacity(_fb.getRackCapacity());
			
			
			_fb.setRackNumbers("-1");
			_fb.setHmode("MODIFY");
			//System.out.println("Inside HisDuplicateRecordException");
			objStatus.add(Status.DONE, "", e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
		return flag;
	}

	
}
