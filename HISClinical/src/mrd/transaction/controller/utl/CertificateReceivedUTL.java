package mrd.transaction.controller.utl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.CertificateReceivedDATA;
import mrd.transaction.controller.fb.CertificateReceivedFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.RackMstVO;
import hisglobal.vo.RackShelfMstVO;


/**
 * @author Administrator
 *
 */
public class CertificateReceivedUTL extends ControllerUTIL
{
	/** Getting the List Of Certificate, Rack & Shelf List
	 * @param fb
	 * @param request
	 */
	public static void getEssentialForCertificateReceived(CertificateReceivedFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		Map essentialMap=new HashMap();
	//	List rackList=new ArrayList();
		
		try
		{
			CertificateIssueDtlVO _certificateIssueDtlVO = new CertificateIssueDtlVO();
			essentialMap=CertificateReceivedDATA.getEssentialForCertificateReceived(fb.getCertificateReceivedMode(),_certificateIssueDtlVO,getUserVO(request));
			
			/*CertificateIssueDtlVO[] certificateIssueDtlVO=(CertificateIssueDtlVO[])essentialMap.get(MrdConfig.ALL_CERTIFICATE_ISSUE_DTL_VO_LIST);
			WebUTIL.setAttributeInSession(request,MrdConfig.ALL_CERTIFICATE_ISSUE_DTL_VO_LIST ,certificateIssueDtlVO );
			
			rackList=(List)essentialMap.get(MrdConfig.ALL_RACK_LIST);
			WebUTIL.setAttributeInSession(request,MrdConfig.ALL_RACK_LIST ,rackList );*/
			
			WebUTIL.setMapInSession(essentialMap, request);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	/** Getting the List Of Shelf On Basis of Rack
	 * @param fb
	 * @param request
	 * @param _resp
	 */
	public static void getShelf(CertificateReceivedFB fb, HttpServletRequest request,HttpServletResponse _resp)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List selfList=new ArrayList();
		//String result="";
		String rackId=request.getParameter("rackId");
		String resp="";
		
		try
		{
			CertificateIssueDtlVO _certificateIssueDtlVO = new CertificateIssueDtlVO();
			
			_certificateIssueDtlVO.setRackId(rackId);
		//	selfList=CertificateReceivedDATA.getSelf(_certificateIssueDtlVO,getUserVO(request));
			
			RackShelfMstVO[] rackShelfMstVO=(RackShelfMstVO[])session.getAttribute(MrdConfig.ALL_RACK_SHELF_LIST_VO);
			for(int i=0;i<rackShelfMstVO.length;i++)
			{
				if(rackId.equals(rackShelfMstVO[i].getRackId()))
				{
					Entry ent=new Entry();
					ent.setValue(rackShelfMstVO[i].getRackShelfId());
					ent.setLabel(rackShelfMstVO[i].getShelfLabel());
					selfList.add(ent);
				}
			}
			
			Iterator itr = selfList.iterator();
			while(itr.hasNext())
			{
				Entry itrSelfList=(Entry)itr.next();
			//	result=result + itrSelfList.getValue() + "$" + itrSelfList.getLabel() + "$";	
				resp = resp + "<option value='" + itrSelfList.getValue() + "'>" + itrSelfList.getLabel() + "</option>";
			}
		//	if(!result.equals(""))	result = result.substring(0,result.length()-1);
								
			try
			{
				PrintWriter writer = _resp.getWriter();
				System.out.println("resp "+resp);
				writer.write(resp);	
				writer.flush();
				writer.close();
			}												
			catch (IOException e)				
			{								
				e.printStackTrace();					
			}
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	/**  Saving the Certificate Received Details
	 * @param fb
	 * @param request
	 */
	public static boolean saveReceiveCertificate(CertificateReceivedFB fb, HttpServletRequest request)
	{
		boolean flag=true;
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		MrdRecordDtlVO[] mrdRecordDtlVO=null;
		int x=0;
		String shelfName="";
		String roomCode="";
		
		
		try
		{
			int count=fb.getChk().length;
			mrdRecordDtlVO=new MrdRecordDtlVO[count];
			CertificateIssueDtlVO[] certificateIssueDtlVO=(CertificateIssueDtlVO[])session.getAttribute(MrdConfig.ALL_CERTIFICATE_ISSUE_DTL_VO_LIST);
			
			for(int i=0;i<count;i++)
			{
				x = Integer.parseInt(fb.getChk()[i]);
				mrdRecordDtlVO[i]=new MrdRecordDtlVO();
				mrdRecordDtlVO[i].setRecordId(certificateIssueDtlVO[x].getCertificateId());
				mrdRecordDtlVO[i].setRecordDesc(certificateIssueDtlVO[x].getCertificateDesc());
				mrdRecordDtlVO[i].setRecordType(certificateIssueDtlVO[x].getRecordType());
				//mrdRecordDtlVO[i].setRecordStatus(MrdConfig.CERTIFICATE_RECEIVED_RECORD_STATUS_IN_MRD);
				mrdRecordDtlVO[i].setRackId(fb.getRackId()[i]);
				mrdRecordDtlVO[i].setShelfId(fb.getShelfId()[i]);
				mrdRecordDtlVO[i].setReceiveFrom(fb.getReceiveFrom());
				mrdRecordDtlVO[i].setSlNo(certificateIssueDtlVO[x].getSlNo());
				mrdRecordDtlVO[i].setDeptUnitCode(certificateIssueDtlVO[x].getDeptUnitCode());
				RackShelfMstVO[] rackShelfMstVO=(RackShelfMstVO[])session.getAttribute(MrdConfig.ALL_RACK_SHELF_LIST_VO);
				for(int j=0;j<rackShelfMstVO.length;j++)
				{
					if(fb.getShelfId()[i].equals(rackShelfMstVO[j].getRackShelfId()))
						shelfName=rackShelfMstVO[j].getShelfLabel();
					break;
				}
				mrdRecordDtlVO[i].setShelfName(shelfName);
				
				RackMstVO[] rackMstVO=(RackMstVO[])session.getAttribute(MrdConfig.ALL_RACK_LIST_VO);
				for(int j=0;j<rackMstVO.length;j++)
				{
					if(fb.getRackId()[i].equals(rackMstVO[j].getRackId()))
						roomCode=rackMstVO[j].getRoomId();
					break;
				}
				mrdRecordDtlVO[i].setRoomCode(roomCode);
			}
			
			
			//CertificateReceivedDATA.saveReceiveCertificate(fb.getCertificateReceivedMode(),mrdRecordDtlVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Certificate Received");
			
		}
		catch (HisDataAccessException e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}	

}
