package mrd.transaction.controller.utl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.OPDFileMovementDATA;
import mrd.transaction.controller.fb.OPDFileMovementFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordMovementVO;

public class OPDFileMovementUTIL extends ControllerUTIL {

	public static void getListOfOPDFileToMove(OPDFileMovementFB fb,	HttpServletRequest request) {
		Status objStatus=new Status();
		//MrdRecordDtlVO[] mrdRecordDtlVO; 
		HttpSession session=request.getSession();
		Map essentialMap=new HashMap();
		try{
			fb.setMrdCode((String)session.getAttribute(MrdConfig.MRD_CODE_FOR_TRACKING));
			essentialMap=OPDFileMovementDATA.getListOfOpdFilesToMove(fb.getMrdCode(),getUserVO(request));
			WebUTIL.setMapInSession(essentialMap, request);
			//mrdRecordDtlVO=OPDFileMovementDATA.getListOfOpdFilesToMove(fb.getMrdCode(),getUserVO(request));
			//WebUTIL.setAttributeInSession(request, MrdConfig.ARRAY_MRD_RECORD_VOLIST_OPD_FILES_FOR_MOVEMENT, mrdRecordDtlVO);
			
			objStatus.add(Status.RECORDFOUND);
			}catch(HisRecordNotFoundException e){
				essentialMap=e.getEssentialMap();
				WebUTIL.setMapInSession(essentialMap, request);
				e.printStackTrace();
				objStatus.add(Status.RECORDFOUND,"" ,e.getMessage());
				
			}catch(HisApplicationExecutionException e){
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE,"" ,e.getMessage());
			}
			catch(HisDataAccessException e){
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA,"" ,e.getMessage());
			}
			catch(Exception e){
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE,"" ,e.getMessage());
			}
			finally{
				WebUTIL.setStatus(request, objStatus);
			}
		
	}

	public static void save(OPDFileMovementFB fb, HttpServletRequest request) {
		Status objStatus=new Status();
		MrdRecordDtlVO[] mrdRecordDtlVO; 
		MrdRecordDtlVO[] mrdRecordVOForUpdate=new MrdRecordDtlVO[fb.getMrdRecordId().length];
		MrdRecordMovementVO[] mrdRecordMovementVO=new MrdRecordMovementVO[fb.getMrdRecordId().length];
		HttpSession session=request.getSession();
		try{
			mrdRecordDtlVO=(MrdRecordDtlVO[])session.getAttribute(MrdConfig.ARRAY_MRD_RECORD_VOLIST_OPD_FILES_FOR_MOVEMENT);
			for(int i=0;i<fb.getMrdRecordId().length;i++)
			{
				for(int j=0;j<mrdRecordDtlVO.length;j++)
				{
					if(mrdRecordDtlVO[j].getMrdRecordId().equals(fb.getMrdRecordId()[i]))
					{
						///for updating record detail
						mrdRecordVOForUpdate[i]=mrdRecordDtlVO[j];
						mrdRecordVOForUpdate[i].setRecordStatus(MrdConfig.MRD_RECORD_STATUS_ISSUE);
						mrdRecordVOForUpdate[i].setIssueFlag(MrdConfig.MRD_RECORD_ISSUE_FLAG_MOVEMENT);
						
						///for inserting into movement detail
						mrdRecordMovementVO[i]=new MrdRecordMovementVO();
						HelperMethods.populate(mrdRecordMovementVO[i], mrdRecordVOForUpdate[i]);
						mrdRecordMovementVO[i].setMovementType(MrdConfig.MOVEMENT_TYPE_ISSED_FROM_MRD);
						mrdRecordMovementVO[i].setHandOverToId(fb.getHandOverToId());
						mrdRecordMovementVO[i].setRemarks(fb.getRemarks());
						
					}
				}
			}
			
			OPDFileMovementDATA.saveOPDFileMovementDetail(mrdRecordVOForUpdate,mrdRecordMovementVO,getUserVO(request));
			
			objStatus.add(Status.DONE);
			}catch(HisRecordNotFoundException e){
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE,"" ,e.getMessage());
				
			}catch(HisApplicationExecutionException e){
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE,"" ,e.getMessage());
			}
			catch(HisDataAccessException e){
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA,"" ,e.getMessage());
			}
			catch(Exception e){
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE,"" ,e.getMessage());
			}
			finally{
				WebUTIL.setStatus(request, objStatus);
			}
		
		
	}

}
