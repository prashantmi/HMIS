package registration.transactions.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import registration.config.RegistrationConfig;
import registration.config.Exceptions.HisDeadPatientException;
import registration.config.Exceptions.HisPatientStillUnknownException;
import registration.transactions.controller.actionsupport.PatientReferralSUP;
import registration.transactions.controller.data.PatientReferralDATA;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;

public class PatientReferralUTIL extends ControllerUTIL {

	
	public static void getEssentials(PatientReferralSUP objSUP_p,
			HttpServletRequest objRequest_p) {

		Status objStatus = new Status();
		Map essentialMap = new HashMap();

		try {

			essentialMap = PatientReferralDATA.getPatientReferralEssentials(
					objSUP_p.getPatCrNo(), getUserVO(objRequest_p));
			WebUTIL.setMapInSession(essentialMap, objRequest_p,"PatientReferralACTION");

			// objStatus.add(Status.NEW);

		} catch (HisRecordNotFoundException e) {
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
			essentialMap = e.getEssentialMap();

			WebUTIL.setMapInSession(essentialMap, objRequest_p,"PatientReferralACTION");
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
		} catch (Exception e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		} finally {
			WebUTIL.setStatus(objRequest_p, objStatus);
		}
	}

	public static void setPatientDtlByCrno(PatientReferralSUP objSUP_p,
			HttpServletRequest objRequest_p) {
		Status status = new Status();
		Map mp = new HashMap();
		EpisodeVO[] arrOpenEpisodeVO;
		try {

			PatientVO objPatientVO=new PatientVO();
			objPatientVO.setPatCrNo(objSUP_p.getPatCrNo());
			
			PatDetailUTIL.getPatientDtlByCrno(objSUP_p, objRequest_p);
			objPatientVO=(PatientVO)objRequest_p.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			//objPatientVO=PatientReferralDATA.getPatientDtl(objPatientVO,getUserVO(objRequest_p));
			if(objPatientVO.getPatIsMerged()!=null && objPatientVO.getPatIsMerged().equals("2"))
			{
				objSUP_p.setAfterGo("0");
				objSUP_p.setErrorMessage("CR No is Not Valid, Already Merged with CR No. " +objPatientVO.getPatMergedMainCrNO());
				throw new HisRecordNotFoundException("CR No is Not Valid, Already Merged with CR No. " +objPatientVO.getPatMergedMainCrNO() );
			}
			if(objPatientVO.getPatIsDead().equals(RegistrationConfig.PATIENT_IS_DEAD))
			{
				objSUP_p.setAfterGo("0");
				System.out.println("Patient Is Dead");
				throw new HisDeadPatientException("Not apllicable, Patient is Dead");
			}
			mp = PatientReferralDATA.getAllOpenEpisodesVisitedToday(
					objSUP_p.getPatCrNo(), getUserVO(objRequest_p));

			arrOpenEpisodeVO = (EpisodeVO[]) mp
					.get(RegistrationConfig.PATIENT_REFERRAL_EPISODEVO_ARR_REGISTRATION);

			WebUTIL.setMapInSession(mp, objRequest_p,"PatientReferralACTION");
			
			WebUTIL.setAttributeInSession(
					objRequest_p,
					RegistrationConfig.PATIENT_REFERRAL_EPISODEVO_ARR_REGISTRATION,
					arrOpenEpisodeVO);
			objSUP_p.setGoFlag("1");
			objSUP_p.setAfterGo("1");
		} catch (HisRecordNotFoundException e) {
			objSUP_p.setGoFlag("0");
			objSUP_p.setErrorMessage(e.getMessage());
			status.add(Status.NEW, e.getMessage(), "");
		} catch(HisDeadPatientException e){
			e.printStackTrace();
			objSUP_p.setErrorMessage("Not applicable, Patient is Dead");
			if(objSUP_p.getErrorMessage()==null || objSUP_p.getErrorMessage().equals("")){
			}else{
				objSUP_p.setAfterGo("0");
			}
		} catch (HisPatientStillUnknownException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.UNSUCESSFULL, e.getMessage(), "");
		} catch (HisDataAccessException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.ERROR_DA, e.getMessage(), "");
		} catch (HisApplicationExecutionException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.ERROR_AE, e.getMessage(), "");
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.ERROR, e.getMessage(), "");
			throw new HisException();
		} finally {
			WebUTIL.setStatus(objRequest_p, status);

		}
	}

	public static void getRefDept_AJAX(HttpServletRequest objRequest,
			HttpServletResponse objResponse) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		TransformerFactory trf = TransformerFactory.newInstance();
		Document responseDocument = null;
		String outputString = "";
		try {
			String strRefHospCode = (String) objRequest
					.getParameter("refHospCode");
			String strFlafRefHospOrInst = (String) objRequest
					.getParameter("flagRefHospOrInst");

			UserVO userVO = getUserVO(objRequest);

			responseDocument = dbf.newDocumentBuilder().newDocument();
			Element rootElement = responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);

			if (strFlafRefHospOrInst != null
					&& strFlafRefHospOrInst.equals("I"))
				strRefHospCode = userVO.getHospitalCode();

			List lstRefDept = PatientReferralDATA.getRefDept_AJAX(userVO,
					strRefHospCode);

			createOptionObject((List<Entry>) lstRefDept, responseDocument,"patRefGnctdHospitalDeptUnit");

		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			java.io.CharArrayWriter baos = new java.io.CharArrayWriter();
			try {
				trf.newTransformer().transform(new DOMSource(responseDocument),
						new StreamResult(baos));
			} catch (Exception e) {
				e.printStackTrace();
			}
			outputString = baos.toString();
			writeResponse(objResponse, outputString);
		}

	}

	private static void createOptionObject(List<Entry> list,
			Document responseDocument, String fieldName) {

		Element fieldElement = responseDocument.createElement(fieldName);
		for (Entry entry : list) {
			Element option = responseDocument.createElement("option");
			fieldElement.appendChild(option);

			option.setAttribute("value", entry.getValue());
			option.setAttribute("label", entry.getLabel());
		}
		responseDocument.getFirstChild().appendChild(fieldElement);
	}

	public static void writeResponse(HttpServletResponse resp, String output) {
		try {
			resp.reset();
			resp.setContentType("text/xml");
			resp.setHeader("Cache-Control", "no-cache");
			System.out.println(output);
			resp.getWriter().write(output);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void saveReferPatientDetail(PatientReferralSUP objSUP_p,
			HttpServletRequest _rq) {

		Status objStatus = new Status();
		Map essentialMap = new HashMap();
		HttpSession session = WebUTIL.getSession(_rq);
		EpisodeVO[] episodeVO = null;
		PatientVO patientVO = null;

		EpisodeRefDtlVO episodeRefVO = new EpisodeRefDtlVO();

		boolean flag = false;
		int i = 0;
		try {
			patientVO = (PatientVO) _rq.getSession().getAttribute(RegistrationConfig.PATIENT_VO);

			episodeVO = (EpisodeVO[]) _rq.getSession().getAttribute(RegistrationConfig.PATIENT_REFERRAL_EPISODEVO_ARR_REGISTRATION);

			for (; i < episodeVO.length; i++) {
				if (episodeVO[i].getEpisodeCode().equals(objSUP_p.getSelectedEpisode())) {
					flag = true;
					break;
				}
			}
			if (flag) {
				HelperMethods.populate(episodeRefVO, episodeVO[i]);

			}

			episodeRefVO.setIsRefferInOut(objSUP_p.getIsRefferInOut());

			if (objSUP_p.getPatRefGnctdHospitalDeptUnit().equals("0")) {
				objSUP_p.setPatRefGnctdHospitalDept(objSUP_p.getPatRefHospitalDeptOther());
			}
			

			episodeRefVO.setSystemIPAddress(_rq.getRemoteAddr());

			if (objSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_TRUE)) {
				episodeRefVO.setExternalHospitalCode(objSUP_p.getPatRefGnctdHospitalCode().split("\\#")[0]);
				/*if(objSUP_p.getPatRefGnctdHospitalCode().split("\\#")[1].equals("I"))
					episodeRefVO.setReferMode("3");
				else if(objSUP_p.getPatRefGnctdHospitalCode().split("\\#")[1].equals("H"))
					episodeRefVO.setReferMode("4");	*/
				episodeRefVO.setExternalHospitalDepartment(objSUP_p.getPatRefGnctdHospitalDeptUnit().split("\\#")[1]);
				episodeRefVO.setExternalHospitalDepartmentUnit(objSUP_p.getPatRefGnctdHospitalDeptUnit().split("\\#")[0]);

				episodeRefVO.setExternalHospitalName(objSUP_p.getPatRefHospitalName());
			} /*else if (objSUP_p.getIsAssociated().equals(
					RegistrationConfig.IS_ASSOCIATED_FALSE)) {
				episodeRefVO.setExternalHospitalCode(objSUP_p
						.getPatRefGnctdHospitalCode().split("\\#")[0]);
				episodeRefVO.setExternalHospitalDepartmentUnit(objSUP_p
						.getPatRefGnctdHospitalDept());
			}*/

			episodeRefVO.setFromDepartmentCode(episodeVO[i].getDepartmentCode());
			episodeRefVO.setFromDepartment(episodeVO[i].getDepartment());
			episodeRefVO.setFromDepartmentUnit(episodeVO[i].getDepartmentUnit());
			episodeRefVO.setFromDepartmentUnitCode(episodeVO[i].getDepartmentUnitCode());

			if (objSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL)) {
				if (objSUP_p.getChoice().equalsIgnoreCase("0")) {
					//episodeRefVO.setToDepartmentCode(objSUP_p.getDepartmentCode());
					episodeRefVO.setToDepartmentCode(objSUP_p.getDepartmentCode().split("#")[0]);
					episodeRefVO.setToDepartmentUnitCode(objSUP_p.getDepartmentCode().split("#")[1]);
				} else {
					episodeRefVO.setToDepartmentCode(objSUP_p.getDepartmentUnitCode().substring(0, 3));
					episodeRefVO.setToDepartmentUnitCode(objSUP_p.getDepartmentUnitCode());
				}
				episodeRefVO.setRemarks(objSUP_p.getRemarks());
			}
			if (objSUP_p.getIsRefferInOut().equals(
					RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL)) {
			episodeRefVO.setRemarks(objSUP_p.getExternalReferRemarks());
			}

			PatientReferralDATA.saveOpdReferPatient(episodeRefVO,
					getUserVO(_rq));
			

			// ******************Code commented for
			// Printing********************************

			// HelperMethods.populate(patientDtlVO, patientVO);
			// patientDtlVO.setPatName(patientVO.getPatFirstName());
			// patientDtlVO.setPatAge(patientVO.getPatAge());
			// GenericTemplateUtility.setVOInInfoBean(_rq, patientDtlVO);

			/*
			 * if(objSUP_p.getIsRefferInOut().equals(RegistrationConfig.
			 * IS_REFERRED_IN_OUT_REFER_EXTERNAL)){
			 * 
			 * //episodeRefVO.setToDepartmentCode(_fb.getDepartmentUnitCode().
			 * substring(0, 3));
			 * //episodeRefVO.setToDepartmentUnitCode(_fb.getDepartmentUnitCode
			 * ()); //str.append(
			 * "<div id='divReferalLetter'><his:GenericTemplateTag templateId='3310001'></his:GenericTemplateTag></div>"
			 * ); objSUP_p.setPrint("1"); //}
			 * 
			 * 
			 * }
			 */
			objStatus.add(Status.DONE, "", "Patient Refered");

		} catch (HisRecordNotFoundException e) {
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
			essentialMap = e.getEssentialMap();
			WebUTIL.setMapInSession(essentialMap, _rq,"PatientReferralACTION");
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} catch (Exception e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

}
