package uploadfilesonserver;

import java.util.Map;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts.upload.FormFile;

import java.util.Scanner;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;

public class CellSerumRegroupingUTL extends ControllerUTIL
{
	
	//Added on 14-11-2018 
	public static void getData(BloodGroupingUploadFB fb,HttpServletRequest request)
	{
		
		Status objStatus = new Status();
		String Path = "";
		FileUploadedVO vo = new FileUploadedVO();
		/*if(fb.getBagNo()!=null)
		{
			fb.setBagNo(fb.getBagNo().trim());
		}
		try
		{
			if(!fb.getDonorNo().equals(""))
				fb.setBagNo(fb.getDonorNo());
			Map map =CellSerumRegroupingDATA.getBagCellSerumDetailBagNoWise(fb.getBagNo(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			objStatus.add(Status.TRANSINPROCESS);
		}*/
		try{
			
			String status="0";
			System.out.println("Upload");
			ByteArrayInputStream is = null;
			BufferedReader br = null;
			FormFile file = fb.getUploadedFileName();
			System.out.println("filename "+fb.getUploadedFileName());
			byte[] array= file.getFileData();						//Uploaded file data
			String fileName = file.getFileName();
			/*String[] fileee = fileName.split(".");
			String ext = fileee[1];*/
			UUID uuid = UUID.randomUUID();					//for random file name
			String filename = uuid.toString().replaceAll("[\\s\\-()]", "");
			System.out.println("Generated File name:-- "+filename);
			Path = CellSerumRegroupingDATA.getDetailsOfPath(getUserVO(request));	// to get Path where the file has to be saved
			Path = Path + "//"+filename+".dcm";
			System.out.println("Full Path :--- "+Path);
			String crno = fb.getCrno(); 
			vo.setCrno(crno);
			vo.setFileName(fileName);
			vo.setStoragePath(Path);
			try (FileOutputStream fos = new FileOutputStream(Path)) {			// File written on path
			   fos.write(array);
			   //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
			}
			CellSerumRegroupingDATA.saveDetailsOfFile(vo,getUserVO(request));
			
			request.setAttribute("success", "File Uploaded Succesfuuly");
			objStatus.add(Status.NEW,"","File Uploaded Succesfully");
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.NEW, "", e.getMessage());
			request.setAttribute("errorMsg", "File Upload Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			request.setAttribute("errorMsg", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			request.setAttribute("errorMsg", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
			request.setAttribute("errorMsg", "Error");
		}
		catch(IOException e){System.out.println(e.getMessage());}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
}


