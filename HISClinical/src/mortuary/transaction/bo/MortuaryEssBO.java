package mortuary.transaction.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.AddressVO;
import hisglobal.vo.ChamberRackMasterVO;
import hisglobal.vo.ConsentMappingMasterVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedIdentityDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.MortuaryExtLabInvReqDtlVO;
import hisglobal.vo.MortuaryExtLabRequestDtlVO;
import hisglobal.vo.MortuaryExtReqSampleDtlVO;
import hisglobal.vo.MortuaryPoliceVerificationVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PoliceVerificationDtlVO;
import hisglobal.vo.PostmortemDetailVO;
import hisglobal.vo.PostmortemItemDtlVO;
import hisglobal.vo.PostmortemItemReqDtlVO;
import hisglobal.vo.PostmortemOpinionDetailVO;
import hisglobal.vo.PostmortemOpinionReqDtlVO;
import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.PostmortemTeamDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import registration.RegistrationConfig;
import registration.dao.EssentialDAO;

import mortuary.MortuaryConfig;
import mortuary.masters.dao.MortuaryMstEssentialDAO;
import mortuary.masters.dao.MortuaryMstEssentialDAOi;
import mortuary.transaction.dao.DeceasedDetailDAO;
import mortuary.transaction.dao.DeceasedDetailDAOi;
import mortuary.transaction.dao.DeceasedIdentityDtlDAO;
import mortuary.transaction.dao.DeceasedIdentityDtlDAOi;
import mortuary.transaction.dao.DeceasedImageDtlDAO;
import mortuary.transaction.dao.DeceasedImageDtlDAOi;
import mortuary.transaction.dao.DeceasedRelativeDtlDAO;
import mortuary.transaction.dao.DeceasedRelativeDtlDAOi;
import mortuary.transaction.dao.ExtLabInvReqDtlDAO;
import mortuary.transaction.dao.ExtLabInvReqDtlDAOi;
import mortuary.transaction.dao.ExtLabReqDtlDAO;
import mortuary.transaction.dao.ExtLabReqDtlDAOi;
import mortuary.transaction.dao.ExtReqSampleDtlDAO;
import mortuary.transaction.dao.ExtReqSampleDtlDAOi;
import mortuary.transaction.dao.MortuaryEssDAO;
import mortuary.transaction.dao.MortuaryEssDAOi;
import mortuary.transaction.dao.MortuaryPoliceVerificationDAO;
import mortuary.transaction.dao.MortuaryPoliceVerificationDAOi;
import mortuary.transaction.dao.PostmortemDetailDAO;
import mortuary.transaction.dao.PostmortemDetailDAOi;
import mortuary.transaction.dao.PostmortemExamDtlDAO;
import mortuary.transaction.dao.PostmortemExamDtlDAOi;
import mortuary.transaction.dao.PostmortemHandoverDtlDAO;
import mortuary.transaction.dao.PostmortemHandoverDtlDAOi;
import mortuary.transaction.dao.PostmortemItemDtlDAO;
import mortuary.transaction.dao.PostmortemItemDtlDAOi;
import mortuary.transaction.dao.PostmortemItemReqDAO;
import mortuary.transaction.dao.PostmortemItemReqDAOi;
import mortuary.transaction.dao.PostmortemOpinionDetailDAO;
import mortuary.transaction.dao.PostmortemOpinionDetailDAOi;
import mortuary.transaction.dao.PostmortemOpinionReqDAO;
import mortuary.transaction.dao.PostmortemOpinionReqDAOi;
import mortuary.transaction.dao.PostmortemRequiredDetailDAO;
import mortuary.transaction.dao.PostmortemRequiredDetailDAOi;
import mortuary.transaction.dao.PostmortemTeamDetailDAO;
import mortuary.transaction.dao.PostmortemTeamDetailDAOi;
import mrd.MrdConfig;

public class MortuaryEssBO implements MortuaryEssBOi
{
	//Getting The List of Peon on The Basis of Department Code Who Brought The Dead Body
	public List getPeonList(String deptCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstPeon=null;
		String processId="";
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO= new MortuaryEssDAO(tx);
			processId=MortuaryConfig.DECEASED_ACCEPTANCE_BROUGHT_BY_PEON;
			lstPeon=essDAO.getEmployeeListDeptProcessWise(processId,deptCode,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return lstPeon;
	}
	
	//Getting Essential Data
	public Map getDeceasedEssential(String deptCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List lstRelation=new ArrayList();
		List lstChamber=new ArrayList();
		ChamberRackMasterVO[] chamberRackVO=null;
		List mortuaryEmpList= new ArrayList();
		String processId="";
		
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi essDAO=new MortuaryMstEssentialDAO(tx);
			MortuaryEssDAOi dao=new MortuaryEssDAO(tx);
			
			lstRelation=essDAO.getAllRelation(userVO);
			essentialMap.put(MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION, lstRelation);
			
			lstChamber=essDAO.getAllChamber(userVO);
			essentialMap.put(MortuaryConfig.ESENTIAL_ALL_CHAMBER, lstChamber);
			
			chamberRackVO=essDAO.getAllChamberRack(userVO);
			essentialMap.put(MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_VO, chamberRackVO );
			
			processId=MortuaryConfig.DECEASED_ACCEPTANCE_BROUGHT_BY_PEON;
			mortuaryEmpList=dao.getEmployeeListDeptProcessWise(processId,deptCode, userVO);
			essentialMap.put(MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST, mortuaryEmpList );
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	//Getting Deceased Address
	public AddressVO getPatAddress(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		AddressVO patAddressVO=null;
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO= new MortuaryEssDAO(tx);
			patAddressVO=essDAO.getPatAddress(crNo,userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			patAddressVO=null;
			//throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patAddressVO;
	}
	
	//Getting Police Verification Detail
	public PoliceVerificationDtlVO getPoliceVerificationDetail(String mlcNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PoliceVerificationDtlVO policeVerVO=null;
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO= new MortuaryEssDAO(tx);
			policeVerVO=essDAO.getPoliceVerificationDetail(mlcNo,userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return policeVerVO;
	}
	
	//Getting Deceased Existing Image
	public PatientImageDtlVO[] getDeceasedExistingImage(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientImageDtlVO[] deceasedImageVO=null;
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO= new MortuaryEssDAO(tx);
			deceasedImageVO=essDAO.getDeceasedExistingImage(crNo,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return deceasedImageVO;
	}
	
	public Map getDeceasedEssentialStorageDetail(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List lstChamber=new ArrayList();
		List lstMortuary=new ArrayList();
		List lstRelation=new ArrayList();
		ChamberRackMasterVO[] chamberRackVO=null;
		List mortuaryEmpList= new ArrayList();
		String deptCode="102";
		String processId="";
		
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi essDAO=new MortuaryMstEssentialDAO(tx);
			MortuaryEssDAOi dao=new MortuaryEssDAO(tx);
			
			lstChamber=essDAO.getAllChamber(userVO);
			essentialMap.put(MortuaryConfig.ESENTIAL_ALL_CHAMBER, lstChamber);
			
			lstMortuary=essDAO.getAllMortuary(userVO);
			essentialMap.put(MortuaryConfig.ESENTIAL_ALL_MORTUARY, lstMortuary);
			
			chamberRackVO=essDAO.getAllChamberRack(userVO);
			essentialMap.put(MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_VO, chamberRackVO );
			
			processId=MortuaryConfig.DECEASED_ACCEPTANCE_BROUGHT_BY_PEON;
			mortuaryEmpList=dao.getEmployeeListDeptProcessWise(processId,deptCode, userVO);
			essentialMap.put(MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST, mortuaryEmpList );
			
			lstRelation=essDAO.getAllRelation(userVO);
			essentialMap.put(MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION, lstRelation);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public List getAllRelationship(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstRelation=new ArrayList();
		
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi essDAO=new MortuaryMstEssentialDAO(tx);
			lstRelation=essDAO.getAllRelation(userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return lstRelation;
	}
	
	public DeceasedRelativeDtlVO[] getExistingRelative(String deceasedNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedRelativeDtlVO[] relativeVO=null;
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO= new MortuaryEssDAO(tx);
			relativeVO=essDAO.getExistingRelative(deceasedNo,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return relativeVO;
	}
	
	public Map getEssentialForPostmortemRequest(String deceasedNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List lstOpinion=new ArrayList();
		List lstdeceasedItem=new ArrayList();
		List lstRelation=new ArrayList();
		MortuaryPoliceVerificationVO policeVerVO=null;
		DeceasedRelativeDtlVO[] relativeVO=null; 
		ConsentMappingMasterVO[] consentMappingVO=null;
		
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi essDAO=new MortuaryMstEssentialDAO(tx);
			MortuaryEssDAOi essenilDAO=new MortuaryEssDAO(tx);
			MortuaryPoliceVerificationDAOi policeDAO=new MortuaryPoliceVerificationDAO(tx);
			DeceasedRelativeDtlDAOi relativeDAO=new DeceasedRelativeDtlDAO(tx);
			
			
			lstOpinion=essDAO.getAllOpinion(userVO);
			essentialMap.put(MortuaryConfig.ESSENTIAL_ALL_OPINION, lstOpinion);
			
			lstdeceasedItem=essDAO.getAllDeceasedItem(userVO);
			essentialMap.put(MortuaryConfig.ESSENTIAL_ALL_DECEASED_ITEM, lstdeceasedItem);
			
			lstRelation=essDAO.getAllRelation(userVO);
			essentialMap.put(MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION, lstRelation);
			
			policeVerVO=policeDAO.getExistingPoliceVerDetail(deceasedNo,userVO);
			essentialMap.put(MortuaryConfig.EXISTING_POLICE_VERIFICATION_DETAIL, policeVerVO);
			
			relativeVO=relativeDAO.getDeceasedExistingRelative(deceasedNo,userVO);
			essentialMap.put(MortuaryConfig.ARR_DECEASED_EXISTING_RELATIVE_VO, relativeVO);
			
			consentMappingVO=essenilDAO.getConsentMappingDetail(userVO);
			essentialMap.put(MortuaryConfig.ARR_CONSENT_MAPPING_DETAIL, consentMappingVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public DeceasedDetailVO[] getDeceasedListForPostmortemEntry(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedDetailVO[] deceasedVO=null;
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO= new MortuaryEssDAO(tx);
			deceasedVO=essDAO.getDeceasedListForPostmortemEntry(userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return deceasedVO;
	}
	
	public Map getPostmortemConductedEssential(String dob, String postmortemId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap=new HashMap(); 
	//	List lstEmp=new ArrayList();
	//	List lstRole=new ArrayList();
		List lstIncisionType=new ArrayList();
	//	List lstAutopsyType=new ArrayList();
		List lstInjuryNature=new ArrayList();
		List lstInjuryType=new ArrayList();
		List lstOpinionNotReq=new ArrayList();
		List lstAllOpinion=new ArrayList();
		List lstTemplateId=new ArrayList();
		List lstOpinionNotAdded=new ArrayList();
		PostmortemOpinionReqDtlVO[] opinionReqVO=null;
		String deptCode="";
		String autopsyCatId="";
		List lstDeathManner=new ArrayList();
		
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi mortuaryEssDAO= new MortuaryMstEssentialDAO(tx);
			PostmortemOpinionReqDAOi opinionReqDAO=new PostmortemOpinionReqDAO(tx);
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			
			autopsyCatId=essDAO.getAutopsyCategoryId(dob,userVO);
			
			lstTemplateId=essDAO.getTemplateIdForPostmoprtemEntry(autopsyCatId,userVO);
			essMap.put(MortuaryConfig.TEMPLATE_ID_LIST_FOR_POSTMORTEM, lstTemplateId);
			
			/*lstEmp=essDAO.getEmployeeListDeptProcessWise(deptCode, userVO);
			essMap.put(MortuaryConfig.POSTMORTEM_CONDUCTED_BY_LIST, lstEmp);
			
			lstRole=mortuaryEssDAO.getPostmortemRole(userVO);
			essMap.put(MortuaryConfig.POSTMORTEM_CONDUCTED_BY_ROLE, lstRole);*/
			
			lstIncisionType=mortuaryEssDAO.getIncisionTypeList(userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_INCISION_TYPE_LIST, lstIncisionType);
			
			/*lstAutopsyType=mortuaryEssDAO.getAutopsyTypeList(userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_AUTOPSY_TYPE_LIST, lstAutopsyType);*/
			
			lstInjuryNature=mortuaryEssDAO.getInjuryNatureList(userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_INJURY_NATURE_LIST, lstInjuryNature);
			
			lstInjuryType=mortuaryEssDAO.getInjuryTypeList(userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_INJURY_TYPE_LIST, lstInjuryType);
			
			opinionReqVO=opinionReqDAO.getRequestedOpinion(postmortemId,userVO);
			essMap.put(MortuaryConfig.ARR_OPINION_REQUESTED_VO, opinionReqVO);
			
			lstOpinionNotReq=mortuaryEssDAO.getOpinionListNotRequested(postmortemId,userVO);
			essMap.put(MortuaryConfig.OPINION_LIST_NOT_REQUESTED, lstOpinionNotReq);
			
			lstAllOpinion=mortuaryEssDAO.getAllOpinion(userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_ALL_OPINION, lstAllOpinion);
			
			lstOpinionNotAdded=mortuaryEssDAO.getOpinionNotAdded(postmortemId,userVO);
			essMap.put(MortuaryConfig.OPINION_LIST_NOT_ADDED, lstOpinionNotAdded);
			
			lstDeathManner=mortuaryEssDAO.getDeathMannerList(userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_DEATH_MANNER_LIST, lstDeathManner);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essMap;
	}
	
	public boolean checkExistingRecord(String postmoretmId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean exist=false;
		
		try
		{
			tx.begin();
			PostmortemDetailDAOi postmortemDAO=new PostmortemDetailDAO(tx);
			exist=postmortemDAO.checkExistingRecord(postmoretmId,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return exist;
	}
	
	public PostmortemItemReqDtlVO[] getItemToBePreserved(String postmortemId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PostmortemItemReqDtlVO[] itemReqVO=null;
		
		try
		{
			tx.begin();
			PostmortemItemReqDAOi itemReqDAO=new PostmortemItemReqDAO(tx);
			itemReqVO=itemReqDAO.getItemToBePreserved(postmortemId,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return itemReqVO;
	}
	
	public boolean checkBodyIdentified(String deceasedNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean exist=false;
		
		try
		{
			tx.begin();
			DeceasedIdentityDtlDAOi identityDAO=new DeceasedIdentityDtlDAO(tx);
			exist=identityDAO.checkBodyIdentified(deceasedNo,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return exist;
	}
	
	public MortuaryDeceasedImageDtlVO[] getExistingPhoto(String deceasedNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MortuaryDeceasedImageDtlVO[] imageVO=null;
		
		try
		{
			tx.begin();
			DeceasedImageDtlDAOi imageDAO=new DeceasedImageDtlDAO(tx);
			imageVO=imageDAO.getExistingPhotoByDeceasedNo(deceasedNo,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return imageVO;
	}
	
	public Map getEssentialForInjuries(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap=new HashMap(); 
		List lstDeathManner=new ArrayList();
		List lstInjuryNature=new ArrayList();
		List lstInjuryType=new ArrayList();
		
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi mortuaryEssDAO= new MortuaryMstEssentialDAO(tx);
			
			lstDeathManner=mortuaryEssDAO.getDeathMannerList(userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_DEATH_MANNER_LIST, lstDeathManner);
			
			lstInjuryNature=mortuaryEssDAO.getInjuryNatureList(userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_INJURY_NATURE_LIST, lstInjuryNature);
			
			lstInjuryType=mortuaryEssDAO.getInjuryTypeList(userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_INJURY_TYPE_LIST, lstInjuryType);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essMap;
	}
	
	public PatientDeathDetailVO getExistingInjuryDetail(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientDeathDetailVO deathVO=new PatientDeathDetailVO();
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO= new MortuaryEssDAO(tx);
			deathVO=essDAO.getExistingInjuryDetail(crNo,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return deathVO;
	}
	
	public boolean getGeneralAppearanceExsistance(String postmortemId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean exist=false;
		
		try
		{
			tx.begin();
			PostmortemDetailDAOi postmortemDAO=new PostmortemDetailDAO(tx);
			exist=postmortemDAO.checkExistingRecord(postmortemId, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return exist;
	}
	
	public PostmortemDetailVO getAddedInjuryDetailToDisplay(String postmortemId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PostmortemDetailVO injuryVO=new PostmortemDetailVO();
		
		try
		{
			tx.begin();
			PostmortemDetailDAOi postmortemDAO=new PostmortemDetailDAO(tx) ;
			injuryVO=postmortemDAO.getAddedInjuryDetailToDisplay(postmortemId,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return injuryVO;
	}
	
	public DeceasedIdentityDtlVO[] getIdentifiedByDetail(String deceasedNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedIdentityDtlVO[] identifyVO=null;
		
		try
		{
			tx.begin();
			DeceasedIdentityDtlDAOi identityDAO=new DeceasedIdentityDtlDAO(tx);
			identifyVO=identityDAO.getIdentifiedByDetailByDeceasedNo(deceasedNo,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return identifyVO;
	}
	
	public PostmortemDetailVO getExistingGeneralAppearance(String postmortemId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PostmortemDetailVO generalAppearanceVO=new PostmortemDetailVO();
		
		try
		{
			tx.begin();
			PostmortemDetailDAOi postmortemDAO=new PostmortemDetailDAO(tx) ;
			generalAppearanceVO=postmortemDAO.getExistingGeneralAppearance(postmortemId,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return generalAppearanceVO;
	}
	
	public String getPatientIdMark(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String patIdMark="";
		
		try
		{
			tx.begin();
			MortuaryEssDAOi mortuaryEssDAO=new MortuaryEssDAO(tx);
			patIdMark=mortuaryEssDAO.getPatientIdMark(crNo,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patIdMark;
	}
	
	public Map getEssentialForTeamDetail(String postmortemId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap=new HashMap(); 
		List lstEmp=new ArrayList();
		List lstRole=new ArrayList();
		PostmortemTeamDetailVO[] addedTeamVO=null;
		String deptCode="";
		String processId="";
		
		try
		
		{
			tx.begin();
			MortuaryMstEssentialDAOi mortuaryEssDAO= new MortuaryMstEssentialDAO(tx);
			MortuaryEssDAOi essDAO= new MortuaryEssDAO(tx);
			PostmortemTeamDetailDAOi teamDAO=new PostmortemTeamDetailDAO(tx);
			
			processId=MortuaryConfig.POSTMORTEM_REQUEST_APPROVED;
			lstEmp=essDAO.getEmployeeListDeptProcessWise(processId,deptCode, userVO);
			essMap.put(MortuaryConfig.POSTMORTEM_CONDUCTED_BY_LIST, lstEmp);
			
			lstRole=mortuaryEssDAO.getPostmortemRole(userVO);
			essMap.put(MortuaryConfig.POSTMORTEM_CONDUCTED_BY_ROLE, lstRole);
			
			addedTeamVO=teamDAO.getAddedTeamMember(postmortemId,userVO);
			essMap.put(MortuaryConfig.ARR_ADDED_TEAM_MEMBER_VO, addedTeamVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essMap;
	}
	
	public PostmortemOpinionDetailVO[] getAddedOpinion(String postmortemId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PostmortemOpinionDetailVO[] opinionVO=null;
		
		try
		{
			tx.begin();
			PostmortemOpinionDetailDAOi opinionDtlDAO=new PostmortemOpinionDetailDAO(tx);
			opinionVO=opinionDtlDAO.getAddedOpinion(postmortemId,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return opinionVO;
	}
	
	public List getChamberBasedOnMortuaryArea(String mortuaryArea,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstChamber=new ArrayList();
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi mortuaryEssDAO= new MortuaryMstEssentialDAO(tx);
			lstChamber=mortuaryEssDAO.getAllChamberBasedOnMortuaryArea(mortuaryArea, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return lstChamber;
	}
	
	public List getAreaBasedOnMortuary(String mortuary,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstArea=new ArrayList();
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi mortuaryEssDAO= new MortuaryMstEssentialDAO(tx);
			lstArea=mortuaryEssDAO.getAllAreaBasedOnMortuary(mortuary, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return lstArea;
	}
	
	public Map<String, Map<String, String>> getTemplateDetail(String postmortemId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, Map<String, String>> mpTempParaVals = null;
		
		try
		{
			tx.begin();
			PostmortemExamDtlDAOi postmortemExamDAO=new PostmortemExamDtlDAO(tx);
			mpTempParaVals = postmortemExamDAO.getTemplateDetail(postmortemId,userVO);
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
		return mpTempParaVals;
	}
	
	public PostmortemRequestDetailVO[] getPostmortemReqList(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PostmortemRequestDetailVO[] postmortemReqVO=null;
		
		try
		{
			tx.begin();
			PostmortemRequiredDetailDAOi postReqDAO=new PostmortemRequiredDetailDAO(tx);
			postmortemReqVO=postReqDAO.getPostmortemReqList(userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return postmortemReqVO;
	}
	
	public PostmortemRequestDetailVO getPostmortemRequestDetail(String postmortemId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PostmortemRequestDetailVO postmortemReqVO=new PostmortemRequestDetailVO();
		
		try
		{
			tx.begin();
			PostmortemRequiredDetailDAOi postmortemReqDAO=new PostmortemRequiredDetailDAO(tx);
			postmortemReqVO=postmortemReqDAO.getPostmortemRequestDetail(postmortemId,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return postmortemReqVO;
	}
	
	public Map getEssentialForPostmortemReqApproved(String deceasedNo,String consentStatus,String postmortemId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap=new HashMap(); 
		PostmortemOpinionReqDtlVO[] opinionReqVO=null;
		List lstOpinionNotReq=new ArrayList();
		PostmortemItemReqDtlVO[] itemReqVO=null;
		List lstItemNotReq=new ArrayList();
		List lstAutopsyType=new ArrayList();
		List lstEmpIncharge=new ArrayList();
		List lstEmpApprovedBy=new ArrayList();
		List lstRole=new ArrayList();
		List lstRelation=new ArrayList();
		DeceasedRelativeDtlVO relativeVO=new DeceasedRelativeDtlVO();
		String dept="";
		String processId="";
		
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi mortuaryEssDAO= new MortuaryMstEssentialDAO(tx);
			PostmortemOpinionReqDAOi opinionReqDAO=new PostmortemOpinionReqDAO(tx);
			PostmortemItemReqDAOi itemReqDAO=new PostmortemItemReqDAO(tx);
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			DeceasedRelativeDtlDAOi relativeDAO=new DeceasedRelativeDtlDAO(tx);
			
			opinionReqVO=opinionReqDAO.getRequestedOpinion(postmortemId,userVO);
			essMap.put(MortuaryConfig.ARR_OPINION_REQUESTED_VO, opinionReqVO);
			
			lstOpinionNotReq=mortuaryEssDAO.getOpinionListNotRequested(postmortemId,userVO);
			essMap.put(MortuaryConfig.OPINION_LIST_NOT_REQUESTED, lstOpinionNotReq);
			
			itemReqVO=itemReqDAO.getItemToBePreserved(postmortemId, userVO);
			essMap.put(MortuaryConfig.ARR_DECEASED_ITEM_TOBE_PRESERVED, itemReqVO);
			
			lstItemNotReq=mortuaryEssDAO.getItemListNotRequested(postmortemId,userVO);
			essMap.put(MortuaryConfig.ITEM_LIST_NOT_REQUESTED, lstItemNotReq);
			
			lstAutopsyType=mortuaryEssDAO.getAutopsyTypeList(userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_AUTOPSY_TYPE_LIST, lstAutopsyType);
			
			processId=MrdConfig.PROCESS_TYPE_POSTMORTEM_REQUEST_APPROVED;     // global process id 
			lstEmpIncharge=essDAO.getEmployeeListDeptProcessWise(processId,dept,userVO);
			essMap.put(MortuaryConfig.EMPLOYEE_INCHARGE_LIST, lstEmpIncharge);
			
			lstEmpApprovedBy=essDAO.getEmployeeListDeptProcessWise(processId,dept,userVO);
			essMap.put(MortuaryConfig.EMPLOYEE_APPROVED_BY_LIST, lstEmpApprovedBy);
			
			lstRole=mortuaryEssDAO.getPostmortemRole(userVO);
			essMap.put(MortuaryConfig.POSTMORTEM_INCHARGE_ROLE_LIST, lstRole);
			
			lstRelation=mortuaryEssDAO.getAllRelation(userVO);
			essMap.put(MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION, lstRelation);
			
			if(consentStatus.equals(MortuaryConfig.CONSENT_RAISED))
			{
				relativeVO=relativeDAO.getPostmortemRequestedByRelative(deceasedNo,userVO);
				essMap.put(MortuaryConfig.PM_REQUESTED_RELATIVE_DETAIL_VO, relativeVO);
			}
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essMap;
	}
	
	public Map getPreservativeList(String postmortemId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap=new HashMap();
		List lstPreservative=new ArrayList();
		List lstItemNotRequested=new ArrayList();
		PostmortemItemDtlVO[] preservedItemVO=null;
		
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi mortuaryEssDAO= new MortuaryMstEssentialDAO(tx);
			PostmortemItemDtlDAOi itemDtlDAO=new PostmortemItemDtlDAO(tx);
			
			lstPreservative=mortuaryEssDAO.getAllPreservative(userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_PRESERVATIVE_LIST, lstPreservative);
			
			lstItemNotRequested=mortuaryEssDAO.getItemNotRequestedNPreserved(postmortemId, userVO);
			essMap.put(MortuaryConfig.ITEM_NOT_REQUESTED_N_PRESERVED_LIST, lstItemNotRequested);
			
			preservedItemVO=itemDtlDAO.getPreservedItem(postmortemId,userVO);
			essMap.put(MortuaryConfig.ARR_PRESERVED_ITEM_DETAIL_VO, preservedItemVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essMap;
	}
	
	public Map getEssentialForExternalBodyAcceptance(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap=new HashMap();
		List lstGender=new ArrayList();
		List lstMaritialStatus=new ArrayList();
		List lstAgeType=new ArrayList();
		List lstRelation=new ArrayList();
		List lstChamber=new ArrayList();
		List lstAssociatedHospital=new ArrayList();
		ChamberRackMasterVO[] chamberRackVO=null;
		List mortuaryEmpList= new ArrayList();
		String deptCode="";
		String processId="";
		
		try
		{
			tx.begin();
			MortuaryEssDAOi mortuaryEssDAO= new MortuaryEssDAO(tx);
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			MortuaryMstEssentialDAOi essDAO=new MortuaryMstEssentialDAO(tx);
			
			
			lstGender=mortuaryEssDAO.getGenderList(userVO);
			essMap.put(MortuaryConfig.ESS_ALL_GENDER_LIST, lstGender);
			
			lstMaritialStatus=mortuaryEssDAO.getMaritilStatusList(userVO);
			essMap.put(MortuaryConfig.ESS_ALL_MARITIAL_STATUS_LIST, lstMaritialStatus);
			
			lstAgeType = objEssentialDAO.getAgeType();
			essMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE, lstAgeType);
			
			lstRelation=essDAO.getAllRelation(userVO);
			essMap.put(MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION, lstRelation);
			
			lstChamber=essDAO.getAllChamber(userVO);
			essMap.put(MortuaryConfig.ESENTIAL_ALL_CHAMBER, lstChamber);
			
			chamberRackVO=essDAO.getAllChamberRack(userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_VO, chamberRackVO );
			
			processId=MortuaryConfig.DECEASED_ACCEPTANCE_BROUGHT_BY_PEON;
			mortuaryEmpList=mortuaryEssDAO.getEmployeeListDeptProcessWise(processId,deptCode, userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST, mortuaryEmpList );
			
			lstAssociatedHospital=essDAO.getAllAssociatedHospital(userVO);
			essMap.put(MortuaryConfig.ESSENTIAL_ALL_ASSOCIATED_HOSPITAL_LIST, lstAssociatedHospital);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		
		return essMap;
	}
	
	public Map fetchPoliceVeriDetails(String postmortemId,String deceasedNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		MortuaryPoliceVerificationVO policeVerificationVO=new MortuaryPoliceVerificationVO();
		List lstEmpApprovedBy=new ArrayList();
		String dept="";
		String processId="";
		
		
		try
		{
			tx.begin();
			MortuaryPoliceVerificationDAOi mortuaryPoliceVerificationDAO=new MortuaryPoliceVerificationDAO(tx);
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			if(!postmortemId.equals(""))
			{
				policeVerificationVO=mortuaryPoliceVerificationDAO.fetchPoliceVeriDetailWaveoff(deceasedNo,userVO);
				essentialMap.put( MortuaryConfig.POSTMORTEM_WAVEOFF_POLICE_VERIFICATION_DETAIL_VO, policeVerificationVO);
			}
			else
			{
				policeVerificationVO=mortuaryPoliceVerificationDAO.fetchPoliceVeriDetail(deceasedNo,userVO);
				essentialMap.put( MortuaryConfig.POSTMORTEM_WAVEOFF_POLICE_VERIFICATION_DETAIL_VO, policeVerificationVO);
			}
			
			processId=MortuaryConfig.POSTMORTEM_REQUEST_APPROVED;
			lstEmpApprovedBy=essDAO.getEmployeeListDeptProcessWise(processId,dept,userVO);
			essentialMap.put(MortuaryConfig.EMPLOYEE_APPROVED_BY_LIST, lstEmpApprovedBy);

			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public Map getEssentialForSampleSend(String postmortemId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List lstExtLab=new ArrayList();
		List lstExtTest=new ArrayList();
		PostmortemItemDtlVO[] preservedItemVO=null;
		
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi mstEssDAO=new MortuaryMstEssentialDAO(tx);
			PostmortemItemDtlDAOi itemDtlDAO=new PostmortemItemDtlDAO(tx);
			
			preservedItemVO=itemDtlDAO.getPreservedItemInMortuary(postmortemId,userVO);
			essentialMap.put(MortuaryConfig.ARR_PRESERVED_ITEM_IN_MORTUARY_DETAIL_VO, preservedItemVO);
			
			lstExtLab=mstEssDAO.getExternalLabList(userVO);
			essentialMap.put(MortuaryConfig.ESSENTIAL_ALL_EXTERNAL_LAB_LIST, lstExtLab);
			
			lstExtTest=mstEssDAO.getExternalLabTestList(userVO);
			essentialMap.put(MortuaryConfig.ESSENTIAL_ALL_EXTERNAL_LAB_TEST_LIST, lstExtTest);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public Map getAllRequestedIdDetailNDeceasedNo(String postmortemId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		MortuaryExtLabRequestDtlVO[] arrExtLabReqDtlVO=null;
		MortuaryExtLabRequestDtlVO[] arrExtLabResultVO=null;
		String deceasedNo="";
		
		try
		{
			tx.begin();
			ExtLabReqDtlDAOi extLabReqDAO=new ExtLabReqDtlDAO(tx);
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			
			arrExtLabReqDtlVO=extLabReqDAO.getAllRequestDetail(postmortemId,userVO);
			essentialMap.put(MortuaryConfig.ARR_REQUESTED_ID_BY_POSTMORTEM_NO_VO, arrExtLabReqDtlVO);
			
			deceasedNo=essDAO.getDeceasedNoByPostmortemId(postmortemId,userVO);
			essentialMap.put(MortuaryConfig.DECEASED_NO_BY_POSTMORTEM_ID, deceasedNo);
			
			arrExtLabResultVO=extLabReqDAO.getAllReceivedReport(postmortemId,userVO);
			essentialMap.put(MortuaryConfig.ARR_RECEIVED_REPORT_BY_POSTMORTEM_NO_VO, arrExtLabResultVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public Map getAllSampleNInvestigationRequestDetail(String requestId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		MortuaryExtReqSampleDtlVO[] arrExtSampleVO=null;
		MortuaryExtLabInvReqDtlVO[] arrExtLabInvVO=null;
		
		try
		{
			tx.begin();
			ExtReqSampleDtlDAOi extSampleDAO=new ExtReqSampleDtlDAO(tx);
			ExtLabInvReqDtlDAOi extLabInvDAO=new ExtLabInvReqDtlDAO(tx);
			
			arrExtSampleVO=extSampleDAO.getRequestedSampleByRequestId(requestId,userVO);
			essentialMap.put(MortuaryConfig.ARR_REQUESTED_SAMPLE_BY_REQUEST_ID_VO, arrExtSampleVO);
			
			arrExtLabInvVO=extLabInvDAO.getRequestedInvestigationByRequestId(requestId,userVO);
			essentialMap.put(MortuaryConfig.ARR_REQUESTED_INVESTIGATION_BY_REQUEST_ID_VO, arrExtLabInvVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public Map checkForDeceasedHandover(String deceasedNo,String postmortemReq,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		String postmortemStatusName="";
		String postmortemStatus="";
		String postmortemStatusNLabel="";
		String isHandover="";
		boolean exist=false;
		
		try
		{
			tx.begin();
			PostmortemRequiredDetailDAOi postmortemReqDAO=new PostmortemRequiredDetailDAO(tx);
			PostmortemHandoverDtlDAOi pmHandoverDAO=new PostmortemHandoverDtlDAO(tx);
			
			if(postmortemReq.equals(MortuaryConfig.POSTMORTEM_REQUEST_OPTIONAL))
			{
				postmortemStatusNLabel=postmortemReqDAO.getPostmortemStatusForHandover(deceasedNo,userVO);
				postmortemStatus=postmortemStatusNLabel.split("@")[1];
				postmortemStatusName=postmortemStatusNLabel.split("@")[0];
				///	0-->Not Raised
				if(postmortemStatus.equals("0") || postmortemStatus.equals(MortuaryConfig.POSTMORTEM_STATUS_REQUEST_CANCELED) || postmortemStatus.equals(MortuaryConfig.POSTMORTEM_STATUS_REQUEST_POSTMORTEM_DONE))
				{
					/*if(postmortemStatus.equals(MortuaryConfig.POSTMORTEM_STATUS_REQUEST_POSTMORTEM_DONE))
					{
						exist=pmHandoverDAO.checkForPostmortemHandover(deceasedNo,userVO);
						if(exist)
							isHandover=MortuaryConfig.YES;
						else
						{
							isHandover=MortuaryConfig.NO;
							postmortemStatusName="Postmortem Done But Report Not Handover";
						}	
					}
					else*/
						isHandover=MortuaryConfig.YES;
				}	
				else
					isHandover=MortuaryConfig.NO;
			}
			
			if(postmortemReq.equals(MortuaryConfig.POSTMORTEM_REQUEST_COMPULSORY))
			{
				postmortemStatusNLabel=postmortemReqDAO.getPostmortemStatusForHandover(deceasedNo,userVO);
				postmortemStatus=postmortemStatusNLabel.split("@")[1];
				postmortemStatusName=postmortemStatusNLabel.split("@")[0];
				if(postmortemStatus.equals(MortuaryConfig.POSTMORTEM_STATUS_REQUEST_WAVEOFF) || postmortemStatus.equals(MortuaryConfig.POSTMORTEM_STATUS_REQUEST_POSTMORTEM_DONE))
				{	
					/*if(postmortemStatus.equals(MortuaryConfig.POSTMORTEM_STATUS_REQUEST_POSTMORTEM_DONE))
					{
						exist=pmHandoverDAO.checkForPostmortemHandover(deceasedNo,userVO);
						if(exist)
							isHandover=MortuaryConfig.YES;
						else
						{
							isHandover=MortuaryConfig.NO;
							postmortemStatusName="Postmortem Done But Report Not Handover";
						}
					}
					else*/
						isHandover=MortuaryConfig.YES;
				}	
				else
					isHandover=MortuaryConfig.NO;
				
			}
			
			if(postmortemReq.equals(MortuaryConfig.POSTMORTEM_REQUEST_WAVEOFF))
			{
				isHandover=MortuaryConfig.YES;
				postmortemStatusName="Wave Off";
			}
			
			essentialMap.put(MortuaryConfig.IS_DECEASED_HANDOVER_FLAG, isHandover);
			essentialMap.put(MortuaryConfig.DECEASED_POSTMORTEM_STATUS, postmortemStatusName);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public Map getEssentialForUnknown(String deceasedNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List lstGender=new ArrayList();
		List lstMaritialStatus=new ArrayList();
		List lstAgeType=new ArrayList();
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		
		try
		{
			tx.begin();
			MortuaryEssDAOi mortuaryEssDAO= new MortuaryEssDAO(tx);
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			DeceasedDetailDAOi deceasedDtlDAO=new DeceasedDetailDAO(tx);
			
			
			lstGender=mortuaryEssDAO.getGenderList(userVO);
			essentialMap.put(MortuaryConfig.ESS_ALL_GENDER_LIST, lstGender);
			
			lstMaritialStatus=mortuaryEssDAO.getMaritilStatusList(userVO);
			essentialMap.put(MortuaryConfig.ESS_ALL_MARITIAL_STATUS_LIST, lstMaritialStatus);
			
			lstAgeType = objEssentialDAO.getAgeType();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE, lstAgeType);
			
			deceasedDtlVO=deceasedDtlDAO.getDeceasedDtlByDeceasedNo(deceasedNo, userVO);
			essentialMap.put(MortuaryConfig.DECEASED_DETAIL_BY_DECEASED_NO_VO, deceasedDtlVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public DeceasedDetailVO[] getPostmortemReadyToHandover(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedDetailVO[] deceasedDtlVO=null;
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			deceasedDtlVO=essDAO.getPostmortemReadyToHandover(userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return deceasedDtlVO;
		
	}
	
	public Map getEssentialForPostmortemHandover(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List lstRelation=new ArrayList();
		
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi essDAO=new MortuaryMstEssentialDAO(tx);
			
			lstRelation=essDAO.getAllRelation(userVO);
			essentialMap.put(MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION, lstRelation);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public DeceasedDetailVO getDeceasedGeneralAppearance(String deceasedNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		
		try
		{
			tx.begin();
			DeceasedDetailDAOi deceasedDtlDAO=new DeceasedDetailDAO(tx);
			deceasedDtlVO=deceasedDtlDAO.getDeceasedGeneralAppearance(deceasedNo, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return deceasedDtlVO; 
	}
	
	public DeceasedDetailVO[] searchDeceasedNo(String fName,String mName,String lName,String deathDate,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedDetailVO[] deceasedDtlVO=null;
		
		try
		{
			tx.begin();
			DeceasedDetailDAOi deceasedDtlDAO=new DeceasedDetailDAO(tx);
			deceasedDtlVO=deceasedDtlDAO.searchDeceasedNo(fName,mName,lName,deathDate,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return deceasedDtlVO; 
	}
	
	public DeceasedDetailVO[] searchPostmortemNo(String decNo, String fName,String mName,String lName,String deathDate,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedDetailVO[] deceasedDtlVO=null;
		
		try
		{
			tx.begin();
			DeceasedDetailDAOi deceasedDtlDAO=new DeceasedDetailDAO(tx);
			deceasedDtlVO=deceasedDtlDAO.searchPostmortemNo(decNo,fName,mName,lName,deathDate,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return deceasedDtlVO; 
	}
	
	public MortuaryDeceasedImageDtlVO getDedeasedDefaultImage(String deceasedNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MortuaryDeceasedImageDtlVO deceasedDefaultImageVO=new MortuaryDeceasedImageDtlVO();
		
		try
		{
			tx.begin();
			DeceasedImageDtlDAOi deceasedImageDAO=new DeceasedImageDtlDAO(tx);
			deceasedDefaultImageVO=deceasedImageDAO.getDedeasedDefaultImage(deceasedNo,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return deceasedDefaultImageVO;
	}
	
	public MortuaryExtLabRequestDtlVO[] getSampleResult(String postmortemId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MortuaryExtLabRequestDtlVO[] arrExtLabResultVO=null;
		
		try
		{
			tx.begin();
			ExtLabReqDtlDAOi extLabReqDAO=new ExtLabReqDtlDAO(tx);
			
			arrExtLabResultVO=extLabReqDAO.getAllReceivedReport(postmortemId,userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return arrExtLabResultVO;
	}
	
}

