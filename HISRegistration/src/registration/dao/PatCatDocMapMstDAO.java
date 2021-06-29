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
import vo.registration.DepartmentVO;
import vo.registration.PatCatDocVO;

/**
 * @author s.singaravelan
 *
 */
public class PatCatDocMapMstDAO
{

	//To List the CategoryWiseMappedDocuments in the Document Mapping Master
	public static List getCategoryWiseMappedDocument(String _categoryCode,HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_PATCAT_VERIFICATION_LIST;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","3",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_patcatcode",_categoryCode,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6);

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
				throw new HisDataAccessException("PatCatDocMapMstDAO:getCategoryWiseMappedDocument:HelperMethods :: " + e);
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
	
	
	//To List the CategoryWiseUnMappedDocuments in the Document Mapping Master
	public static List getCategoryWiseUnMappedDocument(String _categoryCode,HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_PATCAT_VERIFICATION_LIST;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_patcatcode",_categoryCode,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6);

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
				throw new HisDataAccessException("PatCatDocMapMstDAO:getCategoryWiseMappedDocument:HelperMethods :: " + e);
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

	//To Save the CategoryWiseMappedDocuments in the Document Mapping Master
	public static String insert(PatCatDocVO objModelDoc_p,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_PATCAT_VERIFICATION_DML;
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
			HisUtil.replaceNullValueWithEmptyString(objModelDoc_p);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_patcatcode",objModelDoc_p.getStrPatCategoryCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_doccode",objModelDoc_p.getStrDocCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isreq",objModelDoc_p.getStrIsReq(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),7);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,8); 

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
			objModelDoc_p.setStrMsgString("PatCatDocMapMstDAO.save() --> "	+ e.getMessage());
			objModelDoc_p.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}

	
	//To Update the CategoryWiseMappedDocuments in the Document Mapping Master
	public static String update(PatCatDocVO objModelDoc_p,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_PATCAT_VERIFICATION_DML;
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
			HisUtil.replaceNullValueWithEmptyString(objModelDoc_p);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_patcatcode",objModelDoc_p.getStrPatCategoryCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_doccode",objModelDoc_p.getStrDocCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isreq",objModelDoc_p.getStrIsReq(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),7);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,8); 

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
			objModelDoc_p.setStrMsgString("PatCatDocMapMstDAO.update() --> "	+ e.getMessage());
			objModelDoc_p.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}
	
	//To List the CategoryWise Verification Documents in the Category Change Process
	public static List getCategoryWiseVerificationDocument(String _categoryCode,HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_PATCAT_VERIFICATION_LIST;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","4",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_patcatcode",_categoryCode,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6);

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
				throw new HisDataAccessException("PatCatDocMapMstDAO:getCategoryWiseMappedDocument:HelperMethods :: " + e);
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

}
