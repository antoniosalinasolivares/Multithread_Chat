import java.io.BufferedReader;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.IOException;

public class ClientReceiver extends Thread{

	BufferedReader input;
	Socket plug;

	public ClientReceiver(Socket s) throws IOException{
		this.input= new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.plug = s;
	}

	@Override
	public void run(){
		String str;
		while (true) {
			try{
				if(input.ready()){
					str = input.readLine();
					System.out.println("<:" + str);
					str = "";
				}
			}catch(Exception e){
				System.out.println("come tierra");

			}finally{
				break;
			}
		}

	}
}
