/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author carrillo
 */
public class VentanaCalculadora extends JFrame {
   /** numero tecleado */
   private final JTextField jTextFieldPantallaResultados;
   private final JTextField jTextFieldPantallaOperacionesRealizadas;

   /** guarda el resultado de la operacion anterior o el número tecleado */
   private double resultado;

   /** para guardar la operacion a realizar */
   private String operacion;

   /** Los paneles donde colocaremos los botones */
   private final JPanel panelNumeros;
   private final JPanel panelOperaciones;
   private final JPanel panelJTextField;

   /** Indica si estamos iniciando o no una operación */
   private boolean nuevaOperacion = true;

   /**
    * Constructor. Crea los botones y componentes de la calculadora
    */
   @SuppressWarnings("OverridableMethodCallInConstructor")
   public VentanaCalculadora() {
        super("Calculadora");
        setSize(300, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Vamos a dibujar sobre el panel
        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(new BorderLayout());
        
        jTextFieldPantallaOperacionesRealizadas = new JTextField("", 20);
        jTextFieldPantallaOperacionesRealizadas.setBorder(new EmptyBorder(4, 4, 4, 4));
        jTextFieldPantallaOperacionesRealizadas.setFont(new Font("Arial", Font.BOLD, 25));
        jTextFieldPantallaOperacionesRealizadas.setHorizontalAlignment(JTextField.RIGHT);
        jTextFieldPantallaOperacionesRealizadas.setEditable(false);
        jTextFieldPantallaOperacionesRealizadas.setBackground(Color.WHITE);
        panel.add("North", jTextFieldPantallaOperacionesRealizadas);
        
        jTextFieldPantallaResultados = new JTextField("0", 20);
        jTextFieldPantallaResultados.setBorder(new EmptyBorder(4, 4, 4, 4));
        jTextFieldPantallaResultados.setFont(new Font("Arial", Font.BOLD, 25));
        jTextFieldPantallaResultados.setHorizontalAlignment(JTextField.RIGHT);
        jTextFieldPantallaResultados.setEditable(false);
        jTextFieldPantallaResultados.setBackground(Color.WHITE);
        //panel.add("North", jTextFieldPantallaResultados);

        panelNumeros = new JPanel();
        panelNumeros.setLayout(new GridLayout(4, 3));
        panelNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));

        for (int n = 9; n >= 0; n--) {
            nuevoBotonNumerico("" + n);
        }

        nuevoBotonNumerico(".");

        panel.add("Center", panelNumeros);

        panelOperaciones = new JPanel();
        panelOperaciones.setLayout(new GridLayout(6, 1));
        panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));

        nuevoBotonOperacion("+");
        nuevoBotonOperacion("-");
        nuevoBotonOperacion("*");
        nuevoBotonOperacion("/");
        nuevoBotonOperacion("=");
        nuevoBotonOperacion("CE");

        panel.add("East", panelOperaciones);

        validate();
   }

   /**
    * Crea un boton del teclado numérico y enlaza sus eventos con el listener
    * correspondiente
    * 
    * @param digito
    * boton a crear
    */
   private void nuevoBotonNumerico(String digito) {
        JButton btn = new JButton();
        btn.setText(digito);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                    JButton btn = (JButton) evt.getSource();
                    numeroPulsado(btn.getText());
            }
        });

        panelNumeros.add(btn);
   }

   /**
    * Crea un botón de operacion y lo enlaza con sus eventos.
    * 
    * @param operacion
    */
   private void nuevoBotonOperacion(String operacion) {
        JButton btn = new JButton(operacion);
        btn.setForeground(Color.RED);

        btn.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseReleased(MouseEvent evt) {
                     JButton btn = (JButton) evt.getSource();
                     operacionPulsado(btn.getText());
             }
        });

        panelOperaciones.add(btn);
   }

   /**
    * Gestiona las pulsaciones de teclas numéricas
    * 
    * @param digito
    *            tecla pulsada
    */
   private void numeroPulsado(String digito) {
        if (jTextFieldPantallaResultados.getText().equals("0") || nuevaOperacion) {
                jTextFieldPantallaResultados.setText(digito);
        } else {
                jTextFieldPantallaResultados.setText(jTextFieldPantallaResultados.getText() + digito);
        }
        nuevaOperacion = false;
   }

   /**
    * Gestiona el gestiona las pulsaciones de teclas de operación
    * 
    * @param tecla
    */
   private void operacionPulsado(String tecla) {
       switch (tecla) {
           case "=":
               calcularResultado();
               break;
           case "CE":
               resultado = 0;
               jTextFieldPantallaResultados.setText("");
               nuevaOperacion = true;
               break;
           default:
               operacion = tecla;
               if ((resultado > 0) && !nuevaOperacion) {
                   calcularResultado();
               } else {
                   resultado = new Double(jTextFieldPantallaResultados.getText());
               }
               break;
       }
       nuevaOperacion = true;
   }

   /**
    * Calcula el resultado y lo muestra por jTextFieldPantallaResultados
    */
   private void calcularResultado() {
       switch (operacion) {
           case "+":
               resultado += new Double(jTextFieldPantallaResultados.getText());
               break;
           case "-":
               resultado -= new Double(jTextFieldPantallaResultados.getText());
               break;
           case "/":
               resultado /= new Double(jTextFieldPantallaResultados.getText());
               break;
           case "*":
               resultado *= new Double(jTextFieldPantallaResultados.getText());
               break;
           default:
               break;
       }

        jTextFieldPantallaResultados.setText("" + resultado);
        operacion = "";
   }
}
