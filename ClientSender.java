import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSender extends Thread{

	public PrintStream output;
	public Socket plug;

	public ClientSender(Socket s) throws IOException{
		this.output = new PrintStream(s.getOutputStream());
		this.plug = s;
	}


	@Override
	public void run(){
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

		while(true){
			try{
				String str = "";
				while(!str.equals("salir")){
					System.out.print(">:");
					str = entrada.readLine();
					this.output.println(str);
					str = "";
				}
			}catch(IOException ex){
				System.out.println("Se cerro la conexion");
			}finally{
					break;
			}
		}
	}
}
