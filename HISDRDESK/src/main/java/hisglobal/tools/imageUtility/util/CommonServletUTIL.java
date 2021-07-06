package hisglobal.tools.imageUtility.util;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.tools.imageUtility.JakartaFtpWrapper;
import hisglobal.tools.imageUtility.fb.CommonServletFB;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ImageUtilityConfiguratorDetails;
import hisglobal.vo.ImageUtilityVO;
import hisglobal.vo.UserVO;
import investigation.InvestigationConfig;
import investigation.InvestigationStaticConfigurator;
import investigation.masters.TemplateProcessingClass;
import investigation.vo.HisAppletConfigurator;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.jscape.inet.ftp.FtpException;

public class CommonServletUTIL {

	public static void populateImageList(HttpServletRequest request,
			CommonServletFB fb) {
		
		
		ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails=(ImageUtilityConfiguratorDetails)request.getSession().getAttribute("ImageUtilityConfiguratorDetails");
		
		if(imageUtilityConfiguratorDetails.commonservletselectquery!=null)
		{
			fetchFromProvidedQuery(request, imageUtilityConfiguratorDetails, fb);
		}
		else if(imageUtilityConfiguratorDetails.commonservletsessionListName!=null)
		{
			fetchFromProvidedSessionList(request, imageUtilityConfiguratorDetails, fb);
		}
		else if(imageUtilityConfiguratorDetails.commonservletsessionName!=null)
		{
			fetchFromProvidedDirectSessionList(request, imageUtilityConfiguratorDetails, fb);
		}
		else
		{
			request.setAttribute("error", "No Configuration Found ! ");
		}
		
		
		
	}


	private static void fetchFromProvidedSessionList(HttpServletRequest request,ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails,CommonServletFB fb) 
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
				int recordPerPage=Integer.parseInt(fb.getRecordperpage());
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
				request.getSession().setAttribute("PAGECOMMONIMAGELIST",imageUtilityVOList);
				request.getSession().setAttribute("ALLCOMMONIMAGELIST",imageUtilityVOList);
				fb.setLengthofImageList(""+imageUtilityVOList.size());
			}
			
			
		}
		
	}
	
	private static void fetchFromProvidedDirectSessionList(HttpServletRequest request,ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails,CommonServletFB fb) 
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
			request.getSession().setAttribute("PAGECOMMONIMAGELIST",null);
			request.getSession().setAttribute("ALLCOMMONIMAGELIST",null);
		}
		else
		{
				System.out.println("sessionList --> "+sessionList.size());
				List<ImageUtilityVO> imageUtilityVOList=sessionList;
				List<ImageUtilityVO> imageUtilityDisplayVOList=new ArrayList<ImageUtilityVO>();
				int page=Integer.parseInt(fb.getCurrentPageNo());
				int recordPerPage=Integer.parseInt(fb.getRecordperpage());
				settingthepageString(imageUtilityVOList,fb);
				if(imageUtilityVOList!=null)
				{
				for(int i=(page-1)*recordPerPage;((i<((page)*recordPerPage)))&&(i<(imageUtilityVOList.size()));i++)
				{
					imageUtilityDisplayVOList.add(imageUtilityVOList.get(i));
					
				}
				}
				
				System.out.println("displayList size = "+imageUtilityVOList.size());
				request.getSession().setAttribute("PAGECOMMONIMAGELIST",imageUtilityDisplayVOList);
				request.getSession().setAttribute("ALLCOMMONIMAGELIST",imageUtilityVOList);
				fb.setLengthofImageList(""+imageUtilityVOList.size());
		}
			
			
		
		
	}
	

	public static void fetchFromProvidedQuery(HttpServletRequest request, ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails ,CommonServletFB fb) {

		java.util.List imgLst = new ArrayList();
		java.util.List imgDisplayLst = new ArrayList();
		java.util.List imgByteArrayLst = new ArrayList();
		//Map imgByteMap = new HashMap();
		java.util.List imgByteMap = new ArrayList();

		java.util.List videoLst = new ArrayList();
		try {
			Class.forName(InvestigationConfig.databaseDriver);
			java.sql.Connection conn = DriverManager.getConnection(hisglobal.persistence.Conn.getDatabaseConnection(), InvestigationStaticConfigurator.databaseusername,
					InvestigationStaticConfigurator.databasepassword);
			System.out.println("select Query "+imageUtilityConfiguratorDetails.commonservletselectquery);
			java.sql.PreparedStatement stmt = conn.prepareStatement(imageUtilityConfiguratorDetails.commonservletselectquery);
			imageUtilityConfiguratorDetails.primaryKey=fb.getPrimaryKey();
			String [] primaryKeyArray =null;
			System.out.println("DeptCode------------------------------------------------------------------------------->"+fb.getPrimaryKey());
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
		int recordPerPage=Integer.parseInt(fb.getRecordperpage());
		
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
		request.getSession().setAttribute("PAGECOMMONIMAGELIST",imgDisplayLst);
		request.getSession().setAttribute("ALLCOMMONIMAGELIST",imgLst);
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

	


	public  static void settingthepageString(List sessionList,CommonServletFB fb)
	{
		String Str="";
		int TotalNopages=(sessionList.size()%Integer.parseInt(fb.getRecordperpage())==0)?sessionList.size()/Integer.parseInt(fb.getRecordperpage()):sessionList.size()/Integer.parseInt(fb.getRecordperpage())+1;
		int pageperblock=Integer.parseInt(fb.getRecordperpage());
		int pagesperblock=Integer.parseInt(fb.getPagesperblock());
		int totalNoBlock=(TotalNopages%pagesperblock==0)?TotalNopages/pagesperblock:TotalNopages/pagesperblock+1;
		int block=Integer.parseInt(fb.getCurrentblock());
		int page=Integer.parseInt(fb.getCurrentPageNo());
		System.out.println("TotalNopages  ="+TotalNopages+"  totalNoBlock="+totalNoBlock+" block="+block+" page="+page);
		if(block>1)
		{
			Str+=Str+="<a name='prev'  onClick='prevSubmit()'><font Style='cursor:pointer'>  Prev  </font></a>";
		}
		for(int i=(block-1)*Integer.parseInt(fb.getRecordperpage())+1;i<=(block-1)*Integer.parseInt(fb.getRecordperpage())+Integer.parseInt(fb.getRecordperpage())&& i<=TotalNopages;i++)
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
	
	/*public  void SaveImage(HttpServletRequest request,String fileName,String mode,byte[] imageBytes) throws ClassNotFoundException,SQLException
	{
		System.out.println("Step 1 SaveImage");
		
		ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails=(ImageUtilityConfiguratorDetails)request.getSession().getAttribute("ImageUtilityConfiguratorDetails");
		ImageUtilityVO imageUtilityVO=new ImageUtilityVO();
		List<ImageUtilityVO> allImageList=(List<ImageUtilityVO>)request.getSession().getAttribute("ALLCOMMONIMAGELIST");
		if(allImageList==null)
			allImageList=new ArrayList<ImageUtilityVO>();
		
		allImageList.add(imageUtilityVO);
		request.getSession().setAttribute("ALLCOMMONIMAGELIST",allImageList);
		populateImageUtilityVO(request,imageUtilityConfiguratorDetails,imageUtilityVO,fileName, mode,imageBytes );
		
		if(imageUtilityConfiguratorDetails.getCommonservletdatabaseinsertQuery()!=null && imageUtilityConfiguratorDetails.getCommonservletdatabaseinsertQuery().equals("")==false)
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
			request.getSession().setAttribute(imageUtilityConfiguratorDetails.getImageservletsessionName(),(List<ImageUtilityVO>)request.getSession().getAttribute("ALLCOMMONIMAGELIST"));
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
										
										mp.put(request.getParameter("mapKey"), (List<ImageUtilityVO>)request.getSession().getAttribute("ALLCOMMONIMAGELIST"));
										cls2Methods[i].invoke(sessionObject,mp);
										System.out.println(request.getParameter("mapKey")+"setting the map of session"+((java.util.Map<java.lang.String, java.util.List<hisglobal.vo.ImageUtilityVO>>)cls2Methods[j].invoke(sessionObject,null)));
									}
								}
								
									
							}
							else
							{
								System.out.println("setting the list");
								cls2Methods[i].invoke(sessionObject,(List<ImageUtilityVO>)request.getSession().getAttribute("ALLCOMMONIMAGELIST"));
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

	*//*  important to populate the ImageUtilityVO correctly*/
	/*public void populateImageUtilityVO(HttpServletRequest request ,ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails,ImageUtilityVO imageUtilityVO,String fileName,String mode,byte[] imageBytes)
	{
		System.out.println("Step 1 populateImageUtilityVO"+imageUtilityConfiguratorDetails.primaryKey);
		String ftpLocation="";
		
		
		String [] primaryKeyArray =null;
		String requestPrimaryKey=request.getParameter("primaryKey");
		System.out.println("Step 1 request primary key :"+requestPrimaryKey);
		
		if(requestPrimaryKey!=null && requestPrimaryKey.equals("")==false)
		{
			primaryKeyArray=requestPrimaryKey.replace("_", "#").split("#");
		}
		if(primaryKeyArray!=null && primaryKeyArray.length!=0)
		{
			for(int i=1;i<=primaryKeyArray.length;i++)
			{
				ftpLocation="/"+primaryKeyArray[i-1];
			}
		}
		
		
		imageUtilityVO.setFileDesc(fileName);
		imageUtilityVO.setFileName(fileName);
		imageUtilityVO.setOriginalFileName(fileName+".jpg");
		imageUtilityVO.setExtraFolder(imageUtilityConfiguratorDetails.getImageservletextrafolder());
		imageUtilityVO.setFileUrl(ftpLocation);;
		imageUtilityVO.setFtpserver(imageUtilityConfiguratorDetails.getImageservletftp());
		if(ftpLocation!="")
		imageUtilityVO.setFtplocation(ftpLocation+"/"+imageUtilityConfiguratorDetails.getImageservletextrafolder());
		else
			imageUtilityVO.setFtplocation(imageUtilityConfiguratorDetails.getImageservletextrafolder());	
		imageUtilityVO.setImageByteArray(imageBytes);
	}
	
	*/
	/*private  void saveImageToDatabase(
			
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
	
	*/
	public  void SaveCommonImage(HttpServletRequest request,String fileName,String mode,byte[] imageBytes, String screenType, String primaryKey) throws ClassNotFoundException,SQLException
	{
		System.out.println("Step 1 SaveCommonImage");
		System.out.println("DATA---------------->"+request.getParameter("ScreenType"));
		UserVO userVO=ControllerUTIL.getUserVO(request);
		ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails=(ImageUtilityConfiguratorDetails)request.getSession().getAttribute("ImageUtilityConfiguratorDetails");
		ImageUtilityVO imageUtilityVO=new ImageUtilityVO();
		List<ImageUtilityVO> allImageList=(List<ImageUtilityVO>)request.getSession().getAttribute("ALLCOMMONIMAGELIST");
		if(allImageList==null)
			allImageList=new ArrayList<ImageUtilityVO>();
		
		allImageList.add(imageUtilityVO);
		request.getSession().setAttribute("ALLCOMMONIMAGELIST",allImageList);
		populateCommonImageUtilityVO(request,imageUtilityConfiguratorDetails,imageUtilityVO,fileName, mode,imageBytes,primaryKey );
		
		if(screenType.equals("COMMON"))
		{
			
			if(imageUtilityConfiguratorDetails.getCommonservletdatabaseinsertQuery()!=null && imageUtilityConfiguratorDetails.getCommonservletdatabaseinsertQuery().equals("")==false)
			{
				saveCommonImageToDatabase(imageUtilityConfiguratorDetails, imageUtilityVO, mode,primaryKey,userVO);
			}
		}
	
		
		
}

	public void populateCommonImageUtilityVO(HttpServletRequest request ,ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails,ImageUtilityVO imageUtilityVO,String fileName,String mode,byte[] imageBytes, String primaryKey)
	{
		System.out.println("Step 1 populateCommonImageUtilityVO"+imageUtilityConfiguratorDetails.primaryKey);
		String ftpLocation="";
		
		
		String [] primaryKeyArray =null;
		String requestPrimaryKey=primaryKey;//request.getParameter("primaryKey2");
		System.out.println("Step 1 request primary key :"+requestPrimaryKey);
		
		if(requestPrimaryKey!=null && requestPrimaryKey.equals("")==false)
		{
			primaryKeyArray=requestPrimaryKey.replace("_", "#").split("#");
		}
		if(primaryKeyArray!=null && primaryKeyArray.length!=0)
		{
			if(primaryKeyArray.length>1)
			{
				for(int i=1;i<=primaryKeyArray.length;i++)
				{
					ftpLocation="/"+primaryKeyArray[i-1];
				}
			}
			else
			{
				ftpLocation=primaryKeyArray[0];
			}
		}
		
		System.out.println("ftpLocation--------->"+ftpLocation);
		imageUtilityVO.setFileDesc(fileName);
		imageUtilityVO.setFileName(fileName);
		imageUtilityVO.setOriginalFileName(fileName+".jpg");
		imageUtilityVO.setExtraFolder(imageUtilityConfiguratorDetails.getCommonservletextrafolder());
		imageUtilityVO.setFileUrl(ftpLocation);;
		
		imageUtilityVO.setFtpserver(imageUtilityConfiguratorDetails.getCommonservletftp());
		System.out.println(imageUtilityVO.getFtpserver()+imageUtilityVO.getFtpserver().substring(imageUtilityVO.getFtpserver().lastIndexOf("/")));
		imageUtilityVO.setFtpserver(imageUtilityVO.getFtpserver()+imageUtilityVO.getFtpserver().substring(imageUtilityVO.getFtpserver().lastIndexOf("/")));
		
		System.out.println(imageUtilityVO.getFtpserver()+imageUtilityVO.getFtpserver().substring(imageUtilityVO.getFtpserver().lastIndexOf("/")));
		/*
		 imageUtilityVOObj.setFileUrl(imageUtilityVOObj.getFtpserver()+imageUtilityVOObj.getFtpserver().substring(imageUtilityVOObj.getFtpserver().lastIndexOf("/"))+"/"+test.getRequisitionDNo()+"/"+imageUtilityVOObj.getExtraFolder()+imageUtilityVOObj.getFtplocation());
			imageUtilityVOObj.setSavingFTPUrl(imageUtilityVOObj.getFtpserver()+"/"+test.getRequisitionDNo()+"/"+imageUtilityVOObj.getExtraFolder()+imageUtilityVOObj.getFtplocation());
			
		 * */
		if(ftpLocation!="")
		imageUtilityVO.setFtplocation(ftpLocation+"/"+imageUtilityConfiguratorDetails.getCommonservletextrafolder());
		else
		imageUtilityVO.setFtplocation(imageUtilityConfiguratorDetails.getCommonservletextrafolder());	
		
		imageUtilityVO.setImageByteArray(imageBytes);	
		
		imageUtilityVO.setSavingFTPUrl(imageUtilityVO.getFtpserver()+"/"+imageUtilityVO.getFtplocation());
		System.out.println("Save Ftp URL--------->"+imageUtilityVO.getSavingFTPUrl());
		
	}
	
	
private  void saveCommonImageToDatabase(
			
			ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails,
			ImageUtilityVO imageUtilityVO, String mode, String primaryKey, UserVO userVO) {
			System.out.println("Step 1 saveCommonImageToDatabase------>");
			
			
			String imgtype="0";
			if(!mode.equals("IMAGE"))
			{
				imgtype="1";
			}
			
		
		
		//java.sql.Connection conn=this.getConnection();
		java.sql.Connection	conn=null;	
		java.sql.PreparedStatement pStmt;
		try {
			
			conn = DriverManager.getConnection(hisglobal.persistence.Conn.getDatabaseConnection(), InvestigationConfig.databaseuserName, InvestigationConfig.databasepassword);
			
		
			int counter=1;
			String max=null;
			System.out.println("Max Query  :"+imageUtilityConfiguratorDetails.commonservletdatabaseMaxQuery);
			pStmt = conn.prepareStatement(imageUtilityConfiguratorDetails.commonservletdatabaseMaxQuery);
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
					System.out.println("primaryKeyArray[i-1]---------->"+primaryKeyArray[i-1]);
					pStmt.setString(i, primaryKeyArray[i-1]);
					fileName+=primaryKeyArray[i-1]+"_";
				}
			}
			
			java.sql.ResultSet rs=pStmt.executeQuery();
			while(rs.next())
			{
				max=rs.getString(1);
			}
			System.out.println("max Unique Id---->"+max);
			imageUtilityVO.setOriginalFileName(fileName+max+".jpg");
			counter=1;
			pStmt = conn.prepareStatement(imageUtilityConfiguratorDetails.commonservletdatabaseinsertQuery);
			System.out.println("Insert Query  :"+imageUtilityConfiguratorDetails.commonservletdatabaseinsertQuery);			
			
			if(primaryKeyArray!=null && primaryKeyArray.length!=0)
			{
				for(int i=1;i<=primaryKeyArray.length;i++)
				{
					pStmt.setString(counter++, primaryKeyArray[i-1]);
				}
			}
			pStmt.setString(counter++, max);
			pStmt.setString(counter++, userVO.getHospitalCode());
			pStmt.setString(counter++, imageUtilityVO.getFileName());
			pStmt.setString(counter++, imgtype);
			pStmt.setString(counter++, imageUtilityVO.getFtpserver());
			pStmt.setString(counter++, userVO.getSeatId());
			pStmt.setString(counter++, imageUtilityVO.getFtplocation());
			pStmt.setString(counter++, imageUtilityVO.getOriginalFileName());
			pStmt.executeUpdate();
			try {
				saveFileToLocation(imageUtilityVO);
			} catch (FtpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
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

public static void saveFileToLocation(ImageUtilityVO imageUtilityVO) throws IOException, FtpException
{
	System.out.println("Step 1 saveFileToLocation");
	InputStream in = new ByteArrayInputStream(imageUtilityVO.getImageByteArray());
	BufferedImage image;
	

	
	try {
		image = javax.imageio.ImageIO.read(in);
		in.close();
		
		String ftpUrl[]= imageUtilityVO.getFileUrl().replace("/", "#").split("#");
		System.out.println("after------------->"+imageUtilityVO.getSavingFTPUrl());
		URL urlftp =new URL(imageUtilityVO.getSavingFTPUrl()+"/"+imageUtilityVO.getOriginalFileName());;
		System.out.println("urlftp-------------->"+urlftp);
		URLConnection urlc=urlftp.openConnection();
		
		BufferedOutputStream bos = null ;
		try
		{
			bos=new BufferedOutputStream(urlc.getOutputStream());
		}
		catch(Exception ex)
		{
			System.out.println("  catch ");
		}
		System.out.println("urlftp  :"+urlc);
		if(bos==null)
		{
			String[] folder=(imageUtilityVO.getSavingFTPUrl()).replace("/", "#").split("#");			
			createDirectoryStructure(folder[2],folder);
			
			//bos=new BufferedOutputStream(urlc.getOutputStream());
		}
		System.out.println("urlftp  :"+urlc);
		
	
		//ImageIO.write(image, "jpg", new File("c:/UplHello.jpg"));
		ImageIO.write(image, "jpg", bos);
		bos.close();
		
	} catch (IOException e) {
		
		e.printStackTrace();
		throw new IOException();
	}
	
}

private static void createDirectoryStructure(String ftpserver, String[] folders) {
	
	
	try {
		JakartaFtpWrapper ftp = new JakartaFtpWrapper();
		System.out.println("Connecting to " + ftpserver);
		
		HisAppletConfigurator hisAppletConfigurator=new HisAppletConfigurator();
		new TemplateProcessingClass(1,"101").readingAppletConfiguratorXML(hisAppletConfigurator);
		
		
		String ftpUserName=hisAppletConfigurator.getResultprintingusername();
		String ftpUserPassword=hisAppletConfigurator.getResultprintinguserpassword();
		
		
		
		if (ftp.connectAndLogin(ftpserver, ftpUserName, ftpUserPassword)) {
			System.out.println("Connected to " + ftpserver);
			try {
				ftp.setPassiveMode(true);
				
				ftp.changeWorkingDirectory("ftpserver");
				System.out.println("Present Working Directory :"+ftp.pwd());
				for(int i=4;i<folders.length;i++)
				{
					System.out.println("directory "+folders[i]+" created");
					ftp.mkd(folders[i]);
					ftp.changeWorkingDirectory(folders[i]);
				}
				
			} catch (Exception ftpe) {
				ftpe.printStackTrace();
			} finally {
				ftp.logout();
				ftp.disconnect();
			}
		} else {
			System.out.println("Unable to connect to" + ftpserver);
		}
		System.out.println("Finished");
	} catch(Exception e) {
		e.printStackTrace();
	}
}

}
