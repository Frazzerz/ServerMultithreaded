import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ClientGUI extends JFrame{

    public PrintWriter out;
    JTextArea textArea;
    

    public ClientGUI(String[] scelta, PrintWriter out) throws IOException {
        super("Client");
        this.out = out;

        JPanel Panel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        Panel.setPreferredSize(new Dimension(600, 600));

        this.textArea = new JTextArea(11, 30);
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(24,24,16,12);
        c.ipadx = 10;
        c.ipady = 15;
        textArea.setEditable(false);
        Panel.add(textArea,c);
        Panel.setLayout(new GridBagLayout());
        getContentPane().add(Panel);

        PrintStream printStream = new PrintStream(new JTextAreaOutputStream(textArea));
        System.setOut(printStream);


        JLabel NumpostiLabel = new JLabel("Numero posti:");
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(24,12,8,12);
        c.ipady = 20;
        Panel.add(NumpostiLabel,c);

        JTextField numposti = new JTextField(12);
        c.gridx = 1;
        c.insets = new Insets(24,12,8,12);
        Panel.add(numposti,c);

        JLabel EventiLabel = new JLabel("Eventi:");
        c.gridx = 0;
        c.gridy = 2;
        Panel.add(EventiLabel,c);

        JComboBox EventiList = new JComboBox( scelta );
        c.gridx = 1;
        Panel.add(EventiList,c);

        JButton button = new JButton("Prenota");
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(24,24,16,12);
        c.ipadx = 10;
        c.ipady = 15;
        Panel.add(button, c);

        button.addActionListener(new ListenerPrenota(numposti, EventiList, out, textArea));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
}
