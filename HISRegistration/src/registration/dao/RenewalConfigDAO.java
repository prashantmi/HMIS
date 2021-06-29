package registration.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;

import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import registration.config.RegistrationDaoConfig;
import vo.registration.RenewalConfigVO;

public class RenewalConfigDAO 
{

	public static  void saveRenewalConfigDetails(RenewalConfigVO objRenewalConfigVO,String strMode_p, HisDAO hisDAO_p,UserVO uservo, String strFlagExecProcInBatch_p)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.RenewalConfigProcedure_dml;
		final int nProcedureIndex;
		String strDbErr="";
		Boolean flag = null;
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objRenewalConfigVO);
			
			////////////////////////////////////////////
			System.out.println("--------------------------------------");
			System.out.println("RenewalConfigDAO :: saveRenewalConfigDetails()");
			System.out.println("p_mode :"+strMode_p);
			System.out.println("p_gnum_patient_cat_code :"+objRenewalConfigVO.getStrRenewalPatCat());
			System.out.println("p_hrgnum_renewal_group :"+objRenewalConfigVO.getStrRenewalGroup());
			System.out.println("p_gnum_hospital_code :"+uservo.getHospitalCode());
			System.out.println("p_hrgnum_renewal_type :"+objRenewalConfigVO.getStrRenewalType());
			System.out.println("p_hrgnum_renewal_mode :"+objRenewalConfigVO.getStrRenewalMode());
			System.out.println("p_hrgnum_days :"+objRenewalConfigVO.getStrNoOfDays());
			System.out.println("p_hrgnum_ismultiple_month :"+objRenewalConfigVO.getStrIsMultipleMonth());
			System.out.println("p_hrgstr_month1 :"+objRenewalConfigVO.getStrMonth1());
			System.out.println("p_hrgstr_month2 :"+objRenewalConfigVO.getStrMonth2());
			System.out.println("p_hrgstr_month3 :"+objRenewalConfigVO.getStrMonth3());
			System.out.println("p_gnum_isvalid :"+Config.IS_VALID_ACTIVE);
			System.out.println("p_gnum_seat_id :"+uservo.getSeatId());
			System.out.println("--------------------------------------");
			
			////////////////////////////////////////////
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",strMode_p,1);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_patient_cat_code",objRenewalConfigVO.getStrRenewalPatCat(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_renewal_group",objRenewalConfigVO.getStrRenewalGroup(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",uservo.getHospitalCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_renewal_type",objRenewalConfigVO.getStrRenewalType(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_renewal_mode",objRenewalConfigVO.getStrRenewalMode(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_days",objRenewalConfigVO.getStrNoOfDays(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_ismultiple_month",objRenewalConfigVO.getStrIsMultipleMonth(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgstr_month1",objRenewalConfigVO.getStrMonth1(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgstr_month2",objRenewalConfigVO.getStrMonth2(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgstr_month3",objRenewalConfigVO.getStrMonth3(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seat_id",uservo.getSeatId(),13);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 14); // 1 for
			
			if("1".equals(strFlagExecProcInBatch_p)){
				hisDAO_p.execute(nProcedureIndex, 1);
			}else{
				hisDAO_p.executeProcedureByPosition(nProcedureIndex);
				/* Getting out parameters */
				strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			}
			
			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) 
			{
				throw new Exception("Data Base Error:" + strDbErr);
			}
			else
			{
				System.out.println("Data Inserted Successussfully");
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("RenewalConfigDAO:getHospitalSpecificRenewalDetails:: " + e.getMessage());
		}
	}

	
	


	/**
	 * to get data for modify page.
	 * 
	 * @param vo the vo
	 */
	public static RenewalConfigVO[] getHospitalSpecificRenewalDetails(UserVO userVo,HisDAO hisDAO_p, String strMode_p) 
	{
		final String strProcName = RegistrationDaoConfig.RenewalConfigProcedure_view;
		final int nProcedureIndex;
		final String strDbErr;
		WebRowSet webRowSet = null;
		ValueObject[] arrVO = {};
		RenewalConfigVO[] arrRenewalConfigVOs =null;
		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_modeval", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",userVo.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_renewal_gp","",3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 4); // 1 for
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,5); // 2 for
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
				System.out.println("RenewalConfigDAO :: Record Successfully retrieved");
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("RenewalConfigDAO:getHospitalSpecificRenewalDetails:: " + e.getMessage());
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
    			arrVO = HelperMethods.populateVOfrmRS(RenewalConfigVO.class, webRowSet);
    			arrRenewalConfigVOs = new RenewalConfigVO[arrVO.length];
    			int i =0;
    			for(ValueObject objValueObject : arrVO)
    			{
    				RenewalConfigVO objRenewalConfigVONew =  (RenewalConfigVO)objValueObject;
    				arrRenewalConfigVOs[i]=objRenewalConfigVONew;
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
		return arrRenewalConfigVOs;

	}
}
	
	

