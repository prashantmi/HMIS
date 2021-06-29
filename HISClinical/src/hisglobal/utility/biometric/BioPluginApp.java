package hisglobal.utility.biometric;



	import java.awt.BorderLayout;
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

import javax.swing.JApplet;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JTextField;

	import netscape.javascript.JSObject;

	import m2sys.BIOPLUGINACTXLib.BioPlugInActX;

	import com.inzoom.axjni.JActiveX;

	import com.inzoom.comjni.Dll;
	import com.inzoom.comjni.IDispatch;
	import com.inzoom.comjni.IUnknown;
	import com.inzoom.comjni.Variant;
	import com.inzoom.comjni.jcw.IDispatchJCW;
	/*
	 * .............
	 * @author Mahmud
	 * .............
	 */
	
	public class BioPluginApp extends JApplet implements m2sys.BIOPLUGINACTXLib._DBioPlugInActXEvents{
		
		//Properties
		private JLabel _m_id = new JLabel();
		private JTextField _reg_no = new JTextField();
		private JLabel _msg = new JLabel("Status message here");
		m2sys.BIOPLUGINACTXLib.CreateLicense license;
		JActiveX jx;
		IDispatch dis = null;
		IUnknown un = null;

		private com.inzoom.axjni.JActiveX jActiveX;
		private com.inzoom.comjni.IUnknown unknown;
		private com.inzoom.comjni.IDispatch dispatch;
		private m2sys.BIOPLUGINACTXLib.BioPlugInActX biox;
		private String identity="";
		private String thumbImpId="";
		
		public String getIdentity() {
			return identity;
		}

//		public void setIdentity(String identity) {
//			Identity = identity;
//		}

		/**
		 * Constructor
		 */
		public void init() {
			//super("BioPlugin");
			
			if(!Dll.isLoaded()){
			license=new m2sys.BIOPLUGINACTXLib.CreateLicense();
	            	license.licenseInstall();
			}
			jx=new JActiveX("BIOPLUGINACTX.BioPlugInActXCtrl.1");
			jx.setSize(100, 50);
			
			//Do Update for fingerPrint 
		/*	JButton btn_update = new JButton("Update");
			btn_update.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					processUpdate();
				}
			});*/
			
//			//Do verification for fingerPrint Renuka
//			JButton btn_verify = new JButton("Verification");
//			btn_verify.addActionListener(new ActionListener(){
//				public void actionPerformed(ActionEvent e) {
//					processVerification();
//				}
//			});
			
			//Set Fingerprint Enrollment
			JButton btn_register = new JButton("Thumb Impression");
			btn_register.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					processRegistration();
				}
			});
			
			//Do Identification for fingerPrint
			/*JButton btn_identify = new JButton("Close");
			btn_identify.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					processIdentification();
				}
			});*/
			
			GridBagLayout gridbag = new GridBagLayout();
			GridBagConstraints gc = new GridBagConstraints();
			gc.fill = GridBagConstraints.BOTH;
			JPanel panel = new JPanel(gridbag);
			_m_id.setText("ID : ");
			panel.add(_m_id);
			_reg_no.setText("");
			panel.add(_reg_no);
			panel.add(btn_register);
			//panel.add(btn_verify);
			//panel.add(btn_update);
			//panel.add(btn_identify);
			gridbag.setConstraints(_m_id, gc);
			gc.weightx = 1;
			gridbag.setConstraints(_reg_no, gc);
			getContentPane().add(panel, BorderLayout.NORTH);
			getContentPane().add(jx, BorderLayout.CENTER);
			
			getContentPane().add(new JPanel().add(_msg).getParent(), BorderLayout.SOUTH);
		}
		
		/**
		 * Initializes <b>un</b> object of IUnknown 
		 * and <b>dis</b> object of IDispatch 
		 * 
		 */
		private void unknown_dispatch(){
			un = jx.getUnknown();
			try{
				dis = IDispatchJCW.getIDispatchFromUnknown(un);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		/**
		 * Setup Bioplugin Parameters
		 */
		private void setup_Bioplugin(){
			try{
				unknown_dispatch();
				biox = BioPlugInActX.getBioPlugInActXFromUnknown(un);
				biox.add_DBioPlugInActXEventsListener(BioPluginApp.this);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		/**
		 * Sets message for status
		 */
		private void setMsg(){
			String reg = _reg_no.getText();
			_msg.setText("Registering: " + reg);
		}
		
		/**
		 * Verifies fingerPrint
		 */
		private void processVerification(){
			setMsg();
			setup_Bioplugin();
			try{
				dis.invokeMethod("VerifyQuick", new Variant[]{new Variant(_reg_no.getText())});
			}catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				if(un!=null) un.release();
				if(dis!=null) dis.release();
			}
		}
		
		/**
		 * Registers fingerPrint
		 */
		private void processRegistration(){
			setMsg();			
			setup_Bioplugin();
			try{
				try{
					System.out.println("processRegistration------------->>>>>>>>>>>>");
					//System.out.println("thumbImpId  :"+thumbImpId);
					
					dis.invokeMethod("RegisterSinglePrintShort", new Variant[]{new Variant(_reg_no.getText())});
					//JSObject.getWindow (this).eval("javascript:self.close()");
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					if(un!=null) un.release();
					if(dis!=null) dis.release();				
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		/**
		 * Identifies fingerPrint
		 */
	/*	private void processIdentification(){

			setMsg();			
			setup_Bioplugin();
				try{
					System.out.println("Closing Event");
					JSObject.getWindow (this).eval("javascript:closeEvent()") ; 
					//dis.invokeMethod("IdentifyQuick", new Variant[]{new Variant("0")});
					//JSObject.getWindow (this).eval("javascript:closeEvent()");
				}catch(Exception ex){
					ex.printStackTrace();
				}
				

		}*/
		
		/**
		 * Updates fingerPrint
		 */
		/*private void processUpdate(){
			setMsg();			
			setup_Bioplugin();
			try{
				try{
					dis.invokeMethod("UpdateSinglePrint", new Variant[]{new Variant(_reg_no.getText())});
				}catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					if(un!=null) un.release();
					if(dis!=null) dis.release();				
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}*/
		/*public static void thumMain(){
			System.out.println("New Function For Thum Impression");
			JFrame frm = new BioPlugin();
			//frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frm.setDefaultCloseOperation(JFrame. DO_NOTHING_ON_CLOSE);
			frm.setSize(400, 380);
			frm.setVisible(true);
		}*/
		
		
	/*	public static void main(String [] args){
			JFrame frm = new BioPlugin();
			//frm.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE);
			frm.setDefaultCloseOperation(JFrame. DO_NOTHING_ON_CLOSE);
			frm.setSize(400, 380);
			frm.setVisible(true);
		}*/
		public void OnScan() {	
		}
		
		public void destroy() {
			jx=null;
			biox=null;
			license=null;
			
		}
		
		
		public void OnRegister() {
			unknown_dispatch();
			try{
				System.out.println("On Register------------->>>>>>>>>>>>");
				Variant vr = dis.invokeGet("result");
				//_msg.setText("OnRegister : " + vr.getString());
				_msg.setText("New Donor : " + vr.getString());
				thumbImpId=vr.getString();
				System.out.println("On Register thumbImpId :" + thumbImpId);
				if(thumbImpId.trim().equals("0"))
					JSObject.getWindow (this).eval("javascript:populateRegNo(\""+_reg_no.getText().trim()+"\")") ;
				else {
					System.out.println("_reg_no.getText() :"+ _reg_no.getText());
					JSObject.getWindow (this).eval("javascript:populateRegNo(\""+thumbImpId.trim()+"\")") ;
				}	
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		
		
		public void OnChange() {
			unknown_dispatch();
			try{
				Variant vr = dis.invokeGet("result");
				_msg.setText("OnChange : " + vr.getString());
				thumbImpId=vr.getString();
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		
		
		public void OnConfiguration() {
		}
		
		
		public void OnIdentify() {
	            
			unknown_dispatch();
			
			try{
				Variant vr = dis.invokeGet("result");
				//_msg.setText("OnIdentify : " + vr.getString());
				identity = vr.getString();
				_msg.setText("Three months has to complete of this Donor : " + vr.getString());
				thumbImpId=vr.getString();
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		public void OnDelete() {
			
		}
		
		
		public void SendResult() {
		}
		
		
		public void OnCapture() {
		}
		
		
		public void OnError() {
		}
		
		
		public void OnScannerStatus(){
		}
		
		
		public void OnVerify() {
			unknown_dispatch();
			try{
				Variant vr = dis.invokeGet("result");
				_msg.setText("OnVerify : " + vr.getString());
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
	

}
