package mrd.masters.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DoctorDesigMappingVO;
import hisglobal.vo.EnclosureMasterVO;
import hisglobal.vo.EprTabAccessDtlVO;
import hisglobal.vo.MrdCheckListVO;
import hisglobal.vo.MrdMasterVO;
import hisglobal.vo.RackMstVO;
import hisglobal.vo.RackShelfMstVO;
import hisglobal.vo.RecordBoundingVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.RequestPurposeMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mrd.MrdConfig;
import mrd.masters.dao.DoctorDesigMappingDAO;
import mrd.masters.dao.DoctorDesigMappingDAOi;
import mrd.masters.dao.EnclosureMasterDAO;
import mrd.masters.dao.EnclosureMasterDAOi;
import mrd.masters.dao.EprTabAccessDtlDAO;
import mrd.masters.dao.EprTabAccessDtlDAOi;
import mrd.masters.dao.EprTabAccessUserWiseDAO;
import mrd.masters.dao.EprTabAccessUserWiseDAOi;
import mrd.masters.dao.EssentialDAO;
import mrd.masters.dao.EssentialDAOi;
import mrd.masters.dao.MrdCheckListMasterDAO;
import mrd.masters.dao.MrdCheckListMasterDAOi;
import mrd.masters.dao.MrdMasterDAO;
import mrd.masters.dao.MrdMasterDAOi;
import mrd.masters.dao.RackMstDAO;
import mrd.masters.dao.RackShelfMstDAO;
import mrd.masters.dao.RackShelfMstDAOi;
import mrd.masters.dao.RecordBoundingDAO;
import mrd.masters.dao.RecordBoundingDAOi;
import mrd.masters.dao.RecordTypeCheckListDAO;
import mrd.masters.dao.RecordTypeCheckListDAOi;
import mrd.masters.dao.RecordTypeWiseEnclosureMstDAO;
import mrd.masters.dao.RequestPurposeMstDAO;
import mrd.masters.dao.RequestPurposeMstDAOi;
import mrd.masters.dao.UnitWiseEstProcedureMappingMstDAO;
import mrd.transaction.dao.MrdEssentialDAO;
import mrd.transaction.dao.MrdEssentialDAOi;
import mrd.vo.UnitWiseEstProcedureMappingMstVO;

public class MrdMasterBO implements MrdMasterBOi{

	
	//*************************** Functions for Rack Shelf Master*********************************
	
	public Map getEssentialsForRackShelf(UserVO _uservo) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List  rackNameList=new ArrayList();
		//List  shelfList=new ArrayList();
		try
		{
			tx.begin();
			//RackShelfMstDAOi daoobj = new RackShelfMstDAO(tx);
			//rackNameList=daoobj.getRackNameList(_uservo);
			essentialMap.put(MrdConfig.RACK_NAME_LIST, rackNameList);
			//rackNameList=daoobj.getShelfList(_uservo);
			essentialMap.put(MrdConfig.SHELF_NAME_LIST, rackNameList);
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
/*	public List getShelfNotAssigned(String rackId, UserVO _uservo) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List  notAssignedShelfList=new ArrayList();
		try
		{
			tx.begin();
			RackShelfMstDAOi daoobj = new RackShelfMstDAO(tx);
			notAssignedShelfList=daoobj.getNotAssignedShelf(rackId, _uservo);
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return notAssignedShelfList;
	}
	
	public List getAssignedShelf(String rackId, UserVO _uservo) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List  assignedShelfList=new ArrayList();
		try
		{
			tx.begin();
			RackShelfMstDAOi daoobj = new RackShelfMstDAO(tx);
			assignedShelfList=daoobj.getAssignedShelf(rackId, _uservo);
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return assignedShelfList;
	}
	
	*/
	
	public boolean saveRackShelf(RackShelfMstVO insertRackShelfVO, UserVO _uservo) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		try
		{
			tx.begin();
			RackShelfMstDAOi daoobj = new RackShelfMstDAO(tx);
			if(daoobj.checkDuplicateBeforeSave(insertRackShelfVO, _uservo)){
				daoobj.create(insertRackShelfVO, _uservo);
				flag=true;
			}
			else{
				flag=false;
			}
			
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		
		return flag;
		
	}
	
	public boolean modifyRackShelf(RackShelfMstVO rackShelfVO, UserVO userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		try
		{
			tx.begin();
			RackShelfMstDAOi daoobj = new RackShelfMstDAO(tx);
			if(daoobj.checkDuplicateBeforeModify(rackShelfVO, userVO)){
				daoobj.modifyRackShelf(rackShelfVO, userVO);
				daoobj.modifyInsertRackShelf(rackShelfVO, userVO);
				flag=true;
			}
			else{
				flag= false;
			}
			
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	}
	
	//not in use
	public void modifyRackShelf(RackShelfMstVO[] updateRackShelfVO,RackShelfMstVO[] insertRackShelfVO, UserVO _uservo) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RackShelfMstVO  rackShelfVO=new RackShelfMstVO();
		try
		{
			tx.begin();
			//RackShelfMstDAOi daoobj = new RackShelfMstDAO(tx);
			for(int i=0;i<updateRackShelfVO.length;i++){
				rackShelfVO.setRackId(updateRackShelfVO[i].getRackId());
				//rackShelfVO.setShelfId(updateRackShelfVO[i].getShelfId());
				//daoobj.modifyRackShelf(rackShelfVO, _uservo);
			}
			for(int i=0;i<insertRackShelfVO.length;i++){
				rackShelfVO.setRackId(insertRackShelfVO[i].getRackId());
				//rackShelfVO.setShelfId(insertRackShelfVO[i].getShelfId());
				//daoobj.modifyInsertRackShelf(rackShelfVO, _uservo);
			}
			
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		
	}

	public RackShelfMstVO getRackShelfDetail(RackShelfMstVO rackShelfVO,UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			RackShelfMstDAOi daoobj = new RackShelfMstDAO(tx);
			rackShelfVO=daoobj.getRackShelfDetail(rackShelfVO, userVO);
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... Mrd Master BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rackShelfVO;
	}
	
	//not in use
	public Map getRackShelfDetail(String rackId, UserVO _uservo) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List assignedShelfList=new ArrayList();
		List notAssignedShelfList=new ArrayList();
		//String rackName;
		try
		{
			tx.begin();
			//RackShelfMstDAOi daoobj = new RackShelfMstDAO(tx);
			//rackName=daoobj.getRackName(rackId, _uservo);
			//essentialMap.put(MrdConfig.MRD_RACK_NAME, rackName);
			//assignedShelfList=daoobj.getAssignedShelf(rackId, _uservo);
			essentialMap.put(MrdConfig.ASSIGNED_SHELF_TO_RACK, assignedShelfList);
			//notAssignedShelfList=daoobj.getNotAssignedShelf(rackId, _uservo);
			essentialMap.put(MrdConfig.NOT_ASSIGNED_SHELF_TO_RACK, notAssignedShelfList);
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... Mrd Master BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	
	public Map getEssentialsForRecordTypeWiseEnclosure(UserVO _uservo) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List recordtypeList=new ArrayList();
		
		try
		{
			tx.begin();
			EssentialDAO daoobj = new EssentialDAO(tx);
		
			recordtypeList=daoobj.getRecordType( _uservo);
			essentialMap.put(MrdConfig.RECORD_TYPE, recordtypeList);
			
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... Mrd Master BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public void saveEnclosureRecord(List<RecordTypeWiseEnclosureMstVO> lstEnclosure, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			RecordTypeWiseEnclosureMstDAO enclosureMstDAO = new RecordTypeWiseEnclosureMstDAO(tx);
			
			enclosureMstDAO.updatePreviousRecord(lstEnclosure.get(0),_UserVO);
			for(int i=0;i<lstEnclosure.size();i++)
			{
				enclosureMstDAO.saveRecordEnclosure(lstEnclosure.get(i), _UserVO);
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
	}
	
	public RecordTypeWiseEnclosureMstVO fetchEnclosureRecord(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			RecordTypeWiseEnclosureMstDAO daoobj = new RecordTypeWiseEnclosureMstDAO(tx);
			_RecordTypeWiseEnclosureMstVO=daoobj.fetchEnclosureRecord(_RecordTypeWiseEnclosureMstVO, _UserVO);
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
		return _RecordTypeWiseEnclosureMstVO;
	}
	
	public String getRecordTypeName(String recordTypeId,UserVO _UserVO)
	{
		String recordTypeName=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			RecordTypeWiseEnclosureMstDAO daoobj = new RecordTypeWiseEnclosureMstDAO(tx);
			recordTypeName = daoobj.getRecordTypeName(recordTypeId, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return recordTypeName;
	}
	
	public boolean modifyEnclosureRecord(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag=true;
		try
		{
			tx.begin();

			RecordTypeWiseEnclosureMstDAO daoobj = new RecordTypeWiseEnclosureMstDAO(tx);
			

			String enclosureId=daoobj.checkDuplicateRecordForModifyEnclosureInfo(_RecordTypeWiseEnclosureMstVO, _UserVO);
			if(enclosureId==null)
			{
				daoobj.updateEnclosureRecord(_RecordTypeWiseEnclosureMstVO, _UserVO);
				daoobj.modifyEnclosureRecord(_RecordTypeWiseEnclosureMstVO, _UserVO);
				hasFlag=true;		
			}				
			else
			{
				hasFlag=false;
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
		return hasFlag;
	}
	
	// Rack Master
	
	
	public Map getEssentialForRackMst(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap =new HashMap();
		List mrdList=new ArrayList();
		List buildingList=new ArrayList();
		try
		{
			tx.begin();
			//RackMstDAO daoObj = new RackMstDAO(tx);
			EssentialDAOi essentialDAO = new EssentialDAO(tx);
			mrdList=essentialDAO.getMrdList(userVO);
			essentialMap.put(MrdConfig.ESSENTIAL_MRD_LIST_OPTION, mrdList);
			//buildingList=daoObj.getBuilding(userVO);
			buildingList=essentialDAO.getBuilding(userVO);
			essentialMap.put(MrdConfig.BUILDING_LIST, buildingList);
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
		return essentialMap;
	}
	
	
	
	
	public List getBuilding(UserVO userVO)

	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List buildingList=new ArrayList();
		try
		{
			tx.begin();
			//RackMstDAO daoObj = new RackMstDAO(tx);
			EssentialDAOi daoObj = new EssentialDAO(tx);
			buildingList=daoObj.getBuilding(userVO);
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
		return buildingList;
	}
	
	public List getItemList(UserVO userVO)

	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List itemList=new ArrayList();
		try
		{
			tx.begin();
			//RackMstDAO daoObj = new RackMstDAO(tx);
			EssentialDAO daoObj = new EssentialDAO(tx);
			itemList=daoObj.getItemList(userVO);
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
		return itemList;
	}
	
	public List getRackType(UserVO userVO)

	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rackType=new ArrayList();
		try
		{
			tx.begin();
			RackMstDAO daoObj = new RackMstDAO(tx);
			rackType=daoObj.getRackType(userVO);
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
		return rackType;
	}
	
	public List getBlockList(String buildingCode,UserVO userVO)

	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List blockList=new ArrayList();
		try
		{
			tx.begin();
			//RackMstDAO daoObj = new RackMstDAO(tx);
			EssentialDAOi daoObj = new EssentialDAO(tx);
			blockList=daoObj.getBlockList(buildingCode,userVO);
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
		return blockList;
	}
	
	public List getFloorList(String blockId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List floorList=new ArrayList();
		try
		{
			tx.begin();
			//RackMstDAO daoObj = new RackMstDAO(tx);
			EssentialDAOi daoObj = new EssentialDAO(tx);
			floorList=daoObj.getFloorList(blockId,userVO);
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
		return floorList;
	}
	
	public List getRoomList(String floorId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List areaRoomList=new ArrayList();
		try
		{
			tx.begin();
			//RackMstDAO daoObj = new RackMstDAO(tx);
			EssentialDAOi daoObj = new EssentialDAO(tx);
			areaRoomList=daoObj.getRoomList(floorId,userVO);
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
		return areaRoomList;
	}
	
	public boolean saveRackDetails(RackMstVO _RackMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag=true;
		try
		{
			tx.begin();

			RackMstDAO daoobj = new RackMstDAO(tx);
			
			
			String rackId=daoobj.checkDuplicateRack(_RackMstVO, _UserVO);
			if(rackId==null)
			{
				daoobj.saveRackDetails(_RackMstVO, _UserVO);
			  hasFlag=true;
			}else{
				hasFlag=false;
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
		return hasFlag;
	}

	public Map fetchRackDetails(RackMstVO _RackMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List areaBlockList=new ArrayList();
		List areaFloorList=new ArrayList();
		List areaRoomList=new ArrayList();
		List mrdList=new ArrayList();
		List buildingList=new ArrayList();
		Map essentianMap=new HashMap();
		try
		{
			tx.begin();

			RackMstDAO daoobj = new RackMstDAO(tx);
			EssentialDAOi essentialDAO = new EssentialDAO(tx);
			_RackMstVO=daoobj.fetchRackDetails(_RackMstVO, _UserVO);
			essentianMap.put(MrdConfig.RACK_MST_VO,_RackMstVO);

			String buildingCode=_RackMstVO.getBuildingCode();
			
			mrdList=essentialDAO.getMrdList(_UserVO);
			essentianMap.put(MrdConfig.ESSENTIAL_MRD_LIST_OPTION, mrdList);
			
			buildingList=essentialDAO.getBuilding(_UserVO);
			essentianMap.put(MrdConfig.BUILDING_LIST, buildingList);
			
			areaBlockList=essentialDAO.getBlockList(buildingCode,_UserVO);
			essentianMap.put(MrdConfig.BLOCK_LIST, areaBlockList);
			String blockId=_RackMstVO.getBlockId();
			areaFloorList=essentialDAO.getFloorList(blockId,_UserVO);
			essentianMap.put(MrdConfig.FLOOR_LIST, areaFloorList);
			String floorId=_RackMstVO.getFloorId();
			areaRoomList=essentialDAO.getRoomList(floorId,_UserVO);
			essentianMap.put(MrdConfig.ROOM_ID_LIST, areaRoomList);
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
		return essentianMap;
	}
	
	public String getRackTypeName(String rackTypeId,UserVO _UserVO)
	{
		String rackTypeName=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			RackMstDAO daoobj = new RackMstDAO(tx);
			rackTypeName = daoobj.getRackTypeName(rackTypeId, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return rackTypeName;
	}
	
	public boolean modifyRackDetails(RackMstVO _RackMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag=true;
		try
		{
			tx.begin();

			RackMstDAO daoobj = new RackMstDAO(tx);
			
			String rackId=daoobj.checkDuplicateForModifyRack(_RackMstVO, _UserVO);
			if(rackId==null)
			{
				daoobj.updateRackDetails(_RackMstVO, _UserVO);
				daoobj.modifyRackDetails(_RackMstVO, _UserVO);
				hasFlag=true;		
			}				
			else
			{
				hasFlag=false;
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
		return hasFlag;
	}
	
	/* ***********************Process wise designation Mapping **********************************/
	
	public List getProcessType()
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List processTypeList=null;
		try
		{
			tx.begin();

			DoctorDesigMappingDAOi daoobj = new DoctorDesigMappingDAO(tx);
			processTypeList=daoobj.getProcessType();
			
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
		return processTypeList;
	}
	
	public List getNotAssignedDesignationList(String processType,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List designationList=null;
		try
		{
			tx.begin();
			
			DoctorDesigMappingDAOi daoobj = new DoctorDesigMappingDAO(tx);
			designationList=daoobj.getNotAssignedDesignationList(processType, _userVO);
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
		return designationList;
	}
	
	
	public List getAssignedDesignationList(String processType,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List designationList=null;
		try
		{
			tx.begin();
			
			DoctorDesigMappingDAOi daoobj = new DoctorDesigMappingDAO(tx);
			designationList=daoobj.getAssignedDesignationList(processType, _userVO);
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
		return designationList;
	}
	
	public void saveProcessWiseDesig(DoctorDesigMappingVO []insertDoctorDesigMappingVO,DoctorDesigMappingVO []updateDoctorDesigMappingVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//List designationList=null;
		try
		{
			tx.begin();
			DoctorDesigMappingDAOi daoobj = new DoctorDesigMappingDAO(tx);
			for(int i=0;i<updateDoctorDesigMappingVO.length;i++){
				daoobj.modify(updateDoctorDesigMappingVO[i], userVO);
			}
			for(int i=0;i<insertDoctorDesigMappingVO.length;i++){
				daoobj.insert(insertDoctorDesigMappingVO[i], userVO);
			}
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
		
	}

	public Map getEssentialForProcessWiseDesig(String processType, UserVO userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List assignedDesignationList=null;
		List notAssignesDesignationList=null;
		Map essentialMap=new HashMap();
		try
		{
			tx.begin();
			DoctorDesigMappingDAOi daoobj = new DoctorDesigMappingDAO(tx);
			notAssignesDesignationList=daoobj.getNotAssignedDesignationList(processType, userVO);
			essentialMap.put(MrdConfig.DESIGNATION_LIST_NOT_MAPPED, notAssignesDesignationList);
			assignedDesignationList=daoobj.getAssignedDesignationList(processType, userVO);
			essentialMap.put(MrdConfig.DESIGNATION_LIST_MAPPED, assignedDesignationList);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			essentialMap.put(MrdConfig.DESIGNATION_LIST_MAPPED, new ArrayList());
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
		return essentialMap;
	}

	
	public String getRackNameByRackId(String rackId, UserVO userVO){
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String rackName="";
		try
		{
			tx.begin();
			RackMstDAO daoobj = new RackMstDAO(tx);
			rackName=daoobj.getRackName(rackId, userVO);
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
		return rackName;
	}

	
	public Map getEssentialForMrdMst(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List  allDeptList=new ArrayList();
		List  buildingList=new ArrayList();
		String mainMrdFlag="";
		try
		{
			tx.begin();
			EssentialDAOi essDao=new EssentialDAO(tx);
			MrdMasterDAOi mrdMstDao=new MrdMasterDAO(tx);
			
			allDeptList=essDao.getAllDepartment(userVO);
			essentialMap.put(MrdConfig.ESSENTIAL_ALL_DEPARTMENT_LIST,allDeptList);
			
			buildingList=essDao.getBuilding(userVO);
			essentialMap.put(MrdConfig.BUILDING_LIST, buildingList);
			
			mainMrdFlag=mrdMstDao.checkMainMrdExistence(userVO);
			essentialMap.put(MrdConfig.MAIN_MRD_FLAG_EXISTENCE_CHECK, mainMrdFlag);
			
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	
	
	public List getEmployeeListBasedOnDept(String deptCode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List  empList=new ArrayList();
		try
		{
			tx.begin();
			EssentialDAOi essDao=new EssentialDAO(tx);
			
			empList=essDao.getEmployeeListBasedOnDept(deptCode, userVO);
			
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return empList;
	}
	
	
	
	public boolean saveMrdDetails(MrdMasterVO mrdMstVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag=true;
		String existName="";
		String existShortName="";
		try
		{
			tx.begin();

			MrdMasterDAOi daoobj = new MrdMasterDAO(tx);
						
			existName=daoobj.checkDuplicateName(mrdMstVO,userVO);
			existShortName=daoobj.checkDuplicateShortName(mrdMstVO,userVO);
			if(existName.equals("0"))
			{
				if(existShortName.equals("0"))
				{
					daoobj.creat(mrdMstVO,userVO);
				}
				else
				{
					hasFlag=false;
					throw new HisDuplicateRecordException("Mrd Short Name Already Exists");
				}
			}
			else
			{
				hasFlag=false;
				throw new HisDuplicateRecordException("Mrd Name Already Exists");
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
		return hasFlag;
	}
	
	public Map getDataForModifyMrdMst(MrdMasterVO mrdMasterVO,
			UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List areaBlockList=new ArrayList();
		List areaFloorList=new ArrayList();
		List areaRoomList=new ArrayList();
		List buildingList=new ArrayList();
		List allDeptList=new ArrayList();
		List empList=new ArrayList();
		Map essentianMap=new HashMap();
		String mainMrdFlag="";
		
		try
		{
			tx.begin();

			MrdMasterDAOi daoObj=new MrdMasterDAO(tx);
			EssentialDAOi essentialDAO = new EssentialDAO(tx);
			
			mrdMasterVO=daoObj.getDataForModify(mrdMasterVO, userVO);
			essentianMap.put(MrdConfig.MRD_MST_VO,mrdMasterVO);
			
			areaBlockList=essentialDAO.getBlockList(mrdMasterVO.getBuildingCode(),userVO);
			essentianMap.put(MrdConfig.BLOCK_LIST, areaBlockList);
			
			areaFloorList=essentialDAO.getFloorList(mrdMasterVO.getNumBlockId(),userVO);
			essentianMap.put(MrdConfig.FLOOR_LIST, areaFloorList);
			
			areaRoomList=essentialDAO.getRoomList(mrdMasterVO.getNumFloorId(),userVO);
			essentianMap.put(MrdConfig.ROOM_ID_LIST, areaRoomList);
			
			buildingList=essentialDAO.getBuilding(userVO);
			essentianMap.put(MrdConfig.BUILDING_LIST, buildingList);
			
			allDeptList=essentialDAO.getAllDepartment(userVO);
			essentianMap.put(MrdConfig.ESSENTIAL_ALL_DEPARTMENT_LIST,allDeptList);
			
			empList=essentialDAO.getEmployeeListBasedOnDept(mrdMasterVO.getDeptCode(), userVO);
			essentianMap.put(MrdConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT,empList);
			
			mainMrdFlag=daoObj.checkMainMrdExistence(userVO);
			essentianMap.put(MrdConfig.MAIN_MRD_FLAG_EXISTENCE_CHECK, mainMrdFlag);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			//throw new HisRecordNotFoundException(e.getMessage());
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
		return essentianMap;
	}
	
	public boolean saveModMrdMaster(MrdMasterVO mrdMstVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String existName="";
		String existShortName="";
		try{
			tx.begin();
			
			MrdMasterDAOi daoObj = new MrdMasterDAO(tx);
			
			existName=daoObj.checkDuplicateNameForModify(mrdMstVO,userVO);
			existShortName=daoObj.checkDuplicateShortNameForModify(mrdMstVO,userVO);
			if(existName.equals("0"))
			{
				if(existShortName.equals("0")){
					daoObj.updateMrdMaster(mrdMstVO,userVO);
					daoObj.saveModMrdMaster(mrdMstVO,userVO);
					flag=true;
				}
				else
					throw new HisDuplicateRecordException("Mrd Short Name Already Exists");
			}
			else
			{
				throw new HisDuplicateRecordException("Mrd Name Already Exists");
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

	
	public void saveRecordBoundingDetail(List<RecordBoundingVO> lstRecBound,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			RecordBoundingDAOi recBoundDAO= new RecordBoundingDAO(tx);
			
			recBoundDAO.deletePrevRecord(lstRecBound.get(0),userVO);
			for(int i=0;i<lstRecBound.size();i++)
			{
				recBoundDAO.create(lstRecBound.get(i),userVO);
			}
			
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
	
	public Map getEssentialForEnclosureMapping(String recordTypeId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		String recordTypeName="";
		RecordTypeWiseEnclosureMstVO[] addedEnclosureVO=null;
		
		try
		{
			tx.begin();
			RecordTypeWiseEnclosureMstDAO objDAO = new RecordTypeWiseEnclosureMstDAO(tx);
			
			recordTypeName = objDAO.getRecordTypeName(recordTypeId, userVO);
			essentialMap.put(MrdConfig.RECORD_TYPE_NAME, recordTypeName);
			
			addedEnclosureVO=objDAO.getAddedRecordTypeEnclosure(recordTypeId,userVO);
			essentialMap.put(MrdConfig.ARR_ADDED_RECORD_TYPE_ENCLOSURE_VO, addedEnclosureVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return essentialMap;
	}
	
	public List getEnclosureRecordListNotMapped(String recordTypeId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstEncNotMap=new ArrayList();
		
		try
		{
			tx.begin();
			RecordTypeWiseEnclosureMstDAO enclosureDAO = new RecordTypeWiseEnclosureMstDAO(tx);
			
			lstEncNotMap=enclosureDAO.getEnclosureRecordListNotMapped(recordTypeId,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstEncNotMap;
	}

	
	public boolean saveReqPurposeMstDetail(RequestPurposeMstVO reqPurposeMstVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag=true;
		String existName="";
		
		try
		{
			tx.begin();

			RequestPurposeMstDAOi daoobj = new RequestPurposeMstDAO(tx);
						
			existName=daoobj.checkDuplicateName(reqPurposeMstVO,userVO);
			if(existName.equals("0"))
			{
				daoobj.creat(reqPurposeMstVO,userVO);
			}
			else
			{
				hasFlag=false;
				throw new HisDuplicateRecordException("Request Purpose Already Exists");
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
		return hasFlag;
	}
	
	public Map getDataForModifyReqPurposeMst(RequestPurposeMstVO reqPurposeMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RequestPurposeMstVO vo=new RequestPurposeMstVO();
		Map modifyMap=new HashMap();
		List allRecordTypeList=null;
		try
		{
			tx.begin();

			RequestPurposeMstDAOi daoObj=new RequestPurposeMstDAO(tx);
			EssentialDAOi essDao=new EssentialDAO(tx);
			
			
			allRecordTypeList=essDao.getAllMrdRecordType(_UserVO);
			modifyMap.put(MrdConfig.ALL_RECORD_TYPE_LIST_FOR_MRD, allRecordTypeList);
						
			vo=daoObj.getDataForModify(reqPurposeMstVO, _UserVO);
			modifyMap.put(MrdConfig.REQUEST_PURPOSE_MST_VO_FOR_MODIFY, vo);
			
			
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
		return modifyMap;
	}
	
	
	
	public boolean saveModReqPurposeMaster(RequestPurposeMstVO reqPurposeMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String existName="";
		
		try{
			tx.begin();
			
			RequestPurposeMstDAOi daoObj = new RequestPurposeMstDAO(tx);
			
			existName=daoObj.checkDuplicateNameForModify(reqPurposeMstVO,_UserVO);
			
			if(existName.equals("0"))
			{
				daoObj.updateReqPurposeMaster(reqPurposeMstVO, _UserVO);
				daoObj.saveModReqPurposeMaster(reqPurposeMstVO, _UserVO);
				flag=true;
			}
			else
			{
				throw new HisDuplicateRecordException("Request Purpose Already Exists");
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
	
	
	public Map getEssentialForReqPurposeMst(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List  allRecordTypeList=new ArrayList();
		
		try
		{
			tx.begin();
			EssentialDAOi essDao=new EssentialDAO(tx);
			
			
			allRecordTypeList=essDao.getAllMrdRecordType(userVO);
			essentialMap.put(MrdConfig.ALL_RECORD_TYPE_LIST_FOR_MRD, allRecordTypeList);
			
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	
	public boolean saveMrdCheckListMstDetail(MrdCheckListVO mrdCheckListMstVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag=true;
		String existName="";
		
		try
		{
			tx.begin();

			MrdCheckListMasterDAOi daoobj = new MrdCheckListMasterDAO(tx);
						
			existName=daoobj.checkDuplicateName(mrdCheckListMstVO,userVO);
			if(existName.equals("0"))
			{
				daoobj.creat(mrdCheckListMstVO,userVO);
			}
			else
			{
				hasFlag=false;
				throw new HisDuplicateRecordException("Request Purpose Already Exists");
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
		return hasFlag;
	}
	
	public Map getDataForModifyMrdCheckListMst(MrdCheckListVO mrdCheckListMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MrdCheckListVO vo=new MrdCheckListVO();
		Map modifyMap=new HashMap();
		
		try
		{
			tx.begin();

			MrdCheckListMasterDAOi daoobj = new MrdCheckListMasterDAO(tx);
									
			vo=daoobj.getDataForModify(mrdCheckListMstVO, _UserVO);
			modifyMap.put(MrdConfig.MRD_CHECK_LIST_MST_VO_FOR_MODIFY, vo);
			
			
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
		return modifyMap;
	}
	
	
	
	public boolean saveModMrdCheckListMaster(MrdCheckListVO mrdCheckListMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String existName="";
		
		try{
			tx.begin();
			
			MrdCheckListMasterDAOi daoobj = new MrdCheckListMasterDAO(tx);
			
			existName=daoobj.checkDuplicateNameForModify(mrdCheckListMstVO,_UserVO);
			
			if(existName.equals("0"))
			{
				daoobj.updateReqPurposeMaster(mrdCheckListMstVO, _UserVO);
				daoobj.saveModMrdCheckListMaster(mrdCheckListMstVO, _UserVO);
				flag=true;
			}
			else
			{
				throw new HisDuplicateRecordException("Request Purpose Already Exists");
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
	
	
	public boolean saveEnclosureMstDetail(EnclosureMasterVO enclosureMstVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag=true;
		String existName="";
		
		try
		{
			tx.begin();

			EnclosureMasterDAOi daoobj = new EnclosureMasterDAO(tx);
						
			existName=daoobj.checkDuplicateName(enclosureMstVO,userVO);
			if(existName.equals("0"))
			{
				daoobj.creat(enclosureMstVO,userVO);
			}
			else
			{
				hasFlag=false;
				throw new HisDuplicateRecordException("Request Purpose Already Exists");
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
		return hasFlag;
	}
	
	public Map getDataForModifyEnclosureMst(EnclosureMasterVO enclosureMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EnclosureMasterVO vo=new EnclosureMasterVO();
		Map modifyMap=new HashMap();
		
		try
		{
			tx.begin();

			EnclosureMasterDAOi daoobj = new EnclosureMasterDAO(tx);
									
			vo=daoobj.getDataForModify(enclosureMstVO, _UserVO);
			modifyMap.put(MrdConfig.ENCLOSURE_MST_VO_FOR_MODIFY, vo);
			
			
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
		return modifyMap;
	}
	
	
	
	public boolean saveModEnclosureMaster(EnclosureMasterVO enclosureMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String existName="";
		
		try{
			tx.begin();
			
			EnclosureMasterDAOi daoobj = new EnclosureMasterDAO(tx);
			
			existName=daoobj.checkDuplicateNameForModify(enclosureMstVO,_UserVO);
			
			if(existName.equals("0"))
			{
				daoobj.updateEnclosureMaster(enclosureMstVO, _UserVO);
				daoobj.saveModEnclosureMaster(enclosureMstVO,_UserVO);
				flag=true;
			}
			else
			{
				throw new HisDuplicateRecordException("Request Purpose Already Exists");
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
	
	public Map getEssentialForRecordTypeCheckListMst(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List  allRecordTypeList=new ArrayList();
		List allCheckListList=new ArrayList();
		
		try
		{
			tx.begin();
			EssentialDAOi essDao=new EssentialDAO(tx);
			MrdEssentialDAOi daoObj=new MrdEssentialDAO(tx);
			
			allRecordTypeList=essDao.getAllMrdRecordType(userVO);
			essentialMap.put(MrdConfig.ALL_RECORD_TYPE_LIST_FOR_MRD, allRecordTypeList);
			
			allCheckListList=(List)daoObj.getAllCheckListList(userVO);
			essentialMap.put(MrdConfig.ALL_CHECKLIST_LIST, allCheckListList);
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public Map getAllAddedCheckListByRecordType(RecordTypeCheckListMstVO vo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List  allAddedCheckListListByRecordType=null;
		
		try
		{
			tx.begin();
			RecordTypeCheckListDAOi daoObj=new RecordTypeCheckListDAO(tx);
			
			allAddedCheckListListByRecordType=(List) daoObj.getAllAddedCheckLstForRecordType(vo.getRecordTypeId(), vo.getCheckListMode(), userVO);
			essentialMap.put(MrdConfig.ALL_ADDED_CHECKLIST_LIST_BY_RECORDTYPE, allAddedCheckListListByRecordType);
			
			
			
		}	
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public List getCheckListNotMapped(RecordTypeCheckListMstVO recordTypeCheckListVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstcheckListNotMap=new ArrayList();
		
		try
		{
			tx.begin();
			RecordTypeCheckListDAOi daoObj = new RecordTypeCheckListDAO(tx);
			
			lstcheckListNotMap=daoObj.getCheckListNotMapped(recordTypeCheckListVO, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstcheckListNotMap;
	}
	
	
	public boolean saveRecordTypeCheckListMst(List recordTypeCheckListVOLst, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		
		
		try{
			tx.begin();
			
			RecordTypeCheckListDAOi daoobj = new RecordTypeCheckListDAO(tx);
			
			RecordTypeCheckListMstVO recordTypeCheckListVO=(RecordTypeCheckListMstVO)recordTypeCheckListVOLst.get(0);
			daoobj.update(recordTypeCheckListVO, _UserVO);
			
			for(int i=0;i<recordTypeCheckListVOLst.size();i++)
			{
				RecordTypeCheckListMstVO _recordTypeCheckListVO=(RecordTypeCheckListMstVO)recordTypeCheckListVOLst.get(i);
				daoobj.creat(_recordTypeCheckListVO, _UserVO);
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
	
	/**
	 * get all department unit list
	 */
	public Map getAllDeptUnitList(UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List allDepartmentList=null;
		List eprTabList=null;
		Map essentialMap=new HashMap();
		try{
			tx.begin();
			
			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);
			allDepartmentList=daoobj.getAllDeptUnitList(_UserVO);
			essentialMap.put(MrdConfig.ESSENTIAL_DEPARTMENT_UNIT_LIST, allDepartmentList);
			eprTabList=daoobj.getEprTabList(_UserVO);
			essentialMap.put(MrdConfig.ESSENTIAL_EPR_TAB_LIST, eprTabList);
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
		
	}

	
	public Map getTabAccessPolicy(EprTabAccessDtlVO eprTabAccessDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List eprTabAccessDtlVOList=null;
		List eprTabAccessUserWiseList=null;
		Map essentialMap=new HashMap();
		try{
			tx.begin();
			
			EprTabAccessDtlDAOi daoobj = new EprTabAccessDtlDAO(tx);
			EprTabAccessUserWiseDAOi eprTabAccessUserWiseDAO = new EprTabAccessUserWiseDAO(tx);
			eprTabAccessDtlVOList=daoobj.getTabAccessDtlByUnitCodePolicyType(eprTabAccessDtlVO, userVO);
			essentialMap.put(MrdConfig.EPR_TAB_ACCESS_DTL_VO_LIST, eprTabAccessDtlVOList);
			eprTabAccessUserWiseList=eprTabAccessUserWiseDAO.selectUsers(eprTabAccessDtlVO, userVO);
			essentialMap.put(MrdConfig.EPR_TAB_ACCESS_USER_WISE_LIST, eprTabAccessUserWiseList);
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
		
	}
	
	public Map getUsersForTabAccess(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List notAddedUserIdList=null;
		//List addedUserIdList=null;
		Map map=new HashMap();
		try{
			tx.begin();
			//EprTabAccessDtlDAOi daoobj = new EprTabAccessDtlDAO(tx);
			MrdEssentialDAOi essentialDao = new MrdEssentialDAO(tx);
			notAddedUserIdList=essentialDao.getUserIdList(userVO);
			map.put(MrdConfig.EPR_ACCESS_NOT_ADDED_USERID_LIST, notAddedUserIdList);
			
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return map;
		
	}
	
	
	public void saveTabAccessPolicy(List<EprTabAccessDtlVO> insertTabAccessDtlVOList,List<EprTabAccessDtlVO> 
					updateTabAccessDtlVOList,Map userTabIdMap, EprTabAccessDtlVO eprTabAccessVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//List eprTabAccessDtlVOList=null;
		//EprTabAccessDtlVO eprTabAccessVO=new EprTabAccessDtlVO();
		try{
			tx.begin();
			
			EprTabAccessDtlDAOi daoobj = new EprTabAccessDtlDAO(tx);
			EprTabAccessUserWiseDAOi eprAccessUserWiseDao = new EprTabAccessUserWiseDAO(tx);
			
			if(updateTabAccessDtlVOList!=null && updateTabAccessDtlVOList.size()>0){
				for(int i=0;i<updateTabAccessDtlVOList.size();i++){
					daoobj.updateIsValid(updateTabAccessDtlVOList.get(i), userVO);
				}
			}
			if(insertTabAccessDtlVOList!=null && insertTabAccessDtlVOList.size()>0){
				//eprTabAccessVO=insertTabAccessDtlVOList.get(0);
				for(int i=0;i<insertTabAccessDtlVOList.size();i++){
					daoobj.create(insertTabAccessDtlVOList.get(i), userVO);
				}
			}
			
			if(userTabIdMap!=null){
				Set mapkey=userTabIdMap.keySet();
				Iterator itr=mapkey.iterator();
				EprTabAccessDtlVO eprtabAccessDtlVO=null;
				List <Entry>list=new ArrayList<Entry>();
				while (itr.hasNext()) {
					String tabId = (String) itr.next();
					eprtabAccessDtlVO=new EprTabAccessDtlVO();
					HelperMethods.populate(eprtabAccessDtlVO, eprTabAccessVO);
					eprtabAccessDtlVO.setTabId(tabId);
					list=(List)userTabIdMap.get(tabId);
					if(userTabIdMap!=null)
					for(int i=0;i<list.size();i++){
						eprAccessUserWiseDao.update(eprtabAccessDtlVO, userVO);
						//eprtabAccessDtlVO.setUserId(list.get(i).getValue());
						//eprAccessUserWiseDao.create(eprtabAccessDtlVO, userVO);
					}
					for(int i=0;i<list.size();i++){
						//eprAccessUserWiseDao.update(eprtabAccessDtlVO, userVO);
						eprtabAccessDtlVO.setUserId(list.get(i).getValue());
						eprAccessUserWiseDao.create(eprtabAccessDtlVO, userVO);
					}
					
				}
			}
			
			
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
			System.out.println("error.... MrdMasterBO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		
	}
	
	
	// Added by Manisha Gangwar Date: 09.11.15 for Estimate Procedure Unit Mapping Procedure Mapping Master
	
	public void saveEstProcedureUnitList(List _UnitWiseEstProcedureMappingMstVOList, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			UnitWiseEstProcedureMappingMstDAO daoObj = new UnitWiseEstProcedureMappingMstDAO(tx);
			
			for (int i = 0; i < _UnitWiseEstProcedureMappingMstVOList.size(); i++)
			{

				UnitWiseEstProcedureMappingMstVO vo = (UnitWiseEstProcedureMappingMstVO) _UnitWiseEstProcedureMappingMstVOList.get(i);
				daoObj.create(vo, _userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
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
			System.out.println("error.... DisasterMasterBO BO");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}
	
	// Added by Manisha Gangwar Date: 09.11.15 for Estimate Procedure Unit Mapping Procedure Mapping Master
	
	public void modifySaveEstProcedureList(List _UnitWiseEstProcedureMappingMstVOLst, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			UnitWiseEstProcedureMappingMstDAO daoObj = new UnitWiseEstProcedureMappingMstDAO(tx);

			UnitWiseEstProcedureMappingMstVO unitProcedureMasterVO = (UnitWiseEstProcedureMappingMstVO) _UnitWiseEstProcedureMappingMstVOLst.get(0);
			daoObj.update(unitProcedureMasterVO.getDeptUnitCode(), _userVO);

			for (int i = 0; i < _UnitWiseEstProcedureMappingMstVOLst.size(); i++)
			{
				UnitWiseEstProcedureMappingMstVO vo = (UnitWiseEstProcedureMappingMstVO) _UnitWiseEstProcedureMappingMstVOLst.get(i);
				daoObj.create(vo, _userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
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
			System.out.println("error.... ProcedureUnitMappingBO BO");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	
	
	
}//end class






	