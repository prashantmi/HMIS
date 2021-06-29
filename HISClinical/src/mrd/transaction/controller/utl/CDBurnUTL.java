package mrd.transaction.controller.utl;

import hisglobal.backutil.HelperMethods;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HTMLParsingUTIL;
import hisglobal.utility.HTMLToPDFUTIL;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.HisHTMLParserUtil;
import hisglobal.utility.burnDisc.BurnDisc;
import hisglobal.vo.PatientProfileCDBurnDtlVO;
import hisglobal.vo.PatientProfileDetailVO;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;
import mrd.transaction.controller.data.CDBurnDATA;
import mrd.transaction.controller.fb.CDBurnFB;
import registration.controller.util.PatDetailUTIL;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

public class CDBurnUTL extends ControllerUTIL {
	
	public static boolean getRecordToBurn(CDBurnFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		
		try
		{
			PatDetailUTIL.getPatientDtlByCrno(fb, request);
			String patCrNo=fb.getPatCrNo();
			List patientProfileVOList=CDBurnDATA.getPatientProfileByCrNo(patCrNo,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.PATIENT_PROFILE_VO_LIST ,patientProfileVOList);
			objStatus.add(Status.RECORDFOUND);
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
	
	
	public static void setRecordToBurn(CDBurnFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		List<PatientProfileDetailVO> patientProfileVOList=null;
		PatientProfileDetailVO []patientProfileVOArray=null;
		String fileName="";
		float fileSize=0;
		try
		{
			patientProfileVOList=(List)request.getSession().getAttribute(MrdConfig.PATIENT_PROFILE_VO_LIST);
			String [] selectedRecord=fb.getSelectedRecord();
			int k=0;
			if(selectedRecord.length>0){
				patientProfileVOArray=new PatientProfileDetailVO[selectedRecord.length];
				for(int i=0;i<selectedRecord.length;i++){
					patientProfileVOArray[k++]=patientProfileVOList.get(Integer.parseInt(selectedRecord[i]));
				}
			}
			String filePathWin="";
			String filePathLinux="";
			for(int i=0;i<patientProfileVOArray.length;i++){
				filePathWin=Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS;
				filePathLinux=Config.PATIENT_PROFILE_STORAGE_PATH_LINUX;
				HisFileControlUtil fileUtil=new HisFileControlUtil(patientProfileVOArray[i].getProfileId()+Config.PATIENT_PROFILE_FILE_STORAGE_EXT,filePathWin,filePathLinux);
				boolean flag=fileUtil.readFile();
				if(flag){
					fileUtil.getFileContent();
					String htmlCode=fileUtil.getFileContentInString();
					ByteArrayOutputStream baosPDF=null;
					PdfWriter docWriter = null;
					com.lowagie.text.Document document = new com.lowagie.text.Document();
					try
					{
						htmlCode=HisHTMLParserUtil.freezeHTMLCodeElements(htmlCode);
						
					}
					catch (Exception e)
					{
						e.printStackTrace();
						System.out.println("Error in parsing html");
						//objStatus.add(Status.UNSUCESSFULL,"","");
					}
					
						htmlCode = HTMLParsingUTIL.makeHTMLPDFCompatible(htmlCode);
						HTMLToPDFUTIL converter = new HTMLToPDFUTIL(null, document, htmlCode);
						baosPDF = new ByteArrayOutputStream();
						docWriter = PdfWriter.getInstance(document, baosPDF);
						docWriter.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
						converter.convertToPDF();
						
						if (document != null) document.close();
						if (docWriter != null) docWriter.close();
						if (baosPDF.size() < 1) throw new DocumentException("document has " + baosPDF.size() + " bytes");
					//File f=new File("d:\\cdburnFile\\" + patientProfileVOArray[i].getProfileId()+".pdf");
					fileUtil.setFileName(patientProfileVOArray[i].getProfileId()+".pdf");
					fileUtil.setWindowsFilePath(MrdConfig.BURN_FILE_DIRECTORY_WINDOWS);
					fileUtil.setLinuxFilePath(MrdConfig.BURN_FILE_DIRECTORY_LINUX);
					fileUtil.setFileContent(baosPDF.toByteArray());
					fileUtil.saveFile();
					//System.out.println("Document size = "+baosPDF.size());
					fileSize=fileSize+baosPDF.size();
					//FileOutputStream fos=new FileOutputStream(f);
					//baosPDF.writeTo(fos);
					//fos.close();
					fileName+=patientProfileVOArray[i].getProfileId()+".pdf" +"#";
				}
			}
			fb.setFileName(fileName);
			fb.setWinPathToBurn(MrdConfig.BURN_FILE_DIRECTORY_WINDOWS);
			fb.setLinuxPathToBurn("\\root"+MrdConfig.BURN_FILE_DIRECTORY_LINUX);
			if(System.getProperties().getProperty("os.name").startsWith("Win")){
				fb.setServerOS("Windows");
			}
			else{
				fb.setServerOS("Linux");
			}
			//burnCD(fb,request);
			if(fileName.equals("") || fileName.equals("#")){
				//objStatus.add(Status.RECORDFOUND);
				fb.setHmode("");
				throw new HisRecordNotFoundException("File not found to burn");
			}
			else{
				objStatus.add(Status.RECORDFOUND);
				objStatus.add(Status.DONE);
			}
			System.out.println("Total size = "+fileSize/(1024*1024));
			fb.setHiddenRemarks(fb.getRemarks());
			WebUTIL.setAttributeInSession(request,MrdConfig.PATIENT_PROFILE_VO_ARRAY, patientProfileVOArray);
			saveCDBurnDetail(fb, request);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.RECORDFOUND, e.getMessage(), "");
			
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
	
	public static void showProfile(CDBurnFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		List<PatientProfileDetailVO> patientProfileVOList=null;
		PatientProfileDetailVO patientProfileVO=null;
		String htmlCode="";
		try
		{
			patientProfileVOList=(List)request.getSession().getAttribute(MrdConfig.PATIENT_PROFILE_VO_LIST);
			String index=request.getParameter("index");
			
			patientProfileVO=patientProfileVOList.get(Integer.parseInt(index));
			
			
			String filePathWin="";
			String filePathLinux="";
			filePathWin=Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS;
			filePathLinux=Config.PATIENT_PROFILE_STORAGE_PATH_LINUX;
			HisFileControlUtil fileUtil=new HisFileControlUtil(patientProfileVO.getProfileId()+Config.PATIENT_PROFILE_FILE_STORAGE_EXT,filePathWin,filePathLinux);
			fileUtil.readFile();
			fileUtil.getFileContent();
			htmlCode=fileUtil.getFileContentInString();
			
			WebUTIL.setAttributeInSession(request, MrdConfig.PATIENT_PROFILE_HTML_STRING, htmlCode);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			
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
	
	public static void saveCDBurnDetail(CDBurnFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		PatientProfileDetailVO []patientProfileVOArray=null;
		PatientProfileCDBurnDtlVO []patientProfileCDBurnDtlVO=null;
		//String htmlCode="";
		try
		{
			
			patientProfileVOArray=(PatientProfileDetailVO[])request.getSession().getAttribute(MrdConfig.PATIENT_PROFILE_VO_ARRAY);
			if(patientProfileVOArray!=null){
				int i=0;
				patientProfileCDBurnDtlVO=new PatientProfileCDBurnDtlVO[patientProfileVOArray.length];
				for(PatientProfileDetailVO vo:patientProfileVOArray){
					patientProfileCDBurnDtlVO[i]=new PatientProfileCDBurnDtlVO();
					HelperMethods.populate(patientProfileCDBurnDtlVO[i],vo);
					patientProfileCDBurnDtlVO[i].setRemarks(fb.getHiddenRemarks());
					patientProfileCDBurnDtlVO[i].setPatCrNo(fb.getPatCrNo());
					i++;
				}
			
			}
			System.out.println("cd burn detail saved successfully");
			CDBurnDATA.saveCDBurnDetail(patientProfileCDBurnDtlVO,getUserVO(request));
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			
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
	
	
	//not in use
	public static boolean burnCD(CDBurnFB fb,HttpServletRequest request){
		boolean flag=false;
	
		//Status objStatus = new Status();
		//PatientProfileDetailVO []patientProfileVOArray=null;
		try
		{
			String path="C:\\AHIMS\\cdburnFile";
			BurnDisc test = new BurnDisc("F:",path,"0");
		    test.burn();
		    flag=true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
		
}
