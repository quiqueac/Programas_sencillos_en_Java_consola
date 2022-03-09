/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa_4;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author usuario
 */
public class MiVentana extends JFrame implements ActionListener {
    
    private final JButton miBoton;
    private JButton otroBoton;
    private JButton botonEnviar;
    private JLabel labelGradosCentigrados;
    private JLabel labelGradosFahrenheit;
    private JTextField textFieldGradosCentigrados;
    private JTextField textFieldGradosFahrenheit;

    /**
     *
     * @throws HeadlessException
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public MiVentana() throws HeadlessException {
        miBoton = new JButton("Mi botón");
        miBoton.setBounds(0, 0, 100, 25);
        miBoton.addActionListener(this);
        componentesDeLaVentana();
        configuracionDeLaVentana();
    }
    
    private void configuracionDeLaVentana() {
        setLocationRelativeTo(null);
        setSize(220, 200);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setTitle("Programa 3");
        setLayout(null);
        
        add(labelGradosCentigrados);
        add(textFieldGradosCentigrados);
        add(labelGradosFahrenheit);
        add(textFieldGradosFahrenheit);
        add(botonEnviar);
    }
    
    private void componentesDeLaVentana() {
        otroBoton = new JButton("Mi otro botón");
        otroBoton.setBounds(0, 50, 150, 25);
        otroBoton.addActionListener(this);
        
        botonEnviar = new JButton("Convertir");
        botonEnviar.setBounds(15, 125, 100, 25);
        botonEnviar.addActionListener(this);
        
        labelGradosCentigrados = new JLabel();
        labelGradosCentigrados.setText("Grados centigrados: ");
        labelGradosCentigrados.setBounds(5, 5, 125, 15);
        
        textFieldGradosCentigrados = new JTextField();
        textFieldGradosCentigrados.setBounds(5, 25, 125, 20);
        
        labelGradosFahrenheit = new JLabel();
        labelGradosFahrenheit.setText("Grados fahrenheit: ");
        labelGradosFahrenheit.setBounds(5, 60, 125, 15);
        
        textFieldGradosFahrenheit = new JTextField();
        textFieldGradosFahrenheit.setBounds(5, 85, 125, 20);
        textFieldGradosFahrenheit.setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botonEnviar) {
            textFieldGradosFahrenheit.setText(conversionAGradosFahrenheit(textFieldGradosCentigrados.getText()));
        }
    }
    
    private String conversionAGradosFahrenheit(String valor) {
        double valorNumerico;
        double resultadoNumerico;
        String resultadoCadena;
        
        valorNumerico = Double.parseDouble(valor);
        resultadoNumerico = valorNumerico * 1.8 + 32;
        
        resultadoCadena = Double.toString(resultadoNumerico);
        
        return resultadoCadena;
    }
    
}
