package mrd.transaction.controller.utl;


import inpatient.InpatientConfig;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import registration.RegistrationConfig;
import mrd.MrdConfig;
import mrd.transaction.controller.data.CertificateEntryBFormDATA;

import mrd.transaction.controller.fb.CertificateBEntryFormFB;

import mrd.vo.CertificateBEntryFormReqVO;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.PatientDetailVO;

	public class CertificateBEntryFormUTL extends ControllerUTIL {
		
		
		public static boolean getCertificateBEntryHandoverDtl(CertificateBEntryFormFB fb,HttpServletRequest request)
		{
			Status objStatus = new Status();
			boolean flag = true;
			String billNoQty="";
			HttpSession session = request.getSession();
			
			try
			{
				CertificateBEntryFormReqVO[] certBEntryList= (CertificateBEntryFormReqVO[])session.getAttribute(MrdConfig.CERTIFICATE_B_ENTRY_FORM_REQUEST);
				CertificateBEntryFormReqVO certBEntryVO= null;
				for(CertificateBEntryFormReqVO vo : certBEntryList)
				{
					if(vo.getCertificateId().equals(fb.getSelectRecord()))
					{
						certBEntryVO = vo;
						break;
					}
				}
						
				HelperMethods.populate(fb, certBEntryVO);
				
				
				Map map=CertificateEntryBFormDATA.getCertificateHandoverDtl(certBEntryVO,getUserVO(request));
				certBEntryVO.setRecordType(MrdConfig.RECORD_TYPE_CERTIFICATE_B);
				billNoQty=CertificateEntryBFormDATA.getBillNoDtl(certBEntryVO,getUserVO(request));
				fb.setBillNo(billNoQty.replace("^", "#").split("#")[0]);
				fb.setQuantity(billNoQty.replace("^", "#").split("#")[1]);
				System.out.println("billNo:::::"+fb.getBillNo()+"qty:::"+fb.getQuantity());
				WebUTIL.setMapInSession(map, request);
								
				//objStatus.add(Status.NEW);
				
				
				
			}
			catch (HisRecordNotFoundException e)
			{
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
			
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
			return flag;
		}
		
		public static boolean saveCertificateBIssueDtl(CertificateBEntryFormFB fb,HttpServletRequest request)
		{
			Status objStatus = new Status();
			boolean flag = true;
			HttpSession session = request.getSession();
			DocumentUploadDtlVO[] documentUploadDtlVOs = null;
			try
			{
				mrd.vo.CertificateBEntryFormReqVO certBEntryVO=new mrd.vo.CertificateBEntryFormReqVO(); 
				HelperMethods.populate(certBEntryVO, fb);
				setSysdate(request);
				
				
				byte[] fileArray = (byte[]) session.getAttribute(RegistrationConfig.UPLOADED_FILE_AS_ARRAY);
				String strUploadDocName = (String)	session.getAttribute(RegistrationConfig.UPLOADED_FILE_NAME);

				//addedDocumentdetail = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP);
				if(fileArray!=null)
				{
					String sysdate = (String) session.getAttribute(Config.SYSDATE);
					documentUploadDtlVOs = new DocumentUploadDtlVO[1];
					String fileExt=strUploadDocName.substring(strUploadDocName.lastIndexOf("."));
					documentUploadDtlVOs[0] = new DocumentUploadDtlVO();
					HelperMethods.populate(documentUploadDtlVOs[0], certBEntryVO);
					documentUploadDtlVOs[0].setDocumentTitle("Certificate B dated "+ sysdate);
					documentUploadDtlVOs[0].setDocumentCode(strUploadDocName);
					documentUploadDtlVOs[0].setDocumentName(strUploadDocName);
					documentUploadDtlVOs[0].setFileType(MrdConfig.RECORD_TYPE_CERTIFICATE_B);
					documentUploadDtlVOs[0].setDocFile(fileArray);
					documentUploadDtlVOs[0].setEntryDate(sysdate);
					documentUploadDtlVOs[0].setIsValid(Config.IS_VALID_ACTIVE);
					
					CertificateEntryBFormDATA.saveDocument(documentUploadDtlVOs, null, getUserVO(request));
				}

				
				
				CertificateEntryBFormDATA.saveCertificateHAndover(certBEntryVO,getUserVO(request));
				//objStatus.add(Status.NEW);\
				objStatus.add(Status.DONE,"","Record Added Successfully");
			}
			catch (HisRecordNotFoundException e)
			{
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
			
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
			return flag;
		}
		
		
		
		public static boolean getCertificateBEntryList(CertificateBEntryFormFB fb,HttpServletRequest request)
		{
			Status objStatus = new Status();
			boolean flag = true;
			
			try
			{
				setSysdate(request);
				
				Map map=CertificateEntryBFormDATA.getCertificateBEntryDtl(getUserVO(request));
				WebUTIL.setMapInSession(map, request);
				objStatus.add(Status.NEW);
			}
			catch (HisRecordNotFoundException e)
			{
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL, e.getMessage(),"");
			
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
			return flag;
		}
		
		
		
		
		public static boolean getEssentialData(CertificateBEntryFormFB fb,HttpServletRequest request)
		{
			Status objStatus = new Status();
			boolean flag = true;
			
			try
			{
				setSysdate(request);
				Map map=CertificateEntryBFormDATA.getEssentialData(getUserVO(request));
				WebUTIL.setMapInSession(map, request);
				objStatus.add(Status.NEW);
			}
			catch (HisRecordNotFoundException e)
			{
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
			
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
			return flag;
		}
		
		public static boolean saveReqDtl(CertificateBEntryFormFB fb,HttpServletRequest request)
		{
			Status objStatus = new Status();
			boolean flag = true;
			HttpSession session = request.getSession();
			
			try
			{
				mrd.vo.CertificateBEntryFormReqVO certBEntryReqVO =new mrd.vo.CertificateBEntryFormReqVO();
				HelperMethods.populate(certBEntryReqVO, fb);
				PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
				if(ptaientDetailVO!=null)
				{
					HelperMethods.populatetToNullOrEmpty(certBEntryReqVO, ptaientDetailVO);
				}
				setSysdate(request);
				CertificateEntryBFormDATA.saveReqDtl(certBEntryReqVO, getUserVO(request));
			    //saveReqDtl(dupRecPrintReqVO,getUserVO(request));
				//objStatus.add(Status.NEW);\
				objStatus.add(Status.DONE,"","Record Added Successfully");
			}
			catch (HisRecordNotFoundException e)
			{
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
			
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
			return flag;
		}

		
	}
