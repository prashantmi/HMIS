package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordMovementVO;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.OPDFileReturnDATA;
import mrd.transaction.controller.fb.OPDFileReturnFB;

public class OPDFileReturnUTIL extends ControllerUTIL {

	public static void getListOfOPDFileToReturn(OPDFileReturnFB fb,	HttpServletRequest request) {
		Status objStatus=new Status();
		//MrdRecordDtlVO[] mrdRecordDtlVO; 
		HttpSession session=request.getSession();
		Map essentialMap=new HashMap();
		try{
			fb.setMrdCode((String)session.getAttribute(MrdConfig.MRD_CODE_FOR_TRACKING));
			
			essentialMap=OPDFileReturnDATA.getListOfOpdFilesToReturn(fb.getMrdCode(),getUserVO(request));
			WebUTIL.setMapInSession(essentialMap, request);
			
			//mrdRecordDtlVO=OPDFileReturnDATA.getListOfOpdFilesToReturn(fb.getMrdCode(),getUserVO(request));
			//WebUTIL.setAttributeInSession(request, MrdConfig.ARRAY_MRD_RECORD_VOLIST_OPD_FILES_FOR_RETURN, mrdRecordDtlVO);
			
			objStatus.add(Status.RECORDFOUND);
			}catch(HisRecordNotFoundException e){
				//essentialMap=e.getEssentialMap();
				//WebUTIL.setMapInSession(essentialMap, request);
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL,"" ,e.getMessage());
				
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

	public static void save(OPDFileReturnFB fb, HttpServletRequest request) {
		Status objStatus=new Status();
		MrdRecordDtlVO[] mrdRecordDtlVO; 
		MrdRecordDtlVO[] mrdRecordVOForUpdate=new MrdRecordDtlVO[fb.getMrdRecordId().length];
		MrdRecordMovementVO[] mrdRecordMovementVO=new MrdRecordMovementVO[fb.getMrdRecordId().length];
		HttpSession session=request.getSession();
		try{
			mrdRecordDtlVO=(MrdRecordDtlVO[])session.getAttribute(MrdConfig.ARRAY_MRD_RECORD_VOLIST_OPD_FILES_FOR_RETURN);
			for(int i=0;i<fb.getMrdRecordId().length;i++)
			{
				for(int j=0;j<mrdRecordDtlVO.length;j++)
				{
					if(mrdRecordDtlVO[j].getMrdRecordId().equals(fb.getMrdRecordId()[i]))
					{
						///for updating record detail
						mrdRecordVOForUpdate[i]=mrdRecordDtlVO[j];
						
						/////setting status if reocrd is placed in rack shelf or not
						if((mrdRecordDtlVO[j].getRackId()==null || mrdRecordDtlVO[j].getRackId().equals("")) && 
								(mrdRecordDtlVO[j].getShelfId()==null || mrdRecordDtlVO[j].getShelfId().equals("")))
							mrdRecordVOForUpdate[i].setRecordStatus(MrdConfig.MRD_RECORD_STATUS_IN_MRD);
						else
							mrdRecordVOForUpdate[i].setRecordStatus(MrdConfig.MRD_RECORD_STATUS_ARCHIVED);
						
						mrdRecordVOForUpdate[i].setIssueFlag(MrdConfig.MRD_RECORD_ISSUE_FLAG_IN_MRD);
						
						///for inserting into movement detail
						mrdRecordMovementVO[i]=new MrdRecordMovementVO();
						HelperMethods.populate(mrdRecordMovementVO[i], mrdRecordVOForUpdate[i]);
						mrdRecordMovementVO[i].setMovementType(MrdConfig.MOVEMENT_TYPE_RETURN_TO_MRD);
						mrdRecordMovementVO[i].setHandOverToId(fb.getHandOverById());
						mrdRecordMovementVO[i].setRemarks(fb.getRemarks());
						
					}
				}
			}
			
			OPDFileReturnDATA.saveOPDFileReturnDetail(mrdRecordVOForUpdate,mrdRecordMovementVO,getUserVO(request));
			
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
