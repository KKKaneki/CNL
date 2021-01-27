import java.io.*;
import java.lang.*;
import java.util.*;

public class Subnet {

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter IP : ");
        String ip = in.readLine();

        String[] ipPeriod = ip.split("\\.");
		
		int defaultMask[] =new int[32];
		for(int i=0;i<32;i++) {
			defaultMask[i]=1;
		}
		String one="1";
		String zero="0";

		int acnt = 8-Integer.toBinaryString(Integer.parseInt(ipPeriod[0])).length();
		int bcnt = 8 - Integer.toBinaryString(Integer.parseInt(ipPeriod[1])).length();
		int ccnt = 8 - Integer.toBinaryString(Integer.parseInt(ipPeriod[2])).length();
		int dcnt = 8 - Integer.toBinaryString(Integer.parseInt(ipPeriod[3])).length();

		String binaryIP[] = new String[4];
		binaryIP[0] = zero.repeat(acnt) + Integer.toBinaryString(Integer.parseInt(ipPeriod[0]));
		binaryIP[1] = zero.repeat(bcnt) + Integer.toBinaryString(Integer.parseInt(ipPeriod[1]));
		binaryIP[2] = zero.repeat(ccnt) + Integer.toBinaryString(Integer.parseInt(ipPeriod[2]));
		binaryIP[3] = zero.repeat(dcnt) + Integer.toBinaryString(Integer.parseInt(ipPeriod[3]));

		System.out.println("IP in Binary form : " + binaryIP[0] + "." + binaryIP[1] + "." + binaryIP[2] + "." + binaryIP[3]);


        if(ipPeriod.length == 4) {
			System.out.print("Enter the number of addresses in each subnet : ");
			int n = Integer.parseInt(in.readLine());
			int hostbits = (int)Math.ceil(Math.log(n)/Math.log(2));

            String ipClass = calculateClass(ipPeriod[0]);
            System.out.println("IP address belongs to Class : " + ipClass);
            String defMask = calculateSubnetMask(ipClass);
			System.out.println("Default Mask : " + defMask);
			
        
            if(ipClass == "A") {
				int subnetBits = (24 - hostbits);
				System.out.println("HOST BITS : " + hostbits + " SUBNET BITS:" + subnetBits);
				for(int i = 31 ; i > 31 - hostbits ; i-- ) {
					defaultMask[i] = 0;
				}

				System.out.println("No of subnets : " + Math.pow(2, subnetBits));
				System.out.println("No of hosts per subnet : " + (Math.pow(2, hostbits) - 2));

				showMask(defaultMask);
				System.out.println("\nNetwork Address :");
				networkAddr(binaryIP, hostbits);
				System.out.println("\nBroadcast Address :");
				broadcastAddr(binaryIP, hostbits);

            } else if(ipClass == "B"){
				int subnetBits = (16 - hostbits);
				System.out.println("HOST BITS : " + hostbits + " SUBNET BITS:" + subnetBits);
				for(int i = 31 ; i > 31 - hostbits ; i-- ) {
					defaultMask[i] = 0;
				}

				System.out.println("No of subnets : " + Math.pow(2, subnetBits));
				System.out.println("No of hosts per subnet : " + (Math.pow(2, hostbits) - 2));

				showMask(defaultMask);

				System.out.println("\nNetwork Address :");
				networkAddr(binaryIP, hostbits);
				System.out.println("\nBroadcast Address :");
				broadcastAddr(binaryIP, hostbits);

				
			} else if(ipClass == "C") {
				int subnetBits = (8 - hostbits);
				System.out.println("HOST BITS : " + hostbits + " SUBNET BITS:" + subnetBits + "\n" + "Subnet Mask :");
				for(int i = 31 ; i > 31 - hostbits ; i-- ) {
					defaultMask[i] = 0;
				}

				System.out.println("No of subnets : " + Math.pow(2, subnetBits));
				System.out.println("No of hosts per subnet : " + (Math.pow(2, hostbits) - 2));

				showMask(defaultMask);
				System.out.println("\nNetwork Address :");
				networkAddr(binaryIP, hostbits);
				System.out.println("\nBroadcast Address :");
				broadcastAddr(binaryIP, hostbits);
			}



        } else {
            System.out.println("Enter a valid IP");
        }
    }

    public static String calculateClass(String fPeriod){
        int f = Integer.parseInt(fPeriod);
        if(f <= 127) return "A";
        if(f >= 128 && f <= 191) return "B";
        if(f >= 192 && f <= 223) return "C";
        return "D";
    }
    
    public static String calculateSubnetMask(String ipClass){
        if(ipClass == "A") return "255.0.0.0";
        if(ipClass == "B") return "255.255.0.0";
        if(ipClass == "C") return "255.255.255.0";
        return "";
	}
	
	public static void showMask(int arr[]){
		int[] deciMask = new int[4];
		// BINARY
		System.out.print("Binary : ");
		for(int i = 0 ; i < 4 ; i++ ) {
			int p = 0;
			for(int j = 8*i ; j < 8*(i+1) ; j++) {
				if(arr[j] == 1) {
					p += (Double)Math.pow(2, 7 - j % 8);
				}
				System.out.print(arr[j]);
			}
			if(i != 3) System.out.print(".");
			deciMask[i] = p;
		}

		System.out.print("\nDecimal : " + deciMask[0] + "." + deciMask[1] + "." + deciMask[2] + "." + deciMask[3] + "\n");
	}

	public static void networkAddr(String[] binaryIP,int hostBits){
		int[] arr = new int[32];
		for(int j = 3 ; j >= 0; j--) {
			char[] ch = binaryIP[j].toCharArray();
			for(int i = 7 ; i >= 0; i--) {
				if(hostBits > 0) arr[8*j + i] = 0;
				else arr[8*j + i] = ch[i] - '0';
				hostBits--;
			}
		}		
		showMask(arr);
	}
   
	public static void broadcastAddr(String[] binaryIP,int hostBits){
		int[] arr = new int[32];
		for(int j = 3 ; j >= 0; j--) {
			char[] ch = binaryIP[j].toCharArray();
			for(int i = 7 ; i >= 0; i--) {
				if(hostBits > 0) arr[8*j + i] = 1;
				else arr[8*j + i] = ch[i] - '0';
				hostBits--;
			}
		}		
		showMask(arr);
	}
		


}


