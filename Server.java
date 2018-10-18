import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Server extends Thread{
    static ArrayList<Server> chat = new ArrayList<Server>();
    public BufferedReader input;
    public PrintStream output;
    public String username;

    public Server(Socket s) throws IOException{
        this.input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.output = new PrintStream(s.getOutputStream());

        this.username = s.getInetAddress().getCanonicalHostName();
    }

    public void send(String message) throws IOException{
        this.output.println(message);
    }


    public String receive() throws IOException{
        return this.input.readLine();
    }


    public void kick(){

    }

    public void broadcast(String message) throws IOException{
        for(Server temp : Server.chat){
            temp.send(this.username+ " says: " + message);

        }
    }

    public void print_list(){
        for(Server temp : Server.chat){
            System.out.println(temp.username);
        }
    }

    public void run(){
      try {
        while(true){
            broadcast(receive());
        }
      }catch (IOException e) {
        System.out.println("Hubo un error");
      }
    }

  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Introduce the port:");
    int port = sc.nextInt();
    ServerSocket ss = new ServerSocket(port);
    System.out.println("Server initialized");
    while(true){
      Socket s = ss.accept();
      System.out.println("Nuevo usuario conectado: "+ s.getInetAddress().getCanonicalHostName());
      System.out.println("Para aceptar, presione add:");
      if(sc.nextLine().equals("add")){
        Server sv = new Server(s);
        Server.chat.add(sv);
        sv.start();
        System.out.println("Añadido al chat.");
        sv.send("Bienvenido, weon");
      }
      else{
        s.close();
      }
    }
  }
}
