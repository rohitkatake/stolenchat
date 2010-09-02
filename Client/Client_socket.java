
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.border.*;
import java.lang.*;
import java.net.*;



 //=============================================================

 class Client_socket implements Runnable {

    static final int PORT    = 5792;
   public static final int BUFFER_SIZE = 1024 * 50;
	private byte[] buffer;
String HOST ; Socket skt;
            FileInputStream fis;
            BufferedInputStream in ;
            BufferedOutputStream out;
     String filename, fname="";PrivateChat p1;
        public Client_socket( String filename,String fname,PrivateChat p1,String s)
        { this.filename=filename;
        //this.fname=fname;
        this.p1=p1;
        HOST=s;
		buffer = new byte[BUFFER_SIZE];

        }
		public void stop1()
		{ try{
    		out.flush();
            out.close();
            in.close();
            skt.close();
			}
			catch(Exception e){}
		}
    public void run() {

        try {
            System.out.print("Sending data...\n");
           skt = new Socket(HOST, PORT);


            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream in = new BufferedInputStream(fis);
            BufferedOutputStream out = new BufferedOutputStream( skt.getOutputStream() );
		//	.setFileSize( inFile.getChannel().size() );
            //fname=fname+"}";
			fname=fname+fis.getChannel().size()+"}"; 
            byte[] name=fname.getBytes();
            for(int i=0;i<name.length;i++)
                 out.write(name[i]);

            int i;
            while ((i = in.read(buffer)) > 0) {
			out.write(buffer, 0, i);}

            


            out.flush();
            out.close();
            in.close();
            skt.close();
             // p1.a1("");
           
        }
        catch( Exception e ) {


            System.out.print("Error! It didn't work! " + e + "\n");
             //p1.a1("");
           
        }
    }
}
//==============================================================
  