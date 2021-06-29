package registration.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import registration.config.RegistrationConfig;
import registration.config.RegistrationDaoConfig;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.PatCategoryVO;
import vo.registration.PrimaryCategoryChangeVO;

/**
 * @author s.singaravelan
 *
 */
public class PrimaryCategoryChangeDAO extends RegistrationDAO {
	
	
	/**
	 * Creates a new CategoryChangeDAO object.
	 * @param _transactionContext	Provides the lock on a transaction.
	 */	
	public PrimaryCategoryChangeDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}
	
	/**
	 * Creates category change entry in DB for a patient whose Secondary Category has changed.
	 * @param	_changeCategoryVO	Provides category change details.
	 * @param	_userVO		Provides User details.
	 * @return	ChangeCategoryVO with values stored in DB.
	 */
	public PrimaryCategoryChangeVO create(HisDAO objHisDAO_p,PrimaryCategoryChangeVO _primCatChangeVO, UserVO _userVO,String strMode_p){
		
		String strErr = "";
		final String strProcName1 = RegistrationDaoConfig.PROCEDURE_PRICAT_CHANGE_DML;
		int nProcIndex1 = 0;
		//HisDAO objHisDAO_p=null;
		try 
		{			
			HelperMethods.setNullToEmpty(_primCatChangeVO);
			HelperMethods.setNullToEmpty(_userVO);
			
			System.out.println("PrimaryCategoryChangeDAO :: create()");
			//objHisDAO_p = new HisDAO("Registration","PrimaryCategoryChangeDAO");
			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
				    	 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_mode", strMode_p,1);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_crno", _primCatChangeVO.getPatCrNo(),2);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_prevcatcode", _primCatChangeVO.getPatPrevPrimaryCatCode(),3);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_newcatcode", _primCatChangeVO.getPatNewPrimaryCatCode(),4);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isrenewal", _primCatChangeVO.getIsRenewal(),5);

	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gstr_remarks", _primCatChangeVO.getRemarks(),6);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_ipaddress",(_userVO.getIpAddress()==null||_userVO.getIpAddress()=="")?"127.0.0.1":_userVO.getIpAddress(),7);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_cardno", _primCatChangeVO.getCardNo(),8);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_docid", _primCatChangeVO.getVerificationDocumentId(),9);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_prevvalidupto",_primCatChangeVO.getValidUpto(),10);
 	
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,11);			
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seatid",_userVO.getSeatId(),12);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hoscode",_userVO.getHospitalCode(),13);

			/*  ## 		Modification Log							
		 		##		Modify Date				:10thMar'15 
		 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
		 		##		Modify By				:Sheeldarshi */
		 	objHisDAO_p.setProcInValue(nProcIndex1, "p_new_cat_approved_by",_primCatChangeVO.getApprovedBy(),14);
		 	objHisDAO_p.setProcInValue(nProcIndex1, "p_prev_cat_approved_by",_primCatChangeVO.getNewCatapprovedBy(),15);
	    	objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,16);
    		//End
	    	//objHisDAO_p.executeProcedureByPosition(nProcIndex1);
	    	objHisDAO_p.execute(nProcIndex1,1);
			
//			strErr = objHisDAO_p.getString(nProcIndex1, "err");
//		    if (strErr == null)
//				strErr = "";
//			if (!strErr.equals("")) {
//				throw new Exception(strErr);
//			}else{						
//			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
        return _primCatChangeVO;
	}
	
	
	/**
	 * Generate the New Expiry Date for the given Patient Category.
	 * @param	_changeCategoryVO	Provides category change details.
	 * @param	_userVO		Provides User details.
	 * @return	PatCategory Expiry Date.
	 */
	public String generateNewValidUpto(PrimaryCategoryChangeVO _primCatChangeVO, UserVO _userVO){
		
		String strFuncName = "";
		String strNewRenewalDate = "";
		int nFuncIndex = 0;
		HisDAO hisDAO_p=null;
		
		try{
			strFuncName = RegistrationDaoConfig.FUNCTION_GET_NEW_RENEWAL_DATE;
			hisDAO_p = new HisDAO("Registration","PrimaryCategoryChangeDAO");
			
			HisUtil.replaceNullValueWithEmptyString(_primCatChangeVO);
			HisUtil.replaceNullValueWithEmptyString(_userVO);

			nFuncIndex = hisDAO_p.setFunction(strFuncName);
			
			hisDAO_p.setFuncInValue(nFuncIndex, 2, "1");
			hisDAO_p.setFuncInValue(nFuncIndex, 3, _primCatChangeVO.getPatNewPrimaryCatCode());
			hisDAO_p.setFuncInValue(nFuncIndex, 4, _userVO.getHospitalCode());
			
			hisDAO_p.setFuncOutValue(nFuncIndex, 1);
			hisDAO_p.executeFunction(nFuncIndex);
			strNewRenewalDate = hisDAO_p.getFuncString(nFuncIndex);
			System.out.println("---------"+strNewRenewalDate+"---------");
			
      			
		}
		catch(HisRecordNotFoundException e)
		{	
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		}			
			
		return strNewRenewalDate;
	}
	
	/**
	 * Retrieves the details of a given patient category for Primary Patient Category change
	 * @param patCat	Provides Patient Primary Category Code.
	 * @param _userVO	Provides User details.
	 * @return details containing patient category 
	 */
	public List getPrimaryCatDetails(String patCatCode,UserVO _userVO)
	{
		WebRowSet webRs = null;
		ResultSet rs=null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName ="{call Pkg_Reg_View.proc_gblt_patient_cat_combo_dtl(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", "2",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_catcode", patCatCode,2);
			daoObj.setProcInValue(nProcIndex, "p_gnum_cattype", RegistrationConfig.PATIENT_CAT_TYPE_PRIMARY,3);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),4);
			daoObj.setProcInValue(nProcIndex, "p_moduleid",Config.MODULE_ID_REGISTRATION,5);
			daoObj.setProcInValue(nProcIndex, "p_seatid",_userVO.getUserSeatId(),6);

			daoObj.setProcOutValue(nProcIndex, "err", 1,7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			rs = daoObj.getWebRowSet(nProcIndex, "resultset");			

			if (!rs.next()){
				//throw new HisRecordNotFoundException("Unit List Not Found");
			}
			else
			{
				//rs.beforeFirst();
				alRecord=HelperMethodsDAO.getAlOfResultSet(rs);
			}
			
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
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
	
	
	/**
	 * Retrieves the Patient ID details of a given patient category using CR No for Primary Patient Category change
	 * @param patCat	Provides Patient Primary Category Code.
	 * @param _userVO	Provides User details.
	 * @return details Patient ID details 
	 */
	public List getPrimaryCatIDDetails(String crNo,UserVO _userVO)
	{
		WebRowSet webRs = null;
		ResultSet rs=null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName ="{call Pkg_Reg_View.proc_hrgt_patient_id_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", crNo,3);

			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			rs = daoObj.getWebRowSet(nProcIndex, "resultset");			

			if (!rs.next()){
				//throw new HisRecordNotFoundException("Unit List Not Found");
			}
			else{
				//rs.beforeFirst();
				alRecord=HelperMethodsDAO.getAlOfResultSet(rs);
			}
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
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
	
//	public PrimaryCategoryChangeVO[] getChangePrimaryCategoryDetail(String crNo,UserVO _userVO){
//		
//    	PrimaryCategoryChangeVO[] _primaryCategoryChangeVO=null;
//    	ValueObject[] vo ={};
//		final String strProcName = RegistrationDaoConfig.PROCEDURE_PRICAT_CHANGE_VIEW;
//		final int nProcedureIndex;
//		final String strDbErr;
//		WebRowSet webRowSet = null;
//		Sequence sq = new Sequence();
//		ResultSet rs=null;
//		HisDAO hisDAO_p = null;
//		
//		try
//		
//		{
//			hisDAO_p = new HisDAO("Registration","PrimaryCategoryChangeDAO");
//			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
//			/* Setting and Registering In and Out Parameters */
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_crno",crNo,2);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",_userVO.getHospitalCode(),3);
//			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);			
//
//			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 5);
//			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,6); 
//			
//			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
//			
//			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
//			rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");
//
//			/* If Database Error Occurs, No farther processing is required. */
//			if (strDbErr != null && !strDbErr.equals("")) {
//				throw new Exception("Data Base Error:" + strDbErr);
//			}
//
//		}
//		catch(HisRecordNotFoundException e)
//		{	
//			throw new HisRecordNotFoundException(e.getMessage());
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();			
//		}
//		
//		
//		try
//		{
//			if (!rs.next())
//			{
//				throw new HisRecordNotFoundException("No previous change detail found");
//			}
//			rs.beforeFirst();
//			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
//			vo = HelperMethods.populateVOfrmRS(PrimaryCategoryChangeVO.class, rs);
//			System.out.println("length" + vo.length);
//			_primaryCategoryChangeVO = new PrimaryCategoryChangeVO[vo.length];
//			for (int i = 0; i < vo.length; i++)
//			{
//				_primaryCategoryChangeVO[i] = (PrimaryCategoryChangeVO) vo[i];
//				}
//			
//		}
//		catch (Exception e)
//		{
//			if (e.getClass() == HisRecordNotFoundException.class)
//			{
//				throw new HisRecordNotFoundException(e.getMessage());
//			}
//			else throw new HisDataAccessException("PrimaryCategoryChangeDAO:getChangePrimaryCategoryDetail():: " + e);
//		}
//		return _primaryCategoryChangeVO;
//	}
	
	public PrimaryCategoryChangeVO[] getpatcatchangeLog(String crNo,UserVO objUserVO_p,PrimaryCategoryChangeVO catchangevo)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_reg_view.proc_hrgt_pricat_change_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		PrimaryCategoryChangeVO[] objPrimaryCategoryChangeVO;
		try
		{
			System.out.println("PrimaryCtegoryChangeDAO :: getpatcatchangeLog()");
			
			daoObj = new HisDAO("Registration", "EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_crno",crNo, 1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_changetye","2",3);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");


			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			System.out.println("refVo size :"+webRs!=null? webRs.size():0);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		ValueObject[] valueObject = {};
		try
		{
			webRs.beforeFirst();
			valueObject = HelperMethods.populateVOfrmRS(PrimaryCategoryChangeVO.class, webRs);
			objPrimaryCategoryChangeVO=new PrimaryCategoryChangeVO[valueObject.length];
			
			for(int i=0;i<valueObject.length;i++)
			{
				objPrimaryCategoryChangeVO[i]=(PrimaryCategoryChangeVO)valueObject[i];
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objPrimaryCategoryChangeVO;
		
		
		
	}
	//By Mukund on 20.09.2016 for auditLog
	public List getauditLog(String crNo,UserVO objUserVO_p,PrimaryCategoryChangeVO catchangevo)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_reg_view.proc_audit_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		PrimaryCategoryChangeVO[] objPrimaryCategoryChangeVO;
		List alRecord = new ArrayList();

		try
		{
			System.out.println("PrimaryCtegoryChangeDAO :: getpatcatchangeLog()");
			
			daoObj = new HisDAO("Registration", "EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_crno",crNo, 1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_changetye","",3);//need attention by Mukund
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");


			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			System.out.println("refVo size :"+webRs!=null? webRs.size():0);

		
		if (!webRs.next()){
			//throw new HisRecordNotFoundException("Unit List Not Found");
		}
		else{
			//rs.beforeFirst();
			alRecord=HelperMethodsDAO.getAlOfResultSet(webRs);
		}
		}
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
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
		
		
		
	}//End:Mukund
	
}
