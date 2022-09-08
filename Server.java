import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Map.Entry;

// Server class
public class Server {

	private static class ClientHandler implements Runnable {
		
		private final Socket clientSocket;
		public EVENTI programmaEventi;

		// Constructor
		public ClientHandler(Socket socket, EVENTI programmaEventi)
		{
			this.clientSocket = socket;
			this.programmaEventi = programmaEventi;
		}

		public void run()
		{
			PrintWriter out = null;
			BufferedReader in = null;
			String listone = "";

			try {
					
				// get the outputstream of client
				out = new PrintWriter(clientSocket.getOutputStream(), true);

				// get the inputstream of client
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String prenotazione;

				while (true) {
					
					for (Entry<String, Integer> entry : programmaEventi.ListaEventi.entrySet()) {
	
						listone += entry.getKey() + ":" + entry.getValue() + "/";
							
					}

					out.println(listone);
					listone = "";
					
					// writing the received message from client
					if( (prenotazione = in.readLine()) != null){

						System.out.printf(" Sent from the client: %s\n", prenotazione);
						String[] op = prenotazione.split(",");
						
						if((programmaEventi.ListaEventi.get(op[0]))- Integer.parseInt(op[1]) >= 0 ){
							programmaEventi.ListaEventi.put(op[0], (programmaEventi.ListaEventi.get(op[0]))- Integer.parseInt(op[1]));
							out.println("OK");
						}
						else{
							out.println("!OK");
						}
					}
					else 
						return;
				}

			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
					
				try {
					if (out != null)
						out.close();
	
					if (in != null) {
						in.close();
						clientSocket.close();
					}
				}

				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args)
	{
		ServerSocket server = null;
		EVENTI programmaEventi = new EVENTI();
		int max = 30000, min = 1000;
		Random random = new Random();
		for( int i=0 ; i < programmaEventi.numerodiEventi ; i++){
            programmaEventi.Crea(programmaEventi.attivita[i], (random.nextInt(max-min)+min));
        }

		try {
			// server is listening on port 1234
			server = new ServerSocket(1234);
			server.setReuseAddress(true);

			// running infinite loop for getting client request
			while (true) {

				// socket object to receive incoming client requests
				Socket client = server.accept();

				// Displaying that new client is connected to server
				System.out.println("New client connected"+ client.getInetAddress().getHostAddress());

				// create a new thread object
				ClientHandler clientSock = new ClientHandler(client, programmaEventi);

				// This thread will handle the client separately
				new Thread(clientSock).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (server != null) {
				try {
					server.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}