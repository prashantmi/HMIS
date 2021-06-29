package hisglobal.utility.filetransfer.action;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.actionsupport.DisplayUploadedFileSUP;
import hisglobal.utility.filetransfer.config.FileTransferConfig;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import registration.config.RegistrationConfig;
import registration.mongodb.MongoXmlHandler;
import registration.transactions.controller.util.PatientModificationUTIL;

public class DisplayUploadedFileACT extends DisplayUploadedFileSUP
{
	static final long serialVersionUID = 0L;

	public String execute()
	{
		OutputStream os = null;
		BufferedOutputStream bos = null;
		FileInputStream fileInStream=null;
		try
		{
			os = response.getOutputStream();
			bos = new BufferedOutputStream(os);
			byte[] bytes=null;
			//if(strProcessId==null || strProcessId.trim().equals("") || strCRNoHospCode==null || strCRNoHospCode.trim().equals("") || strFileName==null || strFileName.trim().equals(""))
			if(strCRNoHospCode==null || strCRNoHospCode.trim().equals("") || strFileName==null || strFileName.trim().equals(""))
			{
				
				//String processId=request.getParameter("strProcessId");
				//throw new HisApplicationExecutionException("Servlet Parameters are not sufficient:: Please Contact Administrator");
				
				//fileInStream = (FileInputStream)this.getSession().get(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT);
				
				String uploadedFile  = (String) this.getSession().get(FileTransferConfig.KEY_UPLOADED_FILE_NAME);
				
				response.setContentType(FTPFileTransfer.getFileContentType(uploadedFile));
				System.out.println("look out tag manisha :"+strProcessId);
				
				if(FileTransferConfig.MAP_PROCESS_DETIAL.containsKey(strProcessId))				
				{
				//byte[] bytes = IOUtils.toByteArray(fileInStream);
				if(strProcessId.equals(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD))
				{
				try{
				fileInStream = (FileInputStream)this.getSession().get(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT);
				byte[] bytesImg = IOUtils.toByteArray(fileInStream);
				bos.write(bytesImg, 0, bytesImg.length);
				fileInStream.getChannel().position(0);
				}
				catch(Exception e){
				byte[] bytesImag = (byte[])this.getSession().get(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT);
				bos.write(bytesImag, 0, bytesImag.length);
				fileInStream.getChannel().position(0);
				}
				}
				//Added for documents
				if(strProcessId.equals(FileTransferConfig.PROCESS_ID_PATIENT_SCANNED_DOCUMENTS))
				{
				//bytes = (byte[])this.getSession().get(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT_DOC);
				 try
				 {fileInStream = (FileInputStream)this.getSession().get(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT_DOC);
				 byte[] bytesDoc = IOUtils.toByteArray(fileInStream);
				 bos.write(bytesDoc, 0, bytesDoc.length);
				 fileInStream.getChannel().position(0);
				 }
				 catch(Exception e){
				byte[] bytesDocu = (byte[])this.getSession().get(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT_DOC);
				bos.write(bytesDocu, 0, bytesDocu.length);
				fileInStream.getChannel().position(0);
				}
				}
				
				}
				else //in case strprocessid is null
				{
					try{
					byte[] bytesarr = (byte[])this.getSession().get(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT);
					bos.write(bytesarr, 0, bytesarr.length);
					fileInStream.getChannel().position(0);
					 }
					catch(Exception e)
					{
					fileInStream = (FileInputStream)this.getSession().get(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT);
					byte[] bytess = IOUtils.toByteArray(fileInStream);
					bos.write(bytess, 0, bytess.length);
					fileInStream.getChannel().position(0);
					}
				}
		
				
				
				

				/*int c;
				while ((c = fileInStream.read()) != -1)
				{
					os.write(c);
				}*/    
			}
			else
			{
				
				// Setting File Content Type into Response
				response.setContentType(FTPFileTransfer.getFileContentType(strFileName));
				
				if(!FTPFileTransfer.retrieveWriteFile(strProcessId, strFileName, strCRNoHospCode, response))
				{
					String msg="<B><font color='white'>No File Uploaded yet</font></B>";//This file does not exist:: Please Contact Administrator</B>";
					byte[] bytes1=msg.getBytes();
					response.setHeader("Pragma", "no-cache");
					bos.write(bytes1, 0, bytes1.length);
				}
			}
		}
		catch(HisApplicationExecutionException e)
		{
			String msg="<B>"+ e.getMessage()+"</B>";
			byte[] bytes=msg.getBytes();
			response.setHeader("Pragma", "no-cache");
			try
			{
				bos.write(bytes, 0, bytes.length);
			}
			catch(IOException ee)
			{
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			String msg="<B><font color='white'>No File Uploaded yet</font></B>";//This file does not exist:: Please Contact Administrator</B>";
			byte[] bytes=msg.getBytes();
			response.setHeader("Pragma", "no-cache");
			try
			{
				bos.write(bytes, 0, bytes.length);
			}
			catch(IOException ee)
			{
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String msg="<B><font color='white'>No File Uploaded yet</font></B>";//This file does not exist:: Please Contact Administrator</B>";
			byte[] bytes=msg.getBytes();
			response.setHeader("Pragma", "no-cache");
			try
			{
				bos.write(bytes, 0, bytes.length);
			}
			catch(IOException ee)
			{
			}
		}
		finally
		{
			try
			{
				response.getOutputStream().flush();
				if(bos!=null)	bos.close();
								
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String viewImageFromMongoDB() 
	{
		InputStream inputStream = null;
		OutputStream os = null;
		BufferedOutputStream bos = null;
		try
		{
			/*if(strProcessId==null || strProcessId.trim().equals("") || strCRNoHospCode==null || strCRNoHospCode.trim().equals("") || strFileName==null || strFileName.trim().equals(""))
			{*/
			//String docId=strCRNoHospCode + "_" + strFileName;
			String docId= strFileName;
			byte[] getDoc= MongoXmlHandler.getInstance().latestFetchFile(docId);
			 os = response.getOutputStream();
			bos = new BufferedOutputStream(os);
			inputStream = new ByteArrayInputStream(getDoc);
			int c;
			while ((c = inputStream.read()) != -1)
			{
				os.write(c);
			}  
			//}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String msg="<B><font color='white'>No File Uploaded yet</font></B>";//This file does not exist:: Please Contact Administrator</B>";
			byte[] bytes=msg.getBytes();
			response.setHeader("Pragma", "no-cache");
			try
			{
				bos.write(bytes, 0, bytes.length);
			}catch(Exception ee)
			{
			}
			return null;
			
		}
		finally
		{
			try
			{
				if(inputStream!=null) inputStream.close();
				response.getOutputStream().flush();
				if(bos!=null)	bos.close();
			}
			catch(Exception e)
			{
			}
		}
		return null;
	}
	
	
	
	
	
	
}
