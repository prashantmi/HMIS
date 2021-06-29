package bmed.transactions.controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.transactions.controller.data.ItemWarrantyDtlsDATA;
import bmed.transactions.controller.fb.ItemWarrantyDtlsFB;

public class ItemWarrantyDtlsUTIL {

	public static void initializeMain(ItemWarrantyDtlsFB formBean_p,
			HttpServletRequest request_p) {
		ItemWarrantyDtlsDATA.initializeMain(formBean_p, request_p);
		
	}

	public static void getStoreName(ItemWarrantyDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemWarrantyDtlsDATA.getStoreName(formBean_p, request_p, response_p);
		
	}

	public static void getEnggItemSubType(ItemWarrantyDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemWarrantyDtlsDATA.getEnggItemSubType(formBean_p, request_p, response_p);
		
	}

	public static void getItemName(ItemWarrantyDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemWarrantyDtlsDATA.getItemName(formBean_p, request_p, response_p);
		
	}

	public static void getStockDetails(ItemWarrantyDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemWarrantyDtlsDATA.getStockDetails(formBean_p, request_p, response_p);
		
	}

	public static void getWarrantyDetails(ItemWarrantyDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemWarrantyDtlsDATA.getWarrantyDetails(formBean_p, request_p, response_p);
		
	}

	public static void getUploadedFile(ItemWarrantyDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemWarrantyDtlsDATA.getUploadedFile(formBean_p, request_p, response_p);
		
	}

	public static void getSupplierCmb(ItemWarrantyDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemWarrantyDtlsDATA.getSupplierCmb(formBean_p, request_p, response_p);
		
	}

	public static void getCommanAction(ItemWarrantyDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemWarrantyDtlsDATA.getCommanAction(formBean_p, request_p, response_p);
		
	}

	public static void cancelData(ItemWarrantyDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemWarrantyDtlsDATA.cancelData(formBean_p, request_p, response_p);
		
	}

	public static void saveData(ItemWarrantyDtlsFB formBean_p_p,
			HttpServletRequest request_p) {
		ItemWarrantyDtlsDATA.saveData(formBean_p_p, request_p);
		
	}

	public static void saveExtendWarrantyData(ItemWarrantyDtlsFB formBean_p_p,
			HttpServletRequest request_p) {
		ItemWarrantyDtlsDATA.saveExtendWarrantyData(formBean_p_p, request_p);
		
	}

}
