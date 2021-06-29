package mrd.transaction.controller.utl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.CertificateAcceptDATA;
import mrd.transaction.controller.fb.CertificateAcceptFB;

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
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordEnclosureDtlVO;
import hisglobal.vo.RecordLostFoundDtlVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;

public class CertificateAcceptUTL extends ControllerUTIL
{
	public static void getRecordListToAccept(CertificateAcceptFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
	//	RecordDispatchDtlVO[] recordDispatchVO=null;
		String recordType="";
		String recordTypeName="";
		String mrdCode="";
		String searchDate="";
		
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
			
			if(fb.getSearchDate().equals(""))
				searchDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			else
				searchDate=fb.getSearchDate();
		//	getRackBasedOnMrd(fb,request);
			/*recordDispatchVO=CertificateAcceptDATA.getRecordListToAcceptByRecordType(recordType,getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_RECORD_LIST_TO_ACCEPT_IN_MRD_BY_RECORD_TYPE_VO, recordDispatchVO);*/
			Map map=CertificateAcceptDATA.getEssentialForAcceptRecordInMrd(recordType,mrdCode,searchDate,getUserVO(request));
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
	
	public static void getRackBasedOnMrd(CertificateAcceptFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String mrdCode=fb.getMrdCode();
		String recordType="";
		RackMstVO[] rackMstVO=null;
		
		try
		{
			recordType=(String)session.getAttribute(MrdConfig.RECORD_TYPE_FOR_ACCEPTANCE);
			rackMstVO=CertificateAcceptDATA.getRackBasedOnMrd(recordType,mrdCode,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.LIST_OF_RACK_BASED_ON_MRD , rackMstVO);
			
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
	
	public static void getShelfBasedOnRack(CertificateAcceptFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String rackId=fb.getRackId();
		String recordType="";
		String tempStr="";
		String rackInfo="";
		String tempVerStr="";
		
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
					rackInfo=rackInfo+rackMstVO[i].getBuildingCode()+" / "+rackMstVO[i].getBlockId()+" / "+rackMstVO[i].getFloorId()+" / "+(rackMstVO[i].getRoomId()==null?"-":rackMstVO[i].getRoomId());
					fb.setRackInfo(rackInfo);
					break;
				}
			}
			
			recordType=(String)session.getAttribute(MrdConfig.RECORD_TYPE_FOR_ACCEPTANCE);
			List lstShelf=CertificateAcceptDATA.getShelfBasedOnRack(recordType,rackId,getUserVO(request));
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
	
	public static void saveRecordAccepted(CertificateAcceptFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List<MrdRecordDtlVO> lstMrdRecordVO=new ArrayList<MrdRecordDtlVO>();
		List<RecordTypeWiseEnclosureMstVO> lstEnclosure=new ArrayList<RecordTypeWiseEnclosureMstVO>();
		List<RecordCheckListDtlVO> lstCheckList=new ArrayList<RecordCheckListDtlVO>();
		List<RecordEnclosureDtlVO> lstRecordEnclosureDtl=new ArrayList<RecordEnclosureDtlVO>();
		RecordDispatchDtlVO[] arrRecordDispatchVO=null;
		
		
		try
		{
			arrRecordDispatchVO=(RecordDispatchDtlVO[])session.getAttribute(MrdConfig.ARR_RECORD_LIST_TO_ACCEPT_IN_MRD_BY_RECORD_TYPE_VO);
			for(int i=0;i<fb.getSelectedRecord().length;i++)
			{
				for(int j=0;j<arrRecordDispatchVO.length;j++)
				{
					if(fb.getSelectedRecord()[i].equals(arrRecordDispatchVO[j].getDispatchId()))
					{
						MrdRecordDtlVO mrdRecordVO=new MrdRecordDtlVO();
						mrdRecordVO.setRecordId(arrRecordDispatchVO[j].getRecordId());
						mrdRecordVO.setRecordDesc(arrRecordDispatchVO[j].getRecordDesc());
						mrdRecordVO.setRecordType(fb.getRecordType());
						mrdRecordVO.setRecordStatus(MrdConfig.MRD_RECORD_STATUS_IN_MRD);
						mrdRecordVO.setMrdCode(fb.getMrdCode());
						mrdRecordVO.setPatCrNo(arrRecordDispatchVO[j].getPatCrNo());
						mrdRecordVO.setEpisodeCode(arrRecordDispatchVO[j].getEpisodeCode());
						mrdRecordVO.setEpisodeVisitNo(arrRecordDispatchVO[j].getEpisodeVisitNo());
						mrdRecordVO.setPatAdmNo(arrRecordDispatchVO[j].getPatAdmNo());
						mrdRecordVO.setDispatchId(arrRecordDispatchVO[j].getDispatchId());
						
						lstMrdRecordVO.add(mrdRecordVO);
						
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
			
			CertificateAcceptDATA.saveRecordAccepted(lstMrdRecordVO,lstEnclosure,lstCheckList,lstRecordEnclosureDtl,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Accepted In Mrd");
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void saveRecordArchived(CertificateAcceptFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List<MrdRecordDtlVO> lstMrdRecordVO=new ArrayList<MrdRecordDtlVO>();
		List<RecordTypeWiseEnclosureMstVO> lstEnclosure=new ArrayList<RecordTypeWiseEnclosureMstVO>();
		List<RecordCheckListDtlVO> lstCheckList=new ArrayList<RecordCheckListDtlVO>();
		List<RecordEnclosureDtlVO> lstRecordEnclosureDtl=new ArrayList<RecordEnclosureDtlVO>();
		RecordDispatchDtlVO[] arrRecordDispatchVO=null;
		
		
		try
		{
			arrRecordDispatchVO=(RecordDispatchDtlVO[])session.getAttribute(MrdConfig.ARR_RECORD_LIST_TO_ACCEPT_IN_MRD_BY_RECORD_TYPE_VO);
			for(int i=0;i<fb.getSelectedRecord().length;i++)
			{
				for(int j=0;j<arrRecordDispatchVO.length;j++)
				{
					if(fb.getSelectedRecord()[i].equals(arrRecordDispatchVO[j].getDispatchId()))
					{
						MrdRecordDtlVO mrdRecordVO=new MrdRecordDtlVO();
						mrdRecordVO.setRecordId(arrRecordDispatchVO[j].getRecordId());
						mrdRecordVO.setRecordDesc(arrRecordDispatchVO[j].getRecordDesc());
						mrdRecordVO.setRecordType(fb.getRecordType());
						mrdRecordVO.setRecordStatus(MrdConfig.MRD_RECORD_STATUS_ARCHIVED);
						mrdRecordVO.setMrdCode(fb.getMrdCode());
						mrdRecordVO.setPatCrNo(arrRecordDispatchVO[j].getPatCrNo());
						mrdRecordVO.setEpisodeCode(arrRecordDispatchVO[j].getEpisodeCode());
						mrdRecordVO.setEpisodeVisitNo(arrRecordDispatchVO[j].getEpisodeVisitNo());
						mrdRecordVO.setPatAdmNo(arrRecordDispatchVO[j].getPatAdmNo());
						mrdRecordVO.setRackId(fb.getRackId());
						mrdRecordVO.setShelfId(fb.getShelfId());
						mrdRecordVO.setPutBy(fb.getPutBy());
						mrdRecordVO.setDispatchId(arrRecordDispatchVO[j].getDispatchId());
						
						lstMrdRecordVO.add(mrdRecordVO);
						
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
			
			CertificateAcceptDATA.saveRecordArchived(lstMrdRecordVO,lstEnclosure,lstCheckList,lstRecordEnclosureDtl,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Archived In Mrd");
		}	
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	public static void saveRecordLost(CertificateAcceptFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List<RecordLostFoundDtlVO> lstLostRecord=new ArrayList<RecordLostFoundDtlVO>();
		RecordDispatchDtlVO[] arrRecordDispatchVO=null;
		
		try
		{
			arrRecordDispatchVO=(RecordDispatchDtlVO[])session.getAttribute(MrdConfig.ARR_RECORD_LIST_TO_ACCEPT_IN_MRD_BY_RECORD_TYPE_VO);
			
			for(int i=0;i<fb.getSelectedRecord().length;i++)
			{
				for(int j=0;j<arrRecordDispatchVO.length;j++)
				{
					if(fb.getSelectedRecord()[i].equals(arrRecordDispatchVO[j].getDispatchId()))
					{
						RecordLostFoundDtlVO recordLostVO=new RecordLostFoundDtlVO();
						recordLostVO.setDispatchId(arrRecordDispatchVO[j].getDispatchId());
						recordLostVO.setReportedBy(fb.getReportedBy());
						recordLostVO.setMrdCode(fb.getMrdCode());
						recordLostVO.setLastSeenDateTime(fb.getLastSeenDate()+" "+fb.getLastSeenTimeHr()+":"+fb.getLastSeenTimeMin());
						recordLostVO.setLostArea(fb.getLostArea());
						recordLostVO.setLostType(MrdConfig.RECORD_LOST_TYPE_COMPLETE);
						recordLostVO.setLostRemarks(fb.getLostRemarks());
						recordLostVO.setRecordId(arrRecordDispatchVO[j].getRecordId());
												
						lstLostRecord.add(recordLostVO);
					}
				}
			}	
			
			CertificateAcceptDATA.saveRecordLost(lstLostRecord,getUserVO(request));
			objStatus.add(Status.DONE,"","Lost Record Detail Saved Successfully");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}	
	
	public static void getEnclosureDetail(CertificateAcceptFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List<RecordTypeWiseEnclosureMstVO> lstEnclosureVO=new ArrayList<RecordTypeWiseEnclosureMstVO>();
		Map checkListMap=new HashMap();
		String tempStr="";
		
		try
		{
			lstEnclosureVO=CertificateAcceptDATA.getEnclosureDetail(fb.getDispatchId(),fb.getRecordType(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.ENCLOSURE_DETAIL_ACCEPT_IN_MRD_VO,lstEnclosureVO);
			
			Map map=CertificateAcceptDATA.getCheckListDetail(fb.getDispatchId(),fb.getRecordType(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			
			checkListMap=(Map)session.getAttribute(MrdConfig.CHECKLIST_ARCHIVED_IN_MRD_MAP);
			if(checkListMap!=null)
			{
				List lstcheckListVO=(List)checkListMap.get(fb.getDispatchId());
				if(lstcheckListVO!=null)
				{
					for(int i=0;i<lstcheckListVO.size();i++)
					{
						RecordCheckListDtlVO checkListDtlVO=new RecordCheckListDtlVO();
						checkListDtlVO=(RecordCheckListDtlVO)lstcheckListVO.get(i);
						tempStr=tempStr+checkListDtlVO.getCheckListId()+"@";
					}
					if(tempStr.length()>0)tempStr=tempStr.substring(0, tempStr.length()-1);
					fb.setArchivalChkValue(tempStr);
				}
			}
			
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
	
	public static void addEnclosureDetail(CertificateAcceptFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List<RecordTypeWiseEnclosureMstVO> lstEnclosureInMrdVO=new ArrayList<RecordTypeWiseEnclosureMstVO>();
		Map enclosureListMap=new HashMap();
		List<RecordCheckListDtlVO> lstArchivalChecklistVO=new ArrayList<RecordCheckListDtlVO>();
		Map checkListMap=new HashMap();
		
		try
		{
			enclosureListMap=(Map)session.getAttribute(MrdConfig.ENCLOSURE_ACCEPTED_IN_MRD_MAP);
			for(int i=0;i<fb.getEnclosurePages().length;i++)
			{
				RecordTypeWiseEnclosureMstVO enclosureInMrdVO=new RecordTypeWiseEnclosureMstVO();
				enclosureInMrdVO.setDispatchId(fb.getDispatchId());
				enclosureInMrdVO.setVerifiedByMrd(fb.getEnclosurePages()[i]);
				enclosureInMrdVO.setRecordTypeId(fb.getRecordType());
				enclosureInMrdVO.setEnclosureId(fb.getHiddenEnclosureId()[i]);
				
				lstEnclosureInMrdVO.add(enclosureInMrdVO);
			}
			if(enclosureListMap==null)
			{
				enclosureListMap=new HashMap();
				enclosureListMap.put(fb.getDispatchId(), lstEnclosureInMrdVO);
			}
			else
			{
				enclosureListMap.put(fb.getDispatchId(), lstEnclosureInMrdVO);
			}
						
			WebUTIL.setAttributeInSession(request, MrdConfig.ENCLOSURE_ACCEPTED_IN_MRD_MAP, enclosureListMap);
			
			///////////Check List
			checkListMap=(Map)session.getAttribute(MrdConfig.CHECKLIST_ARCHIVED_IN_MRD_MAP);
			String[] strChkArchList=fb.getChkArchivalCheckList();
			if(!(strChkArchList==null||strChkArchList.length==0))
			{
				for(int i=0;i<fb.getChkArchivalCheckList().length;i++)
				{
					RecordCheckListDtlVO checkListDtlVO=new RecordCheckListDtlVO();
					checkListDtlVO.setDispatchId(fb.getDispatchId());
					checkListDtlVO.setCheckListId(fb.getChkArchivalCheckList()[i]);
					checkListDtlVO.setCheckListBy(MrdConfig.CHECKLIST_BY_ARCHIVAL_POINT);
					checkListDtlVO.setRecordType(fb.getRecordType());
					checkListDtlVO.setRecordId(fb.getRecordId());
					
					lstArchivalChecklistVO.add(checkListDtlVO);
				}
			}
			if(checkListMap==null)
			{
				checkListMap=new HashMap();
				checkListMap.put(fb.getDispatchId(), lstArchivalChecklistVO);
			}
			else
			{
				checkListMap.put(fb.getDispatchId(), lstArchivalChecklistVO);
			}
			
			WebUTIL.setAttributeInSession(request, MrdConfig.CHECKLIST_ARCHIVED_IN_MRD_MAP, checkListMap);
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
	
	//Added by Dheeraj on 05-Nov-2018
	public static void getStaffMembers(CertificateAcceptFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String rackId=fb.getRackId();
		String recordType="";
		String tempStr="";
		String rackInfo="";
		String tempVerStr="";
		
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
					rackInfo=rackInfo+rackMstVO[i].getBuildingCode()+" / "+rackMstVO[i].getBlockId()+" / "+rackMstVO[i].getFloorId()+" / "+(rackMstVO[i].getRoomId()==null?"-":rackMstVO[i].getRoomId());
					fb.setRackInfo(rackInfo);
					break;
				}
			}
			
			recordType=(String)session.getAttribute(MrdConfig.RECORD_TYPE_FOR_ACCEPTANCE);
			List lstShelf=CertificateAcceptDATA.getShelfBasedOnRack(recordType,rackId,getUserVO(request));
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
	
	
}
