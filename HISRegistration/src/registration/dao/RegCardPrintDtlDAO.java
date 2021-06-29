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
import vo.registration.RegCardPrintVO;
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
/*
public class RegCardPrintDtlDAO extends DataAccessObject
{
	Logger log;

	public RegCardPrintDtlDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
	
	public RegCardPrintVO saveReprintCard(RegCardPrintVO objRegCardPrintVO_p,UserVO userVO)
	{
		String query =  "" ;
	   	Map populateMAP =new HashMap();    		 	  	
	 	String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
	 	String queryKey="INSERT.REPRINT_DUPLICATE_CARD.HRGT_CARD_PRINT_DTL";
	 	Sequence sq=new Sequence();
	 	try
	 	{
	 	    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	 	}
	 	catch(Exception e)
	 	{
	 		e.printStackTrace();
	 		throw new HisDataAccessException("DuplicateRenewVO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	 	}
	 	
	 	try
	 	{
			objRegCardPrintVO_p.getPatCrNo());
			objRegCardPrintVO_p.getPatCrNo());
			userVO.getHospitalCode());
			objRegCardPrintVO_p.getEpisodeCode());
			objRegCardPrintVO_p.getEpisodeVisitNo());
			objRegCardPrintVO_p.getEpisodeCode());
			objRegCardPrintVO_p.getEpisodeVisitNo());
			objRegCardPrintVO_p.getDupRemarks());
			objRegCardPrintVO_p.getDepartmentCode());
			objRegCardPrintVO_p.getDepartmentUnitCode());
			objRegCardPrintVO_p.getCardPrintFlag());
			Config.IS_VALID_ACTIVE );
			userVO.getSeatId());
			objRegCardPrintVO_p.getIpAdd());
			userVO.getHospitalCode());
	 	}
	 	catch(Exception e)
	 	{
	 		e.printStackTrace();
			throw new HisDataAccessException("DuplicateRenewVO:populateMap(_episodeVO,populateMAP)::"+e);
	 	}
	 	try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        return objRegCardPrintVO_p;
	}
	
	*/

/**
 * AddressDAO is a class which describes all the methods required for database interaction
 * for HRGT_PATIENT_ADD_DTL table, for example, insert, update, select and delete.
 * @author AHIS
 *
 */
public class RegCardPrintDtlDAO extends RegistrationDAO
{
	public RegCardPrintDtlDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}
	

    
    /**
	 * Save/Update an address entry in DB for a patient.
	 * @param	objRegCardPrintVO_p	Provides address details to be entered.
	 * @param	objUserVO_p		Provides User details.
	 * @return	AddressVO with values stored in DB.
	 * if strMode_p = 1 -->> Insert
	 * if strMode_p = 2 -->> Update
	 */
    public RegCardPrintVO saveReprintCard(HisDAO objHisDAO_p,RegCardPrintVO objRegCardPrintVO_p,UserVO objUserVO_p,String strMode_p)
    {
    	String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		try 
		{
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_card_print_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?)}";
			
			HelperMethods.setNullToEmpty(objRegCardPrintVO_p);
			HelperMethods.setNullToEmpty(objUserVO_p);

			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			System.out.println("AddressDAO :: saveAddressDtl()");
			//////////////////////
	
			System.out.println("p_modeval :"+ strMode_p);
			System.out.println("p_hrgnum_puk :"+ (objRegCardPrintVO_p.getPatCrNo().equals("")?"0":objRegCardPrintVO_p.getPatCrNo()));
	    	System.out.println("p_hrgnum_episode_code :"+objRegCardPrintVO_p.getEpisodeCode());
	    	System.out.println("p_hrgnum_visit_no :"+ objRegCardPrintVO_p.getEpisodeVisitNo());
	    	System.out.println("p_hrgstr_remarks :"+objRegCardPrintVO_p.getDupRemarks());
	    	System.out.println("p_gnum_dept_code :"+ objRegCardPrintVO_p.getDepartmentCode());
	    	System.out.println("p_hgnum_deptunitcode :"+ objRegCardPrintVO_p.getDepartmentUnitCode());
	    	System.out.println("p_hrgstr_card_print_flag :"+ objRegCardPrintVO_p.getCardPrintFlag());
	    	System.out.println("p_gbl_isvalid :"+ "1");
	    	System.out.println("p_gnum_seat_id :"+objUserVO_p.getSeatId());
	    	System.out.println("p_hblnum_tariff_id :"+ objUserVO_p.getTariffID());
	    	System.out.println("p_hblnum_bill_no :"+ objRegCardPrintVO_p.getStrBillNo());
	    	System.out.println("p_hrgstr_ip_add :"+ objUserVO_p.getIpAddress());
	    	System.out.println("p_hrgnum_amt_collected :"+"1");
	    	System.out.println("p_gnum_hospital_code :"+ objUserVO_p.getHospitalCode());
	    	
			//////////////////////
	    	 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_modeval", strMode_p,1);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk", (objRegCardPrintVO_p.getPatCrNo().equals("")?"0":objRegCardPrintVO_p.getPatCrNo()),2);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", objRegCardPrintVO_p.getEpisodeCode(),3);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",  objRegCardPrintVO_p.getEpisodeVisitNo(),4);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_remarks", objRegCardPrintVO_p.getDupRemarks(),5);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_dept_code",objRegCardPrintVO_p.getDepartmentCode(),6);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode",objRegCardPrintVO_p.getDepartmentUnitCode(),7);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_card_print_flag", objRegCardPrintVO_p.getCardPrintFlag(),8);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gbl_isvalid",  Config.IS_VALID_ACTIVE,9);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id", objUserVO_p.getSeatId(),10);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hblnum_tariff_id", objUserVO_p.getTariffID(),11);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hblnum_bill_no",objRegCardPrintVO_p.getStrBillNo(),12);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", objUserVO_p.getIpAddress(),13);
	    	//objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", objRegCardPrintVO_p.getAmtCollected(),14);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", objRegCardPrintVO_p.getPatAmountCollected(),14);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),15);
	    	objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,16);

			objHisDAO_p.execute(nProcIndex1,1);			
			
			//strErr = objHisDAO_p.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{						
					}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return objRegCardPrintVO_p;
    }
    
}


