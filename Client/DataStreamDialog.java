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
public class DataStreamDialog extends Dialog implements ActionListener,CommonSettings
{
	DataStreamDialog(ChatClient Parent)
	{
		super(Parent,"Stream Media/File Transfer",true);
		fileoption=streamoption=false;
		chatclient = Parent;				
		setFont(chatclient.TextFont);				
		setLayout(new BorderLayout());
		//properties=new Properties();
		//try {
			//properties.load(this.getClass().getClassLoader().getResourceAsStream("data.properties"));		
		//}catch(java.io.IOException exc)  { }
		//catch(java.lang.NullPointerException NExc)  { }
		
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {setVisible(false);}});
		
		Panel ButtonPanel = new Panel();//new GridLayout(7,2,15,30));				
		ButtonPanel.setBackground(chatclient.ColorMap[3]);
		
		Label LblUserName = new Label("Do you want to stream a media file or transfer a file to a buddy?");
//		TxtUserName = new TextField(properties.getProperty("22224"));		
		ButtonPanel.add(LblUserName);
	//	ButtonPanel.add(TxtUserName);				
		CmdFile = new Button("File Transfer");
		CmdFile.addActionListener(this);
		CmdStream = new Button("Stream Media");
		CmdStream.addActionListener(this);
		ButtonPanel.add("East",CmdFile);
		ButtonPanel.add("West",CmdStream);
		
		add("Center",ButtonPanel);
		
		/*Panel EmptyNorthPanel = new Panel();
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
		add("West",EmptyWestPanel);*/
		
		//setSize(250,400);
		setSize(350,100);
                setResizable(false);
		//chatclient.show();
		show();				
	}	
	
	/******** Action Event Coding Starts **************/
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getSource().equals(CmdFile))
		{
			//FileOutputStream fout=null;
			//try {		
				//fout = new FileOutputStream(new File("data.properties"));			
			///}catch(java.io.IOException exc) { }			
			//properties.setProperty("RTP Number:",TxtUserName.getText());
			//properties.save(fout,PRODUCT_NAME);
			fileoption=true;
			dispose();
		}	
		
		if (evt.getSource().equals(CmdStream))
		{
		    streamoption=true;
			dispose();
		}	
	}
	
	/********* Global Variable Declarations **********/
	ChatClient chatclient;
//	protected TextField TxtUserName;
	protected Button CmdFile,CmdStream;
	boolean fileoption,streamoption;
	//Properties properties;
}