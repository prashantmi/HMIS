package mrd.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.CertificateLostFoundDATA;
import mrd.transaction.controller.fb.CertificateLostFoundFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.RackMstVO;
import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordEnclosureDtlVO;
import hisglobal.vo.RecordLostFoundDtlVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;

public class CertificateLostFoundUTL extends ControllerUTIL
{
	public static void getEssentialForLostFoundDetail(CertificateLostFoundFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String recordType="";
		String recordTypeName="";
		String mrdCode="";
		
		try
		{
			setSysdate(request);
			String sys=(String)session.getAttribute(Config.SYSDATE);
			String time=sys.split(" ")[1];
			fb.setHiddenTimeHr(time.split(":")[0]);
			fb.setHiddenTimeMin(time.split(":")[1]);
			recordType=(String)session.getAttribute(MrdConfig.RECORD_TYPE_FOR_ACCEPTANCE);
			recordTypeName=(String)session.getAttribute(MrdConfig.RECORD_TYPE_NAME_FOR_ACCEPTANCE);
			mrdCode=(String)session.getAttribute(MrdConfig.MRD_CODE_FOR_ACCEPTANCE);
			fb.setMrdCode(mrdCode);
			fb.setRecordType(recordType);
			fb.setRecordTypeName(recordTypeName);
			
			Map map=CertificateLostFoundDATA.getEssentialForLostFoundDetail(recordType,mrdCode,getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			
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
	
	public static void getShelfBasedOnRack(CertificateLostFoundFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String rackId=fb.getRackId();
		String recordType="";
		String tempStr="";
		String tempVerStr="";
		String rackInfo="";
		
		try
		{
			if(fb.getVerifiedEnclosure()!=null)
			{
				for(int i=0;i<fb.getVerifiedEnclosure().length;i++)
				{
					tempVerStr=tempVerStr+fb.getVerifiedEnclosure()[i]+"@";
				}
			}
			if(tempVerStr.length()!=0)tempVerStr=tempVerStr.substring(0,tempVerStr.length()-1);
			fb.setTempVerifiedEnclosure(tempVerStr);
			
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
					rackInfo=rackInfo+rackMstVO[i].getBuildingCode()+" / "+rackMstVO[i].getBlockId()+" / "+rackMstVO[i].getFloorId()+" / "+rackMstVO[i].getRoomId();
					fb.setRackInfo(rackInfo);
					break;
				}
			}
			
			recordType=(String)session.getAttribute(MrdConfig.RECORD_TYPE_FOR_ACCEPTANCE);
			List lstShelf=CertificateLostFoundDATA.getShelfBasedOnRack(recordType,rackId,getUserVO(request));
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
	
	public static void saveFoundDetailNArchivedInMrd(CertificateLostFoundFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List<MrdRecordDtlVO> lstMrdRecordVO=new ArrayList<MrdRecordDtlVO>();
		List<RecordLostFoundDtlVO> lstFoundRecordVO=new ArrayList<RecordLostFoundDtlVO>();
		List<RecordTypeWiseEnclosureMstVO> lstEnclosure=new ArrayList<RecordTypeWiseEnclosureMstVO>();
		List<RecordCheckListDtlVO> lstCheckList=new ArrayList<RecordCheckListDtlVO>();
		List<RecordEnclosureDtlVO> lstRecordEnclosureDtl=new ArrayList<RecordEnclosureDtlVO>();
		RecordLostFoundDtlVO[] lostRecordVO=null;
		
		try
		{
			lostRecordVO=(RecordLostFoundDtlVO[])session.getAttribute(MrdConfig.ARR_LOST_RECORD_LIST_TO_BE_FOUND_BY_RECORD_TYPE_VO);
			
			for(int i=0;i<fb.getSelectedRecord().length;i++)
			{
				for(int j=0;j<lostRecordVO.length;j++)
				{
					if(fb.getSelectedRecord()[i].equals(lostRecordVO[j].getDispatchId()))
					{
						MrdRecordDtlVO mrdRecordVO=new MrdRecordDtlVO();
						RecordLostFoundDtlVO foundRecordVO=new RecordLostFoundDtlVO();
						
						mrdRecordVO.setRecordId(lostRecordVO[j].getRecordId());
						mrdRecordVO.setRecordDesc(lostRecordVO[j].getRecordDesc());
						mrdRecordVO.setRecordType(fb.getRecordType());
						mrdRecordVO.setRecordStatus(MrdConfig.MRD_RECORD_STATUS_ARCHIVED);
						mrdRecordVO.setMrdCode(fb.getMrdCode());
						mrdRecordVO.setPatCrNo(lostRecordVO[j].getPatCrNo());
						mrdRecordVO.setEpisodeCode(lostRecordVO[j].getEpisodeCode());
						mrdRecordVO.setEpisodeVisitNo(lostRecordVO[j].getEpisodeVisitNo());
						mrdRecordVO.setPatAdmNo(lostRecordVO[j].getPatAdmNo());
						mrdRecordVO.setRackId(fb.getRackId());
						mrdRecordVO.setShelfId(fb.getShelfId());
						mrdRecordVO.setDispatchId(lostRecordVO[j].getDispatchId());
						mrdRecordVO.setPutBy(fb.getPutBy());
						
						foundRecordVO.setDispatchId(lostRecordVO[j].getDispatchId());
						foundRecordVO.setSlNo(lostRecordVO[j].getSlNo());
						foundRecordVO.setFoundBy(fb.getFoundBy());
						foundRecordVO.setFoundDateTime(fb.getFoundDate()+" "+fb.getFoundTimeHr()+":"+fb.getFoundTimeMin());
						foundRecordVO.setFoundRemarks(fb.getFoundRemarks());
						
						lstMrdRecordVO.add(mrdRecordVO);
						lstFoundRecordVO.add(foundRecordVO);
						
					/////For Enclosure
						Map enclosureListMap=(Map)session.getAttribute(MrdConfig.ENCLOSURE_ACCEPTED_IN_MRD_MAP);
						if(enclosureListMap!=null)
						{
							List lstEnclosureInMrdVO=(List)enclosureListMap.get(fb.getSelectedRecord()[i]);
							for(int k=0;k<lstEnclosureInMrdVO.size();k++)
							{
								RecordTypeWiseEnclosureMstVO enclosureDtlVO=new RecordTypeWiseEnclosureMstVO();
								enclosureDtlVO=(RecordTypeWiseEnclosureMstVO)lstEnclosureInMrdVO.get(k);
								
								lstEnclosure.add(enclosureDtlVO);
								
								RecordEnclosureDtlVO recEnclosureDtlVO=new RecordEnclosureDtlVO();
								recEnclosureDtlVO.setEnclosureId(enclosureDtlVO.getEnclosureId());
								recEnclosureDtlVO.setPages(enclosureDtlVO.getVerifiedByMrd());
								recEnclosureDtlVO.setIsLost(MrdConfig.NO);
								
								lstRecordEnclosureDtl.add(recEnclosureDtlVO);
							}
						}
					////For CheckList
						Map checkListMap=(Map)session.getAttribute(MrdConfig.CHECKLIST_ARCHIVED_IN_MRD_MAP);
						if(checkListMap!=null)
						{
							List lstArchivalCheckList=(List)checkListMap.get(fb.getSelectedRecord()[i]);
							for(int k=0;k<lstArchivalCheckList.size();k++)
							{
								RecordCheckListDtlVO checkListDtlVO=new RecordCheckListDtlVO();
								checkListDtlVO=(RecordCheckListDtlVO)lstArchivalCheckList.get(k);
								
								lstCheckList.add(checkListDtlVO);
							}
						}	
					}
				}
			}	
			
			CertificateLostFoundDATA.saveFoundDetailNArchivedInMrd(lstEnclosure,lstMrdRecordVO,lstFoundRecordVO,lstCheckList,lstRecordEnclosureDtl,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Found And Archived In Mrd");
			
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
