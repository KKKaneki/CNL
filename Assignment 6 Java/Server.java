import java.net.*;
import java.io.*;

class Calculator implements Serializable {
    public  double first;
    public  char operator;
    public  double second;
}

public class Server {
    public static ServerSocket ss;
    public static void main(String[] args) {
        try {
            ss = new ServerSocket(6000);

            while(true) {
                // WATING FOR CONNECTION FROM CLIENT
                handleOperation();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void handleOperation(){
        try {
            System.out.println("Waiting for client connection ... ");
            Socket s = ss.accept();
            System.out.println("Client has been connected " + s);
            ObjectInputStream obj = new ObjectInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            Calculator calc = (Calculator) obj.readObject();
            double result = 0;
            if(calc.operator == '+') {
                result = calc.first + calc.second;
            } else if(calc.operator == '-') {
                result = calc.first - calc.second;
            } else if(calc.operator == '*') {
                result = calc.first * calc.second;
            } else if(calc.operator == '/') {
                result = calc.first / calc.second;
            } else if(calc.operator == '^') {
                result = Math.pow(calc.first, calc.second);
            } else if(calc.operator == '%') {
                result = calc.first % calc.second;
            } 

            // CALCULATED THE RESULT IN THE SERVER
            dos.writeDouble(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
