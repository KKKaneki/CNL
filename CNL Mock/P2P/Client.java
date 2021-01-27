

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Client {
    public static byte[] sendData = new byte[1024];
    public static byte[] recvData = new byte[1024];
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while(true) {

                sendData = new byte[1024];
                System.out.print(" -> ");
                String se = br.readLine();
                sendData = se.getBytes();
                DatagramPacket sp = new DatagramPacket(sendData, sendData.length,InetAddress.getByName("127.0.0.1"),4444);

                ds.send(sp);
                System.out.println("Waiting for response...");
                recvData = new byte[1024];
                DatagramPacket rp = new DatagramPacket(recvData,recvData.length);
                ds.receive(rp);
                recvData = rp.getData();
                se = new String(recvData,0,recvData.length);
                System.out.println(rp.getAddress() + ":" + rp.getPort() + " -> " + se.trim());

            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
