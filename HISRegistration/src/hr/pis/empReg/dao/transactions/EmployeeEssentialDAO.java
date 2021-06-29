/*
  * Copyright ©.
 */
package hr.pis.empReg.dao.transactions;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
/*import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.presentation.GetPatientDtlJob;
import hisglobal.tools.RSTree;
import hisglobal.tools.Tree;
import hisglobal.utility.Sequence;
import hisglobal.vo.CityLocationMasterVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DeathMannerMasterVO;
import hisglobal.vo.DepartmentMasterVO;
import hisglobal.vo.DeptUnitRosterVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientCategoryVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.ReportVO;
import hisglobal.vo.RoomMasterVO;
import hisglobal.vo.ShiftMasterVO;*/
import hisglobal.vo.UserVO;
//import hr.pis.pr.config.PrDaoConfig;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import vo.hr.pis.empReg.transactions.EmpRegistrationVO;



public class EmployeeEssentialDAO extends DataAccessObject 
//implements EssentialDAOi
{

	//Logger log;

	public EmployeeEssentialDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		//log = LogManager.getLogger(this.getClass());
	}
				
	
	public boolean checkDuplicateEmployeeNumber(EmpRegistrationVO _objEmployeeVO_p, UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strFunc = "";
		strFunc = "{? = call pkg_pis_util.fun_checkduplicateempno(?,?)}";
		int nfuncIndex = 0;
		String isDup="";
		boolean isDuplicate=false;
		try 
		{
			daoObj = new HisDAO("Pis","EmployeeRegEssentialDAO.checkDuplicateEmployeeNumber");

			nfuncIndex = daoObj.setFunction(strFunc);
		
			daoObj.setFuncInValue(nfuncIndex, 2,_objEmployeeVO_p.getStrEmployeeNumber());
			daoObj.setFuncInValue(nfuncIndex, 3,_userVO.getHospitalCode());
			daoObj.setFuncOutValue(nfuncIndex, 1);
		
			daoObj.executeFunction(nfuncIndex);
		
			isDup = daoObj.getFuncString(nfuncIndex);
			
			if (isDup.equalsIgnoreCase("Y")) 
			{
				isDuplicate=true;
				throw new HisDuplicateRecordException("Duplicate Employee Number Exist");
			}
			else
			{
				isDuplicate=false;
			}
		} 
		catch(HisDuplicateRecordException e)
		{
			
		}
		catch (Exception e) 
		{			
		
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		

		return isDuplicate;
	}
	
	public boolean checkDuplicateOldEmployeeNumber(EmpRegistrationVO _objEmployeeVO_p, UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strFunc = "";
		strFunc = "{? = call pkg_pis_util.fun_checkduplicateoldempno(?,?)}";
		int nfuncIndex = 0;
		String isDup="";
		boolean isDuplicate=false;
		try 
		{
			daoObj = new HisDAO("Pis","EmployeeRegEssentialDAO.checkDuplicateOldEmployeeNumber");

			nfuncIndex = daoObj.setFunction(strFunc);
		
			daoObj.setFuncInValue(nfuncIndex, 2,_objEmployeeVO_p.getStrOldEmployeeNumber());
			daoObj.setFuncInValue(nfuncIndex, 3,_userVO.getHospitalCode());
			daoObj.setFuncOutValue(nfuncIndex, 1);
		
			daoObj.executeFunction(nfuncIndex);
		
			isDup = daoObj.getFuncString(nfuncIndex);
			
			if (isDup.equalsIgnoreCase("Y")) 
			{
				isDuplicate=true;
				throw new HisDuplicateRecordException("Duplicate Old Employee Number Exist");
			}
			else
			{
				isDuplicate=false;
			}
		} 
		catch(HisDuplicateRecordException e)
		{
			
		}
		catch (Exception e) 
		{			
		
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		

		return isDuplicate;
	}
	
	public boolean checkDuplicatePANCardNumber(EmpRegistrationVO _objEmployeeVO_p, UserVO _userVO, String duplicasyCheckMode)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strFunc = "";
		strFunc = "{? = call pkg_pis_util.fun_checkduplicatepancardno(?,?,?,?)}";
		int nfuncIndex = 0;
		String isDup="";
		boolean isDuplicate=false;
		try 
		{
			System.out.println("Inside checkDuplicatePANCardNumber");
			
			daoObj = new HisDAO("Pis","EmployeeRegEssentialDAO.checkDuplicatePANCardNumber");

			nfuncIndex = daoObj.setFunction(strFunc);
		
			daoObj.setFuncInValue(nfuncIndex, 2,_objEmployeeVO_p.getStrPANNumber());
			daoObj.setFuncInValue(nfuncIndex, 3,_userVO.getHospitalCode());
			daoObj.setFuncInValue(nfuncIndex, 4,_objEmployeeVO_p.getStrEmployeeNumber());
			daoObj.setFuncInValue(nfuncIndex, 5,duplicasyCheckMode);
			daoObj.setFuncOutValue(nfuncIndex, 1);
		
			daoObj.executeFunction(nfuncIndex);
		
			isDup = daoObj.getFuncString(nfuncIndex);
			
			if (isDup.equalsIgnoreCase("Y")) 
			{
				isDuplicate=true;
				throw new HisDuplicateRecordException("Duplicate PAN Card Number Exist");
			}
			else
			{
				isDuplicate=false;
			}
		} 
		catch(HisDuplicateRecordException e)
		{
			
		}
		catch (Exception e) 
		{			
		
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		

		return isDuplicate;
	}
		
	public List validateGuestLoginDtl(UserVO userVO_p, String guestModifyEmpNo_p,String guestModifyDoB_p,String strMode_p) 
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = PrDaoConfig.PROCEDURE_VALIDATE_GUEST_LOGIN;
		int nProcIndex = 0;
		String strErr = "";
		List lstEmployeeJsonObjString=new ArrayList();

		try 
		{
			System.out.println("EmployeeRegEssentialDAO :: validateGuestLoginDtl()");
			daoObj = new HisDAO("Pis","EmployeeRegEssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_guest_modify_emp_no ", guestModifyEmpNo_p==null?"":guestModifyEmpNo_p,2);
			daoObj.setProcInValue(nProcIndex, "p_guest_modify_emp_dob ", guestModifyDoB_p==null?"":guestModifyDoB_p,3);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code  ", userVO_p.getHospitalCode(),4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				while(webRs.next()){
					lstEmployeeJsonObjString.add(webRs.getString(1));
					System.out.println("webRs.getString(1) :" + webRs.getString(1));
				}
			} 
			else 
			{
				throw new Exception(strErr);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("EmployeeRegEssentialDAO :: validateGuestLoginDtl" + e);
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return lstEmployeeJsonObjString;
	}
	
	
	
	
	
	
}//end class
