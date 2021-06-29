package medicalboard.masters.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.MBCertificateChecklistVO;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.MbInvestigationMappingMstVO;
import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.UserVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import medicalboard.MedicalBoardConfig;
import medicalboard.masters.dao.CertificateChecklistMasterDAO;
import medicalboard.masters.dao.CertificateChecklistMasterDAOi;
import medicalboard.masters.dao.BoardMasterDAO;
import medicalboard.masters.dao.BoardMasterDAOi;
import medicalboard.masters.dao.CertificateBoardMstDAO;
import medicalboard.masters.dao.CertificateBoardMstDAOi;
import medicalboard.masters.dao.CertificateTypeMstDAO;
import medicalboard.masters.dao.CertificateTypeMstDAOi;
import medicalboard.masters.dao.InvestigationMappingMstDAO;
import medicalboard.masters.dao.InvestigationMappingMstDAOi;
import medicalboard.masters.dao.MbMasterEssentialDAO;
import medicalboard.masters.dao.MbMasterEssentialDAOi;
import medicalboard.masters.dao.ChecklistMasterDAO;
import medicalboard.masters.dao.ChecklistMasterDAOi;
import medicalboard.masters.dao.OrganizationMstDAO;
import medicalboard.masters.dao.OrganizationMstDAOi;
import medicalboard.masters.dao.ReferMappingMstDAO;
import medicalboard.masters.dao.ReferMappingMstDAOi;
import medicalboard.masters.dao.RoleMasterDAO;
import medicalboard.masters.dao.RoleMasterDAOi;
import hisglobal.vo.MbCertificateBoardMstVO;
import hisglobal.vo.MbReferMappingMstVO;
import hisglobal.vo.MedicalBoardMasterVO;
import hisglobal.vo.RoleMasterVO;


public class MedicalBoardMasterBO implements MedicalBoardMasterBOi{
	
	public void saveCertificateTypeDtl(MbCertificateTypeMstVO mTypeMstVO,String[] districtId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String certificateTypeName="";
	    String certificateTypeId="";			
		try
		{
			tx.begin();
			CertificateTypeMstDAOi cMstDAOi=new CertificateTypeMstDAO(tx);
			certificateTypeName=cMstDAOi.checkDuplicateCertificateTypeName(mTypeMstVO, userVO);
			
			if(certificateTypeName.equals("0"))
			{
				certificateTypeId=cMstDAOi.getMaxCertificateTypeId(userVO);
				mTypeMstVO.setCertificateTypeID(certificateTypeId);
				if(mTypeMstVO.getMaxAge().equals(""))
				{mTypeMstVO.setMaxAge(null);}
				if(mTypeMstVO.getMinAge().equals(""))
				{
					mTypeMstVO.setMinAge(null);
				}
				
				cMstDAOi.create(mTypeMstVO,userVO);
			    if(districtId!=null){
				  for(int i=0;i<districtId.length;i++)
				   {
					  mTypeMstVO.setDistrictID(districtId[i]);
					  cMstDAOi.saveDistrict(mTypeMstVO, userVO);
				   }}
			}
			else
			{
				throw new HisDuplicateRecordException("Certificate Type Already Exists");
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
	
	
	public Map getDataCertificateType(MbCertificateTypeMstVO mTypeMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
 
		Map certificateDetailMap=new HashMap();
		List districtList=new ArrayList();
		List remainingDistrictList=new ArrayList();
		MbCertificateTypeMstVO vo=new MbCertificateTypeMstVO();
		try
		{
			tx.begin();
			CertificateTypeMstDAOi cMstDAOi=new CertificateTypeMstDAO(tx);
			 vo=cMstDAOi.getDataCertificateType(mTypeMstVO, _UserVO);
			 certificateDetailMap.put(MedicalBoardConfig.VO_OF_CERTIFICATE_DETAIL, vo);
			 districtList=cMstDAOi.getDataDistrictIDForModify(mTypeMstVO, _UserVO);
			 certificateDetailMap.put(MedicalBoardConfig.DISTRICTID_LIST_SELECTED, districtList);
			 remainingDistrictList=cMstDAOi.getRemainingDistrictIDForModify(mTypeMstVO, _UserVO);
			 certificateDetailMap.put(MedicalBoardConfig.DISTRICTID_LIST_REMAINING, remainingDistrictList);
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
		return certificateDetailMap;
	}
	
	
	public boolean saveModCertificateType(MbCertificateTypeMstVO mTypeMstVO,String[] districtId,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String certificateTypeName="";
		
		try{
			tx.begin();
			CertificateTypeMstDAOi cMstDAOi=new CertificateTypeMstDAO(tx);
			certificateTypeName=cMstDAOi.checkDuplicateCertificateNameForModify(mTypeMstVO, _UserVO);
			if(certificateTypeName.equals("0"))
			{
				if(mTypeMstVO.getMaxAge().equals(""))
				{mTypeMstVO.setMaxAge(null);}
				if(mTypeMstVO.getMinAge().equals(""))
				{
					mTypeMstVO.setMinAge(null);
				}
				cMstDAOi.updateCertificateType(mTypeMstVO, _UserVO);
				cMstDAOi.modifySaveCertificateType(mTypeMstVO, _UserVO);
				cMstDAOi.updateDistrictId(mTypeMstVO, _UserVO);
				if(districtId!=null){
				  for(int i=0;i<districtId.length;i++)
				   {
					  mTypeMstVO.setDistrictID(districtId[i]);
					  cMstDAOi.saveDistrict(mTypeMstVO, _UserVO);
				   }}
				flag=true;
			}
			else
			{
				throw new HisDuplicateRecordException("Certificate Type Already Exists");
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
			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
		
	}
	
	
	
	
	/////  Organization Detail//
	
	
	public void saveOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String organizationName="";
		try
		{
			tx.begin();
			OrganizationMstDAOi mstDAOi=new OrganizationMstDAO(tx);
			organizationName=mstDAOi.checkDuplicateOrganizationName(mbOrganizationMstVO, userVO);
			
			if(organizationName.equals("0"))
			{
				mstDAOi.create(mbOrganizationMstVO,userVO);
			}
			else
			{
				throw new HisDuplicateRecordException("Organization Already Exists");
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

	
	
	
	public MbOrganizationMstVO getOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
	
		MbOrganizationMstVO mbMstVO=new MbOrganizationMstVO();
		try
		{
			tx.begin();
			OrganizationMstDAOi mstDAOi=new OrganizationMstDAO(tx);
			mbMstVO=mstDAOi.getOrganizationDtl(mbOrganizationMstVO, _UserVO);
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
		return mbMstVO;
		
	}
	
	public boolean saveModOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String organizationName="";
		
		try{
			tx.begin();
			OrganizationMstDAOi mstDAOi=new OrganizationMstDAO(tx);
			organizationName=mstDAOi.checkDuplicateOrganizatioNameForModify(mbOrganizationMstVO, _UserVO);
			if(organizationName.equals("0"))
			{
				mstDAOi.updateOrganizationDtl(mbOrganizationMstVO, _UserVO);
				mstDAOi.modifySaveOrganizationDtl(mbOrganizationMstVO, _UserVO);
			}
			else
			{
				throw new HisDuplicateRecordException("Organization Already Exists");
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
			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
		
	}


	
	
	
	public void saveBoardInfo(MedicalBoardMasterVO mBoardMasterVO,String[] empId,String[] escortedBy,String[] roleID,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String boardName="";
	    String maxBoardId="";
		try
		{
			tx.begin();
			BoardMasterDAOi cMstDAOi=new BoardMasterDAO(tx);
			
			boardName=cMstDAOi.checkDuplicateBoardName(mBoardMasterVO, userVO);
			
			if(boardName.equals("0"))
			 {
				    maxBoardId=cMstDAOi.getMaxBoardId(userVO);
				    cMstDAOi.create(mBoardMasterVO, maxBoardId, userVO);
				if(empId!=null){ 
			    for(int i=0;i<empId.length; i++)
			      {
				   cMstDAOi.saveBoardTeamDetail(maxBoardId, empId[i], roleID[i], userVO);
			      }
				}
			   if(escortedBy!=null){
			    for(int i=0;i<escortedBy.length; i++)
			      {
				   String roleId="0";
				   cMstDAOi.saveBoardTeamDetail(maxBoardId, escortedBy[i], roleId, userVO);
			      }
			   }
		    } 
			else
			{
				throw new HisDuplicateRecordException("Board Name Already Exists");
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

	
	
	public Map fetchBoardDetail(MedicalBoardMasterVO medicalBoardMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
 
		Map boardDetailMap=new HashMap();
		
		MedicalBoardMasterVO[] masterVOs =null;
		List boardTypeList=new ArrayList();
		List empDoctorList=new ArrayList();
		List empEscortList=new ArrayList();
		List rollList=new ArrayList();
//		List boardIdList=new ArrayList();
		try
		{
			tx.begin();
			BoardMasterDAOi cMstDAOi=new BoardMasterDAO(tx);
			MbMasterEssentialDAOi mEssentialDAOi=new MbMasterEssentialDAO(tx); 
			
			masterVOs =cMstDAOi.getBoardDetail(medicalBoardMasterVO, _UserVO);
			boardDetailMap.put(MedicalBoardConfig.BOARD_DETAIL_VOS, masterVOs);
			
			boardTypeList=mEssentialDAOi.getBoardTypeList(_UserVO);
			boardDetailMap.put(MedicalBoardConfig.BOARD_TYPE_LIST, boardTypeList);
			
			
			empDoctorList=mEssentialDAOi.getEmpDoctorList(_UserVO);
			boardDetailMap.put(MedicalBoardConfig.LIST_OF_EMP_DOCTOR,empDoctorList);	
			
			empEscortList=mEssentialDAOi.getEmpEscortList(_UserVO);
			boardDetailMap.put(MedicalBoardConfig.LIST_OF_EMP_ESCORTS, empEscortList);
			
			rollList=mEssentialDAOi.getRollList(_UserVO);
			boardDetailMap.put(MedicalBoardConfig.LIST_OF_ROLL, rollList);
			
		//	boardIdList=cMstDAOi.getBoardDetail(medicalBoardMasterVO, _UserVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch(HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch(HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
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
		return boardDetailMap;
	}
	
	
	
	
	public void saveModBoardInfo(MedicalBoardMasterVO mBoardMasterVO,String[] empId,String[] escortedBy,String[] roleID,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String boardName="";
	    String maxBoardId="";
		try
		{
			tx.begin();
			BoardMasterDAOi cMstDAOi=new BoardMasterDAO(tx);
			
			boardName=cMstDAOi.checkDuplicateBoardNameForModify(mBoardMasterVO, userVO);
			
			if(boardName.equals("0"))
			 {
				cMstDAOi.updateBoardDetail(mBoardMasterVO, userVO);
				maxBoardId=mBoardMasterVO.getBoardID();
				    cMstDAOi.create(mBoardMasterVO,maxBoardId,userVO);
				
				    cMstDAOi.updateBoardTeamDetail(mBoardMasterVO, userVO);
			 if(empId!=null){	    
			    for(int i=0;i<empId.length; i++)
			      {
				   cMstDAOi.saveBoardTeamDetail(maxBoardId, empId[i], roleID[i], userVO);
			      }}
			 if(escortedBy!=null){
			    for(int i=0;i<escortedBy.length; i++)
			      {
				   String roleId="0";
				   cMstDAOi.saveBoardTeamDetail(maxBoardId, escortedBy[i], roleId, userVO);
			      }}
		    } 
			else
			{
				throw new HisDuplicateRecordException("Board Name Already Exists");
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

	

	/* *************************************checklist master********************************/
	
	public List getIsCompulsoryOptions(UserVO userVO){
		
		List isCompulsoryOptionsList=null;
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try{
			tx.begin();
			MbMasterEssentialDAOi essentialDAO = new MbMasterEssentialDAO(tx);
			isCompulsoryOptionsList=essentialDAO.getIsCompulsoryList(userVO);
			
		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){		
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e){
			e.printStackTrace();	
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
				
		return isCompulsoryOptionsList;
	}

	public boolean saveChecklistMst(MedicalBoardChecklistVO checklistVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String checklist=null;
		
		try{
			tx.begin();
			ChecklistMasterDAOi mstDAO=new ChecklistMasterDAO(tx);
			checklist=mstDAO.checkDuplicateChecklistBeforeSave(checklistVO, userVO);
			if(checklist==null)
			{
				mstDAO.create(checklistVO, userVO);
				flag=true;
			}
		}
		
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
		
	}
	
	
	public MedicalBoardChecklistVO getChecklistById(MedicalBoardChecklistVO checklistVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
				
		try{
			tx.begin();
			ChecklistMasterDAOi mstDAO=new ChecklistMasterDAO(tx);
			
			checklistVO=mstDAO.getChecklistById(checklistVO, userVO);
			
		}
		
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return checklistVO;
		
	}
	

	public boolean modifySaveChecklist(MedicalBoardChecklistVO checklistVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String checklist=null;
		
		try{
			tx.begin();
			ChecklistMasterDAOi mstDAO=new ChecklistMasterDAO(tx);
			checklist=mstDAO.checkDuplicateChecklistBeforeModify(checklistVO, userVO);
			if(checklist==null)
			{
				mstDAO.update(checklistVO, userVO);
				mstDAO.modifyInsert(checklistVO, userVO);
				flag=true;
			}
		}
		
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
		
	}
	
	
	/* *************************************certificate_checklist_master*************************/
	
	public List getCertificateTypeList(UserVO userVO){
		
		List certificateTypeList=null;
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try{
			tx.begin();
			MbMasterEssentialDAOi essentialDAO = new MbMasterEssentialDAO(tx);
			certificateTypeList=essentialDAO.getCertificateTypeList(userVO);
			
		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){		
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e){
			e.printStackTrace();	
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
				
		return certificateTypeList;
	}
	
	public Map getEssentialsForCertificateChecklist(String certificateTypeID,UserVO userVO)
	{	
		JDBCTransactionContext tx =new JDBCTransactionContext();
		String certificateTypeName="";
		Map essentialMap=new HashMap();
		List checklistAddedList=null;
		List checklistNotAddedList=null;
		List isCompulsoryOptionsList=null;
		try{
			tx.begin();
			MbMasterEssentialDAOi essentialDAO = new MbMasterEssentialDAO(tx);
			certificateTypeName=essentialDAO.getCertificateNameByID(certificateTypeID, userVO);
			essentialMap.put(MedicalBoardConfig.CERTIFICATE_TYPE_NAME, certificateTypeName);
			
			isCompulsoryOptionsList=essentialDAO.getIsCompulsoryList(userVO);
			essentialMap.put(MedicalBoardConfig.IS_COMPULSORY_OPTION_LIST, isCompulsoryOptionsList);
			
			checklistNotAddedList=essentialDAO.getChecklistNotAddedToCertificate(certificateTypeID, userVO);
			if(checklistNotAddedList!=null && checklistNotAddedList.size()==0) checklistNotAddedList=null;
			essentialMap.put(MedicalBoardConfig.CHECKLIST_NOT_ADDED_TO_CERTIFICATE_TYPE, checklistNotAddedList);
			
			checklistAddedList=essentialDAO.getAddedChecklistToCertificate(certificateTypeID, userVO);
			if(checklistAddedList!=null && checklistAddedList.size()==0) checklistAddedList=null;
			essentialMap.put(MedicalBoardConfig.CHECKLIST_ADDED_TO_CERTIFICATE_TYPE, checklistAddedList);
		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		
		return essentialMap;
	}
	
	
	public void saveCerificateChecklistMst(List certificateChecklist,UserVO userVO)
	{	
		JDBCTransactionContext tx =new JDBCTransactionContext();
				
		try{
			tx.begin();
			CertificateChecklistMasterDAOi certificateChecklistDAO = new CertificateChecklistMasterDAO(tx);
			for(int i=0;i<certificateChecklist.size();i++){
				certificateChecklistDAO.insert((MBCertificateChecklistVO)certificateChecklist.get(i), userVO);
			}
		}	
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		
	}
	
	
	public void saveCertificateChecklistMst(List certificateChecklist,List certificateChecklistUpdateVOList, UserVO userVO)
	{	
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try{
			tx.begin();
			CertificateChecklistMasterDAOi certificateChecklistDAO = new CertificateChecklistMasterDAO(tx);
			if(certificateChecklistUpdateVOList!=null)
			for(int i=0;i<certificateChecklistUpdateVOList.size();i++){
				certificateChecklistDAO.update((MBCertificateChecklistVO)certificateChecklistUpdateVOList.get(i), userVO);
				certificateChecklistDAO.insert((MBCertificateChecklistVO)certificateChecklistUpdateVOList.get(i), userVO);
			}
			if(certificateChecklist!=null)
			for(int i=0;i<certificateChecklist.size();i++){
				certificateChecklistDAO.insert((MBCertificateChecklistVO)certificateChecklist.get(i), userVO);
			}
		}	
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		
	}
	
	
	
	///// Certificate Board Mapping Master//
	
	public Map getBoard(MbCertificateBoardMstVO mBoardMstVO, UserVO _UserVO)
	 {
		JDBCTransactionContext tx = new JDBCTransactionContext();
 
		Map map=new HashMap();
		List selectedBoardList=new ArrayList();
		List unselectedBoardList=new ArrayList();
		try
		{
		   tx.begin();
		   CertificateBoardMstDAOi cMstDAOi=new CertificateBoardMstDAO(tx);
			
		    selectedBoardList=cMstDAOi.getSelectedBoard(mBoardMstVO, _UserVO);
			map.put(MedicalBoardConfig.SELECTED_BOARD_LIST, selectedBoardList);
			
			unselectedBoardList=cMstDAOi.getUnselectedBoard(mBoardMstVO, _UserVO);
			map.put(MedicalBoardConfig.UNSELECTED_BOARD_LIST, unselectedBoardList);
	
			
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
		return map;
	}
	
	
	
	public boolean saveModCertificateBoardInfo(MbCertificateBoardMstVO mBoardMstVO,String[] boardTypeIdArray,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String BoardId="";
		try{
			tx.begin();
			CertificateBoardMstDAOi cMstDAOi=new CertificateBoardMstDAO(tx);
			
				cMstDAOi.updateCertificateBoardDetail(mBoardMstVO, _UserVO);
				if(boardTypeIdArray!=null){
				  for(int i=0;i<boardTypeIdArray.length;i++)
				   {
					  BoardId=boardTypeIdArray[i];
					  cMstDAOi.create(mBoardMstVO, BoardId, _UserVO);
				   }}
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
			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
		
	}
	

	
	
	/// role Master //
	
	
/////  Organization Detail//
	
	
	public void saveRoleDtl(RoleMasterVO roleMasterVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String roleName="";
		try
		{
			tx.begin();
			RoleMasterDAOi mstDAOi=new RoleMasterDAO(tx);
			roleName=mstDAOi.checkDuplicateRoleName(roleMasterVO, userVO);
			
			if(roleName.equals("0"))
			{
				mstDAOi.create(roleMasterVO,userVO);
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

	
	
	
	public RoleMasterVO getRoleDtl(RoleMasterVO roleMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
	
		RoleMasterVO roleMstVO=new RoleMasterVO();
		try
		{
			tx.begin();
			RoleMasterDAOi mstDAOi=new RoleMasterDAO(tx);
			roleMstVO=mstDAOi.getRoleDtl(roleMasterVO, _UserVO);
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
		return roleMstVO;
		
	}
	
	public boolean saveModRoleDtl(RoleMasterVO roleMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		String roleName="";
		
		try{
			tx.begin();
			RoleMasterDAOi mstDAOi=new RoleMasterDAO(tx);
			roleName=mstDAOi.checkDuplicateRoleNameForModify(roleMasterVO, _UserVO);
			if(roleName.equals("0"))
			{
				mstDAOi.updateRoleDtl(roleMasterVO, _UserVO);
				mstDAOi.modifySaveRoleDtl(roleMasterVO, _UserVO);
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
			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
		
	}


    
	/// refer type Master ///
	
	public void saveReferMappingData(List selectedDeptOrUnit,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			ReferMappingMstDAOi cMstDAOi=new ReferMappingMstDAO(tx);
			
		   
			Iterator itr=selectedDeptOrUnit.iterator();
			 while(itr.hasNext())
			 {
				 MbReferMappingMstVO mappingMstVO=(MbReferMappingMstVO)itr.next();
				 cMstDAOi.create(mappingMstVO, userVO);
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

	
	
	
	
	public Map getReferMappingDetail(MbReferMappingMstVO mappingMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
 
		Map referDetailMap=new HashMap();
		
		MbReferMappingMstVO[] masterVOs =null;
		List certificatetypeList=new ArrayList();
		List departmentList=new ArrayList();
		List unitList=new ArrayList();
//		List departmentListOld=new ArrayList();
//		List unitListOld=new ArrayList();
		try
		{
			tx.begin();
			ReferMappingMstDAOi cMstDAOi=new ReferMappingMstDAO(tx);
			MbMasterEssentialDAOi mEssentialDAOi=new MbMasterEssentialDAO(tx); 
			
			masterVOs =cMstDAOi.getReferMappingDetail(mappingMstVO, _UserVO);
			referDetailMap.put(MedicalBoardConfig.REFER_DETAIL_VOS, masterVOs);
			
			certificatetypeList=mEssentialDAOi.getCertificateTypeList(_UserVO);
			referDetailMap.put(MedicalBoardConfig.CERTIFICATE_TYPE_LIST,certificatetypeList);	
			
			departmentList=mEssentialDAOi.getAllDepartmentList(_UserVO);
			referDetailMap.put(MedicalBoardConfig.ALL_DEPARTMENT_LIST, departmentList);
			
			unitList=mEssentialDAOi.getSpecialDeptUnitList(_UserVO);
			referDetailMap.put(MedicalBoardConfig.ALL_UNIT_LIST, unitList);
			
			//departmentListOld=mEssentialDAOi.getAllDepartmentList(_UserVO);
			referDetailMap.put(MedicalBoardConfig.ALL_DEPARTMENT_LIST_OLD, departmentList);
			
			//unitListOld=mEssentialDAOi.getSpecialDeptUnitList(_UserVO);
			referDetailMap.put(MedicalBoardConfig.ALL_UNIT_LIST_OLD, unitList);
			
			
		/*	boardTypeList=mEssentialDAOi.getBoardTypeList(_UserVO);
			boardDetailMap.put(MedicalBoardConfig.BOARD_TYPE_LIST, boardTypeList);
			
			
			empDoctorList=mEssentialDAOi.getEmpDoctorList(_UserVO);
			boardDetailMap.put(MedicalBoardConfig.LIST_OF_EMP_DOCTOR,empDoctorList);	
			
			empEscortList=mEssentialDAOi.getEmpEscortList(_UserVO);
			boardDetailMap.put(MedicalBoardConfig.LIST_OF_EMP_ESCORTS, empEscortList);
			
			rollList=mEssentialDAOi.getRollList(_UserVO);
			boardDetailMap.put(MedicalBoardConfig.LIST_OF_ROLL, rollList);*/
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch(HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch(HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
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
		return referDetailMap;
	}
	
	
	
	
	public void saveModReferMappingData(List selectedDeptOrUnit,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String certificateTypeId=""; 
		try
		{
			tx.begin();
			ReferMappingMstDAOi cMstDAOi=new ReferMappingMstDAO(tx);
			Iterator itr=selectedDeptOrUnit.iterator();
			MbReferMappingMstVO mstVO=(MbReferMappingMstVO)itr.next();
			 certificateTypeId=mstVO.getCertificateTypeID();  
			 cMstDAOi.updateReferDetail(certificateTypeId, userVO);
			
		  Iterator itr1=selectedDeptOrUnit.iterator();
			 while(itr1.hasNext())
			 {
				 MbReferMappingMstVO mappingMstVO=(MbReferMappingMstVO)itr1.next();
				 cMstDAOi.create(mappingMstVO, userVO);
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

	public void saveInvestigationMappintDtl(List selInvastMapVOList,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
//		String certificateTypeId=""; 
		try
		{
			tx.begin();
			InvestigationMappingMstDAOi daoObj=new InvestigationMappingMstDAO(tx);
			
			for(int i=0;i<selInvastMapVOList.size();i++)
			{
				MbInvestigationMappingMstVO investMapVO=(MbInvestigationMappingMstVO)selInvastMapVOList.get(i);
				daoObj.create(investMapVO, userVO);
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
	
	public void updateInvestigationMappintDtl(List selInvastMapVOList,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			InvestigationMappingMstDAOi daoObj=new InvestigationMappingMstDAO(tx);
			
			MbInvestigationMappingMstVO investVO=(MbInvestigationMappingMstVO)selInvastMapVOList.get(0);
			
			daoObj.updateInvestigationMappingDtl(investVO, userVO);
			
			
			for(int i=0;i<selInvastMapVOList.size();i++)
			{
				MbInvestigationMappingMstVO investMappingVO=(MbInvestigationMappingMstVO)selInvastMapVOList.get(i);
				daoObj.create(investMappingVO, userVO);
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
	
	
	
 }
