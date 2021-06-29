package hisglobal.tools.imageUtility.util;

import investigation.InvestigationConfig;

import java.awt.image.BufferedImage;
import java.beans.Statement;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.backutil.HisMethods;
import hisglobal.persistence.ResultSet;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.tools.imageUtility.fb.ImageServletFB;
import hisglobal.tools.imageUtility.fb.ImageUtilityFB;
import hisglobal.vo.ImageUtilityConfiguratorDetails;
import hisglobal.vo.ImageUtilityVO;
import hisglobal.vo.Test;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ImageServletUTIL extends HisMethods {

	public void populateImageList(HttpServletRequest request,
			ImageServletFB fb) {
		
		ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails=(ImageUtilityConfiguratorDetails)request.getSession().getAttribute("ImageUtilityConfiguratorDetails");
		
		if(imageUtilityConfiguratorDetails.imageservletselectquery!=null)
		{
			fetchFromProvidedQuery(request, imageUtilityConfiguratorDetails, fb);
		}
		else if(imageUtilityConfiguratorDetails.imageservletsessionListName!=null)
		{
			fetchFromProvidedSessionList(request, imageUtilityConfiguratorDetails, fb);
		}
		else if(imageUtilityConfiguratorDetails.imageservletsessionName!=null)
		{
			fetchFromProvidedDirectSessionList(request, imageUtilityConfiguratorDetails, fb);
		}
		else
		{
			request.setAttribute("error", "No Configuration Found ! ");
		}
		
		
		
		
	}

	private static void fetchFromProvidedSessionList(HttpServletRequest request,ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails,ImageServletFB fb) 
	{
		System.out.println("<------fetchFromProvidedSessionList--->");
		List<ImageUtilityVO> sessionList=(List<ImageUtilityVO>)request.getSession().getAttribute(imageUtilityConfiguratorDetails.getImageservletsessionListName());
		
		if(sessionList==null)
		{
			System.out.println("<------sessionList null--->");
			request.setAttribute("error", "No Session Found with name:"+imageUtilityConfiguratorDetails.getImageservletsessionListName());
		}
		else
		{
			
			if(imageUtilityConfiguratorDetails.getImageservletsessionIndex()==null && imageUtilityConfiguratorDetails.getImageservletsessionIndex().equals("")==false)
			{
				System.out.println("<------imageUtilityConfiguratorDetails.getImageservletsessionIndex() null--->");
				request.setAttribute("error", "No Session Index Configured or provided");
			}
			else
			{
				int sessionIndex=Integer.parseInt(imageUtilityConfiguratorDetails.getImageservletsessionIndex());
				List<ImageUtilityVO> imageUtilityVOList=(List<ImageUtilityVO>) sessionList.get(sessionIndex);
				List<ImageUtilityVO> imageUtilityDisplayVOList=new ArrayList<ImageUtilityVO>();
				int page=Integer.parseInt(fb.getCurrentPageNo());
				int recordPerPage=Integer.parseInt(fb.getRecordPerPage());
				settingthepageString(imageUtilityVOList,fb);
				if(imageUtilityVOList!=null)
				{
				for(int i=(page-1)*recordPerPage;((i<((page)*recordPerPage)))&&(i<(imageUtilityVOList.size()));i++)
				{
					imageUtilityDisplayVOList.add(imageUtilityVOList.get(i));
				}
				}
				
				System.out.println("displayList size = "+imageUtilityDisplayVOList.size());
				System.out.println("ImageList size = "+imageUtilityVOList.size());
				request.getSession().setAttribute("PAGEIMAGELIST",imageUtilityVOList);
				request.getSession().setAttribute("ALLIMAGELIST",imageUtilityVOList);
				fb.setLengthofImageList(""+imageUtilityVOList.size());
			}
			
			
		}
		
	}
	
	private void fetchFromProvidedDirectSessionList(HttpServletRequest request,ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails,ImageServletFB fb) 
	{
		
		System.out.println("<------fetchFromProvidedDirectSessionList--->");
		List<ImageUtilityVO> sessionList=null;
		if(imageUtilityConfiguratorDetails.getImageservletsessionvariable()==null)
		{
			sessionList=(List<ImageUtilityVO>)request.getSession().getAttribute(imageUtilityConfiguratorDetails.getImageservletsessionName());
		}
		else
		{
			Object sessionObject=request.getSession().getAttribute(imageUtilityConfiguratorDetails.getImageservletsessionName());
			Class identifyingClass=sessionObject.getClass();
			Method[] cls2Methods = identifyingClass.getMethods();
			for(int i=0;i<cls2Methods.length;i++)
			{
				if(cls2Methods[i].getName().equalsIgnoreCase("get"+imageUtilityConfiguratorDetails.getImageservletsessionvariable())==true)
				{
					try {
						System.out.println(cls2Methods[i].getGenericReturnType()+"getting sessionList"+cls2Methods[i].getReturnType());
						if(cls2Methods[i].getGenericReturnType().toString().equals("java.util.Map<java.lang.String, java.util.List<hisglobal.vo.ImageUtilityVO>>"))
						{
							System.out.println("fetch from map : map key :"+fb.getMapKey());
							Map<String,List<ImageUtilityVO>> actualMap=(Map<String,List<ImageUtilityVO>>)cls2Methods[i].invoke(sessionObject, null);
							System.out.println(actualMap);
							sessionList=((actualMap!=null)?actualMap.get(fb.getMapKey()):null);
						}
						else
						{
							System.out.println("fetch from List :");
							sessionList=(List<ImageUtilityVO>)cls2Methods[i].invoke(sessionObject, null);
						}
					} catch (IllegalArgumentException e) {
						
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			
			
			
			
		}
		
		
		
		if(sessionList==null)
		{
			request.setAttribute("error", "No Session Found with name:"+imageUtilityConfiguratorDetails.getImageservletsessionListName());
			request.getSession().setAttribute("PAGEIMAGELIST",null);
			request.getSession().setAttribute("ALLIMAGELIST",null);
		}
		else
		{
				System.out.println("sessionList --> "+sessionList.size());
				List<ImageUtilityVO> imageUtilityVOList=sessionList;
				List<ImageUtilityVO> imageUtilityDisplayVOList=new ArrayList<ImageUtilityVO>();
				int page=Integer.parseInt(fb.getCurrentPageNo());
				int recordPerPage=Integer.parseInt(fb.getRecordPerPage());
				settingthepageString(imageUtilityVOList,fb);
				if(imageUtilityVOList!=null)
				{
				for(int i=(page-1)*recordPerPage;((i<((page)*recordPerPage)))&&(i<(imageUtilityVOList.size()));i++)
				{
					imageUtilityDisplayVOList.add(imageUtilityVOList.get(i));
					
				}
				}
				
				System.out.println("displayList size = "+imageUtilityVOList.size());
				request.getSession().setAttribute("PAGEIMAGELIST",imageUtilityDisplayVOList);
				request.getSession().setAttribute("ALLIMAGELIST",imageUtilityVOList);
				fb.setLengthofImageList(""+imageUtilityVOList.size());
		}
			
			
		
		
	}
	

	public static void fetchFromProvidedQuery(HttpServletRequest request, ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails ,ImageServletFB fb) {

		java.util.List imgLst = new ArrayList();
		java.util.List imgDisplayLst = new ArrayList();
		java.util.List imgByteArrayLst = new ArrayList();
		//Map imgByteMap = new HashMap();
		java.util.List imgByteMap = new ArrayList();

		java.util.List videoLst = new ArrayList();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.0.5.165:1521:AHIS", "AHIS", "AHIS");
			System.out.println("select Query "+imageUtilityConfiguratorDetails.imageservletselectquery);
			java.sql.PreparedStatement stmt = conn.prepareStatement(imageUtilityConfiguratorDetails.imageservletselectquery);
			String [] primaryKeyArray =null;
			if(imageUtilityConfiguratorDetails.primaryKey!=null && imageUtilityConfiguratorDetails.primaryKey.equals("")==false)
			{
				primaryKeyArray=imageUtilityConfiguratorDetails.primaryKey.replace("_", "#").split("#");
			}
			
			
			if(primaryKeyArray!=null && primaryKeyArray.length!=0)
			{
				for(int i=1;i<=primaryKeyArray.length;i++)
				{
					stmt.setString(i, primaryKeyArray[i-1]);
				}
			}
				
			
			java.sql.ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
					
				
					// rs.getString(4)+"/"+rs.getString(5)+"/"+rs.getString(2)
					ImageUtilityVO imgVdoVO=new ImageUtilityVO();
					HelperMethods.populateVOfrmRS(imgVdoVO, rs);
					imgVdoVO.setFileUrl( imgVdoVO.getFtplocation() + "/" + imgVdoVO.getOriginalFileName());
					
					imgLst.add(imgVdoVO);
					byte[] imageArray=getByteArray(imgVdoVO.getFtpserver() + "/" +imgVdoVO.getFileUrl());
					imgVdoVO.setImageByteArray(imageArray);
					imgByteMap.add(imageArray);
					

				 /*else {
					ImageUtilityVO imgVdoVO=new ImageUtilityVO();
					HelperMethods.populateVOfrmRS(imgVdoVO, rs);
					imgVdoVO.setFileUrl( imgVdoVO.getFtplocation() + "/" + imgVdoVO.getOriginalFileName());
					videoLst.add(imgVdoVO);					
				}*/

			}
			conn.commit();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int page=Integer.parseInt(fb.getCurrentPageNo());
		int recordPerPage=Integer.parseInt(fb.getRecordPerPage());
		
		settingthepageString(imgLst,fb);
		System.out.println("Page ="+page);
		System.out.println("recordPerPage ="+recordPerPage);
		if(imgLst!=null)
		{
		for(int i=(page-1)*recordPerPage;((i<((page)*recordPerPage)))&&(i<(imgLst.size()));i++)
		{
			imgDisplayLst.add(imgLst.get(i));
		}
		}
		
		System.out.println("displayList size = "+imgDisplayLst.size());
		request.getSession().setAttribute("PAGEIMAGELIST",imgDisplayLst);
		request.getSession().setAttribute("ALLIMAGELIST",imgLst);
		fb.setLengthofImageList(""+imgLst.size());
	}

	public static byte[] getByteArray(String file1) {

		System.out.println("file Name:   "+file1);
		byte[] imageInByte = null;
		try {
			BufferedImage originalImage = ImageIO.read(new URL(file1));

			// convert BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();

			// convert byte array back to BufferedImage
			/*InputStream in = new ByteArrayInputStream(imageInByte);
			BufferedImage bImageFromConvert = ImageIO.read(in);
*/
			/*ImageIO.write(bImageFromConvert, "jpg", new File(
					"c://mypic_new.jpg"));*/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return imageInByte;
	}

	


	public  static void settingthepageString(List sessionList,ImageServletFB fb)
	{
		String Str="";
		int TotalNopages=(sessionList.size()%Integer.parseInt(fb.getRecordPerPage())==0)?sessionList.size()/Integer.parseInt(fb.getRecordPerPage()):sessionList.size()/Integer.parseInt(fb.getRecordPerPage())+1;
		int pageperblock=Integer.parseInt(fb.getRecordPerPage());
		int pagesperblock=Integer.parseInt(fb.getPagesPerBlock());
		int totalNoBlock=(TotalNopages%pagesperblock==0)?TotalNopages/pagesperblock:TotalNopages/pagesperblock+1;
		int block=Integer.parseInt(fb.getCurrentblock());
		int page=Integer.parseInt(fb.getCurrentPageNo());
		System.out.println("TotalNopages  ="+TotalNopages+"  totalNoBlock="+totalNoBlock+" block="+block+" page="+page);
		if(block>1)
		{
			Str+=Str+="<a name='prev'  onClick='prevSubmit()'><font Style='cursor:pointer'>  Prev  </font></a>";
		}
		for(int i=(block-1)*Integer.parseInt(fb.getRecordPerPage())+1;i<=(block-1)*Integer.parseInt(fb.getRecordPerPage())+Integer.parseInt(fb.getRecordPerPage())&& i<=TotalNopages;i++)
		{
			//submitform('REQUISITIONLISTING')
			if(i==page)
				{
					Str+="<a name=a"+i+" value="+i+" onClick='return PageSubmit(\""+i+"\",\"INIT\")' style='color:red'><font Style='cursor:default'>&nbsp;"+i+"</font></a>";			
				}
				else
					Str+="<a name=a"+i+" value="+i+" onClick='return PageSubmit(\""+i+"\",\"INIT\")' style='color:blue'><font Style='cursor:pointer'>&nbsp;"+i+"</font></a>";
										
		}
		if(totalNoBlock!=0 && block!=totalNoBlock)
		{
			Str+="<a name='next'  onClick='nextSubmit()'><font Style='cursor:pointer'>  next  </font></a>";
		}
		Str+="</div>";
		System.out.println("Str   ="+Str);
		fb.setPageString(Str);

	}
	
	public  void SaveImage(HttpServletRequest request,String fileName,String mode,byte[] imageBytes, String string, String string2) throws ClassNotFoundException,SQLException
	{
		System.out.println("Step 1 SaveImage");
		
		ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails=(ImageUtilityConfiguratorDetails)request.getSession().getAttribute("ImageUtilityConfiguratorDetails");
		ImageUtilityVO imageUtilityVO=new ImageUtilityVO();
		List<ImageUtilityVO> allImageList=(List<ImageUtilityVO>)request.getSession().getAttribute("ALLIMAGELIST");
		if(allImageList==null)
			allImageList=new ArrayList<ImageUtilityVO>();
		
		allImageList.add(imageUtilityVO);
		request.getSession().setAttribute("ALLIMAGELIST",allImageList);
		populateImageUtilityVO(request,imageUtilityConfiguratorDetails,imageUtilityVO,fileName, mode,imageBytes );
		
		if(imageUtilityConfiguratorDetails.getImageservletdatabaseinsertQuery()!=null && imageUtilityConfiguratorDetails.getImageservletdatabaseinsertQuery().equals("")==false)
		{
			saveImageToDatabase(imageUtilityConfiguratorDetails, imageUtilityVO, mode);
			
		}
		else
		{
			saveImageToSession(imageUtilityConfiguratorDetails, imageUtilityVO, mode,request);
		}
		
	
		
		
}
	public void saveImageToSession(
			ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails,
			ImageUtilityVO imageUtilityVO, String mode, HttpServletRequest request) {
		System.out.println("<----------saveImageToSession---------->");
		List<ImageUtilityVO> sessionList=null;
		if(imageUtilityConfiguratorDetails.getImageservletsessionvariable()==null)
		{
			System.out.println("setting the allimage session liat :Direct");
			sessionList=(List<ImageUtilityVO>)request.getSession().getAttribute(imageUtilityConfiguratorDetails.getImageservletsessionName());
			request.getSession().setAttribute(imageUtilityConfiguratorDetails.getImageservletsessionName(),(List<ImageUtilityVO>)request.getSession().getAttribute("ALLIMAGELIST"));
		}
		else
		{
			System.out.println("setting the allimage session liat :inDirect");
			Object sessionObject=request.getSession().getAttribute(imageUtilityConfiguratorDetails.getImageservletsessionName());
			Class identifyingClass=sessionObject.getClass();
			Method[] cls2Methods = identifyingClass.getMethods();
			for(int i=0;i<cls2Methods.length;i++)
			{
				System.out.println(cls2Methods[i].getName()+"---->set"+imageUtilityConfiguratorDetails.getImageservletsessionvariable());
				//System.out.println("Value------>"+("set"+imageUtilityConfiguratorDetails.getImageservletsessionvariable()).equalsIgnoreCase(cls2Methods[i].getName()));
				if(("set"+imageUtilityConfiguratorDetails.getImageservletsessionvariable()).equalsIgnoreCase(cls2Methods[i].getName())==true)
				{
					try {
						try {
							System.out.println("invoking the setter method of object's List "+identifyingClass.getField(imageUtilityConfiguratorDetails.getImageservletsessionvariable()).getGenericType().toString());
							System.out.println(identifyingClass.getField(imageUtilityConfiguratorDetails.getImageservletsessionvariable()).getGenericType().toString().equals("java.util.Map<java.lang.String, java.util.List<hisglobal.vo.ImageUtilityVO>>"));
							if(identifyingClass.getField(imageUtilityConfiguratorDetails.getImageservletsessionvariable()).getGenericType().toString().equals("java.util.Map<java.lang.String, java.util.List<hisglobal.vo.ImageUtilityVO>>"))
							{
								for(int j=0;j<cls2Methods.length;j++)
								{
									if(("get"+imageUtilityConfiguratorDetails.getImageservletsessionvariable()).equalsIgnoreCase(cls2Methods[j].getName()))		
									{
										System.out.println(request.getParameter("mapKey")+"setting the map of session"+((java.util.Map<java.lang.String, java.util.List<hisglobal.vo.ImageUtilityVO>>)cls2Methods[j].invoke(sessionObject,null)));
										Map mp=((java.util.Map<java.lang.String, java.util.List<hisglobal.vo.ImageUtilityVO>>)cls2Methods[j].invoke(sessionObject,null));
										if(mp==null)
										{
											mp=new HashMap<java.lang.String, java.util.List<hisglobal.vo.ImageUtilityVO>>();
										}
										
										mp.put(request.getParameter("mapKey"), (List<ImageUtilityVO>)request.getSession().getAttribute("ALLIMAGELIST"));
										cls2Methods[i].invoke(sessionObject,mp);
										System.out.println(request.getParameter("mapKey")+"setting the map of session"+((java.util.Map<java.lang.String, java.util.List<hisglobal.vo.ImageUtilityVO>>)cls2Methods[j].invoke(sessionObject,null)));
									}
								}
								
									
							}
							else
							{
								System.out.println("setting the list");
								cls2Methods[i].invoke(sessionObject,(List<ImageUtilityVO>)request.getSession().getAttribute("ALLIMAGELIST"));
							}
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
						
						
						
					} catch (IllegalArgumentException e) {
						
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			
			
			
			
		}
		
		
		
		
	}

	/*  important to populate the ImageUtilityVO correctly*/
	public void populateImageUtilityVO(HttpServletRequest request ,ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails,ImageUtilityVO imageUtilityVO,String fileName,String mode,byte[] imageBytes)
	{
		System.out.println("Step 1 populateImageUtilityVO"+imageUtilityConfiguratorDetails.primaryKey);
		String ftpLocation="";
		String fileNameStr="";
		
		
		String [] primaryKeyArray =null;
		String requestPrimaryKey=request.getParameter("primaryKey");
		System.out.println("Step 1 request primary key :"+requestPrimaryKey);
		
		if(requestPrimaryKey!=null && requestPrimaryKey.equals("")==false)
		{
			primaryKeyArray=requestPrimaryKey.replace("_", "#").split("#");
		}
		if(primaryKeyArray!=null && primaryKeyArray.length!=0)
		{
			for(int i=0;i<primaryKeyArray.length;i++)
			{
				ftpLocation="/"+primaryKeyArray[i];
				if(i==0)
				{
					fileNameStr=primaryKeyArray[i];
					}
				else
					fileNameStr="_"+primaryKeyArray[i];
			}
		}
		
		
		imageUtilityVO.setFileDesc(fileName);
		imageUtilityVO.setFileName(fileNameStr);
		imageUtilityVO.setOriginalFileName(fileName+".jpg");
		imageUtilityVO.setExtraFolder(imageUtilityConfiguratorDetails.getImageservletextrafolder());
		imageUtilityVO.setFileUrl(ftpLocation);;
		System.out.println("imageUtilityConfiguratorDetails.getImageservletftp()  -> "+imageUtilityConfiguratorDetails.getImageservletftp());
		imageUtilityVO.setFtpserver(imageUtilityConfiguratorDetails.getImageservletftp());
		if(ftpLocation!="")
		imageUtilityVO.setFtplocation(ftpLocation+"/"+imageUtilityConfiguratorDetails.getImageservletextrafolder());
		else
			imageUtilityVO.setFtplocation(imageUtilityConfiguratorDetails.getImageservletextrafolder());	
		imageUtilityVO.setImageByteArray(imageBytes);
	}
	
	
	private  void saveImageToDatabase(
			
			ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails,
			ImageUtilityVO imageUtilityVO, String mode) {
		System.out.println("Step 1 saveImageToDatabase");
		
		String imgtype="0";
		if(!mode.equals("IMAGE"))
		{
			imgtype="1";
		}
		
	
	
	//java.sql.Connection conn=this.getConnection();
	java.sql.Connection	conn=null;	
	java.sql.PreparedStatement pStmt;
	try {
		conn=DriverManager.getConnection( "jdbc:oracle:thin:@10.0.5.165:1521:AHIS","AHIS","AHIS");
		int counter=1;
		String max=null;
		System.out.println("Max Query  :"+imageUtilityConfiguratorDetails.imageservletdatabaseMaxQuery);
		pStmt = conn.prepareStatement(imageUtilityConfiguratorDetails.imageservletdatabaseMaxQuery);
		String [] primaryKeyArray =null;
		String fileName="";
		if(imageUtilityConfiguratorDetails.primaryKey!=null && imageUtilityConfiguratorDetails.primaryKey.equals("")==false)
		{
			primaryKeyArray=imageUtilityConfiguratorDetails.primaryKey.replace("_", "#").split("#");
		}
		
		
		if(primaryKeyArray!=null && primaryKeyArray.length!=0)
		{
			for(int i=1;i<=primaryKeyArray.length;i++)
			{
				pStmt.setString(i, primaryKeyArray[i-1]);
				fileName+=primaryKeyArray[i-1]+"_";
			}
		}
		
		java.sql.ResultSet rs=pStmt.executeQuery();
		while(rs.next())
		{
			max=rs.getString(1);
		}
		
		imageUtilityVO.setOriginalFileName(fileName+max+".jpg");
		counter=1;
		pStmt = conn.prepareStatement(imageUtilityConfiguratorDetails.imageservletdatabaseinsertQuery);
		System.out.println("Insert Query  :"+imageUtilityConfiguratorDetails.imageservletdatabaseinsertQuery);
		pStmt.setString(counter++, max);
		if(primaryKeyArray!=null && primaryKeyArray.length!=0)
		{
			for(int i=1;i<=primaryKeyArray.length;i++)
			{
				pStmt.setString(counter++, primaryKeyArray[i-1]);
			}
		}
		
		pStmt.setString(counter++, imageUtilityVO.getFileName());
		pStmt.setString(counter++, imgtype);
		pStmt.setString(counter++, imageUtilityVO.getFtpserver());
		pStmt.setString(counter++, imageUtilityVO.getFtplocation());
		pStmt.setString(counter++, imageUtilityVO.getOriginalFileName());
		pStmt.executeUpdate();
		saveFileToLocation(imageUtilityVO);
		
		
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
				e1.printStackTrace();
		}
		e.printStackTrace();
	}
	catch (IOException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
				e1.printStackTrace();
		}
		e.printStackTrace();
	}
	finally
	{
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connection is Already Closed");
			e.printStackTrace();
		}
	}
	
	
	
	
		
	}
	
	public void saveFileToLocation(ImageUtilityVO imageUtilityVO) throws IOException
	{
		System.out.println("Step 1 saveFileToLocation");
		InputStream in = new ByteArrayInputStream(imageUtilityVO.getImageByteArray());
		BufferedImage image;
		try {
			image = javax.imageio.ImageIO.read(in);
			in.close();
			System.out.println("file url :-->"+imageUtilityVO.getFtpserver()+imageUtilityVO.getFtplocation()+"/"+imageUtilityVO.getOriginalFileName());
			URL urlftp = new URL(imageUtilityVO.getFtpserver()+imageUtilityVO.getFtplocation()+"/"+imageUtilityVO.getOriginalFileName());
			URLConnection urlc = urlftp.openConnection();
			BufferedOutputStream bos = new BufferedOutputStream(urlc.getOutputStream());
			//ImageIO.write(image, "jpg", new File("c:/UplHello.jpg"));
			ImageIO.write(image, "jpg", bos);
			bos.close();
		} catch (IOException e) {
			
			e.printStackTrace();
			throw new IOException(); 
		}
		
	}
	
	
}
