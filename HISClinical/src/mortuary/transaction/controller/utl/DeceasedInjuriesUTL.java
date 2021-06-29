package mortuary.transaction.controller.utl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.DeceasedInjuriesDATA;
import mortuary.transaction.controller.fb.DeceasedInjuriesFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PostmortemDetailVO;

public class DeceasedInjuriesUTL extends ControllerUTIL
{
	public static void getEssentialForInjuries(DeceasedInjuriesFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String crNo="";
		String deathDateTime="";
		boolean exist=false;
		
		
		try
		{
			exist=DeceasedInjuriesDATA.getGeneralAppearanceExsistance(fb.getPostmortemId(),getUserVO(request));
			if(exist)
			{
				PostmortemDetailVO injuryVO=DeceasedInjuriesDATA.getAddedInjuryDetailToDisplay(fb.getPostmortemId(),getUserVO(request));
				
				if(injuryVO.getInjuryTypeCode()==null)
				{
					DeceasedDetailVO[] deceasedVO=(DeceasedDetailVO[])session.getAttribute(DynamicDeskConfig.POSTMORTEM_DESK_DECEASED_LIST);
					for(int i=0;i<deceasedVO.length;i++)
					{
						if(fb.getPostmortemId().equalsIgnoreCase(deceasedVO[i].getPostmortemId()))
						{
							crNo=deceasedVO[i].getPatCrNo();
							deathDateTime=deceasedVO[i].getDeathDate();
							break;
						}	
					}
					PatientDeathDetailVO deathVO=DeceasedInjuriesDATA.getExistingInjuryDetail(crNo,getUserVO(request));
					HelperMethods.populate(fb, deathVO);
					fb.setInjuryExistingFlag(MortuaryConfig.NO);
					fb.setDeathDate(deathDateTime.split(" ")[0]);
					fb.setDeathTimeHr(deathDateTime.split(" ")[1].split(":")[0]);
					fb.setDeathTimeMin(deathDateTime.split(" ")[1].split(":")[1]);
				}	
				else
				{
					HelperMethods.populate(fb, injuryVO);
					fb.setInjuryDate(injuryVO.getInjuryDateTime().split(" ")[0]);
					fb.setInjuryTimeHr(injuryVO.getInjuryDateTime().split(" ")[1].split(":")[0]);
					fb.setInjuryTimeMin(injuryVO.getInjuryDateTime().split(" ")[1].split(":")[1]);
					fb.setInjuryExistingFlag(MortuaryConfig.YES);
					fb.setWeaponUsedValue(injuryVO.getWeaponUsed());
					fb.setInjuryPhotoValue(injuryVO.getInjuryPhoto());
				}
				
				Map map=DeceasedInjuriesDATA.getEssentialForInjuries(getUserVO(request));
				WebUTIL.setMapInSession(map, request);
				
				objStatus.add(Status.TRANSINPROCESS);
			}
			else
				throw new HisDuplicateRecordException("First Enter The General Appearance Details. Then Enter The Injury Details");
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
	
	public static void saveInjuriesDetail(DeceasedInjuriesFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		PostmortemDetailVO postmortemInjuryVO=new PostmortemDetailVO();
		
		try
		{
			HelperMethods.populate(postmortemInjuryVO, fb);
			postmortemInjuryVO.setInjuryDateTime(fb.getInjuryDate()+" "+fb.getInjuryTimeHr()+":"+fb.getInjuryTimeMin());
			
			if(fb.getWeaponUsed()==null)
				postmortemInjuryVO.setWeaponUsed(MortuaryConfig.NO);
			else
				postmortemInjuryVO.setWeaponUsed(MortuaryConfig.YES);
			if(fb.getInjuryPhoto()==null)
				postmortemInjuryVO.setInjuryPhoto(MortuaryConfig.NO);
			else
				postmortemInjuryVO.setInjuryPhoto(MortuaryConfig.YES);
			
			DeceasedInjuriesDATA.saveInjuriesDetail(postmortemInjuryVO,getUserVO(request));
			objStatus.add(Status.UNSUCESSFULL,"","Injury Details Saved Successfully");
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
