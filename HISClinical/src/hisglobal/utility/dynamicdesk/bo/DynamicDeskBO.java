package hisglobal.utility.dynamicdesk.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.dao.DynamicDeskDAO;
import hisglobal.utility.dynamicdesk.dao.DynamicDeskDAOi;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicDeskBO implements DynamicDeskBOi
{
	//  Getting Dynamic Desk Essentials
	public Map getDynamicDeskEssentials(UserDeskMenuMasterVO _userDeskMstVO, UserVO _userVO)
	{
		String deskID=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map map = new HashMap();
		DeskDetailVO[] deskDtl = null; 
		try
		{
			tx.begin();
			DynamicDeskDAO dao = new DynamicDeskDAO(tx);
			if(_userDeskMstVO.getWardCode()==null) _userDeskMstVO.setWardCode("");
				deskID = dao.getDynamicDeskID(_userDeskMstVO, _userVO);
			map.put(DynamicDeskConfig.DYNAMIC_DESK_ID, deskID);
			if(deskID!=null)
			{
				//deskDtl = dao.getDynamicDeskAllMenus(deskID, _userVO);
				DeskDetailVO deskDetailVO = new DeskDetailVO();
				deskDetailVO.setDeskId(deskID);
				List<DeskDetailVO> lstDeskMenus = dao.getDeskMenus("0",deskDetailVO, _userVO);
				deskDtl = new DeskDetailVO[lstDeskMenus.size()];
				deskDtl = lstDeskMenus.toArray(deskDtl);
			}
			map.put(DynamicDeskConfig.DYNAMIC_DESK_MENU_DTL, deskDtl);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		//return deskID;
		return map;
	}

	//  Getting Dynamic Desk Menus
	public DeskDetailVO[] getDynamicDeskMenus(DeskDetailVO _deskDtlVO, UserVO _userVO)
	{
		DeskDetailVO[] menus=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DynamicDeskDAOi dao = new DynamicDeskDAO(tx);
			menus = dao.getDynamicDeskMenus(_deskDtlVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return menus;
	}

	//  Getting Dynamic Desk All Menus
	public DeskDetailVO[] getDynamicDeskAllMenus(DeskDetailVO _deskDtlVO, UserVO _userVO)
	{
		DeskDetailVO[] menus=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DynamicDeskDAOi dao = new DynamicDeskDAO(tx);
			menus = dao.getDynamicDeskMenus(_deskDtlVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return menus;
	}

	//  Getting Desk Profile Based Menus
	public List<DeskMenuMasterVO> getDeskProfileBasedMenus(String profileType,String _deskId, UserVO _userVO)
	{
		List<DeskMenuMasterVO> menus=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DynamicDeskDAOi dao = new DynamicDeskDAO(tx);
			menus = dao.getDeskProfileBasedMenus(profileType,_deskId, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return menus;
	}

	// Getting Desk User List
	public List<UserVO> getUserList(UserVO _uservo,String _wardCode)
	{		
		JDBCTransactionContext tx = new JDBCTransactionContext();		
		List<UserVO> userList = new ArrayList<UserVO>();
		try
		{
			tx.begin();
			DynamicDeskDAOi dao = new DynamicDeskDAO(tx);
			userList = dao.getUserList(_wardCode,_uservo);			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return userList;
	}

	public Map<String, Object> getNewDeskEssentials(DeskDetailVO deskDetailVO,UserVO userVO)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		JDBCTransactionContext tx = new JDBCTransactionContext();			
		try
		{
			tx.begin();
			DynamicDeskDAO dao = new DynamicDeskDAO(tx);
			List<DeskDetailVO> lstDeskMenus = dao.getDeskMenus("1", deskDetailVO, userVO);
			map.put(DynamicDeskConfig.DYNAMIC_DESK_NONPATIENT_CENTRIC_MENU_DTL, lstDeskMenus);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return map;
	}
	
	// get Room*
	public List getRoomsByUnitCode(UserVO _userVO, String unitCode)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DynamicDeskDAO dao = new DynamicDeskDAO(tx);
			list = dao.getRoomsByUnitCode(_userVO, unitCode);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return list;
	}

}
