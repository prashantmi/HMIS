package opd.transaction.controller.util;

import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.PatientDetailVO;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdDocumentArchivalDATA;
import opd.transaction.controller.fb.OpdDocumentArchivalFB;
import registration.RegistrationConfig;
import vo.registration.PatientImageDtlVO;
import vo.registration.PatientVO;


public class OpdDocumentArchivalUTIL extends ControllerUTIL
{
/**
## 		Modification Log		: Taking Data from Desk Vo
##		Modify Date				: 17-01-2015
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh
*/
	public static void getDocumentArchivalEssentials(OpdDocumentArchivalFB opdDocumentArchivalFB, HttpServletRequest request)
	{
		Map essentialsMap = null;
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		DocumentUploadDtlVO[] docUploadVO = null;
		String sequenceNo = "";
		PatientDetailVO[] dailyPatientVOs = null;
		String patCrNo="";
		String episodeCode="";
		try
		{
			opdDocumentArchivalFB.setFileType("");
			opdDocumentArchivalFB.setSelectedRevoke("");
			PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(opdDocumentArchivalFB.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (opdDocumentArchivalFB.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						patientDetailVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			opdDocumentArchivalFB.setPatCrNo(patientDetailVO.getPatCrNo());
			opdDocumentArchivalFB.setEpisodeCode(patientDetailVO.getEpisodeCode());
			opdDocumentArchivalFB.setAdmissionNo(patientDetailVO.getPatAdmNo());
			essentialsMap = OpdDocumentArchivalDATA.getDocumentArchivalEssentials(opdDocumentArchivalFB.getPatCrNo(), opdDocumentArchivalFB.getEpisodeCode(), getUserVO(request));

			WebUTIL.setMapInSession(essentialsMap, request);
			docUploadVO = (DocumentUploadDtlVO[]) session.getAttribute(OpdConfig.DOCUMENT_DTL_VO_ARRAY);
			int x = docUploadVO.length - 1;
			sequenceNo = docUploadVO[x].getSerialNo();
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_DOCUMENT_SERIAL_NO, sequenceNo);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP, new LinkedList());
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_MAP, new LinkedList());
			objStatus.add(Status.TRANSINPROCESS);
			opdDocumentArchivalFB.setDocumentTitle("");
			opdDocumentArchivalFB.setDocumentCode("");
			String[] removeReasonArray = new String[docUploadVO.length];
			if ((docUploadVO != null) && (docUploadVO.length > 0))
			{
				for (int k = 0; k < docUploadVO.length; k++)
				{

					removeReasonArray[k] = "";

				}
			}
			opdDocumentArchivalFB.setRemoveReason(removeReasonArray);

		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			essentialsMap = e.getEssentialMap();

			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP, new LinkedList());
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_MAP, new LinkedList());
			WebUTIL.setAttributeInSession(request, OpdConfig.DOCUMENT_DTL_VO_ARRAY, null);
			WebUTIL.setMapInSession(essentialsMap, request);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_DOCUMENT_SERIAL_NO, "0");
			objStatus.add(Status.TRANSINPROCESS);
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);

		}
	}

	public static void addDocument(OpdDocumentArchivalFB opdDocumentArchivalFB, HttpServletRequest request)
	{
		Map essentialsMap = null;
		Status objStatus = new Status();
		LinkedList addedDocumentdetail = null;
		HttpSession session = WebUTIL.getSession(request);
		LinkedList addedDocumentFile = null;
		String fileName = "";
		byte[] fileDataAsByte = null;
		try
		{

			setSysdate(request);

			DocumentUploadDtlVO[] sessionDocUploadVO = (DocumentUploadDtlVO[]) session.getAttribute(OpdConfig.DOCUMENT_DTL_VO_ARRAY);
			if ((sessionDocUploadVO != null) && (sessionDocUploadVO.length > 0))
			{

				String[] removeReasonArray = new String[sessionDocUploadVO.length];

				for (int i = 0; i < sessionDocUploadVO.length; i++)
				{

					removeReasonArray[i] = "";

				}

				if ((opdDocumentArchivalFB.getRevoke() != null) && (opdDocumentArchivalFB.getRevoke().length > 0))
				{
					for (int i = 0; i < opdDocumentArchivalFB.getRevoke().length; i++)
					{

						removeReasonArray[Integer.parseInt(opdDocumentArchivalFB.getRevoke()[i])] = opdDocumentArchivalFB.getRemoveReason()[i];
					}

					String selectedRevoke = "";
					for (int p = 0; p < opdDocumentArchivalFB.getRevoke().length; p++)
					{
						if (opdDocumentArchivalFB.getRevoke().length == 1)
						{
							selectedRevoke = opdDocumentArchivalFB.getRevoke()[p];
						}
						else
						{
							selectedRevoke = selectedRevoke + "^" + opdDocumentArchivalFB.getRevoke()[p];
						}

					}
					opdDocumentArchivalFB.setSelectedRevoke(selectedRevoke);
				}
				opdDocumentArchivalFB.setRemoveReason(removeReasonArray);
			}

			addedDocumentdetail = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP);
			addedDocumentFile = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_MAP);
			fileDataAsByte = (byte[]) session.getAttribute(RegistrationConfig.UPLOADED_FILE_AS_ARRAY);
			// List docList=(List)session.getAttribute(OpdConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS_LIST);
			String fileNameOld = (String) session.getAttribute(RegistrationConfig.UPLOADED_FILE_NAME);
			// String fileExt=fileNameOld.substring(fileNameOld.lastIndexOf("."));
			// int size=addedDocumentdetail.size();
			fileName = fileNameOld;
			List list = new ArrayList();
			list.add(0, opdDocumentArchivalFB.getDocumentTitle());
			list.add(1, fileName);
			list.add(2, opdDocumentArchivalFB.getFileType());
			addedDocumentdetail.add(list);
			addedDocumentFile.add(fileDataAsByte);
			opdDocumentArchivalFB.setUploadType("");
			opdDocumentArchivalFB.setDocumentTitle("");
			opdDocumentArchivalFB.setDocumentCode("");
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP, addedDocumentdetail);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_MAP, addedDocumentFile);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			essentialsMap = e.getEssentialMap();
			// essentialsMap.put(OpdConfig.DOCUMENT_DTL_VO_ARRAY, new DocumentUploadDtlVO());
			WebUTIL.setMapInSession(essentialsMap, request);
			objStatus.add(Status.INPROCESS);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}

		finally
		{
			WebUTIL.setStatus(request, objStatus);

		}
	}

	public static void removeDocument(OpdDocumentArchivalFB opdDocumentArchivalFB, HttpServletRequest request)
	{
		Map essentialsMap = null;
		Status objStatus = new Status();
		LinkedList addedDocumentdetail = null;
		HttpSession session = WebUTIL.getSession(request);
		LinkedList addedDocumentFile = null;
		try
		{
			// int i=0;

			DocumentUploadDtlVO[] sessionDocUploadVO = (DocumentUploadDtlVO[]) session.getAttribute(OpdConfig.DOCUMENT_DTL_VO_ARRAY);
			if ((sessionDocUploadVO != null) && (sessionDocUploadVO.length > 0))
			{
				String[] removeReasonArray = new String[sessionDocUploadVO.length];

				for (int k = 0; k < sessionDocUploadVO.length; k++)
				{

					removeReasonArray[k] = "";

				}

				opdDocumentArchivalFB.setRemoveReason(removeReasonArray);
				if ((opdDocumentArchivalFB.getRevoke() != null) && (opdDocumentArchivalFB.getRevoke().length > 0))
				{
					for (int k = 0, j = 0; k < opdDocumentArchivalFB.getRevoke().length; k++, j++)
					{

						removeReasonArray[Integer.parseInt(opdDocumentArchivalFB.getRevoke()[k])] = opdDocumentArchivalFB.getRemoveReason()[k];
					}

					String selectedRevoke = "";
					for (int p = 0; p < opdDocumentArchivalFB.getRevoke().length; p++)
					{
						if (opdDocumentArchivalFB.getRevoke().length == 1)
						{
							selectedRevoke = opdDocumentArchivalFB.getRevoke()[p];
						}
						else
						{
							selectedRevoke = selectedRevoke + "^" + opdDocumentArchivalFB.getRevoke()[p];
						}

					}
					opdDocumentArchivalFB.setSelectedRevoke(selectedRevoke);
				}

			}

			addedDocumentdetail = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP);
			addedDocumentFile = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_MAP);
			// List docList=(List)session.getAttribute(OpdConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS_LIST);
			addedDocumentdetail.remove(Integer.parseInt(opdDocumentArchivalFB.getSerialNo()));
			addedDocumentFile.remove(Integer.parseInt(opdDocumentArchivalFB.getSerialNo()));
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP, addedDocumentdetail);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_MAP, addedDocumentFile);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			essentialsMap = e.getEssentialMap();
			// essentialsMap.put(OpdConfig.DOCUMENT_DTL_VO_ARRAY, new DocumentUploadDtlVO());
			WebUTIL.setMapInSession(essentialsMap, request);
			objStatus.add(Status.INPROCESS);
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}

		finally
		{
			WebUTIL.setStatus(request, objStatus);

		}
	}

	public static boolean saveDocument(OpdDocumentArchivalFB opdDocumentArchivalFB, HttpServletRequest request)
	{
		boolean flgSave = true;
		Status objStatus = new Status();
		PatientDetailVO[] dailyPatientVOs = null;
		LinkedList addedDocumentdetail = null;
		LinkedList addedDocumentFile = null;
		DocumentUploadDtlVO[] documentUploadDtlVOs = null;
		HttpSession session = WebUTIL.getSession(request);
		DocumentUploadDtlVO[] documentUploadRevokeDtlVO = null;
		//String fileName=null;
		try
		{
			DocumentUploadDtlVO[] sessionDocUploadVO = (DocumentUploadDtlVO[]) session.getAttribute(OpdConfig.DOCUMENT_DTL_VO_ARRAY);
			String[] revokeArray = opdDocumentArchivalFB.getRevoke();
			if ((revokeArray != null) && (revokeArray.length > 0))
			{
				documentUploadRevokeDtlVO = new DocumentUploadDtlVO[revokeArray.length];
				for (int l = 0; l < revokeArray.length; l++)
				{
					documentUploadRevokeDtlVO[l] = sessionDocUploadVO[Integer.parseInt(revokeArray[l])];
					documentUploadRevokeDtlVO[l].setRemoveReason(opdDocumentArchivalFB.getRemoveReason()[l]);
				}
			}

			addedDocumentdetail = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP);
			addedDocumentFile = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_MAP);
			
			PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(opdDocumentArchivalFB.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (opdDocumentArchivalFB.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						patientDetailVO = dailyPatientVOs[i];
						break;
					}
				}
			}

			String sysdate = (String) session.getAttribute(Config.SYSDATE);
			documentUploadDtlVOs = new DocumentUploadDtlVO[addedDocumentFile.size()];
			int j = 0;
			Iterator addedDocumentIterator = addedDocumentdetail.iterator();
			while (addedDocumentIterator.hasNext())
			{
				List list = (List) addedDocumentIterator.next();
				byte[] fileArray = (byte[]) addedDocumentFile.get(j);
				String strUploadDocName = (String) list.get(1);
				//String fileExt=strUploadDocName.substring(strUploadDocName.lastIndexOf("."));

				
				documentUploadDtlVOs[j] = new DocumentUploadDtlVO();
				HelperMethods.populate(documentUploadDtlVOs[j], patientDetailVO);
				documentUploadDtlVOs[j].setDocumentTitle((String) list.get(0));
				//documentUploadDtlVOs[j].setDocumentName(fileName);
				documentUploadDtlVOs[j].setDocumentCode((String)list.get(1));
				//documentUploadDtlVOs[j].setSerialNo((String)list.get(3));
				documentUploadDtlVOs[j].setDocumentName((String) list.get(1));
				//documentUploadDtlVOs[j].setFileName(fileExt);//fileName);
				documentUploadDtlVOs[j].setFileType((String) list.get(2));
				documentUploadDtlVOs[j].setDocFile(fileArray);
				documentUploadDtlVOs[j].setEntryDate(sysdate);
				documentUploadDtlVOs[j].setAdmissionNo(opdDocumentArchivalFB.getAdmissionNo());
				/*
				 * boolean flag =HisFileControlUtil.isWindowsOS(); if(flag) {
				 * documentUploadDtlVOs[j].setDocumentDirectoryPath(Config.OPD_DOCUMENT_FILE_STORAGE_PATH_WINDOWS);
				 * documentUploadDtlVOs[j].setPathForWindows(Config.OPD_DOCUMENT_FILE_STORAGE_PATH_WINDOWS); } else {
				 * documentUploadDtlVOs[j].setDocumentDirectoryPath(Config.OPD_DOCUMENT_FILE_STORAGE_PATH_LINUX); }
				 */
				//documentUploadDtlVOs[j].setPathForWindows(Config.OPD_DOCUMENT_FILE_STORAGE_PATH_WINDOWS);
				//documentUploadDtlVOs[j].setPathPathLinux(Config.OPD_DOCUMENT_FILE_STORAGE_PATH_LINUX);
				// documentUploadDtlVOs[j].setDocumentDirectoryPath(Config.OPD_DOCUMENT_FILE_STORAGE_PATH);
				documentUploadDtlVOs[j].setIsValid(Config.IS_VALID_ACTIVE);
				j++;
			}

			OpdDocumentArchivalDATA.saveDocument(documentUploadDtlVOs, documentUploadRevokeDtlVO, getUserVO(request));
			objStatus.add(Status.TRANSINPROCESS, "Document Successfully Uploaded", "");
		}
		catch (HisRecordNotFoundException e)
		{
			flgSave = false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flgSave = false;
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			flgSave = false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			flgSave = false;
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			flgSave = false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flgSave;
	}

	public static byte[] viewDocument(OpdDocumentArchivalFB opdDocumentArchivalFB, HttpServletRequest request, HttpServletResponse response)
	{
		Status objStatus = new Status();
		// String dirPath=request.getParameter(OpdConfig.DOCUMENT_STORAGE_PATH);
		// String fileName=request.getParameter(OpdConfig.FILE_NAME);
		// String fileNameWithPath=dirPath+"\\"+fileName;
		byte[] bytes = null;
		try
		{
			bytes = HelperMethods.getByteArrayOfImage("C:\\OpdDocumentDIR\\1000800000037$16-01-2008-17-37$1.pdf");
			/*
			 * OutputStream os =response.getOutputStream(); BufferedOutputStream bos = new BufferedOutputStream(os);
			 */
			/*
			 * response.setContentType("application/pdf"); if(bytes!=null) { response.setHeader("Pragma","no-cache");
			 * bos.write(bytes, 0, bytes.length); response.getOutputStream().flush(); bos.close(); }
			 *  } catch (IOException e) { e.printStackTrace(); objStatus.add(Status.ERROR_DA,"File Not Found",""); }
			 */
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);

		}
		return bytes;
	}

	public static boolean getViewDoc(OpdDocumentArchivalFB fb,	HttpServletRequest request, HttpServletResponse response_p) 
	{
		InputStream inputStream = null;
		BufferedOutputStream bos = null;
		PatientVO patientVO = new PatientVO();
		hisglobal.vo.PatientImageDtlVO patientImageDtlVO =new hisglobal.vo.PatientImageDtlVO();
		try
		{
			String docId = fb.getDocumentCode();
			String[] cr = docId.split("\\_");
			String crNo = cr[0];
			patientImageDtlVO.setFileNo(docId);
			patientImageDtlVO.setPatCrNo(crNo);
			//Added by Vasu on 10.Aug.2018 to get Image data from postgreSql
			byte[] getDoc = OpdDocumentArchivalDATA.fetchImageFromPostgres(patientImageDtlVO);
			//byte[] getDoc= MongoXmlHandler.getInstanceWithURL(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD, HISConfig.HIS_MONGODB_PAT_DOC).latestFetchFile(fb.getDocumentCode());
			//byte[] getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD).latestFetchFile(fb.getDocumentCode());
			OutputStream os = response_p.getOutputStream();
			bos = new BufferedOutputStream(os);
			inputStream = new ByteArrayInputStream(getDoc);
			int c;
			while ((c = inputStream.read()) != -1)
			{
				os.write(c);
			}    
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String msg="<B>This file does not exist:: Please Contact Administrator</B>";
			byte[] bytes=msg.getBytes();
			response_p.setHeader("Pragma", "no-cache");
			try
			{
				bos.write(bytes, 0, bytes.length);
			}catch(Exception ee)
			{
			}
			return false;
			
		}
		finally
		{
			try
			{
				if(inputStream!=null) inputStream.close();
				response_p.getOutputStream().flush();
				if(bos!=null)	bos.close();
			}
			catch(Exception e)
			{
			}
		}
		return true;
	}
	
	//Added by Vasu on 8.Aug.2018 to upload patient document
	public static boolean savePatientDocument(OpdDocumentArchivalFB opdDocumentArchivalFB, HttpServletRequest request)
	{
		boolean flgSave = true;
		Status objStatus = new Status();
		PatientDetailVO[] dailyPatientVOs = null;
		LinkedList addedDocumentdetail = null;
		LinkedList addedDocumentFile = null;
		DocumentUploadDtlVO[] documentUploadDtlVOs = null;
		HttpSession session = WebUTIL.getSession(request);
		DocumentUploadDtlVO[] documentUploadRevokeDtlVO = null;
		PatientVO patientVO = new PatientVO();
		//String fileName=null;
		try
		{
			DocumentUploadDtlVO[] sessionDocUploadVO = (DocumentUploadDtlVO[]) session.getAttribute(OpdConfig.DOCUMENT_DTL_VO_ARRAY);
			String[] revokeArray = opdDocumentArchivalFB.getRevoke();
			if ((revokeArray != null) && (revokeArray.length > 0))
			{
				documentUploadRevokeDtlVO = new DocumentUploadDtlVO[revokeArray.length];
				for (int l = 0; l < revokeArray.length; l++)
				{
					documentUploadRevokeDtlVO[l] = sessionDocUploadVO[Integer.parseInt(revokeArray[l])];
					documentUploadRevokeDtlVO[l].setRemoveReason(opdDocumentArchivalFB.getRemoveReason()[l]);
				}
			}

			addedDocumentdetail = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP);
			addedDocumentFile = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_MAP);
			
			PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(opdDocumentArchivalFB.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (opdDocumentArchivalFB.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						patientDetailVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			//PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			//To Upload Image
						patientVO.setPatCrNo(patientDetailVO.getPatCrNo());
						patientVO.setEpisodeCode(patientDetailVO.getEpisodeCode());
						
						if(WebUTIL.getSession(request).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT)!=null){
							byte[] filContent=IOUtils.toByteArray((FileInputStream)WebUTIL.getSession(request).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT));
							patientVO.setImageFile(filContent);
						    patientVO.setImageFileName((String)WebUTIL.getSession(request).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_NAME));
						    patientVO.setImageFileNameNew((String)WebUTIL.getSession(request).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_NAME_NEW)); 
						    patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_TRUE);
						}
			String sysdate = (String) session.getAttribute(Config.SYSDATE);
			documentUploadDtlVOs = new DocumentUploadDtlVO[addedDocumentFile.size()];
			int j = 0;
			Iterator addedDocumentIterator = addedDocumentdetail.iterator();
			while (addedDocumentIterator.hasNext())
			{
				List list = (List) addedDocumentIterator.next();
				byte[] fileArray = (byte[]) addedDocumentFile.get(j);
				String strUploadDocName = (String) list.get(1);
				//String fileExt=strUploadDocName.substring(strUploadDocName.lastIndexOf("."));
                //int fileExt1 =  patientVO.getImageFileName().lastIndexOf(".");
                //String fileExt = Integer.toString(fileExt1);
                String[] fileExt1 = patientVO.getImageFileNameNew().split("\\.");
                String fileExt = fileExt1[1];
                
				documentUploadDtlVOs[j] = new DocumentUploadDtlVO();
				HelperMethods.populate(documentUploadDtlVOs[j], patientDetailVO);
				documentUploadDtlVOs[j].setDocumentTitle((String) list.get(0));
				//documentUploadDtlVOs[j].setDocumentName(fileName);
				documentUploadDtlVOs[j].setDocumentCode((String)list.get(1));
				//documentUploadDtlVOs[j].setSerialNo((String)list.get(3));
				documentUploadDtlVOs[j].setDocumentName((String) list.get(1));
				documentUploadDtlVOs[j].setFileName(fileExt);//fileName);
				documentUploadDtlVOs[j].setFileType((String) list.get(2));
				documentUploadDtlVOs[j].setDocFile(fileArray);
				documentUploadDtlVOs[j].setEntryDate(sysdate);
				documentUploadDtlVOs[j].setAdmissionNo(opdDocumentArchivalFB.getAdmissionNo());
				documentUploadDtlVOs[j].setIsValid(Config.IS_VALID_ACTIVE);
				j++;
			}
			
			//OpdDocumentArchivalDATA.saveDocument(documentUploadDtlVOs, documentUploadRevokeDtlVO, getUserVO(request));
			OpdDocumentArchivalDATA.savePatientDocument(documentUploadDtlVOs, documentUploadRevokeDtlVO,patientVO,getUserVO(request));
			objStatus.add(Status.TRANSINPROCESS, "Document Successfully Uploaded", "");
		}
		catch (HisRecordNotFoundException e)
		{
			flgSave = false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flgSave = false;
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			flgSave = false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			flgSave = false;
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			flgSave = false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flgSave;
	}
}
