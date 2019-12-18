package moneycalculator.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import moneycalculator.model.ExchangeRate;

public class MainFrame extends JFrame {
    
    private JComboBox cb;
    private JTextField tf1,tf2;
    private JButton b1,b2;
    
    public MainFrame() {
        setTitle("Money Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,125);
        setLocationRelativeTo(null);
        
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        
        JPanel sup = new JPanel();
        sup.setLayout(new FlowLayout());
        
        JPanel inf = new JPanel();
        inf.setLayout(new FlowLayout());
        
        sup.add(new JLabel("Cantidad en € a convertir: "));
        tf1 = new JTextField(10);
        sup.add(tf1);
        sup.add(new JLabel(" en "));
        tf2 = new JTextField(10);
        sup.add(tf2);

        cb = new JComboBox();
        RestExchangeRateLoader e = new RestExchangeRateLoader();
        List<ExchangeRate> r = e.load();
        for (ExchangeRate r1 : r) {
            cb.addItem(r1.toString());
        }
        sup.add(cb);
        
        pane.add(sup, BorderLayout.NORTH);
        
        b1 = new JButton();
        b1.setText("Calculate");
        inf.add(b1);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String aux = tf1.getText();
                double cantidadInicial = Double.valueOf(aux);
                String divisa = (String) cb.getSelectedItem();
                double rate = 0;
                for (ExchangeRate divisa1 : r) {
                    if (divisa1.getTo().getCode().equals(divisa)) {
                        rate = divisa1.getRate();
                        break;
                    }
                }                
                tf2.setText(Double.toString(rate*cantidadInicial));
            }
        });
        
        b2 = new JButton();
        b2.setText("Clean");
        inf.add(b2);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf1.setText("");
                tf2.setText("");
            }
        });
        
        pane.add(inf, BorderLayout.SOUTH);
    }    
}
