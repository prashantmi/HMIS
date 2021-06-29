package mortuary.transaction.controller.utl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.DeceasedTileDATA;
import mortuary.transaction.controller.fb.DeceasedTileFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.UserVO;

public class DeceasedTileUTL extends ControllerUTIL
{
	public static void getDeceasedDetailByDeceasedNo(DeceasedTileFB fb, HttpServletRequest request)
	{
		Status  objStatus=new Status();
		HttpSession session=request.getSession();
		UserVO userVO =getUserVO(request);
		String mlcNoNCaseNo="";
		String mlcNo="";
		String caseNo="";
		
		try
		{
			DeceasedDetailVO deceasedDetailVO=(DeceasedDetailVO)session.getAttribute(MortuaryConfig.DECEASED_DETAIL_VO);
			
			if(deceasedDetailVO == null || !deceasedDetailVO.getDeceasedNo().equals(fb.getDeceasedNo()))
			{
				DeceasedDetailVO deceasedVO=new DeceasedDetailVO();
				deceasedVO.setDeceasedNo(fb.getDeceasedNo());
				
				deceasedVO=DeceasedTileDATA.getDeceasedDetailByDeceasedNo(deceasedVO,userVO);
				WebUTIL.setAttributeInSession(request, MortuaryConfig.DECEASED_DETAIL_VO, deceasedVO);
			//	HelperMethods.populate(fb, deceasedVO);
				
				///////FOR MLC NO/////
		//		if(deceasedVO.getIsMlcCase().equals(MortuaryConfig.IS_MLC_YES))
				{
					try
					{
						if(!(fb.getMlcNo()==null || fb.getMlcNo().equals("") || fb.getMlcNo().equals(" ")))
						{
							deceasedVO.setMlcNo(fb.getMlcNo());
						}
						else
						{
							mlcNoNCaseNo=DeceasedTileDATA.getMlcNo(deceasedVO.getDeceasedNo(),deceasedVO.getPatCrNo(),userVO);
							mlcNo=mlcNoNCaseNo.split("@")[0];
							caseNo=mlcNoNCaseNo.split("@")[1];
							deceasedVO.setMlcNo(mlcNo);
							deceasedVO.setCaseNo(caseNo);
						}
					}
					catch(HisRecordNotFoundException e)
					{		
						deceasedVO.setMlcNo("Yet To Be Alloted");	 
					}	
				}
				HelperMethods.populate(fb, deceasedVO);
				if(fb.getCaseNo()==null || fb.getCaseNo().equals(""))
					fb.setCaseNo(caseNo);
			}
			else
			{
				HelperMethods.populate(fb, deceasedDetailVO);
			}
			 objStatus.add(Status.INPROCESS,"","");
		}
		catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
			e.printStackTrace();
		}		
		catch(HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}	
	}
	
}
