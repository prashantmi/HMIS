package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import vo.registration.AddressVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.RegistrationCancellationVO;

/**
 * EpisodeDAO is a class which describes all the methods required for database interaction
 * for HRGT_EPISODE_DTL table, for example, insert, update, select and delete.
 * EpisodeDAO class provides a standard interface between Business Objects and Database.
 * @author AHIS
 */
public class RegistrationCancellationDAO extends RegistrationDAO
{

	/**
	 * Creates a new EpisodeDAO object.
	 * @param _transactionContext	Provides the lock on a transaction.
	 */
	public RegistrationCancellationDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}

	//Start:Sheeldarshi:Registration Cancellation
	
	public EpisodeVO[] getAllEpisodesForCancellation(String crNo,UserVO objUserVO_p,String strMode)
	{

		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_hrgt_episode_dtl(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		EpisodeVO[] _episodeVO;
		ValueObject[] vo ={};
		try
		{
			
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode,1);

			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", crNo,3);
			daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", objUserVO_p.getUserSeatId(),4);
			daoObj.setProcInValue(nProcIndex, "p_dept", "",5);
			daoObj.setProcInValue(nProcIndex, "p_deptunitcode", "",6);
			daoObj.setProcInValue(nProcIndex, "p_visittype", "",7);

			
			daoObj.setProcOutValue(nProcIndex, "err", 1,8);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No open episode found");

			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_episodeVO[i] = (EpisodeVO) vo[i];
	
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	}
		public void saveRegistrationCancellationDtl(HisDAO daoObj,RegistrationCancellationVO objRegistrationCancellationVO_p, UserVO objUserVO_p,String strMode_p) 
		{
			int seq=0;
			String strErr = "";
			int nProcIndex1 = 0;
			String strProcName1="";
			try 
			{
				strProcName1 = "{call pkg_reg_dml.dml_hrgt_cancellation_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)}";
				
				HelperMethods.setNullToEmpty(objRegistrationCancellationVO_p);
				HelperMethods.setNullToEmpty(objUserVO_p);

				nProcIndex1 = daoObj.setProcedure(strProcName1);
				System.out.println("AddressDAO :: saveAddressDtl()");
				//////////////////////
		
				System.out.println("p_modeval :"+ strMode_p);
				System.out.println("p_hrgnum_puk :"+ (objRegistrationCancellationVO_p.getPatCrNo().equals("")?"0":objRegistrationCancellationVO_p.getPatCrNo()));
		    	System.out.println("p_hrgnum_episode_code :"+objRegistrationCancellationVO_p.getEpisodeCode());
		    	System.out.println("p_hrgnum_visit_no :"+ objRegistrationCancellationVO_p.getEpisodeVisitNo());
		    	System.out.println("p_hrgstr_remarks :"+objRegistrationCancellationVO_p.getDupRemarks());
		    	System.out.println("p_gnum_dept_code :"+ objRegistrationCancellationVO_p.getDepartmentCode());
		    	System.out.println("p_hgnum_deptunitcode :"+ objRegistrationCancellationVO_p.getDepartmentUnitCode());
		    	System.out.println("p_hrgstr_card_print_flag :"+ objRegistrationCancellationVO_p.getCardPrintFlag());
		    	System.out.println("p_gbl_isvalid :"+ "1");
		    	System.out.println("p_gnum_seat_id :"+objUserVO_p.getSeatId());
		    	System.out.println("p_hblnum_tariff_id :"+ objRegistrationCancellationVO_p.getTariffId());
		    	System.out.println("p_hblnum_bill_no :"+ objRegistrationCancellationVO_p.getStrBillNo());
		    	System.out.println("p_hrgstr_ip_add :"+ objUserVO_p.getIpAddress());
		    	System.out.println("p_hrgnum_amt_collected :"+"1");
		    	System.out.println("p_gnum_hospital_code :"+ objUserVO_p.getHospitalCode());
		    	
				//////////////////////
		    	 
				daoObj.setProcInValue(nProcIndex1, "p_modeval", strMode_p,++seq);
		    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", (objRegistrationCancellationVO_p.getPatCrNo().equals("")?"0":objRegistrationCancellationVO_p.getPatCrNo()),++seq);
				daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", objRegistrationCancellationVO_p.getEpisodeCode(),++seq);
		    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",  objRegistrationCancellationVO_p.getEpisodeVisitNo(),++seq);
		    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_remarks", objRegistrationCancellationVO_p.getDupRemarks(),++seq);
		    	daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code",objRegistrationCancellationVO_p.getDepartmentCode(),++seq);
		    	daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode",objRegistrationCancellationVO_p.getDepartmentUnitCode(),++seq);
		    	//daoObj.setProcInValue(nProcIndex1, "p_hrgstr_card_print_flag", objRegistrationCancellationVO_p.getCardPrintFlag(),8);
		    	daoObj.setProcInValue(nProcIndex1, "p_gbl_isvalid",  Config.IS_VALID_ACTIVE,++seq);
		    	daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", objUserVO_p.getSeatId(),++seq);
		        daoObj.setProcInValue(nProcIndex1, "p_hblnum_tariff_id", objRegistrationCancellationVO_p.getTariffId(),++seq);
		    	daoObj.setProcInValue(nProcIndex1, "p_hblnum_bill_no",objRegistrationCancellationVO_p.getStrBillNo(),++seq);
		    //	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", objUserVO_p.getIpAddress(),13);
		    	//daoObj.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", objRegistrationCancellationVO_p.getAmtCollected(),14);
		    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", objRegistrationCancellationVO_p.getPatAmountCollected(),++seq);
		    	daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),++seq);
		    	daoObj.setProcOutValue(nProcIndex1, "err", 1,++seq);

				daoObj.executeProcedureByPosition(nProcIndex1);			
				
				//strErr = daoObj.getString(nProcIndex1, "err");
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
			
		}
		
		
		//End:Sheeldarshi:Registration Cancellation
	
	
}
