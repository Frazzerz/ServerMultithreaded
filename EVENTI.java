import java.util.concurrent.ConcurrentHashMap;

public class EVENTI {
    
    public ConcurrentHashMap< String , Integer > ListaEventi ;
    public Integer numerodiEventi = 11;
    public String[] attivita ={"Lucca comics", "Concerto di Blanco", "Euroflora", "Eurovision", "Sanremo", "Sagra della polpetta",
    "Raduno cosplayer", "Pub dell'amico live", "Comicon", "Raduno di clowns", "Circo maximus"};


    public EVENTI() {

        this.ListaEventi = new ConcurrentHashMap< String , Integer>( numerodiEventi);

    }

    public void Crea( String nome, int posti) {
    
        if ( ListaEventi.get(nome) != null ){
            System.out.println( nome + " esiste già!");
            return;
        }
        ListaEventi.put( nome, posti);
    }
    
}
