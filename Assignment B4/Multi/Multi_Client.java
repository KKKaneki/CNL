import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class Multi_Client implements Runnable
{
 static MulticastSocket clientSocket ;
 static byte[] recvData = new byte[1024];
        static String getsen="hmmm";
        static boolean send_flag=false;
       public static void main(String[] args){
              BufferedReader br ;
              try {
              clientSocket = new MulticastSocket(4447);
              InetAddress group = InetAddress.getByName("230.0.0.1");
              InetAddress IPAddr = InetAddress.getByName("localhost");                       
              clientSocket.joinGroup(group);
              byte[] sendData = new byte[1024];
              String sentence=null;
              new Thread(new Multi_Client()).start();
              while(true) {
                     sentence=null;
                     br= new BufferedReader(new InputStreamReader(System.in));
                                                 sentence=br.readLine(); 
                                                 sendData = new byte[1024]; 
                     sendData = sentence.getBytes();
                     DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,IPAddr,9877);
                     clientSocket.send(sendPacket);
                     send_flag = true;
              }

              } catch (Exception e) {
                     e.printStackTrace();
              }
       
       }

       @Override
       //READ FROM SERVER
       public void run(){
              DatagramPacket recvPacket;
              while(!getsen.equals("Bye")){
                     try {
                            recvPacket = new DatagramPacket(recvData,recvData.length);
                     clientSocket.receive(recvPacket);
                     getsen = new String(recvPacket.getData());
                                          if(send_flag!=true)
                     System.out.println("BROADCASTED MESSAGE:"+getsen.trim());
                                          getsen= "";
                     send_flag = false;
                     } catch (IOException e) {
                            e.printStackTrace();
                     }
              }
       }

}