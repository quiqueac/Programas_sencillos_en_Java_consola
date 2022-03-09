/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa_3;

/**
 * Clase utilitaria para las operaciones con las 2 fracciones.
 * @author Enrique Ayala
 */
public class OperacionFraccion {
    
    /**
     * Función que suma 2 fracciones.
     * @param misFraccionesConNumero los datos de las 2 fracciones.
     * @return el resultado en forma de un objeto de tipo fracción.
     */
    public static Fraccion suma(int [] misFraccionesConNumero) {
        Fraccion resultadoFraccion;
        int primerNumerador;
        int primerDenominador;
        int segundoNumerador;
        int segundoDenominador;
        int resultadoNumerador;
        int resultadoDenominador;
        
        resultadoFraccion = new Fraccion();
        primerNumerador = misFraccionesConNumero[0];
        primerDenominador = misFraccionesConNumero[1];
        segundoNumerador = misFraccionesConNumero[2];
        segundoDenominador = misFraccionesConNumero[3];
        
        if(primerDenominador == segundoDenominador) {
            resultadoDenominador = primerDenominador;
            resultadoFraccion.setDenominador(resultadoDenominador);
            
            resultadoNumerador = primerNumerador + segundoNumerador;
            resultadoFraccion.setNumerador(resultadoNumerador);
        }
        else {
            resultadoDenominador = primerDenominador * segundoDenominador;
            resultadoNumerador = primerNumerador * segundoDenominador + segundoNumerador * primerDenominador;
            
            resultadoFraccion.setDenominador(resultadoDenominador);
            resultadoFraccion.setNumerador(resultadoNumerador);
        }
        
        return resultadoFraccion;
    }
    
    /**
     * Función que resta 2 fracciones.
     * @param misFraccionesConNumero los datos de las 2 fracciones.
     * @return el resultado en forma de un objeto de tipo fracción.
     */
    public static Fraccion resta(int [] misFraccionesConNumero) {
        Fraccion resultadoFraccion;
        int primerNumerador;
        int primerDenominador;
        int segundoNumerador;
        int segundoDenominador;
        int resultadoNumerador;
        int resultadoDenominador;
        
        resultadoFraccion = new Fraccion();
        primerNumerador = misFraccionesConNumero[0];
        primerDenominador = misFraccionesConNumero[1];
        segundoNumerador = misFraccionesConNumero[2];
        segundoDenominador = misFraccionesConNumero[3];
        
        if(primerDenominador == segundoDenominador) {
            resultadoDenominador = primerDenominador;
            resultadoFraccion.setDenominador(resultadoDenominador);
            
            resultadoNumerador = primerNumerador + segundoNumerador;
            resultadoFraccion.setNumerador(resultadoNumerador);
        }
        else {
            resultadoDenominador = primerDenominador * segundoDenominador;
            resultadoNumerador = primerNumerador * segundoDenominador - segundoNumerador * primerDenominador;
            
            resultadoFraccion.setDenominador(resultadoDenominador);
            resultadoFraccion.setNumerador(resultadoNumerador);
        }
        
        return resultadoFraccion;
    }
    
    /**
     * Función que múltiplica 2 fracciones.
     * @param misFraccionesConNumero los datos de las 2 fracciones.
     * @return el resultado en forma de un objeto de tipo fracción.
     */
    public static Fraccion multiplicacion(int [] misFraccionesConNumero) {
        Fraccion resultadoFraccion;
        int primerNumerador;
        int primerDenominador;
        int segundoNumerador;
        int segundoDenominador;
        int resultadoNumerador;
        int resultadoDenominador;
        
        resultadoFraccion = new Fraccion();
        primerNumerador = misFraccionesConNumero[0];
        primerDenominador = misFraccionesConNumero[1];
        segundoNumerador = misFraccionesConNumero[2];
        segundoDenominador = misFraccionesConNumero[3];

        resultadoDenominador = primerDenominador * segundoDenominador;
        resultadoNumerador = primerNumerador * segundoNumerador;
        
        resultadoFraccion.setNumerador(resultadoNumerador);
        resultadoFraccion.setDenominador(resultadoDenominador);
        
        return resultadoFraccion;
    }
    
    /**
     * Función que divide 2 fracciones.
     * @param misFraccionesConNumero los datos de las 2 fracciones.
     * @return el resultado en forma de un objeto de tipo fracción.
     */
    public static Fraccion division(int [] misFraccionesConNumero) {
        Fraccion resultadoFraccion;
        int primerNumerador;
        int primerDenominador;
        int segundoNumerador;
        int segundoDenominador;
        int resultadoNumerador;
        int resultadoDenominador;
        
        resultadoFraccion = new Fraccion();
        primerNumerador = misFraccionesConNumero[0];
        primerDenominador = misFraccionesConNumero[1];
        segundoNumerador = misFraccionesConNumero[2];
        segundoDenominador = misFraccionesConNumero[3];

        resultadoDenominador = primerDenominador * segundoNumerador;
        resultadoNumerador = primerNumerador * segundoDenominador;
        
        resultadoFraccion.setDenominador(resultadoDenominador);
        resultadoFraccion.setNumerador(resultadoNumerador);
        
        return resultadoFraccion;
    }
    
    /**
     * Función que simplifica una fracción.
     * @param miFraccion la fracción que será simplificada
     * @return el resultado en forma de un objeto de tipo fracción.
     */
    public static Fraccion simplificacion(Fraccion miFraccion) {
        int numerador;
        int denominador;
        int resultado;
        
        numerador = miFraccion.getNumerador();
        denominador = miFraccion.getDenominador();
        
        if((numerador % denominador) == 0) {
            resultado = numerador / denominador;
            miFraccion.setNumerador(resultado);
        }
        else if((denominador % numerador) == 0) {
            resultado = denominador / numerador;
            miFraccion.setNumerador(1);
            miFraccion.setDenominador(resultado);
        }
        
        return miFraccion;
    }
    
}
