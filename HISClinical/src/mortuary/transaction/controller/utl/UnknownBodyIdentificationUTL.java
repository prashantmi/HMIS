package mortuary.transaction.controller.utl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.classic.Session;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.UnknownBodyIdentificationDATA;
import mortuary.transaction.controller.fb.UnknownBodyIdentificationFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedIdentityDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;

public class UnknownBodyIdentificationUTL extends ControllerUTIL
{
	public static void getUnknownBodyList(UnknownBodyIdentificationFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedDetailVO[] unknownDeceasedDtlVO=null;
		
		try
		{
			unknownDeceasedDtlVO=UnknownBodyIdentificationDATA.getUnknownBodyList(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.UNKNOWN_N_UNCLAIMED_BODY_LIST, unknownDeceasedDtlVO);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void getEssentialForIdentification(UnknownBodyIdentificationFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedDetailVO deceasedVO=new DeceasedDetailVO();
		DeceasedDetailVO[] unknownDeceasedDtlVO=null;
		HttpSession session=request.getSession();
		String isClaimedFlag="";
		
		/*DeceasedIdentityDtlVO[] identityDtlVO=null;
		DeceasedIdentityDtlVO[] identityByPolice=null;
		DeceasedIdentityDtlVO[] identityByRelative=null;
		int countRel=0;
		int countPol=0;*/
		
		try
		{
			/*identityDtlVO=UnknownBodyIdentificationDATA.getIdentifiedByDetail(fb.getDeceasedNo(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_BODY_IDENTIFIED_BY, identityDtlVO);
			if(identityDtlVO!=null)
			{
				for(int i=0;i<identityDtlVO.length;i++)
				{
					if(identityDtlVO[i].getIsIdentifiyBy().equals(MortuaryConfig.BODY_IDENTIFIED_BY_RELATIVE))
						countRel++;
					else
						countPol++;
				}
				
				identityByPolice=new DeceasedIdentityDtlVO[countPol];
				identityByRelative=new DeceasedIdentityDtlVO[countRel];
				int j=0,k=0;
				
				for(int i=0;i<identityDtlVO.length;i++)
				{
					if(identityDtlVO[i].getIsIdentifiyBy().equals(MortuaryConfig.BODY_IDENTIFIED_BY_RELATIVE))
					{
						identityByRelative[j]=identityDtlVO[i];
						j++;
					}
					else
					{
						identityByPolice[k]=identityDtlVO[i];
						k++;
					}
						
				}
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_BODY_IDENTIFIED_BY_POLICE, identityByPolice);
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_BODY_IDENTIFIED_BY_RELATIVE, identityByRelative);
				
			}*/
			setSysdate(request);
			unknownDeceasedDtlVO=(DeceasedDetailVO[])session.getAttribute(MortuaryConfig.UNKNOWN_N_UNCLAIMED_BODY_LIST);
			for(int i=0;i<unknownDeceasedDtlVO.length;i++)
			{
				if(fb.getDeceasedNo().equals(unknownDeceasedDtlVO[i].getDeceasedNo()))
				{
					if(unknownDeceasedDtlVO[i].getUnidentifiedBody()!=null)
					{
					if(unknownDeceasedDtlVO[i].getUnidentifiedBody().equals(MortuaryConfig.NO))
						isClaimedFlag=MortuaryConfig.YES;
					}
					else
						isClaimedFlag=MortuaryConfig.NO;
					
					
				}		
			}
			
			fb.setIsClaimedFlag(isClaimedFlag);
			Map map= UnknownBodyIdentificationDATA.getEssentialForUnknown(fb.getDeceasedNo(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			deceasedVO=(DeceasedDetailVO)map.get(MortuaryConfig.DECEASED_DETAIL_BY_DECEASED_NO_VO);

			fb.setPatFirstName(deceasedVO.getPatFirstName());
			fb.setPatGenderCode(deceasedVO.getPatGenderCode());
			fb.setIsActualDob(deceasedVO.getIsActualDob());
			//fb.setPatAge(deceasedVO.getPatAge());
			fb.setPatDOB(deceasedVO.getPatDOB());
			
			String patAgeWithUnit = deceasedVO.getPatAge();
			int startidx = patAgeWithUnit.lastIndexOf(" ");
			String ageunit = patAgeWithUnit.substring(startidx + 1);
			patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
			fb.setPatAge(patAgeWithUnit);
		
			if (ageunit.equalsIgnoreCase("yr")) fb.setPatAgeUnit("Y");
			if (ageunit.equalsIgnoreCase("mth")) fb.setPatAgeUnit("M");
			if (ageunit.equalsIgnoreCase("d")) fb.setPatAgeUnit("D");
			if (ageunit.toLowerCase().startsWith("w")) fb.setPatAgeUnit("W");
			
			List lstRelation=UnknownBodyIdentificationDATA.getAllRelationship(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION, lstRelation);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void saveUnknownBodyIdentificationDetail(UnknownBodyIdentificationFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedIdentityDtlVO identityDtlVO=new DeceasedIdentityDtlVO();
		DeceasedRelativeDtlVO relativeVO=new DeceasedRelativeDtlVO();
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		
		try
		{
			identityDtlVO.setIsIdentifiyBy(fb.getIsIdentifiyBy());
			
			identityDtlVO.setDeceasedNo(fb.getDeceasedNo());
			identityDtlVO.setIdentityRemarks(fb.getIdentityRemarks());
			
			if(fb.getIsIdentifiyBy().equals(MortuaryConfig.BODY_IDENTIFIED_BY_RELATIVE))
			{
				identityDtlVO.setIdentifiyByName(fb.getIdentifiyByName());
				identityDtlVO.setIdentifiyByAddress(fb.getIdentifiyByAddress());
				identityDtlVO.setIdentifiyByPhone(fb.getIdentifiyByPhone());
				identityDtlVO.setRelativeCode(fb.getRelativeCode());
				
				relativeVO.setDeceasedNo(fb.getDeceasedNo());
				relativeVO.setRelativeName(fb.getIdentifiyByName());
				relativeVO.setRelativeCode(fb.getRelativeCode());
				relativeVO.setRelativeAddress(fb.getIdentifiyByAddress());
				relativeVO.setRelativeContactNo(fb.getIdentifiyByPhone());
				relativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_IDENTIFIED);
			}
			else
			{
				identityDtlVO.setIdentifiyByName(fb.getOfficerName());
				identityDtlVO.setOfficerBadgeNo(fb.getOfficerBadgeNo());
				identityDtlVO.setOfficerDesignation(fb.getOfficerDesignation());
				
				relativeVO=null;
			}
			
			HelperMethods.populate(deceasedDtlVO, fb);
			if(fb.getIsClaimed()!=null)
			{
				identityDtlVO.setIdentityMode(MortuaryConfig.BODY_IDENTIFICATION_MODE_CLAIMANT);
				deceasedDtlVO.setIsClaimed(MortuaryConfig.YES);
			}
			else
			{
				identityDtlVO.setIdentityMode(MortuaryConfig.BODY_IDENTIFICATION_MODE_UNIDENTIFY_BODY);
				deceasedDtlVO.setIsClaimed(MortuaryConfig.NO);
			}	
			deceasedDtlVO.setUnidentifiedBody(MortuaryConfig.NO);
			
			UnknownBodyIdentificationDATA.saveUnknownBodyIdentificationDetail(deceasedDtlVO,relativeVO, identityDtlVO, getUserVO(request));
			objStatus.add(Status.DONE,"","Record Added Successfully");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void saveDeceasedClaimedDetail(UnknownBodyIdentificationFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedIdentityDtlVO identityDtlVO=new DeceasedIdentityDtlVO();
		DeceasedRelativeDtlVO relativeVO=new DeceasedRelativeDtlVO();
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		
		try
		{
			identityDtlVO.setIsIdentifiyBy(fb.getIsIdentifiyBy());
			identityDtlVO.setDeceasedNo(fb.getDeceasedNo());
			identityDtlVO.setIdentityRemarks(fb.getIdentityRemarks());
			
			if(fb.getIsIdentifiyBy().equals(MortuaryConfig.BODY_IDENTIFIED_BY_RELATIVE))
			{
				identityDtlVO.setIdentifiyByName(fb.getIdentifiyByName());
				identityDtlVO.setIdentifiyByAddress(fb.getIdentifiyByAddress());
				identityDtlVO.setIdentifiyByPhone(fb.getIdentifiyByPhone());
				identityDtlVO.setRelativeCode(fb.getRelativeCode());
				
				relativeVO.setDeceasedNo(fb.getDeceasedNo());
				relativeVO.setRelativeName(fb.getIdentifiyByName());
				relativeVO.setRelativeCode(fb.getRelativeCode());
				relativeVO.setRelativeAddress(fb.getIdentifiyByAddress());
				relativeVO.setRelativeContactNo(fb.getIdentifiyByPhone());
				relativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_IDENTIFIED);
			}
			else
			{
				identityDtlVO.setIdentifiyByName(fb.getOfficerName());
				identityDtlVO.setOfficerBadgeNo(fb.getOfficerBadgeNo());
				identityDtlVO.setOfficerDesignation(fb.getOfficerDesignation());
				
				relativeVO=null;
			}
			
			identityDtlVO.setIdentityMode(MortuaryConfig.BODY_IDENTIFICATION_MODE_CLAIMANT);
			deceasedDtlVO.setIsClaimed(MortuaryConfig.YES);
			deceasedDtlVO.setDeceasedNo(fb.getDeceasedNo());
			
			UnknownBodyIdentificationDATA.saveDeceasedClaimedDetail(deceasedDtlVO,relativeVO, identityDtlVO, getUserVO(request));
			objStatus.add(Status.DONE,"","Deceased Claimed Detail Added Successfully");
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}	
	
}
