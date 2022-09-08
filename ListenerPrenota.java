import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.*;

public class ListenerPrenota implements ActionListener {

    private final JTextField numeroPosti;
    private final JComboBox evento;
    private final JTextArea listone;
    public PrintWriter out; 

    public ListenerPrenota(JTextField numeroPosti, JComboBox evento, PrintWriter out, JTextArea listone){
    
        this.numeroPosti = numeroPosti;
        this.evento = evento;
        this.listone = listone;
        this.out = out;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        try {
            Integer.parseInt(numeroPosti.getText()); // verifico che sia stato inserito un intero e in caso contrario genero eccezione
            if(!numeroPosti.getText().equals("")){
                String risposta = evento.getSelectedItem() + "," + numeroPosti.getText(); 
                out.println(risposta);
                
                numeroPosti.setText("");
                listone.setText("");
            }

        } catch (NumberFormatException e0) {
            numeroPosti.setText("");
            System.out.println("Devi inserire un numero!");
            e0.printStackTrace();
        }
        
    }
    
}
