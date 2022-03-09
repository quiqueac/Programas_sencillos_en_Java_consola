/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa_2;

import java.awt.HeadlessException;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Enrique Paul Ayala Castillo
 */
public class Programa_2 {

    /**
     * @param args the command line arguments
     */
    private static int opcion;
    private static int [] arregloNumeros;
    private static int longitud;
    
    
    public Programa_2() {
        arregloNumeros = null;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Programa_2.menuOpciones();
    }
    
    /**
     * Menú principal.
     */
    public static void menuOpciones() {
        
        do {
            System.out.println("***** Menu *****");
            System.out.println("\n1)Arreglo de N números.");
            System.out.println("2)Triángulo de Pascal.");
            System.out.println("3)Palabra palíndroma.");
            System.out.println("4)Salir del programa.\n");
            
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la opcion"));
            } catch(HeadlessException | NumberFormatException ex) {
                System.exit(0);
            }

            switch(opcion) {
                case 1:
                    Programa_2.menuArregloDeNumeros();
                case 2:
                    int filas;
                    
                    System.out.println("\nTriángulo de Pascal.");
                    filas = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el número de filas"));
                    System.out.println();
                    Programa_2.trianguloDePascal(filas);
                    System.out.println();
                    break;
                case 3:
                    String palabra;
                    String palabraInvertida;
                    
                    System.out.println("\nPalabra palíndroma.");
                    palabra = JOptionPane.showInputDialog("Ingresa la palabra");
                    
                    palabraInvertida = Programa_2.palabraPalindroma(palabra);
                    
                    if(palabraInvertida != null) {
                        System.out.print("Palabra palíndroma: " + palabraInvertida + "\n\n");
                    }
                    else {
                        System.out.println("La palabra no es palíndroma.\n");
                    }
                    break;
                case 4:
                    System.out.println("Fin del programa.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ingresa una opción correcta.\n");
                    break;
            }
            
        } while(opcion != 4);
        
    }
    /**
     * Sub menú
     */
    public static void menuArregloDeNumeros() {
        do {
            System.out.println("\n***** Submenu *****");
            System.out.println("\n1)Crear arreglo de N números.");
            System.out.println("2)Promedio de arreglo de números.");
            System.out.println("3)Ordenar arreglo de números.");
            System.out.println("4)Regresar al menú anterior.\n");
            
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la opción"));

            switch(opcion) {
                case 1:
                    System.out.println("\nCrear arreglo de N números.");
                    Programa_2.opcionCrearArreglo();
                    break;
                case 2:
                    System.out.println("\nPromedio de arreglo de números.");
                    if (arregloNumeros == null) {
                        System.out.println("Primero debes crear el arreglo...\n");
                        Programa_2.opcionCrearArreglo();
                    }
                    System.out.println("El promedio es: " + Programa_2.promedio());
                    break;
                case 3:
                    System.out.println("\nOrdenar arreglo de números.");
                    if (arregloNumeros == null) {
                        System.out.println("Primero debes crear el arreglo...\n");
                        Programa_2.opcionCrearArreglo();
                    }
                    //Ordena el array
                    Arrays.sort(arregloNumeros);

                    //Mostramos el array ya ordenado
                    for (int i = 0; i < longitud; i++) {
                        if(i < longitud - 1) {
                            System.out.print(arregloNumeros[i] + ", ");
                        }
                        else {
                            System.out.print(arregloNumeros[i] + ".\n");
                        }
                    }
                    break;
                case 4:
                    System.out.println();
                    Programa_2.menuOpciones();
                    break;
                default:
                    System.out.println("Ingresa una opción correcta.\n");
                    break;
            }
            
        } while(opcion != 4);
    }
    
    /**
     * 
     * @param numeroDeFilas del triángulo de Pascal.
     */
    public static void trianguloDePascal(int numeroDeFilas) {
        int[] arreglo = new int[1];
        for (int i = 1; i <= numeroDeFilas; i++) {
            int[] x = new int[i];
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == (i - 1)) {
                    x[j] = 1;
                } else {
                    x[j] = arreglo[j] + arreglo[j - 1];
                }
                System.out.print(x[j] + " ");
            }
            arreglo = x;
            System.out.println();
        }
    }
    
    /**
     * 
     * @param palabra a ser comprobada si es palíndroma.
     * @return la palabra palíndroma o null.
     */
    public static String palabraPalindroma(String palabra) {
        String palabraSinEspacios;
        String palabraInvertida;
        
        palabraSinEspacios = palabra.replace(" ","");
        palabraInvertida = "";

        for (int i = palabraSinEspacios.length() - 1; i >= 0; i--) { 
            palabraInvertida = palabraInvertida + palabraSinEspacios.charAt(i); 
        }
        
        if (palabraSinEspacios.equals(palabraInvertida)) { 
            return palabraInvertida;
        }
        else {
            return null;
        }
    }
    
    /**
     * 
     * @param longitud del arreglo.
     */
    public static void crearArreglo(int longitud) {
        arregloNumeros = new int[longitud];
    }
    
    /**
     * Menú en donde se puede crear el arreglo.
     */
    public static void opcionCrearArreglo() {
        longitud = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la longitud del arreglo"));
        Programa_2.crearArreglo(longitud);

        System.out.println("Arreglo creado de " + longitud + " espacios.\n");
        Programa_2.instanciarArreglo();
        System.out.println();
    }
    
    /**
     * Crea um arreglo de N posiciones.
     */
    public static void instanciarArreglo() {
        int numero;
        for(int i = 0; i < longitud; i++) {
            numero = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el número de la posición " + i));
            arregloNumeros[i] = numero;
        }
        
    }
    
    /**
     * Calcula el promedio de un arreglo de números.
     * @return promedio de arreglo de N posiciones.
     */
    public static int promedio() {
        int suma = 0;
        int i;
        
        for(i = 0; i < arregloNumeros.length; i++) {
            suma += arregloNumeros[i];
        }
        
        return suma / i;
    }
    
}
