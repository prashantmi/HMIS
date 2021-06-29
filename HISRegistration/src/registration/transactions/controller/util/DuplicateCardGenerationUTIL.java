package registration.transactions.controller.util;

/*
 * @ author Pragya Sharma
 * Created at 04-Aug-2011
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.QRCodeTest;
import registration.config.RegistrationConfig;
import registration.config.RegistrationConfigurationBean;
import registration.config.slip.NewRegistrationSlip;
import registration.config.slip.RegistrationSlip;
import registration.transactions.controller.actionsupport.DuplicateCardGenerationSUP;
import registration.transactions.controller.actionsupport.NewPatientRegistrationSUP;
import registration.transactions.controller.data.BarcodeGenerationDATA;
import registration.transactions.controller.data.DuplicateCardGenerationDATA;
import registration.transactions.controller.data.EmgPatientVisitDATA;
import vo.registration.BarcodeSlipVO;
import vo.registration.DirectChageDetailVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.RegCardPrintVO;

public class DuplicateCardGenerationUTIL extends ControllerUTIL
{

	public static void getPatientDetail(DuplicateCardGenerationSUP objDuplicateCardGenerationSUP_p, HttpServletRequest request){
		Status status=new Status();
		setSysdate(request);
		PatientVO patVO = new PatientVO();
		try{
			PatDetailUTIL.getPatientDtlByCrno(objDuplicateCardGenerationSUP_p, request);

			if(WebUTIL.getStatus(request).getMessage("007").equalsIgnoreCase("Patient Details Not Found"))
			{
				objDuplicateCardGenerationSUP_p.setErrorMessage("Patient Details Not Found");
				throw new HisRecordNotFoundException("Patient Details Not Found");
			}
			else if(WebUTIL.getStatus(request).getMessage("007").equalsIgnoreCase("Current Patient Category is invalid please change the Patient Category first"))
			{
				objDuplicateCardGenerationSUP_p.setErrorMessage("Current Patient Category is invalid please change the Patient Category first");
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


					if(patVO.getIsCatExpired()!=null && patVO.getIsCatExpired().equals("1"))
					{
						/* #Start					:Sheeldarshi 
						#Modify Date(CR/PRS)	:25thMay'15 
						#Reason					: UAT Test Report Requirements:Requirement 4
						 */	
						//objDuplicateCardGenerationSUP_p.setErrorMessage("Patient Category is Expired. Please Change Patient Category First");
						objDuplicateCardGenerationSUP_p.setErrorMessage("Patient Category Validity is over. Kindly renew it first.");
						throw new HisRecordNotFoundException("Patient Still Unknown. Use Unknown to Known Conversion");
						//End
					}
					if(patVO.getPatIsDead().equals("1"))
					{
						objDuplicateCardGenerationSUP_p.setErrorMessage("Patient is Dead");
						throw new HisRecordNotFoundException("Patient Still Unknown. Use Unknown to Known Conversion");
					}
					//To Check whether the CRNo is merged or not,Added by Singaravelan on 16-Jul-2015 
					if(patVO.getPatIsMerged()!=null && patVO.getPatIsMerged().equals("2"))
					{
						objDuplicateCardGenerationSUP_p.setAfterGo("0");
						objDuplicateCardGenerationSUP_p.setErrorMessage("CR No is Not Valid, Already Merged with CR No. " +patVO.getPatMergedMainCrNO());
						throw new HisRecordNotFoundException("CR No is Not Valid, Already Merged with CR No. " +patVO.getPatMergedMainCrNO() );
					}
					HelperMethods.populate(objDuplicateCardGenerationSUP_p, patVO);
					objDuplicateCardGenerationSUP_p.setChoice(RegistrationConfig.DUPLICATE_CARD);
					setPatientEpisodeDtl(objDuplicateCardGenerationSUP_p,request);
					setBillAmount(request);
					System.out.println(objDuplicateCardGenerationSUP_p.getPatFirstName());


					objDuplicateCardGenerationSUP_p.setTempAfterGo("1");
					objDuplicateCardGenerationSUP_p.setAfterGo("1");
				}	
			}
		}
		catch (HisRecordNotFoundException e)
		{
			objDuplicateCardGenerationSUP_p.setAfterGo("0");
			objDuplicateCardGenerationSUP_p.setTempAfterGo("0");
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

	public static void setBillAmount(HttpServletRequest _rq){		
		UserVO userVO =getUserVO(_rq);
		userVO.setTariffID(RegistrationConfig.DUPLICATE_RENEW_CARD_TARIFF_ID);
		HttpSession session=WebUTIL.getSession(_rq);
		PatientVO patVO=(PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);
		if(patVO!=null){
			String primCat=patVO.getPatPrimaryCatCode();
			String billAmount=DuplicateCardGenerationDATA.getBillAmount(primCat, userVO);
			/*Modify Date				: 1stDec'14
			  Reason	(CR/PRS)		: Bug Id 7610
			  Modify By                 : Sheeldarshi */
			if(billAmount.equals("-1"))
			{
				billAmount="0";
			}
			//End:Sheeldarshi
			System.out.println("------Dupliacte Print Fee-------"+billAmount+"--------------");
			session.setAttribute(RegistrationConfig.BILL_AMOUNT, billAmount);		
		}
	}	

	public static void setPatientEpisodeDtl(DuplicateCardGenerationSUP objDuplicateCardGenerationSUP_p, HttpServletRequest request){
		Status status = new Status();
		HttpSession session=WebUTIL.getSession(request);
		Map mp=new HashMap();
		objDuplicateCardGenerationSUP_p.setDuplicateRenewRemarks("");
		try{
			session.setAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_DUPLICATE,null);
			session.setAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_REGISTRATION,null);
			UserVO userVO =getUserVO(request);
			PatientVO patVO= (PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);;
			if(patVO!=null){
				if(objDuplicateCardGenerationSUP_p.getPatCrNo()!=null){
					patVO.setPatCrNo(objDuplicateCardGenerationSUP_p.getPatCrNo());
				}

				if(objDuplicateCardGenerationSUP_p.getCrNoToRetrieve()!=null && !objDuplicateCardGenerationSUP_p.getCrNoToRetrieve().equals("")){
					patVO.setPatCrNo(objDuplicateCardGenerationSUP_p.getCrNoToRetrieve());
				}


				mp=DuplicateCardGenerationDATA.getPatientEpisodeDtl(patVO.getPatCrNo(), userVO,objDuplicateCardGenerationSUP_p.getChoice(),objDuplicateCardGenerationSUP_p.getIsReprint());
				WebUTIL.populate(objDuplicateCardGenerationSUP_p,patVO);

				WebUTIL.setMapInSession(mp, request,"DuplicateCardGenerationAction");
			}
			status.add(status.RECORDFOUND,"","");
			status.add(status.TRANSINPROCESS,"","");
		}catch(HisRecordNotFoundException e){
			mp=e.getEssentialMap();
			WebUTIL.setMapInSession(mp, request ,"DuplicateCardGenerationAction");
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



	public static void setDetailsByChoice( DuplicateCardGenerationSUP objDuplicateCardGenerationSUP_p,HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		EpisodeVO[] arrEpisopeVO=null;
		StringBuilder br = new StringBuilder(1000);
		objDuplicateCardGenerationSUP_p.setDuplicateRenewRemarks("");
		if(request.getParameter("choice")!=null)
			objDuplicateCardGenerationSUP_p.setChoice(request.getParameter("choice"));
		if(request.getParameter("patCrNo")!=null)
			objDuplicateCardGenerationSUP_p.setPatCrNo(request.getParameter("patCrNo"));
		try{
			if(objDuplicateCardGenerationSUP_p.getChoice().equals(RegistrationConfig.DUPLICATE_CARD))
			{
				if(session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_DUPLICATE)==null)
				{
					essentialMap=DuplicateCardGenerationDATA.getPatientEpisodeDtl(objDuplicateCardGenerationSUP_p.getPatCrNo(),getUserVO(request),objDuplicateCardGenerationSUP_p.getChoice(),objDuplicateCardGenerationSUP_p.getIsReprint());
					WebUTIL.setMapInSession(essentialMap, request ,"DuplicateCardGenerationAction");
					arrEpisopeVO=(EpisodeVO[]) essentialMap.get(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_DUPLICATE);
				}
				else
				{
					arrEpisopeVO=(EpisodeVO[]) session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_DUPLICATE);	
				}

			}
			else if(objDuplicateCardGenerationSUP_p.getChoice().equals(RegistrationConfig.REPRINT_REGISTRATION))
			{
				if(session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_REGISTRATION)==null)
				{
					essentialMap=DuplicateCardGenerationDATA.getPatientEpisodeDtl(objDuplicateCardGenerationSUP_p.getPatCrNo(),getUserVO(request),objDuplicateCardGenerationSUP_p.getChoice(),objDuplicateCardGenerationSUP_p.getIsReprint());
					WebUTIL.setMapInSession(essentialMap, request,"DuplicateCardGenerationAction");
					arrEpisopeVO=(EpisodeVO[]) essentialMap.get(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_REGISTRATION);
					if(arrEpisopeVO==null){
						br.append("<div class='div-table-row title'>");
						br.append("<div class='div-table-col' > Episode Detail</div>");
						br.append("</div>");
						br.append("<div class='div-table-row '>");
						br.append("<div class='div-table-col' > No Episode Detail Found</div>");
						br.append("</div>");

					}
				}
				else
				{
					arrEpisopeVO=(EpisodeVO[]) session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_REGISTRATION);	
				}
			}
			else if(objDuplicateCardGenerationSUP_p.getChoice().equals(RegistrationConfig.PRINT_CARD))
			{
				isPrintCardSelecter(arrEpisopeVO,session,essentialMap,objDuplicateCardGenerationSUP_p,request,br); 
			}
			else{

				arrEpisopeVO= null;
			}

			String billAmount = (String) session.getAttribute(RegistrationConfig.BILL_AMOUNT);	
			if(billAmount.equals("-1"))billAmount="0.00";					
			if(arrEpisopeVO!=null){
				br.append("<div class='div-table-row title'>");
				br.append("<div class='div-table-col' > Episode Detail</div>");
				br.append("</div>");

				br.append("<div class='div-table-row'>");
				br.append("<div class='div-table-col control' style='width: 8%;'>Select</div>");
				br.append("<div class='div-table-col control' style='width: 17%;'><b>Department</b></div>");
				br.append("<div class='div-table-col control' style='width: 25%;'><b>Unit</b></div>");
				br.append("<div class='div-table-col control' style='width: 25%;'><b>Last Visit Date</b></div>");
				br.append("<div class='div-table-col control' style='width: 25%;'><b>Unit Status</b></div>");
				br.append("</div>");
				for(int i=0;i<arrEpisopeVO.length;i++)
				{
					String check="";
					if(i==0){
						check="checked='checked'";
					}

					if(arrEpisopeVO[i].getIsCardPrintedToday()==null||arrEpisopeVO[i].getIsCardPrintedToday().equals("0"))
						br.append("<div class='div-table-row' >");
					else
						br.append("<div class='div-table-row' style='background-color: #FF0000'>");
					br.append("<div class='div-table-col control' style='width: 8%;'><input type='radio' onclick='displayAmount(this,"+billAmount+")' "+check+"name='departmentToGenerateDupCard' tabindex='1' value='"+arrEpisopeVO[i].getDepartmentUnitCode()+"'/></div>");
					br.append("<div class='div-table-col control' style='width: 17%;'>"+arrEpisopeVO[i].getDepartment()+"</div>");
					br.append("<div class='div-table-col control' style='width: 25%;'>"+arrEpisopeVO[i].getDepartmentUnit()+"</div>");
					br.append("<div class='div-table-col control' style='width: 25%;'>"+arrEpisopeVO[i].getEpisodeDate()+"</div>");
					br.append("<div class='div-table-col control' style='width: 25%;'>"+arrEpisopeVO[i].getDeptUnitIsOnDuty()+"</div>");
					br.append("</div>");

				}
				br.append("<div class='div-table-row'>");
			/*  ## 		Modification Log							
		 		##		Modify Date				:16thDec'14 
		 		##		Reason	(CR/PRS)		:Bug fix 7739
		 		##		Modify By				:Sheeldarshi */
				br.append("<div class='div-table-col label' style='width: 25%;'><font color='red'>*</font>Reason</div>");
				//End:sheeldarshi
				br.append("<div class='div-table-col control' style='width: 25%;'><textarea name='duplicateRenewRemarks' rows='1' cols='25'></textarea></div>");
				if(objDuplicateCardGenerationSUP_p.getChoice().equals(RegistrationConfig.DUPLICATE_CARD))
				{
					br.append("<div class='div-table-col label' id='labelRegFee' style='width: 25%;'>Print Fee</div>");
					br.append("<div class='div-table-col control' id='labelRegFee' style='width: 25%;'>	<input type='text' name='duplicateRenewCost'  tabindex='1' value='"+billAmount+"' onkeypress='return CheckMaxLength(event,this,150,1)' readonly='readonly' /></div>");
				}
				else{
					br.append("<div class='div-table-col label'  style='width: 25%;'></div>");
					br.append("<div class='div-table-col control' style='width: 25%;'></div>");
				}
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
	public static void isPrintCardSelecter(EpisodeVO[] arrEpiVO,HttpSession sess,Map essMap,DuplicateCardGenerationSUP objDupCardGenSUP_p,HttpServletRequest req,StringBuilder cr)
	{
		Status objStatus=new Status();
		try{
			String billAmt = (String) sess.getAttribute(RegistrationConfig.BILL_AMOUNT);
			if(sess.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_PRINT_CARD)==null)
			{
				essMap=DuplicateCardGenerationDATA.getPatientEpisodeDtl(objDupCardGenSUP_p.getPatCrNo(),getUserVO(req),objDupCardGenSUP_p.getChoice(),objDupCardGenSUP_p.getIsReprint());
				WebUTIL.setMapInSession(essMap, req,"DuplicateCardGenerationAction");
				arrEpiVO=(EpisodeVO[]) essMap.get(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_PRINT_CARD);
			}
			else
			{
				arrEpiVO=(EpisodeVO[]) sess.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_PRINT_CARD);	
			}
			if(arrEpiVO!=null){
				cr.append("<div class='div-table-row title'>");
				cr.append("<div class='div-table-col' > Episode Detail</div>");
				cr.append("</div>");

				cr.append("<div class='div-table-row'>");
				cr.append("<div class='div-table-col control' style='width: 8%;'>Select</div>");
				cr.append("<div class='div-table-col control' style='width: 17%;'><b>Department</b></div>");
				cr.append("<div class='div-table-col control' style='width: 25%;'><b>Unit</b></div>");
				cr.append("<div class='div-table-col control' style='width: 15%;'><b>Last Visit Date</b></div>");
				cr.append("<div class='div-table-col control' style='width: 20%;'><b>Unit Status</b></div>");
				cr.append("<div class='div-table-col control' style='width: 15%;'><b>Card Status</b></div>");
				cr.append("</div>");
				for(int i=0;i<arrEpiVO.length;i++)
				{
					String check="";
					if(i==0){
						check="checked='checked'";
					}

					if(arrEpiVO[i].getIsCardPrintedToday()==null||arrEpiVO[i].getIsCardPrintedToday().equals("0"))
						cr.append("<div class='div-table-row' >");
					else
						cr.append("<div class='div-table-row' style='background-color: #FF0000'>");

					if(arrEpiVO[i].getIsReprint() != null && arrEpiVO[i].getEpisodeVisitNo() !=null && arrEpiVO[i].getEpisodeVisitNo().equals("") )
					{
						if(arrEpiVO[i].getIsReprint().equals("1") && Integer.parseInt(arrEpiVO[i].getEpisodeVisitNo())>1 )
							arrEpiVO[i].setIsReprint("-1");
					}
					String temp=arrEpiVO[i].getDepartmentUnitCode()+ "#"+ arrEpiVO[i].getIsReprint()+"#"+ arrEpiVO[i].getEpisodeCode();
					cr.append("<div class='div-table-col control' style='width: 8%;'><input type='radio' onclick='displayAmount(this,"+billAmt+")' "+check+"name='departmentToGenerateDupCard' tabindex='1' value='"+temp +"'/></div>");
					cr.append("<div class='div-table-col control' style='width: 17%;'>"+arrEpiVO[i].getDepartment()+"</div>");
					cr.append("<div class='div-table-col control' style='width: 25%;'>"+arrEpiVO[i].getDepartmentUnit()+"</div>");
					cr.append("<div class='div-table-col control' style='width: 15%;'>"+arrEpiVO[i].getEpisodeDate()+"</div>");
					cr.append("<div class='div-table-col control' style='width: 20%;'>"+arrEpiVO[i].getDeptUnitIsOnDuty()+"</div>");
					if(arrEpiVO[i].getIsReprint().equals("-1"))
						cr.append("<div class='div-table-col control' style='width: 15%;'>"+"Duplicate"+"</div>");
					else
						cr.append("<div class='div-table-col control' style='width: 15%;'>"+"Reprint"+"</div>");
					cr.append("<input type='hidden' id= 'isReprintFlag' name='isReprintFlag' value='" +arrEpiVO[i].getIsReprint() + "' />");
					cr.append("</div>");

				}
				cr.append("</br>");
				cr.append("<div class='div-table-row'>");
				/*  ## 		Modification Log							
		 		##		Modify Date				:16thDec'14 
		 		##		Reason	(CR/PRS)		:Bug fix 7739
		 		##		Modify By				:Sheeldarshi */
				cr.append("<div class='div-table-col label' style='width: 25%;'><font color='red'>*</font>Reason</div>");
				//End:sheeldarshi
				cr.append("<div class='div-table-col control' style='width: 25%;'><textarea name='duplicateRenewRemarks' rows='1' cols='25'></textarea></div>");
				if(arrEpiVO[0].getIsReprint().equals("-1"))
				{
					cr.append("<div class='div-table-col' id='isDupFlag' style='display:block; width:50%;' >");
					cr.append("<div class='div-table-col label' id='labelRegFee' style='width: 50%;'>Print Fee</div>");
					cr.append("<div class='div-table-col control' id='labelRegFee' style='width: 50%;'>	<input type='text' name='duplicateRenewCost'  tabindex='1' value='"+billAmt+"' onkeypress='return CheckMaxLength(event,this,150,1)' readonly='readonly' /></div>");
					cr.append("</div>");
				}
				else
				{
					cr.append("<div class='div-table-col' id='isDupFlag' style='display:none; width:50%;'>");
					cr.append("<div class='div-table-col label' id='labelRegFee' style='width: 50%;'>Print Fee</div>");
					cr.append("<div class='div-table-col control' id='labelRegFee' style='width: 50%;'>	<input type='text' name='duplicateRenewCost'  tabindex='1' value='"+billAmt+"' onkeypress='return CheckMaxLength(event,this,150,1)' readonly='readonly' /></div>");
					cr.append("</div>");
					cr.append("<div class='div-table-col label'  style='width: 25%;'></div>");
					cr.append("<div class='div-table-col control' style='width: 25%;'></div>");
				}
				cr.append("</div>");
			}
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
			WebUTIL.setStatus(req,objStatus);
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



	public static void saveDuplicateCardPrinting(HttpServletRequest _req, DuplicateCardGenerationSUP objDuplicateCardGenerationSUP_p){
		Status status = new Status();
		try{
			UserVO userVO=getUserVO(_req);
			userVO.setTariffID(RegistrationConfig.DUPLICATE_RENEW_CARD_TARIFF_ID);
			userVO.setModuleId(Config.MODULE_ID_REGISTRATION);
			HttpSession session=WebUTIL.getSession(_req);

			getHospitalVO(_req);
			String departmentToGenDupCard=objDuplicateCardGenerationSUP_p.getDepartmentToGenerateDupCard();
			EpisodeVO episodeVO=new EpisodeVO();
			PatientVO patientVO= (PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);
			RegistrationSlip []rSlip=null;
			RegCardPrintVO regCardPrintVO=null;
			String billAmount="0.00";	
			/////////for displaying the name ////////////
			if(patientVO.getPatMiddleName()==null)
				patientVO.setPatMiddleName("");
			if(patientVO.getPatLastName()==null)
				patientVO.setPatLastName("");
			//////////print duplicate card///////////////////////
			if(objDuplicateCardGenerationSUP_p.getChoice().equals(RegistrationConfig.DUPLICATE_CARD))
			{
				objDuplicateCardGenerationSUP_p.setIsChoiceBack("0");

				EpisodeVO[] arrEpisodeVOSes = (EpisodeVO[])session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_DUPLICATE);
				EpisodeVO  epVO=new EpisodeVO();
				billAmount=(String)session.getAttribute(RegistrationConfig.BILL_AMOUNT);


				objDuplicateCardGenerationSUP_p.setDuplicateCharge(billAmount);
				patientVO.setPatAmountCollected(billAmount);
				//	DuplicateRenewVO[] duplicateRenewVO=new DuplicateRenewVO[departmentToGenDupCard.length];
				regCardPrintVO=new RegCardPrintVO();
				DirectChageDetailVO objDirectChageDetailVO=new DirectChageDetailVO();
				EpisodeVO episodeVOArray=new EpisodeVO();

				for(int i=0; i<arrEpisodeVOSes.length; i++)
					if(arrEpisodeVOSes[i].getDepartmentUnitCode().equals(departmentToGenDupCard)){
						epVO=arrEpisodeVOSes[i];
						regCardPrintVO=new RegCardPrintVO();
						regCardPrintVO.setDupRemarks(objDuplicateCardGenerationSUP_p.getDuplicateRenewRemarks());
						regCardPrintVO.setIpAdd(_req.getRemoteAddr());
						regCardPrintVO.setCardPrintFlag(objDuplicateCardGenerationSUP_p.getChoice());
						regCardPrintVO.setPatRegCatCode(patientVO.getPatRegCatCode());
						HelperMethods.populate(regCardPrintVO, epVO);
						objDirectChageDetailVO=new DirectChageDetailVO();
						HelperMethods.populate(objDirectChageDetailVO, epVO);
						objDirectChageDetailVO.setPatAmountCollected(billAmount);
						objDirectChageDetailVO.setSystemIPAddress(_req.getRemoteAddr());
						objDirectChageDetailVO.setPatPrimaryCatCode(patientVO.getPatPrimaryCatCode());
						regCardPrintVO.setPatAmountCollected(objDirectChageDetailVO.getPatAmountCollected());

						objDirectChageDetailVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
						objDirectChageDetailVO.setTariffId(RegistrationConfig.DUPLICATE_RENEW_CARD_TARIFF_ID);
						objDirectChageDetailVO.setModuleId(userVO.getModuleId());
						if(epVO.getEpisodeVisitType()!=null && epVO.getEpisodeVisitType().equals("3"))
						{
							objDirectChageDetailVO.setChargeType("3");
						}else if(epVO.getEpisodeVisitType()!=null && epVO.getEpisodeVisitType().equals("1")){
							objDirectChageDetailVO.setChargeType("1");
						}else{
							objDirectChageDetailVO.setChargeType("1");
						}
						objDirectChageDetailVO.setPaymentMode("1");//for cash
					}

				episodeVOArray=DuplicateCardGenerationDATA.saveDuplicateCardPrinting(objDirectChageDetailVO,regCardPrintVO, userVO);
				episodeVO=new EpisodeVO();
				HelperMethods.populate(episodeVO, regCardPrintVO);
				episodeVO.setUnitWorkingDays(epVO.getUnitWorkingDays());
				episodeVO.setDisclaimer(episodeVOArray.getDisclaimer());
				
				if(billAmount!=null && !(billAmount.equals("")))
					episodeVO.setPatAmountCollected(billAmount);
				else
					episodeVO.setPatAmountCollected(epVO.getPatAmountCollected());

				episodeVO.setEntryDate(epVO.getEntryDate());
				episodeVO.setEpisodeDate(epVO.getEpisodeDate());
				episodeVO.setQueNo(epVO.getQueNo());
				episodeVO.setIsGeneral(epVO.getIsGeneral());
				episodeVO.setCardPrintSetting(epVO.getCardPrintSetting());
				episodeVO.setReferFromDepartment(epVO.getReferFromDepartment());
				episodeVO.setIsCardPrint(epVO.getIsCardPrint());
				episodeVO.setUnitConsultant(epVO.getUnitConsultant());

			}
			if(objDuplicateCardGenerationSUP_p.getChoice().equals(RegistrationConfig.PRINT_CARD))
			{
				objDuplicateCardGenerationSUP_p.setIsChoiceBack("0");

				EpisodeVO[] arrEpisodeVOSes = (EpisodeVO[])session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_PRINT_CARD);
				EpisodeVO  epVO=new EpisodeVO();
				//String billAmount=(String)session.getAttribute(RegistrationConfig.BILL_AMOUNT);
				billAmount="0.00";	

				objDuplicateCardGenerationSUP_p.setDuplicateCharge(billAmount);
				patientVO.setPatAmountCollected(billAmount);
				//	DuplicateRenewVO[] duplicateRenewVO=new DuplicateRenewVO[departmentToGenDupCard.length];
				regCardPrintVO=new RegCardPrintVO();
				DirectChageDetailVO objDirectChageDetailVO=new DirectChageDetailVO();
				EpisodeVO episodeVOArray=new EpisodeVO();

				for(int i=0; i<arrEpisodeVOSes.length; i++){
					String[] parts = departmentToGenDupCard.split("#");
					String part1 = parts[0];//deptunitcode
					String part3 = parts[2];//episodeCode
//					if(arrEpisodeVOSes[i].getDepartmentUnitCode().equals(part1)){
					if(arrEpisodeVOSes[i].getEpisodeCode().equals(part3)){
						epVO=arrEpisodeVOSes[i];
						regCardPrintVO=new RegCardPrintVO();
						regCardPrintVO.setDupRemarks(objDuplicateCardGenerationSUP_p.getDuplicateRenewRemarks());
						regCardPrintVO.setIpAdd(_req.getRemoteAddr());
						regCardPrintVO.setCardPrintFlag(objDuplicateCardGenerationSUP_p.getChoice());
						regCardPrintVO.setPatRegCatCode(patientVO.getPatRegCatCode());
						HelperMethods.populate(regCardPrintVO, epVO);
						
						if(arrEpisodeVOSes[i].getIsReprint().equals("-1") )
						{
						billAmount=(String)session.getAttribute(RegistrationConfig.BILL_AMOUNT);
						objDuplicateCardGenerationSUP_p.setDuplicateCharge(billAmount);
						patientVO.setPatAmountCollected(billAmount);

						objDirectChageDetailVO=new DirectChageDetailVO();
						HelperMethods.populate(objDirectChageDetailVO, epVO);
						objDirectChageDetailVO.setPatAmountCollected(billAmount);
						objDirectChageDetailVO.setSystemIPAddress(_req.getRemoteAddr());
						objDirectChageDetailVO.setPatPrimaryCatCode(patientVO.getPatPrimaryCatCode());
						regCardPrintVO.setPatAmountCollected(objDirectChageDetailVO.getPatAmountCollected());
						objDirectChageDetailVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
						objDirectChageDetailVO.setTariffId(RegistrationConfig.DUPLICATE_RENEW_CARD_TARIFF_ID);
						objDirectChageDetailVO.setModuleId(userVO.getModuleId());
						objDirectChageDetailVO.setPaymentMode("1");//for cash
						if(epVO.getEpisodeVisitType()!=null && epVO.getEpisodeVisitType().equals("3"))
						{
							objDirectChageDetailVO.setChargeType("3");
						}else if(epVO.getEpisodeVisitType()!=null && epVO.getEpisodeVisitType().equals("1")){
							objDirectChageDetailVO.setChargeType("1");
						}else{
							objDirectChageDetailVO.setChargeType("1");
						}
						episodeVOArray=DuplicateCardGenerationDATA.saveDuplicateCardPrinting(objDirectChageDetailVO,regCardPrintVO, userVO);

						}
						else{
							episodeVOArray=DuplicateCardGenerationDATA.saveReprintCard(regCardPrintVO, userVO);
						}
					}
			}
				//episodeVOArray=DuplicateCardGenerationDATA.saveDuplicateCardPrinting(objDirectChageDetailVO,regCardPrintVO, userVO);
				episodeVO=new EpisodeVO();
				HelperMethods.populate(episodeVO, regCardPrintVO);
				episodeVO.setUnitWorkingDays(epVO.getUnitWorkingDays());
				episodeVO.setDisclaimer(episodeVOArray.getDisclaimer());
				
				if(billAmount!=null && !(billAmount.equals("")))
					episodeVO.setPatAmountCollected(billAmount);
				else
					episodeVO.setPatAmountCollected(epVO.getPatAmountCollected());				
				
				episodeVO.setEntryDate(epVO.getEntryDate());
				episodeVO.setEpisodeDate(epVO.getEpisodeDate());
				episodeVO.setQueNo(epVO.getQueNo());
				episodeVO.setIsGeneral(epVO.getIsGeneral());
				episodeVO.setCardPrintSetting(epVO.getCardPrintSetting());
				episodeVO.setReferFromDepartment(epVO.getReferFromDepartment());
				episodeVO.setIsCardPrint(epVO.getIsCardPrint());
				//Start:Sheeldarshi
				episodeVO.setIsReprint(epVO.getIsReprint());
				episodeVO.setUnitConsultant(epVO.getUnitConsultant());
				//End
				episodeVO.setEpisodeVisitType(epVO.getEpisodeVisitType());


			}
			////////reprint registration card///////////////////////
			EpisodeVO episodeVOArray=null;

			////////reprint registration card///////////////////////
			if(objDuplicateCardGenerationSUP_p.getChoice().equals(RegistrationConfig.REPRINT_REGISTRATION) )
			{
				objDuplicateCardGenerationSUP_p.setIsChoiceBack("0");

				EpisodeVO[] arrReprintEpisodeVO=(EpisodeVO[])session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_REGISTRATION);
				regCardPrintVO=new RegCardPrintVO();
				episodeVOArray=new EpisodeVO();
				EpisodeVO  epVO=new EpisodeVO();
				for(int i=0;i<arrReprintEpisodeVO.length;i++)
				{
					if(arrReprintEpisodeVO[i].getDepartmentUnitCode().equals(departmentToGenDupCard))
					{
						epVO=arrReprintEpisodeVO[i];
						regCardPrintVO=new RegCardPrintVO();
						regCardPrintVO.setDupRemarks(objDuplicateCardGenerationSUP_p.getDuplicateRenewRemarks());
						regCardPrintVO.setCardPrintFlag(objDuplicateCardGenerationSUP_p.getChoice());
						regCardPrintVO.setIpAdd(_req.getRemoteAddr());
						HelperMethods.populate(regCardPrintVO,epVO);
						regCardPrintVO.setPatRegCatCode(patientVO.getPatRegCatCode());
					}
				}
				userVO.setTariffID("");
				episodeVOArray=DuplicateCardGenerationDATA.saveReprintCard(regCardPrintVO, userVO);
				episodeVO=new EpisodeVO();
				HelperMethods.populate(episodeVO, regCardPrintVO);
				episodeVO.setUnitWorkingDays(epVO.getUnitWorkingDays());
				episodeVO.setDisclaimer(episodeVOArray.getDisclaimer());
				
				if(billAmount!=null && !(billAmount.equals("")))
					episodeVO.setPatAmountCollected(billAmount);
				else
					episodeVO.setPatAmountCollected(epVO.getPatAmountCollected());
				
				episodeVO.setEntryDate(epVO.getEntryDate());
				episodeVO.setQueNo(epVO.getQueNo());
				episodeVO.setIsGeneral(epVO.getIsGeneral());
				episodeVO.setCardPrintSetting(epVO.getCardPrintSetting());
				episodeVO.setReferFromDepartment(epVO.getReferFromDepartment());
				episodeVO.setIsCardPrint(epVO.getIsCardPrint());
				episodeVO.setExpiryDate(epVO.getExpiryDate());
				episodeVO.setUnitConsultant(epVO.getUnitConsultant());
				episodeVO.setEpisodeVisitType(epVO.getEpisodeVisitType());

				String isCardPrint=epVO.getIsCardPrint();
				//				if(isCardPrint.equalsIgnoreCase("2"))
				//				{
				//					String tmpFileName=RegistrationConfig.DUPLICATE_CARD+userVO.getSeatId();
				//					RegistrationSlip regSlip=prepareSlip(episodeVO,patientVO,objDuplicateCardGenerationSUP_p,_req);
				//					NewRegistrationSlip.print(regSlip, tmpFileName, _req, "1");					
				//					StringBuilder str=new StringBuilder();
				//					
				//					String strQueueNo=new String();
				//					String strDeptName=new String();
				//					String strUnitName=new String();
				//					String strRoomName=new String();
				//					
				//					{
				//						 strQueueNo=episodeVO.getQueNo();
				//						 strDeptName=episodeVO.getDepartment();
				//						 strUnitName=episodeVO.getDepartmentUnit();
				//						 strRoomName=episodeVO.getRoom();		
				//						 	    
				//					 }
				//					
				//					str.append("<br><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b>  Name &nbsp;&nbsp;    :: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");str.append(patientVO.getPatFirstName());str.append(" ");str.append(patientVO.getPatMiddleName());str.append(" ");str.append(patientVO.getPatLastName());str.append("</font><br>");
				//					str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b>  CR NO &nbsp;:: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");str.append(episodeVO.getPatCrNo());str.append("</font>");
				//					str.append("<table width='50%'><tr><td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>  Dept Name:: </font></div>");
				//					str.append("<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>  Unit No::</font></div>");
				//					str.append("<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>   Room No::</font></div>");
				//					 str.append("<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>   Queue No::</font><br></div></tr></table>");
				//					 str.append("<table width='50%'><tr><td width='25%'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");str.append(strDeptName);str.append("</font></div>");
				//					 str.append("<td width='25%'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");str.append(strUnitName);str.append("</font></div>");
				//					 str.append("<td width='25%'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");str.append(strRoomName);str.append("</font></div>");
				//					 str.append("<td width='25%'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");str.append(strQueueNo);str.append("</font><br></div></tr></table>");
				//					
				//					
				//					status.add(Status.DONE,str.toString(),"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divPatientVisitedLabel'>"+"Old Department Visit: "+"</div></font>");

				//				}

			}

			String tmpFileName=RegistrationConfig.DUPLICATE_CARD+userVO.getSeatId();

			EpisodeVO newEpisodeVO=new EpisodeVO();
			newEpisodeVO=episodeVO;
			/**to get the patient QrCode*/
			String strQRCode = EmgPatientVisitDATA.getPatientQRCode(patientVO.getPatCrNo(), userVO);
			QRCodeTest objQRC = new QRCodeTest();
			if(strQRCode!=null && !strQRCode.equals(""))
			{
				objQRC.createQRCode(strQRCode, _req);
			}
			/***/
			//calling barcode slip content creation method
			//WebUTIL.setAttributeInSession(_req,RegistrationConfig.EPISODE_FOR_BARCODE, episodeVO);
			//WebUTIL.setAttributeInSession(_req,RegistrationConfig.PATVO_FOR_BARCODE, patientVO);
			/***/
			EpisodeVO[] newArrEpisodeVOForPaidProcess = new EpisodeVO[1];
			newArrEpisodeVOForPaidProcess[0] = episodeVO;
			saveAndCreateBarcodeSlipForPaidProcess(_req, newArrEpisodeVOForPaidProcess, patientVO);
			/***/
			String	str = new String();	
			//Start:Sheeldarshi
			/*if(objDuplicateCardGenerationSUP_p.getChoice().equals(RegistrationConfig.DUPLICATE_CARD))
				str=NewRegistrationSlip.printDuplicateCard(prepareSlip(newEpisodeVO, patientVO, objDuplicateCardGenerationSUP_p, _req), tmpFileName,_req,"DC");
			else if(objDuplicateCardGenerationSUP_p.getChoice().equals(RegistrationConfig.REPRINT_REGISTRATION))
				str=NewRegistrationSlip.printDuplicateCard(prepareSlip(newEpisodeVO, patientVO, objDuplicateCardGenerationSUP_p, _req), tmpFileName,_req,"RP");
			else
				str=NewRegistrationSlip.printDuplicateCard(prepareSlip(newEpisodeVO, patientVO, objDuplicateCardGenerationSUP_p, _req), tmpFileName,_req,"DC");*/
			
			if(newEpisodeVO.getIsReprint()!=null && newEpisodeVO.getIsReprint().equals("1"))
				str=NewRegistrationSlip.printDuplicateCard(prepareSlip(newEpisodeVO, patientVO, objDuplicateCardGenerationSUP_p, _req), tmpFileName,_req,"RP");
			else
				str=NewRegistrationSlip.printDuplicateCard(prepareSlip(newEpisodeVO, patientVO, objDuplicateCardGenerationSUP_p, _req), tmpFileName,_req,"DC");
			//End
			str+="";
			
			//Bill Receipt Printing 
			if((billAmount!=null)&&(billAmount!="0")&&!(billAmount.equalsIgnoreCase("0.00")))
			{
				String billStr="";
				if(objDuplicateCardGenerationSUP_p.getChoice().equals(RegistrationConfig.DUPLICATE_CARD))
					billStr=NewRegistrationSlip.printBillReceipt(prepareSlip(episodeVO,patientVO,objDuplicateCardGenerationSUP_p, _req), tmpFileName,_req,"DC");
				else if(objDuplicateCardGenerationSUP_p.getChoice().equals(RegistrationConfig.REPRINT_REGISTRATION))
					billStr=NewRegistrationSlip.printBillReceipt(prepareSlip(episodeVO,patientVO,objDuplicateCardGenerationSUP_p, _req), tmpFileName,_req,"RP");
				else
					billStr=NewRegistrationSlip.printBillReceipt(prepareSlip(episodeVO,patientVO,objDuplicateCardGenerationSUP_p, _req), tmpFileName,_req,"DC");
				
				//str+=""+billStr+"";
			}
			
			objDuplicateCardGenerationSUP_p.setAfterGo("0");
			objDuplicateCardGenerationSUP_p.setTempAfterGo("0");
			if(!newEpisodeVO.getIsCardPrint().equalsIgnoreCase("2"))
				objDuplicateCardGenerationSUP_p.setPrint("1");				




			StringBuilder	strbuilder = new StringBuilder();	
			if(objDuplicateCardGenerationSUP_p.getChoice().equals(RegistrationConfig.PRINT_BACK_PAGE))
			{
				HelperMethods.setNullToEmpty(patientVO);

				strbuilder.append("<div class='div-table-simple'>");

				objDuplicateCardGenerationSUP_p.setIsChoiceBack("1");


				strbuilder.append("<div class='div-table-row'><div id='divHeader'>");
				strbuilder.append("<div class='div-table-col width20'><b><font size='2'face='Verdana, Arial, Helvetica, sans-serif'>CR No</font></b></div>");
				strbuilder.append("<div class='div-table-col width20'><b><font size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+ patientVO.getPatCrNo()+"</font></b>	</div>");
				strbuilder.append("<div class='div-table-col width20'><b><font size='2' face='Verdana, Arial, Helvetica, sans-serif'>Patient Name</font></b></div>");
				strbuilder.append("<div class='div-table-col width20'><b><font size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+patientVO.getPatFirstName().toUpperCase()+" "+patientVO.getPatMiddleName().toUpperCase()+" "+patientVO.getPatLastName().toUpperCase()+"</font></b></div></<div>");
				strbuilder.append("<div class='div-table-col width20'><b><font size='2' face='Verdana, Arial, Helvetica, sans-serif'></font></b></div></div></div>");


				strbuilder.append("<div class='div-table-row'><div class='div-table-col width25'><b><font size='2'face='Verdana, Arial, Helvetica, sans-serif'></font></b></div>");

				strbuilder.append("<div class='div-table-col width25'><b><font size='2' face='Verdana, Arial, Helvetica, sans-serif'></font></b></div>");
				strbuilder.append("<div class='div-table-col width25'><b><font size='2' face='Verdana, Arial, Helvetica, sans-serif'></font></b></div>");
				strbuilder.append("<div class='div-table-col width25'><b><font size='2' face='Verdana, Arial, Helvetica, sans-serif'></font></b></div>");
				objDuplicateCardGenerationSUP_p.setPrint("1");
				strbuilder.append("</div>");
				strbuilder.append("<div class='div-table-simple' style='border: 1px solid black; width:99%;height:800px;'>");
				strbuilder.append("<div class='div-table-row'>");
				strbuilder.append("<div class='div-table-col width20' style='text-align: center;border: 1px solid black;'><div id='divBlank1'><b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'>Date</font></b>");
				strbuilder.append("<br/>");
				strbuilder.append("<b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'>");
				//strbuilder.append(RegistrationConfig.OPD_CARD_DATE_MARATHI_LABEL);
				strbuilder.append("</font></b></div></div>");
				//strbuilder.append("<div class='div-table-col width20'><div><b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'>Symptoms and Progress</font></b>");
				strbuilder.append("<div class='div-table-col width60' style='text-align: center;border: 1px solid black;'><div><b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'>Clinical Notes/ Prescription</font></b>");
				
				strbuilder.append("<br/>");
				strbuilder.append("<b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'>");
				//strbuilder.append(RegistrationConfig.OPD_CARD_SYMPTOMSPROGRESS_MARATHI_LABEL);
				strbuilder.append("</font></b></div></div>");
				/*strbuilder.append("<div class='div-table-col width40'><div id='divBlank2'><b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'>Prescriptions</font></b>");
				strbuilder.append("<br/>");
				strbuilder.append("<b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'>");
				//strbuilder.append(RegistrationConfig.OPD_CARD_PRESCRIPTIONS_MARATHI_LABEL);
				strbuilder.append("</font></b></div></div>");*/
				//strbuilder.append("<div class='div-table-col width20'><div id='divBlank2'><b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'>For how many days</font></b>");
				strbuilder.append("<div class='div-table-col width20' style='text-align: center;border: 1px solid black;'><div id='divBlank2'><b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'>Investigations</font></b>");
				strbuilder.append("<br/>");
				strbuilder.append("<b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'>");
				//strbuilder.append(RegistrationConfig.OPD_CARD_HOWMANYDAYSILL_MARATHI_LABEL);
				strbuilder.append("</font></b></div></div></div>");
				strbuilder.append("<div class='div-table-col width20' style='border: 1px solid black;height:800px;'>");
				strbuilder.append("</div>");
				strbuilder.append("<div class='div-table-col width60' style='border: 1px solid black;height:800px;'>");
				strbuilder.append("</div>");
				strbuilder.append("<div class='div-table-col width20' style='border: 1px solid black;height:800px;'>");
				strbuilder.append("</div>");
				strbuilder.append("</div>");
				strbuilder.append("<div class='div-table-row'>");
				strbuilder.append("<div class='div-table-col width20'><div id='divBlank1'><b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'></font></b></div></div>");
				strbuilder.append("<div class='div-table-col width20'><div><b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'></font></b></div></div>");
				strbuilder.append("<div class='div-table-col width40'><div id='divBlank2'><b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'></font></b></div></div>");
				strbuilder.append("<div class='div-table-col width20'><div id='divBlank2'><b><font size='1' face='Verdana, Arial, Helvetica, sans-serif'></font></b></div></div></tr>");
				strbuilder.append("</table>");
			
				str=strbuilder.toString();

			}

			//status.add(Status.DONE,str.toString(),"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divPatientRegisteredLabel'>"+"Card Printed Successfully "+"</div></font>");

			objDuplicateCardGenerationSUP_p.setErrorMessage("The request processed successfully!");
			objDuplicateCardGenerationSUP_p.setPrintHtml("The request processed successfully!"+str);
			
			//objDuplicateCardGenerationSUP_p.setPrintHtmlTemp(str);//commented as card print should be stopped in AIIMS PTN
			objDuplicateCardGenerationSUP_p.setPrintHtmlTemp("");
			objDuplicateCardGenerationSUP_p.setIsPrintFlag("1");

			//status.add(Status.DONE,"","Card Printed Successfully");
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



	public static RegistrationSlip prepareSlip(EpisodeVO episodeVO,PatientVO _patVO,DuplicateCardGenerationSUP objDuplicateCardGenerationSUP_p,HttpServletRequest _request) throws ParseException{		

		RegistrationSlip regSlip=new RegistrationSlip();
		HelperMethods.setNullToEmpty(_patVO);
		HelperMethods.setNullToEmpty(episodeVO);
		HelperMethods.populate(regSlip,_patVO);
		regSlip.setPatAge(_patVO.getPatAge() +" "+_patVO.getPatAgeUnit());
		HospitalMstVO hospitalVO=getHospitalVO(_request);
		regSlip.setHospitalName(hospitalVO.getHospitalName());
		regSlip.setHospitalAddress1(hospitalVO.getAddress1());
		regSlip.setHospitalAddress2(hospitalVO.getAddress2());
		regSlip.setHospitalCity(hospitalVO.getCity());
		regSlip.setHospitalDistrict(hospitalVO.getDistrictName());
		regSlip.setHospitalState(hospitalVO.getStateName());
		regSlip.setHospitalpinCode(hospitalVO.getPinCode());
		regSlip.setHospitalPhone(hospitalVO.getPhone());
		regSlip.setHospitalFax(hospitalVO.getFax());
		regSlip.setHospitalEmail(hospitalVO.getEmail());
		regSlip.setEpisodeVisitType(episodeVO.getEpisodeVisitType());
		if(episodeVO.getPatAmountCollected()!=null && !(episodeVO.getPatAmountCollected().equals("")))
			regSlip.setEpisodeVisitAmount(episodeVO.getPatAmountCollected());
		else
			regSlip.setEpisodeVisitAmount("0.00");
		//for card print fee
		if(_patVO.getPatAmountCollected()!=null && !_patVO.getPatAmountCollected().equals(""))
			regSlip.setPatAmountCollected(_patVO.getPatAmountCollected());
		else
			regSlip.setPatAmountCollected("0.00");
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

		String patMiddleName=regSlip.getPatMiddleName();
		
		regSlip.setMlcDetail(_patVO.getMlcDetail().split("\\(")[0]);//  added by warish purpus to get mlc number

		if(!(patMiddleName==null || patMiddleName.equals("")))
		{
			patMiddleName=patMiddleName.substring(0,1).toUpperCase();
			regSlip.setPatMiddleName(patMiddleName);
		}
		/***/
		RegistrationConfigurationBean objRegConfigbean  = (RegistrationConfigurationBean)WebUTIL.getSession(_request).getAttribute(RegistrationConfig.Registration_Configuration_Bean);
		regSlip.setIsQRCodePrintFlag(objRegConfigbean.getIsQRCodePrint());
		/***/
		////Address For card
		String cityLocation="";
		String district="";
		String state="";

		String country=_patVO.getPatAddCountry();

		cityLocation=_patVO.getPatAddCity();
		if(_patVO.getPatAddDistrictCode()==null || _patVO.getPatAddDistrictCode().equals(""))
			district=_patVO.getPatAddDistrict();
		else
			district=_patVO.getPatAddDistrict();
		
			state=_patVO.getPatAddState();

		//inorder to prevent null showing for the location textbox on the print slip
		cityLocation=cityLocation==null?" ":cityLocation;
		String address=_patVO.getPatAddHNo();
		address=address+" "+_patVO.getPatAddStreet();
		
		address=address+" " +_patVO.getPatAddCity();
		if(district.equals("-1"))
			district="";
		String address2=district;

		if(!address2.equals(""))
			address2=address2+","+state;
		else
			address2=state;
		
		address2=address2+" "+country;
		if(_patVO.getPatAddPIN() != null && !_patVO.getPatAddPIN().equals(""))
		address2=address2+"-"+_patVO.getPatAddPIN();
		if(_patVO.getPatAddMobileNo() != null && !_patVO.getPatAddMobileNo().equals(""))
			address2=address2+" Mobile:"+_patVO.getPatAddMobileNo();
		
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
		//workingDays[0]=episodeVO.getUnitWorkingDays();
		String[] temp=episodeVO.getUnitWorkingDays().split("#");
		if(temp.length==0)
			temp=new String[]{"",""};
		System.out.println("temp[0]"+temp[0]);
		//System.out.println("temp[1]"+temp[1]);
		workingDays[0]=temp[0];
	    episodeDate[0]=episodeVO.getEpisodeDate();

	    SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
	    SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
	    /*      ## 		Modification Log							
		##		Modify Date				:13thMar'15 
		##		Reason	(CR/PRS)		:Display hours in valid upto field in bill print
		##		Modify By				:Sheeldarshi 
	   */
	   // expDate[0]=_sdf2.format(_sdf3.parse(expDate[0]));
	    if(expDate[0]!=null&&expDate[0]!="")
	    	expDate[0]=_sdf2.format(_sdf2.parse(expDate[0]));
		//End	
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
		//regSlip.setPatOccupation(patOccupation);
		regSlip.setDependentName(_patVO.getPatStaffName());
		regSlip.setPatMemRelationName(_patVO.getPatStaffRelationName());
		regSlip.setHiddenPatIdNo(_patVO.getPatStaffNo());
		regSlip.setPatMemDeptName(_patVO.getPatStaffDeptName());
		return regSlip;		
	}

public static void setAllDuplicateCardPrintEssentials(DuplicateCardGenerationSUP objDuplicateCardGenerationSUP,HttpServletRequest objRequest,
			HttpServletResponse objResponse, Map<Object, Object> mapSession,String callerName){		
		Status objStatus=new Status();
		Map mp=new HashMap();
		try
		{
			UserVO userVO=getUserVO(objRequest);
		
			userVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			userVO.setTariffID(RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);	
			
			//To Check the Cash Collection Back Date Day End Flag Check, Added by Singaravelan on 03-Jun-2015
			
			System.out.println("Back Date Day End Check Flag :::value :::"+userVO.getCheckBackDateDayEndFlag());			
			if(userVO.getCheckBackDateDayEndFlag().equals("1"))
				throw new HisRecordNotFoundException("Please do your day end first !");
			if(userVO.getCheckBackDateDayEndFlag().equals("2"))
				throw new HisRecordNotFoundException("Please deposit the money first at cash collection counter !");	
			
			mp=DuplicateCardGenerationDATA.getDuplicateCardGenerationInitialEssentials(userVO);
			WebUTIL.setMapInSession(mp,objRequest,callerName);
		}
		catch(HisRecordNotFoundException e){
			objDuplicateCardGenerationSUP.addActionError(e.getMessage());
			objStatus.add(Status.ERROR_DA,"",e.getMessage());
			//objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		}		
		catch(HisDataAccessException e){
		
			objStatus.add(Status.ERROR_DA,"",e.getMessage());	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}	
		finally
		{
			WebUTIL.setStatus(objRequest,objStatus);
		}	
	}
	
//By Mukund for printing barcode throuhg js
	public static String labelMethodForBarcodethroughJs(StringBuilder strbuilder, String patCrNo, String patName, String patAgeGender, String patMobile, String patEmgCntc, String patAddress , String barCodeGenSiString, String deptUnit, String roomNo, String docName, String fees, String epiDate, String patDistrictState)
	{
			strbuilder.append("<table style='margin-left:3%; margin-top:1%' cellspacing='0' cellpadding='0' width='90%'>");
			strbuilder.append("<tr style='font-size:11px;font-family:arial;'><td ><table cellspacing='1'  cellpadding='1' style='margin-left:1%'>"+
								"<tr><td colspan='3' nowrap='nowrap'><b>CR No</b>&nbsp;</td><td colspan='3' rowspan='2'><div id='divBarcodeSlipControl'>"+patCrNo+"</div></td></tr>"+
								"<tr><td colspan='3'><font size='4px;'><b>"+patCrNo+"</b></font></td></tr>"+
								"<tr><td>Name</td><td colspan='5'><b>"+patName+"</b></td></tr>"+
								"<tr><td>Age/Sex</td><td colspan='2'><b>"+patAgeGender+"</b></td><td>Mobile</td><td colspan='2'><b>"+patMobile+"</b></td></tr>"+
								"<tr><td>District/State</td><td colspan='2'><b>"+patDistrictState+"</b></td><td colspan='2'>Duplicate Booklet Fee</td><td>&#8377;<b>"+fees+"&#47;&#45;</b></td></tr>"+
								"<tr><td>Visit Date</td><td colspan='2'><b>"+epiDate+"</b></td><td >&nbsp;&nbsp;Block No</td><td colspan='2'><b>"+roomNo+"</b></td></tr>"+
								//"<tr><td>Doctor</td><td colspan='5'><b>"+docName.toUpperCase()+"</b></td></tr>"+
								"<tr><td>Dept/Unit</td><td colspan='5'><b>"+deptUnit+"</b></td></tr></table></td></tr>"
								);  
								
			strbuilder.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");
		 	System.out.println("Barcode Slip Content: "+strbuilder.toString());
		 	return strbuilder.toString();
	}
	
	//by Mukund for creating barcode slip for paid process
	public static void saveAndCreateBarcodeSlipForPaidProcess(HttpServletRequest _req, EpisodeVO []objEpisodeVO, PatientVO objPatVO){
		Status status = new Status();
		try{
			UserVO userVO=getUserVO(_req);
			getHospitalVO(_req);
			userVO.setModuleId(Config.MODULE_ID_REGISTRATION);

			HttpSession session=WebUTIL.getSession(_req);
			//EpisodeVO []episodeVO = (EpisodeVO[])session.getAttribute(RegistrationConfig.EPISODE_FOR_BARCODE);// "episodeVoArrForBarcodePrintingByMukund");
			//PatientVO patientVO= null;//(PatientVO)session.getAttribute(RegistrationConfig.REGISTRATIONDESK_PATIENT_VO);

			/*if(patientVO==null || patientVO.equals(""))
			{
				 patientVO= (PatientVO)session.getAttribute(RegistrationConfig.PATVO_FOR_BARCODE);//"patientVoInSessionForBarcodePrintingByMukund");
			}*/
			
			BarcodeSlipVO objBarcodeSlipVO=new BarcodeSlipVO();
		
			/////////for displaying the name ////////////
			if(objPatVO.getPatMiddleName()==null)
				objPatVO.setPatMiddleName("");
			if(objPatVO.getPatLastName()==null)
				objPatVO.setPatLastName("");
			
			
			String patName = objPatVO.getPatFirstName()+" "+objPatVO.getPatMiddleName()+" "+objPatVO.getPatLastName();
			patName = patName.toUpperCase();
			String patAgeWithUnit = objPatVO.getPatAge()+objPatVO.getPatAgeUnit();
			
			objBarcodeSlipVO.setPatCrNo(objPatVO.getPatCrNo());
			objBarcodeSlipVO.setBarcodePrintFlag("1");
			objBarcodeSlipVO.setDepartmentCode(objEpisodeVO[0].getDepartmentUnitCode().substring(0,3));
			objBarcodeSlipVO.setDepartmentUnitCode(objEpisodeVO[0].getDepartmentUnitCode());
			objBarcodeSlipVO.setEpisodeCode(objEpisodeVO[0].getEpisodeCode());
			objBarcodeSlipVO.setEpisodeVisitNo(objEpisodeVO[0].getEpisodeVisitNo());
			objBarcodeSlipVO.setPatName(patName);
			objBarcodeSlipVO.setPatAgeWithUnit(patAgeWithUnit);
			objBarcodeSlipVO.setPatGenderCode(objPatVO.getPatGenderCode());
			objBarcodeSlipVO.setPatMobile(objPatVO.getPatAddMobileNo());
			
			
			OutputStream os=new ByteArrayOutputStream();
			//EpisodeVO episodeVOArray = BarcodeGenerationDATA.saveBarcodePrintDtl(objBarcodeSlipVO, userVO);
			StringBuilder	strbuilder = new StringBuilder();	

			
			String patCrNo = objBarcodeSlipVO.getPatCrNo();
			String patAgeGender="";
			if(objPatVO.getIsActualDob().equals("1"))
			{
				if(objPatVO.getPatAgeWithUnit()==null||objPatVO.getPatAgeWithUnit().equals(""))
					objPatVO.setPatAgeWithUnit(objPatVO.getPatAge()+objPatVO.getPatAgeUnit());
				
				patAgeGender = objPatVO.getPatAgeWithUnit()+"/"+objPatVO.getPatGenderCode();
			}else{
				patAgeGender = objPatVO.getPatAge()+objPatVO.getPatAgeUnit()+"/"+objPatVO.getPatGenderCode();
			}
			
			String patMobile = objPatVO.getPatAddMobileNo();
			String patAddress = objPatVO.getPatAddHNo()+" "+objPatVO.getPatAddStreet()+" "+objPatVO.getPatAddCityLoc()+" "+objPatVO.getPatAddDistrict()+" "+objPatVO.getPatAddState()+" "+(objPatVO.getPatAddPIN()==null?"":objPatVO.getPatAddPIN());
			String patEmgCntc = ((objPatVO.getPatEmgCntNo()==null)?"":objPatVO.getPatEmgCntNo());//patientVO.getPatEmgCntNo();
			
			String deptUnit = objEpisodeVO[0].getDepartment()+" ("+objEpisodeVO[0].getDepartmentUnit()+")";
			String patDistrictState=objPatVO.getPatAddDistrict()+" / "+objPatVO.getPatAddState(); //  added by manisha dt 24.7.18
			
			
			String roomNo = objEpisodeVO[0].getRoom();
			String docName = objEpisodeVO[0].getUnitConsultant();
			String fee = objEpisodeVO[0].getPatAmountCollected();
			String epiDate = "";
			
			SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			//epiDate=_sdf2.format(_sdf1.parse((String)_req.getSession().getAttribute(Config.SYSDATE)));
		    epiDate=objEpisodeVO[0].getEpisodeDate();
			
			String barCodeGenSiString=String.valueOf(17);
			/*Barcode     barcode = BarcodeFactory.createCode128(patCrNo);
	
			barcode.setBarWidth(1);
			barcode.setResolution(203);
  		  
		     Font font=new Font("Plain",Font.PLAIN,0);
		     barcode.setFont(font);
		     BarcodeImageHandler.writePNG(barcode, os);
				
		  
		  ByteArrayOutputStream bos=(ByteArrayOutputStream)os;
			 byte[] data=bos.toByteArray();
			session.setAttribute("Upload_File_WithServlet", data); */
			 			
			 //String barcodeSlipLabel = labelMethod(strbuilder, patCrNo,  patName,  patAgeGender,  patMobile,  patEmgCntc,  patAddress ,  barCodeGenSiString,  deptUnit,  roomNo, docName, fee, epiDate, patDistrictState);
			String barcodeSlipLabel = labelMethodForBarcodethroughJs(strbuilder, patCrNo,  patName,  patAgeGender,  patMobile,  patEmgCntc,  patAddress ,  barCodeGenSiString,  deptUnit,  roomNo, docName, fee, epiDate, patDistrictState);
		  			
	 	session.setAttribute("sampleNoLabelBarCodeString", barcodeSlipLabel);
	 	

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

}

