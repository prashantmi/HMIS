package hisglobal.tools.imageeditor;

/*This is my servlet program*/

import hisglobal.tools.imageUtility.util.ImageServletUTIL;
import  hisglobal.tools.imageUtility.util.VideoServletUTIL;
import hisglobal.vo.ImageUtilityConfiguratorDetails;
import hisglobal.vo.ImageUtilityVO;
import investigation.InvestigationCommServlet;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;




import javax.imageio.ImageIO;



import javax.servlet.*;
import javax.servlet.http.*;




public class PipelineServlet extends HttpServlet {
	private Object receiveObject(HttpServletRequest req) throws Exception {
		ObjectInputStream in = new ObjectInputStream(req.getInputStream());
		Object obj = in.readObject();
		in.close();
		return obj;
	}

	private void sendObject(HttpServletResponse resp, Object obj)
			throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(resp.getOutputStream());
		out.writeObject(obj);
		out.close();
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) {

		
		ServletContext sc = getServletContext();
		try {
			ObjectInputStream oin = null;
			try {

				oin = new ObjectInputStream(req.getInputStream());

				String mode = (String) oin.readObject();
				if (mode.equals("GETBYTE")) {
					String fileValue = (String) oin.readObject();
					System.out.println("PipelineServlet:GETBYTE: ScreenType----->"+req.getParameter("ScreenType"));
					java.util.List imgVo=new ArrayList();
					
					if(req.getParameter("ScreenType").equals("IMAGE"))
					imgVo=(java.util.List)req.getSession().getAttribute("PAGEIMAGELIST");
					else if(req.getParameter("ScreenType").equals("COMMON"))
					imgVo=(java.util.List)req.getSession().getAttribute("PAGECOMMONIMAGELIST");
					
					System.out.println("imgVo"+((imgVo!=null)?imgVo.size():null));
					
					byte[] outbyte = (byte[])((ImageUtilityVO)imgVo.get(Integer.parseInt(fileValue))).getImageByteArray();
					/*InputStream in = new ByteArrayInputStream(outbyte);
					BufferedImage image = javax.imageio.ImageIO.read(in);
					ImageIO.write(image, "jpg",new  File("c:/byteImageIII.jpg"));*/
										
					
					ObjectOutputStream oos=new ObjectOutputStream(resp.getOutputStream());
					oos.writeObject(outbyte);
					oos.close();
					oin.close();
					
					/*resp.getOutputStream().flush();
					resp.getOutputStream().write(outbyte);
					*///sendObject(resp, outbyte);
					System.out.println("PipelineServlet:ImageCreated and send Object GETBYTE");
					// byteoutlis.get(i).toString()

					// byteoutlis.get(i).toString()

				} else if (mode.equals("SAVEIMAGE")) {					
					
					System.out.println("PipelineServlet:saveimage");
										
					java.util.List imgVolist=(java.util.List)req.getSession().getAttribute("ALLIMAGELIST");		
					ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails=(ImageUtilityConfiguratorDetails)req.getSession().getAttribute("ImageUtilityConfiguratorDetails");
					if(imgVolist==null)
						imgVolist=new ArrayList<ImageUtilityVO>();
					
					byte[] bytesOut = (byte[]) oin.readObject();					
					String finalFileName = (String) oin.readObject();					
					System.out.println("finalFileName :::    "+finalFileName+"<======>");
					ImageServletUTIL util=new ImageServletUTIL();
					ImageUtilityVO imageVO=new ImageUtilityVO();
					util.populateImageUtilityVO(req,imageUtilityConfiguratorDetails, imageVO, finalFileName, mode, bytesOut);
					
					
					//imageVO.setFileDesc(finalFileName);
					//imageVO.setImageByteArray(bytesOut);
					
					imgVolist.add(imageVO);
					
					req.getSession().setAttribute("ALLIMAGELIST", imgVolist);
					
					
					/*
					 * byte[] bytesOut = (byte[]) oin.readObject();
					String finalFtpUrlWithfileName = (String) oin.readObject();
					String finalFileName = (String) oin.readObject();
					System.out.println("in Servlet: finalFtpUrlWithfileName :"
							+ finalFtpUrlWithfileName+"  FileName"+finalFileName);
					InputStream in = new ByteArrayInputStream(bytesOut);
					BufferedImage image = javax.imageio.ImageIO.read(in);
					in.close();
					// /ImageIO.write(image, "jpg",new File("c:/abc.jpg"));
					System.out.println(finalFtpUrlWithfileName + finalFileName);
					URL urlftp = new URL(finalFtpUrlWithfileName + "/Image/"
							+ finalFileName);
					URLConnection urlc = urlftp.openConnection();
					BufferedOutputStream bos = new BufferedOutputStream(urlc
							.getOutputStream());

					ImageIO.write(image, "jpg", new File("c:/Hello.jpg"));
					ImageIO.write(image, "jpg", bos);
					bos.close();
					oin.close();
					
					 */	
					
					util.saveImageToSession(imageUtilityConfiguratorDetails, imageVO, mode, req);
					String str = "ImageCreated and send Object";
					sendObject(resp, str);
					System.out.println("PipelineServlet:ImageCreated and send Object SAVEIMAGE");

				} else if (mode.equals("IMAGEEDIT")) {
					
					String str = "";
					if(req.getParameter("ScreenType").equals("IMAGE"))
					{
						java.util.List imgVolist=(java.util.List)req.getSession().getAttribute("ALLIMAGELIST");					
						ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails=(ImageUtilityConfiguratorDetails)req.getSession().getAttribute("ImageUtilityConfiguratorDetails");
						
						byte[] bytesOut = (byte[]) oin.readObject();
						String finalFtpUrl = (String) oin.readObject();
						String finalFileName = (String) oin.readObject();
						String openIndex=(String)oin.readObject();
						System.out.println("finalFileName :::    "+finalFileName+"<======>"+"openIndex--->"+openIndex);
						
						ImageUtilityVO imageVO=(ImageUtilityVO)imgVolist.get(Integer.parseInt(openIndex));
						ImageServletUTIL util=new ImageServletUTIL();
						util.populateImageUtilityVO(req,imageUtilityConfiguratorDetails, imageVO, imageVO.getFileDesc(), mode, bytesOut);
						str = "send Object IMAGEEDIT IMAGE MODE--------------->";
					}
					else
					{
						str = "send Object IMAGEEDIT COMMON MODE--------------->------------>";
					}
					
					sendObject(resp, str);
					System.out.println("PipelineServlet:IMAGEEDIT------>"+str);

				} else if (mode.equals("SAVEASIMAGEEDIT")) {

					
					
					ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails=(ImageUtilityConfiguratorDetails)req.getSession().getAttribute("ImageUtilityConfiguratorDetails");
					java.util.List imgVolist=(java.util.List)req.getSession().getAttribute("ALLIMAGELIST");					
					
					
					byte[] bytesOut = (byte[]) oin.readObject();
					String finalFtpUrl = (String) oin.readObject();
					String finalFileName = (String) oin.readObject();
					String openIndex=(String)oin.readObject();
					System.out.println("finalFileName :::    "+finalFileName+"<--openIndex--->"+openIndex);
					
					//String fileName=finalFileName.replace("/", "#").split("#")[1].replace(".","#").split("#")[0];
					ImageUtilityVO imageVO=new ImageUtilityVO();
					imageVO.setFileDesc(finalFileName);				
					imageVO.setImageByteArray(bytesOut);
					ImageServletUTIL util=new ImageServletUTIL();
					System.out.println(imgVolist+"imgVolist");
					if(imgVolist==null || imgVolist.size()==0)
					{
						
						imgVolist=new ArrayList();
						req.getSession().setAttribute("ALLIMAGELIST",imgVolist);
					}
					
					imgVolist.add(imageVO);					
					util.populateImageUtilityVO(req,imageUtilityConfiguratorDetails, imageVO, finalFileName, mode, bytesOut);
					if(req.getParameter("ScreenType").equals("COMMON"))
					{
						util.saveImageToSession(imageUtilityConfiguratorDetails,imageVO,mode,req);			
						System.out.println("Set Image In Session COMMON Mode------------>");
					}
										
					String str = "ImageCreated and send Object";
					sendObject(resp, str);
					System.out.println("PipelineServlet:SAVEASIMAGEEDIT and send Object SAVEASIMAGEEDIT");

				} else {

					try {
						System.out.println("inside servlet");

												
						
						ByteArrayOutputStream bos = (ByteArrayOutputStream) oin
								.readObject();

						String finalFtpUrlWithfileName = (String) oin
								.readObject();
						String finalFileName = (String) oin.readObject();

						URL urlftp = new URL(finalFtpUrlWithfileName
								+ "/Video/" + finalFileName);
						
						URLConnection urlc = urlftp.openConnection();
						
						StringBuffer sb = new StringBuffer( "ftp://10.0.5.152/" );
					  	 
				    	 sb.append( "ftpserver" );
				         sb.append( "/Video/" );
				         sb.append( finalFileName );
				         /*
							 * type ==> a=ASCII mode, i=image (binary) mode, d= file
							 * directory listing
							 */
				         sb.append( ";type=i" );

				         BufferedInputStream bis = null;
				         
				         URL urlftp1 = new URL( sb.toString() );
			             URLConnection urlc1 = urlftp1.openConnection();
			             BufferedOutputStream buos = new BufferedOutputStream(urlc1.getOutputStream() );
						
						
						

						ByteArrayInputStream byteIn = new ByteArrayInputStream(
								bos.toByteArray());
						int c;
						while ((c = byteIn.read()) != -1) {
							buos.write((char) c);
						}
						buos.close();

						VideoServletUTIL.SaveVideo(finalFileName,
								finalFtpUrlWithfileName, "Video", "VIDEO");
						String str = "VideoCreates and send Object";
						sendObject(resp, str);
						System.out.println("VideoCreates and send Object");
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
			

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (Exception e) {
			System.out.println("Clang! Thunk: " + e);
		}
	}

	
	
}

/*
 * StringBuffer sb = new StringBuffer( "ftp://10.0.5.152/" );
 * 
 * sb.append( "ftpserver" ); sb.append( '/' ); sb.append( "09sep20999909.jpg" );
 * 
 * type ==> a=ASCII mode, i=image (binary) mode, d= file directory listing
 * 
 * sb.append( ";type=i" );
 * 
 * BufferedInputStream bis = null; BufferedOutputStream bos = null;
 * 
 * try { System.out.println("inside servlet"); System.out.println("getting the
 * image from applet");
 * 
 * URL urlftp = new URL( sb.toString() ); URLConnection urlc =
 * urlftp.openConnection(); bos = new BufferedOutputStream(
 * urlc.getOutputStream() );
 *  // InputStream in = req.getInputStream();
 * 
 * bis = new BufferedInputStream( req.getInputStream() );
 * 
 * byte[] buf = new byte[256]; int nread = 0, total_read = 0;
 * 
 * while (-1 != (nread = bis.read(buf))) { total_read += nread; bos.write(buf,
 * 0, nread); } System.out.println("bytes read " + total_read); bos.close();
 * 
 * System.out.println("ImageCreated");
 */

/*
 * String fileFolder="c:/ImageFile"; File folder = new File(fileFolder); File[]
 * listOfFiles = folder.listFiles(); if(listOfFiles!=null) { int count=1; for
 * (int i = 0; i < listOfFiles.length; i++) { if (listOfFiles[i].isFile()) {
 * System.out.println("File " + listOfFiles[i].getName());
 * if(listOfFiles[i].getName().replace(".", "#").split("#")[1].equals("jpg")) {
 * count++; }
 *  } else if (listOfFiles[i].isDirectory()) { System.out.println("Directory " +
 * listOfFiles[i].getName()); } }
 * fileFolder=fileFolder+"/"+"capture"+count+".jpg";
 *  } else { fileFolder=fileFolder+"/"+"capture1.jpg"; }
 * 
 * File filename = new File(fileFolder); FileOutputStream outy = new
 * FileOutputStream(filename); InputStream in = req.getInputStream(); byte[] buf =
 * new byte[256]; int nread = 0, total_read = 0;
 * 
 * while (-1 != (nread = in.read(buf))) { total_read += nread; outy.write(buf,
 * 0, nread); } System.out.println("bytes read " + total_read); outy.close();
 */
/*
 * public static boolean ConvertByteArrayToImage(byte[] byteArrayIn) { boolean
 * flag=false; try { //Before is how to change ByteArray back to Image
 * ByteArrayInputStream bis = new ByteArrayInputStream(byteArrayIn); Iterator<?>
 * readers = ImageIO.getImageReadersByFormatName("jpg"); //ImageIO is a class
 * containing static convenience methods for locating ImageReaders //and
 * ImageWriters, and performing simple encoding and decoding.
 * 
 * ImageReader reader = (ImageReader) readers.next(); Object source = bis; //
 * File or InputStream, it seems file is OK
 * 
 * ImageInputStream iis = ImageIO.createImageInputStream(source); //Returns an
 * ImageInputStream that will take its input from the given Object
 * 
 * reader.setInput(iis, true); ImageReadParam param =
 * reader.getDefaultReadParam();
 * 
 * Image image = reader.read(0, param); //got an image file
 * 
 * BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
 * image.getHeight(null), BufferedImage.TYPE_INT_RGB); //bufferedImage is the
 * RenderedImage to be written Graphics2D g2 = bufferedImage.createGraphics();
 * g2.drawImage(image, null, null); File imageFile = new
 * File("C:\\newrose2.jpg"); ImageIO.write(bufferedImage, "jpg", imageFile);
 * //"jpg" is the format of the image //imageFile is the file to be written to.
 * 
 * System.out.println(imageFile.getPath()); flag=true; } catch (Exception e) { //
 * TODO: handle exception } return flag;
 *  }
 */
/*
 * public static BufferedImage toImage(int w, int h, byte[] data) { DataBuffer
 * buffer = new DataBufferByte(data, w*h);
 * 
 * int pixelStride = 4; //assuming r, g, b, skip, r, g, b, skip... int
 * scanlineStride = 4*w; //no extra padding int[] bandOffsets = {0, 1, 2}; //r,
 * g, b WritableRaster raster = Raster.createInterleavedRaster(buffer, w, h,
 * scanlineStride, pixelStride, bandOffsets, null);
 * 
 * ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_sRGB); boolean
 * hasAlpha = false; boolean isAlphaPremultiplied = false; int transparency =
 * Transparency.OPAQUE; int transferType = DataBuffer.TYPE_BYTE; ColorModel
 * colorModel = new ComponentColorModel(colorSpace, hasAlpha,
 * isAlphaPremultiplied, transparency, transferType);
 * 
 * return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null); }
 */
