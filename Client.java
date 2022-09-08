import java.io.*;
import java.net.*;

public class Client{
	
	public static void main(String[] args){
		
	// establish a connection by providing host and port number

		try (Socket socket = new Socket("localhost", 1234)) {
			
			// writing to server
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			// reading from server
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String listone; //lista eventi ottenuta dal server
			String[] eventiEPosti;	// parsing
			String[] temp;	// parsing intermedio
			String[] attivita;	// parsing per combobox
			boolean GUIcreata = false; // controllo GUI nel while
			String risposta; // risposta OK/!OK dal server

			while(true){
				
				listone = in.readLine();

				eventiEPosti=listone.split("/");

				attivita = new String[eventiEPosti.length];

				for(int i=0; i<eventiEPosti.length; i++){
					temp = eventiEPosti[i].split(":");
					attivita[i] = temp[0];
				}

				if(!GUIcreata){
					ClientGUI GUI = new ClientGUI(attivita, out);
					GUIcreata = true;
				}

				//stampa lista (su GUI)
				for(int i=0; i<eventiEPosti.length; i++){
					System.out.println(eventiEPosti[i]);
				}
				
				if((risposta = in.readLine()) != null){
					if (risposta.equals("OK")){
						System.out.println("Prenotazione effettuata con successo");
					}
					else if (risposta.equals("!OK")){
						System.out.println("Impossibile prenotare, riprova");
					}
				}

			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}