package new_investigation.transactions.bo;

 
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import new_investigation.InvestigationConfig;
import new_investigation.InvestigationListingConfig;
import new_investigation.transactions.demo;
import new_investigation.transactions.controller.Helper.TemplateProcessingUSE;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.transactions.controller.fb.invAntibioticProcessFB;
import new_investigation.transactions.controller.fb.invFungusProcessFB;
import new_investigation.transactions.controller.fb.invReportHistoryFB;
import new_investigation.transactions.controller.fb.reportDownloadProcessFB;
import new_investigation.transactions.dao.InvDuplicateResultReportPrintingDAO;
import new_investigation.transactions.dao.InvResultEntryDAO;
import new_investigation.transactions.dao.InvResultReValidationDAO;
import new_investigation.transactions.dao.InvResultReportPrintingDAO;
import new_investigation.transactions.dao.InvResultReportPrintingNewDAO;
import new_investigation.transactions.dao.InvResultValidationDAO;
import new_investigation.transactions.dao.InvResultEntryRespDAO;
import new_investigation.transactions.dao.InvValueAuditDAO;
import new_investigation.transactions.dao.InvestigationBillingDAO;
import new_investigation.transactions.dao.OnlinePatientAcceptanceDAO;
import new_investigation.transactions.dao.InvPatientAcceptanceRespDAO;
import new_investigation.transactions.dao.InvResultEntryRespDAO;
import new_investigation.transactions.dao.InvPatientAcceptanceRespDAO;
import new_investigation.transactions.dao.InvPatientAcceptanceRespDAOi;
import new_investigation.transactions.dao.PackingListGenerationDAO;
import new_investigation.transactions.dao.SampleAcceptanceDAO;
import new_investigation.transactions.dao.SampleAcceptanceDAOi;
import new_investigation.transactions.dao.SampleCollectionCumAcceptanceDAO;
import new_investigation.transactions.dao.SampleCollectionDAO;
import new_investigation.transactions.dao.TestWiseConsumableDAO;
import new_investigation.transactions.dao.departmentSpecificResultEntryDAO;
import new_investigation.transactions.dao.externalInvestigationCaptureDAO;
import new_investigation.transactions.dao.filmUsedDAO;
import new_investigation.transactions.dao.filmUsedDAOi;
import new_investigation.transactions.dao.invAntibioticProcessDAO;
import new_investigation.transactions.dao.invFungusProcessDAO;
import new_investigation.transactions.dao.invReportAddendumDAO;
import new_investigation.transactions.dao.invReportHistoryDAO;
import new_investigation.transactions.dao.invReportInProcessDAO;
import new_investigation.transactions.dao.invStatusDashboardDAO;
import new_investigation.transactions.dao.machineEnquiryDAO;
import new_investigation.transactions.dao.machineResultEntryDAO;
import new_investigation.transactions.dao.reportDownloadProcessDAO;
import new_investigation.transactions.dao.testAvailabilityDAO;
import new_investigation.transactions.dao.viewExternalInvDAO;
import new_investigation.transactions.dao.Helper.InvestigationTemplateDataHelper;
import new_investigation.vo.BookMarkVO;
import new_investigation.vo.InvCriteriaCodeVO;
import new_investigation.vo.InvDuplicateResultReportPrintingVO;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.InvResultReportPrintingNewVO;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.InvValueAuditVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.Inv_ictc_VO;
import new_investigation.vo.InvestigationRequisitionBillDtlVO;
import new_investigation.vo.InvestigationRequistionVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.OnlinePatientAcceptanceVO;
import new_investigation.vo.InvPatientAcceptanceRespVO;
import new_investigation.vo.InvPatientAcceptanceRespVO;
import new_investigation.vo.RequistionHeaderVO;
import new_investigation.vo.SampleAcceptanceVO;
import new_investigation.vo.SampleCollectionCumAcceptanceVO;
import new_investigation.vo.TestMandRefMasterVO;
import new_investigation.vo.antibioticprocessVO;
import new_investigation.vo.externalInvestigationCaptureVO;
import new_investigation.vo.filmUsedVO;
import new_investigation.vo.invAntibioticProcessVO;
import new_investigation.vo.invFungusProcessVO;
import new_investigation.vo.invReportAddendumVO;
import new_investigation.vo.invReportHistoryVO;
import new_investigation.vo.invReportInProcessVO;
import new_investigation.vo.machineEnquiryVO;
import new_investigation.vo.machineResultEntryVO;
import new_investigation.vo.reportDownloadProcessVO;
import new_investigation.vo.testAvailabilityVO;
import new_investigation.vo.viewExternalInvVO;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.TestWiseConsumableVO;
//import mrd.transaction.dao.*;
//import mrd.transaction.delegate.*;
//import mrd.MrdConfig;
import new_investigation.vo.template.TriStringObject;
import new_investigation.vo.template.invStatusDashboardVO;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.ibm.icu.util.Calendar;



import new_investigation.vo.InvResultValidationRespVO;

/**
 * @author 
 * 
 */
public class InvPatientAcceptanceRespBO implements InvPatientAcceptanceRespBOi {
	
	public Map AjaxGetPatAcceptanceReqnList(InvPatientAcceptanceRespVO invPatientAcceptanceRespVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<InvPatientAcceptanceRespVO> lstInvPatientAcceptanceRespVO=new ArrayList<InvPatientAcceptanceRespVO>();


		try
		{
			tx.begin();
			InvPatientAcceptanceRespDAOi invPatientAcceptanceRespDAOi = new InvPatientAcceptanceRespDAO(tx);
			
			lstInvPatientAcceptanceRespVO=invPatientAcceptanceRespDAOi.AjaxGetPatAcceptanceReqnList(invPatientAcceptanceRespVO, _UserVO);
			/*
			if(invPatientAcceptanceRespVO.getAcceptedToNotAccepted().equals("2")) {
			lstInvPatientAcceptanceRespVO=invPatientAcceptanceRespDAOi.setAcceptedPatientEssentials(invPatientAcceptanceRespVO, _UserVO);
			}else {
			lstInvPatientAcceptanceRespVO=invPatientAcceptanceRespDAOi.setPatientEssentials(invPatientAcceptanceRespVO, _UserVO);
			}
			*/
			mp.put(InvestigationConfig.LIST_EPISODE_VO,lstInvPatientAcceptanceRespVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}
	
	public  JsonObject savePatientDetails(Map<String,Map<String,Map<String,List<InvPatientAcceptanceRespVO>>>> mp,UserVO _userVO)
	{	
		JsonObject jsonResponse = new JsonObject();
		JsonArray acceptedPatDataArr = new JsonArray();
		String isSuccess="0";
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List listReqdtlId=new ArrayList();
		/*String sameLabNo="";
		String sameLabCode="";*/
		System.out.println("savePatientDetailss");
		try
		{    
			tx.begin();
			InvPatientAcceptanceRespDAO objInvPatientAcceptanceRespDAO=new InvPatientAcceptanceRespDAO(tx);
                 Map<String,String> keyy=new HashMap<String,String>();
			InvestigationBillingDAO invBillingDAO=new InvestigationBillingDAO(tx);

			InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();

			// Step-1 :: Get Map Of Requisitions from Map of CrNo's
			Set setCrNo=mp.keySet();

			Iterator itrCrNo=setCrNo.iterator();

			//iterate over Crno's
			while(itrCrNo.hasNext())   
			{
				
				String sameLabNo="";
				String sameLabCode="";
				
				// Get CrNo from iterator
				String crNo=(String)itrCrNo.next();

				// Get Map of Requisitions from Map of Cr No's
				Map<String,Map<String,List<InvPatientAcceptanceRespVO>>> mpReqNo=mp.get(crNo);

				//Getting Set of Requisitions
				Set setReqNo=mpReqNo.keySet();

				Iterator itrReqNo=setReqNo.iterator();

				//Iterate over ReqNo's
				while(itrReqNo.hasNext())
				{
					// Get Requistion No from iterator
					String reqNo=(String)itrReqNo.next();

					// Update in Requisition Header table for Mobile No, emailId,Address
					boolean isReqHeaderUpdated=false;


					Map<String,List<InvPatientAcceptanceRespVO>> mpLabCode=mpReqNo.get(reqNo);

					Set setLabCode=mpLabCode.keySet();

					Iterator itrLabCode=setLabCode.iterator();


					while(itrLabCode.hasNext()) 
					{
						
						
						String labCode=(String)itrLabCode.next();
						
						//added by prashant
						List<InvPatientAcceptanceRespVO> lstPatientCollectionVO=mpLabCode.get(labCode);
						String acceptedToNotAccepted=lstPatientCollectionVO.get(0).getAcceptedToNotAccepted();
						String patAccNo="";
						//get acc_no if already accepted
						if(acceptedToNotAccepted.equals("2")) {
							patAccNo=objInvPatientAcceptanceRespDAO.selectPataccNoPatientAcceptedDtl(lstPatientCollectionVO.get(0),_userVO);
						} else 
					     	{
							String sequence_Hash_yymmdd=objInvPatientAcceptanceRespDAO.generatePatientNoSequence(labCode, _userVO);  // Returns sequence#yymmdd  //attention
							String sequence=sequence_Hash_yymmdd.split("#")[0];
							String yymmdd=sequence_Hash_yymmdd.split("#")[1]; //attention
							patAccNo=labCode+""+yymmdd+""+sequence; 
							//Logic to check the sequence is '100001' 

							if(sequence.equals(InvestigationConfig.SAMPLE_NO_SEQUENCE_INVESTIGATION)) //100001
							{
								objInvPatientAcceptanceRespDAO.insertPatientNoSequenceInMaintainer(labCode,sequence,yymmdd,_userVO);//attention
							}
							else
							{
								objInvPatientAcceptanceRespDAO.updatePatientNoSequenceInMaintainer(sequence, labCode, _userVO);//attention
							}
						}
						
						
						
						//commented by prashant
						//List<InvPatientAcceptanceRespVO> lstPatientCollectionVO=mpLabCode.get(labCode);
						// Loop over VO for saving
					
						for(InvPatientAcceptanceRespVO voPatient:lstPatientCollectionVO)
						{
					
							// Update Requisition Header (only once based on flag isReqHeaderUpdated)
							if(!isReqHeaderUpdated)
							{
								objInvPatientAcceptanceRespDAO.updateRequisitionHeaderForPatient( voPatient, voPatient.getAddress(), reqNo, _userVO);
								objInvPatientAcceptanceRespDAO.updateRequisitionHeaderForPatient_dtl( voPatient, voPatient.getAddress(), reqNo, _userVO);
								
								
								isReqHeaderUpdated=true;
							}
							
							//Logic For Auto Lab No Generation
						 
							List<InvPatientAcceptanceRespVO> lstAutoLabNOCon=new ArrayList<InvPatientAcceptanceRespVO>();
                             
							//lstAutoLabNOCon=objInvPatientAcceptanceRespDAO.getPatientAcceptanceAutoLabNOConfig(voPatient, _userVO);
							
							///int lstofSize=lstAutoLabNOCon.size();
							
							
							
							
							
							
							if(voPatient.getCheckAutoLabConfig().equals("2") )
							{
								
								
								

								//for(InvPatientAcceptanceRespVO autoLabVo:lstAutoLabNOCon)
								//{
									
									//if(!autoLabVo.getLabNoFormat().equals("-"))
									//{
										
									
									String autoLabNumber=voPatient.getLabNoFormat();
									String Str = new String(autoLabNumber);
									int MainStrlength=Str.length();
									String[] dateFormate = Str.split("&");
									String subDateFormate=dateFormate[0];
									String xMainValue=dateFormate[1];
									//getting the DateFormate number
									String sequence_LabNO_yymmdd=objInvPatientAcceptanceRespDAO.generateLabNoDateSequence(subDateFormate, _userVO);  // Returns Date Formate
									String entryDate= voPatient.getInitDate();
									DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
									Date date = df.parse(entryDate);
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									Calendar c = Calendar.getInstance();
									c.setTime(date); // Now use entry date.
                                     String finalDate="";
									
									if(voPatient.getInitType().equals("m"))
									{
										
										int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
										  
										String[] SplitDate=entryDate.split("-");
										
										 finalDate=SplitDate[0]+"-"+SplitDate[1]+"-"+daysInMonth;
										 date=df.parse(finalDate);
										 
									}
									if(voPatient.getInitType().equals("d"))
									{
										
										date = df.parse(entryDate);
										//c.add(Calendar.DATE,1); 
									}
									if(voPatient.getInitType().equals("y"))
									{
										String[] SplitDate=entryDate.split("-");
										
										 finalDate=SplitDate[0]+"-"+12+"-"+31;
										date = df.parse(finalDate);
									}
									if(voPatient.getInitType().equals("w"))
									{
										int weekOfTheDay= c.get(Calendar.DAY_OF_WEEK); 
										//"2:Monday", "3:Tuesday", "4:Wednesday", "5:Thursday", "6:Friday", "7:Saturday", "1:Sunday"
										String[] SplitDate;
										/*c.add(Calendar.DATE,7);
										String finalEntryDate = sdf.format(c.getTime());
										*/
										switch (weekOfTheDay) {
										case 1:date = df.parse(entryDate);
    											break;
											  	
										case 2: c.add(Calendar.DATE,6);
				                               finalDate=sdf.format(c.getTime()); 
				                                date = df.parse(finalDate);
        										break;
										case 3: c.add(Calendar.DATE,5);
							                          finalDate=sdf.format(c.getTime()); 
							                          date = df.parse(finalDate);
        										break;
										case 4:c.add(Calendar.DATE,4);
				                                finalDate=sdf.format(c.getTime()); 
				                                date = df.parse(finalDate);
        										break;
										case 5:c.add(Calendar.DATE,3);
				                               finalDate=sdf.format(c.getTime()); 
				                                date = df.parse(finalDate);
        										break;
										case 6:c.add(Calendar.DATE,2);
				                                finalDate=sdf.format(c.getTime()); 
				                                date = df.parse(finalDate);
        										break;
										case 7:c.add(Calendar.DATE,1);
				                                finalDate=sdf.format(c.getTime()); 
				                               date = df.parse(finalDate);
										    break;
										 default:
											break;
										}
										 
									}
									
									String finalEntryDate = sdf.format(date);
									Date todaydateobj = new Date();
									SimpleDateFormat dateFormatObj = new SimpleDateFormat("yyyy-MM-dd");
									String strDate= dateFormatObj.format(todaydateobj);
									int EntryDatecomparWithSysDAte = strDate.compareTo(finalEntryDate);	//attention	
									//String xMainValue=Str.substring(subStrDateLength,MainStrlength);
									int xMainLen=xMainValue.length();
									
									
									//For From Series Count
									String FinalLabNo="";
									int fromSerValue;
									  
									String fromSer=voPatient.getFromSeries();
									int fromserlen=fromSer.length();
									int xSubLen=xMainLen-fromserlen;
									String value=xMainValue.substring(0,xSubLen);
									String value2=xMainValue.substring(xSubLen,xMainLen);
									String getXvalue ="";
									String getXvalue2 ="";
									if(!value.equals(""))
									{
										getXvalue=value.replace("X","0");
									}
									if(!value2.equals(""))
									{
										getXvalue2=value2.replace(value2, fromSer);
									}
									String xVal=getXvalue+getXvalue2;
									int xValLen=xVal.length();
									int gnumnoofseqdigits1=Integer.parseInt(voPatient.getNoOfSeqDigit());
									 String subStrvalueLab1=xVal.substring(Math.max(0,xValLen - gnumnoofseqdigits1));
								
									 //check for 1 labcode generate 1 labno
									if(sameLabCode.equals(voPatient.getLabCode()))
									{
										
									}
									else
									{
                                      String runningLabNO="";
                                      
                                      if(voPatient.getIslabNoAreaWise().equals("0"))
                                      runningLabNO  =objInvPatientAcceptanceRespDAO.checkAutoGenFormateRunningLabNo(voPatient, _userVO);
                                      
                                      if(voPatient.getIslabNoAreaWise().equals("1"))
                                      runningLabNO  =objInvPatientAcceptanceRespDAO.checkAutoGenFormateRunningLabNoAreaWise(voPatient, _userVO);
                                      
                                      sameLabCode=voPatient.getLabCode();
									 sameLabNo=runningLabNO;
									}
                                      voPatient.setRunningLabNo(sameLabNo);
									
									 if(voPatient.getRunningLabNo()!=null)
										{
										 String  sampleNO=voPatient.getRunningLabNo();
											int sampleNOLen=sampleNO.length();
											 int gnumnoofseqdigits=Integer.parseInt(voPatient.getNoOfSeqDigit());
											 String subStrvalueLab=sampleNO.substring(Math.max(0, sampleNO.length() - gnumnoofseqdigits));
											
											 //if Date is not Available in Formate Case Modify On 22-06-2015
											 String constVAlue="";
											 try
											 {
										  constVAlue =sampleNO.substring(subDateFormate.length(),sampleNOLen-gnumnoofseqdigits);
											 }
											 catch (Exception e)
												{
												 constVAlue="";
												}
											 fromSerValue= Integer.parseInt(subStrvalueLab);
										}
									 else
									 {
										 fromSerValue = Integer.parseInt(subStrvalueLab1);
									 }
									
									if(sequence_LabNO_yymmdd==null)
									 {
										FinalLabNo=xVal;
									 }
									 else
									 {
										 FinalLabNo=sequence_LabNO_yymmdd+xVal;
									 }
									
									
									 

									//For To Series Count
									String toSer=voPatient.getToSeries();
									int toserlen=toSer.length();
									int xSubLenToSer=xMainLen-toserlen;
									String valueToSer=xMainValue.substring(0,xSubLenToSer);
									String valueToSer2=xMainValue.substring(xSubLenToSer,xMainLen);
									String getXvalueToSer="";
									String getXvalueToSer2="";
									if(!valueToSer.equals(""))
									{
										getXvalueToSer=valueToSer.replace("X","0");
									}
									if(!valueToSer2.equals(""))
									{
										getXvalueToSer2=valueToSer2.replace(valueToSer2, toSer);
									}
									String xValToSer=getXvalueToSer+getXvalueToSer2;
									 
									int xValToSerLen=xVal.length();
									int gnumnoofseqdigits2=Integer.parseInt(voPatient.getNoOfSeqDigit());
									 String subStrvalueLab2=xValToSer.substring(Math.max(0,xValToSerLen - gnumnoofseqdigits2));
									int toSerValue = Integer.parseInt(subStrvalueLab2);
									if(EntryDatecomparWithSysDAte<=0)//for EntryDatecomparWithSysDAte value is Negative OR Zero Case
									{
										if(voPatient.getRunningLabNo()==null)
										{

											voPatient.setLabNoConfiguration(FinalLabNo);
										}
										else
										{

											if(fromSerValue<toSerValue)
											{
												String  LabNO=voPatient.getRunningLabNo();
												int LabNOlen=LabNO.length();
												 int gnumnoofseqdigits=Integer.parseInt(voPatient.getNoOfSeqDigit());
												 String subStrvalueLab=LabNO.substring(Math.max(0, LabNO.length() - gnumnoofseqdigits));
												// String constVAlue =LabNO.substring(subDateFormate.length(),LabNOlen-gnumnoofseqdigits);
												 
												 //if Date is not Available in Formate Case Modify On 22-06-2015
												 String constVAlue="";
												 try
												 {
													 constVAlue =LabNO.substring(subDateFormate.length(),LabNOlen-gnumnoofseqdigits);
												 }
												 catch (Exception e)
													{
													 constVAlue="";
													}
											 	int toSubStrValueLAb= Integer.parseInt(subStrvalueLab);
												++toSubStrValueLAb;
												int length = String.valueOf(toSubStrValueLAb).length();
												String leftPadded = StringUtils.leftPad("" + toSubStrValueLAb,subStrvalueLab.length(), "0");
												String finalLab="";
												if(sequence_LabNO_yymmdd==null)
												{
													finalLab=constVAlue+leftPadded;
												}
												else
												{
													finalLab=sequence_LabNO_yymmdd+constVAlue+leftPadded;
												}
												voPatient.setLabNoConfiguration(finalLab);
											}
											else  //for EntryDatecomparWithSysDAte value is Positive Case
											{
												voPatient.setLabNoConfiguration(FinalLabNo);
											}
										}
									}


									else 
									{
										
										voPatient.setLabNoConfiguration(FinalLabNo);
										
										
										objInvPatientAcceptanceRespDAO.updatePatientAccInhivtlabnoconfmstResetLabNO(voPatient,finalEntryDate,_userVO);
										Date todayDateob = new Date();
										SimpleDateFormat dateobj = new SimpleDateFormat("yyyy-MM-dd");
										String strSysDate= dateobj.format(todayDateob);
										Date todayDat =dateobj.parse(strSysDate) ; 
										Calendar c1 = Calendar.getInstance();
										c1.setTime(todayDat); // Now use SYDATE date.

										if(voPatient.getInitType().equals("m"))
										{
											int daysInMonth = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
											  
											String[] SplitDate=strSysDate.split("-");
											
											 finalDate=SplitDate[0]+"-"+SplitDate[1]+"-"+daysInMonth;
											 todayDat=dateobj.parse(finalDate);
										}
										if(voPatient.getInitType().equals("d"))
										{
											todayDat=dateobj.parse(strSysDate);
										}
										if(voPatient.getInitType().equals("y"))
										{
											String[] SplitDate=strSysDate.split("-");
											
											 finalDate=SplitDate[0]+"-"+12+"-"+31;
											 todayDat = dateobj.parse(finalDate);
										}
										if(voPatient.getInitType().equals("w"))
										{
											int weekOfTheDay= c.get(Calendar.DAY_OF_WEEK); 
											//"2:Monday", "3:Tuesday", "4:Wednesday", "5:Thursday", "6:Friday", "7:Saturday", "1:Sunday"
											String[] SplitDate;
											/*c.add(Calendar.DATE,7);
											String finalEntryDate = sdf.format(c.getTime());
											*/
											switch (weekOfTheDay) {
											case 1:date = df.parse(entryDate);
	    											break;
												  	
											case 2: c.add(Calendar.DATE,6);
					                               finalDate=sdf.format(c.getTime()); 
					                                date = df.parse(finalDate);
	        										break;
											case 3: c.add(Calendar.DATE,5);
								                          finalDate=sdf.format(c.getTime()); 
								                          date = df.parse(finalDate);
	        										break;
											case 4:c.add(Calendar.DATE,4);
					                                finalDate=sdf.format(c.getTime()); 
					                                date = df.parse(finalDate);
	        										break;
											case 5:c.add(Calendar.DATE,3);
					                               finalDate=sdf.format(c.getTime()); 
					                                date = df.parse(finalDate);
	        										break;
											case 6:c.add(Calendar.DATE,2);
					                                finalDate=sdf.format(c.getTime()); 
					                                date = df.parse(finalDate);
	        										break;
											case 7:c.add(Calendar.DATE,1);
					                                finalDate=sdf.format(c.getTime()); 
					                               date = df.parse(finalDate);
											    break;
											 default:
												break;
											}
											  
										}
										  finalEntryDate = dateobj.format(todayDat);
										
									}
									objInvPatientAcceptanceRespDAO.updatePatientAccInhivtlabnoconfmst(voPatient,finalEntryDate,_userVO);
								
									//}
									//else
									//{
										
									//}
									
									//}//for Loop
									if(voPatient.getAccepted().equals("1"))
									{
										/* Added by Prashant */
										if(voPatient.getAcceptedToNotAccepted().equals("2")) {
										objInvPatientAcceptanceRespDAO.updatePatientAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);
										voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_ACCEPTED); //6
										objInvPatientAcceptanceRespDAO.updateRequisitionDtlForPatientAccepted(voPatient,  _userVO);
										
										listReqdtlId.add("CRequisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +"                  Daily Lab No= "+voPatient.getLabNoConfiguration());
										
										JsonObject acceptedPat = new JsonObject();
										acceptedPat.addProperty("crNo", voPatient.getCrNo());
										acceptedPat.addProperty("requisitionNo", voPatient.getRequisitionNo());
										acceptedPat.addProperty("requisitionDNo", "");
										acceptedPat.addProperty("testName", voPatient.getTestName());
										acceptedPat.addProperty("labNo", voPatient.getLabNoConfiguration());
										acceptedPat.addProperty("patAcceptanceStatusCode", "2.2");
										acceptedPat.addProperty("patAcceptanceStatusComment", "Accepted Patient Test Updated");
										acceptedPat.addProperty("isSuccess", "1");
										acceptedPatDataArr.add(acceptedPat);

										}
										else {
											objInvPatientAcceptanceRespDAO.insertPatientDtlForPatient(patAccNo,voPatient,  _userVO);
											voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_ACCEPTED); //6
											objInvPatientAcceptanceRespDAO.updateRequisitionDtlForPatientAccepted(voPatient,  _userVO);
											listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +"                  Daily Lab No= "+voPatient.getLabNoConfiguration());
											
											JsonObject acceptedPat = new JsonObject();
											acceptedPat.addProperty("crNo", voPatient.getCrNo());
											acceptedPat.addProperty("requisitionNo", voPatient.getRequisitionNo());
											acceptedPat.addProperty("requisitionDNo", "");
											acceptedPat.addProperty("testName", voPatient.getTestName());
											acceptedPat.addProperty("labNo", voPatient.getLabNoConfiguration());
											acceptedPat.addProperty("patAcceptanceStatusCode", "2.1");
											acceptedPat.addProperty("patAcceptanceStatusComment", "Patient Test Accepted");
											acceptedPat.addProperty("isSuccess", "1");
											acceptedPatDataArr.add(acceptedPat);
										}
									}
									else
								{       /* Added by Prashant */
										if(voPatient.getAcceptedToNotAccepted().equals("2")) {
											objInvPatientAcceptanceRespDAO.updatePatientNotAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);
										} else {
											objInvPatientAcceptanceRespDAO.insertPatientNotAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);
										}
										
										

										if(voPatient.getRejectionAction().equals("1"))
										{
											
											
											if(voPatient.getRejectionReason().equals("-2"))
											{
												
												String maxsize=objInvPatientAcceptanceRespDAO.getmaxidreasonmstt(voPatient,  _userVO);
												voPatient.setRejectionReason(maxsize);
												objInvPatientAcceptanceRespDAO.insertreasonmst(voPatient, _userVO,"2",maxsize);
												
												
											}
											
											//calling refund functn chanksrefund
											refund(patAccNo,voPatient,  _userVO,keyy);
											
											
											
											
										    voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_CANCELLED); //9
											objInvPatientAcceptanceRespDAO.updateRequisitionDtlForPatient(voPatient,  _userVO);	 
											listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +",                 has been  Canceled");
											
											JsonObject acceptedPat = new JsonObject();
											acceptedPat.addProperty("crNo", voPatient.getCrNo());
											acceptedPat.addProperty("requisitionNo", voPatient.getRequisitionNo());
											acceptedPat.addProperty("requisitionDNo", "");
											acceptedPat.addProperty("testName", voPatient.getTestName());
											acceptedPat.addProperty("labNo", voPatient.getLabNoConfiguration());
											acceptedPat.addProperty("patAcceptanceStatusCode", "3");
											acceptedPat.addProperty("patAcceptanceStatusComment", "Patient Test Canceled");
											acceptedPat.addProperty("isSuccess", "1");
											acceptedPatDataArr.add(acceptedPat);
										}	
										if(voPatient.getRejectionAction().equals("2"))
										{


											voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_RESCHEDULED); //10
											objInvPatientAcceptanceRespDAO.updateRequisitionDtlForPatientRescheduled(voPatient,  _userVO);	 
											listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +",                  will be Re-Scheduled");
											
											JsonObject acceptedPat = new JsonObject();
											acceptedPat.addProperty("crNo", voPatient.getCrNo());
											acceptedPat.addProperty("requisitionNo", voPatient.getRequisitionNo());
											acceptedPat.addProperty("requisitionDNo", "");
											acceptedPat.addProperty("testName", voPatient.getTestName());
											acceptedPat.addProperty("labNo", voPatient.getLabNoConfiguration());
											acceptedPat.addProperty("patAcceptanceStatusCode", "4");
											acceptedPat.addProperty("patAcceptanceStatusComment", "Patient Test Will be Re-Scheduled");
											acceptedPat.addProperty("isSuccess", "1");
											acceptedPatDataArr.add(acceptedPat);
										}	

									}
								
							}//if loop
							else
							{

								if(voPatient.getAccepted().equals("1"))
								{
									/* Added by Prashant */
									if(voPatient.getAcceptedToNotAccepted().equals("2")) {
									objInvPatientAcceptanceRespDAO.updatePatientAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);
									voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_ACCEPTED); //6
									objInvPatientAcceptanceRespDAO.updateRequisitionDtlForPatientAccepted(voPatient,  _userVO);
									listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +"                  Daily Lab No= "+voPatient.getLabNoConfiguration());
									
									JsonObject acceptedPat = new JsonObject();
									acceptedPat.addProperty("crNo", voPatient.getCrNo());
									acceptedPat.addProperty("requisitionNo", voPatient.getRequisitionNo());
									acceptedPat.addProperty("requisitionDNo", "");
									acceptedPat.addProperty("testName", voPatient.getTestName());
									acceptedPat.addProperty("labNo", voPatient.getLabNoConfiguration());
									acceptedPat.addProperty("patAcceptanceStatusCode", "2.2");
									acceptedPat.addProperty("patAcceptanceStatusComment", "Accepted Patient Test Updated");
									acceptedPat.addProperty("isSuccess", "1");
									acceptedPatDataArr.add(acceptedPat);

									}
									else {
										objInvPatientAcceptanceRespDAO.insertPatientDtlForPatient(patAccNo,voPatient,  _userVO);
										voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_ACCEPTED); //6
										objInvPatientAcceptanceRespDAO.updateRequisitionDtlForPatientAccepted(voPatient,  _userVO);
										listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +"                  Daily Lab No= "+voPatient.getLabNoConfiguration());
										
										JsonObject acceptedPat = new JsonObject();
										acceptedPat.addProperty("crNo", voPatient.getCrNo());
										acceptedPat.addProperty("requisitionNo", voPatient.getRequisitionNo());
										acceptedPat.addProperty("requisitionDNo", "");
										acceptedPat.addProperty("testName", voPatient.getTestName());
										acceptedPat.addProperty("labNo", voPatient.getLabNoConfiguration());
										acceptedPat.addProperty("patAcceptanceStatusCode", "2.1");
										acceptedPat.addProperty("patAcceptanceStatusComment", "Patient Test Accepted");
										acceptedPat.addProperty("isSuccess", "1");
										acceptedPatDataArr.add(acceptedPat);
									}
									
								}
								else
								{ /* Added by Prashant */
									if(voPatient.getAcceptedToNotAccepted().equals("2")) {
										objInvPatientAcceptanceRespDAO.updatePatientNotAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);
									} else {
										objInvPatientAcceptanceRespDAO.insertPatientNotAcceptedDtlForPatient(patAccNo,voPatient,  _userVO);
									}
									
									if(voPatient.getRejectionAction().equals("1"))
									{
										
										
										if(voPatient.getRejectionReason().equals("-2"))
										{
											
											String maxsize=objInvPatientAcceptanceRespDAO.getmaxidreasonmstt(voPatient,  _userVO);
											voPatient.setRejectionReason(maxsize);
											objInvPatientAcceptanceRespDAO.insertreasonmst(voPatient, _userVO,"2",maxsize);
											
											
										}
										
										//calling refund functn chanksrefund
										refund(patAccNo,voPatient,  _userVO,keyy); //refund prashant
										
										
										
										voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_CANCELLED); //9
										objInvPatientAcceptanceRespDAO.updateRequisitionDtlForPatient(voPatient,  _userVO);	 
										listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +",                 has been  Canceled");
										
										JsonObject acceptedPat = new JsonObject();
										acceptedPat.addProperty("crNo", voPatient.getCrNo());
										acceptedPat.addProperty("requisitionNo", voPatient.getRequisitionNo());
										acceptedPat.addProperty("requisitionDNo", "");
										acceptedPat.addProperty("testName", voPatient.getTestName());
										acceptedPat.addProperty("labNo", voPatient.getLabNoConfiguration());
										acceptedPat.addProperty("patAcceptanceStatusCode", "3");
										acceptedPat.addProperty("patAcceptanceStatusComment", "Patient Test Canceled");
										acceptedPat.addProperty("isSuccess", "1");
										acceptedPatDataArr.add(acceptedPat);

									}	
									if(voPatient.getRejectionAction().equals("2"))
									{
										
										if(voPatient.getRejectionReason().equals("-2"))
										{
											
											String maxsize=objInvPatientAcceptanceRespDAO.getmaxidreasonmstt(voPatient,  _userVO);
											voPatient.setRejectionReason(maxsize);
											objInvPatientAcceptanceRespDAO.insertreasonmst(voPatient, _userVO,"2",maxsize);
											
											
										}
										
										voPatient.setReqDtlStatus(InvestigationConfig.REQUISITION_DTL_RESCHEDULED); //10
										objInvPatientAcceptanceRespDAO.updateRequisitionDtlForPatientRescheduled(voPatient,  _userVO);	 
										listReqdtlId.add("Requisition No= "+voPatient.getRequisitionNo()+"       Test Name= "+voPatient.getTestName() +",                 will be Re-Scheduled");
										
										JsonObject acceptedPat = new JsonObject();
										acceptedPat.addProperty("crNo", voPatient.getCrNo());
										acceptedPat.addProperty("requisitionNo", voPatient.getRequisitionNo());
										acceptedPat.addProperty("requisitionDNo", "");
										acceptedPat.addProperty("testName", voPatient.getTestName());
										acceptedPat.addProperty("labNo", voPatient.getLabNoConfiguration());
										acceptedPat.addProperty("patAcceptanceStatusCode", "4");
										acceptedPat.addProperty("patAcceptanceStatusComment", "Patient Test Will be Re-Scheduled");
										acceptedPat.addProperty("isSuccess", "1");
										acceptedPatDataArr.add(acceptedPat);

									}	

								}


							}

//							String tariffId="";
//							String serviceId="";
//							//Update Billing
//							if(voPatient.getGroupType() == null)
//							{
//								tariffId = voPatient.getTestCode();
//								serviceId = "1";
//							}
//							else if(voPatient.getGroupType().equals("1") || voPatient.getGroupType().equals("2"))
//							{
//								tariffId = voPatient.getLabCode()+voPatient.getGroupCode();
//								serviceId = "4";
//							}  
//							else
//							{
//								tariffId = voPatient.getTestCode();
//								serviceId = "1";
//							}

							

						}

					}
				}
				//tx.commitAll()
			}
			
			isSuccess = "1";
			
		}
		catch (HisRecordNotFoundException e)
		{
			isSuccess = "0";
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{	
			isSuccess = "0";
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{	
			isSuccess = "0";
			e.printStackTrace();
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (Exception e)
		{	
			isSuccess = "0";
			e.printStackTrace();
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
			jsonResponse.add("acceptedPatDataArr", acceptedPatDataArr);
			jsonResponse.addProperty("isSuccess", isSuccess);
		}
		
		return jsonResponse;

	}
	
	public Map  LabCombos(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();

		try
		{
			tx.begin();
			InvPatientAcceptanceRespDAOi onlinePatientDaoi = new InvPatientAcceptanceRespDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			labcombo=onlinePatientDaoi.getLabCombos(onlinePatientvo,_UserVO);
			mp.put(InvestigationConfig.LAB_COMBO, labcombo);


		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}


	public Map setPatientEssentialsOnLoad(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<InvPatientAcceptanceRespVO> lstInvPatientAcceptanceRespVO=new ArrayList<InvPatientAcceptanceRespVO>();


		try
		{
			tx.begin();
			InvPatientAcceptanceRespDAOi onlinePatientDaoi = new InvPatientAcceptanceRespDAO(tx);

			lstInvPatientAcceptanceRespVO=onlinePatientDaoi.setPatientEssentialsOnLoad(onlinePatientvo, _UserVO);

			mp.put(InvestigationConfig.LIST_EPISODE_VO,lstInvPatientAcceptanceRespVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}
	
	
	
	public Map setPatientEssentials(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<InvPatientAcceptanceRespVO> lstInvPatientAcceptanceRespVO=new ArrayList<InvPatientAcceptanceRespVO>();


		try
		{
			tx.begin();
			InvPatientAcceptanceRespDAOi onlinePatientDaoi = new InvPatientAcceptanceRespDAO(tx);
			
			if(onlinePatientvo.getAcceptedToNotAccepted().equals("2")) {
			lstInvPatientAcceptanceRespVO=onlinePatientDaoi.setAcceptedPatientEssentials(onlinePatientvo, _UserVO);
			}else {
			lstInvPatientAcceptanceRespVO=onlinePatientDaoi.setPatientEssentials(onlinePatientvo, _UserVO);
			}

			mp.put(InvestigationConfig.LIST_EPISODE_VO,lstInvPatientAcceptanceRespVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}
	
	
	public Map patientDetails(List<InvPatientAcceptanceRespVO> onlinePatientvoo,List<String> reqList, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List<InvPatientAcceptanceRespVO> lstInvPatientAcceptanceRespVO=new ArrayList<InvPatientAcceptanceRespVO>();
		List<InvPatientAcceptanceRespVO> reqLis=new ArrayList<InvPatientAcceptanceRespVO>();
		String reqType="";
		List criteriacombo=new ArrayList();
		try
		{
			tx.begin();
			InvPatientAcceptanceRespDAO onlinePatientDao = new InvPatientAcceptanceRespDAO(tx);
			criteriacombo=onlinePatientDao.getTestCombo( _UserVO);
			mp.put(InvestigationConfig.TEST_REASON_COMBO, criteriacombo);
			
			for(int l=0;l<onlinePatientvoo.size();l++)
			{
				InvPatientAcceptanceRespVO onlinePatientvo=onlinePatientvoo.get(l);
				
			
			
			if(onlinePatientvo.getPatStatus().equals("IPD"))
				reqType=InvestigationConfig.REQUISITION_TYPE_IPD;
			else if(onlinePatientvo.getPatStatus().equals("OPD"))
				reqType=InvestigationConfig.REQUISITION_TYPE_OPD; 
			else
				reqType=InvestigationConfig.REQUISITION_TYPE_CASUALITY; 

		
			if(onlinePatientvo.getPatStatus().equals("IPD"))
				onlinePatientvo.setPatType("2");
			else
				onlinePatientvo.setPatType("1");
			
			
			
			InvPatientAcceptanceRespVO[] arrPatientCollectionVO=null;

			for(String str:reqList)
			{
				String reqNoo=str.split("#")[1]; //CRNO#ReqNo#index
				
				if(reqNoo.equalsIgnoreCase(onlinePatientvo.getPatReqNo()))
				{
				String reqNo=str.split("#")[1]; //CRNO#ReqNo#index
				/* Added by Prashant */
				String acceptedToNotAccepted =onlinePatientvo.getAcceptedToNotAccepted();
				
				
				List<InvPatientAcceptanceRespVO> lstTestBased=new ArrayList<InvPatientAcceptanceRespVO>();
				/* changed by Prashant */
				lstTestBased=onlinePatientDao.patientDetails(reqNo,reqType,acceptedToNotAccepted, _UserVO);

				if(lstTestBased!=null)
				{
					for(InvPatientAcceptanceRespVO voPatientCollection:lstTestBased)
					{
						boolean isBilled=false;
						voPatientCollection.setPatPuk(onlinePatientvo.getPatPuk());
						voPatientCollection.setPatType(onlinePatientvo.getPatType());
						voPatientCollection.setRequisitionNo(reqNo);
						voPatientCollection.setAcceptedToNotAccepted(onlinePatientvo.getAcceptedToNotAccepted());
						
						if(voPatientCollection!=null)
						{
							String billNo=voPatientCollection.getBillDetail().replace("^", "#").split("#")[0];
							voPatientCollection.setBillDetail(billNo);
							if(!billNo.equals("0"))isBilled=true;
						}
						if(isBilled){
							lstInvPatientAcceptanceRespVO.add(voPatientCollection);

						}
						else
							reqLis.add(voPatientCollection);
					}

				}
				}
			}
			
			}
			
			mp.put(InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED,lstInvPatientAcceptanceRespVO);
			mp.put(InvestigationConfig.LIST_PAT_PATIENT_UNBILLED,reqLis);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}

	
	public boolean  checkDailyLabNoDuplicacy(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		boolean isLabNoPresent = false;

		try
		{
			tx.begin();
			InvPatientAcceptanceRespDAO onlinePatientDao = new InvPatientAcceptanceRespDAO(tx);
			isLabNoPresent=onlinePatientDao.checkDailyLabNoDuplicacy(onlinePatientvo, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return isLabNoPresent;
	}

	
	public String checkAutoGenFormate(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();

		List isFormatePresent=null;

		List<InvPatientAcceptanceRespVO> onlinepatientacceptancevo=null;
		
       String Formate="null";
       
		try {

			tx.begin();
			InvPatientAcceptanceRespDAO onlinePatientDao = new InvPatientAcceptanceRespDAO(tx);

			onlinepatientacceptancevo=onlinePatientDao.checkAutoGenFormateAreawise(onlinePatientvo, _UserVO);

			if(onlinepatientacceptancevo.size()==0)
			{
				
				onlinepatientacceptancevo=onlinePatientDao.checkAutoGenFormate(onlinePatientvo, _UserVO);
				
			}
			
			int size=onlinepatientacceptancevo.size();
			
			if(size!=0)
			{
				StringBuilder sb = new StringBuilder();
				//all but last
				for(InvPatientAcceptanceRespVO voSampleVo:onlinepatientacceptancevo ) {
					sb.append(voSampleVo.getLabNoFormat()+"#"+voSampleVo.getInitDate()+"#"+voSampleVo.getNoOfSeqDigit()+"#"+voSampleVo.getFromSeries()+"#"+voSampleVo.getToSeries()+"#"+voSampleVo.getInitType()+"#"+voSampleVo.getRunningLabNo()+"#"+voSampleVo.getConfigLab()+"#"+voSampleVo.getConfigSeq()+"#"+voSampleVo.getConfigType()+"#"+voSampleVo.getConfigTest()+"#"+voSampleVo.getSampleAreaCode()+"#"+voSampleVo.getIslabNoAreaWise()  );
					sb.append("#");
				}
				 
				Formate=sb.toString();
				System.out.println("the Formate"+Formate);
			}


		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("error.... Essential BO");
		} finally {

			tx.close();
		}

		return Formate;
	}
	
		
		public Map  MachineCombos(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List machinecombo=new ArrayList();

			try
			{
				tx.begin();
				InvPatientAcceptanceRespDAOi onlinePatientDaoi = new InvPatientAcceptanceRespDAO(tx);
				//labMstDAOi.fetchLab(labMasterVO, _UserVO);

				machinecombo=onlinePatientDaoi.getMachineCombos(onlinePatientvo,_UserVO);
				mp.put(InvestigationConfig.LIST_LOCATION_BASED_MACHINE__COMBO, machinecombo);


			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisApplicationExecutionException();
			}
			catch (HisDataAccessException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisDataAccessException();
			}
			catch (HisException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisException();
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return mp;
		}
	
	
		public void refund(String pataccNo,InvPatientAcceptanceRespVO voPatient,  UserVO _userVO,Map<String,String> keyy) 
		
		{
		
			JDBCTransactionContext tx = new JDBCTransactionContext();
             tx.begin();
			SampleAcceptanceDAO objSampleAcceptanceDAO=new SampleAcceptanceDAO(tx);

			
			String requisitionNumber=voPatient.getRequisitionNo();
			InvestigationRequisitionBillDtlVO voBillingDtl=new InvestigationRequisitionBillDtlVO();   
          
			//added parameters value
			
			voPatient.setRequisitionNorefund(voBillingDtl.getRequisitionNos()+requisitionNumber+"!");
			
			Inv_RequisitionRaisingPatientVO patVO=null;
          	String crno=voPatient.getPatCRNo();
	patVO=objSampleAcceptanceDAO.getInvRaisingPatDetailsnew(crno,_userVO,requisitionNumber);
	
	
	String  requisitionTypeForBilling="";
	
	if(patVO.getPatvisittypecode()==null)
		requisitionTypeForBilling="1";
	else
		requisitionTypeForBilling=patVO.getPatvisittypecode();
	
	
	
	if(voBillingDtl.getTariffDetails()==null)
	{
		voBillingDtl.setTariffDetails(new ArrayList<String>());
		voBillingDtl.setTariffQty(new ArrayList<String>());
	}
	
	voBillingDtl.getTariffDetails().add(voPatient.getLabCode()+voPatient.getTestCode());
	voBillingDtl.getTariffQty().add("1");
	
	
	String simpletariffdetails="";
String simpletariffQty="";
String makeBillingTestWise="";




if(voBillingDtl.getTariffDetails()!=null)
{
	for(int indexCounter=0;indexCounter<voBillingDtl.getTariffDetails().size();indexCounter++)
	{
		if(indexCounter==0)
		{
			simpletariffdetails=voBillingDtl.getTariffDetails().get(indexCounter).substring(5);
			simpletariffQty=voBillingDtl.getTariffQty().get(indexCounter);
		}
		else
		{
			simpletariffdetails+="^"+voBillingDtl.getTariffDetails().get(indexCounter).substring(5);
			simpletariffQty+="^"+voBillingDtl.getTariffQty().get(indexCounter);
		}
		
		
	}
}

if(simpletariffdetails!=null && !simpletariffdetails.equals(""))
{
	 makeBillingTestWise="1";//procedure

    if(voPatient.getGroupCode()!=null && !voPatient.getGroupCode().equals("null") && (!voPatient.getGroupCode().equals("0") && !voPatient.getGroupCode().equals("")))
    {
    	makeBillingTestWise="4";//grpwise
    	simpletariffdetails=voPatient.getLabCode()+voPatient.getGroupCode();
    }
	 
}

// if reject
OnlinePatientAcceptanceVO voPatient2 = new OnlinePatientAcceptanceVO();

if( voPatient.getGroupCode()!=null && !voPatient.getGroupCode().equals("null") && ( !voPatient.getGroupCode().equals("0") && !voPatient.getGroupCode().equals("")) && keyy.size()==0)
{		HelperMethods.populate(voPatient2, voPatient);
	   objSampleAcceptanceDAO.makeRefundpatbased(voPatient2,_userVO,simpletariffdetails,simpletariffQty,patVO, requisitionTypeForBilling,makeBillingTestWise);
	  String keyyid=voPatient.getRequisitionNo()+"#"+voPatient.getGroupCode();
	 keyy.put(keyyid, "1");   
}
else if(   voPatient.getGroupCode()!=null && !voPatient.getGroupCode().equals("null") && ( !voPatient.getGroupCode().equals("0") && !voPatient.getGroupCode().equals("")) && keyy.size()>0)
{
	   
	   if(!keyy.containsKey(voPatient.getRequisitionNo()+"#"+voPatient.getGroupCode()))
	   {	HelperMethods.populate(voPatient2, voPatient);
		   objSampleAcceptanceDAO.makeRefundpatbased(voPatient2,_userVO,simpletariffdetails,simpletariffQty,patVO, requisitionTypeForBilling,makeBillingTestWise);
		  String keyyid=voPatient.getRequisitionNo()+"#"+voPatient.getGroupCode();
		  keyy.put(keyyid, "1");
	   }

}


if(voPatient.getGroupCode()==null || voPatient.getGroupCode().equals("null") ||  voPatient.getGroupCode().equals("") || voPatient.getGroupCode().equals("0"))
	HelperMethods.populate(voPatient2, voPatient);
	objSampleAcceptanceDAO.makeRefundpatbased(voPatient2,_userVO,simpletariffdetails,simpletariffQty,patVO, requisitionTypeForBilling,makeBillingTestWise);
	
			
		}
		
		public Map getUserList( UserVO _UserVO )
		{
			Map<String, String> uerList = new HashMap<>();
			JDBCTransactionContext tx = new JDBCTransactionContext();
			
			try
			{
				tx.begin();
				InvPatientAcceptanceRespDAO onlinePatientDao = new InvPatientAcceptanceRespDAO(tx);
				uerList=onlinePatientDao.getUserList( _UserVO);
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisApplicationExecutionException();
			}
			catch (HisDataAccessException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisDataAccessException();
			}
			catch (HisException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisException();
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return uerList;
			
		}
}

