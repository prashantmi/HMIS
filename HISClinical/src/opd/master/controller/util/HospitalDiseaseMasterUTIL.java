package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.HospitalDiseaseMasterDATA;
import opd.master.controller.fb.HospitalDiseaseMasterFB;
import opd.transaction.controller.fb.GenericPatientAlertFB;
import registration.RegistrationConfig;
import registration.controller.action.Tab;
import registration.controller.action.TabGroup;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInsertNotAllowedException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.HospitalDiseaseMasterVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.UserVO;

public class HospitalDiseaseMasterUTIL extends ControllerUTIL {

	
	static TabGroup objTabGroup=new TabGroup();
	 static	 {
		 		 
		 Tab t1=new Tab(OpdConfig.HOSPITALDISEASE_MASTER_DISEASE,0,Tab.TAB_UNSELECTED,"Hospital Disease");
		 Tab t2=new Tab(OpdConfig.HOSPITALDISEASE_MASTER_SUBDISEASE,1,Tab.TAB_UNSELECTED,"Hospital Sub Disease");
		
		 objTabGroup.addTab(t1);
		 objTabGroup.addTab(t2);

		 objTabGroup.setHtmlModeFieldName(RegistrationConfig.REG_DSK_HTML_MODE_FIELD_NAME);
		 objTabGroup.setJsOnClickFuncName(RegistrationConfig.REG_DSK_JS_FUNC_ON_CLICK); 
		 
	    } 
	 
	 public static void setTabSequence(String _selectedTab ,HttpServletRequest request)	 
	 {
		 
		 Iterator it =objTabGroup.getTabsCollection().iterator();
		 while(it.hasNext())
		  { 
			Tab tb=(Tab)it.next();
			tb.setStatus(Tab.TAB_UNSELECTED);
		 }	 
		 
		 objTabGroup.setSelectedTab(_selectedTab);		 
		 
		 request.setAttribute(RegistrationConfig.TAB_GROUP,objTabGroup);		 
	 }
	 
	 public static void getHospitalDiseaseEssentials(HospitalDiseaseMasterFB _fb,HttpServletRequest _rq)
		{
			Status objStatus=new Status();
			Map essentialMap=new HashMap();
			try{
					UserVO userVO= getUserVO( _rq);
					setSysdate(_rq);
					essentialMap = HospitalDiseaseMasterDATA.getHospitalDiseaseEssentials(userVO);
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
		public static boolean saveHospitalDisease(HospitalDiseaseMasterFB _fb,HttpServletRequest _rq)
		{
			boolean flag=false;
			Status objStatus=new Status();
			HospitalDiseaseMasterVO hospitalDiseaseMasterVO= new HospitalDiseaseMasterVO();
			try
			{
					UserVO userVO= getUserVO( _rq);

					System.out.println("_fb.getSelIcdNameArray():"+_fb.getSelIcdNameArray());
					System.out.println("_fb.getSelSnomedNameArray():"+_fb.getSelSnomedNameArray());
					_fb.setSelIcd(_fb.getSelIcdArray().split("#"));
					_fb.setSelsnomed(_fb.getSelsnomedArray().split("#"));
					_fb.setSelIcdName(_fb.getSelIcdNameArray().split("#"));
					_fb.setSelSnomedName(_fb.getSelSnomedNameArray().split("#"));
					System.out.println("_fb.getSelIcdArray():"+_fb.getSelIcdArray());
					System.out.println("_fb.getSelsnomedArray():"+_fb.getSelsnomedArray());
					HelperMethods.populate(hospitalDiseaseMasterVO, _fb);
					HospitalDiseaseMasterDATA.saveHospitalDisease(hospitalDiseaseMasterVO,userVO);
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
		public static void fetchHospitalDiseaseModify(HospitalDiseaseMasterFB _fb,HttpServletRequest _rq)
		{
				Status objStatus = new Status();
				UserVO userVO= getUserVO( _rq);
				Map essentialMap1=new HashMap();
				Map essentialMap2=new HashMap();
				HospitalDiseaseMasterVO hospitalDiseaseMasterVO=new HospitalDiseaseMasterVO();
				try
				{
					essentialMap1 = HospitalDiseaseMasterDATA.getHospitalDiseaseEssentials(userVO);
					WebUTIL.setMapInSession(essentialMap1, _rq);
					String chk=_fb.getChk().replace("^", "#");
					String primaryKey[]=chk.split("#");
					String patientAlert=primaryKey[0];
					hospitalDiseaseMasterVO.setDiseaseID(patientAlert);
					HospitalDiseaseMasterDATA.fetchHospitalDiseaseModify(hospitalDiseaseMasterVO,userVO);
					essentialMap2 = HospitalDiseaseMasterDATA.fetchHospitalDiseaseMapping(hospitalDiseaseMasterVO);
					WebUTIL.setMapInSession(essentialMap2, _rq);
					HelperMethods.populate(_fb, hospitalDiseaseMasterVO);
					
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
			
		public static boolean modifySave(HospitalDiseaseMasterFB _fb,HttpServletRequest _rq)
		{
			boolean flag=false;
			Status objStatus = new Status();
			UserVO userVO= getUserVO( _rq);
			HospitalDiseaseMasterVO hospitalDiseaseMasterVO=new HospitalDiseaseMasterVO();
			try{
					HelperMethods.populate(hospitalDiseaseMasterVO,_fb);
					
					hospitalDiseaseMasterVO.setDiseaseID(_fb.getDiseaseID());
									

					System.out.println("_fb.getSelIcdNameArray():"+_fb.getSelIcdNameArray());
					System.out.println("_fb.getSelSnomedNameArray():"+_fb.getSelSnomedNameArray());
					_fb.setSelIcd(_fb.getSelIcdArray().split("#"));
					_fb.setSelsnomed(_fb.getSelsnomedArray().split("#"));
					_fb.setSelIcdName(_fb.getSelIcdNameArray().split("#"));
					_fb.setSelSnomedName(_fb.getSelSnomedNameArray().split("#"));
					System.out.println("_fb.getSelIcdArray():"+_fb.getSelIcdArray());
					System.out.println("_fb.getSelsnomedArray():"+_fb.getSelsnomedArray());
					HelperMethods.populate(hospitalDiseaseMasterVO, _fb);
					HospitalDiseaseMasterDATA.modifySave(hospitalDiseaseMasterVO,userVO);	
					flag=true;
					objStatus.add(Status.DONE,"","Record Modified Successfully");
					
				}
			catch(HisDuplicateRecordException e){	   		   	
				 System.out.println("Inside HisDuplicateRecordException");
		  		 e.printStackTrace(); 
		  		 flag=false;
		  		 objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				System.out.println("Inside HisDataAccessException");
				e.printStackTrace();
				flag=false;
				objStatus.add(Status.ERROR_DA, "", "Data Access Error");
				
			}
			catch (HisApplicationExecutionException e)
			{
				flag=false;
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
				
			}
			catch (HisException e)
			{
				flag=false;
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
		
		
	 
	
}
