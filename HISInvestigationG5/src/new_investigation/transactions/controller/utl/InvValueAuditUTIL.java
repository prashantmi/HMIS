package new_investigation.transactions.controller.utl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.InvResultEntryDATA;
import new_investigation.transactions.controller.data.InvValueAuditDATA;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.vo.InvValueAuditVO;
import new_investigation.vo.template.ResultEntryVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

public class InvValueAuditUTIL extends ControllerUTIL {
	
	public static void getEssential(InvValueAuditFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();

		UserVO userVO = ControllerUTIL.getUserVO(request);
        
		InvValueAuditVO vo=new InvValueAuditVO();
		
		try{	
			Map mp=new HashMap(); 
			Map mpp=new HashMap(); 
          
			HelperMethods.populate(vo,fb);

			ControllerUTIL.setSysdate(request);
			Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);

			mp=InvValueAuditDATA.LabComboForAudit(fb, userVO);
			WebUTIL.setMapInSession(mp, request);
			
				mpp=InvValueAuditDATA.AllTestComboForAudit(vo, userVO);
				WebUTIL.setMapInSession(mpp, request);
			  
			
				objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}			

	}   
		
		public static void getlistauditprocess(InvValueAuditFB fb,HttpServletRequest request)
		{
			Status objStatus= new Status();

			UserVO userVO = ControllerUTIL.getUserVO(request);
          
			InvValueAuditVO vo=new InvValueAuditVO();
			
			try{	
				Map mp=new HashMap(); 
				Map mpp=new HashMap(); 

                 HelperMethods.populate(vo,fb);
                 
				ControllerUTIL.setSysdate(request);
				Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
				WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);

				mp=InvValueAuditDATA.getlistauditprocess(vo, userVO);
				WebUTIL.setMapInSession(mp, request);
				
				objStatus.add(Status.TRANSINPROCESS);
			}catch(HisRecordNotFoundException e)
			{
				System.out.println(e.getMessage());
				objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
			}
			catch(HisDataAccessException e)
			{
				System.out.println(e.getMessage());
				objStatus.add(Status.ERROR_DA,"","Data Access Error");
			}
			catch(HisApplicationExecutionException e)
			{		
				System.out.println(e.getMessage());
				objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			}
			catch(HisException e)
			{
				System.out.println(e.getMessage());
				objStatus.add(Status.ERROR,"","Error");
			}		
		}
			
			
			public static void getTestName(InvValueAuditFB fb,HttpServletRequest request)
			{
				Status objStatus= new Status();

				UserVO userVO = ControllerUTIL.getUserVO(request);
	          
				InvValueAuditVO vo=new InvValueAuditVO();
				
				try{	
					Map mp=new HashMap(); 
					Map mpp=new HashMap(); 

	                 HelperMethods.populate(vo,fb);
	                 
					ControllerUTIL.setSysdate(request);
					Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
					WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);

					mp=InvValueAuditDATA.TestComboForAudit(vo, userVO);
					WebUTIL.setMapInSession(mp, request);
					
						objStatus.add(Status.TRANSINPROCESS);
				}catch(HisRecordNotFoundException e)
				{
					System.out.println(e.getMessage());
					objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
				}
				catch(HisDataAccessException e)
				{
					System.out.println(e.getMessage());
					objStatus.add(Status.ERROR_DA,"","Data Access Error");
				}
				catch(HisApplicationExecutionException e)
				{		
					System.out.println(e.getMessage());
					objStatus.add(Status.ERROR_AE,"","Application Execution Error");
				}
				catch(HisException e)
				{
					System.out.println(e.getMessage());
					objStatus.add(Status.ERROR,"","Error");
				}			

	}
			
			public static void showPatDetails(InvValueAuditFB fb,HttpServletRequest request)
			{
				Status objStatus= new Status();

				UserVO userVO = ControllerUTIL.getUserVO(request);
	          
				InvValueAuditVO vo=new InvValueAuditVO();
				
				try{	
					Map mp=new HashMap(); 
					Map mpp=new HashMap(); 
					String dNo="";
					String testCode="";
					String labCode="";

                      String[] chk=fb.getChkSamplePatient();
                      
                      for(int i=0;i<chk.length;i++)
                      {
                    	  
				      String[] concatid = chk[i].split("#");
					  dNo      += concatid[0]+",";
					  labCode += concatid[1]+",";
					  testCode  += concatid[2]+",";
                      
                      }
                      
                      dNo=dNo.substring(0,dNo.length()-1);
                      testCode=testCode.substring(0,testCode.length()-1);
                      labCode=labCode.substring(0,labCode.length()-1);
                      
	                 HelperMethods.populate(vo,fb);
	                 
					ControllerUTIL.setSysdate(request);
					Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
					WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
					
					
					
					

					mp=InvValueAuditDATA.showPatDetails(vo, userVO,dNo,testCode,labCode);
					WebUTIL.setMapInSession(mp, request);
					
						objStatus.add(Status.TRANSINPROCESS);
				}catch(HisRecordNotFoundException e)
				{
					System.out.println(e.getMessage());
					objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
				}
				catch(HisDataAccessException e)
				{
					System.out.println(e.getMessage());
					objStatus.add(Status.ERROR_DA,"","Data Access Error");
				}
				catch(HisApplicationExecutionException e)
				{		
					System.out.println(e.getMessage());
					objStatus.add(Status.ERROR_AE,"","Application Execution Error");
				}
				catch(HisException e)
				{
					System.out.println(e.getMessage());
					objStatus.add(Status.ERROR,"","Error");
				}		
			}
	
	
	


}
