package investigationDesk.transactions.controller.utl;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import investigationDesk.InvestigationConfig;
import investigationDesk.transactions.controller.data.PackingListGenerationDATA;
import investigationDesk.transactions.controller.fb.InvestigationRaisingDtlFB;
import investigationDesk.transactions.controller.fb.PackingListGenerationFB;
import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.Inv_PatientAdmissionDtlVO;
import investigationDesk.vo.Inv_RequisitionRaisingPatientVO;
import investigationDesk.vo.Inv_SampleCollectionVO;
import investigationDesk.vo.SampleAcceptanceVO;


public class PackingListGenerationUTL extends ControllerUTIL
{
	public static void setPatientRegistrationEssentials(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		List<Inv_EpisodeVO> lstEpisodeVO=new ArrayList<Inv_EpisodeVO>();
		List<Inv_PatientAdmissionDtlVO> lstPatientVO=new ArrayList<Inv_PatientAdmissionDtlVO>();
		Map mp=new HashMap(); 
	try{
		Inv_RequisitionRaisingPatientVO patVO=null;
		
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		
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
	
		
	public static void getPatList(PackingListGenerationFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
	try{
		List<Inv_SampleCollectionVO> lstsamplePatinetVO=null;
		
		lstsamplePatinetVO=new ArrayList<Inv_SampleCollectionVO>();
		Inv_SampleCollectionVO objSampleCollectionVO= new Inv_SampleCollectionVO();
		WebUTIL.populate(objSampleCollectionVO,fb);
		
		objSampleCollectionVO.setPatCRNo(fb.getPatCRNo());
		objSampleCollectionVO.setFromDate(fb.getFromDate());
		objSampleCollectionVO.setToDate(fb.getToDate());
		
		objSampleCollectionVO.setSampleAreaCode(fb.getSampleAreaCode());
		objSampleCollectionVO.setLabCode(fb.getLabCode());
		
		objSampleCollectionVO.setPackingListGenerationType(fb.getPackingListGenerationType()==null?"0":fb.getPackingListGenerationType());
		
		session.removeAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
		session.removeAttribute(InvestigationConfig.LIST_PACKINGLIST_PATIENT_VO);
		
		session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_BILLED);
		session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED);
		
		
		
		//InvestigationConfig.SELECTED_PAT_DETAILS
		
		UserVO userVO=ControllerUTIL.getUserVO(request);
		lstsamplePatinetVO=PackingListGenerationDATA.getPatList(objSampleCollectionVO,userVO);
			//if(lstsamplePatinetVO!=null)
			//{
				//WebUTIL.populate(fb,lstsamplePatinetVO.get(0)); 
				WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_PACKINGLIST_PATIENT_VO,lstsamplePatinetVO);
				
			//}
				fb.setTempFromDate(objSampleCollectionVO.getFromDate());
				fb.setTempToDate(objSampleCollectionVO.getToDate());
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
	public static void getSampleCollectionArea(PackingListGenerationFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		
	try{
		List<Inv_SampleCollectionVO> lstsampleAreaVO=null;
		
		ControllerUTIL.setSysdate(request);
		
		lstsampleAreaVO=new ArrayList<Inv_SampleCollectionVO>();
		Inv_SampleCollectionVO objSampleVO = new Inv_SampleCollectionVO();
		//WebUTIL.populate(lstsampleAreaVO,fb);
		UserVO userVO=ControllerUTIL.getUserVO(request);
		lstsampleAreaVO=PackingListGenerationDATA.getSampleCollectionArea(userVO);
			if(lstsampleAreaVO!=null)
			{
				WebUTIL.populate(fb,lstsampleAreaVO);
				
				
				List<Entry> sampleList=new ArrayList<Entry>();
				for (Inv_SampleCollectionVO sampleAreaVO : lstsampleAreaVO) {
									
					Entry entObj=new Entry();
					entObj.setValue(sampleAreaVO.getSampleAreaCode());
					entObj.setLabel(sampleAreaVO.getSampleAreaName());
					sampleList.add(entObj);
					
				}
				WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_SAMPLE_COLLECTION_VO,sampleList);
				/*if(sampleList.size()==1)
				{	List<Inv_SampleCollectionVO> lstsamplePatinetVO=null;
					lstsamplePatinetVO=new ArrayList<Inv_SampleCollectionVO>();
					lstsamplePatinetVO=PackingListGenerationDATA.getPatList(objSampleVO,userVO);
					if(lstsamplePatinetVO!=null)
					{
						WebUTIL.populate(fb,lstsamplePatinetVO); 
						WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_SAMPLE_PATIENT_VO,lstsamplePatinetVO);
						
					}
				}*/
			}
		
			
		
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
		public static void savePackingListGeneration(PackingListGenerationFB _fb,HttpServletRequest _request)
		{
			Status objStatus=new Status();	
			HttpSession session=_request.getSession();
			
			// Logic defined as Map<CRNo,Map<RequisitionNo,Map<SampleCode#labCode,List<Inv_SampleCollectionVO>>>
			Map<String,List<Inv_SampleCollectionVO>> mpSampleNo= new LinkedHashMap<String,List<Inv_SampleCollectionVO>>();
			try
			{
				Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
				UserVO _userVO=getUserVO(_request);
			//	HelperMethods.populate(bloodRequisitionDtlVO, _fb);
				
				
				String collAreaCode=_fb.getSampleAreaCode()==null?"":_fb.getSampleAreaCode();
				String printStatus="0"; // Need to discuss
				
				int index=0;
							
				List<String> lstIndices=Arrays.asList(_fb.getChkSamplePatient());
				
				List<Inv_SampleCollectionVO> lstOldSampleCollectionVO=(List<Inv_SampleCollectionVO>)session.getAttribute(InvestigationConfig.LIST_PACKINGLIST_PATIENT_VO);
							
							for(Inv_SampleCollectionVO sampleCollectionVO:lstOldSampleCollectionVO)
							{
								if(lstIndices.contains(Integer.toString(index)))
								{
									String labCode=sampleCollectionVO.getLabCode();
									
									List<Inv_SampleCollectionVO> lstSampleCollectionVO= mpSampleNo.get(labCode);
									
									// First Time Insertion 
									if(lstSampleCollectionVO==null ||!(lstSampleCollectionVO.size()>0))
									{
										lstSampleCollectionVO=new ArrayList<Inv_SampleCollectionVO>();
										
										//Adding List of SampleVO<=>RequisitionDNo's
										lstSampleCollectionVO.add(sampleCollectionVO);
										
										
									}
									else
									{
										//Adding List of SampleVO<=>RequisitionDNo's
										lstSampleCollectionVO.add(sampleCollectionVO);
									}
									mpSampleNo.put(labCode,lstSampleCollectionVO);
								}
								index++;
								
							}// end for loop
								
								
							 
							Map<String,List<Inv_SampleCollectionVO>> mpPackingList=PackingListGenerationDATA.savePackingListDetails(mpSampleNo, _userVO);
							 
							session.removeAttribute(InvestigationConfig.MAP_PACKING_LIST_SAVE);
							
							WebUTIL.setAttributeInSession(_request, InvestigationConfig.MAP_PACKING_LIST_SAVE, mpPackingList);
							 	
							objStatus.add(Status.NEW,"","");
							
								
				   
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
			
	
		public static void showPatDetails(PackingListGenerationFB fb,HttpServletRequest request)
		{
			Status status = new Status();
			HttpSession session=request.getSession();
			UserVO _userVO=getUserVO(request);
			Map mp=new HashMap();
			boolean flag=false;
			try{
				List<Inv_SampleCollectionVO> lstsamplePatinetVO=null;
				List<String> reqList=new ArrayList();
				Inv_SampleCollectionVO voSample=new Inv_SampleCollectionVO();
				
				//fb.setisPatDetailPage("1");
				lstsamplePatinetVO=(List<Inv_SampleCollectionVO>)session.getAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
				String selectedCheckBoxValue=fb.getSelectedCheckbox();
				
				String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
				
				String crNo=arrSelectedRequisitions[0].split("#")[0];
				String reqNO=arrSelectedRequisitions[0].split("#")[1];

				
					for(Inv_SampleCollectionVO objSampleCollectionVO:lstsamplePatinetVO)
					{
						if(flag) break;
						if(objSampleCollectionVO.getPatCRNo().equals(crNo)&&objSampleCollectionVO.getRequisitionNo().equals(reqNO))
						{
							//WebUTIL.populate(fb,objSampleCollectionVO); 
							WebUTIL.setAttributeInSession(request,InvestigationConfig.SELECTED_PAT_DETAILS,objSampleCollectionVO);
							voSample=objSampleCollectionVO;
							flag=true;
							break;
						}
					}
					
					for(String str:arrSelectedRequisitions)
						reqList.add(str);
					
					//WebUTIL.populate(fb,voSample); 
					
					//Billed Transactions
					mp=PackingListGenerationDATA.getBilledPatList(reqList, voSample, _userVO);
					
					WebUTIL.setMapInSession(mp, request);
					
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
	
		
		/**
		 * AJAX Response : Checking Duplicacy of Sample No
		 * @param objFB_p
		 * @param objRequest_p
		 * Added By Pawan Kumar B N  on 2011.12.21
		 */
		public static StringBuffer checkSampleNoDuplicacy(PackingListGenerationFB fb, HttpServletRequest objRequest_p)
		{
			StringBuffer sbAjaxRes = new StringBuffer();
			String strMsgText = "";
			try
			{
				UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
				
				Inv_SampleCollectionVO voSampleCollection = new Inv_SampleCollectionVO();
				
				//Setting Area Code and Sample No
				voSampleCollection.setSampleAreaCode(fb.getSampleAreaCode());
				voSampleCollection.setTempSampleNo(fb.getStrSampleNo());

				boolean isTempSamplePresent = PackingListGenerationDATA.checkSampleNoDuplicacy(voSampleCollection, voUser);
				//sbAjaxRes.append("[{isTempSamplePresent:\'");
				sbAjaxRes.append(isTempSamplePresent);
				//sbAjaxRes.append("\'");
				//sbAjaxRes.append("}]");
				
				
			}
			catch (Exception e)
			{
				strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
				//HisException eObj = 
				new HisException("Investigation", "SampleCollectionUTIL.checkSampleNoDuplicacy() --> ", strMsgText);
				//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
				//eObj = null;
			}
			finally
			{
			}
			return sbAjaxRes;
		}
		
		
		//Getting Lab Combo based on selected Sample Area Code'
		public static void getPackingListEssentials(PackingListGenerationFB fb,HttpServletRequest request)
		{
			Status status = new Status();
			//status.add(Status.NEW, "", "");
			Map mp=new HashMap();
			Map mpp=new HashMap();
		try{
			UserVO userVO=ControllerUTIL.getUserVO(request);
			Inv_SampleCollectionVO voSample=new Inv_SampleCollectionVO();
			
			voSample.setSampleAreaCode(fb.getSampleAreaCode());
			fb.setPackingListGenerationType("0");
			mp=PackingListGenerationDATA.getPackingListGenerationEssentials(voSample,userVO);
			mpp=PackingListGenerationDATA.getPackingListGenerationOnLoad(voSample,userVO);	
			WebUTIL.setMapInSession(mp, request);
			WebUTIL.setMapInSession(mpp, request);
			
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
		
		//Generate All Save Logic
		
		public static void saveAllPackingListGeneration(PackingListGenerationFB _fb,HttpServletRequest _request)
		{
			Status objStatus=new Status();	
			HttpSession session=_request.getSession();
			
			// Logic defined as Map<CRNo,Map<RequisitionNo,Map<SampleCode#labCode,List<Inv_SampleCollectionVO>>>
			Map<String,List<Inv_SampleCollectionVO>> mpSampleNo= new LinkedHashMap<String,List<Inv_SampleCollectionVO>>();
			try
			{
				Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
				UserVO _userVO=getUserVO(_request);
			//	HelperMethods.populate(bloodRequisitionDtlVO, _fb);
				
				
				String collAreaCode=_fb.getSampleAreaCode()==null?"":_fb.getSampleAreaCode();
				String printStatus="0"; // Need to discuss
				
				int index=0;
							
				//List<String> lstIndices=Arrays.asList(_fb.getChkSamplePatient());
				
				Inv_SampleCollectionVO objSampleCollectionVO= new Inv_SampleCollectionVO();
				WebUTIL.populate(objSampleCollectionVO,_fb);
				
				objSampleCollectionVO.setPatCRNo(_fb.getPatCRNo());
				objSampleCollectionVO.setFromDate(_fb.getFromDate());
				objSampleCollectionVO.setToDate(_fb.getToDate());
				
				objSampleCollectionVO.setSampleAreaCode(_fb.getSampleAreaCode());
				objSampleCollectionVO.setLabCode(_fb.getLabCode());
				
				
				List<Inv_SampleCollectionVO> lstOldSampleCollectionVO=PackingListGenerationDATA.getPatList(objSampleCollectionVO,_userVO);;
							
							for(Inv_SampleCollectionVO sampleCollectionVO:lstOldSampleCollectionVO)
							{
									String labCode=sampleCollectionVO.getLabCode();
									
									List<Inv_SampleCollectionVO> lstSampleCollectionVO= mpSampleNo.get(labCode);
									
									// First Time Insertion 
									if(lstSampleCollectionVO==null ||!(lstSampleCollectionVO.size()>0))
									{
										lstSampleCollectionVO=new ArrayList<Inv_SampleCollectionVO>();
										
										//Adding List of SampleVO<=>RequisitionDNo's
										lstSampleCollectionVO.add(sampleCollectionVO);
										
										
									}
									else
									{
										//Adding List of SampleVO<=>RequisitionDNo's
										lstSampleCollectionVO.add(sampleCollectionVO);
									}
									mpSampleNo.put(labCode,lstSampleCollectionVO);
								
							}// end for loop
								
								
							 
							Map<String,List<Inv_SampleCollectionVO>> mpPackingList=PackingListGenerationDATA.savePackingListDetails(mpSampleNo, _userVO);
							 
							session.removeAttribute(InvestigationConfig.MAP_PACKING_LIST_SAVE);
							
							WebUTIL.setAttributeInSession(_request, InvestigationConfig.MAP_PACKING_LIST_SAVE, mpPackingList);
							 	
							objStatus.add(Status.NEW,"","");
							
								
				   
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
		
		
		// Save Logic
			/*	public static void generateDuplicatePackingList(PackingListGenerationFB _fb,HttpServletRequest _request)
				{
					Status objStatus=new Status();	
					HttpSession session=_request.getSession();
					
					// Logic defined as Map<CRNo,Map<RequisitionNo,Map<SampleCode#labCode,List<Inv_SampleCollectionVO>>>
					Map<String,List<Inv_SampleCollectionVO>> mpSampleNo= new LinkedHashMap<String,List<Inv_SampleCollectionVO>>();
					try
					{
						Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
						UserVO _userVO=getUserVO(_request);
					//	HelperMethods.populate(bloodRequisitionDtlVO, _fb);
						
						
						String collAreaCode=_fb.getSampleAreaCode()==null?"":_fb.getSampleAreaCode();
						String printStatus="0"; // Need to discuss
						
						int index=0;
									
						List<String> lstIndices=Arrays.asList(_fb.getChkSamplePatient());
						
						List<Inv_SampleCollectionVO> lstOldSampleCollectionVO=(List<Inv_SampleCollectionVO>)session.getAttribute(InvestigationConfig.LIST_PACKINGLIST_PATIENT_VO);
									
									for(Inv_SampleCollectionVO sampleCollectionVO:lstOldSampleCollectionVO)
									{
										if(lstIndices.contains(Integer.toString(index)))
										{
											String labCode=sampleCollectionVO.getLabCode();
											
											String packingListNo=sampleCollectionVO.getPackingListNo();
											
											List<Inv_SampleCollectionVO> lstSampleCollectionVO= mpSampleNo.get(packingListNo);
											
											// First Time Insertion 
											if(lstSampleCollectionVO==null ||!(lstSampleCollectionVO.size()>0))
											{
												lstSampleCollectionVO=new ArrayList<Inv_SampleCollectionVO>();
												
												//Adding List of SampleVO<=>RequisitionDNo's
												lstSampleCollectionVO.add(sampleCollectionVO);
												
												
											}
											else
											{
												//Adding List of SampleVO<=>RequisitionDNo's
												lstSampleCollectionVO.add(sampleCollectionVO);
											}
											mpSampleNo.put(packingListNo,lstSampleCollectionVO);
										}
										index++;
										
									}// end for loop
										
										
									 
									//Map<String,List<Inv_SampleCollectionVO>> mpPackingList=PackingListGenerationDATA.generateDuplicatePackingList(mpSampleNo, _userVO);
									 
									session.removeAttribute(InvestigationConfig.MAP_PACKING_LIST_SAVE);
									
									WebUTIL.setAttributeInSession(_request, InvestigationConfig.MAP_PACKING_LIST_SAVE, mpSampleNo);
									 	
									objStatus.add(Status.NEW,"","");
									
										
						   
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
			
				*/
				
				//to get packing list details for the duplicate generation of the list
				public static boolean getPackingListDetails(PackingListGenerationFB fb, HttpServletRequest _request)
				{
					Status objStatus = new Status();
					SampleAcceptanceVO packListVO = new SampleAcceptanceVO();
					try
					{
						UserVO userVO = ControllerUTIL.getUserVO(_request);
						ControllerUTIL.setSysdate(_request);

						Map mp=new HashMap(); 
						packListVO.setFromDate(fb.getFromDate()); 
						packListVO.setToDate(fb.getToDate());
						packListVO.setLabCode(fb.getLabCode());
						packListVO.setSampleAreaCode(fb.getSampleAreaCode());
						//sampleAcceptancevo.setAcceptance(fb.getAcceptance());
						mp=PackingListGenerationDATA.getPackingListDetails(packListVO, userVO);
						WebUTIL.setMapInSession(mp, _request);
						HelperMethods.populate(fb, packListVO);

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
				
				
				
				//to generate dup list 
				public static void generateDuplicatePackingList(PackingListGenerationFB _fb,HttpServletRequest _request)
				{
					Status objStatus=new Status();	
					HttpSession session=_request.getSession();
					
					// Logic defined as Map<CRNo,Map<RequisitionNo,Map<SampleCode#labCode,List<Inv_SampleCollectionVO>>>
					Map<String,List<SampleAcceptanceVO>> mpPakListingData= new LinkedHashMap<String,List<SampleAcceptanceVO>>();
					try
					{
						Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
						UserVO _userVO=getUserVO(_request);
					//	HelperMethods.populate(bloodRequisitionDtlVO, _fb);
						
						
						String collAreaCode=_fb.getSampleAreaCode()==null?"":_fb.getSampleAreaCode();
						String printStatus="0"; // Need to discuss
						
						int index=0;
									
						List<String> packNo=Arrays.asList(_fb.getChkPackListData());
						
						Map<String,List<SampleAcceptanceVO>> mp_list=(Map<String,List<SampleAcceptanceVO>>)session.getAttribute(InvestigationConfig.MAP_PACK_LIST_DETAILS_VO);
								
						
						 Iterator itr=mp_list.keySet().iterator();
						 
						 
							while(itr.hasNext())
							{
						 
								String pakListNo=(String)itr.next();
						
								
										if(packNo.contains(pakListNo))
										{
											
											List<SampleAcceptanceVO> lstPakData= mp_list.get(pakListNo);
														
										
											mpPakListingData.put(pakListNo,lstPakData);
										}
										
										
								
										
										
							} 
									//Map<String,List<Inv_SampleCollectionVO>> mpPackingList=PackingListGenerationDATA.generateDuplicatePackingList(mpSampleNo, _userVO);
									 
									session.removeAttribute(InvestigationConfig.MAP_PACKING_LIST_SAVE);
									
									WebUTIL.setAttributeInSession(_request, InvestigationConfig.MAP_PACKING_LIST_SAVE, mpPakListingData);
									 	
									objStatus.add(Status.NEW,"","");
									
										
						   
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
			
				
}
