import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static int PORT = 3333;
    
    public static void main(String[] args){
        BufferedReader br = null;
        DatagramSocket socket = null;
        String sen;

        try {
            socket = new DatagramSocket();
            br = new BufferedReader(new InputStreamReader(System.in));
            InetAddress server = InetAddress.getByName("127.0.0.1");
            System.out.println("Client Socket Created\nEnter Msg: ");

            while(true) {
                System.out.print(" -> ");
                sen = br.readLine();
                byte[] sendData = sen.getBytes();
                
                DatagramPacket sendPack = new DatagramPacket(sendData,sen.length(),server,3333);
                socket.send(sendPack);
                System.out.println("Waiting For Reply");

                byte[] recData = new byte[1024];
                DatagramPacket recPack = new DatagramPacket(recData,recData.length);
                socket.receive(recPack);

                String reply = new String(recPack.getData());

                System.out.println(recPack.getAddress().getHostAddress() + ":" + recPack.getPort() + " => " + reply.trim());
  
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
