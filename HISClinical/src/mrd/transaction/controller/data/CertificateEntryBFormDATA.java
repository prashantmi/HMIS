package mrd.transaction.controller.data;
	

	import java.util.Map;

import registration.bo.PatientBO;
import mrd.transaction.bo.MrdBO;
import mrd.transaction.bo.MrdEssentialBO;
import mrd.vo.CertificateBEntryFormReqVO;
import mrd.vo.DupRecPrintReqVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.UserVO;

	public class CertificateEntryBFormDATA extends ControllerDATA {

		public static Map getCertificateBEntryDtl(UserVO userVO)
		{
			MrdEssentialBO serviceBO=new MrdEssentialBO();	
			return serviceBO.getCertificatBEntryDtl(userVO);
		}
	
		
		public static Map getEssentialData(UserVO userVO)
		{
			CertificateBEntryFormReqVO certBBntryReqVO=new CertificateBEntryFormReqVO();
			MrdEssentialBO serviceBO=new MrdEssentialBO();	
			return serviceBO.getEssentialcertificate(certBBntryReqVO,userVO);
		}
		
		public static void saveReqDtl(mrd.vo.CertificateBEntryFormReqVO certificateBentryVO, UserVO UserVO)
		{
			MrdBO serviceBO=new MrdBO();	
			serviceBO.saveCertBEntryReqDtl(certificateBentryVO,UserVO);
			
		}
		
		public static Map getCertificateHandoverDtl(mrd.vo.CertificateBEntryFormReqVO certBEntryReqVO, UserVO UserVO)
		{
			MrdEssentialBO serviceBO=new MrdEssentialBO();	
			return (serviceBO.getcertifificateHandoverDtl(certBEntryReqVO,UserVO));
		}
		
		public static String getBillNoDtl(CertificateBEntryFormReqVO certBEntryReqVO, UserVO UserVO)
		{
			MrdEssentialBO serviceBO=new MrdEssentialBO();
			PatMedicalDtlVO medicalVO = new PatMedicalDtlVO();
			HelperMethods.populate(medicalVO, certBEntryReqVO);
			return (serviceBO.getBillNoQtyDtl(medicalVO,UserVO));
		}
		public static void saveCertificateHAndover(CertificateBEntryFormReqVO certBEntryReqVO , UserVO UserVO)
		{
			MrdBO serviceBO=new MrdBO();	
			serviceBO.saveCertificateBHandoverDtl(certBEntryReqVO,UserVO);
		}
		
		public static void 	saveDocument(DocumentUploadDtlVO[] docUploadVos,DocumentUploadDtlVO[] documentUploadRevokeDtlVO,UserVO userVO)
		{
			PatientBO serviceBO = new PatientBO();
			serviceBO.saveDocumentDetail(docUploadVos,null,userVO);
		}

		
	}
