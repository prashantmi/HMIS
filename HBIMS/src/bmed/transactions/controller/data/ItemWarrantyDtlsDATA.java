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


import org.apache.struts.upload.FormFile;

import bmed.EMMSStaticConfigurator;
import bmed.SaveFileInFtpGlobal;
import bmed.global.controller.util.BmedConfigUtil;
import bmed.transactions.bo.BmedGlobalBO;
import bmed.transactions.bo.BmedTransBO;
import bmed.transactions.controller.fb.ItemWarrantyDtlsFB;
import bmed.vo.ItemBrandMstVO;
import bmed.vo.WarrantyDtlVO;

/* File Save End */
/**
 * @author   Amit kr
 * Creation Date:- 28-April-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project) Item Warranty Details Transactions
 * 
 */

public class ItemWarrantyDtlsDATA
{
	/**
	 * This method is used to initialize Main Page
	 * @param ItemWarrantyDtlsFB_p
	 * @param request_p
	 */
	
	public static void initializeMain(ItemWarrantyDtlsFB ItemWarrantyDtlsFB_p, HttpServletRequest request_p) 
	{
		String strErrMsg;
		BmedGlobalBO bmedGlobalBO=null;
		String strDepartmentComboOptions;
		String strEnggItemTypeComboOptions;
		String strUnitComboOptions;
		String strContMaintenanceTypeComboOptions;
		String strHospitalCode,strSeatId;
		UserVO userVo = null;
		HisUtil util = null;
		try
		{
							     bmedGlobalBO  = new BmedGlobalBO();
							            userVo = ControllerUTIL.getUserVO(request_p);
							 strHospitalCode = userVo.getHospitalCode();
							 strSeatId		 = userVo.getSeatId();
							 util = new HisUtil("BMED", "ItemWarrantyDtlsDATA");
					 strDepartmentComboOptions = bmedGlobalBO.getDepartmentComboOptions(strHospitalCode,strSeatId,2);
				   strEnggItemTypeComboOptions = bmedGlobalBO.getItemTypeComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
					       strUnitComboOptions = BmedGlobalBO.getUnitComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
					
			//Calling BO Method Here
			strContMaintenanceTypeComboOptions = bmedGlobalBO.getMaintenanceContTypeComboOptions(strHospitalCode);
			
			ItemWarrantyDtlsFB_p.setStrMantContractTypeCmb(strContMaintenanceTypeComboOptions);
			ItemWarrantyDtlsFB_p.setStrDeptNameCombo(strDepartmentComboOptions);
			ItemWarrantyDtlsFB_p.setStrEnggItemTypeCmb(strEnggItemTypeComboOptions);
			ItemWarrantyDtlsFB_p.setStrUnitIdCmb(strUnitComboOptions);
			ItemWarrantyDtlsFB_p.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			strErrMsg = "ItemWarrantyDtlsTransDATA.initializeMain() --> "+ e.getMessage();
		HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",strErrMsg);
		ItemWarrantyDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
	
		eObj = null;
		}
	
    }
    /**
     * This method is used to get Store Name
     * @param ItemWarrantyDtlsFB_p
     * @param request_p
     * @param response_p
     */

    public static void getStoreName(ItemWarrantyDtlsFB ItemWarrantyDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) 
    {
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
//					e.printStackTrace();
			}
	

		} 
	    catch (Exception e) 
	    {
	
			      strErrMsg = "ItemWarrantyDtlsTransDATA.getStoreName() --> "	+ e.getMessage();
		  HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",strErrMsg);
		  ItemWarrantyDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
		               eObj = null;
		}
		
	}

    /**
     * This method is used to get Engg Item Sub Type Combo
     * @param ItemWarrantyDtlsFB_p
     * @param request_p
     * @param response_p
     */
    public static void getEnggItemSubType(ItemWarrantyDtlsFB ItemWarrantyDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) 
    {
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
		//			e.printStackTrace();
			}
		 

			} catch (Exception e) {
		
				strErrMsg = "ItemWarrantyDtlsTransDATA.getEnggItemSubType() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",
					strErrMsg);
			ItemWarrantyDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");
		
			eObj = null;
			}
	
		}

    /**
     * Method used to get Item Name Combo
     * @param ItemWarrantyDtlsFB_p
     * @param request_p
     * @param response_p
     */

    public static void getItemName(ItemWarrantyDtlsFB ItemWarrantyDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) 
    {
		String strErrMsg;
		BmedGlobalBO bmedGlobalBO=null;
		ItemBrandMstVO vo = null;
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
//					e.printStackTrace();
			}
			
	
		} catch (Exception e) {
	
			strErrMsg = "ItemWarrantyDtlsTransDATA.getItemName() --> "
				+ e.getMessage();
		HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",
				strErrMsg);
		ItemWarrantyDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");
	
		eObj = null;
		}
	
   }
   /**
    * Method used to get Stock Details
    * @param ItemWarrantyDtlsFB_p
    * @param request_p
    * @param response_p
    */
  
    public static void getStockDetails(ItemWarrantyDtlsFB ItemWarrantyDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) 
    {
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
		//			e.printStackTrace();
			}
			
			
	
			} catch (Exception e) {
		
				strErrMsg = "ItemWarrantyDtlsTransDATA.getStockDetails() --> "
						+ e.getMessage();
				HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",
						strErrMsg);
				ItemWarrantyDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");
		
				eObj = null;
			}
				
	}
	/**
	 * Method is Used to get Previous Warranty Details
	 * @param ItemWarrantyDtlsFB_p
	 * @param request_p
	 * @param response_p
	 */
	public static void getWarrantyDetails(ItemWarrantyDtlsFB ItemWarrantyDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) {
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
			          strPrevMantDtl = bmedTransBO.getPrevWarrantyDetails(strHospitalCode_p,strStockInf_p,"2");
			try 
			{									
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(strPrevMantDtl);
					
			}
			catch(Exception e)
			{
//					e.printStackTrace();
			}
			
			} catch (Exception e) {
	 
				strErrMsg = "ItemWarrantyDtlsTransDATA.getWarrantyDetails() --> "
						+ e.getMessage();
				HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",
						strErrMsg);
				ItemWarrantyDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");
	
				eObj = null;
			}
	
        }
		/**
		 * Method is used to get Supplier Combo
		 * @param ItemWarrantyDtlsFB_p
		 * @param request_p
		 * @param response_p
		 */
	
	    public static void getSupplierCmb(ItemWarrantyDtlsFB ItemWarrantyDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) {
		String strErrMsg;
		BmedGlobalBO bmedGlobalBO=null;
		String strSupplierComboOptions;
		String strHospitalCode_p=null;
		UserVO userVo = null;
		String strItemBrandId_p;
		String strCheckIsItem_p;
		try
		{
				       bmedGlobalBO = new BmedGlobalBO();
				             userVo = ControllerUTIL.getUserVO(request_p);
				 // strHospitalCode_p = userVo.getHospitalCode();
				  strHospitalCode_p = Config.SUPER_USER_HOSPITAL_CODE;
				   strItemBrandId_p = (String) request_p.getParameter("itemId").split("\\^")[0];
				   strCheckIsItem_p = (String) request_p.getParameter("itemId").split("\\^")[1];
			// Calling BO method here
			strSupplierComboOptions = bmedGlobalBO.getSupplierComboOptions(strHospitalCode_p, strItemBrandId_p,strCheckIsItem_p);
			
			try 
			{									
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(strSupplierComboOptions);
					
			}
			catch(Exception e)
			{
		//			e.printStackTrace();
			}
			

		} catch (Exception e) {
 
			strErrMsg = "ItemWarrantyDtlsTransDATA.getSupplierCmb() --> "
				+ e.getMessage();
		HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",
				strErrMsg);
		ItemWarrantyDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");

		eObj = null;
		}
		
	}
	    
	    /**
	     * here we get Up-Load file
	     * @param ItemWarrantyDtlsFB_p
	     * @param request_p
	     * @param response_p
	     */
	    public static void getUploadedFile(ItemWarrantyDtlsFB ItemWarrantyDtlsFB_p,HttpServletRequest request_p, HttpServletResponse response_p) {
			
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
				
				
				strFileName = ItemWarrantyDtlsFB_p.getStrUploadFileId();
							
				//System.out.println("File Name::::::"+ItemWarrantyDtlsFB_p.getStrUploadFileId());
				String[] strTemp = strFileName.replace(".", "#").split("#");
				String strExt = strTemp[strTemp.length - 1];
				
				 if (strExt.equalsIgnoreCase("txt")
						|| strExt.equalsIgnoreCase("txt")) {
					
					response_p.setContentType("application/txt");
					response_p.setHeader("Content-disposition",
							" filename="+strFtpFolderName+""+strFileName);
					
				}
				 else if (strExt.equalsIgnoreCase("pdf")) 
				{
                    
					response_p.setContentType("application/pdf");
					response_p.setHeader("Content-disposition",	"attachment; filename="+strFtpFolderName+""+strFileName);

				} else if (strExt.equalsIgnoreCase("html")
						|| strExt.equalsIgnoreCase("htm")) {
					
					response_p.setContentType("text/html;charset=utf-8");
					response_p.setHeader("Content-disposition",
							"attachment; filename="+strFtpFolderName+""+strFileName);
					
				}else if (strExt.equalsIgnoreCase("xml")) {
					
					response_p.setContentType("application/xml");
					response_p.setHeader("Content-disposition",
							"attachment; filename="+strFtpFolderName+""+strFileName);
					
				} else if (strExt.equalsIgnoreCase("doc") || strExt.equalsIgnoreCase("docx")) {
					
					response_p.setContentType("application/msword");
					response_p.setHeader("Content-disposition",
							"attachment; filename="+strFtpFolderName+""+strFileName);
					
				} else if (strExt.equalsIgnoreCase("rdf")) {
					
					response_p.setContentType("application/msword");
					response_p.setHeader("Content-disposition",
							"attachment; filename="+strFtpFolderName+""+strFileName);
					
				} else if (strExt.equalsIgnoreCase("rtf")) {
					
					response_p.setContentType("application/msword");
					response_p.setHeader("Content-disposition",
							"attachment; filename="+strFtpFolderName+""+strFileName);
					
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
							"attachment; filename="+strFtpFolderName+""+strFileName);
					
				}
				/*******************************************************************/

				  String sessionFtpAddress=EMMSStaticConfigurator.bmedpath; //populate from session 10.0.5.152/ftpserver
				 // String logicalName = sessionFtpAddress.replace('/', '#').split("#")[1];
				  String Fileurl= "ftp://"+sessionFtpAddress+"/"+strFtpFolderName;			
				 
				  System.out.println("test:::"+Fileurl);
				 
				  URL                  urlftp = new URL(Fileurl+"/"+strFileName);
				  URLConnection          urlc =	urlftp.openConnection();
				  InputStream              io = urlc.getInputStream();
				  		  
				 
				        FileOutputStream fos = new FileOutputStream(strFileName);
				        byte[] buf = new byte[4096];
				        int read = 0;
				        while ((read = io.read(buf)) > 0) 
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
				strmsgText = "mms.master.PreTechApprovalTransDATA.getUploadedFile --> "
						+ e.getMessage();
				HisException eObj = new HisException("mms",
						"PreTechApprovalTransDATA->getUploadedFile()", strmsgText);
				ItemWarrantyDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
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
	     * @param ItemWarrantyDtlsFB_p
	     * @param request_p
	     */		    
	    public static void saveData(ItemWarrantyDtlsFB ItemWarrantyDtlsFB_p, HttpServletRequest request_p) 
	    {
			String strErrMsg;
			
			String strSeatId_p;
			String strHospitalCode_p;
			UserVO userVo = null;
			String strFileExt;
			WarrantyDtlVO hsttWarrantyDtlVO =null;
			String strFileId="0";
			String[] temp = null;
			HisUtil hisutil = null;
			BmedConfigUtil bmed =null;
			String strFtpFolderName ="";
			String strCurrentDate;
			AttachFileGlobal fs=null;
			String strFileName =null;
			try
			{					     
				                    bmed = new BmedConfigUtil();
				                    HisUtil his=new HisUtil(" ", " ");
				                    fs=new AttachFileGlobal();
				                 hisutil = new HisUtil("BMED Transactions", "ItemWarrantyTransDATA");
				       hsttWarrantyDtlVO = new WarrantyDtlVO();
					              userVo = ControllerUTIL.getUserVO(request_p);
					   strHospitalCode_p = userVo.getHospitalCode();
					         strSeatId_p = userVo.getSeatId();
		    		   // FormFile myFile = ItemWarrantyDtlsFB_p.getStrLocation();
					 /*FileItem myFile = ((UploadMultipartRequestWrapper)((MultipartRequestWrapper)request_p).getRequest() ).getFileItem("strLocation");

		    		    strCurrentDate = hisutil.getDSDate("DD-Mon-YYYY HH24_MI_SS");
		    		    if (myFile != null && myFile.getSize() > 0) {
		    				strFileExt = myFile.getName();
		    			//strFileExt = myFile.getFileName();
				           temp = strFileExt.replace('.', '#').split("#");
				     strFileExt = temp[temp.length-1];
				               
				//  strFileId = BmedTransBO.getFileName(myFile,strHospitalCode_p,"2");
				  strFileId = ItemWarrantyDtlsFB_p.getStrItemBrandId().split("\\^")[0]+"_"+strCurrentDate.replace(" ", "_")+"."+strFileExt;
				  System.out.println("File Name :::::::::::::: "+strFileId);
			      strFileName = strFileId;//myFile.getFileName();
		    	}*/
			      /*boolean value=his.fileupload(myFile);
			      if(!value)
				    {
			    	  ItemWarrantyDtlsFB_p.setStrNormalMsg("File can not be upload. Please Check FIle Format or File Size ");
				    	//throw new HisException("File can not be upload. Please Check FIle Format or File Size ");
				    }
			      else{*/
			    	  //fs.saveFile(myFile.get(),strFileId);
			     /*
			         // Testing data to Save record in FTP Server   
	              strFtpFolderName = bmed.getStrFtpFileFolder("15",Config.SUPER_USER_HOSPITAL_CODE);
				    
				  if(strFtpFolderName.equals("")||strFtpFolderName==null)
				  {
					 strFtpFolderName = "bmedDOCS";
				  } 
				 //  strFtpFolderName="bmedDOCS";
			        String sessionFtpAddress="10.0.1.78/ftpserver"; //populate from session  10.0.5.152/ftpserver
			        
			        sessionFtpAddress = EMMSStaticConfigurator.bmedpath;
			        
					//String logicalName = sessionFtpAddress.replace('/', '#').split("#")[1];
					String Fileurl= "ftp://"+sessionFtpAddress+"/"+strFtpFolderName;
//					String Fileurl= "ftp://"+sessionFtpAddress+"/"+logicalName+"/"+strFtpFolderName;
					System.out.println("File Url:::::"+Fileurl);			
					String Ftpurl="ftp://"+sessionFtpAddress+"/"+strFtpFolderName;			
					System.out.println("Ftp Url::::"+Ftpurl);		
					String FileName = strFileId; //generated by us at run time  (Adil Wasi)
					System.out.println("File Name::::"+strFileId);		
					SaveFileInFtpGlobal.saveFileToLocation(Fileurl, Ftpurl, FileName,myFile.getInputStream());
					System.out.println("file Created Successfully");
					*/
					/* End Test Data */
			     
			     
			     
			     ItemWarrantyDtlsFB_p.setStrUploadFileId(strFileId);
			     
			     if(ItemWarrantyDtlsFB_p.getStrTermsAndCond().equals("") || ItemWarrantyDtlsFB_p.getStrTermsAndCond().equals(" ") )
			     {
			    	 ItemWarrantyDtlsFB_p.setStrTermsAndCond("---");
			     }
			     /*
		        System.out.println("In Data");
			    System.out.println("Check Wheteher Item or Non-Item:::"+ItemWarrantyDtlsFB_p.getStrCheck());
			   	//System.out.println("Dept ID::"+ItemWarrantyDtlsFB_p.getStrDeptId());
				//System.out.println("Store ID::"+ItemWarrantyDtlsFB_p.getStrStoreId());
				System.out.println("Engg Item Type::"+ItemWarrantyDtlsFB_p.getStrEnggItemTypeId());
				System.out.println("Engg Item Sub Type::"+ItemWarrantyDtlsFB_p.getStrEnggItemSubTypeId());
				System.out.println("Item Brand Id::"+ItemWarrantyDtlsFB_p.getStrItemBrandId());
				System.out.println("Contr Supp Id::"+ItemWarrantyDtlsFB_p.getStrSupplierId());
				System.out.println("Warranty Start Date::"+ItemWarrantyDtlsFB_p.getStrWarrantyStartDate());
				System.out.println("Warranty UpTo::"+ItemWarrantyDtlsFB_p.getStrWarrantyUpTo());
				System.out.println("Warranty Id:::"+ItemWarrantyDtlsFB_p.getStrWarrantyId());
				System.out.println("Tender No::"+ItemWarrantyDtlsFB_p.getStrTenderNo());
				System.out.println("Tender Date::"+ItemWarrantyDtlsFB_p.getStrTenderDate());
				System.out.println("Order No::"+ItemWarrantyDtlsFB_p.getStrOrderNo());
				System.out.println("Order Date::"+ItemWarrantyDtlsFB_p.getStrOrderDate());
				System.out.println("remarks:::"+ItemWarrantyDtlsFB_p.getStrRemarks());
				System.out.println("term  Cod:::"+ItemWarrantyDtlsFB_p.getStrTermsAndCond());
				*/
				 
				//HSTNUM_STORE_ID^HSTNUM_ITEM_ID^HSTNUM_ITEMBRAND_ID^HSTSTR_BATCH_NO^HSTSTR_ITEM_SL_NO^GNUM_HOSPITAL_CODE^HSTNUM_STOCK_STATUS_CODE	
				
                
                    hsttWarrantyDtlVO.setStrMode("1");
				    hsttWarrantyDtlVO.setStrSlNo("0");      // Function Genrated
				    hsttWarrantyDtlVO.setStrTermsAndCondition(ItemWarrantyDtlsFB_p.getStrTermsAndCond());
				if(ItemWarrantyDtlsFB_p.getStrCheck().equals("1"))
				{
				    //System.out.println("Stock Info:::"+ItemWarrantyDtlsFB_p.getStrStockInfoVal());
	                //System.out.println("Item Sl No::::"+ItemWarrantyDtlsFB_p.getStrStockInfoVal().split("\\^")[4]);
	                //System.out.println("Batch No::::"+ItemWarrantyDtlsFB_p.getStrStockInfoVal().split("\\^")[3]);	
				    hsttWarrantyDtlVO.setStrItemId(ItemWarrantyDtlsFB_p.getStrStockInfoVal().split("\\^")[1]);
				    hsttWarrantyDtlVO.setStrItemBrandId(ItemWarrantyDtlsFB_p.getStrStockInfoVal().split("\\^")[2]);	
				    hsttWarrantyDtlVO.setStrIsItem("1");
				    hsttWarrantyDtlVO.setStrItemSlNo(ItemWarrantyDtlsFB_p.getStrStockInfoVal().split("\\^")[4]);
				    hsttWarrantyDtlVO.setStrBatchSlNo(ItemWarrantyDtlsFB_p.getStrStockInfoVal().split("\\^")[3]);
				}
				else
				{
				    hsttWarrantyDtlVO.setStrItemId(ItemWarrantyDtlsFB_p.getStrItemBrandId()); /* In Case of Non-Item*/
				    hsttWarrantyDtlVO.setStrItemBrandId("0");		
				    hsttWarrantyDtlVO.setStrIsItem("0");	
				    hsttWarrantyDtlVO.setStrItemSlNo("0");
				    hsttWarrantyDtlVO.setStrBatchSlNo("0");
				}	
					hsttWarrantyDtlVO.setStrIsValid("1");
					hsttWarrantyDtlVO.setStrSeatId(strSeatId_p);
					hsttWarrantyDtlVO.setStrHospitalCode(strHospitalCode_p);
					hsttWarrantyDtlVO.setStrTenderNo(ItemWarrantyDtlsFB_p.getStrTenderNo());
					//hsttWarrantyDtlVO.setStrUploadNo(ItemWarrantyDtlsFB_p.getStrDocRefNo());  // Getting By Function
					hsttWarrantyDtlVO.setStrUploadNo(strFileName);
					hsttWarrantyDtlVO.setStrWarrentyDate(ItemWarrantyDtlsFB_p.getStrWarrantyStartDate());
					hsttWarrantyDtlVO.setStrWarrentyUpto(ItemWarrantyDtlsFB_p.getStrWarrantyUpTo());
					hsttWarrantyDtlVO.setStrWarrentyUptoUnit(ItemWarrantyDtlsFB_p.getStrWarrantyId());
					hsttWarrantyDtlVO.setStrTermsAndCondition(ItemWarrantyDtlsFB_p.getStrTermsAndCond());
					hsttWarrantyDtlVO.setStrDocRefNo(strFileId);
					hsttWarrantyDtlVO.setStrTenderDate(ItemWarrantyDtlsFB_p.getStrTenderDate());
					hsttWarrantyDtlVO.setStrDocRefDate(ItemWarrantyDtlsFB_p.getStrDocRefDate()); //SYSDATE
					hsttWarrantyDtlVO.setStrOrderNo(ItemWarrantyDtlsFB_p.getStrOrderNo());
					hsttWarrantyDtlVO.setStrOrderDate(ItemWarrantyDtlsFB_p.getStrOrderDate());
					hsttWarrantyDtlVO.setStrManufId(ItemWarrantyDtlsFB_p.getStrSupplierId());
					hsttWarrantyDtlVO.setStrRemarks(ItemWarrantyDtlsFB_p.getStrRemarks());
					hsttWarrantyDtlVO.setStrFinancialStartYear(hisutil.getASDate("dd-MMM-yyyy"));  // Get By Function
					hsttWarrantyDtlVO.setStrFinancialEndYear(hisutil.getASDate("dd-MMM-yyyy"));    // Get By Function
			
					hsttWarrantyDtlVO.setStrCancelId("");  // Only in Case of Cancel
					hsttWarrantyDtlVO.setStrCancelDate("");// Only in Case of Cancel
					hsttWarrantyDtlVO.setStrCancelRemarks("");// Only in Case of Cancel
					hsttWarrantyDtlVO.setStrExtTermsAndCondition("");         // Only in Case of Warranty Extend
					hsttWarrantyDtlVO.setStrExtendedStartDate("");            // Only in Case of Warranty Extend
					hsttWarrantyDtlVO.setStrIsExtended("0");                  // Only in Case of Warranty Extend
					hsttWarrantyDtlVO.setStrExtendedUpto("");                 // Only in Case of Warranty Extend
					hsttWarrantyDtlVO.setStrExtendedUptoUnit("");             // Only in Case of Warranty Extend
					hsttWarrantyDtlVO.setStrExtUploadNo("00");// Only in Case of Warranty Extend                  
					hsttWarrantyDtlVO.setStrExtDocRefNo(""); // Only in Case of Warranty Extend   
					hsttWarrantyDtlVO.setStrExtDocRefDate(hisutil.getASDate("dd-MMM-yyyy"));// Only in Case of Warranty Extend

					hsttWarrantyDtlVO.setStrDeptId(ItemWarrantyDtlsFB_p.getStrDeptId());
					hsttWarrantyDtlVO.setStrStoreId(ItemWarrantyDtlsFB_p.getStrStoreId());
					hsttWarrantyDtlVO.setStrEnggItemTypeId(ItemWarrantyDtlsFB_p.getStrEnggItemTypeId());
					hsttWarrantyDtlVO.setStrEnggItemSubTypeId(ItemWarrantyDtlsFB_p.getStrEnggItemSubTypeId());
					hsttWarrantyDtlVO.setStrInstallationDate(ItemWarrantyDtlsFB_p.getStrInstallationDate());
					
    		       /**Calling BO Method Here**/
				   BmedTransBO.saveItemWarrantyDetails(hsttWarrantyDtlVO);
				   
				   if (hsttWarrantyDtlVO.getStrMsgType().equals("0")) 
				   {
					   ItemWarrantyDtlsFB_p.setStrNormalMsg("Data Saved Successfully");
				   }
			      //}
				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				     strErrMsg = "ItemWarrantyDtlsTransDATA.saveData() --> "	+ e.getMessage();
			 HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",strErrMsg);
			ItemWarrantyDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			

			eObj = null;
			}
			
		}
	    /**
	     * this method is used Renew Data
	     * @param ItemWarrantyDtlsFB_p
	     * @param request_p
	     */
	    public static void saveExtendWarrantyData(ItemWarrantyDtlsFB ItemWarrantyDtlsFB_p, HttpServletRequest request_p) 
	    {
			String strErrMsg;
			String strSeatId_p;
			String strHospitalCode_p;
			UserVO userVo = null;
			BmedConfigUtil bmed =null;
		//	AttachFileGlobal fs=null;
			WarrantyDtlVO hsttWarrantyDtlVO =null;
			String strFileId;
			String strFtpFolderName="";
			HisUtil  hisutil ;
			try
			{					     
				 hsttWarrantyDtlVO = new WarrantyDtlVO();
				 			  bmed = new BmedConfigUtil();
				             userVo = ControllerUTIL.getUserVO(request_p);
				  strHospitalCode_p = userVo.getHospitalCode();
				        strSeatId_p = userVo.getSeatId();
				            hisutil = new HisUtil("BMED Transactions", "ItemWarrantyTransDATA");
				
				 
				    FormFile myFile = ItemWarrantyDtlsFB_p.getStrLocation();
				    strFileId = BmedTransBO.getFileName(myFile,strHospitalCode_p,"2");
			        System.out.println("File ID::::"+strFileId);
			        
			        /*  Testing data to Save record in FTP Server   */
		               strFtpFolderName = bmed.getStrFtpFileFolder("15",strHospitalCode_p);
		               strFtpFolderName="bmedDOCS";
					   if(strFtpFolderName.equals("")||strFtpFolderName==null)
					   {
						    strFtpFolderName = "bmedDocs";
					   } 
				        String sessionFtpAddress="10.0.1.78"; //populate from session 10.0.5.152/ftpserver
				        sessionFtpAddress = EMMSStaticConfigurator.bmedpath;
				        
					//	String logicalName = sessionFtpAddress.replace('/', '#').split("#")[1];
						String Fileurl= "ftp://"+sessionFtpAddress+"/"+strFtpFolderName;
//						String Fileurl= "ftp://"+sessionFtpAddress+"/"+logicalName+"/"+strFtpFolderName;									
						System.out.println("File Url:::::"+Fileurl);			
						String Ftpurl="ftp://"+sessionFtpAddress+"/"+strFtpFolderName;			
						System.out.println("Ftp Url::::"+Ftpurl);		
						String FileName = strFileId; //generated by us at run time  (Adil Wasi)
						System.out.println("File Name::::"+strFileId);		
						SaveFileInFtpGlobal.saveFileToLocation(Fileurl, Ftpurl, FileName,myFile.getInputStream());
//						System.out.println("file Created Successfully");
						
						/* End Test Data */
			     
			    			     
			     ItemWarrantyDtlsFB_p.setStrUploadFileId(strFileId);
			     hsttWarrantyDtlVO.setStrSeatId(strSeatId_p);
			     
			     
			     
			     if(ItemWarrantyDtlsFB_p.getStrExtndTermsAndCond().equals("") || ItemWarrantyDtlsFB_p.getStrExtndTermsAndCond().equals(" "))
			     {
			    	 ItemWarrantyDtlsFB_p.setStrExtndTermsAndCond("---");
			     }
				/*
				
				private String strIsExtendPK; 
				         0           1             2          3            4           5            6             7      
				 //    Item Id ^ Item BrandId ^ Batch No ^ Hosp Code ^ Item Sl No ^ Manuf Id ^ Manu Fat Sl No ^ Sl No  
				*/
//			        System.out.println("File ID::::"+strFileId);
//				    System.out.println("Upload File name::::"+myFile.getFileName());
//					System.out.println("Check Wheteher Item or Non-Item:::"+ItemWarrantyDtlsFB_p.getStrCheck());
//					System.out.println("Terms & Cond:::"+ItemWarrantyDtlsFB_p.getStrExtndTermsAndCond());
//					System.out.println("Strat Date::::"+ItemWarrantyDtlsFB_p.getStrExtndWarrantyStartDate());
//					System.out.println("Extnd Up To::::"+ItemWarrantyDtlsFB_p.getStrExtndWarrantyUpTo());
//					System.out.println("Entnd Up To Unit::::"+ItemWarrantyDtlsFB_p.getStrExtndWarrantyId());
//					System.out.println("Doc Ref No::::"+ItemWarrantyDtlsFB_p.getStrDocRefNo());
//					System.out.println("Doc Ref Date::::"+ItemWarrantyDtlsFB_p.getStrDocRefDate());
                
                	    hsttWarrantyDtlVO.setStrMode("2");
                     if(ItemWarrantyDtlsFB_p.getStrCheck().equals("1"))
        			 {
                	     hsttWarrantyDtlVO.setStrIsItem("1");
        			 }
                     else
                     {
                    	 hsttWarrantyDtlVO.setStrIsItem("0");
                     }	 
					       
                        hsttWarrantyDtlVO.setStrIsValid("1");
		  		        hsttWarrantyDtlVO.setStrItemId(ItemWarrantyDtlsFB_p.getStrIsExtendPK().split("\\^")[0]);
					    hsttWarrantyDtlVO.setStrItemBrandId(ItemWarrantyDtlsFB_p.getStrIsExtendPK().split("\\^")[1]);	
					    hsttWarrantyDtlVO.setStrItemSlNo(ItemWarrantyDtlsFB_p.getStrIsExtendPK().split("\\^")[4]);
					    hsttWarrantyDtlVO.setStrBatchSlNo(ItemWarrantyDtlsFB_p.getStrIsExtendPK().split("\\^")[2]);
					    hsttWarrantyDtlVO.setStrSlNo(ItemWarrantyDtlsFB_p.getStrIsExtendPK().split("\\^")[7]); 
					
						hsttWarrantyDtlVO.setStrSeatId(strSeatId_p);
						hsttWarrantyDtlVO.setStrHospitalCode(strHospitalCode_p);
										
						hsttWarrantyDtlVO.setStrExtTermsAndCondition(ItemWarrantyDtlsFB_p.getStrExtndTermsAndCond());        
						hsttWarrantyDtlVO.setStrExtendedStartDate(ItemWarrantyDtlsFB_p.getStrExtndWarrantyStartDate());      
						hsttWarrantyDtlVO.setStrIsExtended("1");                                                             
						hsttWarrantyDtlVO.setStrExtendedUpto(ItemWarrantyDtlsFB_p.getStrExtndWarrantyUpTo());                
						hsttWarrantyDtlVO.setStrExtendedUptoUnit(ItemWarrantyDtlsFB_p.getStrExtndWarrantyId());             
						hsttWarrantyDtlVO.setStrExtUploadNo(ItemWarrantyDtlsFB_p.getStrDocRefNo());                 
						hsttWarrantyDtlVO.setStrExtDocRefNo(strFileId);                  
						hsttWarrantyDtlVO.setStrExtDocRefDate(ItemWarrantyDtlsFB_p.getStrDocRefDate());    
						hsttWarrantyDtlVO.setStrRemarks(ItemWarrantyDtlsFB_p.getStrExtndRemarks());
						
						// Set Default Value [these dummy Value not use in final Update Statement ]
						hsttWarrantyDtlVO.setStrFinancialStartYear(hisutil.getASDate("dd-MMM-yyyy"));  // Get By Function
						hsttWarrantyDtlVO.setStrFinancialEndYear(hisutil.getASDate("dd-MMM-yyyy"));    // Get By Function
						hsttWarrantyDtlVO.setStrWarrentyDate(hisutil.getASDate("dd-MMM-yyyy"));
						hsttWarrantyDtlVO.setStrFinancialStartYear(hisutil.getASDate("dd-MMM-yyyy"));
						hsttWarrantyDtlVO.setStrDocRefDate(hisutil.getASDate("dd-MMM-yyyy"));
						hsttWarrantyDtlVO.setStrTenderDate(hisutil.getASDate("dd-MMM-yyyy"));
						hsttWarrantyDtlVO.setStrOrderDate(hisutil.getASDate("dd-MMM-yyyy"));
						hsttWarrantyDtlVO.setStrCancelDate(hisutil.getASDate("dd-MMM-yyyy"));
						
						
						
				
				 /**Calling BO Method Here**/
				 BmedTransBO.saveItemWarrantyDetails(hsttWarrantyDtlVO);
				 
				   if (hsttWarrantyDtlVO.getStrMsgType().equals("0")) 
				   {
					   ItemWarrantyDtlsFB_p.setStrNormalMsg("Data Extended Successfully");
				   }
				
			}
			catch (Exception e) 
			{
//				e.printStackTrace();
	 
				strErrMsg = "ItemWarrantyDtlsTransDATA.saveExtendWarrantyData() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",
					strErrMsg);
			ItemWarrantyDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
			}
			
		}
	    /**
		 * This Method is Used to Cancel Data from table hemt_item_mc_dtl
		 * @param ItemWarrantyDtlsFB_p
		 * @param request_p
		 * @param response_p
		 */
	    
	    public static void cancelData(ItemWarrantyDtlsFB ItemWarrantyDtlsFB_p, HttpServletRequest request_p ,HttpServletResponse response_p) 
	    {
			String strErrMsg;
			String strSeatId_p;
			String strHospitalCode_p;
			UserVO userVo = null;
			HisUtil hisutil = null;
			WarrantyDtlVO hsttWarrantyDtlVO =null;
			String strCurrentDate;
			
			try
			{					     
				             hisutil = new HisUtil("","");
				   hsttWarrantyDtlVO = new WarrantyDtlVO();
				              userVo = ControllerUTIL.getUserVO(request_p);
				   strHospitalCode_p = userVo.getHospitalCode();
				         strSeatId_p = userVo.getSeatId();
				      strCurrentDate = hisutil.getDSDate("DD-Mon-YYYY");
				
				 hsttWarrantyDtlVO.setStrMode("3");
				 hsttWarrantyDtlVO.setStrIsValid("0");	
				 hsttWarrantyDtlVO.setStrSeatId(strSeatId_p);
				 hsttWarrantyDtlVO.setStrHospitalCode(strHospitalCode_p);
				 /*
				 
						Value in strWarrantyTableInfo Concated by ^ Symbol [Total Value 44]
				        Value in WebRowSet----
						0.HSTNUM_ITEM_ID:              
						1.HSTNUM_ITEMBRAND_ID:         
						2.HSTSTR_BATCH_SL_NO:          
						3.GNUM_HOSPITAL_CODE:          
						4.HSTNUM_ITEM_SL_NO:           
						5.HSTNUM_MANUF_ID:             
						6.HSTNUM_MANUF_SL_NO:          
						7.HSTNUM_SL_NO:   
				  */
				    /**** Primary Key Value ****/
				    hsttWarrantyDtlVO.setStrItemId((String)request_p.getParameter("stockInfo").split("\\^")[0]);
				    hsttWarrantyDtlVO.setStrItemBrandId((String)request_p.getParameter("stockInfo").split("\\^")[1]);	
				    hsttWarrantyDtlVO.setStrItemSlNo((String)request_p.getParameter("stockInfo").split("\\^")[4]);
				    hsttWarrantyDtlVO.setStrBatchSlNo((String)request_p.getParameter("stockInfo").split("\\^")[2]);
				    hsttWarrantyDtlVO.setStrSlNo((String)request_p.getParameter("stockInfo").split("\\^")[7]); 
				    hsttWarrantyDtlVO.setStrCancelId((String)request_p.getParameter("cancelType"));
				    hsttWarrantyDtlVO.setStrCancelRemarks((String)request_p.getParameter("cancelRemarks"));
				    hsttWarrantyDtlVO.setStrCancelId((String)request_p.getParameter("cancelType"));  
				    hsttWarrantyDtlVO.setStrCancelDate("");   // Sysdate
					hsttWarrantyDtlVO.setStrTenderNo("0");
					hsttWarrantyDtlVO.setStrUploadNo("0"); 
					hsttWarrantyDtlVO.setStrDocRefNo("0");
					hsttWarrantyDtlVO.setStrTenderDate(strCurrentDate);
					hsttWarrantyDtlVO.setStrOrderDate(strCurrentDate);
					hsttWarrantyDtlVO.setStrRemarks("--");
					// Set Default Value [these dummy Value not use in final Update Statement ]
					hsttWarrantyDtlVO.setStrFinancialStartYear(hisutil.getASDate("dd-MMM-yyyy"));  // Get By Function
					hsttWarrantyDtlVO.setStrFinancialEndYear(hisutil.getASDate("dd-MMM-yyyy"));    // Get By Function
					hsttWarrantyDtlVO.setStrWarrentyDate(hisutil.getASDate("dd-MMM-yyyy"));
					hsttWarrantyDtlVO.setStrFinancialStartYear(hisutil.getASDate("dd-MMM-yyyy"));
					hsttWarrantyDtlVO.setStrDocRefDate(hisutil.getASDate("dd-MMM-yyyy"));
					hsttWarrantyDtlVO.setStrTenderDate(hisutil.getASDate("dd-MMM-yyyy"));
					hsttWarrantyDtlVO.setStrOrderDate(hisutil.getASDate("dd-MMM-yyyy"));
					hsttWarrantyDtlVO.setStrCancelDate(hisutil.getASDate("dd-MMM-yyyy"));
				
								
				/**Calling BO Method Here**/
				BmedTransBO.saveItemWarrantyDetails(hsttWarrantyDtlVO);
				
				
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
		//		e.printStackTrace();
	 
				strErrMsg = "ItemWarrantyDtlsTransDATA.cancelData() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",
					strErrMsg);
			ItemWarrantyDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
			}
			
		}
	    /**
	     * This method is used for  'EXTEND' + 'CANCEL' + 'VIEW ' Pop-Up
	     * @param ItemWarrantyDtlsFB_p
	     * @param request_p
	     * @param response_p
	     */
	    public static void getCommanAction(ItemWarrantyDtlsFB ItemWarrantyDtlsFB_p, HttpServletRequest request_p,HttpServletResponse response_p) 
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
				       bmedTransBO = new BmedTransBO();
				            userVo = ControllerUTIL.getUserVO(request_p);
				 strHospitalCode_p = userVo.getHospitalCode();
				      strInfoVal_p = (String) request_p.getParameter("info");
				         strMode_p = (String) request_p.getParameter("mode");
				  String isItem = (String) request_p.getParameter("isItem");
				
				if(strMode_p.equals("1"))
				{	
				         strInfo_p = bmedTransBO.getRenewPageSrc(strHospitalCode_p, strInfoVal_p);
				   strRenewPageSrc = getItemContCommanHlp(strInfoVal_p,strInfo_p,strMode_p,isItem);
				}
				if(strMode_p.equals("2"))
				{
					     strInfo_p = bmedTransBO.getCancelPageSrc(strHospitalCode_p, strInfoVal_p);
				   strRenewPageSrc = getItemContCommanHlp(strInfoVal_p,strInfo_p,strMode_p,isItem);
				}
				if(strMode_p.equals("3"))
				{
					     strInfo_p = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
				   strRenewPageSrc = getItemContCommanHlp(strInfoVal_p,strInfo_p,strMode_p,isItem);
					
				}
													
				try 
				{									
					response_p.setHeader("Cache-Control", "no-cache");
					response_p.getWriter().print(strRenewPageSrc);
						
				}
				catch(Exception e)
				{
			//			e.printStackTrace();
				}
				

			} catch (Exception e) {
	 
				strErrMsg = "ItemWarrantyDtlsTransDATA.getCommanAction() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",
					strErrMsg);
			ItemWarrantyDtlsFB_p.setStrErrMsg("Application Error [ERROR ID : "
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
			
			try 
			{
				/*
				Value in strWarrantyTableInfo Concated by ^ Symbol
		        Value in WebRowSet----
				0.HSTNUM_ITEM_ID:              
				1.HSTNUM_ITEMBRAND_ID:         
				2.HSTSTR_BATCH_SL_NO:          
				3.GNUM_HOSPITAL_CODE:          
				4.HSTNUM_ITEM_SL_NO:           
				5.HSTNUM_MANUF_ID:             
				6.HSTNUM_MANUF_SL_NO:          
				7.HSTNUM_SL_NO:                
				8.HSTDT_WARRENTY_DATE:         
			    9.HSTNUM_WARRENTY_UPTO:        
			    10.HSTNUM_WARRENTY_UPTO_UNIT:   
			    11.HSTNUM_IS_ITEM:              
				12.HSTDT_FINANCIAL_START_YEAR:  
				13.HSTDT_FINANCIAL_END_YEAR:    
				14.HPURNUM_UPLOAD_NO:           
				15.HPURSTR_DOC_REF_NO:          
				16.HEMSTR_TERM_N_CON:           
				17.HPURDT_DOC_REF_DATE:         
				18.GSTR_REMARKS:                
				19.GDT_ENTRY_DATE:              
				20.GNUM_SEATID:                 
				21.GNUM_ISVALID:                
				22.HEMSTR_TENDER_NO:            
				23.GDT_LSTMOD_DATE:             
				24.HEMDT_TENDER_DATE:           
				25.GNUM_LSTMOD_SEATID:          
				26.HEMSTR_ORDER_NO:             
				27.HEMDT_ORDER_DATE:            
				28.HEMNO_CANCEL_ID:             
				29.HEMDT_CANCEL_DATE:           
				30.HEMSTR_EXT_TERM_N_CON:       
				31.HEMSTR_CANCEL_REMARKS:       
				32.HEMNUM_IS_EXTENDED:          
				33.HEMDT_EXTENDED_START_DATE:   
				34.HEMNUM_EXTENDED_UPTO:        
				35.HEMNUM_EXTENDED_UPTO_UNIT:   
				36.HPURNUM_EXT_UPLOAD_NO:       
				37.HPURNUM_EXT_DOC_REF_NO:      
				38.HPURDT_EXT_DOC_REF_DATE:     
				39.ITEM_NAME:                   
				40.VENDOR_NAME:                 
				41.WARRENTY_UPTO_UNIT_NAME:   
				42.WARRANTY_EXTEND_DATE:  
				43.Department Name
				44.Store Name
				45.Engg Item Type
				46.Engg Item Sub Type
				47.Item Name
				
				*/
				
				String strDept              =  readOnlyInfo_p.split("\\^")[43];
				String strStore             =  readOnlyInfo_p.split("\\^")[44];
				String strEnggItemTyp       =  readOnlyInfo_p.split("\\^")[45];
				String strEnggItemSubTyp    =  readOnlyInfo_p.split("\\^")[46];
				String strIteBrandId        =  readOnlyInfo_p.split("\\^")[47];
				String strItemSlNo          =  readOnlyInfo_p.split("\\^")[4];
				String strManufactNo        =  readOnlyInfo_p.split("\\^")[5];
				String strBatchNo           =  readOnlyInfo_p.split("\\^")[2];
			//	String strStockInfo         =  readOnlyInfo_p.split("\\^")[0]+"^"+readOnlyInfo_p.split("\\^")[1]+"^"+readOnlyInfo_p.split("\\^")[3]+"^"+readOnlyInfo_p.split("\\^")[4]+"^"+readOnlyInfo_p.split("\\^")[7];
				String strSupplier          =  readOnlyInfo_p.split("\\^")[40];
				String strWarrantyStartDate =  readOnlyInfo_p.split("\\^")[8];
				String strWarrantyUpTo      =  readOnlyInfo_p.split("\\^")[9];
				String strWarrantyUpToUnit  =  readOnlyInfo_p.split("\\^")[41];
				String strTermsCond         =  readOnlyInfo_p.split("\\^")[16];
				String strRemarks           =  readOnlyInfo_p.split("\\^")[18];
				String strTenderNo          =  readOnlyInfo_p.split("\\^")[22];
				String strTenderDate        =  readOnlyInfo_p.split("\\^")[24];
				String strOrderNo           =  readOnlyInfo_p.split("\\^")[26];
				String strOrderDate         =  readOnlyInfo_p.split("\\^")[27];
				String strDocRefNo          =  readOnlyInfo_p.split("\\^")[15];
				String strUploadNo          =  readOnlyInfo_p.split("\\^")[14];
				
								
				br.append("<table class='TABLE_STYLE'>");
				br.append("<tr class='FOOTER_TR'>");
				if(strMode_p.equals("1"))
				{
			       br.append("<td colspan='5' style='width: 100%'><div id='Id1' style='color:blue;text-align:left'>Item Maintenance Contract [RENEW ] </div></td>");
				}
				if(strMode_p.equals("2"))
				{
			       br.append("<td colspan='5' style='width: 100%'><div id='Id1' style='color:blue;text-align:left'>Item Maintenance Contract [CANCEL ] </div></td>");
				}
				if(strMode_p.equals("3"))
				{
			       br.append("<td colspan='5' style='width: 100%'><div id='Id1' style='color:blue;text-align:left'>Item Maintenance Contract [VIEW ] </div></td>");
				}
			    br.append("</tr>");
				if(isItem.equals("1")){
					br.append("<tr>");
					br.append("<td width='25%' class='LABEL_TD'>Department Name</td>");
					br.append("<td width='25%' class='CONTROL_TD'>"+strDept+"</td>");
					br.append("<td width='25%' class='LABEL_TD'>Store Name</td>");
					br.append("<td width='25%' class='CONTROL_TD'>"+strStore+"</td>");
					br.append("</tr>");
	                br.append("<tr>");
				}
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
				else{
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
				br.append("<td colspan='1' class='LABEL_TD'>Supplier</td>");
				br.append("<td colspan='1' class='CONTROL_TD'>"+strSupplier+"</td>");
				br.append("<td colspan='1' class='LABEL_TD'>Warranty Start Date</td>");
				br.append("<td colspan='1' class='CONTROL_TD'>"+strWarrantyStartDate+"</td>");
				br.append("</tr>");
				if(strMode_p.equals("2"))
				{
					String strSlNo              =  readOnlyInfo_p.split("\\^")[7];
					br.append("<input type='hidden' name='strSlNo' id='strSlNo' value='"+strSlNo+"'>");
					br.append("<input type='hidden' name='strCancelStockInfo' id='strCancelStockInfo' value='"+readOnlyInfo_p+"'>");
					br.append("<tr>");
					br.append("<td colspan='1' class='LABEL_TD'>Warranty UpTo</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+readOnlyInfo_p.split("\\^")[9]+"</td>");
					br.append("<td colspan='1' class='LABEL_TD'>Warranty UpTo Unit</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+readOnlyInfo_p.split("\\^")[41]+"</td>");
					br.append("</tr>");
					br.append("<tr>");
					br.append("<td colspan='1' class='LABEL_TD'>Cancel Type</td>");
					br.append("<td colspan='1' class='CONTROL_TD'><select name='strCancelTypeId' class='combo2'>"+strInfo_p+"</select></td>");
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
					br.append("<img style='cursor: pointer;' src='/AHIMS/hisglobal/images/btn-sv.png' onClick='SaveCancel();' />");
					br.append("<img style='cursor: pointer;' src='/AHIMS/hisglobal/images/btn-ccl.png' onClick='hideInfoPopup();'>");
					br.append("</div>");
				}
				if(strMode_p.equals("3"))
				{
					
					br.append("<tr>");
					br.append("<td colspan='1' class='LABEL_TD'>Warranty UpTo</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strWarrantyUpTo+"</td>");
					br.append("<td colspan='1' class='LABEL_TD'>Warranty UpTo Unit</td>");
					br.append("<td colspan='1' class='CONTROL_TD'>"+strWarrantyUpToUnit+"</td>");
					br.append("</tr>");
					
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
					br.append("<td width='25%' class='LABEL_TD'>Remarks</td>");
					br.append("<td width='25%' class='CONTROL_TD'><textarea name='strRenewRemarks' cols='25' rows='2' readOnly>"+strRemarks+"</textarea></td>");
					
					br.append("<td width='25%' class='LABEL_TD'>Terms & Condition</td>");
					br.append("<td width='25%' class='CONTROL_TD'><textarea	name='strRenewTermsAndCond' cols='25' rows='2' readOnly>"+strTermsCond+"</textarea></td>");
					br.append("</tr>");
					br.append("</table>");
					br.append("<table class='TABLE_STYLE'>");
					br.append("<tr>");
					br.append("<td class='LABEL_TD' width='50%'>Ref. No.</td>");
					br.append("<td class='CONTROL_TD' width='50%'>"+strDocRefNo+"");
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
					br.append("<img style='cursor: pointer;' src='/AHIMS/hisglobal/images/btn-ccl.png' onClick='hideInfoPopup();'>");
					br.append("</div>");
					
					
				}

				
			}
			catch (Exception e) 
			{
			//	e.printStackTrace();

			}
			
			return br.toString();
		}


	    
	    
	    
	    

}
