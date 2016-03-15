package co.edu.uniandes.rest.punajut.exceptions;

/**
 * Representa las excepciones de la lógica de ComunidadLogic
 *
 * @author ja.poveda10
 */
public class ComunidadLogicException extends Exception {

    /**
     * versión usada en la serialización de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto
     */
    public ComunidadLogicException() {
    }

    /**
     * Constructor con un mensaje
     *
     * @param message mensaje de la excepción
     */
    public ComunidadLogicException(String message) {
        super(message);
    }

    /**
     * Constructor con una causa
     *
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public ComunidadLogicException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor con mensaje y causa.
     *
     * @param message mensaje de la excepción
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public ComunidadLogicException(String message, Throwable cause) {
        super(message, cause);
    }
}
