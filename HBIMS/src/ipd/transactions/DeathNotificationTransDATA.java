package ipd.transactions;

import java.util.ResourceBundle;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import ipd.IpdTransConfig;

public class DeathNotificationTransDATA {
	
	
	/**
	 * This function is used to set initial parameters required to display on
	 * main page
	 * 
	 * @param formBean
	 */
	public static boolean patAdmStatus(DeathNotificationTransFB formBean) {

		DeathNotificationTransVO voObj = new DeathNotificationTransVO();
		DeathNotificationTransBO bo = new DeathNotificationTransBO();
		boolean retVal=false;
		String strmsgText = "";
		try {
			voObj.setStrHospCode(formBean.getStrHospCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			voObj.setStrCrNo(formBean.getStrCrNo());
				
			bo.setPatAdmStatus(voObj);
			if(voObj.getStrInvalidCrNo().equals("1"))
			{
				formBean.setStrErrMsg("Invalid CR No/Data not found");
				retVal=false;
			}else{
			
			formBean.setStrPatAdmCode(voObj.getStrPatAdmCode());
			formBean.setStrDead(voObj.getStrDead());
			//voObj.setStrPatAdmCode("12");//delete
			//voObj.setStrIsDead("1");//delete
		
				if(voObj.getStrPatAdmCode().equals("12"))
				{
					if(voObj.getStrDead().equals("1"))
					{
						formBean.setStrErrMsg("Patient is Dead");
						retVal=false;
					}else{
						retVal=true;
						 }
				
				}else{
					formBean.setStrErrMsg("Patient Not Admited");
					retVal=false;
					}
			}
		} catch (Exception e) {
			strmsgText = e.getMessage();
			
			HisException eObj = new HisException("IPD", "DeathNotificationTransData->patAdmStatus()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			formBean.setStrCrNo("");
			eObj = null;
		} finally {
			if (voObj != null) {
				voObj = null;
			}
			if (bo != null) {
				bo = null;
			}
		}
		return retVal;
	}
	
	
	/**
	 * This function is used to set initial parameters for main screen
	 * @param formBean
	 * @param request
	 */
	public static void initDeathNotification(DeathNotificationTransFB formBean)
	{
		HisUtil util=null;
		String temp;
		String  strmsgText = "";
		String strFGnctdFlag="";
		try
		{
			DeathNotificationTransVO vo=new DeathNotificationTransVO();
			DeathNotificationTransBO bo=new DeathNotificationTransBO();
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospCode(formBean.getStrHospCode());
			bo.setRequiredValues(vo);
			strFGnctdFlag=ResourceBundle.getBundle("ipd.hisIpdProperties").getString("IS_GNCTD");
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			else
			{
				formBean.setStrFGnctdFlag(strFGnctdFlag);
				formBean.setStrAdmno(vo.getStrAdmno());
				formBean.setStrAdmDate(vo.getStrAdmDate());
				formBean.setStrEpisodeCode(vo.getStrEpisodeCode());
				formBean.setStrVistNo(vo.getStrVistNo());
				formBean.setStrDeptCode(vo.getStrDeptCode());
				formBean.setStrDeptUnitCode(vo.getStrDeptUnitCode());
				formBean.setStrWardCode(vo.getStrWardCode());
				formBean.setStrRoomeCode(vo.getStrRoomeCode());
				formBean.setStrBedCode(vo.getStrBedCode());
				formBean.setStrIsNewBorn(vo.getStrIsNewBorn());
				formBean.setStrMlcNo(vo.getStrMlcNo());
				util = new HisUtil("Death Notification Trans",
				"DeathNotificationTransData");
				temp = util.getOptionValue(vo.getConsultantWS(), "0",
						"0^Select Value", false);
				formBean.setStrConsultantValue(temp);
				temp = util.getOptionValue(vo.getDeathMannerWS(), "0",
					"0^Select Value", false);
				formBean.setStrDeathMannerValue(temp);
				temp = util.getOptionValue(vo.getDeathCauseWS(), "0",
					"0^Select Value", false);
				formBean.setStrDeathCauseValue(temp);
			}
		}
		catch(Exception e)
		{
				strmsgText = e.getMessage();
				HisException eObj = new HisException("IPD", "DeathNotificationTransData->initDeathNotification()", strmsgText);
				formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
				eObj = null;
			 
		}
	}
	
	/**
	 * This function is used to invoke method of DeathNotificationTransFB to insert data
	 * @param formBean
	 */
	public static  void insert(DeathNotificationTransFB formBean)
	{
		DeathNotificationTransVO vo = null;
		DeathNotificationTransBO bo = null;
		String  strmsgText = "";
		try
		{
			 vo=new DeathNotificationTransVO();
			 bo=new DeathNotificationTransBO();
						
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrAdmno(formBean.getStrAdmno());
			vo.setStrAdmDate(formBean.getStrAdmDate());
			vo.setStrEpisodeCode(formBean.getStrEpisodeCode());
			vo.setStrDeptCode(formBean.getStrDeptCode());
			vo.setStrDeptUnitCode(formBean.getStrDeptUnitCode());
			vo.setStrWardCode(formBean.getStrWardCode());
			vo.setStrVistNo(formBean.getStrVistNo());
			vo.setStrRoomeCode(formBean.getStrRoomeCode());
			vo.setStrBedCode(formBean.getStrBedCode());
			vo.setStrIsNewBorn(formBean.getStrIsNewBorn());
			vo.setStrMlcNo(formBean.getStrMlcNo());
			vo.setStrDeathDateTime(formBean.getStrDeathDateTime());
			vo.setStrOnsetDeathDateTime(formBean.getStrOnsetDeathDateTime());
			vo.setStrIsPregnant(formBean.getStrIsPregnant());
			vo.setStrIsDelivery(formBean.getStrIsDelivery());
			vo.setStrIsSiftToMortuary(formBean.getStrIsSiftToMortuary());
			vo.setStrDeathManner(formBean.getStrDeathManner());
			vo.setStrCauseDeath(formBean.getStrCauseDeath());
			vo.setStrAntecedentCauseDeath(formBean.getStrAntecedentCauseDeath());
			vo.setStrDescpCauseDeath(formBean.getStrDescpCauseDeath());
			vo.setStrInjuryDetail(formBean.getStrInjuryDetail());
			vo.setStrConsultant(formBean.getStrConsultant());
			vo.setStrDeathCode(IpdTransConfig.getDiedCode());
			vo.setStrBedStatusVacantCode(IpdTransConfig.getBedStatusVacantCode());
			vo.setStrVerifyDateTime(formBean.getStrVerifyDateTime());
			if(vo.getStrIsSiftToMortuary().equals("1")){
			vo.setStrShiftDateTime(formBean.getStrShiftDateTime());
			}else{
			vo.setStrHandOverTo(formBean.getStrHandOverTo());
			vo.setStrHandOverDateTime(formBean.getStrHandOverDateTime());
			}
	
		
			
			
			bo.insert(vo);
			
			if (vo.getStrMsgType().equals("0")) {
				formBean.setStrNormalMsg("Death Successfully Notified");
			}else{
				throw new Exception(formBean.getStrMsgString());
			}
		}
		catch(Exception e)
		{			     
		  	strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD", "DeathNotificationTransData->Insert()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		}
	}

	public static void isPregnant(DeathNotificationTransFB formBean)
	{
		
		String  strmsgText = "";
		DeathNotificationTransVO vo = null;
		DeathNotificationTransBO bo = null;
	//	HisUtil util=null;
		try
		{
			 vo=new DeathNotificationTransVO();
			 vo.setStrHospCode(formBean.getStrHospCode());
			 vo.setStrCrNo(formBean.getStrCrNo());
			 bo=new DeathNotificationTransBO();
	//		util = new HisUtil("Death Notification Trans","DeathNotificationTransData");
			bo.isPregnant(vo);
			formBean.setFFemale(vo.isFFemale());
			
			if(formBean.getStrMsgType().equals("1")) {		//error
				throw new Exception(formBean.getStrMsgString());
			}
			
		}catch(Exception e){
			strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD", "DeathNotificationTransData->isPregnant()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
			
		}
	}
}
