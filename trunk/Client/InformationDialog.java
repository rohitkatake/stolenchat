

import java.awt.Dialog;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.Choice;
import java.awt.Checkbox;
import java.util.StringTokenizer;
import java.util.Properties;
import java.io.File;
import java.io.FileOutputStream;
public class InformationDialog extends Dialog implements ActionListener,CommonSettings
{
	InformationDialog(ChatClient Parent)
	{		
		super(Parent,PRODUCT_NAME+" - Login",true);
		chatclient = Parent;				
		setFont(chatclient.TextFont);				
		setLayout(new BorderLayout());
		IsConnect = false;
		
		properties=new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("data.properties"));		
		}catch(java.io.IOException exc)  { }
		catch(java.lang.NullPointerException NExc)  { }
		
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {setVisible(false);}});
		
		Panel ButtonPanel = new Panel(new GridLayout(5,2,11,30));				
		ButtonPanel.setBackground(chatclient.ColorMap[3]);
		
		Label LblUserName = new Label("Nick Name: ");
		TxtUserName = new TextField(properties.getProperty("CSEUserName"));		
		ButtonPanel.add(LblUserName);
		ButtonPanel.add(TxtUserName);
		
		Label LblServerName = new Label("Server Name: ");
				
		TxtServerName = new TextField();
		if (properties.getProperty("CSEServerName") != null)
			TxtServerName.setText(properties.getProperty("CSEServerName"));
		else
			//TxtServerName.setText("CSEindia.dns2go.com");
			TxtServerName.setText("localhost");
			
		ButtonPanel.add(LblServerName);
		ButtonPanel.add(TxtServerName);
		
		Label LblServerPort = new Label("Server Port: ");		
		TxtServerPort = new TextField();
		if (properties.getProperty("CSEServerPort") != null)
			TxtServerPort.setText(properties.getProperty("CSEServerPort"));
		else
			TxtServerPort.setText("1436");
			
		ButtonPanel.add(LblServerPort);
		ButtonPanel.add(TxtServerPort);				
		
		Label LblProxy = new Label("Proxy :");
		IsProxyCheckBox = new Checkbox();
		
		IsProxyCheckBox.setState(Boolean.valueOf(properties.getProperty("CSEProxyState")).booleanValue());
						
		//ButtonPanel.add(LblProxy);
		//ButtonPanel.add(IsProxyCheckBox);
		
		Label LblProxyHost = new Label("Proxy Host (Socks): ");
		TxtProxyHost = new TextField();		
		TxtProxyHost.setText(properties.getProperty("CSEProxyHost"));
		//ButtonPanel.add(LblProxyHost);
		//ButtonPanel.add(TxtProxyHost);
		
		Label LblProxyPort = new Label("Proxy Port (Socks): ");
		TxtProxyPort = new TextField();
		TxtProxyPort.setText(properties.getProperty("CSEProxyPort"));
		//ButtonPanel.add(LblProxyPort);
		//ButtonPanel.add(TxtProxyPort);
		
		CmdOk = new Button("Connect");
		CmdOk.addActionListener(this);
		CmdCancel = new Button("Quit");
		CmdCancel.addActionListener(this);
		ButtonPanel.add(CmdOk);
		ButtonPanel.add(CmdCancel);
		
		add("Center",ButtonPanel);
		
		Panel EmptyNorthPanel = new Panel();
		EmptyNorthPanel.setBackground(chatclient.ColorMap[3]);
		add("North",EmptyNorthPanel);
		
		Panel EmptySouthPanel = new Panel();
		EmptySouthPanel.setBackground(chatclient.ColorMap[3]);
		add("South",EmptySouthPanel);
		
		Panel EmptyEastPanel = new Panel();
		EmptyEastPanel.setBackground(chatclient.ColorMap[3]);
		add("East",EmptyEastPanel);
		
		Panel EmptyWestPanel = new Panel();
		EmptyWestPanel.setBackground(chatclient.ColorMap[3]);
		add("West",EmptyWestPanel);
		
		setSize(250,300);
		chatclient.show();
		show();				
	}	
	
	/******** Action Event Coding Starts **************/
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getSource().equals(CmdOk))
		{
			IsConnect = true;	
			FileOutputStream fout=null;
			try {		
				fout = new FileOutputStream(new File("data.properties"));			
			}catch(java.io.IOException exc) { }
			if(IsProxyCheckBox.getState() == true)
				properties.setProperty("CSEProxyState","true");
			else
				properties.setProperty("CSEProxyState","false");			
			properties.setProperty("CSEUserName",TxtUserName.getText());
			properties.setProperty("CSEServerName",TxtServerName.getText());
			properties.setProperty("CSEServerPort",TxtServerPort.getText());
			properties.setProperty("CSEProxyHost",TxtProxyHost.getText());
			properties.setProperty("CSEProxyPort",TxtProxyPort.getText());
			properties.save(fout,PRODUCT_NAME);
			dispose();
		}	
		
		if (evt.getSource().equals(CmdCancel))
		{
			IsConnect = false;
			dispose();
		}	
	}
	
	/********* Global Variable Declarations **********/
	ChatClient chatclient;
	protected TextField TxtUserName,TxtServerName,TxtServerPort,TxtProxyHost,TxtProxyPort;
	protected Button CmdOk,CmdCancel;
	protected Choice roomchoice;
	protected Checkbox IsProxyCheckBox;
	protected boolean IsConnect;
	Properties properties;
}