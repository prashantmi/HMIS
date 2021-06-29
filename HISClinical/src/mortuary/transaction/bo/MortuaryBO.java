package mortuary.transaction.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.vo.DeceasedBroughtByDtlVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.DeceasedIdentityDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.DeceasedStorageDtlVO;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.MortuaryPoliceVerificationVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.PostmortemDetailVO;
import hisglobal.vo.PostmortemExamDtlVO;
import hisglobal.vo.PostmortemItemDtlVO;
import hisglobal.vo.PostmortemItemReqDtlVO;
import hisglobal.vo.PostmortemOpinionDetailVO;
import hisglobal.vo.PostmortemOpinionReqDtlVO;
import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.PostmortemTeamDetailVO;
import hisglobal.vo.PostmortemWaveoffDtlVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

import mortuary.MortuaryConfig;
import mortuary.transaction.dao.DeceasedBroughtByDtlDAO;
import mortuary.transaction.dao.DeceasedBroughtByDtlDAOi;
import mortuary.transaction.dao.DeceasedDetailDAO;
import mortuary.transaction.dao.DeceasedDetailDAOi;
import mortuary.transaction.dao.DeceasedHandoverDtlDAO;
import mortuary.transaction.dao.DeceasedHandoverDtlDAOi;
import mortuary.transaction.dao.DeceasedIdentityDtlDAO;
import mortuary.transaction.dao.DeceasedIdentityDtlDAOi;
import mortuary.transaction.dao.DeceasedImageDtlDAO;
import mortuary.transaction.dao.DeceasedImageDtlDAOi;
import mortuary.transaction.dao.DeceasedRelativeDtlDAO;
import mortuary.transaction.dao.DeceasedRelativeDtlDAOi;
import mortuary.transaction.dao.DeceasedStorageDtlDAO;
import mortuary.transaction.dao.DeceasedStorageDtlDAOi;
import mortuary.transaction.dao.ExtLabInvReqDtlDAO;
import mortuary.transaction.dao.ExtLabInvReqDtlDAOi;
import mortuary.transaction.dao.ExtLabReqDtlDAO;
import mortuary.transaction.dao.ExtLabReqDtlDAOi;
import mortuary.transaction.dao.ExtReqSampleDtlDAO;
import mortuary.transaction.dao.ExtReqSampleDtlDAOi;
import mortuary.transaction.dao.PostmortemHandoverDtlDAO;
import mortuary.transaction.dao.PostmortemHandoverDtlDAOi;
import mortuary.transaction.dao.PostmortemWaveoffDtlDAO;
import mortuary.transaction.dao.PostmortemWaveoffDtlDAOi;
import mortuary.transaction.dao.MortuaryDAO;
import mortuary.transaction.dao.MortuaryDAOi;
import mortuary.transaction.dao.MortuaryEssDAO;
import mortuary.transaction.dao.MortuaryEssDAOi;
import mortuary.transaction.dao.MortuaryPoliceVerificationDAO;
import mortuary.transaction.dao.MortuaryPoliceVerificationDAOi;
import mortuary.transaction.dao.PostmortemDetailDAO;
import mortuary.transaction.dao.PostmortemDetailDAOi;
import mortuary.transaction.dao.PostmortemExamDtlDAO;
import mortuary.transaction.dao.PostmortemExamDtlDAOi;
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
import mortuary.transaction.dao.PostmortemWaveoffDtlDAO;
import mortuary.transaction.dao.PostmortemWaveoffDtlDAOi;
import registration.dao.EpisodeDAO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.vo.DeceasedBroughtByDtlVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.DeceasedIdentityDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.DeceasedStorageDtlVO;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.MortuaryExtLabInvReqDtlVO;
import hisglobal.vo.MortuaryExtLabRequestDtlVO;
import hisglobal.vo.MortuaryExtReqSampleDtlVO;
import hisglobal.vo.MortuaryPoliceVerificationVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.PostmortemDetailVO;
import hisglobal.vo.PostmortemExamDtlVO;
import hisglobal.vo.PostmortemHandoverDtlVO;
import hisglobal.vo.PostmortemItemDtlVO;
import hisglobal.vo.PostmortemItemReqDtlVO;
import hisglobal.vo.PostmortemOpinionDetailVO;
import hisglobal.vo.PostmortemOpinionReqDtlVO;
import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.PostmortemTeamDetailVO;
import hisglobal.vo.PostmortemWaveoffDtlVO;
import hisglobal.vo.UserVO;

public class MortuaryBO implements MortuaryBOi
{
	//Getting The List of Dead Patient Send To Mortuary 
	public PatientDeathDetailVO[] getInHouseDeadPatientList(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientDeathDetailVO[] deadPatList=null;
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			deadPatList=essDAO.getInHouseDeadPatientList(userVO);
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
		return deadPatList;
	}
	
	//Saving Deceased Detail
	public String saveDeceasedDetail(MortuaryDeceasedImageDtlVO[] deceasedImageDtlVO,MortuaryPoliceVerificationVO policeVerVO,DeceasedBroughtByDtlVO deceasedBroughtVO,DeceasedDetailVO deceasedDtlVO,DeceasedHandoverDtlVO deceasedHandoverVO,DeceasedRelativeDtlVO deceasedRelativeVO,DeceasedStorageDtlVO deceasedStorageVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		String countMaxCapacity="";
		String deceasedNo=null;
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			DeceasedDetailDAOi deceasedDetailDAO=new DeceasedDetailDAO(tx);
			DeceasedHandoverDtlDAOi deceasedHandoverDAO=new DeceasedHandoverDtlDAO(tx);
			DeceasedStorageDtlDAOi deceasedStorageDAO=new DeceasedStorageDtlDAO(tx);
			DeceasedRelativeDtlDAOi deceasedRelativeDAO=new DeceasedRelativeDtlDAO(tx);
			DeceasedBroughtByDtlDAOi deceasedBroughtDAO=new DeceasedBroughtByDtlDAO(tx);
			MortuaryPoliceVerificationDAOi policeVerDAO= new MortuaryPoliceVerificationDAO(tx); 
			DeceasedImageDtlDAOi deceasedImageDAO=new DeceasedImageDtlDAO(tx);
			
			deceasedNo=deceasedDetailDAO.generateDeceasedNo(userVO);
		//	String deceasedNo="1";
			
			deceasedDtlVO.setDeceasedNo(deceasedNo);
			deceasedBroughtVO.setDeceasedNo(deceasedNo);
			deceasedDetailDAO.create(deceasedDtlVO, userVO);
			deceasedBroughtDAO.create(deceasedBroughtVO,userVO);
			
			if(deceasedHandoverVO!=null)		//Inserting Handover Detail
			{
				deceasedHandoverVO.setDeceasedNo(deceasedNo);
				deceasedHandoverDAO.create(deceasedHandoverVO, userVO);
			}
			
			if(deceasedRelativeVO!=null)		//Inserting Relative Detail
			{
				deceasedRelativeVO.setDeceasedNo(deceasedNo);
				deceasedRelativeDAO.create(deceasedRelativeVO, userVO);
			}
			
			if(deceasedStorageVO!=null)			//Inserting Storage Detail
			{
				deceasedStorageVO.setDeceasedNo(deceasedNo);
				deceasedStorageDAO.create(deceasedStorageVO, userVO);
				count=deceasedStorageDAO.countdeadBodyInRack(deceasedStorageVO,userVO);
				countMaxCapacity=essDAO.countMaxCapacityOfRack(deceasedStorageVO.getChamberRackId(),userVO);
				if(Integer.parseInt(count)==Integer.parseInt(countMaxCapacity))
				{
					String chamberStatus=MortuaryConfig.CHAMBER_RACK_STATUS_OCCUPIED;
					essDAO.updateChamberStatus(chamberStatus,deceasedStorageVO.getChamberRackId(),userVO);
				}
			}
			
			if(policeVerVO!=null)			//Inserting Police Verification Detail
			{
				policeVerVO.setDeceasedNo(deceasedNo);
				policeVerDAO.create(policeVerVO, userVO);
			}
			
			if(deceasedImageDtlVO!=null)		//Inserting Deceased Image Detail 
			{
				for(int i=0;i<deceasedImageDtlVO.length;i++)
				{
					deceasedImageDtlVO[i].setDeceasedNo(deceasedNo);
					deceasedImageDAO.create(deceasedImageDtlVO[i],userVO);
					String fileName=deceasedImageDAO.getFileName(deceasedNo);
					
					HisFileControlUtil fileUtil = new HisFileControlUtil();
					fileUtil.setFileName(fileName);
					fileUtil.setWindowsFilePath(Config.MORTUARY_PATIENT_IMAGE_PATH_WINDOWS);
					fileUtil.setLinuxFilePath(Config.MORTUARY_PATIENT_IMAGE_PATH_LINUX);
					fileUtil.setFileContent(deceasedImageDtlVO[i].getImageFile());
					fileUtil.saveFile();
				}
			}
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
		return deceasedNo;
	}
	
	public DeceasedDetailVO[] getDeceasedListForStorage(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedDetailVO[] deceasedListVO=null;

		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			deceasedListVO=essDAO.getDeceasedListInStreacher(userVO);
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
		return deceasedListVO;
	}
	
	public void saveDeceasedStorageDetail(DeceasedRelativeDtlVO deceasedRelativeVO, DeceasedStorageDtlVO deceasedStorageVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		String countMaxCapacity="";
		
		try
		{
			tx.begin();
			DeceasedStorageDtlDAOi deceasedStorageDAO=new DeceasedStorageDtlDAO(tx);
			DeceasedRelativeDtlDAOi deceasedRelativeDAO=new DeceasedRelativeDtlDAO(tx);
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			
			deceasedStorageDAO.create(deceasedStorageVO, userVO);
			String bodyStatus=MortuaryConfig.BODY_STATUS_IN_CHAMBER;
			essDAO.updateBodyStatus(bodyStatus,deceasedStorageVO.getDeceasedNo(),userVO);
			count=deceasedStorageDAO.countdeadBodyInRack(deceasedStorageVO,userVO);
			countMaxCapacity=essDAO.countMaxCapacityOfRack(deceasedStorageVO.getChamberRackId(),userVO);
			
			if(Integer.parseInt(count)==Integer.parseInt(countMaxCapacity))
			{
				String chamberStatus=MortuaryConfig.CHAMBER_RACK_STATUS_OCCUPIED;
				essDAO.updateChamberStatus(chamberStatus,deceasedStorageVO.getChamberRackId(),userVO);
			}
			
			if(deceasedRelativeVO!=null)
			{
				deceasedRelativeDAO.create(deceasedRelativeVO, userVO);
			}
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public DeceasedDetailVO[] getDeceasedListToHandover(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedDetailVO[] deceasedListVO=null;

		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			deceasedListVO=essDAO.getDeceasedListToHandover(userVO);
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
		return deceasedListVO;
	}
	
	public void saveDeceasedHandoverDetail(String bodyStatus, DeceasedRelativeDtlVO deceasedRelativeVO, DeceasedHandoverDtlVO deceasedHandoverVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String chamberRackId="";
		
		try
		{
			tx.begin();
			DeceasedHandoverDtlDAOi deceasedHandoverDAO=new DeceasedHandoverDtlDAO(tx);
			DeceasedRelativeDtlDAOi deceasedRelativeDAO=new DeceasedRelativeDtlDAO(tx);
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			DeceasedStorageDtlDAOi deceasedStorageDAO=new DeceasedStorageDtlDAO(tx);
			
			String deceasedBodyStatus=MortuaryConfig.BODY_STATUS_HANDOVER;
			
			deceasedHandoverDAO.create(deceasedHandoverVO, userVO);
			
			if(deceasedRelativeVO!=null)
			{
				deceasedRelativeDAO.create(deceasedRelativeVO, userVO);
			}
			
			essDAO.updateBodyStatusNIssueTime(deceasedBodyStatus, deceasedHandoverVO.getDeceasedNo(), userVO);
			
			if(bodyStatus.equals(MortuaryConfig.BODY_STATUS_IN_CHAMBER))
			{
				String chamberStatus=MortuaryConfig.CHAMBER_RACK_STATUS_AVAILABLE;
				chamberRackId=deceasedStorageDAO.getRackIdByDeceasedNo(deceasedHandoverVO.getDeceasedNo(),userVO);
				essDAO.updateChamberStatus(chamberStatus,chamberRackId,userVO);
				deceasedStorageDAO.updateHandoverStorageDtl(deceasedHandoverVO.getDeceasedNo(),userVO);
			}
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public String getChamberRackName(String deceasedNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String str="";
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			str=essDAO.getChamberRackName(deceasedNo,userVO);
			
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
		return str;
	}
	
	public PatientDeathDetailVO getDeceasedHandoverDetail(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientDeathDetailVO patDeathVO=null;
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			patDeathVO=essDAO.getDeceasedHandoverDetail(crNo,userVO);
			
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
		return patDeathVO;
	}
	
	public String saveOfflineDeceasedStorageDetail(DeceasedDetailVO deceasedDtlVO, DeceasedBroughtByDtlVO deceasedBroughtVO, DeceasedRelativeDtlVO deceasedRelativeVO, DeceasedStorageDtlVO deceasedStorageVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		String countMaxCapacity="";
		String deceasedNo="";
		try
		{
			tx.begin();
			DeceasedStorageDtlDAOi deceasedStorageDAO=new DeceasedStorageDtlDAO(tx);
			DeceasedRelativeDtlDAOi deceasedRelativeDAO=new DeceasedRelativeDtlDAO(tx);
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			DeceasedDetailDAOi deceasedDetailDAO=new DeceasedDetailDAO(tx);
			DeceasedBroughtByDtlDAOi deceasedBroughtDAO=new DeceasedBroughtByDtlDAO(tx);
			
			//generating Deceased No
			deceasedNo=deceasedDetailDAO.generateDeceasedNo(userVO);
			
			deceasedDtlVO.setDeceasedNo(deceasedNo);
			deceasedBroughtVO.setDeceasedNo(deceasedNo);
			deceasedDetailDAO.create(deceasedDtlVO, userVO);
			deceasedBroughtDAO.create(deceasedBroughtVO,userVO);
			
			if(deceasedRelativeVO!=null)		//Inserting Relative Detail
			{
				deceasedRelativeVO.setDeceasedNo(deceasedNo);
				deceasedRelativeDAO.create(deceasedRelativeVO, userVO);
			}
			
			if(deceasedStorageVO!=null)			//Inserting Storage Detail
			{
				deceasedStorageVO.setDeceasedNo(deceasedNo);
				deceasedStorageDAO.create(deceasedStorageVO, userVO);
				count=deceasedStorageDAO.countdeadBodyInRack(deceasedStorageVO,userVO);
				countMaxCapacity=essDAO.countMaxCapacityOfRack(deceasedStorageVO.getChamberRackId(),userVO);
				if(Integer.parseInt(count)==Integer.parseInt(countMaxCapacity))
				{
					String chamberStatus=MortuaryConfig.CHAMBER_RACK_STATUS_OCCUPIED;
					essDAO.updateChamberStatus(chamberStatus,deceasedStorageVO.getChamberRackId(),userVO);
				}
			}

			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
		return deceasedNo;
	}
	
	public DeceasedDetailVO[] getDeceasedListForPostmortemRequest(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedDetailVO[] deceasedListVO=null;
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			deceasedListVO=essDAO.getDeceasedListForPostmortemRequest(userVO);
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
		return deceasedListVO;
	}
	
	public String savePostmortemRequest(MortuaryPoliceVerificationVO postmortemPoliceVerVO, PostmortemRequestDetailVO postmortemRequestDtlVO,List<PostmortemItemReqDtlVO> lstItemVO, List<PostmortemOpinionReqDtlVO> lstOpinionVO, List<DeceasedRelativeDtlVO> lstRelativeVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String postmortemId="";
		
		try
		{
			tx.begin();
			PostmortemRequiredDetailDAOi postmortemReqDAO=new PostmortemRequiredDetailDAO(tx);
			PostmortemItemReqDAOi postmortemItemDAO=new PostmortemItemReqDAO(tx);
			PostmortemOpinionReqDAOi postmortemOpinionDAO=new PostmortemOpinionReqDAO(tx);
			DeceasedRelativeDtlDAOi relativeDAO=new DeceasedRelativeDtlDAO(tx);
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			String deceasedBodyStatus=MortuaryConfig.BODY_STATUS_ISSUE_FOR_POSTMORTEM;
			MortuaryPoliceVerificationDAOi policeVerDAO=new MortuaryPoliceVerificationDAO(tx); 
			
			
			postmortemId=postmortemReqDAO.generatePostmortemId(postmortemRequestDtlVO,userVO);
			
			postmortemRequestDtlVO.setPostmortemId(postmortemId);
			postmortemReqDAO.create(postmortemRequestDtlVO, userVO);
			
		//	essDAO.updateBodyStatusNIssueTime(deceasedBodyStatus, postmortemRequestDtlVO.getDeceasedNo(), userVO);
			
			if(postmortemPoliceVerVO!=null)
				policeVerDAO.create(postmortemPoliceVerVO, userVO);
			
			for(int i=0;i<lstItemVO.size();i++)
			{
				PostmortemItemReqDtlVO itemReqVO=(PostmortemItemReqDtlVO)lstItemVO.get(i);
				itemReqVO.setPostmortemId(postmortemId);
				postmortemItemDAO.create(itemReqVO, userVO);
			}
			
			for(int i=0;i<lstOpinionVO.size();i++)
			{
				PostmortemOpinionReqDtlVO opinionReqVO=(PostmortemOpinionReqDtlVO)lstOpinionVO.get(i);
				opinionReqVO.setPostmortemId(postmortemId);
				postmortemOpinionDAO.create(opinionReqVO, userVO);
			}
			
			for(int i=0;i<lstRelativeVO.size();i++)
			{
				relativeDAO.create(lstRelativeVO.get(i), userVO);
			}
			
			if(!postmortemRequestDtlVO.getConsentStatus().equals(MortuaryConfig.CONSENT_NOT_REQUIRED))
			{
				///Consent Raised
				essDAO.generatConsent(postmortemRequestDtlVO, userVO);
				
				///consent Received
				if(postmortemRequestDtlVO.getConsentStatus().equals(MortuaryConfig.CONSENT_RECEIVED))
				{
					essDAO.updateConsentReceivedStatus(lstRelativeVO.get(0),postmortemRequestDtlVO,userVO);
				}
			}
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
		return postmortemId;
	}
	
	public void saveGeneralAppearance(List postMortemExamVOList, List<PostmortemOpinionDetailVO> lstOpinionDtl, PostmortemDetailVO postmortemDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String status="";
		
		try
		{
			tx.begin();
			PostmortemDetailDAOi postmortemDAO=new PostmortemDetailDAO(tx);
			PostmortemOpinionDetailDAOi opinionDtlDAO=new PostmortemOpinionDetailDAO(tx);
			PostmortemExamDtlDAOi postmortemExamDAO=new PostmortemExamDtlDAO(tx);
			PostmortemRequiredDetailDAOi postmortemReqDAO=new PostmortemRequiredDetailDAO(tx);
			
			postmortemDAO.create(postmortemDtlVO, userVO);
			
			status=MortuaryConfig.POSTMORTEM_STATUS_REQUEST_POSTMORTEM_ENTRY;
			postmortemReqDAO.updatePostmortemStatusCompleted(postmortemDtlVO.getPostmortemId(), status, userVO);
			
			for(int i=0;i<postMortemExamVOList.size();i++)
			{
				postmortemExamDAO.create((PostmortemExamDtlVO)postMortemExamVOList.get(i), userVO);
			}
			for(int i=0;i<lstOpinionDtl.size();i++)
			{
				opinionDtlDAO.create(lstOpinionDtl.get(i), userVO);
			}
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public DeceasedDetailVO getDeceasedDetailByDeceasedNo(DeceasedDetailVO deceasedVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		
		try
		{
			tx.begin();
			MortuaryDAOi mortuaryDAO=new MortuaryDAO(tx);
			deceasedDtlVO=mortuaryDAO.getDeceasedDetailByDeceasedNo(deceasedVO,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return deceasedDtlVO;
	}
	
	public String getMlcNo(String deceasedNo,String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeDAO episodeDao = new EpisodeDAO(tx);
		MortuaryPoliceVerificationDAOi policeDAO=new MortuaryPoliceVerificationDAO(tx);
		PatientVO patVO=new PatientVO();
		String mlcNoNCaseNo="";
		String mlcNo="";
		String caseNo="";
		
		try
		{
			tx.begin();
			patVO.setPatCrNo(crNo);
			mlcNo=episodeDao.isDeadPatientMLC(patVO, userVO);
			caseNo=policeDAO.getPoliceCaseNo(deceasedNo,userVO);
			mlcNoNCaseNo=mlcNo+"@"+caseNo;
		}
		finally
		{
			tx.close();
		}
		return mlcNoNCaseNo;
	}
	
	public void saveItemToBePreserved(List<PostmortemItemDtlVO> lstItem, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			PostmortemItemDtlDAOi itemDtlDAO=new PostmortemItemDtlDAO(tx);
			for(int i=0;i<lstItem.size();i++)
			{
				itemDtlDAO.create(lstItem.get(i),userVO);
			}
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public void saveBodyIdentificationDetail(DeceasedRelativeDtlVO[] arrRelativeVO, DeceasedIdentityDtlVO[] arrIdentityDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			DeceasedIdentityDtlDAOi identityDtlDAO=new DeceasedIdentityDtlDAO(tx);
			DeceasedRelativeDtlDAOi relativeDAO=new DeceasedRelativeDtlDAO(tx);
			
			for(int i=0;i<arrIdentityDtlVO.length;i++)
				identityDtlDAO.create(arrIdentityDtlVO[i],userVO);

			if(arrRelativeVO!=null)
			{
				for(int i=0;i<arrRelativeVO.length;i++)	
					relativeDAO.create(arrRelativeVO[i], userVO);
			}	
		}	
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public void saveUploadedPhoto(String deceasedNo, String srNo,MortuaryDeceasedImageDtlVO[] deceasedImageDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			DeceasedImageDtlDAOi imageDAO=new DeceasedImageDtlDAO(tx);
		//	imageDAO.updateIsDefaultNoToAll(deceasedNo,userVO);
			if(deceasedImageDtlVO!=null)
			{
				for(int i=0;i<deceasedImageDtlVO.length;i++)
				{
					imageDAO.create(deceasedImageDtlVO[i], userVO);
					String fileName=imageDAO.getFileName(deceasedImageDtlVO[0].getDeceasedNo());
					
					HisFileControlUtil fileUtil = new HisFileControlUtil();
					fileUtil.setFileName(fileName);
					fileUtil.setWindowsFilePath(Config.MORTUARY_PATIENT_IMAGE_PATH_WINDOWS);
					fileUtil.setLinuxFilePath(Config.MORTUARY_PATIENT_IMAGE_PATH_LINUX);
					fileUtil.setFileContent(deceasedImageDtlVO[i].getImageFile());
					fileUtil.saveFile();
				}
			}	
		//	imageDAO.updateIsDefaultValue(deceasedNo,srNo,userVO); 
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public void saveInjuriesDetail(PostmortemDetailVO postmortemInjuryVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			PostmortemDetailDAOi postmortemDAO=new PostmortemDetailDAO(tx);
			postmortemDAO.saveInjuriesDetail(postmortemInjuryVO,userVO);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public DeceasedDetailVO[] getUnknownBodyList(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedDetailVO[] unknownDeceasedListVO=null;
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			unknownDeceasedListVO=essDAO.getUnknownBodyList(userVO);
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
		return unknownDeceasedListVO;
	}
	
	public void saveTeamDetail(List<PostmortemTeamDetailVO> lstTeamModify,List<PostmortemTeamDetailVO> lstTeamAdd,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			PostmortemTeamDetailDAOi teamDAO=new PostmortemTeamDetailDAO(tx);
			for(int i=0;i<lstTeamAdd.size();i++)
			{
				teamDAO.create(lstTeamAdd.get(i), userVO);
			}
			
			for(int i=0;i<lstTeamModify.size();i++)
			{
				teamDAO.update(lstTeamModify.get(i),userVO);
			}
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public void updateGeneralAppearance(List postMortemExamVOList, List<PostmortemOpinionDetailVO> lstOpinionMod,List<PostmortemOpinionDetailVO> lstOpinionDtl, PostmortemDetailVO postmortemDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			PostmortemDetailDAOi postmortemDAO=new PostmortemDetailDAO(tx);
			PostmortemOpinionDetailDAOi opinionDtlDAO=new PostmortemOpinionDetailDAO(tx);
			PostmortemExamDtlDAOi postmortemExamDAO=new PostmortemExamDtlDAO(tx);
			
			postmortemDAO.updateGeneralAppearance(postmortemDtlVO,userVO );
			postmortemExamDAO.invalidTheRecord(postmortemDtlVO.getPostmortemId(),userVO);
			for(int i=0;i<postMortemExamVOList.size();i++)
			{
				postmortemExamDAO.create((PostmortemExamDtlVO)postMortemExamVOList.get(i),userVO);
			}
			
			for(int i=0;i<lstOpinionDtl.size();i++)
			{
				opinionDtlDAO.create(lstOpinionDtl.get(i), userVO);
			}
			
			for(int i=0;i<lstOpinionMod.size();i++)
			{
				opinionDtlDAO.updateOpinion(lstOpinionMod.get(i), userVO);
			}
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public DeceasedDetailVO[] getFinalOpinionToBeApproved(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedDetailVO[] opinionToBeApprovedListVO=null;
		
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			opinionToBeApprovedListVO=essDAO.getFinalOpinionToBeApproved(userVO);
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
		return opinionToBeApprovedListVO;
	}
	
	public void saveApprovedFinalOpinion(List postMortemExamVOList, List<PostmortemOpinionDetailVO> lstOpinionDtl,PostmortemDetailVO postmortemDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String status="";
		
		try
		{
			tx.begin();
			PostmortemDetailDAOi postmortemDAO=new PostmortemDetailDAO(tx);
			PostmortemOpinionDetailDAOi opinionDtlDAO=new PostmortemOpinionDetailDAO(tx);
			PostmortemExamDtlDAOi postmortemExamDAO=new PostmortemExamDtlDAO(tx);
			PostmortemRequiredDetailDAOi postmortemRequestDAO=new PostmortemRequiredDetailDAO(tx);
			
			postmortemDAO.invalidPreviousRow(postmortemDtlVO,userVO);
			postmortemDAO.saveApprovedFinalOpinion(postmortemDtlVO, userVO);
			
			status=MortuaryConfig.POSTMORTEM_STATUS_REQUEST_POSTMORTEM_DONE;
			postmortemRequestDAO.updatePostmortemStatusCompleted(postmortemDtlVO.getPostmortemId(),status, userVO); 
			
			postmortemExamDAO.invalidTheRecord(postmortemDtlVO.getPostmortemId(),userVO);
			for(int i=0;i<postMortemExamVOList.size();i++)
			{
				postmortemExamDAO.create((PostmortemExamDtlVO)postMortemExamVOList.get(i), userVO);
			}
			
			opinionDtlDAO.invalidTheRecord(postmortemDtlVO.getPostmortemId(),userVO);
			for(int i=0;i<lstOpinionDtl.size();i++)
			{
				opinionDtlDAO.create(lstOpinionDtl.get(i), userVO);
			}
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public void approvedPostmortemRequest(PostmortemRequestDetailVO postmortemReqVO,List<PostmortemItemReqDtlVO> lstItemAdd,List<PostmortemItemReqDtlVO> lstItemRevoke,List<PostmortemOpinionReqDtlVO> lstOpinionAdd,List<PostmortemOpinionReqDtlVO> lstOpinionRevoke,List<PostmortemTeamDetailVO> lstTeam, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedRelativeDtlVO relativeVO=new DeceasedRelativeDtlVO();
		
		try
		{
			tx.begin();
			HelperMethods.populate(relativeVO, postmortemReqVO);
			PostmortemItemReqDAOi itemDAO=new PostmortemItemReqDAO(tx);
			PostmortemOpinionReqDAOi opinionDAO=new PostmortemOpinionReqDAO(tx);
			PostmortemRequiredDetailDAOi postReqDAO=new PostmortemRequiredDetailDAO(tx);
			PostmortemTeamDetailDAOi teamDAO=new PostmortemTeamDetailDAO(tx);
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			
			
			for(int i=0;i<lstItemRevoke.size();i++)
			{
				itemDAO.update(lstItemRevoke.get(i), userVO);
			}
			for(int i=0;i<lstItemAdd.size();i++)
			{
				itemDAO.create(lstItemAdd.get(i), userVO);
			}

			for(int i=0;i<lstOpinionRevoke.size();i++)
			{
				opinionDAO.update(lstOpinionRevoke.get(i),userVO);
			}
			for(int i=0;i<lstOpinionAdd.size();i++)
			{
				opinionDAO.create(lstOpinionAdd.get(i), userVO);
			}
			
			for(int i=0;i<lstTeam.size();i++)
			{
				teamDAO.create(lstTeam.get(i), userVO);
			}
			
			postReqDAO.updatePreviousRow(postmortemReqVO,userVO);
			postReqDAO.create(postmortemReqVO, userVO);
			
			if(postmortemReqVO.getRelativeName()!=null && !postmortemReqVO.getRelativeName().equals(""))
				essDAO.updateConsentReceivedStatus(relativeVO, postmortemReqVO, userVO);
			
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public void cancelledPostmortemRequest(PostmortemRequestDetailVO postmortemReqVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			PostmortemRequiredDetailDAOi postReqDAO=new PostmortemRequiredDetailDAO(tx);
			postReqDAO.updatePreviousRow(postmortemReqVO,userVO);
			postReqDAO.create(postmortemReqVO, userVO);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public DeceasedDetailVO[] getWaveoffDetails(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedDetailVO[] deceasedListVO=null;

		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			deceasedListVO=essDAO.getWaveoffDetails(userVO);
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
		return deceasedListVO;
	}
	
	public void savePostMortemWaveoffDetail(PostmortemWaveoffDtlVO postmortemWaveoffDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
			
		try
		{
			tx.begin();
			PostmortemWaveoffDtlDAOi postmortemWaveoffDtlDAO=new PostmortemWaveoffDtlDAO(tx);
			PostmortemRequiredDetailDAOi postmortemRequiredDetailDAO=new PostmortemRequiredDetailDAO(tx);
			DeceasedDetailDAOi deceasedDetailDAO=new DeceasedDetailDAO(tx);
			postmortemWaveoffDtlDAO.create(postmortemWaveoffDtlVO,userVO);
			
			if(!postmortemWaveoffDtlVO.getPostmortemId().equals(""))
			{
				postmortemRequiredDetailDAO.updatePostmortemStatus(postmortemWaveoffDtlVO,userVO);
			}
			
			deceasedDetailDAO.updatePostmortemReq(postmortemWaveoffDtlVO,userVO);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public List getPatientImageDtlByDeceasedNo(String deceasedNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List <MortuaryDeceasedImageDtlVO> deceasedImageDtlVOList=new ArrayList<MortuaryDeceasedImageDtlVO>();
		MortuaryDeceasedImageDtlVO[] deceasedImageDtlVOArray=null;
		try
		{
			tx.begin();
			DeceasedImageDtlDAOi deceasedImageDAO=new DeceasedImageDtlDAO(tx);
			deceasedImageDtlVOArray=deceasedImageDAO.getExistingPhotoByDeceasedNo(deceasedNo, userVO);
			
			if(deceasedImageDtlVOArray!=null && deceasedImageDtlVOArray.length>0){
				for(int i=0;i<deceasedImageDtlVOArray.length;i++){
					deceasedImageDtlVOList.add(deceasedImageDtlVOArray[i]);
				}
			}
			else{
				deceasedImageDtlVOList=null;
				//throw new HisRecordNotFoundException("No Image Found");
			}
		
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
		
		return deceasedImageDtlVOList;
	}
	
	
	public void saveDeceasedImage(MortuaryDeceasedImageDtlVO[] deceasedImageVO,MortuaryDeceasedImageDtlVO[] deceasedImageUpdateVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DeceasedImageDtlDAOi deceasedImageDAO=new DeceasedImageDtlDAO(tx);
					
			if(deceasedImageUpdateVO!=null){
				for(int i=0;i<deceasedImageUpdateVO.length;i++){
					deceasedImageDAO.updateIsDefaultValue(deceasedImageUpdateVO[i], userVO);
				}
			}
			if(deceasedImageVO!=null){
				for(int i=0;i<deceasedImageVO.length;i++){
					deceasedImageDAO.create(deceasedImageVO[i], userVO);
				}
			}
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
		
	}
	
	
	public void deleteDeceasedImage(MortuaryDeceasedImageDtlVO[] deceasedImageVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DeceasedImageDtlDAOi deceasedImageDAO=new DeceasedImageDtlDAO(tx);
			
			if(deceasedImageVO!=null){
				for(int i=0;i<deceasedImageVO.length;i++){
					deceasedImageDAO.deleteDeceasedImage(deceasedImageVO[i], userVO);
				}
			}
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
		
	}
	
	
	

	
	public String saveExternalDeceasedAcceptance(DeceasedDetailVO deceasedDtlVO,DeceasedRelativeDtlVO deceasedRelativeVO,DeceasedStorageDtlVO deceasedStorageVO,DeceasedBroughtByDtlVO deceasedBroughtVO,MortuaryPoliceVerificationVO policeVerVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		String countMaxCapacity="";
		String deceasedNo="";
		String patDob="";
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			DeceasedDetailDAOi deceasedDetailDAO=new DeceasedDetailDAO(tx);
			DeceasedStorageDtlDAOi deceasedStorageDAO=new DeceasedStorageDtlDAO(tx);
			DeceasedRelativeDtlDAOi deceasedRelativeDAO=new DeceasedRelativeDtlDAO(tx);
			DeceasedBroughtByDtlDAOi deceasedBroughtDAO=new DeceasedBroughtByDtlDAO(tx);
			MortuaryPoliceVerificationDAOi policeVerDAO= new MortuaryPoliceVerificationDAO(tx);
			
			deceasedNo=deceasedDetailDAO.generateDeceasedNo(userVO);
			
			deceasedDtlVO.setDeceasedNo(deceasedNo);
			deceasedBroughtVO.setDeceasedNo(deceasedNo);
			
			if(deceasedDtlVO.getIsActualDob().equals(MortuaryConfig.NO))
			{
				patDob=essDAO.getPatientDOB(deceasedDtlVO.getPatAge(),deceasedDtlVO.getPatAgeUnit(),userVO);
				deceasedDtlVO.setPatDOB(patDob);
			}
			
			deceasedDetailDAO.create(deceasedDtlVO, userVO);
			deceasedBroughtDAO.create(deceasedBroughtVO,userVO);
			
			if(deceasedRelativeVO!=null)		//Inserting Relative Detail
			{
				deceasedRelativeVO.setDeceasedNo(deceasedNo);
				deceasedRelativeDAO.create(deceasedRelativeVO, userVO);
			}
			
			if(policeVerVO!=null)			//Inserting Police Verification Detail
			{
				policeVerVO.setDeceasedNo(deceasedNo);
				policeVerDAO.create(policeVerVO, userVO);
			}
			
			if(deceasedStorageVO!=null)			//Inserting Storage Detail
			{
				deceasedStorageVO.setDeceasedNo(deceasedNo);
				deceasedStorageDAO.create(deceasedStorageVO, userVO);
				count=deceasedStorageDAO.countdeadBodyInRack(deceasedStorageVO,userVO);
				countMaxCapacity=essDAO.countMaxCapacityOfRack(deceasedStorageVO.getChamberRackId(),userVO);
				if(Integer.parseInt(count)==Integer.parseInt(countMaxCapacity))
				{
					String chamberStatus=MortuaryConfig.CHAMBER_RACK_STATUS_OCCUPIED;
					essDAO.updateChamberStatus(chamberStatus,deceasedStorageVO.getChamberRackId(),userVO);
				}
			}
			
		}	
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
		return deceasedNo;
	}
	
	public String saveOnSpotDeceasedAcceptance(DeceasedRelativeDtlVO deceasedRelativeVO,DeceasedDetailVO deceasedDtlVO,DeceasedStorageDtlVO deceasedStorageVO,DeceasedBroughtByDtlVO deceasedBroughtVO,MortuaryPoliceVerificationVO policeVerVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		String countMaxCapacity="";
		String deceasedNo="";
		String patDob="";
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			DeceasedDetailDAOi deceasedDetailDAO=new DeceasedDetailDAO(tx);
			DeceasedStorageDtlDAOi deceasedStorageDAO=new DeceasedStorageDtlDAO(tx);
			DeceasedBroughtByDtlDAOi deceasedBroughtDAO=new DeceasedBroughtByDtlDAO(tx);
			MortuaryPoliceVerificationDAOi policeVerDAO= new MortuaryPoliceVerificationDAO(tx);
			DeceasedRelativeDtlDAOi deceasedRelativeDAO=new DeceasedRelativeDtlDAO(tx);
			
			deceasedNo=deceasedDetailDAO.generateDeceasedNo(userVO);
			
			deceasedDtlVO.setDeceasedNo(deceasedNo);
			deceasedBroughtVO.setDeceasedNo(deceasedNo);
			
			if(deceasedDtlVO.getIsActualDob().equals(MortuaryConfig.NO))
			{
				patDob=essDAO.getPatientDOB(deceasedDtlVO.getPatAge(),deceasedDtlVO.getPatAgeUnit(),userVO);
				deceasedDtlVO.setPatDOB(patDob);
			}
			
			deceasedDetailDAO.create(deceasedDtlVO, userVO);
			deceasedBroughtDAO.create(deceasedBroughtVO,userVO);
			
			policeVerVO.setDeceasedNo(deceasedNo);
			policeVerDAO.create(policeVerVO, userVO);
			
			if(deceasedRelativeVO!=null)
			{
				deceasedRelativeVO.setDeceasedNo(deceasedNo);
				deceasedRelativeDAO.create(deceasedRelativeVO, userVO);
			}
			
			if(deceasedStorageVO!=null)			//Inserting Storage Detail
			{
				deceasedStorageVO.setDeceasedNo(deceasedNo);
				deceasedStorageDAO.create(deceasedStorageVO, userVO);
				count=deceasedStorageDAO.countdeadBodyInRack(deceasedStorageVO,userVO);
				countMaxCapacity=essDAO.countMaxCapacityOfRack(deceasedStorageVO.getChamberRackId(),userVO);
				if(Integer.parseInt(count)==Integer.parseInt(countMaxCapacity))
				{
					String chamberStatus=MortuaryConfig.CHAMBER_RACK_STATUS_OCCUPIED;
					essDAO.updateChamberStatus(chamberStatus,deceasedStorageVO.getChamberRackId(),userVO);
				}
			}
			
			
		}	
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
		return deceasedNo;
	}
	
	public void saveSampleSendDetail(MortuaryExtLabRequestDtlVO extLabReqDtlVO,List<MortuaryExtReqSampleDtlVO> lstSample,List<MortuaryExtLabInvReqDtlVO> lstInvestigation,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String itemStatus="";
		String requestId="";
		String maxSrNo="";
		
		try
		{
			tx.begin();
			itemStatus=MortuaryConfig.DECEASED_ITEM_STATUS_SEND_TO_LAB;
			ExtLabReqDtlDAOi extLabReqDAO=new ExtLabReqDtlDAO(tx);
			ExtLabInvReqDtlDAOi extLabInvReqDAO=new ExtLabInvReqDtlDAO(tx);
			ExtReqSampleDtlDAOi extReqSampleDAO=new ExtReqSampleDtlDAO(tx);
			PostmortemItemDtlDAOi itemDAO=new PostmortemItemDtlDAO(tx);
			
			
			maxSrNo=extLabReqDAO.getmaxSrNoBypostmortemId(extLabReqDtlVO.getPostmortemId(),userVO);
			if(maxSrNo.length()==1)
				maxSrNo="0"+maxSrNo;
			requestId=extLabReqDtlVO.getPostmortemId()+maxSrNo;
			
			extLabReqDtlVO.setRequestId(requestId);
			extLabReqDAO.create(extLabReqDtlVO, userVO);
			
			for(int i=0;i<lstSample.size();i++)
			{
				lstSample.get(i).setRequestId(requestId);
				extReqSampleDAO.create(lstSample.get(i), userVO);
				
				itemDAO.updateStatus(extLabReqDtlVO.getPostmortemId(),lstSample.get(i).getItemCode(),itemStatus, userVO);
			}
			
			for(int i=0;i<lstInvestigation.size();i++)
			{
				lstInvestigation.get(i).setRequestId(requestId);
				extLabInvReqDAO.create(lstInvestigation.get(i), userVO);
			}
			
			
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public void saveSampleResultEntry(MortuaryExtLabRequestDtlVO extLabReqDtlVO,List<MortuaryExtLabInvReqDtlVO> lstInvestigation,List<MortuaryExtReqSampleDtlVO> lstSample,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String itemStatus="";
		
		try
		{
			tx.begin();
			itemStatus=MortuaryConfig.DECEASED_ITEM_STATUS_MORTUARY;
			ExtLabReqDtlDAOi extLabReqDAO=new ExtLabReqDtlDAO(tx);
			ExtLabInvReqDtlDAOi extLabInvReqDAO=new ExtLabInvReqDtlDAO(tx);
			ExtReqSampleDtlDAOi extReqSampleDAO=new ExtReqSampleDtlDAO(tx);
			PostmortemItemDtlDAOi itemDAO=new PostmortemItemDtlDAO(tx);
			
			extLabReqDAO.updateFinalResult(extLabReqDtlVO,userVO);
			
			for(int i=0;i<lstInvestigation.size();i++)
			{
				extLabInvReqDAO.updateLabResult(lstInvestigation.get(i),userVO);
			}
			
			for(int i=0;i<lstSample.size();i++)
			{
				extReqSampleDAO.updateReceiveRemarks(lstSample.get(i),userVO);
				itemDAO.updateStatus(extLabReqDtlVO.getPostmortemId(),lstSample.get(i).getItemCode(),itemStatus, userVO);
			}
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public void saveUnknownBodyIdentificationDetail(DeceasedDetailVO deceasedDtlVO,DeceasedRelativeDtlVO relativeVO, DeceasedIdentityDtlVO identityDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String patDob="";
		
		try
		{
			tx.begin();
			DeceasedDetailDAOi deceasedDtlDAO=new DeceasedDetailDAO(tx);
			DeceasedRelativeDtlDAOi relativeDAO=new DeceasedRelativeDtlDAO(tx);
			DeceasedIdentityDtlDAOi identityDAO=new DeceasedIdentityDtlDAO(tx);
			MortuaryEssDAOi essDAO=new MortuaryEssDAO(tx);
			
			identityDAO.create(identityDtlVO, userVO);
			if(relativeVO!=null)
			{
				relativeDAO.create(relativeVO, userVO);
			}
			
			if(deceasedDtlVO.getIsActualDob().equals(MortuaryConfig.NO))
			{
				patDob=essDAO.getPatientDOB(deceasedDtlVO.getPatAge(),deceasedDtlVO.getPatAgeUnit(),userVO);
				deceasedDtlVO.setPatDOB(patDob);
			}
			deceasedDtlDAO.updateUnknownToKnownDetail(deceasedDtlVO,userVO);
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public void saveDeceasedClaimedDetail(DeceasedDetailVO deceasedDtlVO,DeceasedRelativeDtlVO relativeVO, DeceasedIdentityDtlVO identityDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			DeceasedDetailDAOi deceasedDtlDAO=new DeceasedDetailDAO(tx);
			DeceasedRelativeDtlDAOi relativeDAO=new DeceasedRelativeDtlDAO(tx);
			DeceasedIdentityDtlDAOi identityDAO=new DeceasedIdentityDtlDAO(tx);
			
			identityDAO.create(identityDtlVO, userVO);
			if(relativeVO!=null)
			{
				relativeDAO.create(relativeVO, userVO);
			}
			
			deceasedDtlDAO.updateDeceasedClaimedStatus(deceasedDtlVO,userVO);
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public void savePostmortemHandoverDetail(PostmortemHandoverDtlVO postmortemHandoverDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			PostmortemHandoverDtlDAOi postmortemHandoverDtlDAO=new PostmortemHandoverDtlDAO(tx);
			postmortemHandoverDtlDAO.create(postmortemHandoverDtlVO, userVO);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	public void saveDeceasedGeneralAppearance(DeceasedDetailVO deceasedDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			DeceasedDetailDAOi decesedDtlDAO=new DeceasedDetailDAO(tx);
			decesedDtlDAO.updateDeceasedGeneralAppearance(deceasedDtlVO,userVO);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
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
	}
	
	
	public DeceasedHandoverDtlVO getDeceasedDtlAfterHandover(String deceasedNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeceasedHandoverDtlVO deceasedHandOverDtlVO=new DeceasedHandoverDtlVO();
		
		try
		{
			tx.begin();
			DeceasedDetailDAOi deceasedDtlDAO=new DeceasedDetailDAO(tx);
			deceasedHandOverDtlVO=deceasedDtlDAO.getDeceasedDtlAfterHandover(deceasedNo, userVO);
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
		return deceasedHandOverDtlVO; 
	}
	
	//added for the purpose of duplicate MLC Check at External Deceased Entry By Shruti Shail on 11-May-2017
	public Boolean chkDuplicateMLC(String mlcNo,String HospCode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Boolean chk;
		
		try
		{
			tx.begin();
			DeceasedDetailDAOi deceasedDtlDAO=new DeceasedDetailDAO(tx);
			chk=deceasedDtlDAO.chkDuplicateMLC(mlcNo,HospCode);
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
		return chk; 
	}
}
