/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa_3;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Enrique Ayala
 * 
 */
/*
    Suma, resta, multiplicacion, division de fracciones

    Lo ingresas con letra y el resultado es con letra

    Denominador máximo: 100.
    Numerador máximo: 100.

    El resultado de denominador y numerador puede pasar de 100.
*/
public class Programa_3 {
    
    private static Connection miConexion;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        while(true) {
            programaDeFracciones();
        }
    }
    
    /**
     * Programa principal que realiza operaciones con 2 fracciones.
     */
    public static void programaDeFracciones() {
        Fraccion resultadoFraccion;
        String [] misFraccionesEnLetra;
        String [] resultadoDeMisFraccionesEnLetra;
        int [] misFraccionesEnNumero;
        int [] resultadoDeMisFraccionesEnNumero;
        
        misFraccionesEnLetra = setDatosDeFraccionesEnLetra();
        
        if(misFraccionesEnLetra == null) {
            JOptionPane.showMessageDialog(null, "No entiendo lo que acabas de escribir...");
            System.exit(0);
        }
        
        misFraccionesEnNumero = getDatosDeFraccionesEnNumero(misFraccionesEnLetra);
        
        if(misFraccionesEnNumero == null) {
            JOptionPane.showMessageDialog(null, "Has ingresado algo mal...");
            System.exit(0);
        }

        switch(misFraccionesEnNumero[4]) {
            case 0:
                resultadoFraccion = OperacionFraccion.suma(misFraccionesEnNumero);
                OperacionFraccion.simplificacion(resultadoFraccion);
                resultadoDeMisFraccionesEnNumero = getDatosDeFraccionesEnNumeroConArreglo(resultadoFraccion);
                resultadoDeMisFraccionesEnLetra = getDatosDeFraccionesEnLetra(resultadoDeMisFraccionesEnNumero);
                imprimirFraccionResultanteEnLetra(resultadoDeMisFraccionesEnLetra);
                break;
            case 1:
                resultadoFraccion = OperacionFraccion.resta(misFraccionesEnNumero);
                OperacionFraccion.simplificacion(resultadoFraccion);
                resultadoDeMisFraccionesEnNumero = getDatosDeFraccionesEnNumeroConArreglo(resultadoFraccion);
                resultadoDeMisFraccionesEnLetra = getDatosDeFraccionesEnLetra(resultadoDeMisFraccionesEnNumero);
                imprimirFraccionResultanteEnLetra(resultadoDeMisFraccionesEnLetra);
                break;
            case 2:
                resultadoFraccion = OperacionFraccion.multiplicacion(misFraccionesEnNumero);
                OperacionFraccion.simplificacion(resultadoFraccion);
                resultadoDeMisFraccionesEnNumero = getDatosDeFraccionesEnNumeroConArreglo(resultadoFraccion);
                resultadoDeMisFraccionesEnLetra = getDatosDeFraccionesEnLetra(resultadoDeMisFraccionesEnNumero);
                imprimirFraccionResultanteEnLetra(resultadoDeMisFraccionesEnLetra);
                break;
            case 3:
                resultadoFraccion = OperacionFraccion.division(misFraccionesEnNumero);
                OperacionFraccion.simplificacion(resultadoFraccion);
                resultadoDeMisFraccionesEnNumero = getDatosDeFraccionesEnNumeroConArreglo(resultadoFraccion);
                resultadoDeMisFraccionesEnLetra = getDatosDeFraccionesEnLetra(resultadoDeMisFraccionesEnNumero);
                imprimirFraccionResultanteEnLetra(resultadoDeMisFraccionesEnLetra);
                break;
        }
    }
    
    /**
     * Imprime la fracción resultante en formato de cadena.
     * @param resultadoDeMisFraccionesEnLetra el numerador y el denominador de la fracción resultante.
     */
    public static void imprimirFraccionResultanteEnLetra(String [] resultadoDeMisFraccionesEnLetra) {
        if(resultadoDeMisFraccionesEnLetra[0] == null || resultadoDeMisFraccionesEnLetra[1] == null) {
            JOptionPane.showMessageDialog(null, "El resultado es negativo...");
        }
        else {
            JOptionPane.showMessageDialog(null, "El resultado es: " + resultadoDeMisFraccionesEnLetra[0] + " " + resultadoDeMisFraccionesEnLetra[1] + ".");
        }
    }
    
    /**
     * Función privada que retorna el resultado de una fracción en forma de un arreglo de números.
     * @param resultadoFraccion la fracción resultante.
     * @return un arreglo de enteros con el numerador y el denominador de la fracción resultante.
     */
    private static int [] getDatosDeFraccionesEnNumeroConArreglo(Fraccion resultadoFraccion) {
        int [] resultadoDeMisFraccionesEnNumero;
        
        resultadoDeMisFraccionesEnNumero = new int[2];
        
        resultadoDeMisFraccionesEnNumero[0] = resultadoFraccion.getNumerador();
        resultadoDeMisFraccionesEnNumero[1] = resultadoFraccion.getDenominador();
        
        return resultadoDeMisFraccionesEnNumero;
    }
    
    /**
     * Función privada que retorna los datos de las 2 fracciones en formato de cadena.
     * @param misFraccionesEnNumero los datos de mis 2 fracciones.
     * @return un arreglo de cadenas con los numeradores y denominadores de las fracciones.
     */
    private static String [] getDatosDeFraccionesEnLetra(int [] misFraccionesEnNumero) {
        String [] misFraccionesEnLetra;
        
        misFraccionesEnLetra = new String[2];
        
        misFraccionesEnLetra[0] = consultaBaseDeDatos("SELECT numerador FROM numeradores WHERE id=?", misFraccionesEnNumero[0]);
        if(misFraccionesEnNumero[0] == 1) {
            misFraccionesEnLetra[1] = consultaBaseDeDatos("SELECT denominador FROM denominador WHERE id=?", misFraccionesEnNumero[1]);
        }
        else {
            misFraccionesEnLetra[1] = consultaBaseDeDatos("SELECT denominador FROM denominadores WHERE id=?", misFraccionesEnNumero[1]);
        }
        
        return misFraccionesEnLetra;
    }
    
    /**
     * Función privada que retorna los datos de las 2 fracciones en formato de numero.
     * @param misFraccionesEnLetra los datos de mis 2 fracciones.
     * @return un arreglo de cadenas con los numeradores y denominadores de las fracciones.
     */
    private static int [] getDatosDeFraccionesEnNumero(String [] misFraccionesEnLetra) {
        String [] numeradores;
        String [] denominadores;
        String primerNumerador;
        String primerDenominador;
        String operacion;
        String segundoNumerador;
        String segundoDenominador;
        int numeros [];
        int indice;
        
        numeradores = new String[2];
        denominadores = new String[2];
        numeros = new int[5];
        indice = -1;
        
        if("y".equals(misFraccionesEnLetra[1])) {
            primerNumerador = misFraccionesEnLetra[0] + " " + misFraccionesEnLetra[1] + " " + misFraccionesEnLetra[2];
            primerDenominador = misFraccionesEnLetra[3];
            operacion = misFraccionesEnLetra[4];
            
            if("y".equals(misFraccionesEnLetra[6])) {
                segundoNumerador = misFraccionesEnLetra[5] + " " + misFraccionesEnLetra[6] + " " + misFraccionesEnLetra[7];
                segundoDenominador = misFraccionesEnLetra[8];      
            }
            else {
                segundoNumerador = misFraccionesEnLetra[5];
                segundoDenominador = misFraccionesEnLetra[6];   
            }  
        }
        else {
            primerNumerador = misFraccionesEnLetra[0];
            primerDenominador = misFraccionesEnLetra[1];
            operacion = misFraccionesEnLetra[2];
            
            if("y".equals(misFraccionesEnLetra[4])) {
                segundoNumerador = misFraccionesEnLetra[3] + " " + misFraccionesEnLetra[4] + " " + misFraccionesEnLetra[5];
                segundoDenominador = misFraccionesEnLetra[6];
            }
            else {
                segundoNumerador = misFraccionesEnLetra[3];
                segundoDenominador = misFraccionesEnLetra[4];
            } 
        }
        
        numeradores[0] = primerNumerador;
        numeradores[1] = segundoNumerador;
        
        denominadores[0] = primerDenominador;
        denominadores[1] = segundoDenominador;
        
        for(int contador = 0; contador < 2; contador++) {
            indice++;
            numeros[indice] = consultaBaseDeDatos("SELECT id FROM numeradores WHERE numerador=?", numeradores[contador]);
        
            if(numeros[indice] > 100) {
                numeros = null;
                return numeros;
            }

            switch (numeros[indice]) {
                case 0:
                    numeros = null;
                    return numeros;
                case 1:
                    indice++;
                    numeros[indice] = consultaBaseDeDatos("SELECT id FROM denominador WHERE denominador=?", denominadores[contador]);
                    if(numeros[indice] == 0) {
                        numeros = null;
                        return numeros;
                    }
                    if(numeros[indice] > 100) {
                        numeros = null;
                        return numeros;
                    }
                    break;
                default:
                    indice++;
                    numeros[indice] = consultaBaseDeDatos("SELECT id FROM denominadores WHERE denominador=?", denominadores[contador]);
                    if(numeros[indice] == 0) {
                        numeros = null;
                        return numeros;
                    }
                    if(numeros[indice] > 100) {
                        numeros = null;
                        return numeros;
                    }
                    break;
            }
        }
        
        switch(operacion) {
            case "mas":
            case "más":
                numeros[4] = 0;
                break;
            case "menos":
                numeros[4] = 1;
                break;
            case "por":
                numeros[4] = 2;
                break;
            case "entre":
                numeros[4] = 3;
                break;
            default:
                numeros = null;
                break;
        }
        
        return numeros;
    }
    
    /**
     * Función privada que retorna el valor de una consulta a base de datos en MySQL.
     * @param consulta la consulta que se desea realizar.
     * @param valor el valor entero a buscar en la base de datos.
     * @return el valor en formato de cadena de ese valor, si es que existe.
     */
    private static String consultaBaseDeDatos(String consulta, int valor) {
        String valorCadena = null;
        
        try {
            try {
                miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/fraccion", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(Programa_3.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            PreparedStatement miSentencia;
            
            miSentencia = (PreparedStatement) miConexion.prepareStatement(consulta);
            miSentencia.setInt(1, valor);
            
            ResultSet rs;
            rs = miSentencia.executeQuery();
            
            try {
                while(rs.next()) {
                    valorCadena = rs.getString(1);
                }
            } finally {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Programa_3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return valorCadena;
    }
    
    /**
     * Función privada que retorna el valor de una consulta a base de datos.
     * @param consulta la consulta que se desea realizar.
     * @param valor el valor en formato de cadena a buscar en la base de datos.
     * @return el valor entero de ese valor, si es que existe.
     */
    private static int consultaBaseDeDatos(String consulta, String valor) {
        int valorNumero = 0;
        
        try {
            try {
                miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/fraccion", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(Programa_3.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            PreparedStatement miSentencia;
            
            miSentencia = (PreparedStatement) miConexion.prepareStatement(consulta);
            miSentencia.setString(1, valor);
            
            ResultSet rs;
            rs = miSentencia.executeQuery();
            
            try {
                while(rs.next()) {
                    valorNumero = rs.getInt(1);
                }
            } finally {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Programa_3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return valorNumero;
    }
    
    /**
     * Función privada para recibir los datos del usuario.
     * @return un arreglo de cadenas con los datos de las fracciones y la operación que se desea realizar.
     */
    private static String[] setDatosDeFraccionesEnLetra() {
        String oracion;
        String [] palabras;
        String [] palabrasCorrectas = null;
        int indicador = -1;
        
        oracion = JOptionPane.showInputDialog("Introduce tu operación de fracciones con letra");
        
        if(oracion == null) {
            System.exit(0);
        }
        
        palabras = oracion.split(" ");
        
        for (String palabra : palabras) {
            if (!"".equals(palabra)) {
                indicador++;
                palabras[indicador] = palabra;
            }
        }
        
        if(indicador < 8 && indicador > 3) {
            palabrasCorrectas = new String[7];
            
            System.arraycopy(palabras, 0, palabrasCorrectas, 0, palabras.length);
        }
        
        return palabrasCorrectas; 
    }
}
