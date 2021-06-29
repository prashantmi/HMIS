package mms.masters.controller.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;

import mms.masters.bo.CircularMstBO;
import mms.masters.controller.fb.CircularMstFB;
import mms.masters.vo.CircularMstVO;


// TODO: Auto-generated Javadoc
/**
 * The Class CircularMstDATA.
 * 
 * @author manas meher
 */

public class CircularMstDATA {

	/**
	 * to insert the data.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 */

	public static void insertRecord(CircularMstFB formBean,
			HttpServletRequest request) {

		String strmsgText = "";
		CircularMstVO vo = null;
		CircularMstBO bo = null;
		String osType = null;
		String paramValue=null;

		try {
			bo = new CircularMstBO();
			vo = new CircularMstVO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatid = request.getSession().getAttribute("SEATID")
					.toString();

			formBean.setStrHospitalCode(hosCode);
			formBean.setStrSeatId(seatid);
			vo.setStrSeatId(seatid);
			vo.setStrHospitalCode(hosCode);
			vo.setStrIsValid("1");
			vo.setStrCircularName(formBean.getStrCircularName());
			//vo.setStrRemarks(formBean.getStrRemarks());
			vo.setStrCircularDate(formBean.getStrCircularDate());
			vo.setStrCircularLink(formBean.getStrCircularLink());
			FormFile file=formBean.getStrCircularFileUploadx();
			System.out.println("file.getContentType()"+file.getContentType());
			System.out.println("File Name"+file.getFileData());
			System.out.println(file.getFileName());
			String fileetxn=file.getFileName().replace(".", "#").split("#")[1];
			String FileName=bo.getFileUploadName(vo);
			//file.setFileName(FileName);
			System.out.println(file.getFileName());
			try
			{
			osType = System.getProperties().getProperty("os.name");
			if(osType.startsWith("Win")){
				//_Param += "_WIN";
				//paramValue = getParameterFromXML("c:/RAOL/AHIMS/hisPath.xml", _Param);
				OutputStream out=new FileOutputStream(new File("C:\\NIMS\\AHIMSG5\\report"+FileName+"."+fileetxn));
				out.write(file.getFileData());
				out.close();
				//paramValue = getParameterFromXML("c:/NIMS/AHIMSG5/hisPath.xml", _Param);
			}else{
				OutputStream out=new FileOutputStream(new File("/opt/NIMS/AHIMSG5/hisPath.xml"+FileName+"."+fileetxn));
				out.write(file.getFileData());
				out.close();
			}
			
			}catch(Exception e){
				e.printStackTrace();
			}
			vo.setStrCircularSubject(formBean.getStrCircularSubject());
			bo.insertQuery(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			//if (vo.getBExistStatus() == false) {
			//	formBean.setStrWarning("Data already exist");
			//} else {
				formBean.setStrMsg("Data Saved Successfully");
		//	}

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "Circular Master.CircularMstDATA.insertRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"CircularMstDATA->insertRecord()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			vo = null;
			bo = null;
		}

	}

	/**
	 * to get the data for modify page.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 */

	public static void modifyRecord(CircularMstFB formBean,
			HttpServletRequest request) {

		CircularMstVO vo = null;
		CircularMstBO bo = null;
		String strtemp[] = null;
		String strtemp2[] = null;
		String strmsgText = "";
		String chk = "";

		try {
			bo = new CircularMstBO();
			vo = new CircularMstVO();

			chk = request.getParameter("chk");
			strtemp = chk.replace('@', '#').split("#");
			strtemp2 = strtemp[1].replace("$", "#").split("#");

			strtemp[1] = strtemp2[0];
			
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();

			String seatid = request.getSession().getAttribute("SEATID").toString();

			vo.setStrHospitalCode(hosCode);
			vo.setStrCircularId(strtemp[0]);
			vo.setStrSeatId(seatid);

			bo.modifyQuery(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			//System.out.println("vo.getStrCircularId()"+vo.getStrCircularId());
			formBean.setStrCircularId(vo.getStrCircularId());
			formBean.setStrCircularName(vo.getStrCircularName());
			formBean.setStrCircularSubject(vo.getStrCircularSubject());
			formBean.setStrCircularLink(vo.getStrCircularLink());
			formBean.setStrCircularDate(vo.getStrCircularDate());
		} catch (Exception e) {
			strmsgText = "Circular Master.CircularMstDATA.modifyRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"CircularMstDATA->modifyRecord()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			vo = null;
			bo = null;
		}
	}

	/**
	 * to update the record.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 * 
	 * @return true, if update record
	 */

	public static boolean updateRecord(CircularMstFB formBean,
			HttpServletRequest request) {
		CircularMstVO vo = null;
		CircularMstBO bo = null;
		boolean retValue = true;
//		String strtemp[] = null;
//		String strtemp2[] = null;
		String strmsgText = "";
//		String chk = "";
		try {
			bo = new CircularMstBO();
			vo = new CircularMstVO();
			
			// 14@100@6$3
			//chk = request.getParameter("chk");
			//strtemp = chk.replace('@', '#').split("#");
			//strtemp2 = strtemp[2].replace("$", "#").split("#");//6$3
			//strtemp[2] = strtemp2[0];

			String seatid = request.getSession().getAttribute("SEATID").toString();
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			
			//vo.setStrCircularId(strtemp[0]);
			
			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);
			vo.setStrCircularId(formBean.getStrCircularId());
			vo.setStrCircularName(formBean.getStrCircularName());
			vo.setStrCircularDate(formBean.getStrCircularDate());
			vo.setStrCircularLink(formBean.getStrCircularLink());
			vo.setStrCircularSubject(formBean.getStrCircularSubject());
			//vo.setStrIsValid(formBean.getStrIsValid());
			
			bo.updateRecord(vo);

			if (vo.getStrMsgType().equals("1")) {

				retValue = false;
				throw new Exception(vo.getStrMsgString());
			}

			
				if (vo.getStrMsgType().equals("1")) {
					retValue = false;
					throw new Exception(vo.getStrMsgString());
				} else {
					formBean.setStrMsg("Record Modified Successfully");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			retValue = false;
			strmsgText = "Circular Master.CircularMstDATA.updateRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"CircularMstDATA->updateRecord()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}

		return retValue;
	}

	/**
	 * Initial add.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 */
	public static void initialAdd(CircularMstFB formBean,
			HttpServletRequest request) {
		CircularMstVO vo = null;
		CircularMstBO bo = null;
		String strmsgText = "";
		HisUtil hisutil = null;
		try {
			hisutil = new HisUtil("mms", "ComponentMstBO");
			bo = new CircularMstBO();
			vo = new CircularMstVO();

			String ctDate = hisutil.getASDate("dd-MMM-yyyy");
			formBean.setStrCtDate(ctDate);

			bo.initAdd(vo);

		} catch (Exception e) {

			strmsgText = "Invoice Type Master.CircularMstDATA.initialAdd(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"CircularMstDATA->initialAdd()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
			hisutil = null;
		}

	}
}
