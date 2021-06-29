package new_investigation.transactions.controller.utl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.InvResultReportPrintingDATA;
import new_investigation.transactions.controller.data.invReportInProcessDATA;
import new_investigation.transactions.controller.fb.InvResultReportPrintingFB;
import new_investigation.transactions.controller.fb.invReportInProcessFB;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.invReportInProcessVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

public class invReportInProcessUTL extends ControllerUTIL {

	
	
	public static void getEssential(HttpServletRequest request)
	{
		Status objStatus= new Status();
		 
		UserVO userVO = ControllerUTIL.getUserVO(request);
		invReportInProcessVO invReportInProcessVO=new invReportInProcessVO();
		 
		 
		 
	try{	
		Map mp=new HashMap(); 
		Map mpp=new HashMap();
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		
		
		
		 
	  String tDate = WebUTIL.getCustomisedSysDate((Date)request.getSession().getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	  System.out.println("tDate:::"+tDate);
	  invReportInProcessVO.setFromDate(tDate);
	  invReportInProcessVO.setToDate(tDate);
		
		mpp=invReportInProcessDATA.setResultReportPrintingEssentialsOnLoad( userVO);
		WebUTIL.setMapInSession(mpp, request);
		

		
		//HelperMethods.populate(fb, invReportInProcessVO);
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
	
	public static boolean setResultReportPrintingEssentials(invReportInProcessFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		invReportInProcessVO invresultReportPrintingvo=new invReportInProcessVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
			invresultReportPrintingvo.setFromDate(fb.getFromDate()); 
			invresultReportPrintingvo.setToDate(fb.getToDate());
			invresultReportPrintingvo.setPatCRNo(fb.getPatCRNo());
		
			invresultReportPrintingvo.setTempPatName(fb.getTempPatName());
			
			mp=invReportInProcessDATA.setResultReportPrintingEssentials(invresultReportPrintingvo, userVO);
			WebUTIL.setMapInSession(mp, _request);
			
			
			//HelperMethods.populate(fb, InvResultReportPrintingVO);
			  
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	
	
	public static void saveInJobWorkOrder(invReportInProcessFB _fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		 
		UserVO userVO = ControllerUTIL.getUserVO(request);
		 
		List<invReportInProcessVO> invReportInProcessVO=new ArrayList<invReportInProcessVO>();
		 
	try{	
		Map mp=new HashMap(); 
		Map mpp=new HashMap();
		
		String[] selectedLabTestCodeArray=_fb.getChkSamplePatient();
		
		int RowCount=selectedLabTestCodeArray.length;
		System.out.println("lab row count is : " +RowCount );
					for(int i=0;i<RowCount;i++)
					{
						
						invReportInProcessVO voResultEntry=new invReportInProcessVO();
						
						String[] testCodeArray = null;
						if(selectedLabTestCodeArray[i].contains("@"))
						{
							testCodeArray = selectedLabTestCodeArray[i].split("@");
						}
						
						
							
								invReportInProcessVO voResultEntry1=new invReportInProcessVO();
								

								

								String CrNo=testCodeArray[0];
							String	patReqNo=testCodeArray[1];
							String	 requisitionDNo=testCodeArray[2];

								String testCode=testCodeArray[3];
								String sampleNo=testCodeArray[4];
								String labCode=testCodeArray[5];
								String patAge=testCodeArray[6];
								String patGender=testCodeArray[7];
								String reportAvailableAfter=testCodeArray[8];
								String patVisitDat=testCodeArray[9];
								String patVisitNo=testCodeArray[10];
								String labNo=testCodeArray[11];
								String episodeCode=testCodeArray[12];
								String deptCode=testCodeArray[13];
								String deptUnitCode=testCodeArray[14];
								String reqType=testCodeArray[15];
								String testName=testCodeArray[16];
								String labName=testCodeArray[17];
								String sampleName=testCodeArray[18];
								String tempSampleNo=testCodeArray[19];											
								String groupCode = testCodeArray[20];
                               
								
								voResultEntry1.setPatCRNo(CrNo);
								voResultEntry1.setRequisitionNo(patReqNo);
								voResultEntry1.setRequisitionDNo(requisitionDNo);
								voResultEntry1.setTestCode(testCode);
								voResultEntry1.setParameterRequisitionDNo(requisitionDNo);
								voResultEntry1.setSampleNo(sampleNo);
								voResultEntry1.setLabCode(labCode);
								voResultEntry1.setPatAge(patAge);
								voResultEntry1.setPatGender(patGender);
								voResultEntry1.setReportAvailableAfter(reportAvailableAfter);
								// getting Fb Values For Selected Check Box 
								voResultEntry1.setPatVisitDat(patVisitDat);
								voResultEntry1.setPatVisitNo(patVisitNo);
								voResultEntry1.setLabNo(labNo);
								voResultEntry1.setEpisodeCode(episodeCode);
								voResultEntry1.setDepartmentcode(deptCode);
								voResultEntry1.setPatdeptunitcode(deptUnitCode);
								voResultEntry1.setRequisitionTypeCode(reqType);
								voResultEntry1.setPatLabName(labName);
								voResultEntry1.setTestName(testName);
								voResultEntry1.setSampleName(sampleName);
								voResultEntry1.setTempSampleNo(tempSampleNo);
								//voResultEntry1.setDynamicTemplatePrintedGroup("");
								voResultEntry1.setGroupCode(groupCode);
								invReportInProcessVO.add(voResultEntry1);
							
						
						
					}
					
					
					Map<String,List<invReportInProcessVO>> mppp= new HashMap<String,List<invReportInProcessVO>>();
		
		                 if(invReportInProcessVO!=null && invReportInProcessVO.size()>0)
		                 {
		                	 for(int k=0;k<invReportInProcessVO.size();k++)
		                	 {
		                		 invReportInProcessVO vo=invReportInProcessVO.get(k);
		                		 
		                		 String key=vo.getRequisitionNo()+"#"+vo.getTempSampleNo();
		                		 
		                		 if(mppp.containsKey(key))
		                		 {
		                			 List<invReportInProcessVO> voo=mppp.get(key);
		                			 voo.add(vo);
		                		 }
		                		 else
		                		 {
		                			 List<invReportInProcessVO> voo=new ArrayList<invReportInProcessVO>();
		                			 voo.add(vo);
		                			 mppp.put(key, voo);
		                		 }
		                	 }
		                	 
		                 }
		                 
		         		List<invReportInProcessVO> invReportInProcessVOnew=new ArrayList<invReportInProcessVO>();

		         		
		                 if(mppp!=null && mppp.size()>0)
		                 {
		                	 
		 					Iterator itrreqnosammpleno=mppp.keySet().iterator();
		 					
		 					while(itrreqnosammpleno.hasNext())
		 					{
		 						
								String keyy=(String)itrreqnosammpleno.next();
								
								List<invReportInProcessVO>  vooo=mppp.get(keyy);
							
								invReportInProcessVO voo=vooo.get(0);
								
								List<invReportInProcessVO>	lstInvResultReportPrintingVO=invReportInProcessDATA.getdno(voo, userVO);
							
								if(lstInvResultReportPrintingVO!=null && lstInvResultReportPrintingVO.size()>0)
								for(int k=0;k<lstInvResultReportPrintingVO.size();k++)
								{
									
									invReportInProcessVO vop=lstInvResultReportPrintingVO.get(k);
								      invReportInProcessVOnew.add(vop);

								}
						
								if(vooo!=null && vooo.size()>0)
								for(int k=0;k<vooo.size();k++)
								{
									
									invReportInProcessVO vop=vooo.get(k);
								      invReportInProcessVOnew.add(vop);

								}
								
						    
		 					}

		                 }
		                 
		                 invReportInProcessDATA.saveInJobWorkOrder(invReportInProcessVOnew, userVO);
							WebUTIL.setMapInSession(mpp, request);
							
		
		

		
		//HelperMethods.populate(fb, invReportInProcessVO);
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
