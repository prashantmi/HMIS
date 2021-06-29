package registration.transactions.controller.util;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.config.RegistrationConfig;
import registration.config.slip.NewRegistrationSlip;
import registration.config.slip.RegistrationSlip;
import registration.transactions.controller.actionsupport.BarcodeGenerationSUP;
import registration.transactions.controller.data.BarcodeGenerationDATA;
import vo.registration.DirectChageDetailVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.BarcodeSlipVO;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class BarcodeGenerationUTIL extends ControllerUTIL
{

	private static final PrintStream strbuilder = null;



	public static void getPatientDetail(BarcodeGenerationSUP objBarcodeGenerationSUP_p, HttpServletRequest request){
		Status status=new Status();
		setSysdate(request);
		PatientVO patVO = new PatientVO();
		try{
			PatDetailUTIL.getPatientDtlByCrno(objBarcodeGenerationSUP_p, request);

			if(WebUTIL.getStatus(request).getMessage("007").equalsIgnoreCase("Patient Details Not Found"))
			{
				objBarcodeGenerationSUP_p.setErrorMessage("Patient Details Not Found");
				throw new HisRecordNotFoundException("Patient Details Not Found");
			}
			else if(WebUTIL.getStatus(request).getMessage("007").equalsIgnoreCase("Current Patient Category is invalid please change the Patient Category first"))
			{
				objBarcodeGenerationSUP_p.setErrorMessage("Current Patient Category is invalid please change the Patient Category first");
				throw new HisRecordNotFoundException("Current Patient Category is invalid please change the Patient Category first");
			}
			else
			{
				if(WebUTIL.getSession(request).getAttribute(RegistrationConfig.PATIENT_VO)!=null)
				{
					patVO = (PatientVO) request.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
				}
				if(patVO!=null){


					if(patVO.getIsCatExpired()!=null && patVO.getIsCatExpired().equals("1"))
					{
						objBarcodeGenerationSUP_p.setErrorMessage("Patient Category Validity is over. Kindly renew it first.");
						throw new HisRecordNotFoundException("Patient Still Unknown. Use Unknown to Known Conversion");
					}
					if(patVO.getPatIsDead().equals("1"))
					{
						objBarcodeGenerationSUP_p.setErrorMessage("Patient is Dead");
						throw new HisRecordNotFoundException("Patient Still Unknown. Use Unknown to Known Conversion");
					}
	
					if(patVO.getPatIsMerged()!=null && patVO.getPatIsMerged().equals("2"))
					{
						objBarcodeGenerationSUP_p.setAfterGo("0");
						objBarcodeGenerationSUP_p.setErrorMessage("CR No is Not Valid, Already Merged with CR No. " +patVO.getPatMergedMainCrNO());
						throw new HisRecordNotFoundException("CR No is Not Valid, Already Merged with CR No. " +patVO.getPatMergedMainCrNO() );
					}
					HelperMethods.populate(objBarcodeGenerationSUP_p, patVO);
					objBarcodeGenerationSUP_p.setChoice(RegistrationConfig.DUPLICATE_CARD);
					setPatientEpisodeDtl(objBarcodeGenerationSUP_p,request);
					//setBillAmount(request);
					System.out.println("BarcodeGenerationUTIL.GetPatDtl(): "+objBarcodeGenerationSUP_p.getPatFirstName());


					objBarcodeGenerationSUP_p.setTempAfterGo("1");
					objBarcodeGenerationSUP_p.setAfterGo("1");
				}	
			}
		}
		catch (HisRecordNotFoundException e)
		{
			objBarcodeGenerationSUP_p.setAfterGo("0");
			objBarcodeGenerationSUP_p.setTempAfterGo("0");
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
	

	public static void setPatientEpisodeDtl(BarcodeGenerationSUP objBarcodeGenerationSUP_p, HttpServletRequest request){
		Status status = new Status();
		HttpSession session=WebUTIL.getSession(request);
		Map mp=new HashMap();
		objBarcodeGenerationSUP_p.setDuplicateRenewRemarks("");
		try{
			//session.setAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_DUPLICATE,null);
			//session.setAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_REGISTRATION,null);
			UserVO userVO =getUserVO(request);
			PatientVO patVO= (PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);;
			if(patVO!=null){
				if(objBarcodeGenerationSUP_p.getPatCrNo()!=null){
					patVO.setPatCrNo(objBarcodeGenerationSUP_p.getPatCrNo());
				}

				if(objBarcodeGenerationSUP_p.getCrNoToRetrieve()!=null && !objBarcodeGenerationSUP_p.getCrNoToRetrieve().equals("")){
					patVO.setPatCrNo(objBarcodeGenerationSUP_p.getCrNoToRetrieve());
				}


				mp=BarcodeGenerationDATA.getPatientEpisodeDtl(patVO.getPatCrNo(), userVO,objBarcodeGenerationSUP_p.getChoice(),objBarcodeGenerationSUP_p.getIsReprint());
				WebUTIL.populate(objBarcodeGenerationSUP_p,patVO);

				WebUTIL.setMapInSession(mp, request,"BarcodeGenerationAction");
			}
			status.add(status.RECORDFOUND,"","");
			status.add(status.TRANSINPROCESS,"","");
		}catch(HisRecordNotFoundException e){
			mp=e.getEssentialMap();
			WebUTIL.setMapInSession(mp, request ,"BarcodeGenerationAction");
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



	public static void setDetailsByChoice( BarcodeGenerationSUP objBarcodeGenerationSUP_p,HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		EpisodeVO[] arrEpisopeVO=null;
		StringBuilder br = new StringBuilder(1000);
		
		if(request.getParameter("choice")!=null)
			objBarcodeGenerationSUP_p.setChoice(request.getParameter("choice"));
		if(request.getParameter("patCrNo")!=null)
			objBarcodeGenerationSUP_p.setPatCrNo(request.getParameter("patCrNo"));
		try{
			
					essentialMap=BarcodeGenerationDATA.getPatientEpisodeDtl(objBarcodeGenerationSUP_p.getPatCrNo(),getUserVO(request),objBarcodeGenerationSUP_p.getChoice(),objBarcodeGenerationSUP_p.getIsReprint());
					WebUTIL.setMapInSession(essentialMap, request ,"BarcodeGenearationAction");
					arrEpisopeVO=(EpisodeVO[]) essentialMap.get("episodesForBarcodePrint");
			
					if(arrEpisopeVO==null){
						br.append("<div class='div-table-row title'>");
						br.append("<div class='div-table-col' > Episode Detail</div>");
						br.append("</div>");
						br.append("<div class='div-table-row '>");
						br.append("<div class='div-table-col' > No Episode Detail Found</div>");
						br.append("</div>");

					}
									
			if(arrEpisopeVO!=null){
				br.append("<div class='div-table-row title'>");
				br.append("<div class='div-table-col' > Episode Detail</div>");
				br.append("</div>");

				br.append("<div class='div-table-row'>");
				br.append("<div class='div-table-col control' style='width: 8%;'><b>Select</b></div>");
				br.append("<div class='div-table-col control' style='width: 17%;'><b>Department</b></div>");
				br.append("<div class='div-table-col control' style='width: 25%;'><b>Unit</b></div>");
				br.append("<div class='div-table-col control' style='width: 25%;'><b>Last Visit Date</b></div>");
				//br.append("<div class='div-table-col control' style='width: 25%;'><b>Barcode Print Status</b></div>");
				br.append("</div>");
				for(int i=0;i<arrEpisopeVO.length;i++)
				{
					String check="";
					if(i==0){
						check="checked='checked'";
					}
					String button = "<a class='button' id='submitId' href='#' ><span class='print'>Print</span></a>";
					
					br.append("<div class='div-table-row' >");
					//br.append("<div class='div-table-col control' style='width: 8%;'><input type='radio' onclick='displayAmount(this,"+billAmount+")' "+check+"name='departmentToGenerateDupCard' tabindex='1' value='"+arrEpisopeVO[i].getDepartmentUnitCode()+"'/></div>");
					br.append("<div class='div-table-col control' style='width: 8%;'><input type='radio' "+check+"name='departmentToGenerateBarcode' tabindex='1' value='"+arrEpisopeVO[i].getDepartmentUnitCode()+"#"+arrEpisopeVO[i].getEpisodeCode()+"#"+arrEpisopeVO[i].getEpisodeVisitNo()+"'/></div>");
					br.append("<div class='div-table-col control' style='width: 17%;'>"+arrEpisopeVO[i].getDepartment()+"</div>");
					br.append("<div class='div-table-col control' style='width: 25%;'>"+arrEpisopeVO[i].getDepartmentUnit()+"</div>");
					br.append("<div class='div-table-col control' style='width: 25%;'>"+arrEpisopeVO[i].getEpisodeDate()+"</div>");
					//br.append("<div class='div-table-col control' style='width: 25%;'>"+button+"</div>");
					br.append("</div>");

				}
				br.append("<div class='div-table-row'>");
			
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



	public static void saveBarcodePrinting(HttpServletRequest _req, BarcodeGenerationSUP objBarcodeGenerationSUP_p){
		Status status = new Status();
		try{
			UserVO userVO=getUserVO(_req);
			userVO.setModuleId(Config.MODULE_ID_REGISTRATION);
			HttpSession session=WebUTIL.getSession(_req);

			getHospitalVO(_req);
			String departmentToGenBarcode=objBarcodeGenerationSUP_p.getDepartmentToGenerateBarcode();
			String[] arrDepartmentToGenBarcode = departmentToGenBarcode.split("#");
			PatientVO patientVO= (PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);
			
			BarcodeSlipVO objBarcodeSlipVO=new BarcodeSlipVO();
			HelperMethods.populate(objBarcodeSlipVO, objBarcodeGenerationSUP_p);
			
			objBarcodeSlipVO.setPatCrNo(objBarcodeGenerationSUP_p.getPatCrNo());
			objBarcodeSlipVO.setBarcodePrintFlag("1");
			objBarcodeSlipVO.setDepartmentCode(arrDepartmentToGenBarcode[0].substring(0,3));
			objBarcodeSlipVO.setDepartmentUnitCode(arrDepartmentToGenBarcode[0]);
			objBarcodeSlipVO.setEpisodeCode(arrDepartmentToGenBarcode[1]);
			objBarcodeSlipVO.setEpisodeVisitNo(arrDepartmentToGenBarcode[2]);
			objBarcodeSlipVO.setPatName(objBarcodeGenerationSUP_p.getPatName());
			objBarcodeSlipVO.setPatAgeWithUnit(objBarcodeGenerationSUP_p.getPatAge()+" "+objBarcodeGenerationSUP_p.getPatAgeUnit());
			objBarcodeSlipVO.setPatGenderCode(objBarcodeGenerationSUP_p.getPatGenderCode());
			objBarcodeSlipVO.setPatMobile(objBarcodeGenerationSUP_p.getPatMobile());
			EpisodeVO[] arrEpisopeVO=null;
			arrEpisopeVO = (EpisodeVO[])session.getAttribute("episodesForBarcodePrint"); //(EpisodeVO[]) essentialMap.get("episodesForBarcodePrint");
			
			String deptUnit ="";// episodeVO[0].getDepartment()+"/"+episodeVO[0].getDepartmentUnit();
			String roomNo = "";//episodeVO[0].getRoom();
			String docName = "";
			String fee = "";
			String epiDate = "";
			SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		    
			//epiDate=_sdf2.format(_sdf1.parse((String)_req.getSession().getAttribute(Config.SYSDATE)));
						
			for(int i=0;i<arrEpisopeVO.length;i++)
			{
				if(arrEpisopeVO[i].getEpisodeCode().equals(objBarcodeSlipVO.getEpisodeCode()))
				{
					if(arrEpisopeVO[i].getDepartment().contains("("))
						deptUnit = arrEpisopeVO[i].getDepartment();
					else
						deptUnit = arrEpisopeVO[i].getDepartment()+" ("+arrEpisopeVO[i].getDepartmentUnit()+")";
					
					 roomNo = arrEpisopeVO[i].getRoom();
					 docName = arrEpisopeVO[i].getUnitConsultant();
					 fee = arrEpisopeVO[i].getPatAmountCollected();
					 epiDate=arrEpisopeVO[i].getEpisodeDate();
				}
			}
			OutputStream os=new ByteArrayOutputStream();
			/////////for displaying the name ////////////
			if(patientVO.getPatMiddleName()==null)
				patientVO.setPatMiddleName("");
			if(patientVO.getPatLastName()==null)
				patientVO.setPatLastName("");
			//////////print duplicate card///////////////////////
			
			////////reprint registration card///////////////////////
			EpisodeVO episodeVOArray=null;

			////////reprint registration card///////////////////////
			
			//episodeVOArray=BarcodeGenerationDATA.saveDuplicateCardPrinting(objDirectChageDetailVO,regCardPrintVO, userVO);
			episodeVOArray=BarcodeGenerationDATA.saveBarcodePrintDtl(objBarcodeSlipVO, userVO);
			StringBuilder	strbuilder = new StringBuilder();	
			
			
			String patCrNo = objBarcodeSlipVO.getPatCrNo();
			String patName = patientVO.getPatFirstName().toUpperCase()+" "+patientVO.getPatMiddleName().toUpperCase()+" "+patientVO.getPatLastName().toUpperCase();
			String patAgeGender="";
			if(patientVO.getIsActualDob().equals("1"))
			{
				if(patientVO.getPatAgeWithUnit()==null||patientVO.getPatAgeWithUnit().equals(""))
					patientVO.setPatAgeWithUnit(patientVO.getPatAge()+patientVO.getPatAgeUnit());
				
				patAgeGender = patientVO.getPatAgeWithUnit()+"/"+patientVO.getPatGenderCode();
			}else{
				patAgeGender = patientVO.getPatAge()+patientVO.getPatAgeUnit()+"/"+patientVO.getPatGenderCode();
			}
			String patMobile = patientVO.getPatAddMobileNo();
			String patAddress = patientVO.getPatAddHNo()+" "+patientVO.getPatAddStreet()+" "+patientVO.getPatAddCityLoc()+" "+patientVO.getPatAddDistrict()+" "+patientVO.getPatAddState()+" "+(patientVO.getPatAddPIN()==null?"":patientVO.getPatAddPIN());
			
			String patEmgCntc = ((patientVO.getPatEmgCntNo()==null)?"":patientVO.getPatEmgCntNo());

			String patDistrictState=patientVO.getPatAddDistrict()+" / "+patientVO.getPatAddState();   //  added by manisha dt 24.7.18
			
			
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
  		     			
  			/*strbuilder.append("<table style='margin-left:5%; margin-top:1%' cellspacing='0' cellpadding='0' width='90%'>");
  			strbuilder.append("<tr><td ><div id='1diivBarCodeControl'><center><img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no image found in session\"></center></div></td></tr>");
  			//strbuilder.append("<tr><td style='font-size:12px;' ><div id='1diivBarCodeControl'><center>"+patCrNo+"</center></div></td></tr>");
  			//strbuilder.append("<tr><td style='font-size:12px;' ><div id='1diivBarCodeControl'></div></td></tr>");
  			strbuilder.append("<tr style='font-size:13px;font-family:arial;'><td ><table cellspacing='1'  cellpadding='1' style='margin-left:1%'>"+
  								"<tr ><td >CR No</td><td colspan='3' ><b>"+patCrNo+"</b></td></tr>"+
  							    "<tr ><td >Name</td><td colspan='3' ><b>"+patName+"</b></td></tr>"+
  								"<tr ><td >Age/Sex</td><td><b>"+patAgeGender+"</b></td><td>Mobile</td><td><b>"+patMobile+"</b></td></tr>"+
  								//"<tr style='font-size:12px;'><td ><b>Mobile</b></td><td >"+patMobile+"</td></tr>"+
  								"<tr ><td >Address</td><td colspan='3'><b>"+patAddress+"</b></td></tr></table></td></tr>");
  			strbuilder.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");
  			 */	
  			//String barcodeSlipLabel = labelMethod(strbuilder, patCrNo,  patName,  patAgeGender,  patMobile,  patEmgCntc,  patAddress ,  barCodeGenSiString,  deptUnit,  roomNo, docName, fee, epiDate, patDistrictState);
  			String barcodeSlipLabel = labelMethodForBarcodethroughJs(strbuilder, patCrNo,  patName,  patAgeGender,  patMobile,  patEmgCntc,  patAddress ,  barCodeGenSiString,  deptUnit,  roomNo, docName, fee, epiDate, patDistrictState);

	 	session.setAttribute("sampleNoLabelBarCodeString", barcodeSlipLabel);
	 	objBarcodeGenerationSUP_p.setSaveConfirmFlag("1");

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

	
	public static void saveBarcodePrintingFromOutsideProcess(HttpServletRequest _req, BarcodeGenerationSUP objBarcodeGenerationSUP_p){
		Status status = new Status();
		try{
			UserVO userVO=getUserVO(_req);
			userVO.setModuleId(Config.MODULE_ID_REGISTRATION);
			HttpSession session=WebUTIL.getSession(_req);

			getHospitalVO(_req);
			EpisodeVO []episodeVO = (EpisodeVO[])session.getAttribute(RegistrationConfig.EPISODE_FOR_BARCODE);// "episodeVoArrForBarcodePrintingByMukund");
			PatientVO patientVO= null;//(PatientVO)session.getAttribute(RegistrationConfig.REGISTRATIONDESK_PATIENT_VO);

			if(patientVO==null || patientVO.equals(""))
			{
				 patientVO= (PatientVO)session.getAttribute(RegistrationConfig.PATVO_FOR_BARCODE);//"patientVoInSessionForBarcodePrintingByMukund");
			}
			
			BarcodeSlipVO objBarcodeSlipVO=new BarcodeSlipVO();
		
			/////////for displaying the name ////////////
			if(patientVO.getPatMiddleName()==null)
				patientVO.setPatMiddleName("");
			if(patientVO.getPatLastName()==null)
				patientVO.setPatLastName("");
			
			
			String patName = patientVO.getPatFirstName()+" "+patientVO.getPatMiddleName()+" "+patientVO.getPatLastName();
			patName = patName.toUpperCase();
			String patAgeWithUnit = patientVO.getPatAge()+patientVO.getPatAgeUnit();
			
			objBarcodeSlipVO.setPatCrNo(patientVO.getPatCrNo());
			objBarcodeSlipVO.setBarcodePrintFlag("1");
			objBarcodeSlipVO.setDepartmentCode(episodeVO[0].getDepartmentUnitCode().substring(0,3));
			objBarcodeSlipVO.setDepartmentUnitCode(episodeVO[0].getDepartmentUnitCode());
			objBarcodeSlipVO.setEpisodeCode(episodeVO[0].getEpisodeCode());
			objBarcodeSlipVO.setEpisodeVisitNo(episodeVO[0].getEpisodeVisitNo());
			objBarcodeSlipVO.setPatName(patName);
			objBarcodeSlipVO.setPatAgeWithUnit(patAgeWithUnit);
			objBarcodeSlipVO.setPatGenderCode(patientVO.getPatGenderCode());
			objBarcodeSlipVO.setPatMobile(patientVO.getPatAddMobileNo());
			
			
			OutputStream os=new ByteArrayOutputStream();
			EpisodeVO episodeVOArray = BarcodeGenerationDATA.saveBarcodePrintDtl(objBarcodeSlipVO, userVO);
			StringBuilder	strbuilder = new StringBuilder();	

			
			String patCrNo = objBarcodeSlipVO.getPatCrNo();
			String patAgeGender="";
			if(patientVO.getIsActualDob().equals("1"))
			{
				if(patientVO.getPatAgeWithUnit()==null||patientVO.getPatAgeWithUnit().equals(""))
					patientVO.setPatAgeWithUnit(patientVO.getPatAge()+patientVO.getPatAgeUnit());
				
				patAgeGender = patientVO.getPatAgeWithUnit()+"/"+patientVO.getPatGenderCode();
			}else{
				patAgeGender = patientVO.getPatAge()+patientVO.getPatAgeUnit()+"/"+patientVO.getPatGenderCode();
			}
			
			String patMobile = patientVO.getPatAddMobileNo();
			String patAddress = patientVO.getPatAddHNo()+" "+patientVO.getPatAddStreet()+" "+patientVO.getPatAddCityLoc()+" "+patientVO.getPatAddDistrict()+" "+patientVO.getPatAddState()+" "+(patientVO.getPatAddPIN()==null?"":patientVO.getPatAddPIN());
			String patEmgCntc = ((patientVO.getPatEmgCntNo()==null)?"":patientVO.getPatEmgCntNo());//patientVO.getPatEmgCntNo();
			
			String deptUnit = episodeVO[0].getDepartment()+" ("+episodeVO[0].getDepartmentUnit()+")";
			String patDistrictState=patientVO.getPatAddDistrict()+" / "+patientVO.getPatAddState(); //  added by manisha dt 24.7.18
			
			
			String roomNo = episodeVO[0].getRoom();
			String docName = episodeVO[0].getUnitConsultant();
			String fee = episodeVO[0].getPatAmountCollected();
			String epiDate = "";
			
			SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			epiDate=_sdf2.format(_sdf1.parse((String)_req.getSession().getAttribute(Config.SYSDATE)));
			
			String barCodeGenSiString=String.valueOf(17);
			Barcode     barcode = BarcodeFactory.createCode128(patCrNo);
	
			barcode.setBarWidth(1);
			barcode.setResolution(203);
    		  
  		     Font font=new Font("Plain",Font.PLAIN,0);
  		     barcode.setFont(font);
  		     BarcodeImageHandler.writePNG(barcode, os);
				
  		  
  		  ByteArrayOutputStream bos=(ByteArrayOutputStream)os;
  			 byte[] data=bos.toByteArray();
  			session.setAttribute("Upload_File_WithServlet", data); 
  			
  			/*strbuilder.append("<table style='margin-left:5%; margin-top:1%' cellspacing='0' cellpadding='0' width='90%'>");
  			strbuilder.append("<tr><td ><div id='1diivBarCodeControl'><center><img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no image found in session\"></center></div></td></tr>");
  			strbuilder.append("<tr style='font-size:13px;font-family:arial;'><td ><table cellspacing='1'  cellpadding='1' style='margin-left:1%'>"+
  								"<tr ><td >CR No</td><td colspan='3' ><b>"+patCrNo+"</b></td></tr>"+
  							    "<tr ><td >Name</td><td colspan='3' ><b>"+patName+"</b></td></tr>"+
  								//"<tr ><td >Age/Sex</td><td><b>"+patAgeGender+"</b></td><td>Mobile</td><td><b>"+patMobile+"</b></td></tr>"+
  								"<tr ><td >Age/Sex</td><td><b>"+patAgeGender+"</b></td><td></td><td></td></tr>"+
  								"<tr ><td >Mobile</td><td><b>"+patMobile+"</b></td><td>&nbsp;&nbsp;Emrgency Contact</td><td><b>"+patEmgCntc+"</b></td></tr>"+
  								//"<tr ><td >Emg Contact</td><td colspan='3'><b>"+patEmgCntc+"</b></td></tr>"+
  								"<tr ><td >Address</td><td colspan='3'><b>"+patAddress+"</b></td></tr></table></td></tr>");
  			strbuilder.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");
  			
  			strbuilder.append("<br><br>");
  			strbuilder.append("<table style='margin-left:5%; margin-top:1%' cellspacing='0' cellpadding='0' width='90%'>");
  			strbuilder.append("<tr><td ><div id='1diivBarCodeControl'><center><img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no image found in session\"></center></div></td></tr>");
  			strbuilder.append("<tr style='font-size:13px;font-family:arial;'><td ><table cellspacing='1'  cellpadding='1' style='margin-left:1%'>"+
  								"<tr ><td >CR No</td><td colspan='3' ><b>"+patCrNo+"</b></td></tr>"+
  							    "<tr ><td >Name</td><td colspan='3' ><b>"+patName+"</b></td></tr>"+
  								"<tr ><td >Age/Sex</td><td colspan='3' ><b>"+patAgeGender+"</b></td></tr>"+//"<td>Mobile</td><td><b>"+patMobile+"</b></td></tr>"+
  								"<tr ><td >Department/Unit</td><td colspan='3' ><b>"+deptUnit+"</b></td></tr>"+
  								"<tr ><td >Room No</td><td colspan='3'><b>"+roomNo+"</b></td></tr></table></td></tr>");
  			strbuilder.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");*/
  			
  			String barcodeSlipLabel = labelMethod(strbuilder, patCrNo,  patName,  patAgeGender,  patMobile,  patEmgCntc,  patAddress ,  barCodeGenSiString,  deptUnit,  roomNo, docName, fee, epiDate,patDistrictState);
  			
  	 	//System.out.println("Barcode Slip Content: "+barcodeSlipLabel);
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

	
	public static String labelMethod2(StringBuilder strbuilder, String patCrNo, String patName, String patAgeGender, String patMobile, String patEmgCntc, String patAddress , String barCodeGenSiString, String deptUnit, String roomNo)
	{
		/*strbuilder.append("<table style='margin-left:5%; margin-top:1%' cellspacing='0' cellpadding='0' width='90%'>");
		strbuilder.append("<tr><td ><div id='1diivBarCodeControl'><center><img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no image found in session\"></center></div></td></tr>");
			strbuilder.append("<tr style='font-size:13px;font-family:arial;'><td ><table cellspacing='1'  cellpadding='1' style='margin-left:1%'>"+
								"<tr ><td >CR No</td><td colspan='3' ><b>"+patCrNo+"</b></td></tr>"+
							    "<tr ><td >Name</td><td colspan='3' ><b>"+patName+"</b></td></tr>"+
								"<tr ><td >Age/Sex</td><td><b>"+patAgeGender+"</b></td><td></td><td></td></tr>"+
								"<tr ><td >Mobile</td><td><b>"+patMobile+"</b></td><td>&nbsp;&nbsp;Emergency Contact</td><td><b>"+patEmgCntc+"</b></td></tr>"+
								"<tr ><td >Address</td><td colspan='3'><b>"+patAddress+"</b></td></tr></table></td></tr>");
			strbuilder.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");//label 1 ends for aiims patna
			strbuilder.append("<br><br>");*/
			strbuilder.append("<table style='margin-left:5%; margin-top:1%' cellspacing='0' cellpadding='0' width='90%'>");
			strbuilder.append("<tr><td ><div id='1diivBarCodeControl'><center><img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no image found in session\"></center></div></td></tr>");
			strbuilder.append("<tr style='font-size:13px;font-family:arial;'><td ><table cellspacing='1'  cellpadding='1' style='margin-left:1%'>"+
								"<tr ><td >CR No</td><td colspan='3'><b>"+patCrNo+"</b></td></tr>"+
							    "<tr ><td >Name</td><td colspan='3'><b>"+patName+"</b></td></tr>"+
								"<tr ><td >Age/Sex</td><td colspan='3'><b>"+patAgeGender+"</b></td></tr>"+
								"<tr ><td >Mobile</td><td><b>"+patMobile+"</b></td><td>&nbsp;Emg Contact</td><td><b>"+patEmgCntc+"</b></td></tr>"+
								"<tr ><td >Department/Unit</td><td colspan='3'><b>"+deptUnit+"</b></td></tr>"+
								"<tr ><td >Room No</td><td colspan='3'><b>"+roomNo+"</b></td></tr></table></td></tr>");
			strbuilder.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");//label 2 ends for aiims patna
			
			
	 	System.out.println("Barcode Slip Content: "+strbuilder.toString());
 	
 	
		return strbuilder.toString();
	}
	
	public static String labelMethod3(StringBuilder strbuilder, String patCrNo, String patName, String patAgeGender, String patMobile, String patEmgCntc, String patAddress , String barCodeGenSiString, String deptUnit, String roomNo, String docName)
	{
			strbuilder.append("<table style='margin-left:3%; margin-top:1%' cellspacing='0' cellpadding='0' width='90%'>");
			//strbuilder.append("<tr style='font-size:13px;font-family:arial;'><td colspan='2' >CR No&nbsp;&nbsp;<b>"+patCrNo+"</b></td><td colspan='2'><div id='1diivBarCodeControl'><center><img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no image found in session\"></center></div></td></tr>");
			strbuilder.append("<tr style='font-size:13px;font-family:arial;'><td ><table cellspacing='1'  cellpadding='1' style='margin-left:1%'>"+
								//"<tr ><td >CR No</td><td colspan='3'><b>"+patCrNo+"</b></td></tr>"+
								"<tr ><td colspan='2' nowrap='nowrap' >CR No&nbsp;</td><td colspan='2' rowspan='2'><div id='1diivBarCodeControl'><center><img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no image found in session\"></center></div></td></tr>"+
								"<tr><td colspan='2'><font size='4px;'><b>"+patCrNo+"</b></font></td></tr>"+
								"<tr ><td >Name</td><td colspan='3'><b>"+patName+"</b></td></tr>"+
								"<tr ><td >Age/Sex</td><td colspan='3'><b>"+patAgeGender+"</b></td></tr>"+
								"<tr ><td >Mobile</td><td><b>"+patMobile+"</b></td><td>&nbsp;Emg Contact</td><td><b>"+patEmgCntc+"</b></td></tr>"+
								"<tr ><td >Dept/Unit</td><td colspan='3'><b>"+deptUnit+"</b></td></tr>"+
								"<tr ><td >Room No</td><td colspan='3'><b>"+roomNo+"</b></td></tr>"+
								"<tr ><td >Doctor</td><td colspan='3'><b>"+docName.toUpperCase()+"</b></td></tr></table></td></tr>");
			strbuilder.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");//label 2 ends for aiims patna
			
			
	 	System.out.println("Barcode Slip Content: "+strbuilder.toString());
 	
 	
		return strbuilder.toString();
	}
	
	public static String labelMethod4(StringBuilder strbuilder, String patCrNo, String patName, String patAgeGender, String patMobile, String patEmgCntc, String patAddress , String barCodeGenSiString, String deptUnit, String roomNo, String docName, String fees, String epiDate, String patDistrictState)
	{
			strbuilder.append("<table style='margin-left:3%; margin-top:1%' cellspacing='0' cellpadding='0' width='90%'>");
			//strbuilder.append("<tr style='font-size:13px;font-family:arial;'><td colspan='2' >CR No&nbsp;&nbsp;<b>"+patCrNo+"</b></td><td colspan='2'><div id='1diivBarCodeControl'><center><img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no image found in session\"></center></div></td></tr>");
			strbuilder.append("<tr style='font-size:13px;font-family:arial;'><td ><table cellspacing='1'  cellpadding='1' style='margin-left:1%'>"+
								//"<tr ><td >CR No</td><td colspan='3'><b>"+patCrNo+"</b></td></tr>"+
								"<tr ><td colspan='2' nowrap='nowrap'><b>CR No</b>&nbsp;</td><td colspan='2' rowspan='2'><div id='1diivBarCodeControl'><center><img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no image found in session\"></center></div></td></tr>"+
								"<tr><td colspan='2'><font size='4px;'><b>"+patCrNo+"</b></font></td></tr>"+
								"<tr ><td >Name</td><td colspan='3'><b>"+patName+"</b></td></tr>"+
								"<tr ><td >Age/Sex</td><td><b>"+patAgeGender+"</b></td><td >Mobile</td><td><b>"+patMobile+"</b></td></tr>"+
								"<tr ><td>Visit Date</td><td><b>"+epiDate+"</b></td><td>Fee</td><td>&#8377;<b>"+fees+"&#47;&#45;</b></td></tr>"+
								"<tr ><td >Dept/Unit</td><td colspan='3'><b>"+deptUnit+"</b></td></tr>"+
								"<tr ><td >Room No</td><td colspan='3'><b>"+roomNo+"</b></td></tr>"+
								"<tr ><td >Doctor</td><td colspan='3'><b>"+docName.toUpperCase()+"</b></td></tr></table></td></tr>");
					strbuilder.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");//label 2 ends for aiims patna
			
			
	 	System.out.println("Barcode Slip Content: "+strbuilder.toString());
 	
 	
		return strbuilder.toString();
	}
	
	// changed as per new format by manisha gangwar dt 24.7.2018   // old format is in labelMethod4
	public static String labelMethod(StringBuilder strbuilder, String patCrNo, String patName, String patAgeGender, String patMobile, String patEmgCntc, String patAddress , String barCodeGenSiString, String deptUnit, String roomNo, String docName, String fees, String epiDate, String patDistrictState)
	{
			strbuilder.append("<table style='margin-left:3%; margin-top:1%' cellspacing='0' cellpadding='0' width='90%'>");
			//strbuilder.append("<tr style='font-size:13px;font-family:arial;'><td colspan='2' >CR No&nbsp;&nbsp;<b>"+patCrNo+"</b></td><td colspan='2'><div id='1diivBarCodeControl'><center><img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no image found in session\"></center></div></td></tr>");
			strbuilder.append("<tr style='font-size:11px;font-family:arial;'><td ><table cellspacing='1'  cellpadding='1' style='margin-left:1%'>"+
								//"<tr ><td >CR No</td><td colspan='3'><b>"+patCrNo+"</b></td></tr>"+
								"<tr><td colspan='3' nowrap='nowrap'><b>CR No</b>&nbsp;</td><td colspan='3' rowspan='2'><div id='1diivBarCodeControl'><center><img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no image found in session\"></center></div></td></tr>"+
								"<tr><td colspan='3'><font size='4px;'><b>"+patCrNo+"</b></font></td></tr>"+
								"<tr><td>Name</td><td colspan='5'><b>"+patName+"</b></td></tr>"+
								"<tr><td>Age/Sex</td><td><b>"+patAgeGender+"</b></td><td>&nbsp;&nbsp;&nbsp;&nbsp;Mobile</td><td><b>"+patMobile+"</b></td><td>Fee</td><td>&#8377;<b>"+fees+"&#47;&#45;</b></td></tr>"+
								"<tr><td>Dept/Unit</td><td colspan='5'><b>"+deptUnit+"</b></td></tr>"+
								"<tr><td>Visit Date</td><td colspan='2'><b>"+epiDate+"</b></td><td >Room No</td><td colspan='2'><b>"+roomNo+"</b></td></tr>"+
								"<tr><td>Doctor</td><td colspan='5'><b>"+docName.toUpperCase()+"</b></td></tr>"+
								"<tr><td>District/State</td><td colspan='5'><b>"+patDistrictState+"</b></td></tr></table></td></tr>");  //  added by manisha dt 24.7.18
								
			strbuilder.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");//label 2 ends for aiims patna
			
			
	 	System.out.println("Barcode Slip Content: "+strbuilder.toString());
 	
 	
		return strbuilder.toString();
	}
	//By Mukund for printing barcode throuhg js
	public static String labelMethodForBarcodethroughJs(StringBuilder strbuilder, String patCrNo, String patName, String patAgeGender, String patMobile, String patEmgCntc, String patAddress , String barCodeGenSiString, String deptUnit, String roomNo, String docName, String fees, String epiDate, String patDistrictState)
	{
			strbuilder.append("<table style='margin-left:3%; margin-top:1%' cellspacing='0' cellpadding='0' width='90%'>");
			strbuilder.append("<tr style='font-size:11px;font-family:arial;'><td ><table cellspacing='1'  cellpadding='1' style='margin-left:1%'>"+
								"<tr><td colspan='3' nowrap='nowrap'><b>CR No</b>&nbsp;</td><td colspan='3' rowspan='2'><div id='divBarcodeSlipControl'>"+patCrNo+"</div></td></tr>"+
								"<tr><td colspan='3'><font size='4px;'><b>"+patCrNo+"</b></font></td></tr>"+
								"<tr><td>Name</td><td colspan='5'><b>"+patName+"</b></td></tr>"+
								"<tr><td>Age/Sex</td><td><b>"+patAgeGender+"</b></td><td>&nbsp;&nbsp;&nbsp;&nbsp;Mobile</td><td><b>"+patMobile+"</b></td><td>Fee</td><td>&#8377;<b>"+fees+"&#47;&#45;</b></td></tr>"+
								"<tr><td>District/State</td><td colspan='5'><b>"+patDistrictState+"</b></td></tr>"+
								"<tr><td>Visit Date</td><td colspan='2'><b>"+epiDate+"</b></td><td >&nbsp;&nbsp;Block No</td><td colspan='2'><b>"+roomNo+"</b></td></tr>"+
								//"<tr><td>Doctor</td><td colspan='5'><b>"+docName.toUpperCase()+"</b></td></tr>"+
								"<tr><td>Dept/Unit</td><td colspan='5'><b>"+deptUnit+"</b></td></tr></table></td></tr>"
								);  
								
			strbuilder.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");
		 	System.out.println("Barcode Slip Content: "+strbuilder.toString());
		 	return strbuilder.toString();
	}
	//by Mukund for creating barcode slip while registering
	public static void saveAndCreateBarcodeSlipPrintingDetails(HttpServletRequest _req, EpisodeVO []objEpisodeVO, PatientVO objPatVO){
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
			EpisodeVO episodeVOArray = BarcodeGenerationDATA.saveBarcodePrintDtl(objBarcodeSlipVO, userVO);
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
			epiDate=_sdf2.format(_sdf1.parse((String)_req.getSession().getAttribute(Config.SYSDATE)));
			
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
