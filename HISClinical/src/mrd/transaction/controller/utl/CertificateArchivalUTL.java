package mrd.transaction.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.CertificateArchivalDATA;
import mrd.transaction.controller.fb.CertificateArchivalFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.RackMstVO;

public class CertificateArchivalUTL extends ControllerUTIL
{
	public static void getRecordListToArchived(CertificateArchivalFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String recordType="";
		String recordTypeName="";
		String mrdCode="";
		String admno="";
		String crno="";
		Map map=new HashMap();
	//	MrdRecordDtlVO[] MrdRecordDtlVO=new MrdRecordDtlVO();
		
		try
		{
			recordType=(String)session.getAttribute(MrdConfig.RECORD_TYPE_FOR_ACCEPTANCE);
			recordTypeName=(String)session.getAttribute(MrdConfig.RECORD_TYPE_NAME_FOR_ACCEPTANCE);
			mrdCode=(String)session.getAttribute(MrdConfig.MRD_CODE_FOR_ACCEPTANCE);
			admno=(String)session.getAttribute(MrdConfig.ADM_NO_FOR_ACCEPTANCE);//ADDED BY SWATI ON DATE 10-MAY-2019
			
			crno=(String)session.getAttribute(MrdConfig.CR_NO_FOR_ACCEPTANCE);//ADDED BY SWATI ON DATE 13-MAY-2019
			
			/*recordType=(String)session.getAttribute(MrdConfig.RECORD_DISPATCH_VO_ARRAY);
			recordTypeName=(String)session.getAttribute(MrdConfig.RECORD_DISPATCH_VO_ARRAY);
			mrdCode=(String)session.getAttribute(MrdConfig.RECORD_DISPATCH_VO_ARRAY);
*/
			fb.setMrdCode(mrdCode);
			fb.setRecordType(recordType);
			fb.setRecordTypeName(recordTypeName);
			fb.setPatAdmNo(admno);
			
			System.out.println("recordType:::::"+recordType);
			System.out.println("admno:::::"+admno);
			if(!(recordType.equals("-1"))){
				System.out.println("getEssentialForRecordArchived_AdmNo:::::::");
				
			 map=CertificateArchivalDATA.getEssentialForRecordArchived(recordType,mrdCode,getUserVO(request),admno);
			WebUTIL.setMapInSession(map, request);
			}
			//rack fpor cr no nd adm no
			if(recordType.equals("-1")){
			 map=CertificateArchivalDATA.getRackDtlForCrAdmNo(recordType,mrdCode,getUserVO(request),admno);
			 WebUTIL.setMapInSession(map, request);
			}
			
			
			if(!(admno.equals(""))){
				System.out.println("getEssentialForRecordArchived_AdmNo");
				Map map2=CertificateArchivalDATA.getEssentialForRecordArchived_AdmNo(recordType,mrdCode,getUserVO(request),admno);
				WebUTIL.setMapInSession(map2, request);
				MrdRecordDtlVO[] mrddtlvo=(MrdRecordDtlVO[])map2.get(MrdConfig.ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_ADM_NO_VO);
				WebUTIL.setAttributeInSession(request,MrdConfig.MRD_RECORD_DTL_VO , mrddtlvo);
			}
			
			if(!(crno.equals(""))){
				System.out.println("getEssentialForRecordArchived_crno");
				Map map2=CertificateArchivalDATA.getEssentialForRecordArchived_CrNo(recordType,mrdCode,getUserVO(request),admno,crno);
				WebUTIL.setMapInSession(map2, request);
				MrdRecordDtlVO[] mrddtlvo2=(MrdRecordDtlVO[])map2.get(MrdConfig.ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_CR_NO_VO);
				WebUTIL.setAttributeInSession(request,MrdConfig.MRD_RECORD_DTL_VO , mrddtlvo2);
			}
			
		
			
			
			
			
			RackMstVO[] rackMstVO=(RackMstVO[])map.get(MrdConfig.LIST_OF_RACK_BASED_ON_MRD);
			if(rackMstVO!=null)
				objStatus.add(Status.TRANSINPROCESS);
			else
				objStatus.add(Status.TRANSINPROCESS,"","No Rack Found");
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
	
	public static void getShelfBasedOnRack(CertificateArchivalFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String rackId=fb.getRackId();
		String recordType="";
		String tempStr="";
		String rackInfo="";
		
		try
		{
			if(fb.getSelectedRecord()!=null)
			{
				for(int i=0;i<fb.getSelectedRecord().length;i++)
				{
					tempStr=tempStr+fb.getSelectedRecord()[i]+"@";
				}
			}
			if(tempStr.length()!=0)tempStr=tempStr.substring(0,tempStr.length()-1);
			fb.setTempSelectedChk(tempStr);
			
			RackMstVO[] rackMstVO=(RackMstVO[])session.getAttribute(MrdConfig.LIST_OF_RACK_BASED_ON_MRD); 
			for(int i=0;i<rackMstVO.length;i++)
			{
				if(fb.getRackId().equals(rackMstVO[i].getRackId()))
				{
					rackInfo=rackInfo+rackMstVO[i].getBuildingCode()+" / "+rackMstVO[i].getBlockId()+" / "+rackMstVO[i].getFloorId()+" / "+(rackMstVO[i].getRoomId()==null?"-":rackMstVO[i].getRoomId());
					fb.setRackInfo(rackInfo);
					break;
				}
			}
			
			recordType=(String)session.getAttribute(MrdConfig.RECORD_TYPE_FOR_ACCEPTANCE);
			List lstShelf=CertificateArchivalDATA.getShelfBasedOnRack(recordType,rackId,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.LIST_OF_SHELF_BASED_ON_RACK , lstShelf);
			
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
	
	public static void saveRecordArchivalInMrd(CertificateArchivalFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List<MrdRecordDtlVO> lstMrdRecordVO=new ArrayList<MrdRecordDtlVO>();
		MrdRecordDtlVO[] arrMrdRecordVO=null;
		
		try
		{
			arrMrdRecordVO=(MrdRecordDtlVO[])session.getAttribute(MrdConfig.ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_RECORD_TYPE_VO);
			if(arrMrdRecordVO!=null){
			for(int i=0;i<fb.getSelectedRecord().length;i++)
			{
				for(int j=0;j<arrMrdRecordVO.length;j++)
				{
					if(fb.getSelectedRecord()[i].equals(arrMrdRecordVO[j].getMrdRecordId()))
					{
						MrdRecordDtlVO mrdRecordVO=new MrdRecordDtlVO();
						mrdRecordVO.setMrdRecordId(arrMrdRecordVO[j].getMrdRecordId());
						mrdRecordVO.setRackId(fb.getRackId());
						mrdRecordVO.setShelfId(fb.getShelfId());
						mrdRecordVO.setPutBy(fb.getPutBy());
						
						lstMrdRecordVO.add(mrdRecordVO);
					}
				}
			}
			}
			else
			{
				MrdRecordDtlVO [] mrddtlvo=(MrdRecordDtlVO[])session.getAttribute(MrdConfig.MRD_RECORD_DTL_VO);

				
				
				for(int i=0;i<fb.getSelectedRecord().length;i++)
				{
					
							MrdRecordDtlVO mrdRecordVO=new MrdRecordDtlVO();
							mrdRecordVO.setRackId(fb.getRackId());
							mrdRecordVO.setShelfId(fb.getShelfId());
							mrdRecordVO.setPutBy(fb.getPutBy());
							mrdRecordVO.setMrdRecordId(mrddtlvo[0].getMrdRecordId());
							
							lstMrdRecordVO.add(mrdRecordVO);
						
				}	
				
			}
			
			CertificateArchivalDATA.saveRecordArchivalInMrd(lstMrdRecordVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Archived In Mrd");
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
}
