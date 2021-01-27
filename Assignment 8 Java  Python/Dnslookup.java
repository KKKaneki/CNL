import java.net.*;
import java.util.*;


class Dnslookup {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print("DNS to IP\nEnter Hostname :");
            String host = scan.nextLine();
            System.out.println("IP :" + InetAddress.getByName(host).getHostAddress()); 
            
            System.out.print("IP to DNS\nEnter IP :");
            String ip = scan.nextLine();
            System.out.println("Domain :" + InetAddress.getByName(ip).getHostName()); 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}