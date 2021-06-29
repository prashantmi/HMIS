/* 
## Copyright Information		: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Anant Patel 
 ## Module Name					: MRD
 ## Process/Database Object Name: Duplicate Record Printing And Handover process
 ## Purpose						: Duplicate Record Printing And Handover process
 ## Date of Creation			: 19 Jan 2015
 ## Modification Log			:				
 ##		Modify Date				:  
 ##		Reason	(CR/PRS)		:  
 ##		Modify By				: 

*/
package mrd.transaction.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import mrd.MrdConfig;
import mrd.vo.DupRecPrintReqVO;
import hisglobal.backutil.HisDAO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class DupRecPrintAndHandoverDAO extends DataAccessObject {

	public DupRecPrintAndHandoverDAO(TransactionContext _tx) {
		super(_tx);

	}

	public DupRecPrintReqVO[] getDupRecPrintAndHandoverDtl(UserVO strUserVO) 
	{
		DupRecPrintReqVO[] dupRecPrintReqVOs = null;
		ValueObject[] valueObjects = null;
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_mrd_view.proc_hpmrt_duplicate_printing_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("MRD","DupRecPrintAndHandoverDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "req_status", MrdConfig.REQUESTED,1);
		daoObj.setProcInValue(nProcIndex, "isvalid", Config.IS_VALID_ACTIVE,2);	
		daoObj.setProcInValue(nProcIndex, "hoscode", strUserVO.getHospitalCode(),3);
		daoObj.setProcOutValue(nProcIndex, "err",1,4);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		strErr = daoObj.getString(nProcIndex, "err");
		System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Duplicate Record Request Detail Found");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(DupRecPrintReqVO.class, rs);
				dupRecPrintReqVOs = new DupRecPrintReqVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					dupRecPrintReqVOs[i] = (DupRecPrintReqVO) valueObjects[i];
				}
			}
			
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return dupRecPrintReqVOs;
	}
	public String getBillNoQtyDtl(DupRecPrintReqVO dupRecPrintReqVO,UserVO userVO) 
	{
		String count = "";
		ResultSet rs = null;
		String errorMsg="";
		String billNoQty;		
		try
		{
			Procedure strProc=new Procedure(MrdConfig.MEDICAL_CERTIFICATE_ONLINE_BILL_DTL);
			strProc.addInParameter(1,Types.VARCHAR,userVO.getHospitalCode());
			strProc.addInParameter(2,Types.VARCHAR,dupRecPrintReqVO.getRecordType());
			strProc.addInParameter(3,Types.VARCHAR,"1");
		    strProc.addInParameter(4,Types.VARCHAR,"8");
		    strProc.addInParameter(5,Types.VARCHAR,dupRecPrintReqVO.getCertificateId()); 
			strProc.addInParameter(6,Types.VARCHAR,"0");
			strProc.setReturnType(Types.VARCHAR);
			return	billNoQty = (String)strProc.execute(super.getTransactionContext().getConnection());
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("No Record Found");
		}
	}
	
	public void saveDuplicateCertificateIssueDtl(DupRecPrintReqVO dupRecPrintReqVO,UserVO userVO)
	{
		String errorMsg = null;
		String recordStatus=MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_SEND_HANDOVER;
		try
		{
			
					
					Procedure strProc = new Procedure(MrdConfig.PROCEDURE_ESTIMATE_CERTIFICATE_ISSUE_DTL_UPDATE);
							//PROCEDURE_PROC_UPDATE_HPMRT_DUPLICATE_PRINTING_DTL
							
					
					strProc.addInParameter(1, Types.VARCHAR, recordStatus);
					strProc.addInParameter(2, Types.VARCHAR, dupRecPrintReqVO.getBillNo());
					strProc.addInParameter(3, Types.VARCHAR, dupRecPrintReqVO.getHandoverTo());
					strProc.addInParameter(4, Types.VARCHAR, dupRecPrintReqVO.getHandoverToName());
					strProc.addInParameter(5, Types.VARCHAR, dupRecPrintReqVO.getHandoverToRel());
					strProc.addInParameter(6, Types.VARCHAR, dupRecPrintReqVO.getHandoverToAddress());
					strProc.addInParameter(7, Types.VARCHAR, dupRecPrintReqVO.getHandoverToContact());
					strProc.addInParameter(8, Types.VARCHAR, userVO.getSeatId()); 
					strProc.addOutParameter(9, Types.VARCHAR);
					strProc.execute(super.getTransactionContext().getConnection())	;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}

	}
	
}
