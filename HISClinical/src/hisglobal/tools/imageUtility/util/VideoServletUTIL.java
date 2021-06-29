package hisglobal.tools.imageUtility.util;

import java.awt.image.BufferedImage;
import java.beans.Statement;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.persistence.ResultSet;
import hisglobal.utility.Entry;
import hisglobal.tools.imageUtility.fb.ImageServletFB;
import hisglobal.tools.imageUtility.fb.ImageUtilityFB;
import hisglobal.tools.imageUtility.fb.VideoServletFB;
import hisglobal.vo.ImageUtilityVO;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VideoServletUTIL {

	
	public static void populateVideoList(HttpServletRequest request,
			VideoServletFB fb ) {
		selectedList(request,"1",fb);
	}

	public static void selectedList(HttpServletRequest request,
			String listtype, VideoServletFB fb) {

		java.util.List imgLst = new ArrayList();
		java.util.List imgByteArrayLst = new ArrayList();
		java.util.List videoDisplayLst = new ArrayList();
		Map imgByteMap = new HashMap();

		java.util.List videoLst = new ArrayList();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.0.5.165:1521:AHIS", "AHIS", "AHIS");
			java.sql.Statement stmt = conn.createStatement();
			String sql = "select HOTNUM_FILE_UNIQUE_CODE, HOTSTR_FILE_NAME_DESC, HOTSTR_FILE_TYPE, HOTSTR_FTP_URL, HOTSTR_LOCATION from HOTT_VIDEOFILE_DTL where GNUM_ISVALID=1 and HOTSTR_FILE_TYPE='"+listtype.toString()+"' order by HOTNUM_FILE_UNIQUE_CODE";
			java.sql.ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {

				if (rs.getString(3).equals("0")) {
										

				} else {
					ImageUtilityVO imgVdoVO=new ImageUtilityVO();
					imgVdoVO.setFileCode(rs.getString(1));
					imgVdoVO.setFileDesc(rs.getString(2));
					imgVdoVO.setFileUrl(rs.getString(5) + "/" + rs.getString(2));
					videoLst.add(imgVdoVO);					
				}

			}
			conn.commit();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		int page=Integer.parseInt(fb.getCurrentPageNo());
		int recordPerPage=Integer.parseInt(fb.getRecordPerPage());
		
		settingthepageString(videoLst,fb);
		if(imgLst!=null)
		{
		for(int i=(page-1)*recordPerPage;((i<((page)*recordPerPage)))&&(i<(videoLst.size()));i++)
		{
			videoDisplayLst.add(videoLst.get(i));
		}
		}
		System.out.println("videoList size = "+videoLst.size());
		
		System.out.println("displayList size = "+videoDisplayLst.size());
		
		request.getSession().setAttribute("ALLVEDIOLIST", videoDisplayLst);		
		

	}

	
	public  static void settingthepageString(List sessionList,VideoServletFB fb)
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
	
	
	public static byte[] getByteArray(String file1) {

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
	public static void SaveVideo(String fileName,String ftpServer,String ftpLocation,String mode) throws ClassNotFoundException,SQLException
	{
		
		String imgtype="0";
		if(!mode.equals("IMAGE"))
		{
			imgtype="1";
		}
		
	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		java.sql.Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@10.0.5.165:1521:AHIS", "AHIS","AHIS");
		java.sql.Statement stmt=conn.createStatement();
		String sql = "Insert into HOTT_VIDEOFILE_DTL (HOTNUM_FILE_UNIQUE_CODE,HOTSTR_FILE_NAME_DESC,HOTSTR_FILE_TYPE,HOTSTR_FTP_URL,HOTSTR_LOCATION,HOTDT_ENTRY_DATE,GNUM_ISVALID) values ((select nvl(max(HOTNUM_FILE_UNIQUE_CODE)+1,1) from HOTT_VIDEOFILE_DTL),'"
			+ fileName.toString()
			+ "','"
			+ imgtype.toString()
			+ "','"
			+ ftpServer.toString()
			+ "','"
			+ ftpLocation.toString()
			+ "',sysdate,1)";
	stmt.executeUpdate(sql);
	conn.commit();
	stmt.close();
	conn.close();
		
		
	}

	

}
