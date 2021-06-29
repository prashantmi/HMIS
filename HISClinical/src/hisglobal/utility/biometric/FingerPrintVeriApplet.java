package hisglobal.utility.biometric;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.neurotechnology.Library.NetInstall;
import com.neurotechnology.Nffv.Nffv;
import com.neurotechnology.Nffv.NffvStatus;
import com.neurotechnology.Nffv.NffvUser;
import com.neurotechnology.Nffv.ScannerModule;

public class FingerPrintVeriApplet extends JApplet implements ActionListener, ListSelectionListener{
	
	static final int TIMEOUT = 10000;
	JLabel img;
	JList users;
	DefaultListModel usersmodel;
	JButton enroll;
	JButton verify;
	JButton remove;
	JButton clearusers;
	JButton parameters;
	List l=new ArrayList();
	Nffv engine;
	NffvUser curruser;
	
	String database;
	
	public void init(){
		
		ScannerModule scanners[]=Nffv.getAvailableScannerModules();
		System.out.println("Loading scanner modules");
		for (ScannerModule modules : scanners) {	
			System.out.println(modules.getName());
		}	
		
		this.database = database;
		
		
		engine = new Nffv("a", "a", scanners);
		
		usersmodel = new DefaultListModel();
		loadIDNames();
		
		users = new JList(usersmodel);
		users.setMinimumSize(new Dimension(150,Integer.MAX_VALUE));
		users.setPreferredSize(new Dimension(150,Integer.MAX_VALUE));
		users.setMaximumSize(new Dimension(150,Integer.MAX_VALUE));
		users.setBorder(new TitledBorder("Users"));
		users.addListSelectionListener(this);
		img = new JLabel();
		
		enroll = new JButton("Enroll");
		verify = new JButton("Verify");
		remove = new JButton("Remove");
		clearusers = new JButton("Clear users");
		parameters = new JButton("Settings");
		
		enroll.addActionListener(this);
		verify.addActionListener(this);
		remove.addActionListener(this);
		clearusers.addActionListener(this);
		parameters.addActionListener(this);
		
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
		buttons.add(enroll);
		buttons.add(verify);
		buttons.add(remove);
		buttons.add(clearusers);
		buttons.add(parameters);
		buttons.add(Box.createGlue());
		
		setLayout(new BorderLayout());
		add(img, BorderLayout.CENTER);
		add(users, BorderLayout.EAST);
		add(buttons, BorderLayout.NORTH);
		
		setPreferredSize(new Dimension(500,500));
		
		//setPanel(new MainPanel(Nffv.getAvailableScannerModules(), "a","a"));
	}
	
	/*public void setPanel(JPanel panel){
		setContentPane(panel);
		this.setSize(panel.getPreferredSize());
		setVisible(true);
		System.out.println(panel.getClass() + " loaded");
		this.update(getGraphics());
	}*/
	
	public void loadIDNames(){
		File dbfile = new File(database + ".fdb");
		if(dbfile.exists())
		try{
			FileInputStream fis = new FileInputStream(dbfile);
			ObjectInputStream in = new ObjectInputStream(fis);
			
			for (IDName idname = (IDName)in.readObject(); idname != null; idname = (IDName)in.readObject()){
				System.out.println(idname);
				usersmodel.addElement(idname);
				l.add(idname);
			}
			
			in.close();
		}catch (EOFException eof){}
		catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public void saveIDNames(){
		File dbfile = new File(database + ".fdb");
		try{
			FileOutputStream fos = new FileOutputStream(dbfile);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			
			for (int i = 0; i < usersmodel.getSize(); i++)
				out.writeObject(usersmodel.get(i));
			
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void valueChanged(ListSelectionEvent e){
		IDName selection = (IDName)users.getSelectedValue();
		if (selection == null) {
			img.setIcon(null);
			return;
		}
		curruser = engine.getUserByID(selection.getID());
		try{
			img.setIcon(curruser.getNffvImage().getImageIcon());
		}catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
	
public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == enroll){
			
		/*//String name = JOptionPane.showInputDialog(this,new JLabel("Enter name"),"Enter user name");
		double random=Math.random()*10;
		String name=String.valueOf(Math.round(random));
		if(name == null) return;
		
		try{
			curruser = engine.enroll(TIMEOUT);
			System.out.println(engine.getEngineStatus());
		
			if(engine.getEngineStatus() != NffvStatus.TemplateCreated){
				JOptionPane.showMessageDialog(this, "Enroll failed - " + engine.getEngineStatus(), "Failed", JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		
			img.setIcon(curruser.getNffvImage().getImageIcon());
			usersmodel.addElement(new IDName(curruser.getID(),name));
			saveIDNames();*/
			try{
				enroll();
			}catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Enroll failed - " + e.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(arg0.getSource() == verify){
			boolean match=false;
			//for(int i=0;i<usersmodel.size();i++){
				IDName selection = (IDName)users.getSelectedValue();
				//System.out.println("users.getSelectedValue() "+users.getSelectedValue());
				//IDName selection = (IDName)(usersmodel.getElementAt(i));
				//System.out.println(usersmodel.getElementAt(i));
				if(selection == null){
					JOptionPane.showMessageDialog(this,"Please select user to verify with","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//System.out.println("selection.getID() :" +selection.getID());
				NffvUser user = engine.getUserByID(selection.getID());
				int score = engine.verify(user, TIMEOUT);
				if (engine.getEngineStatus() == NffvStatus.TemplateCreated){
					if( score > 0) {
						JOptionPane.showMessageDialog(this,selection.getName() + " matched. \n Fingerprint matching score: " + score,"Verification",JOptionPane.DEFAULT_OPTION);
						match=true;
						//break;
					}
					else 
						match=false;
				}
				else{
					JOptionPane.showMessageDialog(this,"Verification failed -" + engine.getEngineStatus(),"Failed. Please try again",JOptionPane.ERROR_MESSAGE);
					match=true;
					//break;
				}
			//}
			if(!match) JOptionPane.showMessageDialog(this,"Fingerprints did not match with any one","Verification failed",JOptionPane.ERROR_MESSAGE);
		}
		
		if(arg0.getSource() == remove){
			Object [] selections = users.getSelectedValues();
			for (Object object : selections) {
				engine.removeUserID(((IDName)object).getID());
				usersmodel.removeElement((IDName)object);
				users.updateUI();
				saveIDNames();
			}
		}
		
		if(arg0.getSource() == clearusers){
			engine.clearUsers();
			usersmodel.removeAllElements();
			users.updateUI();
			saveIDNames();
		}
		
		if(arg0.getSource() == parameters) new Parameters(engine);
		
	}

	public void enroll(){
	//String name = JOptionPane.showInputDialog(this,new JLabel("Enter name"),"Enter user name");
	double random=Math.random()*10;
	String name=String.valueOf(usersmodel.size()+1);
	if(name == null) return;
	try{
		//URL url=new URL(getCodeBase()+"");
		curruser = engine.enroll(TIMEOUT);
		System.out.println(engine.getEngineStatus());
	
		if(engine.getEngineStatus() != NffvStatus.TemplateCreated){
			JOptionPane.showMessageDialog(this, "Enroll failed - " + engine.getEngineStatus(), "Failed", JOptionPane.ERROR_MESSAGE);
			return;
		}
	
	
		img.setIcon(curruser.getNffvImage().getImageIcon());
		usersmodel.addElement(new IDName(curruser.getID(),name));
		saveIDNames();
	}
	catch (Exception e) {
		// TODO: handle exception
	}
}

}
