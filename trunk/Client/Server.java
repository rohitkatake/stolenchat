

import java.lang.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
class Server implements Runnable  {

    static /*final*/ String OUTPUTFILENAME = "C:\\";//receivedData";
    static final int PORT		= 5792;public static final int BUFFER_SIZE = 1024 * 50;
	private byte[] buffer;
PrivateChat p1;
String fileSize1="";
int fileOffset;
long fileSize;ProgressMonitor transferProgressBar = null;
     public  Server(PrivateChat p1,String f) {buffer = new byte[BUFFER_SIZE];

   this.p1=p1;
   OUTPUTFILENAME=f+"\\";
        System.out.println("New server running...\n\n");

    }
   public void run(){

            try {

                ServerSocket srvr = new ServerSocket(PORT);
                Socket skt = srvr.accept();
                BufferedInputStream in = new BufferedInputStream( skt.getInputStream() );
                char j=0;
                /*while((j =(char)in.read()) != '}') {
                  OUTPUTFILENAME=OUTPUTFILENAME+j;
                }*/
                 while((j =(char)in.read()) != '}') {
                  fileSize1=fileSize1+j;
                }
				fileSize=Long.parseLong(fileSize1);
                FileOutputStream fos = new FileOutputStream(OUTPUTFILENAME);
                BufferedOutputStream out = new BufferedOutputStream(fos);

transferProgressBar = new ProgressMonitor( new JPanel(),"FileTransfer : "+OUTPUTFILENAME,"0% Completed",0,(int)fileSize);
                                    transferProgressBar.setProgress(0);

                int i;
                while ((i = in.read(buffer)) >0) {
                    out.write(buffer,0,i); fileOffset = (int)fos.getChannel().size();
                                        transferProgressBar.setProgress( fileOffset );
                        				transferProgressBar.setNote((short)(100.0 * (fileOffset) / fileSize)+"% Completed");
                    //System.out.println(i);
                    System.out.println("Receiving data...");
					if(transferProgressBar.isCanceled())
					{
					p1.a1("Transfer Cancelled");
                OUTPUTFILENAME = "C:\\";
                out.flush();
                in.close();
                out.close();
                skt.close();
                srvr.close();					
					Thread.currentThread().stop();
					}
                }
				transferProgressBar.setProgress((int)fileSize);
                transferProgressBar.setNote((short)(100.0 * (fileSize) / fileSize)+"% Completed");
                OUTPUTFILENAME = "C:\\";
                out.flush();
                in.close();
                out.close();
                skt.close();
                srvr.close();
                System.out.println("Transfer complete.");
            p1.a1("Transfer Complete");
            }
            catch(Exception e) {

                System.out.print("Error! It didn't work! " + e + "\n");
                OUTPUTFILENAME = "C:\\";
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.err.println("Interrupted");
            }

        }
    
}
