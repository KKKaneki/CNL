import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

class Calculator implements Serializable {
    public  double first;
    public  char operator;
    public  double second;
}


public class Client {
    public static void main(String[] args) {
            while(true) {
                try {
                    Socket s = new Socket("localhost",6000);
                    Calculator calc = new Calculator();
                    Scanner scan = new Scanner(System.in);
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                    DataInputStream dis = new DataInputStream(s.getInputStream());


                    System.out.println("1. Addition");
                    System.out.println("2. Subtraction");
                    System.out.println("3. Multiplication");
                    System.out.println("4. Division");
                    System.out.println("5. Remainder");
                    System.out.println("6. Power");
                    System.out.print("Choose the operation you want to perform : ");
                    int choice = scan.nextInt();
        
                    if(choice == 1) calc.operator = '+';
                    else if(choice == 2) calc.operator = '-';
                    else if(choice == 3) calc.operator = '*';
                    else if(choice == 4) calc.operator = '/';
                    else if(choice == 5) calc.operator = '%';
                    else if(choice == 6) calc.operator = '^';
                
                    
                    System.out.println("Perform a" + calc.operator + "b ");
                    System.out.print("Enter first number : ");
                    calc.first = scan.nextFloat();
                    if(calc.operator == '/') {
                        int flag = 0;
                        while(calc.second == 0) { 
                            if(flag == 1) System.out.println("0 is not defined..");
                            System.out.print("Enter second number : ");
                            calc.second = scan.nextFloat();
                            flag = 1;
                        }
                    } else {
                        System.out.print("Enter second number : ");
                        calc.second = scan.nextFloat();
                    }
                    
                    oos.writeObject(calc);
                    
                    double result = dis.readDouble();

                    System.out.println("Result : " + calc.first + " " + calc.operator + " " + calc.second + " = " + result);

                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
    }
}
