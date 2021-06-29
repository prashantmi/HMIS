/**
 * 
 */
package registration.dao;

import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;






import registration.config.RegistrationDaoConfig;
import vo.registration.ExtInstituteVO;
import vo.registration.RegistrationConfigMstVO;
import vo.registration.RenewalConfigVO;

/**
 * @author s.singaravelan
 *
 */
public class RegistrationConfigDAO
{
	
	
	//To Save the RegConfig in the Registration Config Mst
	public static String saveRegistrationConfigDetails(RegistrationConfigMstVO objModelReg_p,String strMode_p, HisDAO hisDAO_p,UserVO uservo, String string)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_REGCONFIG_DML;
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelReg_p);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_configgroup",(((RegistrationConfigMstVO) objModelReg_p).getStrconfigGroup()),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_duplicacychk",(((RegistrationConfigMstVO) objModelReg_p).getStrduplicacyChk()),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_tempreg",(((RegistrationConfigMstVO) objModelReg_p).getStrtempReg()),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_modechoice",( ((RegistrationConfigMstVO) objModelReg_p).getStrmodechoice()),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_crossconsult",(((RegistrationConfigMstVO) objModelReg_p).getStrcrossconsult()),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_appointbase",( ((RegistrationConfigMstVO) objModelReg_p).getStrappointmentbs()),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_emgrenewal",( ((RegistrationConfigMstVO) objModelReg_p).getStremgRenewal()),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_noofhours",(((RegistrationConfigMstVO) objModelReg_p).getStrNoOfHrs()),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_aadharintegration",(((RegistrationConfigMstVO) objModelReg_p).getStradharintegration()),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seniorcitizenage",( ((RegistrationConfigMstVO) objModelReg_p).getStrseniorCitizenAgeLimit()),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seniorcitizencat",( ((RegistrationConfigMstVO) objModelReg_p).getStrseniorCitizenCatCode()),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_mobilesearch",(((RegistrationConfigMstVO) objModelReg_p).getStrmobileSearch()),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_imageupload",(((RegistrationConfigMstVO) objModelReg_p).getStrimgUploadMode()),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,15);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",(((RegistrationConfigMstVO) objModelReg_p).getStrHospitalCode()),17);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,18); 
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
					
			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			System.out.println("Data Inserted Successussfully");
			
			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}			
		}

		catch (Exception e)
		{
			e.printStackTrace();
			((RegistrationConfigMstVO) objModelReg_p).setStrMsgString("VerificationDocDAO.getStoreName() --> "	+ e.getMessage());
			((RegistrationConfigMstVO) objModelReg_p).setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	
	
	public static RegistrationConfigMstVO[] getHospitalSpecificRegistrationDetails(UserVO userVo,HisDAO hisDAO_p, String strMode_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_REGCONFIG_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		WebRowSet webRowSet = null;
		ValueObject[] arrVO = {};
		RegistrationConfigMstVO[] arrRegistrationConfigVOs =null;
		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_modeval", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",userVo.getHospitalCode(),2);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_reg_config_gp","",3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 3); // 1 for
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,4); // 2 for
			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDbErr);
			}
			else
			{
				System.out.println("RegistrationConfigDAO :: Record Successfully retrieved");
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("RegistrationConfigDAO:getHospitalSpecificRegistrationDetails:: " + e.getMessage());
		}
		try
		{
			if (!webRowSet.next())
			{
    			System.out.println("no records");
    			throw new HisRecordNotFoundException();       			
    		}
    		else
    		{
    			webRowSet.beforeFirst();
    			arrVO = HelperMethods.populateVOfrmRS(RegistrationConfigMstVO.class, webRowSet);
    			arrRegistrationConfigVOs = new RegistrationConfigMstVO[arrVO.length];
    			int i =0;
    			for(ValueObject objValueObject : arrVO)
    			{
    				RegistrationConfigMstVO objRegistrationConfigVONew =  (RegistrationConfigMstVO)objValueObject;
    				arrRegistrationConfigVOs[i]=objRegistrationConfigVONew;
    				i++;
    			}
    		}
		}
		catch(HisRecordNotFoundException e)
		{	
			throw new HisRecordNotFoundException("HIS Record Not Found Exception"+e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("RequestdetailDAO:getStoreName():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		finally
		{
			if (hisDAO_p != null)
			{
				hisDAO_p.free();
				hisDAO_p = null;
			}
		}
		return arrRegistrationConfigVOs;

	}
	
	public static RegistrationConfigMstVO[] fetchDetails(RegistrationConfigMstVO objModelRegistrationConfigg,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_REGCONFIG_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;
		ValueObject[] arrVO = {};
		RegistrationConfigMstVO[] objModelRegistrationConfigVOs = null;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			HisUtil.replaceNullValueWithEmptyString(objModelRegistrationConfigg);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",objModelRegistrationConfigg.getStrHospitalCode(),2);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_reg_config_gp",objModelRegistrationConfig.getStrconfigGroup(),3);			
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelExtInstitute.getStrHospitalCode(),4);			
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,4); 
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}
		catch(HisRecordNotFoundException e)
		{	
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//objModelRegistrationConfig.setStrMsgString("RequestdetailDAO.getStoreName() --> "	+ e.getMessage());
			//objModelRegistrationConfig.setStrMsgType("1");
		}
		
		
			try
			{
				/*webRowSet.beforeFirst();
				webRowSet.next();
								
				objModelRegistrationConfig.setStrconfigGroup(webRowSet.getString(1));
				objModelRegistrationConfig.setStrduplicacyChk(webRowSet.getString(2));
				objModelRegistrationConfig.setStrtempReg(webRowSet.getString(3));
				objModelRegistrationConfig.setStrmodechoice(webRowSet.getString(4));
				objModelRegistrationConfig.setStrcrossconsult(webRowSet.getString(5));
				objModelRegistrationConfig.setStrappointmentbs(webRowSet.getString(6));
				objModelRegistrationConfig.setStremgRenewal(webRowSet.getString(7));
				objModelRegistrationConfig.setStrNoOfHrs(webRowSet.getString(8));
				objModelRegistrationConfig.setStradharintegration(webRowSet.getString(9));
				objModelRegistrationConfig.setStrseniorCitizenCatCode(webRowSet.getString(10));
				//objModelRegistrationConfig.setStrseniorCitizenAge(webRowSet.getString(12));
				objModelRegistrationConfig.setStrimgUploadMode(webRowSet.getString(11));
				//objModelRegistrationConfig.setStrEmail(webRowSet.getString(13));
				//objModelRegistrationConfig.setStrmobileService(webRowSet.getString(14));
				//objModelExtInstitute.setStrIsValid(webRowSet.getString(15));				
*/							
				if (!webRowSet.next())
				{
	    			System.out.println("no records");
	    			throw new HisRecordNotFoundException();       			
	    		}
	    		else
	    		{
	    			webRowSet.beforeFirst();
	    			arrVO = HelperMethods.populateVOfrmRS(RegistrationConfigMstVO.class, webRowSet);
	    			objModelRegistrationConfigVOs = new RegistrationConfigMstVO[arrVO.length];
	    			int i =0;
	    			for(ValueObject objValueObject : arrVO)
	    			{
	    				RegistrationConfigMstVO objRegistrationConfigVONew =  (RegistrationConfigMstVO)objValueObject;
	    				objModelRegistrationConfigVOs[i]=objRegistrationConfigVONew;
	    				i++;
	    			}
	    		}
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("RequestdetailDAO:getStoreName():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
		return objModelRegistrationConfigVOs;
		
	}
	
	//To Update RegistrationConfig Details
	public static void updateRegistrationConfigDetails(RegistrationConfigMstVO objModelRegistrationConfig,String strMode_p,HisDAO hisDAO_p,UserVO uservo ) {

		
		final String strProcName =  RegistrationDaoConfig.PROCEDURE_REGCONFIG_DML;
		final int nProcedureIndex;
		final String strDbErr;
				
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelRegistrationConfig);
						
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_configgroup",objModelRegistrationConfig.getStrconfigGroup(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_tempreg",objModelRegistrationConfig.getStrtempReg(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_duplicacychk",objModelRegistrationConfig.getStrduplicacyChk(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_modechoice",objModelRegistrationConfig.getStrmodechoice(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_crossconsult",objModelRegistrationConfig.getStrcrossconsult(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_appointbase",objModelRegistrationConfig.getStrappointmentbs(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_emgrenewal",objModelRegistrationConfig.getStremgRenewal(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_noofhours",objModelRegistrationConfig.getStrNoOfHrs(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_aadharintegration",objModelRegistrationConfig.getStradharintegration(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seniorcitizenage",objModelRegistrationConfig.getStrseniorCitizenAgeLimit(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seniorcitizencat",objModelRegistrationConfig.getStrseniorCitizenCatCode(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_mobilesearch",objModelRegistrationConfig.getStrmobileSearch(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_imageupload",objModelRegistrationConfig.getStrimgUploadMode(),14);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstemail",objModelRegistrationConfig.getStrmobileService(),14);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_extinstremarks",objModelRegistrationConfig.getStrRemarks(),15);

			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",objModelExtInstitute.getStrIsValid(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,15);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),16);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),18);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelRegistrationConfig.getStrHospitalCode(),17);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,18); 
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");			

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}
		}		
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException(e.getMessage());
			
		}
		
	}
	
	
}
