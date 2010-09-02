import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.border.*;
import java.lang.*;
import java.net.*;

public class Frame3 extends JFrame implements ActionListener {
    static private final String newline = "\n";
        final JTextArea log;
    JScrollPane scrollPane;
 PrivateChat p2;   File file;    final JFileChooser fc;
    public Frame3(PrivateChat p2) {
    	this.p2=p2;
    	
        //super("Sockets upload application");


        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){
            System.out.println("Can't get system look and feel, resorting to default.");
        }


        log = new JTextArea(5,55); // 5,60
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
		log.setVisible(false);
        JScrollPane logScrollPane = new JScrollPane(log);


       fc = new JFileChooser();


        //ImageIcon openIcon = new ImageIcon("open.gif");
        JButton openButton = new JButton("Browse File");
        openButton.addActionListener(this);
       
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);


       Container contentPane = getContentPane();
       /* GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        contentPane.setLayout(gridbag);

        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 0;
        //gridbag.setConstraints(buttonPanel, c);
        

        c.weightx = 0.0;
        c.gridwidth = 60;
        c.gridx = 0;
        c.gridy = 1;
        gridbag.setConstraints(logScrollPane, c);
        logScrollPane.setBorder(new LineBorder(Color.black));
        contentPane.add(logScrollPane);*/
		contentPane.add(buttonPanel);
      pack();
       setSize(425,185);
      setVisible(true);}

   public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(Frame3.this);

                log.append("Opening - ");

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                   file = fc.getSelectedFile();
                    log.append(file.getName() + newline);
                     String s=file.getName();setVisible(false);
                    p2.a1("Request for file transfer /"+s);
                   /* Client_socket client = new Client_socket();
                    if (client.send(file.getPath().toString(),file.getName()))
                        log.append("Success." + newline);
                    else
                        log.append("Failure." + newline);
                */
                
            }

   }

}

