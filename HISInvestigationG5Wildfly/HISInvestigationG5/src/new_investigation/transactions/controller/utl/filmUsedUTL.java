package new_investigation.transactions.controller.utl;


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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.filmUsedDATA;
import new_investigation.transactions.controller.fb.filmUsedFB;
import new_investigation.vo.filmUsedVO;


public class filmUsedUTL extends ControllerUTIL
{
	public static boolean setPatientEssentials(filmUsedFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		filmUsedVO filmusedvo = new filmUsedVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 
			 
			filmusedvo.setFromDate(fb.getFromDate()); 
			filmusedvo.setToDate(fb.getToDate());
			filmusedvo.setPatCrNo(fb.getPatCrNo()); 
			filmusedvo.setLabCode(fb.getLabCode());
			 
			mp=filmUsedDATA.setPatientEssentials(filmusedvo, userVO);
			WebUTIL.setMapInSession(mp, _request);
		//	HelperMethods.populate(fb, onlinePatientvo);
			  
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
	
	
	public static boolean patientDetails(filmUsedFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		filmUsedVO onlinePatientvo = new filmUsedVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 
			
			 
			onlinePatientvo.setFromDate(fb.getFromDate()); 
			onlinePatientvo.setToDate(fb.getToDate());
			onlinePatientvo.setPatCrNo(fb.getPatCrNo()); 
		onlinePatientvo.setLabCode(fb.getLabCode());
			 
			//mp=filmUsedDATA.patientDetails(onlinePatientvo, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, onlinePatientvo);
			  
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
	
	public static void showPatDetails(filmUsedFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		
		Map mp=new HashMap();
		boolean flag=false;
		try{
			List<filmUsedVO> onlinePatientvo=null;
			filmUsedVO onlinePatientv = new filmUsedVO();
		 
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			onlinePatientvo=(List<filmUsedVO>)session.getAttribute(InvestigationConfig.LIST_TEST_DETAILS_FILM_USED);
			String selectedCheckBoxValue=fb.getSelectedCheckbox();
			
			String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			
			String crNo=arrSelectedRequisitions[0].split("#")[0];
			String reqNO=arrSelectedRequisitions[0].split("#")[1];
			String usedfilmdata=arrSelectedRequisitions[0].split("#")[2];
			String labCode=arrSelectedRequisitions[0].split("#")[3];
			String reqdno=arrSelectedRequisitions[0].split("#")[6];

			int totalaused=0;
			int wasteused=0;
			int addtionalused=0;
			
			if(usedfilmdata!=null && !usedfilmdata.equals("") && !usedfilmdata.equals("0"))
			{
				String data[]=usedfilmdata.split("\\$");
				

			     if(data.length>=1)
				totalaused=Integer.parseInt(data[0]);
			     if(data.length>=2)
			    	 addtionalused=Integer.parseInt(data[1]);
			     if(data.length==3)
			    	 wasteused =Integer.parseInt(data[2]);
			     
				if(wasteused!=0)
				{
					totalaused=totalaused-wasteused;
				}
				
				if(addtionalused!=0)
				{
					totalaused=totalaused-addtionalused;
				}
				
				request.getSession().setAttribute("totalaused", Integer.toString(totalaused));
				request.getSession().setAttribute("wasteused", Integer.toString(wasteused));
				request.getSession().setAttribute("addtionalused", Integer.toString(addtionalused));

			}
			
			
				for(filmUsedVO objPatientCollectionVO:onlinePatientvo)
				{
					if(objPatientCollectionVO.getPatCrNo().equals(crNo)&&objPatientCollectionVO.getRequisitionDNo().equals(reqdno))
					{
						WebUTIL.populate(fb,objPatientCollectionVO); 
						objPatientCollectionVO.setTestName(fb.getTestName());
						objPatientCollectionVO.setPatLabName(fb.getPatLabName());
						
						WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_PATIENT_VO,objPatientCollectionVO);
						onlinePatientv=objPatientCollectionVO;
						flag=true;
						break;
					}
				}
				
				for(String str:arrSelectedRequisitions)
					reqList.add(str);
				
				 
				mp=filmUsedDATA.patientDetails(onlinePatientv,reqList, userVO);
				
				WebUTIL.setMapInSession(mp, request);
					
				fb.setTempFilmNo(onlinePatientv.getTempFilmNo());
				fb.setPatType(onlinePatientv.getPatType());
			 fb.setTestStatus("1");
			 fb.setLabcodefilm(labCode);
			status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
	}
	  
	// Save Logic
			public static void savePatientDetails(filmUsedFB _fb,HttpServletRequest _request)
			{
				Status objStatus=new Status();	
				HttpSession session=_request.getSession();
				
				filmUsedVO filmVO=new filmUsedVO();
				List<filmUsedVO> lstFilmUsed=new ArrayList<filmUsedVO>();
				List<filmUsedVO> lstFilmAdditional=new ArrayList<filmUsedVO>();
				List<filmUsedVO> lstFilmWaste=new ArrayList<filmUsedVO>();
				Map mp=new HashMap();
				List<String> batchValues=new ArrayList<String>();
				String tempFilmNo=_fb.getTempFilmNo();
				String batchKey="";
			  	
				try
				{
					Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
					UserVO _userVO=getUserVO(_request);
					
				 		String reqNo=_fb.getReqNo();
				 		String crNo=_fb.getPatCrNo();
				 		String reqDNo=_fb.getRequisitionDNo();
				
						for(int i=0;i<_fb.getFilmNo().length;i++)
						{
							
							filmVO=new filmUsedVO();
							filmVO.setReqNo(reqNo);
							filmVO.setRequisitionDNo(reqDNo);
							filmVO.setPatCrNo(crNo);
							filmVO.setFilmNo(_fb.getFilmNo()[i]);
							filmVO.setFilmSize(_fb.getFilmSize()[i].split("#")[0]);//filmsize#itemId#storeid#noOfFilmRequired
							filmVO.setLabCode(_fb.getLabCode());
							filmVO.setTestCode(_fb.getTestCode());
							filmVO.setFilmUsedType("1");
							filmVO.setRemarks(_fb.getRemarks());
							filmVO.setBatchNo(_fb.getBatchNo()[i]);
							filmVO.setInventory(_fb.getInventory()[i]);
							if(!filmVO.getBatchNo().equals("-"))
							{
								
								batchKey=filmVO.getBatchNo()+"#"+filmVO.getInventory()+"#"+_fb.getFilmSize()[i].split("#")[1]+"#"+_fb.getFilmSize()[i].split("#")[2];
							
							
							if(mp.get(batchKey)==null)
							mp.put(batchKey, 1);
							else
								mp.put(batchKey, Integer.parseInt(mp.get(batchKey).toString())+1 );
							}
							
							lstFilmUsed.add(filmVO);
							
						}
						
						if(_fb.getAdditionalFilmNo()!=null)
						for(int i=0;i<_fb.getAdditionalFilmNo().length;i++)
						{
							
							filmVO=new filmUsedVO();
							filmVO.setReqNo(reqNo);
							filmVO.setRequisitionDNo(reqDNo);
							filmVO.setPatCrNo(crNo);
							filmVO.setFilmNo(_fb.getAdditionalFilmNo()[i]);
							filmVO.setFilmSize(_fb.getFilmSize()[i].split("#")[0]);//filmsize#itemId#storeid#noOfFilmRequired
							filmVO.setLabCode(_fb.getLabCode());
							filmVO.setTestCode(_fb.getTestCode());
							filmVO.setFilmReason(_fb.getFilmReason()[i]);
							filmVO.setFilmUsedType("2");
							filmVO.setRemarks(_fb.getRemarks());
							filmVO.setBatchNo(_fb.getBatchNo()[i]);
							filmVO.setInventory(_fb.getInventory()[i]);
							if(!filmVO.getBatchNo().equals("-"))
							{
batchKey=filmVO.getBatchNo()+"#"+filmVO.getInventory()+"#"+_fb.getFilmSize()[i].split("#")[1]+"#"+_fb.getFilmSize()[i].split("#")[2];
								
								
								if(mp.get(batchKey)==null)
								mp.put(batchKey, 1);
								else
									mp.put(batchKey, Integer.parseInt(mp.get(batchKey).toString())+1 );		
							}
							lstFilmAdditional.add(filmVO);
							
						}
						
						
						if(_fb.getWasteFilmNo()!=null)
						for(int i=0;i<_fb.getWasteFilmNo().length;i++)
						{
							
							filmVO=new filmUsedVO();
							filmVO.setReqNo(reqNo);
							filmVO.setRequisitionDNo(reqDNo);
							filmVO.setPatCrNo(crNo);
							filmVO.setFilmNo(_fb.getWasteFilmNo()[i]);
							filmVO.setFilmSize(_fb.getFilmSize()[i].split("#")[0]);//filmsize#itemId#storeid#noOfFilmRequired
							filmVO.setLabCode(_fb.getLabCode());
							filmVO.setTestCode(_fb.getTestCode());
							filmVO.setFilmReason(_fb.getFilmReasonWaste()[i]);
							filmVO.setFilmUsedType("0");
							filmVO.setRemarks(_fb.getRemarks());
							filmVO.setBatchNo(_fb.getBatchNo()[i]);
							filmVO.setInventory(_fb.getInventory()[i]);
							if(!filmVO.getBatchNo().equals("-"))
							{
								batchKey=filmVO.getBatchNo()+"#"+filmVO.getInventory()+"#"+_fb.getFilmSize()[i].split("#")[1]+"#"+_fb.getFilmSize()[i].split("#")[2];
								
								
								if(mp.get(batchKey)==null)
								mp.put(batchKey, 1);
								else
									mp.put(batchKey, Integer.parseInt(mp.get(batchKey).toString())+1 );
										
							}
							lstFilmWaste.add(filmVO);
							
						}
						
						filmVO=new filmUsedVO();
						filmVO.setRequisitionDNo(reqDNo);
						filmVO.setNoOfFilms(_fb.getNoOfFilms());
						filmVO.setNoOfFilmsAdditional(_fb.getAdditionalFilmNo()==null?"":""+_fb.getAdditionalFilmNo().length);
						filmVO.setNoOfFilmsWated(_fb.getWasteFilmNo()==null?"":""+_fb.getWasteFilmNo().length);
						filmVO.setRemarks(_fb.getRemarks());
						filmVO.setTempFilmNo(tempFilmNo);
						filmVO.setLabCode(_fb.getLabCode());
						filmVO.setTestStatus(_fb.getTestStatus());

						if(_fb.getLabcodefilm()!=null)
							filmVO.setLabCode(_fb.getLabcodefilm());
						
						filmUsedDATA.savePatientDetails(lstFilmUsed,lstFilmAdditional,lstFilmWaste,filmVO, _userVO,mp);
								
						 		objStatus.add(Status.DONE,"",
											"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
													+ "Data Saved Successfully" + "</font>");
								
									
						 		objStatus.add(Status.NEW, "", "");
								objStatus.add(Status.TRANSINPROCESS, "", "");   
				}
				catch (HisRecordNotFoundException e)
				{
					objStatus.add(Status.ERROR_DA, e.getMessage(), "");
					System.out.println(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					objStatus.add(Status.ERROR_DA, e.getMessage(), "");
					System.out.println(e.getMessage());
				}
				catch (HisApplicationExecutionException e)
				{
					objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
					System.out.println(e.getMessage());
				}
				catch (HisException e)
				{
					objStatus.add(Status.ERROR, "", "Error");
					System.out.println(e.getMessage());
				}
				finally
				{
					WebUTIL.setStatus(_request, objStatus);
				}
			}
	//End Save Logic
	
	
	 
	
	public static void getEssential(filmUsedFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		UserVO userVO = ControllerUTIL.getUserVO(request);
		filmUsedVO filmusedvo=new filmUsedVO();
		String[] multipleHospitalArray = new String[1];
		multipleHospitalArray[0] = ControllerUTIL.getUserVO(request).getHospitalCode();
		System.out.println("Logged as Hospital Code"+ multipleHospitalArray[0]);
		 
	try{	
		Map mp=new HashMap(); 
		
		Map mpp=new HashMap();
		
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		 
		String tDate = WebUTIL.getCustomisedSysDate((Date)request.getSession().getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");

		filmusedvo.setLabCode("%");
		filmusedvo.setFromDate(tDate);
		filmusedvo.setToDate(tDate);
		
		
		mpp=filmUsedDATA.setPatientEssentialsOnLoad(filmusedvo, userVO);
		mp=filmUsedDATA.LabCombos(filmusedvo, userVO);
		
		WebUTIL.setMapInSession(mp, request);
		
		WebUTIL.setMapInSession(mpp, request);
	//	HelperMethods.populate(fb, filmusedvo);
		
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
