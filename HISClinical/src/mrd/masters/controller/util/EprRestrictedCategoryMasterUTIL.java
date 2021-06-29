package mrd.masters.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInsertNotAllowedException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.EprRestrictedCategoryVO;
import hisglobal.vo.UserVO;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import mrd.masters.controller.data.EprRestrictedCategoryMasterDATA;
import mrd.masters.controller.fb.EprRestrictedCategoryMasterFB;

public class EprRestrictedCategoryMasterUTIL extends ControllerUTIL{

	public static void getEprRestrictedCategoryEssentials(EprRestrictedCategoryMasterFB _fb,HttpServletRequest _rq)
	{
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		//List<Entry> listProfileType= new ArrayList<Entry>();
		try{
			UserVO userVO= getUserVO( _rq);
			essentialMap=EprRestrictedCategoryMasterDATA.getEprRestrictedCategoryEssentials(userVO);
			WebUTIL.setMapInSession(essentialMap, _rq);
			objStatus.add(Status.NEW,"","");
		}
		catch(HisRecordNotFoundException e){	
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());		
			
		}
		catch(HisInsertNotAllowedException e){	
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());		
			
		}
		catch(HisDataAccessException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Exception","");
			}		
		catch(Exception e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in DisasterAreaMstUTIL","");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}
	}
	
	public static boolean save(EprRestrictedCategoryMasterFB _fb, HttpServletRequest _request)
	{
		boolean flag=false;
		Status objStatus = new Status();
		UserVO userVO = getUserVO(_request);
		EprRestrictedCategoryVO [] eprRestrictedCatMstVO=null;
				
		try
			{
				
				String selectedList[]=_fb.getSelectedPatCatCode();
				eprRestrictedCatMstVO=new EprRestrictedCategoryVO[selectedList.length];
				for(int j=0;j<selectedList.length;j++){
					eprRestrictedCatMstVO[j]=new EprRestrictedCategoryVO();
					eprRestrictedCatMstVO[j].setPatCatCode(selectedList[j]);
				}	
						
				EprRestrictedCategoryMasterDATA.saveEprPatRestrictedCategory(eprRestrictedCatMstVO,userVO);
				objStatus.add(Status.NEW,"","Record Saved Successfully");
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
		return flag;
	}

	
	
}
