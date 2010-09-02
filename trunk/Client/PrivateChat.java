/*************************************************************************/
/*************************************************************************/
/*************************************************************************/
/*****************Chat Client Private Chat********************************/
/*************************************************************************/
/*************************************************************************/
/*************************************************************************/
import java.awt.Panel;
import java.awt.Label;
import java.awt.Window;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Image;
import java.net.URL;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.*;
import java.net.*;
//----------------------------------------------
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
public class PrivateChat extends Frame implements CommonSettings,KeyListener,ActionListener
{
	PrivateChat(ChatClient Parent, String ToUserName)
	{
		chatclient = Parent;
		UserName = ToUserName;
		setTitle("Private Chat With "+UserName);
		Image IconImage = Toolkit.getDefaultToolkit().getImage("images/logo.gif");
		setIconImage(IconImage);
		setBackground(chatclient.ColorMap[0]);
		setFont(chatclient.getFont());
		EmotionFlag = false;
		InitializeComponents();
		/****Window Closing Event *****/
		addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent evt) { ExitPrivateWindow(); }});
	}

	/******* Initialize All Components **********/
	private void InitializeComponents()
	{
		setLayout(null);
		Label LblConversation = new Label("Conversation With "+UserName);
		LblConversation.setForeground(chatclient.ColorMap[5]);
		LblConversation.setBounds(5, 30, 400, 20);
		add(LblConversation);

		Panel MessagePanel = new Panel(new BorderLayout());
		messagecanvas = new MessageCanvas(chatclient);
		MessageScrollView = new ScrollView(messagecanvas,true,true,TAPPANEL_CANVAS_WIDTH,TAPPANEL_CANVAS_HEIGHT,SCROLL_BAR_SIZE);
	  	messagecanvas.scrollview = MessageScrollView;
		MessagePanel.add("Center",MessageScrollView);
		MessagePanel.setBounds(5, 50, 400, 200);
		add(MessagePanel);

		TxtMessage = new TextField();
		TxtMessage.addKeyListener(this);
		TxtMessage.setFont(chatclient.TextFont);
		TxtMessage.setBounds(10, 260, 320, 20);
		add(TxtMessage);

		CmdSend = new CustomButton(chatclient,"Send");
		CmdSend.addActionListener(this);
		CmdSend.setBounds(335, 260, 70, 20);
		add(CmdSend);

		CmdClear = new CustomButton(chatclient,"Clear");
		CmdClear.addActionListener(this);
		CmdClear.setBounds(10, 290, 80, 20);

		CmdAccept=new CustomButton(chatclient,"Accept");
		CmdAccept.addActionListener(this);
		CmdAccept.setBounds(80, 315, 50, 20);

		CmdF=new CustomButton(chatclient,"File Transfer/Stream");
		CmdF.addActionListener(this);
		CmdF.setBounds(150, 315, 140, 20);
		
		CmdE=new CustomButton(chatclient,"Start Editor");
		CmdE.addActionListener(this);
		CmdE.setBounds(310, 315, 80, 20);
	
		CmdIgnore = new CustomButton(chatclient,"Ignore User");
		CmdIgnore.addActionListener(this);
		CmdIgnore.setBounds(110, 290, 80, 20);

		CmdClose = new CustomButton(chatclient,"Close");
		CmdClose.addActionListener(this);
		CmdClose.setBounds(210, 290, 80, 20);

		CmdVideo = new CustomButton(chatclient,"Video");
		CmdVideo.addActionListener(this);
		CmdVideo.setBounds(10, 315, 50, 20);

		CmdEmoticons = new CustomButton(chatclient,"Emoticons");
		CmdEmoticons.addActionListener(this);
		CmdEmoticons.setBounds(310, 290, 80, 20);

		add(CmdClear);
		add(CmdIgnore);
		add(CmdClose);
		add(CmdEmoticons);
		add(CmdVideo);		
		add(CmdAccept);
		add(CmdF);
		add(CmdE);

		EmotionPanel = new Panel(new BorderLayout());
		emotioncanvas = new EmotionCanvas(chatclient,this);
		EmotionScrollView = new ScrollView(emotioncanvas,true,true,EMOTION_CANVAS_WIDTH,EMOTION_CANVAS_HEIGHT,SCROLL_BAR_SIZE);
	  	emotioncanvas.scrollview = EmotionScrollView;
	  	/**********Add Icons into MessageObject *********/
	  	emotioncanvas.AddIconsToMessageObject();
		EmotionPanel.add("Center",EmotionScrollView);
		EmotionPanel.setVisible(false);
		EmotionPanel.setBounds(5,350,EMOTION_CANVAS_WIDTH,EMOTION_CANVAS_HEIGHT);
		add(EmotionPanel);

		setSize(PRIVATE_WINDOW_WIDTH,PRIVATE_WINDOW_HEIGHT);
		setResizable(false);
		show();
		this.requestFocus();
	}

	/***********Action Listener coding **********/
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource().equals(CmdAccept))
		{
		chatclient.SentPrivateMessageToServer("Client has accepted and start transmitting",UserName);
		CmdAccept.setEnabled(false);	
		}
		if(evt.getSource().equals(CmdSend))
		{
			/******** Send Message *********/
			if (!(TxtMessage.getText().trim().equals("")))
				SendMessage();
		}
		
		if(evt.getSource().equals(CmdE))
		{
		if(edit==null)
		{
		edit=new myedit(this);
		}
		}
		if(evt.getSource().equals(CmdVideo))
		{
		messagecanvas.AddMessageToMessageObject("Requesting for Video Chat",MESSAGE_TYPE_DEFAULT);
		chatclient.SentPrivateMessageToServer("Video is coming Click on Accept",UserName);
		CmdVideo.setEnabled(false);
		}

		/*****Close Button Event ********/
		if(evt.getSource().equals(CmdClose))
		{	//chatclient.SentPrivateMessageToServer("Close ",UserName);
		System.out.println("within close"+"t3");
	//	System.out.println(t3.isAlive()+"t"+t.isAlive());
		if(t3!=null)
	{
			if(t3.isAlive())
				{at.a3();
				t3.stop();
				t3=null;
				at=null;
				messagecanvas.AddMessageToMessageObject("Video transmission stopped",MESSAGE_TYPE_ADMIN);
					chatclient.SentPrivateMessageToServer("Stop Receiving",UserName);
				try{
					Thread.sleep(2000);}catch (InterruptedException e){}}
	}
		if(t!=null)
		{
			if(t.isAlive())
		{	ar.a3();
	        t.stop();
	        t=null;
	        ar=null;
		}	}	
if(t3stream!=null)
	{
			if(t3stream.isAlive())
				{atstream.a3();
				t3stream.stop();
				t3stream=null;
				atstream=null;
				messagecanvas.AddMessageToMessageObject("Video streaming stopped",MESSAGE_TYPE_ADMIN);
					chatclient.SentPrivateMessageToServer("Stop Streaming",UserName);
				try{
					Thread.sleep(2000);}catch (InterruptedException e){}}
	}
		if(tstream!=null)
		{
			if(tstream.isAlive())
		{	arstream.a3();
	        tstream.stop();
	        tstream=null;
	        arstream=null;
		}	}				
			ExitPrivateWindow();
		}
		if(evt.getSource().equals(CmdF))
		{
			dialog=new DataStreamDialog(chatclient);
			if(dialog.fileoption)
			{
			f3=new Frame3(PrivateChat.this);
			}
			else
			{
      JFileChooser fileChooser = new JFileChooser();
      int result = fileChooser.showOpenDialog( null );
      if ( result == JFileChooser.APPROVE_OPTION ) // user chose a file
      {

         
         try
         {
            // get the file as URL
            mediaURL = fileChooser.getSelectedFile().toURL();
         } // end try
         catch ( MalformedURLException malformedURLException )
         {
            System.err.println( "Could not create URL for the file" );
         } // end catch

         if ( mediaURL != null ) // only display if there is a valid URL
         {messagecanvas.AddMessageToMessageObject("Requesting for File to Receive",MESSAGE_TYPE_DEFAULT);
		 chatclient.SentPrivateMessageToServer("Request for Streaming /"+mediaURL,UserName);
            /*JFrame mediaTest = new JFrame( "Chat Media Player" );
            mediaTest.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );            
            MediaPanel mediaPanel = new MediaPanel( mediaURL );
            mediaTest.add( mediaPanel );
            mediaTest.setSize( 500,500 );
            mediaTest.setVisible( true );*/
			
         } // end inner if
      }
					
			}
		}
		/*********Clear Button Event ********/
		if(evt.getSource().equals(CmdClear))
		{
			/******** Send Message *********/
			if (!(TxtMessage.getText().trim().equals("")))
				SendMessage();
			messagecanvas.ClearAll();
		}

		/***** Ignore Action Event ********/
		if(evt.getSource().equals(CmdIgnore))
		{
			if(evt.getActionCommand().equals("Ignore User"))
			{
				chatclient.tappanel.UserCanvas.IgnoreUser(true,UserName);
				messagecanvas.AddMessageToMessageObject(UserName +" has been ignored!",MESSAGE_TYPE_ADMIN);
				CmdIgnore.setLabel("Allow User");
		/*	if(t3!=null)
	{
			if(t3.isAlive())
				{at.a3();
				t3.stop();
				t3=null;
				messagecanvas.AddMessageToMessageObject("Video transmission stopped",MESSAGE_TYPE_ADMIN);
				try{
					Thread.sleep(2000);}catch (InterruptedException e){}}
	}*/
		if(t!=null)
		{
			if(t.isAlive())
		{	ar.a3();
	        t.stop();
	        t=null;
	        ar=null;
		}	
		}
        else	if(t3!=null)
	{
			if(t3.isAlive())
				{at.a3();	messagecanvas.AddMessageToMessageObject("Video transmission stopped",MESSAGE_TYPE_ADMIN);
							chatclient.SentPrivateMessageToServer("Stop Receiving",UserName);
		}}if(tstream!=null)
		{
			if(tstream.isAlive())
		{	arstream.a3();
	        tstream.stop();
	        tstream=null;
	        arstream=null;
		}	
		}
        else	if(t3stream!=null)
	{
			if(t3stream.isAlive())
				{atstream.a3();	messagecanvas.AddMessageToMessageObject("Video streaming stopped",MESSAGE_TYPE_ADMIN);
							chatclient.SentPrivateMessageToServer("Stop Streaming",UserName);
		}
		}}
		
			else
			{
				messagecanvas.AddMessageToMessageObject(UserName +" has been removed from ignored list!",MESSAGE_TYPE_ADMIN);
				chatclient.tappanel.UserCanvas.IgnoreUser(false,UserName);
				CmdIgnore.setLabel("Ignore User");
			}
		}

		/***** Emoticons Action Event ********/
		if(evt.getSource().equals(CmdEmoticons))
		{
			if(EmotionFlag)
			{
				EmotionFlag = false;
				EmotionPanel.setVisible(false);
				setSize(PRIVATE_WINDOW_WIDTH,PRIVATE_WINDOW_HEIGHT);
			}
			else
			{
				EmotionFlag = true;
				EmotionPanel.setVisible(true);
				setSize(PRIVATE_WINDOW_WIDTH,PRIVATE_WINDOW_HEIGHT+EMOTION_CANVAS_HEIGHT);
			}
		}
		
	}

	/********* Key Listener Event *************/
	public void keyPressed(KeyEvent evt)
	{
		if((evt.getKeyCode() == 10) && (!(TxtMessage.getText().trim().equals(""))))
		{
			SendMessage();
		}
	}

	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}

	private void SendMessage()
	{
		messagecanvas.AddMessageToMessageObject(chatclient.UserName+": "+TxtMessage.getText(),MESSAGE_TYPE_DEFAULT);
		chatclient.SentPrivateMessageToServer(TxtMessage.getText(),UserName);
		TxtMessage.setText("");
		TxtMessage.requestFocus();
	}

	/******** Function to Set the Image Name into Text Field ************/
	protected void AddImageToTextField(String ImageName)
   	{
   		if(TxtMessage.getText()==null || TxtMessage.getText().equals(""))
			TxtMessage.setText("~~"+ImageName+" ");
		else
			TxtMessage.setText(TxtMessage.getText()+" "+"~~"+ImageName+" ");
   	}
 
     public void a1(String s)
	 {
	 chatclient.SentPrivateMessageToServer(s,UserName);
	 }	
	 public void editclose()
	 {
	 edit=null;
	 //chatclient.SentPrivateMessageToServer(s,UserName);
	 }	
	
	
	
	/*********Function to Add a Message To Messagecanvas *********/
	protected void AddMessageToMessageCanvas(String Message)
	{
	if(Message.indexOf("Close Transmit")!=-1)
	{System.out.println("thread alive"+t3.isAlive()+"name"+t3.getName());
		if(t3!=null)
			if(t3.isAlive())
		{
			at.a3();
	        t3.stop();
	        t3=null;
	        at=null;
	 
	}
	CmdVideo.setEnabled(true);
	}
	else
	if(Message.indexOf("Close Stream")!=-1)
	{ if(t3stream!=null)
	   	 if(t3stream.isAlive())
		{
			atstream.a3();
	        t3stream.stop();
	        t3stream=null;
	        atstream=null;
	    }
	}else if(Message.indexOf("Video is coming Click on Accept")!=-1)
	{
	CmdAccept.setEnabled(true);
	messagecanvas.AddMessageToMessageObject(Message.substring(Message.indexOf(":")+1),MESSAGE_TYPE_DEFAULT);
	}
	else if(Message.indexOf("text~")!=-1)
	{
	Message=Message.substring(Message.indexOf("~")+1);
	if(edit==null) 
	edit=new myedit(this);
	System.out.println(Message);
	edit.done=false;
	edit.text.setText(edit.decode(Message));
	edit.done=true;
	 return;}
	else if(Message.indexOf("Request for Streaming")!=-1)
    	{s3=Message.substring(Message.indexOf('/')+1);
    		int choice = JOptionPane.showConfirmDialog( null, Message.substring(0,Message.indexOf(":")+1) +" is sending you " +s3+ ", \nAccept Streaming?","File Streaming Request",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE );
    	 if ( choice == JOptionPane.OK_OPTION )
            { chatclient.SentPrivateMessageToServer("Client has Accepted streaming",UserName);
			}
		}else
    	if(Message.indexOf("Request for file transfer")!=-1)
    	{ s3=Message.substring(Message.indexOf('/')+1);
    		int choice = JOptionPane.showConfirmDialog( null, Message.substring(0,Message.indexOf(":")+1)+ " is sending you :" +s3+  ", \nAccept Transfer?","File Transfer Request",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE );
    	 if ( choice == JOptionPane.OK_OPTION )
            {
                JFileChooser saveDialog = new JFileChooser();
                saveDialog.setFileSelectionMode( JFileChooser.FILES_ONLY );
                saveDialog.setSelectedFile( new File(saveDialog.getCurrentDirectory()+"\\" +s3));
                //saveDialog.setCurrentDirectory( new File(saveDialog.getCurrentDirectory()+s3) );
                choice = saveDialog.showSaveDialog( this );
                   
               // if ( choice == JFileChooser.APPROVE_OPTION )
               // {
                	Server s6=new Server(this,saveDialog.getSelectedFile().toString());
               Thread t5=new Thread(s6); // fileTransferHandler.receiveFile( saveDialog.getSelectedFile(),request.getFileSize(),true );
               t5.start();
               try{ InetAddress address = InetAddress.getLocalHost();
      s=address.toString();
      s=s.substring(s.indexOf('/')+1);}
    catch(UnknownHostException e){
      System.out.println(e);
      System.out.println("Must be online to run properly.");
    }
                 chatclient.SentPrivateMessageToServer("Now Transfer File/"+s,UserName);
                 }else
                   chatclient.SentPrivateMessageToServer("Client has rejected your file",UserName);
                    
           /* }
            else //new File( System.getProperty("user.dir") )
                fileTransferHandler.receiveFile( null,0,false );
    	*/}else
    	if(Message.indexOf("Transfer Complete")!=-1)
    	{f3.dispose();
    	}else
    	if(Message.indexOf("Now Transfer File")!=-1)
    	{  s=Message.substring(Message.indexOf('/')+1);
    	System.out.println("ip for file"+s);
    		 client = new Client_socket(f3.file.getPath().toString(),f3.file.getName(),this,s);
    	 t4=new Thread(client);
                    t4.start();
                                
        

    	}else
    		if(Message.indexOf("Another Client is receiving")!=-1)
    		{ 	ip=Message.substring(Message.indexOf('/')+1);
    		chatclient.SentPrivateMessageToServer("Now Receive/"+ip,UserName);
    		}else
	if(Message.indexOf("Stop Receiving")!=-1)
	{	if(t.isAlive())
		{  
			ar.a3();
			
	        //t.stop();
	    }
	}else
	
	if(Message.indexOf("Stop Streaming")!=-1)
	{	if(tstream.isAlive())
		{  
			arstream.a3();
			
	        //t.stop();
	    }
	}else
	if(Message.indexOf("Client has Accepted streaming")!=-1)
	    {
		//Av at=new Av();
	ip=Message.substring(Message.indexOf('/')+1);
		System.out.println("ip"+ip);
		String t1stream[]={mediaURL.toString(),ip,"2224"};
		//at.AVfun(t);
		try
		{atstream=new Av(t1stream);
	    t3stream=new Thread(atstream);
		t3stream.start();
		//System.out.println("thread name"+t3.getName());
		 t3stream.setPriority(Thread.MIN_PRIORITY);
		// t3.setName("Thread-3");
		Thread.sleep(6000);
		}
		catch(InterruptedException e){System.out.println("here");}


		chatclient.SentPrivateMessageToServer("Receive Stream/"+ip,UserName);
		}
		else
		if((Message.indexOf("Receive Stream")!=-1))
	    {
	 ip=Message.substring(Message.indexOf('/')+1);
				System.out.println("ip"+ip);
		//String port=Message.substring(0,Message.indexOf(':')+1);
		//AV2 ar=new AV2(q);
		String qstream[]={ip+"/2224",ip+"/2226"};
		//ar.AVfun(q);
		 arstream=new AV2(qstream,this);
        tstream=new Thread(arstream);
        tstream.setPriority(Thread.MIN_PRIORITY);
        //t.setName("Thread-3");
      		tstream.start();
		}
		
		else
	
	if(Message.indexOf("Client has accepted and start transmitting")!=-1)
	    {
		//Av at=new Av();
	ip=Message.substring(Message.indexOf('/')+1);
		System.out.println("ip"+ip);
		String t1[]={"vfw://0",ip,"2224"};
		//at.AVfun(t);
		try
		{at=new Av(t1);
	    t3=new Thread(at);
		t3.start();
		System.out.println("thread name"+t3.getName());
		 t3.setPriority(Thread.MIN_PRIORITY);
		// t3.setName("Thread-3");
		Thread.sleep(6000);
		}
		catch(InterruptedException e){System.out.println("here");}


		chatclient.SentPrivateMessageToServer("Now Receive/"+ip,UserName);
		//return;
		}
		else
		if((Message.indexOf("Now Receive")!=-1))
	    {
	 ip=Message.substring(Message.indexOf('/')+1);
				System.out.println("ip"+ip);
		//String port=Message.substring(0,Message.indexOf(':')+1);
		//AV2 ar=new AV2(q);
		String q[]={ip+"/2224/1/video"};
		//ar.AVfun(q);
		 ar=new AV2(q,this);
        t=new Thread(ar);
        t.setPriority(Thread.MIN_PRIORITY);
        //t.setName("Thread-3");
      		t.start();
		}
		else
		if((Message.indexOf("Transfer Cancelled")!=-1))
			{
			client.stop1();
			t4.stop();
		}
		else
		messagecanvas.AddMessageToMessageObject(Message,MESSAGE_TYPE_DEFAULT);
	}

	protected void DisableAll()
	{   CmdAccept.setEnabled(false);
	    CmdVideo.setEnabled(false);
		TxtMessage.setEnabled(false);
		CmdSend.setEnabled(false);
	}

	protected void EnableAll()
	{CmdAccept.setEnabled(true);
	    CmdVideo.setEnabled(true);
		TxtMessage.setEnabled(true);
		CmdSend.setEnabled(true);
	}

	/****** Exit from Private Chat */
    private void ExitPrivateWindow() {
    	chatclient.RemovePrivateWindow(UserName);
        setVisible(false);
    }

	/*************** Global Variable Declarations ****************/
	ChatClient chatclient;
	protected String UserName;
	MessageCanvas messagecanvas;
	ScrollView MessageScrollView;
	TextField TxtMessage;
	Button CmdSend,CmdClose,CmdIgnore,CmdClear,CmdEmoticons,CmdVideo,CmdAccept,CmdF,CmdE;
	EmotionCanvas emotioncanvas;
	ScrollView EmotionScrollView;
	boolean EmotionFlag;
	Panel EmotionPanel;
	Av at=null,atstream=null;
	Thread t3=null,t=null,t3stream=null,tstream=null,t4=null;
	AV2 ar=null,arstream=null;
		Frame3 f3;
		String s3,s,ip; Client_socket client;
				DataStreamDialog dialog;         URL mediaURL = null;
				myedit edit;
}