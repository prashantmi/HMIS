package mortuary.transaction.controller.utl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.BodyIdentificationDetailDATA;
import mortuary.transaction.controller.fb.BodyIdentificationDetailFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedIdentityDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;

public class BodyIdentificationDetailUTL extends ControllerUTIL
{
	public static void getEssentialForIdentification(BodyIdentificationDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		DeceasedIdentityDtlVO[] identityDtlVO=null;
		DeceasedIdentityDtlVO[] identityByPolice=null;
		DeceasedIdentityDtlVO[] identityByRelative=null;
	//	boolean exist=false;
		String deceasedNo="";
		int countRel=0;
		int countPol=0;
		
		
		try
		{
			DeceasedDetailVO[] deceasedVO=(DeceasedDetailVO[])session.getAttribute(DynamicDeskConfig.POSTMORTEM_DESK_DECEASED_LIST);
			for(int i=0;i<deceasedVO.length;i++)
			{
				if(fb.getPostmortemId().equalsIgnoreCase(deceasedVO[i].getPostmortemId()))
				{
					deceasedNo=deceasedVO[i].getDeceasedNo();
					break;
				}	
			}
			fb.setDeceasedNo(deceasedNo);
			DeceasedTileUTL.getDeceasedDetailByDeceasedNo(fb, request);
			
			identityDtlVO=BodyIdentificationDetailDATA.getIdentifiedByDetail(fb.getDeceasedNo(),getUserVO(request));
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
				
			}
			
			DeceasedRelativeDtlVO[] deceasedRelativeVO=BodyIdentificationDetailDATA.getExistingRelative(fb.getDeceasedNo(),getUserVO(request));
			if(deceasedRelativeVO!=null)
			{
				fb.setExistingRelativeFlag(MortuaryConfig.DEAD_BODY_IDENTIFY_BY_EXISTING);
				fb.setRelativeExist(MortuaryConfig.RELATIVE_EXIST_TO_IDENTIFY_YES);
			}
			else
			{
				fb.setExistingRelativeFlag(MortuaryConfig.DEAD_BODY_IDENTIFY_BY_NEW);
				fb.setRelativeExist(MortuaryConfig.RELATIVE_EXIST_TO_IDENTIFY_NO);
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_DECEASED_RELATIVE_VO, deceasedRelativeVO);
			
			//exist=BodyIdentificationDetailDATA.checkBodyIdentified(fb.getDeceasedNo(),getUserVO(request));
			/*if(exist)
			{
				throw new HisDuplicateRecordException("Body Already Identified");
			}
			else
			{*/
				List lstRelation=BodyIdentificationDetailDATA.getAllRelationship(getUserVO(request));
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION, lstRelation);
				objStatus.add(Status.TRANSINPROCESS);
		//	}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDuplicateRecordException e)
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
	
	public static void saveBodyIdentificationDetail(BodyIdentificationDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
	//	DeceasedIdentityDtlVO identityDtlVO=new DeceasedIdentityDtlVO();
	//	DeceasedRelativeDtlVO relativeVO=new DeceasedRelativeDtlVO();
		DeceasedIdentityDtlVO[] arrIdentityDtlVO=null;
		DeceasedRelativeDtlVO[] arrRelativeVO=null;
		
		try
		{
			//int index=Integer.parseInt(fb.getRelativeExistRadioBtn());
			DeceasedRelativeDtlVO[] deceasedExistingRelativeVO=(DeceasedRelativeDtlVO[])session.getAttribute(MortuaryConfig.ARR_DECEASED_RELATIVE_VO);
			
			
			if(fb.getIsIdentifiyBy().equals(MortuaryConfig.BODY_IDENTIFIED_BY_RELATIVE))
			{
				if(fb.getExistingRelativeFlag().equals(MortuaryConfig.DEAD_BODY_IDENTIFY_BY_NEW))
				{
					arrIdentityDtlVO=new DeceasedIdentityDtlVO[1];
					arrRelativeVO= new DeceasedRelativeDtlVO[1];
					
					arrIdentityDtlVO[0]=new DeceasedIdentityDtlVO();
					arrIdentityDtlVO[0].setIdentifiyByName(fb.getIdentifiyByName());
					arrIdentityDtlVO[0].setIdentifiyByAddress(fb.getIdentifiyByAddress());
					arrIdentityDtlVO[0].setIdentifiyByPhone(fb.getIdentifiyByPhone());
					arrIdentityDtlVO[0].setRelativeCode(fb.getRelativeCode());
					
					arrIdentityDtlVO[0].setIsIdentifiyBy(fb.getIsIdentifiyBy());
					arrIdentityDtlVO[0].setIdentityMode(MortuaryConfig.BODY_IDENTIFICATION_MODE_POSTMORTEM);
					arrIdentityDtlVO[0].setDeceasedNo(fb.getDeceasedNo());
					arrIdentityDtlVO[0].setPostmortemId(fb.getPostmortemId());
					arrIdentityDtlVO[0].setIdentityRemarks(fb.getIdentityRemarks());
					
					
					arrRelativeVO[0]=new DeceasedRelativeDtlVO();
					arrRelativeVO[0].setDeceasedNo(fb.getDeceasedNo());
					arrRelativeVO[0].setRelativeName(fb.getIdentifiyByName());
					arrRelativeVO[0].setRelativeCode(fb.getRelativeCode());
					arrRelativeVO[0].setRelativeAddress(fb.getIdentifiyByAddress());
					arrRelativeVO[0].setRelativeContactNo(fb.getIdentifiyByPhone());
					arrRelativeVO[0].setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_POSTMORTEM_PERFORMANCE);
				}
				if(fb.getExistingRelativeFlag().equals(MortuaryConfig.DEAD_BODY_IDENTIFY_BY_EXISTING))
				{
					arrIdentityDtlVO=new DeceasedIdentityDtlVO[fb.getRelativeExistChk().length];
					arrRelativeVO= new DeceasedRelativeDtlVO[fb.getRelativeExistChk().length];
					
					for(int i=0;i<fb.getRelativeExistChk().length;i++)
					{
						int index=Integer.parseInt(fb.getRelativeExistChk()[i]);
						
						arrIdentityDtlVO[i]=new DeceasedIdentityDtlVO();
						arrIdentityDtlVO[i].setIdentifiyByName(deceasedExistingRelativeVO[index].getRelativeName());
						arrIdentityDtlVO[i].setIdentifiyByAddress(deceasedExistingRelativeVO[index].getRelativeAddress());
						arrIdentityDtlVO[i].setIdentifiyByPhone(deceasedExistingRelativeVO[index].getRelativeContactNo());
						arrIdentityDtlVO[i].setRelativeCode(deceasedExistingRelativeVO[index].getRelativeCode());
						
						arrIdentityDtlVO[i].setIsIdentifiyBy(fb.getIsIdentifiyBy());
						arrIdentityDtlVO[i].setIdentityMode(MortuaryConfig.BODY_IDENTIFICATION_MODE_POSTMORTEM);
						arrIdentityDtlVO[i].setDeceasedNo(fb.getDeceasedNo());
						arrIdentityDtlVO[i].setPostmortemId(fb.getPostmortemId());
						arrIdentityDtlVO[i].setIdentityRemarks(fb.getIdentityRemarks());
						
						//relativeVO=null;
						arrRelativeVO[i]=new DeceasedRelativeDtlVO();
						arrRelativeVO[i].setDeceasedNo(fb.getDeceasedNo());
						arrRelativeVO[i].setRelativeName(deceasedExistingRelativeVO[index].getRelativeName());
						arrRelativeVO[i].setRelativeCode(deceasedExistingRelativeVO[index].getRelativeCode());
						arrRelativeVO[i].setRelativeAddress(deceasedExistingRelativeVO[index].getRelativeAddress());
						arrRelativeVO[i].setRelativeContactNo(deceasedExistingRelativeVO[index].getRelativeContactNo());
						arrRelativeVO[i].setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_POSTMORTEM_PERFORMANCE);
					}	
				}
			}
			else
			{
				arrIdentityDtlVO=new DeceasedIdentityDtlVO[1];
				arrIdentityDtlVO[0]=new DeceasedIdentityDtlVO();
				
				arrIdentityDtlVO[0].setIdentifiyByName(fb.getOfficerName());
				arrIdentityDtlVO[0].setOfficerBadgeNo(fb.getOfficerBadgeNo());
				arrIdentityDtlVO[0].setOfficerDesignation(fb.getOfficerDesignation());
				
				arrIdentityDtlVO[0].setIsIdentifiyBy(fb.getIsIdentifiyBy());
				arrIdentityDtlVO[0].setIdentityMode(MortuaryConfig.BODY_IDENTIFICATION_MODE_POSTMORTEM);
				arrIdentityDtlVO[0].setDeceasedNo(fb.getDeceasedNo());
				arrIdentityDtlVO[0].setPostmortemId(fb.getPostmortemId());
				arrIdentityDtlVO[0].setIdentityRemarks(fb.getIdentityRemarks());
				
				//relativeVO=null;
			}
			BodyIdentificationDetailDATA.saveBodyIdentificationDetail(arrRelativeVO, arrIdentityDtlVO, getUserVO(request)); 
			objStatus.add(Status.UNSUCESSFULL,"","Record Save Successfully"); 
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
