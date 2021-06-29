/**
 * 
 */
package registration.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;




import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationDaoConfig;
import vo.registration.VerificationDocVO;

/**
 * @author s.singaravelan
 *
 */
public class VerificationDocDAO 
{

		
	public static boolean chkVerificationDocDuplicate(VerificationDocVO objModelDoc,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=true;
		int ncount=0;
		final String strProcName = "{call pkg_reg_view.proc_gblt_verification_document_mst(?,?,?,?,?)}";
		final int nProcedureIndex;
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelDoc);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_docname",objModelDoc.getStrDocName().trim(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,3);		

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,5); 
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			objModelDoc.setStrMsgString("VerificationDocDAO.chkVerificationDocDuplicate() --> " + e.getMessage());
			objModelDoc.setStrMsgType("1");

		} 
		try
		{
			webRowSet.beforeFirst();
			webRowSet.next();
			ncount=Integer.parseInt(webRowSet.getString(1));
			System.out.println("------"+ncount+"-----");		
			if (ncount == 0) {
				bExistStatus=true;
			} else {
				bExistStatus=false;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			objModelDoc.setStrMsgString("VerificationDocDAO.chkVerificationDocDuplicate() --> " + e.getMessage());
			objModelDoc.setStrMsgType("1");

		} 
		finally 
		{
		}
		return bExistStatus;
	}
	
		
	public static String saveVerificationDocDetails(VerificationDocVO objModelDoc,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = "{call pkg_gbl_dml.dml_gblt_verificationdoc_mst(?,?,?,?,?,?,?,?,?,?)}";
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelDoc);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_doccode",objModelDoc.getStrDocCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_docname",objModelDoc.getStrDocName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_isalternate",objModelDoc.getStrIsAlternate(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_idsize",objModelDoc.getStrIdSize(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_validationtype",objModelDoc.getStrIdValidationType(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_issuing_authority",objModelDoc.getStrIsAuth(),9);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,10); 
			
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
			objModelDoc.setStrMsgString("VerificationDocDAO.saveVerificationDocDetails() --> "	+ e.getMessage());
			objModelDoc.setStrMsgType("1");
		}
		return strReuestNo;
	}
	
	public static List getVerificationDocuments(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = RegistrationDaoConfig.PROCEDURE_GET_VERIFICATION_DOC_COMBO;
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				System.out.println("getVerificationDocuments() b4 throw");
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("EssentialDAO:getVerificationDocuments" + e);
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}
	
	public static List getVerificationDocumentsExceptDocCode(UserVO _userVO,HisDAO daoObj,String docCode)
	{
		WebRowSet webRs = null;
		List alRecord = new ArrayList();
		String strProcName = "{call pkg_reg_view.proc_gblt_verification_document_mst(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode","1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_docname",docCode,2);
			daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,3);		

			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				System.out.println("getVerificationDocuments() b4 throw");
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("EssentialDAO:getVerificationDocuments" + e);
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}
	
		
}
