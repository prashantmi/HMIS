package bmed.global.controller.data;

import hisglobal.exceptions.HisException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import bmed.global.bo.MainteWarrantyContractBO;
import bmed.global.vo.MainteWarrantyContractVO;

public class MainteWarrantyContractDATA {

	public static void getData(HttpServletRequest request,HttpServletResponse response) {
		//final String strDisplayMode;
		//final String strProcessMode;
		final String strItemId;
		String strWarrantyData=null;
		String strMainteContractData=null;
		final String strHospitalCode;
		final MainteWarrantyContractBO bo=new MainteWarrantyContractBO();
		final MainteWarrantyContractVO vo=new MainteWarrantyContractVO();
		try {
			//strDisplayMode = request.getParameter("strDisplayMode");
			//strProcessMode = request.getParameter("strProcessMode");
			strItemId = request.getParameter("strItemId");
			strHospitalCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
			
			vo.setStrItemId(strItemId);
			vo.setStrHospitalCode(strHospitalCode);
			bo.getData(vo);
			strWarrantyData=getWarrantyData(vo.getWebRowSetWarranty());
			strMainteContractData=getMainteContractData(vo.getWebRowSetMaintenance());
			if(strWarrantyData==null) {
				strWarrantyData="";
			}
			if(strMainteContractData==null) {
				strMainteContractData="";
			}
			response.getWriter().write(strWarrantyData+"####"+strMainteContractData);

		} catch (Exception ex) {
			String strmsgText = "MainteWarrantyContractDATA.getData() --> "
				+ ex.getMessage();
			new HisException("bmed",
					"MainteWarrantyContractDATA", strmsgText);
		}

	}

	public static String getWarrantyData(WebRowSet webRowSet) throws Exception {
		
		
		String strMainteContractUptoUnit = null;
		String strMainteContractUpto = null;
		String strSlNo = null;
		
		String strManufSlNo = null;
		String strManufId = null;
		
		String strItemSlNo = null;
		
		String strItemId = null;
		
		String strIsItem = null;
		String strMainteContractDate = null;
		String strDocRefNo = null;
		
		String strUploadNo = null;
		
		String strExtUploadNo = null;
		String strExtDocRefNo = null;
		
		String strExtDocRefDate = null;
		
		String strDocRefDate = null;
		String strTermNcon = null;
		String strExtTermNcon = null;
		String strIsExtended = null;
		
		String strExtendedUptoUnit = null;
		String strExtendedUpto = null;
		String strExtendedStartDate = null;
		String strMainteContractUptoUnitName = null;
		String strMainteContractExtendedUptoUnit = null;
		String strSupplierName = null;
		String strMaintenanceContractDetails = null;

		@SuppressWarnings("unused")
		String strTermsNCondition;
		String strFile;
		String strWarrantyStartDateUpto;
		String strExtentionStartDateUpto;
		int index = 0;
		StringBuffer sbWarrantyData = new StringBuffer(1000);

		try {
			if (webRowSet.size() > 0) {
				while (webRowSet.next()) {

					strMainteContractUptoUnit = webRowSet.getString(1);
					strMainteContractUpto = webRowSet.getString(2);
					strSlNo = webRowSet.getString(3);
					strManufSlNo = webRowSet.getString(4);
					strManufId = webRowSet.getString(5);
					strItemSlNo = webRowSet.getString(6);
					strItemId = webRowSet.getString(7);
					strIsItem = webRowSet.getString(8);
					strMainteContractDate = webRowSet.getString(9);
					strDocRefNo = webRowSet.getString(10);
					strUploadNo = webRowSet.getString(11);
					strExtUploadNo = webRowSet.getString(12);
					strExtDocRefNo = webRowSet.getString(13);
					strExtDocRefDate = webRowSet.getString(14);
					strDocRefDate = webRowSet.getString(15);
					strTermNcon = webRowSet.getString(16);
					strExtTermNcon = webRowSet.getString(17);
					strIsExtended = webRowSet.getString(18);
					strExtendedUptoUnit = webRowSet.getString(19);
					strExtendedUpto = webRowSet.getString(20);
					strExtendedStartDate = webRowSet.getString(21);
					strMainteContractUptoUnitName = webRowSet.getString(22);
					strMainteContractExtendedUptoUnit = webRowSet.getString(23);
					strSupplierName = webRowSet.getString(24);
					
					/*
					 * Checking for null value.
					 * 
					 */
					if(strMainteContractUptoUnit==null) {
						strMainteContractUptoUnit="---";
					}
					
					
					if(strMainteContractUpto==null) {
						strMainteContractUpto="---";
					}
					if(strSlNo==null) {
						strSlNo="---";
					}
					if(strManufSlNo==null) {
						strManufSlNo="---";
					}
					if(strManufId==null) {
						strManufId="---";
					}
					if(strItemSlNo==null) {
						strItemSlNo="---";
					}
					if(strItemId==null) {
						strItemId="---";
					}
					if(strIsItem==null) {
						strIsItem="---";
					}
					if(strMainteContractDate==null) {
						strMainteContractDate="---";
					}
					if(strDocRefNo==null) {
						strDocRefNo="---";
					}
					if(strUploadNo==null) {
						strUploadNo="---";
					}
					if(strExtUploadNo==null) {
						strExtUploadNo="---";
					}
					if(strExtDocRefNo==null) {
						strExtDocRefNo="---";
					}
					if(strExtDocRefDate==null) {
						strExtDocRefDate="---";
					}
					
					if(strDocRefDate==null) {
						strDocRefDate="---";
					}
					if(strTermNcon==null) {
						strTermNcon="---";
					}
					if(strExtTermNcon==null) {
						strExtTermNcon="---";
					}
					if(strIsExtended==null) {
						strIsExtended="---";
					}
					if(strExtendedUptoUnit==null) {
						strExtendedUptoUnit="---";
					}
					if(strExtendedUpto==null) {
						strExtendedUpto="---";
					}
					if(strExtendedStartDate==null) {
						strExtendedStartDate="---";
					}
					if(strMainteContractUptoUnitName==null) {
						strMainteContractUptoUnitName="---";
					}
					if(strMainteContractExtendedUptoUnit==null) {
						strMainteContractExtendedUptoUnit="---";
					}
					if(strSupplierName==null) {
						strSupplierName="---";
					}
					
					

					if ("1".equals(strIsExtended)) {
						strTermsNCondition = strExtTermNcon;
						strFile = strExtUploadNo;
					} else {
						strTermsNCondition = strTermNcon;
						strFile = strUploadNo;
					}

					strWarrantyStartDateUpto = strMainteContractDate + "/ "
							+ strMainteContractUpto + " " + strMainteContractUptoUnitName;
					strExtentionStartDateUpto = strExtendedStartDate + "/ "
							+ strExtendedUpto + " "
							+ strMainteContractExtendedUptoUnit;
					strMaintenanceContractDetails = strManufId + "^" + strSlNo
							+ "^"+"WARRANTY";
					
					
					sbWarrantyData
							.append("<tr><td class='LABEL' style='text-align: center;'><input type='radio' value='"
									+ strMaintenanceContractDetails
									+ "' name='strMaintenanceContractDetails' /></td><td class='CONTROL' style='text-align: center;'>"
									+ strSupplierName
									+ "</td><td class='CONTROL' style='text-align: center;'>"
									+ strWarrantyStartDateUpto
									+ "/</td><td class='CONTROL' style='text-align: center;'>"
									+ strExtentionStartDateUpto
									+ "</td><td class='CONTROL' style='text-align: center;'>"
									+ strTermNcon
									+ "</td><td class='CONTROL' style='text-align: center;'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='onUploadedFileName(this,"+index+",\""+strDocRefNo+"\");'>"
									+ strFile + "</a></td></tr>");
					
					index++;
				}

			} else {
				sbWarrantyData
						.append("<tr><td class='CONTROL' colspan='6' style='text-align: center;color: red;'>No Record Found.</td></tr>");
			}
		} catch (Exception e) {
			throw new Exception(
					"MainteWarrantyContractDATA.getWarrantyData() --> "
							+ e.getMessage());
		} finally {
			if (webRowSet != null) {
				webRowSet.close();
			}
		}

		return sbWarrantyData.toString();

	}

	public static String getMainteContractData(WebRowSet webRowSet) throws Exception {
		
		String strSlNo;                                         
		String strTermNCon;                                         
//		String strIsItem;                                         
//		String strMcName;                                         
		String strManufId;                                         
		//String strPenaltyCon;                                         
		String strStartDate;                                         
		String strEndDate;                                         
		String strUploadNo;                                         
		String strDocRefNo;                                         
		//String strDocRefDate;
		String strMaintenanceContractDetails;
		String strSupplierName;
		int index = 0;
		StringBuffer sbMainteContractData = new StringBuffer(1000);

		try {
			if (webRowSet.size() > 0) {
				while (webRowSet.next()) {

					 strSlNo=webRowSet.getString(1);                                         
					 strTermNCon=webRowSet.getString(2);                                         
//					 strIsItem=webRowSet.getString(3);                                         
//					 strMcName=webRowSet.getString(4);                                         
					 strManufId=webRowSet.getString(5);                                         
					 //strPenaltyCon=webRowSet.getString(6);                                         
					 strStartDate=webRowSet.getString(7);                                         
					 strEndDate=webRowSet.getString(8);                                         
					 strUploadNo=webRowSet.getString(9);                                         
					 strDocRefNo=webRowSet.getString(10);                                         
					// strDocRefDate=webRowSet.getString(11);
					 strSupplierName=webRowSet.getString(12);
					

					
					strMaintenanceContractDetails = strManufId + "^" + strSlNo
							+ "^"+"MC";
					
					
					sbMainteContractData
							.append("<tr><td class='LABEL' style='text-align: center;'><input type='radio' value='"
									+ strMaintenanceContractDetails
									+ "' name='strMaintenanceContractDetails' /></td><td class='CONTROL' style='text-align: center;'>"
									+ strSupplierName
									+ "</td><td class='CONTROL' style='text-align: center;'>"
									+ strStartDate
									+ "/</td><td class='CONTROL' style='text-align: center;'>"
									+ strEndDate
									+ "</td><td class='CONTROL' style='text-align: center;'>"
									+ strTermNCon
									+ "</td><td class='CONTROL' style='text-align: center;'><a style='cursor:pointer;color:blue;font-size:12px;' onClick='onUploadedFileName(this,"+index+",\""+strDocRefNo+"\");'>"
									+ strUploadNo + "</a></td></tr>");
					
					index++;
				}

			} else {
				sbMainteContractData
						.append("<tr><td class='CONTROL' colspan='6' style='text-align: center;color: red;'>No Record Found.</td></tr>");
			}
		} catch (Exception e) {
			throw new Exception(
					"MainteMainteContractContractDATA.getMainteContractData() --> "
							+ e.getMessage());
		} finally {
			if (webRowSet != null) {
				webRowSet.close();
			}
		}
		return sbMainteContractData.toString();
	}
	
	public static void getDataTrans(HttpServletRequest request,HttpServletResponse response) {
		//final String strDisplayMode;
		//final String strProcessMode;
		final String strItemId;
		String strWarrantyData=null;
		String strMainteContractData=null;
		final String strHospitalCode;
		final MainteWarrantyContractBO bo=new MainteWarrantyContractBO();
		final MainteWarrantyContractVO vo=new MainteWarrantyContractVO();
		try {
			//strDisplayMode = request.getParameter("strDisplayMode");
			//strProcessMode = request.getParameter("strProcessMode");
			strItemId = request.getParameter("strItemId");
			strHospitalCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
			
			vo.setStrItemId(strItemId);
			vo.setStrHospitalCode(strHospitalCode);
			bo.getData(vo);
			//strWarrantyData=getWarrantyDataTrans(vo.getWebRowSetWarranty(),strMode);
			//strMainteContractData=getMainteContractDataTrans(vo.getWebRowSetMaintenance());
			if(strWarrantyData==null) {
				strWarrantyData="";
			}
			if(strMainteContractData==null) {
				strMainteContractData="";
			}
			response.getWriter().write(strWarrantyData+"####"+strMainteContractData);

		} catch (Exception ex) {
			String strmsgText = "MainteWarrantyContractDATA.getData() --> "
				+ ex.getMessage();
			new HisException("bmed",
					"MainteWarrantyContractDATA", strmsgText);
		}

	}

//	private static String getWarrantyDataTrans(WebRowSet webRowSet,String strMode) throws Exception {
//		
//		
//		String strMainteContractUptoUnit = null;
//		String strMainteContractUpto = null;
//		String strSlNo = null;
//		
//		String strManufSlNo = null;
//		String strManufId = null;
//		String strItemSlNo = null;
//		String strItemId = null;
//		String strIsItem = null;
//		String strMainteContractDate = null;
//		String strDocRefNo = null;
//		String strUploadNo = null;
//		String strExtUploadNo = null;
//		String strExtDocRefNo = null;
//		String strExtDocRefDate = null;
//		String strDocRefDate = null;
//		String strTermNcon = null;
//		String strExtTermNcon = null;
//		String strIsExtended = null;
//		String strExtendedUptoUnit = null;
//		String strExtendedUpto = null;
//		String strExtendedStartDate = null;
//		String strMainteContractUptoUnitName = null;
//		String strMainteContractExtendedUptoUnit = null;
//		String strSupplierName = null;
//		String strMaintenanceContractDetails = null;
//
//		@SuppressWarnings("unused")
//		String strTermsNCondition;
//		String strFile;
//		String strWarrantyStartDateUpto;
//		String strExtentionStartDateUpto;
//
//		StringBuffer sbWarrantyData = new StringBuffer(1000);
//
//		try 
//		{
//			if (webRowSet.size() > 0) 
//			{
//				while (webRowSet.next()) 
//				{
//
//					strMainteContractUptoUnit        = webRowSet.getString(1);
//					strMainteContractUpto            = webRowSet.getString(2);
//					strSlNo                          = webRowSet.getString(3);
//					strManufSlNo                     = webRowSet.getString(4);
//					strManufId                       = webRowSet.getString(5);
//					strItemSlNo                      = webRowSet.getString(6);
//					strItemId                        = webRowSet.getString(7);
//					strIsItem                        = webRowSet.getString(8);
//					strMainteContractDate            = webRowSet.getString(9);
//					strDocRefNo                      = webRowSet.getString(10);
//					strUploadNo                      = webRowSet.getString(11);
//					strExtUploadNo                   = webRowSet.getString(12);
//					strExtDocRefNo                   = webRowSet.getString(13);
//					strExtDocRefDate                 = webRowSet.getString(14);
//					strDocRefDate                    = webRowSet.getString(15);
//					strTermNcon                      = webRowSet.getString(16);
//					strExtTermNcon                   = webRowSet.getString(17);
//					strIsExtended                    = webRowSet.getString(18);
//					strExtendedUptoUnit              = webRowSet.getString(19);
//					strExtendedUpto                  = webRowSet.getString(20);
//					strExtendedStartDate             = webRowSet.getString(21);
//					strMainteContractUptoUnitName    = webRowSet.getString(22);
//					strMainteContractExtendedUptoUnit= webRowSet.getString(23);
//					strSupplierName                  = webRowSet.getString(24);
//					
//					String strAllWarrantyValue = strMainteContractUptoUnit +"^"+strMainteContractUpto +"^"+ strSlNo+"^"+strManufSlNo +"^"+ strManufId +"^"+ strItemSlNo +"^"+ strItemId
//					+"^"+ strIsItem +"^"+ strMainteContractDate +"^"+ strDocRefNo +"^"+ strUploadNo +"^"+strExtUploadNo  +"^"+strExtDocRefNo  +"^"+ strExtDocRefDate
//					+"^"+strDocRefDate  +"^"+strTermNcon  +"^"+strExtTermNcon +"^"+strIsExtended  +"^"+ strExtendedUptoUnit +"^"+strExtendedUpto  +"^"+ strExtendedStartDate +"^"+strMainteContractUptoUnitName  +"^"+strMainteContractExtendedUptoUnit  +"^"+strSupplierName ;
//					/*
//					 * Checking for null value.
//					 * 
//					 */
//					if(strMainteContractUptoUnit==null) {
//						strMainteContractUptoUnit="---";
//					}
//					
//					
//					if(strMainteContractUpto==null) {
//						strMainteContractUpto="---";
//					}
//					if(strSlNo==null) {
//						strSlNo="---";
//					}
//					if(strManufSlNo==null) {
//						strManufSlNo="---";
//					}
//					if(strManufId==null) {
//						strManufId="---";
//					}
//					if(strItemSlNo==null) {
//						strItemSlNo="---";
//					}
//					if(strItemId==null) {
//						strItemId="---";
//					}
//					if(strIsItem==null) {
//						strIsItem="---";
//					}
//					if(strMainteContractDate==null) {
//						strMainteContractDate="---";
//					}
//					if(strDocRefNo==null) {
//						strDocRefNo="---";
//					}
//					if(strUploadNo==null) {
//						strUploadNo="---";
//					}
//					if(strExtUploadNo==null) {
//						strExtUploadNo="---";
//					}
//					if(strExtDocRefNo==null) {
//						strExtDocRefNo="---";
//					}
//					if(strExtDocRefDate==null) {
//						strExtDocRefDate="---";
//					}
//					
//					if(strDocRefDate==null) {
//						strDocRefDate="---";
//					}
//					if(strTermNcon==null) {
//						strTermNcon="---";
//					}
//					if(strExtTermNcon==null) {
//						strExtTermNcon="---";
//					}
//					if(strIsExtended==null) {
//						strIsExtended="---";
//					}
//					if(strExtendedUptoUnit==null) {
//						strExtendedUptoUnit="---";
//					}
//					if(strExtendedUpto==null) {
//						strExtendedUpto="---";
//					}
//					if(strExtendedStartDate==null) {
//						strExtendedStartDate="---";
//					}
//					if(strMainteContractUptoUnitName==null) {
//						strMainteContractUptoUnitName="---";
//					}
//					if(strMainteContractExtendedUptoUnit==null) {
//						strMainteContractExtendedUptoUnit="---";
//					}
//					if(strSupplierName==null) {
//						strSupplierName="---";
//					}
//					
//					
//
//					if ("1".equals(strIsExtended)) 
//					{
//						strTermsNCondition = strExtTermNcon;
//						strFile = strExtDocRefNo;
//					} 
//					else 
//					{
//						strTermsNCondition = strTermNcon;
//						strFile = strDocRefNo;
//					}
//
//					strWarrantyStartDateUpto = strMainteContractDate + "/ "	+ strMainteContractUpto + " " + strMainteContractUptoUnitName;
//					strExtentionStartDateUpto = strExtendedStartDate + "/ " + strExtendedUpto + " "	+ strMainteContractExtendedUptoUnit;
//					strMaintenanceContractDetails = strManufId + "^" + strSlNo+ "^";
//					
//					sbWarrantyData.append("<his:ContentTag>");
//				    sbWarrantyData.append("<table class='TABLE_STYLE'>");
//				    sbWarrantyData.append("<tr class='FOOTER_TR'>");
//				    sbWarrantyData.append("<td align='center' style='text-align: center;'width:5%;'><img style='cursor: pointer;' id='imgStockDetails' src='/HEMMS_ODISHA/hisglobal/images/plus.gif'" +
//				    "onclick='showOrHideWrrantyDetails(this)' title='Show'/>" +
//				    "</td><td colspan='5' style='width: 95%'><div id='Id1' style='color:blue;text-align:left'>Stock Details</div></td>");
//				    sbWarrantyData.append("</tr>");
//					sbWarrantyData.append("</table>");
//					sbWarrantyData.append("</his:ContentTag>");
//				 
//				 sbWarrantyData.append("<div id='wrrantyDtlDivId' style='display:block'>");
//				 sbWarrantyData.append("<his:ContentTag>");					 
//                 sbWarrantyData.append("<table class='TABLE_STYLE' align='center'  border='0' cellpadding='1px' cellspacing ='1px'>");
//                 
//                 sbWarrantyData.append("<input type='hidden' name='strAllWarrantyValue'  value='"+strAllWarrantyValue+"'>");
//                 sbWarrantyData.append("<tr><td class='LABEL_TD' style='text-align: center;'><input type='radio' value='"
//									+ strMaintenanceContractDetails
//									+ "' name='strMaintenanceContractDetails' /></td><td class='CONTROL_TD' style='text-align: center;'>"
//									+ strSupplierName
//									+ "</td><td class='CONTROL_TD' style='text-align: center;'>"
//									+ strWarrantyStartDateUpto
//									+ "/</td><td class='CONTROL_TD' style='text-align: center;'>"
//									+ strExtentionStartDateUpto
//									+ "</td><td class='CONTROL_TD' style='text-align: center;'>"
//									+ strTermNcon
//									+ "</td><td class='CONTROL_TD' style='text-align: center;'><a href='http://www.google.co.in/'>"
//									+ strFile + "</a></td></tr>");
//                 
//                 sbWarrantyData.append("</table>");
//                 sbWarrantyData.append("</his:ContentTag>");			
//                 sbWarrantyData.append("</div>");
//
//				}
//
//			} else {
//				sbWarrantyData
//						.append("<tr><td class='CONTROL_TD' colspan='6' style='text-align: center;color: red;'>No Record Found.</td></tr>");
//			}
//		} catch (Exception e) {
//			throw new Exception(
//					"MainteWarrantyContractDATA.getWarrantyDataTrans() --> "
//							+ e.getMessage());
//		} finally {
//			
//		}
//
//		return sbWarrantyData.toString();
//
//	}

//	private static String getMainteContractDataTrans(WebRowSet webRowSet,String strMode) throws Exception {
//		
//		String strSlNo;                                         
//		String strTermNCon;                                         
//		String strIsItem;                                         
//		String strMcName;                                         
//		String strManufId;                                         
//		String strPenaltyCon;                                         
//		String strStartDate;                                         
//		String strEndDate;                                         
//		String strUploadNo;                                         
//		String strDocRefNo;                                         
//		String strDocRefDate;
//		String strMaintenanceContractDetails;
//		String strSupplierName;
//
//		StringBuffer sbMainteContractData = new StringBuffer(1000);
//
//		try {
//			if (webRowSet.size() > 0) {
//				while (webRowSet.next()) {
//
//					 strSlNo           = webRowSet.getString(1);                                         
//					 strTermNCon       = webRowSet.getString(2);                                         
//					 strIsItem         = webRowSet.getString(3);                                         
//					 strMcName    	   = webRowSet.getString(4);                                         
//					 strManufId        = webRowSet.getString(5);                                         
//					 strPenaltyCon     = webRowSet.getString(6);                                         
//					 strStartDate      = webRowSet.getString(7);                                         
//					 strEndDate        = webRowSet.getString(8);                                         
//					 strUploadNo       = webRowSet.getString(9);                                         
//					 strDocRefNo       = webRowSet.getString(10);                                         
//					 strDocRefDate     = webRowSet.getString(11);
//					 strSupplierName   = webRowSet.getString(12);
//					
//
//					
//					strMaintenanceContractDetails = strManufId + "^" + strSlNo
//							+ "^";
//					String strAllMantContractValue = strSlNo+ "^" +strTermNCon  + "^" + strIsItem + "^" +strMcName  + "^" +strManufId  + "^" + strPenaltyCon 
//					+ "^" +strStartDate + "^" + strEndDate + "^" +strUploadNo  + "^" + strDocRefNo+ "^" +strDocRefDate  + "^" + strSupplierName;
//					
//					sbMainteContractData.append("<his:ContentTag>");
//					sbMainteContractData.append("<table class='TABLE_STYLE'>");
//					sbMainteContractData.append("<tr class='FOOTER_TR'>");
//					sbMainteContractData.append("<td align='center' style='text-align: center;'width:5%;'><img style='cursor: pointer;' id='imgStockDetails' src='/HEMMS_ODISHA/hisglobal/images/plus.gif'" +
//				    "onclick='showOrHideMainteContractDetails(this)' title='Show'/>" +
//				    "</td><td colspan='5' style='width: 95%'><div id='Id1' style='color:blue;text-align:left'>Stock Details</div></td>");
//					sbMainteContractData.append("</tr>");
//					sbMainteContractData.append("</table>");
//					sbMainteContractData.append("</his:ContentTag>");
//				 
//					sbMainteContractData.append("<div id='wrrantyDtlDivId' style='display:block'>");
//					sbMainteContractData.append("<his:ContentTag>");					 
//					sbMainteContractData.append("<table class='TABLE_STYLE' align='center'  border='0' cellpadding='1px' cellspacing ='1px'>");
//                 
//					sbMainteContractData.append("<input type='hidden' name='strAllMantContractValue'  value='"+strAllMantContractValue+"'>");
//					
//					sbMainteContractData
//							.append("<tr><td class='LABEL_TD' style='text-align: center;'><input type='radio' value='"
//									+ strMaintenanceContractDetails
//									+ "' name='strMaintenanceContractDetails' /></td><td class='CONTROL_TD' style='text-align: center;'>"
//									+ strSupplierName
//									+ "</td><td class='CONTROL_TD' style='text-align: center;'>"
//									+ strStartDate
//									+ "/</td><td class='CONTROL_TD' style='text-align: center;'>"
//									+ strEndDate
//									+ "</td><td class='CONTROL_TD' style='text-align: center;'>"
//									+ strTermNCon
//									+ "</td><td class='CONTROL_TD' style='text-align: center;'><a href='http://www.google.co.in/'>"
//									+ strDocRefNo + "</a></td></tr>");
//					
//					sbMainteContractData.append("</table>");
//					sbMainteContractData.append("</his:ContentTag>");			
//					sbMainteContractData.append("</div>");
//
//				}
//
//			} else {
//				sbMainteContractData
//						.append("<tr><td class='CONTROL_TD' colspan='6' style='text-align: center;color: red;'>No Record Found.</td></tr>");
//			}
//		} catch (Exception e) {
//			throw new Exception(
//					"MainteMainteContractContractDATA.getMainteContractData() --> "
//							+ e.getMessage());
//		} finally {
//			if (webRowSet != null) {
//				webRowSet.close();
//			}
//		}
//		return sbMainteContractData.toString();
//	}

}
