package registration.bo;

/**
 * Created By 	: Aadil Wasi
 * Date			: Dec 2013
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;
import registration.config.RegistrationConfig;
import registration.dao.CounterMstDAO;
import registration.dao.DepartmentDAO;
import registration.dao.DeptUnitRoomDAO;
import registration.dao.DeptUnitRosterDAO;
import registration.dao.DisclaimerMstDAO;
import registration.dao.EmgCaseDAO;
import registration.dao.EmgDeathDAO;
import registration.dao.EmgMlcCaseTypeDAO;
import registration.dao.EmgPatStatusDAO;
import registration.dao.ExtInstituteDAO;
import registration.dao.OccupationDAO;
import registration.dao.PatCatDocMapMstDAO;
import registration.dao.PatCategoryDAO;
import registration.dao.PoliceStationDAO;
import registration.dao.RegEssentialDAO;
import registration.dao.RegistrationConfigDAO;
import registration.dao.RenewalConfigDAO;
import registration.dao.ShiftDAO;
import registration.dao.UOMMstDAO;
import registration.dao.UnitConsultantDAO;
import registration.dao.VerificationDocDAO;
import registration.dao.LocationDAO;
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
import vo.registration.RegistrationConfigMstVO;
import vo.registration.RenewalConfigVO;
import vo.registration.RosterMasterVO;
import vo.registration.ShiftVO;
import vo.registration.UOMVO;
import vo.registration.UnitConsultantVO;
import vo.registration.VerificationDocVO;
import vo.registration.RoomVO;
import vo.registration.UnitVO;
import vo.registration.LocationVO;
import vo.registration.EmgCaseVO;



public class RegMasterBO {

	public boolean saveOccupationDtl(OccupationVO objVOOccupation_p,
			String strMode_p, UserVO userVo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		//boolean bExistStatus = true;
		boolean bExistStatus=OccupationDAO.chkOccupationDuplicate(objVOOccupation_p,strMode_p,hisDAO,userVo);

		if (objVOOccupation_p.getStrMsgType() != null
				&& objVOOccupation_p.getStrMsgType().equals("1")) {

			String strErr = objVOOccupation_p.getStrMsgString();

			objVOOccupation_p
					.setStrMsgString("CurrencyMstBO.insertQuery() --> "
							+ strErr);
		}
		if (bExistStatus == true) {
			OccupationDAO.saveOccupationDetails(objVOOccupation_p, strMode_p,
					hisDAO, userVo);

			if (objVOOccupation_p.getStrMsgType() != null
					&& objVOOccupation_p.getStrMsgType().equals("1")) {

				String strErr = objVOOccupation_p.getStrMsgString();

				objVOOccupation_p
						.setStrMsgString("CurrencyMstBO.insertQuery() --> "
								+ strErr);
			}
		}
		return bExistStatus;
	}

	public boolean updateOccupationDtl(OccupationVO objVOOccupation_p,
			String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");

		hisDAO = new HisDAO("Reg", "regbo");
		//boolean bExistStatus = true;
		boolean bExistStatus =OccupationDAO.chkOccupationDuplicate(objVOOccupation_p,strMode_p,hisDAO,uservo);

		if (objVOOccupation_p.getStrMsgType() != null
				&& objVOOccupation_p.getStrMsgType().equals("1")) {

			String strErr = objVOOccupation_p.getStrMsgString();

			objVOOccupation_p
					.setStrMsgString("CurrencyMstBO.insertQuery() --> "
							+ strErr);
		}
		if (bExistStatus == true) {
			OccupationDAO
					.updateRequestStatus(objVOOccupation_p, hisDAO, uservo);

			if (objVOOccupation_p.getStrMsgType() != null
					&& objVOOccupation_p.getStrMsgType().equals("1")) {

				String strErr = objVOOccupation_p.getStrMsgString();

				objVOOccupation_p
						.setStrMsgString("CurrencyMstBO.insertQuery() --> "
								+ strErr);
			}
		}
		return bExistStatus;
	}

	/**
	 * to get data for modify page.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 */
	public OccupationVO modifyRecordOccupationMst(OccupationVO vo) {
		OccupationVO OccupationVO_p = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			OccupationVO_p = OccupationDAO.modifyRecord(vo, hisDAO);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("regMasterBO.modifyRecord(vo) --> "
						+ vo.getStrMsgString());
			}
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return OccupationVO_p;
	}

	/**
	 * To Save the Verification Document Details, Added by Singaravelan on
	 * 20-Dec-13
	 **/
	public boolean saveVerificationDocDtl(VerificationDocVO objModelDoc_p,
			String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = VerificationDocDAO.chkVerificationDocDuplicate(
				objModelDoc_p, strMode_p, hisDAO, uservo);
		// boolean bExistStatus=true;

		if (objModelDoc_p.getStrMsgType() != null
				&& objModelDoc_p.getStrMsgType().equals("1")) {
			String strErr = objModelDoc_p.getStrMsgString();
			objModelDoc_p
					.setStrMsgString("VerificationDocMstBO.insertQuery() --> "
							+ strErr);
		}
		if (bExistStatus == true) {
			VerificationDocDAO.saveVerificationDocDetails(objModelDoc_p,
					strMode_p, hisDAO, uservo);
			if (objModelDoc_p.getStrMsgType() != null
					&& objModelDoc_p.getStrMsgType().equals("1")) {
				String strErr = objModelDoc_p.getStrMsgString();
				objModelDoc_p
						.setStrMsgString("VerificationDocMstBO.insertQuery() --> "
								+ strErr);
			}
		}
		return bExistStatus;
	}

	/**
	 * to get data for modify page.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 */
	public RenewalConfigVO[] getRenewalConfigDtl(UserVO vo) {
		RenewalConfigVO[] arrRenewalConfigVO = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			arrRenewalConfigVO = RenewalConfigDAO
					.getHospitalSpecificRenewalDetails(vo, hisDAO, "1");

		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return arrRenewalConfigVO;
	}

	/** To get Department Location Details, Added by Singaravelan on 23-Dec-13 **/
	public List getLocation(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstLocation = null;
		try {
			lstLocation = DepartmentDAO.getLocation(hisDAO, uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstLocation;
	}

	/** To get Department Type Details, Added by Singaravelan on 23-Dec-13 **/
	public List getDeptType(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstDeptType = null;
		try {
			lstDeptType = DepartmentDAO.getDepartmentType(hisDAO, uservo);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstDeptType;

	}

	/**
	 * To Save the Verification Document Details, Added by Singaravelan on
	 * 20-Dec-13
	 **/
	public void saveRenewalConfigDtl(List<RenewalConfigVO> lstRenewalConfigVO_p,
			String strMode_p, UserVO uservo) {
		try {
			System.out.println("RegMasterBO :: saveRenewalConfigDtl()");
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			RenewalConfigDAO.saveRenewalConfigDetails(lstRenewalConfigVO_p.get(0),"3", hisDAO, uservo,"1");
			for (int i = 0; i < lstRenewalConfigVO_p.size(); i++){
				System.out.println("index :"+i);
				RenewalConfigDAO.saveRenewalConfigDetails(lstRenewalConfigVO_p.get(i),
						strMode_p, hisDAO, uservo,"1");
			}
			
			/***********************************************/	
			synchronized (hisDAO) {
				hisDAO.fire();
			}

		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}

	}

	/**
	 * To Save the Department Master Details, Added by Singaravelan on 23-Dec-13
	 **/
	public boolean saveDepartmentDtl(DepartmentVO objModelDept_p,
			String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = DepartmentDAO.chkDepartmentDuplicate(
				objModelDept_p, strMode_p, hisDAO, uservo);
		// boolean bExistStatus=true;

		if (objModelDept_p.getStrMsgType() != null
				&& objModelDept_p.getStrMsgType().equals("1")) {
			String strErr = objModelDept_p.getStrMsgString();
			objModelDept_p
					.setStrMsgString("VerificationDocMstBO.insertQuery() --> "
							+ strErr);
		}
		if (bExistStatus == true) {
			DepartmentDAO.saveDepartmentDetails(objModelDept_p, strMode_p,
					hisDAO, uservo);
			if (objModelDept_p.getStrMsgType() != null
					&& objModelDept_p.getStrMsgType().equals("1")) {
				String strErr = objModelDept_p.getStrMsgString();
				objModelDept_p
						.setStrMsgString("VerificationDocMstBO.insertQuery() --> "
								+ strErr);
			}
		}
		return bExistStatus;
	}

	/** To get Department Location Details, Added by Singaravelan on 27-Dec-13 **/
	public Map getGlobalDept(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		Map essentialMap = new HashMap();
		List globalDepartmentList=new ArrayList();
		DepartmentVO[] deptObj_p=null;
		List lstLocation = null;
		try {
			deptObj_p = DepartmentDAO.getGlobalDept(hisDAO, uservo);
			essentialMap.put("globalDeptArray", deptObj_p);
			
			for(int i=0;i<deptObj_p.length;i++)
			{
				Entry objEntry=new Entry();
				objEntry.setLabel(deptObj_p[i].getStrDeptName());
				objEntry.setValue(deptObj_p[i].getStrDeptCode());
				globalDepartmentList.add(objEntry);				
			}
			essentialMap.put("globalDeptList", globalDepartmentList);			
			
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return essentialMap;
	}

	/** To get Department Location Details, Added by Singaravelan on 27-Dec-13 **/
	public List getHeadOfDept(String desid_id, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstLocation = null;
		try {
			lstLocation = DepartmentDAO.getHeadOfDept(desid_id, hisDAO, uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstLocation;
	}

	/** To show Department Details in Modify,Added by Singaravelan on 27-Dec-13 **/
	public DepartmentVO modifyRecordDepartmentMst(DepartmentVO objModelDept_p) {
		DepartmentVO DepartmentVO_p = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			DepartmentVO_p = DepartmentDAO
					.modifyDetails(objModelDept_p, hisDAO);

			if (objModelDept_p.getStrMsgType().equals("1")) {
				objModelDept_p
						.setStrMsgString("regMasterBO.modifyRecord(vo) --> "
								+ objModelDept_p.getStrMsgString());
			}
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return DepartmentVO_p;
	}

	/** To Update the Department Details,Added by Singaravelan on 27-Dec-13 **/
	public boolean updateDepartmentDtl(DepartmentVO objModelDept_p,
			String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		//boolean bExistStatus = true;
		boolean bExistStatus = DepartmentDAO.chkDepartmentDuplicate(objModelDept_p, strMode_p, hisDAO, uservo);

		if (objModelDept_p.getStrMsgType() != null
				&& objModelDept_p.getStrMsgType().equals("1")) {

			String strErr = objModelDept_p.getStrMsgString();

			objModelDept_p.setStrMsgString("regMasterBO.updateQuery() --> "
					+ strErr);
		}
		if (bExistStatus == true) {
			DepartmentDAO.updateDepartmentDetails(objModelDept_p, hisDAO,
					uservo);

			if (objModelDept_p.getStrMsgType() != null
					&& objModelDept_p.getStrMsgType().equals("1")) {

				String strErr = objModelDept_p.getStrMsgString();

				objModelDept_p.setStrMsgString("regMasterBO.updateQuery() --> "
						+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To Save the Unit Master Details, Added by Singaravelan on 31-Dec-13**/	
	public boolean saveUnitDtl(UnitVO objModelUnit_p,String strMode_p,UserVO uservo) 
	{
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus=UnitDAO.chkUnitDuplicate(objModelUnit_p,strMode_p,hisDAO,uservo);
		//boolean bExistStatus=true;

		if (objModelUnit_p.getStrMsgType()!=null && objModelUnit_p.getStrMsgType().equals("1")) 
		{
			String strErr = objModelUnit_p.getStrMsgString();
			objModelUnit_p.setStrMsgString("UnitMstBO.insertQuery() --> " + strErr);
		}
		if (bExistStatus == true) 
		{			
			UnitDAO.saveUnitDetails(objModelUnit_p,strMode_p,hisDAO,uservo);
			if (objModelUnit_p.getStrMsgType()!=null && objModelUnit_p.getStrMsgType().equals("1")) 
			{
				String strErr = objModelUnit_p.getStrMsgString();
				objModelUnit_p.setStrMsgString("UnitMstBO.insertQuery() --> " + strErr);
			}
		}
		return bExistStatus;
	}
	
	/**To show Unit Details in Modify,Added by Singaravelan on 01-Jan-14 **/
	public UnitVO modifyRecordUnitMst(UnitVO objModelUnit_p)
	{
		UnitVO UnitVO_p = null;
		try
		{
			HisDAO hisDAO = new HisDAO("Reg","regbo");
			UnitVO_p=UnitDAO.modifyDetails(objModelUnit_p,hisDAO);

			if(objModelUnit_p.getStrMsgType().equals("1"))
			{
				objModelUnit_p.setStrMsgString("regMasterBO.modifyRecord(vo) --> " + objModelUnit_p.getStrMsgString());
			}
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
		finally
		{
		}
		return UnitVO_p;
	}
	
	/**To show Unit Renewal Details in Modify,Added by Singaravelan on 01-Jan-14 **/
	public UnitVO modifyRenewalUnitMst(UnitVO objModelUnit_p)
	{
		try
		{
			HisDAO hisDAO = new HisDAO("Reg","regbo");
			objModelUnit_p=UnitDAO.renewalDetails(objModelUnit_p,hisDAO);

			if(objModelUnit_p.getStrMsgType().equals("1"))
			{
				objModelUnit_p.setStrMsgString("regMasterBO.modifyRecord(vo) --> " + objModelUnit_p.getStrMsgString());
			}
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
		finally
		{
		}
		return objModelUnit_p;
	}
	
	/**To Update the Unit Details,Added by Singaravelan on 01-Jan-14 **/
	public boolean updateUnitDtl(UnitVO objModelUnit_p,String strMode_p,UserVO uservo ) {
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		//boolean bExistStatus=true;
		boolean bExistStatus=UnitDAO.chkUnitDuplicate(objModelUnit_p,strMode_p,hisDAO,uservo);

		if (objModelUnit_p.getStrMsgType()!=null && objModelUnit_p.getStrMsgType().equals("1")) {

			String strErr = objModelUnit_p.getStrMsgString();

			objModelUnit_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
		}
		if (bExistStatus == true) {
			UnitDAO.updateUnitDetails(objModelUnit_p,hisDAO, uservo );

			if (objModelUnit_p.getStrMsgType()!=null && objModelUnit_p.getStrMsgType().equals("1")) {

				String strErr = objModelUnit_p.getStrMsgString();

				objModelUnit_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To get Loaction Type Details, Added by Singaravelan on 02-Jan-14**/	
	public List getLocationType(UserVO uservo) 
	{
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		List lstLocationType=null;
		try
		{
			lstLocationType=LocationDAO.getLocationType(hisDAO,uservo);		
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

	/** To Save the Location Master Details, Added by Singaravelan on 02-Jan-14**/	
	public boolean saveLocationDtl(LocationVO objModelLoc_p,String strMode_p,UserVO uservo) 
	{
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		boolean bExistStatus=LocationDAO.chkLocationDuplicate(objModelLoc_p,strMode_p,hisDAO,uservo);

		if (objModelLoc_p.getStrMsgType()!=null && objModelLoc_p.getStrMsgType().equals("1")) 
		{
			String strErr = objModelLoc_p.getStrMsgString();
			objModelLoc_p.setStrMsgString("UnitMstBO.insertQuery() --> " + strErr);
		}
		if (bExistStatus == true) 
		{			
			LocationDAO.saveLocationDetails(objModelLoc_p,strMode_p,hisDAO,uservo);
			if (objModelLoc_p.getStrMsgType()!=null && objModelLoc_p.getStrMsgType().equals("1")) 
			{
				String strErr = objModelLoc_p.getStrMsgString();
				objModelLoc_p.setStrMsgString("UnitMstBO.insertQuery() --> " + strErr);
			}
		}
		return bExistStatus;
	}
	
	/**To show Location Details in Modify,Added by Singaravelan on 02-Jan-14 **/
	public LocationVO modifyRecordLocationMst(LocationVO objModelLoc_p)
	{
		LocationVO LocationVO_p = null;
		try
		{
			HisDAO hisDAO = new HisDAO("Reg","regbo");
			LocationVO_p=LocationDAO.modifyDetails(objModelLoc_p,hisDAO);

			if(objModelLoc_p.getStrMsgType().equals("1"))
			{
				objModelLoc_p.setStrMsgString("regMasterBO.modifyRecord(vo) --> " + objModelLoc_p.getStrMsgString());
			}
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
		finally
		{
		}
		return LocationVO_p;
	}
	
	/**To Update the Location Details,Added by Singaravelan on 02-Jan-14 **/
	public boolean updateLocationDtl(LocationVO objModelLoc_p,String strMode_p,UserVO uservo ) {
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		//boolean bExistStatus=true;
		boolean bExistStatus=LocationDAO.chkLocationDuplicate(objModelLoc_p,strMode_p,hisDAO,uservo);

		if (objModelLoc_p.getStrMsgType()!=null && objModelLoc_p.getStrMsgType().equals("1")) {

			String strErr = objModelLoc_p.getStrMsgString();

			objModelLoc_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
		}
		if (bExistStatus == true) {
			LocationDAO.updateLocationDetails(objModelLoc_p,hisDAO, uservo );

			if (objModelLoc_p.getStrMsgType()!=null && objModelLoc_p.getStrMsgType().equals("1")) {

				String strErr = objModelLoc_p.getStrMsgString();

				objModelLoc_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To Save the Room Master Details, Added by Singaravelan on 02-Jan-14**/	
	public boolean saveRoomDtl(RoomVO objModelRoom_p,String strMode_p,UserVO uservo) 
	{
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		boolean bExistStatus=RoomDAO.chkRoomDuplicate(objModelRoom_p,strMode_p,hisDAO,uservo);

		if (objModelRoom_p.getStrMsgType()!=null && objModelRoom_p.getStrMsgType().equals("1")) 
		{
			String strErr = objModelRoom_p.getStrMsgString();
			objModelRoom_p.setStrMsgString("UnitMstBO.insertQuery() --> " + strErr);
		}
		if (bExistStatus == true) 
		{			
			RoomDAO.saveRoomDetails(objModelRoom_p,strMode_p,hisDAO,uservo);
			if (objModelRoom_p.getStrMsgType()!=null && objModelRoom_p.getStrMsgType().equals("1")) 
			{
				String strErr = objModelRoom_p.getStrMsgString();
				objModelRoom_p.setStrMsgString("UnitMstBO.insertQuery() --> " + strErr);
			}
		}
		return bExistStatus;
	}
	
	/**To show Room Details in Modify,Added by Singaravelan on 02-Jan-14 **/
	public RoomVO modifyRecordRoomMst(RoomVO objModelRoom_p)
	{
		RoomVO RoomVO_p = null;
		try
		{
			HisDAO hisDAO = new HisDAO("Reg","regbo");
			RoomVO_p=RoomDAO.modifyDetails(objModelRoom_p,hisDAO);

			if(objModelRoom_p.getStrMsgType().equals("1"))
			{
				objModelRoom_p.setStrMsgString("regMasterBO.modifyRecord(vo) --> " + objModelRoom_p.getStrMsgString());
			}
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
		finally
		{
		}
		return RoomVO_p;
	}
	
	/**To Update the Room Details,Added by Singaravelan on 02-Jan-14 **/
	public boolean updateRoomDtl(RoomVO objModelRoom_p,String strMode_p,UserVO uservo ) {
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		//boolean bExistStatus=true;
		boolean bExistStatus=RoomDAO.chkRoomDuplicate(objModelRoom_p,strMode_p,hisDAO,uservo);

		if (objModelRoom_p.getStrMsgType()!=null && objModelRoom_p.getStrMsgType().equals("1")) {

			String strErr = objModelRoom_p.getStrMsgString();

			objModelRoom_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
		}
		if (bExistStatus == true) {
			RoomDAO.updateRoomDetails(objModelRoom_p,hisDAO, uservo );

			if (objModelRoom_p.getStrMsgType()!=null && objModelRoom_p.getStrMsgType().equals("1")) {

				String strErr = objModelRoom_p.getStrMsgString();

				objModelRoom_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
			}
		}
		return bExistStatus;
	}


	/** To Save the Patient Category Master Details, Added by Singaravelan on 07-Jan-14**/	
	public boolean savePatCategoryDtl(PatCategoryVO objModelPatCat_p,String strMode_p,UserVO uservo) 
	{
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		boolean bExistStatus=PatCategoryDAO.chkPatCategoryDuplicate(objModelPatCat_p,strMode_p,hisDAO,uservo);

		if (objModelPatCat_p.getStrMsgType()!=null && objModelPatCat_p.getStrMsgType().equals("1")) 
		{
			String strErr = objModelPatCat_p.getStrMsgString();
			objModelPatCat_p.setStrMsgString("ernBO.insertQuery() --> " + strErr);
		}
		if (bExistStatus == true) 
		{			
			PatCategoryDAO.savePatCategoryDetails(objModelPatCat_p,strMode_p,hisDAO,uservo);
			if (objModelPatCat_p.getStrMsgType()!=null && objModelPatCat_p.getStrMsgType().equals("1")) 
			{
				String strErr = objModelPatCat_p.getStrMsgString();
				objModelPatCat_p.setStrMsgString("UnitMstBO.insertQuery() --> " + strErr);
			}
		}
		return bExistStatus;
	}
	
	/**To show Patient Category Master Details in Modify,Added by Singaravelan on 07-Jan-14 **/
	public PatCategoryVO modifyRecordPatCategoryMst(PatCategoryVO objModelPatCat_p)
	{
		PatCategoryVO PatCategoryVO_p = null;
		try
		{
			HisDAO hisDAO = new HisDAO("Reg","regbo");
			PatCategoryVO_p=PatCategoryDAO.modifyDetails(objModelPatCat_p,hisDAO);

			if(objModelPatCat_p.getStrMsgType().equals("1"))
			{
				objModelPatCat_p.setStrMsgString("regMasterBO.modifyRecord(vo) --> " + objModelPatCat_p.getStrMsgString());
			}
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
		finally
		{
		}
		return PatCategoryVO_p;
	}
	
	/**To Update the Patient Category Master Details,Added by Singaravelan on 07-Jan-14 **/
	public boolean updatePatCategoryDtl(PatCategoryVO objModelPatCat_p,String strMode_p,UserVO uservo ) {
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		//boolean bExistStatus=true;
		boolean bExistStatus=PatCategoryDAO.chkPatCategoryDuplicate(objModelPatCat_p,strMode_p,hisDAO,uservo);

		if (objModelPatCat_p.getStrMsgType()!=null && objModelPatCat_p.getStrMsgType().equals("1")) {

			String strErr = objModelPatCat_p.getStrMsgString();

			objModelPatCat_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
		}
		if (bExistStatus == true) {
			PatCategoryDAO.updatePatCategoryDetails(objModelPatCat_p,hisDAO, uservo );

			if (objModelPatCat_p.getStrMsgType()!=null && objModelPatCat_p.getStrMsgType().equals("1")) {

				String strErr = objModelPatCat_p.getStrMsgString();

				objModelPatCat_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To get Global Patient Category Details, Added by Singaravelan on 08-Jan-14 **/
	public List getGlobalPatCategory(UserVO uservo, String strMode_p) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstPatCategory = null;
		try {
			lstPatCategory = PatCategoryDAO.getGlobalPatCategory(hisDAO, uservo,strMode_p);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstPatCategory;
	}
	
	public List getVerificationDocuments(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstVerificationDoc = null;
		try {
			lstVerificationDoc = VerificationDocDAO.getVerificationDocuments(uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		}catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstVerificationDoc;
	}
	/*Start: Surabhi
	 * reason: for adding the organisation name in credit category
	 * date : 28-7-2016*/
	public List getClients(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstClient = null;
		try {
			lstClient = RegEssentialDAO.getClient(uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		}catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstClient;
	}
	//End
	/** To List Genders, Added by Singaravelan on 08-Jan-14 **/
	public List getGenderList(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstLocation = null;
		try {
			lstLocation = DepartmentDAO.getGenderList(hisDAO,uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstLocation;
	}
	
	/** To List Room Details in DeptUnitRoomMst, Added by Singaravelan on 08-Jan-14 **/
	public List getRoomsList(DeptUnitRoomVO objModelDeptUnitRoom_p,UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstLocation = null;
		try {
			lstLocation = DeptUnitRoomDAO.getRoomsList(hisDAO,objModelDeptUnitRoom_p,uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstLocation;
	}
	
	/** To Save Room Details in DeptUnitRoomMst, Added by Singaravelan on 11-Jan-14 **/
	public boolean saveDeptUnitRoomDtl(DeptUnitRoomVO[] objModelDeptUnitRoom_p,String strMode_p, UserVO uservo) {
		
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		//boolean bExistStatus=PatCategoryDAO.chkPatCategoryDuplicate(_deptUnitRoomMasterVO,strMode_p,hisDAO,uservo);
		boolean bExistStatus=true;

	    //Collection col=DeptUnitRoomDAO.getDeletedOrUpdatedRecords(hisDAO,uservo);		 

	    for(int i=0;i<objModelDeptUnitRoom_p.length;i++){			
		//	   if(col.contains(objModelDeptUnitRoom_p[i])){
				   //delete the record and then insert 
		//		   DeptUnitRoomDAO.deleteDeptUnitRoomDtl(objModelDeptUnitRoom_p[i],strMode_p,hisDAO,uservo);
		//		   DeptUnitRoomDAO.createDeptUnitRoomDtl(objModelDeptUnitRoom_p[i],strMode_p,hisDAO,uservo);
		//	   }
		//	   else{			
				   DeptUnitRoomDAO.createDeptUnitRoomDtl(objModelDeptUnitRoom_p[i],strMode_p,hisDAO,uservo);
		//	   }	
		}
	    
	    return bExistStatus;
	}

	/** To Get Already Alloted Rooms in DeptUnitRoomMst, Added by Singaravelan on 13-Jan-14 **/
	public DeptUnitRoomVO[] getAllotedRoomsToUnitsWithSequence(String _deptUnitCode, UserVO uservo) {
	
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		DeptUnitRoomVO[] departmentUnitRoomMasterVO=null;
		try {
			departmentUnitRoomMasterVO = DeptUnitRoomDAO.getAllotedRoomsToUnitsWithSequence(hisDAO,_deptUnitCode,uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return departmentUnitRoomMasterVO;			
	}
	
	/** To show Department Unit Room Details in Modify,Added by Singaravelan on 13-Dec-13 **/
	public DeptUnitRoomVO modifyRecordDeptUnitRoomMst(DeptUnitRoomVO objModelDeptUnitRoom_p) {

		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		DeptUnitRoomVO DeptUnitRoomVO_p = null;
		try {
			DeptUnitRoomVO_p = DeptUnitRoomDAO.modifyDetails(objModelDeptUnitRoom_p, hisDAO);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return DeptUnitRoomVO_p;
	}
	
	/** To Update the Department Unit Room Details,Added by Singaravelan on 15-Dec-13 **/
	public boolean updateDeptUnitRoomDtl(DeptUnitRoomVO objModelDept_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = true;
		// OccupationDAO.chkOccupationDuplicate(objVOOccupation_p,"2");

		if (objModelDept_p.getStrMsgType() != null
				&& objModelDept_p.getStrMsgType().equals("1")) {

			String strErr = objModelDept_p.getStrMsgString();
			objModelDept_p.setStrMsgString("regMasterBO.updateQuery() --> "
					+ strErr);
		}
		if (bExistStatus == true) {
			DeptUnitRoomDAO.updateDeptUnitRoomDetails(objModelDept_p, strMode_p, hisDAO, uservo);
			if (objModelDept_p.getStrMsgType() != null
					&& objModelDept_p.getStrMsgType().equals("1")) {

				String strErr = objModelDept_p.getStrMsgString();

				objModelDept_p.setStrMsgString("regMasterBO.updateQuery() --> "
						+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To List Consultant Details in UnitConsultantMst, Added by Singaravelan on 16-Jan-14 **/
	public List getConsultantList(UnitConsultantVO objModelUnitCon_p,UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstLocation = null;
		try {
			lstLocation = UnitConsultantDAO.getConsultantList(hisDAO,objModelUnitCon_p,uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstLocation;
	}
	
	/** To Get Already Alloted Consultants in UnitConsultant Master, Added by Singaravelan on 16-Jan-14 **/
	public UnitConsultantVO[] getAllotedConsultantsToUnits(String _deptUnitCode, UserVO uservo) {
	
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		UnitConsultantVO[] unitConsultantMasterVO=null;
		try {
			unitConsultantMasterVO = UnitConsultantDAO.getAllotedConsultantsToUnits(hisDAO,_deptUnitCode,uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return unitConsultantMasterVO;			
	}
	
	/** To Save Consultant Details in UnitConsultantMst, Added by Singaravelan on 17-Jan-14 **/
	public boolean saveUnitConsultantDtl(UnitConsultantVO[] objModelUnitCon_p,String strMode_p, UserVO uservo) {
		
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		//boolean bExistStatus=PatCategoryDAO.chkPatCategoryDuplicate(_deptUnitRoomMasterVO,strMode_p,hisDAO,uservo);
		boolean bExistStatus=true;

	    for(int i=0;i<objModelUnitCon_p.length;i++){					 		
	    	UnitConsultantDAO.createUnitconsultantDtl(objModelUnitCon_p[i],strMode_p,hisDAO,uservo);
		}	
		    
	    return bExistStatus;
	}
	
	/** To show Unit Consultant Details in Modify,Added by Singaravelan on 17-Dec-13 **/
	public UnitConsultantVO modifyRecordUnitConsultantMst(UnitConsultantVO objModelUnitCon_p) {

		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		UnitConsultantVO UnitConsultantVO_p = null;
		try {
			UnitConsultantVO_p = UnitConsultantDAO.modifyDetails(objModelUnitCon_p, hisDAO);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return UnitConsultantVO_p;
	}
		
	/** To Update the Unit Consultant Details,Added by Singaravelan on 17-Dec-13 **/
	public boolean updateUnitConsultantDtl(UnitConsultantVO[] objModelUnitCon_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = true;
		// OccupationDAO.chkOccupationDuplicate(objVOOccupation_p,"2");

		for(int i=0;i<objModelUnitCon_p.length;i++){	
		if (objModelUnitCon_p[i].getStrMsgType() != null
				&& objModelUnitCon_p[i].getStrMsgType().equals("1")) {

			String strErr = objModelUnitCon_p[i].getStrMsgString();
			objModelUnitCon_p[i].setStrMsgString("regMasterBO.updateQuery() --> "
					+ strErr);
		}
		if (bExistStatus == true) {
			UnitConsultantDAO.updateDeptUnitRoomDetails(objModelUnitCon_p[i], strMode_p, hisDAO, uservo);
			if (objModelUnitCon_p[i].getStrMsgType() != null
					&& objModelUnitCon_p[i].getStrMsgType().equals("1")) {

				String strErr = objModelUnitCon_p[i].getStrMsgString();

				objModelUnitCon_p[i].setStrMsgString("regMasterBO.updateQuery() --> "
						+ strErr);
			}
		}
		}
		return bExistStatus;
	}
	
	 /** To Save the Shift Master Details, Added by Singaravelan on 21-Jan-14 **/
	public boolean saveShiftDtl(ShiftVO objModelShift_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = ShiftDAO.chkShiftDuplicate(objModelShift_p, strMode_p, hisDAO, uservo);
		//boolean bExistStatus=true;

		if (objModelShift_p.getStrMsgType() != null
				&& objModelShift_p.getStrMsgType().equals("1")) {
			String strErr = objModelShift_p.getStrMsgString();
			objModelShift_p
					.setStrMsgString("VerificationDocMstBO.insertQuery() --> "
							+ strErr);
		}
		if (bExistStatus == true) {
			ShiftDAO.saveShiftDetails(objModelShift_p, strMode_p,
					hisDAO, uservo);
			if (objModelShift_p.getStrMsgType() != null
					&& objModelShift_p.getStrMsgType().equals("1")) {
				String strErr = objModelShift_p.getStrMsgString();
				objModelShift_p
						.setStrMsgString("VerificationDocMstBO.insertQuery() --> "
								+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To show Shift Details in Modify,Added by Singaravelan on 21-Jan-14 **/
	public ShiftVO modifyRecordShiftMst(ShiftVO objModelDept_p) {
		ShiftVO ShiftVO_p = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			ShiftVO_p = ShiftDAO.modifyDetails(objModelDept_p, hisDAO);

			if (objModelDept_p.getStrMsgType().equals("1")) {
				objModelDept_p
						.setStrMsgString("regMasterBO.modifyRecord(vo) --> "
								+ objModelDept_p.getStrMsgString());
			}
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return ShiftVO_p;
	}

	/** To Update the Shift Details,Added by Singaravelan on 21-Jan-14 **/
	public boolean updateShiftDtl(ShiftVO objModelExt_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = ShiftDAO.chkShiftDuplicate(objModelExt_p, strMode_p, hisDAO, uservo);
		if (objModelExt_p.getStrMsgType() != null
				&& objModelExt_p.getStrMsgType().equals("1")) {

			String strErr = objModelExt_p.getStrMsgString();

			objModelExt_p.setStrMsgString("regMasterBO.updateQuery() --> "
					+ strErr);
		}
		if (bExistStatus == true) {
			ShiftDAO.updateShiftDetails(objModelExt_p, hisDAO,uservo);

			if (objModelExt_p.getStrMsgType() != null
					&& objModelExt_p.getStrMsgType().equals("1")) {

				String strErr = objModelExt_p.getStrMsgString();

				objModelExt_p.setStrMsgString("regMasterBO.updateQuery() --> "
						+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To List State Details in ExtInstitute, Added by Singaravelan on 22-Jan-14 **/
	public List getStateList(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstLocation = null;
		try {
			lstLocation = ExtInstituteDAO.getStateList(hisDAO,uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstLocation;
	}
	
	 /** To Save the ExtInstitute Master Details, Added by Singaravelan on 22-Jan-14 **/
	public boolean saveExtInstituteDtl(ExtInstituteVO objModelExt_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = ExtInstituteDAO.chkExtInstituteDuplicate(objModelExt_p, strMode_p, hisDAO, uservo);
		//boolean bExistStatus=true;

		if (objModelExt_p.getStrMsgType() != null
				&& objModelExt_p.getStrMsgType().equals("1")) {
			String strErr = objModelExt_p.getStrMsgString();
			objModelExt_p
					.setStrMsgString("VerificationDocMstBO.insertQuery() --> "
							+ strErr);
		}
		if (bExistStatus == true) {
			ExtInstituteDAO.saveExtInstituteDetails(objModelExt_p, strMode_p,
					hisDAO, uservo);
			if (objModelExt_p.getStrMsgType() != null
					&& objModelExt_p.getStrMsgType().equals("1")) {
				String strErr = objModelExt_p.getStrMsgString();
				objModelExt_p
						.setStrMsgString("VerificationDocMstBO.insertQuery() --> "
								+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To show ExtInstitute Details in Modify,Added by Singaravelan on 22-Jan-14 **/
	public ExtInstituteVO modifyRecordExtInstituteMst(ExtInstituteVO objModelExt_p) {
		ExtInstituteVO ExtInstituteVO_p = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			ExtInstituteVO_p = ExtInstituteDAO.modifyDetails(objModelExt_p, hisDAO);

			if (objModelExt_p.getStrMsgType().equals("1")) {
				objModelExt_p
						.setStrMsgString("regMasterBO.modifyRecord(vo) --> "
								+ objModelExt_p.getStrMsgString());
			}
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return ExtInstituteVO_p;
	}

	/** To Update the ExtInstitute Details,Added by Singaravelan on 22-Jan-14 **/
	public boolean updateExtInstituteDtl(ExtInstituteVO objModelExt_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = ExtInstituteDAO.chkExtInstituteDuplicate(objModelExt_p, strMode_p, hisDAO, uservo);
		if (objModelExt_p.getStrMsgType() != null
				&& objModelExt_p.getStrMsgType().equals("1")) {

			String strErr = objModelExt_p.getStrMsgString();

			objModelExt_p.setStrMsgString("regMasterBO.updateQuery() --> "
					+ strErr);
		}
		if (bExistStatus == true) {
			ExtInstituteDAO.updateExtInstituteDetails(objModelExt_p, hisDAO,uservo);

			if (objModelExt_p.getStrMsgType() != null
					&& objModelExt_p.getStrMsgType().equals("1")) {

				String strErr = objModelExt_p.getStrMsgString();

				objModelExt_p.setStrMsgString("regMasterBO.updateQuery() --> "
						+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To Get Max Unit in Unit Mst, Added by Singaravelan on 30-Jan-14 **/
	public String getDeptUnitCode(UnitVO objModelUnit_p,UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		String maxUnitCode = null;
		try {
			maxUnitCode = UnitDAO.getDeptUnitCode(objModelUnit_p,hisDAO,uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return maxUnitCode;
	}
	
	/** To get Department List Details, Added by Singaravelan on 24-Jan-14 **/
	public List getDeptList(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstDept = null;
		try {
			lstDept = DepartmentDAO.getDepartmentList(hisDAO, uservo);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstDept;

	}
	
	/** To get Department List Details, Added by Singaravelan on 24-Jan-14 **/
	public List getShiftEssentials(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstDept = null;
		try {
			lstDept = ShiftDAO.shiftEssentials(hisDAO, uservo);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstDept;

	}
	
	/** To get Unit List Details, Added by Singaravelan on 24-Jan-14 **/
	public List getUnitList(String deptCode,UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstUnit = null;
		try {
			lstUnit = UnitDAO.getUnitList(deptCode,hisDAO, uservo);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstUnit;

	}
	
	/** To get Room List Details, Added by Singaravelan on 24-Jan-14 **/
	public List getRoomList(String deptUnitCode,UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstUnit = null;
		try {
			lstUnit = RoomDAO.getRoomList(deptUnitCode,hisDAO, uservo);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstUnit;

	}
	
	/** To get Dept Unit Room Roster Details, Added by Singaravelan on 28-Jan-14 **/
	@SuppressWarnings("rawtypes")
	public Collection getDeptUnitShiftWiseRosterDtl(RosterMasterVO rosterMasterVO,UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		Collection collRosterMastreVO=(Collection) new ArrayList();
		try {
			collRosterMastreVO =DeptUnitRosterDAO.getDeptUnitShiftWiseRosterDtl(rosterMasterVO,hisDAO, uservo);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return collRosterMastreVO;

	}
	
	/** To Save Dept Unit Room Roster Details, Added by Singaravelan on 28-Jan-14 **/
	@SuppressWarnings({ "rawtypes", "unused" })
	public void saveDeptUnitRoster(Collection collRosterMasterVO,String unitRoomMasterCapacity, String unitCode, String roomCode, UserVO userVO){
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		RosterMasterVO rosterMasterVO=null;
		RosterMasterVO rosterMasterVONew=null;
		Collection collRetrieveRoster=(Collection) new ArrayList();
		int flag=0;
		try
		{
			Iterator itr=collRosterMasterVO.iterator();
			try {
				rosterMasterVO=new RosterMasterVO();
				rosterMasterVO=new RosterMasterVO();
				rosterMasterVO.setStrDeptUnitCode(unitCode);
				rosterMasterVO.setStrRoomCode(roomCode);
				collRetrieveRoster=DeptUnitRosterDAO.retrieveRosterDtl(rosterMasterVO,hisDAO,userVO);
				DeptUnitRosterDAO.setDeleteIsValid(rosterMasterVO,hisDAO,userVO);
	
				Iterator rosterIterate = collRosterMasterVO.iterator();
				while(rosterIterate.hasNext()){
					rosterMasterVONew = (RosterMasterVO)rosterIterate.next();				
					rosterMasterVONew.setStrRoomCapacity(unitRoomMasterCapacity);
					boolean contains = collRetrieveRoster.contains(rosterMasterVONew);
					if(contains)
					{
						DeptUnitRosterDAO.update(rosterMasterVONew,hisDAO,userVO);
						flag=1;
					}
					else
						DeptUnitRosterDAO.create(rosterMasterVONew,hisDAO,userVO);
				}

				
			}catch(HisRecordNotFoundException e){
				Iterator rosterIterate = collRosterMasterVO.iterator();
				while(rosterIterate.hasNext()){
					rosterMasterVONew = (RosterMasterVO)rosterIterate.next();
					rosterMasterVONew.setStrRoomCapacity(unitRoomMasterCapacity);
					DeptUnitRosterDAO.create(rosterMasterVONew,hisDAO,userVO);
				}
			}	

		}catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		finally{//Execution of roster after completion of insertion
			//if(flag==1)
				//executeDeptUnitSpecificRoster(rosterMasterVONew,userVO);
			
		}	
	}
	
	
	/** To Update the Unit Master for Roster,Added by Singaravelan on 03-Feb-14 **/
	public boolean updateUnitMasterForRoster(UnitVO objModelUnit_p,UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = true;
		// OccupationDAO.chkOccupationDuplicate(objVOOccupation_p,"2");
		if (bExistStatus == true) {
			UnitDAO.updateUnitForRoster(objModelUnit_p,hisDAO, uservo);
			if (objModelUnit_p.getStrMsgType() != null
					&& objModelUnit_p.getStrMsgType().equals("1")) {

				String strErr = objModelUnit_p.getStrMsgString();

				objModelUnit_p.setStrMsgString("regMasterBO.updateQuery() --> "
						+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To Execute Roster, Added by Singaravelan on 06-Feb-14 **/
	public int executeRosterForAll(String sysdate,UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		int status=0;
		try {
			status = DeptUnitRosterDAO.executeRosterForAll(sysdate,uservo,hisDAO);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return status;

	}
	
	/** To Execute Roster, Added by Singaravelan on 06-Feb-14 **/
	public void executeDeptUnitSpecificRoster(RosterMasterVO rosterMasterVO,UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		try {
			DeptUnitRosterDAO.executeDeptUnitSpecificRoster(rosterMasterVO,uservo,hisDAO);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
	}
	
	/** To get Primary Patient Category Details, Added by Singaravelan on 10-Feb-14 **/
	@SuppressWarnings("rawtypes")
	public List getPatientPrimaryCategory(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstPatCategory = null;
		try {
			lstPatCategory = PatCategoryDAO.getPrimaryPatCategory(hisDAO, uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstPatCategory;
	}
	
	/** to get users List Added By Raj Kumar on 1-Nov-2018**/
	//@SuppressWarnings("rawtypes")
	public List getAllUsers(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstUsers = null;
		try {
			lstUsers = PatCategoryDAO.getMyUsers(hisDAO, uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstUsers;
	}
	
	
	/** To get Primary Patient Category wise Mapped and UnMapped Document Details, Added by Singaravelan on 10-Feb-14 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getCategoryWiseMappedUnMappedDocument(String categoryCode,UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List mapped_Docs = null;
		List unMapped_Docs = null;
		Map essentialMap=new HashMap();
		try {
			mapped_Docs = PatCatDocMapMstDAO.getCategoryWiseMappedDocument(categoryCode,hisDAO,uservo);
			essentialMap.put(RegistrationConfig.VERIFICATION_DOCUMENT_MAPPED_IN_PRIMARY_CATEGORY,mapped_Docs);
			unMapped_Docs = PatCatDocMapMstDAO.getCategoryWiseUnMappedDocument(categoryCode,hisDAO,uservo);
			essentialMap.put(RegistrationConfig.VERIFICATION_DOCUMENT_NOT_MAPPED_IN_PRIMARY_CATEGORY,unMapped_Docs);

		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return essentialMap;
	}
	
	 /** To Save the Primary Patient Category wise Mapped and UnMapped Document Details, Added by Singaravelan on 10-Feb-14 **/
	public boolean saveCategoryVerificationDocument(PatCatDocVO[] insertpatCatVerDocVO,PatCatDocVO[] updatepatCatVerDocVO,String strMode_p,UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus=true;
		if (bExistStatus == true) {
			try
			{
				if(insertpatCatVerDocVO!=null && insertpatCatVerDocVO.length!=0){					
					for(int i=0;i < insertpatCatVerDocVO.length;i++)
						PatCatDocMapMstDAO.insert(insertpatCatVerDocVO[i],strMode_p,hisDAO,uservo);							
				}
				if(updatepatCatVerDocVO!=null && updatepatCatVerDocVO.length!=0){					
					for(int i=0;i < updatepatCatVerDocVO.length;i++)
						PatCatDocMapMstDAO.update(updatepatCatVerDocVO[i],strMode_p,hisDAO, uservo);							
				}
			}catch(Exception e)
			{
				bExistStatus=false;
				e.printStackTrace();
			}
			
		}
		return bExistStatus;
	}
	
	/** To get DefaultDisclaimer Details, Added by Singaravelan on 13-Feb-14 **/
	public DisclaimerVO[] getDefaultDisclaimerList(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		DisclaimerVO disclMasterVO[]=new DisclaimerVO[3];
		try {
			disclMasterVO = DisclaimerMstDAO.getDefaultDisclaimer(hisDAO, uservo);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return disclMasterVO;

	}
	
	/** To get DeptDisclaimet Department List Details, Added by Singaravelan on 13-Feb-14 **/
	public List getDisclaimerDeptList(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstDept = null;
		try {
			lstDept = DisclaimerMstDAO.getDisclaimerDeptList(hisDAO, uservo);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstDept;

	}
	
	/** To get UnitDisclaimet DepartmentUnit List Details, Added by Singaravelan on 13-Feb-14 **/
	public List getDisclaimerDeptUnitList(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstDeptUnit = null;
		try {
			lstDeptUnit = DisclaimerMstDAO.getDisclaimerDeptUnitList(hisDAO, uservo);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstDeptUnit;

	}
	
	/** To Save the Disclaimer Master Details, Added by Singaravelan on 13-Feb-14 **/
	public boolean saveDisclaimerDtl(DisclaimerVO objModelDis_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus=true;

		if (bExistStatus) {
			DisclaimerMstDAO.saveDisclaimerDetails(objModelDis_p, strMode_p, hisDAO, uservo);
			if (objModelDis_p.getStrMsgType() != null && objModelDis_p.getStrMsgType().equals("1")) {
				bExistStatus=false;
				String strErr = objModelDis_p.getStrMsgString();
				objModelDis_p.setStrMsgString("RegMstMstBO.insertQuerysaveDisclaimerDtl() --> "	+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To Save the Disclaimer Master Details, Added by Singaravelan on 13-Feb-14 **/
	public boolean updateDisclaimerDtl(DisclaimerVO objModelDis_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus=true;

		if (bExistStatus) {
			DisclaimerMstDAO.updateDisclaimerDetails(objModelDis_p, strMode_p, hisDAO, uservo);
			if (objModelDis_p.getStrMsgType() != null && objModelDis_p.getStrMsgType().equals("1")) {
				bExistStatus=false;
				String strErr = objModelDis_p.getStrMsgString();
				objModelDis_p.setStrMsgString("RegMstMstBO.insertQueryupdateDisclaimerDtl() --> "	+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To show Disclaimer Details in Modify,Added by Singaravelan on 13-Feb-14 **/
	public DisclaimerVO modifyRecordDisclaimerMst(DisclaimerVO objModelDisc_p) {
		DisclaimerVO DisclaimerVO_p = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			DisclaimerVO_p = DisclaimerMstDAO.modifyDetails(objModelDisc_p, hisDAO);

			if (objModelDisc_p.getStrMsgType().equals("1")) {
				objModelDisc_p.setStrMsgString("regMasterBO.modifyRecord(vo) --> "+ objModelDisc_p.getStrMsgString());
			}
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return DisclaimerVO_p;
	}
	
	/** To get Counter Type List Details, Added by Singaravelan on 17-Feb-14 **/
	public List getCounterTypeList(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstDept = null;
		try {
			lstDept = CounterMstDAO.getCounterTypeList(hisDAO, uservo);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstDept;

	}
	
	/** To Save the Counter Master Details, Added by Singaravelan on 17-Feb-14**/	
	public boolean saveCounterDtl(CounterVO objModelcounter_p,String strMode_p,UserVO uservo) 
	{
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		boolean bExistStatus=CounterMstDAO.chkCounterDuplicate(objModelcounter_p,strMode_p,hisDAO,uservo);

		if (objModelcounter_p.getStrMsgType()!=null && objModelcounter_p.getStrMsgType().equals("1")){
			String strErr = objModelcounter_p.getStrMsgString();
			objModelcounter_p.setStrMsgString("RegMstBO.insertCounterQuery() --> " + strErr);
		}
		if (bExistStatus == true){			
			CounterMstDAO.saveCounterDetails(objModelcounter_p,strMode_p,hisDAO,uservo);
			if (objModelcounter_p.getStrMsgType()!=null && objModelcounter_p.getStrMsgType().equals("1")){
				String strErr = objModelcounter_p.getStrMsgString();
				objModelcounter_p.setStrMsgString("RegMstBO.insertCounterQuery() --> " + strErr);
			}
		}
		return bExistStatus;
	}
	
	/**To show Counter Details in Modify,Added by Singaravelan on 17-Feb-14 **/
	public CounterVO modifyRecordCounterMst(CounterVO objModelcounter_p)
	{
		CounterVO CounterVO_p = null;
		try
		{
			HisDAO hisDAO = new HisDAO("Reg","regbo");
			CounterVO_p=CounterMstDAO.modifyDetails(objModelcounter_p,hisDAO);
			if(objModelcounter_p.getStrMsgType().equals("1")){
				objModelcounter_p.setStrMsgString("regMasterBO.modifyRecord(vo) --> " + objModelcounter_p.getStrMsgString());
			}
		}catch(HisRecordNotFoundException e){
			throw new HisRecordNotFoundException(e.getMessage()); 
		}catch(HisApplicationExecutionException e){	   		   	
			throw new HisApplicationExecutionException();
		}catch(HisDataAccessException e){		
			throw new HisDataAccessException();  	
		}catch(Exception e){
			throw new HisApplicationExecutionException();
		}finally{
		}
		return CounterVO_p;
	}
	
	/**To Update the Counter Details,Added by Singaravelan on 17-Feb-14 **/
	public boolean updateCounterDtl(CounterVO objModelcounter_p,String strMode_p,UserVO uservo ) {
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		//boolean bExistStatus=true;
		boolean bExistStatus=CounterMstDAO.chkCounterDuplicate(objModelcounter_p,strMode_p,hisDAO,uservo);

		if (objModelcounter_p.getStrMsgType()!=null && objModelcounter_p.getStrMsgType().equals("1")) {
			String strErr = objModelcounter_p.getStrMsgString();
			objModelcounter_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
		}
		if (bExistStatus == true) {
			CounterMstDAO.updateCounterDetails(objModelcounter_p,strMode_p,hisDAO, uservo );
			if (objModelcounter_p.getStrMsgType()!=null && objModelcounter_p.getStrMsgType().equals("1")) {
				String strErr = objModelcounter_p.getStrMsgString();
				objModelcounter_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To Save the UOM Master Details, Added by Singaravelan on 26-Feb-14**/	
	public boolean saveUOMDtl(UOMVO objModeluom_p,String strMode_p,UserVO uservo) 
	{
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		boolean bExistStatus=UOMMstDAO.chkUOMDuplicate(objModeluom_p,strMode_p,hisDAO,uservo);

		if (objModeluom_p.getStrMsgType()!=null && objModeluom_p.getStrMsgType().equals("1")){
			String strErr = objModeluom_p.getStrMsgString();
			objModeluom_p.setStrMsgString("RegMstBO.insertUOMQuery() --> " + strErr);
		}
		if (bExistStatus == true){			
			UOMMstDAO.saveUOMDetails(objModeluom_p,strMode_p,hisDAO,uservo);
			if (objModeluom_p.getStrMsgType()!=null && objModeluom_p.getStrMsgType().equals("1")){
				String strErr = objModeluom_p.getStrMsgString();
				objModeluom_p.setStrMsgString("RegMstBO.insertUOMQuery() --> " + strErr);
			}
		}
		return bExistStatus;
	}
	
	/**To show UOM Details in Modify,Added by Singaravelan on 26-Feb-14 **/
	public UOMVO modifyRecordUOMMst(UOMVO objModeluom_p)
	{
		UOMVO UomVO_p = null;
		try
		{
			HisDAO hisDAO = new HisDAO("Reg","regbo");
			UomVO_p=UOMMstDAO.modifyDetails(objModeluom_p,hisDAO);
			if(objModeluom_p.getStrMsgType().equals("1")){
				objModeluom_p.setStrMsgString("regMasterBO.modifyRecord(vo) --> " + objModeluom_p.getStrMsgString());
			}
		}catch(HisRecordNotFoundException e){
			throw new HisRecordNotFoundException(e.getMessage()); 
		}catch(HisApplicationExecutionException e){	   		   	
			throw new HisApplicationExecutionException();
		}catch(HisDataAccessException e){		
			throw new HisDataAccessException();  	
		}catch(Exception e){
			throw new HisApplicationExecutionException();
		}finally{
		}
		return UomVO_p;
	}
	
	/**To Update the UOM Details,Added by Singaravelan on 26-Feb-14 **/
	public boolean updateUOMDtl(UOMVO objModeluom_p,String strMode_p,UserVO uservo ) {
		HisDAO hisDAO = new HisDAO("Reg","regbo");
		//boolean bExistStatus=true;
		boolean bExistStatus=UOMMstDAO.chkUOMDuplicate(objModeluom_p,strMode_p,hisDAO,uservo);

		if (objModeluom_p.getStrMsgType()!=null && objModeluom_p.getStrMsgType().equals("1")) {
			String strErr = objModeluom_p.getStrMsgString();
			objModeluom_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
		}
		if (bExistStatus == true) {
			UOMMstDAO.updateUOMDetails(objModeluom_p,strMode_p,hisDAO, uservo );
			if (objModeluom_p.getStrMsgType()!=null && objModeluom_p.getStrMsgType().equals("1")) {
				String strErr = objModeluom_p.getStrMsgString();
				objModeluom_p.setStrMsgString("regMasterBO.updateQuery() --> " + strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To Save the Patient Status Master Details, Added by Singaravelan on 05-May-14 **/
	public boolean saveEmgPatStatusDtl(EmgPatStatusVO objModelEmgPatStatus_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = EmgPatStatusDAO.chkEmgPatStatusDuplicate(objModelEmgPatStatus_p, strMode_p, hisDAO, uservo);
		//boolean bExistStatus=true;

		if (objModelEmgPatStatus_p.getStrMsgType() != null
				&& objModelEmgPatStatus_p.getStrMsgType().equals("1")) {
			String strErr = objModelEmgPatStatus_p.getStrMsgString();
			objModelEmgPatStatus_p
					.setStrMsgString("EmgPatStatusMstBO.insertQuery() --> "
							+ strErr);
		}
		if (bExistStatus == true) {
			EmgPatStatusDAO.saveEmgPatStatusDetails(objModelEmgPatStatus_p, strMode_p,
					hisDAO, uservo);
			if (objModelEmgPatStatus_p.getStrMsgType() != null
					&& objModelEmgPatStatus_p.getStrMsgType().equals("1")) {
				String strErr = objModelEmgPatStatus_p.getStrMsgString();
				objModelEmgPatStatus_p
						.setStrMsgString("EmgPatStatusMstBO.insertQuery() --> "
								+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To show Patient Status Details in Modify,Added by Singaravelan on 05-May-14 **/
	public EmgPatStatusVO modifyRecordEmgPatStatusMst(EmgPatStatusVO objModelEmgPatStatus_p) {
		EmgPatStatusVO EmgPatStatusVO_p = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			EmgPatStatusVO_p = EmgPatStatusDAO.modifyDetails(objModelEmgPatStatus_p, hisDAO);

			if (objModelEmgPatStatus_p.getStrMsgType().equals("1")) {
				objModelEmgPatStatus_p
						.setStrMsgString("regMasterBO.modifyRecord(vo) --> "
								+ objModelEmgPatStatus_p.getStrMsgString());
			}
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return EmgPatStatusVO_p;
	}
	
	/** To Update the Patient Status Details,Added by Singaravelan on 05-May-14 **/
	public boolean updateEmgPatStatusDtl(EmgPatStatusVO objModelEmgPatStatus_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = EmgPatStatusDAO.chkEmgPatStatusDuplicate(objModelEmgPatStatus_p, strMode_p, hisDAO, uservo);
		if (objModelEmgPatStatus_p.getStrMsgType() != null
				&& objModelEmgPatStatus_p.getStrMsgType().equals("1")) {

			String strErr = objModelEmgPatStatus_p.getStrMsgString();

			objModelEmgPatStatus_p.setStrMsgString("regMasterBO.updateQuery() --> "
					+ strErr);
		}
		if (bExistStatus == true) {
			EmgPatStatusDAO.updateEmgPatStatusDetails(objModelEmgPatStatus_p, hisDAO,uservo);

			if (objModelEmgPatStatus_p.getStrMsgType() != null
					&& objModelEmgPatStatus_p.getStrMsgType().equals("1")) {

				String strErr = objModelEmgPatStatus_p.getStrMsgString();

				objModelEmgPatStatus_p.setStrMsgString("regMasterBO.updateQuery() --> "
						+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To Save the Mlc Case Type Master Details, Added by Singaravelan on 06-May-14 **/
	public boolean saveEmgMlcCaseTypeDtl(EmgMlcCaseTypeVO objModelEmgMlcCaseType_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = EmgMlcCaseTypeDAO.chkEmgMlcCaseTypeDuplicate(objModelEmgMlcCaseType_p, strMode_p, hisDAO, uservo);
		//boolean bExistStatus=true;

		if (objModelEmgMlcCaseType_p.getStrMsgType() != null
				&& objModelEmgMlcCaseType_p.getStrMsgType().equals("1")) {
			String strErr = objModelEmgMlcCaseType_p.getStrMsgString();
			objModelEmgMlcCaseType_p
					.setStrMsgString("EmgMlcCaseTypeMstBO.insertQuery() --> "
							+ strErr);
		}
		if (bExistStatus == true) {
			EmgMlcCaseTypeDAO.saveEmgMlcCaseTypeDetails(objModelEmgMlcCaseType_p, strMode_p,
					hisDAO, uservo);
			if (objModelEmgMlcCaseType_p.getStrMsgType() != null
					&& objModelEmgMlcCaseType_p.getStrMsgType().equals("1")) {
				String strErr = objModelEmgMlcCaseType_p.getStrMsgString();
				objModelEmgMlcCaseType_p
						.setStrMsgString("EmgMlcCaseTypeMstBO.insertQuery() --> "
								+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To show Mlc Case Type Master Details in Modify,Added by Singaravelan on 06-May-14 **/
	public EmgMlcCaseTypeVO modifyRecordEmgMlcCaseTypeMst(EmgMlcCaseTypeVO objModelEmgMlcCaseType_p) {
		EmgMlcCaseTypeVO EmgMlcCaseTypeVO_p = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			EmgMlcCaseTypeVO_p = EmgMlcCaseTypeDAO.modifyDetails(objModelEmgMlcCaseType_p, hisDAO);

			if (objModelEmgMlcCaseType_p.getStrMsgType().equals("1")) {
				objModelEmgMlcCaseType_p
						.setStrMsgString("regMasterBO.modifyRecord(vo) --> "
								+ objModelEmgMlcCaseType_p.getStrMsgString());
			}
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return EmgMlcCaseTypeVO_p;
	}
	
	/** To Update the Mlc Case Type Master Details,Added by Singaravelan on 06-May-14 **/
	public boolean updateEmgMlcCaseTypeDtl(EmgMlcCaseTypeVO objModelEmgMlcCaseType_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = EmgMlcCaseTypeDAO.chkEmgMlcCaseTypeDuplicate(objModelEmgMlcCaseType_p, strMode_p, hisDAO, uservo);
		if (objModelEmgMlcCaseType_p.getStrMsgType() != null
				&& objModelEmgMlcCaseType_p.getStrMsgType().equals("1")) {

			String strErr = objModelEmgMlcCaseType_p.getStrMsgString();

			objModelEmgMlcCaseType_p.setStrMsgString("regMasterBO.updateQuery() --> "
					+ strErr);
		}
		if (bExistStatus == true) {
			EmgMlcCaseTypeDAO.updateEmgMlcCaseTypeDetails(objModelEmgMlcCaseType_p, hisDAO,uservo);

			if (objModelEmgMlcCaseType_p.getStrMsgType() != null
					&& objModelEmgMlcCaseType_p.getStrMsgType().equals("1")) {

				String strErr = objModelEmgMlcCaseType_p.getStrMsgString();

				objModelEmgMlcCaseType_p.setStrMsgString("regMasterBO.updateQuery() --> "
						+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To Save the Death Manner Master Details, Added by Singaravelan on 06-May-14 **/
	public boolean saveEmgDeathDtl(EmgDeathMstVO objModelEmgDeath_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = EmgDeathDAO.chkEmgDeathDuplicate(objModelEmgDeath_p, strMode_p, hisDAO, uservo);
		//boolean bExistStatus=true;

		if (objModelEmgDeath_p.getStrMsgType() != null
				&& objModelEmgDeath_p.getStrMsgType().equals("1")) {
			String strErr = objModelEmgDeath_p.getStrMsgString();
			objModelEmgDeath_p
					.setStrMsgString("EmgDeathMstBO.insertQuery() --> "
							+ strErr);
		}
		if (bExistStatus == true) {
			EmgDeathDAO.saveEmgDeathDetails(objModelEmgDeath_p, strMode_p,
					hisDAO, uservo);
			if (objModelEmgDeath_p.getStrMsgType() != null
					&& objModelEmgDeath_p.getStrMsgType().equals("1")) {
				String strErr = objModelEmgDeath_p.getStrMsgString();
				objModelEmgDeath_p
						.setStrMsgString("EmgDeathMstBO.insertQuery() --> "
								+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To show Death Manner Master Details in Modify,Added by Singaravelan on 06-May-14 **/
	public EmgDeathMstVO modifyRecordEmgDeathMst(EmgDeathMstVO objModelEmgDeath_p) {
		EmgDeathMstVO EmgDeathMstVO_p = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			EmgDeathMstVO_p = EmgDeathDAO.modifyDetails(objModelEmgDeath_p, hisDAO);

			if (objModelEmgDeath_p.getStrMsgType().equals("1")) {
				objModelEmgDeath_p
						.setStrMsgString("regMasterBO.modifyRecord(vo) --> "
								+ objModelEmgDeath_p.getStrMsgString());
			}
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return EmgDeathMstVO_p;
	}
	
	/** To Update the Death Manner Master Details,Added by Singaravelan on 06-May-14 **/
	public boolean updateEmgDeathDtl(EmgDeathMstVO objModelEmgDeath_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = EmgDeathDAO.chkEmgDeathDuplicate(objModelEmgDeath_p, strMode_p, hisDAO, uservo);
		if (objModelEmgDeath_p.getStrMsgType() != null
				&& objModelEmgDeath_p.getStrMsgType().equals("1")) {

			String strErr = objModelEmgDeath_p.getStrMsgString();

			objModelEmgDeath_p.setStrMsgString("regMasterBO.updateQuery() --> "
					+ strErr);
		}
		if (bExistStatus == true) {
			EmgDeathDAO.updateEmgDeathDetails(objModelEmgDeath_p, hisDAO,uservo);

			if (objModelEmgDeath_p.getStrMsgType() != null
					&& objModelEmgDeath_p.getStrMsgType().equals("1")) {

				String strErr = objModelEmgDeath_p.getStrMsgString();

				objModelEmgDeath_p.setStrMsgString("regMasterBO.updateQuery() --> "
						+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To Save the Police Station Master Details, Added by Singaravelan on 06-May-14 **/
	public boolean savePoliceStationDtl(PoliceStationMstVO objModelPoliceStation_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = PoliceStationDAO.chkPoliceStationDuplicate(objModelPoliceStation_p, strMode_p, hisDAO, uservo);
		//boolean bExistStatus=true;

		if (objModelPoliceStation_p.getStrMsgType() != null
				&& objModelPoliceStation_p.getStrMsgType().equals("1")) {
			String strErr = objModelPoliceStation_p.getStrMsgString();
			objModelPoliceStation_p
					.setStrMsgString("PoliceStationMstBO.insertQuery() --> "
							+ strErr);
		}
		if (bExistStatus == true) {
			PoliceStationDAO.savePoliceStationDetails(objModelPoliceStation_p, strMode_p,
					hisDAO, uservo);
			if (objModelPoliceStation_p.getStrMsgType() != null
					&& objModelPoliceStation_p.getStrMsgType().equals("1")) {
				String strErr = objModelPoliceStation_p.getStrMsgString();
				objModelPoliceStation_p
						.setStrMsgString("PoliceStationMstBO.insertQuery() --> "
								+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To show Police Station Master Details in Modify,Added by Singaravelan on 06-May-14 **/
	public PoliceStationMstVO modifyRecordPoliceStationMst(PoliceStationMstVO objModelPoliceStation_p) {
		PoliceStationMstVO PoliceStationMstVO_p = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			PoliceStationMstVO_p = PoliceStationDAO.modifyDetails(objModelPoliceStation_p, hisDAO);

			if (objModelPoliceStation_p.getStrMsgType().equals("1")) {
				objModelPoliceStation_p
						.setStrMsgString("regMasterBO.modifyRecord(vo) --> "
								+ objModelPoliceStation_p.getStrMsgString());
			}
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return PoliceStationMstVO_p;
	}
	
	/** To Update the Police Station Master Details,Added by Singaravelan on 06-May-14 **/
	public boolean updatePoliceStationDtl(PoliceStationMstVO objModelPoliceStation_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = PoliceStationDAO.chkPoliceStationDuplicate(objModelPoliceStation_p, strMode_p, hisDAO, uservo);
		if (objModelPoliceStation_p.getStrMsgType() != null
				&& objModelPoliceStation_p.getStrMsgType().equals("1")) {

			String strErr = objModelPoliceStation_p.getStrMsgString();

			objModelPoliceStation_p.setStrMsgString("regMasterBO.updateQuery() --> "
					+ strErr);
		}
		if (bExistStatus == true) {
			PoliceStationDAO.updatePoliceStationDetails(objModelPoliceStation_p, hisDAO,uservo);

			if (objModelPoliceStation_p.getStrMsgType() != null
					&& objModelPoliceStation_p.getStrMsgType().equals("1")) {

				String strErr = objModelPoliceStation_p.getStrMsgString();

				objModelPoliceStation_p.setStrMsgString("regMasterBO.updateQuery() --> "
						+ strErr);
			}
		}
		return bExistStatus;
	}
	/*start:yogender*/
	/** To Save the Emg Case  Master Details, **/
	public boolean saveEmgCaseDtl(EmgCaseVO objModelEmgCase_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = EmgCaseDAO.chkEmgCaseDuplicate(objModelEmgCase_p, strMode_p, hisDAO, uservo);
		//boolean bExistStatus=true;

		if (objModelEmgCase_p.getStrMsgType() != null
				&& objModelEmgCase_p.getStrMsgType().equals("1")) {
			String strErr = objModelEmgCase_p.getStrMsgString();
			objModelEmgCase_p
					.setStrMsgString("EmgCaseMstBO.insertQuery() --> "
							+ strErr);
		}
		if (bExistStatus == true) {
			EmgCaseDAO.saveEmgCaseDetails(objModelEmgCase_p, strMode_p,
					hisDAO, uservo);
			if (objModelEmgCase_p.getStrMsgType() != null
					&& objModelEmgCase_p.getStrMsgType().equals("1")) {
				String strErr = objModelEmgCase_p.getStrMsgString();
				objModelEmgCase_p
						.setStrMsgString("EmgCaseMstBO.insertQuery() --> "
								+ strErr);
			}
		}
		return bExistStatus;
	}
	
	/** To show Emg Case  Master Details in Modify**/
	public EmgCaseVO modifyRecordEmgCaseMst(EmgCaseVO objModelEmgCase_p) {
		EmgCaseVO EmgCaseVO_p = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			EmgCaseVO_p = EmgCaseDAO.modifyDetails(objModelEmgCase_p, hisDAO);

			if (objModelEmgCase_p.getStrMsgType().equals("1")) {
				objModelEmgCase_p
						.setStrMsgString("regMasterBO.modifyRecord(vo) --> "
								+ objModelEmgCase_p.getStrMsgString());
			}
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return EmgCaseVO_p;
	}
	
	/** To Update the Emg Case  Master Details **/
	public boolean updateEmgCaseDtl(EmgCaseVO objModelEmgCase_p,String strMode_p, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		boolean bExistStatus = EmgCaseDAO.chkEmgCaseDuplicate(objModelEmgCase_p, strMode_p, hisDAO, uservo);
		if (objModelEmgCase_p.getStrMsgType() != null
				&& objModelEmgCase_p.getStrMsgType().equals("1")) {

			String strErr = objModelEmgCase_p.getStrMsgString();

			objModelEmgCase_p.setStrMsgString("regMasterBO.updateQuery() --> "
					+ strErr);
		}
		if (bExistStatus == true) {
			EmgCaseDAO.updateEmgCaseDetails(objModelEmgCase_p, hisDAO,uservo);

			if (objModelEmgCase_p.getStrMsgType() != null
					&& objModelEmgCase_p.getStrMsgType().equals("1")) {

				String strErr = objModelEmgCase_p.getStrMsgString();

				objModelEmgCase_p.setStrMsgString("regMasterBO.updateQuery() --> "
						+ strErr);
			}
		}
		return bExistStatus;
	}
	/*end:yogender*/
	
	public List getHospitalList(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstHos = null;
		try {
			lstHos = RegEssentialDAO.getHospitalList(hisDAO, uservo);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstHos;

	}
	
	public void saveRegistrationConfigDtl(List<RegistrationConfigMstVO> lstRegistrationConfigVO_p,
			String strMode_p, UserVO uservo) {
		try {
			System.out.println("RegMasterBO :: saveRegistrationConfigDtl()");
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			//RegistrationConfigDAO.saveRegistrationConfigDetails(lstRegistrationConfigVO_p.get(0),"3", hisDAO, uservo,"1");
			for (int i = 0; i < lstRegistrationConfigVO_p.size(); i++){
				System.out.println("index :"+i);
				RegistrationConfigDAO.saveRegistrationConfigDetails(lstRegistrationConfigVO_p.get(i),
						strMode_p, hisDAO, uservo,"1");
			}
			
			/***********************************************/	
			

		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}

	}

	public RegistrationConfigMstVO[] getRegistrationConfigDtl(UserVO vo) {
		RegistrationConfigMstVO[] arrRegistrationConfigVO = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			arrRegistrationConfigVO = RegistrationConfigDAO
					.getHospitalSpecificRegistrationDetails(vo, hisDAO, "1");

		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return arrRegistrationConfigVO;
	}
	public RegistrationConfigMstVO[] fetchRecordRegistrationConfigMst(RegistrationConfigMstVO objModel_p) {
		RegistrationConfigMstVO[] RegistrationConfigVO_p = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			RegistrationConfigVO_p = RegistrationConfigDAO.fetchDetails(objModel_p, hisDAO);

			/*if (((RegistrationConfigMstVO) objModel_p).getStrMsgType().equals("1")) {
				objModel_p
						.setStrMsgString("regMasterBO.modifyRecord(vo) --> "
								+ objModel_p.getStrMsgString());
			}*/
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return RegistrationConfigVO_p;
	}

	
	public Boolean updateRegistrationConfigDtl(List<RegistrationConfigMstVO> lstRegistrationConfigVO_p,String strMode_p, UserVO uservo) {
		try {
			System.out.println("RegMasterBO :: updateRegistrationConfigDtl()");
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
		for (int i = 0; i < lstRegistrationConfigVO_p.size(); i++){
			System.out.println("index :"+i);
			RegistrationConfigDAO.updateRegistrationConfigDetails(lstRegistrationConfigVO_p.get(i),"1", hisDAO, uservo);
		}
		
		/***********************************************/	
		synchronized (hisDAO) {
			hisDAO.fire();
		}

	} catch (HisRecordNotFoundException e) {
		throw new HisRecordNotFoundException(e.getMessage());
	} catch (HisApplicationExecutionException e) {
		throw new HisApplicationExecutionException();
	}

	catch (HisDataAccessException e) {
		throw new HisDataAccessException();
	} catch (Exception e) {
		throw new HisApplicationExecutionException();
	} finally {
	}
	
return false;
}
	
	/** To get Applicable service list for treatment category, Added by Vasu on 14-May-18 **/
	public List getApplicableServices(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstApplicableServices = null;
		try {
			lstApplicableServices = PatCategoryDAO.getApplicableServices(uservo);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstApplicableServices;

	}
	
	/** To Delete Dept Unit Room Roster Details based on unitcode, roomcode and hospcode, Added by Mukund on Aug'18**/
	@SuppressWarnings({ "rawtypes", "unused" })
	public void deleteDeptUnitRoster(Collection collRosterMasterVO,String unitRoomMasterCapacity, String unitCode, String roomCode, UserVO userVO){
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		RosterMasterVO rosterMasterVO=null;
		RosterMasterVO rosterMasterVONew=null;
		Collection collRetrieveRoster=(Collection) new ArrayList();
		int flag=0;
		try
		{
			Iterator itr=collRosterMasterVO.iterator();
			try {
				rosterMasterVO=new RosterMasterVO();
				rosterMasterVO=new RosterMasterVO();
				rosterMasterVO.setStrDeptUnitCode(unitCode);
				rosterMasterVO.setStrRoomCode(roomCode);
				//collRetrieveRoster=DeptUnitRosterDAO.retrieveRosterDtl(rosterMasterVO,hisDAO,userVO);
				DeptUnitRosterDAO.setDeleteIsValid(rosterMasterVO,hisDAO,userVO);
	
			/*	Iterator rosterIterate = collRosterMasterVO.iterator();
				while(rosterIterate.hasNext()){
					rosterMasterVONew = (RosterMasterVO)rosterIterate.next();				
					rosterMasterVONew.setStrRoomCapacity(unitRoomMasterCapacity);
					boolean contains = collRetrieveRoster.contains(rosterMasterVONew);
					if(contains)
					{
						DeptUnitRosterDAO.update(rosterMasterVONew,hisDAO,userVO);
						flag=1;
					}
					else
						DeptUnitRosterDAO.create(rosterMasterVONew,hisDAO,userVO);
				}*/

				
			}catch(HisRecordNotFoundException e){
				Iterator rosterIterate = collRosterMasterVO.iterator();
				/*while(rosterIterate.hasNext()){
					rosterMasterVONew = (RosterMasterVO)rosterIterate.next();
					rosterMasterVONew.setStrRoomCapacity(unitRoomMasterCapacity);
					DeptUnitRosterDAO.create(rosterMasterVONew,hisDAO,userVO);
				}*/
			}	

		}catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		finally{//Execution of roster after completion of insertion
			//if(flag==1)
				//executeDeptUnitSpecificRoster(rosterMasterVONew,userVO);
			
		}	
	}

/*	public List getSeatId(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstUsersSeat = null;
		try {
			lstUsersSeat = PatCategoryDAO.getMySeatId(hisDAO, uservo);
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstUsersSeat;
	}*/
	
}
