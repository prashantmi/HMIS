package registration.transactions.controller.util;

/*
 * @ author Pragya Sharma
 * Created at 04-Aug-2011
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.config.RegistrationConfig;
import registration.config.slip.NewRegistrationSlip;
import registration.config.slip.RegistrationSlip;
import registration.transactions.controller.actionsupport.DuplicateCardGenerationSUP;
import registration.transactions.controller.actionsupport.PatientVisitSUP;
import registration.transactions.controller.actionsupport.RegistrationCancellationSUP;
import registration.transactions.controller.actionsupport.NewPatientRegistrationSUP;
import registration.transactions.controller.data.PatientVisitDATA;
import registration.transactions.controller.data.RegistrationCancellationDATA;
import vo.registration.DirectChageDetailVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.RegCardPrintVO;
import vo.registration.RegistrationCancellationVO;
import vo.registration.RenewalConfigVO;

public class RegistrationCancellationUTIL extends ControllerUTIL
{

	public static void getPatientDetail(RegistrationCancellationSUP objRegistrationCancellationSUP_p, HttpServletRequest request){
		Status status=new Status();
		setSysdate(request);
		PatientVO patVO = new PatientVO();
		try{
			PatDetailUTIL.getPatientDtlByCrno(objRegistrationCancellationSUP_p, request);

			if(WebUTIL.getStatus(request).getMessage("007").equalsIgnoreCase("Patient Details Not Found"))
			{
				objRegistrationCancellationSUP_p.setErrorMessage("Patient Details Not Found");
				throw new HisRecordNotFoundException("Patient Details Not Found");
			}
			else if(WebUTIL.getStatus(request).getMessage("007").equalsIgnoreCase("Current Patient Category is invalid please change the Patient Category first"))
			{
				objRegistrationCancellationSUP_p.setErrorMessage("Current Patient Category is invalid please change the Patient Category first");
				throw new HisRecordNotFoundException("Current Patient Category is invalid please change the Patient Category first");
			}
			else
			{
				if(WebUTIL.getSession(request).getAttribute(RegistrationConfig.PATIENT_VO)!=null)
				{
					patVO = (PatientVO) request.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
				}


				//PatientVO patVO=(PatientVO)request.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
				if(patVO!=null){


					if(patVO.getIsCatExpired().equals("1"))
					{
						/* #Start					:Sheeldarshi 
						#Modify Date(CR/PRS)	:25thMay'15 
						#Reason					: UAT Test Report Requirements:Requirement 4
						 */	
						//objRegistrationCancellationSUP_p.setErrorMessage("Patient Category is Expired. Please Change Patient Category First");
						objRegistrationCancellationSUP_p.setErrorMessage("Patient Category Validity is over. Kindly renew it first.");
						//End
						throw new HisRecordNotFoundException("Patient Still Unknown. Use Unknown to Known Conversion");
					}
					if(patVO.getPatIsDead().equals("1"))
					{
						objRegistrationCancellationSUP_p.setErrorMessage("Patient is Dead");
						throw new HisRecordNotFoundException("Patient Still Unknown. Use Unknown to Known Conversion");
					}
					HelperMethods.populate(objRegistrationCancellationSUP_p, patVO);
					objRegistrationCancellationSUP_p.setChoice(RegistrationConfig.DUPLICATE_CARD);
					setPatientEpisodeDtl(objRegistrationCancellationSUP_p,request);
					//setBillAmount(request);
					System.out.println(objRegistrationCancellationSUP_p.getPatFirstName());


					objRegistrationCancellationSUP_p.setTempAfterGo("1");
					objRegistrationCancellationSUP_p.setAfterGo("1");
				}	
			}
		}
		catch (HisRecordNotFoundException e)
		{
			objRegistrationCancellationSUP_p.setAfterGo("0");
			objRegistrationCancellationSUP_p.setTempAfterGo("0");
			status.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}		
		catch(Exception e){
			status.add(status.ERROR_AE,"", "");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(request, status);

		}
	}


	public static void setPatientEpisodeDtl(RegistrationCancellationSUP objRegistrationCancellationSUP_p, HttpServletRequest request){
		Status status = new Status();
		HttpSession session=WebUTIL.getSession(request);
		Map mp=new HashMap();
		objRegistrationCancellationSUP_p.setDuplicateRenewRemarks("");
		try{
			session.setAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_REG_CANCELLATION,null);
			//session.setAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_REGISTRATION,null);
			UserVO userVO =getUserVO(request);
			PatientVO patVO= (PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);;
			if(patVO!=null){
				if(objRegistrationCancellationSUP_p.getPatCrNo()!=null){
					patVO.setPatCrNo(objRegistrationCancellationSUP_p.getPatCrNo());
				}

				if(objRegistrationCancellationSUP_p.getCrNoToRetrieve()!=null && !objRegistrationCancellationSUP_p.getCrNoToRetrieve().equals("")){
					patVO.setPatCrNo(objRegistrationCancellationSUP_p.getCrNoToRetrieve());
				}


				mp=RegistrationCancellationDATA.getPatientEpisodeDtl(patVO.getPatCrNo(), userVO);
				WebUTIL.populate(objRegistrationCancellationSUP_p,patVO);

				WebUTIL.setMapInSession(mp, request,"RegistrationCancellationAction");
			}
			status.add(status.RECORDFOUND,"","");
			status.add(status.TRANSINPROCESS,"","");
		}catch(HisRecordNotFoundException e){
			mp=e.getEssentialMap();
			WebUTIL.setMapInSession(mp, request,"RegistrationCancellationAction");
			status.add(Status.TRANSINPROCESS,"",e.getMessage());	
		}		
		catch(Exception e){
			status.add(status.ERROR_AE,"", "");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(request, status);

		}
	}



	public static void getEpisodes( RegistrationCancellationSUP objRegistrationCancellationSUP_p,HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		EpisodeVO[] arrEpisopeVO=null;
		StringBuilder br = new StringBuilder(1000);
		objRegistrationCancellationSUP_p.setDuplicateRenewRemarks("");
		if(request.getParameter("patCrNo")!=null)
			objRegistrationCancellationSUP_p.setPatCrNo(request.getParameter("patCrNo"));
		try
		{
		//addEpisodes(arrEpisopeVO,session,essentialMap,objRegistrationCancellationSUP_p,request,br);
			//Status objStatus=new Status();
			
				//String billAmt = (String) session.getAttribute(RegistrationConfig.BILL_AMOUNT);
				if(session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_REG_CANCELLATION)==null)
				{
					essentialMap=RegistrationCancellationDATA.getPatientEpisodeDtl(objRegistrationCancellationSUP_p.getPatCrNo(),getUserVO(request));
					WebUTIL.setMapInSession(essentialMap, request,"RegistrationCancellationAction");
					arrEpisopeVO=(EpisodeVO[]) essentialMap.get(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_REG_CANCELLATION);
				}
				else
				{
					arrEpisopeVO=(EpisodeVO[]) session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_REG_CANCELLATION);	
				}
				//String billAmount = (String) session.getAttribute(RegistrationConfig.BILL_AMOUNT);	
				if(arrEpisopeVO!=null && arrEpisopeVO.length>0 )
				{
					br.append("<div class='div-table-row title'>");
					br.append("<div class='div-table-col' style='width: 80%;'> Episode Detail</div>");
					/*Added by Mukund on 15.07.2016 for "Refund Only" provision in RegCancellation Process */
					br.append("<div class='div-table-col' style='width: 20%;'><input type='radio' name='isCancellationFlag' value='0' >Refund Only&nbsp;<input type='radio' name='isCancellationFlag' value='1' checked='checked'>Cancellation</div>");
					/*End*/
					br.append("</div>");

					br.append("<div class='div-table-row'>");
					br.append("<div class='div-table-col control' style='width: 8%;'>Select</div>");
					br.append("<div class='div-table-col control' style='width: 17%;'><b>Department</b></div>");
					br.append("<div class='div-table-col control' style='width: 25%;'><b>Unit</b></div>");
					br.append("<div class='div-table-col control' style='width: 15%;'><b>Last Visit Date</b></div>");
					br.append("<div class='div-table-col control' style='width: 20%;'><b>Paid Amount</b></div>");
					br.append("</div>");
					for(int i=0;i<arrEpisopeVO.length;i++)
					{
						String check="";
						if(i==0){
							check="checked='checked'";
						}

						/*if(arrEpisopeVO[i].getIsCardPrintedToday()==null||arrEpisopeVO[i].getIsCardPrintedToday().equals("0"))
							br.append("<div class='div-table-row' >");
						else
							br.append("<div class='div-table-row' style='background-color: #FF0000'>");*/

						/*if(arrEpisopeVO[i].getIsReprint() != null && arrEpisopeVO[i].getEpisodeVisitNo() !=null && arrEpisopeVO[i].getEpisodeVisitNo().equals("") )
						{
							if(arrEpisopeVO[i].getIsReprint().equals("1") && Integer.parseInt(arrEpisopeVO[i].getEpisodeVisitNo())>1 )
								arrEpisopeVO[i].setIsReprint("-1");
						}*/
						String temp=arrEpisopeVO[i].getDepartmentUnitCode()+ "#"+ arrEpisopeVO[i].getIsReprint() + "#"+ arrEpisopeVO[i].getRefundAllowed() ;
						//to get the actual amount ON:15.07.2016 BY:MUKUND
						if(arrEpisopeVO[i].getPatActualAmount()!=null && arrEpisopeVO[i].getPatActualAmount().contains("#"))
						{
						String[] tempBillDtl =arrEpisopeVO[i].getPatActualAmount().split("#");
						arrEpisopeVO[i].setBillNo(tempBillDtl[0]);
						arrEpisopeVO[i].setReceiptNo(tempBillDtl[1]);
						arrEpisopeVO[i].setPatActualAmount(tempBillDtl[2]);
						}
						else
						{
						arrEpisopeVO[i].setPatActualAmount("0");
						}
		
						br.append("<div class='div-table-row' >");
						br.append("<div class='div-table-col control' style='width: 8%;'>"
								+ "<input type='radio' name='RegistrationCancellationEpisodeList' tabindex='1' value='"+temp +"' onclick='checkForRefundAllowed(this)'/>"
										+ "</div>");
						br.append("<div class='div-table-col control' style='width: 17%;'>"+arrEpisopeVO[i].getDepartment()+"</div>");
						br.append("<div class='div-table-col control' style='width: 25%;'>"+arrEpisopeVO[i].getDepartmentUnit()+"</div>");
						br.append("<div class='div-table-col control' style='width: 15%;'>"+arrEpisopeVO[i].getEpisodeDate()+"</div>");
						br.append("<div class='div-table-col control' style='width: 15%;'>"+arrEpisopeVO[i].getPatActualAmount()+"</div>");
						br.append("</div>");

					}
					br.append("</br>");
					br.append("<div class='div-table-row'>");
					br.append("<div class='div-table-col label' style='width: 25%;'><font color='red'>*</font>Reason</div>");
					br.append("<div class='div-table-col control' style='width: 25%;'><textarea name='duplicateRenewRemarks' rows='1' cols='25'></textarea></div>");
					/*if(arrEpisopeVO[0].getIsReprint().equals("-1"))
					{
						br.append("<div class='div-table-col' id='isDupFlag' style='display:block; width:50%;' >");
						br.append("<div class='div-table-col label' id='labelRegFee' style='width: 50%;'>Print Fee</div>");
						br.append("<div class='div-table-col control' id='labelRegFee' style='width: 50%;'>	<input type='text' name='duplicateRenewCost'  tabindex='1' value='"+billAmt+"' onkeypress='return CheckMaxLength(event,this,150,1)' readonly='readonly' /></div>");
						br.append("</div>");
					}
					else
					{
						br.append("<div class='div-table-col' id='isDupFlag' style='display:none; width:50%;'>");
						br.append("<div class='div-table-col label' id='labelRegFee' style='width: 50%;'>Print Fee</div>");
						br.append("<div class='div-table-col control' id='labelRegFee' style='width: 50%;'>	<input type='text' name='duplicateRenewCost'  tabindex='1' value='"+billAmt+"' onkeypress='return CheckMaxLength(event,this,150,1)' readonly='readonly' /></div>");
						br.append("</div>");
						br.append("<div class='div-table-col label'  style='width: 25%;'></div>");
						br.append("<div class='div-table-col control' style='width: 25%;'></div>");
					}*/
					br.append("</div>");
				}
				
				if(arrEpisopeVO==null){
					br.append("<div class='div-table-row title'>");
					br.append("<div class='div-table-col'> Episode Detail</div>");
					br.append("</div>");
					br.append("<div class='div-table-row '>");
					br.append("<div class='div-table-col width100 alignCenter colorRed'> No Episode Detail Found</div>");
					br.append("</div>");

				}
			
		writeResponse(response, br.toString());
		

		}

		catch(HisRecordNotFoundException e){
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS,"",e.getMessage());	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void writeResponse(HttpServletResponse resp, String output){
		try{
			resp.reset();
			resp.setContentType("text/html");
			resp.setHeader("Cache-Control", "no-cache");
			System.out.println(output);
			resp.getWriter().write(output);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}	

	public static void getListOfRenewalConfigDtl(RegistrationCancellationSUP objRegistrationCancellationSUP_p,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		List<RenewalConfigVO> mapOfRenewalVo=null;
		try
		{
			System.out.println("PatientVisitUTL :: getMapOfRenewalConfigDtl()");
			setSysdateAndDefaultCrNoFormat(objRequest_p);
			mapOfRenewalVo=RegistrationCancellationDATA.getMapOfRenewalConfigDtl(objUserVO);
			//objPatVisitSUP_p.setStrRenewalType(objRenewalConfigVO.getStrRenewalType());
			//System.out.println("StrRenewalType :"+objPatVisitSUP_p.getStrRenewalType());
			HttpSession ses = WebUTIL.getSession(objRequest_p);
			PatientVO patVO = (PatientVO) ses.getAttribute(RegistrationConfig.PATIENT_VO);
			//Map<String, RenewalConfigVO> mapOfRenewalVoOnPatCatGroupKey= (Map<String, RenewalConfigVO>)_req.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			//RenewalConfigVO objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get(patVO.getPatPrimaryCatCode());
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVo);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVo);
			//objStatus.add(Status.ERROR,"",e.getMessage());
			objRegistrationCancellationSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			//objStatus.add(Status.ERROR_DA,"","Record Not Found");	
			objRegistrationCancellationSUP_p.setErrorMessage("Record Not Found");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
		}
		catch(HisException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(objRequest_p,objStatus);
		}
	}

	public static void saveRegistrationCancellation(HttpServletRequest _req, RegistrationCancellationSUP objRegistrationCancellationSUP_p){
		Status status = new Status();
		try{
			UserVO userVO=getUserVO(_req);
			HttpSession session=WebUTIL.getSession(_req);
			getHospitalVO(_req);
			String RegistrationCancellationEpisodeList=objRegistrationCancellationSUP_p.getRegistrationCancellationEpisodeList();
			EpisodeVO episodeVO=new EpisodeVO();
			PatientVO patientVO= (PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);
			boolean isExpiryDateUpdated = false;
			String	str = new String();	
			RegistrationCancellationVO regCancellationVO=null;

				EpisodeVO[] arrEpisodeVOSes = (EpisodeVO[])session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_REG_CANCELLATION);
				EpisodeVO  epVO=new EpisodeVO();
				regCancellationVO=new RegistrationCancellationVO();
					
				for(int i=0; i<arrEpisodeVOSes.length; i++){
					String[] parts = RegistrationCancellationEpisodeList.split("#");
					String part1 = parts[0]; 
					String refundAllowed=parts[2];
					if(arrEpisodeVOSes[i].getDepartmentUnitCode().equals(part1)){
						epVO=arrEpisodeVOSes[i];
						regCancellationVO=new RegistrationCancellationVO();
						regCancellationVO.setDupRemarks(objRegistrationCancellationSUP_p.getDuplicateRenewRemarks());
						//regCancellationVO.setIpAdd(_req.getRemoteAddr());
						regCancellationVO.setPatAmountCollected(epVO.getPatAmountCollected());
						//regCancellationVO.setCardPrintFlag(objRegistrationCancellationSUP_p.getChoice());
						regCancellationVO.setPatRegCatCode(patientVO.getPatRegCatCode());
						
						//added by mukund on 15.07.2016 
						regCancellationVO.setIsCancellationFlag(objRegistrationCancellationSUP_p.getIsCancellationFlag());
						
						/*String[] tempBillDtl =epVO.getPatActualAmount().split("#");
						epVO.setBillNo(tempBillDtl[0]);
						epVO.setReceiptNo(tempBillDtl[1]);
						epVO.setPatActualAmount(tempBillDtl[2]);*/
						HelperMethods.populate(regCancellationVO, epVO);
						regCancellationVO.setStrBillNo(epVO.getBillNo());
						regCancellationVO.setRefundAllowed(refundAllowed);
						
						RegistrationCancellationDATA.saveRegistrationCancellation(regCancellationVO, userVO);
						if(_req.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO)!=null)
						{
							HttpSession ses = WebUTIL.getSession(_req);
							PatientVO patVO = (PatientVO) ses.getAttribute(RegistrationConfig.PATIENT_VO);
							List<RenewalConfigVO> RenewalVoSes= (List<RenewalConfigVO>)session.getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
							
							if(RenewalVoSes.size()==0)
								RegistrationCancellationUTIL.getListOfRenewalConfigDtl(objRegistrationCancellationSUP_p,_req);
									
							if(RenewalVoSes.size()!=0)
							{
							for(int j=0; j<RenewalVoSes.size(); j++)
							{	
								 if (isExpiryDateUpdated == false)
								 {
								RenewalConfigVO e = RenewalVoSes.get(j);						 								
								 if (e.getStrRenewalPatCat().equals(patVO.getPatCatCode())|| (j+1 == RenewalVoSes.size()) )//&& e.getStrRenewalGroup() == arrEpisodeVOSes[i].getEpisodeVisitType()
								 {
									 if(j+1 == RenewalVoSes.size())
									 {
										 for(int k=0; k<RenewalVoSes.size(); k++)
											{
											  e = RenewalVoSes.get(k);
											  if(e.getStrRenewalPatCat().equals("10"))
											  {
												  break;
											  }
											 
											}
									 }
									 if(e.getStrRenewalType().equals("1"))
									 {
										 if(arrEpisodeVOSes[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_OPD))
										 {
											 if(e.getStrRenewalGroup().equals("1"))//OPD
											 {	 
												 RegistrationCancellationDATA.updateExpirydates(regCancellationVO, userVO, "3"); 
												 isExpiryDateUpdated = true;
											 }
										 }
										 else if(arrEpisodeVOSes[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC) || arrEpisodeVOSes[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC))
										 {
											 if(e.getStrRenewalGroup().equals("2"))// Casuality
											 {	 
												 RegistrationCancellationDATA.updateExpirydates(regCancellationVO, userVO, "4"); 
												 isExpiryDateUpdated = true;
											 }
										 }
										 else if(arrEpisodeVOSes[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL))
										 {
											 if(e.getStrRenewalGroup().equals("3"))// Special
											 {	 
												 RegistrationCancellationDATA.updateExpirydates(regCancellationVO, userVO, "5"); 
												 isExpiryDateUpdated = true;
											 }
										 }
									 }
									else if(e.getStrRenewalType().equals("2"))//ALL
									 {
									 RegistrationCancellationDATA.updateExpirydates(regCancellationVO, userVO,"2");
									 isExpiryDateUpdated =true;
									 }
									 
								 }
								 
							}
							}
							}
							episodeVO=new EpisodeVO();
							//HelperMethods.populate(regCancellationVO, epVO);
							episodeVO.setUnitWorkingDays(epVO.getUnitWorkingDays());
							episodeVO.setDisclaimer(epVO.getDisclaimer());
							episodeVO.setPatAmountCollected(epVO.getPatAmountCollected());
							episodeVO.setEntryDate(epVO.getEntryDate());
							episodeVO.setQueNo(epVO.getQueNo());
							episodeVO.setIsGeneral(epVO.getIsGeneral());
							episodeVO.setCardPrintSetting(epVO.getCardPrintSetting());
							episodeVO.setReferFromDepartment(epVO.getReferFromDepartment());
							episodeVO.setIsCardPrint(epVO.getIsCardPrint());
							
							/*Start : Surabhi
							 * Reason : Bug resolved for getting the dept details
							 * date : 7th oct 2016 */
							episodeVO.setDepartment(epVO.getDepartment());
							episodeVO.setDepartmentCode(epVO.getDepartmentCode());
							episodeVO.setDepartmentUnit(epVO.getDepartmentUnit());
							episodeVO.setDepartmentUnitCode(epVO.getDepartmentUnitCode());
							// end
							
							//String[] tempBillDtl =epVO.getPatActualAmount().split("#");
							episodeVO.setBillNo(epVO.getBillNo());
							episodeVO.setReceiptNo(epVO.getReceiptNo());
							episodeVO.setPatActualAmount(epVO.getPatActualAmount());
							String tmpFileName=RegistrationConfig.DUPLICATE_CARD+userVO.getSeatId();

							EpisodeVO newEpisodeVO=new EpisodeVO();
							newEpisodeVO=episodeVO;

						
						/*	str=NewRegistrationSlip.print(prepareSlip(newEpisodeVO, patientVO, objRegistrationCancellationSUP_p, _req), tmpFileName,_req,"DC");*/
							
							objRegistrationCancellationSUP_p.setIsPrintFlag("0");
							String strBillPrintDiv="";
							if(episodeVO.getPatActualAmount()!=null && episodeVO.getPatActualAmount()!="" && episodeVO.getPatActualAmount()!="0" && episodeVO.getPatActualAmount()!="0.00" && regCancellationVO.getRefundAllowed().equals("1"))
							{
							if(!patientVO.getPatPrimaryCatGrpCode().equals("3") && !patientVO.getPatPrimaryCatGrpCode().equals("4")){
								
								
										strBillPrintDiv=NewRegistrationSlip.printBillReceiptForCancellation(prepareSlip(episodeVO,patientVO,objRegistrationCancellationSUP_p, _req), tmpFileName,_req,"RC");
										
							}
							}
							/*else
							{
								strBillPrintDiv=NewRegistrationSlip.printBillReceipt(prepareSlip(episodeVO,patientVO,objRegistrationCancellationSUP_p, _req), tmpFileName,_req,"ODV");	
							}*/
							if(regCancellationVO.getRefundAllowed().equals("1"))
								objRegistrationCancellationSUP_p.setIsPrintFlag("1");
							else
								objRegistrationCancellationSUP_p.setIsPrintFlag("0");
							objRegistrationCancellationSUP_p.setAfterGo("0");
							objRegistrationCancellationSUP_p.setTempAfterGo("0");
							/*if(!newEpisodeVO.getIsCardPrint().equalsIgnoreCase("2"))
								objRegistrationCancellationSUP_p.setPrint("1");			*/	

							objRegistrationCancellationSUP_p.setErrorMessage("Cancelled successfully!");
							objRegistrationCancellationSUP_p.setPrintHtml("Cancelled successfully!"+strBillPrintDiv);

							objRegistrationCancellationSUP_p.setPrintHtmlTemp(strBillPrintDiv);

							//objRegistrationCancellationSUP_p.setIsPrintFlag("1");
							System.out.println("PrintBillSlip :"+strBillPrintDiv);
							strBillPrintDiv="<br><br><br><br>"+strBillPrintDiv;
							WebUTIL.setAttributeInSession(_req,"billReceiptString", strBillPrintDiv);
							//str+=""+strBillPrintDiv+"";
						
						
						    System.out.println("FullSlip :"+strBillPrintDiv);
						  // objRegistrationCancellationSUP_p.setIsPrintFlag("1");
						}
								
						
					}
			}
				status.add(Status.DONE,"","Cancelled Successfully");
			
		}catch(HisDataAccessException e){
			e.printStackTrace();
			status.add(status.ERROR_DA,"Data Access Error" ,"");
		}catch(HisApplicationExecutionException e){
			e.printStackTrace();
			status.add(status.ERROR_AE,"Application Execution Error" ,"");
		}catch(Exception e){
			e.printStackTrace();
			status.add(status.ERROR_AE,"Application Execution Error" ,"");
		}
		finally{
			WebUTIL.setStatus(_req, status);
		}

	}

	public static RegistrationSlip prepareSlip(EpisodeVO episodeVO,PatientVO _patVO,RegistrationCancellationSUP objDuplicateCardGenerationSUP_p,HttpServletRequest _request){		

		RegistrationSlip regSlip=new RegistrationSlip();
		HelperMethods.setNullToEmpty(_patVO);
		HelperMethods.setNullToEmpty(episodeVO);
		HelperMethods.populate(regSlip,_patVO);
		
		UserVO _userVO=getUserVO(_request);
		regSlip.setPatAge(_patVO.getPatAge() +" "+_patVO.getPatAgeUnit());
		HospitalMstVO hospitalVO=getHospitalVO(_request);
		regSlip.setHospitalName(hospitalVO.getHospitalName());
		regSlip.setHospitalAddress1(hospitalVO.getAddress1());
		regSlip.setHospitalAddress2(hospitalVO.getAddress2());
		regSlip.setHospitalCity(hospitalVO.getCity());
		regSlip.setHospitalState(hospitalVO.getStateName());
		regSlip.setHospitalpinCode(hospitalVO.getPinCode());
		regSlip.setHospitalPhone(hospitalVO.getPhone());
		regSlip.setHospitalFax(hospitalVO.getFax());
		regSlip.setHospitalEmail(hospitalVO.getEmail());
		regSlip.setPatAmountCollected(episodeVO.getPatAmountCollected());
		regSlip.setPatActualAmount(episodeVO.getPatActualAmount());
		regSlip.setRemarks(objDuplicateCardGenerationSUP_p.getDuplicateRenewRemarks());

		regSlip.setPatGender(_patVO.getPatGender());
		regSlip.setPatOccupation("");

		String fileNoView[] = new String[1];
		String fileNo[] = new String[1];
		String episodeDate [] = new String[1];
		String roomName[] = new String[1];
		String depName[] = new String[1];
		String depUnit[] = new String[1];
		String queueNo[] = new String[1];
		String workingDays[] = new String[1];
		String timing[] = new String[1];
		// disclaimers
		String disclaimer1[] = new String[1];
		String disclaimer2[] = new String[1];
		String disclaimer3[] = new String[1];
		String isHeader[] = new String[1];
		String alignment[] = new String[1];
		String unitConsultant[] = new String[1];
		String filePrintFlag[] = new String[1];
		String cardPrintFlag[] = new String[1];
		String expDate[] = new String[1];;
		String billNo[] = new String[1];
		String receiptNo[] = new String[1];

		String patMiddleName=regSlip.getPatMiddleName();

		if(!(patMiddleName==null || patMiddleName.equals("")))
		{
			patMiddleName=patMiddleName.substring(0,1).toUpperCase();
			regSlip.setPatMiddleName(patMiddleName);
		}

		if(_patVO.getPatPrimaryCatGrpCode().equals("0"))
			regSlip.setPatPrimaryCatGrp("Payment"); 
		else if(_patVO.getPatPrimaryCatGrpCode().equals("1"))
			regSlip.setPatPrimaryCatGrp("Staff"); 
		else if(_patVO.getPatPrimaryCatGrpCode().equals("2"))
		regSlip.setPatPrimaryCatGrp("Free"); 
		else if (_patVO.getPatPrimaryCatGrpCode().equals("3"))
		regSlip.setPatPrimaryCatGrp("Beneficery/Credit"); 
		regSlip.setclientName(_patVO.getClientName()); 
		////Address For card
		String cityLocation="";
		String district="";
		String state="";

		String country=_patVO.getPatAddCountry();

		cityLocation=_patVO.getPatAddCity();
		if(_patVO.getPatAddDistrictCode()==null || _patVO.getPatAddDistrictCode().equals(""))
			district=_patVO.getPatAddDistrict();

		//inorder to prevent null showing for the location textbox on the print slip
		cityLocation=cityLocation==null?" ":cityLocation;
		String address=_patVO.getPatAddHNo();
		address=address+" "+_patVO.getPatAddStreet();
		address=address+" "+cityLocation;
		address=address+" " +_patVO.getPatAddCity();
		if(district.equals("-1"))
			district="";
		String address2=district;

		if(!address2.equals(""))
			address2=address2+" "+state;
		else
			address2=state;

		address2=address2+" "+country;
		address2=address2+" "+_patVO.getPatAddPIN();

		//String patOccupation="";
		//patOccupation=_patVO.getPatOccupation();

		///////////////////////////////////////////////////////////////////////////////////////
		if(episodeVO.getFileNo()==null)
		{
			episodeVO.setFileNo("");
			episodeVO.setFileNoView("");
		}


		fileNoView[0]=episodeVO.getFileNoView();
		fileNo[0]=episodeVO.getFileNo();
		roomName[0]=episodeVO.getRoom();

		depName[0]=episodeVO.getDepartment();			
		depUnit[0]=episodeVO.getDepartmentUnit();
		queueNo[0]=episodeVO.getQueNo();
		expDate[0]=episodeVO.getExpiryDate();
		unitConsultant[0]=episodeVO.getUnitConsultant();
		billNo[0]=episodeVO.getBillNo();
		receiptNo[0]=episodeVO.getReceiptNo();
		//added to set the card and file print format
		filePrintFlag[0]=episodeVO.getFilePrintSetting();
		cardPrintFlag[0]=episodeVO.getCardPrintSetting();
		if(episodeVO.getDisclaimer()!="" && episodeVO.getDisclaimer()!=null){
			disclaimer1[0]=episodeVO.getDisclaimer().split("@")[0];
			disclaimer2[0]=episodeVO.getDisclaimer().split("@")[1];
			disclaimer3[0]=episodeVO.getDisclaimer().split("@")[2];
			isHeader[0]=episodeVO.getDisclaimer().split("@")[3];
			alignment[0]=episodeVO.getDisclaimer().split("@")[4];
		}
		else{
			disclaimer1[0]="";
			disclaimer2[0]="";
			disclaimer3[0]="";
			isHeader[0]="";
			alignment[0]="";
		}
		workingDays[0]=episodeVO.getUnitWorkingDays();
		//episodeDate=episodeVO.getEntryDate();
		episodeDate[0]=(String)_request.getSession().getAttribute(Config.SYSDATE);
		/*Calendar cal = Calendar.getInstance(); 
			String DATE_FORMAT = "dd/MM/yyyy HH:MM";
		    SimpleDateFormat sdf =new SimpleDateFormat(DATE_FORMAT);
			String date=sdf.format(cal.getTime());
			episodeDate=date;*/

		regSlip.setRoom(roomName);
		regSlip.setDepartmentToBeVisited(depName);
		/*for(int k=0;k<regSlip.getDepartmentToBeVisited().length;k++)
		{
			System.out.println("department"+regSlip.getDepartmentToBeVisited()[k]);
		}*/
		regSlip.setFileNoView(fileNoView);
		regSlip.setFileNo(fileNo);
		regSlip.setExpDate(expDate);
		regSlip.setDepartmentUnit(depUnit);		
		regSlip.setUnitConsultant(unitConsultant);		
		regSlip.setQueNo(queueNo);
		regSlip.setEpisodeDate(episodeDate);	
		regSlip.setWorkingDays(workingDays);
		regSlip.setTiming(timing);
		regSlip.setAddress1(address);

		regSlip.setAddress2(address2);
		regSlip.setDisclaimer1(disclaimer1);
		regSlip.setDisclaimer2(disclaimer2);
		regSlip.setDisclaimer3(disclaimer3);
		regSlip.setIsHeader(isHeader);
		regSlip.setAlignment(alignment);
		regSlip.setUnitConsultant(unitConsultant);
		regSlip.setFilePrintFlag(filePrintFlag);
		regSlip.setCardPrintFlag(cardPrintFlag);
		regSlip.setBillNo(billNo);
		regSlip.setReceiptNo(receiptNo);
		//regSlip.setPatOccupation(patOccupation);
		regSlip.setLoginUserName(_userVO.getUsrName());//By Mukund
		
		return regSlip;		
	}

	


}
