import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        DatagramSocket ds = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            ds = new DatagramSocket(3333);
            byte[] recvData = new byte[1024];

            
            System.out.println("Server Socket Created\n Waiting For data\n");
            while(true) {
                DatagramPacket drp = new DatagramPacket(recvData, recvData.length);

                ds.receive(drp);
                String recvMsg = new String(drp.getData());
                System.out.println(drp.getAddress().getHostAddress() + ":" + drp.getPort() + " => " + recvMsg.trim());

                String msg = br.readLine();
                byte[] sendData = msg.getBytes();
                DatagramPacket dsp = new DatagramPacket(sendData,msg.length(),drp.getAddress(),drp.getPort());
                
                ds.send(dsp);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}