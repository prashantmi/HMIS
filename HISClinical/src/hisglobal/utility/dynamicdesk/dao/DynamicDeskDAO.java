package hisglobal.utility.dynamicdesk.dao;

import hisglobal.hisconfig.Config;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;

import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;
import registration.RegistrationConfig;

public class DynamicDeskDAO extends DataAccessObject implements DynamicDeskDAOi
{
	public DynamicDeskDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}

	// Getting Dynamic Desk Essentials (Not in Use)
	public String getDynamicDeskEssentials(UserDeskMenuMasterVO _userDeskMstVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = DynamicDeskConfig.QUERY_FILE_FOR_DYNAMIC_DESK;
		String queryKey = "DYNAMICDESK.DESK_ID.GBLT_USER_DESKMENU_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userDeskMstVO.getDeptUnitCode());
		populateMAP.put(sq.next(), _userDeskMstVO.getDeskType());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
				throw new HisRecordNotFoundException("No Desk Found");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		String deskID = null;
		try
		{
			deskID = rs.getString(1);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDynamicDeskEssentials" + e);
		}
		return deskID;
	}

	// Getting Dynamic Desk ID 
	public String getDynamicDeskID(UserDeskMenuMasterVO _userDeskMstVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String deskID=null;
		String errorMsg="";
		
		try
		{
			Procedure strProc=new Procedure(DynamicDeskConfig.PROC_FOR_DYNAMIC_DESK_ID);
			if(_userDeskMstVO.getMode()==null || _userDeskMstVO.getMode().equals("")) _userDeskMstVO.setMode("1");
			strProc.addInParameter(1,Types.VARCHAR,_userDeskMstVO.getMode());
		    strProc.addInParameter(2,Types.VARCHAR,_userDeskMstVO.getDeskType());
			strProc.addInParameter(3,Types.VARCHAR,_userDeskMstVO.getDeptUnitCode()== null ||  _userDeskMstVO.getDeptUnitCode().equals("") ?"0" : _userDeskMstVO.getDeptUnitCode());
			//strProc.addInParameter(4,Types.VARCHAR,_userDeskMstVO.getWardCode());
			strProc.addInParameter(4,Types.VARCHAR,_userDeskMstVO.getWardCode()== null ||  _userDeskMstVO.getWardCode().equals("") ?"0" : _userDeskMstVO.getWardCode());
			//strProc.addInParameter(4,Types.VARCHAR,_userVO.getUserSeatId());
			strProc.addInParameter(5,Types.VARCHAR,_userVO.getSeatId());	// User in place of Seat
			strProc.addInParameter(6,Types.VARCHAR,_userVO.getHospitalCode());
			strProc.addOutParameter(7,Types.VARCHAR);
			strProc.addOutParameter(8,Types.REF);//OracleTypes.CURSOR);
			
			strProc.execute(super.getTransactionContext().getConnection());
			
			errorMsg=(String) strProc.getParameterAt(7);
			rs=(ResultSet) strProc.getParameterAt(8) ;
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("No Record Found");
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		try
		{
			System.out.println("result Set :"+rs.toString());
			if(rs.next())
			{
				deskID=rs.getString(1);
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("AlertDAO::getAutomaticAlert"+e);
		}
		return deskID;
	}

	//  Getting Dynamic Desk Menus
	public DeskDetailVO[] getDynamicDeskMenus(DeskDetailVO _deskDtlVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = DynamicDeskConfig.QUERY_FILE_FOR_DYNAMIC_DESK;
		String queryKey = "DYNAMICDESK.MENUS.GBLT_DESK_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), _deskDtlVO.getDeskId());
		populateMAP.put(sq.next(), _deskDtlVO.getLocation());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		ValueObject[] vo = null;
		DeskDetailVO[] menus = null;
		
		try
		{
			while(rs.next())
			{				
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(DeskDetailVO.class, rs);

				menus = new DeskDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					menus[i] = (DeskDetailVO) vo[i];
				}
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDynamicDeskMenus" + e);
		}
		return menus;
	}
	
	//  Getting Dynamic Desk All Menus
	public DeskDetailVO[] getDynamicDeskAllMenus(String _deskId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = DynamicDeskConfig.QUERY_FILE_FOR_DYNAMIC_DESK;
		String queryKey = "DYNAMICDESK.ALL_MENUS.GBLT_DESK_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), _deskId);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		ValueObject[] vo = null;
		DeskDetailVO[] menus = null;
		
		try
		{
			while(rs.next())
			{				
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(DeskDetailVO.class, rs);

				menus = new DeskDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					menus[i] = (DeskDetailVO) vo[i];
				}
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDynamicDeskMenus" + e);
		}
		return menus;
	}

	public String getIpdNursingDesk(UserDeskMenuMasterVO _userDeskMstVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = DynamicDeskConfig.QUERY_FILE_FOR_DYNAMIC_DESK;
		String queryKey = "IPD_NURSING_DESK.DESK_ID.GBLT_USER_DESKMENU_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), _userDeskMstVO.getDeptUnitCode());
		populateMAP.put(sq.next(), _userDeskMstVO.getDeskType());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userDeskMstVO.getWardCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
				throw new HisRecordNotFoundException("No Desk Found");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		String deskID = null;
		try
		{
			deskID = rs.getString(1);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDynamicDeskEssentials" + e);
		}
		return deskID;
	}

	//  Getting Desk Profile Based Menus
	//public List<DeskMenuMasterVO> getDeskProfileBasedMenus(String profileType,String _deskId, UserVO _userVO, String _menu_ext_url)
	public List<DeskMenuMasterVO> getDeskProfileBasedMenus(String profileType,String _deskId, UserVO _userVO)

	{
		System.out.println("in Dynamic Desk DAO in getDeskProfileBasedMenus fun()");
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String[] profile = profileType.split("\\#");
		profileType = profile[0]; 
		String filename = DynamicDeskConfig.QUERY_FILE_FOR_DYNAMIC_DESK;
		String queryKey = "DYNAMICDESK.PROFILE_BASED_MENUS.GBLT_DESK_MENU_MST";
		if(_deskId.equals("-1"))
			queryKey = "DYNAMICDESK.PROFILE_BASED_MENUS_AUTO.GBLT_DESK_MENU_MST";
			
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), DynamicDeskConfig.DYNAMIC_DESK_MENU_USABILITY_FLAG_PROFILE_BASED);
		//if(!_deskId.equals("-1")) populateMAP.put(sq.next(), _deskId);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), profileType);
		//populateMAP.put(sq.next(),_menu_ext_url);
		if(!_deskId.equals("-1")) populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //addedby:NehaRajgariya 8Sept2016
		//if(!_deskId.equals("-1")) populateMAP.put(sq.next(), _userVO.getHospitalCode());
		
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		ValueObject[] vo = null;
		List<DeskMenuMasterVO> lstMenus = new ArrayList<DeskMenuMasterVO>();
		try
		{
			while(rs.next())
			{				
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(DeskMenuMasterVO.class, rs);
				for(ValueObject voVO : vo)
					lstMenus.add((DeskMenuMasterVO)voVO);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDynamicDeskMenus" + e);
		}
		return lstMenus;
	}

	// Getting Desk User List
	public List<UserVO> getUserList(String wardCode, UserVO _uservo)
	{
		ResultSet rs = null;
		String errorMsg="";
		try
		{
			Procedure strProc = new Procedure(DynamicDeskConfig.PROCEDURE_GET_USER_LIST);
			strProc.addInParameter(1, Types.VARCHAR, _uservo.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, wardCode);
			strProc.addInParameter(3, Types.VARCHAR, _uservo.getSeatId());
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addOutParameter(5, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(4);
			rs = (ResultSet) strProc.getParameterAt(5);
			System.out.println("");
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
	
		
		ValueObject[] vo = null;
		List<UserVO> alRecord = new ArrayList<UserVO>();
		try
		{
			while(rs.next())
			{				
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(UserVO.class, rs);
				for(ValueObject voVO : vo)
					alRecord.add((UserVO)voVO);
			}
		}
		/*List<Entry> alRecord = new ArrayList<Entry>();
		try
		{
			
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
			System.out.println("");
		}*/
		catch (Exception e)
		{
			throw new HisDataAccessException("DynamicDeskDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
		try
		{
			if (alRecord == null || alRecord.size() == 0)
			{
				throw new HisRecordNotFoundException("User Not Found");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return alRecord;
	}

	public List<DeskDetailVO> getDeskMenus(String p_mode, DeskDetailVO deskDetailVO, UserVO userVO) 
	{
		ResultSet rs = null;
		String errorMsg="";
		try
		{
			Procedure strProc = new Procedure(DynamicDeskConfig.PROC_FOR_GET_DESK_MENUS);
			strProc.addInParameter(1, Types.VARCHAR, p_mode);
			strProc.addInParameter(2, Types.VARCHAR, deskDetailVO.getDeskId());
			
			strProc.addInParameter(3, Types.VARCHAR, deskDetailVO.getDeskType());
			strProc.addInParameter(4, Types.VARCHAR, userVO.getHospitalCode());
			strProc.addOutParameter(5, Types.VARCHAR);
			strProc.addOutParameter(6, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(5);
			rs = (ResultSet) strProc.getParameterAt(6);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DynamicDeskDAO.getNewDeskEssentials::" + e);
		}

		ValueObject[] vo = null;
		List<DeskDetailVO> lstMenus = new ArrayList<DeskDetailVO>();
		try
		{
			while(rs.next())
			{				
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(DeskDetailVO.class, rs);
				for(ValueObject voVO : vo)
					lstMenus.add((DeskDetailVO)voVO);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("DynamicDeskDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return lstMenus;
	}
	

	// Room List For Doctor Desk New (OPD) *
	public List getRoomsByUnitCode(UserVO _userVO, String unitCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HGBT_ROOM_MST.RETRIEVE_BY_DEPTUNITCODE";

		// first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), unitCode);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Room Allotted For This Unit");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}
}
