/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.exceptions;

/**
 *
 * @author ls.hernandez10
 */
public class EventoViajeroLogicException extends Exception {

    /**
     * versión usada en la serialización de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto
     */
    public EventoViajeroLogicException() {
        //Constructor por defecto
    }

    /**
     * Constructor con un mensaje
     *
     * @param message mensaje de la excepción
     */
    public EventoViajeroLogicException(String message) {
        super(message);
    }

    /**
     * Constructor con una causa
     *
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public EventoViajeroLogicException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor con mensaje y causa.
     *
     * @param message mensaje de la excepción
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public EventoViajeroLogicException(String message, Throwable cause) {
        super(message, cause);
    }

}
