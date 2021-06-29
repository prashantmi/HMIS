<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>



<%@taglib uri="/WEB-INF/onLineReqDiscountDtl.tld" prefix="onLineDtl"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>

<html>
<head>
<meta charset=utf-8>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">




<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../transUtil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../js/cashcollection_withoutcr_trans.js"></script>
</head>
<body>

			<table width='100%' >
				<tr class='HEADER'>
					<th colspan='2' align="center">&nbsp;Cash Collection Detail  
					</th>
				</tr>
			</table>
			
			<div class="xx">
			<div class="box maroonbg">
                            <div class="row">
                                <div class="5u">
                                    <div >
                                        <i class="fa fa-user-o"></i>
                                    </div>
                                </div>
                                <div class="7u">
                                    <h3 class="txt_right"><span id="dsb1"><%=request.getAttribute("userName") %></u></span></h3>
                                </div>

                                <div class="12u">
                                    <h4>
                                        <span>User Name :</span>
                                    </h4>
                                </div>
                            </div>
                        </div>
                        </div>
                        <div class="xx">
                        <div class="box orangebg">
                            <div class="row">
                                <div class="5u">
                                    <div class="">
                                        <i class="fa fa-bill-o"></i>
                                    </div>
                                </div>
                                <div class="7u">
                                    <h3 class="txt_right"><span id="dsb1"><%=request.getAttribute("totalBill") %></u></span></h3>
                                </div>

                                <div class="12u">
                                    <h4>
                                        <span>Total No. of Bill :</span>
                                    </h4>
                                </div>
                            </div>
                        </div>
                        </div>
                        <div class="xx">
                        <div class="box yellowbg">
                            <div class="row">
                                <div class="5u">
                                    <div class="">
                                        <i class="fa fa-amount-o"></i>
                                    </div>
                                </div>
                                <div class="7u">
                                    <h3 class="txt_right"><span id="dsb1"><%=request.getAttribute("recievedAmount") %></u></span></h3>
                                </div>

                                <div class="12u">
                                    <h4>
                                        <span>Total Recieved Amount :</span>
                                    </h4>
                                </div>
                            </div>
                        </div>
                        </div>
                        <div class="xx">
                        <div class="box bluebg">
                            <div class="row">
                                <div class="5u">
                                    <div class="">
                                        <i class="fa fa-refund-o"></i>
                                    </div>
                                </div>
                                <div class="7u">
                                    <h3 class="txt_right"><span id="dsb1"><%=request.getAttribute("refundAmount") %></u></span></h3>
                                </div>

                                <div class="12u">
                                    <h4>
                                        <span>Total Refund Amount :</span>
                                    </h4>
                                </div>
                            </div>
                        </div>
                        </div>
                        <div class="xx">
                        <div class="box greenbg">
                            <div class="row">
                                <div class="5u">
                                    <div class="">
                                        <i class="fa fa-net-o"></i>
                                    </div>
                                </div>
                                <div class="7u">
                                    <h3 class="txt_right"><span id="dsb1"><%=request.getAttribute("netAmount") %></u></span></h3>
                                </div>

                                <div class="12u">
                                    <h4>
                                        <span>Net Amount :</span>
                                    </h4>
                                </div>
                            </div>
                        </div>
                        </div>
                      <!--    
			<table width='100%' border="0" >
				
				<tr >
				<td  width="25%" align="left"><font size="2"><b>User Name :</b></font></td>
				
				<td  width="25%" align="left" ><font size="5"><b><u><%=request.getAttribute("userName") %></u></b></font></td>
				
				
				
					<td  width="25%" align="left" nowrap="nowrap"><font size="2"><b>Total No. of Bill :</b></font></td>
					<td  width="25%" align="left" ><font size="5"><b><u><%=request.getAttribute("totalBill") %></u></b></font></td>
					
				</tr>
			</table>
			<table width='100%' border="0" >
				
				<tr>
					<td  width="50%" align="right"><font size="2"><b>Total Recieved Amount :</b></font></td>
					<td  width="50%" align="left"><font size="5"><b><u><%=request.getAttribute("recievedAmount") %></u></b></font></td>
					
				</tr>
				<tr>
					<td  width="50%" align="right"><font size="2"><b>Total Refund Amount :</b></font></td>
					<td  width="50%" align="left"><font size="5"><b><u><%=request.getAttribute("refundAmount") %></u></b></font></td>
					
				</tr>
				<tr>
					<td  width="50%" align="right"><font size="2"><b>Net Amount :</b></font></td>
					<td  width="50%" align="left"><b><u><font size="5" color="blue"><%=request.getAttribute("netAmount") %></font></u></b></td>

				</tr>
				
			</table>
			-->
			
			<table width='100%' border='1' cellpadding='0' cellspacing='0'>
				<tr>
					<td>
					<center><!-- <img
						style="cursor: hand; cursor: pointer"
						src='../../hisglobal/images/btn-ccl.png' tabindex="1"
						onClick="window.close()"> -->
						<a href="#" class="button" onClick="window.close()"><span class="cancel">Cancel</span></a>
						</center>
					</td>
				</tr>
			</table>
			



</body>
</html>