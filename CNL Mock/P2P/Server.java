
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Server {
    public static byte[] sendData = new byte[1024];
    public static byte[] recvData = new byte[1024];
    public static void main(String[] args) {
        try{
            DatagramSocket ds = new DatagramSocket(4444);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while(true) {
                System.out.println("Waiting for response...");
                recvData = new byte[1024];
                DatagramPacket rp = new DatagramPacket(recvData, recvData.length);
                ds.receive(rp);
                byte[] ms = rp.getData();
                String re = new String(ms,0,ms.length);
                System.out.println(rp.getAddress() + ":" + rp.getPort() + " -> " + re.trim());
                System.out.println(" -> ");
                sendData = new byte[1024];
                String se = br.readLine();
                sendData = se.getBytes();

                DatagramPacket sp = new DatagramPacket(sendData,sendData.length,rp.getAddress(),rp.getPort());
                ds.send(sp);
            }


        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
