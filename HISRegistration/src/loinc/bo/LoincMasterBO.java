package loinc.bo;

/**
 * Created By 	: Sheeldarshi
 * Date			: 26thSep'14
 */
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.UserVO;

import java.util.List;

import registration.dao.LocationDAO;
import vo.registration.LocationVO;
import loinc.dao.LoincDAO;
import registration.dao.RoomDAO;
import registration.dao.UnitDAO;
import vo.registration.CounterVO;
import vo.registration.DepartmentVO;
import vo.registration.DeptUnitRoomVO;
import vo.registration.DisclaimerVO;
import vo.registration.EmgDeathMstVO;
import vo.registration.EmgMlcCaseTypeVO;
import vo.registration.EmgPatStatusVO;
import vo.registration.ExtInstituteVO;
import vo.registration.OccupationVO;
import vo.registration.PatCatDocVO;
import vo.registration.PatCategoryVO;
import vo.registration.PoliceStationMstVO;
import vo.registration.RenewalConfigVO;
import vo.registration.RosterMasterVO;
import vo.registration.ShiftVO;
import vo.registration.UOMVO;
import vo.registration.UnitConsultantVO;
import vo.registration.VerificationDocVO;
import vo.registration.RoomVO;
import vo.registration.UnitVO;
import vo.registration.LocationVO;
import loinc.vo.LoincVO;



public class LoincMasterBO {

		public List getLoincTestName(UserVO uservo) 
	{
		HisDAO hisDAO = new HisDAO("LOINC","LOINCDAO");
		List lstLocationType=null;
		try
		{
			lstLocationType=LoincDAO.getLoincTestName(hisDAO,uservo);		
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			throw new HisApplicationExecutionException();
		}

		catch(HisDataAccessException e){		
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			throw new HisApplicationExecutionException();
		}
		return lstLocationType;
	}
		
		public List getTestParaName_AJAX(UserVO _userVO, String strTestCode) 
		{
			
			List lstLocationType=null;
			

			JDBCTransactionContext tx = new JDBCTransactionContext();
			try {
				tx.begin();
			
				HisDAO hisDAO = new HisDAO("LOINC","LOINCDAO");
				lstLocationType=LoincDAO.getLoincTestParaNameNew(_userVO,hisDAO,strTestCode);	
				
				
			}

			catch (HisRecordNotFoundException e) {
				tx.rollback();
				throw new HisRecordNotFoundException(e.getMessage());
			} catch (HisApplicationExecutionException e) {
				tx.rollback();
				e.printStackTrace();
				throw new HisApplicationExecutionException();
			}

			catch (HisDataAccessException e) {
				tx.rollback();
				e.printStackTrace();
				throw new HisDataAccessException();
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
				throw new HisApplicationExecutionException();
			} finally {
				tx.close();
			}
			return lstLocationType;
		}
	
		public List getLoincTestSample(UserVO uservo, String strTestCode, String strTestType) 
	{
		HisDAO hisDAO = new HisDAO("LOINC","LOINCDAO");
		List lstTestSample=null;
		try
		{
			lstTestSample=LoincDAO.getLoincTestSample(hisDAO,uservo,strTestCode,strTestType);		
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			throw new HisApplicationExecutionException();
		}

		catch(HisDataAccessException e){		
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			throw new HisApplicationExecutionException();
		}
		return lstTestSample;
	}
	
		public List GetPropertyCombo(UserVO uservo) 
	{
		HisDAO hisDAO = new HisDAO("LOINC","LOINCDAO");
		List lstLocationType=null;
		try
		{
			lstLocationType=LoincDAO.GetPropertyCombo(hisDAO,uservo);		
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			throw new HisApplicationExecutionException();
		}

		catch(HisDataAccessException e){		
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			throw new HisApplicationExecutionException();
		}
		return lstLocationType;
	}
	
	public LoincVO[] searchSuggestiveLoinc(LoincVO loincVO, UserVO userVO) 

	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		LoincVO[]  lstLocationType= null;
		try {
			tx.begin();
			
			HisDAO hisDAO = new HisDAO("LOINC","LOINCDAO");
			lstLocationType=LoincDAO.searchSuggestiveLoinc(loincVO,userVO);	
			

		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstLocationType;
	}	
	
	public LoincVO[] searchLoinc(LoincVO loincVO, UserVO userVO) 

	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		LoincVO[]  lstLocationType= null;
		try {
			tx.begin();
			
			HisDAO hisDAO = new HisDAO("LOINC","LOINCDAO");
			lstLocationType=LoincDAO.searchLoinc(loincVO,userVO);	
			

		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstLocationType;
	}	
	
		//** To Save the Location Master Details, Added by Singaravelan on 02-Jan-14**//*	
	public boolean saveLoincDtl(LoincVO objModelLoc_p,String strMode_p,UserVO uservo) 
	{	
		HisDAO hisDAO =new HisDAO("LOINC","LOINCDAO");
		boolean bExistStatus=LoincDAO.chkLoincPresent(objModelLoc_p,strMode_p,hisDAO,uservo);

		if (objModelLoc_p.getStrMsgType()!=null && objModelLoc_p.getStrMsgType().equals("1")) 
		{
			String strErr = objModelLoc_p.getStrMsgString();
			objModelLoc_p.setStrMsgString("UnitMstBO.insertQuery() --> " + strErr);
		}
		if (bExistStatus == false) 
		{			
			LoincDAO.saveLoincDetails(objModelLoc_p,strMode_p,hisDAO,uservo);
			if (objModelLoc_p.getStrMsgType()!=null && objModelLoc_p.getStrMsgType().equals("1")) 
			{
				String strErr = objModelLoc_p.getStrMsgString();
				objModelLoc_p.setStrMsgString("UnitMstBO.insertQuery() --> " + strErr);
			}
		}
		return bExistStatus;
	}
	
	public boolean updateLoincDtl(LoincVO objModelLoc_p,UserVO uservo ) {
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		boolean bExistStatus=true;
		//boolean bExistStatus=LocationDAO.chkLocationDuplicate(objModelLoc_p,hisDAO,uservo);

		//if (objModelLoc_p.getStrMsgType()!=null && objModelLoc_p.getStrMsgType().equals("1")) {

			//String strErr = objModelLoc_p.getStrMsgString();

			//objModelLoc_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
		//}
		if (bExistStatus == true) {
			LoincDAO.updateLoincDetails(objModelLoc_p,hisDAO, uservo );

			//if (objModelLoc_p.getStrMsgType()!=null && objModelLoc_p.getStrMsgType().equals("1")) {

			//	String strErr = objModelLoc_p.getStrMsgString();

			//	objModelLoc_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
			}
		
		return bExistStatus;
	}
	public List GetValuesForSelectedCheckbox(LoincVO objModelLoc_p,UserVO uservo ) {

		HisDAO hisDAO = new HisDAO("LOINC","LOINCDAO");
		List lstCheckboxValues= LoincDAO.GetValuesForSelectedCheckbox(objModelLoc_p,hisDAO, uservo );
		
		return lstCheckboxValues;
	}
	
}
