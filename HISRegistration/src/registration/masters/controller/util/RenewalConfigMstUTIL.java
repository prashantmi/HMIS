package registration.masters.controller.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import org.apache.commons.beanutils.BeanUtils;

import auditlogClient.AuditlogClientConfig;
import auditlogClient.util.AuditlogDATA;
import registration.bo.RegMasterBO;
import registration.config.RegistrationConfig;
import registration.masters.controller.actionsupport.RenewalConfigSUP;
import registration.masters.controller.data.RenewalConfigMstDATA;
import vo.registration.RenewalConfigVO;

//import hisglobal.vo.UserVO;

/**
 * @author Deepika Gaba Creation Date:- 12-Jan-2013 Modifying Date:-
 *         Used For:- Team Lead By:- Module:- Reg(HIS Project)
 * 
 */
public class RenewalConfigMstUTIL {




	public static boolean getRenewalConfigDtl(RenewalConfigSUP RenewalConfigSUP_p,
			String strMode_p, HttpServletRequest objRequest_p) {

		RenewalConfigVO[] arrRenewalConfigVOs=null;
		boolean bExistStatus=false;
		List<RenewalConfigVO> lstGenRenewalConfigVO=new ArrayList();
		List<RenewalConfigVO> lstEmgRenewalConfigVO=new ArrayList();
		List<RenewalConfigVO> lstSplRenewalConfigVO=new ArrayList();
		List<RenewalConfigVO> lstHospCommonRenewalConfigVO=new ArrayList();
		Map mp = new LinkedHashMap();

		try {
			UserVO userVo= ControllerUTIL.getUserVO(RenewalConfigSUP_p.getObjRequest());
			RenewalConfigMstDATA dataobj= new RenewalConfigMstDATA();
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
			arrRenewalConfigVOs= dataobj.getRenewalConfigDtl( strMode_p, userVo);
			//RenewalConfigSUP_p.setFlagAddMod("2");
			//BeanUtils.copyProperties(RenewalConfigSUP_p,arrRenewalConfigVOs[0]);
			
			if(arrRenewalConfigVOs!=null && arrRenewalConfigVOs.length >0){
				for(int i=0; i<arrRenewalConfigVOs.length; i++){
					if("1".equals(arrRenewalConfigVOs[i].getStrRenewalType())){
						if("1".equals(arrRenewalConfigVOs[i].getStrRenewalGroup())){
							lstHospCommonRenewalConfigVO.add(arrRenewalConfigVOs[i]);
						}
						RenewalConfigSUP_p.setStrRenewalTypeCommonToAll("1");
					}else{
						if(arrRenewalConfigVOs[i].getStrRenewalGroup().equals("1")){
							lstGenRenewalConfigVO.add(arrRenewalConfigVOs[i]);
						}
						// Start : Surabhi
						// Reason : changed the renewal group for emg and spl on 15 June 2016
						if(arrRenewalConfigVOs[i].getStrRenewalGroup().equals("3")){
							lstEmgRenewalConfigVO.add(arrRenewalConfigVOs[i]);
						}
						if(arrRenewalConfigVOs[i].getStrRenewalGroup().equals("2")){
							lstSplRenewalConfigVO.add(arrRenewalConfigVOs[i]);
						}
						//end
					}
					
					mp.put("RenewalConfigVO"+(i+1), arrRenewalConfigVOs[i]);
				}
				
				//initialize Audit Log
				/*String auditlogProcessId=RegistrationConfig.AUDIT_LOG_RENEWAL_CONFIG_MASTER;
				AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(objRequest_p),objRequest_p);*/
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

			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REG_MST_LIST_OF_GEN_RENEWAL_CONFIG_VO, lstGenRenewalConfigVO);
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REG_MST_LIST_OF_EMG_RENEWAL_CONFIG_VO, lstEmgRenewalConfigVO);
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REG_MST_LIST_OF_SPL_RENEWAL_CONFIG_VO, lstSplRenewalConfigVO);
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REG_MST_LIST_OF_HOPWISE_COMMON_RENEWAL_CONFIG_VO, lstHospCommonRenewalConfigVO);
		}
		return bExistStatus;
	}
	

	public static boolean saveRenewalConfigDtl(RenewalConfigSUP RenewalConfigSUP_p,
			String strMode_p, HttpServletRequest objRequest_p) {

		String strmsgText = "";
		RenewalConfigMstDATA objRenewalConfigMstDATA = null;
		List<RenewalConfigVO> lstRenewalConfigVO = new ArrayList();
		List<String> lstKeyVariables= new ArrayList<String>();
		Map mp = new LinkedHashMap();
		boolean bExistStatus=false;
		int counter = 0;

		
		try {
			UserVO userVo= ControllerUTIL.getUserVO(RenewalConfigSUP_p.getObjRequest());
			objRenewalConfigMstDATA = new RenewalConfigMstDATA();
			
			if("1".equals(RenewalConfigSUP_p.getStrRenewalTypeCommonToAll())){
				if(RenewalConfigSUP_p.getStrRenewalPatCat()!=null && RenewalConfigSUP_p.getStrRenewalPatCat().length>0){
					for(int i=0; i<RenewalConfigSUP_p.getStrRenewalPatCat().length; i++){
						for(int j=1; j<=3; j++){
							RenewalConfigVO vo = new RenewalConfigVO();
							populateRenewalConfigVoFromRenewalConfigSup(vo, RenewalConfigSUP_p, "", i);
							vo.setStrRenewalGroup(j+"");
							lstRenewalConfigVO.add(vo);
							mp.put("Save_RenewalConfigVO"+(++counter)  , vo);
							lstKeyVariables.add(vo.getStrRenewalGroup());
						}
					}
				}
			}else{
				if(RenewalConfigSUP_p.getStrRenewalPatCatGen()!=null && RenewalConfigSUP_p.getStrRenewalPatCatGen().length>0){
					for(int i=0; i<RenewalConfigSUP_p.getStrRenewalPatCatGen().length; i++){
						RenewalConfigVO vo = new RenewalConfigVO();
						populateRenewalConfigVoFromRenewalConfigSup(vo, RenewalConfigSUP_p, "Gen", i);
						lstRenewalConfigVO.add(vo);
						mp.put("Save_RenewalConfigVO"+(++counter)  , vo);
						lstKeyVariables.add(vo.getStrRenewalGroup());
					}
				}
				
				if(RenewalConfigSUP_p.getStrRenewalPatCatEmg()!=null && RenewalConfigSUP_p.getStrRenewalPatCatEmg().length>0){
					for(int i=0; i<RenewalConfigSUP_p.getStrRenewalPatCatEmg().length; i++){
						RenewalConfigVO vo = new RenewalConfigVO();
						populateRenewalConfigVoFromRenewalConfigSup(vo, RenewalConfigSUP_p, "Emg", i);
						lstRenewalConfigVO.add(vo);
						mp.put("Save_RenewalConfigVO"+(++counter)  , vo);
						lstKeyVariables.add(vo.getStrRenewalGroup());
					}
				}
				
				if(RenewalConfigSUP_p.getStrRenewalPatCatSpl()!=null && RenewalConfigSUP_p.getStrRenewalPatCatSpl().length>0){
					for(int i=0; i<RenewalConfigSUP_p.getStrRenewalPatCatSpl().length; i++){
						RenewalConfigVO vo = new RenewalConfigVO();
						populateRenewalConfigVoFromRenewalConfigSup(vo, RenewalConfigSUP_p, "Spl", i);
						lstRenewalConfigVO.add(vo);
						mp.put("Save_RenewalConfigVO"+(++counter)  , vo);
						lstKeyVariables.add(vo.getStrRenewalGroup());
					}
				}
			}
			
			objRenewalConfigMstDATA.saveRenewalConfigDtl(lstRenewalConfigVO,strMode_p,userVo);
			
			


				//Audit Log on update
						
			bExistStatus = true;
			/*if(bExistStatus)
			{
				//String[] arrKeyVariables= (String[])lstKeyVariables.toArray();
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]="1";
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(objRequest_p),objRequest_p,arrKeyVariables);
			}*/
			RenewalConfigSUP_p.setStrMsg("Record Saved Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			RenewalConfigSUP_p.setStrMsg("Error, Contact System Administrator");
			strmsgText = "RenewalConfigAction.RenewalConfigDATA.saveRenewalConfigDtl(vo) --> "
					+ e.getMessage();
			
		} finally {

			objRenewalConfigMstDATA = null;
		}
		return bExistStatus;
	}
	
	public static void populateRenewalConfigVoFromRenewalConfigSup( RenewalConfigVO objRenewalConfigVO_p,RenewalConfigSUP objRenewalConfigSUP_p,String strMode_p, int i){
		
		if("Gen".equals(strMode_p)){
			objRenewalConfigVO_p.setStrRenewalPatCat(objRenewalConfigSUP_p.getStrRenewalPatCatGen()[i]);
			objRenewalConfigVO_p.setStrRenewalGroup("1");
			objRenewalConfigVO_p.setStrRenewalType(objRenewalConfigSUP_p.getStrRenewalTypeGen()[i]);
			objRenewalConfigVO_p.setStrRenewalMode(objRenewalConfigSUP_p.getStrRenewalModeGen()[i]);
			objRenewalConfigVO_p.setStrIsMultipleMonth(objRenewalConfigSUP_p.getStrIsMultipleMonthGen()[i]);
			objRenewalConfigVO_p.setStrNoOfDays(objRenewalConfigSUP_p.getStrNoOfDaysGen()[i]);
			
			if(objRenewalConfigVO_p.getStrRenewalMode().equals("2"))///Month wise
			{
				objRenewalConfigVO_p.setStrMonth1(objRenewalConfigSUP_p.getStrDay1Gen()[i]+"-"+objRenewalConfigSUP_p.getStrMonth1Gen()[i]);
				if(objRenewalConfigVO_p.getStrIsMultipleMonth().equals("1")||(objRenewalConfigVO_p.getStrIsMultipleMonth().equals("2")))
					objRenewalConfigVO_p.setStrMonth2(objRenewalConfigSUP_p.getStrDay2Gen()[i]+"-"+objRenewalConfigSUP_p.getStrMonth2Gen()[i]);
				if(objRenewalConfigVO_p.getStrIsMultipleMonth().equals("2"))
					objRenewalConfigVO_p.setStrMonth3(objRenewalConfigSUP_p.getStrDay3Gen()[i]+"-"+objRenewalConfigSUP_p.getStrMonth3Gen()[i]);
				objRenewalConfigVO_p.setStrNoOfDays("");
			}
			
		}else if("Emg".equals(strMode_p)){
			objRenewalConfigVO_p.setStrRenewalPatCat(objRenewalConfigSUP_p.getStrRenewalPatCatEmg()[i]);
			objRenewalConfigVO_p.setStrRenewalGroup("3");
			objRenewalConfigVO_p.setStrRenewalType(objRenewalConfigSUP_p.getStrRenewalTypeEmg()[i]);
			objRenewalConfigVO_p.setStrRenewalMode(objRenewalConfigSUP_p.getStrRenewalModeEmg()[i]);
			objRenewalConfigVO_p.setStrIsMultipleMonth(objRenewalConfigSUP_p.getStrIsMultipleMonthEmg()[i]);
			objRenewalConfigVO_p.setStrNoOfDays(objRenewalConfigSUP_p.getStrNoOfDaysEmg()[i]);
			
			if(objRenewalConfigVO_p.getStrRenewalMode().equals("2"))///Month wise
			{
				objRenewalConfigVO_p.setStrMonth1(objRenewalConfigSUP_p.getStrDay1Emg()[i]+"-"+objRenewalConfigSUP_p.getStrMonth1Emg()[i]);
				if(objRenewalConfigVO_p.getStrIsMultipleMonth().equals("1")||(objRenewalConfigVO_p.getStrIsMultipleMonth().equals("2")))
					objRenewalConfigVO_p.setStrMonth2(objRenewalConfigSUP_p.getStrDay2Emg()[i]+"-"+objRenewalConfigSUP_p.getStrMonth2Emg()[i]);
				if(objRenewalConfigVO_p.getStrIsMultipleMonth().equals("2"))
					objRenewalConfigVO_p.setStrMonth3(objRenewalConfigSUP_p.getStrDay3Emg()[i]+"-"+objRenewalConfigSUP_p.getStrMonth3Emg()[i]);
				objRenewalConfigVO_p.setStrNoOfDays("");
			}
			
		}else if("Spl".equals(strMode_p)){
			objRenewalConfigVO_p.setStrRenewalPatCat(objRenewalConfigSUP_p.getStrRenewalPatCatSpl()[i]);
			objRenewalConfigVO_p.setStrRenewalGroup("2");
			objRenewalConfigVO_p.setStrRenewalType(objRenewalConfigSUP_p.getStrRenewalTypeSpl()[i]);
			objRenewalConfigVO_p.setStrRenewalMode(objRenewalConfigSUP_p.getStrRenewalModeSpl()[i]);
			objRenewalConfigVO_p.setStrIsMultipleMonth(objRenewalConfigSUP_p.getStrIsMultipleMonthSpl()[i]);
			objRenewalConfigVO_p.setStrNoOfDays(objRenewalConfigSUP_p.getStrNoOfDaysSpl()[i]);
			
			if(objRenewalConfigVO_p.getStrRenewalMode().equals("2"))///Month wise
			{
				objRenewalConfigVO_p.setStrMonth1(objRenewalConfigSUP_p.getStrDay1Spl()[i]+"-"+objRenewalConfigSUP_p.getStrMonth1Spl()[i]);
				if(objRenewalConfigVO_p.getStrIsMultipleMonth().equals("1")||(objRenewalConfigVO_p.getStrIsMultipleMonth().equals("2")))
					objRenewalConfigVO_p.setStrMonth2(objRenewalConfigSUP_p.getStrDay2Spl()[i]+"-"+objRenewalConfigSUP_p.getStrMonth2Spl()[i]);
				if(objRenewalConfigVO_p.getStrIsMultipleMonth().equals("2"))
					objRenewalConfigVO_p.setStrMonth3(objRenewalConfigSUP_p.getStrDay3Spl()[i]+"-"+objRenewalConfigSUP_p.getStrMonth3Spl()[i]);
				objRenewalConfigVO_p.setStrNoOfDays("");
			}
			
		}else{
			objRenewalConfigVO_p.setStrRenewalPatCat(objRenewalConfigSUP_p.getStrRenewalPatCat()[i]);
			//objRenewalConfigVO_p.setStrRenewalGroup(objRenewalConfigSUP_p.getStrRenewalGroup()[i]);
			objRenewalConfigVO_p.setStrRenewalType("1");
			objRenewalConfigVO_p.setStrRenewalMode(objRenewalConfigSUP_p.getStrRenewalMode()[i]);
			objRenewalConfigVO_p.setStrIsMultipleMonth(objRenewalConfigSUP_p.getStrIsMultipleMonth()[i]);
			objRenewalConfigVO_p.setStrNoOfDays(objRenewalConfigSUP_p.getStrNoOfDays()[i]);
			
			if(objRenewalConfigVO_p.getStrRenewalMode().equals("2"))///Month wise
			{
				objRenewalConfigVO_p.setStrMonth1(objRenewalConfigSUP_p.getStrDay1()[i]+"-"+objRenewalConfigSUP_p.getStrMonth1()[i]);
				if(objRenewalConfigVO_p.getStrIsMultipleMonth().equals("1")||(objRenewalConfigVO_p.getStrIsMultipleMonth().equals("2")))
					objRenewalConfigVO_p.setStrMonth2(objRenewalConfigSUP_p.getStrDay2()[i]+"-"+objRenewalConfigSUP_p.getStrMonth2()[i]);
				if(objRenewalConfigVO_p.getStrIsMultipleMonth().equals("2"))
					objRenewalConfigVO_p.setStrMonth3(objRenewalConfigSUP_p.getStrDay3()[i]+"-"+objRenewalConfigSUP_p.getStrMonth3()[i]);
				objRenewalConfigVO_p.setStrNoOfDays("");
			}
		}
		if(!objRenewalConfigVO_p.getStrRenewalMode().equals("2")){
			objRenewalConfigVO_p.setStrIsMultipleMonth("");
			objRenewalConfigVO_p.setStrMonth1("");
			objRenewalConfigVO_p.setStrMonth2("");
			objRenewalConfigVO_p.setStrMonth3("");
		}
			
		
	}
}
