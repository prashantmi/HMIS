/**
 * 
 */
package registration.dao;

import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.utility.Sequence;
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

import registration.config.RegistrationConfig;
import registration.config.RegistrationDaoConfig;
import vo.registration.DisclaimerVO;
import vo.registration.DisclaimerVO;
import vo.registration.PatCatDocVO;

/**
 * @author s.singaravelan
 *
 */
public class DisclaimerMstDAO
{

	//To List the Department List in the Dept Disclaimer Master
	public static List getDisclaimerDeptList(HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPARTMENT_VIEW;
		final int nProcedureIndex;
		final String strDbErr;		
		List alRecord = new ArrayList(); 

		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","4",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode","",2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deptname","",3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7);

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");			

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}

		try 
		{
			if (!rs.next()) 
			{
				//throw new HisRecordNotFoundException("Department Location Details Not Found");
			}
			else
			{
				rs.beforeFirst();
				alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		} 
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} 
			else
				throw new HisDataAccessException("DisclaimerMstDAO:getDisclaimerDeptList:HelperMethods :: " + e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();hisDAO_p = null;
			}
		}
		return alRecord;
	}

	//To List the Department Unit List in the Unit Disclaimer Master
	public static List getDisclaimerDeptUnitList(HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_UNIT_VIEW;
		final int nProcedureIndex;
		final String strDbErr;		
		List alRecord = new ArrayList(); 

		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","3",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_unitcode","",2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode","",3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_unitname","",4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),5);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,6);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 7);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,8); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");			

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}

		try 
		{
			if (!rs.next()) 
			{
				//throw new HisRecordNotFoundException("Department Location Details Not Found");
			}
			else
			{
				rs.beforeFirst();
				alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		} 
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} 
			else
				throw new HisDataAccessException("DisclaimerMstDAO:getDisclaimerDeptList:HelperMethods :: " + e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();hisDAO_p = null;
			}
		}
		return alRecord;
	}

	
	//To Save the Disclaimer Details 
	public static String saveDisclaimerDetails(DisclaimerVO objModelDisclaimer,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DISCLAIMER_DML;
		final int nProcedureIndex;
		final String strDbErr;
		WebRowSet webRowSet = null;
		Boolean flag = null;
		String strReuestNo="";
		int funcIndex = 0;
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HelperMethods.setNullToEmpty(objModelDisclaimer);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_disclaimercode",objModelDisclaimer.getStrDisclaimerCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_disclaimertype",objModelDisclaimer.getStrDisclaimerType(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelDisclaimer.getStrDeptCode().trim(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelDisclaimer.getStrDeptUnitCode().trim(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_alignment",objModelDisclaimer.getStrAlignment(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isheader",objModelDisclaimer.getStrIsHeader(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_usability",objModelDisclaimer.getStrUsabilityFlag(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_disclaimer1",objModelDisclaimer.getStrDisclaimerDesc1(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_disclaimer2",objModelDisclaimer.getStrDisclaimerDesc2(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_disclaimer3",objModelDisclaimer.getStrDisclaimerDesc3(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isdefault",objModelDisclaimer.getStrIsDefault(),12);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),15);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,16); 

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
			objModelDisclaimer.setStrMsgString("DisclaimerMstDAO.saveDisclaimerDetails() --> "	+ e.getMessage());
			objModelDisclaimer.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	
	//To List the Default Disclaimer in  Default Disclaimer Master Modify
	public static DisclaimerVO[] getDefaultDisclaimer(HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_GET_DISCLAIMER;
		final int nProcedureIndex;
		final String strDbErr;		
		DisclaimerVO []disclMasterVO=null;

		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
						
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "5",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",uservo.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_deptcode","",3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_deptunitcode","",4);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_usabilityflag",RegistrationConfig.DISCLAIMER_DEFAULT_YES,5);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,7); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");			

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}

		try 
		{
			if (!rs.next()) 
			{
				//throw new HisRecordNotFoundException("Department Location Details Not Found");
			}
			else
			{
				Sequence sq=new Sequence();
				rs.beforeFirst();
				int len=0;
				while(rs.next())len++;
				disclMasterVO=new DisclaimerVO[len];
				len=0;
				rs.beforeFirst();
				while(rs.next())
				{
					disclMasterVO[len]=new DisclaimerVO();
					HelperMethods.populateVOfrmRS(disclMasterVO[len],rs);
					len++;
				}
			}
		} 
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} 
			else
				throw new HisDataAccessException("DisclaimerMstDAO:getDefaultDisclaimerDeptList:HelperMethods :: " + e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();hisDAO_p = null;
			}
		}
		return disclMasterVO;
	}	
	
	
	//To Update the Disclaimer Details 
	public static String updateDisclaimerDetails(DisclaimerVO objModelDisclaimer,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DISCLAIMER_DML;
		final int nProcedureIndex;
		final String strDbErr;
		WebRowSet webRowSet = null;
		Boolean flag = null;
		String strReuestNo="";
		int funcIndex = 0;
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HelperMethods.setNullToEmpty(objModelDisclaimer);


			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_disclaimercode",objModelDisclaimer.getStrDisclaimerCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_disclaimertype",objModelDisclaimer.getStrDisclaimerType(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelDisclaimer.getStrDeptCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",objModelDisclaimer.getStrDeptUnitCode(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_alignment",objModelDisclaimer.getStrAlignment(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isheader",objModelDisclaimer.getStrIsHeader(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_usability",objModelDisclaimer.getStrUsabilityFlag(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_disclaimer1",objModelDisclaimer.getStrDisclaimerDesc1(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_disclaimer2",objModelDisclaimer.getStrDisclaimerDesc2(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_disclaimer3",objModelDisclaimer.getStrDisclaimerDesc3(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isdefault",objModelDisclaimer.getStrIsDefault(),12);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),15);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,16); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);


			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			System.out.println("Data Updated Successussfully");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objModelDisclaimer.setStrMsgString("DisclaimerMstDAO.UpdateDisclaimerDetails() --> "	+ e.getMessage());
			objModelDisclaimer.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	
	//To get Disclaimer Details in Modify
	public static DisclaimerVO modifyDetails(DisclaimerVO objDiscModel,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_GET_DISCLAIMER;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HelperMethods.setNullToEmpty(objDiscModel);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "4",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",objDiscModel.getStrHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_deptcode","",3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_deptunitcode","",4);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_usabilityflag",objDiscModel.getStrDisclaimerCode(),5);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,7); 

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
			objDiscModel.setStrMsgString("DisclaimerVO.modifyDetails() --> "	+ e.getMessage());
			objDiscModel.setStrMsgType("1");
		}


		try
		{
			//List<Entry>listMachineName =(List<Entry>) HelperMethodsDAO.getAlOfEntryObjects(webRowSet);
			webRowSet.beforeFirst();
			webRowSet.next();
			objDiscModel.setStrDisclaimerCode(webRowSet.getString(1));
			objDiscModel.setStrDeptCode(webRowSet.getString(2));
			objDiscModel.setStrUsabilityFlag(webRowSet.getString(3));
			objDiscModel.setStrDeptUnitCode(webRowSet.getString(4));
			objDiscModel.setStrDisclaimerDesc1(webRowSet.getString(5));
			objDiscModel.setStrDisclaimerDesc2(webRowSet.getString(6));
			objDiscModel.setStrDisclaimerDesc3(webRowSet.getString(7));
			objDiscModel.setStrIsDefault(webRowSet.getString(8));
			objDiscModel.setStrIsHeader(webRowSet.getString(9));
			objDiscModel.setStrAlignment(webRowSet.getString(10));
			objDiscModel.setStrHospitalCode(webRowSet.getString(11));
			//objDiscModel.setStrIsValid(webRowSet.getString(12));


		}
		catch (Exception e)
		{
			throw new HisDataAccessException("DisclaimerVO:modifyDetails():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objDiscModel;

	}


}
