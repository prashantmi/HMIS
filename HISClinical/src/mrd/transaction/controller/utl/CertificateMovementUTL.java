package mrd.transaction.controller.utl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.CertificateMovementDATA;
import mrd.transaction.controller.fb.CertificateMovementFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;

public class CertificateMovementUTL extends ControllerUTIL
{
	
	/** Getting The List of Certificate For Movement By Cr No 
	 * @param fb
	 * @param request
	 */
	public static boolean getAllCertificateForMoveByCrNo(CertificateMovementFB fb,HttpServletRequest request)
	{
		boolean flag = true;
		Status objStatus = new Status();
		try
		{
			CertificateIssueDtlVO[] certificateDtlVO=CertificateMovementDATA.getAllCertificateForMoveByCrNo(fb.getPatCrNo(),getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.LIST_OF_CERTIFICATE_FOR_MOVEMENT , certificateDtlVO);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	/** Getting The List of Certificate For Movement By Unit 
	 * @param fb
	 * @param request
	 */
	public static boolean getAllCertificateForMoveBydUnit(CertificateMovementFB fb,HttpServletRequest request)
	{
		boolean flag = true;
		Status objStatus = new Status();
		try
		{
			CertificateIssueDtlVO[] certificateDtlVO=CertificateMovementDATA.getAllCertificateForMoveBydUnit(fb.getUnitCode(),getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.LIST_OF_CERTIFICATE_FOR_MOVEMENT , certificateDtlVO);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	/** Saving Certificate Movement Detail
	 * @param fb
	 * @param request
	 */
	public static void saveCertificateMovement(CertificateMovementFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		RecordDispatchDtlVO[] recordMoveDtlVO=null;
		Map<String, String> map = null;
		int count=0,x=0,y=0,k=0;
		List<RecordCheckListDtlVO> lstRecordCheckList=new ArrayList<RecordCheckListDtlVO>();
		List<RecordDispatchDtlVO> lstRejectRecord=new ArrayList<RecordDispatchDtlVO>();
		
		try
		{
			if(session.getAttribute(MrdConfig.CERTIFICATE_MOVEMENT_SELECTED_CERTIFICATE)!=null)
				map=(HashMap<String, String>)session.getAttribute(MrdConfig.CERTIFICATE_MOVEMENT_SELECTED_CERTIFICATE);
			else
				map=new HashMap<String, String>();
			
			removeUnselectedCertificate(fb, request, map);
			
			CertificateIssueDtlVO[] certificateDtlVO=(CertificateIssueDtlVO[])session.getAttribute(MrdConfig.LIST_OF_CERTIFICATE_FOR_MOVEMENT);
			
			if(fb.getAcceptRejectFlag().equals(MrdConfig.CERTIFICATE_ACCEPT))
			{
				if(fb.getChk()!=null)
					y=fb.getChk().length;
				if(map!=null)
				{
					x=map.size();
				}
				count=x+y;
				
				//r=fb.getCompCheckListId().split("@").length;
				recordMoveDtlVO=new  RecordDispatchDtlVO[count];
				if(fb.getChk()!=null)
				{
					for(int i=0;i<fb.getChk().length;i++)
					{
						for(int j=0;j<certificateDtlVO.length;j++)
						{
							if(fb.getChk()[i].equals(certificateDtlVO[j].getCertificateId()))
							{
								//k=0;
								recordMoveDtlVO[i]=new RecordDispatchDtlVO();
								recordMoveDtlVO[i].setRecordId(certificateDtlVO[j].getCertificateId());
								recordMoveDtlVO[i].setRecordDesc(certificateDtlVO[j].getCertificateDesc());
								recordMoveDtlVO[i].setRecordType(certificateDtlVO[j].getRecordType());
								recordMoveDtlVO[i].setDeptUnitCode(certificateDtlVO[j].getDeptUnitCode());
								recordMoveDtlVO[i].setDispatchId(certificateDtlVO[j].getDispatchId());
								recordMoveDtlVO[i].setRecordStatus(MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_SEND_HANDOVER);
								recordMoveDtlVO[i].setSlNo(certificateDtlVO[j].getSlNo());
								k++;
								
								if(fb.getSelectedCheckList()!=null)
								{
									for(int ck=0;ck<fb.getSelectedCheckList().length;ck++)
									{
										RecordCheckListDtlVO recCheckListDtlVO=new RecordCheckListDtlVO();
										recCheckListDtlVO.setRecordId(certificateDtlVO[j].getCertificateId());
										recCheckListDtlVO.setDispatchId(certificateDtlVO[j].getDispatchId());
										recCheckListDtlVO.setRecordType(certificateDtlVO[j].getRecordType());
										recCheckListDtlVO.setCheckListId(fb.getSelectedCheckList()[ck]);
										recCheckListDtlVO.setRemarks(fb.getCheckListRemarks()[ck]);
										recCheckListDtlVO.setCheckListBy(MrdConfig.CHECK_LIST_BY_RECEIVING);
										
										lstRecordCheckList.add(recCheckListDtlVO);
									}
								}	
							}
						}
					}
				}
				
				if(map!=null)
				{
					//for(int i=0;i<map.size();i++)
					Iterator itr = ((Collection)map.values()).iterator();
					while(itr.hasNext())
					{
						String certId=(String)itr.next();
						for(int j=0;j<certificateDtlVO.length;j++)
						{
							if(certId.equals(certificateDtlVO[j].getCertificateId()))
							{
								int i=k;
								recordMoveDtlVO[i]=new RecordDispatchDtlVO();
								recordMoveDtlVO[i].setRecordId(certificateDtlVO[j].getCertificateId());
								recordMoveDtlVO[i].setRecordDesc(certificateDtlVO[j].getCertificateDesc());
								recordMoveDtlVO[i].setRecordType(certificateDtlVO[j].getRecordType());
								recordMoveDtlVO[i].setDeptUnitCode(certificateDtlVO[j].getDeptUnitCode());
								recordMoveDtlVO[i].setDispatchId(certificateDtlVO[j].getDispatchId());
								recordMoveDtlVO[i].setRecordStatus(MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_SEND_HANDOVER);
								recordMoveDtlVO[i].setSlNo(certificateDtlVO[j].getSlNo());
								k++;
								
								if(fb.getSelectedCheckList()!=null)
								{
									for(int ck=0;ck<fb.getSelectedCheckList().length;ck++)
									{
										RecordCheckListDtlVO recCheckListDtlVO=new RecordCheckListDtlVO();
										recCheckListDtlVO.setRecordId(certificateDtlVO[j].getCertificateId());
										recCheckListDtlVO.setDispatchId(certificateDtlVO[j].getDispatchId());
										recCheckListDtlVO.setRecordType(certificateDtlVO[j].getRecordType());
										recCheckListDtlVO.setCheckListId(fb.getSelectedCheckList()[ck]);
										recCheckListDtlVO.setRemarks(fb.getCheckListRemarks()[ck]);
										recCheckListDtlVO.setCheckListBy(MrdConfig.CHECK_LIST_BY_RECEIVING);
										
										lstRecordCheckList.add(recCheckListDtlVO);
									}
								}
							}
						}
					}
				}
				
				CertificateMovementDATA.saveCertificateMovement(lstRecordCheckList,recordMoveDtlVO,getUserVO(request));
				objStatus.add(Status.DONE,"","Certificate Accepted");
			}
			else
			{
				if(fb.getChk()!=null)
				{
					for(int i=0;i<fb.getChk().length;i++)
					{
						for(int j=0;j<certificateDtlVO.length;j++)
						{
							if(fb.getChk()[i].equals(certificateDtlVO[j].getCertificateId()))
							{
								RecordDispatchDtlVO recDispatchDtlVO=new RecordDispatchDtlVO();
								recDispatchDtlVO.setDispatchId(certificateDtlVO[j].getDispatchId());
								recDispatchDtlVO.setReturnReason(fb.getReturnReason());
								recDispatchDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_NOT_RECEIVED_RETURN);
								
								lstRejectRecord.add(recDispatchDtlVO);
							}
						}
					}
				}
				
				if(map!=null)
				{
					//for(int i=0;i<map.size();i++)
					Iterator itr = ((Collection)map.values()).iterator();
					while(itr.hasNext())
					{
						String certId=(String)itr.next();
						for(int j=0;j<certificateDtlVO.length;j++)
						{
							if(certId.equals(certificateDtlVO[j].getCertificateId()))
							{
								RecordDispatchDtlVO recDispatchDtlVO=new RecordDispatchDtlVO();
								recDispatchDtlVO.setDispatchId(certificateDtlVO[j].getDispatchId());
								recDispatchDtlVO.setReturnReason(fb.getReturnReason());
								recDispatchDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_NOT_RECEIVED_RETURN);
								
								lstRejectRecord.add(recDispatchDtlVO);
							}
						}
					}
				}
				
				CertificateMovementDATA.rejectCertificateHandover(lstRejectRecord,getUserVO(request));
				objStatus.add(Status.DONE,"","Certificate Rejected");
			}
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
	
	/** Getting The List of Unit
	 * @param fb
	 * @param request
	 */
	public static void getAllUnit(CertificateMovementFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			List lstUnit=CertificateMovementDATA.getAllUnit(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.ALL_UNIT_LIST_FOR_CERTIFICATE_MOVE, lstUnit);
			//objStatus.add(Status.TRANSINPROCESS);
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
	
	public static void setSelectedCertificateId(CertificateMovementFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		Map<String, String> map = null;
		String str="";
		
		try
		{
			if(fb.getSelectedCheckList()!=null)
			{
				for(int i=0;i<fb.getSelectedCheckList().length;i++)
				{
					str=str+fb.getSelectedCheckList()[i]+"@";
					
				}
				if(str.length()>0)str=str.substring(0,str.length()-1);
				fb.setTempChkValue(str);
			}
			
			if(session.getAttribute(MrdConfig.CERTIFICATE_MOVEMENT_SELECTED_CERTIFICATE)!=null)
				map=(HashMap<String, String>)session.getAttribute(MrdConfig.CERTIFICATE_MOVEMENT_SELECTED_CERTIFICATE);
			else
				map=new HashMap<String, String>();
			
			new HashMap<String, String>();	
			removeUnselectedCertificate(fb, request, map);
			if(fb.getChk()!=null)
			{
				if(fb.getChk().length>0)
				{
					for(int i=0;i<fb.getChk().length;i++)
					{
						map.put(fb.getChk()[i], fb.getChk()[i]);
					}
				}
			}	
			WebUTIL.setAttributeInSession(request, MrdConfig.CERTIFICATE_MOVEMENT_SELECTED_CERTIFICATE,map);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
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
		//return true;
	}
	
	public static void removeUnselectedCertificate(CertificateMovementFB fb,HttpServletRequest request,Map<String,String> map )
	{
		try
		{
			HttpSession session = request.getSession();
			CertificateIssueDtlVO[] certificateDtlVO=(CertificateIssueDtlVO[])session.getAttribute(MrdConfig.LIST_OF_CERTIFICATE_FOR_MOVEMENT);
			for(int i=Integer.parseInt(fb.getStartIdx());i<=Integer.parseInt(fb.getEndIdx());i++)
			{
				map.remove(certificateDtlVO[i].getCertificateId());
			}
		}
		catch (Exception e)
		{}
	}
	
	public static void getCheckListForMedNFitCertificate(CertificateMovementFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String recoedType="";
		RecordTypeCheckListMstVO[] recordTypeCheckListVO=null; 
		String unitCode="";
		
		try
		{
			unitCode=fb.getUnitCode();
			recoedType=MrdConfig.RECORD_TYPE_MEDICAL;
			Map map=CertificateMovementDATA.getEssentialForCertificateReceived(recoedType,unitCode,getUserVO(request) );
			WebUTIL.setMapInSession(map, request);
			/*recordTypeCheckListVO=CertificateMovementDATA.getCheckListForMedNFitCertificate(recoedType,getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_CHECKLIST_FOR_MEDICAL_CERTIFICATE_VO, recordTypeCheckListVO);*/
			
			
			recordTypeCheckListVO=(RecordTypeCheckListMstVO[])session.getAttribute(MrdConfig.ARR_CHECKLIST_FOR_MEDICAL_CERTIFICATE_VO);
			
			if(recordTypeCheckListVO.length>0)
			{	
				fb.setCheckListRemarks(new String[recordTypeCheckListVO.length]);
				objStatus.add(Status.NEW, "", "");
			}	
			else
				objStatus.add(Status.NEW, "", "No Check List Found");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
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
	
	public static void setSelectedCheckList(CertificateMovementFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		String tempStr="";
		
		try
		{
			if(fb.getChk()!=null)
			{
				for(int i=0;i<fb.getChk().length;i++)
				{
					tempStr=tempStr+fb.getChk()[i]+"@";
				}
				tempStr=tempStr.substring(0, tempStr.length()-1);
				fb.setTempChkValue(tempStr);
			}
			
			String str=fb.getCompCheckListId();
			
			WebUTIL.setAttributeInSession(request, MrdConfig.SELECTED_CHECK_LIST, str);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
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
	
	public static void rejectCertificateHandover(CertificateMovementFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		Map<String, String> map = null;
		List<RecordDispatchDtlVO> lstRejectRecord=new ArrayList<RecordDispatchDtlVO>();
		
		try
		{
			if(session.getAttribute(MrdConfig.CERTIFICATE_MOVEMENT_SELECTED_CERTIFICATE)!=null)
				map=(HashMap<String, String>)session.getAttribute(MrdConfig.CERTIFICATE_MOVEMENT_SELECTED_CERTIFICATE);
			else
				map=new HashMap<String, String>();
			
			removeUnselectedCertificate(fb, request, map);
			
			CertificateIssueDtlVO[] certificateDtlVO=(CertificateIssueDtlVO[])session.getAttribute(MrdConfig.LIST_OF_CERTIFICATE_FOR_MOVEMENT);
			
			if(fb.getChk()!=null)
			{
				for(int i=0;i<fb.getChk().length;i++)
				{
					for(int j=0;j<certificateDtlVO.length;j++)
					{
						if(fb.getChk()[i].equals(certificateDtlVO[j].getCertificateId()))
						{
							RecordDispatchDtlVO recDispatchDtlVO=new RecordDispatchDtlVO();
							recDispatchDtlVO.setDispatchId(certificateDtlVO[j].getDispatchId());
							recDispatchDtlVO.setReturnReason(fb.getReturnReason());
							recDispatchDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_NOT_RECEIVED_RETURN);
							
							lstRejectRecord.add(recDispatchDtlVO);
						}
					}
				}
			}
			
			if(map!=null)
			{
				//for(int i=0;i<map.size();i++)
				Iterator itr = ((Collection)map.values()).iterator();
				while(itr.hasNext())
				{
					String certId=(String)itr.next();
					for(int j=0;j<certificateDtlVO.length;j++)
					{
						if(certId.equals(certificateDtlVO[j].getCertificateId()))
						{
							RecordDispatchDtlVO recDispatchDtlVO=new RecordDispatchDtlVO();
							recDispatchDtlVO.setDispatchId(certificateDtlVO[j].getDispatchId());
							recDispatchDtlVO.setReturnReason(fb.getReturnReason());
							recDispatchDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_NOT_RECEIVED_RETURN);
							
							lstRejectRecord.add(recDispatchDtlVO);
						}
					}
				}
			}
			
			CertificateMovementDATA.rejectCertificateHandover(lstRejectRecord,getUserVO(request));
			objStatus.add(Status.DONE,"","Certificate Rejected");
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
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
}
