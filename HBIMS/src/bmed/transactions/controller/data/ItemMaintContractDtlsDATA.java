package bmed.transactions.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;








import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.AttachFileGlobal;
import org.apache.commons.fileupload.FileItem;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestWrapper;
import application.filters.UploadMultipartRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestWrapper;

import application.filters.UploadMultipartRequestWrapper;
import bmed.EMMSStaticConfigurator;
import bmed.SaveFileInFtpGlobal;
import bmed.global.controller.util.BmedConfigUtil;
import bmed.transactions.bo.BmedGlobalBO;
import bmed.transactions.bo.BmedTransBO;
import bmed.transactions.controller.fb.ItemMaintContractDtlsFB;
import bmed.vo.HemtItemMcDtlVO;
import bmed.vo.ItemBrandMstVO;

public class ItemMaintContractDtlsDATA 
{
	/**
	 * This method is used to initialize Main Page
	 * @param ItemMaintContractDtlsFB_p
	 * @param request_p
	 */
	public static void initializeMain(ItemMaintContractDtlsFB ItemMaintContractDtlsFB_p, HttpServletRequest request_p) {
	String strErrMsg;
	BmedGlobalBO bmedGlobalBO=null;
	String strDepartmentComboOptions;
	String strEnggItemTypeComboOptions;
	String strUnitComboOptions;
	String strContMaintenanceTypeComboOptions;
	String strHospitalCode,strSeatId;
	
	UserVO userVo = null;
		try
		{
					    bmedGlobalBO  = new BmedGlobalBO();
					           userVo = ControllerUTIL.getUserVO(request_p);
					strHospitalCode = userVo.getHospitalCode();						
						strSeatId 	= userVo.getSeatId();
					
			strDepartmentComboOptions = bmedGlobalBO.getDepartmentComboOptions(strHospitalCode,strSeatId,2);
		  strEnggItemTypeComboOptions = bmedGlobalBO.getItemTypeComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
			
			ItemMaintContractDtlsFB_p.setStrDeptNameCombo(strDepartmentComboOptions);
			ItemMaintContractDtlsFB_p.setStrEnggItemTypeCmb(strEnggItemTypeComboOptions);
			
			
			     strUnitComboOptions = BmedGlobalBO.getUnitComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
			ItemMaintContractDtlsFB_p.setStrUnitIdCmb(strUnitComboOptions);
						
			//Calling BO Method Here
			strContMaintenanceTypeComboOptions=bmedGlobalBO.getMaintenanceContTypeComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
			
			ItemMaintContractDtlsFB_p.setStrMantContractTypeCmb(strContMaintenanceTypeComboOptions);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			strErrMsg = "ItemMaintContractDtlsTransDATA.initializeMain() --> "
				+ e.getMessage();
		HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
				strErrMsg);
		ItemMaintContractDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");
	
		eObj = null;
		}
	
    }
    /**
     * This method is used to get Store Name
     * @param ItemMaintContractDtlsFB_p
     * @param request_p
     * @param response_p
     */

    public static void getStoreName(ItemMaintContractDtlsFB ItemMaintContractDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) {
	String strErrMsg;
	BmedGlobalBO bmedGlobalBO=null;
	String strStoreComboOptions;
	String strHospitalCode_p;
	UserVO userVo = null;
	String strDeptId_p;
	try
	{
		       bmedGlobalBO = new BmedGlobalBO();
		             userVo = ControllerUTIL.getUserVO(request_p);
		  strHospitalCode_p = userVo.getHospitalCode();
		        strDeptId_p = (String) request_p.getParameter("deptId");
		//Calling BO method here
	    strStoreComboOptions = bmedGlobalBO.getStoreComboOptions(strHospitalCode_p,userVo.getSeatId(),strDeptId_p);
		
		try 
		{									
			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strStoreComboOptions);
				
		}
		catch(Exception e)
		{
//				e.printStackTrace();
		}
	

		} catch (Exception e) {
			e.printStackTrace();
			strErrMsg = "ItemMaintContractDtlsTransDATA.getStoreName() --> "
				+ e.getMessage();
		HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
				strErrMsg);
		ItemMaintContractDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");
	
		eObj = null;
		}
		
	}

    /**
     * This method is used to get Engg Item Sub Type Combo
     * @param ItemMaintContractDtlsFB_p
     * @param request_p
     * @param response_p
     */
    public static void getEnggItemSubType(ItemMaintContractDtlsFB ItemMaintContractDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) {
	String strErrMsg;
	BmedGlobalBO bmedGlobalBO=null;
	String strEnggItemSubTypeComboOptions;
	String strHospitalCode_p=null;
	UserVO userVo = null;
	String strEnggItemTypeId_p;
	try
	{
		                  bmedGlobalBO = new BmedGlobalBO();
		                        userVo = ControllerUTIL.getUserVO(request_p);
		             strHospitalCode_p = userVo.getHospitalCode();
		           strEnggItemTypeId_p = (String) request_p.getParameter("enggItemTypeId");
		
		//Calling BO method here
		strEnggItemSubTypeComboOptions = bmedGlobalBO.getItemSubTypeComboOptions(strHospitalCode_p, strEnggItemTypeId_p);
		
		try 
		{									
			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strEnggItemSubTypeComboOptions);
				
		}
		catch(Exception e)
		{
				e.printStackTrace();
		}
		

		} catch (Exception e) {
	
			e.printStackTrace();
			strErrMsg = "ItemMaintContractDtlsTransDATA.getEnggItemSubType() --> "
				+ e.getMessage();
		HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
				strErrMsg);
		ItemMaintContractDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");
	
		eObj = null;
		}
		
	}

    /**
     * Method used to get Item Name Combo
     * @param ItemMaintContractDtlsFB_p
     * @param request_p
     * @param response_p
     */

    public static void getItemName(ItemMaintContractDtlsFB ItemMaintContractDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) {
	String strErrMsg;
	BmedGlobalBO bmedGlobalBO=null;
	ItemBrandMstVO vo =null;
	String strItemComboOptions=null;
	String strHospitalCode_p;
	String strDeptId_p;
	String strStoreId_p;
	String strEnggItemType_p;
	String strEnggItemSubType_p;
	UserVO userVo = null;
	String strFlag;
	try
	{
		     bmedGlobalBO = new BmedGlobalBO();
		     vo = new ItemBrandMstVO(); 
		           userVo = ControllerUTIL.getUserVO(request_p);
		strHospitalCode_p = userVo.getHospitalCode();
		          strFlag = (String) request_p.getParameter("flag");
						
		if(strFlag.equals("0"))
		{	
			       strStoreId_p = (String) request_p.getParameter("storeId");
			//Calling BO method here
			       vo.setStrMode("13");
		    strItemComboOptions = bmedGlobalBO.getItemBrandComboOptionsOnStore(strHospitalCode_p,strStoreId_p,vo);
			
		}	
		else
		{	
			if(strFlag.equals("1"))
			{	
				        strDeptId_p = (String) request_p.getParameter("deptId");
				//Calling BO method here
				strItemComboOptions = bmedGlobalBO.getItemBrandComboOptionsOnDepartment(strHospitalCode_p,strDeptId_p);
			}
			else
			{
				strEnggItemType_p    = (String) request_p.getParameter("enggItemTypeId");
				strEnggItemSubType_p = (String) request_p.getParameter("enggItemSubTypeId");
				 //Calling BO method here
				 strItemComboOptions = bmedGlobalBO.getItemBrandComboOptionsOnBasisOfEngg(strHospitalCode_p,strEnggItemType_p,strEnggItemSubType_p);
				
			}	
		}	
					
		try 
		{									
			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strItemComboOptions);
				
		}
		catch(Exception e)
		{
		//		e.printStackTrace();
		}
		

	} catch (Exception e) {

		e.printStackTrace();
		strErrMsg = "ItemMaintContractDtlsTransDATA.getItemName() --> "
			+ e.getMessage();
	HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
			strErrMsg);
	ItemMaintContractDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
			+ eObj.getErrorID() + "],Contact System Administrator! ");

	eObj = null;
	}
	
   }
   /**
    * Method used to get Stock Details
    * @param ItemMaintContractDtlsFB_p
    * @param request_p
    * @param response_p
    */
  
    public static void getStockDetails(ItemMaintContractDtlsFB ItemMaintContractDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) {
	String strErrMsg;
	BmedTransBO bmedTransBO=null;
	String strStockDtl=null;
	String strHospitalCode_p;
	String strItemBrandId_p;
	String strDeptId_p;
	String strMode_p;
	UserVO userVo = null;
	try
	{
		             bmedTransBO = new BmedTransBO();
				          userVo = ControllerUTIL.getUserVO(request_p);
			   strHospitalCode_p = userVo.getHospitalCode();
				strItemBrandId_p = (String) request_p.getParameter("itemNo");
				     strDeptId_p = (String) request_p.getParameter("deptId");
				       strMode_p = (String) request_p.getParameter("mode");
				     
				     // Calling BO Method
	                 strStockDtl = bmedTransBO.getStockDetails(strHospitalCode_p,strItemBrandId_p,strDeptId_p,strMode_p);
		             
								
		try 
		{									
			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strStockDtl);
				
		}
		catch(Exception e)
		{
				e.printStackTrace();
		}
		
		

		} catch (Exception e) {
	
			e.printStackTrace();
			strErrMsg = "ItemMaintContractDtlsTransDATA.getStockDetails() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
					strErrMsg);
			ItemMaintContractDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");
	
			eObj = null;
		}
				
	}
	/**
	 * Method is Used to get Previous Maintenance Details
	 * @param ItemMaintContractDtlsFB_p
	 * @param request_p
	 * @param response_p
	 */
	public static void getPrevMantDetails(ItemMaintContractDtlsFB ItemMaintContractDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) {
		String strErrMsg;
		BmedTransBO bmedTransBO=null;
		String strPrevMantDtl=null;
		String strHospitalCode_p;
		String strStockInf_p;
		UserVO userVo = null;
		try
		{
			             bmedTransBO = new BmedTransBO();
					          userVo = ControllerUTIL.getUserVO(request_p);
				   strHospitalCode_p = userVo.getHospitalCode();
				       strStockInf_p = (String) request_p.getParameter("strStockInf");
				      // Calling BO Method 
			          strPrevMantDtl = bmedTransBO.getPrevMantDetails(strHospitalCode_p,strStockInf_p,"1");
			try 
			{									
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(strPrevMantDtl);
					
			}
			catch(Exception e)
			{
			//		e.printStackTrace();
			}
			
			} catch (Exception e) {
	 
				strErrMsg = "ItemMaintContractDtlsTransDATA.getStockDetails() --> "
						+ e.getMessage();
				HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
						strErrMsg);
				ItemMaintContractDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");
	
				eObj = null;
			}
	
        }
		/**
		 * Method is used to get Supplier Combo
		 * @param ItemMaintContractDtlsFB_p
		 * @param request_p
		 * @param response_p
		 */
	
	    public static void getSupplierCmb(ItemMaintContractDtlsFB ItemMaintContractDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) {
		String strErrMsg;
		BmedGlobalBO bmedGlobalBO=null;
		String strSupplierComboOptions;
		String strHospitalCode_p=null;
		UserVO userVo = null;
		String strItemBrandId_p;
		String strCheckIsItem_p;
		try
		{
			bmedGlobalBO= new BmedGlobalBO();
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode_p=userVo.getHospitalCode();
			strItemBrandId_p = (String) request_p.getParameter("itemId").split("\\^")[0];
			strCheckIsItem_p = (String) request_p.getParameter("itemId").split("\\^")[1];
			
			
			// Calling BO method here
			strSupplierComboOptions = bmedGlobalBO.getSupplierComboOptions(Config.SUPER_USER_HOSPITAL_CODE, strItemBrandId_p,strCheckIsItem_p);
			
			try 
			{									
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(strSupplierComboOptions);
					
			}
			catch(Exception e)
			{
				//	e.printStackTrace();
			}
			

		} catch (Exception e) {
 
			strErrMsg = "ItemMaintContractDtlsTransDATA.getEnggItemSubType() --> "
				+ e.getMessage();
		HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
				strErrMsg);
		ItemMaintContractDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");

		eObj = null;
		}
		
	}
	    
	    
public static void getUploadedFile(ItemMaintContractDtlsFB ItemMaintContractDtlsFB_p,HttpServletRequest request_p, HttpServletResponse response_p)
{			
			String strmsgText = null;
			String strFileName = "";
			File f = null;
			FileInputStream fis = null;
			byte[] fileContent = new byte[1024];
			BmedConfigUtil bmed =null;
			String strFtpFolderName ="";
			UserVO userVo = null;
			
			try 
			{
				   
				   bmed = new BmedConfigUtil();
				   userVo = ControllerUTIL.getUserVO(request_p);
				   strFtpFolderName = bmed.getStrFtpFileFolder("15", userVo.getHospitalCode());
			       
				  
				   
				   if(strFtpFolderName.equals("")||strFtpFolderName==null)
				   {
					    strFtpFolderName = "bmedDOCS";
				   } 
				
			
				    strFileName = ItemMaintContractDtlsFB_p.getStrUploadFileId();
				//strFileName= temp.split("//.")[0];
							
				//System.out.println("File Name::::::"+ItemWarrantyDtlsFB_p.getStrUploadFileId());
				String[] strTemp = strFileName.replace(".", "#").split("#");
				String strExt = strTemp[strTemp.length - 1];
				strFileName=strTemp[0]+"."+strExt;
				
				
				if (strExt.equalsIgnoreCase("txt")
						|| strExt.equalsIgnoreCase("txt")) {
					
					response_p.setContentType("application/txt");
					response_p.setHeader("Content-disposition",
							" filename="+strFileName);
					
				}
				
				else if (strExt.equalsIgnoreCase("pdf")) 
				{
                    
					response_p.setContentType("application/pdf");
					response_p.setHeader("Content-disposition",	"attachment; filename="+strFileName);

				} else if (strExt.equalsIgnoreCase("html")
						|| strExt.equalsIgnoreCase("htm")) {
					
					response_p.setContentType("text/html;charset=utf-8");
					response_p.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
					
				}else if (strExt.equalsIgnoreCase("xml")) {
					
					response_p.setContentType("application/xml");
					response_p.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
					
				} else if (strExt.equalsIgnoreCase("doc") || strExt.equalsIgnoreCase("docx")) {
					
					response_p.setContentType("application/msword");
					response_p.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
					
				} else if (strExt.equalsIgnoreCase("rdf")) {
					
					response_p.setContentType("application/msword");
					response_p.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
					
				} else if (strExt.equalsIgnoreCase("rtf")) {
					
					response_p.setContentType("application/msword");
					response_p.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
					
				} else if(strExt.equalsIgnoreCase("png")){

					response_p.setContentType("image/png");
					response_p.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
					
				} else if(strExt.equalsIgnoreCase("gif")){

					response_p.setContentType("image/gif");
					response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
				} else if(strExt.equalsIgnoreCase("jpeg") || strExt.equalsIgnoreCase("jpg")){
		
					response_p.setContentType("image/jpg");
					response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
				} else {

					response_p.setContentType("text/html;charset=utf-8");
					response_p.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
					
				}
				/*******************************************************************/
				
				 fis = new FileInputStream( new File(HisUtil.getParameterFromHisPathXML("TEMP_PATH")+"/"+strFileName));
				
				/*  String sessionFtpAddress="10.0.1.78/ftpserver"; //populate from session
				  sessionFtpAddress = EMMSStaticConfigurator.bmedpath;
				//  String logicalName = sessionFtpAddress.replace('/', '#').split("#")[1];
				  String Fileurl= "ftp://"+sessionFtpAddress+"/"+strFtpFolderName;			
				 
				 
				  URL                  urlftp = new URL(Fileurl+"/"+strFileName);
				  URLConnection          urlc =	urlftp.openConnection();
				  InputStream              io = urlc.getInputStream();
				  	*/	  
				 
				        FileOutputStream fos = new FileOutputStream(strFileName);
				        byte[] buf = new byte[4096];
				        int read = 0;
				        while ((read = fis.read(buf)) > 0) 
				        {
				            fos.write(buf, 0, read);
				        }	    				  				  	  
				  
				     f = new File(strFileName);

					if (!f.isFile()) 
					{

						throw new Exception("Invalid File Path");
					}

					fis = new FileInputStream(f);

					while (fis.read(fileContent) != -1) 
					{
		                
						response_p.getOutputStream().write(fileContent);
					}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				strmsgText = "bmed.master.PreTechApprovalTransDATA.getUploadedFile --> "
						+ e.getMessage();
				HisException eObj = new HisException("bmed",
						"PreTechApprovalTransDATA->getUploadedFile()", strmsgText);
				ItemMaintContractDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");
		
				eObj = null;
			} finally {
		
				if (f != null)
					f = null;
				if (fis != null)
					fis = null;
			}
		}
	    
	    
	   
	    /**
	     * Method is Used to Save Data into table HEMT_ITEM_MC_DTL
	     * @param ItemMaintContractDtlsFB_p
	     * @param request_p
	     */		    
	    public static void saveData(ItemMaintContractDtlsFB ItemMaintContractDtlsFB_p, HttpServletRequest request_p) 
	    {
			String strErrMsg;
			
			String strSeatId_p;
			String strHospitalCode_p;
			UserVO userVo = null;
			HemtItemMcDtlVO hemtItemMcGlobalVO =null;
			BmedConfigUtil bmed =null;
			String strFileId="0";
			String strFtpFolderName ="";
			HisUtil hisutil = null;
			AttachFileGlobal fs=null;
			String strFileExt;
			String strCurrentDate=null;
			String[] temp = null;
			try
			{					     
				  	hemtItemMcGlobalVO = new HemtItemMcDtlVO();
				  				  bmed = new BmedConfigUtil();
				  				 hisutil = new HisUtil("BMED Transactions", "ItemMaintContractTransDATA");
				                userVo = ControllerUTIL.getUserVO(request_p);
	                 strHospitalCode_p = userVo.getHospitalCode();
				           strSeatId_p = userVo.getSeatId();
				           HisUtil his=new HisUtil(" ", " ");
				           fs=new AttachFileGlobal();
				           strCurrentDate = hisutil.getDSDate("DD-Mon-YYYY HH24_MI_SS");
				          // FormFile myFile = ItemMaintContractDtlsFB_p.getStrLocation();
							 //FileItem myFile = ((UploadMultipartRequestWrapper)((MultipartRequestWrapper)request_p).getRequest() ).getFileItem("strLocation");

			    		    //strFileExt = myFile.getName();
					           //temp = strFileExt.replace('.', '#').split("#");
					     //strFileExt = temp[temp.length-1];
					     //if (myFile != null && myFile.getSize() > 0) {	       
					//  strFileId = BmedTransBO.getFileName(myFile,strHospitalCode_p,"2");
				    		    //strFileId = ItemMaintContractDtlsFB_p.getStrItemBrandId().split("\\^")[0]+"_"+strCurrentDate.replace(" ", "_")+"."+strFileExt;
				      //String strFileName = myFile.getName();
				   
						//  myFile = ItemMaintContractDtlsFB_p.getStrLocation();
							
				    		    //strFileId = BmedTransBO.getFileName(myFile,strHospitalCode_p,"1");
			              
//	                Testing data to Save record in FTP Server   
	             //  strFtpFolderName = bmed.getStrFtpFileFolder("15",strHospitalCode_p);
							//}
					     //boolean value=his.fileupload(myFile);
	              // if(!value)
	   		    {//
	            	  // ItemMaintContractDtlsFB_p.setStrNormalMsg("File can not be upload. Please Check FIle Format or File Size ");
	   		    	//throw new HisException("File can not be upload. Please Check FIle Format or File Size ");
	   		   // }else{
	   		    	//fs.saveFile(myFile.get(),strFileId);
	   		    	//fs.saveFile(myFile.getFileData(),strFileId);
	   		    	//ItemMaintContractDtlsFB_p.setStrUploadFileId(strFileId);
				    
				/*   if(strFtpFolderName.equals("")||strFtpFolderName==null)
				   {
					    strFtpFolderName = "bmedDOCS";
				   } 
			        String sessionFtpAddress="10.0.1.78/ftpserver"; //populate from session
			        sessionFtpAddress = EMMSStaticConfigurator.bmedpath;
					//String logicalName = sessionFtpAddress.replace('/', '#').split("#")[1];
					String Fileurl= "ftp://"+sessionFtpAddress+"/"+strFtpFolderName;		
					
					System.out.println("File Url:::::"+Fileurl);			
					String Ftpurl="ftp://"+sessionFtpAddress+"/"+strFtpFolderName;			
					System.out.println("Ftp Url::::"+Ftpurl);		
					String FileName = strFileId; //given by user at run time
					System.out.println("File Name::::"+strFileId);		
					SaveFileInFtpGlobal.saveFileToLocation(Fileurl, Ftpurl, FileName,myFile.getInputStream());
					System.out.println("file Created Successfully");
					*/
//					 End Test Data 
			    
			     /*fs.saveFile(myFile.getFileData(),strFileId); 
			     
			     ItemMaintContractDtlsFB_p.setStrUploadFileId(strFileId);
			     hemtItemMcGlobalVO.setStrSeatId(strSeatId_p);
				 hemtItemMcGlobalVO.setStrHospCode(strHospitalCode_p);
			     */ 				      
			     
				/*
				private String strPKey;   // HEMNUM_ITEM_ID||'^'|| HEMSTR_BATCH_NO||'^'||HEMNUM_ITEM_SL_NO||'^'|| HEMNUM_SL_NO||'^'||GNUM_HOSPITAL_CODE
				private String strStockInf; //HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
				                            //||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
			                                //GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE
				
			    System.out.println("File ID::::"+strFileId);
			    System.out.println("Upload File name::::"+myFile.getFileName());
				System.out.println("Check Wheteher Item or Non-Item:::"+ItemMaintContractDtlsFB_p.getStrCheck());
			    System.out.println("Doc ref Date::::"+ItemMaintContractDtlsFB_p.getStrDocRefDate());
			    System.out.println("Doc Ref No::::"+ItemMaintContractDtlsFB_p.getStrDocRefNo());
				System.out.println("Dept ID::"+ItemMaintContractDtlsFB_p.getStrDeptId());
				System.out.println("Store ID::"+ItemMaintContractDtlsFB_p.getStrStoreId());
				System.out.println("Engg Item Type::"+ItemMaintContractDtlsFB_p.getStrEnggItemTypeId());
				System.out.println("Engg Item Sub Type::"+ItemMaintContractDtlsFB_p.getStrEnggItemSubTypeId());
				System.out.println("Item Brand Id::"+ItemMaintContractDtlsFB_p.getStrItemBrandId());
				System.out.println("Contr Supp Id::"+ItemMaintContractDtlsFB_p.getStrMantContractSuppId());
				System.out.println("Contract Type ID::"+ItemMaintContractDtlsFB_p.getStrMantContractTypeId());
				System.out.println("Main Contract Name::"+ItemMaintContractDtlsFB_p.getStrMaintenanceContractName());
				System.out.println("Tender No::"+ItemMaintContractDtlsFB_p.getStrTenderNo());
				System.out.println("Tender Date::"+ItemMaintContractDtlsFB_p.getStrTenderDate());
				System.out.println("Order No::"+ItemMaintContractDtlsFB_p.getStrOrderNo());
				System.out.println("Order Date::"+ItemMaintContractDtlsFB_p.getStrOrderDate());
				System.out.println("From Date:::"+ItemMaintContractDtlsFB_p.getStrFromDate());
				System.out.println("To Date::"+ItemMaintContractDtlsFB_p.getStrToDate());
				System.out.println("Rotine Frequency::"+ItemMaintContractDtlsFB_p.getStrRoutineFrequency());
				System.out.println("Routin Unit:::"+ItemMaintContractDtlsFB_p.getStrRoutineUnitId());
				System.out.println("Response Time:::"+ItemMaintContractDtlsFB_p.getStrResponseTime());
				System.out.println("Response Unit::"+ItemMaintContractDtlsFB_p.getStrResponseTimeUnitId());
				System.out.println("Cost:::"+ItemMaintContractDtlsFB_p.getStrMaintenanceCost());
				System.out.println("remarks:::"+ItemMaintContractDtlsFB_p.getStrRemarks());
				System.out.println("term  Cod:::"+ItemMaintContractDtlsFB_p.getStrTermsAndCond());
				System.out.println("Penlty Cond:::"+ItemMaintContractDtlsFB_p.getStrPeneltyCond());
				System.out.println("Stock Info Value:::::"+ItemMaintContractDtlsFB_p.getStrStockInfoVal());
                */
                    
                
	                hemtItemMcGlobalVO.setStrMode("1");
					hemtItemMcGlobalVO.setStrMcType(ItemMaintContractDtlsFB_p.getStrMantContractTypeId());
					hemtItemMcGlobalVO.setStrItemId(ItemMaintContractDtlsFB_p.getStrItemBrandId());
					hemtItemMcGlobalVO.setStrManufctSlNo("0");
					//hemtItemMcGlobalVO.setStrSlNo("0");// Function Genrated
					hemtItemMcGlobalVO.setStrTermsCond(ItemMaintContractDtlsFB_p.getStrTermsAndCond());
				if(ItemMaintContractDtlsFB_p.getStrCheck().equals("1"))
				{
//				   System.out.println("Stock Info:::"+ItemMaintContractDtlsFB_p.getStrStockInf());
//                   System.out.println("Previous Cont Dtl PK::"+ItemMaintContractDtlsFB_p.getStrPKey());
//				   System.out.println("Item Sl No::::"+ItemMaintContractDtlsFB_p.getStrStockInf().split("\\^")[4]);
//                   System.out.println("Batch No::::"+ItemMaintContractDtlsFB_p.getStrStockInf().split("\\^")[3]);	
					hemtItemMcGlobalVO.setStrItemId(ItemMaintContractDtlsFB_p.getStrStockInfoVal().split("\\^")[1]);
					hemtItemMcGlobalVO.setStrItemNameId(ItemMaintContractDtlsFB_p.getStrStockInfoVal().split("\\^")[2]);	
				   hemtItemMcGlobalVO.setStrIsItem("1");
				   hemtItemMcGlobalVO.setStrItemSlNo(ItemMaintContractDtlsFB_p.getStrStockInfoVal().split("\\^")[4]);
				   hemtItemMcGlobalVO.setStrBatchNo(ItemMaintContractDtlsFB_p.getStrStockInfoVal().split("\\^")[3]);
				}
				else
				{
				   hemtItemMcGlobalVO.setStrIsItem("0");	
				   hemtItemMcGlobalVO.setStrItemSlNo("0");
				   hemtItemMcGlobalVO.setStrBatchNo("0");
				}	
					hemtItemMcGlobalVO.setStrRoutineVisit(ItemMaintContractDtlsFB_p.getStrRoutineFrequency());
					hemtItemMcGlobalVO.setStrMcName(ItemMaintContractDtlsFB_p.getStrMaintenanceContractName());
					//hemtItemMcGlobalVO.setStrBreakVisit("0");
					hemtItemMcGlobalVO.setStrManufactId(ItemMaintContractDtlsFB_p.getStrMantContractSuppId());
					hemtItemMcGlobalVO.setStrResponseTime(ItemMaintContractDtlsFB_p.getStrResponseTime());
					hemtItemMcGlobalVO.setStrCost(ItemMaintContractDtlsFB_p.getStrMaintenanceCost());
					hemtItemMcGlobalVO.setStrPenaltyCond(ItemMaintContractDtlsFB_p.getStrPeneltyCond());
					//hemtItemMcGlobalVO.setStrEntryDate("");  // SYSDATE
					hemtItemMcGlobalVO.setStrIsValid("1");
					hemtItemMcGlobalVO.setStrSeatId(strSeatId_p);
					hemtItemMcGlobalVO.setStrHospCode(strHospitalCode_p);
					hemtItemMcGlobalVO.setStrRoutineFreq(ItemMaintContractDtlsFB_p.getStrRoutineFrequency());
					hemtItemMcGlobalVO.setStrFrqUnit(ItemMaintContractDtlsFB_p.getStrRoutineUnitId());
					hemtItemMcGlobalVO.setStrResponseTimeUnit(ItemMaintContractDtlsFB_p.getStrResponseTimeUnitId());
					hemtItemMcGlobalVO.setStrStarDate(ItemMaintContractDtlsFB_p.getStrFromDate());
					hemtItemMcGlobalVO.setStrEndDate(ItemMaintContractDtlsFB_p.getStrToDate());
					hemtItemMcGlobalVO.setStrTenderNo(ItemMaintContractDtlsFB_p.getStrTenderNo());
					hemtItemMcGlobalVO.setStrUploadNo("");  
					hemtItemMcGlobalVO.setStrDocRefNo(strFileId);
					hemtItemMcGlobalVO.setStrTenderDate(ItemMaintContractDtlsFB_p.getStrTenderDate());
					hemtItemMcGlobalVO.setStrDocrefDate(ItemMaintContractDtlsFB_p.getStrDocRefDate()); //SYSDATE
					hemtItemMcGlobalVO.setStrOrederNo(ItemMaintContractDtlsFB_p.getStrOrderNo());
					hemtItemMcGlobalVO.setStrCancelSeatId(""); // Seat Id
					hemtItemMcGlobalVO.setStrOrderDate(ItemMaintContractDtlsFB_p.getStrOrderDate());
					hemtItemMcGlobalVO.setStrFinancialStartYear("");
					hemtItemMcGlobalVO.setStrEffFrom("");   // SYSDATE
					hemtItemMcGlobalVO.setStrRemarks(ItemMaintContractDtlsFB_p.getStrRemarks());
					hemtItemMcGlobalVO.setStrFinancialEndYear("");
					hemtItemMcGlobalVO.setStrIsReNew("0");
					hemtItemMcGlobalVO.setStrCancelId("");  // Only in Case of Cancel
					hemtItemMcGlobalVO.setStrCancelDate("");// Only in Case of Cancel
					hemtItemMcGlobalVO.setStrCancelRemarks("");// Only in Case of Cancel

					hemtItemMcGlobalVO.setStrDeptId(ItemMaintContractDtlsFB_p.getStrDeptId());
					hemtItemMcGlobalVO.setStrStoreId(ItemMaintContractDtlsFB_p.getStrStoreId());
					hemtItemMcGlobalVO.setStrEnggItemTypeId(ItemMaintContractDtlsFB_p.getStrEnggItemTypeId());
					hemtItemMcGlobalVO.setStrEnggItemSubTypeId(ItemMaintContractDtlsFB_p.getStrEnggItemSubTypeId());
					hemtItemMcGlobalVO.setStrManufacturerName(ItemMaintContractDtlsFB_p.getStrManufacturerName());
	   		    	
			    // Calling BO Method Here
				   BmedTransBO.saveItemMaintenanceContactDetails(hemtItemMcGlobalVO);
				   if (hemtItemMcGlobalVO.getStrMsgType().equals("0")) 
				   {
					   ItemMaintContractDtlsFB_p.setStrNormalMsg("Data Saved Successfully");
				   }
	   		    }
			}
			catch (Exception e) 
			{
				
	 
				strErrMsg = "ItemMaintContractDtlsTransDATA.save() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
					strErrMsg);
			ItemMaintContractDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
			}
			
		}
	    /**
	     * this method is used Renew Data
	     * @param ItemMaintContractDtlsFB_p
	     * @param request_p
	     */
	    public static void saveRenewData(ItemMaintContractDtlsFB ItemMaintContractDtlsFB_p, HttpServletRequest request_p) 
	    {
			String strErrMsg;
			
			String strSeatId_p;
			String strHospitalCode_p;
			UserVO userVo = null;
			//AttachFileGlobal fs=null;
			HemtItemMcDtlVO hemtItemMcGlobalVO =null;
			String strFileId;
			String strFtpFolderName ="";
			BmedConfigUtil bmed =null;
			try
			{					     
				 hemtItemMcGlobalVO = new HemtItemMcDtlVO();
				 			   bmed = new BmedConfigUtil();
				             userVo = ControllerUTIL.getUserVO(request_p);
				  strHospitalCode_p = userVo.getHospitalCode();
				        strSeatId_p = userVo.getSeatId();
				
				
				 
				    FormFile myFile = ItemMaintContractDtlsFB_p.getStrLocation();
				    strFileId = BmedTransBO.getFileName(myFile,strHospitalCode_p,"1");
				    
				    /*  Testing data to Save record in FTP Server   */
		               strFtpFolderName = bmed.getStrFtpFileFolder("15",strHospitalCode_p);
					    
					   if(strFtpFolderName.equals("")||strFtpFolderName==null)
					   {
						    strFtpFolderName = "bmedDocs";
					   } 
				        String sessionFtpAddress="10.0.1.78/ftpserver"; //populate from session
				        sessionFtpAddress = EMMSStaticConfigurator.bmedpath;
						//String logicalName = sessionFtpAddress.replace('/', '#').split("#")[1];
						String Fileurl= "ftp://"+sessionFtpAddress+"/"+strFtpFolderName;			
						System.out.println("File Url:::::"+Fileurl);			
						String Ftpurl="ftp://"+sessionFtpAddress+"/"+strFtpFolderName;			
						System.out.println("Ftp Url::::"+Ftpurl);		
						String FileName = strFileId; //given by user at run time
						System.out.println("File Name::::"+strFileId);		
						SaveFileInFtpGlobal.saveFileToLocation(Fileurl, Ftpurl, FileName,myFile.getInputStream());
						System.out.println("file Created Successfully");
						
						/* End Test Data */
//			     System.out.println("File ID::::"+strFileId);
			     //fs.saveFile(myFile.getFileData(),strFileId); 
			     
			     ItemMaintContractDtlsFB_p.setStrUploadFileId(strFileId);
			     hemtItemMcGlobalVO.setStrSeatId(strSeatId_p);
				 hemtItemMcGlobalVO.setStrHospCode(strHospitalCode_p);
				 
			      				      
			     
				/*
				private String strPKey;   // HEMNUM_ITEM_ID||'^'|| HEMSTR_BATCH_NO||'^'||HEMNUM_ITEM_SL_NO||'^'|| HEMNUM_SL_NO||'^'||GNUM_HOSPITAL_CODE
				private String strStockInf; //HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
				                            //||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
			                                //GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE
				*/
			    //System.out.println("Upload File name::::"+myFile.getFileName());
				//System.out.println("Check Wheteher Item or Non-Item:::"+ItemMaintContractDtlsFB_p.getStrCheck());
			    //System.out.println("Doc ref Date::::"+ItemMaintContractDtlsFB_p.getStrDocRefDate());
			    //System.out.println("Doc Ref No::::"+ItemMaintContractDtlsFB_p.getStrDocRefNo());
				//System.out.println("Dept ID::"+ItemMaintContractDtlsFB_p.getStrDeptId());
				//System.out.println("Store ID::"+ItemMaintContractDtlsFB_p.getStrStoreId());
				//System.out.println("Engg Item Type::"+ItemMaintContractDtlsFB_p.getStrEnggItemTypeId());
				//System.out.println("Engg Item Sub Type::"+ItemMaintContractDtlsFB_p.getStrEnggItemSubTypeId());
				//System.out.println("Item Brand Id::"+ItemMaintContractDtlsFB_p.getStrItemBrandId());
				//System.out.println("Contr Supp Id::"+ItemMaintContractDtlsFB_p.getStrMantContractSuppId());
				//System.out.println("Contract Type ID::"+ItemMaintContractDtlsFB_p.getStrMantContractTypeId());
				//System.out.println("Main Contract Name::"+ItemMaintContractDtlsFB_p.getStrMaintenanceContractName());
//				System.out.println("Tender No::"+ItemMaintContractDtlsFB_p.getStrTenderNo());
//				System.out.println("Tender Date::"+ItemMaintContractDtlsFB_p.getStrTenderDate());
//				System.out.println("Order No::"+ItemMaintContractDtlsFB_p.getStrOrderNo());
//				System.out.println("Order Date::"+ItemMaintContractDtlsFB_p.getStrOrderDate());
//				System.out.println("From Date:::"+ItemMaintContractDtlsFB_p.getStrFromDate());
//				System.out.println("To Date::"+ItemMaintContractDtlsFB_p.getStrToDate());
//				System.out.println("Rotine Frequency::"+ItemMaintContractDtlsFB_p.getStrRoutineFrequency());
//  			System.out.println("Routin Unit:::"+ItemMaintContractDtlsFB_p.getStrRenewRoutineUnitId());
// 				System.out.println("Response Time:::"+ItemMaintContractDtlsFB_p.getStrResponseTime());
//				System.out.println("Response Unit::"+ItemMaintContractDtlsFB_p.getStrRenewResponseTimeUnitId());
//				System.out.println("Cost:::"+ItemMaintContractDtlsFB_p.getStrMaintenanceCost());
//				System.out.println("remarks:::"+ItemMaintContractDtlsFB_p.getStrRemarks());
//				System.out.println("term  Cod:::"+ItemMaintContractDtlsFB_p.getStrTermsAndCond());
//				System.out.println("Penlty Cond:::"+ItemMaintContractDtlsFB_p.getStrPeneltyCond());
//                System.out.println("Primary Key::for Renew:::::"+ItemMaintContractDtlsFB_p.getStrIsReNew());
                
                
                    hemtItemMcGlobalVO.setStrMode("3");
				    hemtItemMcGlobalVO.setStrMcType(ItemMaintContractDtlsFB_p.getStrMantContractTypeId());
				    hemtItemMcGlobalVO.setStrManufctSlNo("0");
				    hemtItemMcGlobalVO.setStrSlNo("0");// Function Genrated
				    hemtItemMcGlobalVO.setStrTermsCond(ItemMaintContractDtlsFB_p.getStrTermsAndCond());
                    hemtItemMcGlobalVO.setStrIsItem("1");
				    hemtItemMcGlobalVO.setStrSlNo(ItemMaintContractDtlsFB_p.getStrPKey().split("\\^")[3]);
				    hemtItemMcGlobalVO.setStrItemSlNo(ItemMaintContractDtlsFB_p.getStrPKey().split("\\^")[2]);
				    hemtItemMcGlobalVO.setStrBatchNo(ItemMaintContractDtlsFB_p.getStrPKey().split("\\^")[1]);
				    hemtItemMcGlobalVO.setStrItemId(ItemMaintContractDtlsFB_p.getStrPKey().split("\\^")[0]);
				    hemtItemMcGlobalVO.setStrRoutineVisit(ItemMaintContractDtlsFB_p.getStrRoutineFrequency());
				    hemtItemMcGlobalVO.setStrMcName("--");
				    hemtItemMcGlobalVO.setStrBreakVisit("0");
					hemtItemMcGlobalVO.setStrManufactId(ItemMaintContractDtlsFB_p.getStrMantContractSuppId());
					hemtItemMcGlobalVO.setStrResponseTime(ItemMaintContractDtlsFB_p.getStrResponseTime());
					hemtItemMcGlobalVO.setStrCost(ItemMaintContractDtlsFB_p.getStrMaintenanceCost());
					hemtItemMcGlobalVO.setStrPenaltyCond(ItemMaintContractDtlsFB_p.getStrPeneltyCond());
				    hemtItemMcGlobalVO.setStrEntryDate("");  // SYSDATE
				    hemtItemMcGlobalVO.setStrIsValid("1");
				    hemtItemMcGlobalVO.setStrSeatId(strSeatId_p);
					hemtItemMcGlobalVO.setStrHospCode(strHospitalCode_p);
					hemtItemMcGlobalVO.setStrRoutineFreq(ItemMaintContractDtlsFB_p.getStrRoutineFrequency());
					hemtItemMcGlobalVO.setStrFrqUnit(ItemMaintContractDtlsFB_p.getStrRenewRoutineUnitId());
					hemtItemMcGlobalVO.setStrResponseTimeUnit(ItemMaintContractDtlsFB_p.getStrRenewResponseTimeUnitId());
					hemtItemMcGlobalVO.setStrStarDate(ItemMaintContractDtlsFB_p.getStrFromDate());
				    hemtItemMcGlobalVO.setStrEndDate(ItemMaintContractDtlsFB_p.getStrToDate());
				    hemtItemMcGlobalVO.setStrTenderNo(ItemMaintContractDtlsFB_p.getStrTenderNo());
				    hemtItemMcGlobalVO.setStrUploadNo(ItemMaintContractDtlsFB_p.getStrDocRefNo());  
					hemtItemMcGlobalVO.setStrDocRefNo(strFileId);
					hemtItemMcGlobalVO.setStrTenderDate(ItemMaintContractDtlsFB_p.getStrTenderDate());
					hemtItemMcGlobalVO.setStrDocrefDate(ItemMaintContractDtlsFB_p.getStrDocRefDate()); //SYSDATE
					hemtItemMcGlobalVO.setStrOrederNo(ItemMaintContractDtlsFB_p.getStrOrderNo());
					hemtItemMcGlobalVO.setStrCancelSeatId(""); // Seat Id
				    hemtItemMcGlobalVO.setStrOrderDate(ItemMaintContractDtlsFB_p.getStrOrderDate());
				    hemtItemMcGlobalVO.setStrFinancialStartYear("");
					hemtItemMcGlobalVO.setStrEffFrom("");   // SYSDATE
					hemtItemMcGlobalVO.setStrRemarks(ItemMaintContractDtlsFB_p.getStrRemarks());
					hemtItemMcGlobalVO.setStrFinancialEndYear("");
					hemtItemMcGlobalVO.setStrIsReNew("0");
				    hemtItemMcGlobalVO.setStrCancelId("");  // Only in Case of Cancel
				    hemtItemMcGlobalVO.setStrCancelDate("");// Only in Case of Cancel
				    hemtItemMcGlobalVO.setStrCancelRemarks("");// Only in Case of Cancel
				
			    // Calling BO method here
				   BmedTransBO.saveItemMaintenanceContactDetails(hemtItemMcGlobalVO);
				   
				   if (hemtItemMcGlobalVO.getStrMsgType().equals("0")) 
				   {
					   ItemMaintContractDtlsFB_p.setStrNormalMsg("Data Saved Successfully");
				   }
				
			}
			catch (Exception e) 
			{
			//	e.printStackTrace();
	 
				strErrMsg = "ItemMaintContractDtlsTransDATA.initializeMain() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
					strErrMsg);
			ItemMaintContractDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
			}
			
		}
	    /**
		 * This Method is Used to Cancel Data from table hemt_item_mc_dtl
		 * @param ItemMaintContractDtlsFB_p
		 * @param request_p
		 * @param response_p
		 */
	    
	    public static void cancelData(ItemMaintContractDtlsFB ItemMaintContractDtlsFB_p, HttpServletRequest request_p ,HttpServletResponse response_p) 
	    {
			String strErrMsg;
			
			String strSeatId_p;
			String strHospitalCode_p;
			UserVO userVo = null;
			HisUtil hisutil = null;
			
			HemtItemMcDtlVO hemtItemMcGlobalVO =null;
			String strCurrentDate;
			
			try
			{					     
				           hisutil = new HisUtil("","");
				hemtItemMcGlobalVO = new HemtItemMcDtlVO();
				            userVo = ControllerUTIL.getUserVO(request_p);
				 strHospitalCode_p = userVo.getHospitalCode();
				       strSeatId_p = userVo.getSeatId();
				       strCurrentDate = hisutil.getDSDate("DD-Mon-YYYY");
				
				 hemtItemMcGlobalVO.setStrSeatId(strSeatId_p);
				 hemtItemMcGlobalVO.setStrHospCode(strHospitalCode_p);
				 										 
				 ItemMaintContractDtlsFB_p.setStrStockInf((String)request_p.getParameter("stockInfo"));
				 hemtItemMcGlobalVO.setStrCancelId((String)request_p.getParameter("cancelType"));
				 hemtItemMcGlobalVO.setStrCancelRemarks((String)request_p.getParameter("cancelRemarks"));
				 hemtItemMcGlobalVO.setStrCancelSeatId(strSeatId_p); // Seat Id
				 hemtItemMcGlobalVO.setStrItemId((String)request_p.getParameter("stockInfo").split("\\^")[2]);
				 hemtItemMcGlobalVO.setStrBatchNo((String)request_p.getParameter("stockInfo").split("\\^")[3]);
				 hemtItemMcGlobalVO.setStrItemSlNo((String)request_p.getParameter("stockInfo").split("\\^")[4]);
				 hemtItemMcGlobalVO.setStrSlNo((String)request_p.getParameter("slNo"));
				 hemtItemMcGlobalVO.setStrCancelId((String)request_p.getParameter("cancelType"));  
				 hemtItemMcGlobalVO.setStrCancelDate(strCurrentDate);   // Sysdate
				 hemtItemMcGlobalVO.setStrCancelRemarks((String)request_p.getParameter("cancelRemarks"));  
				/*
				private String strStockInf; //HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
				                            //||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
			                                //GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE
				*/
						    
                hemtItemMcGlobalVO.setStrMode("2");
				hemtItemMcGlobalVO.setStrMcType("0");
				hemtItemMcGlobalVO.setStrManufctSlNo("0");
				hemtItemMcGlobalVO.setStrRoutineVisit("0");
				
				hemtItemMcGlobalVO.setStrMcName("0");
				hemtItemMcGlobalVO.setStrBreakVisit("0");
				hemtItemMcGlobalVO.setStrManufactId("0");
				hemtItemMcGlobalVO.setStrPersonalTime("0");
				
				hemtItemMcGlobalVO.setStrCost("0");
				hemtItemMcGlobalVO.setStrPenaltyCond("0");
				hemtItemMcGlobalVO.setStrEntryDate(strCurrentDate);  // SYSDATE
				hemtItemMcGlobalVO.setStrIsValid("0");
				
				hemtItemMcGlobalVO.setStrRoutineFreq("0");
				hemtItemMcGlobalVO.setStrFrqUnit("0");
				hemtItemMcGlobalVO.setStrResponseTimeUnit("0");
				hemtItemMcGlobalVO.setStrStarDate(strCurrentDate);
				
				hemtItemMcGlobalVO.setStrEndDate(strCurrentDate);
				hemtItemMcGlobalVO.setStrTenderNo("0");
				hemtItemMcGlobalVO.setStrUploadNo("0"); 
				hemtItemMcGlobalVO.setStrDocRefNo("0");
				
				hemtItemMcGlobalVO.setStrTenderDate(strCurrentDate);
				hemtItemMcGlobalVO.setStrDocrefDate(strCurrentDate);
				hemtItemMcGlobalVO.setStrOrederNo("0");
				hemtItemMcGlobalVO.setStrOrderDate(strCurrentDate);
				
				hemtItemMcGlobalVO.setStrFinancialStartYear(strCurrentDate);
				hemtItemMcGlobalVO.setStrEffFrom(strCurrentDate);
				hemtItemMcGlobalVO.setStrRemarks("--");
				hemtItemMcGlobalVO.setStrFinancialEndYear(strCurrentDate);
				
				hemtItemMcGlobalVO.setStrIsReNew("0");
				hemtItemMcGlobalVO.setStrTermsCond("--");
				
				
			    // Callling BO method here
				BmedTransBO.saveItemMaintenanceContactDetails(hemtItemMcGlobalVO);
				
				
				try 
				{									
					response_p.setHeader("Cache-Control", "no-cache");
					response_p.getWriter().print("1");
						
				}
				catch(Exception e)
				{
					//	e.printStackTrace();
				}
				
			}
			catch (Exception e) 
			{
				//e.printStackTrace();
	 
				strErrMsg = "ItemMaintContractDtlsTransDATA.initializeMain() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
					strErrMsg);
			ItemMaintContractDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
			}
			
		}
	    /**
	     * This method is used for  'RENEW' selected combo
	     * @param ItemMaintContractDtlsFB_p
	     * @param request_p
	     * @param response_p
	     */
	    public static void getRenewSelectedCombo(ItemMaintContractDtlsFB ItemMaintContractDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) 
	    {
			String strErrMsg;
			BmedTransBO bmedTransBO=null;
			//String strRenewPageSrc=null;
			String strHospitalCode_p=null;
			UserVO userVo = null;
			String strInfoVal_p;
			String strInfo_p;
			String strMode_p;
			
			try
			{
				bmedTransBO= new BmedTransBO();
				/*
				 * User Value
				 */
				userVo = ControllerUTIL.getUserVO(request_p);
				strHospitalCode_p=userVo.getHospitalCode();
				strInfoVal_p = (String) request_p.getParameter("info");
				strMode_p    = (String) request_p.getParameter("mode");
				//Value Pass in This format
				//department+"##"+storeName+"##"+enggItemTyp+"##"+enggItemSubTyp+"##"+iteBrandId+"##"+ItemSlNo+"##"+ManufactNo+"##"+stockInfo+"##"+MantContSupp+"##"+MantContType;
				//HSTNUM_STORE_ID^HSTNUM_ITEM_ID^HSTNUM_ITEMBRAND_ID^HSTSTR_BATCH_NO^HSTSTR_ITEM_SL_NO^GNUM_HOSPITAL_CODE^HSTNUM_STOCK_STATUS_CODE
								
				strInfo_p = bmedTransBO.getRenewPageSelectedComboOne(strHospitalCode_p, strInfoVal_p);
				strMode_p = bmedTransBO.getRenewPageSelectedComboTwo(strHospitalCode_p, strInfoVal_p);	
												
				try 
				{									
					response_p.setHeader("Cache-Control", "no-cache");
					response_p.getWriter().print(strInfo_p+"$$"+strMode_p);
						
				}
				catch(Exception e)
				{
					//	e.printStackTrace();
				}
				

			} catch (Exception e) {
	 
				strErrMsg = "ItemMaintContractDtlsTransDATA.getEnggItemSubType() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
					strErrMsg);
			ItemMaintContractDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
			}
			
		}
	    
	    /**
	     * This method is used for  'RENEW' + 'CANCEL' + 'VIEW ' Pop-Up
	     * @param ItemMaintContractDtlsFB_p
	     * @param request_p
	     * @param response_p
	     */
	    public static void getCommanAction(ItemMaintContractDtlsFB ItemMaintContractDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) 
	    {
			String strErrMsg;
			BmedTransBO bmedTransBO=null;
			String strRenewPageSrc=null;
			String strHospitalCode_p=null;
			UserVO userVo = null;
			String strInfoVal_p;
			String strInfo_p;
			String strMode_p;
			
			try
			{
				bmedTransBO= new BmedTransBO();
				/*
				 * User Value
				 */
				userVo = ControllerUTIL.getUserVO(request_p);
				strHospitalCode_p=userVo.getHospitalCode();
				strInfoVal_p = (String) request_p.getParameter("info");
				strMode_p    = (String) request_p.getParameter("mode");
				String isItem = (String) request_p.getParameter("isItem");
				//Value Pass in This format
				//department+"##"+storeName+"##"+enggItemTyp+"##"+enggItemSubTyp+"##"+iteBrandId+"##"+ItemSlNo+"##"+ManufactNo+"##"+stockInfo+"##"+MantContSupp+"##"+MantContType;
				//HSTNUM_STORE_ID^HSTNUM_ITEM_ID^HSTNUM_ITEMBRAND_ID^HSTSTR_BATCH_NO^HSTSTR_ITEM_SL_NO^GNUM_HOSPITAL_CODE^HSTNUM_STOCK_STATUS_CODE
				
				if(strMode_p.equals("1"))
				{	
				   strInfo_p = bmedTransBO.getRenewPageSrc(strHospitalCode_p, strInfoVal_p);
				   strRenewPageSrc  = getItemContCommanHlp(strInfoVal_p,strInfo_p,strMode_p,isItem);
				}
				if(strMode_p.equals("2")) // For Cancel
				{
					strInfo_p = bmedTransBO.getCancelPageSrc(strHospitalCode_p, strInfoVal_p);
					strRenewPageSrc  = getItemContCommanHlp(strInfoVal_p,strInfo_p,strMode_p,isItem);
				}
				if(strMode_p.equals("3")) // For View
				{
					strInfo_p = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
					strRenewPageSrc  = getItemContCommanHlp(strInfoVal_p,strInfo_p,strMode_p,isItem);
					
				}
									
				try 
				{									
					response_p.setHeader("Cache-Control", "no-cache");
					response_p.getWriter().print(strRenewPageSrc);
						
				}
				catch(Exception e)
				{
				//		e.printStackTrace();
				}
				

			} catch (Exception e) {
	 
				strErrMsg = "ItemMaintContractDtlsTransDATA.getEnggItemSubType() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
					strErrMsg);
			ItemMaintContractDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
			}
			
		}
	
	    
	    /**
	     * Method is Used as HLP to Open Pop-Up for 'CANCEL' and 'VIEW' 
	     * @param readOnlyInfo_p
	     * @param strInfo_p
	     * @param strMode_p
	     * @return
	     */
		public static String getItemContCommanHlp(String readOnlyInfo_p,String strInfo_p,String strMode_p,String isItem) 
		{
			StringBuffer br = new StringBuffer();
		//	String strRoutineVisit="";
	//		String strBreakVisit="";
			String strResponseTime="";
			String strRoutineFreq="";
			String strFreqUnit="";
			String strRresUnit="";
			String strTenderNo="";
			String strUploadNo="";
			String strDocRefNo="";
			String strTenderDate="";
			//String strDocRefDate ="";
			String strOrderNo ="";
			String strOrderDate="";
			String strRemarks="";
			//String strIsRenew="";
			String strPenalty="";
			String strTermsCond="";
			try 
			{
				//Value Pass in This format
				//department+"##"+storeName+"##"+enggItemTyp+"##"+enggItemSubTyp+"##"+iteBrandId+"##"+ItemSlNo+"##"+ManufactNo+"##"+stockInfo+"##"+MantContSupp+"##"+MantContType;
				//HSTNUM_STORE_ID^HSTNUM_ITEM_ID^HSTNUM_ITEMBRAND_ID^HSTSTR_BATCH_NO^HSTSTR_ITEM_SL_NO^GNUM_HOSPITAL_CODE^HSTNUM_STOCK_STATUS_CODE
				String strDept              =  readOnlyInfo_p.split("\\@")[0];
				String strStore             =  readOnlyInfo_p.split("\\@")[1];
				String strEnggItemTyp       =  readOnlyInfo_p.split("\\@")[2];
				String strEnggItemSubTyp    =  readOnlyInfo_p.split("\\@")[3];
				String strIteBrandId        =  readOnlyInfo_p.split("\\@")[4];
				String strItemSlNo          =  readOnlyInfo_p.split("\\@")[5];
				String strManufactNo        =  readOnlyInfo_p.split("\\@")[6];
				String strStockInfo         =  readOnlyInfo_p.split("\\@")[7]; // Contain Primary Key
				String strMantContSupplier  =  readOnlyInfo_p.split("\\@")[8];
				String strMantContType      =  readOnlyInfo_p.split("\\@")[9];
				String strStartDate         =  readOnlyInfo_p.split("\\@")[10];
				String strEndDate           =  readOnlyInfo_p.split("\\@")[11];
				String strCost              =  readOnlyInfo_p.split("\\@")[12];
				if(strMode_p.equals("3"))
				{
					String strRemainInfo    =  readOnlyInfo_p.split("\\@")[13];
					// strRoutineVisit  =  strRemainInfo.split("\\^")[0];
					// strBreakVisit  =  strRemainInfo.split("\\^")[1];
					 strResponseTime  =  strRemainInfo.split("\\^")[2];
					 strRoutineFreq  =  strRemainInfo.split("\\^")[3];
					 strFreqUnit  =  strRemainInfo.split("\\^")[4];
					 strRresUnit  =  strRemainInfo.split("\\^")[5];
					 strTenderNo  =  strRemainInfo.split("\\^")[6];
					 strUploadNo  =  strRemainInfo.split("\\^")[7];
					 strDocRefNo  =  strRemainInfo.split("\\^")[8];
					 strTenderDate  =  strRemainInfo.split("\\^")[9];
					 //strDocRefDate  =  strRemainInfo.split("\\^")[10];
					 strOrderNo  =  strRemainInfo.split("\\^")[11];
					 strOrderDate  =  strRemainInfo.split("\\^")[12];
					 //strIsRenew  =  strRemainInfo.split("\\^")[14];
					 strTermsCond = strRemainInfo.split("\\^")[15];
					 strPenalty   =strRemainInfo.split("\\^")[16];
					   /*
					   16. HEMNUM_ROUTINE_VISIT
					   17. HEMNUM_BREAK_VISIT
			           18. HEMSTR_RESPONSE_TIME 
			           19. HEMNUM_ROUTINE_FREQ
			           20. HEMSTR_FREQ_UNIT  
			           21. HEMSTR_RES_TIME_UNIT 
			           22. HEMSTR_TENDER_NO 
			           23. HPURNUM_UPLOAD_NO 
			           24. HPURSTR_DOC_REF_NO  
			           25. HEMDT_TENDER_DATE,
			           26. HPURDT_DOC_REF_DATE  
			           27. HEMSTR_ORDER_NO  
			           28. HEMDT_ORDER_DATE 
			           29. GSTR_REMARKS 
			           30. HEMNUM_IS_RENEWED
			           31. Terms & Cond
			           32. Penalty & Cond
			           */
				}
				
				String strBatchNo           =  strStockInfo.split("\\^")[3];
				//String strItemSlNum         =  strStockInfo.split("\\^")[4];
				
				br.append("<table class='TABLE_STYLE'>");
				br.append("<tr class='FOOTER_TR'>");
				if(strMode_p.equals("1"))
				{
			       br.append("<td colspan='5' style='width: 100%'><div id='Id1' style='color:blue;text-align:left'>Item Maintenance Contract [RENEW :::: "+strMantContType+" / "+strCost+" ] </div></td>");
				}
				if(strMode_p.equals("2")) // For Cancel
				{
			       br.append("<td colspan='5' style='width: 100%'><div id='Id1' style='color:blue;text-align:left'>Item Maintenance Contract [CANCEL :::: "+strMantContType+" / "+strCost+" ] </div></td>");
				}
				if(strMode_p.equals("3")) // For View
				{
			       br.append("<td colspan='5' style='width: 100%'><div id='Id1' style='color:blue;text-align:left'>Item Maintenance Contract [VIEW :::: "+strMantContType+" / "+strCost+" ] </div></td>");
				}
			    br.append("</tr>");
			    if(isItem.equals("1")){
					br.append("<tr>");
					br.append("<td width='25%' class='LABEL_TD'>Department Name</td>");
					br.append("<td width='25%' class='CONTROL_TD'>"+strDept+"</td>");
					br.append("<td width='25%' class='LABEL_TD'>Store Name</td>");
					br.append("<td width='25%' class='CONTROL_TD'>"+strStore+"</td>");
					br.append("</tr>");
			    }
                br.append("<tr>");
				br.append("<td colspan='1' class='LABEL_TD'>Engineering Item Type</td>");
				br.append("<td colspan='1' class='CONTROL_TD'>"+strEnggItemTyp+"</td>");						
				br.append("<td colspan='1' class='LABEL_TD'>Engineering Item Sub-Type</td>");
				br.append("<td colspan='1' class='CONTROL_TD'>"+strEnggItemSubTyp+"</td>");
				br.append("</tr>");
				br.append("<tr>");
				br.append("<td colspan='1' class='LABEL_TD'>Item Name</td>");
				br.append("<td colspan='1' class='CONTROL_TD'>"+strIteBrandId+"</td>");
				if(isItem.equals("1")){
					br.append("	<td colspan='1' class='LABEL_TD'>Item Batch No</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strBatchNo+"</td>");
				}
				else
				{
					br.append("	<td colspan='1' class='LABEL_TD'></td>");
					br.append("<td colspan='1' class='CONTROL_TD'></td>");
				}
				br.append("</tr>");
				if(isItem.equals("1")){
					br.append("<tr>");
					br.append("<td colspan='1' class='LABEL_TD'>Item Serial No</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strItemSlNo+"</td>");
					br.append("<td colspan='1' class='LABEL_TD'>Manufactring Serial No</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strManufactNo+"</td>");
					br.append("</tr>");
				}
				br.append("<tr>");
				br.append("<td colspan='1' class='LABEL_TD'>Maintenance Contract Supplier</td>");
				br.append("<td colspan='1' class='CONTROL_TD'>"+strMantContSupplier+"</td>");
				br.append("<td colspan='1' class='LABEL_TD'>Maintenance Contract Type</td>");
				br.append("<td colspan='1' class='CONTROL_TD'>"+strMantContType+"</td>");
				br.append("</tr>");
				if(strMode_p.equals("1"))
				{
						String strMaintContTypeCmb  =  strInfo_p.split("\\@@")[0];
						String strUnitCmb           =  strInfo_p.split("\\@@")[1];
						br.append("<tr>");
						br.append("<td colspan='1' class='LABEL_TD'>Maintenance Contract Type</td>");
						br.append("<td colspan='1' class='CONTROL_TD'><select name='strRenewMantContractTypeId' class='COMBO_NORMAL'>"+strMaintContTypeCmb+"</select></td>");
						br.append("<td colspan='1' class='CONTROL_TD'></td>");
						br.append("<td colspan='1' class='CONTROL_TD'></td>");
						br.append("</tr>");
						br.append("<tr>");
						br.append("<td colspan='1' class='LABEL_TD'>Tender No</td>");
						br.append("<td colspan='1' class='CONTROL_TD'><input type='text' class='TEXT_FIELD_MAX' name='strRenewTenderNo' maxlength='15' value='' onkeypress='return validateData(event,5);'></td>");
						br.append("<td colspan='1' class='LABEL_TD'>Tender Date</td>");
						br.append("<td colspan='1' class='CONTROL_TD'><dateTag:date	name='strRenewTenderDate' value=''></dateTag:date></td>");
						br.append("</tr>");
						br.append("<tr>");
						br.append("<td colspan='1' class='LABEL_TD'><font color='red'>*</font>Order No</td>");
						br.append("<td colspan='1' class='CONTROL_TD'><input type='text' class='TEXT_FIELD_MAX' name='strRenewOrderNo' maxlength='15' value='' onkeypress='return validateData(event,5);'></td>");
						br.append("<td colspan='1' class='LABEL_TD'>Order Date</td>");
						br.append("<td colspan='1' class='CONTROL_TD'><dateTag:date 	name='strRenewOrderDate' 	value=''></dateTag:date></td>");
						br.append("</tr>");
						br.append("<tr>");
						br.append("<td colspan='1' class='LABEL_TD'><font color='red'>*</font>From Date</td>");
						br.append("<td colspan='1' class='CONTROL_TD'><dateTag:date	name='strRenewFromDate'	value=''></dateTag:date></td>");
						br.append("<td colspan='1' class='LABEL_TD'>To Date</td>");
						br.append("<td colspan='1' class='CONTROL_TD'><dateTag:date	name='strRenewToDate' value=''></dateTag:date>");
						br.append("</td>");
						br.append("</tr>");
						br.append("<tr>");
						br.append("<td colspan='1' class='LABEL_TD'><font color='red'>*</font>Routine Frequency</td>");
						br.append("<td colspan='1' class='CONTROL_TD'><input type='text' class='TEXT_FIELD_SMALL' name='strRenewRoutineFrequency' maxlength='3' value='' onkeypress='return validateData(event,5);'></td>");
						br.append("<td colspan='1' class='LABEL_TD'><font color='red'>*</font>UnitName</td>");
						br.append("<td colspan='1' class='CONTROL_TD'><select name='strRenewRoutineUnitId' class='COMBO_NORMAL'>"+strUnitCmb+"</select></td>");
					    br.append("</tr>");
						br.append("<tr>");
						br.append("<td colspan='1' class='LABEL_TD'><font color='red'>*</font>Response Time</td>");
						br.append("<td colspan='1' class='CONTROL_TD'><input type='text' class='TEXT_FIELD_SMALL' name='strRenewRenewResponseTime' maxlength='3' value='' onkeypress='return validateData(event,5);'></td>");
						br.append("<td colspan='1' class='LABEL_TD'><font color='red'>*</font>Unit Name</td>");
						br.append("<td colspan='1' class='CONTROL_TD'><select name='strRenewResponseTimeUnitId' class='COMBO_NORMAL'>"+strUnitCmb+"</select></td>");
						br.append("</tr>");
						br.append("<tr>");
						br.append("<td class='LABEL_TD' width='25%'>");
						br.append("<div align='right'><font color='red'>*</font>Maintenance Cost</div>");
						br.append("</td>");
						br.append("<td class='CONTROL_TD'><input type='text' class='TEXT_FIELD_MAX'	name='strRenewMaintenanceCost' maxlength='15' value='' onkeypress='return validateData(event,5);'></td>");
						br.append("<td width='25%' class='LABEL_TD'>Remarks</td>");
						br.append("<td width='25%' class='CONTROL_TD'><textarea name='strRenewRemarks' cols='25' rows='2' onkeypress='return validateData(event,17);'></textarea></td>");
						br.append("</tr>");
	
						br.append("<tr>");
						br.append("<td width='25%' class='LABEL_TD'>Terms & Condition</td>");
						br.append("<td width='25%' class='CONTROL_TD'><textarea	name='strRenewTermsAndCond' cols='25' rows='2' onkeypress='return validateData(event,17);'></textarea></td>");
						br.append("<td width='25%' class='LABEL_TD'>Penalty Conditions</td>");
						br.append("<td width='25%' class='CONTROL_TD'><textarea	name='strRenewPeneltyCond' cols='25' rows='2' onkeypress='return validateData(event,17);'></textarea></td>");
						br.append("</tr>");
						br.append("</table>");
						br.append("<table class='TABLE_STYLE'>");
						br.append("<tr>");
						br.append("<td class='LABEL_TD' width='50%'>Ref. No.</td>");
						br.append("<td class='CONTROL_TD' width='50%'>");
						br.append("<input type='text' class='TEXT_FIELD_MAX' name='strRenewDocRefNo' onkeypress='return validateData(event,9);' maxlength='35'></td>");
						br.append("</tr>");
						br.append("</table>");
						br.append("<table class='TABLE_STYLE'>");
						br.append("<tr>");
						br.append("<td class='LABEL_TD' width='50%'>Upload Item Specefication</td>");
						br.append("<td class='CONTROL_TD' width='50%'><input type='file' id ='formFileMultiple#"+0+"' name='formFileMultiple["+2+"]'>");
						br.append("</td>");
						br.append("</tr>");
	
						br.append("</table>");
						br.append("<table class='TABLE_STYLE'>");
						br.append("<tr class='FOOTER_TR'>");
						br.append("<td><font size='2' color='red'>*</font> Mandatory Fields</td>");
						br.append("</tr>");
						br.append("</table>");
						br.append("</his:ContentTag>");
						br.append("<div align='center'>");
						br.append("<img style='cursor: pointer;' src='/HBIMS/hisglobal/images/btn-sv.png' onClick='validate2();' />");
						br.append("<img style='cursor: pointer;' src='/HBIMS/hisglobal/images/btn-ccl.png' onClick='hideInfoPopup();'>");
						br.append("</div>");
				}
				if(strMode_p.equals("2"))
				{
					String strSlNo              =  (readOnlyInfo_p.split("\\@")[13]).split("\\^")[17];
					br.append("<input type='hidden' name='strSlNo' id='strSlNo' value='"+strSlNo+"'>");
					//department+"@"+storeName+"@"+enggItemTyp+"@"+enggItemSubTyp+"@"+iteBrandId+"@"+ItemSlNo+"@"+ManufactNo+"@"+stockInfo+"@"+mantContSupplier+"@"+mantContType+"@"+mantStartDate+"@"+mantEndDate+"@"+mantCost+"@"+strReaminInfo;
					br.append("<input type='hidden' name='strCancelStockInfo' id='strCancelStockInfo' value='"+strStockInfo+"'>");
					br.append("<tr>");
					br.append("<td colspan='1' class='LABEL_TD'>Start Date</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strStartDate+"</td>");
					br.append("<td colspan='1' class='LABEL_TD'>End Date</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strEndDate+"</td>");
					br.append("</tr>");
					br.append("<tr>");
					br.append("<td colspan='1' class='LABEL_TD'>Cost</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strCost+"</td>");
					br.append("<td colspan='1' class='CONTROL_TD'></td>");
					br.append("<td colspan='1' class='CONTROL_TD'></td>");
					br.append("</tr>");
					br.append("<tr>");
					br.append("<td colspan='1' class='LABEL_TD'>Cancel Type</td>");
					br.append("<td colspan='1' class='CONTROL_TD'><select name='strCancelTypeId' class='COMBO_NORMAL'>"+strInfo_p+"</select></td>");
					br.append("<td colspan='1' class='LABEL_TD'>Remarks</td>");
					br.append("<td colspan='1' class='CONTROL_TD'><textarea	name='strCancelRemarks' cols='25' rows='2' ></textarea></td>");
					br.append("</tr>");
					br.append("</table>");
					br.append("<table class='TABLE_STYLE'>");
					br.append("<tr class='FOOTER_TR'>");
					br.append("<td><font size='2' color='red'>*</font> Mandatory Fields</td>");
					br.append("</tr>");
					br.append("</table>");
					br.append("<div align='center'>");
					br.append("<img style='cursor: pointer;' src='/HBIMS/hisglobal/images/btn-sv.png' onClick='SaveCancel();' />");
					br.append("<img style='cursor: pointer;' src='/HBIMS/hisglobal/images/btn-ccl.png' onClick='hideInfoPopup();'>");
					br.append("</div>");
					
					
				}
				
				if(strMode_p.equals("3"))
				{
					br.append("<tr>");
					br.append("<td colspan='1' class='LABEL_TD'>Tender No</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strTenderNo+"</td>");
					br.append("<td colspan='1' class='LABEL_TD'>Tender Date</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strTenderDate+"</td>");
					br.append("</tr>");
					br.append("<tr>");
					br.append("<td colspan='1' class='LABEL_TD'>Order No</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strOrderNo+"</td>");
					br.append("<td colspan='1' class='LABEL_TD'>Order Date</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strOrderDate+"</td>");
					br.append("</tr>");
					br.append("<tr>");
					br.append("<td colspan='1' class='LABEL_TD'>From Date</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strStartDate+"</td>");
					br.append("<td colspan='1' class='LABEL_TD'>To Date</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strEndDate+"");
					br.append("</td>");
					br.append("</tr>");
					br.append("<tr>");
					br.append("<td colspan='1' class='LABEL_TD'>Routine Frequency</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strRoutineFreq+"</td>");
					br.append("<td colspan='1' class='LABEL_TD'>UnitName</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strFreqUnit+"</td>");
				    br.append("</tr>");
					br.append("<tr>");
					br.append("<td colspan='1' class='LABEL_TD'>Response Time</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strResponseTime+"</td>");
					br.append("<td colspan='1' class='LABEL_TD'>Unit Name</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strRresUnit+"</td>");
					br.append("</tr>");
					br.append("<tr>");
					br.append("<td class='LABEL_TD' width='25%'>");
					br.append("<div align='right'>Maintenance Cost</div>");
					br.append("</td>");
					br.append("<td class='CONTROL_TD'>"+strCost+"</td>");
					br.append("<td width='25%' class='LABEL_TD'>Remarks</td>");
					br.append("<td width='25%' class='CONTROL_TD'><textarea name='strRenewRemarks' cols='25' rows='2' readOnly>"+strRemarks+"</textarea></td>");
					br.append("</tr>");

					br.append("<tr>");
					br.append("<td width='25%' class='LABEL_TD'>Terms & Condition</td>");
					br.append("<td width='25%' class='CONTROL_TD'><textarea	name='strRenewTermsAndCond' cols='25' rows='2' readOnly>"+strTermsCond+"</textarea></td>");
					br.append("<td width='25%' class='LABEL_TD'>Penalty Conditions</td>");
					br.append("<td width='25%' class='CONTROL_TD'><textarea	name='strRenewPeneltyCond' cols='25' rows='2' readOnly>"+strPenalty+"</textarea></td>");
					br.append("</tr>");
					br.append("</table>");
					br.append("<table class='TABLE_STYLE'>");
					br.append("<tr>");
					br.append("<td class='LABEL_TD' width='50%'>Ref. No.</td>");
					br.append("<td class='CONTROL_TD' width='50%'>"+strUploadNo+"");
					br.append("</td>");
					br.append("</tr>");
					br.append("</table>");
					br.append("<table class='TABLE_STYLE'>");
					br.append("<tr>");
					br.append("<td class='LABEL_TD' width='50%'>Upload Item Specefication</td>");
					br.append("<td class='CONTROL_TD' width='50%'>"+strUploadNo+"");
					br.append("</td>");
					br.append("</tr>");

					br.append("</table>");
					br.append("<table class='TABLE_STYLE'>");
					br.append("<tr class='FOOTER_TR'>");
					br.append("<td><font size='2' color='red'>*</font> Mandatory Fields</td>");
					br.append("</tr>");
					br.append("</table>");
					br.append("<div align='center'>");
					br.append("<img style='cursor: pointer;' src='/HBIMS/hisglobal/images/btn-ccl.png' onClick='hideInfoPopup();'>");
					br.append("</div>");
					
					
				}

				
			}
			catch (Exception e) 
			{
				

			}
			
			return br.toString();
		}


	    
	    
	    
	    

}