package mrd.transaction.controller.utl;


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
import mrd.MrdConfig;
import mrd.vo.MRDDocumentUploadVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.InpatientDetailDATA;

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
import mrd.transaction.controller.data.MRDDocumentUploadDATA;
import mrd.transaction.controller.data.MrdRecordIssueDATA;
import mrd.transaction.controller.fb.MRDDocumentUploadFB;
import registration.RegistrationConfig;
import registration.controller.fb.CRNoFB;
import vo.registration.PatientVO;


public class MRDDocumentUploadUTIL extends ControllerUTIL
{
	
	public static void getDocumentArchivalEssentials(MRDDocumentUploadFB MRDDocumentUploadFB, HttpServletRequest request)
	{
		Map essentialsMap = null;
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		MRDDocumentUploadVO[] docUploadVO = null; 
		String sequenceNo = "";
		PatientDetailVO[] dailyPatientVOs = null;
		String patCrNo="";
		String episodeCode="";
		try
		{
			MRDDocumentUploadFB.setFileType("");
			MRDDocumentUploadFB.setSelectedRevoke("");
			/*PatientDetailVO patientDetailVO = (PatientDetailVO) request.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(MRDDocumentUploadFB.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (MRDDocumentUploadFB.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						patientDetailVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			MRDDocumentUploadFB.setPatCrNo(patientDetailVO.getPatCrNo());
			MRDDocumentUploadFB.setEpisodeCode(patientDetailVO.getEpisodeCode());
			MRDDocumentUploadFB.setAdmissionNo(patientDetailVO.getPatAdmNo());*/
			List mrdRecordRequestVOList = MRDDocumentUploadDATA.getDocumentArchivalEssentials(MRDDocumentUploadFB.getPatCrNo(), MRDDocumentUploadFB.getEpisodeCode(), getUserVO(request));
			
			
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_RECORD_VO_LIST ,mrdRecordRequestVOList);
			
			if(mrdRecordRequestVOList!=null)
			{
			
			MRDDocumentUploadVO docUploadVO1 =(MRDDocumentUploadVO) mrdRecordRequestVOList.get(0);
			MRDDocumentUploadFB.setPatName(docUploadVO1.getPatName().toString());
			
			
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP, new LinkedList());
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_MAP, new LinkedList());
			WebUTIL.setAttributeInSession(request, OpdConfig.DOCUMENT_DTL_VO_ARRAY, null);
			WebUTIL.setMapInSession(essentialsMap, request); //uncommented
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_DOCUMENT_SERIAL_NO, "0");
			objStatus.add(Status.TRANSINPROCESS); //uncommented
			MRDDocumentUploadFB.setDocumentTitle("");
			MRDDocumentUploadFB.setDocumentCode("");
			objStatus.add(Status.LIST);
			
			
			/*WebUTIL.setMapInSession(essentialsMap, request);
			docUploadVO = (MRDDocumentUploadVO[]) session.getAttribute(OpdConfig.DOCUMENT_DTL_VO_ARRAY);
			int x = docUploadVO.length - 1;
			sequenceNo = docUploadVO[x].getSerialNo();
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_DOCUMENT_SERIAL_NO, sequenceNo);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP, new LinkedList());
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_MAP, new LinkedList());
			objStatus.add(Status.TRANSINPROCESS);
			MRDDocumentUploadFB.setDocumentTitle("");
			MRDDocumentUploadFB.setDocumentCode("");
			String[] removeReasonArray = new String[docUploadVO.length];
			if ((docUploadVO != null) && (docUploadVO.length > 0))
			{
				for (int k = 0; k < docUploadVO.length; k++)
				{

					removeReasonArray[k] = "";

				}
			}
			MRDDocumentUploadFB.setRemoveReason(removeReasonArray);

		}*/}
			else
			{
				objStatus.add(Status.TRANSINPROCESS,"No MRD Record Found","");
				
			
			}
			
				
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			essentialsMap = e.getEssentialMap();

			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP, new LinkedList());
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_MAP, new LinkedList());
			WebUTIL.setAttributeInSession(request, OpdConfig.DOCUMENT_DTL_VO_ARRAY, null);
			WebUTIL.setMapInSession(essentialsMap, request); //uncommented by Dheeraj
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

	public static void addDocument(MRDDocumentUploadFB MRDDocumentUploadFB, HttpServletRequest request)
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

			MRDDocumentUploadVO[] sessionDocUploadVO = (MRDDocumentUploadVO[]) session.getAttribute(OpdConfig.DOCUMENT_DTL_VO_ARRAY);
			/*if ((sessionDocUploadVO != null) && (sessionDocUploadVO.length > 0))
			{

				String[] removeReasonArray = new String[sessionDocUploadVO.length];

				for (int i = 0; i < sessionDocUploadVO.length; i++)
				{

					removeReasonArray[i] = "";

				}

				if ((MRDDocumentUploadFB.getRevoke() != null) && (MRDDocumentUploadFB.getRevoke().length > 0))
				{
					for (int i = 0; i < MRDDocumentUploadFB.getRevoke().length; i++)
					{

						removeReasonArray[Integer.parseInt(MRDDocumentUploadFB.getRevoke()[i])] = MRDDocumentUploadFB.getRemoveReason()[i];
					}

					String selectedRevoke = "";
					for (int p = 0; p < MRDDocumentUploadFB.getRevoke().length; p++)
					{
						if (MRDDocumentUploadFB.getRevoke().length == 1)
						{
							selectedRevoke = MRDDocumentUploadFB.getRevoke()[p];
						}
						else
						{
							selectedRevoke = selectedRevoke + "^" + MRDDocumentUploadFB.getRevoke()[p];
						}

					}
					MRDDocumentUploadFB.setSelectedRevoke(selectedRevoke);
				}
				MRDDocumentUploadFB.setRemoveReason(removeReasonArray);
			}
*/
			addedDocumentdetail = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP);
			addedDocumentFile = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_MAP);
			fileDataAsByte = (byte[]) session.getAttribute(RegistrationConfig.UPLOADED_FILE_AS_ARRAY);
			// List docList=(List)session.getAttribute(OpdConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS_LIST);
			String fileNameOld = (String) session.getAttribute(RegistrationConfig.UPLOADED_FILE_NAME);
			// String fileExt=fileNameOld.substring(fileNameOld.lastIndexOf("."));
			// int size=addedDocumentdetail.size();
			fileName = fileNameOld;
			List list = new ArrayList();
			list.add(0, MRDDocumentUploadFB.getSelectedRecordTypeName() +"_"+MRDDocumentUploadFB.getSelectedAdmissionNo());    //filename
			list.add(1, fileName);   //old filename
			list.add(2, MRDDocumentUploadFB.getSelectedRecordType());      //doc type
			list.add(3, MRDDocumentUploadFB.getSelectedRecordTypeName());  //doctitle
			list.add(4, MRDDocumentUploadFB.getSelectedRecordId());       //doc Id
			addedDocumentdetail.add(list);
			
			
			
			addedDocumentFile.add(fileDataAsByte);
			MRDDocumentUploadFB.setUploadType("");
			MRDDocumentUploadFB.setDocumentTitle("");
			MRDDocumentUploadFB.setDocumentCode("");
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP, addedDocumentdetail);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_MAP, addedDocumentFile);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			essentialsMap = e.getEssentialMap();
			// essentialsMap.put(OpdConfig.DOCUMENT_DTL_VO_ARRAY, new MRDDocumentUploadVO());
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

	public static void removeDocument(MRDDocumentUploadFB MRDDocumentUploadFB, HttpServletRequest request)
	{
		Map essentialsMap = null;
		Status objStatus = new Status();
		LinkedList addedDocumentdetail = null;
		HttpSession session = WebUTIL.getSession(request);
		LinkedList addedDocumentFile = null;
		try
		{
			// int i=0;

			MRDDocumentUploadVO[] sessionDocUploadVO = (MRDDocumentUploadVO[]) session.getAttribute(OpdConfig.DOCUMENT_DTL_VO_ARRAY);
			if ((sessionDocUploadVO != null) && (sessionDocUploadVO.length > 0))
			{
				String[] removeReasonArray = new String[sessionDocUploadVO.length];

				for (int k = 0; k < sessionDocUploadVO.length; k++)
				{

					removeReasonArray[k] = "";

				}

				MRDDocumentUploadFB.setRemoveReason(removeReasonArray);
				if ((MRDDocumentUploadFB.getRevoke() != null) && (MRDDocumentUploadFB.getRevoke().length > 0))
				{
					for (int k = 0, j = 0; k < MRDDocumentUploadFB.getRevoke().length; k++, j++)
					{

						removeReasonArray[Integer.parseInt(MRDDocumentUploadFB.getRevoke()[k])] = MRDDocumentUploadFB.getRemoveReason()[k];
					}

					String selectedRevoke = "";
					for (int p = 0; p < MRDDocumentUploadFB.getRevoke().length; p++)
					{
						if (MRDDocumentUploadFB.getRevoke().length == 1)
						{
							selectedRevoke = MRDDocumentUploadFB.getRevoke()[p];
						}
						else
						{
							selectedRevoke = selectedRevoke + "^" + MRDDocumentUploadFB.getRevoke()[p];
						}

					}
					MRDDocumentUploadFB.setSelectedRevoke(selectedRevoke);
				}

			}

			addedDocumentdetail = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP);
			addedDocumentFile = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_MAP);
			// List docList=(List)session.getAttribute(OpdConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS_LIST);
			addedDocumentdetail.remove(Integer.parseInt(MRDDocumentUploadFB.getSerialNo()));
			addedDocumentFile.remove(Integer.parseInt(MRDDocumentUploadFB.getSerialNo()));
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP, addedDocumentdetail);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_MAP, addedDocumentFile);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			essentialsMap = e.getEssentialMap();
			// essentialsMap.put(OpdConfig.DOCUMENT_DTL_VO_ARRAY, new MRDDocumentUploadVO());
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

	public static boolean saveDocument(MRDDocumentUploadFB MRDDocumentUploadFB, HttpServletRequest request)
	{
		boolean flgSave = true;
		Status objStatus = new Status();
		PatientDetailVO[] dailyPatientVOs = null;
		LinkedList addedDocumentdetail = null;
		LinkedList addedDocumentFile = null;
		MRDDocumentUploadVO[] MRDDocumentUploadVOs = null;
		HttpSession session = WebUTIL.getSession(request);
		MRDDocumentUploadVO[] documentUploadRevokeDtlVO = null;
		
		//String fileName=null;
		try
		{
			/*MRDDocumentUploadVO[] sessionDocUploadVO = (MRDDocumentUploadVO[]) session.getAttribute(OpdConfig.DOCUMENT_DTL_VO_ARRAY);
			*/
			
			String[] revokeArray = MRDDocumentUploadFB.getRevoke();
			if ((revokeArray != null) && (revokeArray.length > 0))
			{
				documentUploadRevokeDtlVO = new MRDDocumentUploadVO[revokeArray.length];
				for (int l = 0; l < revokeArray.length; l++)
				{
				//	documentUploadRevokeDtlVO[l] = sessionDocUploadVO[Integer.parseInt(revokeArray[l])];
					documentUploadRevokeDtlVO[l].setRemoveReason(MRDDocumentUploadFB.getRemoveReason()[l]);
				}
			}

			addedDocumentdetail = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP);
			addedDocumentFile = (LinkedList) session.getAttribute(OpdConfig.OPD_ADD_DOCUMENT_MAP);
			
/*			
			PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(MRDDocumentUploadFB.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (MRDDocumentUploadFB.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						patientDetailVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			
			
			//To Upload Image Added by Dheeraj on 25-Sept-2018
			patientVO.setPatCrNo(patientDetailVO.getPatCrNo());
			patientVO.setEpisodeCode(patientDetailVO.getEpisodeCode());
*/			
		/*	if(WebUTIL.getSession(request).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT)!=null){
				byte[] filContent=IOUtils.toByteArray((FileInputStream)WebUTIL.getSession(request).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT));
				patientVO.setImageFile(filContent);
			    patientVO.setImageFileName((String)WebUTIL.getSession(request).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_NAME));
			    patientVO.setImageFileNameNew((String)WebUTIL.getSession(request).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_NAME_NEW)); 
			    patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_TRUE);
			}
		*/		

			String sysdate = (String) session.getAttribute(Config.SYSDATE);
			MRDDocumentUploadVOs = new MRDDocumentUploadVO[addedDocumentFile.size()];
			int j = 0;
			Iterator addedDocumentIterator = addedDocumentdetail.iterator();
			while (addedDocumentIterator.hasNext())
			{
				List list = (List) addedDocumentIterator.next();
				byte[] fileArray = (byte[]) addedDocumentFile.get(j);
				String strUploadDocName = (String) list.get(1);
				String fileExt=strUploadDocName.substring(strUploadDocName.lastIndexOf("."));

				
				 MRDDocumentUploadVOs[j] = new MRDDocumentUploadVO();
				//HelperMethods.populate(MRDDocumentUploadVOs[j], patientDetailVO);
				MRDDocumentUploadVOs[j].setPatCrNo(MRDDocumentUploadFB.getPatCrNo());
				MRDDocumentUploadVOs[j].setDocumentTitle((String) list.get(0));
				MRDDocumentUploadVOs[j].setDocumentName((String) list.get(1));
				MRDDocumentUploadVOs[j].setFileName((String) list.get(3));//fileName);
				MRDDocumentUploadVOs[j].setFileType((String) list.get(2));
				MRDDocumentUploadVOs[j].setDocFile(fileArray);
				MRDDocumentUploadVOs[j].setEntryDate(sysdate);
				MRDDocumentUploadVOs[j].setAdmissionNo(MRDDocumentUploadFB.getSelectedAdmissionNo());  
				MRDDocumentUploadVOs[j].setDocumentCode(MRDDocumentUploadFB.getSelectedRecordId());
				MRDDocumentUploadVOs[j].setRecordId(MRDDocumentUploadFB.getSelectedRecordId());
				
				/*
				 * boolean flag =HisFileControlUtil.isWindowsOS(); if(flag) {
				 * MRDDocumentUploadVOs[j].setDocumentDirectoryPath(Config.OPD_DOCUMENT_FILE_STORAGE_PATH_WINDOWS);
				 * MRDDocumentUploadVOs[j].setPathForWindows(Config.OPD_DOCUMENT_FILE_STORAGE_PATH_WINDOWS); } else {
				 * MRDDocumentUploadVOs[j].setDocumentDirectoryPath(Config.OPD_DOCUMENT_FILE_STORAGE_PATH_LINUX); }
				 */
				//MRDDocumentUploadVOs[j].setPathForWindows(Config.OPD_DOCUMENT_FILE_STORAGE_PATH_WINDOWS);
				//MRDDocumentUploadVOs[j].setPathPathLinux(Config.OPD_DOCUMENT_FILE_STORAGE_PATH_LINUX);
				// MRDDocumentUploadVOs[j].setDocumentDirectoryPath(Config.OPD_DOCUMENT_FILE_STORAGE_PATH);
				MRDDocumentUploadVOs[j].setIsValid(Config.IS_VALID_ACTIVE);
				j++;
			}

			MRDDocumentUploadDATA.saveDocument(MRDDocumentUploadVOs, documentUploadRevokeDtlVO, getUserVO(request));  
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

	public static byte[] viewDocument(MRDDocumentUploadFB MRDDocumentUploadFB, HttpServletRequest request, HttpServletResponse response)
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

	public static boolean getViewDoc(MRDDocumentUploadFB fb,	HttpServletRequest request, HttpServletResponse response_p) 
	{
		InputStream inputStream = null;
		BufferedOutputStream bos = null;
		MRDDocumentUploadVO mrdDocumentUploadVO = new MRDDocumentUploadVO();
		
		/*PatientVO patientVO = new PatientVO();
		hisglobal.vo.PatientImageDtlVO patientImageDtlVO =new hisglobal.vo.PatientImageDtlVO();*/
		try
		{
			//Commented By Dheeraj on 27-Sept-2018
			/*byte[] getDoc= MongoXmlHandler.getInstanceWithURL(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD, HISConfig.HIS_MONGODB_PAT_DOC).latestFetchFile(fb.getDocumentCode());
			OutputStream os = response_p.getOutputStream();
			bos = new BufferedOutputStream(os);
			inputStream = new ByteArrayInputStream(getDoc);
			int c;
			while ((c = inputStream.read()) != -1)
			{
				os.write(c);
			}    */
			
			
			//Added by Dheeraj on 27-Sept-2018 to get file from Postgres
			
			String patCrNo = fb.getPatCrNo();
			//String[] cr = docId.split("\\_");
			//String crNo = cr[0];
			//mrdDocumentUploadVO.setDocSlNo(docId);
			mrdDocumentUploadVO.setPatCrNo(patCrNo);
			//Added by Vasu on 10.Aug.2018 to get Image data from postgreSql
			byte[] getDoc = MRDDocumentUploadDATA.fetchImageFromPostgres(mrdDocumentUploadVO);
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
}
