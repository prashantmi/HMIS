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
import hisglobal.vo.ChamberMasterVO;
import hisglobal.vo.UserVO;
import javax.servlet.http.HttpServletRequest;

import mortuary.MortuaryConfig;


public class ChamberMasterUTL extends ControllerUTIL
{
	
	
	// For fetching the mortuary name and area name from selection
	
	public static void getChamberEssentialDetails(ChamberMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			Map essentialMap=new HashMap();
			
			essentialMap=ChamberMasterDATA.getChamberEssentialDetails(_fb.getControls(), userVO);
			
			
			List mortuaryList=new ArrayList();
			List areaList=new ArrayList();
			
			//Getting and putting all mortuaries
			
			mortuaryList=(List)essentialMap.get(MortuaryConfig.ESENTIAL_ALL_MORTUARY);			

			//Getting and putting all areas bsed on mortuaries
			
			areaList=(List)essentialMap.get(MortuaryConfig.ESENTIAL_ALL_AREA_BASED_ON_MORTUARY);
			
			
			Iterator itr1=mortuaryList.iterator();
			
			while(itr1.hasNext())
			{
				Entry obj=(Entry)itr1.next();
				
				if(obj.getValue().equals(_fb.getControls()[0]))
				{
					
					_fb.setMortuaryName(obj.getLabel());
				}
			}	
				
			
			Iterator itr2=areaList.iterator();
			
			while(itr2.hasNext())
			{
				Entry obj=(Entry)itr2.next();
				
				if(obj.getValue().equals(_fb.getControls()[1]))
					_fb.setAreaName(obj.getLabel());
				
			}	
			
			_fb.setAreaCode(_fb.getControls()[1]);
			
			
		//	_fb.setControls(essentialList.toArray());
			
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
	
	
	
	// On Add Page saving Values into Database
	public static boolean saveChamber(ChamberMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			ChamberMasterVO _chamberMstVO=new ChamberMasterVO();
			
			//In case the chamber type is Chambers ,then by defailt rack no.=1 
			
			if(_fb.getChamberType().equals("2"))
				_fb.setRackNumbers("1");
			
			HelperMethods.populate(_chamberMstVO, _fb); 

			
				
				
			_fb.setHmode("ADD");
			
			hasFlag=ChamberMasterDATA.saveChamber(_chamberMstVO, userVO);
			
			
			if(hasFlag)
				objStatus.add(Status.INPROCESS, "", "Record Added Successfully");
				
			           	
    	}
		catch (HisDuplicateRecordException e)
		{
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
	public static void getChamberDetails(ChamberMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		
		//passed as a parmeter
		ChamberMasterVO _chamberMstVO=new ChamberMasterVO();

		//received from a function
		ChamberMasterVO chamberMstVO=new ChamberMasterVO();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String chamberId = concatid[0];
			String chamberSlno = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo
			
			_chamberMstVO.setChamberId(chamberId);
			_chamberMstVO.setSerialNo(chamberSlno);
			
			chamberMstVO=ChamberMasterDATA.getChamberDetails(_chamberMstVO, userVO);
			
			HelperMethods.populate(_fb, chamberMstVO);
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
	public static boolean updateChamber(ChamberMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			//passed as a parmeter
			ChamberMasterVO _chamberMstVO=new ChamberMasterVO();

				
			//In case the chamber type is Chambers ,then by defailt rack no.=1
			
			if(_fb.getChamberType().equals("2"))
				_fb.setRackNumbers("1");
		
					
			HelperMethods.populate(_chamberMstVO, _fb);
			
			flag=ChamberMasterDATA.updateChamber(_chamberMstVO, userVO);
			if(flag)
			{
				_fb.setHmode("LIST");
				objStatus.add(Status.DONE,"","Record Modified Successfully");
			}
			
			
		}
		catch (HisDuplicateRecordException e)
		{
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
