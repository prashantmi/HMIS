package mortuary.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.DeceasedStorageDATA;
import mortuary.transaction.controller.fb.DeceasedStorageFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ChamberRackMasterVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.DeceasedStorageDtlVO;

public class DeceasedStorageUTL extends ControllerUTIL
{
	public static void getDeceasedListForStorage(DeceasedStorageFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedDetailVO[] deceasedDtlVO=null;
		
		try
		{
			deceasedDtlVO=DeceasedStorageDATA.getDeceasedListForStorage(getUserVO(request));
			for(int i=0;i<deceasedDtlVO.length;i++)
			{
				if(deceasedDtlVO[i].getUnidentifiedBody()==null || deceasedDtlVO[i].getUnidentifiedBody().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
					deceasedDtlVO[i].setDeceasedType(MortuaryConfig.DECEASED_TYPE_UNKNOWN);
					//deceasedDtlVO[i].setColor(MortuaryConfig.DECEASED_COLOR_UNKNOWN);
			
				else if(deceasedDtlVO[i].getIsBroughtDead().equals(RegistrationConfig.IS_BROUGHT_DEAD_TRUE))
					deceasedDtlVO[i].setDeceasedType(MortuaryConfig.DECEASED_TYPE_BROUGHT_DEAD);
					//deceasedDtlVO[i].setColor(MortuaryConfig.DECEASED_COLOR_BROUGHT_DEAD);
				
				else if(deceasedDtlVO[i].getIsMlcCase().equals(MortuaryConfig.IS_MLC_YES))
					deceasedDtlVO[i].setDeceasedType(MortuaryConfig.DECEASED_TYPE_MLC);
					//deceasedDtlVO[i].setColor(MortuaryConfig.DECEASED_COLOR_MLC);
				
				else
					deceasedDtlVO[i].setDeceasedType(MortuaryConfig.DECEASED_TYPE_NORMAL);
					//deceasedDtlVO[i].setColor(MortuaryConfig.DECEASED_COLOR_NORMAL);
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.DECEASED_LIST_IN_STREACHER, deceasedDtlVO);
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
	
	public static void getDeceasedDetail(DeceasedStorageFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			Map mp=DeceasedStorageDATA.getDeceasedEssentialStorageDetail(getUserVO(request));
			WebUTIL.setMapInSession(mp, request);
			
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
	
	/** Getting Rack Based On The Chamber
	 * @param fb
	 * @param request
	 */
	public static void getRackBasedOnChamber(DeceasedStorageFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session= request.getSession();
		List lstRack=new ArrayList();
		
		try
		{
			String chamber=fb.getChamberId();
			ChamberRackMasterVO[] chamberRackVO=(ChamberRackMasterVO[])session.getAttribute(MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_VO);
			for(int i=0;i<chamberRackVO.length;i++)
			{
				if(chamber.equals(chamberRackVO[i].getChamberId()))
				{
					Entry ent =new Entry();
					ent.setValue(chamberRackVO[i].getChamberRackId());
					ent.setLabel(chamberRackVO[i].getRackName());
					lstRack.add(ent);
				}
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST, lstRack);
			if(lstRack.size()==0)
				objStatus.add(Status.TRANSINPROCESS,"","No Rack Found");
			else
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
	
	public static void getChamberBasedOnMortuaryArea(DeceasedStorageFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		List lstChamber=new ArrayList();
		
		try
		{
			String mortuaryArea=fb.getMortuaryAreaCode();
			lstChamber=DeceasedStorageDATA.getChamberBasedOnMortuaryArea(mortuaryArea,getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ESENTIAL_ALL_CHAMBER_BASED_ON_MORTUARY_AREA, lstChamber);
			if(lstChamber.size()==0)
				objStatus.add(Status.TRANSINPROCESS,"","No Chamber Found");
			else
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
	
	public static void getAreaBasedOnMortuary(DeceasedStorageFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		List lstArea=new ArrayList();
		
		try
		{
			String mortuary=fb.getMortuaryCode();
			lstArea=DeceasedStorageDATA.getAreaBasedOnMortuary(mortuary,getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ESENTIAL_ALL_AREA_BASED_ON_MORTUARY, lstArea);
			if(lstArea.size()==0)
				objStatus.add(Status.TRANSINPROCESS,"","No Mortuary Area Found");
			else
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
	
	public static void saveDeceasedStorageDetail(DeceasedStorageFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedStorageDtlVO deceasedStorageVO=new DeceasedStorageDtlVO();
		DeceasedRelativeDtlVO deceasedRelativeVO=new DeceasedRelativeDtlVO();
		
		try
		{
			HelperMethods.populate(deceasedStorageVO, fb);
			if(fb.getIsStorageByRelative()!=null)
			{
				if(fb.getIsStorageByRelative().equals(MortuaryConfig.IS_DECEASED_STORAGE_BY_RELATIVE_YES))
				{
					deceasedRelativeVO.setDeceasedNo(fb.getDeceasedNo());
					deceasedRelativeVO.setRelativeName(fb.getStorageRelativeName());
					deceasedRelativeVO.setRelativeAddress(fb.getStorageRelativeAddress());
					deceasedRelativeVO.setRelativeCode(fb.getStorageRelativeCode());
					deceasedRelativeVO.setRelativeContactNo(fb.getStorageRelativeContactNo());
					deceasedRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_STORAGE);
				}
				else
					deceasedRelativeVO=null;
			}
			else
				deceasedRelativeVO=null;
			
			DeceasedStorageDATA.saveDeceasedStorageDetail(deceasedRelativeVO,deceasedStorageVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Deceased Storage Detail Saved Successfully");
			
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
