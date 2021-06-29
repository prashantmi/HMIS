/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongoapp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class MongoDemoGUI extends JFrame {

    private JTextField filename = new JTextField(), dir = new JTextField();
    private JTextField fileId = new JTextField();
    private JTextArea fileContent = new JTextArea();
    private JLabel fileLabel = new JLabel("File Name: ");
    private JLabel idLabel = new JLabel("Enter Id: ");
    private JLabel dirLabel = new JLabel("Directory Name: ");
    private JLabel contentLabel = new JLabel("File Content from Mongo: ");
    private JLabel statusLabel = new JLabel("Status");
    private JLabel statusLabelAct = new JLabel();
    private File file = null;
    private JButton open = new JButton("Open File from Disk"),
            save = new JButton("Save in Mongo"),
            openMongo = new JButton("Open from Mongo"),
            saveToDisk = new JButton("Save to Disk");

    public MongoDemoGUI() {
        JPanel p = new JPanel();
        open.addActionListener(new OpenL());
        p.add(open);
        save.addActionListener(new SaveL());
        p.add(save);
        openMongo.addActionListener(new OpenMongoL());
        p.add(openMongo);

        Container cp = getContentPane();
        cp.add(p, BorderLayout.CENTER);
        dir.setEditable(false);
        filename.setEditable(false);
        fileId.setEditable(true);
        
        fileContent.setEditable(false);
        fileContent.setLineWrap(true);
        fileContent.setRows(10);
        p = new JPanel();
        p.setLayout(new GridLayout(4, 2));
        p.add(fileLabel);
        p.add(filename);
        p.add(dirLabel);
        p.add(dir);
        p.add(idLabel);
        p.add(fileId);
        p.add(contentLabel);
      //  p.add(statusLabel);
      //  p.add(statusLabelAct);
        JScrollPane jp = new JScrollPane(fileContent);
        p.add(jp);
        cp.add(p, BorderLayout.NORTH);
    }

    class OpenL implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            // Demonstrate "Open" dialog:
            int rVal = c.showOpenDialog(MongoDemoGUI.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                file = c.getSelectedFile();
                filename.setText(c.getSelectedFile().getAbsolutePath());
                dir.setText(c.getCurrentDirectory().toString());
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
                dir.setText("");
            }
        }
    }

    class SaveL implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //JFileChooser c = new JFileChooser();
            MongoDemo dm = new MongoDemo();
            String id = fileId.getText().toString();
            String name = filename.getText().toString();
            try {
               String returnMsg = dm.saveFile(id, name);
               filename.setText(returnMsg);
            // Demonstrate "Save" dialog:
            /* int rVal = c.showSaveDialog(MongoDemoGUI.this);
                 if (rVal == JFileChooser.APPROVE_OPTION) {
            
                 filename.setText(c.getSelectedFile().getName());
                 dir.setText(c.getCurrentDirectory().toString());
                 }
                 if (rVal == JFileChooser.CANCEL_OPTION) {
                 filename.setText("You pressed cancel");
                 dir.setText("");
                 }*/
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(MongoDemoGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class OpenMongoL implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            MongoDemo md = new MongoDemo();
            String id = fileId.getText();
            try {
                String readFile = md.fetchFile(id);
                if(readFile == null)
                {
                    fileContent.setText("File Not Found");
                    filename.setText("File Not found");
                }
                else
                    fileContent.setText(readFile);
            /* JFileChooser c = new JFileChooser();
                 // Demonstrate "Open" dialog:
                 int rVal = c.showOpenDialog(MongoDemoGUI.this);
                 if (rVal == JFileChooser.APPROVE_OPTION) {
                 filename.setText(c.getSelectedFile().getName());
                 dir.setText(c.getCurrentDirectory().toString());
                 }
                 if (rVal == JFileChooser.CANCEL_OPTION) {
                 filename.setText("You pressed cancel");
                 dir.setText("");
                 }*/
            } catch (IOException ex) {
                Logger.getLogger(MongoDemoGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        run(new MongoDemoGUI(), 250, 110);
    }

    public static void run(JFrame frame, int width, int height) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
