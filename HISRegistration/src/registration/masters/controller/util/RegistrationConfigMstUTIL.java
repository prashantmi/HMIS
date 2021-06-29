/**
 * 
 */
package registration.masters.controller.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import registration.config.RegistrationConfig;
import registration.masters.controller.actionsupport.RegistrationConfigSUP;
import registration.masters.controller.actionsupport.RenewalConfigSUP;
import registration.masters.controller.data.ExtInstituteMstDATA;
import registration.masters.controller.data.RegistrationConfigMstDATA;
import registration.masters.controller.data.RenewalConfigMstDATA;
import vo.registration.ExtInstituteVO;
import vo.registration.RegistrationConfigMstVO;
import vo.registration.RenewalConfigVO;
import auditlogClient.AuditlogClientConfig;
import auditlogClient.util.AuditlogDATA;

import com.opensymphony.xwork2.ActionContext;

import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

/**
 * @author s.singaravelan
 * Creation Date:- 22-Jan-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */
public class RegistrationConfigMstUTIL  {
	
	
	
	HttpSession httpSession = null;
	public static HttpServletRequest request = null;

	
	/*public static boolean getRegistrationConfigDtl(RegistrationConfigSUP RegistrationConfigSUP_p,
			String strMode_p, HttpServletRequest objRequest_p) {

		RegistrationConfigMstVO[] arrRegistrationConfigVOs=null;
		boolean bExistStatus=false;
		List<RegistrationConfigMstVO> lstOpdRegistrationConfigVO=new ArrayList();
		List<RegistrationConfigMstVO> lstEmgRegistrationConfigVO=new ArrayList();
		List<RegistrationConfigMstVO> lstSplRegistrationConfigVO=new ArrayList();
		//List<RenewalConfigVO> lstHospCommonRenewalConfigVO=new ArrayList();
		Map mp = new LinkedHashMap();

		try {
			UserVO userVo= ControllerUTIL.getUserVO(RegistrationConfigSUP_p.getObjRequest());
			RegistrationConfigMstDATA dataobj= new RegistrationConfigMstDATA();
			List<Entry> lstPrimaryCategory = new ArrayList();
			LinkedList<Entry> lstPatCategory = new LinkedList<Entry>();
			Entry entryOptionFirst= new Entry();
			entryOptionFirst.setValue("10");
			entryOptionFirst.setLabel("Default");
			try{
				lstPatCategory.add(entryOptionFirst);
				lstPrimaryCategory=dataobj.getGlobalPatCategory(userVo);
				lstPatCategory.addAll(lstPrimaryCategory);
			}finally{
				objRequest_p.getSession().setAttribute(RegistrationConfig.REG_MST_OPTION_PRIMARY_CATEGORY, lstPatCategory);
			}
			arrRegistrationConfigVOs= dataobj.getRegistrationConfigDtl( strMode_p, userVo);
			//RenewalConfigSUP_p.setFlagAddMod("2");
			//BeanUtils.copyProperties(RenewalConfigSUP_p,arrRenewalConfigVOs[0]);
			
			if(arrRegistrationConfigVOs!=null && arrRegistrationConfigVOs.length >0){
				for(int i=0; i<arrRegistrationConfigVOs.length; i++){
						if(arrRegistrationConfigVOs[i].getStrconfigGroup().equals("1")){
							lstOpdRegistrationConfigVO.add(arrRegistrationConfigVOs[i]);
						}
						if(arrRegistrationConfigVOs[i].getStrconfigGroup().equals("2")){
							lstEmgRegistrationConfigVO.add(arrRegistrationConfigVOs[i]);
						}
						if(arrRegistrationConfigVOs[i].getStrconfigGroup().equals("3")){
							lstSplRegistrationConfigVO.add(arrRegistrationConfigVOs[i]);
						}
					
					
					mp.put("RegistrationConfigVO"+(i+1), arrRegistrationConfigVOs[i]);
				}
				
				//initialize Audit Log
				String auditlogProcessId=RegistrationConfig.AUDIT_LOG_RENEWAL_CONFIG_MASTER;
				//AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(objRequest_p),objRequest_p);
			}
		} 
		catch(HisRecordNotFoundException e)
		{	
			
			//RenewalConfigSUP_p.setFlagAddMod("0");
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
	finally {

			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REG_MST_LIST_OF_OPD_REG_CONFIG_VO, lstOpdRegistrationConfigVO);
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REG_MST_LIST_OF_EMG_REG_CONFIG_VO, lstEmgRegistrationConfigVO);
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REG_MST_LIST_OF_SPL_REG_CONFIG_VO, lstSplRegistrationConfigVO);
			//WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REG_MST_LIST_OF_HOPWISE_COMMON_RENEWAL_CONFIG_VO, lstHospCommonRenewalConfigVO);
		}
		return bExistStatus;
	}*/
	

	
	
	
	//To populate the values in the ExtInstitute Master Screen Combos
	
	
	//To Save the ExtInstitute Details 
	public static boolean saveRegistrationConfigDtl(RegistrationConfigSUP objRegistrationConfigSUP,	String strMode_p, HttpServletRequest objRequest_p) 
	{

		String strmsgText = "";
		RegistrationConfigMstDATA objRegistrationConfigData_p = null;
		List<RegistrationConfigMstVO> objRegistrationConfigVO = new ArrayList();
		List<String> lstKeyVariables= new ArrayList<String>();
		Map mp = new LinkedHashMap();
		int counter = 0;
		boolean bExistStatus=false;

		try {
			UserVO userVo= ControllerUTIL.getUserVO(objRegistrationConfigSUP.getObjRequest());
			objRegistrationConfigSUP.setStrHospitalCode(userVo.getHospitalCode());
			objRegistrationConfigData_p = new RegistrationConfigMstDATA();
			
			if(objRegistrationConfigSUP.getStrtempRegOpd()!=null){
					RegistrationConfigMstVO vo = new RegistrationConfigMstVO();
					populateRegistrationConfigVoFromRegistrationConfigSup(vo, objRegistrationConfigSUP, "Opd");
					objRegistrationConfigVO.add(vo);
					mp.put("Save_RegistrationConfigMstVO"+(++counter)  , vo);
					lstKeyVariables.add(vo.getStrconfigGroup());
			}
			
			if(objRegistrationConfigSUP.getStrtempRegEmg()!=null){
				RegistrationConfigMstVO vo = new RegistrationConfigMstVO();
					populateRegistrationConfigVoFromRegistrationConfigSup(vo, objRegistrationConfigSUP, "Emg");
					objRegistrationConfigVO.add(vo);
					mp.put("Save_RenewalConfigVO"+(++counter)  , vo);
					lstKeyVariables.add(vo.getStrconfigGroup());
			}
			
			if(objRegistrationConfigSUP.getStrtempRegSpl()!=null){
					RegistrationConfigMstVO vo = new RegistrationConfigMstVO();
					populateRegistrationConfigVoFromRegistrationConfigSup(vo, objRegistrationConfigSUP, "Spl");
					objRegistrationConfigVO.add(vo);
					mp.put("Save_RegistrationConfigVO"+(++counter)  , vo);
					lstKeyVariables.add(vo.getStrconfigGroup());
			}
			
			if(objRegistrationConfigSUP.getStrtemp()<3)
				objRegistrationConfigData_p.saveRegistrationConfigDtl(objRegistrationConfigVO,strMode_p,userVo);
			else if(objRegistrationConfigSUP.getStrtemp()==3)
				objRegistrationConfigData_p.updateRegistrationConfigDtl(objRegistrationConfigVO,strMode_p,userVo);
			//objRegistrationConfigVO= new RegistrationConfigMstVO();			
			//objRegistrationConfigModel=registrationConfigModel;
			
			bExistStatus = true;
			if(bExistStatus)
			{
				//String[] arrKeyVariables= (String[])lstKeyVariables.toArray();
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]="1";
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(objRequest_p),objRequest_p,arrKeyVariables);
			}
			objRegistrationConfigSUP.setStrMsg("Record Saved Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			objRegistrationConfigSUP.setStrMsg("Error, Contact System Administrator");
			strmsgText = "RegistrationConfigAction.RegistrationConfigDATA.saveRegistrationConfigDtl(vo) --> "
					+ e.getMessage();
			
		} finally {

			objRegistrationConfigData_p = null;
		}
		return bExistStatus;
	}
	

	
	
public static void populateRegistrationConfigVoFromRegistrationConfigSup( RegistrationConfigMstVO objRegistrationConfigVO_p,RegistrationConfigSUP objRegistrationConfigSUP_p,String strMode_p){
		
	//UserVO uservo = ControllerUTIL.getUserVO(request);	
	
	//objRegistrationConfigVO_p.setStrHospitalCode(uservo.getHospitalCode());
		
	if("Opd".equals(strMode_p)){
			objRegistrationConfigVO_p.setStrtempReg(objRegistrationConfigSUP_p.getStrtempRegOpd());
			objRegistrationConfigVO_p.setStrconfigGroup("1");
			objRegistrationConfigVO_p.setStrmodechoice(objRegistrationConfigSUP_p.getStrmodechoiceOpd());
			objRegistrationConfigVO_p.setStrcrossconsult(objRegistrationConfigSUP_p.getStrcrossconsultOpd());
			objRegistrationConfigVO_p.setStrappointmentbs(objRegistrationConfigSUP_p.getStrappointmentbsOpd());
			objRegistrationConfigVO_p.setStrduplicacyChk(objRegistrationConfigSUP_p.getStrduplicacyChkOpd());
			objRegistrationConfigVO_p.setStradharintegration(objRegistrationConfigSUP_p.getStradharintegrationOpd());
			objRegistrationConfigVO_p.setStremgRenewal(objRegistrationConfigSUP_p.getStremgRenewalOpd());
			objRegistrationConfigVO_p.setStrNoOfHrs(objRegistrationConfigSUP_p.getStrNoOfHrsOpd());
			objRegistrationConfigVO_p.setStrseniorCitizenCatCode(objRegistrationConfigSUP_p.getStrseniorCitizenCatCodeOpd());
			objRegistrationConfigVO_p.setStrseniorCitizenAgeLimit(objRegistrationConfigSUP_p.getStrseniorCitizenAgeOpd());
			objRegistrationConfigVO_p.setStrmobileSearch(objRegistrationConfigSUP_p.getStrmobileServiceOpd());
			objRegistrationConfigVO_p.setStrimgUploadMode(objRegistrationConfigSUP_p.getStrimgUploadModeOpd());
			objRegistrationConfigVO_p.setStrHospitalCode(objRegistrationConfigSUP_p.getStrHospitalCode());
			
		}else if("Emg".equals(strMode_p)){
			objRegistrationConfigVO_p.setStrtempReg(objRegistrationConfigSUP_p.getStrtempRegEmg());
			objRegistrationConfigVO_p.setStrconfigGroup("3");
			objRegistrationConfigVO_p.setStrmodechoice(objRegistrationConfigSUP_p.getStrmodechoiceEmg());
			objRegistrationConfigVO_p.setStrcrossconsult(objRegistrationConfigSUP_p.getStrcrossconsultEmg());
			objRegistrationConfigVO_p.setStrappointmentbs(objRegistrationConfigSUP_p.getStrappointmentbsEmg());
			objRegistrationConfigVO_p.setStrduplicacyChk(objRegistrationConfigSUP_p.getStrduplicacyChkEmg());
			objRegistrationConfigVO_p.setStradharintegration(objRegistrationConfigSUP_p.getStradharintegrationEmg());
			objRegistrationConfigVO_p.setStremgRenewal(objRegistrationConfigSUP_p.getStremgRenewalEmg());
			objRegistrationConfigVO_p.setStrNoOfHrs(objRegistrationConfigSUP_p.getStrNoOfHrsEmg());
			objRegistrationConfigVO_p.setStrseniorCitizenCatCode(objRegistrationConfigSUP_p.getStrseniorCitizenCatCodeEmg());
			objRegistrationConfigVO_p.setStrseniorCitizenAgeLimit(objRegistrationConfigSUP_p.getStrseniorCitizenAgeEmg());
			objRegistrationConfigVO_p.setStrmobileSearch(objRegistrationConfigSUP_p.getStrmobileServiceEmg());
			objRegistrationConfigVO_p.setStrimgUploadMode(objRegistrationConfigSUP_p.getStrimgUploadModeEmg());
			objRegistrationConfigVO_p.setStrHospitalCode(objRegistrationConfigSUP_p.getStrHospitalCode());
			
		}else if("Spl".equals(strMode_p)){
			objRegistrationConfigVO_p.setStrtempReg(objRegistrationConfigSUP_p.getStrtempRegSpl());
			objRegistrationConfigVO_p.setStrconfigGroup("2");
			objRegistrationConfigVO_p.setStrmodechoice(objRegistrationConfigSUP_p.getStrmodechoiceSpl());
			objRegistrationConfigVO_p.setStrcrossconsult(objRegistrationConfigSUP_p.getStrcrossconsultSpl());
			objRegistrationConfigVO_p.setStrappointmentbs(objRegistrationConfigSUP_p.getStrappointmentbsSpl());
			objRegistrationConfigVO_p.setStrduplicacyChk(objRegistrationConfigSUP_p.getStrduplicacyChkSpl());
			objRegistrationConfigVO_p.setStradharintegration(objRegistrationConfigSUP_p.getStradharintegrationSpl());
			objRegistrationConfigVO_p.setStremgRenewal(objRegistrationConfigSUP_p.getStremgRenewalSpl());
			objRegistrationConfigVO_p.setStrNoOfHrs(objRegistrationConfigSUP_p.getStrNoOfHrsSpl());
			objRegistrationConfigVO_p.setStrseniorCitizenCatCode(objRegistrationConfigSUP_p.getStrseniorCitizenCatCodeSpl());
			objRegistrationConfigVO_p.setStrseniorCitizenAgeLimit(objRegistrationConfigSUP_p.getStrseniorCitizenAgeSpl());
			objRegistrationConfigVO_p.setStrmobileSearch(objRegistrationConfigSUP_p.getStrmobileServiceSpl());
			objRegistrationConfigVO_p.setStrimgUploadMode(objRegistrationConfigSUP_p.getStrimgUploadModeSpl());
			objRegistrationConfigVO_p.setStrHospitalCode(objRegistrationConfigSUP_p.getStrHospitalCode());
			
		}			
		
	}
public static RegistrationConfigMstVO fetchRecord(RegistrationConfigSUP objRegistrationConfigSUP_p, HttpServletRequest request) 
{
	RegistrationConfigMstDATA RegistrationConfigMstDATA_obj = null;
	String strMsgText = "";
	String temp;
	String strTemp1=null;
	String strTemp2=null;
	RegistrationConfigMstVO RegistrationConfigModel =new RegistrationConfigMstVO();
	String strChk = "";
	Map mp= new LinkedHashMap();
	UserVO uservo = ControllerUTIL.getUserVO(request);	
	try {
		
		RegistrationConfigMstDATA_obj = new RegistrationConfigMstDATA();
        //String strCombos[] = request.getParameterValues("combo");
		//RegistrationConfigModel.setStrconfigGroup(strTemp1);
		RegistrationConfigModel.setStrHospitalCode(uservo.getHospitalCode());
		RegistrationConfigMstVO[] RegistrationConfigVO_p=RegistrationConfigMstDATA_obj.fetchRecordRegistrationConfigMst(RegistrationConfigModel);
		objRegistrationConfigSUP_p.setStrtemp(RegistrationConfigVO_p.length);
		for(int i=0;i<RegistrationConfigVO_p.length;i++)
		{
			if(RegistrationConfigVO_p[i].getStrconfigGroup().equals("1"))
			{
				objRegistrationConfigSUP_p.setStrtempRegOpd(RegistrationConfigVO_p[i].getStrtempReg());
				objRegistrationConfigSUP_p.setStrconfigGroupOpd("1");
				objRegistrationConfigSUP_p.setStrmodechoiceOpd(RegistrationConfigVO_p[i].getStrmodechoice());
				objRegistrationConfigSUP_p.setStrcrossconsultOpd(RegistrationConfigVO_p[i].getStrcrossconsult());
				objRegistrationConfigSUP_p.setStrappointmentbsOpd(RegistrationConfigVO_p[i].getStrappointmentbs());
				objRegistrationConfigSUP_p.setStrduplicacyChkOpd(RegistrationConfigVO_p[i].getStrduplicacyChk());
				objRegistrationConfigSUP_p.setStradharintegrationOpd(RegistrationConfigVO_p[i].getStradharintegration());
				objRegistrationConfigSUP_p.setStremgRenewalOpd(RegistrationConfigVO_p[i].getStremgRenewal());
				objRegistrationConfigSUP_p.setStrNoOfHrsOpd(RegistrationConfigVO_p[i].getStrNoOfHrs());
				objRegistrationConfigSUP_p.setStrseniorCitizenCatCodeOpd(RegistrationConfigVO_p[i].getStrseniorCitizenCatCode());
				objRegistrationConfigSUP_p.setStrseniorCitizenAgeOpd(RegistrationConfigVO_p[i].getStrseniorCitizenAgeLimit());
				objRegistrationConfigSUP_p.setStrmobileServiceOpd(RegistrationConfigVO_p[i].getStrmobileSearch());
				objRegistrationConfigSUP_p.setStrimgUploadModeOpd(RegistrationConfigVO_p[i].getStrimgUploadMode());
				objRegistrationConfigSUP_p.setStrHospitalCode(RegistrationConfigVO_p[i].getStrHospitalCode());
			}
			else if(RegistrationConfigVO_p[i].getStrconfigGroup().equals("2"))
			{
				objRegistrationConfigSUP_p.setStrtempRegSpl(RegistrationConfigVO_p[i].getStrtempReg());
				objRegistrationConfigSUP_p.setStrconfigGroupSpl("1");
				objRegistrationConfigSUP_p.setStrmodechoiceSpl(RegistrationConfigVO_p[i].getStrmodechoice());
				objRegistrationConfigSUP_p.setStrcrossconsultSpl(RegistrationConfigVO_p[i].getStrcrossconsult());
				objRegistrationConfigSUP_p.setStrappointmentbsSpl(RegistrationConfigVO_p[i].getStrappointmentbs());
				objRegistrationConfigSUP_p.setStrduplicacyChkSpl(RegistrationConfigVO_p[i].getStrduplicacyChk());
				objRegistrationConfigSUP_p.setStradharintegrationSpl(RegistrationConfigVO_p[i].getStradharintegration());
				objRegistrationConfigSUP_p.setStremgRenewalSpl(RegistrationConfigVO_p[i].getStremgRenewal());
				objRegistrationConfigSUP_p.setStrNoOfHrsSpl(RegistrationConfigVO_p[i].getStrNoOfHrs());
				objRegistrationConfigSUP_p.setStrseniorCitizenCatCodeSpl(RegistrationConfigVO_p[i].getStrseniorCitizenCatCode());
				objRegistrationConfigSUP_p.setStrseniorCitizenAgeSpl(RegistrationConfigVO_p[i].getStrseniorCitizenAgeLimit());
				objRegistrationConfigSUP_p.setStrmobileServiceSpl(RegistrationConfigVO_p[i].getStrmobileSearch());
				objRegistrationConfigSUP_p.setStrimgUploadModeSpl(RegistrationConfigVO_p[i].getStrimgUploadMode());
				objRegistrationConfigSUP_p.setStrHospitalCode(RegistrationConfigVO_p[i].getStrHospitalCode());
			}
			else if(RegistrationConfigVO_p[i].getStrconfigGroup().equals("3"))
			{
				objRegistrationConfigSUP_p.setStrtempRegEmg(RegistrationConfigVO_p[i].getStrtempReg());
				objRegistrationConfigSUP_p.setStrconfigGroupEmg("1");
				objRegistrationConfigSUP_p.setStrmodechoiceEmg(RegistrationConfigVO_p[i].getStrmodechoice());
				objRegistrationConfigSUP_p.setStrcrossconsultEmg(RegistrationConfigVO_p[i].getStrcrossconsult());
				objRegistrationConfigSUP_p.setStrappointmentbsEmg(RegistrationConfigVO_p[i].getStrappointmentbs());
				objRegistrationConfigSUP_p.setStrduplicacyChkEmg(RegistrationConfigVO_p[i].getStrduplicacyChk());
				objRegistrationConfigSUP_p.setStradharintegrationEmg(RegistrationConfigVO_p[i].getStradharintegration());
				objRegistrationConfigSUP_p.setStremgRenewalEmg(RegistrationConfigVO_p[i].getStremgRenewal());
				objRegistrationConfigSUP_p.setStrNoOfHrsEmg(RegistrationConfigVO_p[i].getStrNoOfHrs());
				objRegistrationConfigSUP_p.setStrseniorCitizenCatCodeEmg(RegistrationConfigVO_p[i].getStrseniorCitizenCatCode());
				objRegistrationConfigSUP_p.setStrseniorCitizenAgeEmg(RegistrationConfigVO_p[i].getStrseniorCitizenAgeLimit());
				objRegistrationConfigSUP_p.setStrmobileServiceEmg(RegistrationConfigVO_p[i].getStrmobileSearch());
				objRegistrationConfigSUP_p.setStrimgUploadModeEmg(RegistrationConfigVO_p[i].getStrimgUploadMode());
				objRegistrationConfigSUP_p.setStrHospitalCode(RegistrationConfigVO_p[i].getStrHospitalCode());
			}
		}
		
		if (RegistrationConfigModel.getStrMsgType().equals("1")) {
			throw new Exception(RegistrationConfigModel.getStrMsgString());
		}
		
		HelperMethods.populate(RegistrationConfigModel, RegistrationConfigVO_p);
		
		//Audit Log Initiation
		String auditlogProcessId=RegistrationConfig.AUDIT_LOG_EXT_INSTITUTE_MASTER;
		mp.put("save_1", RegistrationConfigModel);
		//AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);

	} 
	catch (Exception e) 
	{
		strMsgText = "QualityTestMstDATA.modifyRecord(fb,request) --> "
				+ e.getMessage();
		HisException eObj = new HisException("cssd","QualityTestMstDATA->modifyRecord()", strMsgText);
		//objOccupationVO.setStrErrMsg("Application Error [ERROR ID : "
		//		+ eObj.getErrorID() + "],Contact System Administrator! ");

		eObj = null;
	}
	finally 
	{
		RegistrationConfigMstDATA_obj = null;
		strMsgText = null;
	}
	return RegistrationConfigModel;

}


}

