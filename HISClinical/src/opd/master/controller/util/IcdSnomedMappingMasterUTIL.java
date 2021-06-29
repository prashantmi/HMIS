package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInsertNotAllowedException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.IcdSnomedMappingMasterVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.IcdSnomedMappingMasterDATA;
import opd.master.controller.fb.icdSnomedMappingMasterFB;

public class IcdSnomedMappingMasterUTIL extends ControllerUTIL {

	
	 
	 public static void getIcdSnomedEssentials(icdSnomedMappingMasterFB _fb,HttpServletRequest _rq)
		{
			Status objStatus=new Status();
			Map essentialMap=new HashMap();
			try{
					UserVO userVO= getUserVO( _rq);
					setSysdate(_rq);
					essentialMap = IcdSnomedMappingMasterDATA.getIcdSnomedEssentials(userVO);
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
				objStatus.add(Status.ERROR,"Exception in ProfileAccessPolicyUTIL","");
			}
			finally{
				WebUTIL.setStatus(_rq,objStatus);
			}
		}
		public static boolean saveIcdSnomedMapping(icdSnomedMappingMasterFB _fb,HttpServletRequest _rq)
		{
			boolean flag=false;
			Status objStatus=new Status();
			IcdSnomedMappingMasterVO icdSnomedMappingMasterVO= new IcdSnomedMappingMasterVO();
			try
			{
					UserVO userVO= getUserVO( _rq);
					_fb.setSelsnomed(_fb.getSelsnomedArray().split("#"));
					_fb.setSelSnomedName(_fb.getSelSnomedNameArray().split("#"));
					System.out.println("_fb.getSelsnomedArray():"+_fb.getSelsnomedArray());
					HelperMethods.populate(icdSnomedMappingMasterVO, _fb);
					IcdSnomedMappingMasterDATA.saveIcdSnomedMapping(icdSnomedMappingMasterVO,userVO);
					flag=true;
					objStatus.add(Status.DONE,"","Record Saved Successfully");
				}
				catch(HisDuplicateRecordException e){	   		   	
					 System.out.println("Inside HisDuplicateRecordException");
			  		 e.printStackTrace(); 
			  		 flag=false;
			  		 objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
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
					WebUTIL.setStatus(_rq, objStatus);
					System.out.println("   -----> objStatus in finally  : " + objStatus);
					System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
				}
			return flag;
		}
		public static void fetchIcdSnomedModify(icdSnomedMappingMasterFB _fb,HttpServletRequest _rq)
		{
				Status objStatus = new Status();
				UserVO userVO= getUserVO( _rq);
				Map essentialMap1=new HashMap();
				Map essentialMap2=new HashMap();
				IcdSnomedMappingMasterVO icdSnomedMappingMasterVO=new IcdSnomedMappingMasterVO();
				try
				{
					essentialMap1 = IcdSnomedMappingMasterDATA.getIcdSnomedEssentials(userVO);
					WebUTIL.setMapInSession(essentialMap1, _rq);
					String chk=_fb.getChk().replace("^", "#");
					String primaryKey[]=chk.split("#");
					String mpSq=primaryKey[0];
					icdSnomedMappingMasterVO.setMappingSq(mpSq);
					IcdSnomedMappingMasterDATA.fetchIcdSnomedModify(icdSnomedMappingMasterVO,userVO);
					essentialMap2 = IcdSnomedMappingMasterDATA.fetchIcdSnomedMapping(icdSnomedMappingMasterVO);
					WebUTIL.setMapInSession(essentialMap2, _rq);
					HelperMethods.populate(_fb, icdSnomedMappingMasterVO);
					
					if(_fb.getHmode().equals("VIEW"))
					{
						
					}
					
					objStatus.add(Status.DONE);
				}
				catch (HisRecordNotFoundException e)
				{
					System.out.println("Inside HisRecordNotFoundException");
					e.printStackTrace();
					objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
					
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
					WebUTIL.setStatus(_rq, objStatus);
					System.out.println("   -----> objStatus in finally  : " + objStatus);
					System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
				}
			}
			
		
		public static void icdSnomedModify(HttpServletRequest _rq)
		{
			Status objStatus=new Status();
			try{
					UserVO userVO= getUserVO( _rq);
					setSysdate(_rq);					
					
					objStatus.add(Status.DONE,"","Modification is Not Allowed");
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
				objStatus.add(Status.ERROR,"Exception in ProfileAccessPolicyUTIL","");
			}
			finally{
				WebUTIL.setStatus(_rq,objStatus);
			}
		}
	
}

