package hisglobal.tools.imageUtility.util;



import hisglobal.tools.imageUtility.fb.ImageUtilityFB;
import hisglobal.vo.ImageUtilityConfiguratorDetails;
import hisglobal.vo.ImageUtilityVO;
import investigation.masters.TemplateProcessingClass;
import investigation.vo.HisAppletConfigurator;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Document;

public class ImageUtilityUTIL {

	public static void populateFormbeanWithUserConfigxml(
			HttpServletRequest request, ImageUtilityFB fb,ServletContext sc) {
		try {
			Document xmlConfigurator=(Document)sc.getAttribute("CONFIGURATORXML");
			ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails=new ImageUtilityConfiguratorDetails();
			imageUtilityConfiguratorDetails.id=fb.getId();
			new TemplateProcessingClass(1,"101").readingAppletUserInformationXML(imageUtilityConfiguratorDetails);
			
			HisAppletConfigurator hisAppletConfigurator=new HisAppletConfigurator();
			new TemplateProcessingClass(1,"101").readingAppletConfiguratorXML(hisAppletConfigurator);
			
			fb.setImageservlet(imageUtilityConfiguratorDetails.getImageservletvalue());
			fb.setVideoservlet(imageUtilityConfiguratorDetails.getVideoservletvalue());
			fb.setPlayerApplet("0");
			request.getSession().setAttribute("ImageUtilityConfiguratorDetails", imageUtilityConfiguratorDetails);
			request.getSession().setAttribute("HISAppletConfigurator", hisAppletConfigurator);
			imageUtilityConfiguratorDetails.setPrimaryKey(fb.getPrimaryKey());
			
			System.out.println("PrimaryKey:::  "+imageUtilityConfiguratorDetails.getPrimaryKey());
		
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}

	public static void populateImageList(HttpServletRequest request,
			ImageUtilityFB fb, ServletContext sc) {
		selectedList(request,"0",sc);
	}

	public static void selectedList(HttpServletRequest request,
			String listtype, ServletContext sc) {

		java.util.List imgLst = new ArrayList();
		java.util.List imgByteArrayLst = new ArrayList();
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
					// rs.getString(4)+"/"+rs.getString(5)+"/"+rs.getString(2)
					ImageUtilityVO imgVdoVO=new ImageUtilityVO();
					imgVdoVO.setFileCode(rs.getString(1));
					imgVdoVO.setFileDesc(rs.getString(2));
					imgVdoVO.setFileUrl(rs.getString(4)+ "/" + rs.getString(5) + "/" + rs.getString(2));
					imgLst.add(imgVdoVO);
					imgByteMap.put(imgVdoVO.getFileCode(), getByteArray(rs.getString(4)+ "/" + rs.getString(5) + "/" + rs.getString(2)));
					

				} else {
					ImageUtilityVO imgVdoVO=new ImageUtilityVO();
					imgVdoVO.setFileCode(rs.getString(1));
					imgVdoVO.setFileDesc(rs.getString(2));
					imgVdoVO.setFileUrl(rs.getString(4)+ "/" + rs.getString(5) + "/" + rs.getString(2));
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
		request.getSession().setAttribute("ALLIMAGELIST", imgLst);
		request.getSession().setAttribute("ALLVEDIOLIST", videoLst);
		request.getSession().setAttribute("IMAGEARRAYLIST", imgByteMap);
		sc.setAttribute("BYTEARRAYLIST", imgByteMap);

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

	public void settingthecannedpageString(List sessionList,ImageUtilityFB fb)

	{
	    String Str="";
	    int TotalNopages=(sessionList.size()%Integer.parseInt(fb.getRecordPerPage())==0)?sessionList.size()/Integer.parseInt(fb.getRecordPerPage()):sessionList.size()/Integer.parseInt(fb.getRecordPerPage())+1;
	    int pageperblock=Integer.parseInt(fb.getRecordPerPage());
	    int pagesperblock=Integer.parseInt(fb.getPagesPerBlock());
	    int totalNoBlock=(TotalNopages%pagesperblock==0)?TotalNopages/pagesperblock:TotalNopages/pagesperblock+1;
	    int block=1;
	    int page=1;
	    System.out.println("TotalNopages  ="+TotalNopages+"  totalNoBlock="+totalNoBlock+" block="+block+" page="+page);
	    for(int i=1;i<=TotalNopages;i++)
	    {
	        if((i%pagesperblock)==1)
	        {
	            if(((i/pagesperblock)+1)==block)
	            Str+="<div id='block"+((i/pagesperblock)+1)+"' style='display:block' >";
	            else
	            Str+="<div id='block"+((i/pagesperblock)+1)+"' style='display:none' >";
	            if(((i/pagesperblock)+1)>1)
	            {
	            Str+="<a name='prev'  onClick='prevSubmit()'><font Style='cursor:pointer'>  Prev  </font></a>";
	            }
	        }
	        if(i==page)
	        {
	            Str+="<a name=a"+i+" value="+i+" onClick='return PageSubmit(\""+i+"\",\"GO\")' style='color:red'><font Style='cursor:default'>&nbsp;"+i+"</font></a>";                               
	        }
	        else
	            Str+="<a name=a"+i+" value="+i+" onClick='return PageSubmit(\""+i+"\",\"GO\")' style='color:blue'><font Style='cursor:pointer'>&nbsp;"+i+"</font></a>";
	        if((i%pagesperblock)==0)
	        {
	            if(((i/pagesperblock))+1<=totalNoBlock)
	                {
	                    Str+="<a name='next'  onClick='nextSubmit()'><font Style='cursor:pointer'>  next  </font></a>";
	                }
	             Str+="</div>";
	        }

	    }
	    Str+="</div>";
	    System.out.println("Str   ="+Str);
	    fb.setPageString(Str);
	}

}
