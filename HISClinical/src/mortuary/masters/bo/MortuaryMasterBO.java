package mortuary.masters.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mortuary.MortuaryConfig;
import mortuary.masters.dao.ChamberMasterDAO;
import mortuary.masters.dao.ChamberMasterDAOi;
import mortuary.masters.dao.ChamberRackMasterDAO;
import mortuary.masters.dao.ChamberRackMasterDAOi;
import mortuary.masters.dao.ExternalLabMasterDAO;
import mortuary.masters.dao.ExternalLabMasterDAOi;
import mortuary.masters.dao.ExternalLabTestMasterDAO;
import mortuary.masters.dao.ExternalLabTestMasterDAOi;
import mortuary.masters.dao.MortuaryMasterDAO;
import mortuary.masters.dao.MortuaryMasterDAOi;
import mortuary.masters.dao.MortuaryAreaMasterDAO;
import mortuary.masters.dao.MortuaryAreaMasterDAOi;
import mortuary.masters.dao.MortuaryMstEssentialDAO;
import mortuary.masters.dao.MortuaryMstEssentialDAOi;
import mortuary.masters.dao.OpinionMasterDAO;
import mortuary.masters.dao.OpinionMasterDAOi;
import mortuary.masters.dao.PreservativeMasterDAO;
import mortuary.masters.dao.PreservativeMasterDAOi;
import mortuary.masters.dao.RoleMasterDAO;
import mortuary.masters.dao.RoleMasterDAOi;
import mortuary.masters.dao.DeceasedItemMasterDAO;
import mortuary.masters.dao.DeceasedItemMasterDAOi;
import mortuary.masters.dao.IncisionTypeMasterDAO;
import mortuary.masters.dao.IncisionTypeMasterDAOi;
import mortuary.transaction.dao.MortuaryEssDAO;
import mortuary.transaction.dao.MortuaryEssDAOi;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;

import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.ChamberMasterVO;
import hisglobal.vo.ChamberRackMasterVO;

import hisglobal.vo.ExternalLabMasterVO;
import hisglobal.vo.ExternalLabTestMasterVO;
import hisglobal.vo.IncisionTypeMasterVO;
import hisglobal.vo.MortuaryMasterVO;
import hisglobal.vo.MortuaryAreaMasterVO;
import hisglobal.vo.OpinionMasterVO;
import hisglobal.vo.DeceasedItemMasterVO;
import hisglobal.vo.MortuaryRoleMasterVO;
import hisglobal.vo.PreservativeMasterVO;
import hisglobal.vo.UserVO;

public class MortuaryMasterBO implements MortuaryMasterBOi
{
	//Inserting Record in Mortuary Master 
	public void saveMortuaryMaster(MortuaryMasterVO mortuaryMstVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String existName="";
		String existShortName="";
		
		try
		{
			tx.begin();
			MortuaryMasterDAOi mortuaryDAO=new MortuaryMasterDAO(tx);
			existName=mortuaryDAO.checkDuplicateName(mortuaryMstVO,userVO);
			existShortName=mortuaryDAO.checkDuplicateShortName(mortuaryMstVO,userVO);
			if(existName.equals("0"))
			{
				if(existShortName.equals("0"))
					mortuaryDAO.create(mortuaryMstVO,userVO);
				else
					throw new HisDuplicateRecordException("Mortuary Short Name Already Exists");
			}
			else
			{
				throw new HisDuplicateRecordException("Mortuary Name Already Exists");
			}
			
		}
		
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
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
	}
	
	public MortuaryMasterVO getDataForModify(MortuaryMasterVO  _MortuaryMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		MortuaryMasterVO vo=new MortuaryMasterVO();
		try
		{
			tx.begin();
			MortuaryMasterDAOi daoObj = new MortuaryMasterDAO(tx);
			vo=daoObj.getDataForModify(_MortuaryMasterVO, _UserVO);
			
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;
		
	}
	public boolean saveModMortuaryMaster(MortuaryMasterVO  _MortuaryMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String existName="";
		String existShortName="";
		try{
			tx.begin();
			MortuaryMasterDAOi daoObj = new MortuaryMasterDAO(tx);
			existName=daoObj.checkDuplicateNameForModify(_MortuaryMasterVO,_UserVO);
			existShortName=daoObj.checkDuplicateShortNameForModify(_MortuaryMasterVO,_UserVO);
			if(existName.equals("0"))
			{
				if(existShortName.equals("0")){
					daoObj.updateMortuaryMaster(_MortuaryMasterVO,_UserVO);
					daoObj.modifySaveMortuaryMaster(_MortuaryMasterVO,_UserVO);
					flag=true;
				}
				else
					throw new HisDuplicateRecordException("Mortuary Short Name Already Exists");
			}
			else
			{
				throw new HisDuplicateRecordException("Mortuary Name Already Exists");
			}
			
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
		
	}
	
	
	public void saveOpinionMaster(OpinionMasterVO opinionMasterVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String opinionName="";
				
		try
		{
			tx.begin();
			OpinionMasterDAOi opinionDAO=new OpinionMasterDAO(tx);
			opinionName=opinionDAO.checkDuplicateOpinionName(opinionMasterVO,userVO);
			
			if(opinionName.equals("0"))
			{
				opinionDAO.create(opinionMasterVO,userVO);
				
			}
			else
			{
				throw new HisDuplicateRecordException("Opinion Name Already Exists");
			}
			
		}
		
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
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
	}
	
	public OpinionMasterVO getDataForOpinionModify(OpinionMasterVO opinionMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		OpinionMasterVO vo=new OpinionMasterVO();
		try
		{
			tx.begin();
			OpinionMasterDAOi opinionDAO=new OpinionMasterDAO(tx);
			vo=opinionDAO.getDataForModify(opinionMasterVO, _UserVO);
			
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;
		
	}
	public boolean saveModOpinionMaster(OpinionMasterVO opinionMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String opinionName="";
		
		try{
			tx.begin();
			OpinionMasterDAOi opinionDAO=new OpinionMasterDAO(tx);
			opinionName=opinionDAO.checkDuplicateNameForModify(opinionMasterVO,_UserVO);
		
			if(opinionName.equals("0"))
			{
				opinionDAO.updateOpinionMaster(opinionMasterVO,_UserVO);
				opinionDAO.modifySaveOpinionMaster(opinionMasterVO,_UserVO);
				flag=true;
			}
			
			else
			{
				throw new HisDuplicateRecordException("Opinion Name Already Exists");
			}
			
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
		
	}

	
	public void saveRoleMaster(MortuaryRoleMasterVO mortuaryRoleMasterVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String roleName="";
				
		try
		{
			tx.begin();
			RoleMasterDAOi roleDAO=new RoleMasterDAO(tx);
			roleName=roleDAO.checkDuplicateRoleName(mortuaryRoleMasterVO,userVO);
			
			if(roleName.equals("0"))
			{
				roleDAO.create(mortuaryRoleMasterVO,userVO);
				
			}
			else
			{
				throw new HisDuplicateRecordException("Role Name Already Exists");
			}
			
		}
		
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
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
	}
	
	public MortuaryRoleMasterVO getDataForRoleModify(MortuaryRoleMasterVO mortuaryRoleMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		MortuaryRoleMasterVO vo=new MortuaryRoleMasterVO();
		try
		{
			tx.begin();
			RoleMasterDAOi roleDAO=new RoleMasterDAO(tx);
			vo=roleDAO.getDataForModify(mortuaryRoleMasterVO, _UserVO);
			
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;
		
	}
	public boolean saveModRoleMaster(MortuaryRoleMasterVO mortuaryRoleMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String roleName="";
		
		try{
			tx.begin();
			RoleMasterDAOi roleDAO=new RoleMasterDAO(tx);
			roleName=roleDAO.checkDuplicateNameForModify(mortuaryRoleMasterVO,_UserVO);
		
			if(roleName.equals("0"))
			{
				roleDAO.updateRoleMaster(mortuaryRoleMasterVO,_UserVO);
				roleDAO.modifySaveRoleMaster(mortuaryRoleMasterVO,_UserVO);
				flag=true;
			}
			
			else
			{
				throw new HisDuplicateRecordException("Role Name Already Exists");
			}
			
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
		
	}
	
	
	public void saveDeceasedItemMaster(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String itemName="";
				
		try
		{
			tx.begin();
			DeceasedItemMasterDAOi itemDAO=new DeceasedItemMasterDAO(tx);
			itemName=itemDAO.checkDuplicateItemName(deceasedItemMasterVO,userVO);
			
			if(itemName.equals("0"))
			{
				itemDAO.create(deceasedItemMasterVO,userVO);
				
			}
			else
			{
				throw new HisDuplicateRecordException("Item Name Already Exists");
			}
			
		}
		
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
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
	}
	
	public DeceasedItemMasterVO getDataForDeceasedItemModify(DeceasedItemMasterVO deceasedItemMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		DeceasedItemMasterVO vo=new DeceasedItemMasterVO();
		try
		{
			tx.begin();
			DeceasedItemMasterDAOi itemDAO=new DeceasedItemMasterDAO(tx);
			vo=itemDAO.getDataForModify(deceasedItemMasterVO, _UserVO);
			
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;
		
	}
	public boolean saveModDeceasedItemMaster(DeceasedItemMasterVO deceasedItemMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String itemName="";
		
		try{
			tx.begin();
			DeceasedItemMasterDAOi itemDAO=new DeceasedItemMasterDAO(tx);
			itemName=itemDAO.checkDuplicateNameForModify(deceasedItemMasterVO,_UserVO);
		
			if(itemName.equals("0"))
			{
				itemDAO.updateDeceasedItemMaster(deceasedItemMasterVO,_UserVO);
				itemDAO.modifySaveDeceasedItemMaster(deceasedItemMasterVO,_UserVO);
				flag=true;
			}
			
			else
			{
				throw new HisDuplicateRecordException("Item Name Already Exists");
			}
			
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
		
	}
	
	public void saveMortuaryAreaMaster(MortuaryAreaMasterVO mortuaryMstVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String existName="";
		
		
		try
		{
			tx.begin();
			MortuaryAreaMasterDAOi mortuaryDAO=new MortuaryAreaMasterDAO(tx);
			existName=mortuaryDAO.checkDuplicateName(mortuaryMstVO,userVO);
		
			if(existName.equals("0"))
			{
				mortuaryDAO.create(mortuaryMstVO,userVO);
			}
			else
			{
				throw new HisDuplicateRecordException("Mortuary Area Name Already Exists");
			}
			
		}
		
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
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
	}
	
	public MortuaryAreaMasterVO getDataForModify(MortuaryAreaMasterVO  _MortuaryMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		MortuaryAreaMasterVO vo=new MortuaryAreaMasterVO();
		try
		{
			tx.begin();
			MortuaryAreaMasterDAOi daoObj = new MortuaryAreaMasterDAO(tx);
			vo=daoObj.getDataForModify(_MortuaryMasterVO, _UserVO);
			
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;
		
	}
	public boolean saveModMortuaryAreaMaster(MortuaryAreaMasterVO  _MortuaryMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String existName="";
		
		try{
			tx.begin();
			MortuaryAreaMasterDAOi daoObj = new MortuaryAreaMasterDAO(tx);
			existName=daoObj.checkDuplicateNameForModify(_MortuaryMasterVO,_UserVO);
		
			if(existName.equals("0"))
			{
					daoObj.updateMortuaryAreaMaster(_MortuaryMasterVO,_UserVO);
					daoObj.modifySaveMortuaryAreaMaster(_MortuaryMasterVO,_UserVO);
					flag=true;
				
			}
			else
			{
				throw new HisDuplicateRecordException("Mortuary Area Name Already Exists");
			}
			
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error....  BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
		
	}
	
	public String getMortuaryName(String mortuaryCode, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String mortuaryName="";
		
		try{
			tx.begin();
			MortuaryEssDAOi daoObj = new MortuaryEssDAO(tx);
			mortuaryName=daoObj.getMortuaryName(mortuaryCode,_UserVO);
			
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error....  BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mortuaryName;
		
	}

	public void saveIncisionTypeMaster(IncisionTypeMasterVO incisionTypeMasterVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String existName="";
		
		
		try
		{
			tx.begin();
			IncisionTypeMasterDAOi incisionDAO=new IncisionTypeMasterDAO(tx);
			existName=incisionDAO.checkDuplicateName(incisionTypeMasterVO,userVO);
		
			if(existName.equals("0"))
			{
				incisionDAO.create(incisionTypeMasterVO,userVO);
			}
			else
			{
				throw new HisDuplicateRecordException("Incision Type Already Exists");
			}
			
		}
		
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
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
	}
	
	public IncisionTypeMasterVO getDataForModify(IncisionTypeMasterVO incisionTypeMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		IncisionTypeMasterVO vo=new IncisionTypeMasterVO();
		try
		{
			tx.begin();
			IncisionTypeMasterDAOi incisionDAO=new IncisionTypeMasterDAO(tx);
			vo=incisionDAO.getDataForModify(incisionTypeMasterVO, _UserVO);
			
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... ");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;
		
	}
	public boolean saveModIncisionTypeMaster(IncisionTypeMasterVO incisionTypeMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String existName="";
		
		try{
			tx.begin();
			IncisionTypeMasterDAOi incisionDAO=new IncisionTypeMasterDAO(tx);
			existName=incisionDAO.checkDuplicateNameForModify(incisionTypeMasterVO,_UserVO);
		
			if(existName.equals("0"))
			{
				incisionDAO.updateIncisionTypeMaster(incisionTypeMasterVO,_UserVO);
				incisionDAO.modifySaveIncisionTypeMaster(incisionTypeMasterVO,_UserVO);
					flag=true;
				
			}
			else
			{
				throw new HisDuplicateRecordException("Incision Type Already Exists");
			}
			
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error....  BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
		
	}
	
	
	/*********************************Chamber Master***********************************************/	
	
	
	//for fetching the essential details 
	
	public Map getChamberEssentialDetails(String[]  _controlsArray, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		Map EssentialMap=new HashMap();			

		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi daoObj = new MortuaryMstEssentialDAO(tx);
			List mortuaryList=new ArrayList();
			List areaList=new ArrayList();
			
			//Getting and putting all mortuaries
			
			mortuaryList=daoObj.getAllMortuary(_userVO);
			
			EssentialMap.put(MortuaryConfig.ESENTIAL_ALL_MORTUARY, mortuaryList);			

			//Getting and putting all areas bsed on mortuaries
			
			areaList=daoObj.getAllAreaBasedOnMortuary(_controlsArray[0],_userVO);		
			
			EssentialMap.put(MortuaryConfig.ESENTIAL_ALL_AREA_BASED_ON_MORTUARY, areaList);
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return EssentialMap;
		
	}
	
	//Inserting Record in Chamber Master HMRT_CHAMBER_MST 
	public boolean saveChamber(ChamberMasterVO _chamberMstVO,UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();

			ChamberMasterDAOi daoObj = new ChamberMasterDAO(tx);
				

				
				daoObj.checkDuplicateBeforeInsert(_chamberMstVO.getChamberName(), _userVO);
								
				daoObj.saveChamber(_chamberMstVO, _userVO);
				flag=true;

				
			}
			catch(HisDuplicateRecordException e){	   		   	
		  		 tx.rollback();
		  		 e.printStackTrace(); 
		  		 throw new HisDuplicateRecordException(e.getMessage());
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
			catch (HisException e)
			{
				tx.rollback();
				System.out.println("HisException:: " + e);
				e.printStackTrace();
				throw new HisException();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return flag;
		}
	
	public ChamberMasterVO getChamberDetails(ChamberMasterVO _chamberMstVO,UserVO _userVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ChamberMasterVO chamberMstVO=new ChamberMasterVO();
		
		try
		{
			tx.begin();
			ChamberMasterDAOi daoObj = new ChamberMasterDAO(tx);
			chamberMstVO=daoObj.getChamberDetails(_chamberMstVO, _userVO);
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
			throw new HisDuplicateRecordException(e.getMessage());
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return chamberMstVO;
	}
	
	
	public boolean updateChamber(ChamberMasterVO _chamberMstVO, UserVO _userVO)
	{

		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();
			
			ChamberMasterDAOi daoObj = new ChamberMasterDAO(tx);
			
				daoObj.checkDuplicateBeforeModify(_chamberMstVO, _userVO);
				
				
				daoObj.modifyChamber(_chamberMstVO, _userVO);
				daoObj.modifyInsertChamber(_chamberMstVO, _userVO);
				
				flag=true;
			
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
			throw new HisDuplicateRecordException(e.getMessage());
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	
	}
	
	
	
/*********************************Chamber Rack Master***********************************************/	
	
	
	//For fetching the name of chamber and checking if it's Record is present
	// in Chamber rack Master HMRT_CHAMBER_RACK_MST 
	
	
	public String getChamberRackEssentials(String  _chamberId, UserVO _userVO) 
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		String chamberName="";
		Map chamberMap=new HashMap();
		
		
		try
		{
			tx.begin();

			ChamberRackMasterDAOi daoObj = new ChamberRackMasterDAO(tx);
			MortuaryMstEssentialDAOi essDaoObj = new MortuaryMstEssentialDAO(tx);
		
			
			//Getting and putting all mortuaries
			
			chamberName=essDaoObj.getChamberName(_chamberId,_userVO);
		
			chamberMap.put(_chamberId, chamberName);
			
			
			daoObj.checkChamberIdPresent(_chamberId, _userVO);
			
			
			
		

				
			}
			catch(HisDuplicateRecordException e){	   		   	
		  		 tx.rollback();
		  		 e.printStackTrace(); 
		  		 throw new HisDuplicateRecordException(e.getMessage(),chamberMap);
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
			catch (HisException e)
			{
				tx.rollback();
				System.out.println("HisException:: " + e);
				e.printStackTrace();
				throw new HisException();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return chamberName;
		}
	
	
	
	
	
	//Inserting Record in Chamber Master HMRT_CHAMBER_MST 
	public boolean saveChamberRack(ChamberRackMasterVO[] _chamberRackMstVO,UserVO _userVO) 
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();

			ChamberRackMasterDAOi daoObj = new ChamberRackMasterDAO(tx);
				
			for(int i=0;i < _chamberRackMstVO.length;i++){
				flag=false;			
				
				daoObj.checkDuplicateBeforeInsert(_chamberRackMstVO[i].getRackName(),_chamberRackMstVO[i].getChamberId(),_userVO);
				daoObj.saveChamberRack(_chamberRackMstVO[i], _userVO);
				flag=true;
				
					}

				
			}
			catch(HisDuplicateRecordException e){	   		   	
		  		 tx.rollback();
		  		 e.printStackTrace(); 
		  		 throw new HisDuplicateRecordException(e.getMessage());
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
			catch (HisException e)
			{
				tx.rollback();
				System.out.println("HisException:: " + e);
				e.printStackTrace();
				throw new HisException();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return flag;
		}
	
	public ChamberRackMasterVO getChamberRackDetails(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ChamberRackMasterVO chamberRackMstVO=new ChamberRackMasterVO();
		
		try
		{
			tx.begin();
			ChamberRackMasterDAOi daoObj = new ChamberRackMasterDAO(tx);
			chamberRackMstVO=daoObj.getChamberRackDetails(_chamberRackMstVO, _userVO);
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
			throw new HisDuplicateRecordException(e.getMessage());
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return chamberRackMstVO;
	}
	
	
	public boolean updateChamberRack(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO)
	{

		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();
			
			ChamberRackMasterDAOi daoObj = new ChamberRackMasterDAO(tx);
			
				daoObj.checkDuplicateBeforeModify(_chamberRackMstVO, _userVO);
				
				
				daoObj.modifyChamberRack(_chamberRackMstVO, _userVO);
				daoObj.modifyInsertChamberRack(_chamberRackMstVO, _userVO);
				
				flag=true;
			
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
			throw new HisDuplicateRecordException(e.getMessage());
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	
	}
	
	/********************************External Lab Test Master**************************************************/
	
	
	//Inserting Record in External Lab Test Master HMRT_EXT_LABTEST_MST 
	
	
	public boolean saveExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();

			ExternalLabTestMasterDAOi daoObj = new ExternalLabTestMasterDAO(tx);
				

				
				daoObj.checkDuplicateBeforeInsert(_externalLabTestMstVO.getExternalLabTestName(), _userVO);
								
				daoObj.saveExternalLabTestDetails(_externalLabTestMstVO, _userVO);
				flag=true;

				
			}
			catch(HisDuplicateRecordException e){	   		   	
		  		 tx.rollback();
		  		 e.printStackTrace(); 
		  		 throw new HisDuplicateRecordException(e.getMessage());
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
			catch (HisException e)
			{
				tx.rollback();
				System.out.println("HisException:: " + e);
				e.printStackTrace();
				throw new HisException();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return flag;
		}
	
	public ExternalLabTestMasterVO getExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ExternalLabTestMasterVO externalLabTestMstVO=new ExternalLabTestMasterVO();
		
		try
		{
			tx.begin();
			ExternalLabTestMasterDAOi daoObj = new ExternalLabTestMasterDAO(tx);
			externalLabTestMstVO=daoObj.getExternalLabTestDetails(_externalLabTestMstVO, _userVO);
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
			throw new HisDuplicateRecordException(e.getMessage());
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return externalLabTestMstVO;
	}
	
	
	public boolean updateExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO)
	{

		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();
			
			ExternalLabTestMasterDAOi daoObj = new ExternalLabTestMasterDAO(tx);
			
				daoObj.checkDuplicateBeforeModify(_externalLabTestMstVO, _userVO);
				
				
				daoObj.modiftExternalLabTestDetails(_externalLabTestMstVO, _userVO);
				daoObj.modifyInsertExternalLabTestDetails(_externalLabTestMstVO, _userVO);
				
				flag=true;
			
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
			throw new HisDuplicateRecordException(e.getMessage());
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	
	}
	
	
/********************************External Lab Test Master**************************************************/
	
	
	//Inserting Record in External Lab Test Master HMRT_EXT_LABTEST_MST 
	
	
	public boolean saveExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();

			ExternalLabMasterDAOi daoObj = new ExternalLabMasterDAO(tx);
				

				
				daoObj.checkDuplicateBeforeInsert(_externalLabMstVO.getExternalLabName(), _userVO);
								
				daoObj.saveExternalLabDetails(_externalLabMstVO, _userVO);
				flag=true;

				
			}
			catch(HisDuplicateRecordException e){	   		   	
		  		 tx.rollback();
		  		 e.printStackTrace(); 
		  		 throw new HisDuplicateRecordException(e.getMessage());
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
			catch (HisException e)
			{
				tx.rollback();
				System.out.println("HisException:: " + e);
				e.printStackTrace();
				throw new HisException();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return flag;
		}
	
	public ExternalLabMasterVO getExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ExternalLabMasterVO externalLabMstVO=new ExternalLabMasterVO();
		
		try
		{
			tx.begin();
			ExternalLabMasterDAOi daoObj = new ExternalLabMasterDAO(tx);
			externalLabMstVO=daoObj.getExternalLabDetails(_externalLabMstVO, _userVO);
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
			throw new HisDuplicateRecordException(e.getMessage());
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return externalLabMstVO;
	}
	
	
	public boolean updateExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO)
	{

		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();
			
			ExternalLabMasterDAOi daoObj = new ExternalLabMasterDAO(tx);
			
				daoObj.checkDuplicateBeforeModify(_externalLabMstVO, _userVO);
				
				
				daoObj.modiftExternalLabDetails(_externalLabMstVO, _userVO);
				daoObj.modifyInsertExternalLabDetails(_externalLabMstVO, _userVO);
				
				flag=true;
			
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
			throw new HisDuplicateRecordException(e.getMessage());
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	
	}
	
	
/********************************Preservative Master**************************************************/
	
	
	//Inserting Record in External Lab Test Master HMRT_PRESERVATIVE_MST  
	
	
	public boolean savePreservativeDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();

			PreservativeMasterDAOi daoObj = new PreservativeMasterDAO(tx);
				

				
				daoObj.checkDuplicateBeforeInsert(_preservativeMasterVO.getPreservativeName(), _userVO);
								
				daoObj.saveExternalLabTestDetails(_preservativeMasterVO, _userVO);
				flag=true;

				
			}
			catch(HisDuplicateRecordException e){	   		   	
		  		 tx.rollback();
		  		 e.printStackTrace(); 
		  		 throw new HisDuplicateRecordException(e.getMessage());
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
			catch (HisException e)
			{
				tx.rollback();
				System.out.println("HisException:: " + e);
				e.printStackTrace();
				throw new HisException();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return flag;
		}
	
	public PreservativeMasterVO getPreservativeDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PreservativeMasterVO externalLabTestMstVO=new PreservativeMasterVO();
		
		try
		{
			tx.begin();
			PreservativeMasterDAOi daoObj = new PreservativeMasterDAO(tx);
			externalLabTestMstVO=daoObj.getExternalLabTestDetails(_preservativeMasterVO, _userVO);
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
			throw new HisDuplicateRecordException(e.getMessage());
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return externalLabTestMstVO;
	}
	
	
	public boolean updatePreservativeDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO)
	{

		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();
			
			PreservativeMasterDAOi daoObj = new PreservativeMasterDAO(tx);
			
				daoObj.checkDuplicateBeforeModify(_preservativeMasterVO, _userVO);
				
				
				daoObj.modiftExternalLabTestDetails(_preservativeMasterVO, _userVO);
				daoObj.modifyInsertExternalLabTestDetails(_preservativeMasterVO, _userVO);
				
				flag=true;
			
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
			throw new HisDuplicateRecordException(e.getMessage());
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
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	
	}
}
