package registration.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import registration.config.RegistrationConfig;
import vo.registration.BarcodeSlipVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.Procedure;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ValueObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.rowset.WebRowSet;
import registration.config.RegistrationDaoConfig;
import vo.registration.AddressVO;
import vo.registration.PatientModificationVO;
import vo.registration.PatientVO;

/**
 * AddressDAO is a class which describes all the methods required for database interaction
 * for HRGT_PATIENT_ADD_DTL table, for example, insert, update, select and delete.
 * @author AHIS
 *
 */
public class BarcodeGenerationDAO extends RegistrationDAO
{
	public BarcodeGenerationDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}
   
    /**
	 * Save/Update an barcode slip entry in DB for a patient.
	 * if strMode_p = 1 -->> Insert
	 * if strMode_p = 2 -->> Update
	 */
    public BarcodeSlipVO saveBarcodeSlipDtl(HisDAO objHisDAO_p,BarcodeSlipVO objBarcodeSlipVO,UserVO objUserVO_p,String strMode_p)
    {
    	String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		try 
		{
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_barcode_print_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?)}";//12 args as on sep'17
			
			HelperMethods.setNullToEmpty(objBarcodeSlipVO);
			HelperMethods.setNullToEmpty(objUserVO_p);

			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			System.out.println("BarcodeGenerationDAO :: saveBarcodeSlipDtl()");
			//////////////////////
	    	 int i =1;
			objHisDAO_p.setProcInValue(nProcIndex1, "p_modeval", strMode_p,i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk", (objBarcodeSlipVO.getPatCrNo().equals("")?"0":objBarcodeSlipVO.getPatCrNo()),i++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", objBarcodeSlipVO.getEpisodeCode(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",  objBarcodeSlipVO.getEpisodeVisitNo(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_dept_code",objBarcodeSlipVO.getDepartmentCode(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode",objBarcodeSlipVO.getDepartmentUnitCode(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_barcode_print_flag", objBarcodeSlipVO.getBarcodePrintFlag(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid",  Config.IS_VALID_ACTIVE,i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id", objUserVO_p.getSeatId(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", objUserVO_p.getIpAddress(),i++);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),i++);
	    	objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,i++);//12th arg

			objHisDAO_p.execute(nProcIndex1,1);			
			
			//strErr = objHisDAO_p.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
						throw new Exception(strErr);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return objBarcodeSlipVO;
    }
    
}


