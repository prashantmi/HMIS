/**
 * 
 */
package registration.dao;


import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;

import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationDaoConfig;
import vo.registration.EmgDeathMstVO;

/**
 * @author s.singaravelan
 *
 */
public class EmgDeathDAO
{

	//To Save the Death in the Death Mst
	public static String saveEmgDeathDetails(EmgDeathMstVO objModelEmgDeath,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EMG_DEATH_MANNER_DML;
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelEmgDeath);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deathmannercode",objModelEmgDeath.getStrEmgDeathMannerCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deathmanner",objModelEmgDeath.getStrEmgDeathManner(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deathmannerdesc",objModelEmgDeath.getStrEmgDeathMannerDesc(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_ispostmortem",objModelEmgDeath.getStrIsPostMortem(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isaccidental",objModelEmgDeath.getStrIsAccidental(),6);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),8);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),9);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,9); 

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
			objModelEmgDeath.setStrMsgString("EmgDeathDAO.saveEmgDeathDetails() --> "	+ e.getMessage());
			objModelEmgDeath.setStrMsgType("1");
		}
		return strReuestNo;
	}


	//To get EmgDeath Details in Modify
	public static EmgDeathMstVO modifyDetails(EmgDeathMstVO objModelEmgDeath,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EMG_DEATH_MANNER_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelEmgDeath);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deathmannercode",objModelEmgDeath.getStrEmgDeathMannerCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deathmanner",objModelEmgDeath.getStrEmgDeathManner(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,6); 

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
			objModelEmgDeath.setStrMsgString("EmgDeathDAO.modifyDetails() --> "	+ e.getMessage());
			objModelEmgDeath.setStrMsgType("1");
		}


		try
		{
			webRowSet.beforeFirst();
			webRowSet.next();
			objModelEmgDeath.setStrEmgDeathMannerCode(webRowSet.getString(1));
			objModelEmgDeath.setStrEmgDeathManner(webRowSet.getString(2));
			objModelEmgDeath.setStrEmgDeathMannerDesc(webRowSet.getString(3));
			objModelEmgDeath.setStrIsPostMortem(webRowSet.getString(4));
			objModelEmgDeath.setStrIsAccidental(webRowSet.getString(5));				

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EmgDeathDAO.modifyDetails():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objModelEmgDeath;

	}

	//To Update EmgDeath Details
	public static void updateEmgDeathDetails(EmgDeathMstVO objModelEmgDeath,HisDAO hisDAO_p,UserVO uservo ) {


		final String strProcName =  RegistrationDaoConfig.PROCEDURE_EMG_DEATH_MANNER_DML;
		final int nProcedureIndex;
		final String strDbErr;



		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelEmgDeath);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deathmannercode",objModelEmgDeath.getStrEmgDeathMannerCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deathmanner",objModelEmgDeath.getStrEmgDeathManner(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deathmannerdesc",objModelEmgDeath.getStrEmgDeathMannerDesc(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_ispostmortem",objModelEmgDeath.getStrIsPostMortem(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isaccidental",objModelEmgDeath.getStrIsAccidental(),6);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),8);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),9);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,9); 
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

	public static boolean chkEmgDeathDuplicate(EmgDeathMstVO objModelEmgDeath,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_EMG_DEATH_MANNER_VIEW;
		final int nProcedureIndex;
		int ncount=0;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelEmgDeath);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deathmannercode",objModelEmgDeath.getStrEmgDeathMannerCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deathmanner",objModelEmgDeath.getStrEmgDeathManner(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,6); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}catch (Exception e) 
		{
			e.printStackTrace();
			objModelEmgDeath.setStrMsgString("EmgDeathDAO.chkEmgDeathDuplicate() --> " + e.getMessage());
			objModelEmgDeath.setStrMsgType("1");

		} 

		try
		{
			webRowSet.beforeFirst();
			webRowSet.next();
			ncount=Integer.parseInt(webRowSet.getString(1));
			System.out.println("------"+ncount+"-----");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("EmgDeathDAO():HelperMethodsDAO.chkEmgDeathDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) 
		{
			bExistStatus=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2"))
		{
			if(objModelEmgDeath.getStrOldEmgDeathManner().equalsIgnoreCase(objModelEmgDeath.getStrEmgDeathManner()) && (ncount == 1))
			{
				bExistStatus=true;
			}
			if(!(objModelEmgDeath.getStrOldEmgDeathManner().equalsIgnoreCase(objModelEmgDeath.getStrEmgDeathManner())) && (ncount == 0))
			{
				bExistStatus=true;
			}
		} else 
		{
			bExistStatus=false;
		}
		return bExistStatus;
	}	
	
	
}
