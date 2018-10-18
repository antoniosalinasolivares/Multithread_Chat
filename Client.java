import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{
		public PrintStream output;
		public Socket plug;
  	BufferedReader input;
    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));


    public Client(Socket s) throws IOException{
    this.input = new BufferedReader(new InputStreamReader(s.getInputStream()));
    this.output = new PrintStream(s.getOutputStream());
    this.plug = s;

    }

    public void run(){
      String str = null;
      while(true){
        try{
            if(keyboard.ready()){
              str = input.readLine();
              System.out.println("@" + str);
              str = "";
            }
            else{
            System.out.print("message: ");
            str = keyboard.readLine();
            this.output.println(str);
            str = "";
            }
        }catch(IOException e){
          System.out.println("Error");
        }finally{
          try{
            this.input.close();
            this.output.close();
          }catch(IOException e){
            System.out.println("Otro error");
          }finally{
            System.exit(1);
          }
        }
      }
    }

    public static void main(String[] args) throws IOException{
    	String host;
			int port;
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the host address: ");
			host =sc.nextLine();
			System.out.println("Enter the port: ");
			port= sc.nextInt();
      Socket s = new Socket(host,port);
      Client current = new Client(s);
      current.start();
    }
}
