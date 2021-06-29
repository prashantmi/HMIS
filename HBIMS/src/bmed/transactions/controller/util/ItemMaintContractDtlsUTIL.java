package bmed.transactions.controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.transactions.controller.data.ItemMaintContractDtlsDATA;
import bmed.transactions.controller.fb.ItemMaintContractDtlsFB;

public class ItemMaintContractDtlsUTIL {

	public static void initializeMain(ItemMaintContractDtlsFB formBean_p,
			HttpServletRequest request_p) {
		ItemMaintContractDtlsDATA.initializeMain(formBean_p, request_p);
		
	}

	public static void getStoreName(ItemMaintContractDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemMaintContractDtlsDATA.getStoreName(formBean_p, request_p, response_p);
		
	}

	public static void getEnggItemSubType(ItemMaintContractDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemMaintContractDtlsDATA.getEnggItemSubType(formBean_p, request_p, response_p);
		
	}

	public static void getItemName(ItemMaintContractDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemMaintContractDtlsDATA.getItemName(formBean_p, request_p, response_p);
		
	}

	public static void getStockDetails(ItemMaintContractDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemMaintContractDtlsDATA.getStockDetails(formBean_p, request_p, response_p);
		
	}

	public static void getPrevMantDetails(ItemMaintContractDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemMaintContractDtlsDATA.getPrevMantDetails(formBean_p, request_p, response_p);
		
	}

	public static void getUploadedFile(ItemMaintContractDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemMaintContractDtlsDATA.getUploadedFile(formBean_p, request_p, response_p);
		
	}

	public static void getSupplierCmb(ItemMaintContractDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemMaintContractDtlsDATA.getSupplierCmb(formBean_p, request_p, response_p);
		
	}

	public static void getCommanAction(ItemMaintContractDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemMaintContractDtlsDATA.getCommanAction(formBean_p, request_p, response_p);
		
	}

	public static void getRenewSelectedCombo(
			ItemMaintContractDtlsFB formBean_p, HttpServletRequest request_p,
			HttpServletResponse response_p) {
		ItemMaintContractDtlsDATA.getRenewSelectedCombo(formBean_p, request_p, response_p);
		
	}

	public static void cancelData(ItemMaintContractDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ItemMaintContractDtlsDATA.cancelData(formBean_p, request_p, response_p);
		
	}

	public static void saveData(ItemMaintContractDtlsFB formBean_p_p,
			HttpServletRequest request_p) {
		ItemMaintContractDtlsDATA.saveData(formBean_p_p, request_p);
		
	}

	public static void saveRenewData(ItemMaintContractDtlsFB formBean_p_p,
			HttpServletRequest request_p) {
		ItemMaintContractDtlsDATA.saveRenewData(formBean_p_p, request_p);
		
	}

}
